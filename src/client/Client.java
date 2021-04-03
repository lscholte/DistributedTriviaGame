package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import GUI.Quiz;
import game.Server;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;
import io.grpc.Status.Code;
import io.grpc.stub.StreamObserver;
import protobuf.generated.AnswerServiceGrpc;
import protobuf.generated.AnswerServiceGrpc.AnswerServiceBlockingStub;
import protobuf.generated.AnswerServiceMessages.AnswerRequest;
import protobuf.generated.AnswerServiceMessages.AnswerResponse;
import protobuf.generated.LobbyServiceGrpc;
import protobuf.generated.LobbyServiceGrpc.LobbyServiceBlockingStub;
import protobuf.generated.LobbyServiceMessages.CreateLobbyRequest;
import protobuf.generated.LobbyServiceMessages.CreateLobbyResponse;
import protobuf.generated.LobbyServiceMessages.JoinLobbyError;
import protobuf.generated.LobbyServiceMessages.JoinLobbyRequest;
import protobuf.generated.LobbyServiceMessages.JoinLobbyResponse;
import protobuf.generated.LobbyServiceMessages.QuestionStream;
import protobuf.generated.QuestionServiceGrpc.QuestionServiceImplBase;
import protobuf.generated.QuestionServiceMessages.QuestionRequest;
import protobuf.generated.QuestionServiceMessages.QuestionResponse;
import io.grpc.StatusRuntimeException;
import utilities.Logger;
import utilities.ProtobufUtils;

public class Client {

    public static final int PORT = 7767;

    private static final int RESPONSE_TIMEOUT_S = 10;

    private LobbyServiceBlockingStub lobbyServiceStub;
    private AnswerServiceBlockingStub answerServiceStub;

    private io.grpc.Server grpcServer;

    private ManagedChannel channel;

    private Quiz gui;

    public Client() throws UnknownHostException, IOException {
        gui = new Quiz();
        channel = ManagedChannelBuilder.forAddress("localhost", Server.PORT).usePlaintext().build();
        lobbyServiceStub = LobbyServiceGrpc.newBlockingStub(channel);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    public void start() throws IOException, InterruptedException {
        UUID lobbyUuid = createLobby();
        if (lobbyUuid != null) {

            grpcServer = ServerBuilder.forPort(PORT).addService(new QuestionService()).build();

            grpcServer.start();

            joinLobby(lobbyUuid, "Test Player");

            answerServiceStub = AnswerServiceGrpc.newBlockingStub(channel);
        }
    }

    public UUID createLobby() {
        // Build request
        CreateLobbyRequest.Builder requestBuilder = CreateLobbyRequest.newBuilder();

        // Send request
        CreateLobbyRequest request = requestBuilder.build();
        Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));

        UUID lobbyUuid = null;
        try {
            CreateLobbyResponse response = lobbyServiceStub
                    .withDeadlineAfter(RESPONSE_TIMEOUT_S, TimeUnit.SECONDS).createLobby(request);

            Logger.logInfo(
                    String.format("Received %s", ProtobufUtils.getPrintableMessage(response)));

            if (response.hasLobbyId()) {
                lobbyUuid = UUID.fromString(response.getLobbyId());
                Logger.logInfo(
                        String.format("A lobby with ID %s was created", response.getLobbyId()));
            } else {
                Logger.logInfo("A lobby was not created");
            }
        } catch (StatusRuntimeException e) {
            handleGrpcError("CreateLobby", e.getStatus().getCode());
        }

        return lobbyUuid;
    }

    public void joinLobby(UUID lobbyUuid, String playerName) {
        // Build request
        JoinLobbyRequest.Builder requestBuilder = JoinLobbyRequest.newBuilder();
        requestBuilder.setLobbyId(lobbyUuid.toString());
        requestBuilder.setPlayerName(playerName);

        // Send request
        JoinLobbyRequest request = requestBuilder.build();
        Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));

        try {
            Iterator<QuestionStream> response = lobbyServiceStub
                    .withDeadlineAfter(RESPONSE_TIMEOUT_S, TimeUnit.SECONDS)
                    .joinLobby(request);

//            Logger.logInfo(
//                    String.format("Received %s", ProtobufUtils.getPrintableMessage(response)));
//
//            if (response.getError() == JoinLobbyError.SUCCESS) {
//                Logger.logInfo(String.format("Successfully joined lobby %s as %s",
//                        lobbyUuid.toString(), playerName));
//            } else {
//                Logger.logInfo("Failed to join the lobby");
//            }
        } catch (StatusRuntimeException e) {
            handleGrpcError("JoinLobby", e.getStatus().getCode());
        }
    }

    public void answer(String answerText) {
        // Build request
        AnswerRequest.Builder requestBuilder = AnswerRequest.newBuilder();
        requestBuilder.setText(answerText);

        // Send request
        AnswerRequest request = requestBuilder.build();
        Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));

        try {
            AnswerResponse response = answerServiceStub
                    .withDeadlineAfter(RESPONSE_TIMEOUT_S, TimeUnit.SECONDS).answer(request);

            Logger.logInfo(
                    String.format("Received %s", ProtobufUtils.getPrintableMessage(response)));

            if (response.getCorrect()) {
                Logger.logInfo("Answer was correct");
            } else {
                Logger.logInfo("Answer was incorrect");
            }
        } catch (StatusRuntimeException e) {
            handleGrpcError("Answer", e.getStatus().getCode());
        }
    }

    private void handleGrpcError(String requestType, Code code) {
        switch (code) {
        case UNAVAILABLE:
            Logger.logError(String.format("%s failed because server is unavailable", requestType));
            break;
        case DEADLINE_EXCEEDED:
            Logger.logError(String.format("%s timed out waiting for response", requestType));
            break;
        default:
            Logger.logError(String.format("%s failed with error status code %s", requestType,
                    code.toString()));
            break;
        }
    }

    private class QuestionService extends QuestionServiceImplBase {

        @Override
        public void askQuestion(QuestionRequest request,
                StreamObserver<QuestionResponse> responseObserver) {
            Logger.logInfo(
                    String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));
            Logger.logInfo(String.format("Question: ", request.getText()));

            gui.nextQuestion(request.getText(), new Date(new Date().getTime() + 15 * 1000));

            QuestionResponse.Builder responseBuilder = QuestionResponse.newBuilder();
            QuestionResponse response = responseBuilder.build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
