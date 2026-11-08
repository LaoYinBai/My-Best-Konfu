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
public final class ResponseAsset extends AndroidMessage<ResponseAsset, Builder> {
    public static final ProtoAdapter<ResponseAsset> ADAPTER = new ProtoAdapter_ResponseAsset();
    public static final Parcelable.Creator<ResponseAsset> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_INDEX = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.ResponseAssetImage#ADAPTER", tag = 3)
    public final ResponseAssetImage image;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer index;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.ResponseAssetText#ADAPTER", tag = 4)
    public final ResponseAssetText text;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.ResponseAssetVideo#ADAPTER", tag = 2)
    public final ResponseAssetVideo video;

    public static final class Builder extends Message.Builder<ResponseAsset, Builder> {
        public ResponseAssetImage image;
        public Integer index;
        public ResponseAssetText text;
        public ResponseAssetVideo video;

        @Override // com.sigmob.wire.Message.Builder
        public ResponseAsset build() {
            return new ResponseAsset(this.index, this.video, this.image, this.text, super.buildUnknownFields());
        }

        public Builder image(ResponseAssetImage responseAssetImage) {
            this.image = responseAssetImage;
            return this;
        }

        public Builder index(Integer num) {
            this.index = num;
            return this;
        }

        public Builder text(ResponseAssetText responseAssetText) {
            this.text = responseAssetText;
            return this;
        }

        public Builder video(ResponseAssetVideo responseAssetVideo) {
            this.video = responseAssetVideo;
            return this;
        }
    }

    private static final class ProtoAdapter_ResponseAsset extends ProtoAdapter<ResponseAsset> {
        public ProtoAdapter_ResponseAsset() {
            super(FieldEncoding.LENGTH_DELIMITED, ResponseAsset.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public ResponseAsset decode(ProtoReader protoReader) {
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
                        builder.index(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.video(ResponseAssetVideo.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        builder.image(ResponseAssetImage.ADAPTER.decode(protoReader));
                        break;
                    case 4:
                        builder.text(ResponseAssetText.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, ResponseAsset responseAsset) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, responseAsset.index);
            ResponseAssetVideo.ADAPTER.encodeWithTag(protoWriter, 2, responseAsset.video);
            ResponseAssetImage.ADAPTER.encodeWithTag(protoWriter, 3, responseAsset.image);
            ResponseAssetText.ADAPTER.encodeWithTag(protoWriter, 4, responseAsset.text);
            protoWriter.writeBytes(responseAsset.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(ResponseAsset responseAsset) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, responseAsset.index) + ResponseAssetVideo.ADAPTER.encodedSizeWithTag(2, responseAsset.video) + ResponseAssetImage.ADAPTER.encodedSizeWithTag(3, responseAsset.image) + ResponseAssetText.ADAPTER.encodedSizeWithTag(4, responseAsset.text) + responseAsset.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public ResponseAsset redact(ResponseAsset responseAsset) {
            Builder builderNewBuilder = responseAsset.newBuilder();
            if (builderNewBuilder.video != null) {
                builderNewBuilder.video = ResponseAssetVideo.ADAPTER.redact(builderNewBuilder.video);
            }
            if (builderNewBuilder.image != null) {
                builderNewBuilder.image = ResponseAssetImage.ADAPTER.redact(builderNewBuilder.image);
            }
            if (builderNewBuilder.text != null) {
                builderNewBuilder.text = ResponseAssetText.ADAPTER.redact(builderNewBuilder.text);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public ResponseAsset(Integer num, ResponseAssetVideo responseAssetVideo, ResponseAssetImage responseAssetImage, ResponseAssetText responseAssetText) {
        this(num, responseAssetVideo, responseAssetImage, responseAssetText, ByteString.EMPTY);
    }

    public ResponseAsset(Integer num, ResponseAssetVideo responseAssetVideo, ResponseAssetImage responseAssetImage, ResponseAssetText responseAssetText, ByteString byteString) {
        super(ADAPTER, byteString);
        this.index = num;
        this.video = responseAssetVideo;
        this.image = responseAssetImage;
        this.text = responseAssetText;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResponseAsset)) {
            return false;
        }
        ResponseAsset responseAsset = (ResponseAsset) obj;
        return unknownFields().equals(responseAsset.unknownFields()) && Internal.equals(this.index, responseAsset.index) && Internal.equals(this.video, responseAsset.video) && Internal.equals(this.image, responseAsset.image) && Internal.equals(this.text, responseAsset.text);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.index != null ? this.index.hashCode() : 0)) * 37) + (this.video != null ? this.video.hashCode() : 0)) * 37) + (this.image != null ? this.image.hashCode() : 0)) * 37) + (this.text != null ? this.text.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.index = this.index;
        builder.video = this.video;
        builder.image = this.image;
        builder.text = this.text;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.index != null) {
            sb.append(", index=");
            sb.append(this.index);
        }
        if (this.video != null) {
            sb.append(", video=");
            sb.append(this.video);
        }
        if (this.image != null) {
            sb.append(", image=");
            sb.append(this.image);
        }
        if (this.text != null) {
            sb.append(", text=");
            sb.append(this.text);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "ResponseAsset{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
