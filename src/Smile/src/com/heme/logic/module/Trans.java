// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trans.proto
package com.heme.logic.module;

public final class Trans {
  private Trans() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface TransProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required uint64 uint64_uid = 1;
    /**
     * <code>required uint64 uint64_uid = 1;</code>
     *
     * <pre>
     *帐号
     * </pre>
     */
    boolean hasUint64Uid();
    /**
     * <code>required uint64 uint64_uid = 1;</code>
     *
     * <pre>
     *帐号
     * </pre>
     */
    long getUint64Uid();

    // required uint32 uint32_seq = 2;
    /**
     * <code>required uint32 uint32_seq = 2;</code>
     *
     * <pre>
     *序号
     * </pre>
     */
    boolean hasUint32Seq();
    /**
     * <code>required uint32 uint32_seq = 2;</code>
     *
     * <pre>
     *序号
     * </pre>
     */
    int getUint32Seq();

    // required string str_cmd = 3;
    /**
     * <code>required string str_cmd = 3;</code>
     *
     * <pre>
     *命令字
     * </pre>
     */
    boolean hasStrCmd();
    /**
     * <code>required string str_cmd = 3;</code>
     *
     * <pre>
     *命令字
     * </pre>
     */
    java.lang.String getStrCmd();
    /**
     * <code>required string str_cmd = 3;</code>
     *
     * <pre>
     *命令字
     * </pre>
     */
    com.google.protobuf.ByteString
        getStrCmdBytes();

    // required bytes bytes_body = 4;
    /**
     * <code>required bytes bytes_body = 4;</code>
     *
     * <pre>
     *	消息填写 MsgSvr
     *	资料填写 DataSvr
     *	状态填写 StatusSvr
     * </pre>
     */
    boolean hasBytesBody();
    /**
     * <code>required bytes bytes_body = 4;</code>
     *
     * <pre>
     *	消息填写 MsgSvr
     *	资料填写 DataSvr
     *	状态填写 StatusSvr
     * </pre>
     */
    com.google.protobuf.ByteString getBytesBody();
  }
  /**
   * Protobuf type {@code TransProto}
   */
  public static final class TransProto extends
      com.google.protobuf.GeneratedMessage
      implements TransProtoOrBuilder {
    // Use TransProto.newBuilder() to construct.
    private TransProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private TransProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final TransProto defaultInstance;
    public static TransProto getDefaultInstance() {
      return defaultInstance;
    }

    public TransProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private TransProto(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              uint64Uid_ = input.readUInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              uint32Seq_ = input.readUInt32();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              strCmd_ = input.readBytes();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              bytesBody_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Trans.internal_static_TransProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Trans.internal_static_TransProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Trans.TransProto.class, Trans.TransProto.Builder.class);
    }

    public static com.google.protobuf.Parser<TransProto> PARSER =
        new com.google.protobuf.AbstractParser<TransProto>() {
      public TransProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new TransProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<TransProto> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required uint64 uint64_uid = 1;
    public static final int UINT64_UID_FIELD_NUMBER = 1;
    private long uint64Uid_;
    /**
     * <code>required uint64 uint64_uid = 1;</code>
     *
     * <pre>
     *帐号
     * </pre>
     */
    public boolean hasUint64Uid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required uint64 uint64_uid = 1;</code>
     *
     * <pre>
     *帐号
     * </pre>
     */
    public long getUint64Uid() {
      return uint64Uid_;
    }

    // required uint32 uint32_seq = 2;
    public static final int UINT32_SEQ_FIELD_NUMBER = 2;
    private int uint32Seq_;
    /**
     * <code>required uint32 uint32_seq = 2;</code>
     *
     * <pre>
     *序号
     * </pre>
     */
    public boolean hasUint32Seq() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required uint32 uint32_seq = 2;</code>
     *
     * <pre>
     *序号
     * </pre>
     */
    public int getUint32Seq() {
      return uint32Seq_;
    }

    // required string str_cmd = 3;
    public static final int STR_CMD_FIELD_NUMBER = 3;
    private java.lang.Object strCmd_;
    /**
     * <code>required string str_cmd = 3;</code>
     *
     * <pre>
     *命令字
     * </pre>
     */
    public boolean hasStrCmd() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string str_cmd = 3;</code>
     *
     * <pre>
     *命令字
     * </pre>
     */
    public java.lang.String getStrCmd() {
      java.lang.Object ref = strCmd_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          strCmd_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string str_cmd = 3;</code>
     *
     * <pre>
     *命令字
     * </pre>
     */
    public com.google.protobuf.ByteString
        getStrCmdBytes() {
      java.lang.Object ref = strCmd_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        strCmd_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required bytes bytes_body = 4;
    public static final int BYTES_BODY_FIELD_NUMBER = 4;
    private com.google.protobuf.ByteString bytesBody_;
    /**
     * <code>required bytes bytes_body = 4;</code>
     *
     * <pre>
     *	消息填写 MsgSvr
     *	资料填写 DataSvr
     *	状态填写 StatusSvr
     * </pre>
     */
    public boolean hasBytesBody() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required bytes bytes_body = 4;</code>
     *
     * <pre>
     *	消息填写 MsgSvr
     *	资料填写 DataSvr
     *	状态填写 StatusSvr
     * </pre>
     */
    public com.google.protobuf.ByteString getBytesBody() {
      return bytesBody_;
    }

    private void initFields() {
      uint64Uid_ = 0L;
      uint32Seq_ = 0;
      strCmd_ = "";
      bytesBody_ = com.google.protobuf.ByteString.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasUint64Uid()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUint32Seq()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasStrCmd()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasBytesBody()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeUInt64(1, uint64Uid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeUInt32(2, uint32Seq_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getStrCmdBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, bytesBody_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(1, uint64Uid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, uint32Seq_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getStrCmdBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, bytesBody_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static Trans.TransProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Trans.TransProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Trans.TransProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Trans.TransProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Trans.TransProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Trans.TransProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static Trans.TransProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static Trans.TransProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static Trans.TransProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Trans.TransProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(Trans.TransProto prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code TransProto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements Trans.TransProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return Trans.internal_static_TransProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return Trans.internal_static_TransProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                Trans.TransProto.class, Trans.TransProto.Builder.class);
      }

      // Construct using Trans.TransProto.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        uint64Uid_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        uint32Seq_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        strCmd_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        bytesBody_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Trans.internal_static_TransProto_descriptor;
      }

      public Trans.TransProto getDefaultInstanceForType() {
        return Trans.TransProto.getDefaultInstance();
      }

      public Trans.TransProto build() {
        Trans.TransProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public Trans.TransProto buildPartial() {
        Trans.TransProto result = new Trans.TransProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.uint64Uid_ = uint64Uid_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.uint32Seq_ = uint32Seq_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.strCmd_ = strCmd_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.bytesBody_ = bytesBody_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Trans.TransProto) {
          return mergeFrom((Trans.TransProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(Trans.TransProto other) {
        if (other == Trans.TransProto.getDefaultInstance()) return this;
        if (other.hasUint64Uid()) {
          setUint64Uid(other.getUint64Uid());
        }
        if (other.hasUint32Seq()) {
          setUint32Seq(other.getUint32Seq());
        }
        if (other.hasStrCmd()) {
          bitField0_ |= 0x00000004;
          strCmd_ = other.strCmd_;
          onChanged();
        }
        if (other.hasBytesBody()) {
          setBytesBody(other.getBytesBody());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasUint64Uid()) {
          
          return false;
        }
        if (!hasUint32Seq()) {
          
          return false;
        }
        if (!hasStrCmd()) {
          
          return false;
        }
        if (!hasBytesBody()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        Trans.TransProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (Trans.TransProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required uint64 uint64_uid = 1;
      private long uint64Uid_ ;
      /**
       * <code>required uint64 uint64_uid = 1;</code>
       *
       * <pre>
       *帐号
       * </pre>
       */
      public boolean hasUint64Uid() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required uint64 uint64_uid = 1;</code>
       *
       * <pre>
       *帐号
       * </pre>
       */
      public long getUint64Uid() {
        return uint64Uid_;
      }
      /**
       * <code>required uint64 uint64_uid = 1;</code>
       *
       * <pre>
       *帐号
       * </pre>
       */
      public Builder setUint64Uid(long value) {
        bitField0_ |= 0x00000001;
        uint64Uid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required uint64 uint64_uid = 1;</code>
       *
       * <pre>
       *帐号
       * </pre>
       */
      public Builder clearUint64Uid() {
        bitField0_ = (bitField0_ & ~0x00000001);
        uint64Uid_ = 0L;
        onChanged();
        return this;
      }

      // required uint32 uint32_seq = 2;
      private int uint32Seq_ ;
      /**
       * <code>required uint32 uint32_seq = 2;</code>
       *
       * <pre>
       *序号
       * </pre>
       */
      public boolean hasUint32Seq() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required uint32 uint32_seq = 2;</code>
       *
       * <pre>
       *序号
       * </pre>
       */
      public int getUint32Seq() {
        return uint32Seq_;
      }
      /**
       * <code>required uint32 uint32_seq = 2;</code>
       *
       * <pre>
       *序号
       * </pre>
       */
      public Builder setUint32Seq(int value) {
        bitField0_ |= 0x00000002;
        uint32Seq_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required uint32 uint32_seq = 2;</code>
       *
       * <pre>
       *序号
       * </pre>
       */
      public Builder clearUint32Seq() {
        bitField0_ = (bitField0_ & ~0x00000002);
        uint32Seq_ = 0;
        onChanged();
        return this;
      }

      // required string str_cmd = 3;
      private java.lang.Object strCmd_ = "";
      /**
       * <code>required string str_cmd = 3;</code>
       *
       * <pre>
       *命令字
       * </pre>
       */
      public boolean hasStrCmd() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string str_cmd = 3;</code>
       *
       * <pre>
       *命令字
       * </pre>
       */
      public java.lang.String getStrCmd() {
        java.lang.Object ref = strCmd_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          strCmd_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string str_cmd = 3;</code>
       *
       * <pre>
       *命令字
       * </pre>
       */
      public com.google.protobuf.ByteString
          getStrCmdBytes() {
        java.lang.Object ref = strCmd_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          strCmd_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string str_cmd = 3;</code>
       *
       * <pre>
       *命令字
       * </pre>
       */
      public Builder setStrCmd(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        strCmd_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string str_cmd = 3;</code>
       *
       * <pre>
       *命令字
       * </pre>
       */
      public Builder clearStrCmd() {
        bitField0_ = (bitField0_ & ~0x00000004);
        strCmd_ = getDefaultInstance().getStrCmd();
        onChanged();
        return this;
      }
      /**
       * <code>required string str_cmd = 3;</code>
       *
       * <pre>
       *命令字
       * </pre>
       */
      public Builder setStrCmdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        strCmd_ = value;
        onChanged();
        return this;
      }

      // required bytes bytes_body = 4;
      private com.google.protobuf.ByteString bytesBody_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>required bytes bytes_body = 4;</code>
       *
       * <pre>
       *	消息填写 MsgSvr
       *	资料填写 DataSvr
       *	状态填写 StatusSvr
       * </pre>
       */
      public boolean hasBytesBody() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required bytes bytes_body = 4;</code>
       *
       * <pre>
       *	消息填写 MsgSvr
       *	资料填写 DataSvr
       *	状态填写 StatusSvr
       * </pre>
       */
      public com.google.protobuf.ByteString getBytesBody() {
        return bytesBody_;
      }
      /**
       * <code>required bytes bytes_body = 4;</code>
       *
       * <pre>
       *	消息填写 MsgSvr
       *	资料填写 DataSvr
       *	状态填写 StatusSvr
       * </pre>
       */
      public Builder setBytesBody(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        bytesBody_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required bytes bytes_body = 4;</code>
       *
       * <pre>
       *	消息填写 MsgSvr
       *	资料填写 DataSvr
       *	状态填写 StatusSvr
       * </pre>
       */
      public Builder clearBytesBody() {
        bitField0_ = (bitField0_ & ~0x00000008);
        bytesBody_ = getDefaultInstance().getBytesBody();
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:TransProto)
    }

    static {
      defaultInstance = new TransProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:TransProto)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_TransProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_TransProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013trans.proto\"Y\n\nTransProto\022\022\n\nuint64_ui" +
      "d\030\001 \002(\004\022\022\n\nuint32_seq\030\002 \002(\r\022\017\n\007str_cmd\030\003" +
      " \002(\t\022\022\n\nbytes_body\030\004 \002(\014"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_TransProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_TransProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_TransProto_descriptor,
              new java.lang.String[] { "Uint64Uid", "Uint32Seq", "StrCmd", "BytesBody", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
