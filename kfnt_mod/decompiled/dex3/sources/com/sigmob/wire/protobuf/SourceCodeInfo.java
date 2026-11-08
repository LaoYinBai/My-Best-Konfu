package com.sigmob.wire.protobuf;

import com.sigmob.wire.FieldEncoding;
import com.sigmob.wire.Message;
import com.sigmob.wire.ProtoAdapter;
import com.sigmob.wire.ProtoReader;
import com.sigmob.wire.ProtoWriter;
import com.sigmob.wire.WireField;
import com.sigmob.wire.internal.Internal;
import com.sigmob.wire.okio.ByteString;
import com.umeng.message.MsgConstant;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class SourceCodeInfo extends Message<SourceCodeInfo, Builder> {
    public static final ProtoAdapter<SourceCodeInfo> ADAPTER = new ProtoAdapter_SourceCodeInfo();
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.google.protobuf.SourceCodeInfo$Location#ADAPTER", label = WireField.Label.REPEATED, tag = 1)
    public final List<Location> location;

    public static final class Builder extends Message.Builder<SourceCodeInfo, Builder> {
        public List<Location> location = Internal.newMutableList();

        @Override // com.sigmob.wire.Message.Builder
        public SourceCodeInfo build() {
            return new SourceCodeInfo(this.location, super.buildUnknownFields());
        }

        public Builder location(List<Location> list) {
            Internal.checkElementsNotNull(list);
            this.location = list;
            return this;
        }
    }

    public static final class Location extends Message<Location, Builder> {
        public static final ProtoAdapter<Location> ADAPTER = new ProtoAdapter_Location();
        public static final String DEFAULT_LEADING_COMMENTS = "";
        public static final String DEFAULT_TRAILING_COMMENTS = "";
        private static final long serialVersionUID = 0;

        @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
        public final String leading_comments;

        @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", label = WireField.Label.REPEATED, tag = 6)
        public final List<String> leading_detached_comments;

        @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", label = WireField.Label.PACKED, tag = 1)
        public final List<Integer> path;

        @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", label = WireField.Label.PACKED, tag = 2)
        public final List<Integer> span;

        @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
        public final String trailing_comments;

        public static final class Builder extends Message.Builder<Location, Builder> {
            public String leading_comments;
            public String trailing_comments;
            public List<Integer> path = Internal.newMutableList();
            public List<Integer> span = Internal.newMutableList();
            public List<String> leading_detached_comments = Internal.newMutableList();

            @Override // com.sigmob.wire.Message.Builder
            public Location build() {
                return new Location(this.path, this.span, this.leading_comments, this.trailing_comments, this.leading_detached_comments, super.buildUnknownFields());
            }

            public Builder leading_comments(String str) {
                this.leading_comments = str;
                return this;
            }

            public Builder leading_detached_comments(List<String> list) {
                Internal.checkElementsNotNull(list);
                this.leading_detached_comments = list;
                return this;
            }

            public Builder path(List<Integer> list) {
                Internal.checkElementsNotNull(list);
                this.path = list;
                return this;
            }

            public Builder span(List<Integer> list) {
                Internal.checkElementsNotNull(list);
                this.span = list;
                return this;
            }

            public Builder trailing_comments(String str) {
                this.trailing_comments = str;
                return this;
            }
        }

        private static final class ProtoAdapter_Location extends ProtoAdapter<Location> {
            public ProtoAdapter_Location() {
                super(FieldEncoding.LENGTH_DELIMITED, Location.class);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sigmob.wire.ProtoAdapter
            public Location decode(ProtoReader protoReader) {
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
                    if (iNextTag != 6) {
                        switch (iNextTag) {
                            case 1:
                                list = builder.path;
                                break;
                            case 2:
                                list = builder.span;
                                break;
                            case 3:
                                builder.leading_comments(ProtoAdapter.STRING.decode(protoReader));
                                continue;
                            case 4:
                                builder.trailing_comments(ProtoAdapter.STRING.decode(protoReader));
                                continue;
                            default:
                                FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                                builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                                continue;
                        }
                        protoAdapter = ProtoAdapter.INT32;
                    } else {
                        list = builder.leading_detached_comments;
                        protoAdapter = ProtoAdapter.STRING;
                    }
                    list.add(protoAdapter.decode(protoReader));
                }
            }

            @Override // com.sigmob.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, Location location) {
                ProtoAdapter.INT32.asPacked().encodeWithTag(protoWriter, 1, location.path);
                ProtoAdapter.INT32.asPacked().encodeWithTag(protoWriter, 2, location.span);
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, location.leading_comments);
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, location.trailing_comments);
                ProtoAdapter.STRING.asRepeated().encodeWithTag(protoWriter, 6, location.leading_detached_comments);
                protoWriter.writeBytes(location.unknownFields());
            }

            @Override // com.sigmob.wire.ProtoAdapter
            public int encodedSize(Location location) {
                return ProtoAdapter.INT32.asPacked().encodedSizeWithTag(1, location.path) + ProtoAdapter.INT32.asPacked().encodedSizeWithTag(2, location.span) + ProtoAdapter.STRING.encodedSizeWithTag(3, location.leading_comments) + ProtoAdapter.STRING.encodedSizeWithTag(4, location.trailing_comments) + ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(6, location.leading_detached_comments) + location.unknownFields().size();
            }

            @Override // com.sigmob.wire.ProtoAdapter
            public Location redact(Location location) {
                Builder builderNewBuilder = location.newBuilder();
                builderNewBuilder.clearUnknownFields();
                return builderNewBuilder.build();
            }
        }

        public Location(List<Integer> list, List<Integer> list2, String str, String str2, List<String> list3) {
            this(list, list2, str, str2, list3, ByteString.EMPTY);
        }

        public Location(List<Integer> list, List<Integer> list2, String str, String str2, List<String> list3, ByteString byteString) {
            super(ADAPTER, byteString);
            this.path = Internal.immutableCopyOf("path", list);
            this.span = Internal.immutableCopyOf("span", list2);
            this.leading_comments = str;
            this.trailing_comments = str2;
            this.leading_detached_comments = Internal.immutableCopyOf("leading_detached_comments", list3);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Location)) {
                return false;
            }
            Location location = (Location) obj;
            return unknownFields().equals(location.unknownFields()) && this.path.equals(location.path) && this.span.equals(location.span) && Internal.equals(this.leading_comments, location.leading_comments) && Internal.equals(this.trailing_comments, location.trailing_comments) && this.leading_detached_comments.equals(location.leading_detached_comments);
        }

        public int hashCode() {
            int i = this.hashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = (((((((((unknownFields().hashCode() * 37) + this.path.hashCode()) * 37) + this.span.hashCode()) * 37) + (this.leading_comments != null ? this.leading_comments.hashCode() : 0)) * 37) + (this.trailing_comments != null ? this.trailing_comments.hashCode() : 0)) * 37) + this.leading_detached_comments.hashCode();
            this.hashCode = iHashCode;
            return iHashCode;
        }

        @Override // com.sigmob.wire.Message
        public Builder newBuilder() {
            Builder builder = new Builder();
            builder.path = Internal.copyOf("path", this.path);
            builder.span = Internal.copyOf("span", this.span);
            builder.leading_comments = this.leading_comments;
            builder.trailing_comments = this.trailing_comments;
            builder.leading_detached_comments = Internal.copyOf("leading_detached_comments", this.leading_detached_comments);
            builder.addUnknownFields(unknownFields());
            return builder;
        }

        @Override // com.sigmob.wire.Message
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (!this.path.isEmpty()) {
                sb.append(", path=");
                sb.append(this.path);
            }
            if (!this.span.isEmpty()) {
                sb.append(", span=");
                sb.append(this.span);
            }
            if (this.leading_comments != null) {
                sb.append(", leading_comments=");
                sb.append(this.leading_comments);
            }
            if (this.trailing_comments != null) {
                sb.append(", trailing_comments=");
                sb.append(this.trailing_comments);
            }
            if (!this.leading_detached_comments.isEmpty()) {
                sb.append(", leading_detached_comments=");
                sb.append(this.leading_detached_comments);
            }
            StringBuilder sbReplace = sb.replace(0, 2, "Location{");
            sbReplace.append('}');
            return sbReplace.toString();
        }
    }

    private static final class ProtoAdapter_SourceCodeInfo extends ProtoAdapter<SourceCodeInfo> {
        public ProtoAdapter_SourceCodeInfo() {
            super(FieldEncoding.LENGTH_DELIMITED, SourceCodeInfo.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public SourceCodeInfo decode(ProtoReader protoReader) {
            Builder builder = new Builder();
            long jBeginMessage = protoReader.beginMessage();
            while (true) {
                int iNextTag = protoReader.nextTag();
                if (iNextTag == -1) {
                    protoReader.endMessage(jBeginMessage);
                    return builder.build();
                }
                if (iNextTag != 1) {
                    FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                    builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                } else {
                    builder.location.add(Location.ADAPTER.decode(protoReader));
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SourceCodeInfo sourceCodeInfo) {
            Location.ADAPTER.asRepeated().encodeWithTag(protoWriter, 1, sourceCodeInfo.location);
            protoWriter.writeBytes(sourceCodeInfo.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(SourceCodeInfo sourceCodeInfo) {
            return Location.ADAPTER.asRepeated().encodedSizeWithTag(1, sourceCodeInfo.location) + sourceCodeInfo.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public SourceCodeInfo redact(SourceCodeInfo sourceCodeInfo) {
            Builder builderNewBuilder = sourceCodeInfo.newBuilder();
            Internal.redactElements(builderNewBuilder.location, Location.ADAPTER);
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public SourceCodeInfo(List<Location> list) {
        this(list, ByteString.EMPTY);
    }

    public SourceCodeInfo(List<Location> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.location = Internal.immutableCopyOf(MsgConstant.KEY_LOCATION_PARAMS, list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SourceCodeInfo)) {
            return false;
        }
        SourceCodeInfo sourceCodeInfo = (SourceCodeInfo) obj;
        return unknownFields().equals(sourceCodeInfo.unknownFields()) && this.location.equals(sourceCodeInfo.location);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (unknownFields().hashCode() * 37) + this.location.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.location = Internal.copyOf(MsgConstant.KEY_LOCATION_PARAMS, this.location);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.location.isEmpty()) {
            sb.append(", location=");
            sb.append(this.location);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "SourceCodeInfo{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
