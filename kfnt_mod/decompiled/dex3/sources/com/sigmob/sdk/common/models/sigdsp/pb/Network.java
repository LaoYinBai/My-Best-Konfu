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
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Network extends AndroidMessage<Network, Builder> {
    public static final String DEFAULT_CARRIER_NAME = "";
    public static final String DEFAULT_IPV4 = "";
    public static final String DEFAULT_MAC = "";
    public static final String DEFAULT_OPERATOR = "";
    public static final String DEFAULT_UA = "";
    public static final String DEFAULT_WIFI_ID = "";
    public static final String DEFAULT_WIFI_MAC = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 10)
    public final String carrier_name;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer connection_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", keyAdapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 9)
    public final Map<String, String> ips;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String ipv4;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String mac;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String operator;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer operator_type;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String ua;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String wifi_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String wifi_mac;
    public static final ProtoAdapter<Network> ADAPTER = new ProtoAdapter_Network();
    public static final Parcelable.Creator<Network> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_CONNECTION_TYPE = 0;
    public static final Integer DEFAULT_OPERATOR_TYPE = 0;

    public static final class Builder extends Message.Builder<Network, Builder> {
        public String carrier_name;
        public String ipv4 = "";
        public Integer connection_type = Network.DEFAULT_CONNECTION_TYPE;
        public Integer operator_type = Network.DEFAULT_OPERATOR_TYPE;
        public String ua = "";
        public String operator = "";
        public String mac = "";
        public String wifi_mac = "";
        public String wifi_id = "";
        public Map<String, String> ips = Internal.newMutableMap();

        @Override // com.sigmob.wire.Message.Builder
        public Network build() {
            return new Network(this.ipv4, this.connection_type, this.operator_type, this.ua, this.operator, this.mac, this.wifi_mac, this.wifi_id, this.ips, this.carrier_name, super.buildUnknownFields());
        }

        public Builder carrier_name(String str) {
            this.carrier_name = str;
            return this;
        }

        public Builder connection_type(Integer num) {
            this.connection_type = num;
            return this;
        }

        public Builder ips(Map<String, String> map) {
            Internal.checkElementsNotNull(map);
            this.ips = map;
            return this;
        }

        public Builder ipv4(String str) {
            this.ipv4 = str;
            return this;
        }

        public Builder mac(String str) {
            this.mac = str;
            return this;
        }

        public Builder operator(String str) {
            this.operator = str;
            return this;
        }

        public Builder operator_type(Integer num) {
            this.operator_type = num;
            return this;
        }

        public Builder ua(String str) {
            this.ua = str;
            return this;
        }

        public Builder wifi_id(String str) {
            this.wifi_id = str;
            return this;
        }

        public Builder wifi_mac(String str) {
            this.wifi_mac = str;
            return this;
        }
    }

    private static final class ProtoAdapter_Network extends ProtoAdapter<Network> {
        private final ProtoAdapter<Map<String, String>> a;

        public ProtoAdapter_Network() {
            super(FieldEncoding.LENGTH_DELIMITED, Network.class);
            this.a = ProtoAdapter.newMapAdapter(ProtoAdapter.STRING, ProtoAdapter.STRING);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Network decode(ProtoReader protoReader) {
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
                        builder.ipv4(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.connection_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.operator_type(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 4:
                        builder.ua(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 5:
                        builder.operator(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 6:
                        builder.mac(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 7:
                        builder.wifi_mac(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 8:
                        builder.wifi_id(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 9:
                        builder.ips.putAll(this.a.decode(protoReader));
                        break;
                    case 10:
                        builder.carrier_name(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Network network) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, network.ipv4);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, network.connection_type);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, network.operator_type);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, network.ua);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, network.operator);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, network.mac);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, network.wifi_mac);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, network.wifi_id);
            this.a.encodeWithTag(protoWriter, 9, network.ips);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 10, network.carrier_name);
            protoWriter.writeBytes(network.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Network network) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, network.ipv4) + ProtoAdapter.UINT32.encodedSizeWithTag(2, network.connection_type) + ProtoAdapter.UINT32.encodedSizeWithTag(3, network.operator_type) + ProtoAdapter.STRING.encodedSizeWithTag(4, network.ua) + ProtoAdapter.STRING.encodedSizeWithTag(5, network.operator) + ProtoAdapter.STRING.encodedSizeWithTag(6, network.mac) + ProtoAdapter.STRING.encodedSizeWithTag(7, network.wifi_mac) + ProtoAdapter.STRING.encodedSizeWithTag(8, network.wifi_id) + this.a.encodedSizeWithTag(9, network.ips) + ProtoAdapter.STRING.encodedSizeWithTag(10, network.carrier_name) + network.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Network redact(Network network) {
            Builder builderNewBuilder = network.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Network(String str, Integer num, Integer num2, String str2, String str3, String str4, String str5, String str6, Map<String, String> map, String str7) {
        this(str, num, num2, str2, str3, str4, str5, str6, map, str7, ByteString.EMPTY);
    }

    public Network(String str, Integer num, Integer num2, String str2, String str3, String str4, String str5, String str6, Map<String, String> map, String str7, ByteString byteString) {
        super(ADAPTER, byteString);
        this.ipv4 = str;
        this.connection_type = num;
        this.operator_type = num2;
        this.ua = str2;
        this.operator = str3;
        this.mac = str4;
        this.wifi_mac = str5;
        this.wifi_id = str6;
        this.ips = Internal.immutableCopyOf("ips", map);
        this.carrier_name = str7;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Network)) {
            return false;
        }
        Network network = (Network) obj;
        return unknownFields().equals(network.unknownFields()) && Internal.equals(this.ipv4, network.ipv4) && Internal.equals(this.connection_type, network.connection_type) && Internal.equals(this.operator_type, network.operator_type) && Internal.equals(this.ua, network.ua) && Internal.equals(this.operator, network.operator) && Internal.equals(this.mac, network.mac) && Internal.equals(this.wifi_mac, network.wifi_mac) && Internal.equals(this.wifi_id, network.wifi_id) && this.ips.equals(network.ips) && Internal.equals(this.carrier_name, network.carrier_name);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((unknownFields().hashCode() * 37) + (this.ipv4 != null ? this.ipv4.hashCode() : 0)) * 37) + (this.connection_type != null ? this.connection_type.hashCode() : 0)) * 37) + (this.operator_type != null ? this.operator_type.hashCode() : 0)) * 37) + (this.ua != null ? this.ua.hashCode() : 0)) * 37) + (this.operator != null ? this.operator.hashCode() : 0)) * 37) + (this.mac != null ? this.mac.hashCode() : 0)) * 37) + (this.wifi_mac != null ? this.wifi_mac.hashCode() : 0)) * 37) + (this.wifi_id != null ? this.wifi_id.hashCode() : 0)) * 37) + this.ips.hashCode()) * 37) + (this.carrier_name != null ? this.carrier_name.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.ipv4 = this.ipv4;
        builder.connection_type = this.connection_type;
        builder.operator_type = this.operator_type;
        builder.ua = this.ua;
        builder.operator = this.operator;
        builder.mac = this.mac;
        builder.wifi_mac = this.wifi_mac;
        builder.wifi_id = this.wifi_id;
        builder.ips = Internal.copyOf("ips", this.ips);
        builder.carrier_name = this.carrier_name;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.ipv4 != null) {
            sb.append(", ipv4=");
            sb.append(this.ipv4);
        }
        if (this.connection_type != null) {
            sb.append(", connection_type=");
            sb.append(this.connection_type);
        }
        if (this.operator_type != null) {
            sb.append(", operator_type=");
            sb.append(this.operator_type);
        }
        if (this.ua != null) {
            sb.append(", ua=");
            sb.append(this.ua);
        }
        if (this.operator != null) {
            sb.append(", operator=");
            sb.append(this.operator);
        }
        if (this.mac != null) {
            sb.append(", mac=");
            sb.append(this.mac);
        }
        if (this.wifi_mac != null) {
            sb.append(", wifi_mac=");
            sb.append(this.wifi_mac);
        }
        if (this.wifi_id != null) {
            sb.append(", wifi_id=");
            sb.append(this.wifi_id);
        }
        if (!this.ips.isEmpty()) {
            sb.append(", ips=");
            sb.append(this.ips);
        }
        if (this.carrier_name != null) {
            sb.append(", carrier_name=");
            sb.append(this.carrier_name);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Network{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
