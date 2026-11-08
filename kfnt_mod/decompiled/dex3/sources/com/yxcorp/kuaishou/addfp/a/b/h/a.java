package com.yxcorp.kuaishou.addfp.a.b.h;

import android.content.Context;
import com.sigmob.sdk.common.Constants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.taobao.accs.utl.UtilityImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a c;
    private Context a;
    private boolean b;

    private a(Context context) {
        this.b = false;
        this.a = context;
        this.b = "1".equals(a("persist.sys.identifierid.supported", Constants.FAIL));
    }

    public static a a(Context context) {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(context);
                }
            }
        }
        return c;
    }

    private String a(int i, String str) {
        try {
            return a(this.a, 0, null);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(android.content.Context r8, int r9, java.lang.String r10) throws java.lang.Throwable {
        /*
            r0 = 0
            switch(r9) {
                case 0: goto L28;
                case 1: goto L15;
                case 2: goto L6;
                default: goto L4;
            }
        L4:
            r2 = r0
            goto L32
        L6:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            java.lang.String r1 = "content://com.vivo.vms.IdProvider/IdentifierId/AAID_"
            r9.<init>(r1)     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            r9.append(r10)     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            goto L23
        L15:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            java.lang.String r1 = "content://com.vivo.vms.IdProvider/IdentifierId/VAID_"
            r9.<init>(r1)     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            r9.append(r10)     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
        L23:
            android.net.Uri r9 = android.net.Uri.parse(r9)     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            goto L2b
        L28:
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID"
            goto L23
        L2b:
            r2 = r9
            goto L32
        L2d:
            r8 = move-exception
            goto L6c
        L2f:
            r8 = move-exception
            r9 = r0
            goto L61
        L32:
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2f
            if (r8 == 0) goto L5b
            boolean r9 = r8.moveToNext()     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L56
            if (r9 == 0) goto L5b
            java.lang.String r9 = "value"
            int r9 = r8.getColumnIndex(r9)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L56
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L56
            r0 = r9
            goto L5b
        L52:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto L6c
        L56:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto L61
        L5b:
            if (r8 == 0) goto L69
            r8.close()
            goto L69
        L61:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L6a
            if (r9 == 0) goto L69
            r9.close()
        L69:
            return r0
        L6a:
            r8 = move-exception
            r0 = r9
        L6c:
            if (r0 == 0) goto L71
            r0.close()
        L71:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yxcorp.kuaishou.addfp.a.b.h.a.a(android.content.Context, int, java.lang.String):java.lang.String");
    }

    private static String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(cls, str, UtilityImpl.NET_TYPE_UNKNOWN);
        } catch (Throwable unused) {
            return str2;
        }
    }

    public final boolean a() {
        return this.b;
    }

    public final String b() {
        if (this.b) {
            return a(0, (String) null);
        }
        return null;
    }
}
