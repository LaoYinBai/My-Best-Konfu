package com.kwad.sdk.utils;

import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class q {
    public static int a(Reader reader, Writer writer) {
        long jB = b(reader, writer);
        if (jB > 2147483647L) {
            return -1;
        }
        return (int) jB;
    }

    public static long a(Reader reader, Writer writer, char[] cArr) throws IOException {
        long j = 0;
        while (true) {
            int i = reader.read(cArr);
            if (-1 == i) {
                return j;
            }
            writer.write(cArr, 0, i);
            j += i;
        }
    }

    public static FileInputStream a(File file) {
        if (file.exists() && !file.isDirectory() && file.canRead()) {
            return new FileInputStream(file);
        }
        return null;
    }

    @Nullable
    public static FileOutputStream a(File file, boolean z) {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                return null;
            }
        } else if (file.isDirectory() || !file.canWrite()) {
            return null;
        }
        return new FileOutputStream(file, z);
    }

    @Nullable
    public static String a(File file, Charset charset) throws Throwable {
        FileInputStream fileInputStreamA;
        try {
            fileInputStreamA = a(file);
            if (fileInputStreamA == null) {
                a(fileInputStreamA);
                return null;
            }
            try {
                String strA = a(fileInputStreamA, a(charset));
                a(fileInputStreamA);
                return strA;
            } catch (Throwable th) {
                th = th;
                a(fileInputStreamA);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStreamA = null;
        }
    }

    @Nullable
    public static String a(InputStream inputStream, Charset charset) {
        if (inputStream == null) {
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        a(inputStream, stringWriter, charset);
        return stringWriter.toString();
    }

    public static String a(String str) throws Throwable {
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        return new String(com.kwad.sdk.core.a.c.a().a(o.a(b(new File(str)))));
    }

    public static Charset a(Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    public static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void a(File file, String str, Charset charset, boolean z) throws Throwable {
        FileOutputStream fileOutputStreamA;
        try {
            fileOutputStreamA = a(file, z);
            if (fileOutputStreamA != null) {
                try {
                    a(str, fileOutputStreamA, charset);
                } catch (Throwable th) {
                    th = th;
                    a(fileOutputStreamA);
                    throw th;
                }
            }
            a(fileOutputStreamA);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStreamA = null;
        }
    }

    public static void a(InputStream inputStream, Writer writer, Charset charset) {
        a(new InputStreamReader(inputStream, a(charset)), writer);
    }

    public static void a(String str, OutputStream outputStream, Charset charset) throws IOException {
        if (outputStream == null || str == null) {
            return;
        }
        outputStream.write(str.getBytes(a(charset)));
    }

    public static long b(Reader reader, Writer writer) {
        return a(reader, writer, new char[4096]);
    }

    public static InputStream b(String str) throws IOException {
        FileInputStream fileInputStream;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception unused) {
            b((Closeable) null);
            fileInputStream = null;
        }
        if (fileInputStream == null) {
            return null;
        }
        return new BufferedInputStream(fileInputStream);
    }

    public static void b(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] b(File file) throws Throwable {
        FileInputStream fileInputStream;
        byte[] bArr = new byte[(int) file.length()];
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            fileInputStream.read(bArr);
            com.kwad.sdk.crash.utils.b.a((InputStream) fileInputStream);
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            com.kwad.sdk.core.d.a.a(th);
            com.kwad.sdk.crash.utils.b.a((InputStream) fileInputStream2);
            return bArr;
        }
        return bArr;
    }

    public static void c(File file) {
        File[] fileArrListFiles;
        if (!file.isFile() && (fileArrListFiles = file.listFiles()) != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                c(file2);
            }
        }
        file.delete();
    }
}
