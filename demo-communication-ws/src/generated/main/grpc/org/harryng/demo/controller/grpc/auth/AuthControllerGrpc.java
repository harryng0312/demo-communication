package org.harryng.demo.controller.grpc.auth;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: controller/Auth.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthControllerGrpc {

  private AuthControllerGrpc() {}

  public static final java.lang.String SERVICE_NAME = "controller.AuthController";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.auth.LoginReq,
      org.harryng.demo.controller.grpc.auth.LoginRes> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = org.harryng.demo.controller.grpc.auth.LoginReq.class,
      responseType = org.harryng.demo.controller.grpc.auth.LoginRes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.auth.LoginReq,
      org.harryng.demo.controller.grpc.auth.LoginRes> getLoginMethod() {
    io.grpc.MethodDescriptor<org.harryng.demo.controller.grpc.auth.LoginReq, org.harryng.demo.controller.grpc.auth.LoginRes> getLoginMethod;
    if ((getLoginMethod = AuthControllerGrpc.getLoginMethod) == null) {
      synchronized (AuthControllerGrpc.class) {
        if ((getLoginMethod = AuthControllerGrpc.getLoginMethod) == null) {
          AuthControllerGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<org.harryng.demo.controller.grpc.auth.LoginReq, org.harryng.demo.controller.grpc.auth.LoginRes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.auth.LoginReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.harryng.demo.controller.grpc.auth.LoginRes.getDefaultInstance()))
              .setSchemaDescriptor(new AuthControllerMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthControllerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthControllerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthControllerStub>() {
        @java.lang.Override
        public AuthControllerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthControllerStub(channel, callOptions);
        }
      };
    return AuthControllerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthControllerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthControllerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthControllerBlockingStub>() {
        @java.lang.Override
        public AuthControllerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthControllerBlockingStub(channel, callOptions);
        }
      };
    return AuthControllerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthControllerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthControllerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthControllerFutureStub>() {
        @java.lang.Override
        public AuthControllerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthControllerFutureStub(channel, callOptions);
        }
      };
    return AuthControllerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void login(org.harryng.demo.controller.grpc.auth.LoginReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.auth.LoginRes> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AuthController.
   */
  public static abstract class AuthControllerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AuthControllerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AuthController.
   */
  public static final class AuthControllerStub
      extends io.grpc.stub.AbstractAsyncStub<AuthControllerStub> {
    private AuthControllerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthControllerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthControllerStub(channel, callOptions);
    }

    /**
     */
    public void login(org.harryng.demo.controller.grpc.auth.LoginReq request,
        io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.auth.LoginRes> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AuthController.
   */
  public static final class AuthControllerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AuthControllerBlockingStub> {
    private AuthControllerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthControllerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthControllerBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.harryng.demo.controller.grpc.auth.LoginRes login(org.harryng.demo.controller.grpc.auth.LoginReq request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AuthController.
   */
  public static final class AuthControllerFutureStub
      extends io.grpc.stub.AbstractFutureStub<AuthControllerFutureStub> {
    private AuthControllerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthControllerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthControllerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.harryng.demo.controller.grpc.auth.LoginRes> login(
        org.harryng.demo.controller.grpc.auth.LoginReq request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;

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
        case METHODID_LOGIN:
          serviceImpl.login((org.harryng.demo.controller.grpc.auth.LoginReq) request,
              (io.grpc.stub.StreamObserver<org.harryng.demo.controller.grpc.auth.LoginRes>) responseObserver);
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
          getLoginMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.harryng.demo.controller.grpc.auth.LoginReq,
              org.harryng.demo.controller.grpc.auth.LoginRes>(
                service, METHODID_LOGIN)))
        .build();
  }

  private static abstract class AuthControllerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuthControllerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.harryng.demo.controller.grpc.auth.Auth.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AuthController");
    }
  }

  private static final class AuthControllerFileDescriptorSupplier
      extends AuthControllerBaseDescriptorSupplier {
    AuthControllerFileDescriptorSupplier() {}
  }

  private static final class AuthControllerMethodDescriptorSupplier
      extends AuthControllerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AuthControllerMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AuthControllerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthControllerFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .build();
        }
      }
    }
    return result;
  }
}
