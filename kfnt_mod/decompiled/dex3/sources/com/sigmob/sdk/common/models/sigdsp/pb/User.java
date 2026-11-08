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
public final class User extends AndroidMessage<User, Builder> {
    public static final String DEFAULT_GENDER = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 3)
    public final List<String> app_list;

    @WireField(adapter = "com.sigmob.sdk.common.models.sigdsp.pb.Version#ADAPTER", tag = 5)
    public final Version app_market_version;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 8)
    public final Boolean disable_personalized_recommendation;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 2)
    public final String gender;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 7)
    public final Boolean is_minor;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 4)
    public final List<String> user_strategy;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer yob;
    public static final ProtoAdapter<User> ADAPTER = new ProtoAdapter_User();
    public static final Parcelable.Creator<User> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_YOB = 0;
    public static final Boolean DEFAULT_IS_MINOR = false;
    public static final Boolean DEFAULT_DISABLE_PERSONALIZED_RECOMMENDATION = false;

    public static final class Builder extends Message.Builder<User, Builder> {
        public Version app_market_version;
        public Boolean disable_personalized_recommendation;
        public String gender;
        public Boolean is_minor;
        public Integer yob;
        public List<String> app_list = Internal.newMutableList();
        public List<String> user_strategy = Internal.newMutableList();

        public Builder app_list(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.app_list = list;
            return this;
        }

        public Builder app_market_version(Version version) {
            this.app_market_version = version;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public User build() {
            return new User(this.yob, this.gender, this.app_list, this.user_strategy, this.app_market_version, this.is_minor, this.disable_personalized_recommendation, super.buildUnknownFields());
        }

        public Builder disable_personalized_recommendation(Boolean bool) {
            this.disable_personalized_recommendation = bool;
            return this;
        }

        public Builder gender(String str) {
            this.gender = str;
            return this;
        }

        public Builder is_minor(Boolean bool) {
            this.is_minor = bool;
            return this;
        }

        public Builder user_strategy(List<String> list) {
            Internal.checkElementsNotNull(list);
            this.user_strategy = list;
            return this;
        }

        public Builder yob(Integer num) {
            this.yob = num;
            return this;
        }
    }

    private static final class ProtoAdapter_User extends ProtoAdapter<User> {
        public ProtoAdapter_User() {
            super(FieldEncoding.LENGTH_DELIMITED, User.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public User decode(ProtoReader protoReader) {
            List<String> list;
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
                        builder.yob(ProtoAdapter.UINT32.decode(protoReader));
                        continue;
                    case 2:
                        builder.gender(ProtoAdapter.STRING.decode(protoReader));
                        continue;
                    case 3:
                        list = builder.app_list;
                        break;
                    case 4:
                        list = builder.user_strategy;
                        break;
                    case 5:
                        builder.app_market_version(Version.ADAPTER.decode(protoReader));
                        continue;
                    case 6:
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        continue;
                    case 7:
                        builder.is_minor(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                    case 8:
                        builder.disable_personalized_recommendation(ProtoAdapter.BOOL.decode(protoReader));
                        continue;
                }
                list.add(ProtoAdapter.STRING.decode(protoReader));
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, User user) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, user.yob);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 2, user.gender);
            ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 3, user.app_list);
            ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 4, user.user_strategy);
            Version.ADAPTER.encodeWithTag(protoWriter, 5, user.app_market_version);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 7, user.is_minor);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 8, user.disable_personalized_recommendation);
            protoWriter.writeBytes(user.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(User user) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, user.yob) + ProtoAdapter.STRING.encodedSizeWithTag(2, user.gender) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(3, user.app_list) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(4, user.user_strategy) + Version.ADAPTER.encodedSizeWithTag(5, user.app_market_version) + ProtoAdapter.BOOL.encodedSizeWithTag(7, user.is_minor) + ProtoAdapter.BOOL.encodedSizeWithTag(8, user.disable_personalized_recommendation) + user.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public User redact(User user) {
            Builder builderNewBuilder = user.newBuilder();
            if (builderNewBuilder.app_market_version != null) {
                builderNewBuilder.app_market_version = Version.ADAPTER.redact(builderNewBuilder.app_market_version);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public User(Integer num, String str, List<String> list, List<String> list2, Version version, Boolean bool, Boolean bool2) {
        this(num, str, list, list2, version, bool, bool2, ByteString.EMPTY);
    }

    public User(Integer num, String str, List<String> list, List<String> list2, Version version, Boolean bool, Boolean bool2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.yob = num;
        this.gender = str;
        this.app_list = Internal.immutableCopyOf("app_list", list);
        this.user_strategy = Internal.immutableCopyOf("user_strategy", list2);
        this.app_market_version = version;
        this.is_minor = bool;
        this.disable_personalized_recommendation = bool2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return unknownFields().equals(user.unknownFields()) && Internal.equals(this.yob, user.yob) && Internal.equals(this.gender, user.gender) && this.app_list.equals(user.app_list) && this.user_strategy.equals(user.user_strategy) && Internal.equals(this.app_market_version, user.app_market_version) && Internal.equals(this.is_minor, user.is_minor) && Internal.equals(this.disable_personalized_recommendation, user.disable_personalized_recommendation);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((unknownFields().hashCode() * 37) + (this.yob != null ? this.yob.hashCode() : 0)) * 37) + (this.gender != null ? this.gender.hashCode() : 0)) * 37) + this.app_list.hashCode()) * 37) + this.user_strategy.hashCode()) * 37) + (this.app_market_version != null ? this.app_market_version.hashCode() : 0)) * 37) + (this.is_minor != null ? this.is_minor.hashCode() : 0)) * 37) + (this.disable_personalized_recommendation != null ? this.disable_personalized_recommendation.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.yob = this.yob;
        builder.gender = this.gender;
        builder.app_list = Internal.copyOf("app_list", this.app_list);
        builder.user_strategy = Internal.copyOf("user_strategy", this.user_strategy);
        builder.app_market_version = this.app_market_version;
        builder.is_minor = this.is_minor;
        builder.disable_personalized_recommendation = this.disable_personalized_recommendation;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.yob != null) {
            sb.append(", yob=");
            sb.append(this.yob);
        }
        if (this.gender != null) {
            sb.append(", gender=");
            sb.append(this.gender);
        }
        if (!this.app_list.isEmpty()) {
            sb.append(", app_list=");
            sb.append(this.app_list);
        }
        if (!this.user_strategy.isEmpty()) {
            sb.append(", user_strategy=");
            sb.append(this.user_strategy);
        }
        if (this.app_market_version != null) {
            sb.append(", app_market_version=");
            sb.append(this.app_market_version);
        }
        if (this.is_minor != null) {
            sb.append(", is_minor=");
            sb.append(this.is_minor);
        }
        if (this.disable_personalized_recommendation != null) {
            sb.append(", disable_personalized_recommendation=");
            sb.append(this.disable_personalized_recommendation);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "User{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
