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
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class App extends AndroidMessage<App, Builder> {
    public static final String DEFAULT_APP_ID = "";
    public static final String DEFAULT_APP_PACKAGE = "";
    public static final String DEFAULT_CHANNEL_ID = "";
    public static final String DEFAULT_IDFV = "";
    public static final String DEFAULT_NAME = "";
    public static final String DEFAULT_PRODUCT_ID = "";
    public static final String DEFAULT_SUPPORT_SK_VERSION = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 10)
    public final List<String> ad_network_ids;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String app_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String app_package;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Version#ADAPTER", tag = 2)
    public final Version app_version;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 7)
    public final String channel_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 6)
    public final String idfv;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 5)
    public final String name;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 4)
    public final Integer orientation;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String product_id;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", label = WireField.Label.REPEATED, tag = 12)
    public final List<Integer> sdk_ext_cap;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 9)
    public final Boolean support_http;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String support_sk_version;
    public static final ProtoAdapter<App> ADAPTER = new ProtoAdapter_App();
    public static final Parcelable.Creator<App> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_ORIENTATION = 0;
    public static final Boolean DEFAULT_SUPPORT_HTTP = false;

    public static final class Builder extends Message.Builder<App, Builder> {
        public Version app_version;
        public String support_sk_version;
        public String app_id = "";
        public String app_package = "";
        public Integer orientation = App.DEFAULT_ORIENTATION;
        public String name = "";
        public String idfv = "";
        public String channel_id = "";
        public String product_id = "";
        public Boolean support_http = App.DEFAULT_SUPPORT_HTTP;
        public List<String> ad_network_ids = Internal.newMutableList();
        public List<Integer> sdk_ext_cap = Internal.newMutableList();

        public Builder ad_network_ids(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.ad_network_ids = list;
            return this;
        }

        public Builder app_id(String str) {
            this.app_id = str;
            return this;
        }

        public Builder app_package(String str) {
            this.app_package = str;
            return this;
        }

        public Builder app_version(Version version) {
            this.app_version = version;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public App build() {
            return new App(this.app_id, this.app_version, this.app_package, this.orientation, this.name, this.idfv, this.channel_id, this.product_id, this.support_http, this.ad_network_ids, this.support_sk_version, this.sdk_ext_cap, super.buildUnknownFields());
        }

        public Builder channel_id(String str) {
            this.channel_id = str;
            return this;
        }

        public Builder idfv(String str) {
            this.idfv = str;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder orientation(Integer num) {
            this.orientation = num;
            return this;
        }

        public Builder product_id(String str) {
            this.product_id = str;
            return this;
        }

        public Builder sdk_ext_cap(List<Integer> list) {
            Internal.checkElementsNotNull(list);
            this.sdk_ext_cap = list;
            return this;
        }

        public Builder support_http(Boolean bool) {
            this.support_http = bool;
            return this;
        }

        public Builder support_sk_version(String str) {
            this.support_sk_version = str;
            return this;
        }
    }

    private static final class ProtoAdapter_App extends ProtoAdapter<App> {
        public ProtoAdapter_App() {
            super(FieldEncoding.LENGTH_DELIMITED, App.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public App decode(ProtoReader protoReader) {
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
                        builder.app_id(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 2:
                        builder.app_version(Version.ADAPTER.decode(protoReader));
                        continue;
                    case 3:
                        builder.app_package(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 4:
                        builder.orientation(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 5:
                        builder.name(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 6:
                        builder.idfv(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 7:
                        builder.channel_id(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 8:
                        builder.product_id(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 9:
                        builder.support_http(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 10:
                        list = builder.ad_network_ids;
                        protoAdapter = ProtoAdapter.STRING;
                        break;
                    case 11:
                        builder.support_sk_version(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 12:
                        list = builder.sdk_ext_cap;
                        protoAdapter = ProtoAdapter.UINT32;
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        continue;
                }
                list.add(protoAdapter.decode(protoReader));
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, App app) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, app.app_id);
            Version.ADAPTER.encodeWithTag(protoWriter, 2, app.app_version);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, app.app_package);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 4, app.orientation);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 5, app.name);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 6, app.idfv);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 7, app.channel_id);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, app.product_id);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 9, app.support_http);
            ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 10, app.ad_network_ids);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, app.support_sk_version);
            ProtoAdapter.UINT32.asRepeated().encodeWithTag(protoWriter, 12, app.sdk_ext_cap);
            protoWriter.writeBytes(app.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(App app) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, app.app_id) + Version.ADAPTER.encodedSizeWithTag(2, app.app_version) + ProtoAdapter.STRING.encodedSizeWithTag(3, app.app_package) + ProtoAdapter.UINT32.encodedSizeWithTag(4, app.orientation) + ProtoAdapter.STRING.encodedSizeWithTag(5, app.name) + ProtoAdapter.STRING.encodedSizeWithTag(6, app.idfv) + ProtoAdapter.STRING.encodedSizeWithTag(7, app.channel_id) + ProtoAdapter.STRING.encodedSizeWithTag(8, app.product_id) + ProtoAdapter.BOOL.encodedSizeWithTag(9, app.support_http) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(10, app.ad_network_ids) + ProtoAdapter.STRING.encodedSizeWithTag(11, app.support_sk_version) + ProtoAdapter.UINT32.asRepeated().encodedSizeWithTag(12, app.sdk_ext_cap) + app.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public App redact(App app) {
            Builder builderNewBuilder = app.newBuilder();
            if (builderNewBuilder.app_version != null) {
                builderNewBuilder.app_version = Version.ADAPTER.redact(builderNewBuilder.app_version);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public App(String str, Version version, String str2, Integer num, String str3, String str4, String str5, String str6, Boolean bool, List<String> list, String str7, List<Integer> list2) {
        this(str, version, str2, num, str3, str4, str5, str6, bool, list, str7, list2, ByteString.EMPTY);
    }

    public App(String str, Version version, String str2, Integer num, String str3, String str4, String str5, String str6, Boolean bool, List<String> list, String str7, List<Integer> list2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.app_id = str;
        this.app_version = version;
        this.app_package = str2;
        this.orientation = num;
        this.name = str3;
        this.idfv = str4;
        this.channel_id = str5;
        this.product_id = str6;
        this.support_http = bool;
        this.ad_network_ids = Internal.immutableCopyOf("ad_network_ids", list);
        this.support_sk_version = str7;
        this.sdk_ext_cap = Internal.immutableCopyOf("sdk_ext_cap", list2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof App)) {
            return false;
        }
        App app = (App) obj;
        return unknownFields().equals(app.unknownFields()) && Internal.equals(this.app_id, app.app_id) && Internal.equals(this.app_version, app.app_version) && Internal.equals(this.app_package, app.app_package) && Internal.equals(this.orientation, app.orientation) && Internal.equals(this.name, app.name) && Internal.equals(this.idfv, app.idfv) && Internal.equals(this.channel_id, app.channel_id) && Internal.equals(this.product_id, app.product_id) && Internal.equals(this.support_http, app.support_http) && this.ad_network_ids.equals(app.ad_network_ids) && Internal.equals(this.support_sk_version, app.support_sk_version) && this.sdk_ext_cap.equals(app.sdk_ext_cap);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.app_id != null ? this.app_id.hashCode() : 0)) * 37) + (this.app_version != null ? this.app_version.hashCode() : 0)) * 37) + (this.app_package != null ? this.app_package.hashCode() : 0)) * 37) + (this.orientation != null ? this.orientation.hashCode() : 0)) * 37) + (this.name != null ? this.name.hashCode() : 0)) * 37) + (this.idfv != null ? this.idfv.hashCode() : 0)) * 37) + (this.channel_id != null ? this.channel_id.hashCode() : 0)) * 37) + (this.product_id != null ? this.product_id.hashCode() : 0)) * 37) + (this.support_http != null ? this.support_http.hashCode() : 0)) * 37) + this.ad_network_ids.hashCode()) * 37) + (this.support_sk_version != null ? this.support_sk_version.hashCode() : 0)) * 37) + this.sdk_ext_cap.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.app_id = this.app_id;
        builder.app_version = this.app_version;
        builder.app_package = this.app_package;
        builder.orientation = this.orientation;
        builder.name = this.name;
        builder.idfv = this.idfv;
        builder.channel_id = this.channel_id;
        builder.product_id = this.product_id;
        builder.support_http = this.support_http;
        builder.ad_network_ids = Internal.copyOf("ad_network_ids", this.ad_network_ids);
        builder.support_sk_version = this.support_sk_version;
        builder.sdk_ext_cap = Internal.copyOf("sdk_ext_cap", this.sdk_ext_cap);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.app_id != null) {
            sb.append(", app_id=");
            sb.append(this.app_id);
        }
        if (this.app_version != null) {
            sb.append(", app_version=");
            sb.append(this.app_version);
        }
        if (this.app_package != null) {
            sb.append(", app_package=");
            sb.append(this.app_package);
        }
        if (this.orientation != null) {
            sb.append(", orientation=");
            sb.append(this.orientation);
        }
        if (this.name != null) {
            sb.append(", name=");
            sb.append(this.name);
        }
        if (this.idfv != null) {
            sb.append(", idfv=");
            sb.append(this.idfv);
        }
        if (this.channel_id != null) {
            sb.append(", channel_id=");
            sb.append(this.channel_id);
        }
        if (this.product_id != null) {
            sb.append(", product_id=");
            sb.append(this.product_id);
        }
        if (this.support_http != null) {
            sb.append(", support_http=");
            sb.append(this.support_http);
        }
        if (!this.ad_network_ids.isEmpty()) {
            sb.append(", ad_network_ids=");
            sb.append(this.ad_network_ids);
        }
        if (this.support_sk_version != null) {
            sb.append(", support_sk_version=");
            sb.append(this.support_sk_version);
        }
        if (!this.sdk_ext_cap.isEmpty()) {
            sb.append(", sdk_ext_cap=");
            sb.append(this.sdk_ext_cap);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "App{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
