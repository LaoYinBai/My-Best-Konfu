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
public final class RequestAsset extends AndroidMessage<RequestAsset, Builder> {
    public static final ProtoAdapter<RequestAsset> ADAPTER = new ProtoAdapter_RequestAsset();
    public static final Parcelable.Creator<RequestAsset> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_ID = 0;
    public static final Integer DEFAULT_REQUIRED = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer id;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.RequestAssetImage#ADAPTER", tag = 4)
    public final RequestAssetImage image;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer required;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.RequestAssetText#ADAPTER", tag = 5)
    public final RequestAssetText text;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.RequestAssetVideo#ADAPTER", tag = 3)
    public final RequestAssetVideo video;

    public static final class Builder extends Message.Builder<RequestAsset, Builder> {
        public Integer id;
        public RequestAssetImage image;
        public Integer required;
        public RequestAssetText text;
        public RequestAssetVideo video;

        @Override // com.sigmob.wire.Message.Builder
        public RequestAsset build() {
            return new RequestAsset(this.id, this.required, this.video, this.image, this.text, super.buildUnknownFields());
        }

        public Builder id(Integer num) {
            this.id = num;
            return this;
        }

        public Builder image(RequestAssetImage requestAssetImage) {
            this.image = requestAssetImage;
            return this;
        }

        public Builder required(Integer num) {
            this.required = num;
            return this;
        }

        public Builder text(RequestAssetText requestAssetText) {
            this.text = requestAssetText;
            return this;
        }

        public Builder video(RequestAssetVideo requestAssetVideo) {
            this.video = requestAssetVideo;
            return this;
        }
    }

    private static final class ProtoAdapter_RequestAsset extends ProtoAdapter<RequestAsset> {
        public ProtoAdapter_RequestAsset() {
            super(FieldEncoding.LENGTH_DELIMITED, RequestAsset.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public RequestAsset decode(ProtoReader protoReader) {
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
                        builder.id(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.required(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.video(RequestAssetVideo.ADAPTER.decode(protoReader));
                        break;
                    case 4:
                        builder.image(RequestAssetImage.ADAPTER.decode(protoReader));
                        break;
                    case 5:
                        builder.text(RequestAssetText.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, RequestAsset requestAsset) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, requestAsset.id);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, requestAsset.required);
            RequestAssetVideo.ADAPTER.encodeWithTag(protoWriter, 3, requestAsset.video);
            RequestAssetImage.ADAPTER.encodeWithTag(protoWriter, 4, requestAsset.image);
            RequestAssetText.ADAPTER.encodeWithTag(protoWriter, 5, requestAsset.text);
            protoWriter.writeBytes(requestAsset.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(RequestAsset requestAsset) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, requestAsset.id) + ProtoAdapter.UINT32.encodedSizeWithTag(2, requestAsset.required) + RequestAssetVideo.ADAPTER.encodedSizeWithTag(3, requestAsset.video) + RequestAssetImage.ADAPTER.encodedSizeWithTag(4, requestAsset.image) + RequestAssetText.ADAPTER.encodedSizeWithTag(5, requestAsset.text) + requestAsset.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public RequestAsset redact(RequestAsset requestAsset) {
            Builder builderNewBuilder = requestAsset.newBuilder();
            if (builderNewBuilder.video != null) {
                builderNewBuilder.video = RequestAssetVideo.ADAPTER.redact(builderNewBuilder.video);
            }
            if (builderNewBuilder.image != null) {
                builderNewBuilder.image = RequestAssetImage.ADAPTER.redact(builderNewBuilder.image);
            }
            if (builderNewBuilder.text != null) {
                builderNewBuilder.text = RequestAssetText.ADAPTER.redact(builderNewBuilder.text);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public RequestAsset(Integer num, Integer num2, RequestAssetVideo requestAssetVideo, RequestAssetImage requestAssetImage, RequestAssetText requestAssetText) {
        this(num, num2, requestAssetVideo, requestAssetImage, requestAssetText, ByteString.EMPTY);
    }

    public RequestAsset(Integer num, Integer num2, RequestAssetVideo requestAssetVideo, RequestAssetImage requestAssetImage, RequestAssetText requestAssetText, ByteString byteString) {
        super(ADAPTER, byteString);
        this.id = num;
        this.required = num2;
        this.video = requestAssetVideo;
        this.image = requestAssetImage;
        this.text = requestAssetText;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RequestAsset)) {
            return false;
        }
        RequestAsset requestAsset = (RequestAsset) obj;
        return unknownFields().equals(requestAsset.unknownFields()) && Internal.equals(this.id, requestAsset.id) && Internal.equals(this.required, requestAsset.required) && Internal.equals(this.video, requestAsset.video) && Internal.equals(this.image, requestAsset.image) && Internal.equals(this.text, requestAsset.text);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((unknownFields().hashCode() * 37) + (this.id != null ? this.id.hashCode() : 0)) * 37) + (this.required != null ? this.required.hashCode() : 0)) * 37) + (this.video != null ? this.video.hashCode() : 0)) * 37) + (this.image != null ? this.image.hashCode() : 0)) * 37) + (this.text != null ? this.text.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.id = this.id;
        builder.required = this.required;
        builder.video = this.video;
        builder.image = this.image;
        builder.text = this.text;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.id != null) {
            sb.append(", id=");
            sb.append(this.id);
        }
        if (this.required != null) {
            sb.append(", required=");
            sb.append(this.required);
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
        StringBuilder sbReplace = sb.replace(0, 2, "RequestAsset{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
