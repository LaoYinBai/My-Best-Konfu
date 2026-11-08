package com.sigmob.sdk.common.models.ssp.pb;

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
public final class DialogSetting extends AndroidMessage<DialogSetting, Builder> {
    public static final ProtoAdapter<DialogSetting> ADAPTER = new ProtoAdapter_DialogSetting();
    public static final Parcelable.Creator<DialogSetting> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_BODY_TEXT = "";
    public static final String DEFAULT_CANCEL_BUTTON_TEXT = "";
    public static final String DEFAULT_CLOSE_BUTTON_TEXT = "";
    public static final String DEFAULT_TITLE = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String body_text;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String cancel_button_text;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String close_button_text;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String title;

    public static final class Builder extends Message.Builder<DialogSetting, Builder> {
        public String body_text;
        public String cancel_button_text;
        public String close_button_text;
        public String title;

        public Builder body_text(String str) {
            this.body_text = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public DialogSetting build() {
            return new DialogSetting(this.title, this.body_text, this.cancel_button_text, this.close_button_text, super.buildUnknownFields());
        }

        public Builder cancel_button_text(String str) {
            this.cancel_button_text = str;
            return this;
        }

        public Builder close_button_text(String str) {
            this.close_button_text = str;
            return this;
        }

        public Builder title(String str) {
            this.title = str;
            return this;
        }
    }

    private static final class ProtoAdapter_DialogSetting extends ProtoAdapter<DialogSetting> {
        public ProtoAdapter_DialogSetting() {
            super(FieldEncoding.LENGTH_DELIMITED, DialogSetting.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public DialogSetting decode(ProtoReader protoReader) {
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
                        builder.title(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.body_text(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.cancel_button_text(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 4:
                        builder.close_button_text(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, DialogSetting dialogSetting) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, dialogSetting.title);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, dialogSetting.body_text);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, dialogSetting.cancel_button_text);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, dialogSetting.close_button_text);
            protoWriter.writeBytes(dialogSetting.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(DialogSetting dialogSetting) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, dialogSetting.title) + ProtoAdapter.STRING.encodedSizeWithTag(2, dialogSetting.body_text) + ProtoAdapter.STRING.encodedSizeWithTag(3, dialogSetting.cancel_button_text) + ProtoAdapter.STRING.encodedSizeWithTag(4, dialogSetting.close_button_text) + dialogSetting.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public DialogSetting redact(DialogSetting dialogSetting) {
            Builder builderNewBuilder = dialogSetting.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public DialogSetting(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, ByteString.EMPTY);
    }

    public DialogSetting(String str, String str2, String str3, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.title = str;
        this.body_text = str2;
        this.cancel_button_text = str3;
        this.close_button_text = str4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DialogSetting)) {
            return false;
        }
        DialogSetting dialogSetting = (DialogSetting) obj;
        return unknownFields().equals(dialogSetting.unknownFields()) && Internal.equals(this.title, dialogSetting.title) && Internal.equals(this.body_text, dialogSetting.body_text) && Internal.equals(this.cancel_button_text, dialogSetting.cancel_button_text) && Internal.equals(this.close_button_text, dialogSetting.close_button_text);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.title != null ? this.title.hashCode() : 0)) * 37) + (this.body_text != null ? this.body_text.hashCode() : 0)) * 37) + (this.cancel_button_text != null ? this.cancel_button_text.hashCode() : 0)) * 37) + (this.close_button_text != null ? this.close_button_text.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.title = this.title;
        builder.body_text = this.body_text;
        builder.cancel_button_text = this.cancel_button_text;
        builder.close_button_text = this.close_button_text;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.title != null) {
            sb.append(", title=");
            sb.append(this.title);
        }
        if (this.body_text != null) {
            sb.append(", body_text=");
            sb.append(this.body_text);
        }
        if (this.cancel_button_text != null) {
            sb.append(", cancel_button_text=");
            sb.append(this.cancel_button_text);
        }
        if (this.close_button_text != null) {
            sb.append(", close_button_text=");
            sb.append(this.close_button_text);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "DialogSetting{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
