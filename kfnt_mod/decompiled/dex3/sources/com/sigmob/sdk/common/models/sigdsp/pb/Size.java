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
public final class Size extends AndroidMessage<Size, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer height;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer width;
    public static final ProtoAdapter<Size> ADAPTER = new ProtoAdapter_Size();
    public static final Parcelable.Creator<Size> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_WIDTH = 0;
    public static final Integer DEFAULT_HEIGHT = 0;

    public static final class Builder extends Message.Builder<Size, Builder> {
        public Integer width = Size.DEFAULT_WIDTH;
        public Integer height = Size.DEFAULT_HEIGHT;

        @Override // com.sigmob.wire.Message.Builder
        public Size build() {
            return new Size(this.width, this.height, super.buildUnknownFields());
        }

        public Builder height(Integer num) {
            this.height = num;
            return this;
        }

        public Builder width(Integer num) {
            this.width = num;
            return this;
        }
    }

    private static final class ProtoAdapter_Size extends ProtoAdapter<Size> {
        public ProtoAdapter_Size() {
            super(FieldEncoding.LENGTH_DELIMITED, Size.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Size decode(ProtoReader protoReader) {
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
                        builder.width(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.height(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Size size) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, size.width);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, size.height);
            protoWriter.writeBytes(size.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Size size) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, size.width) + ProtoAdapter.UINT32.encodedSizeWithTag(2, size.height) + size.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Size redact(Size size) {
            Builder builderNewBuilder = size.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Size(Integer num, Integer num2) {
        this(num, num2, ByteString.EMPTY);
    }

    public Size(Integer num, Integer num2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.width = num;
        this.height = num2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return unknownFields().equals(size.unknownFields()) && Internal.equals(this.width, size.width) && Internal.equals(this.height, size.height);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((unknownFields().hashCode() * 37) + (this.width != null ? this.width.hashCode() : 0)) * 37) + (this.height != null ? this.height.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.width = this.width;
        builder.height = this.height;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.width != null) {
            sb.append(", width=");
            sb.append(this.width);
        }
        if (this.height != null) {
            sb.append(", height=");
            sb.append(this.height);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Size{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
