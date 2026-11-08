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
public final class MotionConfig extends AndroidMessage<MotionConfig, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer count;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer interval;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer queue_max;
    public static final ProtoAdapter<MotionConfig> ADAPTER = new ProtoAdapter_MotionConfig();
    public static final Parcelable.Creator<MotionConfig> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_INTERVAL = 0;
    public static final Integer DEFAULT_QUEUE_MAX = 0;
    public static final Integer DEFAULT_COUNT = 0;

    public static final class Builder extends Message.Builder<MotionConfig, Builder> {
        public Integer interval = MotionConfig.DEFAULT_INTERVAL;
        public Integer queue_max = MotionConfig.DEFAULT_QUEUE_MAX;
        public Integer count = MotionConfig.DEFAULT_COUNT;

        @Override // com.sigmob.wire.Message.Builder
        public MotionConfig build() {
            return new MotionConfig(this.interval, this.queue_max, this.count, super.buildUnknownFields());
        }

        public Builder count(Integer num) {
            this.count = num;
            return this;
        }

        public Builder interval(Integer num) {
            this.interval = num;
            return this;
        }

        public Builder queue_max(Integer num) {
            this.queue_max = num;
            return this;
        }
    }

    private static final class ProtoAdapter_MotionConfig extends ProtoAdapter<MotionConfig> {
        public ProtoAdapter_MotionConfig() {
            super(FieldEncoding.LENGTH_DELIMITED, MotionConfig.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public MotionConfig decode(ProtoReader protoReader) {
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
                        builder.interval(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.queue_max(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.count(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, MotionConfig motionConfig) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, motionConfig.interval);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, motionConfig.queue_max);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, motionConfig.count);
            protoWriter.writeBytes(motionConfig.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(MotionConfig motionConfig) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, motionConfig.interval) + ProtoAdapter.UINT32.encodedSizeWithTag(2, motionConfig.queue_max) + ProtoAdapter.UINT32.encodedSizeWithTag(3, motionConfig.count) + motionConfig.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public MotionConfig redact(MotionConfig motionConfig) {
            Builder builderNewBuilder = motionConfig.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public MotionConfig(Integer num, Integer num2, Integer num3) {
        this(num, num2, num3, ByteString.EMPTY);
    }

    public MotionConfig(Integer num, Integer num2, Integer num3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.interval = num;
        this.queue_max = num2;
        this.count = num3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MotionConfig)) {
            return false;
        }
        MotionConfig motionConfig = (MotionConfig) obj;
        return unknownFields().equals(motionConfig.unknownFields()) && Internal.equals(this.interval, motionConfig.interval) && Internal.equals(this.queue_max, motionConfig.queue_max) && Internal.equals(this.count, motionConfig.count);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.interval != null ? this.interval.hashCode() : 0)) * 37) + (this.queue_max != null ? this.queue_max.hashCode() : 0)) * 37) + (this.count != null ? this.count.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.interval = this.interval;
        builder.queue_max = this.queue_max;
        builder.count = this.count;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.interval != null) {
            sb.append(", interval=");
            sb.append(this.interval);
        }
        if (this.queue_max != null) {
            sb.append(", queue_max=");
            sb.append(this.queue_max);
        }
        if (this.count != null) {
            sb.append(", count=");
            sb.append(this.count);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "MotionConfig{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
