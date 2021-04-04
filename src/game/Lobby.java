package game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lobby {
    private UUID lobbyID;
    private List<Player> players;

    public Lobby(){
        lobbyID = UUID.randomUUID();
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
}
