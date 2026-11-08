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
public final class RvAdSetting extends AndroidMessage<RvAdSetting, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.ClickAreaSetting#ADAPTER", tag = 10)
    public final ClickAreaSetting click_setting;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 15)
    public final Boolean disable_auto_load;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 8)
    public final Boolean enable_exit_on_video_close;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 14)
    public final Integer end_impression_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 12)
    public final Integer end_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 13)
    public final Integer endcard_close_image;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 4)
    public final Integer endcard_close_position;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
    public final Float finished;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 9)
    public final Boolean full_click_on_video;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 1)
    public final Integer if_mute;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 11)
    public final Boolean invisible_ad_label;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 5)
    public final Integer mute_postion;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 6)
    public final Integer skip_percent;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 7)
    public final Integer skip_seconds;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer video_close_position;
    public static final ProtoAdapter<RvAdSetting> ADAPTER = new ProtoAdapter_RvAdSetting();
    public static final Parcelable.Creator<RvAdSetting> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_IF_MUTE = 0;
    public static final Float DEFAULT_FINISHED = Float.valueOf(1.0f);
    public static final Integer DEFAULT_VIDEO_CLOSE_POSITION = 1;
    public static final Integer DEFAULT_ENDCARD_CLOSE_POSITION = 3;
    public static final Integer DEFAULT_MUTE_POSTION = 4;
    public static final Integer DEFAULT_SKIP_PERCENT = 0;
    public static final Integer DEFAULT_SKIP_SECONDS = -1;
    public static final Boolean DEFAULT_ENABLE_EXIT_ON_VIDEO_CLOSE = false;
    public static final Boolean DEFAULT_FULL_CLICK_ON_VIDEO = false;
    public static final Boolean DEFAULT_INVISIBLE_AD_LABEL = false;
    public static final Integer DEFAULT_END_TIME = 0;
    public static final Integer DEFAULT_ENDCARD_CLOSE_IMAGE = 0;
    public static final Integer DEFAULT_END_IMPRESSION_TIME = 0;
    public static final Boolean DEFAULT_DISABLE_AUTO_LOAD = false;

    public static final class Builder extends Message.Builder<RvAdSetting, Builder> {
        public ClickAreaSetting click_setting;
        public Integer if_mute = RvAdSetting.DEFAULT_IF_MUTE;
        public Float finished = RvAdSetting.DEFAULT_FINISHED;
        public Integer video_close_position = RvAdSetting.DEFAULT_VIDEO_CLOSE_POSITION;
        public Integer endcard_close_position = RvAdSetting.DEFAULT_ENDCARD_CLOSE_POSITION;
        public Integer mute_postion = RvAdSetting.DEFAULT_MUTE_POSTION;
        public Integer skip_percent = RvAdSetting.DEFAULT_SKIP_PERCENT;
        public Integer skip_seconds = RvAdSetting.DEFAULT_SKIP_SECONDS;
        public Boolean enable_exit_on_video_close = RvAdSetting.DEFAULT_ENABLE_EXIT_ON_VIDEO_CLOSE;
        public Boolean full_click_on_video = RvAdSetting.DEFAULT_FULL_CLICK_ON_VIDEO;
        public Boolean invisible_ad_label = RvAdSetting.DEFAULT_INVISIBLE_AD_LABEL;
        public Integer end_time = RvAdSetting.DEFAULT_END_TIME;
        public Integer endcard_close_image = RvAdSetting.DEFAULT_ENDCARD_CLOSE_IMAGE;
        public Integer end_impression_time = RvAdSetting.DEFAULT_END_IMPRESSION_TIME;
        public Boolean disable_auto_load = RvAdSetting.DEFAULT_DISABLE_AUTO_LOAD;

        @Override // com.sigmob.wire.Message.Builder
        public RvAdSetting build() {
            return new RvAdSetting(this.if_mute, this.finished, this.video_close_position, this.endcard_close_position, this.mute_postion, this.skip_percent, this.skip_seconds, this.enable_exit_on_video_close, this.full_click_on_video, this.click_setting, this.invisible_ad_label, this.end_time, this.endcard_close_image, this.end_impression_time, this.disable_auto_load, super.buildUnknownFields());
        }

        public Builder click_setting(ClickAreaSetting clickAreaSetting) {
            this.click_setting = clickAreaSetting;
            return this;
        }

        public Builder disable_auto_load(Boolean bool) {
            this.disable_auto_load = bool;
            return this;
        }

        public Builder enable_exit_on_video_close(Boolean bool) {
            this.enable_exit_on_video_close = bool;
            return this;
        }

        public Builder end_impression_time(Integer num) {
            this.end_impression_time = num;
            return this;
        }

        public Builder end_time(Integer num) {
            this.end_time = num;
            return this;
        }

        public Builder endcard_close_image(Integer num) {
            this.endcard_close_image = num;
            return this;
        }

        public Builder endcard_close_position(Integer num) {
            this.endcard_close_position = num;
            return this;
        }

        public Builder finished(Float f) {
            this.finished = f;
            return this;
        }

        public Builder full_click_on_video(Boolean bool) {
            this.full_click_on_video = bool;
            return this;
        }

        public Builder if_mute(Integer num) {
            this.if_mute = num;
            return this;
        }

        public Builder invisible_ad_label(Boolean bool) {
            this.invisible_ad_label = bool;
            return this;
        }

        public Builder mute_postion(Integer num) {
            this.mute_postion = num;
            return this;
        }

        public Builder skip_percent(Integer num) {
            this.skip_percent = num;
            return this;
        }

        public Builder skip_seconds(Integer num) {
            this.skip_seconds = num;
            return this;
        }

        public Builder video_close_position(Integer num) {
            this.video_close_position = num;
            return this;
        }
    }

    private static final class ProtoAdapter_RvAdSetting extends ProtoAdapter<RvAdSetting> {
        public ProtoAdapter_RvAdSetting() {
            super(FieldEncoding.LENGTH_DELIMITED, RvAdSetting.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public RvAdSetting decode(ProtoReader protoReader) {
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
                        builder.if_mute(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 2:
                        builder.finished(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 3:
                        builder.video_close_position(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 4:
                        builder.endcard_close_position(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 5:
                        builder.mute_postion(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 6:
                        builder.skip_percent(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 7:
                        builder.skip_seconds(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 8:
                        builder.enable_exit_on_video_close(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 9:
                        builder.full_click_on_video(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 10:
                        builder.click_setting(ClickAreaSetting.ADAPTER.decode(protoReader));
                        break;
                    case 11:
                        builder.invisible_ad_label(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 12:
                        builder.end_time(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 13:
                        builder.endcard_close_image(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 14:
                        builder.end_impression_time(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    case 15:
                        builder.disable_auto_load(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, RvAdSetting rvAdSetting) {
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 1, rvAdSetting.if_mute);
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, rvAdSetting.finished);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, rvAdSetting.video_close_position);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 4, rvAdSetting.endcard_close_position);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 5, rvAdSetting.mute_postion);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 6, rvAdSetting.skip_percent);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 7, rvAdSetting.skip_seconds);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 8, rvAdSetting.enable_exit_on_video_close);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 9, rvAdSetting.full_click_on_video);
            ClickAreaSetting.ADAPTER.encodeWithTag(protoWriter, 10, rvAdSetting.click_setting);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 11, rvAdSetting.invisible_ad_label);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 12, rvAdSetting.end_time);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 13, rvAdSetting.endcard_close_image);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 14, rvAdSetting.end_impression_time);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 15, rvAdSetting.disable_auto_load);
            protoWriter.writeBytes(rvAdSetting.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(RvAdSetting rvAdSetting) {
            return ProtoAdapter.INT32.encodedSizeWithTag(1, rvAdSetting.if_mute) + ProtoAdapter.FLOAT.encodedSizeWithTag(2, rvAdSetting.finished) + ProtoAdapter.INT32.encodedSizeWithTag(3, rvAdSetting.video_close_position) + ProtoAdapter.INT32.encodedSizeWithTag(4, rvAdSetting.endcard_close_position) + ProtoAdapter.INT32.encodedSizeWithTag(5, rvAdSetting.mute_postion) + ProtoAdapter.INT32.encodedSizeWithTag(6, rvAdSetting.skip_percent) + ProtoAdapter.INT32.encodedSizeWithTag(7, rvAdSetting.skip_seconds) + ProtoAdapter.BOOL.encodedSizeWithTag(8, rvAdSetting.enable_exit_on_video_close) + ProtoAdapter.BOOL.encodedSizeWithTag(9, rvAdSetting.full_click_on_video) + ClickAreaSetting.ADAPTER.encodedSizeWithTag(10, rvAdSetting.click_setting) + ProtoAdapter.BOOL.encodedSizeWithTag(11, rvAdSetting.invisible_ad_label) + ProtoAdapter.INT32.encodedSizeWithTag(12, rvAdSetting.end_time) + ProtoAdapter.INT32.encodedSizeWithTag(13, rvAdSetting.endcard_close_image) + ProtoAdapter.INT32.encodedSizeWithTag(14, rvAdSetting.end_impression_time) + ProtoAdapter.BOOL.encodedSizeWithTag(15, rvAdSetting.disable_auto_load) + rvAdSetting.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public RvAdSetting redact(RvAdSetting rvAdSetting) {
            Builder builderNewBuilder = rvAdSetting.newBuilder();
            if (builderNewBuilder.click_setting != null) {
                builderNewBuilder.click_setting = ClickAreaSetting.ADAPTER.redact(builderNewBuilder.click_setting);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public RvAdSetting(Integer num, Float f, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Boolean bool, Boolean bool2, ClickAreaSetting clickAreaSetting, Boolean bool3, Integer num7, Integer num8, Integer num9, Boolean bool4) {
        this(num, f, num2, num3, num4, num5, num6, bool, bool2, clickAreaSetting, bool3, num7, num8, num9, bool4, ByteString.EMPTY);
    }

    public RvAdSetting(Integer num, Float f, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Boolean bool, Boolean bool2, ClickAreaSetting clickAreaSetting, Boolean bool3, Integer num7, Integer num8, Integer num9, Boolean bool4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.if_mute = num;
        this.finished = f;
        this.video_close_position = num2;
        this.endcard_close_position = num3;
        this.mute_postion = num4;
        this.skip_percent = num5;
        this.skip_seconds = num6;
        this.enable_exit_on_video_close = bool;
        this.full_click_on_video = bool2;
        this.click_setting = clickAreaSetting;
        this.invisible_ad_label = bool3;
        this.end_time = num7;
        this.endcard_close_image = num8;
        this.end_impression_time = num9;
        this.disable_auto_load = bool4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RvAdSetting)) {
            return false;
        }
        RvAdSetting rvAdSetting = (RvAdSetting) obj;
        return unknownFields().equals(rvAdSetting.unknownFields()) && Internal.equals(this.if_mute, rvAdSetting.if_mute) && Internal.equals(this.finished, rvAdSetting.finished) && Internal.equals(this.video_close_position, rvAdSetting.video_close_position) && Internal.equals(this.endcard_close_position, rvAdSetting.endcard_close_position) && Internal.equals(this.mute_postion, rvAdSetting.mute_postion) && Internal.equals(this.skip_percent, rvAdSetting.skip_percent) && Internal.equals(this.skip_seconds, rvAdSetting.skip_seconds) && Internal.equals(this.enable_exit_on_video_close, rvAdSetting.enable_exit_on_video_close) && Internal.equals(this.full_click_on_video, rvAdSetting.full_click_on_video) && Internal.equals(this.click_setting, rvAdSetting.click_setting) && Internal.equals(this.invisible_ad_label, rvAdSetting.invisible_ad_label) && Internal.equals(this.end_time, rvAdSetting.end_time) && Internal.equals(this.endcard_close_image, rvAdSetting.endcard_close_image) && Internal.equals(this.end_impression_time, rvAdSetting.end_impression_time) && Internal.equals(this.disable_auto_load, rvAdSetting.disable_auto_load);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.if_mute != null ? this.if_mute.hashCode() : 0)) * 37) + (this.finished != null ? this.finished.hashCode() : 0)) * 37) + (this.video_close_position != null ? this.video_close_position.hashCode() : 0)) * 37) + (this.endcard_close_position != null ? this.endcard_close_position.hashCode() : 0)) * 37) + (this.mute_postion != null ? this.mute_postion.hashCode() : 0)) * 37) + (this.skip_percent != null ? this.skip_percent.hashCode() : 0)) * 37) + (this.skip_seconds != null ? this.skip_seconds.hashCode() : 0)) * 37) + (this.enable_exit_on_video_close != null ? this.enable_exit_on_video_close.hashCode() : 0)) * 37) + (this.full_click_on_video != null ? this.full_click_on_video.hashCode() : 0)) * 37) + (this.click_setting != null ? this.click_setting.hashCode() : 0)) * 37) + (this.invisible_ad_label != null ? this.invisible_ad_label.hashCode() : 0)) * 37) + (this.end_time != null ? this.end_time.hashCode() : 0)) * 37) + (this.endcard_close_image != null ? this.endcard_close_image.hashCode() : 0)) * 37) + (this.end_impression_time != null ? this.end_impression_time.hashCode() : 0)) * 37) + (this.disable_auto_load != null ? this.disable_auto_load.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.if_mute = this.if_mute;
        builder.finished = this.finished;
        builder.video_close_position = this.video_close_position;
        builder.endcard_close_position = this.endcard_close_position;
        builder.mute_postion = this.mute_postion;
        builder.skip_percent = this.skip_percent;
        builder.skip_seconds = this.skip_seconds;
        builder.enable_exit_on_video_close = this.enable_exit_on_video_close;
        builder.full_click_on_video = this.full_click_on_video;
        builder.click_setting = this.click_setting;
        builder.invisible_ad_label = this.invisible_ad_label;
        builder.end_time = this.end_time;
        builder.endcard_close_image = this.endcard_close_image;
        builder.end_impression_time = this.end_impression_time;
        builder.disable_auto_load = this.disable_auto_load;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.if_mute != null) {
            sb.append(", if_mute=");
            sb.append(this.if_mute);
        }
        if (this.finished != null) {
            sb.append(", finished=");
            sb.append(this.finished);
        }
        if (this.video_close_position != null) {
            sb.append(", video_close_position=");
            sb.append(this.video_close_position);
        }
        if (this.endcard_close_position != null) {
            sb.append(", endcard_close_position=");
            sb.append(this.endcard_close_position);
        }
        if (this.mute_postion != null) {
            sb.append(", mute_postion=");
            sb.append(this.mute_postion);
        }
        if (this.skip_percent != null) {
            sb.append(", skip_percent=");
            sb.append(this.skip_percent);
        }
        if (this.skip_seconds != null) {
            sb.append(", skip_seconds=");
            sb.append(this.skip_seconds);
        }
        if (this.enable_exit_on_video_close != null) {
            sb.append(", enable_exit_on_video_close=");
            sb.append(this.enable_exit_on_video_close);
        }
        if (this.full_click_on_video != null) {
            sb.append(", full_click_on_video=");
            sb.append(this.full_click_on_video);
        }
        if (this.click_setting != null) {
            sb.append(", click_setting=");
            sb.append(this.click_setting);
        }
        if (this.invisible_ad_label != null) {
            sb.append(", invisible_ad_label=");
            sb.append(this.invisible_ad_label);
        }
        if (this.end_time != null) {
            sb.append(", end_time=");
            sb.append(this.end_time);
        }
        if (this.endcard_close_image != null) {
            sb.append(", endcard_close_image=");
            sb.append(this.endcard_close_image);
        }
        if (this.end_impression_time != null) {
            sb.append(", end_impression_time=");
            sb.append(this.end_impression_time);
        }
        if (this.disable_auto_load != null) {
            sb.append(", disable_auto_load=");
            sb.append(this.disable_auto_load);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "RvAdSetting{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
