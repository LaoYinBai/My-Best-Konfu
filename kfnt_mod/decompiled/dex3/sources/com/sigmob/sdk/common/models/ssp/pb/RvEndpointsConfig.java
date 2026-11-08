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
public final class RvEndpointsConfig extends AndroidMessage<RvEndpointsConfig, Builder> {
    public static final ProtoAdapter<RvEndpointsConfig> ADAPTER = new ProtoAdapter_RvEndpointsConfig();
    public static final Parcelable.Creator<RvEndpointsConfig> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_ADS = "";
    public static final String DEFAULT_STRATEGY = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String ads;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String strategy;

    public static final class Builder extends Message.Builder<RvEndpointsConfig, Builder> {
        public String ads = "";
        public String strategy = "";

        public Builder ads(String str) {
            this.ads = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public RvEndpointsConfig build() {
            return new RvEndpointsConfig(this.ads, this.strategy, super.buildUnknownFields());
        }

        public Builder strategy(String str) {
            this.strategy = str;
            return this;
        }
    }

    private static final class ProtoAdapter_RvEndpointsConfig extends ProtoAdapter<RvEndpointsConfig> {
        public ProtoAdapter_RvEndpointsConfig() {
            super(FieldEncoding.LENGTH_DELIMITED, RvEndpointsConfig.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public RvEndpointsConfig decode(ProtoReader protoReader) {
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
                        builder.ads(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.strategy(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, RvEndpointsConfig rvEndpointsConfig) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, rvEndpointsConfig.ads);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, rvEndpointsConfig.strategy);
            protoWriter.writeBytes(rvEndpointsConfig.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(RvEndpointsConfig rvEndpointsConfig) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, rvEndpointsConfig.ads) + ProtoAdapter.STRING.encodedSizeWithTag(2, rvEndpointsConfig.strategy) + rvEndpointsConfig.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public RvEndpointsConfig redact(RvEndpointsConfig rvEndpointsConfig) {
            Builder builderNewBuilder = rvEndpointsConfig.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public RvEndpointsConfig(String str, String str2) {
        this(str, str2, ByteString.EMPTY);
    }

    public RvEndpointsConfig(String str, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.ads = str;
        this.strategy = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RvEndpointsConfig)) {
            return false;
        }
        RvEndpointsConfig rvEndpointsConfig = (RvEndpointsConfig) obj;
        return unknownFields().equals(rvEndpointsConfig.unknownFields()) && Internal.equals(this.ads, rvEndpointsConfig.ads) && Internal.equals(this.strategy, rvEndpointsConfig.strategy);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((unknownFields().hashCode() * 37) + (this.ads != null ? this.ads.hashCode() : 0)) * 37) + (this.strategy != null ? this.strategy.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.ads = this.ads;
        builder.strategy = this.strategy;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.ads != null) {
            sb.append(", ads=");
            sb.append(this.ads);
        }
        if (this.strategy != null) {
            sb.append(", strategy=");
            sb.append(this.strategy);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "RvEndpointsConfig{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
