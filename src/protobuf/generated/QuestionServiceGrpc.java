package protobuf.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: question_service.proto")
public final class QuestionServiceGrpc {

  private QuestionServiceGrpc() {}

  public static final String SERVICE_NAME = "protobuf.QuestionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.AskQuestionRequest,
      protobuf.generated.QuestionServiceMessages.AskQuestionResponse> getAskQuestionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AskQuestion",
      requestType = protobuf.generated.QuestionServiceMessages.AskQuestionRequest.class,
      responseType = protobuf.generated.QuestionServiceMessages.AskQuestionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.AskQuestionRequest,
      protobuf.generated.QuestionServiceMessages.AskQuestionResponse> getAskQuestionMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.AskQuestionRequest, protobuf.generated.QuestionServiceMessages.AskQuestionResponse> getAskQuestionMethod;
    if ((getAskQuestionMethod = QuestionServiceGrpc.getAskQuestionMethod) == null) {
      synchronized (QuestionServiceGrpc.class) {
        if ((getAskQuestionMethod = QuestionServiceGrpc.getAskQuestionMethod) == null) {
          QuestionServiceGrpc.getAskQuestionMethod = getAskQuestionMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.QuestionServiceMessages.AskQuestionRequest, protobuf.generated.QuestionServiceMessages.AskQuestionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AskQuestion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.AskQuestionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.AskQuestionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QuestionServiceMethodDescriptorSupplier("AskQuestion"))
              .build();
        }
      }
    }
    return getAskQuestionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.UpdateScoresRequest,
      protobuf.generated.QuestionServiceMessages.UpdateScoresResponse> getUpdateScoresMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateScores",
      requestType = protobuf.generated.QuestionServiceMessages.UpdateScoresRequest.class,
      responseType = protobuf.generated.QuestionServiceMessages.UpdateScoresResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.UpdateScoresRequest,
      protobuf.generated.QuestionServiceMessages.UpdateScoresResponse> getUpdateScoresMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.UpdateScoresRequest, protobuf.generated.QuestionServiceMessages.UpdateScoresResponse> getUpdateScoresMethod;
    if ((getUpdateScoresMethod = QuestionServiceGrpc.getUpdateScoresMethod) == null) {
      synchronized (QuestionServiceGrpc.class) {
        if ((getUpdateScoresMethod = QuestionServiceGrpc.getUpdateScoresMethod) == null) {
          QuestionServiceGrpc.getUpdateScoresMethod = getUpdateScoresMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.QuestionServiceMessages.UpdateScoresRequest, protobuf.generated.QuestionServiceMessages.UpdateScoresResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateScores"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.UpdateScoresRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.UpdateScoresResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QuestionServiceMethodDescriptorSupplier("UpdateScores"))
              .build();
        }
      }
    }
    return getUpdateScoresMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest,
      protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse> getUpdateLobbyPlayersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateLobbyPlayers",
      requestType = protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest.class,
      responseType = protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest,
      protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse> getUpdateLobbyPlayersMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest, protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse> getUpdateLobbyPlayersMethod;
    if ((getUpdateLobbyPlayersMethod = QuestionServiceGrpc.getUpdateLobbyPlayersMethod) == null) {
      synchronized (QuestionServiceGrpc.class) {
        if ((getUpdateLobbyPlayersMethod = QuestionServiceGrpc.getUpdateLobbyPlayersMethod) == null) {
          QuestionServiceGrpc.getUpdateLobbyPlayersMethod = getUpdateLobbyPlayersMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest, protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateLobbyPlayers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QuestionServiceMethodDescriptorSupplier("UpdateLobbyPlayers"))
              .build();
        }
      }
    }
    return getUpdateLobbyPlayersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.StartGameRequest,
      protobuf.generated.QuestionServiceMessages.StartGameResponse> getStartGameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StartGame",
      requestType = protobuf.generated.QuestionServiceMessages.StartGameRequest.class,
      responseType = protobuf.generated.QuestionServiceMessages.StartGameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.StartGameRequest,
      protobuf.generated.QuestionServiceMessages.StartGameResponse> getStartGameMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.StartGameRequest, protobuf.generated.QuestionServiceMessages.StartGameResponse> getStartGameMethod;
    if ((getStartGameMethod = QuestionServiceGrpc.getStartGameMethod) == null) {
      synchronized (QuestionServiceGrpc.class) {
        if ((getStartGameMethod = QuestionServiceGrpc.getStartGameMethod) == null) {
          QuestionServiceGrpc.getStartGameMethod = getStartGameMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.QuestionServiceMessages.StartGameRequest, protobuf.generated.QuestionServiceMessages.StartGameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StartGame"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.StartGameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.StartGameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QuestionServiceMethodDescriptorSupplier("StartGame"))
              .build();
        }
      }
    }
    return getStartGameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.FinishGameRequest,
      protobuf.generated.QuestionServiceMessages.FinishGameResponse> getFinishGameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FinishGame",
      requestType = protobuf.generated.QuestionServiceMessages.FinishGameRequest.class,
      responseType = protobuf.generated.QuestionServiceMessages.FinishGameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.FinishGameRequest,
      protobuf.generated.QuestionServiceMessages.FinishGameResponse> getFinishGameMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.FinishGameRequest, protobuf.generated.QuestionServiceMessages.FinishGameResponse> getFinishGameMethod;
    if ((getFinishGameMethod = QuestionServiceGrpc.getFinishGameMethod) == null) {
      synchronized (QuestionServiceGrpc.class) {
        if ((getFinishGameMethod = QuestionServiceGrpc.getFinishGameMethod) == null) {
          QuestionServiceGrpc.getFinishGameMethod = getFinishGameMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.QuestionServiceMessages.FinishGameRequest, protobuf.generated.QuestionServiceMessages.FinishGameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FinishGame"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.FinishGameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.FinishGameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QuestionServiceMethodDescriptorSupplier("FinishGame"))
              .build();
        }
      }
    }
    return getFinishGameMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QuestionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QuestionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QuestionServiceStub>() {
        @java.lang.Override
        public QuestionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QuestionServiceStub(channel, callOptions);
        }
      };
    return QuestionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QuestionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QuestionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QuestionServiceBlockingStub>() {
        @java.lang.Override
        public QuestionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QuestionServiceBlockingStub(channel, callOptions);
        }
      };
    return QuestionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QuestionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<QuestionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<QuestionServiceFutureStub>() {
        @java.lang.Override
        public QuestionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new QuestionServiceFutureStub(channel, callOptions);
        }
      };
    return QuestionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class QuestionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void askQuestion(protobuf.generated.QuestionServiceMessages.AskQuestionRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.AskQuestionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAskQuestionMethod(), responseObserver);
    }

    /**
     */
    public void updateScores(protobuf.generated.QuestionServiceMessages.UpdateScoresRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.UpdateScoresResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateScoresMethod(), responseObserver);
    }

    /**
     */
    public void updateLobbyPlayers(protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateLobbyPlayersMethod(), responseObserver);
    }

    /**
     */
    public void startGame(protobuf.generated.QuestionServiceMessages.StartGameRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.StartGameResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStartGameMethod(), responseObserver);
    }

    /**
     */
    public void finishGame(protobuf.generated.QuestionServiceMessages.FinishGameRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.FinishGameResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFinishGameMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAskQuestionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.QuestionServiceMessages.AskQuestionRequest,
                protobuf.generated.QuestionServiceMessages.AskQuestionResponse>(
                  this, METHODID_ASK_QUESTION)))
          .addMethod(
            getUpdateScoresMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.QuestionServiceMessages.UpdateScoresRequest,
                protobuf.generated.QuestionServiceMessages.UpdateScoresResponse>(
                  this, METHODID_UPDATE_SCORES)))
          .addMethod(
            getUpdateLobbyPlayersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest,
                protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse>(
                  this, METHODID_UPDATE_LOBBY_PLAYERS)))
          .addMethod(
            getStartGameMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.QuestionServiceMessages.StartGameRequest,
                protobuf.generated.QuestionServiceMessages.StartGameResponse>(
                  this, METHODID_START_GAME)))
          .addMethod(
            getFinishGameMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.QuestionServiceMessages.FinishGameRequest,
                protobuf.generated.QuestionServiceMessages.FinishGameResponse>(
                  this, METHODID_FINISH_GAME)))
          .build();
    }
  }

  /**
   */
  public static final class QuestionServiceStub extends io.grpc.stub.AbstractAsyncStub<QuestionServiceStub> {
    private QuestionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuestionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QuestionServiceStub(channel, callOptions);
    }

    /**
     */
    public void askQuestion(protobuf.generated.QuestionServiceMessages.AskQuestionRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.AskQuestionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAskQuestionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateScores(protobuf.generated.QuestionServiceMessages.UpdateScoresRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.UpdateScoresResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateScoresMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateLobbyPlayers(protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateLobbyPlayersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startGame(protobuf.generated.QuestionServiceMessages.StartGameRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.StartGameResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStartGameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void finishGame(protobuf.generated.QuestionServiceMessages.FinishGameRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.FinishGameResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFinishGameMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class QuestionServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<QuestionServiceBlockingStub> {
    private QuestionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuestionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QuestionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public protobuf.generated.QuestionServiceMessages.AskQuestionResponse askQuestion(protobuf.generated.QuestionServiceMessages.AskQuestionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAskQuestionMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.QuestionServiceMessages.UpdateScoresResponse updateScores(protobuf.generated.QuestionServiceMessages.UpdateScoresRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateScoresMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse updateLobbyPlayers(protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateLobbyPlayersMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.QuestionServiceMessages.StartGameResponse startGame(protobuf.generated.QuestionServiceMessages.StartGameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStartGameMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.QuestionServiceMessages.FinishGameResponse finishGame(protobuf.generated.QuestionServiceMessages.FinishGameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFinishGameMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class QuestionServiceFutureStub extends io.grpc.stub.AbstractFutureStub<QuestionServiceFutureStub> {
    private QuestionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QuestionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new QuestionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.QuestionServiceMessages.AskQuestionResponse> askQuestion(
        protobuf.generated.QuestionServiceMessages.AskQuestionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAskQuestionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.QuestionServiceMessages.UpdateScoresResponse> updateScores(
        protobuf.generated.QuestionServiceMessages.UpdateScoresRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateScoresMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse> updateLobbyPlayers(
        protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateLobbyPlayersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.QuestionServiceMessages.StartGameResponse> startGame(
        protobuf.generated.QuestionServiceMessages.StartGameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStartGameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.QuestionServiceMessages.FinishGameResponse> finishGame(
        protobuf.generated.QuestionServiceMessages.FinishGameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFinishGameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ASK_QUESTION = 0;
  private static final int METHODID_UPDATE_SCORES = 1;
  private static final int METHODID_UPDATE_LOBBY_PLAYERS = 2;
  private static final int METHODID_START_GAME = 3;
  private static final int METHODID_FINISH_GAME = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QuestionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QuestionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ASK_QUESTION:
          serviceImpl.askQuestion((protobuf.generated.QuestionServiceMessages.AskQuestionRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.AskQuestionResponse>) responseObserver);
          break;
        case METHODID_UPDATE_SCORES:
          serviceImpl.updateScores((protobuf.generated.QuestionServiceMessages.UpdateScoresRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.UpdateScoresResponse>) responseObserver);
          break;
        case METHODID_UPDATE_LOBBY_PLAYERS:
          serviceImpl.updateLobbyPlayers((protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.UpdateLobbyPlayersResponse>) responseObserver);
          break;
        case METHODID_START_GAME:
          serviceImpl.startGame((protobuf.generated.QuestionServiceMessages.StartGameRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.StartGameResponse>) responseObserver);
          break;
        case METHODID_FINISH_GAME:
          serviceImpl.finishGame((protobuf.generated.QuestionServiceMessages.FinishGameRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.FinishGameResponse>) responseObserver);
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

  private static abstract class QuestionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QuestionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protobuf.generated.QuestionServiceMessages.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QuestionService");
    }
  }

  private static final class QuestionServiceFileDescriptorSupplier
      extends QuestionServiceBaseDescriptorSupplier {
    QuestionServiceFileDescriptorSupplier() {}
  }

  private static final class QuestionServiceMethodDescriptorSupplier
      extends QuestionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QuestionServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (QuestionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QuestionServiceFileDescriptorSupplier())
              .addMethod(getAskQuestionMethod())
              .addMethod(getUpdateScoresMethod())
              .addMethod(getUpdateLobbyPlayersMethod())
              .addMethod(getStartGameMethod())
              .addMethod(getFinishGameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
