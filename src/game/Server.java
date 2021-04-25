package game;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import database.MongoConnection;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import protobuf.generated.AnswerServiceGrpc.AnswerServiceImplBase;
import protobuf.generated.AnswerServiceMessages.AnswerRequest;
import protobuf.generated.AnswerServiceMessages.AnswerResponse;
import protobuf.generated.LobbyServiceGrpc.LobbyServiceImplBase;
import protobuf.generated.LobbyServiceMessages;
import protobuf.generated.LobbyServiceMessages.CreateLobbyRequest;
import protobuf.generated.LobbyServiceMessages.CreateLobbyResponse;
import protobuf.generated.LobbyServiceMessages.JoinLobbyRequest;
import protobuf.generated.LobbyServiceMessages.JoinLobbyResponse;
import protobuf.generated.LobbyServiceMessages.StartGameRequest;
import protobuf.generated.LobbyServiceMessages.StartGameResponse;
import protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest;
import protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse;
import protobuf.generated.QuestionServiceGrpc;
import protobuf.generated.QuestionServiceMessages.AskQuestionRequest;
import protobuf.generated.QuestionServiceMessages.FinishGameRequest;
import protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest;
import protobuf.generated.QuestionServiceMessages.UpdateScoresRequest;
import utilities.Logger;
import utilities.ProtobufUtils;

public class Server {
    public static final int PORT = 7766;
    
    //How long the player should have to submit an answer in seconds
    private static final int QUESTION_DEADLINE_S = 10;

    private io.grpc.Server grpcServer;
    
    private Map<UUID, Lobby> lobbyMap = new HashMap<>();
    
    private MongoConnection dbConnection;
    
    public Server(String args) {
        grpcServer = ServerBuilder
                .forPort(PORT)
                .addService(new LobbyService())
                .addService(new AnswerService())
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                grpcServer.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        
        //dbConnection = new MongoConnection("localhost", 27017, "trivia","questions");
        dbConnection = new MongoConnection(args, "trivia", "questions");
    }

    public void start() throws IOException, InterruptedException {    
        grpcServer.start();
        grpcServer.awaitTermination();
    }

    private class LobbyService extends LobbyServiceImplBase {

        @Override
        public void createLobby(CreateLobbyRequest request, StreamObserver<CreateLobbyResponse> responseObserver) {
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            Lobby lobbyToBeCreated = new Lobby();
            lobbyMap.put(lobbyToBeCreated.getLobbyID(), lobbyToBeCreated);
            
            CreateLobbyResponse.Builder responseBuilder = CreateLobbyResponse.newBuilder();
            responseBuilder.setLobbyId(lobbyToBeCreated.getLobbyID().toString());
            CreateLobbyResponse response = responseBuilder.build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void joinLobby(JoinLobbyRequest request,
                              StreamObserver<LobbyServiceMessages.JoinLobbyResponse> responseObserver) {

            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", request.getPlayerPort()).usePlaintext().build();
            Player player = new Player(UUID.fromString(request.getPlayerId()), request.getPlayerName(), QuestionServiceGrpc.newBlockingStub(channel));
            
            UUID lobbyID = UUID.fromString(request.getLobbyId());

            Lobby lobby = lobbyMap.get(lobbyID);
            lobby.addPlayerToLobby(player);
            
            UpdateLobbyPlayersRequest.Builder updatePlayersRequestBuilder = UpdateLobbyPlayersRequest.newBuilder();
            updatePlayersRequestBuilder.addAllPlayerNames(lobby.getPlayers().stream().map(p -> p.getName()).collect(Collectors.toList()));
            UpdateLobbyPlayersRequest updatePlayersRequest = updatePlayersRequestBuilder.build();
            
            for (Player p : lobby.getPlayers()) {
                new Thread(() -> {
                    p.getQuestionServiceStub().updateLobbyPlayers(updatePlayersRequest);                       
                }).start();
            }
            
            JoinLobbyResponse.Builder responseBuilder = JoinLobbyResponse.newBuilder();
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
        
        @Override
        public void startGame(StartGameRequest request, StreamObserver<StartGameResponse> responseObserver) {
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            StartGameResponse.Builder responseBuilder = StartGameResponse.newBuilder();
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
            
            UUID lobbyID = UUID.fromString(request.getLobbyId());
            String errormsg = "";

            Lobby lobby = lobbyMap.get(lobbyID);

            List<Player> players = lobby.getPlayers();
            
            for (Player player: players) {
                protobuf.generated.QuestionServiceMessages.StartGameRequest.Builder startGameRequestBuilder = protobuf.generated.QuestionServiceMessages.StartGameRequest.newBuilder();
                startGameRequestBuilder.setLobbyId(lobbyID.toString());
                player.getQuestionServiceStub().startGame(startGameRequestBuilder.build());
            }

            for (int i = 1; i <= 10; ++i) {
                //Build an UpdateScoresRequest
                UpdateScoresRequest.Builder updateScoresRequestBuilder = UpdateScoresRequest.newBuilder();
                for (Player player : players) {
                    protobuf.generated.QuestionServiceMessages.Player.Builder playerBuilder = protobuf.generated.QuestionServiceMessages.Player.newBuilder();
                    playerBuilder.setName(player.getName());
                    playerBuilder.setScore(player.getScore());
                    updateScoresRequestBuilder.addPlayers(playerBuilder.build());
                }
                UpdateScoresRequest updateScoresRequest = updateScoresRequestBuilder.build();
                
                AskQuestionRequest.Builder questionRequestBuilder = AskQuestionRequest.newBuilder();

                //Build an AskQuestionRequest
                Map<String, String> currentQuestion = null;
                for(int j=0; j<5; j++) {
                    try {
                        currentQuestion = dbConnection.getQuestion(new Random().nextInt(100) + 1);
                        break;
                    } catch (Exception e) {
                        try {
                            System.out.println("Error fetching question. Trying again");
                            Thread.sleep(1000);
                        }catch (Exception es) {}
                    }
                }

                // Map<String, String> currentQuestion = dbConnection.getQuestion(new Random().nextInt(100) + 1);
                // if we where able to fetch a question within 5 tries, send the question
                // otherwise, break out of this loop and end the game prematurely
                if(currentQuestion != null) {
                    lobby.setCurrentQuestion(currentQuestion);

                    long questionDeadline = new Date().getTime() + QUESTION_DEADLINE_S * 1000;
                    currentQuestion.put("deadline", Long.toString(questionDeadline));

                    questionRequestBuilder.setNumber(i);
                    questionRequestBuilder.setQuestion(currentQuestion.get("question"));

                    List<String> options = new ArrayList<String>(4);
                    options.add(currentQuestion.get("option 1"));
                    options.add(currentQuestion.get("option 2"));
                    options.add(currentQuestion.get("option 3"));
                    options.add(currentQuestion.get("option 4"));
                    questionRequestBuilder.addAllOptions(options);

                    // testing code
                    //questionRequestBuilder.setDeadline(new Date().getTime() + 1000000 + QUESTION_DEADLINE_S * 1000);
                    questionRequestBuilder.setDeadline(new Date().getTime() + QUESTION_DEADLINE_S * 1000);

                    AskQuestionRequest questionRequest = questionRequestBuilder.build();


                    //Send the requests to each player
                    for (Player player : players) {
                        new Thread(() -> {
                            Logger.logInfo(String.format("Sending %s to player %s", ProtobufUtils.getPrintableMessage(updateScoresRequest), player.getName()));

                            player.getQuestionServiceStub().updateScores(updateScoresRequest);

                            Logger.logInfo(String.format("Sending %s to player %s", ProtobufUtils.getPrintableMessage(questionRequest), player.getName()));
                            player.getQuestionServiceStub().askQuestion(questionRequest);
                        }).start();
                    }

                    //Just a quick experiment to send questions every 10 seconds
                    try {
                        Thread.sleep(QUESTION_DEADLINE_S * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    errormsg = "Unable to fetch questions. Game ended";
                    break;
                }
            }
            
            // Build an UpdateScoresRequest
            FinishGameRequest.Builder finishGameRequestBuilder = FinishGameRequest.newBuilder();
            for (Player player : players.stream().sorted((a, b) -> b.getScore() - a.getScore()).collect(Collectors.toList())) {
                protobuf.generated.QuestionServiceMessages.Player.Builder playerBuilder = protobuf.generated.QuestionServiceMessages.Player.newBuilder();
                playerBuilder.setName(player.getName());
                playerBuilder.setScore(player.getScore());
                finishGameRequestBuilder.addPlayers(playerBuilder.build());
            }
            finishGameRequestBuilder.setErrorMsg(errormsg);

            FinishGameRequest finishGameRequest = finishGameRequestBuilder.build();
            
            //Send the requests to each player
            for (Player player: players) {
                new Thread(() -> {
                    Logger.logInfo(String.format("Sending %s to player %s", ProtobufUtils.getPrintableMessage(finishGameRequest), player.getName()));
                    player.getQuestionServiceStub().finishGame(finishGameRequest);                                        
                }).start();
            }
        }

        @Override
        public void synchronizeTime(SynchronizeTimeRequest request, StreamObserver<SynchronizeTimeResponse> responseObserver) {
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            SynchronizeTimeResponse.Builder responseBuilder = SynchronizeTimeResponse.newBuilder();
            // Testing code
            // long serverTime = new Date().getTime() + 1000000;
            // responseBuilder.setTimestamp(serverTime);
            // System.out.println(new Date(serverTime));
            responseBuilder.setTimestamp(new Date().getTime());

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();

        }

    }

    private class AnswerService extends AnswerServiceImplBase {
        
        @Override
        public void answer(AnswerRequest request, StreamObserver<AnswerResponse> responseObserver) {
                   
            AnswerResponse.Builder responseBuilder = AnswerResponse.newBuilder();
            Lobby lobby = lobbyMap.get(UUID.fromString(request.getLobbyId()));
            
            responseBuilder.setCorrectAnswer(lobby.getCurrentQuestion().get("answer"));
            if (request.getText().equals(lobby.getCurrentQuestion().get("answer"))) {
                responseBuilder.setCorrect(true);
                Optional<Player> player = lobbyMap.get(UUID.fromString(request.getLobbyId())).getPlayers().stream().filter(p -> p.getId().equals(UUID.fromString(request.getPlayerId()))).findFirst();
                if (player.isPresent()) {
                    long now = new Date().getTime();
                    long deadline = Long.parseLong(lobby.getCurrentQuestion().get("deadline"));
                    
                    int scoreToAdd = (int)(100 * ((deadline - now) / 1000.0)/QUESTION_DEADLINE_S);
                    scoreToAdd = Math.max(0, Math.min(100, scoreToAdd));
                    
                    player.get().incrementScore(scoreToAdd);
                }
            }
            else {
                responseBuilder.setCorrect(false);
            }   
                        
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }

    }

}
