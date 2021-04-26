package coordinator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest;
import protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse;
import protobuf.generated.TwoPhaseCommitCoordinatorServiceGrpc.TwoPhaseCommitCoordinatorServiceImplBase;
import protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest;
import protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse;
import protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest;
import protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse;
import protobuf.generated.TwoPhaseCommitServerServiceGrpc;
import protobuf.generated.TwoPhaseCommitServerServiceGrpc.TwoPhaseCommitServerServiceBlockingStub;
import utilities.Logger;
import utilities.ProtobufUtils;

/**
 * An Two-Phase Commit coordinator that can ensure consistency among
 * Key-Value store server participants.
 * @author lscholte
 *
 */
public class Coordinator {

    private static final int PORT = 5000;
    
    private Server grpcServer;
    private List<TwoPhaseCommitServerServiceBlockingStub> serviceStubs;

    public Coordinator(List<InetSocketAddress> serverAddresses) {
        grpcServer = ServerBuilder
                .forPort(PORT)
                .addService(new TwoPhaseCommitCoordinatorService())
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Logger.logInfo("Shutting down coordinator");
            try {
                grpcServer.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));

        //Create a 2PC server service stub for each server that the coordinator oversees
        serviceStubs = new ArrayList<>(serverAddresses.size());
        for (InetSocketAddress serverAddress : serverAddresses) {
            ManagedChannel channel = ManagedChannelBuilder
                    .forAddress(serverAddress.getHostString(), serverAddress.getPort())
                    .usePlaintext()
                    .build();
            serviceStubs.add(TwoPhaseCommitServerServiceGrpc.newBlockingStub(channel));

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                Logger.logInfo(
                        String.format(
                                "Shutting down channel to %s:%d",
                                serverAddress.getHostString(),
                                serverAddress.getPort()));
                try {
                    channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            })); 
        }
    }

    public void start() throws IOException, InterruptedException {
        Logger.logInfo("Starting coordinator");

        grpcServer.start();
        grpcServer.awaitTermination();
    }

    private class TwoPhaseCommitCoordinatorService extends TwoPhaseCommitCoordinatorServiceImplBase {

        @Override
        public synchronized void twoPhaseCommit(TwoPhaseCommitRequest request, StreamObserver<TwoPhaseCommitResponse> responseObserver) {
            Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

            boolean shouldCommit = true;
            for (TwoPhaseCommitServerServiceBlockingStub serviceStub : serviceStubs) {
                QueryCommitResponse queryCommitResponse = serviceStub.queryCommit(QueryCommitRequest.newBuilder().build());

                if (!queryCommitResponse.getShouldCommit()) {
                    shouldCommit = false;
                    break;
                }
            }

            if (!shouldCommit) {
                responseObserver.onNext(TwoPhaseCommitResponse.newBuilder().setCommitted(false).build());
                responseObserver.onCompleted();
                return;
            }

            for (TwoPhaseCommitServerServiceBlockingStub serviceStub : serviceStubs) {
                CommitRequest.Builder commitRequestBuilder = CommitRequest.newBuilder();
                commitRequestBuilder.setDetails(request.getDetails());
                serviceStub.commit(commitRequestBuilder.build());
            }

            responseObserver.onNext(TwoPhaseCommitResponse.newBuilder().setCommitted(true).build());
            responseObserver.onCompleted();
        }
    }
}