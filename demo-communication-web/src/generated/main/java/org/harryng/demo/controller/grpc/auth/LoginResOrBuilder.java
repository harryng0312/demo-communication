// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: controller/Auth.proto

// Protobuf Java Version: 4.26.1
package org.harryng.demo.controller.grpc.auth;

public interface LoginResOrBuilder extends
    // @@protoc_insertion_point(interface_extends:controller.LoginRes)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.common.CommonRes header = 1;</code>
   * @return Whether the header field is set.
   */
  boolean hasHeader();
  /**
   * <code>.common.CommonRes header = 1;</code>
   * @return The header.
   */
  org.harryng.demo.controller.grpc.common.dto.CommonRes getHeader();
  /**
   * <code>.common.CommonRes header = 1;</code>
   */
  org.harryng.demo.controller.grpc.common.dto.CommonResOrBuilder getHeaderOrBuilder();

  /**
   * <code>string id = 2;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 2;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string username = 3;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <code>string username = 3;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>.google.protobuf.Timestamp requestTime = 4;</code>
   * @return Whether the requestTime field is set.
   */
  boolean hasRequestTime();
  /**
   * <code>.google.protobuf.Timestamp requestTime = 4;</code>
   * @return The requestTime.
   */
  com.google.protobuf.Timestamp getRequestTime();
  /**
   * <code>.google.protobuf.Timestamp requestTime = 4;</code>
   */
  com.google.protobuf.TimestampOrBuilder getRequestTimeOrBuilder();

  /**
   * <code>string token = 5;</code>
   * @return The token.
   */
  java.lang.String getToken();
  /**
   * <code>string token = 5;</code>
   * @return The bytes for token.
   */
  com.google.protobuf.ByteString
      getTokenBytes();
}
