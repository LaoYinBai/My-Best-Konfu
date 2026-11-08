package com.sigmob.wire;

import com.sigmob.wire.okio.BufferedSink;
import com.sigmob.wire.okio.ByteString;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ProtoWriter {
    private final BufferedSink sink;

    public ProtoWriter(BufferedSink bufferedSink) {
        this.sink = bufferedSink;
    }

    static int decodeZigZag32(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    static int encodeZigZag32(int i) {
        return (i >> 31) ^ (i << 1);
    }

    static long encodeZigZag64(long j) {
        return (j >> 63) ^ (j << 1);
    }

    static int int32Size(int i) {
        if (i >= 0) {
            return varint32Size(i);
        }
        return 10;
    }

    private static int makeTag(int i, FieldEncoding fieldEncoding) {
        return (i << 3) | fieldEncoding.value;
    }

    static int tagSize(int i) {
        return varint32Size(makeTag(i, FieldEncoding.VARINT));
    }

    static int varint32Size(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    static int varint64Size(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public void writeBytes(ByteString byteString) {
        this.sink.write(byteString);
    }

    public void writeFixed32(int i) {
        this.sink.writeIntLe(i);
    }

    public void writeFixed64(long j) {
        this.sink.writeLongLe(j);
    }

    void writeSignedVarint32(int i) {
        if (i >= 0) {
            writeVarint32(i);
        } else {
            writeVarint64(i);
        }
    }

    public void writeString(String str) {
        this.sink.writeUtf8(str);
    }

    public void writeTag(int i, FieldEncoding fieldEncoding) {
        writeVarint32(makeTag(i, fieldEncoding));
    }

    public void writeVarint32(int i) {
        while ((i & (-128)) != 0) {
            this.sink.writeByte((i & 127) | 128);
            i >>>= 7;
        }
        this.sink.writeByte(i);
    }

    public void writeVarint64(long j) {
        while (((-128) & j) != 0) {
            this.sink.writeByte((((int) j) & 127) | 128);
            j >>>= 7;
        }
        this.sink.writeByte((int) j);
    }
}
