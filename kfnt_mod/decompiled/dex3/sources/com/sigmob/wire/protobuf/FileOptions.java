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
public final class FileOptions extends Message<FileOptions, Builder> {
    public static final String DEFAULT_CSHARP_NAMESPACE = "";
    public static final String DEFAULT_GO_PACKAGE = "";
    public static final String DEFAULT_JAVA_OUTER_CLASSNAME = "";
    public static final String DEFAULT_JAVA_PACKAGE = "";
    public static final String DEFAULT_OBJC_CLASS_PREFIX = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 31)
    public final Boolean cc_enable_arenas;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 16)
    public final Boolean cc_generic_services;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 37)
    public final String csharp_namespace;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 23)
    public final Boolean deprecated;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 11)
    public final String go_package;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 20)
    public final Boolean java_generate_equals_and_hash;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 17)
    public final Boolean java_generic_services;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 10)
    public final Boolean java_multiple_files;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 8)
    public final String java_outer_classname;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String java_package;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 27)
    public final Boolean java_string_check_utf8;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 36)
    public final String objc_class_prefix;

    @WireField(adapter = "com.google.protobuf.FileOptions$OptimizeMode#ADAPTER", tag = 9)
    public final OptimizeMode optimize_for;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 18)
    public final Boolean py_generic_services;

    @WireField(adapter = "com.google.protobuf.UninterpretedOption#ADAPTER", label = WireField.Label.REPEATED, tag = 999)
    public final List<UninterpretedOption> uninterpreted_option;
    public static final ProtoAdapter<FileOptions> ADAPTER = new ProtoAdapter_FileOptions();
    public static final Boolean DEFAULT_JAVA_MULTIPLE_FILES = false;
    public static final Boolean DEFAULT_JAVA_GENERATE_EQUALS_AND_HASH = false;
    public static final Boolean DEFAULT_JAVA_STRING_CHECK_UTF8 = false;
    public static final OptimizeMode DEFAULT_OPTIMIZE_FOR = OptimizeMode.SPEED;
    public static final Boolean DEFAULT_CC_GENERIC_SERVICES = false;
    public static final Boolean DEFAULT_JAVA_GENERIC_SERVICES = false;
    public static final Boolean DEFAULT_PY_GENERIC_SERVICES = false;
    public static final Boolean DEFAULT_DEPRECATED = false;
    public static final Boolean DEFAULT_CC_ENABLE_ARENAS = false;

    public static final class Builder extends Message.Builder<FileOptions, Builder> {
        public Boolean cc_enable_arenas;
        public Boolean cc_generic_services;
        public String csharp_namespace;
        public Boolean deprecated;
        public String go_package;
        public Boolean java_generate_equals_and_hash;
        public Boolean java_generic_services;
        public Boolean java_multiple_files;
        public String java_outer_classname;
        public String java_package;
        public Boolean java_string_check_utf8;
        public String objc_class_prefix;
        public OptimizeMode optimize_for;
        public Boolean py_generic_services;
        public List<UninterpretedOption> uninterpreted_option = Internal.newMutableList();

        @Override // com.sigmob.wire.Message.Builder
        public FileOptions build() {
            return new FileOptions(this.java_package, this.java_outer_classname, this.java_multiple_files, this.java_generate_equals_and_hash, this.java_string_check_utf8, this.optimize_for, this.go_package, this.cc_generic_services, this.java_generic_services, this.py_generic_services, this.deprecated, this.cc_enable_arenas, this.objc_class_prefix, this.csharp_namespace, this.uninterpreted_option, super.buildUnknownFields());
        }

        public Builder cc_enable_arenas(Boolean bool) {
            this.cc_enable_arenas = bool;
            return this;
        }

        public Builder cc_generic_services(Boolean bool) {
            this.cc_generic_services = bool;
            return this;
        }

        public Builder csharp_namespace(String str) {
            this.csharp_namespace = str;
            return this;
        }

        public Builder deprecated(Boolean bool) {
            this.deprecated = bool;
            return this;
        }

        public Builder go_package(String str) {
            this.go_package = str;
            return this;
        }

        public Builder java_generate_equals_and_hash(Boolean bool) {
            this.java_generate_equals_and_hash = bool;
            return this;
        }

        public Builder java_generic_services(Boolean bool) {
            this.java_generic_services = bool;
            return this;
        }

        public Builder java_multiple_files(Boolean bool) {
            this.java_multiple_files = bool;
            return this;
        }

        public Builder java_outer_classname(String str) {
            this.java_outer_classname = str;
            return this;
        }

        public Builder java_package(String str) {
            this.java_package = str;
            return this;
        }

        public Builder java_string_check_utf8(Boolean bool) {
            this.java_string_check_utf8 = bool;
            return this;
        }

        public Builder objc_class_prefix(String str) {
            this.objc_class_prefix = str;
            return this;
        }

        public Builder optimize_for(OptimizeMode optimizeMode) {
            this.optimize_for = optimizeMode;
            return this;
        }

        public Builder py_generic_services(Boolean bool) {
            this.py_generic_services = bool;
            return this;
        }

        public Builder uninterpreted_option(List<UninterpretedOption> list) {
            Internal.checkElementsNotNull(list);
            this.uninterpreted_option = list;
            return this;
        }
    }

    public enum OptimizeMode implements WireEnum {
        SPEED(1),
        CODE_SIZE(2),
        LITE_RUNTIME(3);

        public static final ProtoAdapter<OptimizeMode> ADAPTER = new ProtoAdapter_OptimizeMode();
        private final int value;

        private static final class ProtoAdapter_OptimizeMode extends EnumAdapter<OptimizeMode> {
            ProtoAdapter_OptimizeMode() {
                super(OptimizeMode.class);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sigmob.wire.EnumAdapter
            public OptimizeMode fromValue(int i) {
                return OptimizeMode.fromValue(i);
            }
        }

        OptimizeMode(int i) {
            this.value = i;
        }

        public static OptimizeMode fromValue(int i) {
            switch (i) {
                case 1:
                    return SPEED;
                case 2:
                    return CODE_SIZE;
                case 3:
                    return LITE_RUNTIME;
                default:
                    return null;
            }
        }

        @Override // com.sigmob.wire.WireEnum
        public int getValue() {
            return this.value;
        }
    }

    private static final class ProtoAdapter_FileOptions extends ProtoAdapter<FileOptions> {
        public ProtoAdapter_FileOptions() {
            super(FieldEncoding.LENGTH_DELIMITED, FileOptions.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public FileOptions decode(ProtoReader protoReader) {
            Builder builder = new Builder();
            long jBeginMessage = protoReader.beginMessage();
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    return builder.build();
                }
                if (iNextTag == 1) {
                    builder.java_package(ProtoAdapter.STRING.decode(protoReader));
                } else if (iNextTag == 20) {
                    builder.java_generate_equals_and_hash(ProtoAdapter.BOOL.decode(protoReader));
                } else if (iNextTag == 23) {
                    builder.deprecated(ProtoAdapter.BOOL.decode(protoReader));
                } else if (iNextTag == 27) {
                    builder.java_string_check_utf8(ProtoAdapter.BOOL.decode(protoReader));
                } else if (iNextTag == 31) {
                    builder.cc_enable_arenas(ProtoAdapter.BOOL.decode(protoReader));
                } else if (iNextTag != 999) {
                    switch (iNextTag) {
                        case 8:
                            builder.java_outer_classname(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 9:
                            try {
                                builder.optimize_for(OptimizeMode.ADAPTER.decode(protoReader));
                                break;
                            } catch (ProtoAdapter.EnumConstantNotFoundException e) {
                                builder.addUnknownField(iNextTag, FieldEncoding.VARINT, Long.valueOf(e.value));
                                break;
                            }
                        case 10:
                            builder.java_multiple_files(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 11:
                            builder.go_package(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            switch (iNextTag) {
                                case 16:
                                    builder.cc_generic_services(ProtoAdapter.BOOL.decode(protoReader));
                                    break;
                                case 17:
                                    builder.java_generic_services(ProtoAdapter.BOOL.decode(protoReader));
                                    break;
                                case 18:
                                    builder.py_generic_services(ProtoAdapter.BOOL.decode(protoReader));
                                    break;
                                default:
                                    switch (iNextTag) {
                                        case 36:
                                            builder.objc_class_prefix(ProtoAdapter.STRING.decode(protoReader));
                                            break;
                                        case 37:
                                            builder.csharp_namespace(ProtoAdapter.STRING.decode(protoReader));
                                            break;
                                        default:
                                            FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                                            builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                                            break;
                                    }
                            }
                    }
                } else {
                    builder.uninterpreted_option.add(UninterpretedOption.ADAPTER.decode(protoReader));
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, FileOptions fileOptions) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, fileOptions.java_package);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 8, fileOptions.java_outer_classname);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 10, fileOptions.java_multiple_files);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 20, fileOptions.java_generate_equals_and_hash);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 27, fileOptions.java_string_check_utf8);
            OptimizeMode.ADAPTER.encodeWithTag(protoWriter, 9, fileOptions.optimize_for);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 11, fileOptions.go_package);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 16, fileOptions.cc_generic_services);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 17, fileOptions.java_generic_services);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 18, fileOptions.py_generic_services);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 23, fileOptions.deprecated);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 31, fileOptions.cc_enable_arenas);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 36, fileOptions.objc_class_prefix);
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 37, fileOptions.csharp_namespace);
            UninterpretedOption.ADAPTER.asRepeated().encodeWithTag(protoWriter, 999, fileOptions.uninterpreted_option);
            protoWriter.writeBytes(fileOptions.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(FileOptions fileOptions) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, fileOptions.java_package) + ProtoAdapter.STRING.encodedSizeWithTag(8, fileOptions.java_outer_classname) + ProtoAdapter.BOOL.encodedSizeWithTag(10, fileOptions.java_multiple_files) + ProtoAdapter.BOOL.encodedSizeWithTag(20, fileOptions.java_generate_equals_and_hash) + ProtoAdapter.BOOL.encodedSizeWithTag(27, fileOptions.java_string_check_utf8) + OptimizeMode.ADAPTER.encodedSizeWithTag(9, fileOptions.optimize_for) + ProtoAdapter.STRING.encodedSizeWithTag(11, fileOptions.go_package) + ProtoAdapter.BOOL.encodedSizeWithTag(16, fileOptions.cc_generic_services) + ProtoAdapter.BOOL.encodedSizeWithTag(17, fileOptions.java_generic_services) + ProtoAdapter.BOOL.encodedSizeWithTag(18, fileOptions.py_generic_services) + ProtoAdapter.BOOL.encodedSizeWithTag(23, fileOptions.deprecated) + ProtoAdapter.BOOL.encodedSizeWithTag(31, fileOptions.cc_enable_arenas) + ProtoAdapter.STRING.encodedSizeWithTag(36, fileOptions.objc_class_prefix) + ProtoAdapter.STRING.encodedSizeWithTag(37, fileOptions.csharp_namespace) + UninterpretedOption.ADAPTER.asRepeated().encodedSizeWithTag(999, fileOptions.uninterpreted_option) + fileOptions.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public FileOptions redact(FileOptions fileOptions) {
            Builder builderNewBuilder = fileOptions.newBuilder();
            Internal.redactElements(builderNewBuilder.uninterpreted_option, UninterpretedOption.ADAPTER);
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public FileOptions(String str, String str2, Boolean bool, Boolean bool2, Boolean bool3, OptimizeMode optimizeMode, String str3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, String str4, String str5, List<UninterpretedOption> list) {
        this(str, str2, bool, bool2, bool3, optimizeMode, str3, bool4, bool5, bool6, bool7, bool8, str4, str5, list, ByteString.EMPTY);
    }

    public FileOptions(String str, String str2, Boolean bool, Boolean bool2, Boolean bool3, OptimizeMode optimizeMode, String str3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, String str4, String str5, List<UninterpretedOption> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.java_package = str;
        this.java_outer_classname = str2;
        this.java_multiple_files = bool;
        this.java_generate_equals_and_hash = bool2;
        this.java_string_check_utf8 = bool3;
        this.optimize_for = optimizeMode;
        this.go_package = str3;
        this.cc_generic_services = bool4;
        this.java_generic_services = bool5;
        this.py_generic_services = bool6;
        this.deprecated = bool7;
        this.cc_enable_arenas = bool8;
        this.objc_class_prefix = str4;
        this.csharp_namespace = str5;
        this.uninterpreted_option = Internal.immutableCopyOf("uninterpreted_option", list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FileOptions)) {
            return false;
        }
        FileOptions fileOptions = (FileOptions) obj;
        return unknownFields().equals(fileOptions.unknownFields()) && Internal.equals(this.java_package, fileOptions.java_package) && Internal.equals(this.java_outer_classname, fileOptions.java_outer_classname) && Internal.equals(this.java_multiple_files, fileOptions.java_multiple_files) && Internal.equals(this.java_generate_equals_and_hash, fileOptions.java_generate_equals_and_hash) && Internal.equals(this.java_string_check_utf8, fileOptions.java_string_check_utf8) && Internal.equals(this.optimize_for, fileOptions.optimize_for) && Internal.equals(this.go_package, fileOptions.go_package) && Internal.equals(this.cc_generic_services, fileOptions.cc_generic_services) && Internal.equals(this.java_generic_services, fileOptions.java_generic_services) && Internal.equals(this.py_generic_services, fileOptions.py_generic_services) && Internal.equals(this.deprecated, fileOptions.deprecated) && Internal.equals(this.cc_enable_arenas, fileOptions.cc_enable_arenas) && Internal.equals(this.objc_class_prefix, fileOptions.objc_class_prefix) && Internal.equals(this.csharp_namespace, fileOptions.csharp_namespace) && this.uninterpreted_option.equals(fileOptions.uninterpreted_option);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((((((((((((((((((((((unknownFields().hashCode() * 37) + (this.java_package != null ? this.java_package.hashCode() : 0)) * 37) + (this.java_outer_classname != null ? this.java_outer_classname.hashCode() : 0)) * 37) + (this.java_multiple_files != null ? this.java_multiple_files.hashCode() : 0)) * 37) + (this.java_generate_equals_and_hash != null ? this.java_generate_equals_and_hash.hashCode() : 0)) * 37) + (this.java_string_check_utf8 != null ? this.java_string_check_utf8.hashCode() : 0)) * 37) + (this.optimize_for != null ? this.optimize_for.hashCode() : 0)) * 37) + (this.go_package != null ? this.go_package.hashCode() : 0)) * 37) + (this.cc_generic_services != null ? this.cc_generic_services.hashCode() : 0)) * 37) + (this.java_generic_services != null ? this.java_generic_services.hashCode() : 0)) * 37) + (this.py_generic_services != null ? this.py_generic_services.hashCode() : 0)) * 37) + (this.deprecated != null ? this.deprecated.hashCode() : 0)) * 37) + (this.cc_enable_arenas != null ? this.cc_enable_arenas.hashCode() : 0)) * 37) + (this.objc_class_prefix != null ? this.objc_class_prefix.hashCode() : 0)) * 37) + (this.csharp_namespace != null ? this.csharp_namespace.hashCode() : 0)) * 37) + this.uninterpreted_option.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.java_package = this.java_package;
        builder.java_outer_classname = this.java_outer_classname;
        builder.java_multiple_files = this.java_multiple_files;
        builder.java_generate_equals_and_hash = this.java_generate_equals_and_hash;
        builder.java_string_check_utf8 = this.java_string_check_utf8;
        builder.optimize_for = this.optimize_for;
        builder.go_package = this.go_package;
        builder.cc_generic_services = this.cc_generic_services;
        builder.java_generic_services = this.java_generic_services;
        builder.py_generic_services = this.py_generic_services;
        builder.deprecated = this.deprecated;
        builder.cc_enable_arenas = this.cc_enable_arenas;
        builder.objc_class_prefix = this.objc_class_prefix;
        builder.csharp_namespace = this.csharp_namespace;
        builder.uninterpreted_option = Internal.copyOf("uninterpreted_option", this.uninterpreted_option);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.java_package != null) {
            sb.append(", java_package=");
            sb.append(this.java_package);
        }
        if (this.java_outer_classname != null) {
            sb.append(", java_outer_classname=");
            sb.append(this.java_outer_classname);
        }
        if (this.java_multiple_files != null) {
            sb.append(", java_multiple_files=");
            sb.append(this.java_multiple_files);
        }
        if (this.java_generate_equals_and_hash != null) {
            sb.append(", java_generate_equals_and_hash=");
            sb.append(this.java_generate_equals_and_hash);
        }
        if (this.java_string_check_utf8 != null) {
            sb.append(", java_string_check_utf8=");
            sb.append(this.java_string_check_utf8);
        }
        if (this.optimize_for != null) {
            sb.append(", optimize_for=");
            sb.append(this.optimize_for);
        }
        if (this.go_package != null) {
            sb.append(", go_package=");
            sb.append(this.go_package);
        }
        if (this.cc_generic_services != null) {
            sb.append(", cc_generic_services=");
            sb.append(this.cc_generic_services);
        }
        if (this.java_generic_services != null) {
            sb.append(", java_generic_services=");
            sb.append(this.java_generic_services);
        }
        if (this.py_generic_services != null) {
            sb.append(", py_generic_services=");
            sb.append(this.py_generic_services);
        }
        if (this.deprecated != null) {
            sb.append(", deprecated=");
            sb.append(this.deprecated);
        }
        if (this.cc_enable_arenas != null) {
            sb.append(", cc_enable_arenas=");
            sb.append(this.cc_enable_arenas);
        }
        if (this.objc_class_prefix != null) {
            sb.append(", objc_class_prefix=");
            sb.append(this.objc_class_prefix);
        }
        if (this.csharp_namespace != null) {
            sb.append(", csharp_namespace=");
            sb.append(this.csharp_namespace);
        }
        if (!this.uninterpreted_option.isEmpty()) {
            sb.append(", uninterpreted_option=");
            sb.append(this.uninterpreted_option);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "FileOptions{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
