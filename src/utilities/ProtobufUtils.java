package utilities;

import com.google.protobuf.Message;

import io.grpc.Status.Code;
import io.grpc.stub.StreamObserver;

public final class ProtobufUtils {

    private ProtobufUtils() {
    }

    public static String getPrintableMessage(Message message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message.getDescriptorForType().getName()).append(" {")
                .append(System.lineSeparator()).append(message.toString()).append("}");
        return stringBuilder.toString();
    }
    
    public static <T> StreamObserver<T> createEmptyResponseObserver() {
        return new StreamObserver<T>() {
            @Override
            public void onNext(T response) {}
            
            @Override
            public void onCompleted() {}
            
            @Override
            public void onError(Throwable throwable) {}
        };
    }
    
    public static void handleGrpcError(String requestType, Code code) {
        switch (code) {
            case UNAVAILABLE:
                Logger.logError(String.format("%s failed because server is unavailable", requestType));
                break;
            case DEADLINE_EXCEEDED:
                Logger.logError(String.format("%s timed out waiting for response", requestType));
                break;
            default:
                Logger.logError(String.format("%s failed with error status code %s", requestType,
                        code.toString()));
                break;
        }
    }

}
