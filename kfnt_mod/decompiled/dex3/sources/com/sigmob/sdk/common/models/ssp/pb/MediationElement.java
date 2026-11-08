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
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class MediationElement extends AndroidMessage<MediationElement, Builder> {
    public static final ProtoAdapter<MediationElement> ADAPTER = new ProtoAdapter_MediationElement();
    public static final Parcelable.Creator<MediationElement> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_ELEMENT_ID = 0;
    public static final Integer DEFAULT_MEDIATION_APP_ID = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer element_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer mediation_app_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final Map<String, String> options;

    public static final class Builder extends Message.Builder<MediationElement, Builder> {
        public Integer element_id = MediationElement.DEFAULT_ELEMENT_ID;
        public Integer mediation_app_id = MediationElement.DEFAULT_MEDIATION_APP_ID;
        public Map<String, String> options = Internal.newMutableMap();

        @Override // com.sigmob.wire.Message.Builder
        public MediationElement build() {
            return new MediationElement(this.element_id, this.mediation_app_id, this.options, super.buildUnknownFields());
        }

        public Builder element_id(Integer num) {
            this.element_id = num;
            return this;
        }

        public Builder mediation_app_id(Integer num) {
            this.mediation_app_id = num;
            return this;
        }

        public Builder options(Map<String, String> map) {
            Internal.checkElementsNotNull(map);
            this.options = map;
            return this;
        }
    }

    private static final class ProtoAdapter_MediationElement extends ProtoAdapter<MediationElement> {
        private final ProtoAdapter<Map<String, String>> a;

        public ProtoAdapter_MediationElement() {
            super(FieldEncoding.LENGTH_DELIMITED, MediationElement.class);
            this.a = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.STRING);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public MediationElement decode(ProtoReader protoReader) {
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
                        builder.element_id(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.mediation_app_id(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.options.putAll(this.a.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, MediationElement mediationElement) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, mediationElement.element_id);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, mediationElement.mediation_app_id);
            this.a.encodeWithTag(protoWriter, 3, mediationElement.options);
            protoWriter.writeBytes(mediationElement.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(MediationElement mediationElement) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, mediationElement.element_id) + ProtoAdapter.UINT32.encodedSizeWithTag(2, mediationElement.mediation_app_id) + this.a.encodedSizeWithTag(3, mediationElement.options) + mediationElement.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public MediationElement redact(MediationElement mediationElement) {
            Builder builderNewBuilder = mediationElement.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public MediationElement(Integer num, Integer num2, Map<String, String> map) {
        this(num, num2, map, ByteString.EMPTY);
    }

    public MediationElement(Integer num, Integer num2, Map<String, String> map, ByteString byteString) {
        super(ADAPTER, byteString);
        this.element_id = num;
        this.mediation_app_id = num2;
        this.options = Internal.immutableCopyOf("options", map);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediationElement)) {
            return false;
        }
        MediationElement mediationElement = (MediationElement) obj;
        return unknownFields().equals(mediationElement.unknownFields()) && Internal.equals(this.element_id, mediationElement.element_id) && Internal.equals(this.mediation_app_id, mediationElement.mediation_app_id) && this.options.equals(mediationElement.options);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.element_id != null ? this.element_id.hashCode() : 0)) * 37) + (this.mediation_app_id != null ? this.mediation_app_id.hashCode() : 0)) * 37) + this.options.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.element_id = this.element_id;
        builder.mediation_app_id = this.mediation_app_id;
        builder.options = Internal.copyOf("options", this.options);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.element_id != null) {
            sb.append(", element_id=");
            sb.append(this.element_id);
        }
        if (this.mediation_app_id != null) {
            sb.append(", mediation_app_id=");
            sb.append(this.mediation_app_id);
        }
        if (!this.options.isEmpty()) {
            sb.append(", options=");
            sb.append(this.options);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "MediationElement{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
