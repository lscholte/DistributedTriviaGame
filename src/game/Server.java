package game;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
import protobuf.generated.QuestionServiceGrpc;
import protobuf.generated.QuestionServiceMessages.AskQuestionRequest;
import protobuf.generated.QuestionServiceMessages.AskQuestionResponse;
import protobuf.generated.QuestionServiceMessages.UpdateScoresRequest;
import protobuf.generated.QuestionServiceMessages.UpdateScoresResponse;
import utilities.Logger;
import utilities.ProtobufUtils;

public class Server {
    public static final int PORT = 7766;
    
    //How long the player should have to submit an answer in seconds
    private static final int QUESTION_DEADLINE_S = 10;

    private io.grpc.Server grpcServer;
    
    private Map<UUID, Lobby> lobbyMap = new HashMap<>();
    
    private MongoConnection dbConnection;

    public Server() {
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
        
        dbConnection = new MongoConnection("localhost", 27017, "trivia","questions");
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
            Player player = new Player(request.getPlayerName(), QuestionServiceGrpc.newStub(channel));
            
            UUID lobbyID = UUID.fromString(request.getLobbyId());

            Lobby lobby = lobbyMap.get(lobbyID);
            lobby.addPlayerToLobby(player);


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

            Lobby lobby = lobbyMap.get(lobbyID);

            List<Player> players = lobby.getPlayers();

            for (int i = 1; i <= 10; ++i) {
                //Build an UpdateScoresRequest
                UpdateScoresRequest.Builder updateScoresRequestBuilder = UpdateScoresRequest.newBuilder();
                for (Player player : players) {
                    UpdateScoresRequest.Player.Builder playerBuilder = UpdateScoresRequest.Player.newBuilder();
                    playerBuilder.setName(player.getName());
                    playerBuilder.setScore(player.getScore());
                    updateScoresRequestBuilder.addPlayers(playerBuilder.build());
                }
                UpdateScoresRequest updateScoresRequest = updateScoresRequestBuilder.build();
                
                //Build an AskQuestionRequest
                Map<String, String> question = dbConnection.getQuestion(new Random().nextInt(100) + 1);

                AskQuestionRequest.Builder questionRequestBuilder = AskQuestionRequest.newBuilder();
                questionRequestBuilder.setNumber(i);
                questionRequestBuilder.setQuestion(question.get("question"));
                
                List<String> options = new ArrayList<String>(4);
                options.add(question.get("option 1"));
                options.add(question.get("option 2"));
                options.add(question.get("option 3"));
                options.add(question.get("option 4"));
                questionRequestBuilder.addAllOptions(options);
                
                questionRequestBuilder.setDeadline(new Date().getTime() + QUESTION_DEADLINE_S * 1000);
                
                AskQuestionRequest questionRequest = questionRequestBuilder.build();
                
                //Send the requests to each player
                for (Player player: players) {
                    Logger.logInfo(String.format("Sending %s to player %s", ProtobufUtils.getPrintableMessage(updateScoresRequest), player.getName()));
                    player.getQuestionServiceStub().updateScores(updateScoresRequestBuilder.build(), new StreamObserver<UpdateScoresResponse>() {
                        @Override
                        public void onNext(UpdateScoresResponse response) {}
                        
                        @Override
                        public void onCompleted() {}
                        
                        @Override
                        public void onError(Throwable throwable) {}
                    });
                                        
                    Logger.logInfo(String.format("Sending %s to player %s", ProtobufUtils.getPrintableMessage(questionRequest), player.getName()));
                    player.getQuestionServiceStub().askQuestion(questionRequest, new StreamObserver<AskQuestionResponse>() {
                        @Override
                        public void onNext(AskQuestionResponse response) {}
                        
                        @Override
                        public void onCompleted() {}
                        
                        @Override
                        public void onError(Throwable throwable) {}
                    });
                }
                
                //Just a quick experiment to send questions every 10 seconds
                try {
                    Thread.sleep(QUESTION_DEADLINE_S * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class AnswerService extends AnswerServiceImplBase {
        
        @Override
        public void answer(AnswerRequest request, StreamObserver<AnswerResponse> responseObserver) {
            //TODO: Validate the answer given and accumulate a score
            
            AnswerResponse.Builder responseBuilder = AnswerResponse.newBuilder();
            responseBuilder.setCorrect(true); //TODO: or false if answer is wrong
                        
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }

    }

}
