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
public final class Version extends AndroidMessage<Version, Builder> {
    public static final String DEFAULT_VERSION_STR = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer major;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer micro;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer minor;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String version_str;
    public static final ProtoAdapter<Version> ADAPTER = new ProtoAdapter_Version();
    public static final Parcelable.Creator<Version> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_MAJOR = 0;
    public static final Integer DEFAULT_MINOR = 0;
    public static final Integer DEFAULT_MICRO = 0;

    public static final class Builder extends Message.Builder<Version, Builder> {
        public Integer major = Version.DEFAULT_MINOR;
        public Integer minor = Version.DEFAULT_MAJOR;
        public Integer micro = Version.DEFAULT_MICRO;
        public String version_str = "";

        @Override // com.sigmob.wire.Message.Builder
        public Version build() {
            return new Version(this.major, this.minor, this.micro, this.version_str, super.buildUnknownFields());
        }

        public Builder major(Integer num) {
            this.major = num;
            return this;
        }

        public Builder micro(Integer num) {
            this.micro = num;
            return this;
        }

        public Builder minor(Integer num) {
            this.minor = num;
            return this;
        }

        public Builder version_str(String str) {
            this.version_str = str;
            return this;
        }
    }

    private static final class ProtoAdapter_Version extends ProtoAdapter<Version> {
        public ProtoAdapter_Version() {
            super(FieldEncoding.LENGTH_DELIMITED, Version.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Version decode(ProtoReader protoReader) {
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
                        builder.major(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.minor(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.micro(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 4:
                        builder.version_str(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Version version) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, version.major);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, version.minor);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, version.micro);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, version.version_str);
            protoWriter.writeBytes(version.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Version version) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, version.major) + ProtoAdapter.UINT32.encodedSizeWithTag(2, version.minor) + ProtoAdapter.UINT32.encodedSizeWithTag(3, version.micro) + ProtoAdapter.STRING.encodedSizeWithTag(4, version.version_str) + version.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Version redact(Version version) {
            Builder builderNewBuilder = version.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Version(Integer num, Integer num2, Integer num3, String str) {
        this(num, num2, num3, str, ByteString.EMPTY);
    }

    public Version(Integer num, Integer num2, Integer num3, String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.major = num;
        this.minor = num2;
        this.micro = num3;
        this.version_str = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Version)) {
            return false;
        }
        Version version = (Version) obj;
        return unknownFields().equals(version.unknownFields()) && Internal.equals(this.major, version.major) && Internal.equals(this.minor, version.minor) && Internal.equals(this.micro, version.micro) && Internal.equals(this.version_str, version.version_str);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((unknownFields().hashCode() * 37) + (this.major != null ? this.major.hashCode() : 0)) * 37) + (this.minor != null ? this.minor.hashCode() : 0)) * 37) + (this.micro != null ? this.micro.hashCode() : 0)) * 37) + (this.version_str != null ? this.version_str.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.major = this.major;
        builder.minor = this.minor;
        builder.micro = this.micro;
        builder.version_str = this.version_str;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.major != null) {
            sb.append(", major=");
            sb.append(this.major);
        }
        if (this.minor != null) {
            sb.append(", minor=");
            sb.append(this.minor);
        }
        if (this.micro != null) {
            sb.append(", micro=");
            sb.append(this.micro);
        }
        if (this.version_str != null) {
            sb.append(", version_str=");
            sb.append(this.version_str);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Version{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
