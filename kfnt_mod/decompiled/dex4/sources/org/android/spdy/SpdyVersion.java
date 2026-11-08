package org.android.spdy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public enum SpdyVersion {
    SPDY2(2),
    SPDY3(3),
    SPDY3DOT1(4);

    private int version;

    SpdyVersion(int i) {
        this.version = i;
    }

    int getInt() {
        return this.version;
    }
}
