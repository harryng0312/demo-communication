// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: controller/Asset.proto

// Protobuf Java Version: 4.26.1
package org.harryng.demo.controller.grpc.asset;

public interface AssetIdReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:controller.AssetIdReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.common.CommonReq header = 1;</code>
   * @return Whether the header field is set.
   */
  boolean hasHeader();
  /**
   * <code>.common.CommonReq header = 1;</code>
   * @return The header.
   */
  org.harryng.demo.controller.grpc.common.dto.CommonReq getHeader();
  /**
   * <code>.common.CommonReq header = 1;</code>
   */
  org.harryng.demo.controller.grpc.common.dto.CommonReqOrBuilder getHeaderOrBuilder();

  /**
   * <code>int64 id = 2;</code>
   * @return The id.
   */
  long getId();
}
