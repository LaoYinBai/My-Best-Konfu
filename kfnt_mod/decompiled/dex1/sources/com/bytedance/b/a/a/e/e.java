package com.bytedance.b.a.a.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Pair;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    static final boolean f3639a = false;

    /* renamed from: b, reason: collision with root package name */
    private static c f3640b = null;

    /* renamed from: c, reason: collision with root package name */
    private static b f3641c = b.UNKNOWN;
    private static boolean d = false;
    private static final String e = "&";
    private static final String f = "=";
    private static final String g = "ISO-8859-1";

    public interface c {
        b a();
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

    public enum b {
        UNKNOWN(-1),
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5);

        final int h;

        b(int i2) {
            this.h = i2;
        }

        public int a() {
            return this.h;
        }

        public boolean b() {
            return this == MOBILE || this == MOBILE_2G;
        }

        public boolean c() {
            return this == WIFI;
        }

        public boolean d() {
            return (this == UNKNOWN || this == NONE) ? false : true;
        }
    }

    static void a(b bVar) {
        f3641c = bVar;
    }

    public static void a(c cVar) {
        f3640b = cVar;
    }

    public static boolean a(Context context) {
        b bVarD = d(context);
        return bVarD == b.MOBILE || bVarD == b.MOBILE_2G;
    }

    public static boolean b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return (f3640b == null || f3640b.a() == b.NONE) ? 1 == activeNetworkInfo.getType() : f3640b.a() == b.WIFI;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static b d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return b.WIFI;
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
                            return b.MOBILE_3G;
                        case 4:
                        case 7:
                        case 11:
                        default:
                            return b.MOBILE;
                        case 13:
                            return b.MOBILE_4G;
                    }
                }
                return b.MOBILE;
            }
            return b.NONE;
        } catch (Throwable unused) {
            return b.MOBILE;
        }
    }

    public static String b(b bVar) {
        return bVar == b.WIFI ? OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR : bVar == b.MOBILE_2G ? "2g" : bVar == b.MOBILE_3G ? "3g" : bVar == b.MOBILE_4G ? "4g" : bVar == b.MOBILE ? "mobile" : "";
    }

    public static String a(List<Pair<String, String>> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (Pair<String, String> pair : list) {
            String strA = a((String) pair.first, str);
            String str2 = (String) pair.second;
            String strA2 = str2 != null ? a(str2, str) : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(strA);
            sb.append(f);
            sb.append(strA2);
        }
        return sb.toString();
    }

    private static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "ISO-8859-1";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
