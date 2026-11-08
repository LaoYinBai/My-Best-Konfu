package com.sigmob.sdk.common.models.ssp.pb;

import android.os.Parcelable;
import com.sigmob.wire.AndroidMessage;
import com.sigmob.wire.FieldEncoding;
import com.sigmob.wire.Message;
import com.sigmob.wire.ProtoAdapter;
import com.sigmob.wire.ProtoReader;
import com.sigmob.wire.ProtoWriter;
import com.sigmob.wire.WireField;
import com.sigmob.wire.internal.Internal;
import com.sigmob.wire.okio.ByteString;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class NativeConfig extends AndroidMessage<NativeConfig, Builder> {
    public static final ProtoAdapter<NativeConfig> ADAPTER = new ProtoAdapter_NativeConfig();
    public static final Parcelable.Creator<NativeConfig> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CACHETOP = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer cacheTop;

    public static final class Builder extends Message.Builder<NativeConfig, Builder> {
        public Integer cacheTop = NativeConfig.DEFAULT_CACHETOP;

        @Override // com.sigmob.wire.Message.Builder
        public NativeConfig build() {
            return new NativeConfig(this.cacheTop, super.buildUnknownFields());
        }

        public Builder cacheTop(Integer num) {
            this.cacheTop = num;
            return this;
        }
    }

    private static final class ProtoAdapter_NativeConfig extends ProtoAdapter<NativeConfig> {
        public ProtoAdapter_NativeConfig() {
            super(FieldEncoding.LENGTH_DELIMITED, NativeConfig.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public NativeConfig decode(ProtoReader protoReader) {
            Builder builder = new Builder();
            long jBeginMessage = protoReader.beginMessage();
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    return builder.build();
                }
                if (iNextTag != 1) {
                    FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                    builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                } else {
                    builder.cacheTop(ProtoAdapter.INT32.decode(protoReader));
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, NativeConfig nativeConfig) {
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, nativeConfig.cacheTop);
            protoWriter.writeBytes(nativeConfig.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(NativeConfig nativeConfig) {
            return ProtoAdapter.INT32.encodedSizeWithTag(1, nativeConfig.cacheTop) + nativeConfig.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public NativeConfig redact(NativeConfig nativeConfig) {
            Builder builderNewBuilder = nativeConfig.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public NativeConfig(Integer num) {
        this(num, ByteString.EMPTY);
    }

    public NativeConfig(Integer num, ByteString byteString) {
        super(ADAPTER, byteString);
        this.cacheTop = num;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NativeConfig)) {
            return false;
        }
        NativeConfig nativeConfig = (NativeConfig) obj;
        return unknownFields().equals(nativeConfig.unknownFields()) && Internal.equals(this.cacheTop, nativeConfig.cacheTop);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (unknownFields().hashCode() * 37) + (this.cacheTop != null ? this.cacheTop.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.cacheTop = this.cacheTop;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.cacheTop != null) {
            sb.append(", cacheTop=");
            sb.append(this.cacheTop);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "NativeConfig{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
