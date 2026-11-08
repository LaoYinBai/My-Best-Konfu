package com.ss.union.game.sdk.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.taobao.accs.utl.UtilityImpl;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NetworkUtils {

    public enum NetworkType {
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5);

        final int a;

        NetworkType(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    public static boolean isWifi() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return 1 == activeNetworkInfo.getType();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isNetworkAvailable() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String getMacAddress() {
        WifiInfo connectionInfo;
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                String strA = a();
                if (!TextUtils.isEmpty(strA)) {
                    return strA;
                }
            }
            Context context = GlobalApplicationUtils.getContext();
            return (context == null || (connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo()) == null) ? "" : connectionInfo.getMacAddress();
        } catch (Throwable unused) {
            return "";
        }
    }

    private static String a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                byte[] hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                if (hardwareAddress != null && hardwareAddress.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String string = sb.toString();
                    if (networkInterfaceNextElement.getName().equals("wlan0")) {
                        return string;
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static NetworkType getNetworkType() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return NetworkType.MOBILE;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return NetworkType.WIFI;
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
                            return NetworkType.MOBILE_3G;
                        case 4:
                        case 7:
                        case 11:
                        default:
                            return NetworkType.MOBILE;
                        case 13:
                            return NetworkType.MOBILE_4G;
                    }
                }
                return NetworkType.MOBILE;
            }
            return NetworkType.NONE;
        } catch (Throwable unused) {
            return NetworkType.MOBILE;
        }
    }

    public static String getNetworkAccessType() {
        return a(getNetworkType());
    }

    private static String a(NetworkType networkType) {
        String str;
        try {
            switch (networkType) {
                case WIFI:
                    str = UtilityImpl.NET_TYPE_WIFI;
                    break;
                case MOBILE_2G:
                    str = UtilityImpl.NET_TYPE_2G;
                    break;
                case MOBILE_3G:
                    str = UtilityImpl.NET_TYPE_3G;
                    break;
                case MOBILE_4G:
                    str = UtilityImpl.NET_TYPE_4G;
                    break;
                case MOBILE:
                    str = "mobile";
                    break;
                default:
                    return "";
            }
            return str;
        } catch (Throwable unused) {
            return "";
        }
    }

    private static void a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            if (hostAddress == null || hostAddress.length() <= 0) {
                return;
            }
            strArr[0] = hostAddress;
        } catch (UnknownHostException unused) {
        }
    }
}
