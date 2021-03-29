package game;

import java.io.IOException;
import java.util.UUID;
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

public class Server {
    public static final int PORT = 7766;

    private static final int RESPONSE_TIMEOUT_S = 10;

    private io.grpc.Server grpcServer;

    //TODO: There should be many of these -- 1 for each player
    private QuestionServiceBlockingStub questionServiceStub;

    public Server() {
        grpcServer = ServerBuilder
                .forPort(PORT)
                .addService(new LobbyService())
                .intercept(new ClientInfoInterceptor())
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

    public void askQuestion(String questionText) {
        //Build request 
        QuestionRequest.Builder requestBuilder = QuestionRequest.newBuilder();
        requestBuilder.setText(questionText);

        //Send request
        QuestionRequest request = requestBuilder.build();
        Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));

        QuestionResponse response = questionServiceStub
                .withDeadlineAfter(RESPONSE_TIMEOUT_S, TimeUnit.SECONDS)
                .askQuestion(request);

        Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(response)));
    }

    private class LobbyService extends LobbyServiceImplBase {

        @Override
        public void createLobby(CreateLobbyRequest request, StreamObserver<CreateLobbyResponse> responseObserver) {
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            CreateLobbyResponse.Builder responseBuilder = CreateLobbyResponse.newBuilder();
            responseBuilder.setLobbyId(UUID.randomUUID().toString());
            CreateLobbyResponse response = responseBuilder.build();

            //TODO: Create a lobby

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void joinLobby(JoinLobbyRequest request, StreamObserver<JoinLobbyResponse> responseObserver) {
            //TODO: add the player to the lobby.

            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            JoinLobbyResponse.Builder responseBuilder = JoinLobbyResponse.newBuilder();
            JoinLobbyResponse response = responseBuilder.build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    private class ClientInfoInterceptor implements ServerInterceptor {

        /**
         * Listens for a JoinLobby request and sets up a connection
         * to the player's QuestionService.
         */
        @Override
        public <ReqT, RespT> Listener<ReqT> interceptCall(
                ServerCall<ReqT, RespT> call,
                Metadata headers,
                ServerCallHandler<ReqT, RespT> next) {
            String callName = call.getMethodDescriptor().getFullMethodName();
            if (callName.equals("protobuf.LobbyService/JoinLobby")) {
                String address = call.getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR).toString();
                address = address.substring(1, address.indexOf(':'));
                Logger.logInfo(String.format("Connecting QuestionService at %s:%d", address, Client.PORT));

                ManagedChannel channel = ManagedChannelBuilder
                        .forAddress(address, Client.PORT)
                        .usePlaintext()
                        .build();
                questionServiceStub = QuestionServiceGrpc.newBlockingStub(channel);

                askQuestion("Is this a test question?");

                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    try {
                        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }
            return next.startCall(call, headers);
        }

    }
}
