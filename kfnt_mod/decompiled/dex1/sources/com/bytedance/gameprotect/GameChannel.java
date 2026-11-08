package com.bytedance.gameprotect;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class GameChannel extends GPPacketChannel {

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f3862a = new byte[0];

    protected GameChannel(int i) {
        nativeInit(i);
    }

    @Keep
    private native void nativeInit(int i);

    @Keep
    private native byte[] nativeReadPacket();

    @Keep
    private native int nativeWritePacket(byte[] bArr, int i, int i2);

    @Override // com.bytedance.gameprotect.GPPacketChannel
    @NonNull
    public byte[] readPacket() {
        byte[] bArrNativeReadPacket = nativeReadPacket();
        return bArrNativeReadPacket == null ? f3862a : bArrNativeReadPacket;
    }

    @Override // com.bytedance.gameprotect.GPPacketChannel
    public int writePacket(@NonNull byte[] bArr) {
        if (bArr == null) {
            throw null;
        }
        if (bArr.length == 0) {
            return -1;
        }
        return nativeWritePacket(bArr, 0, bArr.length);
    }
}
