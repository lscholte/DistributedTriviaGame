package protobuf.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.1)",
    comments = "Source: lobby_service.proto")
public final class LobbyServiceGrpc {

  private LobbyServiceGrpc() {}

  public static final String SERVICE_NAME = "protobuf.LobbyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.CreateLobbyRequest,
      protobuf.generated.LobbyServiceMessages.CreateLobbyResponse> getCreateLobbyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateLobby",
      requestType = protobuf.generated.LobbyServiceMessages.CreateLobbyRequest.class,
      responseType = protobuf.generated.LobbyServiceMessages.CreateLobbyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.CreateLobbyRequest,
      protobuf.generated.LobbyServiceMessages.CreateLobbyResponse> getCreateLobbyMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.CreateLobbyRequest, protobuf.generated.LobbyServiceMessages.CreateLobbyResponse> getCreateLobbyMethod;
    if ((getCreateLobbyMethod = LobbyServiceGrpc.getCreateLobbyMethod) == null) {
      synchronized (LobbyServiceGrpc.class) {
        if ((getCreateLobbyMethod = LobbyServiceGrpc.getCreateLobbyMethod) == null) {
          LobbyServiceGrpc.getCreateLobbyMethod = getCreateLobbyMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.LobbyServiceMessages.CreateLobbyRequest, protobuf.generated.LobbyServiceMessages.CreateLobbyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateLobby"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.LobbyServiceMessages.CreateLobbyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.LobbyServiceMessages.CreateLobbyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LobbyServiceMethodDescriptorSupplier("CreateLobby"))
              .build();
        }
      }
    }
    return getCreateLobbyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.JoinLobbyRequest,
      protobuf.generated.LobbyServiceMessages.JoinLobbyResponse> getJoinLobbyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JoinLobby",
      requestType = protobuf.generated.LobbyServiceMessages.JoinLobbyRequest.class,
      responseType = protobuf.generated.LobbyServiceMessages.JoinLobbyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.JoinLobbyRequest,
      protobuf.generated.LobbyServiceMessages.JoinLobbyResponse> getJoinLobbyMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.JoinLobbyRequest, protobuf.generated.LobbyServiceMessages.JoinLobbyResponse> getJoinLobbyMethod;
    if ((getJoinLobbyMethod = LobbyServiceGrpc.getJoinLobbyMethod) == null) {
      synchronized (LobbyServiceGrpc.class) {
        if ((getJoinLobbyMethod = LobbyServiceGrpc.getJoinLobbyMethod) == null) {
          LobbyServiceGrpc.getJoinLobbyMethod = getJoinLobbyMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.LobbyServiceMessages.JoinLobbyRequest, protobuf.generated.LobbyServiceMessages.JoinLobbyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "JoinLobby"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.LobbyServiceMessages.JoinLobbyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.LobbyServiceMessages.JoinLobbyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LobbyServiceMethodDescriptorSupplier("JoinLobby"))
              .build();
        }
      }
    }
    return getJoinLobbyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.StartGameRequest,
      protobuf.generated.LobbyServiceMessages.StartGameResponse> getStartGameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartGame",
      requestType = protobuf.generated.LobbyServiceMessages.StartGameRequest.class,
      responseType = protobuf.generated.LobbyServiceMessages.StartGameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.StartGameRequest,
      protobuf.generated.LobbyServiceMessages.StartGameResponse> getStartGameMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.StartGameRequest, protobuf.generated.LobbyServiceMessages.StartGameResponse> getStartGameMethod;
    if ((getStartGameMethod = LobbyServiceGrpc.getStartGameMethod) == null) {
      synchronized (LobbyServiceGrpc.class) {
        if ((getStartGameMethod = LobbyServiceGrpc.getStartGameMethod) == null) {
          LobbyServiceGrpc.getStartGameMethod = getStartGameMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.LobbyServiceMessages.StartGameRequest, protobuf.generated.LobbyServiceMessages.StartGameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartGame"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.LobbyServiceMessages.StartGameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.LobbyServiceMessages.StartGameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LobbyServiceMethodDescriptorSupplier("StartGame"))
              .build();
        }
      }
    }
    return getStartGameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest,
      protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse> getSynchronizeTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SynchronizeTime",
      requestType = protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest.class,
      responseType = protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest,
      protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse> getSynchronizeTimeMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest, protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse> getSynchronizeTimeMethod;
    if ((getSynchronizeTimeMethod = LobbyServiceGrpc.getSynchronizeTimeMethod) == null) {
      synchronized (LobbyServiceGrpc.class) {
        if ((getSynchronizeTimeMethod = LobbyServiceGrpc.getSynchronizeTimeMethod) == null) {
          LobbyServiceGrpc.getSynchronizeTimeMethod = getSynchronizeTimeMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest, protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SynchronizeTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LobbyServiceMethodDescriptorSupplier("SynchronizeTime"))
              .build();
        }
      }
    }
    return getSynchronizeTimeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LobbyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LobbyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LobbyServiceStub>() {
        @java.lang.Override
        public LobbyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LobbyServiceStub(channel, callOptions);
        }
      };
    return LobbyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LobbyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LobbyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LobbyServiceBlockingStub>() {
        @java.lang.Override
        public LobbyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LobbyServiceBlockingStub(channel, callOptions);
        }
      };
    return LobbyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LobbyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LobbyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LobbyServiceFutureStub>() {
        @java.lang.Override
        public LobbyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LobbyServiceFutureStub(channel, callOptions);
        }
      };
    return LobbyServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class LobbyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createLobby(protobuf.generated.LobbyServiceMessages.CreateLobbyRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.CreateLobbyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateLobbyMethod(), responseObserver);
    }

    /**
     */
    public void joinLobby(protobuf.generated.LobbyServiceMessages.JoinLobbyRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.JoinLobbyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getJoinLobbyMethod(), responseObserver);
    }

    /**
     */
    public void startGame(protobuf.generated.LobbyServiceMessages.StartGameRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.StartGameResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStartGameMethod(), responseObserver);
    }

    /**
     */
    public void synchronizeTime(protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSynchronizeTimeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateLobbyMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.LobbyServiceMessages.CreateLobbyRequest,
                protobuf.generated.LobbyServiceMessages.CreateLobbyResponse>(
                  this, METHODID_CREATE_LOBBY)))
          .addMethod(
            getJoinLobbyMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.LobbyServiceMessages.JoinLobbyRequest,
                protobuf.generated.LobbyServiceMessages.JoinLobbyResponse>(
                  this, METHODID_JOIN_LOBBY)))
          .addMethod(
            getStartGameMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.LobbyServiceMessages.StartGameRequest,
                protobuf.generated.LobbyServiceMessages.StartGameResponse>(
                  this, METHODID_START_GAME)))
          .addMethod(
            getSynchronizeTimeMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest,
                protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse>(
                  this, METHODID_SYNCHRONIZE_TIME)))
          .build();
    }
  }

  /**
   */
  public static final class LobbyServiceStub extends io.grpc.stub.AbstractAsyncStub<LobbyServiceStub> {
    private LobbyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LobbyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LobbyServiceStub(channel, callOptions);
    }

    /**
     */
    public void createLobby(protobuf.generated.LobbyServiceMessages.CreateLobbyRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.CreateLobbyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateLobbyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinLobby(protobuf.generated.LobbyServiceMessages.JoinLobbyRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.JoinLobbyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getJoinLobbyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startGame(protobuf.generated.LobbyServiceMessages.StartGameRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.StartGameResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStartGameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void synchronizeTime(protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSynchronizeTimeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LobbyServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<LobbyServiceBlockingStub> {
    private LobbyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LobbyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LobbyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public protobuf.generated.LobbyServiceMessages.CreateLobbyResponse createLobby(protobuf.generated.LobbyServiceMessages.CreateLobbyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateLobbyMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.LobbyServiceMessages.JoinLobbyResponse joinLobby(protobuf.generated.LobbyServiceMessages.JoinLobbyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getJoinLobbyMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.LobbyServiceMessages.StartGameResponse startGame(protobuf.generated.LobbyServiceMessages.StartGameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStartGameMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse synchronizeTime(protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSynchronizeTimeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LobbyServiceFutureStub extends io.grpc.stub.AbstractFutureStub<LobbyServiceFutureStub> {
    private LobbyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LobbyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LobbyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.LobbyServiceMessages.CreateLobbyResponse> createLobby(
        protobuf.generated.LobbyServiceMessages.CreateLobbyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateLobbyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.LobbyServiceMessages.JoinLobbyResponse> joinLobby(
        protobuf.generated.LobbyServiceMessages.JoinLobbyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getJoinLobbyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.LobbyServiceMessages.StartGameResponse> startGame(
        protobuf.generated.LobbyServiceMessages.StartGameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStartGameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse> synchronizeTime(
        protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSynchronizeTimeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_LOBBY = 0;
  private static final int METHODID_JOIN_LOBBY = 1;
  private static final int METHODID_START_GAME = 2;
  private static final int METHODID_SYNCHRONIZE_TIME = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LobbyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LobbyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_LOBBY:
          serviceImpl.createLobby((protobuf.generated.LobbyServiceMessages.CreateLobbyRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.CreateLobbyResponse>) responseObserver);
          break;
        case METHODID_JOIN_LOBBY:
          serviceImpl.joinLobby((protobuf.generated.LobbyServiceMessages.JoinLobbyRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.JoinLobbyResponse>) responseObserver);
          break;
        case METHODID_START_GAME:
          serviceImpl.startGame((protobuf.generated.LobbyServiceMessages.StartGameRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.StartGameResponse>) responseObserver);
          break;
        case METHODID_SYNCHRONIZE_TIME:
          serviceImpl.synchronizeTime((protobuf.generated.LobbyServiceMessages.SynchronizeTimeRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.LobbyServiceMessages.SynchronizeTimeResponse>) responseObserver);
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

  private static abstract class LobbyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LobbyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protobuf.generated.LobbyServiceMessages.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LobbyService");
    }
  }

  private static final class LobbyServiceFileDescriptorSupplier
      extends LobbyServiceBaseDescriptorSupplier {
    LobbyServiceFileDescriptorSupplier() {}
  }

  private static final class LobbyServiceMethodDescriptorSupplier
      extends LobbyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LobbyServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (LobbyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LobbyServiceFileDescriptorSupplier())
              .addMethod(getCreateLobbyMethod())
              .addMethod(getJoinLobbyMethod())
              .addMethod(getStartGameMethod())
              .addMethod(getSynchronizeTimeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
