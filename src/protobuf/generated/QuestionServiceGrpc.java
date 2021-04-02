package protobuf.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.1)",
    comments = "Source: question_service.proto")
public final class QuestionServiceGrpc {

  private QuestionServiceGrpc() {}

  public static final String SERVICE_NAME = "protobuf.QuestionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.QuestionRequest,
      protobuf.generated.QuestionServiceMessages.QuestionResponse> getAskQuestionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AskQuestion",
      requestType = protobuf.generated.QuestionServiceMessages.QuestionRequest.class,
      responseType = protobuf.generated.QuestionServiceMessages.QuestionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.QuestionRequest,
      protobuf.generated.QuestionServiceMessages.QuestionResponse> getAskQuestionMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.QuestionServiceMessages.QuestionRequest, protobuf.generated.QuestionServiceMessages.QuestionResponse> getAskQuestionMethod;
    if ((getAskQuestionMethod = QuestionServiceGrpc.getAskQuestionMethod) == null) {
      synchronized (QuestionServiceGrpc.class) {
        if ((getAskQuestionMethod = QuestionServiceGrpc.getAskQuestionMethod) == null) {
          QuestionServiceGrpc.getAskQuestionMethod = getAskQuestionMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.QuestionServiceMessages.QuestionRequest, protobuf.generated.QuestionServiceMessages.QuestionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AskQuestion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.QuestionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.QuestionServiceMessages.QuestionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new QuestionServiceMethodDescriptorSupplier("AskQuestion"))
              .build();
        }
      }
    }
    return getAskQuestionMethod;
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
    public void askQuestion(protobuf.generated.QuestionServiceMessages.QuestionRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.QuestionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAskQuestionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAskQuestionMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.QuestionServiceMessages.QuestionRequest,
                protobuf.generated.QuestionServiceMessages.QuestionResponse>(
                  this, METHODID_ASK_QUESTION)))
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
    public void askQuestion(protobuf.generated.QuestionServiceMessages.QuestionRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.QuestionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAskQuestionMethod(), getCallOptions()), request, responseObserver);
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
    public protobuf.generated.QuestionServiceMessages.QuestionResponse askQuestion(protobuf.generated.QuestionServiceMessages.QuestionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAskQuestionMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.QuestionServiceMessages.QuestionResponse> askQuestion(
        protobuf.generated.QuestionServiceMessages.QuestionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAskQuestionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ASK_QUESTION = 0;

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
          serviceImpl.askQuestion((protobuf.generated.QuestionServiceMessages.QuestionRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.QuestionServiceMessages.QuestionResponse>) responseObserver);
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
              .build();
        }
      }
    }
    return result;
  }
}
