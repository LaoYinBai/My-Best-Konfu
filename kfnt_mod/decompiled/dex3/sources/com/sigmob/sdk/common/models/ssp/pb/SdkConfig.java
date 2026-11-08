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
import com.ss.android.socialbase.appdownloader.constants.Constants;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class SdkConfig extends AndroidMessage<SdkConfig, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.AntiFraudLogConfig#ADAPTER", tag = 8)
    public final AntiFraudLogConfig anti_fraud_log;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 20)
    public final Integer apk_expired_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer appOrientation;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 16)
    public final Integer auto_load_interval;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer configRefresh;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", label = WireField.Label.REPEATED, tag = 15)
    public final List<Integer> dclog_blacklist;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 6)
    public final Boolean disableUpAppInfo;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 21)
    public final Boolean disable_up_ip;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 17)
    public final Boolean disable_up_location;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 18)
    public final Integer disable_up_oaid;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 23)
    public final Boolean enable_attribution_update;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 26)
    public final Boolean enable_debug_level;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 19)
    public final Boolean enable_permission;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 24)
    public final Boolean enable_report_crash;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.CommonEndpointsConfig#ADAPTER", tag = 1)
    public final CommonEndpointsConfig endpoints;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 22)
    public final List<String> ip_names;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 9)
    public final Boolean is_gdpr_region;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 13)
    public final Integer max_send_log_records;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.NativeConfig#ADAPTER", tag = 27)
    public final NativeConfig native_;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 25)
    public final Boolean oaid_api_is_disable;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 7)
    public final Integer report_log;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.RvConfig#ADAPTER", tag = 4)
    public final RvConfig rv;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 14)
    public final Integer send_log_interval;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.SplashConfig#ADAPTER", tag = 5)
    public final SplashConfig splash;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 10)
    public final Integer tracking_expiration_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 11)
    public final Integer tracking_retry_interval;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 12)
    public final Integer up_wifi_list_interval;
    public static final ProtoAdapter<SdkConfig> ADAPTER = new ProtoAdapter_SdkConfig();
    public static final Parcelable.Creator<SdkConfig> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CONFIGREFRESH = 0;
    public static final Integer DEFAULT_APPORIENTATION = 0;
    public static final Boolean DEFAULT_DISABLEUPAPPINFO = false;
    public static final Integer DEFAULT_REPORT_LOG = 0;
    public static final Boolean DEFAULT_IS_GDPR_REGION = false;
    public static final Integer DEFAULT_TRACKING_EXPIRATION_TIME = Integer.valueOf(Constants.MAX_RETRY_AFTER);
    public static final Integer DEFAULT_TRACKING_RETRY_INTERVAL = 180;
    public static final Integer DEFAULT_UP_WIFI_LIST_INTERVAL = 0;
    public static final Integer DEFAULT_MAX_SEND_LOG_RECORDS = 10;
    public static final Integer DEFAULT_SEND_LOG_INTERVAL = 3;
    public static final Integer DEFAULT_AUTO_LOAD_INTERVAL = 0;
    public static final Boolean DEFAULT_DISABLE_UP_LOCATION = false;
    public static final Integer DEFAULT_DISABLE_UP_OAID = 0;
    public static final Boolean DEFAULT_ENABLE_PERMISSION = false;
    public static final Integer DEFAULT_APK_EXPIRED_TIME = 0;
    public static final Boolean DEFAULT_DISABLE_UP_IP = false;
    public static final Boolean DEFAULT_ENABLE_ATTRIBUTION_UPDATE = false;
    public static final Boolean DEFAULT_ENABLE_REPORT_CRASH = false;
    public static final Boolean DEFAULT_OAID_API_IS_DISABLE = false;
    public static final Boolean DEFAULT_ENABLE_DEBUG_LEVEL = false;

    public static final class Builder extends Message.Builder<SdkConfig, Builder> {
        public AntiFraudLogConfig anti_fraud_log;
        public CommonEndpointsConfig endpoints;
        public NativeConfig native_;
        public RvConfig rv;
        public SplashConfig splash;
        public Integer configRefresh = SdkConfig.DEFAULT_CONFIGREFRESH;
        public Integer appOrientation = SdkConfig.DEFAULT_APPORIENTATION;
        public Boolean disableUpAppInfo = SdkConfig.DEFAULT_DISABLEUPAPPINFO;
        public Integer report_log = SdkConfig.DEFAULT_REPORT_LOG;
        public Boolean is_gdpr_region = SdkConfig.DEFAULT_IS_GDPR_REGION;
        public Integer tracking_expiration_time = SdkConfig.DEFAULT_TRACKING_EXPIRATION_TIME;
        public Integer tracking_retry_interval = SdkConfig.DEFAULT_TRACKING_RETRY_INTERVAL;
        public Integer up_wifi_list_interval = SdkConfig.DEFAULT_UP_WIFI_LIST_INTERVAL;
        public Integer max_send_log_records = SdkConfig.DEFAULT_MAX_SEND_LOG_RECORDS;
        public Integer send_log_interval = SdkConfig.DEFAULT_SEND_LOG_INTERVAL;
        public Integer auto_load_interval = SdkConfig.DEFAULT_AUTO_LOAD_INTERVAL;
        public Boolean disable_up_location = SdkConfig.DEFAULT_DISABLE_UP_LOCATION;
        public Integer disable_up_oaid = SdkConfig.DEFAULT_DISABLE_UP_OAID;
        public Boolean enable_permission = SdkConfig.DEFAULT_ENABLE_PERMISSION;
        public Integer apk_expired_time = SdkConfig.DEFAULT_APK_EXPIRED_TIME;
        public Boolean disable_up_ip = SdkConfig.DEFAULT_DISABLE_UP_IP;
        public Boolean enable_attribution_update = SdkConfig.DEFAULT_ENABLE_ATTRIBUTION_UPDATE;
        public Boolean enable_report_crash = SdkConfig.DEFAULT_ENABLE_REPORT_CRASH;
        public Boolean oaid_api_is_disable = SdkConfig.DEFAULT_OAID_API_IS_DISABLE;
        public Boolean enable_debug_level = SdkConfig.DEFAULT_ENABLE_DEBUG_LEVEL;
        public List<Integer> dclog_blacklist = Internal.newMutableList();
        public List<String> ip_names = Internal.newMutableList();

        public Builder anti_fraud_log(AntiFraudLogConfig antiFraudLogConfig) {
            this.anti_fraud_log = antiFraudLogConfig;
            return this;
        }

        public Builder apk_expired_time(Integer num) {
            this.apk_expired_time = num;
            return this;
        }

        public Builder appOrientation(Integer num) {
            this.appOrientation = num;
            return this;
        }

        public Builder auto_load_interval(Integer num) {
            this.auto_load_interval = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public SdkConfig build() {
            return new SdkConfig(this.endpoints, this.configRefresh, this.appOrientation, this.rv, this.splash, this.disableUpAppInfo, this.report_log, this.anti_fraud_log, this.is_gdpr_region, this.tracking_expiration_time, this.tracking_retry_interval, this.up_wifi_list_interval, this.max_send_log_records, this.send_log_interval, this.dclog_blacklist, this.auto_load_interval, this.disable_up_location, this.disable_up_oaid, this.enable_permission, this.apk_expired_time, this.disable_up_ip, this.ip_names, this.enable_attribution_update, this.enable_report_crash, this.oaid_api_is_disable, this.enable_debug_level, this.native_, super.buildUnknownFields());
        }

        public Builder configRefresh(Integer num) {
            this.configRefresh = num;
            return this;
        }

        public Builder dclog_blacklist(List<Integer> list) {
            Internal.checkElementsNotNull(list);
            this.dclog_blacklist = list;
            return this;
        }

        public Builder disableUpAppInfo(Boolean bool) {
            this.disableUpAppInfo = bool;
            return this;
        }

        public Builder disable_up_ip(Boolean bool) {
            this.disable_up_ip = bool;
            return this;
        }

        public Builder disable_up_location(Boolean bool) {
            this.disable_up_location = bool;
            return this;
        }

        public Builder disable_up_oaid(Integer num) {
            this.disable_up_oaid = num;
            return this;
        }

        public Builder enable_attribution_update(Boolean bool) {
            this.enable_attribution_update = bool;
            return this;
        }

        public Builder enable_debug_level(Boolean bool) {
            this.enable_debug_level = bool;
            return this;
        }

        public Builder enable_permission(Boolean bool) {
            this.enable_permission = bool;
            return this;
        }

        public Builder enable_report_crash(Boolean bool) {
            this.enable_report_crash = bool;
            return this;
        }

        public Builder endpoints(CommonEndpointsConfig commonEndpointsConfig) {
            this.endpoints = commonEndpointsConfig;
            return this;
        }

        public Builder ip_names(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.ip_names = list;
            return this;
        }

        public Builder is_gdpr_region(Boolean bool) {
            this.is_gdpr_region = bool;
            return this;
        }

        public Builder max_send_log_records(Integer num) {
            this.max_send_log_records = num;
            return this;
        }

        public Builder native_(NativeConfig nativeConfig) {
            this.native_ = nativeConfig;
            return this;
        }

        public Builder oaid_api_is_disable(Boolean bool) {
            this.oaid_api_is_disable = bool;
            return this;
        }

        public Builder report_log(Integer num) {
            this.report_log = num;
            return this;
        }

        public Builder rv(RvConfig rvConfig) {
            this.rv = rvConfig;
            return this;
        }

        public Builder send_log_interval(Integer num) {
            this.send_log_interval = num;
            return this;
        }

        public Builder splash(SplashConfig splashConfig) {
            this.splash = splashConfig;
            return this;
        }

        public Builder tracking_expiration_time(Integer num) {
            this.tracking_expiration_time = num;
            return this;
        }

        public Builder tracking_retry_interval(Integer num) {
            this.tracking_retry_interval = num;
            return this;
        }

        public Builder up_wifi_list_interval(Integer num) {
            this.up_wifi_list_interval = num;
            return this;
        }
    }

    private static final class ProtoAdapter_SdkConfig extends ProtoAdapter<SdkConfig> {
        public ProtoAdapter_SdkConfig() {
            super(FieldEncoding.LENGTH_DELIMITED, SdkConfig.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SdkConfig decode(ProtoReader protoReader) {
            List list;
            ProtoAdapter protoAdapter;
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
                        builder.endpoints(CommonEndpointsConfig.ADAPTER.decode(protoReader));
                        continue;
                    case 2:
                        builder.configRefresh(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 3:
                        builder.appOrientation(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 4:
                        builder.rv(RvConfig.ADAPTER.decode(protoReader));
                        continue;
                    case 5:
                        builder.splash(SplashConfig.ADAPTER.decode(protoReader));
                        continue;
                    case 6:
                        builder.disableUpAppInfo(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 7:
                        builder.report_log(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 8:
                        builder.anti_fraud_log(AntiFraudLogConfig.ADAPTER.decode(protoReader));
                        continue;
                    case 9:
                        builder.is_gdpr_region(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 10:
                        builder.tracking_expiration_time(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 11:
                        builder.tracking_retry_interval(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 12:
                        builder.up_wifi_list_interval(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 13:
                        builder.max_send_log_records(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 14:
                        builder.send_log_interval(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 15:
                        list = builder.dclog_blacklist;
                        protoAdapter = ProtoAdapter.UINT32;
                        break;
                    case 16:
                        builder.auto_load_interval(ProtoAdapter.INT32.decode(protoReader));
                        continue;
                    case 17:
                        builder.disable_up_location(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 18:
                        builder.disable_up_oaid(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 19:
                        builder.enable_permission(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 20:
                        builder.apk_expired_time(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 21:
                        builder.disable_up_ip(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 22:
                        list = builder.ip_names;
                        protoAdapter = ProtoAdapter.STRING;
                        break;
                    case 23:
                        builder.enable_attribution_update(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 24:
                        builder.enable_report_crash(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 25:
                        builder.oaid_api_is_disable(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 26:
                        builder.enable_debug_level(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 27:
                        builder.native_(NativeConfig.ADAPTER.decode(protoReader));
                        continue;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        continue;
                }
                list.add(protoAdapter.decode(protoReader));
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SdkConfig sdkConfig) {
            CommonEndpointsConfig.ADAPTER.encodeWithTag(protoWriter, 1, sdkConfig.endpoints);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, sdkConfig.configRefresh);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, sdkConfig.appOrientation);
            RvConfig.ADAPTER.encodeWithTag(protoWriter, 4, sdkConfig.rv);
            SplashConfig.ADAPTER.encodeWithTag(protoWriter, 5, sdkConfig.splash);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 6, sdkConfig.disableUpAppInfo);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 7, sdkConfig.report_log);
            AntiFraudLogConfig.ADAPTER.encodeWithTag(protoWriter, 8, sdkConfig.anti_fraud_log);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 9, sdkConfig.is_gdpr_region);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 10, sdkConfig.tracking_expiration_time);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 11, sdkConfig.tracking_retry_interval);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 12, sdkConfig.up_wifi_list_interval);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 13, sdkConfig.max_send_log_records);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 14, sdkConfig.send_log_interval);
            ProtoAdapter.UINT32.asRepeated().encodeWithTag(protoWriter, 15, sdkConfig.dclog_blacklist);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 16, sdkConfig.auto_load_interval);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 17, sdkConfig.disable_up_location);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 18, sdkConfig.disable_up_oaid);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 19, sdkConfig.enable_permission);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 20, sdkConfig.apk_expired_time);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 21, sdkConfig.disable_up_ip);
            ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 22, sdkConfig.ip_names);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 23, sdkConfig.enable_attribution_update);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 24, sdkConfig.enable_report_crash);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 25, sdkConfig.oaid_api_is_disable);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 26, sdkConfig.enable_debug_level);
            NativeConfig.ADAPTER.encodeWithTag(protoWriter, 27, sdkConfig.native_);
            protoWriter.writeBytes(sdkConfig.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SdkConfig sdkConfig) {
            return CommonEndpointsConfig.ADAPTER.encodedSizeWithTag(1, sdkConfig.endpoints) + ProtoAdapter.UINT32.encodedSizeWithTag(2, sdkConfig.configRefresh) + ProtoAdapter.UINT32.encodedSizeWithTag(3, sdkConfig.appOrientation) + RvConfig.ADAPTER.encodedSizeWithTag(4, sdkConfig.rv) + SplashConfig.ADAPTER.encodedSizeWithTag(5, sdkConfig.splash) + ProtoAdapter.BOOL.encodedSizeWithTag(6, sdkConfig.disableUpAppInfo) + ProtoAdapter.UINT32.encodedSizeWithTag(7, sdkConfig.report_log) + AntiFraudLogConfig.ADAPTER.encodedSizeWithTag(8, sdkConfig.anti_fraud_log) + ProtoAdapter.BOOL.encodedSizeWithTag(9, sdkConfig.is_gdpr_region) + ProtoAdapter.UINT32.encodedSizeWithTag(10, sdkConfig.tracking_expiration_time) + ProtoAdapter.UINT32.encodedSizeWithTag(11, sdkConfig.tracking_retry_interval) + ProtoAdapter.UINT32.encodedSizeWithTag(12, sdkConfig.up_wifi_list_interval) + ProtoAdapter.UINT32.encodedSizeWithTag(13, sdkConfig.max_send_log_records) + ProtoAdapter.UINT32.encodedSizeWithTag(14, sdkConfig.send_log_interval) + ProtoAdapter.UINT32.asRepeated().encodedSizeWithTag(15, sdkConfig.dclog_blacklist) + ProtoAdapter.INT32.encodedSizeWithTag(16, sdkConfig.auto_load_interval) + ProtoAdapter.BOOL.encodedSizeWithTag(17, sdkConfig.disable_up_location) + ProtoAdapter.UINT32.encodedSizeWithTag(18, sdkConfig.disable_up_oaid) + ProtoAdapter.BOOL.encodedSizeWithTag(19, sdkConfig.enable_permission) + ProtoAdapter.UINT32.encodedSizeWithTag(20, sdkConfig.apk_expired_time) + ProtoAdapter.BOOL.encodedSizeWithTag(21, sdkConfig.disable_up_ip) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(22, sdkConfig.ip_names) + ProtoAdapter.BOOL.encodedSizeWithTag(23, sdkConfig.enable_attribution_update) + ProtoAdapter.BOOL.encodedSizeWithTag(24, sdkConfig.enable_report_crash) + ProtoAdapter.BOOL.encodedSizeWithTag(25, sdkConfig.oaid_api_is_disable) + ProtoAdapter.BOOL.encodedSizeWithTag(26, sdkConfig.enable_debug_level) + NativeConfig.ADAPTER.encodedSizeWithTag(27, sdkConfig.native_) + sdkConfig.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SdkConfig redact(SdkConfig sdkConfig) {
            Builder builderNewBuilder = sdkConfig.newBuilder();
            if (builderNewBuilder.endpoints != null) {
                builderNewBuilder.endpoints = CommonEndpointsConfig.ADAPTER.redact(builderNewBuilder.endpoints);
            }
            if (builderNewBuilder.rv != null) {
                builderNewBuilder.rv = RvConfig.ADAPTER.redact(builderNewBuilder.rv);
            }
            if (builderNewBuilder.splash != null) {
                builderNewBuilder.splash = SplashConfig.ADAPTER.redact(builderNewBuilder.splash);
            }
            if (builderNewBuilder.anti_fraud_log != null) {
                builderNewBuilder.anti_fraud_log = AntiFraudLogConfig.ADAPTER.redact(builderNewBuilder.anti_fraud_log);
            }
            if (builderNewBuilder.native_ != null) {
                builderNewBuilder.native_ = NativeConfig.ADAPTER.redact(builderNewBuilder.native_);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SdkConfig(CommonEndpointsConfig commonEndpointsConfig, Integer num, Integer num2, RvConfig rvConfig, SplashConfig splashConfig, Boolean bool, Integer num3, AntiFraudLogConfig antiFraudLogConfig, Boolean bool2, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, List<Integer> list, Integer num9, Boolean bool3, Integer num10, Boolean bool4, Integer num11, Boolean bool5, List<String> list2, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, NativeConfig nativeConfig) {
        this(commonEndpointsConfig, num, num2, rvConfig, splashConfig, bool, num3, antiFraudLogConfig, bool2, num4, num5, num6, num7, num8, list, num9, bool3, num10, bool4, num11, bool5, list2, bool6, bool7, bool8, bool9, nativeConfig, ByteString.EMPTY);
    }

    public SdkConfig(CommonEndpointsConfig commonEndpointsConfig, Integer num, Integer num2, RvConfig rvConfig, SplashConfig splashConfig, Boolean bool, Integer num3, AntiFraudLogConfig antiFraudLogConfig, Boolean bool2, Integer num4, Integer num5, Integer num6, Integer num7, Integer num8, List<Integer> list, Integer num9, Boolean bool3, Integer num10, Boolean bool4, Integer num11, Boolean bool5, List<String> list2, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, NativeConfig nativeConfig, ByteString byteString) {
        super(ADAPTER, byteString);
        this.endpoints = commonEndpointsConfig;
        this.configRefresh = num;
        this.appOrientation = num2;
        this.rv = rvConfig;
        this.splash = splashConfig;
        this.disableUpAppInfo = bool;
        this.report_log = num3;
        this.anti_fraud_log = antiFraudLogConfig;
        this.is_gdpr_region = bool2;
        this.tracking_expiration_time = num4;
        this.tracking_retry_interval = num5;
        this.up_wifi_list_interval = num6;
        this.max_send_log_records = num7;
        this.send_log_interval = num8;
        this.dclog_blacklist = Internal.immutableCopyOf("dclog_blacklist", list);
        this.auto_load_interval = num9;
        this.disable_up_location = bool3;
        this.disable_up_oaid = num10;
        this.enable_permission = bool4;
        this.apk_expired_time = num11;
        this.disable_up_ip = bool5;
        this.ip_names = Internal.immutableCopyOf("ip_names", list2);
        this.enable_attribution_update = bool6;
        this.enable_report_crash = bool7;
        this.oaid_api_is_disable = bool8;
        this.enable_debug_level = bool9;
        this.native_ = nativeConfig;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SdkConfig)) {
            return false;
        }
        SdkConfig sdkConfig = (SdkConfig) obj;
        return unknownFields().equals(sdkConfig.unknownFields()) && Internal.equals(this.endpoints, sdkConfig.endpoints) && Internal.equals(this.configRefresh, sdkConfig.configRefresh) && Internal.equals(this.appOrientation, sdkConfig.appOrientation) && Internal.equals(this.rv, sdkConfig.rv) && Internal.equals(this.splash, sdkConfig.splash) && Internal.equals(this.disableUpAppInfo, sdkConfig.disableUpAppInfo) && Internal.equals(this.report_log, sdkConfig.report_log) && Internal.equals(this.anti_fraud_log, sdkConfig.anti_fraud_log) && Internal.equals(this.is_gdpr_region, sdkConfig.is_gdpr_region) && Internal.equals(this.tracking_expiration_time, sdkConfig.tracking_expiration_time) && Internal.equals(this.tracking_retry_interval, sdkConfig.tracking_retry_interval) && Internal.equals(this.up_wifi_list_interval, sdkConfig.up_wifi_list_interval) && Internal.equals(this.max_send_log_records, sdkConfig.max_send_log_records) && Internal.equals(this.send_log_interval, sdkConfig.send_log_interval) && this.dclog_blacklist.equals(sdkConfig.dclog_blacklist) && Internal.equals(this.auto_load_interval, sdkConfig.auto_load_interval) && Internal.equals(this.disable_up_location, sdkConfig.disable_up_location) && Internal.equals(this.disable_up_oaid, sdkConfig.disable_up_oaid) && Internal.equals(this.enable_permission, sdkConfig.enable_permission) && Internal.equals(this.apk_expired_time, sdkConfig.apk_expired_time) && Internal.equals(this.disable_up_ip, sdkConfig.disable_up_ip) && this.ip_names.equals(sdkConfig.ip_names) && Internal.equals(this.enable_attribution_update, sdkConfig.enable_attribution_update) && Internal.equals(this.enable_report_crash, sdkConfig.enable_report_crash) && Internal.equals(this.oaid_api_is_disable, sdkConfig.oaid_api_is_disable) && Internal.equals(this.enable_debug_level, sdkConfig.enable_debug_level) && Internal.equals(this.native_, sdkConfig.native_);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.endpoints != null ? this.endpoints.hashCode() : 0)) * 37) + (this.configRefresh != null ? this.configRefresh.hashCode() : 0)) * 37) + (this.appOrientation != null ? this.appOrientation.hashCode() : 0)) * 37) + (this.rv != null ? this.rv.hashCode() : 0)) * 37) + (this.splash != null ? this.splash.hashCode() : 0)) * 37) + (this.disableUpAppInfo != null ? this.disableUpAppInfo.hashCode() : 0)) * 37) + (this.report_log != null ? this.report_log.hashCode() : 0)) * 37) + (this.anti_fraud_log != null ? this.anti_fraud_log.hashCode() : 0)) * 37) + (this.is_gdpr_region != null ? this.is_gdpr_region.hashCode() : 0)) * 37) + (this.tracking_expiration_time != null ? this.tracking_expiration_time.hashCode() : 0)) * 37) + (this.tracking_retry_interval != null ? this.tracking_retry_interval.hashCode() : 0)) * 37) + (this.up_wifi_list_interval != null ? this.up_wifi_list_interval.hashCode() : 0)) * 37) + (this.max_send_log_records != null ? this.max_send_log_records.hashCode() : 0)) * 37) + (this.send_log_interval != null ? this.send_log_interval.hashCode() : 0)) * 37) + this.dclog_blacklist.hashCode()) * 37) + (this.auto_load_interval != null ? this.auto_load_interval.hashCode() : 0)) * 37) + (this.disable_up_location != null ? this.disable_up_location.hashCode() : 0)) * 37) + (this.disable_up_oaid != null ? this.disable_up_oaid.hashCode() : 0)) * 37) + (this.enable_permission != null ? this.enable_permission.hashCode() : 0)) * 37) + (this.apk_expired_time != null ? this.apk_expired_time.hashCode() : 0)) * 37) + (this.disable_up_ip != null ? this.disable_up_ip.hashCode() : 0)) * 37) + this.ip_names.hashCode()) * 37) + (this.enable_attribution_update != null ? this.enable_attribution_update.hashCode() : 0)) * 37) + (this.enable_report_crash != null ? this.enable_report_crash.hashCode() : 0)) * 37) + (this.oaid_api_is_disable != null ? this.oaid_api_is_disable.hashCode() : 0)) * 37) + (this.enable_debug_level != null ? this.enable_debug_level.hashCode() : 0)) * 37) + (this.native_ != null ? this.native_.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.endpoints = this.endpoints;
        builder.configRefresh = this.configRefresh;
        builder.appOrientation = this.appOrientation;
        builder.rv = this.rv;
        builder.splash = this.splash;
        builder.disableUpAppInfo = this.disableUpAppInfo;
        builder.report_log = this.report_log;
        builder.anti_fraud_log = this.anti_fraud_log;
        builder.is_gdpr_region = this.is_gdpr_region;
        builder.tracking_expiration_time = this.tracking_expiration_time;
        builder.tracking_retry_interval = this.tracking_retry_interval;
        builder.up_wifi_list_interval = this.up_wifi_list_interval;
        builder.max_send_log_records = this.max_send_log_records;
        builder.send_log_interval = this.send_log_interval;
        builder.dclog_blacklist = Internal.copyOf("dclog_blacklist", this.dclog_blacklist);
        builder.auto_load_interval = this.auto_load_interval;
        builder.disable_up_location = this.disable_up_location;
        builder.disable_up_oaid = this.disable_up_oaid;
        builder.enable_permission = this.enable_permission;
        builder.apk_expired_time = this.apk_expired_time;
        builder.disable_up_ip = this.disable_up_ip;
        builder.ip_names = Internal.copyOf("ip_names", this.ip_names);
        builder.enable_attribution_update = this.enable_attribution_update;
        builder.enable_report_crash = this.enable_report_crash;
        builder.oaid_api_is_disable = this.oaid_api_is_disable;
        builder.enable_debug_level = this.enable_debug_level;
        builder.native_ = this.native_;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.endpoints != null) {
            sb.append(", endpoints=");
            sb.append(this.endpoints);
        }
        if (this.configRefresh != null) {
            sb.append(", configRefresh=");
            sb.append(this.configRefresh);
        }
        if (this.appOrientation != null) {
            sb.append(", appOrientation=");
            sb.append(this.appOrientation);
        }
        if (this.rv != null) {
            sb.append(", rv=");
            sb.append(this.rv);
        }
        if (this.splash != null) {
            sb.append(", splash=");
            sb.append(this.splash);
        }
        if (this.disableUpAppInfo != null) {
            sb.append(", disableUpAppInfo=");
            sb.append(this.disableUpAppInfo);
        }
        if (this.report_log != null) {
            sb.append(", report_log=");
            sb.append(this.report_log);
        }
        if (this.anti_fraud_log != null) {
            sb.append(", anti_fraud_log=");
            sb.append(this.anti_fraud_log);
        }
        if (this.is_gdpr_region != null) {
            sb.append(", is_gdpr_region=");
            sb.append(this.is_gdpr_region);
        }
        if (this.tracking_expiration_time != null) {
            sb.append(", tracking_expiration_time=");
            sb.append(this.tracking_expiration_time);
        }
        if (this.tracking_retry_interval != null) {
            sb.append(", tracking_retry_interval=");
            sb.append(this.tracking_retry_interval);
        }
        if (this.up_wifi_list_interval != null) {
            sb.append(", up_wifi_list_interval=");
            sb.append(this.up_wifi_list_interval);
        }
        if (this.max_send_log_records != null) {
            sb.append(", max_send_log_records=");
            sb.append(this.max_send_log_records);
        }
        if (this.send_log_interval != null) {
            sb.append(", send_log_interval=");
            sb.append(this.send_log_interval);
        }
        if (!this.dclog_blacklist.isEmpty()) {
            sb.append(", dclog_blacklist=");
            sb.append(this.dclog_blacklist);
        }
        if (this.auto_load_interval != null) {
            sb.append(", auto_load_interval=");
            sb.append(this.auto_load_interval);
        }
        if (this.disable_up_location != null) {
            sb.append(", disable_up_location=");
            sb.append(this.disable_up_location);
        }
        if (this.disable_up_oaid != null) {
            sb.append(", disable_up_oaid=");
            sb.append(this.disable_up_oaid);
        }
        if (this.enable_permission != null) {
            sb.append(", enable_permission=");
            sb.append(this.enable_permission);
        }
        if (this.apk_expired_time != null) {
            sb.append(", apk_expired_time=");
            sb.append(this.apk_expired_time);
        }
        if (this.disable_up_ip != null) {
            sb.append(", disable_up_ip=");
            sb.append(this.disable_up_ip);
        }
        if (!this.ip_names.isEmpty()) {
            sb.append(", ip_names=");
            sb.append(this.ip_names);
        }
        if (this.enable_attribution_update != null) {
            sb.append(", enable_attribution_update=");
            sb.append(this.enable_attribution_update);
        }
        if (this.enable_report_crash != null) {
            sb.append(", enable_report_crash=");
            sb.append(this.enable_report_crash);
        }
        if (this.oaid_api_is_disable != null) {
            sb.append(", oaid_api_is_disable=");
            sb.append(this.oaid_api_is_disable);
        }
        if (this.enable_debug_level != null) {
            sb.append(", enable_debug_level=");
            sb.append(this.enable_debug_level);
        }
        if (this.native_ != null) {
            sb.append(", native=");
            sb.append(this.native_);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SdkConfig{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
