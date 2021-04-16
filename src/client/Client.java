package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import GUI.LobbyScreen;
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
import protobuf.generated.LobbyServiceMessages.JoinLobbyRequest;
import protobuf.generated.LobbyServiceMessages.JoinLobbyResponse;
import protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest;
import protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse;
import protobuf.generated.LobbyServiceMessages.StartGameRequest;
import protobuf.generated.QuestionServiceGrpc.QuestionServiceImplBase;
import protobuf.generated.QuestionServiceMessages.AskQuestionRequest;
import protobuf.generated.QuestionServiceMessages.AskQuestionResponse;
import protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest;
import protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse;
import protobuf.generated.QuestionServiceMessages.UpdateScoresRequest;
import protobuf.generated.QuestionServiceMessages.UpdateScoresResponse;
import io.grpc.StatusRuntimeException;
import utilities.Logger;
import utilities.ProtobufUtils;

public class Client {

    private static final int RESPONSE_TIMEOUT_S = 10;

    private LobbyServiceBlockingStub lobbyServiceBlockingStub;
    private AnswerServiceBlockingStub answerServiceStub;

    private io.grpc.Server grpcServer;

    private ManagedChannel channel;

    private long timeDifference;
    private LobbyScreen lobbyGui;
    private Quiz gui;

    private UUID lobbyId;
    private UUID playerId;

    public Client() throws UnknownHostException, IOException {
        channel = ManagedChannelBuilder.forAddress("localhost", Server.PORT).usePlaintext().build();

        lobbyServiceBlockingStub = LobbyServiceGrpc.newBlockingStub(channel);
        answerServiceStub = AnswerServiceGrpc.newBlockingStub(channel);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        grpcServer = ServerBuilder.forPort(0).addService(new QuestionService()).build();
        grpcServer.start();
    }

    public void start() throws IOException, InterruptedException {
        lobbyGui = new LobbyScreen(this);
    }

    public UUID createLobby() {
        // Build request
        CreateLobbyRequest.Builder requestBuilder = CreateLobbyRequest.newBuilder();

        // Send request
        CreateLobbyRequest request = requestBuilder.build();
        Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));

        UUID lobbyUuid = null;
        try {
            CreateLobbyResponse response = lobbyServiceBlockingStub
                    .withDeadlineAfter(RESPONSE_TIMEOUT_S, TimeUnit.SECONDS)
                    .createLobby(request);

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
        requestBuilder.setPlayerPort(grpcServer.getPort());

        playerId = UUID.randomUUID();
        requestBuilder.setPlayerId(playerId.toString());

        // Send request
        JoinLobbyRequest request = requestBuilder.build();
        Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));

        try {
            JoinLobbyResponse response = lobbyServiceBlockingStub.joinLobby(request);
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(response)));
        } catch (StatusRuntimeException e) {
            handleGrpcError("JoinLobby", e.getStatus().getCode());
        }
    }

    public void startGame(UUID lobbyUuid) {
        // Build request
        StartGameRequest.Builder requestBuilder = StartGameRequest.newBuilder();
        requestBuilder.setLobbyId(lobbyUuid.toString());

        // Send request
        StartGameRequest request = requestBuilder.build();
        Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));

        try {
            lobbyServiceBlockingStub.startGame(request);
        }
        catch (StatusRuntimeException e) {
            handleGrpcError("StartGame", e.getStatus().getCode());
        }
    }

    public Pair<Boolean, String> answer(String answer) {
        // Build request
        AnswerRequest.Builder requestBuilder = AnswerRequest.newBuilder();
        requestBuilder.setLobbyId(lobbyId.toString());
        requestBuilder.setText(answer);
        requestBuilder.setPlayerId(playerId.toString());

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
                return Pair.of(true, response.getCorrectAnswer());
            } else {
                Logger.logInfo("Answer was incorrect");
                return Pair.of(false, response.getCorrectAnswer());
            }
        } catch (StatusRuntimeException e) {
            handleGrpcError("Answer", e.getStatus().getCode());
        }
        return Pair.of(false, "");
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
        public void startGame(protobuf.generated.QuestionServiceMessages.StartGameRequest request,
                              StreamObserver<protobuf.generated.QuestionServiceMessages.StartGameResponse> responseObserver) {

            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            long serverTime = 0, startTime = System.currentTimeMillis();
            SynchronizeTimeRequest timeBuilder = SynchronizeTimeRequest.newBuilder().build();
            Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(timeBuilder)));
            try{
                SynchronizeTimeResponse response = lobbyServiceBlockingStub.synchronizeTime(timeBuilder);

                Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(response)));
                serverTime = response.getTimestamp();
            } catch (StatusRuntimeException e) {
                handleGrpcError("Time Synchronization", e.getStatus().getCode());
            }
            long endTime = System.currentTimeMillis();
            long syncTime = serverTime + ((endTime - startTime)/2);
            timeDifference = endTime - syncTime;
            Logger.logInfo("Client End " + new Date(endTime));
            Logger.logInfo("Sync Time  " + new Date(syncTime) + "\nDifference " + timeDifference);

            lobbyId = UUID.fromString(request.getLobbyId());

            lobbyGui.close();
            gui = new Quiz(Client.this);

            protobuf.generated.QuestionServiceMessages.StartGameResponse.Builder responseBuilder = protobuf.generated.QuestionServiceMessages.StartGameResponse.newBuilder();
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }

        @Override
        public void updateLobbyPlayers(UpdateLobbyPlayersRequest request,
                                       StreamObserver<UpdateLobbyPlayersResponse> responseObserver) {

            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            lobbyGui.setPlayers(request.getPlayerNamesList());

            UpdateLobbyPlayersResponse.Builder responseBuilder = UpdateLobbyPlayersResponse.newBuilder();
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }

        @Override
        public void updateScores(UpdateScoresRequest request,
                                 StreamObserver<UpdateScoresResponse> responseObserver) {

            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));
            List<Player> players = request.getPlayersList().stream().map(p -> new Player(p.getName(), p.getScore())).collect(Collectors.toList());

            gui.updateScores(players);

            UpdateScoresResponse.Builder responseBuilder = UpdateScoresResponse.newBuilder();
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }

        @Override
        public void askQuestion(AskQuestionRequest request,
                                StreamObserver<AskQuestionResponse> responseObserver) {

            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));
            Question question = new Question(request.getNumber(), request.getQuestion(), request.getOptionsList());

            gui.nextQuestion(question, new Date(request.getDeadline()));

            AskQuestionResponse.Builder responseBuilder = AskQuestionResponse.newBuilder();
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }
}