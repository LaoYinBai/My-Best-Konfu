package com.sigmob.wire;

import com.sigmob.wire.okio.Buffer;
import com.sigmob.wire.okio.BufferedSource;
import com.sigmob.wire.okio.ByteString;
import com.umeng.commonsdk.proguard.az;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ProtoReader {
    private static final int FIELD_ENCODING_MASK = 7;
    private static final int RECURSION_LIMIT = 65;
    private static final int STATE_END_GROUP = 4;
    private static final int STATE_FIXED32 = 5;
    private static final int STATE_FIXED64 = 1;
    private static final int STATE_LENGTH_DELIMITED = 2;
    private static final int STATE_PACKED_TAG = 7;
    private static final int STATE_START_GROUP = 3;
    private static final int STATE_TAG = 6;
    private static final int STATE_VARINT = 0;
    static final int TAG_FIELD_ENCODING_BITS = 3;
    private FieldEncoding nextFieldEncoding;
    private int recursionDepth;
    private final BufferedSource source;
    private long pos = 0;
    private long limit = Long.MAX_VALUE;
    private int state = 2;
    private int tag = -1;
    private long pushedLimit = -1;

    public ProtoReader(BufferedSource bufferedSource) {
        this.source = bufferedSource;
    }

    private void afterPackableScalar(int i) throws IOException {
        if (this.state != i) {
            if (this.pos > this.limit) {
                throw new IOException("Expected to end at " + this.limit + " but was " + this.pos);
            }
            if (this.pos != this.limit) {
                this.state = 7;
                return;
            } else {
                this.limit = this.pushedLimit;
                this.pushedLimit = -1L;
            }
        }
        this.state = 6;
    }

    private long beforeLengthDelimitedScalar() throws ProtocolException {
        if (this.state != 2) {
            throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.state);
        }
        long j = this.limit - this.pos;
        this.source.require(j);
        this.state = 6;
        this.pos = this.limit;
        this.limit = this.pushedLimit;
        this.pushedLimit = -1L;
        return j;
    }

    private void copyTag(ProtoWriter protoWriter, int i) {
        ProtoAdapter<?> protoAdapterRawProtoAdapter = peekFieldEncoding().rawProtoAdapter();
        try {
            protoAdapterRawProtoAdapter.encodeWithTag(protoWriter, i, protoAdapterRawProtoAdapter.decode(this));
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private int internalReadVarint32() throws ProtocolException {
        int i;
        this.source.require(1L);
        this.pos++;
        byte b = this.source.readByte();
        if (b >= 0) {
            return b;
        }
        int i2 = b & 127;
        this.source.require(1L);
        this.pos++;
        byte b2 = this.source.readByte();
        if (b2 >= 0) {
            i = b2 << 7;
        } else {
            i2 |= (b2 & 127) << 7;
            this.source.require(1L);
            this.pos++;
            byte b3 = this.source.readByte();
            if (b3 >= 0) {
                i = b3 << az.l;
            } else {
                i2 |= (b3 & 127) << 14;
                this.source.require(1L);
                this.pos++;
                byte b4 = this.source.readByte();
                if (b4 < 0) {
                    int i3 = i2 | ((b4 & 127) << 21);
                    this.source.require(1L);
                    this.pos++;
                    byte b5 = this.source.readByte();
                    int i4 = i3 | (b5 << 28);
                    if (b5 >= 0) {
                        return i4;
                    }
                    for (int i5 = 0; i5 < 5; i5++) {
                        this.source.require(1L);
                        this.pos++;
                        if (this.source.readByte() >= 0) {
                            return i4;
                        }
                    }
                    throw new ProtocolException("Malformed VARINT");
                }
                i = b4 << 21;
            }
        }
        return i2 | i;
    }

    private void skipGroup(int i) throws IOException {
        while (this.pos < this.limit && !this.source.exhausted()) {
            int iInternalReadVarint32 = internalReadVarint32();
            if (iInternalReadVarint32 == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            int i2 = iInternalReadVarint32 >> 3;
            int i3 = iInternalReadVarint32 & 7;
            switch (i3) {
                case 0:
                    this.state = 0;
                    readVarint64();
                    break;
                case 1:
                    this.state = 1;
                    readFixed64();
                    break;
                case 2:
                    long jInternalReadVarint32 = internalReadVarint32();
                    this.pos += jInternalReadVarint32;
                    this.source.skip(jInternalReadVarint32);
                    break;
                case 3:
                    skipGroup(i2);
                    break;
                case 4:
                    if (i2 != i) {
                        throw new ProtocolException("Unexpected end group");
                    }
                    return;
                case 5:
                    this.state = 5;
                    readFixed32();
                    break;
                default:
                    throw new ProtocolException("Unexpected field encoding: " + i3);
            }
        }
        throw new EOFException();
    }

    public long beginMessage() {
        if (this.state != 2) {
            throw new IllegalStateException("Unexpected call to beginMessage()");
        }
        int i = this.recursionDepth + 1;
        this.recursionDepth = i;
        if (i > 65) {
            throw new IOException("Wire recursion limit exceeded");
        }
        long j = this.pushedLimit;
        this.pushedLimit = -1L;
        this.state = 6;
        return j;
    }

    public void endMessage(long j) {
        if (this.state != 6) {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
        int i = this.recursionDepth - 1;
        this.recursionDepth = i;
        if (i < 0 || this.pushedLimit != -1) {
            throw new IllegalStateException("No corresponding call to beginMessage()");
        }
        if (this.pos == this.limit || this.recursionDepth == 0) {
            this.limit = j;
            return;
        }
        throw new IOException("Expected to end at " + this.limit + " but was " + this.pos);
    }

    public ByteString forEachTag(TagHandler tagHandler) {
        long jBeginMessage = beginMessage();
        Buffer buffer = null;
        ProtoWriter protoWriter = null;
        while (true) {
            int iNextTag = nextTag();
            if (iNextTag == -1) {
                break;
            }
            if (tagHandler.decodeMessage(iNextTag) == TagHandler.UNKNOWN_TAG) {
                if (buffer == null) {
                    buffer = new Buffer();
                    protoWriter = new ProtoWriter(buffer);
                }
                copyTag(protoWriter, iNextTag);
            }
        }
        endMessage(jBeginMessage);
        return buffer != null ? buffer.readByteString() : ByteString.EMPTY;
    }

    public int nextTag() {
        int i;
        if (this.state != 7) {
            if (this.state != 6) {
                throw new IllegalStateException("Unexpected call to nextTag()");
            }
            while (this.pos < this.limit && !this.source.exhausted()) {
                int iInternalReadVarint32 = internalReadVarint32();
                if (iInternalReadVarint32 == 0) {
                    throw new ProtocolException("Unexpected tag 0");
                }
                this.tag = iInternalReadVarint32 >> 3;
                int i2 = iInternalReadVarint32 & 7;
                switch (i2) {
                    case 0:
                        this.nextFieldEncoding = FieldEncoding.VARINT;
                        i = 0;
                        break;
                    case 1:
                        this.nextFieldEncoding = FieldEncoding.FIXED64;
                        i = 1;
                        break;
                    case 2:
                        this.nextFieldEncoding = FieldEncoding.LENGTH_DELIMITED;
                        this.state = 2;
                        int iInternalReadVarint322 = internalReadVarint32();
                        if (iInternalReadVarint322 < 0) {
                            throw new ProtocolException("Negative length: " + iInternalReadVarint322);
                        }
                        if (this.pushedLimit != -1) {
                            throw new IllegalStateException();
                        }
                        this.pushedLimit = this.limit;
                        this.limit = this.pos + iInternalReadVarint322;
                        if (this.limit <= this.pushedLimit) {
                            return this.tag;
                        }
                        throw new EOFException();
                    case 3:
                        skipGroup(this.tag);
                    case 4:
                        throw new ProtocolException("Unexpected end group");
                    case 5:
                        this.nextFieldEncoding = FieldEncoding.FIXED32;
                        i = 5;
                        break;
                    default:
                        throw new ProtocolException("Unexpected field encoding: " + i2);
                }
                this.state = i;
            }
            return -1;
        }
        this.state = 2;
        return this.tag;
    }

    public FieldEncoding peekFieldEncoding() {
        return this.nextFieldEncoding;
    }

    public ByteString readBytes() throws ProtocolException {
        long jBeforeLengthDelimitedScalar = beforeLengthDelimitedScalar();
        this.source.require(jBeforeLengthDelimitedScalar);
        return this.source.readByteString(jBeforeLengthDelimitedScalar);
    }

    public int readFixed32() throws IOException {
        if (this.state != 5 && this.state != 2) {
            throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.state);
        }
        this.source.require(4L);
        this.pos += 4;
        int intLe = this.source.readIntLe();
        afterPackableScalar(5);
        return intLe;
    }

    public long readFixed64() throws IOException {
        if (this.state != 1 && this.state != 2) {
            throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.state);
        }
        this.source.require(8L);
        this.pos += 8;
        long longLe = this.source.readLongLe();
        afterPackableScalar(1);
        return longLe;
    }

    public String readString() throws ProtocolException {
        long jBeforeLengthDelimitedScalar = beforeLengthDelimitedScalar();
        this.source.require(jBeforeLengthDelimitedScalar);
        return this.source.readUtf8(jBeforeLengthDelimitedScalar);
    }

    public int readVarint32() {
        if (this.state == 0 || this.state == 2) {
            int iInternalReadVarint32 = internalReadVarint32();
            afterPackableScalar(0);
            return iInternalReadVarint32;
        }
        throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.state);
    }

    public long readVarint64() throws IOException {
        if (this.state != 0 && this.state != 2) {
            throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.state);
        }
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            this.source.require(1L);
            this.pos++;
            j |= (r1 & 127) << i;
            if ((this.source.readByte() & 128) == 0) {
                afterPackableScalar(0);
                return j;
            }
        }
        throw new ProtocolException("WireInput encountered a malformed varint");
    }

    public void skip() throws IOException {
        int i = this.state;
        if (i == 5) {
            readFixed32();
            return;
        }
        switch (i) {
            case 0:
                readVarint64();
                return;
            case 1:
                readFixed64();
                return;
            case 2:
                this.source.skip(beforeLengthDelimitedScalar());
                return;
            default:
                throw new IllegalStateException("Unexpected call to skip()");
        }
    }
}
