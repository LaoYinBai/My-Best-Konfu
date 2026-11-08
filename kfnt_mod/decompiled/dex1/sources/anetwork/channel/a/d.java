package anetwork.channel.a;

import anet.channel.util.HttpConstant;
import anet.channel.util.HttpHelper;
import anetwork.channel.a.a;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final TimeZone f630a = TimeZone.getTimeZone("GMT");

    /* renamed from: b, reason: collision with root package name */
    private static final DateFormat f631b = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);

    static {
        f631b.setTimeZone(f630a);
    }

    public static String a(long j) {
        return f631b.format(new Date(j));
    }

    private static long a(String str) {
        if (str.length() == 0) {
            return 0L;
        }
        try {
            ParsePosition parsePosition = new ParsePosition(0);
            Date date = f631b.parse(str, parsePosition);
            if (parsePosition.getIndex() == str.length()) {
                return date.getTime();
            }
        } catch (Exception unused) {
        }
        return 0L;
    }

    public static a.C0010a a(Map<String, List<String>> map) throws NumberFormatException {
        long j;
        long j2;
        long jCurrentTimeMillis = System.currentTimeMillis();
        String singleHeaderFieldByKey = HttpHelper.getSingleHeaderFieldByKey(map, HttpConstant.CACHE_CONTROL);
        boolean z = true;
        int i = 0;
        if (singleHeaderFieldByKey != null) {
            String[] strArrSplit = singleHeaderFieldByKey.split(IWeiboService.Scope.EMPTY_SCOPE);
            j = 0;
            while (true) {
                if (i >= strArrSplit.length) {
                    break;
                }
                String strTrim = strArrSplit[i].trim();
                if (strTrim.equals("no-store")) {
                    return null;
                }
                if (strTrim.equals("no-cache")) {
                    j = 0;
                    break;
                }
                if (strTrim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(strTrim.substring(8));
                    } catch (Exception unused) {
                    }
                }
                i++;
            }
        } else {
            j = 0;
            z = false;
        }
        String singleHeaderFieldByKey2 = HttpHelper.getSingleHeaderFieldByKey(map, "Date");
        long jA = singleHeaderFieldByKey2 != null ? a(singleHeaderFieldByKey2) : 0L;
        String singleHeaderFieldByKey3 = HttpHelper.getSingleHeaderFieldByKey(map, "Expires");
        long jA2 = singleHeaderFieldByKey3 != null ? a(singleHeaderFieldByKey3) : 0L;
        String singleHeaderFieldByKey4 = HttpHelper.getSingleHeaderFieldByKey(map, "Last-Modified");
        long jA3 = singleHeaderFieldByKey4 != null ? a(singleHeaderFieldByKey4) : 0L;
        String singleHeaderFieldByKey5 = HttpHelper.getSingleHeaderFieldByKey(map, "ETag");
        if (z) {
            jCurrentTimeMillis += j * 1000;
        } else {
            if (jA <= 0 || jA2 < jA) {
                j2 = jA3;
                if (j2 <= 0) {
                    jCurrentTimeMillis = 0;
                }
                if (jCurrentTimeMillis != 0 && singleHeaderFieldByKey5 == null) {
                    return null;
                }
                a.C0010a c0010a = new a.C0010a();
                c0010a.f622b = singleHeaderFieldByKey5;
                c0010a.e = jCurrentTimeMillis;
                c0010a.f623c = jA;
                c0010a.d = j2;
                c0010a.f = map;
                return c0010a;
            }
            jCurrentTimeMillis += jA2 - jA;
        }
        j2 = jA3;
        if (jCurrentTimeMillis != 0) {
        }
        a.C0010a c0010a2 = new a.C0010a();
        c0010a2.f622b = singleHeaderFieldByKey5;
        c0010a2.e = jCurrentTimeMillis;
        c0010a2.f623c = jA;
        c0010a2.d = j2;
        c0010a2.f = map;
        return c0010a2;
    }
}
