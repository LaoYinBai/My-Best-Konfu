package com.huawei.hms.framework.network.grs.c;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static final String a = "c";

    public static String a(String str, Context context) throws Throwable {
        InputStream inputStreamOpen;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                inputStreamOpen = context.getAssets().open(str);
            } catch (Throwable th) {
                th = th;
                inputStreamOpen = inputStream;
            }
        } catch (IOException unused) {
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i == -1) {
                    byteArrayOutputStream.flush();
                    String str2 = new String(byteArrayOutputStream.toByteArray(), C.UTF8_NAME);
                    a((OutputStream) byteArrayOutputStream);
                    a(inputStreamOpen);
                    return str2;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (IOException unused2) {
            inputStream = inputStreamOpen;
            Logger.w(a, "local config file is not exist.filename is {%s}", str);
            a((OutputStream) byteArrayOutputStream);
            a(inputStream);
            return "";
        } catch (Throwable th2) {
            th = th2;
            a((OutputStream) byteArrayOutputStream);
            a(inputStreamOpen);
            throw th;
        }
    }

    public static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Logger.e(a, "closeQuietly IOException", e);
            }
        }
    }

    public static void a(InputStream inputStream) throws IOException {
        a((Closeable) inputStream);
    }

    public static void a(OutputStream outputStream) throws IOException {
        a((Closeable) outputStream);
    }
}
