// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common/CommonReq.proto

// Protobuf Java Version: 4.26.1
package org.harryng.demo.controller.grpc.common.dto;

/**
 * Protobuf type {@code common.CommonReq}
 */
public final class CommonReq extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:common.CommonReq)
    CommonReqOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      CommonReq.class.getName());
  }
  // Use CommonReq.newBuilder() to construct.
  private CommonReq(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private CommonReq() {
    correlationId_ = "";
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.harryng.demo.controller.grpc.common.dto.CommonReqOuterClass.internal_static_common_CommonReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.harryng.demo.controller.grpc.common.dto.CommonReqOuterClass.internal_static_common_CommonReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.harryng.demo.controller.grpc.common.dto.CommonReq.class, org.harryng.demo.controller.grpc.common.dto.CommonReq.Builder.class);
  }

  public static final int CORRELATIONID_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object correlationId_ = "";
  /**
   * <code>string correlationId = 1;</code>
   * @return The correlationId.
   */
  @java.lang.Override
  public java.lang.String getCorrelationId() {
    java.lang.Object ref = correlationId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      correlationId_ = s;
      return s;
    }
  }
  /**
   * <code>string correlationId = 1;</code>
   * @return The bytes for correlationId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCorrelationIdBytes() {
    java.lang.Object ref = correlationId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      correlationId_ = b;
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
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(correlationId_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, correlationId_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(correlationId_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, correlationId_);
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
    if (!(obj instanceof org.harryng.demo.controller.grpc.common.dto.CommonReq)) {
      return super.equals(obj);
    }
    org.harryng.demo.controller.grpc.common.dto.CommonReq other = (org.harryng.demo.controller.grpc.common.dto.CommonReq) obj;

    if (!getCorrelationId()
        .equals(other.getCorrelationId())) return false;
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
    hash = (37 * hash) + CORRELATIONID_FIELD_NUMBER;
    hash = (53 * hash) + getCorrelationId().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static org.harryng.demo.controller.grpc.common.dto.CommonReq parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.harryng.demo.controller.grpc.common.dto.CommonReq prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code common.CommonReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:common.CommonReq)
      org.harryng.demo.controller.grpc.common.dto.CommonReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.harryng.demo.controller.grpc.common.dto.CommonReqOuterClass.internal_static_common_CommonReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.harryng.demo.controller.grpc.common.dto.CommonReqOuterClass.internal_static_common_CommonReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.harryng.demo.controller.grpc.common.dto.CommonReq.class, org.harryng.demo.controller.grpc.common.dto.CommonReq.Builder.class);
    }

    // Construct using org.harryng.demo.controller.grpc.common.dto.CommonReq.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      correlationId_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.harryng.demo.controller.grpc.common.dto.CommonReqOuterClass.internal_static_common_CommonReq_descriptor;
    }

    @java.lang.Override
    public org.harryng.demo.controller.grpc.common.dto.CommonReq getDefaultInstanceForType() {
      return org.harryng.demo.controller.grpc.common.dto.CommonReq.getDefaultInstance();
    }

    @java.lang.Override
    public org.harryng.demo.controller.grpc.common.dto.CommonReq build() {
      org.harryng.demo.controller.grpc.common.dto.CommonReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.harryng.demo.controller.grpc.common.dto.CommonReq buildPartial() {
      org.harryng.demo.controller.grpc.common.dto.CommonReq result = new org.harryng.demo.controller.grpc.common.dto.CommonReq(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.harryng.demo.controller.grpc.common.dto.CommonReq result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.correlationId_ = correlationId_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.harryng.demo.controller.grpc.common.dto.CommonReq) {
        return mergeFrom((org.harryng.demo.controller.grpc.common.dto.CommonReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.harryng.demo.controller.grpc.common.dto.CommonReq other) {
      if (other == org.harryng.demo.controller.grpc.common.dto.CommonReq.getDefaultInstance()) return this;
      if (!other.getCorrelationId().isEmpty()) {
        correlationId_ = other.correlationId_;
        bitField0_ |= 0x00000001;
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
              correlationId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
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

    private java.lang.Object correlationId_ = "";
    /**
     * <code>string correlationId = 1;</code>
     * @return The correlationId.
     */
    public java.lang.String getCorrelationId() {
      java.lang.Object ref = correlationId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        correlationId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string correlationId = 1;</code>
     * @return The bytes for correlationId.
     */
    public com.google.protobuf.ByteString
        getCorrelationIdBytes() {
      java.lang.Object ref = correlationId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        correlationId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string correlationId = 1;</code>
     * @param value The correlationId to set.
     * @return This builder for chaining.
     */
    public Builder setCorrelationId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      correlationId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string correlationId = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCorrelationId() {
      correlationId_ = getDefaultInstance().getCorrelationId();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string correlationId = 1;</code>
     * @param value The bytes for correlationId to set.
     * @return This builder for chaining.
     */
    public Builder setCorrelationIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      correlationId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:common.CommonReq)
  }

  // @@protoc_insertion_point(class_scope:common.CommonReq)
  private static final org.harryng.demo.controller.grpc.common.dto.CommonReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.harryng.demo.controller.grpc.common.dto.CommonReq();
  }

  public static org.harryng.demo.controller.grpc.common.dto.CommonReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CommonReq>
      PARSER = new com.google.protobuf.AbstractParser<CommonReq>() {
    @java.lang.Override
    public CommonReq parsePartialFrom(
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

  public static com.google.protobuf.Parser<CommonReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CommonReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.harryng.demo.controller.grpc.common.dto.CommonReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

