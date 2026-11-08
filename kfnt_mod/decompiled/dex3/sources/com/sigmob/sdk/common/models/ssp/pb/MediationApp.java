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
public final class MediationApp extends AndroidMessage<MediationApp, Builder> {
    public static final ProtoAdapter<MediationApp> ADAPTER = new ProtoAdapter_MediationApp();
    public static final Parcelable.Creator<MediationApp> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_APP_ID = 0;
    public static final Integer DEFAULT_MEDIATION_CHANNEL_ID = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer app_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer mediation_channel_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final Map<String, String> options;

    public static final class Builder extends Message.Builder<MediationApp, Builder> {
        public Integer app_id = MediationApp.DEFAULT_APP_ID;
        public Integer mediation_channel_id = MediationApp.DEFAULT_MEDIATION_CHANNEL_ID;
        public Map<String, String> options = Internal.newMutableMap();

        public Builder app_id(Integer num) {
            this.app_id = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public MediationApp build() {
            return new MediationApp(this.app_id, this.mediation_channel_id, this.options, super.buildUnknownFields());
        }

        public Builder mediation_channel_id(Integer num) {
            this.mediation_channel_id = num;
            return this;
        }

        public Builder options(Map<String, String> map) {
            Internal.checkElementsNotNull(map);
            this.options = map;
            return this;
        }
    }

    private static final class ProtoAdapter_MediationApp extends ProtoAdapter<MediationApp> {
        private final ProtoAdapter<Map<String, String>> a;

        public ProtoAdapter_MediationApp() {
            super(FieldEncoding.LENGTH_DELIMITED, MediationApp.class);
            this.a = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.STRING);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public MediationApp decode(ProtoReader protoReader) {
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
                        builder.app_id(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.mediation_channel_id(ProtoAdapter.UINT32.decode(protoReader));
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
        public void encode(ProtoWriter protoWriter, MediationApp mediationApp) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, mediationApp.app_id);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, mediationApp.mediation_channel_id);
            this.a.encodeWithTag(protoWriter, 3, mediationApp.options);
            protoWriter.writeBytes(mediationApp.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(MediationApp mediationApp) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, mediationApp.app_id) + ProtoAdapter.UINT32.encodedSizeWithTag(2, mediationApp.mediation_channel_id) + this.a.encodedSizeWithTag(3, mediationApp.options) + mediationApp.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public MediationApp redact(MediationApp mediationApp) {
            Builder builderNewBuilder = mediationApp.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public MediationApp(Integer num, Integer num2, Map<String, String> map) {
        this(num, num2, map, ByteString.EMPTY);
    }

    public MediationApp(Integer num, Integer num2, Map<String, String> map, ByteString byteString) {
        super(ADAPTER, byteString);
        this.app_id = num;
        this.mediation_channel_id = num2;
        this.options = Internal.immutableCopyOf("options", map);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediationApp)) {
            return false;
        }
        MediationApp mediationApp = (MediationApp) obj;
        return unknownFields().equals(mediationApp.unknownFields()) && Internal.equals(this.app_id, mediationApp.app_id) && Internal.equals(this.mediation_channel_id, mediationApp.mediation_channel_id) && this.options.equals(mediationApp.options);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.app_id != null ? this.app_id.hashCode() : 0)) * 37) + (this.mediation_channel_id != null ? this.mediation_channel_id.hashCode() : 0)) * 37) + this.options.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.app_id = this.app_id;
        builder.mediation_channel_id = this.mediation_channel_id;
        builder.options = Internal.copyOf("options", this.options);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.app_id != null) {
            sb.append(", app_id=");
            sb.append(this.app_id);
        }
        if (this.mediation_channel_id != null) {
            sb.append(", mediation_channel_id=");
            sb.append(this.mediation_channel_id);
        }
        if (!this.options.isEmpty()) {
            sb.append(", options=");
            sb.append(this.options);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "MediationApp{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
