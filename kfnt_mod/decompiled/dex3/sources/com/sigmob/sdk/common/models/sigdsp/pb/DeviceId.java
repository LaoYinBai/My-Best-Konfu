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
public final class DeviceId extends AndroidMessage<DeviceId, Builder> {
    public static final ProtoAdapter<DeviceId> ADAPTER = new ProtoAdapter_DeviceId();
    public static final Parcelable.Creator<DeviceId> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_AAID = "";
    public static final String DEFAULT_ANDROID_ID = "";
    public static final String DEFAULT_ANDROID_ID_MD5 = "";
    public static final String DEFAULT_ANDROID_UUID = "";
    public static final String DEFAULT_BRAND = "";
    public static final String DEFAULT_GAID = "";
    public static final String DEFAULT_GAID_MD5 = "";
    public static final String DEFAULT_IDFA = "";
    public static final String DEFAULT_IDFA_MD5 = "";
    public static final String DEFAULT_IMEI = "";
    public static final String DEFAULT_IMEI1 = "";
    public static final String DEFAULT_IMEI1_MD5 = "";
    public static final String DEFAULT_IMEI2 = "";
    public static final String DEFAULT_IMEI2_MD5 = "";
    public static final String DEFAULT_IMEI_MD5 = "";
    public static final String DEFAULT_IMSI = "";
    public static final String DEFAULT_MSA_UDID = "";
    public static final String DEFAULT_OAID = "";
    public static final String DEFAULT_OAID_MD5 = "";
    public static final String DEFAULT_UDID = "";
    public static final String DEFAULT_UID = "";
    public static final String DEFAULT_USER_ID = "";
    public static final String DEFAULT_VAID = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 15)
    public final String aaid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String android_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 19)
    public final String android_id_md5;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String android_uuid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String brand;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String gaid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 20)
    public final String gaid_md5;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String idfa;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 17)
    public final String idfa_md5;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String imei;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String imei1;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 22)
    public final String imei1_md5;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String imei2;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 23)
    public final String imei2_md5;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 18)
    public final String imei_md5;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String imsi;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 16)
    public final String msa_udid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 13)
    public final String oaid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 21)
    public final String oaid_md5;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String udid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String uid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String user_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final String vaid;

    public static final class Builder extends Message.Builder<DeviceId, Builder> {
        public String aaid;
        public String android_id;
        public String android_id_md5;
        public String android_uuid;
        public String brand;
        public String gaid;
        public String gaid_md5;
        public String idfa;
        public String idfa_md5;
        public String imei;
        public String imei1;
        public String imei1_md5;
        public String imei2;
        public String imei2_md5;
        public String imei_md5;
        public String imsi;
        public String msa_udid;
        public String oaid;
        public String oaid_md5;
        public String udid;
        public String uid;
        public String user_id;
        public String vaid;

        public Builder aaid(String str) {
            this.aaid = str;
            return this;
        }

        public Builder android_id(String str) {
            this.android_id = str;
            return this;
        }

        public Builder android_id_md5(String str) {
            this.android_id_md5 = str;
            return this;
        }

        public Builder android_uuid(String str) {
            this.android_uuid = str;
            return this;
        }

        public Builder brand(String str) {
            this.brand = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public DeviceId build() {
            return new DeviceId(this.idfa, this.udid, this.user_id, this.imei, this.android_id, this.android_uuid, this.imsi, this.gaid, this.uid, this.brand, this.imei1, this.imei2, this.oaid, this.vaid, this.aaid, this.msa_udid, this.idfa_md5, this.imei_md5, this.android_id_md5, this.gaid_md5, this.oaid_md5, this.imei1_md5, this.imei2_md5, super.buildUnknownFields());
        }

        public Builder gaid(String str) {
            this.gaid = str;
            return this;
        }

        public Builder gaid_md5(String str) {
            this.gaid_md5 = str;
            return this;
        }

        public Builder idfa(String str) {
            this.idfa = str;
            return this;
        }

        public Builder idfa_md5(String str) {
            this.idfa_md5 = str;
            return this;
        }

        public Builder imei(String str) {
            this.imei = str;
            return this;
        }

        public Builder imei1(String str) {
            this.imei1 = str;
            return this;
        }

        public Builder imei1_md5(String str) {
            this.imei1_md5 = str;
            return this;
        }

        public Builder imei2(String str) {
            this.imei2 = str;
            return this;
        }

        public Builder imei2_md5(String str) {
            this.imei2_md5 = str;
            return this;
        }

        public Builder imei_md5(String str) {
            this.imei_md5 = str;
            return this;
        }

        public Builder imsi(String str) {
            this.imsi = str;
            return this;
        }

        public Builder msa_udid(String str) {
            this.msa_udid = str;
            return this;
        }

        public Builder oaid(String str) {
            this.oaid = str;
            return this;
        }

        public Builder oaid_md5(String str) {
            this.oaid_md5 = str;
            return this;
        }

        public Builder udid(String str) {
            this.udid = str;
            return this;
        }

        public Builder uid(String str) {
            this.uid = str;
            return this;
        }

        public Builder user_id(String str) {
            this.user_id = str;
            return this;
        }

        public Builder vaid(String str) {
            this.vaid = str;
            return this;
        }
    }

    private static final class ProtoAdapter_DeviceId extends ProtoAdapter<DeviceId> {
        public ProtoAdapter_DeviceId() {
            super(FieldEncoding.LENGTH_DELIMITED, DeviceId.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public DeviceId decode(ProtoReader protoReader) {
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
                        builder.idfa(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.udid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.user_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 4:
                        builder.imei(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.android_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 6:
                        builder.android_uuid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 7:
                        builder.imsi(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 8:
                        builder.gaid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 9:
                        builder.uid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 10:
                        builder.brand(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 11:
                        builder.imei1(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 12:
                        builder.imei2(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 13:
                        builder.oaid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 14:
                        builder.vaid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 15:
                        builder.aaid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 16:
                        builder.msa_udid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 17:
                        builder.idfa_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 18:
                        builder.imei_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 19:
                        builder.android_id_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 20:
                        builder.gaid_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 21:
                        builder.oaid_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 22:
                        builder.imei1_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 23:
                        builder.imei2_md5(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, DeviceId deviceId) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, deviceId.idfa);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, deviceId.udid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, deviceId.user_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, deviceId.imei);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, deviceId.android_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, deviceId.android_uuid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, deviceId.imsi);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, deviceId.gaid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, deviceId.uid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, deviceId.brand);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, deviceId.imei1);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, deviceId.imei2);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 13, deviceId.oaid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 14, deviceId.vaid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 15, deviceId.aaid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 16, deviceId.msa_udid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 17, deviceId.idfa_md5);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 18, deviceId.imei_md5);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 19, deviceId.android_id_md5);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 20, deviceId.gaid_md5);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 21, deviceId.oaid_md5);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 22, deviceId.imei1_md5);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 23, deviceId.imei2_md5);
            protoWriter.writeBytes(deviceId.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(DeviceId deviceId) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, deviceId.idfa) + ProtoAdapter.STRING.encodedSizeWithTag(2, deviceId.udid) + ProtoAdapter.STRING.encodedSizeWithTag(3, deviceId.user_id) + ProtoAdapter.STRING.encodedSizeWithTag(4, deviceId.imei) + ProtoAdapter.STRING.encodedSizeWithTag(5, deviceId.android_id) + ProtoAdapter.STRING.encodedSizeWithTag(6, deviceId.android_uuid) + ProtoAdapter.STRING.encodedSizeWithTag(7, deviceId.imsi) + ProtoAdapter.STRING.encodedSizeWithTag(8, deviceId.gaid) + ProtoAdapter.STRING.encodedSizeWithTag(9, deviceId.uid) + ProtoAdapter.STRING.encodedSizeWithTag(10, deviceId.brand) + ProtoAdapter.STRING.encodedSizeWithTag(11, deviceId.imei1) + ProtoAdapter.STRING.encodedSizeWithTag(12, deviceId.imei2) + ProtoAdapter.STRING.encodedSizeWithTag(13, deviceId.oaid) + ProtoAdapter.STRING.encodedSizeWithTag(14, deviceId.vaid) + ProtoAdapter.STRING.encodedSizeWithTag(15, deviceId.aaid) + ProtoAdapter.STRING.encodedSizeWithTag(16, deviceId.msa_udid) + ProtoAdapter.STRING.encodedSizeWithTag(17, deviceId.idfa_md5) + ProtoAdapter.STRING.encodedSizeWithTag(18, deviceId.imei_md5) + ProtoAdapter.STRING.encodedSizeWithTag(19, deviceId.android_id_md5) + ProtoAdapter.STRING.encodedSizeWithTag(20, deviceId.gaid_md5) + ProtoAdapter.STRING.encodedSizeWithTag(21, deviceId.oaid_md5) + ProtoAdapter.STRING.encodedSizeWithTag(22, deviceId.imei1_md5) + ProtoAdapter.STRING.encodedSizeWithTag(23, deviceId.imei2_md5) + deviceId.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public DeviceId redact(DeviceId deviceId) {
            Builder builderNewBuilder = deviceId.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public DeviceId(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21, str22, str23, ByteString.EMPTY);
    }

    public DeviceId(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, String str23, ByteString byteString) {
        super(ADAPTER, byteString);
        this.idfa = str;
        this.udid = str2;
        this.user_id = str3;
        this.imei = str4;
        this.android_id = str5;
        this.android_uuid = str6;
        this.imsi = str7;
        this.gaid = str8;
        this.uid = str9;
        this.brand = str10;
        this.imei1 = str11;
        this.imei2 = str12;
        this.oaid = str13;
        this.vaid = str14;
        this.aaid = str15;
        this.msa_udid = str16;
        this.idfa_md5 = str17;
        this.imei_md5 = str18;
        this.android_id_md5 = str19;
        this.gaid_md5 = str20;
        this.oaid_md5 = str21;
        this.imei1_md5 = str22;
        this.imei2_md5 = str23;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeviceId)) {
            return false;
        }
        DeviceId deviceId = (DeviceId) obj;
        return unknownFields().equals(deviceId.unknownFields()) && Internal.equals(this.idfa, deviceId.idfa) && Internal.equals(this.udid, deviceId.udid) && Internal.equals(this.user_id, deviceId.user_id) && Internal.equals(this.imei, deviceId.imei) && Internal.equals(this.android_id, deviceId.android_id) && Internal.equals(this.android_uuid, deviceId.android_uuid) && Internal.equals(this.imsi, deviceId.imsi) && Internal.equals(this.gaid, deviceId.gaid) && Internal.equals(this.uid, deviceId.uid) && Internal.equals(this.brand, deviceId.brand) && Internal.equals(this.imei1, deviceId.imei1) && Internal.equals(this.imei2, deviceId.imei2) && Internal.equals(this.oaid, deviceId.oaid) && Internal.equals(this.vaid, deviceId.vaid) && Internal.equals(this.aaid, deviceId.aaid) && Internal.equals(this.msa_udid, deviceId.msa_udid) && Internal.equals(this.idfa_md5, deviceId.idfa_md5) && Internal.equals(this.imei_md5, deviceId.imei_md5) && Internal.equals(this.android_id_md5, deviceId.android_id_md5) && Internal.equals(this.gaid_md5, deviceId.gaid_md5) && Internal.equals(this.oaid_md5, deviceId.oaid_md5) && Internal.equals(this.imei1_md5, deviceId.imei1_md5) && Internal.equals(this.imei2_md5, deviceId.imei2_md5);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.idfa != null ? this.idfa.hashCode() : 0)) * 37) + (this.udid != null ? this.udid.hashCode() : 0)) * 37) + (this.user_id != null ? this.user_id.hashCode() : 0)) * 37) + (this.imei != null ? this.imei.hashCode() : 0)) * 37) + (this.android_id != null ? this.android_id.hashCode() : 0)) * 37) + (this.android_uuid != null ? this.android_uuid.hashCode() : 0)) * 37) + (this.imsi != null ? this.imsi.hashCode() : 0)) * 37) + (this.gaid != null ? this.gaid.hashCode() : 0)) * 37) + (this.uid != null ? this.uid.hashCode() : 0)) * 37) + (this.brand != null ? this.brand.hashCode() : 0)) * 37) + (this.imei1 != null ? this.imei1.hashCode() : 0)) * 37) + (this.imei2 != null ? this.imei2.hashCode() : 0)) * 37) + (this.oaid != null ? this.oaid.hashCode() : 0)) * 37) + (this.vaid != null ? this.vaid.hashCode() : 0)) * 37) + (this.aaid != null ? this.aaid.hashCode() : 0)) * 37) + (this.msa_udid != null ? this.msa_udid.hashCode() : 0)) * 37) + (this.idfa_md5 != null ? this.idfa_md5.hashCode() : 0)) * 37) + (this.imei_md5 != null ? this.imei_md5.hashCode() : 0)) * 37) + (this.android_id_md5 != null ? this.android_id_md5.hashCode() : 0)) * 37) + (this.gaid_md5 != null ? this.gaid_md5.hashCode() : 0)) * 37) + (this.oaid_md5 != null ? this.oaid_md5.hashCode() : 0)) * 37) + (this.imei1_md5 != null ? this.imei1_md5.hashCode() : 0)) * 37) + (this.imei2_md5 != null ? this.imei2_md5.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.idfa = this.idfa;
        builder.udid = this.udid;
        builder.user_id = this.user_id;
        builder.imei = this.imei;
        builder.android_id = this.android_id;
        builder.android_uuid = this.android_uuid;
        builder.imsi = this.imsi;
        builder.gaid = this.gaid;
        builder.uid = this.uid;
        builder.brand = this.brand;
        builder.imei1 = this.imei1;
        builder.imei2 = this.imei2;
        builder.oaid = this.oaid;
        builder.vaid = this.vaid;
        builder.aaid = this.aaid;
        builder.msa_udid = this.msa_udid;
        builder.idfa_md5 = this.idfa_md5;
        builder.imei_md5 = this.imei_md5;
        builder.android_id_md5 = this.android_id_md5;
        builder.gaid_md5 = this.gaid_md5;
        builder.oaid_md5 = this.oaid_md5;
        builder.imei1_md5 = this.imei1_md5;
        builder.imei2_md5 = this.imei2_md5;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.idfa != null) {
            sb.append(", idfa=");
            sb.append(this.idfa);
        }
        if (this.udid != null) {
            sb.append(", udid=");
            sb.append(this.udid);
        }
        if (this.user_id != null) {
            sb.append(", user_id=");
            sb.append(this.user_id);
        }
        if (this.imei != null) {
            sb.append(", imei=");
            sb.append(this.imei);
        }
        if (this.android_id != null) {
            sb.append(", android_id=");
            sb.append(this.android_id);
        }
        if (this.android_uuid != null) {
            sb.append(", android_uuid=");
            sb.append(this.android_uuid);
        }
        if (this.imsi != null) {
            sb.append(", imsi=");
            sb.append(this.imsi);
        }
        if (this.gaid != null) {
            sb.append(", gaid=");
            sb.append(this.gaid);
        }
        if (this.uid != null) {
            sb.append(", uid=");
            sb.append(this.uid);
        }
        if (this.brand != null) {
            sb.append(", brand=");
            sb.append(this.brand);
        }
        if (this.imei1 != null) {
            sb.append(", imei1=");
            sb.append(this.imei1);
        }
        if (this.imei2 != null) {
            sb.append(", imei2=");
            sb.append(this.imei2);
        }
        if (this.oaid != null) {
            sb.append(", oaid=");
            sb.append(this.oaid);
        }
        if (this.vaid != null) {
            sb.append(", vaid=");
            sb.append(this.vaid);
        }
        if (this.aaid != null) {
            sb.append(", aaid=");
            sb.append(this.aaid);
        }
        if (this.msa_udid != null) {
            sb.append(", msa_udid=");
            sb.append(this.msa_udid);
        }
        if (this.idfa_md5 != null) {
            sb.append(", idfa_md5=");
            sb.append(this.idfa_md5);
        }
        if (this.imei_md5 != null) {
            sb.append(", imei_md5=");
            sb.append(this.imei_md5);
        }
        if (this.android_id_md5 != null) {
            sb.append(", android_id_md5=");
            sb.append(this.android_id_md5);
        }
        if (this.gaid_md5 != null) {
            sb.append(", gaid_md5=");
            sb.append(this.gaid_md5);
        }
        if (this.oaid_md5 != null) {
            sb.append(", oaid_md5=");
            sb.append(this.oaid_md5);
        }
        if (this.imei1_md5 != null) {
            sb.append(", imei1_md5=");
            sb.append(this.imei1_md5);
        }
        if (this.imei2_md5 != null) {
            sb.append(", imei2_md5=");
            sb.append(this.imei2_md5);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "DeviceId{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
