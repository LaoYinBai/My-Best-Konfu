package com.sigmob.sdk.common.models.sigdsp.pb;

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
public final class ResponseAssetText extends AndroidMessage<ResponseAssetText, Builder> {
    public static final ProtoAdapter<ResponseAssetText> ADAPTER = new ProtoAdapter_ResponseAssetText();
    public static final Parcelable.Creator<ResponseAssetText> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_CONTEXT = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String context;

    public static final class Builder extends Message.Builder<ResponseAssetText, Builder> {
        public String context;

        @Override // com.sigmob.wire.Message.Builder
        public ResponseAssetText build() {
            return new ResponseAssetText(this.context, super.buildUnknownFields());
        }

        public Builder context(String str) {
            this.context = str;
            return this;
        }
    }

    private static final class ProtoAdapter_ResponseAssetText extends ProtoAdapter<ResponseAssetText> {
        public ProtoAdapter_ResponseAssetText() {
            super(FieldEncoding.LENGTH_DELIMITED, ResponseAssetText.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public ResponseAssetText decode(ProtoReader protoReader) {
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
                    builder.context(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, ResponseAssetText responseAssetText) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, responseAssetText.context);
            protoWriter.writeBytes(responseAssetText.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(ResponseAssetText responseAssetText) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, responseAssetText.context) + responseAssetText.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public ResponseAssetText redact(ResponseAssetText responseAssetText) {
            Builder builderNewBuilder = responseAssetText.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public ResponseAssetText(String str) {
        this(str, ByteString.EMPTY);
    }

    public ResponseAssetText(String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.context = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResponseAssetText)) {
            return false;
        }
        ResponseAssetText responseAssetText = (ResponseAssetText) obj;
        return unknownFields().equals(responseAssetText.unknownFields()) && Internal.equals(this.context, responseAssetText.context);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (unknownFields().hashCode() * 37) + (this.context != null ? this.context.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.context = this.context;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.context != null) {
            sb.append(", context=");
            sb.append(this.context);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "ResponseAssetText{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
