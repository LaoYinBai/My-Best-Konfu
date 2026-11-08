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
public final class WebEvent extends AndroidMessage<WebEvent, Builder> {
    public static final ProtoAdapter<WebEvent> ADAPTER = new ProtoAdapter_WebEvent();
    public static final Parcelable.Creator<WebEvent> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_EVENT_NAME = "";
    public static final String DEFAULT_EVENT_TYPE = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String event_name;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String event_type;

    public static final class Builder extends Message.Builder<WebEvent, Builder> {
        public String event_name;
        public String event_type;

        @Override // com.sigmob.wire.Message.Builder
        public WebEvent build() {
            return new WebEvent(this.event_type, this.event_name, super.buildUnknownFields());
        }

        public Builder event_name(String str) {
            this.event_name = str;
            return this;
        }

        public Builder event_type(String str) {
            this.event_type = str;
            return this;
        }
    }

    private static final class ProtoAdapter_WebEvent extends ProtoAdapter<WebEvent> {
        public ProtoAdapter_WebEvent() {
            super(FieldEncoding.LENGTH_DELIMITED, WebEvent.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public WebEvent decode(ProtoReader protoReader) {
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
                        builder.event_type(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.event_name(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, WebEvent webEvent) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, webEvent.event_type);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, webEvent.event_name);
            protoWriter.writeBytes(webEvent.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(WebEvent webEvent) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, webEvent.event_type) + ProtoAdapter.STRING.encodedSizeWithTag(2, webEvent.event_name) + webEvent.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public WebEvent redact(WebEvent webEvent) {
            Builder builderNewBuilder = webEvent.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public WebEvent(String str, String str2) {
        this(str, str2, ByteString.EMPTY);
    }

    public WebEvent(String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.event_type = str;
        this.event_name = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WebEvent)) {
            return false;
        }
        WebEvent webEvent = (WebEvent) obj;
        return unknownFields().equals(webEvent.unknownFields()) && Internal.equals(this.event_type, webEvent.event_type) && Internal.equals(this.event_name, webEvent.event_name);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((unknownFields().hashCode() * 37) + (this.event_type != null ? this.event_type.hashCode() : 0)) * 37) + (this.event_name != null ? this.event_name.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.event_type = this.event_type;
        builder.event_name = this.event_name;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.event_type != null) {
            sb.append(", event_type=");
            sb.append(this.event_type);
        }
        if (this.event_name != null) {
            sb.append(", event_name=");
            sb.append(this.event_name);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "WebEvent{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
