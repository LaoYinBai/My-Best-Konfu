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
public final class RequestAssetText extends AndroidMessage<RequestAssetText, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer max_length;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer min_length;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer type;
    public static final ProtoAdapter<RequestAssetText> ADAPTER = new ProtoAdapter_RequestAssetText();
    public static final Parcelable.Creator<RequestAssetText> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_TYPE = 0;
    public static final Integer DEFAULT_MIN_LENGTH = 0;
    public static final Integer DEFAULT_MAX_LENGTH = 0;

    public static final class Builder extends Message.Builder<RequestAssetText, Builder> {
        public Integer max_length;
        public Integer min_length;
        public Integer type;

        @Override // com.sigmob.wire.Message.Builder
        public RequestAssetText build() {
            return new RequestAssetText(this.type, this.min_length, this.max_length, super.buildUnknownFields());
        }

        public Builder max_length(Integer num) {
            this.max_length = num;
            return this;
        }

        public Builder min_length(Integer num) {
            this.min_length = num;
            return this;
        }

        public Builder type(Integer num) {
            this.type = num;
            return this;
        }
    }

    private static final class ProtoAdapter_RequestAssetText extends ProtoAdapter<RequestAssetText> {
        public ProtoAdapter_RequestAssetText() {
            super(FieldEncoding.LENGTH_DELIMITED, RequestAssetText.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public RequestAssetText decode(ProtoReader protoReader) {
            Builder builder = new Builder();
            long jBeginMessage = protoReader.beginMessage();
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    return builder.build();
                }
                switch (iNextTag) {
                    case 1:
                        builder.type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.min_length(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.max_length(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, RequestAssetText requestAssetText) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, requestAssetText.type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, requestAssetText.min_length);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, requestAssetText.max_length);
            protoWriter.writeBytes(requestAssetText.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(RequestAssetText requestAssetText) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, requestAssetText.type) + ProtoAdapter.UINT32.encodedSizeWithTag(2, requestAssetText.min_length) + ProtoAdapter.UINT32.encodedSizeWithTag(3, requestAssetText.max_length) + requestAssetText.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public RequestAssetText redact(RequestAssetText requestAssetText) {
            Builder builderNewBuilder = requestAssetText.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public RequestAssetText(Integer num, Integer num2, Integer num3) {
        this(num, num2, num3, ByteString.EMPTY);
    }

    public RequestAssetText(Integer num, Integer num2, Integer num3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.type = num;
        this.min_length = num2;
        this.max_length = num3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RequestAssetText)) {
            return false;
        }
        RequestAssetText requestAssetText = (RequestAssetText) obj;
        return unknownFields().equals(requestAssetText.unknownFields()) && Internal.equals(this.type, requestAssetText.type) && Internal.equals(this.min_length, requestAssetText.min_length) && Internal.equals(this.max_length, requestAssetText.max_length);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.type != null ? this.type.hashCode() : 0)) * 37) + (this.min_length != null ? this.min_length.hashCode() : 0)) * 37) + (this.max_length != null ? this.max_length.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.type = this.type;
        builder.min_length = this.min_length;
        builder.max_length = this.max_length;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.type != null) {
            sb.append(", type=");
            sb.append(this.type);
        }
        if (this.min_length != null) {
            sb.append(", min_length=");
            sb.append(this.min_length);
        }
        if (this.max_length != null) {
            sb.append(", max_length=");
            sb.append(this.max_length);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "RequestAssetText{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
