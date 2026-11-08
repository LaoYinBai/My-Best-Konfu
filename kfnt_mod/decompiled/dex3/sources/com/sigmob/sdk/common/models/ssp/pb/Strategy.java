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
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Strategy extends AndroidMessage<Strategy, Builder> {
    public static final String DEFAULT_ADAPTER = "";
    public static final String DEFAULT_CHANNEL_ID = "";
    public static final String DEFAULT_NAME = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 5)
    public final Integer ad_expire_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String adapter;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String channel_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 6)
    public final Boolean enable_extra_close_callback;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String name;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final Map<String, String> options;
    public static final ProtoAdapter<Strategy> ADAPTER = new ProtoAdapter_Strategy();
    public static final Parcelable.Creator<Strategy> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_AD_EXPIRE_TIME = 0;
    public static final Boolean DEFAULT_ENABLE_EXTRA_CLOSE_CALLBACK = false;

    public static final class Builder extends Message.Builder<Strategy, Builder> {
        public Map<String, String> options;
        public String name = "";
        public String adapter = "";
        public String channel_id = "";
        public Integer ad_expire_time = Strategy.DEFAULT_AD_EXPIRE_TIME;
        public Boolean enable_extra_close_callback = Strategy.DEFAULT_ENABLE_EXTRA_CLOSE_CALLBACK;

        public Builder() {
            this.options = new HashMap();
            this.options = Internal.newMutableMap();
        }

        public Builder ad_expire_time(Integer num) {
            this.ad_expire_time = num;
            return this;
        }

        public Builder adapter(String str) {
            this.adapter = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public Strategy build() {
            return new Strategy(this.name, this.options, this.adapter, this.channel_id, this.ad_expire_time, this.enable_extra_close_callback, super.buildUnknownFields());
        }

        public Builder channel_id(String str) {
            this.channel_id = str;
            return this;
        }

        public Builder enable_extra_close_callback(Boolean bool) {
            this.enable_extra_close_callback = bool;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder options(Map<String, String> map) {
            Internal.checkElementsNotNull(map);
            this.options = map;
            return this;
        }
    }

    private static final class ProtoAdapter_Strategy extends ProtoAdapter<Strategy> {
        private final ProtoAdapter<Map<String, String>> a;

        public ProtoAdapter_Strategy() {
            super(FieldEncoding.LENGTH_DELIMITED, Strategy.class);
            this.a = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.STRING);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Strategy decode(ProtoReader protoReader) {
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
                        builder.name(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.options.putAll(this.a.decode(protoReader));
                        break;
                    case 3:
                        builder.adapter(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 4:
                        builder.channel_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.ad_expire_time(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 6:
                        builder.enable_extra_close_callback(ProtoAdapter.BOOL.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Strategy strategy) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, strategy.name);
            this.a.encodeWithTag(protoWriter, 2, strategy.options);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, strategy.adapter);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, strategy.channel_id);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 5, strategy.ad_expire_time);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 6, strategy.enable_extra_close_callback);
            protoWriter.writeBytes(strategy.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Strategy strategy) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, strategy.name) + this.a.encodedSizeWithTag(2, strategy.options) + ProtoAdapter.STRING.encodedSizeWithTag(3, strategy.adapter) + ProtoAdapter.STRING.encodedSizeWithTag(4, strategy.channel_id) + ProtoAdapter.UINT32.encodedSizeWithTag(5, strategy.ad_expire_time) + ProtoAdapter.BOOL.encodedSizeWithTag(6, strategy.enable_extra_close_callback) + strategy.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Strategy redact(Strategy strategy) {
            Builder builderNewBuilder = strategy.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Strategy(String str, Map<String, String> map, String str2, String str3, Integer num, Boolean bool) {
        this(str, map, str2, str3, num, bool, ByteString.EMPTY);
    }

    public Strategy(String str, Map<String, String> map, String str2, String str3, Integer num, Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.name = str;
        this.options = Internal.immutableCopyOf("options", map);
        this.adapter = str2;
        this.channel_id = str3;
        this.ad_expire_time = num;
        this.enable_extra_close_callback = bool;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Strategy)) {
            return false;
        }
        Strategy strategy = (Strategy) obj;
        return unknownFields().equals(strategy.unknownFields()) && Internal.equals(this.name, strategy.name) && this.options.equals(strategy.options) && Internal.equals(this.adapter, strategy.adapter) && Internal.equals(this.channel_id, strategy.channel_id) && Internal.equals(this.ad_expire_time, strategy.ad_expire_time) && Internal.equals(this.enable_extra_close_callback, strategy.enable_extra_close_callback);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((unknownFields().hashCode() * 37) + (this.name != null ? this.name.hashCode() : 0)) * 37) + this.options.hashCode()) * 37) + (this.adapter != null ? this.adapter.hashCode() : 0)) * 37) + (this.channel_id != null ? this.channel_id.hashCode() : 0)) * 37) + (this.ad_expire_time != null ? this.ad_expire_time.hashCode() : 0)) * 37) + (this.enable_extra_close_callback != null ? this.enable_extra_close_callback.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.name = this.name;
        builder.options = Internal.copyOf("options", this.options);
        builder.adapter = this.adapter;
        builder.channel_id = this.channel_id;
        builder.ad_expire_time = DEFAULT_AD_EXPIRE_TIME;
        builder.enable_extra_close_callback = this.enable_extra_close_callback;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.name != null) {
            sb.append(", name=");
            sb.append(this.name);
        }
        if (!this.options.isEmpty()) {
            sb.append(", options=");
            sb.append(this.options);
        }
        if (this.adapter != null) {
            sb.append(", adapter=");
            sb.append(this.adapter);
        }
        if (this.channel_id != null) {
            sb.append(", channel_id=");
            sb.append(this.channel_id);
        }
        if (this.ad_expire_time != null) {
            sb.append(", ad_expire_time=");
            sb.append(this.ad_expire_time);
        }
        if (this.enable_extra_close_callback != null) {
            sb.append(", enable_extra_close_callback=");
            sb.append(this.enable_extra_close_callback);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Strategy{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
