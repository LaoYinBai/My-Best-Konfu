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
public final class AdSetting extends AndroidMessage<AdSetting, Builder> {
    public static final ProtoAdapter<AdSetting> ADAPTER = new ProtoAdapter_AdSetting();
    public static final Parcelable.Creator<AdSetting> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_RETRY_COUNT = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer retry_count;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.RvAdSetting#ADAPTER", tag = 1)
    public final RvAdSetting rv_setting;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.SingleNativeAdSetting#ADAPTER", tag = 4)
    public final SingleNativeAdSetting single_native_setting;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.SplashAdSetting#ADAPTER", tag = 2)
    public final SplashAdSetting splash_setting;

    public static final class Builder extends Message.Builder<AdSetting, Builder> {
        public Integer retry_count = AdSetting.DEFAULT_RETRY_COUNT;
        public RvAdSetting rv_setting;
        public SingleNativeAdSetting single_native_setting;
        public SplashAdSetting splash_setting;

        @Override // com.sigmob.wire.Message.Builder
        public AdSetting build() {
            return new AdSetting(this.rv_setting, this.splash_setting, this.retry_count, this.single_native_setting, super.buildUnknownFields());
        }

        public Builder retry_count(Integer num) {
            this.retry_count = num;
            return this;
        }

        public Builder rv_setting(RvAdSetting rvAdSetting) {
            this.rv_setting = rvAdSetting;
            return this;
        }

        public Builder single_native_setting(SingleNativeAdSetting singleNativeAdSetting) {
            this.single_native_setting = singleNativeAdSetting;
            return this;
        }

        public Builder splash_setting(SplashAdSetting splashAdSetting) {
            this.splash_setting = splashAdSetting;
            return this;
        }
    }

    private static final class ProtoAdapter_AdSetting extends ProtoAdapter<AdSetting> {
        public ProtoAdapter_AdSetting() {
            super(FieldEncoding.LENGTH_DELIMITED, AdSetting.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public AdSetting decode(ProtoReader protoReader) {
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
                        builder.rv_setting(RvAdSetting.ADAPTER.decode(protoReader));
                        break;
                    case 2:
                        builder.splash_setting(SplashAdSetting.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        builder.retry_count(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 4:
                        builder.single_native_setting(SingleNativeAdSetting.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, AdSetting adSetting) {
            RvAdSetting.ADAPTER.encodeWithTag(protoWriter, 1, adSetting.rv_setting);
            SplashAdSetting.ADAPTER.encodeWithTag(protoWriter, 2, adSetting.splash_setting);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, adSetting.retry_count);
            SingleNativeAdSetting.ADAPTER.encodeWithTag(protoWriter, 4, adSetting.single_native_setting);
            protoWriter.writeBytes(adSetting.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(AdSetting adSetting) {
            return RvAdSetting.ADAPTER.encodedSizeWithTag(1, adSetting.rv_setting) + SplashAdSetting.ADAPTER.encodedSizeWithTag(2, adSetting.splash_setting) + ProtoAdapter.INT32.encodedSizeWithTag(3, adSetting.retry_count) + SingleNativeAdSetting.ADAPTER.encodedSizeWithTag(4, adSetting.single_native_setting) + adSetting.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public AdSetting redact(AdSetting adSetting) {
            Builder builderNewBuilder = adSetting.newBuilder();
            if (builderNewBuilder.rv_setting != null) {
                builderNewBuilder.rv_setting = RvAdSetting.ADAPTER.redact(builderNewBuilder.rv_setting);
            }
            if (builderNewBuilder.splash_setting != null) {
                builderNewBuilder.splash_setting = SplashAdSetting.ADAPTER.redact(builderNewBuilder.splash_setting);
            }
            if (builderNewBuilder.single_native_setting != null) {
                builderNewBuilder.single_native_setting = SingleNativeAdSetting.ADAPTER.redact(builderNewBuilder.single_native_setting);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public AdSetting(RvAdSetting rvAdSetting, SplashAdSetting splashAdSetting, Integer num, SingleNativeAdSetting singleNativeAdSetting) {
        this(rvAdSetting, splashAdSetting, num, singleNativeAdSetting, ByteString.EMPTY);
    }

    public AdSetting(RvAdSetting rvAdSetting, SplashAdSetting splashAdSetting, Integer num, SingleNativeAdSetting singleNativeAdSetting, ByteString byteString) {
        super(ADAPTER, byteString);
        this.rv_setting = rvAdSetting;
        this.splash_setting = splashAdSetting;
        this.retry_count = num;
        this.single_native_setting = singleNativeAdSetting;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSetting)) {
            return false;
        }
        AdSetting adSetting = (AdSetting) obj;
        return unknownFields().equals(adSetting.unknownFields()) && Internal.equals(this.rv_setting, adSetting.rv_setting) && Internal.equals(this.splash_setting, adSetting.splash_setting) && Internal.equals(this.retry_count, adSetting.retry_count) && Internal.equals(this.single_native_setting, adSetting.single_native_setting);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.rv_setting != null ? this.rv_setting.hashCode() : 0)) * 37) + (this.splash_setting != null ? this.splash_setting.hashCode() : 0)) * 37) + (this.retry_count != null ? this.retry_count.hashCode() : 0)) * 37) + (this.single_native_setting != null ? this.single_native_setting.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.rv_setting = this.rv_setting;
        builder.splash_setting = this.splash_setting;
        builder.retry_count = this.retry_count;
        builder.single_native_setting = this.single_native_setting;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.rv_setting != null) {
            sb.append(", rv_setting=");
            sb.append(this.rv_setting);
        }
        if (this.splash_setting != null) {
            sb.append(", splash_setting=");
            sb.append(this.splash_setting);
        }
        if (this.retry_count != null) {
            sb.append(", retry_count=");
            sb.append(this.retry_count);
        }
        if (this.single_native_setting != null) {
            sb.append(", single_native_setting=");
            sb.append(this.single_native_setting);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "AdSetting{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
