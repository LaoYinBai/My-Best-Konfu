package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.az;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.zip.Deflater;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f {
    public static int a;
    private static final byte[] b = {10, 1, 11, 5, 4, az.m, 7, 9, 23, 3, 1, 6, 8, 12, az.k, 91};
    private static Object c = new Object();

    public static boolean a(long j, long j2) {
        return j > j2;
    }

    public static boolean a(Context context, String str, String str2, byte[] bArr) throws Throwable {
        boolean z;
        String str3;
        Object[] objArr;
        if (context == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                synchronized (c) {
                    try {
                        ULog.i("walle", "[stateless] begin write envelope, thread is " + Thread.currentThread());
                        File file = new File(context.getFilesDir() + "/" + a.e);
                        if (!file.isDirectory()) {
                            file.mkdir();
                        }
                        File file2 = new File(file.getPath() + "/" + str);
                        if (!file2.isDirectory()) {
                            file2.mkdir();
                        }
                        File file3 = new File(file2.getPath() + "/" + str2);
                        if (!file3.exists()) {
                            file3.createNewFile();
                        }
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                        try {
                            fileOutputStream2.write(bArr);
                            fileOutputStream2.close();
                            try {
                                ULog.i("walle", "[stateless] end write envelope, thread id " + Thread.currentThread());
                                return true;
                            } catch (Throwable th) {
                                th = th;
                                z = true;
                                while (true) {
                                    try {
                                        break;
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            fileOutputStream = fileOutputStream2;
                            z = false;
                            while (true) {
                                break;
                                break;
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                }
            } catch (Throwable th5) {
                if (0 != 0) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                    }
                }
                ULog.i("walle", "[stateless] end write envelope, thread id " + Thread.currentThread());
                throw th5;
            }
        } catch (IOException e) {
            e = e;
            z = false;
        } catch (Throwable th6) {
            th = th6;
            z = false;
        }
        try {
            break;
            throw th;
        } catch (IOException e2) {
            e = e2;
            ULog.i("walle", "[stateless] write envelope, e is " + e.getMessage());
            UMCrashManager.reportCrash(context, e);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused2) {
                }
            }
            str3 = "walle";
            objArr = new Object[]{"[stateless] end write envelope, thread id " + Thread.currentThread()};
            ULog.i(str3, objArr);
            return z;
        } catch (Throwable th7) {
            th = th7;
            ULog.i("walle", "[stateless] write envelope, e is " + th.getMessage());
            UMCrashManager.reportCrash(context, th);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                }
            }
            str3 = "walle";
            objArr = new Object[]{"[stateless] end write envelope, thread id " + Thread.currentThread()};
            ULog.i(str3, objArr);
            return z;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(java.lang.String r13) throws java.io.IOException {
        /*
            java.lang.Object r0 = com.umeng.commonsdk.stateless.f.c
            monitor-enter(r0)
            java.lang.String r1 = "walle"
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> Laa
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Laa
            r4.<init>()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r5 = "[stateless] begin read envelope, thread is "
            r4.append(r5)     // Catch: java.lang.Throwable -> Laa
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> Laa
            r4.append(r5)     // Catch: java.lang.Throwable -> Laa
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> Laa
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> Laa
            com.umeng.commonsdk.statistics.common.ULog.i(r1, r3)     // Catch: java.lang.Throwable -> Laa
            r1 = 0
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L7b java.io.IOException -> L80
            java.lang.String r4 = "r"
            r3.<init>(r13, r4)     // Catch: java.lang.Throwable -> L7b java.io.IOException -> L80
            java.nio.channels.FileChannel r13 = r3.getChannel()     // Catch: java.lang.Throwable -> L7b java.io.IOException -> L80
            java.nio.channels.FileChannel$MapMode r7 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            r8 = 0
            long r10 = r13.size()     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            r6 = r13
            java.nio.MappedByteBuffer r1 = r6.map(r7, r8, r10)     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            java.nio.MappedByteBuffer r1 = r1.load()     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            long r3 = r13.size()     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            int r3 = (int) r3     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            byte[] r3 = new byte[r3]     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            int r4 = r1.remaining()     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            if (r4 <= 0) goto L54
            int r4 = r1.remaining()     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            r1.get(r3, r5, r4)     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
        L54:
            java.lang.String r1 = "walle"
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            r6.<init>()     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            java.lang.String r7 = "[stateless] end read envelope, thread id "
            r6.append(r7)     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            r6.append(r7)     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            java.lang.String r6 = r6.toString()     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            r4[r5] = r6     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            com.umeng.commonsdk.statistics.common.ULog.i(r1, r4)     // Catch: java.io.IOException -> L79 java.lang.Throwable -> La3
            if (r13 == 0) goto L77
            r13.close()     // Catch: java.io.IOException -> L77 java.lang.Throwable -> Laa
        L77:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Laa
            return r3
        L79:
            r1 = move-exception
            goto L84
        L7b:
            r13 = move-exception
            r12 = r1
            r1 = r13
            r13 = r12
            goto La4
        L80:
            r13 = move-exception
            r12 = r1
            r1 = r13
            r13 = r12
        L84:
            java.lang.String r3 = "walle"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> La3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La3
            r4.<init>()     // Catch: java.lang.Throwable -> La3
            java.lang.String r6 = "[stateless] write envelope, e is "
            r4.append(r6)     // Catch: java.lang.Throwable -> La3
            java.lang.String r6 = r1.getMessage()     // Catch: java.lang.Throwable -> La3
            r4.append(r6)     // Catch: java.lang.Throwable -> La3
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> La3
            r2[r5] = r4     // Catch: java.lang.Throwable -> La3
            com.umeng.commonsdk.statistics.common.ULog.i(r3, r2)     // Catch: java.lang.Throwable -> La3
            throw r1     // Catch: java.lang.Throwable -> La3
        La3:
            r1 = move-exception
        La4:
            if (r13 == 0) goto La9
            r13.close()     // Catch: java.io.IOException -> La9 java.lang.Throwable -> Laa
        La9:
            throw r1     // Catch: java.lang.Throwable -> Laa
        Laa:
            r13 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Laa
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.f.a(java.lang.String):byte[]");
    }

    public static File a(Context context) {
        File[] fileArrListFiles;
        File[] fileArrListFiles2;
        File file = null;
        try {
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
        synchronized (c) {
            try {
                ULog.i("walle", "get last envelope begin, thread is " + Thread.currentThread());
                if (context != null && context.getApplicationContext() != null) {
                    String str = context.getApplicationContext().getFilesDir() + "/" + a.e;
                    if (!TextUtils.isEmpty(str)) {
                        File file2 = new File(str);
                        if (file2.isDirectory() && (fileArrListFiles = file2.listFiles()) != null && fileArrListFiles.length > 0) {
                            File file3 = null;
                            for (File file4 : fileArrListFiles) {
                                try {
                                    if (file4 != null && file4.isDirectory() && (fileArrListFiles2 = file4.listFiles()) != null && fileArrListFiles2.length > 0) {
                                        Arrays.sort(fileArrListFiles2, new Comparator<File>() { // from class: com.umeng.commonsdk.stateless.f.1
                                            @Override // java.util.Comparator
                                            /* renamed from: a, reason: merged with bridge method [inline-methods] */
                                            public int compare(File file5, File file6) {
                                                long jLastModified = file5.lastModified() - file6.lastModified();
                                                if (jLastModified > 0) {
                                                    return 1;
                                                }
                                                return jLastModified == 0 ? 0 : -1;
                                            }
                                        });
                                        File file5 = fileArrListFiles2[0];
                                        if (file5 != null && (file3 == null || file3.lastModified() > file5.lastModified())) {
                                            file3 = file5;
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    file = file3;
                                    throw th;
                                }
                            }
                            file = file3;
                        }
                    }
                }
                ULog.i("walle", "get last envelope end, thread is " + Thread.currentThread());
                return file;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public static void a(Context context, String str, int i) {
        try {
            if (str == null) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            File file = new File(str);
            if (!file.isDirectory()) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            synchronized (c) {
                File[] fileArrListFiles = file.listFiles();
                ULog.i("AmapLBS", "[lbs-build] delete file begin " + fileArrListFiles.length + ", thread is " + Thread.currentThread());
                if (fileArrListFiles != null && fileArrListFiles.length >= i) {
                    ULog.i("AmapLBS", "[lbs-build] file size >= max");
                    ArrayList arrayList = new ArrayList();
                    for (File file2 : fileArrListFiles) {
                        if (file2 != null) {
                            arrayList.add(file2);
                        }
                    }
                    if (arrayList.size() >= i) {
                        Collections.sort(arrayList, new Comparator<File>() { // from class: com.umeng.commonsdk.stateless.f.2
                            @Override // java.util.Comparator
                            /* renamed from: a, reason: merged with bridge method [inline-methods] */
                            public int compare(File file3, File file4) {
                                if (file3 == null || file4 == null || file3.lastModified() >= file4.lastModified()) {
                                    return (file3 == null || file4 == null || file3.lastModified() != file4.lastModified()) ? 1 : 0;
                                }
                                return -1;
                            }
                        });
                        if (ULog.DEBUG) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                ULog.i("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i2)).getPath());
                            }
                        }
                        for (int i3 = 0; i3 <= arrayList.size() - i; i3++) {
                            if (arrayList.get(i3) != null) {
                                ULog.i("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i3)).getPath());
                                try {
                                    ((File) arrayList.get(i3)).delete();
                                    arrayList.remove(i3);
                                } catch (Exception unused) {
                                }
                            }
                        }
                    }
                } else {
                    ULog.i("AmapLBS", "[lbs-build] file size < max");
                }
                ULog.i("AmapLBS", "[lbs-build] delete file end " + fileArrListFiles.length + ", thread is " + Thread.currentThread());
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static byte[] a(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        a = 0;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int iDeflate = deflater.deflate(bArr2);
                    a += iDeflate;
                    byteArrayOutputStream.write(bArr2, 0, iDeflate);
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
            deflater.end();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(b));
        return cipher.doFinal(bArr);
    }

    public static byte[] b(byte[] bArr) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(b2)));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }
}
