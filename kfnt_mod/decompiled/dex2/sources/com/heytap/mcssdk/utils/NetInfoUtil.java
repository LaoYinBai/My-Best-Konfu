package com.heytap.mcssdk.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.meizu.cloud.pushsdk.constants.PushConstants;

@SuppressLint({"DefaultLocale"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class NetInfoUtil {
    private static final int NETWORK_CLASS_2_G = 1;
    private static final int NETWORK_CLASS_3_G = 2;
    private static final int NETWORK_CLASS_4_G = 3;
    private static final int NETWORK_CLASS_UNAVAILABLE = -1;
    private static final int NETWORK_CLASS_UNKNOWN = 0;
    private static final int NETWORK_CLASS_WIFI = -101;
    public static final int NETWORK_TYPE_1xRTT = 7;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_HSPAP = 15;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_UMTS = 3;
    private static final int NETWORK_TYPE_UNAVAILABLE = -1;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    private static final int NETWORK_TYPE_WIFI = -101;

    @SuppressLint({"DefaultLocale"})
    public static String getNetType(Context context) {
        String str = PushConstants.PUSH_TYPE_NOTIFY;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (activeNetworkInfo == null) {
                return PushConstants.PUSH_TYPE_NOTIFY;
            }
            String upperCase = activeNetworkInfo.getTypeName().toUpperCase();
            try {
                if (upperCase.equals("MOBILE")) {
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (!TextUtils.isEmpty(extraInfo)) {
                        return extraInfo.toUpperCase();
                    }
                }
                return upperCase;
            } catch (Exception e) {
                e = e;
                str = upperCase;
                LogUtil.e(e);
                return str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private static int getNetworkClassByType(int i) {
        int i2 = -101;
        if (i != -101) {
            i2 = -1;
            if (i != -1) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 1;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 2;
                    case 13:
                        return 3;
                    default:
                        return 0;
                }
            }
        }
        return i2;
    }

    public static String getNetworkType(Context context) {
        int networkType;
        NetworkInfo activeNetworkInfo;
        TelephonyManager telephonyManager;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
            int type = activeNetworkInfo.getType();
            networkType = type == 1 ? -101 : (type != 0 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) ? 0 : telephonyManager.getNetworkType();
        } else {
            networkType = -1;
        }
        int networkClassByType = getNetworkClassByType(networkType);
        if (networkClassByType == -101) {
            return "WIFI";
        }
        switch (networkClassByType) {
            case -1:
            case 0:
            default:
                return GrsBaseInfo.CountryCodeSource.UNKNOWN;
            case 1:
                return "2G";
            case 2:
                return "3G";
            case 3:
                return "4G";
        }
    }

    public static boolean is2GNetwork(Context context) {
        try {
            String netType = getNetType(context);
            if (!netType.equals("UNINET") && !netType.equals("UNIWAP") && !netType.equals("CMNET") && !netType.equals("CMWAP") && !netType.equals("CTNET")) {
                if (!netType.equals("CTWAP")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            LogUtil.e(e);
            return false;
        }
    }

    public static boolean is3GNetwork(Context context) {
        try {
            String netType = getNetType(context);
            if (!netType.equals("3GNET")) {
                if (!netType.equals("3GWAP")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            LogUtil.e(e);
            return false;
        }
    }

    public static boolean isConnectNet(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            LogUtil.e(e);
            return false;
        }
    }

    public static boolean isWapNet(Context context) {
        String netType = getNetType(context);
        return netType.equals("CMWAP") || netType.equals("3GWAP") || netType.equals("UNIWAP");
    }

    public static boolean isWifiNetWork(Context context) {
        try {
            return getNetType(context).equals("WIFI");
        } catch (Exception e) {
            LogUtil.e(e);
            return false;
        }
    }
}
