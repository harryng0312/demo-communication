// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common/ValidationResult.proto

// Protobuf Java Version: 3.25.3
package org.harryng.demo.controller.grpc.common.dto;

public final class ValidationResultOuterClass {
  private ValidationResultOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_common_ValidationError_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_common_ValidationError_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_common_ValidationResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_common_ValidationResult_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035common/ValidationResult.proto\022\006common\"" +
      "9\n\017ValidationError\022\n\n\002id\030\001 \001(\003\022\r\n\005field\030" +
      "\002 \001(\t\022\013\n\003msg\030\003 \001(\t\"S\n\020ValidationResult\022\r" +
      "\n\005valid\030\001 \001(\010\0220\n\017validationError\030\002 \003(\0132\027" +
      ".common.ValidationErrorB5\n+org.harryng.d" +
      "emo.controller.grpc.common.dtoP\001\210\001\001\240\001\001b\006" +
      "proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_common_ValidationError_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_common_ValidationError_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_common_ValidationError_descriptor,
        new java.lang.String[] { "Id", "Field", "Msg", });
    internal_static_common_ValidationResult_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_common_ValidationResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_common_ValidationResult_descriptor,
        new java.lang.String[] { "Valid", "ValidationError", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
