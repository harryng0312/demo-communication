// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common/CommonReqRes.proto

// Protobuf Java Version: 4.26.1
package org.harryng.demo.controller.grpc.common.dto;

public interface CommonResOrBuilder extends
    // @@protoc_insertion_point(interface_extends:common.CommonRes)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string correlationId = 1;</code>
   * @return The correlationId.
   */
  java.lang.String getCorrelationId();
  /**
   * <code>string correlationId = 1;</code>
   * @return The bytes for correlationId.
   */
  com.google.protobuf.ByteString
      getCorrelationIdBytes();

  /**
   * <code>int32 code = 2;</code>
   * @return The code.
   */
  int getCode();

  /**
   * <code>string msg = 3;</code>
   * @return The msg.
   */
  java.lang.String getMsg();
  /**
   * <code>string msg = 3;</code>
   * @return The bytes for msg.
   */
  com.google.protobuf.ByteString
      getMsgBytes();
}
