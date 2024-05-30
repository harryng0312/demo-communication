// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: controller/Asset.proto

// Protobuf Java Version: 4.26.1
package org.harryng.demo.controller.grpc.asset;

public interface AssetDtoGrpcOrBuilder extends
    // @@protoc_insertion_point(interface_extends:controller.AssetDtoGrpc)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 id = 1;</code>
   * @return The id.
   */
  long getId();

  /**
   * <code>int64 orgId = 2;</code>
   * @return The orgId.
   */
  long getOrgId();

  /**
   * <code>string orgTreepath = 3;</code>
   * @return The orgTreepath.
   */
  java.lang.String getOrgTreepath();
  /**
   * <code>string orgTreepath = 3;</code>
   * @return The bytes for orgTreepath.
   */
  com.google.protobuf.ByteString
      getOrgTreepathBytes();

  /**
   * <code>string name = 4;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 4;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>optional string description = 5;</code>
   * @return Whether the description field is set.
   */
  boolean hasDescription();
  /**
   * <code>optional string description = 5;</code>
   * @return The description.
   */
  java.lang.String getDescription();
  /**
   * <code>optional string description = 5;</code>
   * @return The bytes for description.
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();
}
