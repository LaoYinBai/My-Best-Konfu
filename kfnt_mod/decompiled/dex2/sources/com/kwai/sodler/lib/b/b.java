package com.kwai.sodler.lib.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.kwad.sdk.collector.AppStatusRules;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.foundation.tools.SameMD5;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private static long a(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return j;
            }
            outputStream.write(bArr, 0, i);
            j += i;
        }
    }

    public static String a(InputStream inputStream) throws NoSuchAlgorithmException {
        int i;
        if (inputStream == null) {
            return null;
        }
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            MessageDigest messageDigest = MessageDigest.getInstance(SameMD5.TAG);
            StringBuilder sb = new StringBuilder(32);
            byte[] bArr = new byte[AppStatusRules.UploadConfig.DEFAULT_FILE_MAX_SIZE];
            while (true) {
                int i2 = bufferedInputStream.read(bArr);
                if (i2 == -1) {
                    break;
                }
                messageDigest.update(bArr, 0, i2);
            }
            for (byte b : messageDigest.digest()) {
                sb.append(Integer.toString((b & FileDownloadStatus.error) + 256, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.io.BufferedOutputStream, java.io.OutputStream, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public static void a(Context context, String str, File file) throws Throwable {
        InputStream inputStreamOpen;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        if (TextUtils.isEmpty(str)) {
            throw new IOException("Asset path is empty.");
        }
        d(file);
        ?? bufferedOutputStream = 0;
        bufferedOutputStream = 0;
        bufferedOutputStream = 0;
        InputStream inputStream = null;
        try {
            inputStreamOpen = context.getAssets().open(str);
            try {
                fileOutputStream2 = new FileOutputStream(file);
            } catch (IOException e) {
                e = e;
                FileOutputStream fileOutputStream3 = bufferedOutputStream;
                inputStream = inputStreamOpen;
                fileOutputStream = fileOutputStream3;
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpen = null;
        }
        try {
            FileDescriptor fd = fileOutputStream2.getFD();
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream2);
            a(inputStreamOpen, (OutputStream) bufferedOutputStream, new byte[16384]);
            bufferedOutputStream.flush();
            fd.sync();
            a((Object) inputStreamOpen);
            a((Object) bufferedOutputStream);
        } catch (IOException e3) {
            inputStream = inputStreamOpen;
            fileOutputStream = fileOutputStream2;
            e = e3;
            try {
                com.kwai.sodler.lib.a.a("plugin.files", e);
                a((Object) inputStream);
                a(fileOutputStream);
            } catch (Throwable th3) {
                th = th3;
                InputStream inputStream2 = inputStream;
                bufferedOutputStream = fileOutputStream;
                inputStreamOpen = inputStream2;
                a((Object) inputStreamOpen);
                a((Object) bufferedOutputStream);
                throw th;
            }
        } catch (Throwable th4) {
            bufferedOutputStream = fileOutputStream2;
            th = th4;
            a((Object) inputStreamOpen);
            a((Object) bufferedOutputStream);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.FileInputStream, java.io.InputStream, java.lang.Object] */
    public static void a(File file, File file2) throws Throwable {
        ?? fileInputStream;
        OutputStream outputStream;
        OutputStream fileOutputStream;
        if (file == null) {
            throw new IOException("Source file is null.");
        }
        if (file2 == null) {
            throw new IOException("Dest file is null.");
        }
        if (!file.exists()) {
            throw new IOException("Source file not found.");
        }
        d(file2);
        OutputStream bufferedOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (IOException e) {
                e = e;
                outputStream = bufferedOutputStream;
                bufferedOutputStream = fileInputStream;
            } catch (Throwable th) {
                th = th;
            }
            try {
                FileDescriptor fd = ((FileOutputStream) fileOutputStream).getFD();
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                a((InputStream) fileInputStream, bufferedOutputStream, new byte[16384]);
                bufferedOutputStream.flush();
                fd.sync();
                a((Object) fileInputStream);
                a(bufferedOutputStream);
            } catch (IOException e2) {
                bufferedOutputStream = fileInputStream;
                outputStream = fileOutputStream;
                e = e2;
                try {
                    com.kwai.sodler.lib.a.a("plugin.files", e);
                    a(bufferedOutputStream);
                    a(outputStream);
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = bufferedOutputStream;
                    bufferedOutputStream = outputStream;
                    a((Object) fileInputStream);
                    a(bufferedOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                bufferedOutputStream = fileOutputStream;
                th = th3;
                a((Object) fileInputStream);
                a(bufferedOutputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            outputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = 0;
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            if (obj instanceof Closeable) {
                ((Closeable) obj).close();
            } else if (Build.VERSION.SDK_INT < 19 || !(obj instanceof AutoCloseable)) {
            } else {
                ((AutoCloseable) obj).close();
            }
        } catch (Throwable unused) {
        }
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        return file.isDirectory() ? c(file) : b(file);
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return a(new File(str));
    }

    static boolean b(File file) {
        boolean zDelete = true;
        if (file == null) {
            return true;
        }
        if (file.exists()) {
            com.kwai.sodler.lib.a.c("plugin.files", "safeDeleteFile, try to delete path: " + file.getPath());
            zDelete = file.delete();
            if (!zDelete) {
                com.kwai.sodler.lib.a.d("plugin.files", "Failed to delete file, try to delete when exit. path: " + file.getPath());
                file.deleteOnExit();
            }
        }
        return zDelete;
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    static boolean c(File file) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return false;
        }
        if (!file.isFile()) {
            if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
                return true;
            }
            for (File file2 : fileArrListFiles) {
                c(file2);
            }
        }
        b(file);
        return true;
    }

    public static void d(File file) throws IOException {
        if (file == null) {
            throw new IOException("File is null.");
        }
        if (file.exists()) {
            a(file);
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!file.createNewFile()) {
            throw new IOException("Create file fail, file already exists.");
        }
    }

    public static void e(File file) throws IOException {
        if (file == null) {
            throw new IOException("Dir is null.");
        }
        if (!file.exists()) {
            file.mkdirs();
        } else {
            if (file.isDirectory()) {
                return;
            }
            if (!a(file)) {
                throw new IOException("Fail to delete existing file, file = " + file.getAbsolutePath());
            }
            file.mkdir();
        }
        if (file.exists() && file.isDirectory()) {
            return;
        }
        throw new IOException("Fail to create dir, dir = " + file.getAbsolutePath());
    }

    public static String f(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (file != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    String strA = a((InputStream) fileInputStream);
                    a((Object) fileInputStream);
                    return strA;
                } catch (Exception unused) {
                    a((Object) fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    a((Object) fileInputStream2);
                    throw th;
                }
            } catch (Exception unused2) {
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }
}
