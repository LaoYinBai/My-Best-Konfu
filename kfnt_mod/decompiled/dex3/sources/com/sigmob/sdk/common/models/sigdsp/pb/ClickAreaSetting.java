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
public final class ClickAreaSetting extends AndroidMessage<ClickAreaSetting, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
    public final Float bottom;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
    public final Float left;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
    public final Float right;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
    public final Float top;
    public static final ProtoAdapter<ClickAreaSetting> ADAPTER = new ProtoAdapter_ClickAreaSetting();
    public static final Parcelable.Creator<ClickAreaSetting> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Float DEFAULT_TOP = Float.valueOf(0.0f);
    public static final Float DEFAULT_LEFT = Float.valueOf(0.0f);
    public static final Float DEFAULT_BOTTOM = Float.valueOf(0.0f);
    public static final Float DEFAULT_RIGHT = Float.valueOf(0.0f);

    public static final class Builder extends Message.Builder<ClickAreaSetting, Builder> {
        public Float top = ClickAreaSetting.DEFAULT_TOP;
        public Float left = ClickAreaSetting.DEFAULT_LEFT;
        public Float bottom = ClickAreaSetting.DEFAULT_BOTTOM;
        public Float right = ClickAreaSetting.DEFAULT_RIGHT;

        public Builder bottom(Float f) {
            this.bottom = f;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public ClickAreaSetting build() {
            return new ClickAreaSetting(this.top, this.left, this.bottom, this.right, super.buildUnknownFields());
        }

        public Builder left(Float f) {
            this.left = f;
            return this;
        }

        public Builder right(Float f) {
            this.right = f;
            return this;
        }

        public Builder top(Float f) {
            this.top = f;
            return this;
        }
    }

    private static final class ProtoAdapter_ClickAreaSetting extends ProtoAdapter<ClickAreaSetting> {
        public ProtoAdapter_ClickAreaSetting() {
            super(FieldEncoding.LENGTH_DELIMITED, ClickAreaSetting.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public ClickAreaSetting decode(ProtoReader protoReader) {
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
                        builder.top(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 2:
                        builder.left(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 3:
                        builder.bottom(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 4:
                        builder.right(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, ClickAreaSetting clickAreaSetting) {
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, clickAreaSetting.top);
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, clickAreaSetting.left);
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, clickAreaSetting.bottom);
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, clickAreaSetting.right);
            protoWriter.writeBytes(clickAreaSetting.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(ClickAreaSetting clickAreaSetting) {
            return ProtoAdapter.FLOAT.encodedSizeWithTag(1, clickAreaSetting.top) + ProtoAdapter.FLOAT.encodedSizeWithTag(2, clickAreaSetting.left) + ProtoAdapter.FLOAT.encodedSizeWithTag(3, clickAreaSetting.bottom) + ProtoAdapter.FLOAT.encodedSizeWithTag(4, clickAreaSetting.right) + clickAreaSetting.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public ClickAreaSetting redact(ClickAreaSetting clickAreaSetting) {
            Builder builderNewBuilder = clickAreaSetting.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public ClickAreaSetting(Float f, Float f2, Float f3, Float f4) {
        this(f, f2, f3, f4, ByteString.EMPTY);
    }

    public ClickAreaSetting(Float f, Float f2, Float f3, Float f4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.top = f;
        this.left = f2;
        this.bottom = f3;
        this.right = f4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClickAreaSetting)) {
            return false;
        }
        ClickAreaSetting clickAreaSetting = (ClickAreaSetting) obj;
        return unknownFields().equals(clickAreaSetting.unknownFields()) && Internal.equals(this.top, clickAreaSetting.top) && Internal.equals(this.left, clickAreaSetting.left) && Internal.equals(this.bottom, clickAreaSetting.bottom) && Internal.equals(this.right, clickAreaSetting.right);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.top != null ? this.top.hashCode() : 0)) * 37) + (this.left != null ? this.left.hashCode() : 0)) * 37) + (this.bottom != null ? this.bottom.hashCode() : 0)) * 37) + (this.right != null ? this.right.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.top = this.top;
        builder.left = this.left;
        builder.bottom = this.bottom;
        builder.right = this.right;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.top != null) {
            sb.append(", top=");
            sb.append(this.top);
        }
        if (this.left != null) {
            sb.append(", left=");
            sb.append(this.left);
        }
        if (this.bottom != null) {
            sb.append(", bottom=");
            sb.append(this.bottom);
        }
        if (this.right != null) {
            sb.append(", right=");
            sb.append(this.right);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "ClickAreaSetting{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
