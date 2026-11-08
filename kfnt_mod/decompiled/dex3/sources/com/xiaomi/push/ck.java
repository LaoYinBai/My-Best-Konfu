package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.ci;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ck implements Runnable {
    final /* synthetic */ Context a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ ci.a f193a;

    ck(ci.a aVar, Context context) {
        this.f193a = aVar;
        this.a = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0071 A[Catch: Exception -> 0x0069, TRY_LEAVE, TryCatch #4 {Exception -> 0x0069, blocks: (B:37:0x0065, B:40:0x006b, B:42:0x0071), top: B:50:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0065 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 0
            com.xiaomi.push.ci$a r1 = r4.f193a     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L3a
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L3a
            if (r1 == 0) goto L1f
            boolean r0 = r1.isOpen()     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L62
            if (r0 == 0) goto L1f
            r1.beginTransaction()     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L62
            com.xiaomi.push.ci$a r0 = r4.f193a     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L62
            android.content.Context r2 = r4.a     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L62
            r0.a(r2, r1)     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L62
            r1.setTransactionSuccessful()     // Catch: java.lang.Exception -> L1d java.lang.Throwable -> L62
            goto L1f
        L1d:
            r0 = move-exception
            goto L3e
        L1f:
            if (r1 == 0) goto L27
            r1.endTransaction()     // Catch: java.lang.Exception -> L25
            goto L27
        L25:
            r0 = move-exception
            goto L57
        L27:
            com.xiaomi.push.ci$a r0 = r4.f193a     // Catch: java.lang.Exception -> L25
            com.xiaomi.push.cg r0 = r0.f184a     // Catch: java.lang.Exception -> L25
            if (r0 == 0) goto L5a
            com.xiaomi.push.ci$a r0 = r4.f193a     // Catch: java.lang.Exception -> L25
            com.xiaomi.push.cg r0 = r0.f184a     // Catch: java.lang.Exception -> L25
            r0.close()     // Catch: java.lang.Exception -> L25
            goto L5a
        L35:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L63
        L3a:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
        L3e:
            com.xiaomi.channel.commonutils.logger.b.a(r0)     // Catch: java.lang.Throwable -> L62
            if (r1 == 0) goto L49
            r1.endTransaction()     // Catch: java.lang.Exception -> L47
            goto L49
        L47:
            r0 = move-exception
            goto L57
        L49:
            com.xiaomi.push.ci$a r0 = r4.f193a     // Catch: java.lang.Exception -> L47
            com.xiaomi.push.cg r0 = r0.f184a     // Catch: java.lang.Exception -> L47
            if (r0 == 0) goto L5a
            com.xiaomi.push.ci$a r0 = r4.f193a     // Catch: java.lang.Exception -> L47
            com.xiaomi.push.cg r0 = r0.f184a     // Catch: java.lang.Exception -> L47
            r0.close()     // Catch: java.lang.Exception -> L47
            goto L5a
        L57:
            com.xiaomi.channel.commonutils.logger.b.a(r0)
        L5a:
            com.xiaomi.push.ci$a r0 = r4.f193a
            android.content.Context r1 = r4.a
            r0.a(r1)
            return
        L62:
            r0 = move-exception
        L63:
            if (r1 == 0) goto L6b
            r1.endTransaction()     // Catch: java.lang.Exception -> L69
            goto L6b
        L69:
            r1 = move-exception
            goto L79
        L6b:
            com.xiaomi.push.ci$a r1 = r4.f193a     // Catch: java.lang.Exception -> L69
            com.xiaomi.push.cg r1 = r1.f184a     // Catch: java.lang.Exception -> L69
            if (r1 == 0) goto L7c
            com.xiaomi.push.ci$a r1 = r4.f193a     // Catch: java.lang.Exception -> L69
            com.xiaomi.push.cg r1 = r1.f184a     // Catch: java.lang.Exception -> L69
            r1.close()     // Catch: java.lang.Exception -> L69
            goto L7c
        L79:
            com.xiaomi.channel.commonutils.logger.b.a(r1)
        L7c:
            com.xiaomi.push.ci$a r1 = r4.f193a
            android.content.Context r2 = r4.a
            r1.a(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ck.run():void");
    }
}
