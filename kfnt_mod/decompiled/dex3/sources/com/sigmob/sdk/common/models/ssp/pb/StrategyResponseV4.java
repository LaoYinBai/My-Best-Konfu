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
public final class StrategyResponseV4 extends AndroidMessage<StrategyResponseV4, Builder> {
    public static final String DEFAULT_ERROR_MESSAGE = "";
    public static final String DEFAULT_STRATEGY_ID = "";
    public static final String DEFAULT_UID = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.MediationApp#ADAPTER", label = WireField.Label.REPEATED, tag = 5)
    public final List<MediationApp> apps;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.MediationChannel#ADAPTER", label = WireField.Label.REPEATED, tag = 8)
    public final List<MediationChannel> channels;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer code;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.MediationElement#ADAPTER", label = WireField.Label.REPEATED, tag = 4)
    public final List<MediationElement> elements;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 9)
    public final Boolean enable_ab_test;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String error_message;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 7)
    public final Integer single_channel_timeout;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.AdSlot#ADAPTER", label = WireField.Label.REPEATED, tag = 6)
    public final List<AdSlot> slots;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String strategy_id;

    @WireField(adapter = "com.sigmob.sdk.base.models.ssp.pb.StrategyWaterFall#ADAPTER", tag = 10)
    public final StrategyWaterFall strategy_waterfall;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String uid;
    public static final ProtoAdapter<StrategyResponseV4> ADAPTER = new ProtoAdapter_StrategyResponseV4();
    public static final Parcelable.Creator<StrategyResponseV4> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CODE = 0;
    public static final Integer DEFAULT_SINGLE_CHANNEL_TIMEOUT = 0;
    public static final Boolean DEFAULT_ENABLE_AB_TEST = false;

    public static final class Builder extends Message.Builder<StrategyResponseV4, Builder> {
        public StrategyWaterFall strategy_waterfall;
        public Integer code = StrategyResponseV4.DEFAULT_CODE;
        public String error_message = "";
        public String uid = "";
        public Integer single_channel_timeout = StrategyResponseV4.DEFAULT_SINGLE_CHANNEL_TIMEOUT;
        public Boolean enable_ab_test = StrategyResponseV4.DEFAULT_ENABLE_AB_TEST;
        public String strategy_id = "";
        public List<MediationElement> elements = Internal.newMutableList();
        public List<MediationApp> apps = Internal.newMutableList();
        public List<AdSlot> slots = Internal.newMutableList();
        public List<MediationChannel> channels = Internal.newMutableList();

        public Builder apps(List<MediationApp> list) {
            Internal.checkElementsNotNull(list);
            this.apps = list;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public StrategyResponseV4 build() {
            return new StrategyResponseV4(this.code, this.error_message, this.uid, this.elements, this.apps, this.slots, this.single_channel_timeout, this.channels, this.enable_ab_test, this.strategy_waterfall, this.strategy_id, super.buildUnknownFields());
        }

        public Builder channels(List<MediationChannel> list) {
            Internal.checkElementsNotNull(list);
            this.channels = list;
            return this;
        }

        public Builder code(Integer num) {
            this.code = num;
            return this;
        }

        public Builder elements(List<MediationElement> list) {
            Internal.checkElementsNotNull(list);
            this.elements = list;
            return this;
        }

        public Builder enable_ab_test(Boolean bool) {
            this.enable_ab_test = bool;
            return this;
        }

        public Builder error_message(String str) {
            this.error_message = str;
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

        public Builder strategy_id(String str) {
            this.strategy_id = str;
            return this;
        }

        public Builder strategy_waterfall(StrategyWaterFall strategyWaterFall) {
            this.strategy_waterfall = strategyWaterFall;
            return this;
        }

        public Builder uid(String str) {
            this.uid = str;
            return this;
        }
    }

    private static final class ProtoAdapter_StrategyResponseV4 extends ProtoAdapter<StrategyResponseV4> {
        public ProtoAdapter_StrategyResponseV4() {
            super(FieldEncoding.LENGTH_DELIMITED, StrategyResponseV4.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public StrategyResponseV4 decode(ProtoReader protoReader) {
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
                        builder.uid(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 4:
                        list = builder.elements;
                        protoAdapter = MediationElement.ADAPTER;
                        break;
                    case 5:
                        list = builder.apps;
                        protoAdapter = MediationApp.ADAPTER;
                        break;
                    case 6:
                        list = builder.slots;
                        protoAdapter = AdSlot.ADAPTER;
                        break;
                    case 7:
                        builder.single_channel_timeout(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 8:
                        list = builder.channels;
                        protoAdapter = MediationChannel.ADAPTER;
                        break;
                    case 9:
                        builder.enable_ab_test(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 10:
                        builder.strategy_waterfall(StrategyWaterFall.ADAPTER.decode(protoReader));
                        continue;
                    case 11:
                        builder.strategy_id(ProtoAdapter.STRING.decode(protoReader));
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
        public void encode(ProtoWriter protoWriter, StrategyResponseV4 strategyResponseV4) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, strategyResponseV4.code);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, strategyResponseV4.error_message);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, strategyResponseV4.uid);
            MediationElement.ADAPTER.asRepeated().encodeWithTag(protoWriter, 4, strategyResponseV4.elements);
            MediationApp.ADAPTER.asRepeated().encodeWithTag(protoWriter, 5, strategyResponseV4.apps);
            AdSlot.ADAPTER.asRepeated().encodeWithTag(protoWriter, 6, strategyResponseV4.slots);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 7, strategyResponseV4.single_channel_timeout);
            MediationChannel.ADAPTER.asRepeated().encodeWithTag(protoWriter, 8, strategyResponseV4.channels);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 9, strategyResponseV4.enable_ab_test);
            StrategyWaterFall.ADAPTER.encodeWithTag(protoWriter, 10, strategyResponseV4.strategy_waterfall);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, strategyResponseV4.strategy_id);
            protoWriter.writeBytes(strategyResponseV4.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(StrategyResponseV4 strategyResponseV4) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, strategyResponseV4.code) + ProtoAdapter.STRING.encodedSizeWithTag(2, strategyResponseV4.error_message) + ProtoAdapter.STRING.encodedSizeWithTag(3, strategyResponseV4.uid) + MediationElement.ADAPTER.asRepeated().encodedSizeWithTag(4, strategyResponseV4.elements) + MediationApp.ADAPTER.asRepeated().encodedSizeWithTag(5, strategyResponseV4.apps) + AdSlot.ADAPTER.asRepeated().encodedSizeWithTag(6, strategyResponseV4.slots) + ProtoAdapter.UINT32.encodedSizeWithTag(7, strategyResponseV4.single_channel_timeout) + MediationChannel.ADAPTER.asRepeated().encodedSizeWithTag(8, strategyResponseV4.channels) + ProtoAdapter.BOOL.encodedSizeWithTag(9, strategyResponseV4.enable_ab_test) + StrategyWaterFall.ADAPTER.encodedSizeWithTag(10, strategyResponseV4.strategy_waterfall) + ProtoAdapter.STRING.encodedSizeWithTag(11, strategyResponseV4.strategy_id) + strategyResponseV4.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public StrategyResponseV4 redact(StrategyResponseV4 strategyResponseV4) {
            Builder builderNewBuilder = strategyResponseV4.newBuilder();
            Internal.redactElements(builderNewBuilder.elements, MediationElement.ADAPTER);
            Internal.redactElements(builderNewBuilder.apps, MediationApp.ADAPTER);
            Internal.redactElements(builderNewBuilder.slots, AdSlot.ADAPTER);
            Internal.redactElements(builderNewBuilder.channels, MediationChannel.ADAPTER);
            if (builderNewBuilder.strategy_waterfall != null) {
                builderNewBuilder.strategy_waterfall = StrategyWaterFall.ADAPTER.redact(builderNewBuilder.strategy_waterfall);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public StrategyResponseV4(Integer num, String str, String str2, List<MediationElement> list, List<MediationApp> list2, List<AdSlot> list3, Integer num2, List<MediationChannel> list4, Boolean bool, StrategyWaterFall strategyWaterFall, String str3) {
        this(num, str, str2, list, list2, list3, num2, list4, bool, strategyWaterFall, str3, ByteString.EMPTY);
    }

    public StrategyResponseV4(Integer num, String str, String str2, List<MediationElement> list, List<MediationApp> list2, List<AdSlot> list3, Integer num2, List<MediationChannel> list4, Boolean bool, StrategyWaterFall strategyWaterFall, String str3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.code = num;
        this.error_message = str;
        this.uid = str2;
        this.elements = Internal.immutableCopyOf("elements", list);
        this.apps = Internal.immutableCopyOf("apps", list2);
        this.slots = Internal.immutableCopyOf("slots", list3);
        this.single_channel_timeout = num2;
        this.channels = Internal.immutableCopyOf("channels", list4);
        this.enable_ab_test = bool;
        this.strategy_waterfall = strategyWaterFall;
        this.strategy_id = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StrategyResponseV4)) {
            return false;
        }
        StrategyResponseV4 strategyResponseV4 = (StrategyResponseV4) obj;
        return unknownFields().equals(strategyResponseV4.unknownFields()) && Internal.equals(this.code, strategyResponseV4.code) && Internal.equals(this.error_message, strategyResponseV4.error_message) && Internal.equals(this.uid, strategyResponseV4.uid) && this.elements.equals(strategyResponseV4.elements) && this.apps.equals(strategyResponseV4.apps) && this.slots.equals(strategyResponseV4.slots) && Internal.equals(this.single_channel_timeout, strategyResponseV4.single_channel_timeout) && this.channels.equals(strategyResponseV4.channels) && Internal.equals(this.enable_ab_test, strategyResponseV4.enable_ab_test) && Internal.equals(this.strategy_waterfall, strategyResponseV4.strategy_waterfall) && Internal.equals(this.strategy_id, strategyResponseV4.strategy_id);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((unknownFields().hashCode() * 37) + (this.code != null ? this.code.hashCode() : 0)) * 37) + (this.error_message != null ? this.error_message.hashCode() : 0)) * 37) + (this.uid != null ? this.uid.hashCode() : 0)) * 37) + this.elements.hashCode()) * 37) + this.apps.hashCode()) * 37) + this.slots.hashCode()) * 37) + (this.single_channel_timeout != null ? this.single_channel_timeout.hashCode() : 0)) * 37) + this.channels.hashCode()) * 37) + (this.enable_ab_test != null ? this.enable_ab_test.hashCode() : 0)) * 37) + (this.strategy_waterfall != null ? this.strategy_waterfall.hashCode() : 0)) * 37) + (this.strategy_id != null ? this.strategy_id.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.code = this.code;
        builder.error_message = this.error_message;
        builder.uid = this.uid;
        builder.elements = Internal.copyOf("elements", this.elements);
        builder.apps = Internal.copyOf("apps", this.apps);
        builder.slots = Internal.copyOf("slots", this.slots);
        builder.single_channel_timeout = this.single_channel_timeout;
        builder.channels = Internal.copyOf("channels", this.channels);
        builder.enable_ab_test = this.enable_ab_test;
        builder.strategy_waterfall = this.strategy_waterfall;
        builder.strategy_id = this.strategy_id;
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
        if (this.uid != null) {
            sb.append(", uid=");
            sb.append(this.uid);
        }
        if (!this.elements.isEmpty()) {
            sb.append(", elements=");
            sb.append(this.elements);
        }
        if (!this.apps.isEmpty()) {
            sb.append(", apps=");
            sb.append(this.apps);
        }
        if (!this.slots.isEmpty()) {
            sb.append(", slots=");
            sb.append(this.slots);
        }
        if (this.single_channel_timeout != null) {
            sb.append(", single_channel_timeout=");
            sb.append(this.single_channel_timeout);
        }
        if (!this.channels.isEmpty()) {
            sb.append(", channels=");
            sb.append(this.channels);
        }
        if (this.enable_ab_test != null) {
            sb.append(", enable_ab_test=");
            sb.append(this.enable_ab_test);
        }
        if (this.strategy_waterfall != null) {
            sb.append(", strategy_waterfall=");
            sb.append(this.strategy_waterfall);
        }
        if (this.strategy_id != null) {
            sb.append(", strategy_id=");
            sb.append(this.strategy_id);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "StrategyResponseV4{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
