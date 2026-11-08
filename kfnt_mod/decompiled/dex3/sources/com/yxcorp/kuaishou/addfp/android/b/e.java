package com.yxcorp.kuaishou.addfp.android.b;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e implements com.yxcorp.kuaishou.addfp.a.b.a {
    private Context a;

    public e(Context context) {
        this.a = context;
    }

    public static String a(int i) {
        String str = null;
        try {
            String strC = c(String.format("/proc/%d/cmdline", Integer.valueOf(i)));
            try {
                return !TextUtils.isEmpty(strC) ? strC.trim() : strC;
            } catch (Throwable th) {
                str = strC;
                th = th;
                th.printStackTrace();
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static String a(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                return sb.toString();
            }
            if (z) {
                z = false;
            } else {
                sb.append('\n');
            }
            sb.append(line);
        }
    }

    public static String a(String str) {
        d dVarA;
        String str2 = "KWE_OTHER";
        try {
            dVarA = c.a(new String[]{str}, false, true);
        } catch (Throwable th) {
            str2 = "KWE_PE";
            th.printStackTrace();
        }
        if (!TextUtils.isEmpty(dVarA.a)) {
            return dVarA.a;
        }
        if (!TextUtils.isEmpty(dVarA.b) && dVarA.b.contains("denied")) {
            return "KWE_PN";
        }
        if (TextUtils.isEmpty(dVarA.a)) {
            str2 = "KWE_N";
        }
        return str2;
    }

    public static String a(Throwable th) {
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
            if (cause instanceof UnknownHostException) {
                return "UnknownHostException";
            }
            try {
            } catch (Throwable th2) {
                th2.printStackTrace();
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        String string = stringWriter.toString();
        printWriter.close();
        return string;
    }

    public static boolean a(Context context, String[] strArr) {
        for (int i = 0; i <= 0; i++) {
            try {
                if (context.checkPermission(strArr[0], Process.myPid(), Process.myUid()) == -1) {
                    return false;
                }
            } catch (Throwable unused) {
                return false;
            }
        }
        return true;
    }

    public static String b(String str) {
        return TextUtils.isEmpty(str) ? "KWE_N" : str;
    }

    public static boolean b(Context context, String[] strArr) {
        for (int i = 0; i < 2; i++) {
            try {
                if (context.checkPermission(strArr[i], Process.myPid(), Process.myUid()) == 0) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0021 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String c(java.lang.String r2) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L10 java.lang.Throwable -> L12
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L10 java.lang.Throwable -> L12
            java.lang.String r2 = a(r1)     // Catch: java.lang.Throwable -> Le java.lang.Throwable -> L1d
            r1.close()     // Catch: java.lang.Throwable -> Ld
        Ld:
            return r2
        Le:
            r2 = move-exception
            goto L14
        L10:
            r2 = move-exception
            goto L1f
        L12:
            r2 = move-exception
            r1 = r0
        L14:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L1d
            if (r1 == 0) goto L1c
            r1.close()     // Catch: java.lang.Throwable -> L1c
        L1c:
            return r0
        L1d:
            r2 = move-exception
            r0 = r1
        L1f:
            if (r0 == 0) goto L24
            r0.close()     // Catch: java.lang.Throwable -> L24
        L24:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yxcorp.kuaishou.addfp.android.b.e.c(java.lang.String):java.lang.String");
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public void a(com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        if (aVar != null) {
            try {
                aVar.a(c(), this);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public boolean a() {
        return false;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public String b() {
        com.yxcorp.kuaishou.addfp.a.b.d.a aVarA = com.yxcorp.kuaishou.addfp.a.b.d.a.a(this.a);
        String strA = !aVarA.a() ? null : aVarA.a("oaid");
        return TextUtils.isEmpty(strA) ? "" : strA;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public boolean c() {
        return com.yxcorp.kuaishou.addfp.a.b.d.a.a(this.a).a();
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public void d() {
    }
}
