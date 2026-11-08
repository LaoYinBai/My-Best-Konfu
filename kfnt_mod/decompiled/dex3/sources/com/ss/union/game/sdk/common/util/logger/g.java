package com.ss.union.game.sdk.common.util.logger;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class g implements f {
    static final String a = "NO_TAG";

    g() {
    }

    @Override // com.ss.union.game.sdk.common.util.logger.f
    public void a(int i, String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return;
        }
        if (str == null) {
            str = a;
        }
        if (LogUtils.isDebug) {
            Log.println(i, str, str2);
        }
    }
}
