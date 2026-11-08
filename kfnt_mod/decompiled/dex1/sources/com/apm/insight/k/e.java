package com.apm.insight.k;

import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.apm.insight.Npth;
import com.apm.insight.l.q;
import com.bytedance.pangle.provider.ContentProviderManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f2919a = false;

    /* renamed from: b, reason: collision with root package name */
    private static h f2920b;

    public enum a {
        NONE(0),
        GZIP(1),
        DEFLATER(2);

        final int d;

        a(int i) {
            this.d = i;
        }
    }

    public enum b {
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5),
        MOBILE_5G(6),
        WIFI_24GHZ(7),
        WIFI_5GHZ(8),
        MOBILE_3G_H(9),
        MOBILE_3G_HP(10);

        final int l;

        b(int i) {
            this.l = i;
        }
    }

    public static l a(long j, String str, byte[] bArr, a aVar, String str2, boolean z) {
        String str3;
        String str4;
        byte[] bArr2;
        StringBuilder sb;
        String str5;
        if (!Npth.isStopUpload() && str != null) {
            if (bArr == null) {
                bArr = new byte[0];
            }
            int length = bArr.length;
            String str6 = null;
            if (a.GZIP == aVar && length > 128) {
                bArr = b(bArr);
                str6 = HttpConstant.GZIP;
            } else if (a.DEFLATER == aVar && length > 128) {
                bArr = a(bArr);
                str6 = "deflate";
            }
            byte[] bArr3 = bArr;
            String str7 = str6;
            if (bArr3 == null) {
                return new l(202);
            }
            if (!z) {
                return a(str, bArr3, str2, str7, "POST", true, false);
            }
            byte[] bArrA = com.apm.insight.h.i().getEncryptImpl().a(bArr3);
            if (bArrA == null) {
                str3 = str;
                str4 = str2;
                bArr2 = bArr3;
            } else if (TextUtils.isEmpty(new URL(str).getQuery())) {
                if (!str.endsWith("?")) {
                    sb = new StringBuilder();
                    sb.append(str);
                    str5 = "?";
                    sb.append(str5);
                    str = sb.toString();
                }
                bArr2 = bArrA;
                str3 = str + "tt_data=a";
                str4 = "application/octet-stream;tt-data=a";
            } else {
                if (!str.endsWith("&")) {
                    sb = new StringBuilder();
                    sb.append(str);
                    str5 = "&";
                    sb.append(str5);
                    str = sb.toString();
                }
                bArr2 = bArrA;
                str3 = str + "tt_data=a";
                str4 = "application/octet-stream;tt-data=a";
            }
            return a(str3, bArr2, str4, str7, "POST", true, true);
        }
        return new l(201);
    }

    public static l a(String str, String str2) {
        return a(str, str2, b());
    }

    public static l a(String str, String str2, boolean z) {
        try {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                return a(PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, str, str2.getBytes(), a.GZIP, com.bytedance.b.a.a.c.e.f3610c, z);
            }
            return new l(201);
        } catch (Throwable th) {
            q.b(th);
            return new l(207, th);
        }
    }

    public static l a(String str, String str2, File... fileArr) {
        return b(str, str2, fileArr);
    }

    private static l a(String str, byte[] bArr, String str2, String str3, String str4, boolean z, boolean z2) {
        return b(str, bArr, str2, str3, str4, z, z2);
    }

    private static String a(String str, Map map) {
        if (TextUtils.isDigitsOnly(str) || map == null || map.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (!str.contains("?")) {
            sb.append("?");
        }
        try {
            for (Map.Entry entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    if (!sb.toString().endsWith("?")) {
                        sb.append("&");
                    }
                    sb.append(d(entry.getKey().toString(), "UTF-8"));
                    sb.append("=");
                    sb.append(d(entry.getValue().toString(), "UTF-8"));
                }
            }
            return sb.toString();
        } catch (Exception unused) {
            return str;
        }
    }

    public static void a(h hVar) {
        f2920b = hVar;
    }

    public static boolean a() {
        return true;
    }

    public static boolean a(String str, String str2, String str3, String str4, List<String> list) {
        if (Npth.isStopUpload()) {
            return false;
        }
        try {
            j jVar = new j(str, "UTF-8", false);
            jVar.a("aid", str2);
            jVar.a(com.bytedance.b.a.c.h.x, str3);
            jVar.a("os", "Android");
            jVar.a(ContentProviderManager.PLUGIN_PROCESS_NAME, str4);
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                File file = new File(it.next());
                if (file.exists()) {
                    HashMap map = new HashMap();
                    map.put("logtype", "alog");
                    map.put("scene", "崩溃");
                    jVar.a(file.getName(), file, map);
                }
            }
            return new JSONObject(jVar.a()).optInt("errno", -1) == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                inputStream.close();
                try {
                    return byteArrayOutputStream.toByteArray();
                } finally {
                    com.apm.insight.l.k.a(byteArrayOutputStream);
                }
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static byte[] a(String str, Map<String, String> map, byte[] bArr) {
        try {
            return a(PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, a(str, map), bArr, a.GZIP, com.bytedance.b.a.a.c.e.f3610c, false).b();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        while (!deflater.finished()) {
            byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
        }
        deflater.end();
        return byteArrayOutputStream.toByteArray();
    }

    public static l b(String str, String str2) {
        return a(str, str2, a());
    }

    public static l b(String str, String str2, File... fileArr) {
        if (Npth.isStopUpload()) {
            return new l(201);
        }
        try {
            j jVar = new j(c(str, "have_dump=true&encrypt=true"), "UTF-8", true);
            jVar.a("json", str2, true);
            jVar.a("file", fileArr);
            try {
                return new l(0, new JSONObject(jVar.a()));
            } catch (JSONException e) {
                return new l(0, e);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return new l(207);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x00f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.apm.insight.k.l b(java.lang.String r2, byte[] r3, java.lang.String r4, java.lang.String r5, java.lang.String r6, boolean r7, boolean r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.k.e.b(java.lang.String, byte[], java.lang.String, java.lang.String, java.lang.String, boolean, boolean):com.apm.insight.k.l");
    }

    public static boolean b() {
        return true;
    }

    private static byte[] b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                q.b(th);
                gZIPOutputStream.close();
                return null;
            }
        } catch (Throwable th2) {
            gZIPOutputStream.close();
            throw th2;
        }
    }

    private static l c(byte[] bArr) {
        return new l(204, bArr);
    }

    public static String c() {
        return com.apm.insight.h.i().getJavaCrashUploadUrl();
    }

    private static String c(String str, String str2) {
        StringBuilder sb;
        try {
            if (TextUtils.isEmpty(new URL(str).getQuery())) {
                if (!str.endsWith("?")) {
                    sb = new StringBuilder();
                    sb.append(str);
                    sb.append("?");
                    str = sb.toString();
                }
            } else if (!str.endsWith("&")) {
                sb = new StringBuilder();
                sb.append(str);
                sb.append("&");
                str = sb.toString();
            }
            return str + str2;
        } catch (Throwable unused) {
            return str;
        }
    }

    public static String d() {
        return com.apm.insight.h.i().getAlogUploadUrl();
    }

    private static String d(String str, String str2) {
        if (str2 == null) {
            str2 = "UTF-8";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String e() {
        return com.apm.insight.h.i().getLaunchCrashUploadUrl();
    }

    public static String f() {
        return com.apm.insight.h.i().getExceptionUploadUrl();
    }

    public static String g() {
        return com.apm.insight.h.i().getNativeCrashUploadUrl();
    }
}
