package game;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import database.MongoConnection;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;
import io.grpc.StatusRuntimeException;
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
import protobuf.generated.QuestionServiceMessages.AskQuestionRequest;
import protobuf.generated.QuestionServiceMessages.FinishGameRequest;
import protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest;
import protobuf.generated.QuestionServiceMessages.UpdateScoresRequest;
import protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest;
import protobuf.generated.TwoPhaseCommitCoordinatorServiceGrpc;
import protobuf.generated.TwoPhaseCommitCoordinatorServiceGrpc.TwoPhaseCommitCoordinatorServiceBlockingStub;
import protobuf.generated.TwoPhaseCommitMessages.AddLobbyRequest;
import protobuf.generated.TwoPhaseCommitMessages.AddPlayerRequest;
import protobuf.generated.TwoPhaseCommitMessages.RequestDetails;
import protobuf.generated.TwoPhaseCommitMessages.UpdatePlayerRequest;
import protobuf.generated.TwoPhaseCommitMessages.UpdateQuestionRequest;
import protobuf.generated.TwoPhaseCommitMessages.UpdateQuestionRequest.Question;
import protobuf.generated.TwoPhaseCommitMessages.UpdateQuestionRequest.Question.KeyValue;
import protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest;
import protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse;
import protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest;
import protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse;
import protobuf.generated.TwoPhaseCommitServerServiceGrpc.TwoPhaseCommitServerServiceImplBase;
import utilities.Logger;
import utilities.ProtobufUtils;

public class Server {
    
    //How long the player should have to submit an answer in seconds
    private static final int QUESTION_DEADLINE_S = 10;

    private io.grpc.Server grpcServer;
    
    private Map<UUID, Lobby> lobbyMap = new HashMap<>();
    private Map<UUID, Player> playerMap = new HashMap<>();
        
    private MongoConnection dbConnection;
    
    TwoPhaseCommitCoordinatorServiceBlockingStub coordinatorServiceStub;
    
    public Server(int port) {
        grpcServer = ServerBuilder
                .forPort(port)
                .addService(new LobbyService())
                .addService(new AnswerService())
                .addService(new TwoPhaseCommitServerService())
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
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000).usePlaintext().build();
        coordinatorServiceStub = TwoPhaseCommitCoordinatorServiceGrpc.newBlockingStub(channel);
    }

    public void start() throws IOException, InterruptedException {    
        grpcServer.start();
        grpcServer.awaitTermination();
    }
    
    private boolean twoPhaseCommit(RequestDetails requestDetails) {        
        TwoPhaseCommitRequest.Builder requestBuilder = TwoPhaseCommitRequest.newBuilder();
        requestBuilder.setDetails(requestDetails);
        try {
            return coordinatorServiceStub.twoPhaseCommit(requestBuilder.build()).getCommitted();

        }
        catch (StatusRuntimeException e) {
            ProtobufUtils.handleGrpcError("TwoPhaseCommit", e.getStatus().getCode());
        }
        return false;
    }

    private class LobbyService extends LobbyServiceImplBase {

        @Override
        public void createLobby(CreateLobbyRequest request, StreamObserver<CreateLobbyResponse> responseObserver) {
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            UUID lobbyId = UUID.randomUUID();
            
            AddLobbyRequest.Builder addLobbyRequestBuilder = AddLobbyRequest.newBuilder();
            addLobbyRequestBuilder.setLobbyId(lobbyId.toString());
            
            RequestDetails.Builder requestDetailsBuilder = RequestDetails.newBuilder();
            requestDetailsBuilder.setAddLobbyRequest(addLobbyRequestBuilder.build());
            
            CreateLobbyResponse.Builder responseBuilder = CreateLobbyResponse.newBuilder();

            if (!twoPhaseCommit(requestDetailsBuilder.build())) {
                responseObserver.onNext(responseBuilder.build());
                responseObserver.onCompleted();
                return;
            }
            
            responseBuilder.setLobbyId(lobbyId.toString());

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }

        @Override
        public void joinLobby(JoinLobbyRequest request,
                              StreamObserver<LobbyServiceMessages.JoinLobbyResponse> responseObserver) {

            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            AddPlayerRequest.Builder addPlayerRequestBuilder = AddPlayerRequest.newBuilder();
            addPlayerRequestBuilder.setLobbyId(request.getLobbyId());
            addPlayerRequestBuilder.setPlayerId(request.getPlayerId());
            addPlayerRequestBuilder.setPlayerName(request.getPlayerName());
            addPlayerRequestBuilder.setPlayerIp("localhost");
            addPlayerRequestBuilder.setPlayerPort(request.getPlayerPort());
            
            RequestDetails.Builder requestDetailsBuilder = RequestDetails.newBuilder();
            requestDetailsBuilder.setAddPlayerRequest(addPlayerRequestBuilder.build());

            if (!twoPhaseCommit(requestDetailsBuilder.build())) {
                JoinLobbyResponse.Builder responseBuilder = JoinLobbyResponse.newBuilder();
                responseObserver.onNext(responseBuilder.build());
                responseObserver.onCompleted();
                return;
            }
            
            UUID lobbyID = UUID.fromString(request.getLobbyId());

            Lobby lobby = lobbyMap.get(lobbyID);
            
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
            Lobby lobby = lobbyMap.get(lobbyID);
            List<Player> players = lobby.getPlayers();
            
            for (Player player : players) {
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

                Map<String, String> currentQuestion = dbConnection.getQuestion(new Random().nextInt(100) + 1);
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

                UpdateQuestionRequest.Builder updateQuestionRequestBuilder = UpdateQuestionRequest.newBuilder();
                updateQuestionRequestBuilder.setLobbyId(request.getLobbyId());
                Question.Builder questionBuilder = Question.newBuilder();
                
                for (Map.Entry<String, String> entry : currentQuestion.entrySet()) {
                    
                    KeyValue.Builder keyValueBuilder = KeyValue.newBuilder();
                    keyValueBuilder.setKey(entry.getKey());
                    keyValueBuilder.setValue(entry.getValue());
                    
                    questionBuilder.addEntries(keyValueBuilder.build());
                }
                
                updateQuestionRequestBuilder.setCurrentQuestion(questionBuilder.build());
                
                RequestDetails.Builder requestDetailsBuilder = RequestDetails.newBuilder();
                requestDetailsBuilder.setUpdateQuestionRequest(updateQuestionRequestBuilder.build());
                twoPhaseCommit(requestDetailsBuilder.build());
                
                lobby = lobbyMap.get(lobbyID);
                players = lobby.getPlayers();
                
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
                
                //Send question every 10 seconds
                try {
                    Thread.sleep(QUESTION_DEADLINE_S * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            //Build an UpdateScoresRequest
            FinishGameRequest.Builder finishGameRequestBuilder = FinishGameRequest.newBuilder();
            for (Player player : players.stream().sorted((a, b) -> b.getScore() - a.getScore()).collect(Collectors.toList())) {
                protobuf.generated.QuestionServiceMessages.Player.Builder playerBuilder = protobuf.generated.QuestionServiceMessages.Player.newBuilder();
                playerBuilder.setName(player.getName());
                playerBuilder.setScore(player.getScore());
                finishGameRequestBuilder.addPlayers(playerBuilder.build());
            }
            FinishGameRequest finishGameRequest = finishGameRequestBuilder.build();
            
            //Send the requests to each player
            for (Player player : players) {
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
                long now = new Date().getTime();
                long deadline = Long.parseLong(lobby.getCurrentQuestion().get("deadline"));
                
                int scoreToAdd = (int)(100 * ((deadline - now) / 1000.0)/QUESTION_DEADLINE_S);
                scoreToAdd = Math.max(0, Math.min(100, scoreToAdd));
                                   
                UpdatePlayerRequest.Builder updatePlayerRequestBuilder = UpdatePlayerRequest.newBuilder();
                updatePlayerRequestBuilder.setPlayerId(request.getPlayerId());
                updatePlayerRequestBuilder.setScore(scoreToAdd);
                
                RequestDetails.Builder requestDetailsBuilder = RequestDetails.newBuilder();
                requestDetailsBuilder.setUpdatePlayerRequest(updatePlayerRequestBuilder.build());
                twoPhaseCommit(requestDetailsBuilder.build());
            }
            else {
                responseBuilder.setCorrect(false);
            }   
                        
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }

    }

    private class TwoPhaseCommitServerService extends TwoPhaseCommitServerServiceImplBase {
        
        @Override
        public void queryCommit(QueryCommitRequest request, StreamObserver<QueryCommitResponse> responseObserver) {
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            QueryCommitResponse.Builder responseBuilder = QueryCommitResponse.newBuilder();
            responseBuilder.setShouldCommit(true);
            
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
        
        @Override
        public void commit(CommitRequest request, StreamObserver<CommitResponse> responseObserver) {
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));
            
            switch (request.getDetails().getMsgCase()) {
            case ADD_LOBBY_REQUEST:
            {
                AddLobbyRequest addLobbyRequest = request.getDetails().getAddLobbyRequest();
                Lobby lobby = new Lobby(UUID.fromString(addLobbyRequest.getLobbyId()));
                lobbyMap.put(lobby.getLobbyID(), lobby);
                break;   
            }
            case ADD_PLAYER_REQUEST:
            {
                AddPlayerRequest addPlayerRequest = request.getDetails().getAddPlayerRequest();
                Player player = new Player(
                        UUID.fromString(addPlayerRequest.getPlayerId()),
                        addPlayerRequest.getPlayerName(),
                        InetSocketAddress.createUnresolved(addPlayerRequest.getPlayerIp(), addPlayerRequest.getPlayerPort()));
                        
                lobbyMap.get(UUID.fromString(addPlayerRequest.getLobbyId())).addPlayerToLobby(player);
                playerMap.put(player.getId(), player);
                break;   
            }
            case UPDATE_PLAYER_REQUEST:
            {
                UpdatePlayerRequest updatePlayerRequest = request.getDetails().getUpdatePlayerRequest();
                
                playerMap.get(UUID.fromString(updatePlayerRequest.getPlayerId())).incrementScore(updatePlayerRequest.getScore());
                break;   
            }
            case UPDATE_QUESTION_REQUEST:
            {
                UpdateQuestionRequest updateQuestionRequest = request.getDetails().getUpdateQuestionRequest();
                Lobby lobby = lobbyMap.get(UUID.fromString(updateQuestionRequest.getLobbyId()));
                
                Map<String, String> currentQuestion = new HashMap<>();
                updateQuestionRequest.getCurrentQuestion().getEntriesList().forEach(entry -> currentQuestion.put(entry.getKey(), entry.getValue()));
                lobby.setCurrentQuestion(currentQuestion);
                
                break;   
            }
            default:
                break;
            
            }
                        
            responseObserver.onNext(CommitResponse.newBuilder().build());
            responseObserver.onCompleted();
        }
    }
}
