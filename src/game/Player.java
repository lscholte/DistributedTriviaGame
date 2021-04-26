package game;

import java.net.InetSocketAddress;
import java.util.UUID;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import protobuf.generated.QuestionServiceGrpc;
import protobuf.generated.QuestionServiceGrpc.QuestionServiceBlockingStub;

public class Player {
    private QuestionServiceBlockingStub questionServiceStub;

    private UUID id;
    private String name;
    private int score;
    
    private InetSocketAddress address;
    
    public Player(UUID id, String name, InetSocketAddress address) {
        this(id, name, 0, address);
    }
    
    public Player(UUID id, String name, int score, InetSocketAddress address) {
        this.id = id;
        this.name = name;
        this.score = score;
        
        this.address = address;
        
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address.getHostString(), address.getPort()).usePlaintext().build();

        this.questionServiceStub = QuestionServiceGrpc.newBlockingStub(channel);
//        this.questionServiceStub = stub;
    }
    
    public QuestionServiceBlockingStub getQuestionServiceStub() {
        return questionServiceStub;
    }
    
    public InetSocketAddress getAddress() {
        return address;
    }
    
    public UUID getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    public void incrementScore(int amountToAdd) {
        score += amountToAdd;
    }
}