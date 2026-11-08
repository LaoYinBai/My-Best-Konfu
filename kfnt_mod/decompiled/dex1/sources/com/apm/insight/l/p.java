package com.apm.insight.l;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.apm.insight.k.e;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class p {
    public static String a(Context context) {
        return a(c(context));
    }

    public static String a(e.b bVar) {
        String str;
        try {
            switch (bVar) {
                case WIFI:
                    str = OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR;
                    break;
                case MOBILE_2G:
                    str = "2g";
                    break;
                case MOBILE_3G:
                    str = "3g";
                    break;
                case MOBILE_4G:
                    str = "4g";
                    break;
                case MOBILE:
                    str = "mobile";
                    break;
                case MOBILE_5G:
                    str = "5g";
                    break;
                default:
                    return "";
            }
            return str;
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean b(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isAvailable();
        } catch (Exception unused) {
            return false;
        }
    }

    private static e.b c(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return e.b.NONE;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return e.b.WIFI;
                }
                if (type != 0) {
                    return e.b.MOBILE;
                }
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
                        return e.b.MOBILE_3G;
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    default:
                        return e.b.MOBILE;
                    case 13:
                        return e.b.MOBILE_4G;
                    case 20:
                        return e.b.MOBILE_5G;
                }
            }
            return e.b.NONE;
        } catch (Throwable unused) {
            return e.b.MOBILE;
        }
    }
}
