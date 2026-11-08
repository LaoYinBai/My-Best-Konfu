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
public final class MessageOptions extends Message<MessageOptions, Builder> {
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 3)
    public final Boolean deprecated;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 7)
    public final Boolean map_entry;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 1)
    public final Boolean message_set_wire_format;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#BOOL", tag = 2)
    public final Boolean no_standard_descriptor_accessor;

    @WireField(adapter = "com.google.protobuf.UninterpretedOption#ADAPTER", label = WireField.Label.REPEATED, tag = 999)
    public final List<UninterpretedOption> uninterpreted_option;
    public static final ProtoAdapter<MessageOptions> ADAPTER = new ProtoAdapter_MessageOptions();
    public static final Boolean DEFAULT_MESSAGE_SET_WIRE_FORMAT = false;
    public static final Boolean DEFAULT_NO_STANDARD_DESCRIPTOR_ACCESSOR = false;
    public static final Boolean DEFAULT_DEPRECATED = false;
    public static final Boolean DEFAULT_MAP_ENTRY = false;

    public static final class Builder extends Message.Builder<MessageOptions, Builder> {
        public Boolean deprecated;
        public Boolean map_entry;
        public Boolean message_set_wire_format;
        public Boolean no_standard_descriptor_accessor;
        public List<UninterpretedOption> uninterpreted_option = Internal.newMutableList();

        @Override // com.sigmob.wire.Message.Builder
        public MessageOptions build() {
            return new MessageOptions(this.message_set_wire_format, this.no_standard_descriptor_accessor, this.deprecated, this.map_entry, this.uninterpreted_option, super.buildUnknownFields());
        }

        public Builder deprecated(Boolean bool) {
            this.deprecated = bool;
            return this;
        }

        public Builder map_entry(Boolean bool) {
            this.map_entry = bool;
            return this;
        }

        public Builder message_set_wire_format(Boolean bool) {
            this.message_set_wire_format = bool;
            return this;
        }

        public Builder no_standard_descriptor_accessor(Boolean bool) {
            this.no_standard_descriptor_accessor = bool;
            return this;
        }

        public Builder uninterpreted_option(List<UninterpretedOption> list) {
            Internal.checkElementsNotNull(list);
            this.uninterpreted_option = list;
            return this;
        }
    }

    private static final class ProtoAdapter_MessageOptions extends ProtoAdapter<MessageOptions> {
        public ProtoAdapter_MessageOptions() {
            super(FieldEncoding.LENGTH_DELIMITED, MessageOptions.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public MessageOptions decode(ProtoReader protoReader) {
            Builder builder = new Builder();
            long jBeginMessage = protoReader.beginMessage();
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    return builder.build();
                }
                if (iNextTag == 7) {
                    builder.map_entry(ProtoAdapter.BOOL.decode(protoReader));
                } else if (iNextTag != 999) {
                    switch (iNextTag) {
                        case 1:
                            builder.message_set_wire_format(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 2:
                            builder.no_standard_descriptor_accessor(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        case 3:
                            builder.deprecated(ProtoAdapter.BOOL.decode(protoReader));
                            break;
                        default:
                            FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                            builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                            break;
                    }
                } else {
                    builder.uninterpreted_option.add(UninterpretedOption.ADAPTER.decode(protoReader));
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, MessageOptions messageOptions) {
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 1, messageOptions.message_set_wire_format);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 2, messageOptions.no_standard_descriptor_accessor);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 3, messageOptions.deprecated);
            ProtoAdapter.BOOL.encodeWithTag(protoWriter, 7, messageOptions.map_entry);
            UninterpretedOption.ADAPTER.asRepeated().encodeWithTag(protoWriter, 999, messageOptions.uninterpreted_option);
            protoWriter.writeBytes(messageOptions.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(MessageOptions messageOptions) {
            return ProtoAdapter.BOOL.encodedSizeWithTag(1, messageOptions.message_set_wire_format) + ProtoAdapter.BOOL.encodedSizeWithTag(2, messageOptions.no_standard_descriptor_accessor) + ProtoAdapter.BOOL.encodedSizeWithTag(3, messageOptions.deprecated) + ProtoAdapter.BOOL.encodedSizeWithTag(7, messageOptions.map_entry) + UninterpretedOption.ADAPTER.asRepeated().encodedSizeWithTag(999, messageOptions.uninterpreted_option) + messageOptions.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public MessageOptions redact(MessageOptions messageOptions) {
            Builder builderNewBuilder = messageOptions.newBuilder();
            Internal.redactElements(builderNewBuilder.uninterpreted_option, UninterpretedOption.ADAPTER);
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public MessageOptions(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, List<UninterpretedOption> list) {
        this(bool, bool2, bool3, bool4, list, ByteString.EMPTY);
    }

    public MessageOptions(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, List<UninterpretedOption> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.message_set_wire_format = bool;
        this.no_standard_descriptor_accessor = bool2;
        this.deprecated = bool3;
        this.map_entry = bool4;
        this.uninterpreted_option = Internal.immutableCopyOf("uninterpreted_option", list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MessageOptions)) {
            return false;
        }
        MessageOptions messageOptions = (MessageOptions) obj;
        return unknownFields().equals(messageOptions.unknownFields()) && Internal.equals(this.message_set_wire_format, messageOptions.message_set_wire_format) && Internal.equals(this.no_standard_descriptor_accessor, messageOptions.no_standard_descriptor_accessor) && Internal.equals(this.deprecated, messageOptions.deprecated) && Internal.equals(this.map_entry, messageOptions.map_entry) && this.uninterpreted_option.equals(messageOptions.uninterpreted_option);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((((((unknownFields().hashCode() * 37) + (this.message_set_wire_format != null ? this.message_set_wire_format.hashCode() : 0)) * 37) + (this.no_standard_descriptor_accessor != null ? this.no_standard_descriptor_accessor.hashCode() : 0)) * 37) + (this.deprecated != null ? this.deprecated.hashCode() : 0)) * 37) + (this.map_entry != null ? this.map_entry.hashCode() : 0)) * 37) + this.uninterpreted_option.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.message_set_wire_format = this.message_set_wire_format;
        builder.no_standard_descriptor_accessor = this.no_standard_descriptor_accessor;
        builder.deprecated = this.deprecated;
        builder.map_entry = this.map_entry;
        builder.uninterpreted_option = Internal.copyOf("uninterpreted_option", this.uninterpreted_option);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.message_set_wire_format != null) {
            sb.append(", message_set_wire_format=");
            sb.append(this.message_set_wire_format);
        }
        if (this.no_standard_descriptor_accessor != null) {
            sb.append(", no_standard_descriptor_accessor=");
            sb.append(this.no_standard_descriptor_accessor);
        }
        if (this.deprecated != null) {
            sb.append(", deprecated=");
            sb.append(this.deprecated);
        }
        if (this.map_entry != null) {
            sb.append(", map_entry=");
            sb.append(this.map_entry);
        }
        if (!this.uninterpreted_option.isEmpty()) {
            sb.append(", uninterpreted_option=");
            sb.append(this.uninterpreted_option);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "MessageOptions{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
