package utilities;

import com.google.protobuf.Message;

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

}
