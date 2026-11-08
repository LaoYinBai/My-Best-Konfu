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
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Ad extends AndroidMessage<Ad, Builder> {
    public static final String DEFAULT_ADSLOT_ID = "";
    public static final String DEFAULT_AD_SOURCE_CHANNEL = "";
    public static final String DEFAULT_AD_SOURCE_LOGO = "";
    public static final String DEFAULT_ATTRIBUTION_ID = "";
    public static final String DEFAULT_CAMP_ID = "";
    public static final String DEFAULT_CRID = "";
    public static final String DEFAULT_CUST_ID = "";
    public static final String DEFAULT_PRODUCT_ID = "";
    public static final String DEFAULT_SETTLEMENT_PRICE_ENC = "";
    public static final String DEFAULT_VID = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.AdSetting#ADAPTER", tag = 19)
    public final AdSetting ad_setting;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 13)
    public final String ad_source_channel;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 12)
    public final String ad_source_logo;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 24)
    public final Map<String, String> ad_track_macro;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Tracking#ADAPTER", label = WireField.Label.REPEATED, tag = 7)
    public final List<Tracking> ad_tracking;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 14)
    public final Integer ad_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String adslot_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 21)
    public final String attribution_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 8)
    public final Integer bid_price;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 20)
    public final Integer bid_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String camp_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String crid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String cust_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 18)
    public final Integer display_orientation;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 16)
    public final Integer expired_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 17)
    public final Boolean forbiden_parse_landingpage;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 11)
    public final Integer is_override;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta#ADAPTER", label = WireField.Label.REPEATED, tag = 6)
    public final List<MaterialMeta> materials;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 26)
    public final Integer max_price;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 27)
    public final Integer min_price;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 15)
    public final Map<String, String> options;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 25)
    public final Integer original_price;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final String product_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String settlement_price_enc;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.SKAdNetwork#ADAPTER", tag = 22)
    public final SKAdNetwork sk_ad_network;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String vid;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.SKAdNetwork#ADAPTER", tag = 23)
    public final SKAdNetwork view_through_ad_network;
    public static final ProtoAdapter<Ad> ADAPTER = new ProtoAdapter_Ad();
    public static final Parcelable.Creator<Ad> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_BID_PRICE = 0;
    public static final Integer DEFAULT_IS_OVERRIDE = 0;
    public static final Integer DEFAULT_AD_TYPE = 0;
    public static final Integer DEFAULT_EXPIRED_TIME = 0;
    public static final Boolean DEFAULT_FORBIDEN_PARSE_LANDINGPAGE = false;
    public static final Integer DEFAULT_DISPLAY_ORIENTATION = 0;
    public static final Integer DEFAULT_BID_TYPE = 0;
    public static final Integer DEFAULT_ORIGINAL_PRICE = 0;
    public static final Integer DEFAULT_MAX_PRICE = 0;
    public static final Integer DEFAULT_MIN_PRICE = 0;

    public static final class Builder extends Message.Builder<Ad, Builder> {
        public AdSetting ad_setting;
        public Integer bid_type;
        public String imp_id;
        public Integer max_price;
        public Integer min_price;
        public Integer original_price;
        public SKAdNetwork sk_ad_network;
        public SKAdNetwork view_through_ad_network;
        public String adslot_id = "";
        public String vid = "";
        public String cust_id = "";
        public String camp_id = "";
        public String crid = "";
        public Integer bid_price = Ad.DEFAULT_BID_PRICE;
        public String product_id = "";
        public String settlement_price_enc = "";
        public Integer is_override = Ad.DEFAULT_IS_OVERRIDE;
        public String ad_source_logo = "";
        public String ad_source_channel = "";
        public Integer ad_type = Ad.DEFAULT_AD_TYPE;
        public Integer expired_time = Ad.DEFAULT_EXPIRED_TIME;
        public Boolean forbiden_parse_landingpage = Ad.DEFAULT_FORBIDEN_PARSE_LANDINGPAGE;
        public Integer display_orientation = Ad.DEFAULT_DISPLAY_ORIENTATION;
        public String attribution_id = "";
        public List<MaterialMeta> materials = Internal.newMutableList();
        public List<Tracking> ad_tracking = Internal.newMutableList();
        public Map<String, String> options = Internal.newMutableMap();
        public Map<String, String> ad_track_macro = Internal.newMutableMap();

        public Builder ad_setting(AdSetting adSetting) {
            this.ad_setting = adSetting;
            return this;
        }

        public Builder ad_source_channel(String str) {
            this.ad_source_channel = str;
            return this;
        }

        public Builder ad_source_logo(String str) {
            this.ad_source_logo = str;
            return this;
        }

        public Builder ad_track_macro(Map<String, String> map) {
            Internal.checkElementsNotNull(map);
            this.ad_track_macro = map;
            return this;
        }

        public Builder ad_tracking(List<Tracking> list) {
            Internal.checkElementsNotNull(list);
            this.ad_tracking = list;
            return this;
        }

        public Builder ad_type(Integer num) {
            this.ad_type = num;
            return this;
        }

        public Builder adslot_id(String str) {
            this.adslot_id = str;
            return this;
        }

        public Builder attribution_id(String str) {
            this.attribution_id = str;
            return this;
        }

        public Builder bid_price(Integer num) {
            this.bid_price = num;
            return this;
        }

        public Builder bid_type(Integer num) {
            this.bid_type = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public Ad build() {
            return new Ad(this.adslot_id, this.vid, this.cust_id, this.camp_id, this.crid, this.materials, this.ad_tracking, this.bid_price, this.product_id, this.settlement_price_enc, this.is_override, this.ad_source_logo, this.ad_source_channel, this.ad_type, this.options, this.expired_time, this.forbiden_parse_landingpage, this.display_orientation, this.ad_setting, this.bid_type, this.attribution_id, this.sk_ad_network, this.view_through_ad_network, this.ad_track_macro, this.original_price, this.max_price, this.min_price, super.buildUnknownFields());
        }

        public Builder camp_id(String str) {
            this.camp_id = str;
            return this;
        }

        public Builder crid(String str) {
            this.crid = str;
            return this;
        }

        public Builder cust_id(String str) {
            this.cust_id = str;
            return this;
        }

        public Builder display_orientation(Integer num) {
            this.display_orientation = num;
            return this;
        }

        public Builder expired_time(Integer num) {
            this.expired_time = num;
            return this;
        }

        public Builder forbiden_parse_landingpage(Boolean bool) {
            this.forbiden_parse_landingpage = bool;
            return this;
        }

        public Builder is_override(Integer num) {
            this.is_override = num;
            return this;
        }

        public Builder materials(List<MaterialMeta> list) {
            Internal.checkElementsNotNull(list);
            this.materials = list;
            return this;
        }

        public Builder max_price(Integer num) {
            this.max_price = num;
            return this;
        }

        public Builder min_price(Integer num) {
            this.min_price = num;
            return this;
        }

        public Builder options(Map<String, String> map) {
            Internal.checkElementsNotNull(map);
            this.options = map;
            return this;
        }

        public Builder original_price(Integer num) {
            this.original_price = num;
            return this;
        }

        public Builder product_id(String str) {
            this.product_id = str;
            return this;
        }

        public Builder settlement_price_enc(String str) {
            this.settlement_price_enc = str;
            return this;
        }

        public Builder sk_ad_network(SKAdNetwork sKAdNetwork) {
            this.sk_ad_network = sKAdNetwork;
            return this;
        }

        public Builder vid(String str) {
            this.vid = str;
            return this;
        }

        public Builder view_through_ad_network(SKAdNetwork sKAdNetwork) {
            this.view_through_ad_network = sKAdNetwork;
            return this;
        }
    }

    private static final class ProtoAdapter_Ad extends ProtoAdapter<Ad> {
        private final ProtoAdapter<Map<String, String>> a;
        private final ProtoAdapter<Map<String, String>> b;

        public ProtoAdapter_Ad() {
            super(FieldEncoding.LENGTH_DELIMITED, Ad.class);
            this.a = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.STRING);
            this.b = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.STRING);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Ad decode(ProtoReader protoReader) {
            List list;
            ProtoAdapter protoAdapter;
            Map<String, String> map;
            ProtoAdapter<Map<String, String>> protoAdapter2;
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
                        builder.adslot_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.vid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.cust_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 4:
                        builder.camp_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.crid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 6:
                        list = builder.materials;
                        protoAdapter = MaterialMeta.ADAPTER;
                        list.add(protoAdapter.decode(protoReader));
                        break;
                    case 7:
                        list = builder.ad_tracking;
                        protoAdapter = Tracking.ADAPTER;
                        list.add(protoAdapter.decode(protoReader));
                        break;
                    case 8:
                        builder.bid_price(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 9:
                        builder.product_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 10:
                        builder.settlement_price_enc(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 11:
                        builder.is_override(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 12:
                        builder.ad_source_logo(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 13:
                        builder.ad_source_channel(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 14:
                        builder.ad_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 15:
                        map = builder.options;
                        protoAdapter2 = this.a;
                        map.putAll(protoAdapter2.decode(protoReader));
                        break;
                    case 16:
                        builder.expired_time(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 17:
                        builder.forbiden_parse_landingpage(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 18:
                        builder.display_orientation(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 19:
                        builder.ad_setting(AdSetting.ADAPTER.decode(protoReader));
                        break;
                    case 20:
                        builder.bid_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 21:
                        builder.attribution_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 22:
                        builder.sk_ad_network(SKAdNetwork.ADAPTER.decode(protoReader));
                        break;
                    case 23:
                        builder.view_through_ad_network(SKAdNetwork.ADAPTER.decode(protoReader));
                        break;
                    case 24:
                        map = builder.ad_track_macro;
                        protoAdapter2 = this.b;
                        map.putAll(protoAdapter2.decode(protoReader));
                        break;
                    case 25:
                        builder.original_price(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 26:
                        builder.max_price(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 27:
                        builder.min_price(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Ad ad) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, ad.adslot_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, ad.vid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, ad.cust_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, ad.camp_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, ad.crid);
            MaterialMeta.ADAPTER.asRepeated().encodeWithTag(protoWriter, 6, ad.materials);
            Tracking.ADAPTER.asRepeated().encodeWithTag(protoWriter, 7, ad.ad_tracking);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 8, ad.bid_price);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 9, ad.product_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, ad.settlement_price_enc);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 11, ad.is_override);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 12, ad.ad_source_logo);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 13, ad.ad_source_channel);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 14, ad.ad_type);
            this.a.encodeWithTag(protoWriter, 15, ad.options);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 16, ad.expired_time);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 17, ad.forbiden_parse_landingpage);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 18, ad.display_orientation);
            AdSetting.ADAPTER.encodeWithTag(protoWriter, 19, ad.ad_setting);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 20, ad.bid_type);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 21, ad.attribution_id);
            SKAdNetwork.ADAPTER.encodeWithTag(protoWriter, 22, ad.sk_ad_network);
            SKAdNetwork.ADAPTER.encodeWithTag(protoWriter, 23, ad.view_through_ad_network);
            this.b.encodeWithTag(protoWriter, 24, ad.ad_track_macro);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 25, ad.original_price);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 26, ad.max_price);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 27, ad.min_price);
            protoWriter.writeBytes(ad.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Ad ad) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, ad.adslot_id) + ProtoAdapter.STRING.encodedSizeWithTag(2, ad.vid) + ProtoAdapter.STRING.encodedSizeWithTag(3, ad.cust_id) + ProtoAdapter.STRING.encodedSizeWithTag(4, ad.camp_id) + ProtoAdapter.STRING.encodedSizeWithTag(5, ad.crid) + MaterialMeta.ADAPTER.asRepeated().encodedSizeWithTag(6, ad.materials) + Tracking.ADAPTER.asRepeated().encodedSizeWithTag(7, ad.ad_tracking) + ProtoAdapter.UINT32.encodedSizeWithTag(8, ad.bid_price) + ProtoAdapter.STRING.encodedSizeWithTag(9, ad.product_id) + ProtoAdapter.STRING.encodedSizeWithTag(10, ad.settlement_price_enc) + ProtoAdapter.UINT32.encodedSizeWithTag(11, ad.is_override) + ProtoAdapter.STRING.encodedSizeWithTag(12, ad.ad_source_logo) + ProtoAdapter.STRING.encodedSizeWithTag(13, ad.ad_source_channel) + ProtoAdapter.UINT32.encodedSizeWithTag(14, ad.ad_type) + this.a.encodedSizeWithTag(15, ad.options) + ProtoAdapter.UINT32.encodedSizeWithTag(16, ad.expired_time) + ProtoAdapter.BOOL.encodedSizeWithTag(17, ad.forbiden_parse_landingpage) + ProtoAdapter.UINT32.encodedSizeWithTag(18, ad.display_orientation) + AdSetting.ADAPTER.encodedSizeWithTag(19, ad.ad_setting) + ProtoAdapter.UINT32.encodedSizeWithTag(20, ad.bid_type) + ProtoAdapter.STRING.encodedSizeWithTag(21, ad.attribution_id) + SKAdNetwork.ADAPTER.encodedSizeWithTag(22, ad.sk_ad_network) + SKAdNetwork.ADAPTER.encodedSizeWithTag(23, ad.view_through_ad_network) + this.b.encodedSizeWithTag(24, ad.ad_track_macro) + ProtoAdapter.UINT32.encodedSizeWithTag(25, ad.original_price) + ProtoAdapter.UINT32.encodedSizeWithTag(26, ad.max_price) + ProtoAdapter.UINT32.encodedSizeWithTag(27, ad.min_price) + ad.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Ad redact(Ad ad) {
            Builder builderNewBuilder = ad.newBuilder();
            Internal.redactElements(builderNewBuilder.materials, MaterialMeta.ADAPTER);
            Internal.redactElements(builderNewBuilder.ad_tracking, Tracking.ADAPTER);
            if (builderNewBuilder.ad_setting != null) {
                builderNewBuilder.ad_setting = AdSetting.ADAPTER.redact(builderNewBuilder.ad_setting);
            }
            if (builderNewBuilder.sk_ad_network != null) {
                builderNewBuilder.sk_ad_network = SKAdNetwork.ADAPTER.redact(builderNewBuilder.sk_ad_network);
            }
            if (builderNewBuilder.view_through_ad_network != null) {
                builderNewBuilder.view_through_ad_network = SKAdNetwork.ADAPTER.redact(builderNewBuilder.view_through_ad_network);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Ad(String str, String str2, String str3, String str4, String str5, List<MaterialMeta> list, List<Tracking> list2, Integer num, String str6, String str7, Integer num2, String str8, String str9, Integer num3, Map<String, String> map, Integer num4, Boolean bool, Integer num5, AdSetting adSetting, Integer num6, String str10, SKAdNetwork sKAdNetwork, SKAdNetwork sKAdNetwork2, Map<String, String> map2, Integer num7, Integer num8, Integer num9) {
        this(str, str2, str3, str4, str5, list, list2, num, str6, str7, num2, str8, str9, num3, map, num4, bool, num5, adSetting, num6, str10, sKAdNetwork, sKAdNetwork2, map2, num7, num8, num9, ByteString.EMPTY);
    }

    public Ad(String str, String str2, String str3, String str4, String str5, List<MaterialMeta> list, List<Tracking> list2, Integer num, String str6, String str7, Integer num2, String str8, String str9, Integer num3, Map<String, String> map, Integer num4, Boolean bool, Integer num5, AdSetting adSetting, Integer num6, String str10, SKAdNetwork sKAdNetwork, SKAdNetwork sKAdNetwork2, Map<String, String> map2, Integer num7, Integer num8, Integer num9, ByteString byteString) {
        super(ADAPTER, byteString);
        this.adslot_id = str;
        this.vid = str2;
        this.cust_id = str3;
        this.camp_id = str4;
        this.crid = str5;
        this.materials = Internal.immutableCopyOf("materials", list);
        this.ad_tracking = Internal.immutableCopyOf("ad_tracking", list2);
        this.bid_price = num;
        this.product_id = str6;
        this.settlement_price_enc = str7;
        this.is_override = num2;
        this.ad_source_logo = str8;
        this.ad_source_channel = str9;
        this.ad_type = num3;
        this.options = Internal.immutableCopyOf("options", map);
        this.expired_time = num4;
        this.forbiden_parse_landingpage = bool;
        this.display_orientation = num5;
        this.ad_setting = adSetting;
        this.bid_type = num6;
        this.attribution_id = str10;
        this.sk_ad_network = sKAdNetwork;
        this.view_through_ad_network = sKAdNetwork2;
        this.ad_track_macro = Internal.immutableCopyOf("ad_track_macro", map2);
        this.original_price = num7;
        this.max_price = num8;
        this.min_price = num9;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ad)) {
            return false;
        }
        Ad ad = (Ad) obj;
        return unknownFields().equals(ad.unknownFields()) && Internal.equals(this.adslot_id, ad.adslot_id) && Internal.equals(this.vid, ad.vid) && Internal.equals(this.cust_id, ad.cust_id) && Internal.equals(this.camp_id, ad.camp_id) && Internal.equals(this.crid, ad.crid) && this.materials.equals(ad.materials) && this.ad_tracking.equals(ad.ad_tracking) && Internal.equals(this.bid_price, ad.bid_price) && Internal.equals(this.product_id, ad.product_id) && Internal.equals(this.settlement_price_enc, ad.settlement_price_enc) && Internal.equals(this.is_override, ad.is_override) && Internal.equals(this.ad_source_logo, ad.ad_source_logo) && Internal.equals(this.ad_source_channel, ad.ad_source_channel) && Internal.equals(this.ad_type, ad.ad_type) && this.options.equals(ad.options) && Internal.equals(this.expired_time, ad.expired_time) && Internal.equals(this.forbiden_parse_landingpage, ad.forbiden_parse_landingpage) && Internal.equals(this.display_orientation, ad.display_orientation) && Internal.equals(this.ad_setting, ad.ad_setting) && Internal.equals(this.bid_type, ad.bid_type) && Internal.equals(this.attribution_id, ad.attribution_id) && Internal.equals(this.sk_ad_network, ad.sk_ad_network) && Internal.equals(this.view_through_ad_network, ad.view_through_ad_network) && this.ad_track_macro.equals(ad.ad_track_macro) && Internal.equals(this.original_price, ad.original_price) && Internal.equals(this.max_price, ad.max_price) && Internal.equals(this.min_price, ad.min_price);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.adslot_id != null ? this.adslot_id.hashCode() : 0)) * 37) + (this.vid != null ? this.vid.hashCode() : 0)) * 37) + (this.cust_id != null ? this.cust_id.hashCode() : 0)) * 37) + (this.camp_id != null ? this.camp_id.hashCode() : 0)) * 37) + (this.crid != null ? this.crid.hashCode() : 0)) * 37) + this.materials.hashCode()) * 37) + this.ad_tracking.hashCode()) * 37) + (this.bid_price != null ? this.bid_price.hashCode() : 0)) * 37) + (this.product_id != null ? this.product_id.hashCode() : 0)) * 37) + (this.settlement_price_enc != null ? this.settlement_price_enc.hashCode() : 0)) * 37) + (this.is_override != null ? this.is_override.hashCode() : 0)) * 37) + (this.ad_source_logo != null ? this.ad_source_logo.hashCode() : 0)) * 37) + (this.ad_source_channel != null ? this.ad_source_channel.hashCode() : 0)) * 37) + (this.ad_type != null ? this.ad_type.hashCode() : 0)) * 37) + this.options.hashCode()) * 37) + (this.expired_time != null ? this.expired_time.hashCode() : 0)) * 37) + (this.forbiden_parse_landingpage != null ? this.forbiden_parse_landingpage.hashCode() : 0)) * 37) + (this.display_orientation != null ? this.display_orientation.hashCode() : 0)) * 37) + (this.ad_setting != null ? this.ad_setting.hashCode() : 0)) * 37) + (this.bid_type != null ? this.bid_type.hashCode() : 0)) * 37) + (this.attribution_id != null ? this.attribution_id.hashCode() : 0)) * 37) + (this.sk_ad_network != null ? this.sk_ad_network.hashCode() : 0)) * 37) + (this.view_through_ad_network != null ? this.view_through_ad_network.hashCode() : 0)) * 37) + this.ad_track_macro.hashCode()) * 37) + (this.original_price != null ? this.original_price.hashCode() : 0)) * 37) + (this.max_price != null ? this.max_price.hashCode() : 0)) * 37) + (this.min_price != null ? this.min_price.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.adslot_id = this.adslot_id;
        builder.vid = this.vid;
        builder.cust_id = this.cust_id;
        builder.camp_id = this.camp_id;
        builder.crid = this.crid;
        builder.materials = Internal.copyOf("materials", this.materials);
        builder.ad_tracking = Internal.copyOf("ad_tracking", this.ad_tracking);
        builder.bid_price = this.bid_price;
        builder.product_id = this.product_id;
        builder.settlement_price_enc = this.settlement_price_enc;
        builder.is_override = this.is_override;
        builder.ad_source_logo = this.ad_source_logo;
        builder.ad_source_channel = this.ad_source_channel;
        builder.ad_type = this.ad_type;
        builder.options = Internal.copyOf("options", this.options);
        builder.expired_time = this.expired_time;
        builder.forbiden_parse_landingpage = this.forbiden_parse_landingpage;
        builder.display_orientation = this.display_orientation;
        builder.ad_setting = this.ad_setting;
        builder.bid_type = this.bid_type;
        builder.attribution_id = this.attribution_id;
        builder.sk_ad_network = this.sk_ad_network;
        builder.view_through_ad_network = this.view_through_ad_network;
        builder.ad_track_macro = Internal.copyOf("ad_track_macro", this.ad_track_macro);
        builder.original_price = this.original_price;
        builder.max_price = this.max_price;
        builder.min_price = this.min_price;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.adslot_id != null) {
            sb.append(", adslot_id=");
            sb.append(this.adslot_id);
        }
        if (this.vid != null) {
            sb.append(", vid=");
            sb.append(this.vid);
        }
        if (this.cust_id != null) {
            sb.append(", cust_id=");
            sb.append(this.cust_id);
        }
        if (this.camp_id != null) {
            sb.append(", camp_id=");
            sb.append(this.camp_id);
        }
        if (this.crid != null) {
            sb.append(", crid=");
            sb.append(this.crid);
        }
        if (!this.materials.isEmpty()) {
            sb.append(", materials=");
            sb.append(this.materials);
        }
        if (!this.ad_tracking.isEmpty()) {
            sb.append(", ad_tracking=");
            sb.append(this.ad_tracking);
        }
        if (this.bid_price != null) {
            sb.append(", bid_price=");
            sb.append(this.bid_price);
        }
        if (this.product_id != null) {
            sb.append(", product_id=");
            sb.append(this.product_id);
        }
        if (this.settlement_price_enc != null) {
            sb.append(", settlement_price_enc=");
            sb.append(this.settlement_price_enc);
        }
        if (this.is_override != null) {
            sb.append(", is_override=");
            sb.append(this.is_override);
        }
        if (this.ad_source_logo != null) {
            sb.append(", ad_source_logo=");
            sb.append(this.ad_source_logo);
        }
        if (this.ad_source_channel != null) {
            sb.append(", ad_source_channel=");
            sb.append(this.ad_source_channel);
        }
        if (this.ad_type != null) {
            sb.append(", ad_type=");
            sb.append(this.ad_type);
        }
        if (!this.options.isEmpty()) {
            sb.append(", options=");
            sb.append(this.options);
        }
        if (this.expired_time != null) {
            sb.append(", expired_time=");
            sb.append(this.expired_time);
        }
        if (this.forbiden_parse_landingpage != null) {
            sb.append(", forbiden_parse_landingpage=");
            sb.append(this.forbiden_parse_landingpage);
        }
        if (this.display_orientation != null) {
            sb.append(", display_orientation=");
            sb.append(this.display_orientation);
        }
        if (this.ad_setting != null) {
            sb.append(", ad_setting=");
            sb.append(this.ad_setting);
        }
        if (this.bid_type != null) {
            sb.append(", bid_type=");
            sb.append(this.bid_type);
        }
        if (this.attribution_id != null) {
            sb.append(", attribution_id=");
            sb.append(this.attribution_id);
        }
        if (this.sk_ad_network != null) {
            sb.append(", sk_ad_network=");
            sb.append(this.sk_ad_network);
        }
        if (this.view_through_ad_network != null) {
            sb.append(", view_through_ad_network=");
            sb.append(this.view_through_ad_network);
        }
        if (!this.ad_track_macro.isEmpty()) {
            sb.append(", ad_track_macro=");
            sb.append(this.ad_track_macro);
        }
        if (this.original_price != null) {
            sb.append(", original_price=");
            sb.append(this.original_price);
        }
        if (this.max_price != null) {
            sb.append(", max_price=");
            sb.append(this.max_price);
        }
        if (this.min_price != null) {
            sb.append(", min_price=");
            sb.append(this.min_price);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Ad{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
