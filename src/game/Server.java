package game;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
import protobuf.generated.QuestionServiceGrpc.QuestionServiceStub;
import protobuf.generated.QuestionServiceMessages.AskQuestionRequest;
import protobuf.generated.QuestionServiceMessages.AskQuestionResponse;
import utilities.Logger;
import utilities.ProtobufUtils;

public class Server {
    public static final int PORT = 7766;
    
    //How long the player should have to submit an answer in seconds
    private static final int QUESTION_DEADLINE_S = 10;

    private io.grpc.Server grpcServer;

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
    }

    public void start() throws IOException, InterruptedException {    
        grpcServer.start();
        grpcServer.awaitTermination();
    }

    private class LobbyService extends LobbyServiceImplBase {

        private Map<UUID, Lobby> lobbyMap = new HashMap<>();

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
                String question = "Example question " + i;
                for (Player player: players) {
                    AskQuestionRequest.Builder questionRequestBuilder = AskQuestionRequest.newBuilder();
                    questionRequestBuilder.setQuestion(question);
                    questionRequestBuilder.setDeadline(new Date().getTime() + QUESTION_DEADLINE_S * 1000);
                    
                    List<String> options = new ArrayList<String>(4);
                    options.add("Option A -- Question " + i);
                    options.add("Option B -- Question " + i);
                    options.add("Option C -- Question " + i);
                    options.add("Option D -- Question " + i);
                    questionRequestBuilder.addAllOptions(options);
                    
                    AskQuestionRequest questionRequest = questionRequestBuilder.build();
                    
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
