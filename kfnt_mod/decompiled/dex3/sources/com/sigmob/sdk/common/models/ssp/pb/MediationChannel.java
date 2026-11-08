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
public final class MediationChannel extends AndroidMessage<MediationChannel, Builder> {
    public static final String DEFAULT_NAME = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer ad_expire_time;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer channel_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 4)
    public final Boolean enable_extra_close_callback;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String name;
    public static final ProtoAdapter<MediationChannel> ADAPTER = new ProtoAdapter_MediationChannel();
    public static final Parcelable.Creator<MediationChannel> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CHANNEL_ID = 0;
    public static final Integer DEFAULT_AD_EXPIRE_TIME = 0;
    public static final Boolean DEFAULT_ENABLE_EXTRA_CLOSE_CALLBACK = false;

    public static final class Builder extends Message.Builder<MediationChannel, Builder> {
        public Integer channel_id = MediationChannel.DEFAULT_CHANNEL_ID;
        public String name = "";
        public Integer ad_expire_time = MediationChannel.DEFAULT_AD_EXPIRE_TIME;
        public Boolean enable_extra_close_callback = MediationChannel.DEFAULT_ENABLE_EXTRA_CLOSE_CALLBACK;

        public Builder ad_expire_time(Integer num) {
            this.ad_expire_time = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public MediationChannel build() {
            return new MediationChannel(this.channel_id, this.name, this.ad_expire_time, this.enable_extra_close_callback, super.buildUnknownFields());
        }

        public Builder channel_id(Integer num) {
            this.channel_id = num;
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
    }

    private static final class ProtoAdapter_MediationChannel extends ProtoAdapter<MediationChannel> {
        public ProtoAdapter_MediationChannel() {
            super(FieldEncoding.LENGTH_DELIMITED, MediationChannel.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public MediationChannel decode(ProtoReader protoReader) {
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
                        builder.channel_id(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.name(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 3:
                        builder.ad_expire_time(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 4:
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
        public void encode(ProtoWriter protoWriter, MediationChannel mediationChannel) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, mediationChannel.channel_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, mediationChannel.name);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, mediationChannel.ad_expire_time);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 4, mediationChannel.enable_extra_close_callback);
            protoWriter.writeBytes(mediationChannel.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(MediationChannel mediationChannel) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, mediationChannel.channel_id) + ProtoAdapter.STRING.encodedSizeWithTag(2, mediationChannel.name) + ProtoAdapter.UINT32.encodedSizeWithTag(3, mediationChannel.ad_expire_time) + ProtoAdapter.BOOL.encodedSizeWithTag(4, mediationChannel.enable_extra_close_callback) + mediationChannel.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public MediationChannel redact(MediationChannel mediationChannel) {
            Builder builderNewBuilder = mediationChannel.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public MediationChannel(Integer num, String str, Integer num2, Boolean bool) {
        this(num, str, num2, bool, ByteString.EMPTY);
    }

    public MediationChannel(Integer num, String str, Integer num2, Boolean bool, ByteString byteString) {
        super(ADAPTER, byteString);
        this.channel_id = num;
        this.name = str;
        this.ad_expire_time = num2;
        this.enable_extra_close_callback = bool;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediationChannel)) {
            return false;
        }
        MediationChannel mediationChannel = (MediationChannel) obj;
        return unknownFields().equals(mediationChannel.unknownFields()) && Internal.equals(this.channel_id, mediationChannel.channel_id) && Internal.equals(this.name, mediationChannel.name) && Internal.equals(this.ad_expire_time, mediationChannel.ad_expire_time) && Internal.equals(this.enable_extra_close_callback, mediationChannel.enable_extra_close_callback);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.channel_id != null ? this.channel_id.hashCode() : 0)) * 37) + (this.name != null ? this.name.hashCode() : 0)) * 37) + (this.ad_expire_time != null ? this.ad_expire_time.hashCode() : 0)) * 37) + (this.enable_extra_close_callback != null ? this.enable_extra_close_callback.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.channel_id = this.channel_id;
        builder.name = this.name;
        builder.ad_expire_time = this.ad_expire_time;
        builder.enable_extra_close_callback = this.enable_extra_close_callback;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.channel_id != null) {
            sb.append(", channel_id=");
            sb.append(this.channel_id);
        }
        if (this.name != null) {
            sb.append(", name=");
            sb.append(this.name);
        }
        if (this.ad_expire_time != null) {
            sb.append(", ad_expire_time=");
            sb.append(this.ad_expire_time);
        }
        if (this.enable_extra_close_callback != null) {
            sb.append(", enable_extra_close_callback=");
            sb.append(this.enable_extra_close_callback);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "MediationChannel{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
