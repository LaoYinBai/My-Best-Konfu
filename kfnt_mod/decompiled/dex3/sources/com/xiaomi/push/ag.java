package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import com.umeng.message.MsgConstant;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ag {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.Closeable, java.io.RandomAccessFile] */
    public static boolean a(Context context, String str, long j) throws Throwable {
        FileLock fileLockLock;
        ?? randomAccessFile = 23;
        if (Build.VERSION.SDK_INT >= 23 && !g.c(context, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            return true;
        }
        FileLock fileLock = null;
        try {
            try {
                File file = new File(new File(Environment.getExternalStorageDirectory(), "/.vdevdir/"), "lcfp.lock");
                y.m650a(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    fileLockLock = randomAccessFile.getChannel().lock();
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
            randomAccessFile = 0;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = 0;
        }
        try {
            boolean zB = b(context, str, j);
            if (fileLockLock != null && fileLockLock.isValid()) {
                try {
                    fileLockLock.release();
                } catch (IOException unused) {
                }
            }
            y.a((Closeable) randomAccessFile);
            return zB;
        } catch (IOException e3) {
            e = e3;
            fileLock = fileLockLock;
            e.printStackTrace();
            if (fileLock != null && fileLock.isValid()) {
                try {
                    fileLock.release();
                } catch (IOException unused2) {
                }
            }
            y.a((Closeable) randomAccessFile);
            return true;
        } catch (Throwable th3) {
            th = th3;
            fileLock = fileLockLock;
            if (fileLock != null && fileLock.isValid()) {
                try {
                    fileLock.release();
                } catch (IOException unused3) {
                }
            }
            y.a((Closeable) randomAccessFile);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d6 A[Catch: all -> 0x00ea, IOException -> 0x00ec, LOOP:0: B:38:0x00d0->B:40:0x00d6, LOOP_END, TRY_LEAVE, TryCatch #7 {IOException -> 0x00ec, all -> 0x00ea, blocks: (B:37:0x00cc, B:38:0x00d0, B:40:0x00d6), top: B:64:0x00cc }] */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean b(android.content.Context r15, java.lang.String r16, long r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.ag.b(android.content.Context, java.lang.String, long):boolean");
    }
}
