package com.bytedance.b.a.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.bytedance.frameworks.core.encrypt.TTEncryptUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f3691a = false;

    /* renamed from: b, reason: collision with root package name */
    private static b f3692b;

    public interface b {
        String a(String str, byte[] bArr);
    }

    public enum a {
        NONE(0),
        GZIP(1),
        DEFLATER(2);

        final int d;

        a(int i) {
            this.d = i;
        }
    }

    public enum c {
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5);

        final int g;

        c(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }
    }

    public static boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return 1 == activeNetworkInfo.getType();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static c b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return c.WIFI;
                }
                if (type == 0) {
                    switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return c.MOBILE_3G;
                        case 4:
                        case 7:
                        case 11:
                        default:
                            return c.MOBILE;
                        case 13:
                            return c.MOBILE_4G;
                    }
                }
                return c.MOBILE;
            }
            return c.NONE;
        } catch (Throwable unused) {
            return c.MOBILE;
        }
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String d(Context context) {
        if (context == null) {
            return null;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return activeNetworkInfo.getTypeName();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void a(b bVar) {
        f3692b = bVar;
    }

    public static byte[] a(String str, String str2, boolean z) throws Throwable {
        if (TextUtils.isDigitsOnly(str)) {
            return null;
        }
        return a(str, null, str2, null, "GET", false, z).b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0142 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0059 A[Catch: all -> 0x004e, Throwable -> 0x0051, TryCatch #12 {all -> 0x004e, Throwable -> 0x0051, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:28:0x0059, B:31:0x0063, B:33:0x006a, B:34:0x006f, B:36:0x007f, B:38:0x0084, B:42:0x0096, B:47:0x009e, B:48:0x00a1, B:49:0x00a2, B:51:0x00aa, B:82:0x011f, B:83:0x0128, B:84:0x0129, B:85:0x0130, B:29:0x005e), top: B:115:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005e A[Catch: all -> 0x004e, Throwable -> 0x0051, TryCatch #12 {all -> 0x004e, Throwable -> 0x0051, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:28:0x0059, B:31:0x0063, B:33:0x006a, B:34:0x006f, B:36:0x007f, B:38:0x0084, B:42:0x0096, B:47:0x009e, B:48:0x00a1, B:49:0x00a2, B:51:0x00aa, B:82:0x011f, B:83:0x0128, B:84:0x0129, B:85:0x0130, B:29:0x005e), top: B:115:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0063 A[Catch: all -> 0x004e, Throwable -> 0x0051, TryCatch #12 {all -> 0x004e, Throwable -> 0x0051, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:28:0x0059, B:31:0x0063, B:33:0x006a, B:34:0x006f, B:36:0x007f, B:38:0x0084, B:42:0x0096, B:47:0x009e, B:48:0x00a1, B:49:0x00a2, B:51:0x00aa, B:82:0x011f, B:83:0x0128, B:84:0x0129, B:85:0x0130, B:29:0x005e), top: B:115:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006a A[Catch: all -> 0x004e, Throwable -> 0x0051, TryCatch #12 {all -> 0x004e, Throwable -> 0x0051, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:28:0x0059, B:31:0x0063, B:33:0x006a, B:34:0x006f, B:36:0x007f, B:38:0x0084, B:42:0x0096, B:47:0x009e, B:48:0x00a1, B:49:0x00a2, B:51:0x00aa, B:82:0x011f, B:83:0x0128, B:84:0x0129, B:85:0x0130, B:29:0x005e), top: B:115:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007f A[Catch: all -> 0x004e, Throwable -> 0x0051, TryCatch #12 {all -> 0x004e, Throwable -> 0x0051, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:28:0x0059, B:31:0x0063, B:33:0x006a, B:34:0x006f, B:36:0x007f, B:38:0x0084, B:42:0x0096, B:47:0x009e, B:48:0x00a1, B:49:0x00a2, B:51:0x00aa, B:82:0x011f, B:83:0x0128, B:84:0x0129, B:85:0x0130, B:29:0x005e), top: B:115:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0129 A[Catch: all -> 0x004e, Throwable -> 0x0051, TryCatch #12 {all -> 0x004e, Throwable -> 0x0051, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:28:0x0059, B:31:0x0063, B:33:0x006a, B:34:0x006f, B:36:0x007f, B:38:0x0084, B:42:0x0096, B:47:0x009e, B:48:0x00a1, B:49:0x00a2, B:51:0x00aa, B:82:0x011f, B:83:0x0128, B:84:0x0129, B:85:0x0130, B:29:0x005e), top: B:115:0x0029 }] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.bytedance.services.apm.api.d a(java.lang.String r4, byte[] r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, boolean r9, boolean r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.b.a.c.j.a(java.lang.String, byte[], java.lang.String, java.lang.String, java.lang.String, boolean, boolean):com.bytedance.services.apm.api.d");
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] a(byte[] bArr, Map<String, String> map) throws IOException {
        if (bArr.length <= 128) {
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                map.put("Content-Encoding", HttpConstant.GZIP);
                return byteArray;
            } catch (IOException e) {
                throw e;
            }
        } catch (Throwable th) {
            gZIPOutputStream.close();
            throw th;
        }
    }

    public static com.bytedance.services.apm.api.d a(String str, byte[] bArr, a aVar, String str2, boolean z) throws Throwable {
        String str3;
        String str4;
        byte[] bArr2;
        String str5 = null;
        if (str == null) {
            return null;
        }
        if (bArr == null) {
            bArr = new byte[0];
        }
        int length = bArr.length;
        if (a.GZIP == aVar && length > 128) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.close();
                bArr = byteArrayOutputStream.toByteArray();
                str5 = HttpConstant.GZIP;
            } catch (Throwable th) {
                gZIPOutputStream.close();
                throw th;
            }
        } else if (a.DEFLATER == aVar && length > 128) {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(8192);
            Deflater deflater = new Deflater();
            deflater.setInput(bArr);
            deflater.finish();
            byte[] bArr3 = new byte[8192];
            while (!deflater.finished()) {
                byteArrayOutputStream2.write(bArr3, 0, deflater.deflate(bArr3));
            }
            deflater.end();
            bArr = byteArrayOutputStream2.toByteArray();
            str5 = "deflate";
        }
        byte[] bArr4 = bArr;
        String str6 = str5;
        if (z) {
            byte[] bArrA = TTEncryptUtils.a(bArr4, bArr4.length);
            if (bArrA != null) {
                str3 = str + "&tt_data=a";
                bArr2 = bArrA;
                str4 = "application/octet-stream;tt-data=a";
            } else {
                str3 = str;
                str4 = str2;
                bArr2 = bArr4;
            }
            return a(str3, bArr2, str4, str6, "POST", true, true);
        }
        return a(str, bArr4, str2, str6, "POST", true, false);
    }
}
