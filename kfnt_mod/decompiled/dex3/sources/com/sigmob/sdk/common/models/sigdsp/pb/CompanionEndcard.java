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
public final class CompanionEndcard extends AndroidMessage<CompanionEndcard, Builder> {
    public static final String DEFAULT_BUTTON_TEXT = "";
    public static final String DEFAULT_DESC = "";
    public static final String DEFAULT_ICON_URL = "";
    public static final String DEFAULT_TITLE = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 10)
    public final Integer animate_type;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Color#ADAPTER", tag = 8)
    public final Color bar_color;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Color#ADAPTER", tag = 5)
    public final Color button_color;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String button_text;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Color#ADAPTER", tag = 6)
    public final Color button_text_color;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 7)
    public final Integer click_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String desc;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String icon_url;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
    public final Float score;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 11)
    public final Integer show_delay_secs;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String title;
    public static final ProtoAdapter<CompanionEndcard> ADAPTER = new ProtoAdapter_CompanionEndcard();
    public static final Parcelable.Creator<CompanionEndcard> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Float DEFAULT_SCORE = Float.valueOf(0.0f);
    public static final Integer DEFAULT_CLICK_TYPE = 0;
    public static final Integer DEFAULT_ANIMATE_TYPE = 0;
    public static final Integer DEFAULT_SHOW_DELAY_SECS = 0;

    public static final class Builder extends Message.Builder<CompanionEndcard, Builder> {
        public Color bar_color;
        public Color button_color;
        public Color button_text_color;
        public Integer click_type;
        public String desc;
        public String icon_url = "";
        public String title = "";
        public Float score = CompanionEndcard.DEFAULT_SCORE;
        public String button_text = "";
        public Integer animate_type = CompanionEndcard.DEFAULT_ANIMATE_TYPE;
        public Integer show_delay_secs = CompanionEndcard.DEFAULT_SHOW_DELAY_SECS;

        public Builder animate_type(Integer num) {
            this.animate_type = num;
            return this;
        }

        public Builder bar_color(Color color) {
            this.bar_color = color;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public CompanionEndcard build() {
            return new CompanionEndcard(this.icon_url, this.title, this.score, this.button_text, this.button_color, this.button_text_color, this.click_type, this.bar_color, this.desc, this.animate_type, this.show_delay_secs, super.buildUnknownFields());
        }

        public Builder button_color(Color color) {
            this.button_color = color;
            return this;
        }

        public Builder button_text(String str) {
            this.button_text = str;
            return this;
        }

        public Builder button_text_color(Color color) {
            this.button_text_color = color;
            return this;
        }

        public Builder click_type(Integer num) {
            this.click_type = num;
            return this;
        }

        public Builder desc(String str) {
            this.desc = str;
            return this;
        }

        public Builder icon_url(String str) {
            this.icon_url = str;
            return this;
        }

        public Builder score(Float f) {
            this.score = f;
            return this;
        }

        public Builder show_delay_secs(Integer num) {
            this.show_delay_secs = num;
            return this;
        }

        public Builder title(String str) {
            this.title = str;
            return this;
        }
    }

    private static final class ProtoAdapter_CompanionEndcard extends ProtoAdapter<CompanionEndcard> {
        public ProtoAdapter_CompanionEndcard() {
            super(FieldEncoding.LENGTH_DELIMITED, CompanionEndcard.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public CompanionEndcard decode(ProtoReader protoReader) {
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
                        builder.icon_url(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.title(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.score(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 4:
                        builder.button_text(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.button_color(Color.ADAPTER.decode(protoReader));
                        break;
                    case 6:
                        builder.button_text_color(Color.ADAPTER.decode(protoReader));
                        break;
                    case 7:
                        builder.click_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 8:
                        builder.bar_color(Color.ADAPTER.decode(protoReader));
                        break;
                    case 9:
                        builder.desc(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 10:
                        builder.animate_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 11:
                        builder.show_delay_secs(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, CompanionEndcard companionEndcard) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, companionEndcard.icon_url);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, companionEndcard.title);
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, companionEndcard.score);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, companionEndcard.button_text);
            Color.ADAPTER.encodeWithTag(protoWriter, 5, companionEndcard.button_color);
            Color.ADAPTER.encodeWithTag(protoWriter, 6, companionEndcard.button_text_color);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 7, companionEndcard.click_type);
            Color.ADAPTER.encodeWithTag(protoWriter, 8, companionEndcard.bar_color);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, companionEndcard.desc);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 10, companionEndcard.animate_type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 11, companionEndcard.show_delay_secs);
            protoWriter.writeBytes(companionEndcard.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(CompanionEndcard companionEndcard) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, companionEndcard.icon_url) + ProtoAdapter.STRING.encodedSizeWithTag(2, companionEndcard.title) + ProtoAdapter.FLOAT.encodedSizeWithTag(3, companionEndcard.score) + ProtoAdapter.STRING.encodedSizeWithTag(4, companionEndcard.button_text) + Color.ADAPTER.encodedSizeWithTag(5, companionEndcard.button_color) + Color.ADAPTER.encodedSizeWithTag(6, companionEndcard.button_text_color) + ProtoAdapter.UINT32.encodedSizeWithTag(7, companionEndcard.click_type) + Color.ADAPTER.encodedSizeWithTag(8, companionEndcard.bar_color) + ProtoAdapter.STRING.encodedSizeWithTag(9, companionEndcard.desc) + ProtoAdapter.UINT32.encodedSizeWithTag(10, companionEndcard.animate_type) + ProtoAdapter.UINT32.encodedSizeWithTag(11, companionEndcard.show_delay_secs) + companionEndcard.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public CompanionEndcard redact(CompanionEndcard companionEndcard) {
            Builder builderNewBuilder = companionEndcard.newBuilder();
            if (builderNewBuilder.button_color != null) {
                builderNewBuilder.button_color = Color.ADAPTER.redact(builderNewBuilder.button_color);
            }
            if (builderNewBuilder.button_text_color != null) {
                builderNewBuilder.button_text_color = Color.ADAPTER.redact(builderNewBuilder.button_text_color);
            }
            if (builderNewBuilder.bar_color != null) {
                builderNewBuilder.bar_color = Color.ADAPTER.redact(builderNewBuilder.bar_color);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public CompanionEndcard(String str, String str2, Float f, String str3, Color color, Color color2, Integer num, Color color3, String str4, Integer num2, Integer num3) {
        this(str, str2, f, str3, color, color2, num, color3, str4, num2, num3, ByteString.EMPTY);
    }

    public CompanionEndcard(String str, String str2, Float f, String str3, Color color, Color color2, Integer num, Color color3, String str4, Integer num2, Integer num3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.icon_url = str;
        this.title = str2;
        this.score = f;
        this.button_text = str3;
        this.button_color = color;
        this.button_text_color = color2;
        this.click_type = num;
        this.bar_color = color3;
        this.desc = str4;
        this.animate_type = num2;
        this.show_delay_secs = num3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CompanionEndcard)) {
            return false;
        }
        CompanionEndcard companionEndcard = (CompanionEndcard) obj;
        return unknownFields().equals(companionEndcard.unknownFields()) && Internal.equals(this.icon_url, companionEndcard.icon_url) && Internal.equals(this.title, companionEndcard.title) && Internal.equals(this.score, companionEndcard.score) && Internal.equals(this.button_text, companionEndcard.button_text) && Internal.equals(this.button_color, companionEndcard.button_color) && Internal.equals(this.button_text_color, companionEndcard.button_text_color) && Internal.equals(this.click_type, companionEndcard.click_type) && Internal.equals(this.bar_color, companionEndcard.bar_color) && Internal.equals(this.desc, companionEndcard.desc) && Internal.equals(this.animate_type, companionEndcard.animate_type) && Internal.equals(this.show_delay_secs, companionEndcard.show_delay_secs);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((unknownFields().hashCode() * 37) + (this.icon_url != null ? this.icon_url.hashCode() : 0)) * 37) + (this.title != null ? this.title.hashCode() : 0)) * 37) + (this.score != null ? this.score.hashCode() : 0)) * 37) + (this.button_text != null ? this.button_text.hashCode() : 0)) * 37) + (this.button_color != null ? this.button_color.hashCode() : 0)) * 37) + (this.button_text_color != null ? this.button_text_color.hashCode() : 0)) * 37) + (this.click_type != null ? this.click_type.hashCode() : 0)) * 37) + (this.bar_color != null ? this.bar_color.hashCode() : 0)) * 37) + (this.desc != null ? this.desc.hashCode() : 0)) * 37) + (this.animate_type != null ? this.animate_type.hashCode() : 0)) * 37) + (this.show_delay_secs != null ? this.show_delay_secs.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.icon_url = this.icon_url;
        builder.title = this.title;
        builder.score = this.score;
        builder.button_text = this.button_text;
        builder.button_color = this.button_color;
        builder.button_text_color = this.button_text_color;
        builder.click_type = this.click_type;
        builder.bar_color = this.bar_color;
        builder.desc = this.desc;
        builder.animate_type = this.animate_type;
        builder.show_delay_secs = this.show_delay_secs;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.icon_url != null) {
            sb.append(", icon_url=");
            sb.append(this.icon_url);
        }
        if (this.title != null) {
            sb.append(", title=");
            sb.append(this.title);
        }
        if (this.score != null) {
            sb.append(", score=");
            sb.append(this.score);
        }
        if (this.button_text != null) {
            sb.append(", button_text=");
            sb.append(this.button_text);
        }
        if (this.button_color != null) {
            sb.append(", button_color=");
            sb.append(this.button_color);
        }
        if (this.button_text_color != null) {
            sb.append(", button_text_color=");
            sb.append(this.button_text_color);
        }
        if (this.click_type != null) {
            sb.append(", click_type=");
            sb.append(this.click_type);
        }
        if (this.bar_color != null) {
            sb.append(", bar_color=");
            sb.append(this.bar_color);
        }
        if (this.desc != null) {
            sb.append(", desc=");
            sb.append(this.desc);
        }
        if (this.animate_type != null) {
            sb.append(", animate_type=");
            sb.append(this.animate_type);
        }
        if (this.show_delay_secs != null) {
            sb.append(", show_delay_secs=");
            sb.append(this.show_delay_secs);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "CompanionEndcard{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
