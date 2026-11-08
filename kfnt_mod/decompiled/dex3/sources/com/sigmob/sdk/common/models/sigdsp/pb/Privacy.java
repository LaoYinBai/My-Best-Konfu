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
public final class Privacy extends AndroidMessage<Privacy, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 3)
    public final Integer age;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer child_protection;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer gdpr_consent;
    public static final ProtoAdapter<Privacy> ADAPTER = new ProtoAdapter_Privacy();
    public static final Parcelable.Creator<Privacy> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_GDPR_CONSENT = 0;
    public static final Integer DEFAULT_CHILD_PROTECTION = 0;
    public static final Integer DEFAULT_AGE = 0;

    public static final class Builder extends Message.Builder<Privacy, Builder> {
        public Integer gdpr_consent = Privacy.DEFAULT_GDPR_CONSENT;
        public Integer child_protection = Privacy.DEFAULT_CHILD_PROTECTION;
        public Integer age = Privacy.DEFAULT_AGE;

        public Builder age(Integer num) {
            this.age = num;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public Privacy build() {
            return new Privacy(this.gdpr_consent, this.child_protection, this.age, super.buildUnknownFields());
        }

        public Builder child_protection(Integer num) {
            this.child_protection = num;
            return this;
        }

        public Builder gdpr_consent(Integer num) {
            this.gdpr_consent = num;
            return this;
        }
    }

    private static final class ProtoAdapter_Privacy extends ProtoAdapter<Privacy> {
        public ProtoAdapter_Privacy() {
            super(FieldEncoding.LENGTH_DELIMITED, Privacy.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Privacy decode(ProtoReader protoReader) {
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
                        builder.gdpr_consent(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.child_protection(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 3:
                        builder.age(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Privacy privacy) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, privacy.gdpr_consent);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, privacy.child_protection);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 3, privacy.age);
            protoWriter.writeBytes(privacy.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Privacy privacy) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, privacy.gdpr_consent) + ProtoAdapter.UINT32.encodedSizeWithTag(2, privacy.child_protection) + ProtoAdapter.UINT32.encodedSizeWithTag(3, privacy.age) + privacy.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Privacy redact(Privacy privacy) {
            Builder builderNewBuilder = privacy.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Privacy(Integer num, Integer num2, Integer num3) {
        this(num, num2, num3, ByteString.EMPTY);
    }

    public Privacy(Integer num, Integer num2, Integer num3, ByteString byteString) {
        super(ADAPTER, byteString);
        this.gdpr_consent = num;
        this.child_protection = num2;
        this.age = num3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Privacy)) {
            return false;
        }
        Privacy privacy = (Privacy) obj;
        return unknownFields().equals(privacy.unknownFields()) && Internal.equals(this.gdpr_consent, privacy.gdpr_consent) && Internal.equals(this.child_protection, privacy.child_protection) && Internal.equals(this.age, privacy.age);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.gdpr_consent != null ? this.gdpr_consent.hashCode() : 0)) * 37) + (this.child_protection != null ? this.child_protection.hashCode() : 0)) * 37) + (this.age != null ? this.age.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.gdpr_consent = this.gdpr_consent;
        builder.child_protection = this.child_protection;
        builder.age = this.age;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.gdpr_consent != null) {
            sb.append(", gdpr_consent=");
            sb.append(this.gdpr_consent);
        }
        if (this.child_protection != null) {
            sb.append(", child_protection=");
            sb.append(this.child_protection);
        }
        if (this.age != null) {
            sb.append(", age=");
            sb.append(this.age);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Privacy{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
