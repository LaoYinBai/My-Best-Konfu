package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a a;

    /* renamed from: a, reason: collision with other field name */
    private Context f859a;
    private volatile String e;
    private volatile String f;

    /* renamed from: a, reason: collision with other field name */
    private final Object f860a = new Object();
    private final Object b = new Object();

    /* renamed from: a, reason: collision with other field name */
    private final String f861a = "mipush_region";

    /* renamed from: b, reason: collision with other field name */
    private final String f862b = "mipush_country_code";
    private final String c = "mipush_region.lock";
    private final String d = "mipush_country_code.lock";

    public a(Context context) {
        this.f859a = context;
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    private String a(Context context, String str, String str2, Object obj) {
        RandomAccessFile randomAccessFile;
        FileLock fileLockLock;
        File file = new File(context.getFilesDir(), str);
        FileLock fileLock = null;
        if (!file.exists()) {
            com.xiaomi.channel.commonutils.logger.b.m22a("No ready file to get data from " + str);
            return null;
        }
        synchronized (obj) {
            try {
                try {
                    File file2 = new File(context.getFilesDir(), str2);
                    com.xiaomi.push.y.m650a(file2);
                    randomAccessFile = new RandomAccessFile(file2, "rw");
                } catch (Throwable th) {
                    throw th;
                }
            } catch (Exception e) {
                e = e;
                randomAccessFile = null;
                fileLockLock = null;
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = null;
            }
            try {
                fileLockLock = randomAccessFile.getChannel().lock();
                try {
                    try {
                        String strA = com.xiaomi.push.y.a(file);
                        if (fileLockLock != null && fileLockLock.isValid()) {
                            try {
                                fileLockLock.release();
                            } catch (IOException e2) {
                                com.xiaomi.channel.commonutils.logger.b.a(e2);
                            }
                        }
                        com.xiaomi.push.y.a(randomAccessFile);
                        return strA;
                    } catch (Exception e3) {
                        e = e3;
                        com.xiaomi.channel.commonutils.logger.b.a(e);
                        if (fileLockLock != null && fileLockLock.isValid()) {
                            try {
                                fileLockLock.release();
                            } catch (IOException e4) {
                                com.xiaomi.channel.commonutils.logger.b.a(e4);
                            }
                        }
                        com.xiaomi.push.y.a(randomAccessFile);
                        return null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileLock = fileLockLock;
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e5) {
                            com.xiaomi.channel.commonutils.logger.b.a(e5);
                        }
                    }
                    com.xiaomi.push.y.a(randomAccessFile);
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                fileLockLock = null;
            } catch (Throwable th4) {
                th = th4;
                if (fileLock != null) {
                    fileLock.release();
                }
                com.xiaomi.push.y.a(randomAccessFile);
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.RandomAccessFile] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    private void a(Context context, String str, String str2, String str3, Object obj) {
        FileLock fileLockLock;
        synchronized (obj) {
            FileLock fileLock = null;
            fileLock = null;
            FileLock fileLock2 = null;
            fileLock = null;
            try {
                try {
                    try {
                        File file = new File(context.getFilesDir(), (String) str3);
                        com.xiaomi.push.y.m650a(file);
                        str3 = new RandomAccessFile(file, "rw");
                        try {
                            fileLockLock = str3.getChannel().lock();
                        } catch (Exception e) {
                            e = e;
                        }
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        ?? file2 = new File(context.getFilesDir(), str2);
                        com.xiaomi.push.y.a((File) file2, str);
                        fileLock = file2;
                        str3 = str3;
                        if (fileLockLock != null) {
                            fileLock = file2;
                            str3 = str3;
                            if (fileLockLock.isValid()) {
                                try {
                                    fileLockLock.release();
                                    fileLock = file2;
                                    str3 = str3;
                                } catch (IOException e2) {
                                    com.xiaomi.channel.commonutils.logger.b.a(e2);
                                    fileLock = file2;
                                    str3 = str3;
                                }
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        fileLock2 = fileLockLock;
                        com.xiaomi.channel.commonutils.logger.b.a(e);
                        fileLock = fileLock2;
                        str3 = str3;
                        if (fileLock2 != null) {
                            boolean zIsValid = fileLock2.isValid();
                            fileLock = fileLock2;
                            str3 = str3;
                            if (zIsValid) {
                                try {
                                    fileLock2.release();
                                    fileLock = fileLock2;
                                    str3 = str3;
                                } catch (IOException e4) {
                                    com.xiaomi.channel.commonutils.logger.b.a(e4);
                                    fileLock = fileLock2;
                                    str3 = str3;
                                }
                            }
                        }
                        com.xiaomi.push.y.a((Closeable) str3);
                    } catch (Throwable th2) {
                        th = th2;
                        fileLock = fileLockLock;
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException e5) {
                                com.xiaomi.channel.commonutils.logger.b.a(e5);
                            }
                        }
                        com.xiaomi.push.y.a((Closeable) str3);
                        throw th;
                    }
                } catch (Exception e6) {
                    e = e6;
                    str3 = 0;
                } catch (Throwable th3) {
                    th = th3;
                    str3 = 0;
                }
                com.xiaomi.push.y.a((Closeable) str3);
            } catch (Throwable th4) {
                throw th4;
            }
        }
    }

    public String a() {
        if (TextUtils.isEmpty(this.e)) {
            this.e = a(this.f859a, "mipush_region", "mipush_region.lock", this.f860a);
        }
        return this.e;
    }

    public void a(String str) {
        if (TextUtils.equals(str, this.e)) {
            return;
        }
        this.e = str;
        a(this.f859a, this.e, "mipush_region", "mipush_region.lock", this.f860a);
    }

    public String b() {
        if (TextUtils.isEmpty(this.f)) {
            this.f = a(this.f859a, "mipush_country_code", "mipush_country_code.lock", this.b);
        }
        return this.f;
    }

    public void b(String str) {
        if (TextUtils.equals(str, this.f)) {
            return;
        }
        this.f = str;
        a(this.f859a, this.f, "mipush_country_code", "mipush_country_code.lock", this.b);
    }
}
