package game;

import protobuf.generated.QuestionServiceGrpc.QuestionServiceStub;

public class Player {
    private QuestionServiceStub questionServiceStub;

    private String name;
    private int score;
    
    public Player(String name, QuestionServiceStub stub) {
        this.name = name;
        this.questionServiceStub = stub;
        this.score = 0;
    }
    
    public QuestionServiceStub getQuestionServiceStub() {
        return questionServiceStub;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
}