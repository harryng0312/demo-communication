package org.harryng.demo.controller.grpc.asset;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: controller/Asset.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AssetControllerGrpc {

  private AssetControllerGrpc() {}

  public static final java.lang.String SERVICE_NAME = "controller.AssetController";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetIdReq,
      org.harryng.demo.controller.grpc.asset.AssetResultRes> getFindByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findById",
      requestType = org.harryng.demo.controller.grpc.asset.AssetIdReq.class,
      responseType = org.harryng.demo.controller.grpc.asset.AssetResultRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetIdReq,
      org.harryng.demo.controller.grpc.asset.AssetResultRes> getFindByIdMethod() {
    io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetIdReq, org.harryng.demo.controller.grpc.asset.AssetResultRes> getFindByIdMethod;
    if ((getFindByIdMethod = AssetControllerGrpc.getFindByIdMethod) == null) {
      synchronized (AssetControllerGrpc.class) {
        if ((getFindByIdMethod = AssetControllerGrpc.getFindByIdMethod) == null) {
          AssetControllerGrpc.getFindByIdMethod = getFindByIdMethod =
              io.grpc.MethodDescriptor.<org.harryng.demo.controller.grpc.asset.AssetIdReq, org.harryng.demo.controller.grpc.asset.AssetResultRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetIdReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetResultRes.getDefaultInstance()))
              .setSchemaDescriptor(new AssetControllerMethodDescriptorSupplier("findById"))
              .build();
        }
      }
    }
    return getFindByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetReq,
      org.harryng.demo.controller.grpc.asset.AssetRes> getAddMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "add",
      requestType = org.harryng.demo.controller.grpc.asset.AssetReq.class,
      responseType = org.harryng.demo.controller.grpc.asset.AssetRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetReq,
      org.harryng.demo.controller.grpc.asset.AssetRes> getAddMethod() {
    io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetReq, org.harryng.demo.controller.grpc.asset.AssetRes> getAddMethod;
    if ((getAddMethod = AssetControllerGrpc.getAddMethod) == null) {
      synchronized (AssetControllerGrpc.class) {
        if ((getAddMethod = AssetControllerGrpc.getAddMethod) == null) {
          AssetControllerGrpc.getAddMethod = getAddMethod =
              io.grpc.MethodDescriptor.<org.harryng.demo.controller.grpc.asset.AssetReq, org.harryng.demo.controller.grpc.asset.AssetRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "add"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetRes.getDefaultInstance()))
              .setSchemaDescriptor(new AssetControllerMethodDescriptorSupplier("add"))
              .build();
        }
      }
    }
    return getAddMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetReq,
      org.harryng.demo.controller.grpc.asset.AssetRes> getEditMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "edit",
      requestType = org.harryng.demo.controller.grpc.asset.AssetReq.class,
      responseType = org.harryng.demo.controller.grpc.asset.AssetRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetReq,
      org.harryng.demo.controller.grpc.asset.AssetRes> getEditMethod() {
    io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetReq, org.harryng.demo.controller.grpc.asset.AssetRes> getEditMethod;
    if ((getEditMethod = AssetControllerGrpc.getEditMethod) == null) {
      synchronized (AssetControllerGrpc.class) {
        if ((getEditMethod = AssetControllerGrpc.getEditMethod) == null) {
          AssetControllerGrpc.getEditMethod = getEditMethod =
              io.grpc.MethodDescriptor.<org.harryng.demo.controller.grpc.asset.AssetReq, org.harryng.demo.controller.grpc.asset.AssetRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "edit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetRes.getDefaultInstance()))
              .setSchemaDescriptor(new AssetControllerMethodDescriptorSupplier("edit"))
              .build();
        }
      }
    }
    return getEditMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetIdReq,
      org.harryng.demo.controller.grpc.asset.AssetRes> getRemoveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "remove",
      requestType = org.harryng.demo.controller.grpc.asset.AssetIdReq.class,
      responseType = org.harryng.demo.controller.grpc.asset.AssetRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetIdReq,
      org.harryng.demo.controller.grpc.asset.AssetRes> getRemoveMethod() {
    io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetIdReq, org.harryng.demo.controller.grpc.asset.AssetRes> getRemoveMethod;
    if ((getRemoveMethod = AssetControllerGrpc.getRemoveMethod) == null) {
      synchronized (AssetControllerGrpc.class) {
        if ((getRemoveMethod = AssetControllerGrpc.getRemoveMethod) == null) {
          AssetControllerGrpc.getRemoveMethod = getRemoveMethod =
              io.grpc.MethodDescriptor.<org.harryng.demo.controller.grpc.asset.AssetIdReq, org.harryng.demo.controller.grpc.asset.AssetRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "remove"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetIdReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetRes.getDefaultInstance()))
              .setSchemaDescriptor(new AssetControllerMethodDescriptorSupplier("remove"))
              .build();
        }
      }
    }
    return getRemoveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetNameReq,
      org.harryng.demo.controller.grpc.asset.AssetResultRes> getFindByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findByName",
      requestType = org.harryng.demo.controller.grpc.asset.AssetNameReq.class,
      responseType = org.harryng.demo.controller.grpc.asset.AssetResultRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetNameReq,
      org.harryng.demo.controller.grpc.asset.AssetResultRes> getFindByNameMethod() {
    io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.asset.AssetNameReq, org.harryng.demo.controller.grpc.asset.AssetResultRes> getFindByNameMethod;
    if ((getFindByNameMethod = AssetControllerGrpc.getFindByNameMethod) == null) {
      synchronized (AssetControllerGrpc.class) {
        if ((getFindByNameMethod = AssetControllerGrpc.getFindByNameMethod) == null) {
          AssetControllerGrpc.getFindByNameMethod = getFindByNameMethod =
              io.grpc.MethodDescriptor.<org.harryng.demo.controller.grpc.asset.AssetNameReq, org.harryng.demo.controller.grpc.asset.AssetResultRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetNameReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.asset.AssetResultRes.getDefaultInstance()))
              .setSchemaDescriptor(new AssetControllerMethodDescriptorSupplier("findByName"))
              .build();
        }
      }
    }
    return getFindByNameMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AssetControllerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AssetControllerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AssetControllerStub>() {
        @java.lang.Override
        public AssetControllerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AssetControllerStub(channel, callOptions);
        }
      };
    return AssetControllerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AssetControllerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AssetControllerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AssetControllerBlockingStub>() {
        @java.lang.Override
        public AssetControllerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AssetControllerBlockingStub(channel, callOptions);
        }
      };
    return AssetControllerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AssetControllerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AssetControllerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AssetControllerFutureStub>() {
        @java.lang.Override
        public AssetControllerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AssetControllerFutureStub(channel, callOptions);
        }
      };
    return AssetControllerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void findById(org.harryng.demo.controller.grpc.asset.AssetIdReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetResultRes> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindByIdMethod(), responseObserver);
    }

    /**
     */
    default void add(org.harryng.demo.controller.grpc.asset.AssetReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetRes> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddMethod(), responseObserver);
    }

    /**
     */
    default void edit(org.harryng.demo.controller.grpc.asset.AssetReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetRes> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEditMethod(), responseObserver);
    }

    /**
     */
    default void remove(org.harryng.demo.controller.grpc.asset.AssetIdReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetRes> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveMethod(), responseObserver);
    }

    /**
     */
    default void findByName(org.harryng.demo.controller.grpc.asset.AssetNameReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetResultRes> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindByNameMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AssetController.
   */
  public static abstract class AssetControllerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AssetControllerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AssetController.
   */
  public static final class AssetControllerStub
      extends io.grpc.stub.AbstractAsyncStub<AssetControllerStub> {
    private AssetControllerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AssetControllerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AssetControllerStub(channel, callOptions);
    }

    /**
     */
    public void findById(org.harryng.demo.controller.grpc.asset.AssetIdReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetResultRes> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void add(org.harryng.demo.controller.grpc.asset.AssetReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetRes> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void edit(org.harryng.demo.controller.grpc.asset.AssetReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetRes> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEditMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void remove(org.harryng.demo.controller.grpc.asset.AssetIdReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetRes> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findByName(org.harryng.demo.controller.grpc.asset.AssetNameReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetResultRes> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindByNameMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AssetController.
   */
  public static final class AssetControllerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AssetControllerBlockingStub> {
    private AssetControllerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AssetControllerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AssetControllerBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.harryng.demo.controller.grpc.asset.AssetResultRes findById(org.harryng.demo.controller.grpc.asset.AssetIdReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.harryng.demo.controller.grpc.asset.AssetRes add(org.harryng.demo.controller.grpc.asset.AssetReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.harryng.demo.controller.grpc.asset.AssetRes edit(org.harryng.demo.controller.grpc.asset.AssetReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEditMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.harryng.demo.controller.grpc.asset.AssetRes remove(org.harryng.demo.controller.grpc.asset.AssetIdReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.harryng.demo.controller.grpc.asset.AssetResultRes findByName(org.harryng.demo.controller.grpc.asset.AssetNameReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindByNameMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AssetController.
   */
  public static final class AssetControllerFutureStub
      extends io.grpc.stub.AbstractFutureStub<AssetControllerFutureStub> {
    private AssetControllerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AssetControllerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AssetControllerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.harryng.demo.controller.grpc.asset.AssetResultRes> findById(
        org.harryng.demo.controller.grpc.asset.AssetIdReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.harryng.demo.controller.grpc.asset.AssetRes> add(
        org.harryng.demo.controller.grpc.asset.AssetReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.harryng.demo.controller.grpc.asset.AssetRes> edit(
        org.harryng.demo.controller.grpc.asset.AssetReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEditMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.harryng.demo.controller.grpc.asset.AssetRes> remove(
        org.harryng.demo.controller.grpc.asset.AssetIdReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.harryng.demo.controller.grpc.asset.AssetResultRes> findByName(
        org.harryng.demo.controller.grpc.asset.AssetNameReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindByNameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_BY_ID = 0;
  private static final int METHODID_ADD = 1;
  private static final int METHODID_EDIT = 2;
  private static final int METHODID_REMOVE = 3;
  private static final int METHODID_FIND_BY_NAME = 4;

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
        case METHODID_FIND_BY_ID:
          serviceImpl.findById((org.harryng.demo.controller.grpc.asset.AssetIdReq) request,
              (io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetResultRes>) responseObserver);
          break;
        case METHODID_ADD:
          serviceImpl.add((org.harryng.demo.controller.grpc.asset.AssetReq) request,
              (io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetRes>) responseObserver);
          break;
        case METHODID_EDIT:
          serviceImpl.edit((org.harryng.demo.controller.grpc.asset.AssetReq) request,
              (io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetRes>) responseObserver);
          break;
        case METHODID_REMOVE:
          serviceImpl.remove((org.harryng.demo.controller.grpc.asset.AssetIdReq) request,
              (io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetRes>) responseObserver);
          break;
        case METHODID_FIND_BY_NAME:
          serviceImpl.findByName((org.harryng.demo.controller.grpc.asset.AssetNameReq) request,
              (io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.asset.AssetResultRes>) responseObserver);
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
          getFindByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.harryng.demo.controller.grpc.asset.AssetIdReq,
              org.harryng.demo.controller.grpc.asset.AssetResultRes>(
                service, METHODID_FIND_BY_ID)))
        .addMethod(
          getAddMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.harryng.demo.controller.grpc.asset.AssetReq,
              org.harryng.demo.controller.grpc.asset.AssetRes>(
                service, METHODID_ADD)))
        .addMethod(
          getEditMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.harryng.demo.controller.grpc.asset.AssetReq,
              org.harryng.demo.controller.grpc.asset.AssetRes>(
                service, METHODID_EDIT)))
        .addMethod(
          getRemoveMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.harryng.demo.controller.grpc.asset.AssetIdReq,
              org.harryng.demo.controller.grpc.asset.AssetRes>(
                service, METHODID_REMOVE)))
        .addMethod(
          getFindByNameMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.harryng.demo.controller.grpc.asset.AssetNameReq,
              org.harryng.demo.controller.grpc.asset.AssetResultRes>(
                service, METHODID_FIND_BY_NAME)))
        .build();
  }

  private static abstract class AssetControllerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AssetControllerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.harryng.demo.controller.grpc.asset.Asset.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AssetController");
    }
  }

  private static final class AssetControllerFileDescriptorSupplier
      extends AssetControllerBaseDescriptorSupplier {
    AssetControllerFileDescriptorSupplier() {}
  }

  private static final class AssetControllerMethodDescriptorSupplier
      extends AssetControllerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AssetControllerMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AssetControllerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AssetControllerFileDescriptorSupplier())
              .addMethod(getFindByIdMethod())
              .addMethod(getAddMethod())
              .addMethod(getEditMethod())
              .addMethod(getRemoveMethod())
              .addMethod(getFindByNameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
