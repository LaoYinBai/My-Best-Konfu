package com.sigmob.wire.protobuf;

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
public final class EnumDescriptorProto extends Message<EnumDescriptorProto, Builder> {
    public static final ProtoAdapter<EnumDescriptorProto> ADAPTER = new ProtoAdapter_EnumDescriptorProto();
    public static final String DEFAULT_NAME = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String name;

    @WireField(adapter = "com.google.protobuf.EnumOptions#ADAPTER", tag = 3)
    public final EnumOptions options;

    @WireField(adapter = "com.google.protobuf.EnumValueDescriptorProto#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<EnumValueDescriptorProto> value;

    public static final class Builder extends Message.Builder<EnumDescriptorProto, Builder> {
        public String name;
        public EnumOptions options;
        public List<EnumValueDescriptorProto> value = Internal.newMutableList();

        @Override // com.sigmob.wire.Message.Builder
        public EnumDescriptorProto build() {
            return new EnumDescriptorProto(this.name, this.value, this.options, super.buildUnknownFields());
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder options(EnumOptions enumOptions) {
            this.options = enumOptions;
            return this;
        }

        public Builder value(List<EnumValueDescriptorProto> list) {
            Internal.checkElementsNotNull(list);
            this.value = list;
            return this;
        }
    }

    private static final class ProtoAdapter_EnumDescriptorProto extends ProtoAdapter<EnumDescriptorProto> {
        public ProtoAdapter_EnumDescriptorProto() {
            super(FieldEncoding.LENGTH_DELIMITED, EnumDescriptorProto.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public EnumDescriptorProto decode(ProtoReader protoReader) {
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
                        builder.name(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 2:
                        builder.value.add(EnumValueDescriptorProto.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        builder.options(EnumOptions.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, EnumDescriptorProto enumDescriptorProto) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, enumDescriptorProto.name);
            EnumValueDescriptorProto.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, enumDescriptorProto.value);
            EnumOptions.ADAPTER.encodeWithTag(protoWriter, 3, enumDescriptorProto.options);
            protoWriter.writeBytes(enumDescriptorProto.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(EnumDescriptorProto enumDescriptorProto) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, enumDescriptorProto.name) + EnumValueDescriptorProto.ADAPTER.asRepeated().encodedSizeWithTag(2, enumDescriptorProto.value) + EnumOptions.ADAPTER.encodedSizeWithTag(3, enumDescriptorProto.options) + enumDescriptorProto.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public EnumDescriptorProto redact(EnumDescriptorProto enumDescriptorProto) {
            Builder builderNewBuilder = enumDescriptorProto.newBuilder();
            Internal.redactElements(builderNewBuilder.value, EnumValueDescriptorProto.ADAPTER);
            if (builderNewBuilder.options != null) {
                builderNewBuilder.options = EnumOptions.ADAPTER.redact(builderNewBuilder.options);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public EnumDescriptorProto(String str, List<EnumValueDescriptorProto> list, EnumOptions enumOptions) {
        this(str, list, enumOptions, ByteString.EMPTY);
    }

    public EnumDescriptorProto(String str, List<EnumValueDescriptorProto> list, EnumOptions enumOptions, ByteString byteString) {
        super(ADAPTER, byteString);
        this.name = str;
        this.value = Internal.immutableCopyOf("value", list);
        this.options = enumOptions;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EnumDescriptorProto)) {
            return false;
        }
        EnumDescriptorProto enumDescriptorProto = (EnumDescriptorProto) obj;
        return unknownFields().equals(enumDescriptorProto.unknownFields()) && Internal.equals(this.name, enumDescriptorProto.name) && this.value.equals(enumDescriptorProto.value) && Internal.equals(this.options, enumDescriptorProto.options);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.name != null ? this.name.hashCode() : 0)) * 37) + this.value.hashCode()) * 37) + (this.options != null ? this.options.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.name = this.name;
        builder.value = Internal.copyOf("value", this.value);
        builder.options = this.options;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.name != null) {
            sb.append(", name=");
            sb.append(this.name);
        }
        if (!this.value.isEmpty()) {
            sb.append(", value=");
            sb.append(this.value);
        }
        if (this.options != null) {
            sb.append(", options=");
            sb.append(this.options);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "EnumDescriptorProto{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
