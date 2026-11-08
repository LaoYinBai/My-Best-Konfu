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
public final class SplashAdSetting extends AndroidMessage<SplashAdSetting, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer auto_click_mode;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 4)
    public final Integer auto_click_time_ratio;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 2)
    public final Boolean enable_close_on_click;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 5)
    public final Boolean enable_full_click;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 6)
    public final Boolean invisible_ad_label;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer show_duration;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 7)
    public final Boolean use_floating_btn;
    public static final ProtoAdapter<SplashAdSetting> ADAPTER = new ProtoAdapter_SplashAdSetting();
    public static final Parcelable.Creator<SplashAdSetting> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_SHOW_DURATION = 0;
    public static final Boolean DEFAULT_ENABLE_CLOSE_ON_CLICK = false;
    public static final Integer DEFAULT_AUTO_CLICK_MODE = 0;
    public static final Integer DEFAULT_AUTO_CLICK_TIME_RATIO = 0;
    public static final Boolean DEFAULT_ENABLE_FULL_CLICK = false;
    public static final Boolean DEFAULT_INVISIBLE_AD_LABEL = false;
    public static final Boolean DEFAULT_USE_FLOATING_BTN = false;

    public static final class Builder extends Message.Builder<SplashAdSetting, Builder> {
        public Integer show_duration = SplashAdSetting.DEFAULT_SHOW_DURATION;
        public Boolean enable_close_on_click = SplashAdSetting.DEFAULT_ENABLE_CLOSE_ON_CLICK;
        public Integer auto_click_mode = SplashAdSetting.DEFAULT_AUTO_CLICK_MODE;
        public Integer auto_click_time_ratio = SplashAdSetting.DEFAULT_AUTO_CLICK_TIME_RATIO;
        public Boolean enable_full_click = SplashAdSetting.DEFAULT_ENABLE_FULL_CLICK;
        public Boolean invisible_ad_label = SplashAdSetting.DEFAULT_INVISIBLE_AD_LABEL;
        public Boolean use_floating_btn = SplashAdSetting.DEFAULT_USE_FLOATING_BTN;

        public Builder auto_click_mode(Integer num) {
            this.auto_click_mode = num;
            return this;
        }

        public Builder auto_click_time_ratio(Integer num) {
            this.auto_click_time_ratio = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public SplashAdSetting build() {
            return new SplashAdSetting(this.show_duration, this.enable_close_on_click, this.auto_click_mode, this.auto_click_time_ratio, this.enable_full_click, this.invisible_ad_label, this.use_floating_btn, super.buildUnknownFields());
        }

        public Builder enable_close_on_click(Boolean bool) {
            this.enable_close_on_click = bool;
            return this;
        }

        public Builder enable_full_click(Boolean bool) {
            this.enable_full_click = bool;
            return this;
        }

        public Builder invisible_ad_label(Boolean bool) {
            this.invisible_ad_label = bool;
            return this;
        }

        public Builder show_duration(Integer num) {
            this.show_duration = num;
            return this;
        }

        public Builder use_floating_btn(Boolean bool) {
            this.use_floating_btn = bool;
            return this;
        }
    }

    private static final class ProtoAdapter_SplashAdSetting extends ProtoAdapter<SplashAdSetting> {
        public ProtoAdapter_SplashAdSetting() {
            super(FieldEncoding.LENGTH_DELIMITED, SplashAdSetting.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SplashAdSetting decode(ProtoReader protoReader) {
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
                        builder.show_duration(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 2:
                        builder.enable_close_on_click(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 3:
                        builder.auto_click_mode(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 4:
                        builder.auto_click_time_ratio(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 5:
                        builder.enable_full_click(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 6:
                        builder.invisible_ad_label(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 7:
                        builder.use_floating_btn(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SplashAdSetting splashAdSetting) {
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, splashAdSetting.show_duration);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 2, splashAdSetting.enable_close_on_click);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, splashAdSetting.auto_click_mode);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 4, splashAdSetting.auto_click_time_ratio);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 5, splashAdSetting.enable_full_click);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 6, splashAdSetting.invisible_ad_label);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 7, splashAdSetting.use_floating_btn);
            protoWriter.writeBytes(splashAdSetting.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SplashAdSetting splashAdSetting) {
            return ProtoAdapter.INT32.encodedSizeWithTag(1, splashAdSetting.show_duration) + ProtoAdapter.BOOL.encodedSizeWithTag(2, splashAdSetting.enable_close_on_click) + ProtoAdapter.UINT32.encodedSizeWithTag(3, splashAdSetting.auto_click_mode) + ProtoAdapter.INT32.encodedSizeWithTag(4, splashAdSetting.auto_click_time_ratio) + ProtoAdapter.BOOL.encodedSizeWithTag(5, splashAdSetting.enable_full_click) + ProtoAdapter.BOOL.encodedSizeWithTag(6, splashAdSetting.invisible_ad_label) + ProtoAdapter.BOOL.encodedSizeWithTag(7, splashAdSetting.use_floating_btn) + splashAdSetting.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SplashAdSetting redact(SplashAdSetting splashAdSetting) {
            Builder builderNewBuilder = splashAdSetting.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SplashAdSetting(Integer num, Boolean bool, Integer num2, Integer num3, Boolean bool2, Boolean bool3, Boolean bool4) {
        this(num, bool, num2, num3, bool2, bool3, bool4, ByteString.EMPTY);
    }

    public SplashAdSetting(Integer num, Boolean bool, Integer num2, Integer num3, Boolean bool2, Boolean bool3, Boolean bool4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.show_duration = num;
        this.enable_close_on_click = bool;
        this.auto_click_mode = num2;
        this.auto_click_time_ratio = num3;
        this.enable_full_click = bool2;
        this.invisible_ad_label = bool3;
        this.use_floating_btn = bool4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SplashAdSetting)) {
            return false;
        }
        SplashAdSetting splashAdSetting = (SplashAdSetting) obj;
        return unknownFields().equals(splashAdSetting.unknownFields()) && Internal.equals(this.show_duration, splashAdSetting.show_duration) && Internal.equals(this.enable_close_on_click, splashAdSetting.enable_close_on_click) && Internal.equals(this.auto_click_mode, splashAdSetting.auto_click_mode) && Internal.equals(this.auto_click_time_ratio, splashAdSetting.auto_click_time_ratio) && Internal.equals(this.enable_full_click, splashAdSetting.enable_full_click) && Internal.equals(this.invisible_ad_label, splashAdSetting.invisible_ad_label) && Internal.equals(this.use_floating_btn, splashAdSetting.use_floating_btn);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((unknownFields().hashCode() * 37) + (this.show_duration != null ? this.show_duration.hashCode() : 0)) * 37) + (this.enable_close_on_click != null ? this.enable_close_on_click.hashCode() : 0)) * 37) + (this.auto_click_mode != null ? this.auto_click_mode.hashCode() : 0)) * 37) + (this.auto_click_time_ratio != null ? this.auto_click_time_ratio.hashCode() : 0)) * 37) + (this.enable_full_click != null ? this.enable_full_click.hashCode() : 0)) * 37) + (this.invisible_ad_label != null ? this.invisible_ad_label.hashCode() : 0)) * 37) + (this.use_floating_btn != null ? this.use_floating_btn.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.show_duration = this.show_duration;
        builder.enable_close_on_click = this.enable_close_on_click;
        builder.auto_click_mode = this.auto_click_mode;
        builder.auto_click_time_ratio = this.auto_click_time_ratio;
        builder.enable_full_click = this.enable_full_click;
        builder.invisible_ad_label = this.invisible_ad_label;
        builder.use_floating_btn = this.use_floating_btn;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.show_duration != null) {
            sb.append(", show_duration=");
            sb.append(this.show_duration);
        }
        if (this.enable_close_on_click != null) {
            sb.append(", enable_close_on_click=");
            sb.append(this.enable_close_on_click);
        }
        if (this.auto_click_mode != null) {
            sb.append(", auto_click_mode=");
            sb.append(this.auto_click_mode);
        }
        if (this.auto_click_time_ratio != null) {
            sb.append(", auto_click_time_ratio=");
            sb.append(this.auto_click_time_ratio);
        }
        if (this.enable_full_click != null) {
            sb.append(", enable_full_click=");
            sb.append(this.enable_full_click);
        }
        if (this.invisible_ad_label != null) {
            sb.append(", invisible_ad_label=");
            sb.append(this.invisible_ad_label);
        }
        if (this.use_floating_btn != null) {
            sb.append(", use_floating_btn=");
            sb.append(this.use_floating_btn);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SplashAdSetting{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
