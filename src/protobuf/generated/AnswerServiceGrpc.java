package protobuf.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.1)",
    comments = "Source: answer_service.proto")
public final class AnswerServiceGrpc {

  private AnswerServiceGrpc() {}

  public static final String SERVICE_NAME = "protobuf.AnswerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protobuf.generated.AnswerServiceMessages.AnswerRequest,
      protobuf.generated.AnswerServiceMessages.AnswerResponse> getAnswerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Answer",
      requestType = protobuf.generated.AnswerServiceMessages.AnswerRequest.class,
      responseType = protobuf.generated.AnswerServiceMessages.AnswerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.AnswerServiceMessages.AnswerRequest,
      protobuf.generated.AnswerServiceMessages.AnswerResponse> getAnswerMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.AnswerServiceMessages.AnswerRequest, protobuf.generated.AnswerServiceMessages.AnswerResponse> getAnswerMethod;
    if ((getAnswerMethod = AnswerServiceGrpc.getAnswerMethod) == null) {
      synchronized (AnswerServiceGrpc.class) {
        if ((getAnswerMethod = AnswerServiceGrpc.getAnswerMethod) == null) {
          AnswerServiceGrpc.getAnswerMethod = getAnswerMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.AnswerServiceMessages.AnswerRequest, protobuf.generated.AnswerServiceMessages.AnswerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Answer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.AnswerServiceMessages.AnswerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.AnswerServiceMessages.AnswerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AnswerServiceMethodDescriptorSupplier("Answer"))
              .build();
        }
      }
    }
    return getAnswerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AnswerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnswerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnswerServiceStub>() {
        @java.lang.Override
        public AnswerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnswerServiceStub(channel, callOptions);
        }
      };
    return AnswerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AnswerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnswerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnswerServiceBlockingStub>() {
        @java.lang.Override
        public AnswerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnswerServiceBlockingStub(channel, callOptions);
        }
      };
    return AnswerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AnswerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnswerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnswerServiceFutureStub>() {
        @java.lang.Override
        public AnswerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnswerServiceFutureStub(channel, callOptions);
        }
      };
    return AnswerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class AnswerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void answer(protobuf.generated.AnswerServiceMessages.AnswerRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.AnswerServiceMessages.AnswerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAnswerMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAnswerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.AnswerServiceMessages.AnswerRequest,
                protobuf.generated.AnswerServiceMessages.AnswerResponse>(
                  this, METHODID_ANSWER)))
          .build();
    }
  }

  /**
   */
  public static final class AnswerServiceStub extends io.grpc.stub.AbstractAsyncStub<AnswerServiceStub> {
    private AnswerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnswerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnswerServiceStub(channel, callOptions);
    }

    /**
     */
    public void answer(protobuf.generated.AnswerServiceMessages.AnswerRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.AnswerServiceMessages.AnswerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAnswerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AnswerServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AnswerServiceBlockingStub> {
    private AnswerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnswerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnswerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public protobuf.generated.AnswerServiceMessages.AnswerResponse answer(protobuf.generated.AnswerServiceMessages.AnswerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnswerMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AnswerServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AnswerServiceFutureStub> {
    private AnswerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnswerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnswerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.AnswerServiceMessages.AnswerResponse> answer(
        protobuf.generated.AnswerServiceMessages.AnswerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAnswerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ANSWER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AnswerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AnswerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ANSWER:
          serviceImpl.answer((protobuf.generated.AnswerServiceMessages.AnswerRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.AnswerServiceMessages.AnswerResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class AnswerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AnswerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protobuf.generated.AnswerServiceMessages.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AnswerService");
    }
  }

  private static final class AnswerServiceFileDescriptorSupplier
      extends AnswerServiceBaseDescriptorSupplier {
    AnswerServiceFileDescriptorSupplier() {}
  }

  private static final class AnswerServiceMethodDescriptorSupplier
      extends AnswerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AnswerServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AnswerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AnswerServiceFileDescriptorSupplier())
              .addMethod(getAnswerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
