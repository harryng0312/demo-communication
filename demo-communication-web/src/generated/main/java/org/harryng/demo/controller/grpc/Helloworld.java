// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: helloworld.proto
// Protobuf Java Version: 4.26.1

package org.harryng.demo.controller.grpc;

public final class Helloworld {
  private Helloworld() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      Helloworld.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_HelloWorldRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_HelloWorldRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_HelloWorldResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_HelloWorldResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020helloworld.proto\"/\n\021HelloWorldRequest\022" +
      "\021\n\004name\030\001 \001(\tH\000\210\001\001B\007\n\005_name\"&\n\022HelloWorl" +
      "dResponse\022\020\n\010greeting\030\001 \001(\t2L\n\021HelloWorl" +
      "dService\0227\n\010SayHello\022\022.HelloWorldRequest" +
      "\032\023.HelloWorldResponse(\0010\001B$\n org.harryng" +
      ".demo.controller.grpcP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_HelloWorldRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_HelloWorldRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_HelloWorldRequest_descriptor,
        new java.lang.String[] { "Name", });
    internal_static_HelloWorldResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_HelloWorldResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_HelloWorldResponse_descriptor,
        new java.lang.String[] { "Greeting", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
