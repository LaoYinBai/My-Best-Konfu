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
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Tracking extends AndroidMessage<Tracking, Builder> {
    public static final ProtoAdapter<Tracking> ADAPTER = new ProtoAdapter_Tracking();
    public static final Parcelable.Creator<Tracking> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_TRACKING_EVENT_TYPE = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String tracking_event_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 2)
    public final List<String> tracking_url;

    public static final class Builder extends Message.Builder<Tracking, Builder> {
        public String tracking_event_type = "";
        public List<String> tracking_url;

        public Builder() {
            this.tracking_url = new ArrayList();
            this.tracking_url = Internal.newMutableList();
        }

        @Override // com.sigmob.wire.Message.Builder
        public Tracking build() {
            return new Tracking(this.tracking_event_type, this.tracking_url, super.buildUnknownFields());
        }

        public Builder tracking_event_type(String str) {
            this.tracking_event_type = str;
            return this;
        }

        public Builder tracking_url(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.tracking_url = list;
            return this;
        }
    }

    private static final class ProtoAdapter_Tracking extends ProtoAdapter<Tracking> {
        public ProtoAdapter_Tracking() {
            super(FieldEncoding.LENGTH_DELIMITED, Tracking.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Tracking decode(ProtoReader protoReader) {
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
                        builder.tracking_event_type(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.tracking_url.add(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Tracking tracking) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, tracking.tracking_event_type);
            ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 2, tracking.tracking_url);
            protoWriter.writeBytes(tracking.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Tracking tracking) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, tracking.tracking_event_type) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(2, tracking.tracking_url) + tracking.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Tracking redact(Tracking tracking) {
            Builder builderNewBuilder = tracking.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Tracking(String str, List<String> list) {
        this(str, list, ByteString.EMPTY);
    }

    public Tracking(String str, List<String> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.tracking_event_type = str;
        this.tracking_url = Internal.immutableCopyOf("tracking_url", list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tracking)) {
            return false;
        }
        Tracking tracking = (Tracking) obj;
        return unknownFields().equals(tracking.unknownFields()) && Internal.equals(this.tracking_event_type, tracking.tracking_event_type) && this.tracking_url.equals(tracking.tracking_url);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((unknownFields().hashCode() * 37) + (this.tracking_event_type != null ? this.tracking_event_type.hashCode() : 0)) * 37) + this.tracking_url.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.tracking_event_type = this.tracking_event_type;
        builder.tracking_url = Internal.copyOf("tracking_url", this.tracking_url);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.tracking_event_type != null) {
            sb.append(", tracking_event_type=");
            sb.append(this.tracking_event_type);
        }
        if (!this.tracking_url.isEmpty()) {
            sb.append(", tracking_url=");
            sb.append(this.tracking_url);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Tracking{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
