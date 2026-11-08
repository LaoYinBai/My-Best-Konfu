package com.huawei.a.a.b.k;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.zip.Deflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    public static String a(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
            try {
                a aVar = new a(1024);
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    aVar.a(bArr, i);
                }
                if (aVar.a() == 0) {
                    a((Closeable) fileInputStream);
                    return "";
                }
                String str = new String(aVar.b(), C.UTF8_NAME);
                a((Closeable) fileInputStream);
                return str;
            } catch (FileNotFoundException unused) {
                fileInputStream2 = fileInputStream;
                com.huawei.a.a.b.e.a.c("StreamUtil", "getInfoFromFile(): No files need to be read");
                a((Closeable) fileInputStream2);
                return "";
            } catch (IOException unused2) {
                fileInputStream2 = fileInputStream;
                com.huawei.a.a.b.e.a.c("StreamUtil", "getInfoFromFile(): stream.read or new string exception");
                a((Closeable) fileInputStream2);
                return "";
            } catch (Throwable th2) {
                th = th2;
                a((Closeable) fileInputStream);
                throw th;
            }
        } catch (FileNotFoundException unused3) {
        } catch (IOException unused4) {
        }
    }

    public static String a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    return byteArrayOutputStream.toString(C.UTF8_NAME);
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } finally {
            a((Closeable) byteArrayOutputStream);
        }
    }

    public static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                com.huawei.a.a.b.e.a.c("StreamUtil", "closeQuietly(): Exception when closing the closeable!");
            }
        }
    }

    public static void a(File file, String str) throws Throwable {
        String str2;
        String str3;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        try {
            fileOutputStream.write(str.getBytes(C.UTF8_NAME));
            fileOutputStream.flush();
            a((Closeable) fileOutputStream);
        } catch (FileNotFoundException unused3) {
            fileOutputStream2 = fileOutputStream;
            str2 = "StreamUtil";
            str3 = "saveInfoToFile(): No files need to be read";
            com.huawei.a.a.b.e.a.c(str2, str3);
            a((Closeable) fileOutputStream2);
        } catch (IOException unused4) {
            fileOutputStream2 = fileOutputStream;
            str2 = "StreamUtil";
            str3 = "saveInfoToFile(): io exc from write info to file!";
            com.huawei.a.a.b.e.a.c(str2, str3);
            a((Closeable) fileOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            a((Closeable) fileOutputStream2);
            throw th;
        }
    }

    private static void a(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
                com.huawei.a.a.b.e.a.c("StreamUtil", "closeStream(): Exception: close OutputStream error!");
            }
        }
    }

    public static void a(HttpURLConnection httpURLConnection) throws IOException {
        try {
            httpURLConnection.getInputStream().close();
        } catch (Exception unused) {
            com.huawei.a.a.b.e.a.c("StreamUtil", "closeQuietly(): Exception when connHttp.getInputStream()!,There may be no network, or no INTERNET permission");
        }
        httpURLConnection.disconnect();
        com.huawei.a.a.b.e.a.a("StreamUtil", " connHttp disconnect");
    }

    public static byte[] a(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[1024];
        while (!deflater.finished()) {
            byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        deflater.end();
        a((OutputStream) byteArrayOutputStream);
        return byteArray;
    }
}
