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
public final class SlotAdSetting extends AndroidMessage<SlotAdSetting, Builder> {
    public static final ProtoAdapter<SlotAdSetting> ADAPTER = new ProtoAdapter_SlotAdSetting();
    public static final Parcelable.Creator<SlotAdSetting> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_RETRY_COUNT = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.NativeAdSetting#ADAPTER", tag = 3)
    public final NativeAdSetting native_setting;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 4)
    public final Integer retry_count;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.RvAdSetting#ADAPTER", tag = 1)
    public final RvAdSetting rv_setting;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.SplashAdSetting#ADAPTER", tag = 2)
    public final SplashAdSetting splash_setting;

    public static final class Builder extends Message.Builder<SlotAdSetting, Builder> {
        public NativeAdSetting native_setting;
        public Integer retry_count;
        public RvAdSetting rv_setting;
        public SplashAdSetting splash_setting;

        @Override // com.sigmob.wire.Message.Builder
        public SlotAdSetting build() {
            return new SlotAdSetting(this.rv_setting, this.splash_setting, this.native_setting, this.retry_count, super.buildUnknownFields());
        }

        public Builder native_setting(NativeAdSetting nativeAdSetting) {
            this.native_setting = nativeAdSetting;
            return this;
        }

        public Builder retry_count(Integer num) {
            this.retry_count = num;
            return this;
        }

        public Builder rv_setting(RvAdSetting rvAdSetting) {
            this.rv_setting = rvAdSetting;
            return this;
        }

        public Builder splash_setting(SplashAdSetting splashAdSetting) {
            this.splash_setting = splashAdSetting;
            return this;
        }
    }

    private static final class ProtoAdapter_SlotAdSetting extends ProtoAdapter<SlotAdSetting> {
        public ProtoAdapter_SlotAdSetting() {
            super(FieldEncoding.LENGTH_DELIMITED, SlotAdSetting.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SlotAdSetting decode(ProtoReader protoReader) {
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
                        builder.native_setting(NativeAdSetting.ADAPTER.decode(protoReader));
                        break;
                    case 4:
                        builder.retry_count(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SlotAdSetting slotAdSetting) {
            RvAdSetting.ADAPTER.encodeWithTag(protoWriter, 1, slotAdSetting.rv_setting);
            SplashAdSetting.ADAPTER.encodeWithTag(protoWriter, 2, slotAdSetting.splash_setting);
            NativeAdSetting.ADAPTER.encodeWithTag(protoWriter, 3, slotAdSetting.native_setting);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 4, slotAdSetting.retry_count);
            protoWriter.writeBytes(slotAdSetting.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SlotAdSetting slotAdSetting) {
            return RvAdSetting.ADAPTER.encodedSizeWithTag(1, slotAdSetting.rv_setting) + SplashAdSetting.ADAPTER.encodedSizeWithTag(2, slotAdSetting.splash_setting) + NativeAdSetting.ADAPTER.encodedSizeWithTag(3, slotAdSetting.native_setting) + ProtoAdapter.INT32.encodedSizeWithTag(4, slotAdSetting.retry_count) + slotAdSetting.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SlotAdSetting redact(SlotAdSetting slotAdSetting) {
            Builder builderNewBuilder = slotAdSetting.newBuilder();
            if (builderNewBuilder.rv_setting != null) {
                builderNewBuilder.rv_setting = RvAdSetting.ADAPTER.redact(builderNewBuilder.rv_setting);
            }
            if (builderNewBuilder.splash_setting != null) {
                builderNewBuilder.splash_setting = SplashAdSetting.ADAPTER.redact(builderNewBuilder.splash_setting);
            }
            if (builderNewBuilder.native_setting != null) {
                builderNewBuilder.native_setting = NativeAdSetting.ADAPTER.redact(builderNewBuilder.native_setting);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SlotAdSetting(RvAdSetting rvAdSetting, SplashAdSetting splashAdSetting, NativeAdSetting nativeAdSetting, Integer num) {
        this(rvAdSetting, splashAdSetting, nativeAdSetting, num, ByteString.EMPTY);
    }

    public SlotAdSetting(RvAdSetting rvAdSetting, SplashAdSetting splashAdSetting, NativeAdSetting nativeAdSetting, Integer num, ByteString byteString) {
        super(ADAPTER, byteString);
        this.rv_setting = rvAdSetting;
        this.splash_setting = splashAdSetting;
        this.native_setting = nativeAdSetting;
        this.retry_count = num;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SlotAdSetting)) {
            return false;
        }
        SlotAdSetting slotAdSetting = (SlotAdSetting) obj;
        return unknownFields().equals(slotAdSetting.unknownFields()) && Internal.equals(this.rv_setting, slotAdSetting.rv_setting) && Internal.equals(this.splash_setting, slotAdSetting.splash_setting) && Internal.equals(this.native_setting, slotAdSetting.native_setting) && Internal.equals(this.retry_count, slotAdSetting.retry_count);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.rv_setting != null ? this.rv_setting.hashCode() : 0)) * 37) + (this.splash_setting != null ? this.splash_setting.hashCode() : 0)) * 37) + (this.native_setting != null ? this.native_setting.hashCode() : 0)) * 37) + (this.retry_count != null ? this.retry_count.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.rv_setting = this.rv_setting;
        builder.splash_setting = this.splash_setting;
        builder.native_setting = this.native_setting;
        builder.retry_count = this.retry_count;
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
        if (this.native_setting != null) {
            sb.append(", native_setting=");
            sb.append(this.native_setting);
        }
        if (this.retry_count != null) {
            sb.append(", retry_count=");
            sb.append(this.retry_count);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SlotAdSetting{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
