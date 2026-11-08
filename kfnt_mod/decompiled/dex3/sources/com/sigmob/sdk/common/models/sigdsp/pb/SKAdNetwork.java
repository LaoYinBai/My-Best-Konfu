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
public final class SKAdNetwork extends AndroidMessage<SKAdNetwork, Builder> {
    public static final String DEFAULT_AD_NETWORK_ID = "";
    public static final String DEFAULT_ITUNES_ID = "";
    public static final String DEFAULT_NONCE = "";
    public static final String DEFAULT_SIGN = "";
    public static final String DEFAULT_SOURCE_APP_ID = "";
    public static final String DEFAULT_VERSION = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String ad_network_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer campaign_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 9)
    public final Integer fidelity_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String itunes_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String nonce;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String sign;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String source_app_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 7)
    public final Long timestamp;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String version;
    public static final ProtoAdapter<SKAdNetwork> ADAPTER = new ProtoAdapter_SKAdNetwork();
    public static final Parcelable.Creator<SKAdNetwork> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CAMPAIGN_ID = 0;
    public static final Long DEFAULT_TIMESTAMP = 0L;
    public static final Integer DEFAULT_FIDELITY_TYPE = 0;

    public static final class Builder extends Message.Builder<SKAdNetwork, Builder> {
        public String ad_network_id;
        public Integer campaign_id;
        public Integer fidelity_type;
        public String itunes_id;
        public String nonce;
        public String sign;
        public String source_app_id;
        public Long timestamp;
        public String version;

        public Builder ad_network_id(String str) {
            this.ad_network_id = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public SKAdNetwork build() {
            return new SKAdNetwork(this.version, this.ad_network_id, this.campaign_id, this.itunes_id, this.source_app_id, this.nonce, this.timestamp, this.sign, this.fidelity_type, super.buildUnknownFields());
        }

        public Builder campaign_id(Integer num) {
            this.campaign_id = num;
            return this;
        }

        public Builder fidelity_type(Integer num) {
            this.fidelity_type = num;
            return this;
        }

        public Builder itunes_id(String str) {
            this.itunes_id = str;
            return this;
        }

        public Builder nonce(String str) {
            this.nonce = str;
            return this;
        }

        public Builder sign(String str) {
            this.sign = str;
            return this;
        }

        public Builder source_app_id(String str) {
            this.source_app_id = str;
            return this;
        }

        public Builder timestamp(Long l) {
            this.timestamp = l;
            return this;
        }

        public Builder version(String str) {
            this.version = str;
            return this;
        }
    }

    private static final class ProtoAdapter_SKAdNetwork extends ProtoAdapter<SKAdNetwork> {
        public ProtoAdapter_SKAdNetwork() {
            super(FieldEncoding.LENGTH_DELIMITED, SKAdNetwork.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SKAdNetwork decode(ProtoReader protoReader) {
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
                        builder.version(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.ad_network_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.campaign_id(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 4:
                        builder.itunes_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.source_app_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 6:
                        builder.nonce(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 7:
                        builder.timestamp(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 8:
                        builder.sign(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 9:
                        builder.fidelity_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SKAdNetwork sKAdNetwork) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, sKAdNetwork.version);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, sKAdNetwork.ad_network_id);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, sKAdNetwork.campaign_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, sKAdNetwork.itunes_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, sKAdNetwork.source_app_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, sKAdNetwork.nonce);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 7, sKAdNetwork.timestamp);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, sKAdNetwork.sign);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 9, sKAdNetwork.fidelity_type);
            protoWriter.writeBytes(sKAdNetwork.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SKAdNetwork sKAdNetwork) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, sKAdNetwork.version) + ProtoAdapter.STRING.encodedSizeWithTag(2, sKAdNetwork.ad_network_id) + ProtoAdapter.INT32.encodedSizeWithTag(3, sKAdNetwork.campaign_id) + ProtoAdapter.STRING.encodedSizeWithTag(4, sKAdNetwork.itunes_id) + ProtoAdapter.STRING.encodedSizeWithTag(5, sKAdNetwork.source_app_id) + ProtoAdapter.STRING.encodedSizeWithTag(6, sKAdNetwork.nonce) + ProtoAdapter.UINT64.encodedSizeWithTag(7, sKAdNetwork.timestamp) + ProtoAdapter.STRING.encodedSizeWithTag(8, sKAdNetwork.sign) + ProtoAdapter.UINT32.encodedSizeWithTag(9, sKAdNetwork.fidelity_type) + sKAdNetwork.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SKAdNetwork redact(SKAdNetwork sKAdNetwork) {
            Builder builderNewBuilder = sKAdNetwork.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SKAdNetwork(String str, String str2, Integer num, String str3, String str4, String str5, Long l, String str6, Integer num2) {
        this(str, str2, num, str3, str4, str5, l, str6, num2, ByteString.EMPTY);
    }

    public SKAdNetwork(String str, String str2, Integer num, String str3, String str4, String str5, Long l, String str6, Integer num2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.version = str;
        this.ad_network_id = str2;
        this.campaign_id = num;
        this.itunes_id = str3;
        this.source_app_id = str4;
        this.nonce = str5;
        this.timestamp = l;
        this.sign = str6;
        this.fidelity_type = num2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SKAdNetwork)) {
            return false;
        }
        SKAdNetwork sKAdNetwork = (SKAdNetwork) obj;
        return unknownFields().equals(sKAdNetwork.unknownFields()) && Internal.equals(this.version, sKAdNetwork.version) && Internal.equals(this.ad_network_id, sKAdNetwork.ad_network_id) && Internal.equals(this.campaign_id, sKAdNetwork.campaign_id) && Internal.equals(this.itunes_id, sKAdNetwork.itunes_id) && Internal.equals(this.source_app_id, sKAdNetwork.source_app_id) && Internal.equals(this.nonce, sKAdNetwork.nonce) && Internal.equals(this.timestamp, sKAdNetwork.timestamp) && Internal.equals(this.sign, sKAdNetwork.sign) && Internal.equals(this.fidelity_type, sKAdNetwork.fidelity_type);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((unknownFields().hashCode() * 37) + (this.version != null ? this.version.hashCode() : 0)) * 37) + (this.ad_network_id != null ? this.ad_network_id.hashCode() : 0)) * 37) + (this.campaign_id != null ? this.campaign_id.hashCode() : 0)) * 37) + (this.itunes_id != null ? this.itunes_id.hashCode() : 0)) * 37) + (this.source_app_id != null ? this.source_app_id.hashCode() : 0)) * 37) + (this.nonce != null ? this.nonce.hashCode() : 0)) * 37) + (this.timestamp != null ? this.timestamp.hashCode() : 0)) * 37) + (this.sign != null ? this.sign.hashCode() : 0)) * 37) + (this.fidelity_type != null ? this.fidelity_type.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.version = this.version;
        builder.ad_network_id = this.ad_network_id;
        builder.campaign_id = this.campaign_id;
        builder.itunes_id = this.itunes_id;
        builder.source_app_id = this.source_app_id;
        builder.nonce = this.nonce;
        builder.timestamp = this.timestamp;
        builder.sign = this.sign;
        builder.fidelity_type = this.fidelity_type;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.version != null) {
            sb.append(", version=");
            sb.append(this.version);
        }
        if (this.ad_network_id != null) {
            sb.append(", ad_network_id=");
            sb.append(this.ad_network_id);
        }
        if (this.campaign_id != null) {
            sb.append(", campaign_id=");
            sb.append(this.campaign_id);
        }
        if (this.itunes_id != null) {
            sb.append(", itunes_id=");
            sb.append(this.itunes_id);
        }
        if (this.source_app_id != null) {
            sb.append(", source_app_id=");
            sb.append(this.source_app_id);
        }
        if (this.nonce != null) {
            sb.append(", nonce=");
            sb.append(this.nonce);
        }
        if (this.timestamp != null) {
            sb.append(", timestamp=");
            sb.append(this.timestamp);
        }
        if (this.sign != null) {
            sb.append(", sign=");
            sb.append(this.sign);
        }
        if (this.fidelity_type != null) {
            sb.append(", fidelity_type=");
            sb.append(this.fidelity_type);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SKAdNetwork{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
