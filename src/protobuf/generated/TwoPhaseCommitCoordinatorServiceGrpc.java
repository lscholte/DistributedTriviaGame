package protobuf.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: two_phase_commit_coordinator_service.proto")
public final class TwoPhaseCommitCoordinatorServiceGrpc {

  private TwoPhaseCommitCoordinatorServiceGrpc() {}

  public static final String SERVICE_NAME = "protobuf.TwoPhaseCommitCoordinatorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest,
      protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse> getTwoPhaseCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TwoPhaseCommit",
      requestType = protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest.class,
      responseType = protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest,
      protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse> getTwoPhaseCommitMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest, protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse> getTwoPhaseCommitMethod;
    if ((getTwoPhaseCommitMethod = TwoPhaseCommitCoordinatorServiceGrpc.getTwoPhaseCommitMethod) == null) {
      synchronized (TwoPhaseCommitCoordinatorServiceGrpc.class) {
        if ((getTwoPhaseCommitMethod = TwoPhaseCommitCoordinatorServiceGrpc.getTwoPhaseCommitMethod) == null) {
          TwoPhaseCommitCoordinatorServiceGrpc.getTwoPhaseCommitMethod = getTwoPhaseCommitMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest, protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TwoPhaseCommit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TwoPhaseCommitCoordinatorServiceMethodDescriptorSupplier("TwoPhaseCommit"))
              .build();
        }
      }
    }
    return getTwoPhaseCommitMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TwoPhaseCommitCoordinatorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitCoordinatorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitCoordinatorServiceStub>() {
        @java.lang.Override
        public TwoPhaseCommitCoordinatorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TwoPhaseCommitCoordinatorServiceStub(channel, callOptions);
        }
      };
    return TwoPhaseCommitCoordinatorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TwoPhaseCommitCoordinatorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitCoordinatorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitCoordinatorServiceBlockingStub>() {
        @java.lang.Override
        public TwoPhaseCommitCoordinatorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TwoPhaseCommitCoordinatorServiceBlockingStub(channel, callOptions);
        }
      };
    return TwoPhaseCommitCoordinatorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TwoPhaseCommitCoordinatorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitCoordinatorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitCoordinatorServiceFutureStub>() {
        @java.lang.Override
        public TwoPhaseCommitCoordinatorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TwoPhaseCommitCoordinatorServiceFutureStub(channel, callOptions);
        }
      };
    return TwoPhaseCommitCoordinatorServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TwoPhaseCommitCoordinatorServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void twoPhaseCommit(protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getTwoPhaseCommitMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getTwoPhaseCommitMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest,
                protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse>(
                  this, METHODID_TWO_PHASE_COMMIT)))
          .build();
    }
  }

  /**
   */
  public static final class TwoPhaseCommitCoordinatorServiceStub extends io.grpc.stub.AbstractAsyncStub<TwoPhaseCommitCoordinatorServiceStub> {
    private TwoPhaseCommitCoordinatorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TwoPhaseCommitCoordinatorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TwoPhaseCommitCoordinatorServiceStub(channel, callOptions);
    }

    /**
     */
    public void twoPhaseCommit(protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getTwoPhaseCommitMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TwoPhaseCommitCoordinatorServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TwoPhaseCommitCoordinatorServiceBlockingStub> {
    private TwoPhaseCommitCoordinatorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TwoPhaseCommitCoordinatorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TwoPhaseCommitCoordinatorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse twoPhaseCommit(protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getTwoPhaseCommitMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TwoPhaseCommitCoordinatorServiceFutureStub extends io.grpc.stub.AbstractFutureStub<TwoPhaseCommitCoordinatorServiceFutureStub> {
    private TwoPhaseCommitCoordinatorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TwoPhaseCommitCoordinatorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TwoPhaseCommitCoordinatorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse> twoPhaseCommit(
        protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getTwoPhaseCommitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_TWO_PHASE_COMMIT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TwoPhaseCommitCoordinatorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TwoPhaseCommitCoordinatorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_TWO_PHASE_COMMIT:
          serviceImpl.twoPhaseCommit((protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.TwoPhaseCommitCoordinatorMessages.TwoPhaseCommitResponse>) responseObserver);
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

  private static abstract class TwoPhaseCommitCoordinatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TwoPhaseCommitCoordinatorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protobuf.generated.TwoPhaseCommitCoordinatorMessages.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TwoPhaseCommitCoordinatorService");
    }
  }

  private static final class TwoPhaseCommitCoordinatorServiceFileDescriptorSupplier
      extends TwoPhaseCommitCoordinatorServiceBaseDescriptorSupplier {
    TwoPhaseCommitCoordinatorServiceFileDescriptorSupplier() {}
  }

  private static final class TwoPhaseCommitCoordinatorServiceMethodDescriptorSupplier
      extends TwoPhaseCommitCoordinatorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TwoPhaseCommitCoordinatorServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TwoPhaseCommitCoordinatorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TwoPhaseCommitCoordinatorServiceFileDescriptorSupplier())
              .addMethod(getTwoPhaseCommitMethod())
              .build();
        }
      }
    }
    return result;
  }
}
