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
public final class WebEventHandle extends AndroidMessage<WebEventHandle, Builder> {
    public static final ProtoAdapter<WebEventHandle> ADAPTER = new ProtoAdapter_WebEventHandle();
    public static final Parcelable.Creator<WebEventHandle> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_HANDLE_TYPE = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.WebEvent#ADAPTER", label = WireField.Label.REPEATED, tag = 3)
    public final List<WebEvent> events;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 2)
    public final List<String> handle_name;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer handle_type;

    public static final class Builder extends Message.Builder<WebEventHandle, Builder> {
        public Integer handle_type;
        public List<String> handle_name = Internal.newMutableList();
        public List<WebEvent> events = Internal.newMutableList();

        @Override // com.sigmob.wire.Message.Builder
        public WebEventHandle build() {
            return new WebEventHandle(this.handle_type, this.handle_name, this.events, super.buildUnknownFields());
        }

        public Builder events(List<WebEvent> list) {
            Internal.checkElementsNotNull(list);
            this.events = list;
            return this;
        }

        public Builder handle_name(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.handle_name = list;
            return this;
        }

        public Builder handle_type(Integer num) {
            this.handle_type = num;
            return this;
        }
    }

    private static final class ProtoAdapter_WebEventHandle extends ProtoAdapter<WebEventHandle> {
        public ProtoAdapter_WebEventHandle() {
            super(FieldEncoding.LENGTH_DELIMITED, WebEventHandle.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public WebEventHandle decode(ProtoReader protoReader) {
            List list;
            ProtoAdapter protoAdapter;
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
                        builder.handle_type(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 2:
                        list = builder.handle_name;
                        protoAdapter = ProtoAdapter.STRING;
                        break;
                    case 3:
                        list = builder.events;
                        protoAdapter = WebEvent.ADAPTER;
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        continue;
                }
                list.add(protoAdapter.decode(protoReader));
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, WebEventHandle webEventHandle) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, webEventHandle.handle_type);
            ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 2, webEventHandle.handle_name);
            WebEvent.ADAPTER.asRepeated().encodeWithTag(protoWriter, 3, webEventHandle.events);
            protoWriter.writeBytes(webEventHandle.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(WebEventHandle webEventHandle) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, webEventHandle.handle_type) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(2, webEventHandle.handle_name) + WebEvent.ADAPTER.asRepeated().encodedSizeWithTag(3, webEventHandle.events) + webEventHandle.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public WebEventHandle redact(WebEventHandle webEventHandle) {
            Builder builderNewBuilder = webEventHandle.newBuilder();
            Internal.redactElements(builderNewBuilder.events, WebEvent.ADAPTER);
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public WebEventHandle(Integer num, List<String> list, List<WebEvent> list2) {
        this(num, list, list2, ByteString.EMPTY);
    }

    public WebEventHandle(Integer num, List<String> list, List<WebEvent> list2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.handle_type = num;
        this.handle_name = Internal.immutableCopyOf("handle_name", list);
        this.events = Internal.immutableCopyOf("events", list2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WebEventHandle)) {
            return false;
        }
        WebEventHandle webEventHandle = (WebEventHandle) obj;
        return unknownFields().equals(webEventHandle.unknownFields()) && Internal.equals(this.handle_type, webEventHandle.handle_type) && this.handle_name.equals(webEventHandle.handle_name) && this.events.equals(webEventHandle.events);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.handle_type != null ? this.handle_type.hashCode() : 0)) * 37) + this.handle_name.hashCode()) * 37) + this.events.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.handle_type = this.handle_type;
        builder.handle_name = Internal.copyOf("handle_name", this.handle_name);
        builder.events = Internal.copyOf("events", this.events);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.handle_type != null) {
            sb.append(", handle_type=");
            sb.append(this.handle_type);
        }
        if (!this.handle_name.isEmpty()) {
            sb.append(", handle_name=");
            sb.append(this.handle_name);
        }
        if (!this.events.isEmpty()) {
            sb.append(", events=");
            sb.append(this.events);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "WebEventHandle{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
