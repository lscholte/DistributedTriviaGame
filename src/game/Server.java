package game;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import protobuf.generated.LobbyServiceGrpc.LobbyServiceImplBase;
import protobuf.generated.LobbyServiceMessages.CreateLobbyRequest;
import protobuf.generated.LobbyServiceMessages.CreateLobbyResponse;
import protobuf.generated.LobbyServiceMessages.JoinLobbyRequest;
import protobuf.generated.LobbyServiceMessages.JoinLobbyResponse;

public class Server {
  public static final int PORT = 7766;
  
  private io.grpc.Server grpcServer;
  
  public Server() {
    grpcServer = ServerBuilder
        .forPort(PORT)
        .addService(new LobbyService())
        .build();
    
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        grpcServer.shutdown().awaitTermination(5, TimeUnit.SECONDS);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }));
  }

  public void start() throws IOException, InterruptedException {    
    grpcServer.start();
    grpcServer.awaitTermination();
  }
      
  private class LobbyService extends LobbyServiceImplBase {
    
    @Override
    public void createLobby(CreateLobbyRequest request, StreamObserver<CreateLobbyResponse> responseObserver) {
      //TODO: Create a lobby, assign a UUID and return the UUID back to client
    }
    
    @Override
    public void joinLobby(JoinLobbyRequest request, StreamObserver<JoinLobbyResponse> responseObserver) {
      //TODO: add the player to the lobby.
      //Setup a channel with the player to send them questions when the game starts
    }
  }
}
