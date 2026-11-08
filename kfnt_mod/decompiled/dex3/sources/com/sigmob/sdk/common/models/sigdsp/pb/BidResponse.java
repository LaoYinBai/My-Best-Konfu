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
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class BidResponse extends AndroidMessage<BidResponse, Builder> {
    public static final String DEFAULT_AD_UA = "";
    public static final String DEFAULT_PCTR_VERSION = "";
    public static final String DEFAULT_REQUEST_ID = "";
    public static final String DEFAULT_UID = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String ad_ua;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Ad#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<Ad> ads;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 3)
    public final Long error_code;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 9)
    public final Integer expiration_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 6)
    public final Integer pctr_valid_check;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String pctr_version;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 4)
    public final Long process_time_ms_dsp;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 5)
    public final Long process_time_ms_ssp;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String request_id;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.SlotAdSetting#ADAPTER", tag = 11)
    public final SlotAdSetting slot_ad_setting;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String uid;
    public static final ProtoAdapter<BidResponse> ADAPTER = new ProtoAdapter_BidResponse();
    public static final Parcelable.Creator<BidResponse> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Long DEFAULT_ERROR_CODE = 0L;
    public static final Long DEFAULT_PROCESS_TIME_MS_DSP = 0L;
    public static final Long DEFAULT_PROCESS_TIME_MS_SSP = 0L;
    public static final Integer DEFAULT_PCTR_VALID_CHECK = 0;
    public static final Integer DEFAULT_EXPIRATION_TIME = 0;

    public static final class Builder extends Message.Builder<BidResponse, Builder> {
        public String ad_ua;
        public List<Ad> ads = Internal.newMutableList();
        public Long error_code;
        public Integer expiration_time;
        public Integer pctr_valid_check;
        public String pctr_version;
        public Long process_time_ms_dsp;
        public Long process_time_ms_ssp;
        public String request_id;
        public SlotAdSetting slot_ad_setting;
        public String uid;

        public Builder ad_ua(String str) {
            this.ad_ua = str;
            return this;
        }

        public Builder ads(List<Ad> list) {
            Internal.checkElementsNotNull(list);
            this.ads = list;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public BidResponse build() {
            return new BidResponse(this.request_id, this.ads, this.error_code, this.process_time_ms_dsp, this.process_time_ms_ssp, this.pctr_valid_check, this.pctr_version, this.uid, this.expiration_time, this.ad_ua, this.slot_ad_setting, super.buildUnknownFields());
        }

        public Builder error_code(Long l) {
            this.error_code = l;
            return this;
        }

        public Builder expiration_time(Integer num) {
            this.expiration_time = num;
            return this;
        }

        public Builder pctr_valid_check(Integer num) {
            this.pctr_valid_check = num;
            return this;
        }

        public Builder pctr_version(String str) {
            this.pctr_version = str;
            return this;
        }

        public Builder process_time_ms_dsp(Long l) {
            this.process_time_ms_dsp = l;
            return this;
        }

        public Builder process_time_ms_ssp(Long l) {
            this.process_time_ms_ssp = l;
            return this;
        }

        public Builder request_id(String str) {
            this.request_id = str;
            return this;
        }

        public Builder slot_ad_setting(SlotAdSetting slotAdSetting) {
            this.slot_ad_setting = slotAdSetting;
            return this;
        }

        public Builder uid(String str) {
            this.uid = str;
            return this;
        }
    }

    private static final class ProtoAdapter_BidResponse extends ProtoAdapter<BidResponse> {
        public ProtoAdapter_BidResponse() {
            super(FieldEncoding.LENGTH_DELIMITED, BidResponse.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public BidResponse decode(ProtoReader protoReader) {
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
                        builder.request_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.ads.add(Ad.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        builder.error_code(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 4:
                        builder.process_time_ms_dsp(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 5:
                        builder.process_time_ms_ssp(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 6:
                        builder.pctr_valid_check(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 7:
                        builder.pctr_version(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 8:
                        builder.uid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 9:
                        builder.expiration_time(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 10:
                        builder.ad_ua(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 11:
                        builder.slot_ad_setting(SlotAdSetting.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, BidResponse bidResponse) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, bidResponse.request_id);
            Ad.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, bidResponse.ads);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 3, bidResponse.error_code);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 4, bidResponse.process_time_ms_dsp);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 5, bidResponse.process_time_ms_ssp);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 6, bidResponse.pctr_valid_check);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, bidResponse.pctr_version);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, bidResponse.uid);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 9, bidResponse.expiration_time);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, bidResponse.ad_ua);
            SlotAdSetting.ADAPTER.encodeWithTag(protoWriter, 11, bidResponse.slot_ad_setting);
            protoWriter.writeBytes(bidResponse.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(BidResponse bidResponse) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, bidResponse.request_id) + Ad.ADAPTER.asRepeated().encodedSizeWithTag(2, bidResponse.ads) + ProtoAdapter.UINT64.encodedSizeWithTag(3, bidResponse.error_code) + ProtoAdapter.UINT64.encodedSizeWithTag(4, bidResponse.process_time_ms_dsp) + ProtoAdapter.UINT64.encodedSizeWithTag(5, bidResponse.process_time_ms_ssp) + ProtoAdapter.UINT32.encodedSizeWithTag(6, bidResponse.pctr_valid_check) + ProtoAdapter.STRING.encodedSizeWithTag(7, bidResponse.pctr_version) + ProtoAdapter.STRING.encodedSizeWithTag(8, bidResponse.uid) + ProtoAdapter.UINT32.encodedSizeWithTag(9, bidResponse.expiration_time) + ProtoAdapter.STRING.encodedSizeWithTag(10, bidResponse.ad_ua) + SlotAdSetting.ADAPTER.encodedSizeWithTag(11, bidResponse.slot_ad_setting) + bidResponse.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public BidResponse redact(BidResponse bidResponse) {
            Builder builderNewBuilder = bidResponse.newBuilder();
            Internal.redactElements(builderNewBuilder.ads, Ad.ADAPTER);
            if (builderNewBuilder.slot_ad_setting != null) {
                builderNewBuilder.slot_ad_setting = SlotAdSetting.ADAPTER.redact(builderNewBuilder.slot_ad_setting);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public BidResponse(String str, List<Ad> list, Long l, Long l2, Long l3, Integer num, String str2, String str3, Integer num2, String str4, SlotAdSetting slotAdSetting) {
        this(str, list, l, l2, l3, num, str2, str3, num2, str4, slotAdSetting, ByteString.EMPTY);
    }

    public BidResponse(String str, List<Ad> list, Long l, Long l2, Long l3, Integer num, String str2, String str3, Integer num2, String str4, SlotAdSetting slotAdSetting, ByteString byteString) {
        super(ADAPTER, byteString);
        this.request_id = str;
        this.ads = Internal.immutableCopyOf("ads", list);
        this.error_code = l;
        this.process_time_ms_dsp = l2;
        this.process_time_ms_ssp = l3;
        this.pctr_valid_check = num;
        this.pctr_version = str2;
        this.uid = str3;
        this.expiration_time = num2;
        this.ad_ua = str4;
        this.slot_ad_setting = slotAdSetting;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BidResponse)) {
            return false;
        }
        BidResponse bidResponse = (BidResponse) obj;
        return unknownFields().equals(bidResponse.unknownFields()) && Internal.equals(this.request_id, bidResponse.request_id) && this.ads.equals(bidResponse.ads) && Internal.equals(this.error_code, bidResponse.error_code) && Internal.equals(this.process_time_ms_dsp, bidResponse.process_time_ms_dsp) && Internal.equals(this.process_time_ms_ssp, bidResponse.process_time_ms_ssp) && Internal.equals(this.pctr_valid_check, bidResponse.pctr_valid_check) && Internal.equals(this.pctr_version, bidResponse.pctr_version) && Internal.equals(this.uid, bidResponse.uid) && Internal.equals(this.expiration_time, bidResponse.expiration_time) && Internal.equals(this.ad_ua, bidResponse.ad_ua) && Internal.equals(this.slot_ad_setting, bidResponse.slot_ad_setting);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((unknownFields().hashCode() * 37) + (this.request_id != null ? this.request_id.hashCode() : 0)) * 37) + this.ads.hashCode()) * 37) + (this.error_code != null ? this.error_code.hashCode() : 0)) * 37) + (this.process_time_ms_dsp != null ? this.process_time_ms_dsp.hashCode() : 0)) * 37) + (this.process_time_ms_ssp != null ? this.process_time_ms_ssp.hashCode() : 0)) * 37) + (this.pctr_valid_check != null ? this.pctr_valid_check.hashCode() : 0)) * 37) + (this.pctr_version != null ? this.pctr_version.hashCode() : 0)) * 37) + (this.uid != null ? this.uid.hashCode() : 0)) * 37) + (this.expiration_time != null ? this.expiration_time.hashCode() : 0)) * 37) + (this.ad_ua != null ? this.ad_ua.hashCode() : 0)) * 37) + (this.slot_ad_setting != null ? this.slot_ad_setting.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.request_id = this.request_id;
        builder.ads = Internal.copyOf("ads", this.ads);
        builder.error_code = this.error_code;
        builder.process_time_ms_dsp = this.process_time_ms_dsp;
        builder.process_time_ms_ssp = this.process_time_ms_ssp;
        builder.pctr_valid_check = this.pctr_valid_check;
        builder.pctr_version = this.pctr_version;
        builder.uid = this.uid;
        builder.expiration_time = this.expiration_time;
        builder.ad_ua = this.ad_ua;
        builder.slot_ad_setting = this.slot_ad_setting;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.request_id != null) {
            sb.append(", request_id=");
            sb.append(this.request_id);
        }
        if (!this.ads.isEmpty()) {
            sb.append(", ads=");
            sb.append(this.ads);
        }
        if (this.error_code != null) {
            sb.append(", error_code=");
            sb.append(this.error_code);
        }
        if (this.process_time_ms_dsp != null) {
            sb.append(", process_time_ms_dsp=");
            sb.append(this.process_time_ms_dsp);
        }
        if (this.process_time_ms_ssp != null) {
            sb.append(", process_time_ms_ssp=");
            sb.append(this.process_time_ms_ssp);
        }
        if (this.pctr_valid_check != null) {
            sb.append(", pctr_valid_check=");
            sb.append(this.pctr_valid_check);
        }
        if (this.pctr_version != null) {
            sb.append(", pctr_version=");
            sb.append(this.pctr_version);
        }
        if (this.uid != null) {
            sb.append(", uid=");
            sb.append(this.uid);
        }
        if (this.expiration_time != null) {
            sb.append(", expiration_time=");
            sb.append(this.expiration_time);
        }
        if (this.ad_ua != null) {
            sb.append(", ad_ua=");
            sb.append(this.ad_ua);
        }
        if (this.slot_ad_setting != null) {
            sb.append(", slot_ad_setting=");
            sb.append(this.slot_ad_setting);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "BidResponse{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
