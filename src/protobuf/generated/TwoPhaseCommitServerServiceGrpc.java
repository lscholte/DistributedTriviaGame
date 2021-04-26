package protobuf.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: two_phase_commit_server_service.proto")
public final class TwoPhaseCommitServerServiceGrpc {

  private TwoPhaseCommitServerServiceGrpc() {}

  public static final String SERVICE_NAME = "protobuf.TwoPhaseCommitServerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest,
      protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse> getQueryCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QueryCommit",
      requestType = protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest.class,
      responseType = protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest,
      protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse> getQueryCommitMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest, protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse> getQueryCommitMethod;
    if ((getQueryCommitMethod = TwoPhaseCommitServerServiceGrpc.getQueryCommitMethod) == null) {
      synchronized (TwoPhaseCommitServerServiceGrpc.class) {
        if ((getQueryCommitMethod = TwoPhaseCommitServerServiceGrpc.getQueryCommitMethod) == null) {
          TwoPhaseCommitServerServiceGrpc.getQueryCommitMethod = getQueryCommitMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest, protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryCommit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TwoPhaseCommitServerServiceMethodDescriptorSupplier("QueryCommit"))
              .build();
        }
      }
    }
    return getQueryCommitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest,
      protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse> getCommitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Commit",
      requestType = protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest.class,
      responseType = protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest,
      protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse> getCommitMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest, protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse> getCommitMethod;
    if ((getCommitMethod = TwoPhaseCommitServerServiceGrpc.getCommitMethod) == null) {
      synchronized (TwoPhaseCommitServerServiceGrpc.class) {
        if ((getCommitMethod = TwoPhaseCommitServerServiceGrpc.getCommitMethod) == null) {
          TwoPhaseCommitServerServiceGrpc.getCommitMethod = getCommitMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest, protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Commit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TwoPhaseCommitServerServiceMethodDescriptorSupplier("Commit"))
              .build();
        }
      }
    }
    return getCommitMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TwoPhaseCommitServerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitServerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitServerServiceStub>() {
        @java.lang.Override
        public TwoPhaseCommitServerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TwoPhaseCommitServerServiceStub(channel, callOptions);
        }
      };
    return TwoPhaseCommitServerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TwoPhaseCommitServerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitServerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitServerServiceBlockingStub>() {
        @java.lang.Override
        public TwoPhaseCommitServerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TwoPhaseCommitServerServiceBlockingStub(channel, callOptions);
        }
      };
    return TwoPhaseCommitServerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TwoPhaseCommitServerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitServerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TwoPhaseCommitServerServiceFutureStub>() {
        @java.lang.Override
        public TwoPhaseCommitServerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TwoPhaseCommitServerServiceFutureStub(channel, callOptions);
        }
      };
    return TwoPhaseCommitServerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TwoPhaseCommitServerServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void queryCommit(protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryCommitMethod(), responseObserver);
    }

    /**
     */
    public void commit(protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCommitMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryCommitMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest,
                protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse>(
                  this, METHODID_QUERY_COMMIT)))
          .addMethod(
            getCommitMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest,
                protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse>(
                  this, METHODID_COMMIT)))
          .build();
    }
  }

  /**
   */
  public static final class TwoPhaseCommitServerServiceStub extends io.grpc.stub.AbstractAsyncStub<TwoPhaseCommitServerServiceStub> {
    private TwoPhaseCommitServerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TwoPhaseCommitServerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TwoPhaseCommitServerServiceStub(channel, callOptions);
    }

    /**
     */
    public void queryCommit(protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryCommitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void commit(protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TwoPhaseCommitServerServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TwoPhaseCommitServerServiceBlockingStub> {
    private TwoPhaseCommitServerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TwoPhaseCommitServerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TwoPhaseCommitServerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse queryCommit(protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryCommitMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse commit(protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCommitMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TwoPhaseCommitServerServiceFutureStub extends io.grpc.stub.AbstractFutureStub<TwoPhaseCommitServerServiceFutureStub> {
    private TwoPhaseCommitServerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TwoPhaseCommitServerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TwoPhaseCommitServerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse> queryCommit(
        protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryCommitMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse> commit(
        protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCommitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_COMMIT = 0;
  private static final int METHODID_COMMIT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TwoPhaseCommitServerServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TwoPhaseCommitServerServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_COMMIT:
          serviceImpl.queryCommit((protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.TwoPhaseCommitServerMessages.QueryCommitResponse>) responseObserver);
          break;
        case METHODID_COMMIT:
          serviceImpl.commit((protobuf.generated.TwoPhaseCommitServerMessages.CommitRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.TwoPhaseCommitServerMessages.CommitResponse>) responseObserver);
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

  private static abstract class TwoPhaseCommitServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TwoPhaseCommitServerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protobuf.generated.TwoPhaseCommitServerMessages.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TwoPhaseCommitServerService");
    }
  }

  private static final class TwoPhaseCommitServerServiceFileDescriptorSupplier
      extends TwoPhaseCommitServerServiceBaseDescriptorSupplier {
    TwoPhaseCommitServerServiceFileDescriptorSupplier() {}
  }

  private static final class TwoPhaseCommitServerServiceMethodDescriptorSupplier
      extends TwoPhaseCommitServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TwoPhaseCommitServerServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TwoPhaseCommitServerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TwoPhaseCommitServerServiceFileDescriptorSupplier())
              .addMethod(getQueryCommitMethod())
              .addMethod(getCommitMethod())
              .build();
        }
      }
    }
    return result;
  }
}
