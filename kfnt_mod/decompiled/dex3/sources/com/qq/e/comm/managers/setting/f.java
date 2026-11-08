package com.qq.e.comm.managers.setting;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import com.qq.e.comm.util.SystemUtil;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class f {

    static class b<T> {
        private final String a;
        private final T b;

        private b(String str, T t) {
            this.a = str;
            this.b = t;
        }

        public T a() {
            return this.b;
        }

        public String b() {
            return this.a;
        }
    }

    private static Pair<String, String> a(Context context, String str) throws Throwable {
        File dir = context.getDir(SystemUtil.buildNewPathByProcessName("e_qq_com_setting"), 0);
        if (!dir.exists()) {
            return null;
        }
        File file = new File(dir, str + ".sig");
        File file2 = new File(dir, str + ".cfg");
        if (file.exists() && file2.exists()) {
            try {
                String all = StringUtil.readAll(file);
                String all2 = StringUtil.readAll(file2);
                if (!TextUtils.isEmpty(all) && !TextUtils.isEmpty(all2)) {
                    com.qq.e.comm.util.a aVarA = com.qq.e.comm.util.a.a();
                    if (aVarA == null) {
                        throw null;
                    }
                    if (aVarA.a(all, Md5Util.encode(all2))) {
                        return new Pair<>(all, new String(Base64.decode(all2, 0), "UTF-8"));
                    }
                }
                GDTLogger.d("verify " + str + " setting fail");
            } catch (Exception unused) {
                GDTLogger.d("exception while loading local " + str);
            }
        }
        return null;
    }

    static b<com.qq.e.comm.managers.setting.a> a(Context context) {
        Pair<String, String> pairA = a(context, "devCloudSetting");
        if (pairA == null) {
            return null;
        }
        return new b<>((String) pairA.first, new com.qq.e.comm.managers.setting.a((String) pairA.second));
    }

    static boolean a(Context context, String str, String str2) {
        return a(context, "devCloudSetting", str, str2);
    }

    static b<d> b(Context context) {
        Pair<String, String> pairA = a(context, "placementCloudSetting");
        if (pairA == null) {
            return null;
        }
        return new b<>((String) pairA.first, new d((String) pairA.second));
    }

    static boolean b(Context context, String str, String str2) {
        return a(context, "placementCloudSetting", str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0083 A[Catch: Exception -> 0x0086, TRY_LEAVE, TryCatch #5 {Exception -> 0x0086, blocks: (B:29:0x007e, B:31:0x0083), top: B:41:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean b(android.content.Context r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "e_qq_com_setting"
            java.lang.String r0 = com.qq.e.comm.util.SystemUtil.buildNewPathByProcessName(r0)
            r1 = 0
            java.io.File r5 = r5.getDir(r0, r1)
            boolean r0 = r5.exists()
            if (r0 != 0) goto L14
            r5.mkdirs()
        L14:
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r3 = ".cfg"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r5, r2)
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r6)
            java.lang.String r6 = ".sig"
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            r2.<init>(r5, r6)
            r5 = 0
            java.io.FileWriter r6 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L69
            r6.write(r8)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L63
            java.io.FileWriter r8 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L63
            r8.<init>(r2)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L63
            r8.write(r7)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L64
            r5 = 1
            r6.close()     // Catch: java.lang.Exception -> L58
            r8.close()     // Catch: java.lang.Exception -> L58
        L58:
            return r5
        L59:
            r5 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
            goto L7c
        L5e:
            r7 = move-exception
            r8 = r5
            r5 = r6
            r6 = r7
            goto L7c
        L63:
            r8 = r5
        L64:
            r5 = r6
            goto L6a
        L66:
            r6 = move-exception
            r8 = r5
            goto L7c
        L69:
            r8 = r5
        L6a:
            r0.delete()     // Catch: java.lang.Throwable -> L7b
            r2.delete()     // Catch: java.lang.Throwable -> L7b
            if (r5 == 0) goto L75
            r5.close()     // Catch: java.lang.Exception -> L7a
        L75:
            if (r8 == 0) goto L7a
            r8.close()     // Catch: java.lang.Exception -> L7a
        L7a:
            return r1
        L7b:
            r6 = move-exception
        L7c:
            if (r5 == 0) goto L81
            r5.close()     // Catch: java.lang.Exception -> L86
        L81:
            if (r8 == 0) goto L86
            r8.close()     // Catch: java.lang.Exception -> L86
        L86:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.managers.setting.f.b(android.content.Context, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    static b<e> c(Context context) {
        Pair<String, String> pairA = a(context, "sdkCloudSetting");
        if (pairA == null) {
            return null;
        }
        return new b<>((String) pairA.first, new e((String) pairA.second));
    }

    static boolean c(Context context, String str, String str2) {
        return a(context, "sdkCloudSetting", str, str2);
    }

    private static final boolean a(Context context, String str, String str2, String str3) {
        if (StringUtil.isEmpty(str2) || StringUtil.isEmpty(str3)) {
            GDTLogger.d(String.format("Fail to update Cloud setting due to sig or setting is empty,name=%s\tsig=%s\tsetting=%s", str, str2, str3));
            return false;
        }
        com.qq.e.comm.util.a aVarA = com.qq.e.comm.util.a.a();
        if (aVarA != null) {
            if (aVarA.a(str2, Md5Util.encode(str3))) {
                return b(context, str, str2, str3);
            }
            GDTLogger.d(String.format("Fail to update Cloud setting due to sig verify fail,name=%s\tsig=%s\tsetting=%s", str, str2, str3));
            return false;
        }
        throw null;
    }
}
