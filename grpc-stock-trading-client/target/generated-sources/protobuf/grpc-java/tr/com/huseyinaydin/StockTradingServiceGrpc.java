package tr.com.huseyinaydin;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * UNARY - RPC -&gt; güncel hisse senedi fiyatını edin
 * </pre>
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class StockTradingServiceGrpc {

  private StockTradingServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "tr.com.huseyinaydin.StockTradingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<tr.com.huseyinaydin.StockRequest,
      tr.com.huseyinaydin.StockResponse> getGetStockPriceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getStockPrice",
      requestType = tr.com.huseyinaydin.StockRequest.class,
      responseType = tr.com.huseyinaydin.StockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tr.com.huseyinaydin.StockRequest,
      tr.com.huseyinaydin.StockResponse> getGetStockPriceMethod() {
    io.grpc.MethodDescriptor<tr.com.huseyinaydin.StockRequest, tr.com.huseyinaydin.StockResponse> getGetStockPriceMethod;
    if ((getGetStockPriceMethod = StockTradingServiceGrpc.getGetStockPriceMethod) == null) {
      synchronized (StockTradingServiceGrpc.class) {
        if ((getGetStockPriceMethod = StockTradingServiceGrpc.getGetStockPriceMethod) == null) {
          StockTradingServiceGrpc.getGetStockPriceMethod = getGetStockPriceMethod =
              io.grpc.MethodDescriptor.<tr.com.huseyinaydin.StockRequest, tr.com.huseyinaydin.StockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getStockPrice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tr.com.huseyinaydin.StockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tr.com.huseyinaydin.StockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StockTradingServiceMethodDescriptorSupplier("getStockPrice"))
              .build();
        }
      }
    }
    return getGetStockPriceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StockTradingServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StockTradingServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StockTradingServiceStub>() {
        @java.lang.Override
        public StockTradingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StockTradingServiceStub(channel, callOptions);
        }
      };
    return StockTradingServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static StockTradingServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StockTradingServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StockTradingServiceBlockingV2Stub>() {
        @java.lang.Override
        public StockTradingServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StockTradingServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return StockTradingServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StockTradingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StockTradingServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StockTradingServiceBlockingStub>() {
        @java.lang.Override
        public StockTradingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StockTradingServiceBlockingStub(channel, callOptions);
        }
      };
    return StockTradingServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StockTradingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StockTradingServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StockTradingServiceFutureStub>() {
        @java.lang.Override
        public StockTradingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StockTradingServiceFutureStub(channel, callOptions);
        }
      };
    return StockTradingServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * UNARY - RPC -&gt; güncel hisse senedi fiyatını edin
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     *UNARY RPC: Bu, gRPC'nin temel RPC türüdür. İstemci sunucuya bir istek gönderir ve sunucu da sadece bir yanıt döner. Yani, tek bir istek ve tek bir yanıt vardır.
     *Bu örnekte, istemci hisse senedinin sembolünü (stockSymbol) gönderiyor ve sunucu da bu sembole karşılık gelen hisse fiyatını (price) geri döndürüyor.
     *İstemci ile sunucu arasında her işlem tek bir veri gönderimi ve cevabı ile yapılır, bu da daha hızlı ve basit işlem akışlarını sağlar.
     * </pre>
     */
    default void getStockPrice(tr.com.huseyinaydin.StockRequest request,
        io.grpc.stub.StreamObserver<tr.com.huseyinaydin.StockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStockPriceMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StockTradingService.
   * <pre>
   * UNARY - RPC -&gt; güncel hisse senedi fiyatını edin
   * </pre>
   */
  public static abstract class StockTradingServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return StockTradingServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StockTradingService.
   * <pre>
   * UNARY - RPC -&gt; güncel hisse senedi fiyatını edin
   * </pre>
   */
  public static final class StockTradingServiceStub
      extends io.grpc.stub.AbstractAsyncStub<StockTradingServiceStub> {
    private StockTradingServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StockTradingServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StockTradingServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *UNARY RPC: Bu, gRPC'nin temel RPC türüdür. İstemci sunucuya bir istek gönderir ve sunucu da sadece bir yanıt döner. Yani, tek bir istek ve tek bir yanıt vardır.
     *Bu örnekte, istemci hisse senedinin sembolünü (stockSymbol) gönderiyor ve sunucu da bu sembole karşılık gelen hisse fiyatını (price) geri döndürüyor.
     *İstemci ile sunucu arasında her işlem tek bir veri gönderimi ve cevabı ile yapılır, bu da daha hızlı ve basit işlem akışlarını sağlar.
     * </pre>
     */
    public void getStockPrice(tr.com.huseyinaydin.StockRequest request,
        io.grpc.stub.StreamObserver<tr.com.huseyinaydin.StockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStockPriceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StockTradingService.
   * <pre>
   * UNARY - RPC -&gt; güncel hisse senedi fiyatını edin
   * </pre>
   */
  public static final class StockTradingServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<StockTradingServiceBlockingV2Stub> {
    private StockTradingServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StockTradingServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StockTradingServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     *UNARY RPC: Bu, gRPC'nin temel RPC türüdür. İstemci sunucuya bir istek gönderir ve sunucu da sadece bir yanıt döner. Yani, tek bir istek ve tek bir yanıt vardır.
     *Bu örnekte, istemci hisse senedinin sembolünü (stockSymbol) gönderiyor ve sunucu da bu sembole karşılık gelen hisse fiyatını (price) geri döndürüyor.
     *İstemci ile sunucu arasında her işlem tek bir veri gönderimi ve cevabı ile yapılır, bu da daha hızlı ve basit işlem akışlarını sağlar.
     * </pre>
     */
    public tr.com.huseyinaydin.StockResponse getStockPrice(tr.com.huseyinaydin.StockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStockPriceMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service StockTradingService.
   * <pre>
   * UNARY - RPC -&gt; güncel hisse senedi fiyatını edin
   * </pre>
   */
  public static final class StockTradingServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StockTradingServiceBlockingStub> {
    private StockTradingServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StockTradingServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StockTradingServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *UNARY RPC: Bu, gRPC'nin temel RPC türüdür. İstemci sunucuya bir istek gönderir ve sunucu da sadece bir yanıt döner. Yani, tek bir istek ve tek bir yanıt vardır.
     *Bu örnekte, istemci hisse senedinin sembolünü (stockSymbol) gönderiyor ve sunucu da bu sembole karşılık gelen hisse fiyatını (price) geri döndürüyor.
     *İstemci ile sunucu arasında her işlem tek bir veri gönderimi ve cevabı ile yapılır, bu da daha hızlı ve basit işlem akışlarını sağlar.
     * </pre>
     */
    public tr.com.huseyinaydin.StockResponse getStockPrice(tr.com.huseyinaydin.StockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStockPriceMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StockTradingService.
   * <pre>
   * UNARY - RPC -&gt; güncel hisse senedi fiyatını edin
   * </pre>
   */
  public static final class StockTradingServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<StockTradingServiceFutureStub> {
    private StockTradingServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StockTradingServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StockTradingServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *UNARY RPC: Bu, gRPC'nin temel RPC türüdür. İstemci sunucuya bir istek gönderir ve sunucu da sadece bir yanıt döner. Yani, tek bir istek ve tek bir yanıt vardır.
     *Bu örnekte, istemci hisse senedinin sembolünü (stockSymbol) gönderiyor ve sunucu da bu sembole karşılık gelen hisse fiyatını (price) geri döndürüyor.
     *İstemci ile sunucu arasında her işlem tek bir veri gönderimi ve cevabı ile yapılır, bu da daha hızlı ve basit işlem akışlarını sağlar.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<tr.com.huseyinaydin.StockResponse> getStockPrice(
        tr.com.huseyinaydin.StockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStockPriceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_STOCK_PRICE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_STOCK_PRICE:
          serviceImpl.getStockPrice((tr.com.huseyinaydin.StockRequest) request,
              (io.grpc.stub.StreamObserver<tr.com.huseyinaydin.StockResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetStockPriceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              tr.com.huseyinaydin.StockRequest,
              tr.com.huseyinaydin.StockResponse>(
                service, METHODID_GET_STOCK_PRICE)))
        .build();
  }

  private static abstract class StockTradingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StockTradingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return tr.com.huseyinaydin.StockTradingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StockTradingService");
    }
  }

  private static final class StockTradingServiceFileDescriptorSupplier
      extends StockTradingServiceBaseDescriptorSupplier {
    StockTradingServiceFileDescriptorSupplier() {}
  }

  private static final class StockTradingServiceMethodDescriptorSupplier
      extends StockTradingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    StockTradingServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (StockTradingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StockTradingServiceFileDescriptorSupplier())
              .addMethod(getGetStockPriceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
