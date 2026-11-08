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
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class StrategyWaterFall extends AndroidMessage<StrategyWaterFall, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer ab_flag;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer concurrent_count;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", label = WireField.Label.REPEATED, tag = 4)
    public final List<Integer> element_ids;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer traffic_weight;
    public static final ProtoAdapter<StrategyWaterFall> ADAPTER = new ProtoAdapter_StrategyWaterFall();
    public static final Parcelable.Creator<StrategyWaterFall> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_AB_FLAG = 0;
    public static final Integer DEFAULT_TRAFFIC_WEIGHT = 0;
    public static final Integer DEFAULT_CONCURRENT_COUNT = 0;

    public static final class Builder extends Message.Builder<StrategyWaterFall, Builder> {
        public Integer ab_flag = StrategyWaterFall.DEFAULT_AB_FLAG;
        public Integer traffic_weight = StrategyWaterFall.DEFAULT_TRAFFIC_WEIGHT;
        public Integer concurrent_count = StrategyWaterFall.DEFAULT_CONCURRENT_COUNT;
        public List<Integer> element_ids = Internal.newMutableList();

        public Builder ab_flag(Integer num) {
            this.ab_flag = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public StrategyWaterFall build() {
            return new StrategyWaterFall(this.ab_flag, this.traffic_weight, this.concurrent_count, this.element_ids, super.buildUnknownFields());
        }

        public Builder concurrent_count(Integer num) {
            this.concurrent_count = num;
            return this;
        }

        public Builder element_ids(List<Integer> list) {
            Internal.checkElementsNotNull(list);
            this.element_ids = list;
            return this;
        }

        public Builder traffic_weight(Integer num) {
            this.traffic_weight = num;
            return this;
        }
    }

    private static final class ProtoAdapter_StrategyWaterFall extends ProtoAdapter<StrategyWaterFall> {
        public ProtoAdapter_StrategyWaterFall() {
            super(FieldEncoding.LENGTH_DELIMITED, StrategyWaterFall.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public StrategyWaterFall decode(ProtoReader protoReader) {
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
                        builder.ab_flag(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.traffic_weight(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.concurrent_count(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 4:
                        builder.element_ids.add(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, StrategyWaterFall strategyWaterFall) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, strategyWaterFall.ab_flag);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, strategyWaterFall.traffic_weight);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, strategyWaterFall.concurrent_count);
            ProtoAdapter.UINT32.asRepeated().encodeWithTag(protoWriter, 4, strategyWaterFall.element_ids);
            protoWriter.writeBytes(strategyWaterFall.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(StrategyWaterFall strategyWaterFall) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, strategyWaterFall.ab_flag) + ProtoAdapter.UINT32.encodedSizeWithTag(2, strategyWaterFall.traffic_weight) + ProtoAdapter.UINT32.encodedSizeWithTag(3, strategyWaterFall.concurrent_count) + ProtoAdapter.UINT32.asRepeated().encodedSizeWithTag(4, strategyWaterFall.element_ids) + strategyWaterFall.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public StrategyWaterFall redact(StrategyWaterFall strategyWaterFall) {
            Builder builderNewBuilder = strategyWaterFall.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public StrategyWaterFall(Integer num, Integer num2, Integer num3, List<Integer> list) {
        this(num, num2, num3, list, ByteString.EMPTY);
    }

    public StrategyWaterFall(Integer num, Integer num2, Integer num3, List<Integer> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.ab_flag = num;
        this.traffic_weight = num2;
        this.concurrent_count = num3;
        this.element_ids = Internal.immutableCopyOf("element_ids", list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StrategyWaterFall)) {
            return false;
        }
        StrategyWaterFall strategyWaterFall = (StrategyWaterFall) obj;
        return unknownFields().equals(strategyWaterFall.unknownFields()) && Internal.equals(this.ab_flag, strategyWaterFall.ab_flag) && Internal.equals(this.traffic_weight, strategyWaterFall.traffic_weight) && Internal.equals(this.concurrent_count, strategyWaterFall.concurrent_count) && this.element_ids.equals(strategyWaterFall.element_ids);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.ab_flag != null ? this.ab_flag.hashCode() : 0)) * 37) + (this.traffic_weight != null ? this.traffic_weight.hashCode() : 0)) * 37) + (this.concurrent_count != null ? this.concurrent_count.hashCode() : 0)) * 37) + this.element_ids.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.ab_flag = this.ab_flag;
        builder.traffic_weight = this.traffic_weight;
        builder.concurrent_count = this.concurrent_count;
        builder.element_ids = Internal.copyOf("element_ids", this.element_ids);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.ab_flag != null) {
            sb.append(", ab_flag=");
            sb.append(this.ab_flag);
        }
        if (this.traffic_weight != null) {
            sb.append(", traffic_weight=");
            sb.append(this.traffic_weight);
        }
        if (this.concurrent_count != null) {
            sb.append(", concurrent_count=");
            sb.append(this.concurrent_count);
        }
        if (!this.element_ids.isEmpty()) {
            sb.append(", element_ids=");
            sb.append(this.element_ids);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "StrategyWaterFall{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
