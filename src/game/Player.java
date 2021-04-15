package game;

import java.util.UUID;

import protobuf.generated.QuestionServiceGrpc.QuestionServiceBlockingStub;

public class Player {
    private QuestionServiceBlockingStub questionServiceStub;

    private UUID id;
    private String name;
    private int score;
    
    public Player(UUID id, String name, QuestionServiceBlockingStub stub) {
        this.id = id;
        this.name = name;
        this.questionServiceStub = stub;
        this.score = 0;
    }
    
    public QuestionServiceBlockingStub getQuestionServiceStub() {
        return questionServiceStub;
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