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
public final class RequestAssetImage extends AndroidMessage<RequestAssetImage, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 4)
    public final Integer h;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 2)
    public final List<String> mimes;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer w;
    public static final ProtoAdapter<RequestAssetImage> ADAPTER = new ProtoAdapter_RequestAssetImage();
    public static final Parcelable.Creator<RequestAssetImage> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_TYPE = 0;
    public static final Integer DEFAULT_W = 0;
    public static final Integer DEFAULT_H = 0;

    public static final class Builder extends Message.Builder<RequestAssetImage, Builder> {
        public Integer h;
        public List<String> mimes = Internal.newMutableList();
        public Integer type;
        public Integer w;

        @Override // com.sigmob.wire.Message.Builder
        public RequestAssetImage build() {
            return new RequestAssetImage(this.type, this.mimes, this.w, this.h, super.buildUnknownFields());
        }

        public Builder h(Integer num) {
            this.h = num;
            return this;
        }

        public Builder mimes(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.mimes = list;
            return this;
        }

        public Builder type(Integer num) {
            this.type = num;
            return this;
        }

        public Builder w(Integer num) {
            this.w = num;
            return this;
        }
    }

    private static final class ProtoAdapter_RequestAssetImage extends ProtoAdapter<RequestAssetImage> {
        public ProtoAdapter_RequestAssetImage() {
            super(FieldEncoding.LENGTH_DELIMITED, RequestAssetImage.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public RequestAssetImage decode(ProtoReader protoReader) {
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
                        builder.mimes.add(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.w(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 4:
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
        public void encode(ProtoWriter protoWriter, RequestAssetImage requestAssetImage) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, requestAssetImage.type);
            ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 2, requestAssetImage.mimes);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, requestAssetImage.w);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 4, requestAssetImage.h);
            protoWriter.writeBytes(requestAssetImage.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(RequestAssetImage requestAssetImage) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, requestAssetImage.type) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(2, requestAssetImage.mimes) + ProtoAdapter.UINT32.encodedSizeWithTag(3, requestAssetImage.w) + ProtoAdapter.UINT32.encodedSizeWithTag(4, requestAssetImage.h) + requestAssetImage.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public RequestAssetImage redact(RequestAssetImage requestAssetImage) {
            Builder builderNewBuilder = requestAssetImage.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public RequestAssetImage(Integer num, List<String> list, Integer num2, Integer num3) {
        this(num, list, num2, num3, ByteString.EMPTY);
    }

    public RequestAssetImage(Integer num, List<String> list, Integer num2, Integer num3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.type = num;
        this.mimes = Internal.immutableCopyOf("mimes", list);
        this.w = num2;
        this.h = num3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RequestAssetImage)) {
            return false;
        }
        RequestAssetImage requestAssetImage = (RequestAssetImage) obj;
        return unknownFields().equals(requestAssetImage.unknownFields()) && Internal.equals(this.type, requestAssetImage.type) && this.mimes.equals(requestAssetImage.mimes) && Internal.equals(this.w, requestAssetImage.w) && Internal.equals(this.h, requestAssetImage.h);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.type != null ? this.type.hashCode() : 0)) * 37) + this.mimes.hashCode()) * 37) + (this.w != null ? this.w.hashCode() : 0)) * 37) + (this.h != null ? this.h.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.type = this.type;
        builder.mimes = Internal.copyOf("mimes", this.mimes);
        builder.w = this.w;
        builder.h = this.h;
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
        if (!this.mimes.isEmpty()) {
            sb.append(", mimes=");
            sb.append(this.mimes);
        }
        if (this.w != null) {
            sb.append(", w=");
            sb.append(this.w);
        }
        if (this.h != null) {
            sb.append(", h=");
            sb.append(this.h);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "RequestAssetImage{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
