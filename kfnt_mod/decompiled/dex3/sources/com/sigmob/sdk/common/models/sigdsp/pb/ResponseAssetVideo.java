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
public final class ResponseAssetVideo extends AndroidMessage<ResponseAssetVideo, Builder> {
    public static final String DEFAULT_URL = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 4)
    public final Integer duration;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer h;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String url;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer w;
    public static final ProtoAdapter<ResponseAssetVideo> ADAPTER = new ProtoAdapter_ResponseAssetVideo();
    public static final Parcelable.Creator<ResponseAssetVideo> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_W = 0;
    public static final Integer DEFAULT_H = 0;
    public static final Integer DEFAULT_DURATION = 0;

    public static final class Builder extends Message.Builder<ResponseAssetVideo, Builder> {
        public String url = "";
        public Integer w = ResponseAssetVideo.DEFAULT_W;
        public Integer h = ResponseAssetVideo.DEFAULT_H;
        public Integer duration = ResponseAssetVideo.DEFAULT_DURATION;

        @Override // com.sigmob.wire.Message.Builder
        public ResponseAssetVideo build() {
            return new ResponseAssetVideo(this.url, this.w, this.h, this.duration, super.buildUnknownFields());
        }

        public Builder duration(Integer num) {
            this.duration = num;
            return this;
        }

        public Builder h(Integer num) {
            this.h = num;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        public Builder w(Integer num) {
            this.w = num;
            return this;
        }
    }

    private static final class ProtoAdapter_ResponseAssetVideo extends ProtoAdapter<ResponseAssetVideo> {
        public ProtoAdapter_ResponseAssetVideo() {
            super(FieldEncoding.LENGTH_DELIMITED, ResponseAssetVideo.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public ResponseAssetVideo decode(ProtoReader protoReader) {
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
                        builder.url(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.w(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.h(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 4:
                        builder.duration(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, ResponseAssetVideo responseAssetVideo) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, responseAssetVideo.url);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, responseAssetVideo.w);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, responseAssetVideo.h);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 4, responseAssetVideo.duration);
            protoWriter.writeBytes(responseAssetVideo.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(ResponseAssetVideo responseAssetVideo) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, responseAssetVideo.url) + ProtoAdapter.UINT32.encodedSizeWithTag(2, responseAssetVideo.w) + ProtoAdapter.UINT32.encodedSizeWithTag(3, responseAssetVideo.h) + ProtoAdapter.UINT32.encodedSizeWithTag(4, responseAssetVideo.duration) + responseAssetVideo.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public ResponseAssetVideo redact(ResponseAssetVideo responseAssetVideo) {
            Builder builderNewBuilder = responseAssetVideo.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public ResponseAssetVideo(String str, Integer num, Integer num2, Integer num3) {
        this(str, num, num2, num3, ByteString.EMPTY);
    }

    public ResponseAssetVideo(String str, Integer num, Integer num2, Integer num3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.url = str;
        this.w = num;
        this.h = num2;
        this.duration = num3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResponseAssetVideo)) {
            return false;
        }
        ResponseAssetVideo responseAssetVideo = (ResponseAssetVideo) obj;
        return unknownFields().equals(responseAssetVideo.unknownFields()) && Internal.equals(this.url, responseAssetVideo.url) && Internal.equals(this.w, responseAssetVideo.w) && Internal.equals(this.h, responseAssetVideo.h) && Internal.equals(this.duration, responseAssetVideo.duration);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.url != null ? this.url.hashCode() : 0)) * 37) + (this.w != null ? this.w.hashCode() : 0)) * 37) + (this.h != null ? this.h.hashCode() : 0)) * 37) + (this.duration != null ? this.duration.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.url = this.url;
        builder.w = this.w;
        builder.h = this.h;
        builder.duration = this.duration;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        if (this.w != null) {
            sb.append(", w=");
            sb.append(this.w);
        }
        if (this.h != null) {
            sb.append(", h=");
            sb.append(this.h);
        }
        if (this.duration != null) {
            sb.append(", duration=");
            sb.append(this.duration);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "ResponseAssetVideo{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
