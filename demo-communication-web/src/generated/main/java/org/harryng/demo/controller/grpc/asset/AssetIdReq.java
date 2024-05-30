// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: controller/Asset.proto

// Protobuf Java Version: 4.26.1
package org.harryng.demo.controller.grpc.asset;

/**
 * Protobuf type {@code controller.AssetIdReq}
 */
public final class AssetIdReq extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:controller.AssetIdReq)
    AssetIdReqOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      AssetIdReq.class.getName());
  }
  // Use AssetIdReq.newBuilder() to construct.
  private AssetIdReq(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private AssetIdReq() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.harryng.demo.controller.grpc.asset.Asset.internal_static_controller_AssetIdReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.harryng.demo.controller.grpc.asset.Asset.internal_static_controller_AssetIdReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.harryng.demo.controller.grpc.asset.AssetIdReq.class, org.harryng.demo.controller.grpc.asset.AssetIdReq.Builder.class);
  }

  private int bitField0_;
  public static final int HEADER_FIELD_NUMBER = 1;
  private org.harryng.demo.controller.grpc.common.dto.CommonReq header_;
  /**
   * <code>.common.CommonReq header = 1;</code>
   * @return Whether the header field is set.
   */
  @java.lang.Override
  public boolean hasHeader() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.common.CommonReq header = 1;</code>
   * @return The header.
   */
  @java.lang.Override
  public org.harryng.demo.controller.grpc.common.dto.CommonReq getHeader() {
    return header_ == null ? org.harryng.demo.controller.grpc.common.dto.CommonReq.getDefaultInstance() : header_;
  }
  /**
   * <code>.common.CommonReq header = 1;</code>
   */
  @java.lang.Override
  public org.harryng.demo.controller.grpc.common.dto.CommonReqOrBuilder getHeaderOrBuilder() {
    return header_ == null ? org.harryng.demo.controller.grpc.common.dto.CommonReq.getDefaultInstance() : header_;
  }

  public static final int ID_FIELD_NUMBER = 2;
  private long id_ = 0L;
  /**
   * <code>int64 id = 2;</code>
   * @return The id.
   */
  @java.lang.Override
  public long getId() {
    return id_;
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
    if (id_ != 0L) {
      output.writeInt64(2, id_);
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
    if (id_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, id_);
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
    if (!(obj instanceof org.harryng.demo.controller.grpc.asset.AssetIdReq)) {
      return super.equals(obj);
    }
    org.harryng.demo.controller.grpc.asset.AssetIdReq other = (org.harryng.demo.controller.grpc.asset.AssetIdReq) obj;

    if (hasHeader() != other.hasHeader()) return false;
    if (hasHeader()) {
      if (!getHeader()
          .equals(other.getHeader())) return false;
    }
    if (getId()
        != other.getId()) return false;
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
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getId());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static org.harryng.demo.controller.grpc.asset.AssetIdReq parseFrom(
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
  public static Builder newBuilder(org.harryng.demo.controller.grpc.asset.AssetIdReq prototype) {
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
   * Protobuf type {@code controller.AssetIdReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:controller.AssetIdReq)
      org.harryng.demo.controller.grpc.asset.AssetIdReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.harryng.demo.controller.grpc.asset.Asset.internal_static_controller_AssetIdReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.harryng.demo.controller.grpc.asset.Asset.internal_static_controller_AssetIdReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.harryng.demo.controller.grpc.asset.AssetIdReq.class, org.harryng.demo.controller.grpc.asset.AssetIdReq.Builder.class);
    }

    // Construct using org.harryng.demo.controller.grpc.asset.AssetIdReq.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage
              .alwaysUseFieldBuilders) {
        getHeaderFieldBuilder();
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
      id_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.harryng.demo.controller.grpc.asset.Asset.internal_static_controller_AssetIdReq_descriptor;
    }

    @java.lang.Override
    public org.harryng.demo.controller.grpc.asset.AssetIdReq getDefaultInstanceForType() {
      return org.harryng.demo.controller.grpc.asset.AssetIdReq.getDefaultInstance();
    }

    @java.lang.Override
    public org.harryng.demo.controller.grpc.asset.AssetIdReq build() {
      org.harryng.demo.controller.grpc.asset.AssetIdReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.harryng.demo.controller.grpc.asset.AssetIdReq buildPartial() {
      org.harryng.demo.controller.grpc.asset.AssetIdReq result = new org.harryng.demo.controller.grpc.asset.AssetIdReq(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.harryng.demo.controller.grpc.asset.AssetIdReq result) {
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
      result.bitField0_ |= to_bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.harryng.demo.controller.grpc.asset.AssetIdReq) {
        return mergeFrom((org.harryng.demo.controller.grpc.asset.AssetIdReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.harryng.demo.controller.grpc.asset.AssetIdReq other) {
      if (other == org.harryng.demo.controller.grpc.asset.AssetIdReq.getDefaultInstance()) return this;
      if (other.hasHeader()) {
        mergeHeader(other.getHeader());
      }
      if (other.getId() != 0L) {
        setId(other.getId());
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
            case 16: {
              id_ = input.readInt64();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
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

    private org.harryng.demo.controller.grpc.common.dto.CommonReq header_;
    private com.google.protobuf.SingleFieldBuilder<
        org.harryng.demo.controller.grpc.common.dto.CommonReq, org.harryng.demo.controller.grpc.common.dto.CommonReq.Builder, org.harryng.demo.controller.grpc.common.dto.CommonReqOrBuilder> headerBuilder_;
    /**
     * <code>.common.CommonReq header = 1;</code>
     * @return Whether the header field is set.
     */
    public boolean hasHeader() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.common.CommonReq header = 1;</code>
     * @return The header.
     */
    public org.harryng.demo.controller.grpc.common.dto.CommonReq getHeader() {
      if (headerBuilder_ == null) {
        return header_ == null ? org.harryng.demo.controller.grpc.common.dto.CommonReq.getDefaultInstance() : header_;
      } else {
        return headerBuilder_.getMessage();
      }
    }
    /**
     * <code>.common.CommonReq header = 1;</code>
     */
    public Builder setHeader(org.harryng.demo.controller.grpc.common.dto.CommonReq value) {
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
     * <code>.common.CommonReq header = 1;</code>
     */
    public Builder setHeader(
        org.harryng.demo.controller.grpc.common.dto.CommonReq.Builder builderForValue) {
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
     * <code>.common.CommonReq header = 1;</code>
     */
    public Builder mergeHeader(org.harryng.demo.controller.grpc.common.dto.CommonReq value) {
      if (headerBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          header_ != null &&
          header_ != org.harryng.demo.controller.grpc.common.dto.CommonReq.getDefaultInstance()) {
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
     * <code>.common.CommonReq header = 1;</code>
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
     * <code>.common.CommonReq header = 1;</code>
     */
    public org.harryng.demo.controller.grpc.common.dto.CommonReq.Builder getHeaderBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getHeaderFieldBuilder().getBuilder();
    }
    /**
     * <code>.common.CommonReq header = 1;</code>
     */
    public org.harryng.demo.controller.grpc.common.dto.CommonReqOrBuilder getHeaderOrBuilder() {
      if (headerBuilder_ != null) {
        return headerBuilder_.getMessageOrBuilder();
      } else {
        return header_ == null ?
            org.harryng.demo.controller.grpc.common.dto.CommonReq.getDefaultInstance() : header_;
      }
    }
    /**
     * <code>.common.CommonReq header = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilder<
        org.harryng.demo.controller.grpc.common.dto.CommonReq, org.harryng.demo.controller.grpc.common.dto.CommonReq.Builder, org.harryng.demo.controller.grpc.common.dto.CommonReqOrBuilder> 
        getHeaderFieldBuilder() {
      if (headerBuilder_ == null) {
        headerBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            org.harryng.demo.controller.grpc.common.dto.CommonReq, org.harryng.demo.controller.grpc.common.dto.CommonReq.Builder, org.harryng.demo.controller.grpc.common.dto.CommonReqOrBuilder>(
                getHeader(),
                getParentForChildren(),
                isClean());
        header_ = null;
      }
      return headerBuilder_;
    }

    private long id_ ;
    /**
     * <code>int64 id = 2;</code>
     * @return The id.
     */
    @java.lang.Override
    public long getId() {
      return id_;
    }
    /**
     * <code>int64 id = 2;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(long value) {

      id_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>int64 id = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      id_ = 0L;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:controller.AssetIdReq)
  }

  // @@protoc_insertion_point(class_scope:controller.AssetIdReq)
  private static final org.harryng.demo.controller.grpc.asset.AssetIdReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.harryng.demo.controller.grpc.asset.AssetIdReq();
  }

  public static org.harryng.demo.controller.grpc.asset.AssetIdReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AssetIdReq>
      PARSER = new com.google.protobuf.AbstractParser<AssetIdReq>() {
    @java.lang.Override
    public AssetIdReq parsePartialFrom(
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

  public static com.google.protobuf.Parser<AssetIdReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AssetIdReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.harryng.demo.controller.grpc.asset.AssetIdReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

