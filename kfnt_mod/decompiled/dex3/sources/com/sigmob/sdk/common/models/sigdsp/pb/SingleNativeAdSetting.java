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
public final class SingleNativeAdSetting extends AndroidMessage<SingleNativeAdSetting, Builder> {
    public static final ProtoAdapter<SingleNativeAdSetting> ADAPTER = new ProtoAdapter_SingleNativeAdSetting();
    public static final Parcelable.Creator<SingleNativeAdSetting> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Boolean DEFAULT_USE_NA_VIDEO_COMPONENT = false;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 1)
    public final Boolean use_na_video_component;

    public static final class Builder extends Message.Builder<SingleNativeAdSetting, Builder> {
        public Boolean use_na_video_component = SingleNativeAdSetting.DEFAULT_USE_NA_VIDEO_COMPONENT;

        @Override // com.sigmob.wire.Message.Builder
        public SingleNativeAdSetting build() {
            return new SingleNativeAdSetting(this.use_na_video_component, super.buildUnknownFields());
        }

        public Builder use_na_video_component(Boolean bool) {
            this.use_na_video_component = bool;
            return this;
        }
    }

    private static final class ProtoAdapter_SingleNativeAdSetting extends ProtoAdapter<SingleNativeAdSetting> {
        public ProtoAdapter_SingleNativeAdSetting() {
            super(FieldEncoding.LENGTH_DELIMITED, SingleNativeAdSetting.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SingleNativeAdSetting decode(ProtoReader protoReader) {
            Builder builder = new Builder();
            long jBeginMessage = protoReader.beginMessage();
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    return builder.build();
                }
                if (iNextTag != 1) {
                    FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                    builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                } else {
                    builder.use_na_video_component(ProtoAdapter.BOOL.decode(protoReader));
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SingleNativeAdSetting singleNativeAdSetting) {
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 1, singleNativeAdSetting.use_na_video_component);
            protoWriter.writeBytes(singleNativeAdSetting.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SingleNativeAdSetting singleNativeAdSetting) {
            return ProtoAdapter.BOOL.encodedSizeWithTag(1, singleNativeAdSetting.use_na_video_component) + singleNativeAdSetting.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SingleNativeAdSetting redact(SingleNativeAdSetting singleNativeAdSetting) {
            Builder builderNewBuilder = singleNativeAdSetting.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SingleNativeAdSetting(Boolean bool) {
        this(bool, ByteString.EMPTY);
    }

    public SingleNativeAdSetting(Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.use_na_video_component = bool;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SingleNativeAdSetting)) {
            return false;
        }
        SingleNativeAdSetting singleNativeAdSetting = (SingleNativeAdSetting) obj;
        return unknownFields().equals(singleNativeAdSetting.unknownFields()) && Internal.equals(this.use_na_video_component, singleNativeAdSetting.use_na_video_component);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (unknownFields().hashCode() * 37) + (this.use_na_video_component != null ? this.use_na_video_component.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.use_na_video_component = this.use_na_video_component;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.use_na_video_component != null) {
            sb.append(", use_na_video_component=");
            sb.append(this.use_na_video_component);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SingleNativeAdSetting{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
