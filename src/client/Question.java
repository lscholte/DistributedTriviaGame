package client;

import java.util.ArrayList;
import java.util.List;

public class Question {
    
    private int number;
    private String text;
    private List<String> options;
    
    public Question(int number, String text, List<String> options) {
        this.number = number;
        this.text = text;
        this.options = new ArrayList<String>(options);
    }
    
    public int getNumber() {
        return number;
    }
    
    public String getText() {
        return text;
    }
    
    public List<String> getOptions() {
        return options;
    }

}
