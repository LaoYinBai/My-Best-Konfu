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
public final class NativeAdSetting extends AndroidMessage<NativeAdSetting, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer detail_page_video_mute;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 6)
    public final Integer end_impression_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 4)
    public final Integer impression_percent;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 5)
    public final Integer impression_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer preview_page_video_mute;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer video_auto_play;
    public static final ProtoAdapter<NativeAdSetting> ADAPTER = new ProtoAdapter_NativeAdSetting();
    public static final Parcelable.Creator<NativeAdSetting> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_VIDEO_AUTO_PLAY = 0;
    public static final Integer DEFAULT_PREVIEW_PAGE_VIDEO_MUTE = 0;
    public static final Integer DEFAULT_DETAIL_PAGE_VIDEO_MUTE = 0;
    public static final Integer DEFAULT_IMPRESSION_PERCENT = 0;
    public static final Integer DEFAULT_IMPRESSION_TIME = 0;
    public static final Integer DEFAULT_END_IMPRESSION_TIME = 0;

    public static final class Builder extends Message.Builder<NativeAdSetting, Builder> {
        public Integer video_auto_play = NativeAdSetting.DEFAULT_VIDEO_AUTO_PLAY;
        public Integer preview_page_video_mute = NativeAdSetting.DEFAULT_PREVIEW_PAGE_VIDEO_MUTE;
        public Integer detail_page_video_mute = NativeAdSetting.DEFAULT_DETAIL_PAGE_VIDEO_MUTE;
        public Integer impression_percent = NativeAdSetting.DEFAULT_IMPRESSION_PERCENT;
        public Integer impression_time = NativeAdSetting.DEFAULT_IMPRESSION_TIME;
        public Integer end_impression_time = NativeAdSetting.DEFAULT_END_IMPRESSION_TIME;

        @Override // com.sigmob.wire.Message.Builder
        public NativeAdSetting build() {
            return new NativeAdSetting(this.video_auto_play, this.preview_page_video_mute, this.detail_page_video_mute, this.impression_percent, this.impression_time, this.end_impression_time, super.buildUnknownFields());
        }

        public Builder detail_page_video_mute(Integer num) {
            this.detail_page_video_mute = num;
            return this;
        }

        public Builder end_impression_time(Integer num) {
            this.end_impression_time = num;
            return this;
        }

        public Builder impression_percent(Integer num) {
            this.impression_percent = num;
            return this;
        }

        public Builder impression_time(Integer num) {
            this.impression_time = num;
            return this;
        }

        public Builder preview_page_video_mute(Integer num) {
            this.preview_page_video_mute = num;
            return this;
        }

        public Builder video_auto_play(Integer num) {
            this.video_auto_play = num;
            return this;
        }
    }

    private static final class ProtoAdapter_NativeAdSetting extends ProtoAdapter<NativeAdSetting> {
        public ProtoAdapter_NativeAdSetting() {
            super(FieldEncoding.LENGTH_DELIMITED, NativeAdSetting.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public NativeAdSetting decode(ProtoReader protoReader) {
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
                        builder.video_auto_play(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.preview_page_video_mute(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.detail_page_video_mute(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 4:
                        builder.impression_percent(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 5:
                        builder.impression_time(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 6:
                        builder.end_impression_time(ProtoAdapter.INT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, NativeAdSetting nativeAdSetting) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, nativeAdSetting.video_auto_play);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, nativeAdSetting.preview_page_video_mute);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, nativeAdSetting.detail_page_video_mute);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 4, nativeAdSetting.impression_percent);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 5, nativeAdSetting.impression_time);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 6, nativeAdSetting.end_impression_time);
            protoWriter.writeBytes(nativeAdSetting.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(NativeAdSetting nativeAdSetting) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, nativeAdSetting.video_auto_play) + ProtoAdapter.UINT32.encodedSizeWithTag(2, nativeAdSetting.preview_page_video_mute) + ProtoAdapter.UINT32.encodedSizeWithTag(3, nativeAdSetting.detail_page_video_mute) + ProtoAdapter.UINT32.encodedSizeWithTag(4, nativeAdSetting.impression_percent) + ProtoAdapter.UINT32.encodedSizeWithTag(5, nativeAdSetting.impression_time) + ProtoAdapter.INT32.encodedSizeWithTag(6, nativeAdSetting.end_impression_time) + nativeAdSetting.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public NativeAdSetting redact(NativeAdSetting nativeAdSetting) {
            Builder builderNewBuilder = nativeAdSetting.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public NativeAdSetting(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6) {
        this(num, num2, num3, num4, num5, num6, ByteString.EMPTY);
    }

    public NativeAdSetting(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, ByteString byteString) {
        super(ADAPTER, byteString);
        this.video_auto_play = num;
        this.preview_page_video_mute = num2;
        this.detail_page_video_mute = num3;
        this.impression_percent = num4;
        this.impression_time = num5;
        this.end_impression_time = num6;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NativeAdSetting)) {
            return false;
        }
        NativeAdSetting nativeAdSetting = (NativeAdSetting) obj;
        return unknownFields().equals(nativeAdSetting.unknownFields()) && Internal.equals(this.video_auto_play, nativeAdSetting.video_auto_play) && Internal.equals(this.preview_page_video_mute, nativeAdSetting.preview_page_video_mute) && Internal.equals(this.detail_page_video_mute, nativeAdSetting.detail_page_video_mute) && Internal.equals(this.impression_percent, nativeAdSetting.impression_percent) && Internal.equals(this.impression_time, nativeAdSetting.impression_time) && Internal.equals(this.end_impression_time, nativeAdSetting.end_impression_time);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((unknownFields().hashCode() * 37) + (this.video_auto_play != null ? this.video_auto_play.hashCode() : 0)) * 37) + (this.preview_page_video_mute != null ? this.preview_page_video_mute.hashCode() : 0)) * 37) + (this.detail_page_video_mute != null ? this.detail_page_video_mute.hashCode() : 0)) * 37) + (this.impression_percent != null ? this.impression_percent.hashCode() : 0)) * 37) + (this.impression_time != null ? this.impression_time.hashCode() : 0)) * 37) + (this.end_impression_time != null ? this.end_impression_time.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.video_auto_play = this.video_auto_play;
        builder.preview_page_video_mute = this.preview_page_video_mute;
        builder.detail_page_video_mute = this.detail_page_video_mute;
        builder.impression_percent = this.impression_percent;
        builder.impression_time = this.impression_time;
        builder.end_impression_time = this.end_impression_time;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.video_auto_play != null) {
            sb.append(", video_auto_play=");
            sb.append(this.video_auto_play);
        }
        if (this.preview_page_video_mute != null) {
            sb.append(", preview_page_video_mute=");
            sb.append(this.preview_page_video_mute);
        }
        if (this.detail_page_video_mute != null) {
            sb.append(", detail_page_video_mute=");
            sb.append(this.detail_page_video_mute);
        }
        if (this.impression_percent != null) {
            sb.append(", impression_percent=");
            sb.append(this.impression_percent);
        }
        if (this.impression_time != null) {
            sb.append(", impression_time=");
            sb.append(this.impression_time);
        }
        if (this.end_impression_time != null) {
            sb.append(", end_impression_time=");
            sb.append(this.end_impression_time);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "NativeAdSetting{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
