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
public final class SdkConfigV2 extends AndroidMessage<SdkConfigV2, Builder> {
    public static final ProtoAdapter<SdkConfigV2> ADAPTER = new ProtoAdapter_SdkConfigV2();
    public static final Parcelable.Creator<SdkConfigV2> CREATOR = AndroidMessage.newCreator(ADAPTER);
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.Android#ADAPTER", tag = 3)
    public final Android android_config;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.Common#ADAPTER", tag = 1)
    public final Common common_config;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.IOS#ADAPTER", tag = 2)
    public final IOS ios_config;

    public static final class Builder extends Message.Builder<SdkConfigV2, Builder> {
        public Android android_config;
        public Common common_config;
        public IOS ios_config;

        public Builder android_config(Android android) {
            this.android_config = android;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public SdkConfigV2 build() {
            return new SdkConfigV2(this.common_config, this.ios_config, this.android_config, super.buildUnknownFields());
        }

        public Builder common_config(Common common) {
            this.common_config = common;
            return this;
        }

        public Builder ios_config(IOS ios) {
            this.ios_config = ios;
            return this;
        }
    }

    private static final class ProtoAdapter_SdkConfigV2 extends ProtoAdapter<SdkConfigV2> {
        public ProtoAdapter_SdkConfigV2() {
            super(FieldEncoding.LENGTH_DELIMITED, SdkConfigV2.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SdkConfigV2 decode(ProtoReader protoReader) {
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
                        builder.common_config(Common.ADAPTER.decode(protoReader));
                        break;
                    case 2:
                        builder.ios_config(IOS.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        builder.android_config(Android.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SdkConfigV2 sdkConfigV2) {
            Common.ADAPTER.encodeWithTag(protoWriter, 1, sdkConfigV2.common_config);
            IOS.ADAPTER.encodeWithTag(protoWriter, 2, sdkConfigV2.ios_config);
            Android.ADAPTER.encodeWithTag(protoWriter, 3, sdkConfigV2.android_config);
            protoWriter.writeBytes(sdkConfigV2.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SdkConfigV2 sdkConfigV2) {
            return Common.ADAPTER.encodedSizeWithTag(1, sdkConfigV2.common_config) + IOS.ADAPTER.encodedSizeWithTag(2, sdkConfigV2.ios_config) + Android.ADAPTER.encodedSizeWithTag(3, sdkConfigV2.android_config) + sdkConfigV2.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SdkConfigV2 redact(SdkConfigV2 sdkConfigV2) {
            Builder builderNewBuilder = sdkConfigV2.newBuilder();
            if (builderNewBuilder.common_config != null) {
                builderNewBuilder.common_config = Common.ADAPTER.redact(builderNewBuilder.common_config);
            }
            if (builderNewBuilder.ios_config != null) {
                builderNewBuilder.ios_config = IOS.ADAPTER.redact(builderNewBuilder.ios_config);
            }
            if (builderNewBuilder.android_config != null) {
                builderNewBuilder.android_config = Android.ADAPTER.redact(builderNewBuilder.android_config);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SdkConfigV2(Common common, IOS ios, Android android) {
        this(common, ios, android, ByteString.EMPTY);
    }

    public SdkConfigV2(Common common, IOS ios, Android android, ByteString byteString) {
        super(ADAPTER, byteString);
        this.common_config = common;
        this.ios_config = ios;
        this.android_config = android;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SdkConfigV2)) {
            return false;
        }
        SdkConfigV2 sdkConfigV2 = (SdkConfigV2) obj;
        return unknownFields().equals(sdkConfigV2.unknownFields()) && Internal.equals(this.common_config, sdkConfigV2.common_config) && Internal.equals(this.ios_config, sdkConfigV2.ios_config) && Internal.equals(this.android_config, sdkConfigV2.android_config);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.common_config != null ? this.common_config.hashCode() : 0)) * 37) + (this.ios_config != null ? this.ios_config.hashCode() : 0)) * 37) + (this.android_config != null ? this.android_config.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.common_config = this.common_config;
        builder.ios_config = this.ios_config;
        builder.android_config = this.android_config;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.common_config != null) {
            sb.append(", common_config=");
            sb.append(this.common_config);
        }
        if (this.ios_config != null) {
            sb.append(", ios_config=");
            sb.append(this.ios_config);
        }
        if (this.android_config != null) {
            sb.append(", android_config=");
            sb.append(this.android_config);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SdkConfigV2{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
