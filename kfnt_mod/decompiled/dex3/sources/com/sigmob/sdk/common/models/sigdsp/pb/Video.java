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
public final class Video extends AndroidMessage<Video, Builder> {
    public static final ProtoAdapter<Video> ADAPTER = new ProtoAdapter_Video();
    public static final Parcelable.Creator<Video> CREATOR = AndroidMessage.newCreator(ADAPTER);
    public static final Integer DEFAULT_MAX_DURATION = 0;
    public static final Integer DEFAULT_MIN_DURATION = 0;
    private static final long serialVersionUID = 0;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 1)
    public final Integer max_duration;

    @WireField(adapter = "com.squareup.wire.ProtoAdapter#UINT32", tag = 2)
    public final Integer min_duration;

    public static final class Builder extends Message.Builder<Video, Builder> {
        public Integer max_duration;
        public Integer min_duration;

        @Override // com.sigmob.wire.Message.Builder
        public Video build() {
            return new Video(this.max_duration, this.min_duration, super.buildUnknownFields());
        }

        public Builder max_duration(Integer num) {
            this.max_duration = num;
            return this;
        }

        public Builder min_duration(Integer num) {
            this.min_duration = num;
            return this;
        }
    }

    private static final class ProtoAdapter_Video extends ProtoAdapter<Video> {
        public ProtoAdapter_Video() {
            super(FieldEncoding.LENGTH_DELIMITED, Video.class);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sigmob.wire.ProtoAdapter
        public Video decode(ProtoReader protoReader) {
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
                        builder.max_duration(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    case 2:
                        builder.min_duration(ProtoAdapter.UINT32.decode(protoReader));
                        break;
                    default:
                        FieldEncoding fieldEncodingPeekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(iNextTag, fieldEncodingPeekFieldEncoding, fieldEncodingPeekFieldEncoding.rawProtoAdapter().decode(protoReader));
                        break;
                }
            }
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Video video) {
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 1, video.max_duration);
            ProtoAdapter.UINT32.encodeWithTag(protoWriter, 2, video.min_duration);
            protoWriter.writeBytes(video.unknownFields());
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public int encodedSize(Video video) {
            return ProtoAdapter.UINT32.encodedSizeWithTag(1, video.max_duration) + ProtoAdapter.UINT32.encodedSizeWithTag(2, video.min_duration) + video.unknownFields().size();
        }

        @Override // com.sigmob.wire.ProtoAdapter
        public Video redact(Video video) {
            Builder builderNewBuilder = video.newBuilder();
            builderNewBuilder.clearUnknownFields();
            return builderNewBuilder.build();
        }
    }

    public Video(Integer num, Integer num2) {
        this(num, num2, ByteString.EMPTY);
    }

    public Video(Integer num, Integer num2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.max_duration = num;
        this.min_duration = num2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Video)) {
            return false;
        }
        Video video = (Video) obj;
        return unknownFields().equals(video.unknownFields()) && Internal.equals(this.max_duration, video.max_duration) && Internal.equals(this.min_duration, video.min_duration);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = (((unknownFields().hashCode() * 37) + (this.max_duration != null ? this.max_duration.hashCode() : 0)) * 37) + (this.min_duration != null ? this.min_duration.hashCode() : 0);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.sigmob.wire.Message
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.max_duration = this.max_duration;
        builder.min_duration = this.min_duration;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    @Override // com.sigmob.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.max_duration != null) {
            sb.append(", max_duration=");
            sb.append(this.max_duration);
        }
        if (this.min_duration != null) {
            sb.append(", min_duration=");
            sb.append(this.min_duration);
        }
        StringBuilder sbReplace = sb.replace(0, 2, "Video{");
        sbReplace.append('}');
        return sbReplace.toString();
    }
}
