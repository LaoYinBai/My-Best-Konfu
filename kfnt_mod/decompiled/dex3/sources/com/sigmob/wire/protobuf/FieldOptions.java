package com.sigmob.wire.protobuf;

import com.sigmob.wire.EnumAdapter;
import com.sigmob.wire.FieldEncoding;
import com.sigmob.wire.Message;
import com.sigmob.wire.ProtoAdapter;
import com.sigmob.wire.ProtoReader;
import com.sigmob.wire.ProtoWriter;
import com.sigmob.wire.WireEnum;
import com.sigmob.wire.WireField;
import com.sigmob.wire.internal.Internal;
import com.sigmob.wire.okio.ByteString;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class FieldOptions extends Message<FieldOptions, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.google.protobuf.FieldOptions$CType#ADAPTER", tag = 1)
    public final CType ctype;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 3)
    public final Boolean deprecated;

    @WireField(adapter = "com.google.protobuf.FieldOptions$JSType#ADAPTER", tag = 6)
    public final JSType jstype;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 5)
    public final Boolean lazy;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 2)
    public final Boolean packed;

    @WireField(adapter = "com.google.protobuf.UninterpretedOption#ADAPTER", label = WireField.Label.REPEATED, tag = 999)
    public final List<UninterpretedOption> uninterpreted_option;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 10)
    public final Boolean weak;
    public static final ProtoAdapter<FieldOptions> ADAPTER = new ProtoAdapter_FieldOptions();
    public static final CType DEFAULT_CTYPE = CType.STRING;
    public static final Boolean DEFAULT_PACKED = false;
    public static final JSType DEFAULT_JSTYPE = JSType.JS_NORMAL;
    public static final Boolean DEFAULT_LAZY = false;
    public static final Boolean DEFAULT_DEPRECATED = false;
    public static final Boolean DEFAULT_WEAK = false;

    public static final class Builder extends Message.Builder<FieldOptions, Builder> {
        public CType ctype;
        public Boolean deprecated;
        public JSType jstype;
        public Boolean lazy;
        public Boolean packed;
        public List<UninterpretedOption> uninterpreted_option = Internal.newMutableList();
        public Boolean weak;

        @Override // com.sigmob.wire.Message.Builder
        public FieldOptions build() {
            return new FieldOptions(this.ctype, this.packed, this.jstype, this.lazy, this.deprecated, this.weak, this.uninterpreted_option, super.buildUnknownFields());
        }

        public Builder ctype(CType cType) {
            this.ctype = cType;
            return this;
        }

        public Builder deprecated(Boolean bool) {
            this.deprecated = bool;
            return this;
        }

        public Builder jstype(JSType jSType) {
            this.jstype = jSType;
            return this;
        }

        public Builder lazy(Boolean bool) {
            this.lazy = bool;
            return this;
        }

        public Builder packed(Boolean bool) {
            this.packed = bool;
            return this;
        }

        public Builder uninterpreted_option(List<UninterpretedOption> list) {
            Internal.checkElementsNotNull(list);
            this.uninterpreted_option = list;
            return this;
        }

        public Builder weak(Boolean bool) {
            this.weak = bool;
            return this;
        }
    }

    public enum CType implements WireEnum {
        STRING(0),
        CORD(1),
        STRING_PIECE(2);

        public static final ProtoAdapter<CType> ADAPTER = new ProtoAdapter_CType();
        private final int value;

        private static final class ProtoAdapter_CType extends EnumAdapter<CType> {
            ProtoAdapter_CType() {
                super(CType.class);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sigmob.wire.EnumAdapter
            public CType fromValue(int i) {
                return CType.fromValue(i);
            }
        }

        CType(int i) {
            this.value = i;
        }

        public static CType fromValue(int i) {
            switch (i) {
                case 0:
                    return STRING;
                case 1:
                    return CORD;
                case 2:
                    return STRING_PIECE;
                default:
                    return null;
            }
        }

        @Override // com.sigmob.wire.WireEnum
        public int getValue() {
            return this.value;
        }
    }

    public enum JSType implements WireEnum {
        JS_NORMAL(0),
        JS_STRING(1),
        JS_NUMBER(2);

        public static final ProtoAdapter<JSType> ADAPTER = new ProtoAdapter_JSType();
        private final int value;

        private static final class ProtoAdapter_JSType extends EnumAdapter<JSType> {
            ProtoAdapter_JSType() {
                super(JSType.class);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sigmob.wire.EnumAdapter
            public JSType fromValue(int i) {
                return JSType.fromValue(i);
            }
        }

        JSType(int i) {
            this.value = i;
        }

        public static JSType fromValue(int i) {
            switch (i) {
                case 0:
                    return JS_NORMAL;
                case 1:
                    return JS_STRING;
                case 2:
                    return JS_NUMBER;
                default:
                    return null;
            }
        }

        @Override // com.sigmob.wire.WireEnum
        public int getValue() {
            return this.value;
        }
    }

    private static final class ProtoAdapter_FieldOptions extends ProtoAdapter<FieldOptions> {
        public ProtoAdapter_FieldOptions() {
            super(FieldEncoding.LENGTH_DELIMITED, FieldOptions.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public FieldOptions decode(ProtoReader protoReader) {
            Builder builder = new Builder();
            long jBeginMessage = protoReader.beginMessage();
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    return builder.build();
                }
                if (iNextTag == 10) {
                    builder.weak(ProtoAdapter.BOOL.decode(protoReader));
                } else if (iNextTag != 999) {
                    switch (iNextTag) {
                        case 1:
                            builder.ctype(CType.ADAPTER.decode(protoReader));
                            break;
                        case 2:
                            builder.packed(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 3:
                            builder.deprecated(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        default:
                            switch (iNextTag) {
                                case 5:
                                    builder.lazy(ProtoAdapter.BOOL.decode(protoReader));
                                    break;
                                case 6:
                                    try {
                                        builder.jstype(JSType.ADAPTER.decode(protoReader));
                                        break;
                                    } catch (ProtoAdapter.EnumConstantNotFoundException e) {
                                        builder.addUnknownField(iNextTag, FieldEncoding.VARINT, Long.valueOf(e.value));
                                        break;
                                    }
                                default:
                                    FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                                    builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                                    break;
                            }
                    }
                } else {
                    builder.uninterpreted_option.add(UninterpretedOption.ADAPTER.decode(protoReader));
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, FieldOptions fieldOptions) {
            CType.ADAPTER.encodeWithTag(protoWriter, 1, fieldOptions.ctype);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 2, fieldOptions.packed);
            JSType.ADAPTER.encodeWithTag(protoWriter, 6, fieldOptions.jstype);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 5, fieldOptions.lazy);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 3, fieldOptions.deprecated);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 10, fieldOptions.weak);
            UninterpretedOption.ADAPTER.asRepeated().encodeWithTag(protoWriter, 999, fieldOptions.uninterpreted_option);
            protoWriter.writeBytes(fieldOptions.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(FieldOptions fieldOptions) {
            return CType.ADAPTER.encodedSizeWithTag(1, fieldOptions.ctype) + ProtoAdapter.BOOL.encodedSizeWithTag(2, fieldOptions.packed) + JSType.ADAPTER.encodedSizeWithTag(6, fieldOptions.jstype) + ProtoAdapter.BOOL.encodedSizeWithTag(5, fieldOptions.lazy) + ProtoAdapter.BOOL.encodedSizeWithTag(3, fieldOptions.deprecated) + ProtoAdapter.BOOL.encodedSizeWithTag(10, fieldOptions.weak) + UninterpretedOption.ADAPTER.asRepeated().encodedSizeWithTag(999, fieldOptions.uninterpreted_option) + fieldOptions.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public FieldOptions redact(FieldOptions fieldOptions) {
            Builder builderNewBuilder = fieldOptions.newBuilder();
            Internal.redactElements(builderNewBuilder.uninterpreted_option, UninterpretedOption.ADAPTER);
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public FieldOptions(CType cType, Boolean bool, JSType jSType, Boolean bool2, Boolean bool3, Boolean bool4, List<UninterpretedOption> list) {
        this(cType, bool, jSType, bool2, bool3, bool4, list, ByteString.EMPTY);
    }

    public FieldOptions(CType cType, Boolean bool, JSType jSType, Boolean bool2, Boolean bool3, Boolean bool4, List<UninterpretedOption> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.ctype = cType;
        this.packed = bool;
        this.jstype = jSType;
        this.lazy = bool2;
        this.deprecated = bool3;
        this.weak = bool4;
        this.uninterpreted_option = Internal.immutableCopyOf("uninterpreted_option", list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldOptions)) {
            return false;
        }
        FieldOptions fieldOptions = (FieldOptions) obj;
        return unknownFields().equals(fieldOptions.unknownFields()) && Internal.equals(this.ctype, fieldOptions.ctype) && Internal.equals(this.packed, fieldOptions.packed) && Internal.equals(this.jstype, fieldOptions.jstype) && Internal.equals(this.lazy, fieldOptions.lazy) && Internal.equals(this.deprecated, fieldOptions.deprecated) && Internal.equals(this.weak, fieldOptions.weak) && this.uninterpreted_option.equals(fieldOptions.uninterpreted_option);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((unknownFields().hashCode() * 37) + (this.ctype != null ? this.ctype.hashCode() : 0)) * 37) + (this.packed != null ? this.packed.hashCode() : 0)) * 37) + (this.jstype != null ? this.jstype.hashCode() : 0)) * 37) + (this.lazy != null ? this.lazy.hashCode() : 0)) * 37) + (this.deprecated != null ? this.deprecated.hashCode() : 0)) * 37) + (this.weak != null ? this.weak.hashCode() : 0)) * 37) + this.uninterpreted_option.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.ctype = this.ctype;
        builder.packed = this.packed;
        builder.jstype = this.jstype;
        builder.lazy = this.lazy;
        builder.deprecated = this.deprecated;
        builder.weak = this.weak;
        builder.uninterpreted_option = Internal.copyOf("uninterpreted_option", this.uninterpreted_option);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.ctype != null) {
            sb.append(", ctype=");
            sb.append(this.ctype);
        }
        if (this.packed != null) {
            sb.append(", packed=");
            sb.append(this.packed);
        }
        if (this.jstype != null) {
            sb.append(", jstype=");
            sb.append(this.jstype);
        }
        if (this.lazy != null) {
            sb.append(", lazy=");
            sb.append(this.lazy);
        }
        if (this.deprecated != null) {
            sb.append(", deprecated=");
            sb.append(this.deprecated);
        }
        if (this.weak != null) {
            sb.append(", weak=");
            sb.append(this.weak);
        }
        if (!this.uninterpreted_option.isEmpty()) {
            sb.append(", uninterpreted_option=");
            sb.append(this.uninterpreted_option);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "FieldOptions{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
