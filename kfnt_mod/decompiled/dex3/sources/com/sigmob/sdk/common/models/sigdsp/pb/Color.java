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
public final class Color extends AndroidMessage<Color, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
    public final Float alpha;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer blue;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer green;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer red;
    public static final ProtoAdapter<Color> ADAPTER = new ProtoAdapter_Color();
    public static final Parcelable.Creator<Color> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_RED = 0;
    public static final Integer DEFAULT_GREEN = 0;
    public static final Integer DEFAULT_BLUE = 0;
    public static final Float DEFAULT_ALPHA = Float.valueOf(0.0f);

    public static final class Builder extends Message.Builder<Color, Builder> {
        public Float alpha;
        public Integer blue;
        public Integer green;
        public Integer red;

        public Builder alpha(Float f) {
            this.alpha = f;
            return this;
        }

        public Builder blue(Integer num) {
            this.blue = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public Color build() {
            return new Color(this.red, this.green, this.blue, this.alpha, super.buildUnknownFields());
        }

        public Builder green(Integer num) {
            this.green = num;
            return this;
        }

        public Builder red(Integer num) {
            this.red = num;
            return this;
        }
    }

    private static final class ProtoAdapter_Color extends ProtoAdapter<Color> {
        public ProtoAdapter_Color() {
            super(FieldEncoding.LENGTH_DELIMITED, Color.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Color decode(ProtoReader protoReader) {
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
                        builder.red(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.green(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.blue(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 4:
                        builder.alpha(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Color color) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, color.red);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, color.green);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, color.blue);
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, color.alpha);
            protoWriter.writeBytes(color.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Color color) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, color.red) + ProtoAdapter.UINT32.encodedSizeWithTag(2, color.green) + ProtoAdapter.UINT32.encodedSizeWithTag(3, color.blue) + ProtoAdapter.FLOAT.encodedSizeWithTag(4, color.alpha) + color.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Color redact(Color color) {
            Builder builderNewBuilder = color.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Color(Integer num, Integer num2, Integer num3, Float f) {
        this(num, num2, num3, f, ByteString.EMPTY);
    }

    public Color(Integer num, Integer num2, Integer num3, Float f, ByteString byteString) {
        super(ADAPTER, byteString);
        this.red = num;
        this.green = num2;
        this.blue = num3;
        this.alpha = f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Color)) {
            return false;
        }
        Color color = (Color) obj;
        return unknownFields().equals(color.unknownFields()) && Internal.equals(this.red, color.red) && Internal.equals(this.green, color.green) && Internal.equals(this.blue, color.blue) && Internal.equals(this.alpha, color.alpha);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.red != null ? this.red.hashCode() : 0)) * 37) + (this.green != null ? this.green.hashCode() : 0)) * 37) + (this.blue != null ? this.blue.hashCode() : 0)) * 37) + (this.alpha != null ? this.alpha.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.red = this.red;
        builder.green = this.green;
        builder.blue = this.blue;
        builder.alpha = this.alpha;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.red != null) {
            sb.append(", red=");
            sb.append(this.red);
        }
        if (this.green != null) {
            sb.append(", green=");
            sb.append(this.green);
        }
        if (this.blue != null) {
            sb.append(", blue=");
            sb.append(this.blue);
        }
        if (this.alpha != null) {
            sb.append(", alpha=");
            sb.append(this.alpha);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Color{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
