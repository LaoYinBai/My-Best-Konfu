package anet.channel.util;

import android.content.Context;
import anet.channel.GlobalAppRuntimeInfo;
import java.io.File;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SerializeHelper {
    private static final String TAG = "awcn.SerializeHelper";
    private static File cacheDir;

    public static File getCacheFiles(String str) {
        Context context;
        if (cacheDir == null && (context = GlobalAppRuntimeInfo.getContext()) != null) {
            cacheDir = context.getCacheDir();
        }
        return new File(cacheDir, str);
    }

    public static synchronized void persist(Serializable serializable, File file) {
        persist(serializable, file, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x007c A[Catch: all -> 0x00ee, TryCatch #7 {, blocks: (B:8:0x000b, B:13:0x0043, B:29:0x0074, B:31:0x007c, B:33:0x008e, B:35:0x0094, B:38:0x00cf, B:36:0x00c4, B:27:0x0070, B:43:0x00df, B:44:0x00e2, B:45:0x00e3), top: B:66:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008e A[Catch: all -> 0x00ee, TryCatch #7 {, blocks: (B:8:0x000b, B:13:0x0043, B:29:0x0074, B:31:0x007c, B:33:0x008e, B:35:0x0094, B:38:0x00cf, B:36:0x00c4, B:27:0x0070, B:43:0x00df, B:44:0x00e2, B:45:0x00e3), top: B:66:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized void persist(java.io.Serializable r13, java.io.File r14, anet.channel.statist.StrategyStatObject r15) {
        /*
            Method dump skipped, instructions count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.util.SerializeHelper.persist(java.io.Serializable, java.io.File, anet.channel.statist.StrategyStatObject):void");
    }

    public static synchronized <T> T restore(File file) {
        return (T) restore(file, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized <T> T restore(java.io.File r14, anet.channel.statist.StrategyStatObject r15) {
        /*
            java.lang.Class<anet.channel.util.SerializeHelper> r0 = anet.channel.util.SerializeHelper.class
            monitor-enter(r0)
            if (r15 == 0) goto Lf
            java.lang.String r1 = java.lang.String.valueOf(r14)     // Catch: java.lang.Throwable -> Lc
            r15.readStrategyFilePath = r1     // Catch: java.lang.Throwable -> Lc
            goto Lf
        Lc:
            r14 = move-exception
            goto Lbe
        Lf:
            r1 = 0
            r2 = 3
            r3 = 0
            boolean r4 = r14.exists()     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
            r5 = 2
            r6 = 1
            if (r4 != 0) goto L35
            boolean r4 = anet.channel.util.ALog.isPrintLog(r2)     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
            if (r4 == 0) goto L33
            java.lang.String r4 = "awcn.SerializeHelper"
            java.lang.String r7 = "file not exist."
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
            java.lang.String r8 = "file"
            r5[r1] = r8     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
            java.lang.String r14 = r14.getName()     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
            r5[r6] = r14     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
            anet.channel.util.ALog.w(r4, r7, r3, r5)     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
        L33:
            monitor-exit(r0)
            return r3
        L35:
            if (r15 == 0) goto L39
            r15.isFileExists = r6     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
        L39:
            long r7 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
            r4.<init>(r14)     // Catch: java.lang.Throwable -> L96 java.lang.Throwable -> L99
            java.io.ObjectInputStream r9 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            java.io.BufferedInputStream r10 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            r10.<init>(r4)     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            java.lang.Object r10 = r9.readObject()     // Catch: java.lang.Throwable -> L93 java.lang.Throwable -> Lb7
            r9.close()     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            long r11 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            r9 = 0
            long r11 = r11 - r7
            if (r15 == 0) goto L5f
            r15.isReadObjectSucceed = r6     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            r15.readCostTime = r11     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
        L5f:
            java.lang.String r7 = "awcn.SerializeHelper"
            java.lang.String r8 = "restore end."
            r9 = 6
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            java.lang.String r13 = "file"
            r9[r1] = r13     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            java.io.File r13 = r14.getAbsoluteFile()     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            r9[r6] = r13     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            java.lang.String r6 = "size"
            r9[r5] = r6     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            long r5 = r14.length()     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            java.lang.Long r14 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            r9[r2] = r14     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            r14 = 4
            java.lang.String r5 = "cost"
            r9[r14] = r5     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            r14 = 5
            java.lang.Long r5 = java.lang.Long.valueOf(r11)     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            r9[r14] = r5     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
            anet.channel.util.ALog.i(r7, r8, r3, r9)     // Catch: java.lang.Throwable -> L91 java.lang.Throwable -> Lb7
        L8d:
            r4.close()     // Catch: java.lang.Throwable -> Lc java.io.IOException -> Lb5
            goto Lb5
        L91:
            r14 = move-exception
            goto L9c
        L93:
            r14 = move-exception
            r10 = r3
            goto L9c
        L96:
            r14 = move-exception
            r4 = r3
            goto Lb8
        L99:
            r14 = move-exception
            r4 = r3
            r10 = r4
        L9c:
            boolean r2 = anet.channel.util.ALog.isPrintLog(r2)     // Catch: java.lang.Throwable -> Lb7
            if (r2 == 0) goto Lab
            java.lang.String r2 = "awcn.SerializeHelper"
            java.lang.String r5 = "restore file fail."
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lb7
            anet.channel.util.ALog.w(r2, r5, r3, r14, r1)     // Catch: java.lang.Throwable -> Lb7
        Lab:
            if (r15 == 0) goto Lb2
            java.lang.String r1 = "SerializeHelper.restore()"
            r15.appendErrorTrace(r1, r14)     // Catch: java.lang.Throwable -> Lb7
        Lb2:
            if (r4 == 0) goto Lb5
            goto L8d
        Lb5:
            monitor-exit(r0)
            return r10
        Lb7:
            r14 = move-exception
        Lb8:
            if (r4 == 0) goto Lbd
            r4.close()     // Catch: java.lang.Throwable -> Lc java.io.IOException -> Lbd
        Lbd:
            throw r14     // Catch: java.lang.Throwable -> Lc
        Lbe:
            monitor-exit(r0)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.util.SerializeHelper.restore(java.io.File, anet.channel.statist.StrategyStatObject):java.lang.Object");
    }
}
