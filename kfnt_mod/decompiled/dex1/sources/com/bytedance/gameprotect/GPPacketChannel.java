package com.bytedance.gameprotect;

import android.util.Base64;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GPPacketChannel {
    public static final int MAX_PACKET_SIZE = 2596;
    private static GPPacketChannel sInstance;

    public static synchronized GPPacketChannel open() {
        if (sInstance == null) {
            sInstance = new GameChannel(MAX_PACKET_SIZE);
        }
        return sInstance;
    }

    @NonNull
    public abstract byte[] readPacket();

    @NonNull
    public byte[] readPacketText() {
        byte[] packet = readPacket();
        return packet.length == 0 ? packet : Base64.encode(packet, 10);
    }

    public void testEchoRequest(int i) {
        p.a(i);
    }

    public abstract int writePacket(@NonNull byte[] bArr);

    public int writePacketText(@NonNull byte[] bArr) {
        if (bArr == null) {
            throw null;
        }
        if (bArr.length == 0) {
            return -1;
        }
        return writePacket(Base64.decode(bArr, 8));
    }
}
