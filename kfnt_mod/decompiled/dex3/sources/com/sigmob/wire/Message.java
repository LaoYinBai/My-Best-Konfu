package com.sigmob.wire;

import com.sigmob.wire.Message;
import com.sigmob.wire.Message.Builder;
import com.sigmob.wire.okio.Buffer;
import com.sigmob.wire.okio.BufferedSink;
import com.sigmob.wire.okio.ByteString;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class Message<M extends Message<M, B>, B extends Builder<M, B>> implements Serializable {
    private static final long serialVersionUID = 0;
    private final transient ProtoAdapter<M> adapter;
    transient int cachedSerializedSize = 0;
    protected transient int hashCode = 0;
    private final transient ByteString unknownFields;

    public static abstract class Builder<M extends Message<M, B>, B extends Builder<M, B>> {
        transient Buffer unknownFieldsBuffer;
        transient ByteString unknownFieldsByteString = ByteString.EMPTY;
        transient ProtoWriter unknownFieldsWriter;

        protected Builder() {
        }

        private void prepareForNewUnknownFields() {
            if (this.unknownFieldsBuffer == null) {
                this.unknownFieldsBuffer = new Buffer();
                this.unknownFieldsWriter = new ProtoWriter(this.unknownFieldsBuffer);
                try {
                    this.unknownFieldsWriter.writeBytes(this.unknownFieldsByteString);
                    this.unknownFieldsByteString = ByteString.EMPTY;
                } catch (IOException unused) {
                    throw new AssertionError();
                }
            }
        }

        public final Builder<M, B> addUnknownField(int i, FieldEncoding fieldEncoding, Object obj) {
            prepareForNewUnknownFields();
            try {
                fieldEncoding.rawProtoAdapter().encodeWithTag(this.unknownFieldsWriter, i, obj);
                return this;
            } catch (IOException unused) {
                throw new AssertionError();
            }
        }

        public final Builder<M, B> addUnknownFields(ByteString byteString) {
            if (byteString.size() > 0) {
                prepareForNewUnknownFields();
                try {
                    this.unknownFieldsWriter.writeBytes(byteString);
                } catch (IOException unused) {
                    throw new AssertionError();
                }
            }
            return this;
        }

        public abstract M build();

        public final ByteString buildUnknownFields() {
            if (this.unknownFieldsBuffer != null) {
                this.unknownFieldsByteString = this.unknownFieldsBuffer.readByteString();
                this.unknownFieldsBuffer = null;
                this.unknownFieldsWriter = null;
            }
            return this.unknownFieldsByteString;
        }

        public final Builder<M, B> clearUnknownFields() {
            this.unknownFieldsByteString = ByteString.EMPTY;
            if (this.unknownFieldsBuffer != null) {
                this.unknownFieldsBuffer.clear();
                this.unknownFieldsBuffer = null;
            }
            this.unknownFieldsWriter = null;
            return this;
        }
    }

    protected Message(ProtoAdapter<M> protoAdapter, ByteString byteString) {
        if (protoAdapter == null) {
            throw new NullPointerException("adapter == null");
        }
        if (byteString == null) {
            throw new NullPointerException("unknownFields == null");
        }
        this.adapter = protoAdapter;
        this.unknownFields = byteString;
    }

    public final ProtoAdapter<M> adapter() {
        return this.adapter;
    }

    public final void encode(BufferedSink bufferedSink) {
        this.adapter.encode(bufferedSink, (BufferedSink) this);
    }

    public final void encode(OutputStream outputStream) {
        this.adapter.encode(outputStream, (OutputStream) this);
    }

    public final byte[] encode() {
        return this.adapter.encode(this);
    }

    public abstract B newBuilder();

    public String toString() {
        return this.adapter.toString(this);
    }

    public final ByteString unknownFields() {
        ByteString byteString = this.unknownFields;
        return byteString != null ? byteString : ByteString.EMPTY;
    }

    public final M withoutUnknownFields() {
        return (M) newBuilder().clearUnknownFields().build();
    }

    protected final Object writeReplace() {
        return new MessageSerializedForm(encode(), getClass());
    }
}
