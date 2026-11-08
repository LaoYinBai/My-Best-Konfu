package com.bytedance.common.push.utility;

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
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static b f3844a;

    public interface b {
        a a();
    }

    public enum a {
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5);

        final int g;

        a(int i) {
            this.g = i;
        }
    }

    public static boolean a(Context context) {
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

    public static a b(Context context) {
        if (f3844a != null && f3844a.a() != a.NONE) {
            return f3844a.a();
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return a.WIFI;
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
                            return a.MOBILE_3G;
                        case 4:
                        case 7:
                        case 11:
                        default:
                            return a.MOBILE;
                        case 13:
                            return a.MOBILE_4G;
                    }
                }
                return a.MOBILE;
            }
            return a.NONE;
        } catch (Throwable unused) {
            return a.MOBILE;
        }
    }

    public static String c(Context context) {
        return a(b(context));
    }

    public static String a(a aVar) {
        return aVar == a.WIFI ? OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR : aVar == a.MOBILE_2G ? "2g" : aVar == a.MOBILE_3G ? "3g" : aVar == a.MOBILE_4G ? "4g" : aVar == a.MOBILE ? "mobile" : "";
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
            sb.append("=");
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
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
