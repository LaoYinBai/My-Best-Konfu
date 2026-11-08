package com.appsflyer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.io.File;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class p {

    /* renamed from: ˎ, reason: contains not printable characters */
    private static String f251;

    p() {
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    public static synchronized String m202(WeakReference<Context> weakReference) {
        String string;
        if (weakReference.get() == null) {
            return f251;
        }
        if (f251 == null) {
            String string2 = null;
            if (weakReference.get() != null) {
                string2 = weakReference.get().getSharedPreferences("appsflyer-data", 0).getString("AF_INSTALLATION", null);
            }
            if (string2 != null) {
                f251 = string2;
            } else {
                try {
                    File file = new File(weakReference.get().getFilesDir(), "AF_INSTALLATION");
                    if (file.exists()) {
                        f251 = m201(file);
                        file.delete();
                    } else {
                        if (Build.VERSION.SDK_INT >= 9) {
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            StringBuilder sb = new StringBuilder();
                            sb.append(jCurrentTimeMillis);
                            sb.append("-");
                            sb.append(Math.abs(new SecureRandom().nextLong()));
                            string = sb.toString();
                        } else {
                            string = UUID.randomUUID().toString();
                        }
                        f251 = string;
                    }
                    String str = f251;
                    SharedPreferences.Editor editorEdit = weakReference.get().getSharedPreferences("appsflyer-data", 0).edit();
                    editorEdit.putString("AF_INSTALLATION", str);
                    if (Build.VERSION.SDK_INT >= 9) {
                        editorEdit.apply();
                    } else {
                        editorEdit.commit();
                    }
                } catch (Exception e) {
                    AFLogger.afErrorLog("Error getting AF unique ID", e);
                }
            }
            if (f251 != null) {
                AppsFlyerProperties.getInstance().set(IWeiboService.ResponseConstants.UID, f251);
            }
        }
        return f251;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    /* renamed from: ॱ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String m201(java.io.File r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L2f
            java.lang.String r2 = "r"
            r1.<init>(r5, r2)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L2f
            long r2 = r1.length()     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L28
            int r5 = (int) r2     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L28
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L25 java.io.IOException -> L28
            r1.readFully(r5)     // Catch: java.io.IOException -> L20 java.lang.Throwable -> L25
            r1.close()     // Catch: java.io.IOException -> L20 java.lang.Throwable -> L25
            r1.close()     // Catch: java.io.IOException -> L19
            goto L43
        L19:
            r0 = move-exception
            java.lang.String r1 = "Exception while trying to close the InstallationFile"
            com.appsflyer.AFLogger.afErrorLog(r1, r0)
            goto L43
        L20:
            r0 = move-exception
            r4 = r1
            r1 = r5
            r5 = r0
            goto L2b
        L25:
            r5 = move-exception
            r0 = r1
            goto L4f
        L28:
            r5 = move-exception
            r4 = r1
            r1 = r0
        L2b:
            r0 = r4
            goto L31
        L2d:
            r5 = move-exception
            goto L4f
        L2f:
            r5 = move-exception
            r1 = r0
        L31:
            java.lang.String r2 = "Exception while reading InstallationFile: "
            com.appsflyer.AFLogger.afErrorLog(r2, r5)     // Catch: java.lang.Throwable -> L2d
            if (r0 == 0) goto L42
            r0.close()     // Catch: java.io.IOException -> L3c
            goto L42
        L3c:
            r5 = move-exception
            java.lang.String r0 = "Exception while trying to close the InstallationFile"
            com.appsflyer.AFLogger.afErrorLog(r0, r5)
        L42:
            r5 = r1
        L43:
            java.lang.String r0 = new java.lang.String
            if (r5 == 0) goto L48
            goto L4b
        L48:
            r5 = 0
            byte[] r5 = new byte[r5]
        L4b:
            r0.<init>(r5)
            return r0
        L4f:
            if (r0 == 0) goto L5b
            r0.close()     // Catch: java.io.IOException -> L55
            goto L5b
        L55:
            r0 = move-exception
            java.lang.String r1 = "Exception while trying to close the InstallationFile"
            com.appsflyer.AFLogger.afErrorLog(r1, r0)
        L5b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.p.m201(java.io.File):java.lang.String");
    }
}
