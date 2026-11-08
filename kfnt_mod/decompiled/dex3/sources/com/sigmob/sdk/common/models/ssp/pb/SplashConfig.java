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
public final class SplashConfig extends AndroidMessage<SplashConfig, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 2)
    public final Integer cacheTop;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer material_expired_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer showDuration;
    public static final ProtoAdapter<SplashConfig> ADAPTER = new ProtoAdapter_SplashConfig();
    public static final Parcelable.Creator<SplashConfig> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_SHOWDURATION = 0;
    public static final Integer DEFAULT_CACHETOP = 50;
    public static final Integer DEFAULT_MATERIAL_EXPIRED_TIME = 3;

    public static final class Builder extends Message.Builder<SplashConfig, Builder> {
        public Integer showDuration = SplashConfig.DEFAULT_SHOWDURATION;
        public Integer cacheTop = SplashConfig.DEFAULT_CACHETOP;
        public Integer material_expired_time = SplashConfig.DEFAULT_MATERIAL_EXPIRED_TIME;

        @Override // com.sigmob.wire.Message.Builder
        public SplashConfig build() {
            return new SplashConfig(this.showDuration, this.cacheTop, this.material_expired_time, super.buildUnknownFields());
        }

        public Builder cacheTop(Integer num) {
            this.cacheTop = num;
            return this;
        }

        public Builder material_expired_time(Integer num) {
            this.material_expired_time = num;
            return this;
        }

        public Builder showDuration(Integer num) {
            this.showDuration = num;
            return this;
        }
    }

    private static final class ProtoAdapter_SplashConfig extends ProtoAdapter<SplashConfig> {
        public ProtoAdapter_SplashConfig() {
            super(FieldEncoding.LENGTH_DELIMITED, SplashConfig.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SplashConfig decode(ProtoReader protoReader) {
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
                        builder.showDuration(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.cacheTop(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 3:
                        builder.material_expired_time(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SplashConfig splashConfig) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, splashConfig.showDuration);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 2, splashConfig.cacheTop);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, splashConfig.material_expired_time);
            protoWriter.writeBytes(splashConfig.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SplashConfig splashConfig) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, splashConfig.showDuration) + ProtoAdapter.INT32.encodedSizeWithTag(2, splashConfig.cacheTop) + ProtoAdapter.INT32.encodedSizeWithTag(3, splashConfig.material_expired_time) + splashConfig.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SplashConfig redact(SplashConfig splashConfig) {
            Builder builderNewBuilder = splashConfig.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SplashConfig(Integer num, Integer num2, Integer num3) {
        this(num, num2, num3, ByteString.EMPTY);
    }

    public SplashConfig(Integer num, Integer num2, Integer num3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.showDuration = num;
        this.cacheTop = num2;
        this.material_expired_time = num3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SplashConfig)) {
            return false;
        }
        SplashConfig splashConfig = (SplashConfig) obj;
        return unknownFields().equals(splashConfig.unknownFields()) && Internal.equals(this.showDuration, splashConfig.showDuration) && Internal.equals(this.cacheTop, splashConfig.cacheTop) && Internal.equals(this.material_expired_time, splashConfig.material_expired_time);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.showDuration != null ? this.showDuration.hashCode() : 0)) * 37) + (this.cacheTop != null ? this.cacheTop.hashCode() : 0)) * 37) + (this.material_expired_time != null ? this.material_expired_time.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.showDuration = this.showDuration;
        builder.cacheTop = this.cacheTop;
        builder.material_expired_time = this.material_expired_time;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.showDuration != null) {
            sb.append(", showDuration=");
            sb.append(this.showDuration);
        }
        if (this.cacheTop != null) {
            sb.append(", cacheTop=");
            sb.append(this.cacheTop);
        }
        if (this.material_expired_time != null) {
            sb.append(", material_expired_time=");
            sb.append(this.material_expired_time);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SplashConfig{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
