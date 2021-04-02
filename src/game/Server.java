package game;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import client.Client;
import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.ServerBuilder;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.stub.StreamObserver;
import protobuf.generated.LobbyServiceGrpc.LobbyServiceImplBase;
import protobuf.generated.LobbyServiceMessages;
import protobuf.generated.LobbyServiceMessages.CreateLobbyRequest;
import protobuf.generated.LobbyServiceMessages.CreateLobbyResponse;
import protobuf.generated.LobbyServiceMessages.JoinLobbyRequest;
import protobuf.generated.LobbyServiceMessages.JoinLobbyResponse;
import protobuf.generated.QuestionServiceGrpc;
import protobuf.generated.QuestionServiceGrpc.QuestionServiceBlockingStub;
import protobuf.generated.QuestionServiceMessages.QuestionRequest;
import protobuf.generated.QuestionServiceMessages.QuestionResponse;
import utilities.Logger;
import utilities.ProtobufUtils;

public class Server extends QuestionServiceGrpc.QuestionServiceImplBase {
    public static final int PORT = 7766;

    private static final int RESPONSE_TIMEOUT_S = 10;

    private io.grpc.Server grpcServer;

    private QuestionServiceBlockingStub questionServiceStub;

    public Server() {
        grpcServer = ServerBuilder
                .forPort(PORT)
                .addService(new LobbyService())
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

    @Override
    public void askQuestion(QuestionRequest request, StreamObserver<QuestionResponse> responseObserver) {
        //Build request
        // Get the question text from db
        String questionText = "Example question";
        QuestionRequest.Builder requestBuilder = QuestionRequest.newBuilder();
        requestBuilder.setText(questionText);
    }

    private class LobbyService extends LobbyServiceImplBase {

        private Map<UUID, Lobby> lobbyMap = new HashMap<>();
        private Map<String, StreamObserver<LobbyServiceMessages.questionStream>> observers = new HashMap<>();

        @Override
        public void createLobby(CreateLobbyRequest request, StreamObserver<CreateLobbyResponse> responseObserver) {
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            CreateLobbyResponse.Builder responseBuilder = CreateLobbyResponse.newBuilder();
            responseBuilder.setLobbyId(UUID.randomUUID().toString());
            CreateLobbyResponse response = responseBuilder.build();

            Lobby lobbyToBeCreated = new Lobby();
            lobbyMap.put(lobbyToBeCreated.getLobbyID(), lobbyToBeCreated);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void joinLobby(JoinLobbyRequest request,
                              StreamObserver<LobbyServiceMessages.questionStream> responseObserver) {

            String lobbyID = request.getLobbyId();
            String playerName = request.getPlayerName();

            Lobby lobby = lobbyMap.get(lobbyID);
            lobby.addPlayerToLobby(playerName);


            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            // Add the observer to map for that lobby
            observers.put(playerName, responseObserver);

            // Wait for the round to start
            // Once the round has started
            // Get a list of players in the lobby
            List<String> players = lobby.getPlayers();
            // Send the question to each player in the lobby
            String question = "Example question";
            for(String player: players){
                StreamObserver<LobbyServiceMessages.questionStream> observer = observers.get(player);
                LobbyServiceMessages.questionStream.Builder builder = LobbyServiceMessages.questionStream.newBuilder();
                builder.setQuestion(question);
                observer.onNext(builder.build());
            }
            // Once the round is completed
            for(String player: players){
                StreamObserver<LobbyServiceMessages.questionStream> observer = observers.get(player);
                observer.onCompleted();
            }
        }
    }

    private class Lobby{
        private UUID lobbyID;
        private List<String> players;

        public Lobby(){
            lobbyID = UUID.randomUUID();
            players = new ArrayList<>();
        }

        public void addPlayerToLobby(String playerID){
            players.add(playerID);
        }

        public UUID getLobbyID(){
            return lobbyID;
        }

        public List<String> getPlayers(){
            return players;
        }
    }
}
