package com.sigmob.wire;

import java.net.ProtocolException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum FieldEncoding {
    VARINT(0),
    FIXED64(1),
    LENGTH_DELIMITED(2),
    FIXED32(5);

    final int value;

    FieldEncoding(int i) {
        this.value = i;
    }

    static FieldEncoding get(int i) throws ProtocolException {
        if (i == 5) {
            return FIXED32;
        }
        switch (i) {
            case 0:
                return VARINT;
            case 1:
                return FIXED64;
            case 2:
                return LENGTH_DELIMITED;
            default:
                throw new ProtocolException("Unexpected FieldEncoding: " + i);
        }
    }

    public ProtoAdapter<?> rawProtoAdapter() {
        switch (this) {
            case VARINT:
                return ProtoAdapter.UINT64;
            case FIXED32:
                return ProtoAdapter.FIXED32;
            case FIXED64:
                return ProtoAdapter.FIXED64;
            case LENGTH_DELIMITED:
                return ProtoAdapter.BYTES;
            default:
                throw new AssertionError();
        }
    }
}
