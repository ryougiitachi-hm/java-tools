// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/protobuf/IqiyiDanmuku.proto

package per.itachi.java.tools.danmuku.infra.protobuf.iqiyi;

/**
 * Protobuf type {@code per.itachi.java.tools.danmuku.BulletInfo}
 */
public final class BulletInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:per.itachi.java.tools.danmuku.BulletInfo)
    BulletInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use BulletInfo.newBuilder() to construct.
  private BulletInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BulletInfo() {
    content_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new BulletInfo();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.IqiyiDanmuku.internal_static_per_itachi_java_tools_danmuku_BulletInfo_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.IqiyiDanmuku.internal_static_per_itachi_java_tools_danmuku_BulletInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo.class, per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo.Builder.class);
  }

  private int bitField0_;
  public static final int CONTENT_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object content_ = "";
  /**
   * <pre>
   * becomes awesomeField
   * </pre>
   *
   * <code>optional string content = 2;</code>
   * @return Whether the content field is set.
   */
  @java.lang.Override
  public boolean hasContent() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <pre>
   * becomes awesomeField
   * </pre>
   *
   * <code>optional string content = 2;</code>
   * @return The content.
   */
  @java.lang.Override
  public java.lang.String getContent() {
    java.lang.Object ref = content_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        content_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * becomes awesomeField
   * </pre>
   *
   * <code>optional string content = 2;</code>
   * @return The bytes for content.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getContentBytes() {
    java.lang.Object ref = content_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      content_ = b;
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
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, content_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, content_);
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
    if (!(obj instanceof per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo)) {
      return super.equals(obj);
    }
    per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo other = (per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo) obj;

    if (hasContent() != other.hasContent()) return false;
    if (hasContent()) {
      if (!getContent()
          .equals(other.getContent())) return false;
    }
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
    if (hasContent()) {
      hash = (37 * hash) + CONTENT_FIELD_NUMBER;
      hash = (53 * hash) + getContent().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo parseFrom(
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
  public static Builder newBuilder(per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo prototype) {
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
   * Protobuf type {@code per.itachi.java.tools.danmuku.BulletInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:per.itachi.java.tools.danmuku.BulletInfo)
      per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.IqiyiDanmuku.internal_static_per_itachi_java_tools_danmuku_BulletInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.IqiyiDanmuku.internal_static_per_itachi_java_tools_danmuku_BulletInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo.class, per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo.Builder.class);
    }

    // Construct using per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      content_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.IqiyiDanmuku.internal_static_per_itachi_java_tools_danmuku_BulletInfo_descriptor;
    }

    @java.lang.Override
    public per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo getDefaultInstanceForType() {
      return per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo.getDefaultInstance();
    }

    @java.lang.Override
    public per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo build() {
      per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo buildPartial() {
      per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo result = new per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.content_ = content_;
        to_bitField0_ |= 0x00000001;
      }
      result.bitField0_ |= to_bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo) {
        return mergeFrom((per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo other) {
      if (other == per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo.getDefaultInstance()) return this;
      if (other.hasContent()) {
        content_ = other.content_;
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
            case 18: {
              content_ = input.readBytes();
              bitField0_ |= 0x00000001;
              break;
            } // case 18
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

    private java.lang.Object content_ = "";
    /**
     * <pre>
     * becomes awesomeField
     * </pre>
     *
     * <code>optional string content = 2;</code>
     * @return Whether the content field is set.
     */
    public boolean hasContent() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <pre>
     * becomes awesomeField
     * </pre>
     *
     * <code>optional string content = 2;</code>
     * @return The content.
     */
    public java.lang.String getContent() {
      java.lang.Object ref = content_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          content_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * becomes awesomeField
     * </pre>
     *
     * <code>optional string content = 2;</code>
     * @return The bytes for content.
     */
    public com.google.protobuf.ByteString
        getContentBytes() {
      java.lang.Object ref = content_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        content_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * becomes awesomeField
     * </pre>
     *
     * <code>optional string content = 2;</code>
     * @param value The content to set.
     * @return This builder for chaining.
     */
    public Builder setContent(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      content_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * becomes awesomeField
     * </pre>
     *
     * <code>optional string content = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearContent() {
      content_ = getDefaultInstance().getContent();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * becomes awesomeField
     * </pre>
     *
     * <code>optional string content = 2;</code>
     * @param value The bytes for content to set.
     * @return This builder for chaining.
     */
    public Builder setContentBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      content_ = value;
      bitField0_ |= 0x00000001;
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


    // @@protoc_insertion_point(builder_scope:per.itachi.java.tools.danmuku.BulletInfo)
  }

  // @@protoc_insertion_point(class_scope:per.itachi.java.tools.danmuku.BulletInfo)
  private static final per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo();
  }

  public static per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BulletInfo>
      PARSER = new com.google.protobuf.AbstractParser<BulletInfo>() {
    @java.lang.Override
    public BulletInfo parsePartialFrom(
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

  public static com.google.protobuf.Parser<BulletInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BulletInfo> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public per.itachi.java.tools.danmuku.infra.protobuf.iqiyi.BulletInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

