package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.hn;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class bq implements Runnable {
    final /* synthetic */ Context a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ hn f940a;

    bq(Context context, hn hnVar) {
        this.a = context;
        this.f940a = hnVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.io.IOException, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.IOException, java.lang.Throwable] */
    @Override // java.lang.Runnable
    public void run() {
        RandomAccessFile randomAccessFile;
        FileLock fileLockLock;
        synchronized (bp.a) {
            FileLock fileLock = null;
            fileLock = null;
            FileLock fileLock2 = null;
            fileLock = null;
            try {
                try {
                    try {
                        File file = new File(this.a.getFilesDir(), "tiny_data.lock");
                        com.xiaomi.push.y.m650a(file);
                        randomAccessFile = new RandomAccessFile(file, "rw");
                        try {
                            fileLockLock = randomAccessFile.getChannel().lock();
                        } catch (Exception e) {
                            e = e;
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    randomAccessFile = null;
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile = null;
                }
                try {
                    ?? r1 = this.a;
                    bp.c(r1, this.f940a);
                    fileLock = r1;
                    if (fileLockLock != null) {
                        boolean zIsValid = fileLockLock.isValid();
                        fileLock = zIsValid;
                        if (zIsValid) {
                            try {
                                fileLockLock.release();
                                fileLock = zIsValid;
                            } catch (IOException e3) {
                                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e3);
                                fileLock = e3;
                            }
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileLock2 = fileLockLock;
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                    fileLock = fileLock2;
                    if (fileLock2 != null) {
                        boolean zIsValid2 = fileLock2.isValid();
                        fileLock = fileLock2;
                        if (zIsValid2) {
                            try {
                                fileLock2.release();
                                fileLock = fileLock2;
                            } catch (IOException e5) {
                                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e5);
                                fileLock = e5;
                            }
                        }
                    }
                    com.xiaomi.push.y.a(randomAccessFile);
                } catch (Throwable th3) {
                    th = th3;
                    fileLock = fileLockLock;
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e6) {
                            com.xiaomi.channel.commonutils.logger.b.a(e6);
                        }
                    }
                    com.xiaomi.push.y.a(randomAccessFile);
                    throw th;
                }
                com.xiaomi.push.y.a(randomAccessFile);
            } catch (Throwable th4) {
                throw th4;
            }
        }
    }
}
