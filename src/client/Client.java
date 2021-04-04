package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
import protobuf.generated.LobbyServiceMessages.StartGameRequest;
import protobuf.generated.QuestionServiceGrpc.QuestionServiceImplBase;
import protobuf.generated.QuestionServiceMessages.AskQuestionRequest;
import protobuf.generated.QuestionServiceMessages.AskQuestionResponse;
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

    private Quiz gui;

    public Client() throws UnknownHostException, IOException {
        gui = new Quiz(this);
        channel = ManagedChannelBuilder.forAddress("localhost", Server.PORT).usePlaintext().build();
        
        lobbyServiceBlockingStub = LobbyServiceGrpc.newBlockingStub(channel);

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

            grpcServer = ServerBuilder.forPort(0).addService(new QuestionService()).build();
            grpcServer.start();

            joinLobby(lobbyUuid, "Test Player");
            startGame(lobbyUuid);
            
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
        
        // Send request
        JoinLobbyRequest request = requestBuilder.build();
        Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));

        try {
            lobbyServiceBlockingStub.joinLobby(request);           
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

    public boolean answer(MultipleChoiceAnswer answer) {
        // Build request
        AnswerRequest.Builder requestBuilder = AnswerRequest.newBuilder();
//        requestBuilder.setText(answerText);

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
                return true;
            } else {
                Logger.logInfo("Answer was incorrect");
                return false;
            }
        } catch (StatusRuntimeException e) {
            handleGrpcError("Answer", e.getStatus().getCode());
        }
        return false;
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
            Question question = new Question(request.getQuestion(), request.getOptionsList());
            
            gui.nextQuestion(question, new Date(request.getDeadline()));
            
            AskQuestionResponse.Builder responseBuilder = AskQuestionResponse.newBuilder();
            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }
}
