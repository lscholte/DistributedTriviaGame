package game;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {    
        List<Server> servers = new ArrayList<>();
        servers.add(new Server(10000));
        servers.add(new Server(11000));
        servers.add(new Server(12000));
        servers.add(new Server(13000));
        servers.add(new Server(14000));
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(Server server: servers){
            executorService.submit(() -> {
                try {
                    server.start();
                }catch(IOException|InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
    }
}
