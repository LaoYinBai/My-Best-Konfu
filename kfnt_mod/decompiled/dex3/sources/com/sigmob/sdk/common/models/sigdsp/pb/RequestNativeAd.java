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
public final class RequestNativeAd extends AndroidMessage<RequestNativeAd, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.RequestAsset#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<RequestAsset> assets;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer h;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer w;
    public static final ProtoAdapter<RequestNativeAd> ADAPTER = new ProtoAdapter_RequestNativeAd();
    public static final Parcelable.Creator<RequestNativeAd> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_W = 0;
    public static final Integer DEFAULT_H = 0;

    public static final class Builder extends Message.Builder<RequestNativeAd, Builder> {
        public List<RequestAsset> assets = Internal.newMutableList();
        public Integer h;
        public Integer w;

        public Builder assets(List<RequestAsset> list) {
            Internal.checkElementsNotNull(list);
            this.assets = list;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public RequestNativeAd build() {
            return new RequestNativeAd(this.assets, this.w, this.h, super.buildUnknownFields());
        }

        public Builder h(Integer num) {
            this.h = num;
            return this;
        }

        public Builder w(Integer num) {
            this.w = num;
            return this;
        }
    }

    private static final class ProtoAdapter_RequestNativeAd extends ProtoAdapter<RequestNativeAd> {
        public ProtoAdapter_RequestNativeAd() {
            super(FieldEncoding.LENGTH_DELIMITED, RequestNativeAd.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public RequestNativeAd decode(ProtoReader protoReader) {
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
                        builder.assets.add(RequestAsset.ADAPTER.decode(protoReader));
                        break;
                    case 2:
                        builder.w(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.h(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, RequestNativeAd requestNativeAd) {
            RequestAsset.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, requestNativeAd.assets);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, requestNativeAd.w);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, requestNativeAd.h);
            protoWriter.writeBytes(requestNativeAd.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(RequestNativeAd requestNativeAd) {
            return RequestAsset.ADAPTER.asRepeated().encodedSizeWithTag(1, requestNativeAd.assets) + ProtoAdapter.UINT32.encodedSizeWithTag(2, requestNativeAd.w) + ProtoAdapter.UINT32.encodedSizeWithTag(3, requestNativeAd.h) + requestNativeAd.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public RequestNativeAd redact(RequestNativeAd requestNativeAd) {
            Builder builderNewBuilder = requestNativeAd.newBuilder();
            Internal.redactElements(builderNewBuilder.assets, RequestAsset.ADAPTER);
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public RequestNativeAd(List<RequestAsset> list, Integer num, Integer num2) {
        this(list, num, num2, ByteString.EMPTY);
    }

    public RequestNativeAd(List<RequestAsset> list, Integer num, Integer num2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.assets = Internal.immutableCopyOf("assets", list);
        this.w = num;
        this.h = num2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RequestNativeAd)) {
            return false;
        }
        RequestNativeAd requestNativeAd = (RequestNativeAd) obj;
        return unknownFields().equals(requestNativeAd.unknownFields()) && this.assets.equals(requestNativeAd.assets) && Internal.equals(this.w, requestNativeAd.w) && Internal.equals(this.h, requestNativeAd.h);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + this.assets.hashCode()) * 37) + (this.w != null ? this.w.hashCode() : 0)) * 37) + (this.h != null ? this.h.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.assets = Internal.copyOf("assets", this.assets);
        builder.w = this.w;
        builder.h = this.h;
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
        if (this.w != null) {
            sb.append(", w=");
            sb.append(this.w);
        }
        if (this.h != null) {
            sb.append(", h=");
            sb.append(this.h);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "RequestNativeAd{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
