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
public final class ServiceDescriptorProto extends Message<ServiceDescriptorProto, Builder> {
    public static final ProtoAdapter<ServiceDescriptorProto> ADAPTER = new ProtoAdapter_ServiceDescriptorProto();
    public static final String DEFAULT_NAME = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.google.protobuf.MethodDescriptorProto#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<MethodDescriptorProto> method;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String name;

    @WireField(adapter = "com.google.protobuf.ServiceOptions#ADAPTER", tag = 3)
    public final ServiceOptions options;

    public static final class Builder extends Message.Builder<ServiceDescriptorProto, Builder> {
        public List<MethodDescriptorProto> method = Internal.newMutableList();
        public String name;
        public ServiceOptions options;

        @Override // com.sigmob.wire.Message.Builder
        public ServiceDescriptorProto build() {
            return new ServiceDescriptorProto(this.name, this.method, this.options, super.buildUnknownFields());
        }

        public Builder method(List<MethodDescriptorProto> list) {
            Internal.checkElementsNotNull(list);
            this.method = list;
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder options(ServiceOptions serviceOptions) {
            this.options = serviceOptions;
            return this;
        }
    }

    private static final class ProtoAdapter_ServiceDescriptorProto extends ProtoAdapter<ServiceDescriptorProto> {
        public ProtoAdapter_ServiceDescriptorProto() {
            super(FieldEncoding.LENGTH_DELIMITED, ServiceDescriptorProto.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public ServiceDescriptorProto decode(ProtoReader protoReader) {
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
                        builder.method.add(MethodDescriptorProto.ADAPTER.decode(protoReader));
                        break;
                    case 3:
                        builder.options(ServiceOptions.ADAPTER.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, ServiceDescriptorProto serviceDescriptorProto) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, serviceDescriptorProto.name);
            MethodDescriptorProto.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, serviceDescriptorProto.method);
            ServiceOptions.ADAPTER.encodeWithTag(protoWriter, 3, serviceDescriptorProto.options);
            protoWriter.writeBytes(serviceDescriptorProto.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(ServiceDescriptorProto serviceDescriptorProto) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, serviceDescriptorProto.name) + MethodDescriptorProto.ADAPTER.asRepeated().encodedSizeWithTag(2, serviceDescriptorProto.method) + ServiceOptions.ADAPTER.encodedSizeWithTag(3, serviceDescriptorProto.options) + serviceDescriptorProto.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public ServiceDescriptorProto redact(ServiceDescriptorProto serviceDescriptorProto) {
            Builder builderNewBuilder = serviceDescriptorProto.newBuilder();
            Internal.redactElements(builderNewBuilder.method, MethodDescriptorProto.ADAPTER);
            if (builderNewBuilder.options != null) {
                builderNewBuilder.options = ServiceOptions.ADAPTER.redact(builderNewBuilder.options);
            }
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public ServiceDescriptorProto(String str, List<MethodDescriptorProto> list, ServiceOptions serviceOptions) {
        this(str, list, serviceOptions, ByteString.EMPTY);
    }

    public ServiceDescriptorProto(String str, List<MethodDescriptorProto> list, ServiceOptions serviceOptions, ByteString byteString) {
        super(ADAPTER, byteString);
        this.name = str;
        this.method = Internal.immutableCopyOf("method", list);
        this.options = serviceOptions;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ServiceDescriptorProto)) {
            return false;
        }
        ServiceDescriptorProto serviceDescriptorProto = (ServiceDescriptorProto) obj;
        return unknownFields().equals(serviceDescriptorProto.unknownFields()) && Internal.equals(this.name, serviceDescriptorProto.name) && this.method.equals(serviceDescriptorProto.method) && Internal.equals(this.options, serviceDescriptorProto.options);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((((unknownFields().hashCode() * 37) + (this.name != null ? this.name.hashCode() : 0)) * 37) + this.method.hashCode()) * 37) + (this.options != null ? this.options.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.name = this.name;
        builder.method = Internal.copyOf("method", this.method);
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
        if (!this.method.isEmpty()) {
            sb.append(", method=");
            sb.append(this.method);
        }
        if (this.options != null) {
            sb.append(", options=");
            sb.append(this.options);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "ServiceDescriptorProto{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
