package com.huawei.updatesdk.a.a.a;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static final a a = new a();
    private final Queue<byte[]> b = new ArrayDeque(0);

    private a() {
    }

    public static a a() {
        return a;
    }

    public void a(byte[] bArr) {
        if (bArr.length == 65536) {
            synchronized (this.b) {
                if (this.b.size() < 32 && !this.b.offer(bArr)) {
                    com.huawei.updatesdk.a.a.b.a.a.a.a("ByteArrayPool", "releaseBytes false");
                }
            }
        }
    }

    public byte[] b() {
        byte[] bArrPoll;
        synchronized (this.b) {
            bArrPoll = this.b.poll();
        }
        return bArrPoll == null ? new byte[C.DEFAULT_BUFFER_SEGMENT_SIZE] : bArrPoll;
    }
}
