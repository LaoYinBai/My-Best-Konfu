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
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class IOS extends AndroidMessage<IOS, Builder> {
    public static final ProtoAdapter<IOS> ADAPTER = new ProtoAdapter_IOS();
    public static final Parcelable.Creator<IOS> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Boolean DEFAULT_DISABLE_UP_IP = false;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 1)
    public final Boolean disable_up_ip;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 2)
    public final List<String> ip_names;

    public static final class Builder extends Message.Builder<IOS, Builder> {
        public Boolean disable_up_ip = IOS.DEFAULT_DISABLE_UP_IP;
        public List<String> ip_names = Internal.newMutableList();

        @Override // com.sigmob.wire.Message.Builder
        public IOS build() {
            return new IOS(this.disable_up_ip, this.ip_names, super.buildUnknownFields());
        }

        public Builder disable_up_ip(Boolean bool) {
            this.disable_up_ip = bool;
            return this;
        }

        public Builder ip_names(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.ip_names = list;
            return this;
        }
    }

    private static final class ProtoAdapter_IOS extends ProtoAdapter<IOS> {
        public ProtoAdapter_IOS() {
            super(FieldEncoding.LENGTH_DELIMITED, IOS.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public IOS decode(ProtoReader protoReader) {
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
                        builder.disable_up_ip(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 2:
                        builder.ip_names.add(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, IOS ios) {
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 1, ios.disable_up_ip);
            ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 2, ios.ip_names);
            protoWriter.writeBytes(ios.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(IOS ios) {
            return ProtoAdapter.BOOL.encodedSizeWithTag(1, ios.disable_up_ip) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(2, ios.ip_names) + ios.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public IOS redact(IOS ios) {
            Builder builderNewBuilder = ios.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public IOS(Boolean bool, List<String> list) {
        this(bool, list, ByteString.EMPTY);
    }

    public IOS(Boolean bool, List<String> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.disable_up_ip = bool;
        this.ip_names = Internal.immutableCopyOf("ip_names", list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IOS)) {
            return false;
        }
        IOS ios = (IOS) obj;
        return unknownFields().equals(ios.unknownFields()) && Internal.equals(this.disable_up_ip, ios.disable_up_ip) && this.ip_names.equals(ios.ip_names);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((unknownFields().hashCode() * 37) + (this.disable_up_ip != null ? this.disable_up_ip.hashCode() : 0)) * 37) + this.ip_names.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.disable_up_ip = this.disable_up_ip;
        builder.ip_names = Internal.copyOf("ip_names", this.ip_names);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.disable_up_ip != null) {
            sb.append(", disable_up_ip=");
            sb.append(this.disable_up_ip);
        }
        if (!this.ip_names.isEmpty()) {
            sb.append(", ip_names=");
            sb.append(this.ip_names);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "IOS{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
