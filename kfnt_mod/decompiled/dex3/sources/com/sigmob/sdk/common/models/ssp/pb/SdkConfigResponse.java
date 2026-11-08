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
public final class SdkConfigResponse extends AndroidMessage<SdkConfigResponse, Builder> {
    public static final ProtoAdapter<SdkConfigResponse> ADAPTER = new ProtoAdapter_SdkConfigResponse();
    public static final Parcelable.Creator<SdkConfigResponse> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CODE = 0;
    public static final String DEFAULT_ERROR_MESSAGE = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer code;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.SdkConfig#ADAPTER", tag = 3)
    public final SdkConfig config;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.SdkConfigV2#ADAPTER", tag = 4)
    public final SdkConfigV2 config_v2;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String error_message;

    public static final class Builder extends Message.Builder<SdkConfigResponse, Builder> {
        public SdkConfig config;
        public SdkConfigV2 config_v2;
        public Integer code = SdkConfigResponse.DEFAULT_CODE;
        public String error_message = "";

        @Override // com.sigmob.wire.Message.Builder
        public SdkConfigResponse build() {
            return new SdkConfigResponse(this.code, this.error_message, this.config, this.config_v2, super.buildUnknownFields());
        }

        public Builder code(Integer num) {
            this.code = num;
            return this;
        }

        public Builder config(SdkConfig sdkConfig) {
            this.config = sdkConfig;
            return this;
        }

        public Builder config_v2(SdkConfigV2 sdkConfigV2) {
            this.config_v2 = sdkConfigV2;
            return this;
        }

        public Builder error_message(String str) {
            this.error_message = str;
            return this;
        }
    }

    private static final class ProtoAdapter_SdkConfigResponse extends ProtoAdapter<SdkConfigResponse> {
        public ProtoAdapter_SdkConfigResponse() {
            super(FieldEncoding.LENGTH_DELIMITED, SdkConfigResponse.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SdkConfigResponse decode(ProtoReader protoReader) {
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
                        builder.code(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.error_message(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.config(SdkConfig.ADAPTER.decode(protoReader));
                        break;
                    case 4:
                        builder.config_v2(SdkConfigV2.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SdkConfigResponse sdkConfigResponse) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, sdkConfigResponse.code);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, sdkConfigResponse.error_message);
            SdkConfig.ADAPTER.encodeWithTag(protoWriter, 3, sdkConfigResponse.config);
            SdkConfigV2.ADAPTER.encodeWithTag(protoWriter, 4, sdkConfigResponse.config_v2);
            protoWriter.writeBytes(sdkConfigResponse.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SdkConfigResponse sdkConfigResponse) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, sdkConfigResponse.code) + ProtoAdapter.STRING.encodedSizeWithTag(2, sdkConfigResponse.error_message) + SdkConfig.ADAPTER.encodedSizeWithTag(3, sdkConfigResponse.config) + SdkConfigV2.ADAPTER.encodedSizeWithTag(4, sdkConfigResponse.config_v2) + sdkConfigResponse.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SdkConfigResponse redact(SdkConfigResponse sdkConfigResponse) {
            Builder builderNewBuilder = sdkConfigResponse.newBuilder();
            if (builderNewBuilder.config != null) {
                builderNewBuilder.config = SdkConfig.ADAPTER.redact(builderNewBuilder.config);
            }
            if (builderNewBuilder.config_v2 != null) {
                builderNewBuilder.config_v2 = SdkConfigV2.ADAPTER.redact(builderNewBuilder.config_v2);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SdkConfigResponse(Integer num, String str, SdkConfig sdkConfig, SdkConfigV2 sdkConfigV2) {
        this(num, str, sdkConfig, sdkConfigV2, ByteString.EMPTY);
    }

    public SdkConfigResponse(Integer num, String str, SdkConfig sdkConfig, SdkConfigV2 sdkConfigV2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.code = num;
        this.error_message = str;
        this.config = sdkConfig;
        this.config_v2 = sdkConfigV2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SdkConfigResponse)) {
            return false;
        }
        SdkConfigResponse sdkConfigResponse = (SdkConfigResponse) obj;
        return unknownFields().equals(sdkConfigResponse.unknownFields()) && Internal.equals(this.code, sdkConfigResponse.code) && Internal.equals(this.error_message, sdkConfigResponse.error_message) && Internal.equals(this.config, sdkConfigResponse.config) && Internal.equals(this.config_v2, sdkConfigResponse.config_v2);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.code != null ? this.code.hashCode() : 0)) * 37) + (this.error_message != null ? this.error_message.hashCode() : 0)) * 37) + (this.config != null ? this.config.hashCode() : 0)) * 37) + (this.config_v2 != null ? this.config_v2.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.code = this.code;
        builder.error_message = this.error_message;
        builder.config = this.config;
        builder.config_v2 = this.config_v2;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.code != null) {
            sb.append(", code=");
            sb.append(this.code);
        }
        if (this.error_message != null) {
            sb.append(", error_message=");
            sb.append(this.error_message);
        }
        if (this.config != null) {
            sb.append(", config=");
            sb.append(this.config);
        }
        if (this.config_v2 != null) {
            sb.append(", config_v2=");
            sb.append(this.config_v2);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SdkConfigResponse{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
