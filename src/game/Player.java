package game;

import protobuf.generated.QuestionServiceGrpc.QuestionServiceBlockingStub;

public class Player {
    private QuestionServiceBlockingStub questionServiceStub;

    private String name;
    private int score;
    
    public Player(String name, QuestionServiceBlockingStub stub) {
        this.name = name;
        this.questionServiceStub = stub;
        this.score = 0;
    }
    
    public QuestionServiceBlockingStub getQuestionServiceStub() {
        return questionServiceStub;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
}