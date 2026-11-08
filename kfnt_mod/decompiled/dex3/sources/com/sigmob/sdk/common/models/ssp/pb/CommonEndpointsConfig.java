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
public final class CommonEndpointsConfig extends AndroidMessage<CommonEndpointsConfig, Builder> {
    public static final ProtoAdapter<CommonEndpointsConfig> ADAPTER = new ProtoAdapter_CommonEndpointsConfig();
    public static final Parcelable.Creator<CommonEndpointsConfig> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_ADS = "";
    public static final String DEFAULT_HB_ADS = "";
    public static final String DEFAULT_LOG = "";
    public static final String DEFAULT_STRATEGY = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String ads;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String hb_ads;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String log;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String strategy;

    public static final class Builder extends Message.Builder<CommonEndpointsConfig, Builder> {
        public String log = "";
        public String ads = "";
        public String strategy = "";
        public String hb_ads = "";

        public Builder ads(String str) {
            this.ads = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public CommonEndpointsConfig build() {
            return new CommonEndpointsConfig(this.log, this.ads, this.strategy, this.hb_ads, super.buildUnknownFields());
        }

        public Builder hb_ads(String str) {
            this.hb_ads = str;
            return this;
        }

        public Builder log(String str) {
            this.log = str;
            return this;
        }

        public Builder strategy(String str) {
            this.strategy = str;
            return this;
        }
    }

    private static final class ProtoAdapter_CommonEndpointsConfig extends ProtoAdapter<CommonEndpointsConfig> {
        public ProtoAdapter_CommonEndpointsConfig() {
            super(FieldEncoding.LENGTH_DELIMITED, CommonEndpointsConfig.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public CommonEndpointsConfig decode(ProtoReader protoReader) {
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
                        builder.log(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.ads(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.strategy(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 4:
                        builder.hb_ads(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, CommonEndpointsConfig commonEndpointsConfig) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, commonEndpointsConfig.log);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, commonEndpointsConfig.ads);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, commonEndpointsConfig.strategy);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, commonEndpointsConfig.hb_ads);
            protoWriter.writeBytes(commonEndpointsConfig.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(CommonEndpointsConfig commonEndpointsConfig) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, commonEndpointsConfig.log) + ProtoAdapter.STRING.encodedSizeWithTag(2, commonEndpointsConfig.ads) + ProtoAdapter.STRING.encodedSizeWithTag(3, commonEndpointsConfig.strategy) + ProtoAdapter.STRING.encodedSizeWithTag(4, commonEndpointsConfig.hb_ads) + commonEndpointsConfig.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public CommonEndpointsConfig redact(CommonEndpointsConfig commonEndpointsConfig) {
            Builder builderNewBuilder = commonEndpointsConfig.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public CommonEndpointsConfig(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, ByteString.EMPTY);
    }

    public CommonEndpointsConfig(String str, String str2, String str3, String str4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.log = str;
        this.ads = str2;
        this.strategy = str3;
        this.hb_ads = str4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CommonEndpointsConfig)) {
            return false;
        }
        CommonEndpointsConfig commonEndpointsConfig = (CommonEndpointsConfig) obj;
        return unknownFields().equals(commonEndpointsConfig.unknownFields()) && Internal.equals(this.log, commonEndpointsConfig.log) && Internal.equals(this.ads, commonEndpointsConfig.ads) && Internal.equals(this.strategy, commonEndpointsConfig.strategy) && Internal.equals(this.hb_ads, commonEndpointsConfig.hb_ads);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.log != null ? this.log.hashCode() : 0)) * 37) + (this.ads != null ? this.ads.hashCode() : 0)) * 37) + (this.strategy != null ? this.strategy.hashCode() : 0)) * 37) + (this.hb_ads != null ? this.hb_ads.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.log = this.log;
        builder.ads = this.ads;
        builder.strategy = this.strategy;
        builder.hb_ads = this.hb_ads;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.log != null) {
            sb.append(", log=");
            sb.append(this.log);
        }
        if (this.ads != null) {
            sb.append(", ads=");
            sb.append(this.ads);
        }
        if (this.strategy != null) {
            sb.append(", strategy=");
            sb.append(this.strategy);
        }
        if (this.hb_ads != null) {
            sb.append(", hb_ads=");
            sb.append(this.hb_ads);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "CommonEndpointsConfig{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
