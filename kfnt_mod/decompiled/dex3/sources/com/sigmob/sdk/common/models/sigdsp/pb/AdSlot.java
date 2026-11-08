package com.sigmob.sdk.common.models.sigdsp.pb;

import android.os.Parcelable;
import com.sigmob.sdk.common.Constants;
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
public final class AdSlot extends AndroidMessage<AdSlot, Builder> {
    public static final String DEFAULT_ADSLOT_ID = "";
    public static final String DEFAULT_LATEST_CAMP_ID = "";
    public static final String DEFAULT_LATEST_CRID = "";
    public static final String DEFAULT_VID = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.AdCache#ADAPTER", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 15)
    public final Map<String, AdCache> ad_caches;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 28)
    public final Integer ad_count;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String adslot_id;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Size#ADAPTER", tag = 2)
    public final Size adslot_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", label = WireField.Label.REPEATED, tag = 3)
    public final List<Integer> adslot_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 24)
    public final Integer adx_ecpm;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 20)
    public final Integer alg_bid_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 13)
    public final Integer algorithm_floor;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 10)
    public final Integer api_strategy_index;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 18)
    public final Integer auto_click_mode;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 27)
    public final List<String> b_seat;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 4)
    public final Integer bidfloor;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 23)
    public final Integer commer_ecpm;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 25)
    public final Integer commer_ecpm_state;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", label = WireField.Label.REPEATED, tag = 12)
    public final List<Integer> creative_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 21)
    public final Integer ecpm_target;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 17)
    public final Integer expected_floor;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 14)
    public final Map<String, String> ext;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String latest_camp_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String latest_crid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", label = WireField.Label.REPEATED, tag = 8)
    public final List<Integer> material_type;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.RequestNativeAd#ADAPTER", tag = 26)
    public final RequestNativeAd native_ad;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final Map<String, String> sdk_cached_ads;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 9)
    public final Integer sdk_strategy_index;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 19)
    public final Integer settlement_mode;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 22)
    public final Integer settlement_setting;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String vid;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Video#ADAPTER", tag = 16)
    public final Video video;
    public static final ProtoAdapter<AdSlot> ADAPTER = new ProtoAdapter_AdSlot();
    public static final Parcelable.Creator<AdSlot> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_BIDFLOOR = 0;
    public static final Integer DEFAULT_SDK_STRATEGY_INDEX = 0;
    public static final Integer DEFAULT_API_STRATEGY_INDEX = 0;
    public static final Integer DEFAULT_ALGORITHM_FLOOR = 0;
    public static final Integer DEFAULT_EXPECTED_FLOOR = 0;
    public static final Integer DEFAULT_AUTO_CLICK_MODE = 0;
    public static final Integer DEFAULT_SETTLEMENT_MODE = 0;
    public static final Integer DEFAULT_ALG_BID_TYPE = 0;
    public static final Integer DEFAULT_ECPM_TARGET = 0;
    public static final Integer DEFAULT_SETTLEMENT_SETTING = 0;
    public static final Integer DEFAULT_COMMER_ECPM = 0;
    public static final Integer DEFAULT_ADX_ECPM = 0;
    public static final Integer DEFAULT_COMMER_ECPM_STATE = 0;
    public static final Integer DEFAULT_AD_COUNT = 0;

    public static final class Builder extends Message.Builder<AdSlot, Builder> {
        public Integer ad_count;
        public Size adslot_size;
        public Integer adx_ecpm;
        public Integer alg_bid_type;
        public Integer algorithm_floor;
        public Integer auto_click_mode;
        public Integer commer_ecpm;
        public Integer commer_ecpm_state;
        public Integer ecpm_target;
        public RequestNativeAd native_ad;
        public Integer settlement_mode;
        public Integer settlement_setting;
        public Video video;
        public String adslot_id = "";
        public Integer bidfloor = AdSlot.DEFAULT_BIDFLOOR;
        public String vid = "";
        public String latest_camp_id = "";
        public String latest_crid = "";
        public Integer sdk_strategy_index = AdSlot.DEFAULT_SDK_STRATEGY_INDEX;
        public Integer api_strategy_index = AdSlot.DEFAULT_API_STRATEGY_INDEX;
        public Integer expected_floor = AdSlot.DEFAULT_EXPECTED_FLOOR;
        public List<Integer> adslot_type = Internal.newMutableList();
        public List<Integer> material_type = Internal.newMutableList();
        public Map<String, String> sdk_cached_ads = Internal.newMutableMap();
        public List<Integer> creative_type = Internal.newMutableList();
        public Map<String, String> ext = Internal.newMutableMap();
        public Map<String, AdCache> ad_caches = Internal.newMutableMap();
        public List<String> b_seat = Internal.newMutableList();

        public Builder ad_caches(Map<String, AdCache> map) {
            Internal.checkElementsNotNull(map);
            this.ad_caches = map;
            return this;
        }

        public Builder ad_count(Integer num) {
            this.ad_count = num;
            return this;
        }

        public Builder adslot_id(String str) {
            this.adslot_id = str;
            return this;
        }

        public Builder adslot_size(Size size) {
            this.adslot_size = size;
            return this;
        }

        public Builder adslot_type(List<Integer> list) {
            Internal.checkElementsNotNull(list);
            this.adslot_type = list;
            return this;
        }

        public Builder adx_ecpm(Integer num) {
            this.adx_ecpm = num;
            return this;
        }

        public Builder alg_bid_type(Integer num) {
            this.alg_bid_type = num;
            return this;
        }

        public Builder algorithm_floor(Integer num) {
            this.algorithm_floor = num;
            return this;
        }

        public Builder api_strategy_index(Integer num) {
            this.api_strategy_index = num;
            return this;
        }

        public Builder auto_click_mode(Integer num) {
            this.auto_click_mode = num;
            return this;
        }

        public Builder b_seat(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.b_seat = list;
            return this;
        }

        public Builder bidfloor(Integer num) {
            this.bidfloor = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public AdSlot build() {
            return new AdSlot(this.adslot_id, this.adslot_size, this.adslot_type, this.bidfloor, this.vid, this.latest_camp_id, this.latest_crid, this.material_type, this.sdk_strategy_index, this.api_strategy_index, this.sdk_cached_ads, this.creative_type, this.algorithm_floor, this.ext, this.ad_caches, this.video, this.expected_floor, this.auto_click_mode, this.settlement_mode, this.alg_bid_type, this.ecpm_target, this.settlement_setting, this.commer_ecpm, this.adx_ecpm, this.commer_ecpm_state, this.native_ad, this.b_seat, this.ad_count, super.buildUnknownFields());
        }

        public Builder commer_ecpm(Integer num) {
            this.commer_ecpm = num;
            return this;
        }

        public Builder commer_ecpm_state(Integer num) {
            this.commer_ecpm_state = num;
            return this;
        }

        public Builder creative_type(List<Integer> list) {
            Internal.checkElementsNotNull(list);
            this.creative_type = list;
            return this;
        }

        public Builder ecpm_target(Integer num) {
            this.ecpm_target = num;
            return this;
        }

        public Builder expected_floor(Integer num) {
            this.expected_floor = num;
            return this;
        }

        public Builder ext(Map<String, String> map) {
            Internal.checkElementsNotNull(map);
            this.ext = map;
            return this;
        }

        public Builder latest_camp_id(String str) {
            this.latest_camp_id = str;
            return this;
        }

        public Builder latest_crid(String str) {
            this.latest_crid = str;
            return this;
        }

        public Builder material_type(List<Integer> list) {
            Internal.checkElementsNotNull(list);
            this.material_type = list;
            return this;
        }

        public Builder native_ad(RequestNativeAd requestNativeAd) {
            this.native_ad = requestNativeAd;
            return this;
        }

        public Builder sdk_cached_ads(Map<String, String> map) {
            Internal.checkElementsNotNull(map);
            this.sdk_cached_ads = map;
            return this;
        }

        public Builder sdk_strategy_index(Integer num) {
            this.sdk_strategy_index = num;
            return this;
        }

        public Builder settlement_mode(Integer num) {
            this.settlement_mode = num;
            return this;
        }

        public Builder settlement_setting(Integer num) {
            this.settlement_setting = num;
            return this;
        }

        public Builder vid(String str) {
            this.vid = str;
            return this;
        }

        public Builder video(Video video) {
            this.video = video;
            return this;
        }
    }

    private static final class ProtoAdapter_AdSlot extends ProtoAdapter<AdSlot> {
        private final ProtoAdapter<Map<String, String>> a;
        private final ProtoAdapter<Map<String, String>> b;
        private final ProtoAdapter<Map<String, AdCache>> c;

        public ProtoAdapter_AdSlot() {
            super(FieldEncoding.LENGTH_DELIMITED, AdSlot.class);
            this.a = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.STRING);
            this.b = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.STRING);
            this.c = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, AdCache.ADAPTER);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public AdSlot decode(ProtoReader protoReader) {
            List list;
            Map map;
            ProtoAdapter protoAdapter;
            ProtoAdapter protoAdapter2;
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
                        builder.adslot_size(Size.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        list = builder.adslot_type;
                        protoAdapter2 = ProtoAdapter.UINT32;
                        list.add(protoAdapter2.decode(protoReader));
                        break;
                    case 4:
                        builder.bidfloor(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 5:
                        builder.vid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 6:
                        builder.latest_camp_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 7:
                        builder.latest_crid(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 8:
                        list = builder.material_type;
                        protoAdapter2 = ProtoAdapter.UINT32;
                        list.add(protoAdapter2.decode(protoReader));
                        break;
                    case 9:
                        builder.sdk_strategy_index(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 10:
                        builder.api_strategy_index(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 11:
                        map = builder.sdk_cached_ads;
                        protoAdapter = this.a;
                        map.putAll((Map) protoAdapter.decode(protoReader));
                        break;
                    case 12:
                        list = builder.creative_type;
                        protoAdapter2 = ProtoAdapter.UINT32;
                        list.add(protoAdapter2.decode(protoReader));
                        break;
                    case 13:
                        builder.algorithm_floor(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 14:
                        map = builder.ext;
                        protoAdapter = this.b;
                        map.putAll((Map) protoAdapter.decode(protoReader));
                        break;
                    case 15:
                        map = builder.ad_caches;
                        protoAdapter = this.c;
                        map.putAll((Map) protoAdapter.decode(protoReader));
                        break;
                    case 16:
                        builder.video(Video.ADAPTER.decode(protoReader));
                        break;
                    case 17:
                        builder.expected_floor(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 18:
                        builder.auto_click_mode(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 19:
                        builder.settlement_mode(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 20:
                        builder.alg_bid_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 21:
                        builder.ecpm_target(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 22:
                        builder.settlement_setting(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 23:
                        builder.commer_ecpm(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 24:
                        builder.adx_ecpm(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 25:
                        builder.commer_ecpm_state(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 26:
                        builder.native_ad(RequestNativeAd.ADAPTER.decode(protoReader));
                        break;
                    case 27:
                        list = builder.b_seat;
                        protoAdapter2 = ProtoAdapter.STRING;
                        list.add(protoAdapter2.decode(protoReader));
                        break;
                    case 28:
                        builder.ad_count(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, AdSlot adSlot) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, adSlot.adslot_id);
            Size.ADAPTER.encodeWithTag(protoWriter, 2, adSlot.adslot_size);
            ProtoAdapter.UINT32.asRepeated().encodeWithTag(protoWriter, 3, adSlot.adslot_type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 4, adSlot.bidfloor);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, adSlot.vid);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, adSlot.latest_camp_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, adSlot.latest_crid);
            ProtoAdapter.UINT32.asRepeated().encodeWithTag(protoWriter, 8, adSlot.material_type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 9, adSlot.sdk_strategy_index);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 10, adSlot.api_strategy_index);
            this.a.encodeWithTag(protoWriter, 11, adSlot.sdk_cached_ads);
            ProtoAdapter.UINT32.asRepeated().encodeWithTag(protoWriter, 12, adSlot.creative_type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 13, adSlot.algorithm_floor);
            this.b.encodeWithTag(protoWriter, 14, adSlot.ext);
            this.c.encodeWithTag(protoWriter, 15, adSlot.ad_caches);
            Video.ADAPTER.encodeWithTag(protoWriter, 16, adSlot.video);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 17, adSlot.expected_floor);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 18, adSlot.auto_click_mode);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 19, adSlot.settlement_mode);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 20, adSlot.alg_bid_type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 21, adSlot.ecpm_target);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 22, adSlot.settlement_setting);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 23, adSlot.commer_ecpm);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 24, adSlot.adx_ecpm);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 25, adSlot.commer_ecpm_state);
            RequestNativeAd.ADAPTER.encodeWithTag(protoWriter, 26, adSlot.native_ad);
            ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 27, adSlot.b_seat);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 28, adSlot.ad_count);
            protoWriter.writeBytes(adSlot.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(AdSlot adSlot) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, adSlot.adslot_id) + Size.ADAPTER.encodedSizeWithTag(2, adSlot.adslot_size) + ProtoAdapter.UINT32.asRepeated().encodedSizeWithTag(3, adSlot.adslot_type) + ProtoAdapter.UINT32.encodedSizeWithTag(4, adSlot.bidfloor) + ProtoAdapter.STRING.encodedSizeWithTag(5, adSlot.vid) + ProtoAdapter.STRING.encodedSizeWithTag(6, adSlot.latest_camp_id) + ProtoAdapter.STRING.encodedSizeWithTag(7, adSlot.latest_crid) + ProtoAdapter.UINT32.asRepeated().encodedSizeWithTag(8, adSlot.material_type) + ProtoAdapter.UINT32.encodedSizeWithTag(9, adSlot.sdk_strategy_index) + ProtoAdapter.UINT32.encodedSizeWithTag(10, adSlot.api_strategy_index) + this.a.encodedSizeWithTag(11, adSlot.sdk_cached_ads) + ProtoAdapter.UINT32.asRepeated().encodedSizeWithTag(12, adSlot.creative_type) + ProtoAdapter.UINT32.encodedSizeWithTag(13, adSlot.algorithm_floor) + this.b.encodedSizeWithTag(14, adSlot.ext) + this.c.encodedSizeWithTag(15, adSlot.ad_caches) + Video.ADAPTER.encodedSizeWithTag(16, adSlot.video) + ProtoAdapter.UINT32.encodedSizeWithTag(17, adSlot.expected_floor) + ProtoAdapter.UINT32.encodedSizeWithTag(18, adSlot.auto_click_mode) + ProtoAdapter.UINT32.encodedSizeWithTag(19, adSlot.settlement_mode) + ProtoAdapter.UINT32.encodedSizeWithTag(20, adSlot.alg_bid_type) + ProtoAdapter.UINT32.encodedSizeWithTag(21, adSlot.ecpm_target) + ProtoAdapter.UINT32.encodedSizeWithTag(22, adSlot.settlement_setting) + ProtoAdapter.UINT32.encodedSizeWithTag(23, adSlot.commer_ecpm) + ProtoAdapter.UINT32.encodedSizeWithTag(24, adSlot.adx_ecpm) + ProtoAdapter.UINT32.encodedSizeWithTag(25, adSlot.commer_ecpm_state) + RequestNativeAd.ADAPTER.encodedSizeWithTag(26, adSlot.native_ad) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(27, adSlot.b_seat) + ProtoAdapter.UINT32.encodedSizeWithTag(28, adSlot.ad_count) + adSlot.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public AdSlot redact(AdSlot adSlot) {
            Builder builderNewBuilder = adSlot.newBuilder();
            if (builderNewBuilder.adslot_size != null) {
                builderNewBuilder.adslot_size = Size.ADAPTER.redact(builderNewBuilder.adslot_size);
            }
            Internal.redactElements(builderNewBuilder.ad_caches, AdCache.ADAPTER);
            if (builderNewBuilder.video != null) {
                builderNewBuilder.video = Video.ADAPTER.redact(builderNewBuilder.video);
            }
            if (builderNewBuilder.native_ad != null) {
                builderNewBuilder.native_ad = RequestNativeAd.ADAPTER.redact(builderNewBuilder.native_ad);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public AdSlot(String str, Size size, List<Integer> list, Integer num, String str2, String str3, String str4, List<Integer> list2, Integer num2, Integer num3, Map<String, String> map, List<Integer> list3, Integer num4, Map<String, String> map2, Map<String, AdCache> map3, Video video, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, RequestNativeAd requestNativeAd, List<String> list4, Integer num14) {
        this(str, size, list, num, str2, str3, str4, list2, num2, num3, map, list3, num4, map2, map3, video, num5, num6, num7, num8, num9, num10, num11, num12, num13, requestNativeAd, list4, num14, ByteString.EMPTY);
    }

    public AdSlot(String str, Size size, List<Integer> list, Integer num, String str2, String str3, String str4, List<Integer> list2, Integer num2, Integer num3, Map<String, String> map, List<Integer> list3, Integer num4, Map<String, String> map2, Map<String, AdCache> map3, Video video, Integer num5, Integer num6, Integer num7, Integer num8, Integer num9, Integer num10, Integer num11, Integer num12, Integer num13, RequestNativeAd requestNativeAd, List<String> list4, Integer num14, ByteString byteString) {
        super(ADAPTER, byteString);
        this.adslot_id = str;
        this.adslot_size = size;
        this.adslot_type = Internal.immutableCopyOf("adslot_type", list);
        this.bidfloor = num;
        this.vid = str2;
        this.latest_camp_id = str3;
        this.latest_crid = str4;
        this.material_type = Internal.immutableCopyOf("material_type", list2);
        this.sdk_strategy_index = num2;
        this.api_strategy_index = num3;
        this.sdk_cached_ads = Internal.immutableCopyOf("sdk_cached_ads", map);
        this.creative_type = Internal.immutableCopyOf("creative_type", list3);
        this.algorithm_floor = num4;
        this.ext = Internal.immutableCopyOf(Constants.EXT, map2);
        this.ad_caches = Internal.immutableCopyOf("ad_caches", map3);
        this.video = video;
        this.expected_floor = num5;
        this.auto_click_mode = num6;
        this.settlement_mode = num7;
        this.alg_bid_type = num8;
        this.ecpm_target = num9;
        this.settlement_setting = num10;
        this.commer_ecpm = num11;
        this.adx_ecpm = num12;
        this.commer_ecpm_state = num13;
        this.native_ad = requestNativeAd;
        this.b_seat = Internal.immutableCopyOf("b_seat", list4);
        this.ad_count = num14;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSlot)) {
            return false;
        }
        AdSlot adSlot = (AdSlot) obj;
        return unknownFields().equals(adSlot.unknownFields()) && Internal.equals(this.adslot_id, adSlot.adslot_id) && Internal.equals(this.adslot_size, adSlot.adslot_size) && this.adslot_type.equals(adSlot.adslot_type) && Internal.equals(this.bidfloor, adSlot.bidfloor) && Internal.equals(this.vid, adSlot.vid) && Internal.equals(this.latest_camp_id, adSlot.latest_camp_id) && Internal.equals(this.latest_crid, adSlot.latest_crid) && this.material_type.equals(adSlot.material_type) && Internal.equals(this.sdk_strategy_index, adSlot.sdk_strategy_index) && Internal.equals(this.api_strategy_index, adSlot.api_strategy_index) && this.sdk_cached_ads.equals(adSlot.sdk_cached_ads) && this.creative_type.equals(adSlot.creative_type) && Internal.equals(this.algorithm_floor, adSlot.algorithm_floor) && this.ext.equals(adSlot.ext) && this.ad_caches.equals(adSlot.ad_caches) && Internal.equals(this.video, adSlot.video) && Internal.equals(this.expected_floor, adSlot.expected_floor) && Internal.equals(this.auto_click_mode, adSlot.auto_click_mode) && Internal.equals(this.settlement_mode, adSlot.settlement_mode) && Internal.equals(this.alg_bid_type, adSlot.alg_bid_type) && Internal.equals(this.ecpm_target, adSlot.ecpm_target) && Internal.equals(this.settlement_setting, adSlot.settlement_setting) && Internal.equals(this.commer_ecpm, adSlot.commer_ecpm) && Internal.equals(this.adx_ecpm, adSlot.adx_ecpm) && Internal.equals(this.commer_ecpm_state, adSlot.commer_ecpm_state) && Internal.equals(this.native_ad, adSlot.native_ad) && this.b_seat.equals(adSlot.b_seat) && Internal.equals(this.ad_count, adSlot.ad_count);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.adslot_id != null ? this.adslot_id.hashCode() : 0)) * 37) + (this.adslot_size != null ? this.adslot_size.hashCode() : 0)) * 37) + this.adslot_type.hashCode()) * 37) + (this.bidfloor != null ? this.bidfloor.hashCode() : 0)) * 37) + (this.vid != null ? this.vid.hashCode() : 0)) * 37) + (this.latest_camp_id != null ? this.latest_camp_id.hashCode() : 0)) * 37) + (this.latest_crid != null ? this.latest_crid.hashCode() : 0)) * 37) + this.material_type.hashCode()) * 37) + (this.sdk_strategy_index != null ? this.sdk_strategy_index.hashCode() : 0)) * 37) + (this.api_strategy_index != null ? this.api_strategy_index.hashCode() : 0)) * 37) + this.sdk_cached_ads.hashCode()) * 37) + this.creative_type.hashCode()) * 37) + (this.algorithm_floor != null ? this.algorithm_floor.hashCode() : 0)) * 37) + this.ext.hashCode()) * 37) + this.ad_caches.hashCode()) * 37) + (this.video != null ? this.video.hashCode() : 0)) * 37) + (this.expected_floor != null ? this.expected_floor.hashCode() : 0)) * 37) + (this.auto_click_mode != null ? this.auto_click_mode.hashCode() : 0)) * 37) + (this.settlement_mode != null ? this.settlement_mode.hashCode() : 0)) * 37) + (this.alg_bid_type != null ? this.alg_bid_type.hashCode() : 0)) * 37) + (this.ecpm_target != null ? this.ecpm_target.hashCode() : 0)) * 37) + (this.settlement_setting != null ? this.settlement_setting.hashCode() : 0)) * 37) + (this.commer_ecpm != null ? this.commer_ecpm.hashCode() : 0)) * 37) + (this.adx_ecpm != null ? this.adx_ecpm.hashCode() : 0)) * 37) + (this.commer_ecpm_state != null ? this.commer_ecpm_state.hashCode() : 0)) * 37) + (this.native_ad != null ? this.native_ad.hashCode() : 0)) * 37) + this.b_seat.hashCode()) * 37) + (this.ad_count != null ? this.ad_count.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.adslot_id = this.adslot_id;
        builder.adslot_size = this.adslot_size;
        builder.adslot_type = Internal.copyOf("adslot_type", this.adslot_type);
        builder.bidfloor = this.bidfloor;
        builder.vid = this.vid;
        builder.latest_camp_id = this.latest_camp_id;
        builder.latest_crid = this.latest_crid;
        builder.material_type = Internal.copyOf("material_type", this.material_type);
        builder.sdk_strategy_index = this.sdk_strategy_index;
        builder.api_strategy_index = this.api_strategy_index;
        builder.sdk_cached_ads = Internal.copyOf("sdk_cached_ads", this.sdk_cached_ads);
        builder.creative_type = Internal.copyOf("creative_type", this.creative_type);
        builder.algorithm_floor = this.algorithm_floor;
        builder.ext = Internal.copyOf(Constants.EXT, this.ext);
        builder.ad_caches = Internal.copyOf("ad_caches", this.ad_caches);
        builder.video = this.video;
        builder.expected_floor = this.expected_floor;
        builder.auto_click_mode = this.auto_click_mode;
        builder.settlement_mode = this.settlement_mode;
        builder.alg_bid_type = this.alg_bid_type;
        builder.ecpm_target = this.ecpm_target;
        builder.settlement_setting = this.settlement_setting;
        builder.commer_ecpm = this.commer_ecpm;
        builder.adx_ecpm = this.adx_ecpm;
        builder.commer_ecpm_state = this.commer_ecpm_state;
        builder.native_ad = this.native_ad;
        builder.b_seat = Internal.copyOf("b_seat", this.b_seat);
        builder.ad_count = this.ad_count;
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
        if (this.adslot_size != null) {
            sb.append(", adslot_size=");
            sb.append(this.adslot_size);
        }
        if (!this.adslot_type.isEmpty()) {
            sb.append(", adslot_type=");
            sb.append(this.adslot_type);
        }
        if (this.bidfloor != null) {
            sb.append(", bidfloor=");
            sb.append(this.bidfloor);
        }
        if (this.vid != null) {
            sb.append(", vid=");
            sb.append(this.vid);
        }
        if (this.latest_camp_id != null) {
            sb.append(", latest_camp_id=");
            sb.append(this.latest_camp_id);
        }
        if (this.latest_crid != null) {
            sb.append(", latest_crid=");
            sb.append(this.latest_crid);
        }
        if (!this.material_type.isEmpty()) {
            sb.append(", material_type=");
            sb.append(this.material_type);
        }
        if (this.sdk_strategy_index != null) {
            sb.append(", sdk_strategy_index=");
            sb.append(this.sdk_strategy_index);
        }
        if (this.api_strategy_index != null) {
            sb.append(", api_strategy_index=");
            sb.append(this.api_strategy_index);
        }
        if (!this.sdk_cached_ads.isEmpty()) {
            sb.append(", sdk_cached_ads=");
            sb.append(this.sdk_cached_ads);
        }
        if (!this.creative_type.isEmpty()) {
            sb.append(", creative_type=");
            sb.append(this.creative_type);
        }
        if (this.algorithm_floor != null) {
            sb.append(", algorithm_floor=");
            sb.append(this.algorithm_floor);
        }
        if (!this.ext.isEmpty()) {
            sb.append(", ext=");
            sb.append(this.ext);
        }
        if (!this.ad_caches.isEmpty()) {
            sb.append(", ad_caches=");
            sb.append(this.ad_caches);
        }
        if (this.video != null) {
            sb.append(", video=");
            sb.append(this.video);
        }
        if (this.expected_floor != null) {
            sb.append(", expected_floor=");
            sb.append(this.expected_floor);
        }
        if (this.auto_click_mode != null) {
            sb.append(", auto_click_mode=");
            sb.append(this.auto_click_mode);
        }
        if (this.settlement_mode != null) {
            sb.append(", settlement_mode=");
            sb.append(this.settlement_mode);
        }
        if (this.alg_bid_type != null) {
            sb.append(", alg_bid_type=");
            sb.append(this.alg_bid_type);
        }
        if (this.ecpm_target != null) {
            sb.append(", ecpm_target=");
            sb.append(this.ecpm_target);
        }
        if (this.settlement_setting != null) {
            sb.append(", settlement_setting=");
            sb.append(this.settlement_setting);
        }
        if (this.commer_ecpm != null) {
            sb.append(", commer_ecpm=");
            sb.append(this.commer_ecpm);
        }
        if (this.adx_ecpm != null) {
            sb.append(", adx_ecpm=");
            sb.append(this.adx_ecpm);
        }
        if (this.commer_ecpm_state != null) {
            sb.append(", commer_ecpm_state=");
            sb.append(this.commer_ecpm_state);
        }
        if (this.native_ad != null) {
            sb.append(", native_ad=");
            sb.append(this.native_ad);
        }
        if (!this.b_seat.isEmpty()) {
            sb.append(", b_seat=");
            sb.append(this.b_seat);
        }
        if (this.ad_count != null) {
            sb.append(", ad_count=");
            sb.append(this.ad_count);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "AdSlot{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
