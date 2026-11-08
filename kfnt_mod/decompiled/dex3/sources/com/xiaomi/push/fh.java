package com.xiaomi.push;

import android.util.Log;
import com.xiaomi.push.fk;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class fh {
    private static final boolean a = Log.isLoggable("BCompressed", 3);

    static byte[] a(fg fgVar, byte[] bArr) {
        try {
            byte[] bArrA = fk.a.a(bArr);
            if (a) {
                com.xiaomi.channel.commonutils.logger.b.m23a("BCompressed", "decompress " + bArr.length + " to " + bArrA.length + " for " + fgVar);
                if (fgVar.f367a == 1) {
                    com.xiaomi.channel.commonutils.logger.b.m23a("BCompressed", "decompress not support upStream");
                }
            }
            return bArrA;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m23a("BCompressed", "decompress error " + e);
            return bArr;
        }
    }
}
