// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: controller/Auth.proto

// Protobuf Java Version: 3.25.3
package org.harryng.demo.controller.grpc.auth;

/**
 * Protobuf type {@code controller.LoginRes}
 */
public final class LoginRes extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:controller.LoginRes)
    LoginResOrBuilder {
private static final long serialVersionUID = 0L;
  // Use LoginRes.newBuilder() to construct.
  private LoginRes(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private LoginRes() {
    id_ = "";
    username_ = "";
    token_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new LoginRes();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.harryng.demo.controller.grpc.auth.Auth.internal_static_controller_LoginRes_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.harryng.demo.controller.grpc.auth.Auth.internal_static_controller_LoginRes_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.harryng.demo.controller.grpc.auth.LoginRes.class, org.harryng.demo.controller.grpc.auth.LoginRes.Builder.class);
  }

  private int bitField0_;
  public static final int HEADER_FIELD_NUMBER = 1;
  private org.harryng.demo.controller.grpc.common.dto.CommonRes header_;
  /**
   * <code>.common.CommonRes header = 1;</code>
   * @return Whether the header field is set.
   */
  @java.lang.Override
  public boolean hasHeader() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.common.CommonRes header = 1;</code>
   * @return The header.
   */
  @java.lang.Override
  public org.harryng.demo.controller.grpc.common.dto.CommonRes getHeader() {
    return header_ == null ? org.harryng.demo.controller.grpc.common.dto.CommonRes.getDefaultInstance() : header_;
  }
  /**
   * <code>.common.CommonRes header = 1;</code>
   */
  @java.lang.Override
  public org.harryng.demo.controller.grpc.common.dto.CommonResOrBuilder getHeaderOrBuilder() {
    return header_ == null ? org.harryng.demo.controller.grpc.common.dto.CommonRes.getDefaultInstance() : header_;
  }

  public static final int ID_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object id_ = "";
  /**
   * <code>string id = 2;</code>
   * @return The id.
   */
  @java.lang.Override
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 2;</code>
   * @return The bytes for id.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USERNAME_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile java.lang.Object username_ = "";
  /**
   * <code>string username = 3;</code>
   * @return The username.
   */
  @java.lang.Override
  public java.lang.String getUsername() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      username_ = s;
      return s;
    }
  }
  /**
   * <code>string username = 3;</code>
   * @return The bytes for username.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUsernameBytes() {
    java.lang.Object ref = username_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      username_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int REQUESTTIME_FIELD_NUMBER = 4;
  private com.google.protobuf.Timestamp requestTime_;
  /**
   * <code>.google.protobuf.Timestamp requestTime = 4;</code>
   * @return Whether the requestTime field is set.
   */
  @java.lang.Override
  public boolean hasRequestTime() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>.google.protobuf.Timestamp requestTime = 4;</code>
   * @return The requestTime.
   */
  @java.lang.Override
  public com.google.protobuf.Timestamp getRequestTime() {
    return requestTime_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : requestTime_;
  }
  /**
   * <code>.google.protobuf.Timestamp requestTime = 4;</code>
   */
  @java.lang.Override
  public com.google.protobuf.TimestampOrBuilder getRequestTimeOrBuilder() {
    return requestTime_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : requestTime_;
  }

  public static final int TOKEN_FIELD_NUMBER = 5;
  @SuppressWarnings("serial")
  private volatile java.lang.Object token_ = "";
  /**
   * <code>string token = 5;</code>
   * @return The token.
   */
  @java.lang.Override
  public java.lang.String getToken() {
    java.lang.Object ref = token_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      token_ = s;
      return s;
    }
  }
  /**
   * <code>string token = 5;</code>
   * @return The bytes for token.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTokenBytes() {
    java.lang.Object ref = token_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      token_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getHeader());
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, id_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(username_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, username_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeMessage(4, getRequestTime());
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(token_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, token_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getHeader());
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, id_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(username_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, username_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getRequestTime());
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(token_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, token_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.harryng.demo.controller.grpc.auth.LoginRes)) {
      return super.equals(obj);
    }
    org.harryng.demo.controller.grpc.auth.LoginRes other = (org.harryng.demo.controller.grpc.auth.LoginRes) obj;

    if (hasHeader() != other.hasHeader()) return false;
    if (hasHeader()) {
      if (!getHeader()
          .equals(other.getHeader())) return false;
    }
    if (!getId()
        .equals(other.getId())) return false;
    if (!getUsername()
        .equals(other.getUsername())) return false;
    if (hasRequestTime() != other.hasRequestTime()) return false;
    if (hasRequestTime()) {
      if (!getRequestTime()
          .equals(other.getRequestTime())) return false;
    }
    if (!getToken()
        .equals(other.getToken())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasHeader()) {
      hash = (37 * hash) + HEADER_FIELD_NUMBER;
      hash = (53 * hash) + getHeader().hashCode();
    }
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId().hashCode();
    hash = (37 * hash) + USERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getUsername().hashCode();
    if (hasRequestTime()) {
      hash = (37 * hash) + REQUESTTIME_FIELD_NUMBER;
      hash = (53 * hash) + getRequestTime().hashCode();
    }
    hash = (37 * hash) + TOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getToken().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.harryng.demo.controller.grpc.auth.LoginRes parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.harryng.demo.controller.grpc.auth.LoginRes parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.harryng.demo.controller.grpc.auth.LoginRes parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.harryng.demo.controller.grpc.auth.LoginRes prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code controller.LoginRes}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:controller.LoginRes)
      org.harryng.demo.controller.grpc.auth.LoginResOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.harryng.demo.controller.grpc.auth.Auth.internal_static_controller_LoginRes_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.harryng.demo.controller.grpc.auth.Auth.internal_static_controller_LoginRes_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.harryng.demo.controller.grpc.auth.LoginRes.class, org.harryng.demo.controller.grpc.auth.LoginRes.Builder.class);
    }

    // Construct using org.harryng.demo.controller.grpc.auth.LoginRes.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getHeaderFieldBuilder();
        getRequestTimeFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      header_ = null;
      if (headerBuilder_ != null) {
        headerBuilder_.dispose();
        headerBuilder_ = null;
      }
      id_ = "";
      username_ = "";
      requestTime_ = null;
      if (requestTimeBuilder_ != null) {
        requestTimeBuilder_.dispose();
        requestTimeBuilder_ = null;
      }
      token_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.harryng.demo.controller.grpc.auth.Auth.internal_static_controller_LoginRes_descriptor;
    }

    @java.lang.Override
    public org.harryng.demo.controller.grpc.auth.LoginRes getDefaultInstanceForType() {
      return org.harryng.demo.controller.grpc.auth.LoginRes.getDefaultInstance();
    }

    @java.lang.Override
    public org.harryng.demo.controller.grpc.auth.LoginRes build() {
      org.harryng.demo.controller.grpc.auth.LoginRes result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.harryng.demo.controller.grpc.auth.LoginRes buildPartial() {
      org.harryng.demo.controller.grpc.auth.LoginRes result = new org.harryng.demo.controller.grpc.auth.LoginRes(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.harryng.demo.controller.grpc.auth.LoginRes result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.header_ = headerBuilder_ == null
            ? header_
            : headerBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.id_ = id_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.username_ = username_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.requestTime_ = requestTimeBuilder_ == null
            ? requestTime_
            : requestTimeBuilder_.build();
        to_bitField0_ |= 0x00000002;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.token_ = token_;
      }
      result.bitField0_ |= to_bitField0_;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.harryng.demo.controller.grpc.auth.LoginRes) {
        return mergeFrom((org.harryng.demo.controller.grpc.auth.LoginRes)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.harryng.demo.controller.grpc.auth.LoginRes other) {
      if (other == org.harryng.demo.controller.grpc.auth.LoginRes.getDefaultInstance()) return this;
      if (other.hasHeader()) {
        mergeHeader(other.getHeader());
      }
      if (!other.getId().isEmpty()) {
        id_ = other.id_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (!other.getUsername().isEmpty()) {
        username_ = other.username_;
        bitField0_ |= 0x00000004;
        onChanged();
      }
      if (other.hasRequestTime()) {
        mergeRequestTime(other.getRequestTime());
      }
      if (!other.getToken().isEmpty()) {
        token_ = other.token_;
        bitField0_ |= 0x00000010;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              input.readMessage(
                  getHeaderFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              id_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              username_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            case 34: {
              input.readMessage(
                  getRequestTimeFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000008;
              break;
            } // case 34
            case 42: {
              token_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000010;
              break;
            } // case 42
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private org.harryng.demo.controller.grpc.common.dto.CommonRes header_;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.harryng.demo.controller.grpc.common.dto.CommonRes, org.harryng.demo.controller.grpc.common.dto.CommonRes.Builder, org.harryng.demo.controller.grpc.common.dto.CommonResOrBuilder> headerBuilder_;
    /**
     * <code>.common.CommonRes header = 1;</code>
     * @return Whether the header field is set.
     */
    public boolean hasHeader() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.common.CommonRes header = 1;</code>
     * @return The header.
     */
    public org.harryng.demo.controller.grpc.common.dto.CommonRes getHeader() {
      if (headerBuilder_ == null) {
        return header_ == null ? org.harryng.demo.controller.grpc.common.dto.CommonRes.getDefaultInstance() : header_;
      } else {
        return headerBuilder_.getMessage();
      }
    }
    /**
     * <code>.common.CommonRes header = 1;</code>
     */
    public Builder setHeader(org.harryng.demo.controller.grpc.common.dto.CommonRes value) {
      if (headerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        header_ = value;
      } else {
        headerBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.common.CommonRes header = 1;</code>
     */
    public Builder setHeader(
        org.harryng.demo.controller.grpc.common.dto.CommonRes.Builder builderForValue) {
      if (headerBuilder_ == null) {
        header_ = builderForValue.build();
      } else {
        headerBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.common.CommonRes header = 1;</code>
     */
    public Builder mergeHeader(org.harryng.demo.controller.grpc.common.dto.CommonRes value) {
      if (headerBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          header_ != null &&
          header_ != org.harryng.demo.controller.grpc.common.dto.CommonRes.getDefaultInstance()) {
          getHeaderBuilder().mergeFrom(value);
        } else {
          header_ = value;
        }
      } else {
        headerBuilder_.mergeFrom(value);
      }
      if (header_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.common.CommonRes header = 1;</code>
     */
    public Builder clearHeader() {
      bitField0_ = (bitField0_ & ~0x00000001);
      header_ = null;
      if (headerBuilder_ != null) {
        headerBuilder_.dispose();
        headerBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.common.CommonRes header = 1;</code>
     */
    public org.harryng.demo.controller.grpc.common.dto.CommonRes.Builder getHeaderBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getHeaderFieldBuilder().getBuilder();
    }
    /**
     * <code>.common.CommonRes header = 1;</code>
     */
    public org.harryng.demo.controller.grpc.common.dto.CommonResOrBuilder getHeaderOrBuilder() {
      if (headerBuilder_ != null) {
        return headerBuilder_.getMessageOrBuilder();
      } else {
        return header_ == null ?
            org.harryng.demo.controller.grpc.common.dto.CommonRes.getDefaultInstance() : header_;
      }
    }
    /**
     * <code>.common.CommonRes header = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.harryng.demo.controller.grpc.common.dto.CommonRes, org.harryng.demo.controller.grpc.common.dto.CommonRes.Builder, org.harryng.demo.controller.grpc.common.dto.CommonResOrBuilder> 
        getHeaderFieldBuilder() {
      if (headerBuilder_ == null) {
        headerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.harryng.demo.controller.grpc.common.dto.CommonRes, org.harryng.demo.controller.grpc.common.dto.CommonRes.Builder, org.harryng.demo.controller.grpc.common.dto.CommonResOrBuilder>(
                getHeader(),
                getParentForChildren(),
                isClean());
        header_ = null;
      }
      return headerBuilder_;
    }

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 2;</code>
     * @return The id.
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 2;</code>
     * @return The bytes for id.
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 2;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      id_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      id_ = getDefaultInstance().getId();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string id = 2;</code>
     * @param value The bytes for id to set.
     * @return This builder for chaining.
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      id_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private java.lang.Object username_ = "";
    /**
     * <code>string username = 3;</code>
     * @return The username.
     */
    public java.lang.String getUsername() {
      java.lang.Object ref = username_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        username_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string username = 3;</code>
     * @return The bytes for username.
     */
    public com.google.protobuf.ByteString
        getUsernameBytes() {
      java.lang.Object ref = username_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string username = 3;</code>
     * @param value The username to set.
     * @return This builder for chaining.
     */
    public Builder setUsername(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      username_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>string username = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearUsername() {
      username_ = getDefaultInstance().getUsername();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>string username = 3;</code>
     * @param value The bytes for username to set.
     * @return This builder for chaining.
     */
    public Builder setUsernameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      username_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }

    private com.google.protobuf.Timestamp requestTime_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> requestTimeBuilder_;
    /**
     * <code>.google.protobuf.Timestamp requestTime = 4;</code>
     * @return Whether the requestTime field is set.
     */
    public boolean hasRequestTime() {
      return ((bitField0_ & 0x00000008) != 0);
    }
    /**
     * <code>.google.protobuf.Timestamp requestTime = 4;</code>
     * @return The requestTime.
     */
    public com.google.protobuf.Timestamp getRequestTime() {
      if (requestTimeBuilder_ == null) {
        return requestTime_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : requestTime_;
      } else {
        return requestTimeBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Timestamp requestTime = 4;</code>
     */
    public Builder setRequestTime(com.google.protobuf.Timestamp value) {
      if (requestTimeBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        requestTime_ = value;
      } else {
        requestTimeBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp requestTime = 4;</code>
     */
    public Builder setRequestTime(
        com.google.protobuf.Timestamp.Builder builderForValue) {
      if (requestTimeBuilder_ == null) {
        requestTime_ = builderForValue.build();
      } else {
        requestTimeBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp requestTime = 4;</code>
     */
    public Builder mergeRequestTime(com.google.protobuf.Timestamp value) {
      if (requestTimeBuilder_ == null) {
        if (((bitField0_ & 0x00000008) != 0) &&
          requestTime_ != null &&
          requestTime_ != com.google.protobuf.Timestamp.getDefaultInstance()) {
          getRequestTimeBuilder().mergeFrom(value);
        } else {
          requestTime_ = value;
        }
      } else {
        requestTimeBuilder_.mergeFrom(value);
      }
      if (requestTime_ != null) {
        bitField0_ |= 0x00000008;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp requestTime = 4;</code>
     */
    public Builder clearRequestTime() {
      bitField0_ = (bitField0_ & ~0x00000008);
      requestTime_ = null;
      if (requestTimeBuilder_ != null) {
        requestTimeBuilder_.dispose();
        requestTimeBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp requestTime = 4;</code>
     */
    public com.google.protobuf.Timestamp.Builder getRequestTimeBuilder() {
      bitField0_ |= 0x00000008;
      onChanged();
      return getRequestTimeFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Timestamp requestTime = 4;</code>
     */
    public com.google.protobuf.TimestampOrBuilder getRequestTimeOrBuilder() {
      if (requestTimeBuilder_ != null) {
        return requestTimeBuilder_.getMessageOrBuilder();
      } else {
        return requestTime_ == null ?
            com.google.protobuf.Timestamp.getDefaultInstance() : requestTime_;
      }
    }
    /**
     * <code>.google.protobuf.Timestamp requestTime = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> 
        getRequestTimeFieldBuilder() {
      if (requestTimeBuilder_ == null) {
        requestTimeBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder>(
                getRequestTime(),
                getParentForChildren(),
                isClean());
        requestTime_ = null;
      }
      return requestTimeBuilder_;
    }

    private java.lang.Object token_ = "";
    /**
     * <code>string token = 5;</code>
     * @return The token.
     */
    public java.lang.String getToken() {
      java.lang.Object ref = token_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        token_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string token = 5;</code>
     * @return The bytes for token.
     */
    public com.google.protobuf.ByteString
        getTokenBytes() {
      java.lang.Object ref = token_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        token_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string token = 5;</code>
     * @param value The token to set.
     * @return This builder for chaining.
     */
    public Builder setToken(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      token_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>string token = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearToken() {
      token_ = getDefaultInstance().getToken();
      bitField0_ = (bitField0_ & ~0x00000010);
      onChanged();
      return this;
    }
    /**
     * <code>string token = 5;</code>
     * @param value The bytes for token to set.
     * @return This builder for chaining.
     */
    public Builder setTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      token_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:controller.LoginRes)
  }

  // @@protoc_insertion_point(class_scope:controller.LoginRes)
  private static final org.harryng.demo.controller.grpc.auth.LoginRes DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.harryng.demo.controller.grpc.auth.LoginRes();
  }

  public static org.harryng.demo.controller.grpc.auth.LoginRes getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<LoginRes>
      PARSER = new com.google.protobuf.AbstractParser<LoginRes>() {
    @java.lang.Override
    public LoginRes parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<LoginRes> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<LoginRes> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.harryng.demo.controller.grpc.auth.LoginRes getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

