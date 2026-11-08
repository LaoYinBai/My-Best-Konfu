package com.sigmob.sdk.common.models.ssp.pb;

import android.os.Parcelable;
import com.sigmob.sdk.common.models.sigdsp.pb.AdSlot;
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
public final class StrategyResponse extends AndroidMessage<StrategyResponse, Builder> {
    public static final String DEFAULT_ERROR_MESSAGE = "";
    public static final String DEFAULT_UID = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer code;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String error_message;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 6)
    public final Integer max_concurrent_operation_count;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 7)
    public final Integer single_channel_timeout;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.AdSlot#ADAPTER", label = WireField.Label.REPEATED, tag = 5)
    public final List<AdSlot> slots;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.Strategy#ADAPTER", label = WireField.Label.REPEATED, tag = 3)
    public final List<Strategy> strategy;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String uid;
    public static final ProtoAdapter<StrategyResponse> ADAPTER = new ProtoAdapter_StrategyResponse();
    public static final Parcelable.Creator<StrategyResponse> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CODE = 0;
    public static final Integer DEFAULT_MAX_CONCURRENT_OPERATION_COUNT = 0;
    public static final Integer DEFAULT_SINGLE_CHANNEL_TIMEOUT = 0;

    public static final class Builder extends Message.Builder<StrategyResponse, Builder> {
        public Integer code = StrategyResponse.DEFAULT_CODE;
        public String error_message = "";
        public String uid = "";
        public Integer max_concurrent_operation_count = StrategyResponse.DEFAULT_MAX_CONCURRENT_OPERATION_COUNT;
        public Integer single_channel_timeout = StrategyResponse.DEFAULT_SINGLE_CHANNEL_TIMEOUT;
        public List<Strategy> strategy = Internal.newMutableList();
        public List<AdSlot> slots = Internal.newMutableList();

        @Override // com.sigmob.wire.Message.Builder
        public StrategyResponse build() {
            return new StrategyResponse(this.code, this.error_message, this.strategy, this.uid, this.slots, this.max_concurrent_operation_count, this.single_channel_timeout, super.buildUnknownFields());
        }

        public Builder code(Integer num) {
            this.code = num;
            return this;
        }

        public Builder error_message(String str) {
            this.error_message = str;
            return this;
        }

        public Builder max_concurrent_operation_count(Integer num) {
            this.max_concurrent_operation_count = num;
            return this;
        }

        public Builder single_channel_timeout(Integer num) {
            this.single_channel_timeout = num;
            return this;
        }

        public Builder slots(List<AdSlot> list) {
            Internal.checkElementsNotNull(list);
            this.slots = list;
            return this;
        }

        public Builder strategy(List<Strategy> list) {
            Internal.checkElementsNotNull(list);
            this.strategy = list;
            return this;
        }

        public Builder uid(String str) {
            this.uid = str;
            return this;
        }
    }

    private static final class ProtoAdapter_StrategyResponse extends ProtoAdapter<StrategyResponse> {
        public ProtoAdapter_StrategyResponse() {
            super(FieldEncoding.LENGTH_DELIMITED, StrategyResponse.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public StrategyResponse decode(ProtoReader protoReader) {
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
                        builder.code(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 2:
                        builder.error_message(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 3:
                        list = builder.strategy;
                        protoAdapter = Strategy.ADAPTER;
                        break;
                    case 4:
                        builder.uid(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 5:
                        list = builder.slots;
                        protoAdapter = AdSlot.ADAPTER;
                        break;
                    case 6:
                        builder.max_concurrent_operation_count(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 7:
                        builder.single_channel_timeout(ProtoAdapter.UINT32.decode(protoReader));
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
        public void encode(ProtoWriter protoWriter, StrategyResponse strategyResponse) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, strategyResponse.code);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, strategyResponse.error_message);
            Strategy.ADAPTER.asRepeated().encodeWithTag(protoWriter, 3, strategyResponse.strategy);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, strategyResponse.uid);
            AdSlot.ADAPTER.asRepeated().encodeWithTag(protoWriter, 5, strategyResponse.slots);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 6, strategyResponse.max_concurrent_operation_count);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 7, strategyResponse.single_channel_timeout);
            protoWriter.writeBytes(strategyResponse.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(StrategyResponse strategyResponse) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, strategyResponse.code) + ProtoAdapter.STRING.encodedSizeWithTag(2, strategyResponse.error_message) + Strategy.ADAPTER.asRepeated().encodedSizeWithTag(3, strategyResponse.strategy) + ProtoAdapter.STRING.encodedSizeWithTag(4, strategyResponse.uid) + AdSlot.ADAPTER.asRepeated().encodedSizeWithTag(5, strategyResponse.slots) + ProtoAdapter.UINT32.encodedSizeWithTag(6, strategyResponse.max_concurrent_operation_count) + ProtoAdapter.UINT32.encodedSizeWithTag(7, strategyResponse.single_channel_timeout) + strategyResponse.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public StrategyResponse redact(StrategyResponse strategyResponse) {
            Builder builderNewBuilder = strategyResponse.newBuilder();
            Internal.redactElements(builderNewBuilder.strategy, Strategy.ADAPTER);
            Internal.redactElements(builderNewBuilder.slots, AdSlot.ADAPTER);
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public StrategyResponse(Integer num, String str, List<Strategy> list, String str2, List<AdSlot> list2, Integer num2, Integer num3) {
        this(num, str, list, str2, list2, num2, num3, ByteString.EMPTY);
    }

    public StrategyResponse(Integer num, String str, List<Strategy> list, String str2, List<AdSlot> list2, Integer num2, Integer num3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.code = num;
        this.error_message = str;
        this.strategy = Internal.immutableCopyOf("strategy", list);
        this.uid = str2;
        this.slots = Internal.immutableCopyOf("slots", list2);
        this.max_concurrent_operation_count = num2;
        this.single_channel_timeout = num3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StrategyResponse)) {
            return false;
        }
        StrategyResponse strategyResponse = (StrategyResponse) obj;
        return unknownFields().equals(strategyResponse.unknownFields()) && Internal.equals(this.code, strategyResponse.code) && Internal.equals(this.error_message, strategyResponse.error_message) && this.strategy.equals(strategyResponse.strategy) && Internal.equals(this.uid, strategyResponse.uid) && this.slots.equals(strategyResponse.slots) && Internal.equals(this.max_concurrent_operation_count, strategyResponse.max_concurrent_operation_count) && Internal.equals(this.single_channel_timeout, strategyResponse.single_channel_timeout);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((unknownFields().hashCode() * 37) + (this.code != null ? this.code.hashCode() : 0)) * 37) + (this.error_message != null ? this.error_message.hashCode() : 0)) * 37) + this.strategy.hashCode()) * 37) + (this.uid != null ? this.uid.hashCode() : 0)) * 37) + this.slots.hashCode()) * 37) + (this.max_concurrent_operation_count != null ? this.max_concurrent_operation_count.hashCode() : 0)) * 37) + (this.single_channel_timeout != null ? this.single_channel_timeout.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.code = this.code;
        builder.error_message = this.error_message;
        builder.strategy = Internal.copyOf("strategy", this.strategy);
        builder.uid = this.uid;
        builder.slots = Internal.copyOf("slots", this.slots);
        builder.max_concurrent_operation_count = this.max_concurrent_operation_count;
        builder.single_channel_timeout = this.single_channel_timeout;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.code != null) {
            sb.append(", code=");
            sb.append(this.code);
        }
        if (this.error_message != null) {
            sb.append(", error_message=");
            sb.append(this.error_message);
        }
        if (!this.strategy.isEmpty()) {
            sb.append(", strategy=");
            sb.append(this.strategy);
        }
        if (this.uid != null) {
            sb.append(", uid=");
            sb.append(this.uid);
        }
        if (!this.slots.isEmpty()) {
            sb.append(", slots=");
            sb.append(this.slots);
        }
        if (this.max_concurrent_operation_count != null) {
            sb.append(", max_concurrent_operation_count=");
            sb.append(this.max_concurrent_operation_count);
        }
        if (this.single_channel_timeout != null) {
            sb.append(", single_channel_timeout=");
            sb.append(this.single_channel_timeout);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "StrategyResponse{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
