// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dto/auth/LoginReq.proto
// Protobuf Java Version: 4.26.1

package org.harryng.demo.controller.grpc.auth.dto;

public final class LoginReqOuterClass {
  private LoginReqOuterClass() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      LoginReqOuterClass.class.getName());
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
    internal_static_dto_auth_LoginReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_dto_auth_LoginReq_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\027dto/auth/LoginReq.proto\022\010dto.auth\032\032dto" +
      "/common/CommonReq.proto\"U\n\010LoginReq\022%\n\006h" +
      "eader\030\001 \001(\0132\025.dto.common.CommonReq\022\020\n\010us" +
      "ername\030\003 \001(\t\022\020\n\010password\030\004 \001(\tB-\n)org.ha" +
      "rryng.demo.controller.grpc.auth.dtoP\001b\006p" +
      "roto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          org.harryng.demo.controller.grpc.common.dto.CommonReqOuterClass.getDescriptor(),
        });
    internal_static_dto_auth_LoginReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_dto_auth_LoginReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_dto_auth_LoginReq_descriptor,
        new java.lang.String[] { "Header", "Username", "Password", });
    descriptor.resolveAllFeaturesImmutable();
    org.harryng.demo.controller.grpc.common.dto.CommonReqOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
