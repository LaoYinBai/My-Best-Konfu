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
public final class Device extends AndroidMessage<Device, Builder> {
    public static final String DEFAULT_BOOT_MARK = "";
    public static final String DEFAULT_DEVICE_NAME = "";
    public static final String DEFAULT_INTERNAL_NAME = "";
    public static final String DEFAULT_MODEL = "";
    public static final String DEFAULT_SYSTEM_UPDATE_TIME = "";
    public static final String DEFAULT_UPDATE_MARK = "";
    public static final String DEFAULT_VENDOR = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 18)
    public final Integer android_api_level;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 14)
    public final Float battery_level;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 15)
    public final Boolean battery_save_enabled;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 13)
    public final Integer battery_state;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 27)
    public final String boot_mark;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 16)
    public final String device_name;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer device_type;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.DeviceId#ADAPTER", tag = 6)
    public final DeviceId did;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 11)
    public final Long disk_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 9)
    public final Integer dpi;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 21)
    public final Long free_disk_size;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Geo#ADAPTER", tag = 8)
    public final Geo geo;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 26)
    public final String internal_name;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 10)
    public final Boolean is_root;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 19)
    public final Long mem_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String model;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer os_type;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Version#ADAPTER", tag = 3)
    public final Version os_version;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Size#ADAPTER", tag = 24)
    public final Size resolution;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Size#ADAPTER", tag = 7)
    public final Size screen_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 23)
    public final Long sd_free_disk_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 22)
    public final Long sd_total_disk_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT64", tag = 17)
    public final Long start_timestamp;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 25)
    public final String system_update_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT64", tag = 20)
    public final Long total_disk_size;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 28)
    public final String update_mark;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String vendor;
    public static final ProtoAdapter<Device> ADAPTER = new ProtoAdapter_Device();
    public static final Parcelable.Creator<Device> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_DEVICE_TYPE = 0;
    public static final Integer DEFAULT_OS_TYPE = 0;
    public static final Integer DEFAULT_DPI = 0;
    public static final Boolean DEFAULT_IS_ROOT = false;
    public static final Long DEFAULT_DISK_SIZE = 0L;
    public static final Integer DEFAULT_BATTERY_STATE = 0;
    public static final Float DEFAULT_BATTERY_LEVEL = Float.valueOf(0.0f);
    public static final Boolean DEFAULT_BATTERY_SAVE_ENABLED = false;
    public static final Long DEFAULT_START_TIMESTAMP = 0L;
    public static final Integer DEFAULT_ANDROID_API_LEVEL = 0;
    public static final Long DEFAULT_MEM_SIZE = 0L;
    public static final Long DEFAULT_TOTAL_DISK_SIZE = 0L;
    public static final Long DEFAULT_FREE_DISK_SIZE = 0L;
    public static final Long DEFAULT_SD_TOTAL_DISK_SIZE = 0L;
    public static final Long DEFAULT_SD_FREE_DISK_SIZE = 0L;

    public static final class Builder extends Message.Builder<Device, Builder> {
        public DeviceId did;
        public Geo geo;
        public Version os_version;
        public Size resolution;
        public Size screen_size;
        public Integer device_type = Device.DEFAULT_DEVICE_TYPE;
        public Integer os_type = Device.DEFAULT_OS_TYPE;
        public String vendor = "";
        public String model = "";
        public Integer dpi = Device.DEFAULT_DPI;
        public Boolean is_root = Device.DEFAULT_IS_ROOT;
        public Long disk_size = Device.DEFAULT_DISK_SIZE;
        public Integer battery_state = Device.DEFAULT_BATTERY_STATE;
        public Float battery_level = Device.DEFAULT_BATTERY_LEVEL;
        public Boolean battery_save_enabled = Device.DEFAULT_BATTERY_SAVE_ENABLED;
        public String device_name = "";
        public Long start_timestamp = Device.DEFAULT_START_TIMESTAMP;
        public Integer android_api_level = Device.DEFAULT_ANDROID_API_LEVEL;
        public Long mem_size = Device.DEFAULT_MEM_SIZE;
        public Long total_disk_size = Device.DEFAULT_TOTAL_DISK_SIZE;
        public Long free_disk_size = Device.DEFAULT_FREE_DISK_SIZE;
        public Long sd_total_disk_size = Device.DEFAULT_SD_TOTAL_DISK_SIZE;
        public Long sd_free_disk_size = Device.DEFAULT_SD_FREE_DISK_SIZE;
        public String system_update_time = "";
        public String internal_name = "";
        public String boot_mark = "";
        public String update_mark = "";

        public Builder android_api_level(Integer num) {
            this.android_api_level = num;
            return this;
        }

        public Builder battery_level(Float f) {
            this.battery_level = f;
            return this;
        }

        public Builder battery_save_enabled(Boolean bool) {
            this.battery_save_enabled = bool;
            return this;
        }

        public Builder battery_state(Integer num) {
            this.battery_state = num;
            return this;
        }

        public Builder boot_mark(String str) {
            this.boot_mark = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public Device build() {
            return new Device(this.device_type, this.os_type, this.os_version, this.vendor, this.model, this.did, this.screen_size, this.geo, this.dpi, this.is_root, this.disk_size, this.battery_state, this.battery_level, this.battery_save_enabled, this.device_name, this.start_timestamp, this.android_api_level, this.mem_size, this.total_disk_size, this.free_disk_size, this.sd_total_disk_size, this.sd_free_disk_size, this.resolution, this.system_update_time, this.internal_name, this.boot_mark, this.update_mark, super.buildUnknownFields());
        }

        public Builder device_name(String str) {
            this.device_name = str;
            return this;
        }

        public Builder device_type(Integer num) {
            this.device_type = num;
            return this;
        }

        public Builder did(DeviceId deviceId) {
            this.did = deviceId;
            return this;
        }

        public Builder disk_size(Long l) {
            this.disk_size = l;
            return this;
        }

        public Builder dpi(Integer num) {
            this.dpi = num;
            return this;
        }

        public Builder free_disk_size(Long l) {
            this.free_disk_size = l;
            return this;
        }

        public Builder geo(Geo geo) {
            this.geo = geo;
            return this;
        }

        public Builder internal_name(String str) {
            this.internal_name = str;
            return this;
        }

        public Builder is_root(Boolean bool) {
            this.is_root = bool;
            return this;
        }

        public Builder mem_size(Long l) {
            this.mem_size = l;
            return this;
        }

        public Builder model(String str) {
            this.model = str;
            return this;
        }

        public Builder os_type(Integer num) {
            this.os_type = num;
            return this;
        }

        public Builder os_version(Version version) {
            this.os_version = version;
            return this;
        }

        public Builder resolution(Size size) {
            this.resolution = size;
            return this;
        }

        public Builder screen_size(Size size) {
            this.screen_size = size;
            return this;
        }

        public Builder sd_free_disk_size(Long l) {
            this.sd_free_disk_size = l;
            return this;
        }

        public Builder sd_total_disk_size(Long l) {
            this.sd_total_disk_size = l;
            return this;
        }

        public Builder start_timestamp(Long l) {
            this.start_timestamp = l;
            return this;
        }

        public Builder system_update_time(String str) {
            this.system_update_time = str;
            return this;
        }

        public Builder total_disk_size(Long l) {
            this.total_disk_size = l;
            return this;
        }

        public Builder update_mark(String str) {
            this.update_mark = str;
            return this;
        }

        public Builder vendor(String str) {
            this.vendor = str;
            return this;
        }
    }

    private static final class ProtoAdapter_Device extends ProtoAdapter<Device> {
        public ProtoAdapter_Device() {
            super(FieldEncoding.LENGTH_DELIMITED, Device.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Device decode(ProtoReader protoReader) {
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
                        builder.device_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.os_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.os_version(Version.ADAPTER.decode(protoReader));
                        break;
                    case 4:
                        builder.vendor(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.model(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 6:
                        builder.did(DeviceId.ADAPTER.decode(protoReader));
                        break;
                    case 7:
                        builder.screen_size(Size.ADAPTER.decode(protoReader));
                        break;
                    case 8:
                        builder.geo(Geo.ADAPTER.decode(protoReader));
                        break;
                    case 9:
                        builder.dpi(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 10:
                        builder.is_root(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 11:
                        builder.disk_size(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 12:
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                    case 13:
                        builder.battery_state(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 14:
                        builder.battery_level(ProtoAdapter.FLOAT.decode(protoReader));
                        break;
                    case 15:
                        builder.battery_save_enabled(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    case 16:
                        builder.device_name(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 17:
                        builder.start_timestamp(ProtoAdapter.INT64.decode(protoReader));
                        break;
                    case 18:
                        builder.android_api_level(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 19:
                        builder.mem_size(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 20:
                        builder.total_disk_size(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 21:
                        builder.free_disk_size(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 22:
                        builder.sd_total_disk_size(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 23:
                        builder.sd_free_disk_size(ProtoAdapter.UINT64.decode(protoReader));
                        break;
                    case 24:
                        builder.resolution(Size.ADAPTER.decode(protoReader));
                        break;
                    case 25:
                        builder.system_update_time(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 26:
                        builder.internal_name(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 27:
                        builder.boot_mark(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 28:
                        builder.update_mark(ProtoAdapter.STRING.decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Device device) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, device.device_type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, device.os_type);
            Version.ADAPTER.encodeWithTag(protoWriter, 3, device.os_version);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, device.vendor);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, device.model);
            DeviceId.ADAPTER.encodeWithTag(protoWriter, 6, device.did);
            Size.ADAPTER.encodeWithTag(protoWriter, 7, device.screen_size);
            Geo.ADAPTER.encodeWithTag(protoWriter, 8, device.geo);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 9, device.dpi);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 10, device.is_root);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 11, device.disk_size);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 13, device.battery_state);
            ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 14, device.battery_level);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 15, device.battery_save_enabled);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 16, device.device_name);
            ProtoAdapter.INT64.encodeWithTag(protoWriter, 17, device.start_timestamp);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 18, device.android_api_level);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 19, device.mem_size);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 20, device.total_disk_size);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 21, device.free_disk_size);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 22, device.sd_total_disk_size);
            ProtoAdapter.UINT64.encodeWithTag(protoWriter, 23, device.sd_free_disk_size);
            Size.ADAPTER.encodeWithTag(protoWriter, 24, device.resolution);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 25, device.system_update_time);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 26, device.internal_name);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 27, device.boot_mark);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 28, device.update_mark);
            protoWriter.writeBytes(device.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Device device) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, device.device_type) + ProtoAdapter.UINT32.encodedSizeWithTag(2, device.os_type) + Version.ADAPTER.encodedSizeWithTag(3, device.os_version) + ProtoAdapter.STRING.encodedSizeWithTag(4, device.vendor) + ProtoAdapter.STRING.encodedSizeWithTag(5, device.model) + DeviceId.ADAPTER.encodedSizeWithTag(6, device.did) + Size.ADAPTER.encodedSizeWithTag(7, device.screen_size) + Geo.ADAPTER.encodedSizeWithTag(8, device.geo) + ProtoAdapter.UINT32.encodedSizeWithTag(9, device.dpi) + ProtoAdapter.BOOL.encodedSizeWithTag(10, device.is_root) + ProtoAdapter.UINT64.encodedSizeWithTag(11, device.disk_size) + ProtoAdapter.UINT32.encodedSizeWithTag(13, device.battery_state) + ProtoAdapter.FLOAT.encodedSizeWithTag(14, device.battery_level) + ProtoAdapter.BOOL.encodedSizeWithTag(15, device.battery_save_enabled) + ProtoAdapter.STRING.encodedSizeWithTag(16, device.device_name) + ProtoAdapter.INT64.encodedSizeWithTag(17, device.start_timestamp) + ProtoAdapter.UINT32.encodedSizeWithTag(18, device.android_api_level) + ProtoAdapter.UINT64.encodedSizeWithTag(19, device.mem_size) + ProtoAdapter.UINT64.encodedSizeWithTag(20, device.total_disk_size) + ProtoAdapter.UINT64.encodedSizeWithTag(21, device.free_disk_size) + ProtoAdapter.UINT64.encodedSizeWithTag(22, device.sd_total_disk_size) + ProtoAdapter.UINT64.encodedSizeWithTag(23, device.sd_free_disk_size) + Size.ADAPTER.encodedSizeWithTag(24, device.resolution) + ProtoAdapter.STRING.encodedSizeWithTag(25, device.system_update_time) + ProtoAdapter.STRING.encodedSizeWithTag(26, device.internal_name) + ProtoAdapter.STRING.encodedSizeWithTag(27, device.boot_mark) + ProtoAdapter.STRING.encodedSizeWithTag(28, device.update_mark) + device.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Device redact(Device device) {
            Builder builderNewBuilder = device.newBuilder();
            if (builderNewBuilder.os_version != null) {
                builderNewBuilder.os_version = Version.ADAPTER.redact(builderNewBuilder.os_version);
            }
            if (builderNewBuilder.did != null) {
                builderNewBuilder.did = DeviceId.ADAPTER.redact(builderNewBuilder.did);
            }
            if (builderNewBuilder.screen_size != null) {
                builderNewBuilder.screen_size = Size.ADAPTER.redact(builderNewBuilder.screen_size);
            }
            if (builderNewBuilder.geo != null) {
                builderNewBuilder.geo = Geo.ADAPTER.redact(builderNewBuilder.geo);
            }
            if (builderNewBuilder.resolution != null) {
                builderNewBuilder.resolution = Size.ADAPTER.redact(builderNewBuilder.resolution);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Device(Integer num, Integer num2, Version version, String str, String str2, DeviceId deviceId, Size size, Geo geo, Integer num3, Boolean bool, Long l, Integer num4, Float f, Boolean bool2, String str3, Long l2, Integer num5, Long l3, Long l4, Long l5, Long l6, Long l7, Size size2, String str4, String str5, String str6, String str7) {
        this(num, num2, version, str, str2, deviceId, size, geo, num3, bool, l, num4, f, bool2, str3, l2, num5, l3, l4, l5, l6, l7, size2, str4, str5, str6, str7, ByteString.EMPTY);
    }

    public Device(Integer num, Integer num2, Version version, String str, String str2, DeviceId deviceId, Size size, Geo geo, Integer num3, Boolean bool, Long l, Integer num4, Float f, Boolean bool2, String str3, Long l2, Integer num5, Long l3, Long l4, Long l5, Long l6, Long l7, Size size2, String str4, String str5, String str6, String str7, ByteString byteString) {
        super(ADAPTER, byteString);
        this.device_type = num;
        this.os_type = num2;
        this.os_version = version;
        this.vendor = str;
        this.model = str2;
        this.did = deviceId;
        this.screen_size = size;
        this.geo = geo;
        this.dpi = num3;
        this.is_root = bool;
        this.disk_size = l;
        this.battery_state = num4;
        this.battery_level = f;
        this.battery_save_enabled = bool2;
        this.device_name = str3;
        this.start_timestamp = l2;
        this.android_api_level = num5;
        this.mem_size = l3;
        this.total_disk_size = l4;
        this.free_disk_size = l5;
        this.sd_total_disk_size = l6;
        this.sd_free_disk_size = l7;
        this.resolution = size2;
        this.system_update_time = str4;
        this.internal_name = str5;
        this.boot_mark = str6;
        this.update_mark = str7;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return unknownFields().equals(device.unknownFields()) && Internal.equals(this.device_type, device.device_type) && Internal.equals(this.os_type, device.os_type) && Internal.equals(this.os_version, device.os_version) && Internal.equals(this.vendor, device.vendor) && Internal.equals(this.model, device.model) && Internal.equals(this.did, device.did) && Internal.equals(this.screen_size, device.screen_size) && Internal.equals(this.geo, device.geo) && Internal.equals(this.dpi, device.dpi) && Internal.equals(this.is_root, device.is_root) && Internal.equals(this.disk_size, device.disk_size) && Internal.equals(this.battery_state, device.battery_state) && Internal.equals(this.battery_level, device.battery_level) && Internal.equals(this.battery_save_enabled, device.battery_save_enabled) && Internal.equals(this.device_name, device.device_name) && Internal.equals(this.start_timestamp, device.start_timestamp) && Internal.equals(this.android_api_level, device.android_api_level) && Internal.equals(this.mem_size, device.mem_size) && Internal.equals(this.total_disk_size, device.total_disk_size) && Internal.equals(this.free_disk_size, device.free_disk_size) && Internal.equals(this.sd_total_disk_size, device.sd_total_disk_size) && Internal.equals(this.sd_free_disk_size, device.sd_free_disk_size) && Internal.equals(this.resolution, device.resolution) && Internal.equals(this.system_update_time, device.system_update_time) && Internal.equals(this.internal_name, device.internal_name) && Internal.equals(this.boot_mark, device.boot_mark) && Internal.equals(this.update_mark, device.update_mark);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((((((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.device_type != null ? this.device_type.hashCode() : 0)) * 37) + (this.os_type != null ? this.os_type.hashCode() : 0)) * 37) + (this.os_version != null ? this.os_version.hashCode() : 0)) * 37) + (this.vendor != null ? this.vendor.hashCode() : 0)) * 37) + (this.model != null ? this.model.hashCode() : 0)) * 37) + (this.did != null ? this.did.hashCode() : 0)) * 37) + (this.screen_size != null ? this.screen_size.hashCode() : 0)) * 37) + (this.geo != null ? this.geo.hashCode() : 0)) * 37) + (this.dpi != null ? this.dpi.hashCode() : 0)) * 37) + (this.is_root != null ? this.is_root.hashCode() : 0)) * 37) + (this.disk_size != null ? this.disk_size.hashCode() : 0)) * 37) + (this.battery_state != null ? this.battery_state.hashCode() : 0)) * 37) + (this.battery_level != null ? this.battery_level.hashCode() : 0)) * 37) + (this.battery_save_enabled != null ? this.battery_save_enabled.hashCode() : 0)) * 37) + (this.device_name != null ? this.device_name.hashCode() : 0)) * 37) + (this.start_timestamp != null ? this.start_timestamp.hashCode() : 0)) * 37) + (this.android_api_level != null ? this.android_api_level.hashCode() : 0)) * 37) + (this.mem_size != null ? this.mem_size.hashCode() : 0)) * 37) + (this.total_disk_size != null ? this.total_disk_size.hashCode() : 0)) * 37) + (this.free_disk_size != null ? this.free_disk_size.hashCode() : 0)) * 37) + (this.sd_total_disk_size != null ? this.sd_total_disk_size.hashCode() : 0)) * 37) + (this.sd_free_disk_size != null ? this.sd_free_disk_size.hashCode() : 0)) * 37) + (this.resolution != null ? this.resolution.hashCode() : 0)) * 37) + (this.system_update_time != null ? this.system_update_time.hashCode() : 0)) * 37) + (this.internal_name != null ? this.internal_name.hashCode() : 0)) * 37) + (this.boot_mark != null ? this.boot_mark.hashCode() : 0)) * 37) + (this.update_mark != null ? this.update_mark.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.device_type = this.device_type;
        builder.os_type = this.os_type;
        builder.os_version = this.os_version;
        builder.vendor = this.vendor;
        builder.model = this.model;
        builder.did = this.did;
        builder.screen_size = this.screen_size;
        builder.geo = this.geo;
        builder.dpi = this.dpi;
        builder.is_root = this.is_root;
        builder.disk_size = this.disk_size;
        builder.battery_state = this.battery_state;
        builder.battery_level = this.battery_level;
        builder.battery_save_enabled = this.battery_save_enabled;
        builder.device_name = this.device_name;
        builder.start_timestamp = this.start_timestamp;
        builder.android_api_level = this.android_api_level;
        builder.mem_size = this.mem_size;
        builder.total_disk_size = this.total_disk_size;
        builder.free_disk_size = this.free_disk_size;
        builder.sd_total_disk_size = this.sd_total_disk_size;
        builder.sd_free_disk_size = this.sd_free_disk_size;
        builder.resolution = this.resolution;
        builder.system_update_time = this.system_update_time;
        builder.internal_name = this.internal_name;
        builder.boot_mark = this.boot_mark;
        builder.update_mark = this.update_mark;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.device_type != null) {
            sb.append(", device_type=");
            sb.append(this.device_type);
        }
        if (this.os_type != null) {
            sb.append(", os_type=");
            sb.append(this.os_type);
        }
        if (this.os_version != null) {
            sb.append(", os_version=");
            sb.append(this.os_version);
        }
        if (this.vendor != null) {
            sb.append(", vendor=");
            sb.append(this.vendor);
        }
        if (this.model != null) {
            sb.append(", model=");
            sb.append(this.model);
        }
        if (this.did != null) {
            sb.append(", did=");
            sb.append(this.did);
        }
        if (this.screen_size != null) {
            sb.append(", screen_size=");
            sb.append(this.screen_size);
        }
        if (this.geo != null) {
            sb.append(", geo=");
            sb.append(this.geo);
        }
        if (this.dpi != null) {
            sb.append(", dpi=");
            sb.append(this.dpi);
        }
        if (this.is_root != null) {
            sb.append(", is_root=");
            sb.append(this.is_root);
        }
        if (this.disk_size != null) {
            sb.append(", disk_size=");
            sb.append(this.disk_size);
        }
        if (this.battery_state != null) {
            sb.append(", battery_state=");
            sb.append(this.battery_state);
        }
        if (this.battery_level != null) {
            sb.append(", battery_level=");
            sb.append(this.battery_level);
        }
        if (this.battery_save_enabled != null) {
            sb.append(", battery_save_enabled=");
            sb.append(this.battery_save_enabled);
        }
        if (this.device_name != null) {
            sb.append(", device_name=");
            sb.append(this.device_name);
        }
        if (this.start_timestamp != null) {
            sb.append(", start_timestamp=");
            sb.append(this.start_timestamp);
        }
        if (this.android_api_level != null) {
            sb.append(", android_api_level=");
            sb.append(this.android_api_level);
        }
        if (this.mem_size != null) {
            sb.append(", mem_size=");
            sb.append(this.mem_size);
        }
        if (this.total_disk_size != null) {
            sb.append(", total_disk_size=");
            sb.append(this.total_disk_size);
        }
        if (this.free_disk_size != null) {
            sb.append(", free_disk_size=");
            sb.append(this.free_disk_size);
        }
        if (this.sd_total_disk_size != null) {
            sb.append(", sd_total_disk_size=");
            sb.append(this.sd_total_disk_size);
        }
        if (this.sd_free_disk_size != null) {
            sb.append(", sd_free_disk_size=");
            sb.append(this.sd_free_disk_size);
        }
        if (this.resolution != null) {
            sb.append(", resolution=");
            sb.append(this.resolution);
        }
        if (this.system_update_time != null) {
            sb.append(", system_update_time=");
            sb.append(this.system_update_time);
        }
        if (this.internal_name != null) {
            sb.append(", internal_name=");
            sb.append(this.internal_name);
        }
        if (this.boot_mark != null) {
            sb.append(", boot_mark=");
            sb.append(this.boot_mark);
        }
        if (this.update_mark != null) {
            sb.append(", update_mark=");
            sb.append(this.update_mark);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Device{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
