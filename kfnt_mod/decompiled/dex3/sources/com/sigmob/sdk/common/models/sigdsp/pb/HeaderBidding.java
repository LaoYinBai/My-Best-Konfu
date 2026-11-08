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
public final class HeaderBidding extends AndroidMessage<HeaderBidding, Builder> {
    public static final ProtoAdapter<HeaderBidding> ADAPTER = new ProtoAdapter_HeaderBidding();
    public static final Parcelable.Creator<HeaderBidding> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final String DEFAULT_BID_TOKEN = "";
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String bid_token;

    public static final class Builder extends Message.Builder<HeaderBidding, Builder> {
        public String bid_token;

        public Builder bid_token(String str) {
            this.bid_token = str;
            return this;
        }

        @Override // com.sigmob.wire.Message.Builder
        public HeaderBidding build() {
            return new HeaderBidding(this.bid_token, super.buildUnknownFields());
        }
    }

    private static final class ProtoAdapter_HeaderBidding extends ProtoAdapter<HeaderBidding> {
        public ProtoAdapter_HeaderBidding() {
            super(FieldEncoding.LENGTH_DELIMITED, HeaderBidding.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public HeaderBidding decode(ProtoReader protoReader) {
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
                    builder.bid_token(ProtoAdapter.STRING.decode(protoReader));
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, HeaderBidding headerBidding) {
            ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, headerBidding.bid_token);
            protoWriter.writeBytes(headerBidding.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(HeaderBidding headerBidding) {
            return ProtoAdapter.STRING.encodedSizeWithTag(1, headerBidding.bid_token) + headerBidding.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public HeaderBidding redact(HeaderBidding headerBidding) {
            Builder builderNewBuilder = headerBidding.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public HeaderBidding(String str) {
        this(str, ByteString.EMPTY);
    }

    public HeaderBidding(String str, ByteString byteString) {
        super(ADAPTER, byteString);
        this.bid_token = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeaderBidding)) {
            return false;
        }
        HeaderBidding headerBidding = (HeaderBidding) obj;
        return unknownFields().equals(headerBidding.unknownFields()) && Internal.equals(this.bid_token, headerBidding.bid_token);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (unknownFields().hashCode() * 37) + (this.bid_token != null ? this.bid_token.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.bid_token = this.bid_token;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.bid_token != null) {
            sb.append(", bid_token=");
            sb.append(this.bid_token);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "HeaderBidding{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
