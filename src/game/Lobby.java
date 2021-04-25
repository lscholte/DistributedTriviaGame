package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Lobby {
    private UUID lobbyID;
    private List<Player> players;
    
    private Map<String, String> currentQuestion;

    public Lobby(){
        this(UUID.randomUUID());
    }
    
    public Lobby(UUID lobbyID) {
        this.lobbyID = lobbyID;
        players = new ArrayList<>();
    }

    public void addPlayerToLobby(Player player){
        players.add(player);
    }

    public UUID getLobbyID(){
        return lobbyID;
    }

    public List<Player> getPlayers(){
        return players;
    }
    
    public void setCurrentQuestion(Map<String, String> currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Map<String, String> getCurrentQuestion() {
        return currentQuestion;
    }
}
