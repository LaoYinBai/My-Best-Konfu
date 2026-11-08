package com.mbridge.msdk.playercommon.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class SystemClock implements Clock {
    SystemClock() {
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.util.Clock
    public final long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.util.Clock
    public final long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.util.Clock
    public final void sleep(long j) {
        android.os.SystemClock.sleep(j);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.util.Clock
    public final HandlerWrapper createHandler(Looper looper, Handler.Callback callback) {
        return new SystemHandlerWrapper(new Handler(looper, callback));
    }
}
