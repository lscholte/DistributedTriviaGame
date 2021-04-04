package game;

import protobuf.generated.QuestionServiceGrpc.QuestionServiceStub;

public class Player {
    private QuestionServiceStub questionServiceStub;

    private String name;
    
    public Player(String name, QuestionServiceStub stub) {
        this.name = name;
        this.questionServiceStub = stub;
    }
    
    public QuestionServiceStub getQuestionServiceStub() {
        return questionServiceStub;
    }
    
    public String getName() {
        return name;
    }
}