package com.meizu.cloud.pushsdk.notification.c;

import com.meizu.cloud.pushinternal.DebugLogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private final File a;
    private final File b;
    private final String c;

    public e(String str, String str2) {
        this.a = new File(str);
        this.b = new File(str2);
        this.c = this.b.getAbsolutePath();
        DebugLogger.i("ZipExtractTask", "Extract mInput file = " + this.a.toString());
        DebugLogger.i("ZipExtractTask", "Extract mOutput file = " + this.b.toString());
    }

    private int a(InputStream inputStream, OutputStream outputStream) throws IOException {
        String str;
        StringBuilder sb;
        byte[] bArr = new byte[8192];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        int i = 0;
        while (true) {
            try {
                try {
                    int i2 = bufferedInputStream.read(bArr, 0, 8192);
                    if (i2 == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, i2);
                    i += i2;
                } catch (IOException e) {
                    DebugLogger.e("ZipExtractTask", "Extracted IOException:" + e.toString());
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e2) {
                        DebugLogger.e("ZipExtractTask", "out.close() IOException e=" + e2.toString());
                    }
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        str = "ZipExtractTask";
                        sb = new StringBuilder();
                        sb.append("in.close() IOException e=");
                        sb.append(e.toString());
                        DebugLogger.e(str, sb.toString());
                        return i;
                    }
                }
            } catch (Throwable th) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e4) {
                    DebugLogger.e("ZipExtractTask", "out.close() IOException e=" + e4.toString());
                }
                try {
                    bufferedInputStream.close();
                    throw th;
                } catch (IOException e5) {
                    DebugLogger.e("ZipExtractTask", "in.close() IOException e=" + e5.toString());
                    throw th;
                }
            }
        }
        bufferedOutputStream.flush();
        try {
            bufferedOutputStream.close();
        } catch (IOException e6) {
            DebugLogger.e("ZipExtractTask", "out.close() IOException e=" + e6.toString());
        }
        try {
            bufferedInputStream.close();
        } catch (IOException e7) {
            e = e7;
            str = "ZipExtractTask";
            sb = new StringBuilder();
            sb.append("in.close() IOException e=");
            sb.append(e.toString());
            DebugLogger.e(str, sb.toString());
            return i;
        }
        return i;
    }

    private void b() {
        String str;
        StringBuilder sb;
        String str2;
        if (this.a == null || !this.a.exists()) {
            return;
        }
        if (this.a.delete()) {
            str = "ZipExtractTask";
            sb = new StringBuilder();
            str2 = "Delete file:";
        } else {
            str = "ZipExtractTask";
            sb = new StringBuilder();
            str2 = "Can't delete file:";
        }
        sb.append(str2);
        sb.append(this.a.toString());
        sb.append(" after extracted.");
        DebugLogger.i(str, sb.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:86:0x01f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long c() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 565
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.notification.c.e.c():long");
    }

    public boolean a() {
        return c() > 0;
    }
}
