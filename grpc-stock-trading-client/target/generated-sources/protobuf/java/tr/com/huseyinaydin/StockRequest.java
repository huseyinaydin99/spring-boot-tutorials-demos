// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: stock_trading.proto

// Protobuf Java Version: 3.25.6
package tr.com.huseyinaydin;

/**
 * Protobuf type {@code tr.com.huseyinaydin.StockRequest}
 */
public final class StockRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tr.com.huseyinaydin.StockRequest)
    StockRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StockRequest.newBuilder() to construct.
  private StockRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StockRequest() {
    stockSymbol_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new StockRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return tr.com.huseyinaydin.StockTradingProto.internal_static_tr_com_huseyinaydin_StockRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return tr.com.huseyinaydin.StockTradingProto.internal_static_tr_com_huseyinaydin_StockRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            tr.com.huseyinaydin.StockRequest.class, tr.com.huseyinaydin.StockRequest.Builder.class);
  }

  public static final int STOCK_SYMBOL_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object stockSymbol_ = "";
  /**
   * <code>string stock_symbol = 1;</code>
   * @return The stockSymbol.
   */
  @java.lang.Override
  public java.lang.String getStockSymbol() {
    java.lang.Object ref = stockSymbol_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      stockSymbol_ = s;
      return s;
    }
  }
  /**
   * <code>string stock_symbol = 1;</code>
   * @return The bytes for stockSymbol.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getStockSymbolBytes() {
    java.lang.Object ref = stockSymbol_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      stockSymbol_ = b;
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(stockSymbol_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, stockSymbol_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(stockSymbol_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, stockSymbol_);
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
    if (!(obj instanceof tr.com.huseyinaydin.StockRequest)) {
      return super.equals(obj);
    }
    tr.com.huseyinaydin.StockRequest other = (tr.com.huseyinaydin.StockRequest) obj;

    if (!getStockSymbol()
        .equals(other.getStockSymbol())) return false;
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
    hash = (37 * hash) + STOCK_SYMBOL_FIELD_NUMBER;
    hash = (53 * hash) + getStockSymbol().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static tr.com.huseyinaydin.StockRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tr.com.huseyinaydin.StockRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tr.com.huseyinaydin.StockRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tr.com.huseyinaydin.StockRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tr.com.huseyinaydin.StockRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static tr.com.huseyinaydin.StockRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static tr.com.huseyinaydin.StockRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tr.com.huseyinaydin.StockRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static tr.com.huseyinaydin.StockRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static tr.com.huseyinaydin.StockRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static tr.com.huseyinaydin.StockRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static tr.com.huseyinaydin.StockRequest parseFrom(
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
  public static Builder newBuilder(tr.com.huseyinaydin.StockRequest prototype) {
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
   * Protobuf type {@code tr.com.huseyinaydin.StockRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tr.com.huseyinaydin.StockRequest)
      tr.com.huseyinaydin.StockRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return tr.com.huseyinaydin.StockTradingProto.internal_static_tr_com_huseyinaydin_StockRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return tr.com.huseyinaydin.StockTradingProto.internal_static_tr_com_huseyinaydin_StockRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              tr.com.huseyinaydin.StockRequest.class, tr.com.huseyinaydin.StockRequest.Builder.class);
    }

    // Construct using tr.com.huseyinaydin.StockRequest.newBuilder()
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
      stockSymbol_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return tr.com.huseyinaydin.StockTradingProto.internal_static_tr_com_huseyinaydin_StockRequest_descriptor;
    }

    @java.lang.Override
    public tr.com.huseyinaydin.StockRequest getDefaultInstanceForType() {
      return tr.com.huseyinaydin.StockRequest.getDefaultInstance();
    }

    @java.lang.Override
    public tr.com.huseyinaydin.StockRequest build() {
      tr.com.huseyinaydin.StockRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public tr.com.huseyinaydin.StockRequest buildPartial() {
      tr.com.huseyinaydin.StockRequest result = new tr.com.huseyinaydin.StockRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(tr.com.huseyinaydin.StockRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.stockSymbol_ = stockSymbol_;
      }
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
      if (other instanceof tr.com.huseyinaydin.StockRequest) {
        return mergeFrom((tr.com.huseyinaydin.StockRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(tr.com.huseyinaydin.StockRequest other) {
      if (other == tr.com.huseyinaydin.StockRequest.getDefaultInstance()) return this;
      if (!other.getStockSymbol().isEmpty()) {
        stockSymbol_ = other.stockSymbol_;
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
              stockSymbol_ = input.readStringRequireUtf8();
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

    private java.lang.Object stockSymbol_ = "";
    /**
     * <code>string stock_symbol = 1;</code>
     * @return The stockSymbol.
     */
    public java.lang.String getStockSymbol() {
      java.lang.Object ref = stockSymbol_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        stockSymbol_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string stock_symbol = 1;</code>
     * @return The bytes for stockSymbol.
     */
    public com.google.protobuf.ByteString
        getStockSymbolBytes() {
      java.lang.Object ref = stockSymbol_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        stockSymbol_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string stock_symbol = 1;</code>
     * @param value The stockSymbol to set.
     * @return This builder for chaining.
     */
    public Builder setStockSymbol(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      stockSymbol_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string stock_symbol = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearStockSymbol() {
      stockSymbol_ = getDefaultInstance().getStockSymbol();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string stock_symbol = 1;</code>
     * @param value The bytes for stockSymbol to set.
     * @return This builder for chaining.
     */
    public Builder setStockSymbolBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      stockSymbol_ = value;
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


    // @@protoc_insertion_point(builder_scope:tr.com.huseyinaydin.StockRequest)
  }

  // @@protoc_insertion_point(class_scope:tr.com.huseyinaydin.StockRequest)
  private static final tr.com.huseyinaydin.StockRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new tr.com.huseyinaydin.StockRequest();
  }

  public static tr.com.huseyinaydin.StockRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StockRequest>
      PARSER = new com.google.protobuf.AbstractParser<StockRequest>() {
    @java.lang.Override
    public StockRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<StockRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StockRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public tr.com.huseyinaydin.StockRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

