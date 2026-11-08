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
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ResponseNativeAd extends AndroidMessage<ResponseNativeAd, Builder> {
    public static final ProtoAdapter<ResponseNativeAd> ADAPTER = new ProtoAdapter_ResponseNativeAd();
    public static final Parcelable.Creator<ResponseNativeAd> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_TYPE = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.ResponseAsset#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<ResponseAsset> assets;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer type;

    public static final class Builder extends Message.Builder<ResponseNativeAd, Builder> {
        public Integer type = ResponseNativeAd.DEFAULT_TYPE;
        public List<ResponseAsset> assets = Internal.newMutableList();

        public Builder assets(List<ResponseAsset> list) {
            Internal.checkElementsNotNull(list);
            this.assets = list;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public ResponseNativeAd build() {
            return new ResponseNativeAd(this.assets, this.type, super.buildUnknownFields());
        }

        public Builder type(Integer num) {
            this.type = num;
            return this;
        }
    }

    private static final class ProtoAdapter_ResponseNativeAd extends ProtoAdapter<ResponseNativeAd> {
        public ProtoAdapter_ResponseNativeAd() {
            super(FieldEncoding.LENGTH_DELIMITED, ResponseNativeAd.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public ResponseNativeAd decode(ProtoReader protoReader) {
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
                        builder.assets.add(ResponseAsset.ADAPTER.decode(protoReader));
                        break;
                    case 2:
                        builder.type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, ResponseNativeAd responseNativeAd) {
            ResponseAsset.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, responseNativeAd.assets);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, responseNativeAd.type);
            protoWriter.writeBytes(responseNativeAd.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(ResponseNativeAd responseNativeAd) {
            return ResponseAsset.ADAPTER.asRepeated().encodedSizeWithTag(1, responseNativeAd.assets) + ProtoAdapter.UINT32.encodedSizeWithTag(2, responseNativeAd.type) + responseNativeAd.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public ResponseNativeAd redact(ResponseNativeAd responseNativeAd) {
            Builder builderNewBuilder = responseNativeAd.newBuilder();
            Internal.redactElements(builderNewBuilder.assets, ResponseAsset.ADAPTER);
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public ResponseNativeAd(List<ResponseAsset> list, Integer num) {
        this(list, num, ByteString.EMPTY);
    }

    public ResponseNativeAd(List<ResponseAsset> list, Integer num, ByteString byteString) {
        super(ADAPTER, byteString);
        this.assets = Internal.immutableCopyOf("assets", list);
        this.type = num;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResponseNativeAd)) {
            return false;
        }
        ResponseNativeAd responseNativeAd = (ResponseNativeAd) obj;
        return unknownFields().equals(responseNativeAd.unknownFields()) && this.assets.equals(responseNativeAd.assets) && Internal.equals(this.type, responseNativeAd.type);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((unknownFields().hashCode() * 37) + this.assets.hashCode()) * 37) + (this.type != null ? this.type.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.assets = Internal.copyOf("assets", this.assets);
        builder.type = this.type;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.assets.isEmpty()) {
            sb.append(", assets=");
            sb.append(this.assets);
        }
        if (this.type != null) {
            sb.append(", type=");
            sb.append(this.type);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "ResponseNativeAd{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
