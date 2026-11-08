package com.sigmob.volley.toolbox;

import android.text.TextUtils;
import com.sigmob.volley.b;
import com.sigmob.volley.t;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.umeng.message.util.HttpRequest;
import com.xiaomi.mipush.sdk.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h {
    public static long a(String str) {
        try {
            return a().parse(str).getTime();
        } catch (ParseException e) {
            t.a(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0L;
        }
    }

    public static b.a a(com.sigmob.volley.i iVar) throws NumberFormatException {
        long j;
        long j2;
        boolean z;
        long j3;
        long j4;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = iVar.c;
        String str = map.get(HttpRequest.HEADER_DATE);
        long jA = str != null ? a(str) : 0L;
        String str2 = map.get("Cache-Control");
        int i = 0;
        if (str2 != null) {
            String[] strArrSplit = str2.split(Constants.ACCEPT_TIME_SEPARATOR_SP, 0);
            j = 0;
            int i2 = 0;
            j2 = 0;
            while (i < strArrSplit.length) {
                String strTrim = strArrSplit[i].trim();
                if (strTrim.equals("no-cache") || strTrim.equals("no-store")) {
                    return null;
                }
                if (strTrim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(strTrim.substring(8));
                    } catch (Throwable unused) {
                    }
                } else if (strTrim.startsWith("stale-while-revalidate=")) {
                    j2 = Long.parseLong(strTrim.substring(23));
                } else if (strTrim.equals("must-revalidate") || strTrim.equals("proxy-revalidate")) {
                    i2 = 1;
                }
                i++;
            }
            i = i2;
            z = true;
        } else {
            j = 0;
            j2 = 0;
            z = false;
        }
        String str3 = map.get(HttpRequest.HEADER_EXPIRES);
        long jA2 = str3 != null ? a(str3) : 0L;
        String str4 = map.get("Last-Modified");
        long jA3 = str4 != null ? a(str4) : 0L;
        String str5 = map.get(HttpRequest.HEADER_ETAG);
        if (z) {
            j3 = jCurrentTimeMillis + (j * 1000);
            j4 = i != 0 ? j3 : (j2 * 1000) + j3;
        } else if (jA <= 0 || jA2 < jA) {
            j3 = 0;
        } else {
            j4 = (jA2 - jA) + jCurrentTimeMillis;
            j3 = j4;
        }
        b.a aVar = new b.a();
        aVar.a = iVar.b;
        aVar.b = str5;
        aVar.f = j3;
        aVar.e = j4;
        aVar.c = jA;
        aVar.d = jA3;
        aVar.g = map;
        aVar.h = iVar.d;
        return aVar;
    }

    static String a(long j) {
        return a().format(new Date(j));
    }

    public static String a(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (str2 != null) {
            String[] strArrSplit = str2.split(";", 0);
            for (int i = 1; i < strArrSplit.length; i++) {
                String[] strArrSplit2 = strArrSplit[i].trim().split("=", 0);
                if (strArrSplit2.length == 2 && strArrSplit2[0].equals(HttpRequest.PARAM_CHARSET)) {
                    return strArrSplit2[1];
                }
            }
        }
        return str;
    }

    private static SimpleDateFormat a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    public static boolean a(Map<String, String> map) {
        if (TextUtils.equals(b(map, DownloadUtils.ACCEPT_RANGES), "bytes")) {
            return true;
        }
        String strB = b(map, "Content-Range");
        return strB != null && strB.startsWith("bytes");
    }

    public static String b(Map<String, String> map, String str) {
        return map.get(str);
    }

    public static boolean b(Map<String, String> map) {
        return TextUtils.equals(b(map, HttpRequest.HEADER_CONTENT_ENCODING), HttpRequest.ENCODING_GZIP);
    }
}
