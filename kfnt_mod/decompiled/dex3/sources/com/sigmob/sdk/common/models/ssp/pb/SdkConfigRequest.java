package com.sigmob.sdk.common.models.ssp.pb;

import android.os.Parcelable;
import com.sigmob.sdk.common.models.sigdsp.pb.Device;
import com.sigmob.sdk.common.models.sigdsp.pb.Geo;
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
public final class SdkConfigRequest extends AndroidMessage<SdkConfigRequest, Builder> {
    public static final ProtoAdapter<SdkConfigRequest> ADAPTER = new ProtoAdapter_SdkConfigRequest();
    public static final Parcelable.Creator<SdkConfigRequest> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_ANDROID_ID = "";
    public static final String DEFAULT_COUNTRY = "";
    public static final String DEFAULT_GAID = "";
    public static final String DEFAULT_IDFA = "";
    public static final String DEFAULT_IDFV = "";
    public static final String DEFAULT_IMEI = "";
    public static final String DEFAULT_LANGUAGE = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String android_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String country;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Device#ADAPTER", tag = 9)
    public final Device device;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String gaid;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Geo#ADAPTER", tag = 5)
    public final Geo geo;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String idfa;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String idfv;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String imei;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String language;

    public static final class Builder extends Message.Builder<SdkConfigRequest, Builder> {
        public String android_id;
        public String country;
        public Device device;
        public String gaid;
        public Geo geo;
        public String idfa;
        public String idfv;
        public String imei;
        public String language;

        public Builder android_id(String str) {
            this.android_id = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public SdkConfigRequest build() {
            return new SdkConfigRequest(this.language, this.idfa, this.idfv, this.country, this.geo, this.android_id, this.imei, this.gaid, this.device, super.buildUnknownFields());
        }

        public Builder country(String str) {
            this.country = str;
            return this;
        }

        public Builder device(Device device) {
            this.device = device;
            return this;
        }

        public Builder gaid(String str) {
            this.gaid = str;
            return this;
        }

        public Builder geo(Geo geo) {
            this.geo = geo;
            return this;
        }

        public Builder idfa(String str) {
            this.idfa = str;
            return this;
        }

        public Builder idfv(String str) {
            this.idfv = str;
            return this;
        }

        public Builder imei(String str) {
            this.imei = str;
            return this;
        }

        public Builder language(String str) {
            this.language = str;
            return this;
        }
    }

    private static final class ProtoAdapter_SdkConfigRequest extends ProtoAdapter<SdkConfigRequest> {
        public ProtoAdapter_SdkConfigRequest() {
            super(FieldEncoding.LENGTH_DELIMITED, SdkConfigRequest.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SdkConfigRequest decode(ProtoReader protoReader) {
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
                        builder.language(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.idfa(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.idfv(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 4:
                        builder.country(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.geo(Geo.ADAPTER.decode(protoReader));
                        break;
                    case 6:
                        builder.android_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 7:
                        builder.imei(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 8:
                        builder.gaid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 9:
                        builder.device(Device.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SdkConfigRequest sdkConfigRequest) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, sdkConfigRequest.language);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, sdkConfigRequest.idfa);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, sdkConfigRequest.idfv);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, sdkConfigRequest.country);
            Geo.ADAPTER.encodeWithTag(protoWriter, 5, sdkConfigRequest.geo);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, sdkConfigRequest.android_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, sdkConfigRequest.imei);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, sdkConfigRequest.gaid);
            Device.ADAPTER.encodeWithTag(protoWriter, 9, sdkConfigRequest.device);
            protoWriter.writeBytes(sdkConfigRequest.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SdkConfigRequest sdkConfigRequest) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, sdkConfigRequest.language) + ProtoAdapter.STRING.encodedSizeWithTag(2, sdkConfigRequest.idfa) + ProtoAdapter.STRING.encodedSizeWithTag(3, sdkConfigRequest.idfv) + ProtoAdapter.STRING.encodedSizeWithTag(4, sdkConfigRequest.country) + Geo.ADAPTER.encodedSizeWithTag(5, sdkConfigRequest.geo) + ProtoAdapter.STRING.encodedSizeWithTag(6, sdkConfigRequest.android_id) + ProtoAdapter.STRING.encodedSizeWithTag(7, sdkConfigRequest.imei) + ProtoAdapter.STRING.encodedSizeWithTag(8, sdkConfigRequest.gaid) + Device.ADAPTER.encodedSizeWithTag(9, sdkConfigRequest.device) + sdkConfigRequest.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SdkConfigRequest redact(SdkConfigRequest sdkConfigRequest) {
            Builder builderNewBuilder = sdkConfigRequest.newBuilder();
            if (builderNewBuilder.geo != null) {
                builderNewBuilder.geo = Geo.ADAPTER.redact(builderNewBuilder.geo);
            }
            if (builderNewBuilder.device != null) {
                builderNewBuilder.device = Device.ADAPTER.redact(builderNewBuilder.device);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SdkConfigRequest(String str, String str2, String str3, String str4, Geo geo, String str5, String str6, String str7, Device device) {
        this(str, str2, str3, str4, geo, str5, str6, str7, device, ByteString.EMPTY);
    }

    public SdkConfigRequest(String str, String str2, String str3, String str4, Geo geo, String str5, String str6, String str7, Device device, ByteString byteString) {
        super(ADAPTER, byteString);
        this.language = str;
        this.idfa = str2;
        this.idfv = str3;
        this.country = str4;
        this.geo = geo;
        this.android_id = str5;
        this.imei = str6;
        this.gaid = str7;
        this.device = device;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SdkConfigRequest)) {
            return false;
        }
        SdkConfigRequest sdkConfigRequest = (SdkConfigRequest) obj;
        return unknownFields().equals(sdkConfigRequest.unknownFields()) && Internal.equals(this.language, sdkConfigRequest.language) && Internal.equals(this.idfa, sdkConfigRequest.idfa) && Internal.equals(this.idfv, sdkConfigRequest.idfv) && Internal.equals(this.country, sdkConfigRequest.country) && Internal.equals(this.geo, sdkConfigRequest.geo) && Internal.equals(this.android_id, sdkConfigRequest.android_id) && Internal.equals(this.imei, sdkConfigRequest.imei) && Internal.equals(this.gaid, sdkConfigRequest.gaid) && Internal.equals(this.device, sdkConfigRequest.device);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((unknownFields().hashCode() * 37) + (this.language != null ? this.language.hashCode() : 0)) * 37) + (this.idfa != null ? this.idfa.hashCode() : 0)) * 37) + (this.idfv != null ? this.idfv.hashCode() : 0)) * 37) + (this.country != null ? this.country.hashCode() : 0)) * 37) + (this.geo != null ? this.geo.hashCode() : 0)) * 37) + (this.android_id != null ? this.android_id.hashCode() : 0)) * 37) + (this.imei != null ? this.imei.hashCode() : 0)) * 37) + (this.gaid != null ? this.gaid.hashCode() : 0)) * 37) + (this.device != null ? this.device.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.language = this.language;
        builder.idfa = this.idfa;
        builder.idfv = this.idfv;
        builder.country = this.country;
        builder.geo = this.geo;
        builder.android_id = this.android_id;
        builder.imei = this.imei;
        builder.gaid = this.gaid;
        builder.device = this.device;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.language != null) {
            sb.append(", language=");
            sb.append(this.language);
        }
        if (this.idfa != null) {
            sb.append(", idfa=");
            sb.append(this.idfa);
        }
        if (this.idfv != null) {
            sb.append(", idfv=");
            sb.append(this.idfv);
        }
        if (this.country != null) {
            sb.append(", country=");
            sb.append(this.country);
        }
        if (this.geo != null) {
            sb.append(", geo=");
            sb.append(this.geo);
        }
        if (this.android_id != null) {
            sb.append(", android_id=");
            sb.append(this.android_id);
        }
        if (this.imei != null) {
            sb.append(", imei=");
            sb.append(this.imei);
        }
        if (this.gaid != null) {
            sb.append(", gaid=");
            sb.append(this.gaid);
        }
        if (this.device != null) {
            sb.append(", device=");
            sb.append(this.device);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SdkConfigRequest{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
