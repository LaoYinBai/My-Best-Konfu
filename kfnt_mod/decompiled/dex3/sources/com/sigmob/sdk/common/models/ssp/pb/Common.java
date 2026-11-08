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
public final class Common extends AndroidMessage<Common, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.AntiFraudLogConfig#ADAPTER", tag = 5)
    public final AntiFraudLogConfig anti_fraud_log;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 12)
    public final Integer auto_load_interval;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer configRefresh;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", label = WireField.Label.REPEATED, tag = 11)
    public final List<Integer> dclog_blacklist;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 18)
    public final Boolean disable_up_location;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 23)
    public final Boolean enable_attribution_update;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 14)
    public final Boolean enable_debug_level;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.CommonEndpointsConfig#ADAPTER", tag = 1)
    public final CommonEndpointsConfig endpoints;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 6)
    public final Boolean is_gdpr_region;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 17)
    public final Integer load_periodTime;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 16)
    public final Integer load_timeout;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 9)
    public final Integer max_send_log_records;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.NativeConfig#ADAPTER", tag = 15)
    public final NativeConfig native_config;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.RvConfig#ADAPTER", tag = 3)
    public final RvConfig rv_config;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 10)
    public final Integer send_log_interval;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.SplashConfig#ADAPTER", tag = 4)
    public final SplashConfig splash_config;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 7)
    public final Integer tracking_expiration_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 8)
    public final Integer tracking_retry_interval;
    public static final ProtoAdapter<Common> ADAPTER = new ProtoAdapter_Common();
    public static final Parcelable.Creator<Common> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CONFIGREFRESH = 0;
    public static final Boolean DEFAULT_IS_GDPR_REGION = false;
    public static final Integer DEFAULT_TRACKING_EXPIRATION_TIME = Integer.valueOf(Constants.MAX_RETRY_AFTER);
    public static final Integer DEFAULT_TRACKING_RETRY_INTERVAL = 180;
    public static final Integer DEFAULT_MAX_SEND_LOG_RECORDS = 10;
    public static final Integer DEFAULT_SEND_LOG_INTERVAL = 3;
    public static final Integer DEFAULT_AUTO_LOAD_INTERVAL = 0;
    public static final Boolean DEFAULT_ENABLE_ATTRIBUTION_UPDATE = false;
    public static final Boolean DEFAULT_ENABLE_DEBUG_LEVEL = false;
    public static final Integer DEFAULT_LOAD_TIMEOUT = 0;
    public static final Integer DEFAULT_LOAD_PERIODTIME = 0;
    public static final Boolean DEFAULT_DISABLE_UP_LOCATION = false;

    public static final class Builder extends Message.Builder<Common, Builder> {
        public AntiFraudLogConfig anti_fraud_log;
        public CommonEndpointsConfig endpoints;
        public NativeConfig native_config;
        public RvConfig rv_config;
        public SplashConfig splash_config;
        public Integer configRefresh = Common.DEFAULT_CONFIGREFRESH;
        public Boolean is_gdpr_region = Common.DEFAULT_IS_GDPR_REGION;
        public Integer tracking_expiration_time = Common.DEFAULT_TRACKING_EXPIRATION_TIME;
        public Integer tracking_retry_interval = Common.DEFAULT_TRACKING_RETRY_INTERVAL;
        public Integer max_send_log_records = Common.DEFAULT_MAX_SEND_LOG_RECORDS;
        public Integer send_log_interval = Common.DEFAULT_SEND_LOG_INTERVAL;
        public Integer auto_load_interval = Common.DEFAULT_AUTO_LOAD_INTERVAL;
        public Boolean enable_attribution_update = Common.DEFAULT_ENABLE_ATTRIBUTION_UPDATE;
        public Boolean enable_debug_level = Common.DEFAULT_ENABLE_DEBUG_LEVEL;
        public Integer load_timeout = Common.DEFAULT_LOAD_TIMEOUT;
        public Integer load_periodTime = Common.DEFAULT_LOAD_PERIODTIME;
        public Boolean disable_up_location = Common.DEFAULT_DISABLE_UP_LOCATION;
        public List<Integer> dclog_blacklist = Internal.newMutableList();

        public Builder anti_fraud_log(AntiFraudLogConfig antiFraudLogConfig) {
            this.anti_fraud_log = antiFraudLogConfig;
            return this;
        }

        public Builder auto_load_interval(Integer num) {
            this.auto_load_interval = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public Common build() {
            return new Common(this.endpoints, this.configRefresh, this.rv_config, this.splash_config, this.anti_fraud_log, this.is_gdpr_region, this.tracking_expiration_time, this.tracking_retry_interval, this.max_send_log_records, this.send_log_interval, this.dclog_blacklist, this.auto_load_interval, this.enable_attribution_update, this.enable_debug_level, this.native_config, this.load_timeout, this.load_periodTime, this.disable_up_location, super.buildUnknownFields());
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

        public Builder disable_up_location(Boolean bool) {
            this.disable_up_location = bool;
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

        public Builder endpoints(CommonEndpointsConfig commonEndpointsConfig) {
            this.endpoints = commonEndpointsConfig;
            return this;
        }

        public Builder is_gdpr_region(Boolean bool) {
            this.is_gdpr_region = bool;
            return this;
        }

        public Builder load_periodTime(Integer num) {
            this.load_periodTime = num;
            return this;
        }

        public Builder load_timeout(Integer num) {
            this.load_timeout = num;
            return this;
        }

        public Builder max_send_log_records(Integer num) {
            this.max_send_log_records = num;
            return this;
        }

        public Builder native_config(NativeConfig nativeConfig) {
            this.native_config = nativeConfig;
            return this;
        }

        public Builder rv_config(RvConfig rvConfig) {
            this.rv_config = rvConfig;
            return this;
        }

        public Builder send_log_interval(Integer num) {
            this.send_log_interval = num;
            return this;
        }

        public Builder splash_config(SplashConfig splashConfig) {
            this.splash_config = splashConfig;
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
    }

    private static final class ProtoAdapter_Common extends ProtoAdapter<Common> {
        public ProtoAdapter_Common() {
            super(FieldEncoding.LENGTH_DELIMITED, Common.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Common decode(ProtoReader protoReader) {
            Builder builder = new Builder();
            long jBeginMessage = protoReader.beginMessage();
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    return builder.build();
                }
                if (iNextTag != 23) {
                    switch (iNextTag) {
                        case 1:
                            builder.endpoints(CommonEndpointsConfig.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.configRefresh(ProtoAdapter.UINT32.decode(protoReader));
                            break;
                        case 3:
                            builder.rv_config(RvConfig.ADAPTER.decode(protoReader));
                            break;
                        case 4:
                            builder.splash_config(SplashConfig.ADAPTER.decode(protoReader));
                            break;
                        case 5:
                            builder.anti_fraud_log(AntiFraudLogConfig.ADAPTER.decode(protoReader));
                            break;
                        case 6:
                            builder.is_gdpr_region(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 7:
                            builder.tracking_expiration_time(ProtoAdapter.UINT32.decode(protoReader));
                            break;
                        case 8:
                            builder.tracking_retry_interval(ProtoAdapter.UINT32.decode(protoReader));
                            break;
                        case 9:
                            builder.max_send_log_records(ProtoAdapter.UINT32.decode(protoReader));
                            break;
                        case 10:
                            builder.send_log_interval(ProtoAdapter.UINT32.decode(protoReader));
                            break;
                        case 11:
                            builder.dclog_blacklist.add(ProtoAdapter.UINT32.decode(protoReader));
                            break;
                        case 12:
                            builder.auto_load_interval(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        default:
                            switch (iNextTag) {
                                case 14:
                                    builder.enable_debug_level(ProtoAdapter.BOOL.decode(protoReader));
                                    break;
                                case 15:
                                    builder.native_config(NativeConfig.ADAPTER.decode(protoReader));
                                    break;
                                case 16:
                                    builder.load_timeout(ProtoAdapter.UINT32.decode(protoReader));
                                    break;
                                case 17:
                                    builder.load_periodTime(ProtoAdapter.UINT32.decode(protoReader));
                                    break;
                                case 18:
                                    builder.disable_up_location(ProtoAdapter.BOOL.decode(protoReader));
                                    break;
                                default:
                                    FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                                    builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                                    break;
                            }
                    }
                } else {
                    builder.enable_attribution_update(ProtoAdapter.BOOL.decode(protoReader));
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Common common) {
            CommonEndpointsConfig.ADAPTER.encodeWithTag(protoWriter, 1, common.endpoints);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, common.configRefresh);
            RvConfig.ADAPTER.encodeWithTag(protoWriter, 3, common.rv_config);
            SplashConfig.ADAPTER.encodeWithTag(protoWriter, 4, common.splash_config);
            AntiFraudLogConfig.ADAPTER.encodeWithTag(protoWriter, 5, common.anti_fraud_log);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 6, common.is_gdpr_region);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 7, common.tracking_expiration_time);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 8, common.tracking_retry_interval);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 9, common.max_send_log_records);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 10, common.send_log_interval);
            ProtoAdapter.UINT32.asRepeated().encodeWithTag(protoWriter, 11, common.dclog_blacklist);
            ProtoAdapter.INT32.encodeWithTag(protoWriter, 12, common.auto_load_interval);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 23, common.enable_attribution_update);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 14, common.enable_debug_level);
            NativeConfig.ADAPTER.encodeWithTag(protoWriter, 15, common.native_config);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 16, common.load_timeout);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 17, common.load_periodTime);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 18, common.disable_up_location);
            protoWriter.writeBytes(common.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Common common) {
            return CommonEndpointsConfig.ADAPTER.encodedSizeWithTag(1, common.endpoints) + ProtoAdapter.UINT32.encodedSizeWithTag(2, common.configRefresh) + RvConfig.ADAPTER.encodedSizeWithTag(3, common.rv_config) + SplashConfig.ADAPTER.encodedSizeWithTag(4, common.splash_config) + AntiFraudLogConfig.ADAPTER.encodedSizeWithTag(5, common.anti_fraud_log) + ProtoAdapter.BOOL.encodedSizeWithTag(6, common.is_gdpr_region) + ProtoAdapter.UINT32.encodedSizeWithTag(7, common.tracking_expiration_time) + ProtoAdapter.UINT32.encodedSizeWithTag(8, common.tracking_retry_interval) + ProtoAdapter.UINT32.encodedSizeWithTag(9, common.max_send_log_records) + ProtoAdapter.UINT32.encodedSizeWithTag(10, common.send_log_interval) + ProtoAdapter.UINT32.asRepeated().encodedSizeWithTag(11, common.dclog_blacklist) + ProtoAdapter.INT32.encodedSizeWithTag(12, common.auto_load_interval) + ProtoAdapter.BOOL.encodedSizeWithTag(23, common.enable_attribution_update) + ProtoAdapter.BOOL.encodedSizeWithTag(14, common.enable_debug_level) + NativeConfig.ADAPTER.encodedSizeWithTag(15, common.native_config) + ProtoAdapter.UINT32.encodedSizeWithTag(16, common.load_timeout) + ProtoAdapter.UINT32.encodedSizeWithTag(17, common.load_periodTime) + ProtoAdapter.BOOL.encodedSizeWithTag(18, common.disable_up_location) + common.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Common redact(Common common) {
            Builder builderNewBuilder = common.newBuilder();
            if (builderNewBuilder.endpoints != null) {
                builderNewBuilder.endpoints = CommonEndpointsConfig.ADAPTER.redact(builderNewBuilder.endpoints);
            }
            if (builderNewBuilder.rv_config != null) {
                builderNewBuilder.rv_config = RvConfig.ADAPTER.redact(builderNewBuilder.rv_config);
            }
            if (builderNewBuilder.splash_config != null) {
                builderNewBuilder.splash_config = SplashConfig.ADAPTER.redact(builderNewBuilder.splash_config);
            }
            if (builderNewBuilder.anti_fraud_log != null) {
                builderNewBuilder.anti_fraud_log = AntiFraudLogConfig.ADAPTER.redact(builderNewBuilder.anti_fraud_log);
            }
            if (builderNewBuilder.native_config != null) {
                builderNewBuilder.native_config = NativeConfig.ADAPTER.redact(builderNewBuilder.native_config);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Common(CommonEndpointsConfig commonEndpointsConfig, Integer num, RvConfig rvConfig, SplashConfig splashConfig, AntiFraudLogConfig antiFraudLogConfig, Boolean bool, Integer num2, Integer num3, Integer num4, Integer num5, List<Integer> list, Integer num6, Boolean bool2, Boolean bool3, NativeConfig nativeConfig, Integer num7, Integer num8, Boolean bool4) {
        this(commonEndpointsConfig, num, rvConfig, splashConfig, antiFraudLogConfig, bool, num2, num3, num4, num5, list, num6, bool2, bool3, nativeConfig, num7, num8, bool4, ByteString.EMPTY);
    }

    public Common(CommonEndpointsConfig commonEndpointsConfig, Integer num, RvConfig rvConfig, SplashConfig splashConfig, AntiFraudLogConfig antiFraudLogConfig, Boolean bool, Integer num2, Integer num3, Integer num4, Integer num5, List<Integer> list, Integer num6, Boolean bool2, Boolean bool3, NativeConfig nativeConfig, Integer num7, Integer num8, Boolean bool4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.endpoints = commonEndpointsConfig;
        this.configRefresh = num;
        this.rv_config = rvConfig;
        this.splash_config = splashConfig;
        this.anti_fraud_log = antiFraudLogConfig;
        this.is_gdpr_region = bool;
        this.tracking_expiration_time = num2;
        this.tracking_retry_interval = num3;
        this.max_send_log_records = num4;
        this.send_log_interval = num5;
        this.dclog_blacklist = Internal.immutableCopyOf("dclog_blacklist", list);
        this.auto_load_interval = num6;
        this.enable_attribution_update = bool2;
        this.enable_debug_level = bool3;
        this.native_config = nativeConfig;
        this.load_timeout = num7;
        this.load_periodTime = num8;
        this.disable_up_location = bool4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Common)) {
            return false;
        }
        Common common = (Common) obj;
        return unknownFields().equals(common.unknownFields()) && Internal.equals(this.endpoints, common.endpoints) && Internal.equals(this.configRefresh, common.configRefresh) && Internal.equals(this.rv_config, common.rv_config) && Internal.equals(this.splash_config, common.splash_config) && Internal.equals(this.anti_fraud_log, common.anti_fraud_log) && Internal.equals(this.is_gdpr_region, common.is_gdpr_region) && Internal.equals(this.tracking_expiration_time, common.tracking_expiration_time) && Internal.equals(this.tracking_retry_interval, common.tracking_retry_interval) && Internal.equals(this.max_send_log_records, common.max_send_log_records) && Internal.equals(this.send_log_interval, common.send_log_interval) && this.dclog_blacklist.equals(common.dclog_blacklist) && Internal.equals(this.auto_load_interval, common.auto_load_interval) && Internal.equals(this.enable_attribution_update, common.enable_attribution_update) && Internal.equals(this.enable_debug_level, common.enable_debug_level) && Internal.equals(this.native_config, common.native_config) && Internal.equals(this.load_timeout, common.load_timeout) && Internal.equals(this.load_periodTime, common.load_periodTime) && Internal.equals(this.disable_up_location, common.disable_up_location);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.endpoints != null ? this.endpoints.hashCode() : 0)) * 37) + (this.configRefresh != null ? this.configRefresh.hashCode() : 0)) * 37) + (this.rv_config != null ? this.rv_config.hashCode() : 0)) * 37) + (this.splash_config != null ? this.splash_config.hashCode() : 0)) * 37) + (this.anti_fraud_log != null ? this.anti_fraud_log.hashCode() : 0)) * 37) + (this.is_gdpr_region != null ? this.is_gdpr_region.hashCode() : 0)) * 37) + (this.tracking_expiration_time != null ? this.tracking_expiration_time.hashCode() : 0)) * 37) + (this.tracking_retry_interval != null ? this.tracking_retry_interval.hashCode() : 0)) * 37) + (this.max_send_log_records != null ? this.max_send_log_records.hashCode() : 0)) * 37) + (this.send_log_interval != null ? this.send_log_interval.hashCode() : 0)) * 37) + this.dclog_blacklist.hashCode()) * 37) + (this.auto_load_interval != null ? this.auto_load_interval.hashCode() : 0)) * 37) + (this.enable_attribution_update != null ? this.enable_attribution_update.hashCode() : 0)) * 37) + (this.enable_debug_level != null ? this.enable_debug_level.hashCode() : 0)) * 37) + (this.native_config != null ? this.native_config.hashCode() : 0)) * 37) + (this.load_timeout != null ? this.load_timeout.hashCode() : 0)) * 37) + (this.load_periodTime != null ? this.load_periodTime.hashCode() : 0)) * 37) + (this.disable_up_location != null ? this.disable_up_location.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.endpoints = this.endpoints;
        builder.configRefresh = this.configRefresh;
        builder.rv_config = this.rv_config;
        builder.splash_config = this.splash_config;
        builder.anti_fraud_log = this.anti_fraud_log;
        builder.is_gdpr_region = this.is_gdpr_region;
        builder.tracking_expiration_time = this.tracking_expiration_time;
        builder.tracking_retry_interval = this.tracking_retry_interval;
        builder.max_send_log_records = this.max_send_log_records;
        builder.send_log_interval = this.send_log_interval;
        builder.dclog_blacklist = Internal.copyOf("dclog_blacklist", this.dclog_blacklist);
        builder.auto_load_interval = this.auto_load_interval;
        builder.enable_attribution_update = this.enable_attribution_update;
        builder.enable_debug_level = this.enable_debug_level;
        builder.native_config = this.native_config;
        builder.load_timeout = this.load_timeout;
        builder.load_periodTime = this.load_periodTime;
        builder.disable_up_location = this.disable_up_location;
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
        if (this.rv_config != null) {
            sb.append(", rv_config=");
            sb.append(this.rv_config);
        }
        if (this.splash_config != null) {
            sb.append(", splash_config=");
            sb.append(this.splash_config);
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
        if (this.enable_attribution_update != null) {
            sb.append(", enable_attribution_update=");
            sb.append(this.enable_attribution_update);
        }
        if (this.enable_debug_level != null) {
            sb.append(", enable_debug_level=");
            sb.append(this.enable_debug_level);
        }
        if (this.native_config != null) {
            sb.append(", native_config=");
            sb.append(this.native_config);
        }
        if (this.load_timeout != null) {
            sb.append(", load_timeout=");
            sb.append(this.load_timeout);
        }
        if (this.load_periodTime != null) {
            sb.append(", load_periodTime=");
            sb.append(this.load_periodTime);
        }
        if (this.disable_up_location != null) {
            sb.append(", disable_up_location=");
            sb.append(this.disable_up_location);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Common{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
