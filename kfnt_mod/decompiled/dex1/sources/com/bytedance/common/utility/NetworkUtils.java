package com.bytedance.common.utility;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Pair;
import com.baidu.mobads.sdk.internal.a;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class NetworkUtils {
    static final boolean DEBUG_MOBILE = false;
    private static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    private static final long INTERVAL = 2000;
    private static final int MAX_24G = 2500;
    private static final int MAX_5G = 5900;
    private static final int MIN_24G = 2400;
    private static final int MIN_5G = 4900;
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final int NR_STATE = 3;
    private static final String PARAMETER_SEPARATOR = "&";
    private static NetworkTypeInterceptor sNetworkTypeInterceptor;
    private static NetworkType sNetworkType = NetworkType.UNKNOWN;
    private static boolean sIsReceiverRegisted = false;
    private static long lastAdjustTime = 0;

    public interface NetworkTypeInterceptor {
        NetworkType getNetworkType();
    }

    public static boolean is24GHz(int i) {
        return i > MIN_24G && i < MAX_24G;
    }

    public static boolean is5GHz(int i) {
        return i > MIN_5G && i < MAX_5G;
    }

    private NetworkUtils() {
    }

    public enum CompressType {
        NONE(0),
        GZIP(1),
        DEFLATER(2);

        final int nativeInt;

        CompressType(int i) {
            this.nativeInt = i;
        }
    }

    public enum NetworkType {
        UNKNOWN(-1),
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

        final int nativeInt;

        NetworkType(int i) {
            this.nativeInt = i;
        }

        public int getValue() {
            return this.nativeInt;
        }

        public boolean is2G() {
            return this == MOBILE || this == MOBILE_2G;
        }

        public boolean isWifi() {
            return this == WIFI;
        }

        public boolean is4GOrHigher() {
            return this == MOBILE_4G || this == MOBILE_5G;
        }

        public boolean is3GOrHigher() {
            return this == MOBILE_3G || this == MOBILE_3G_H || this == MOBILE_3G_HP || this == MOBILE_4G || this == MOBILE_5G;
        }

        public boolean isAvailable() {
            return (this == UNKNOWN || this == NONE) ? false : true;
        }
    }

    static void setNetworkType(NetworkType networkType) {
        sNetworkType = networkType;
    }

    public static void setNetworkTypeInterceptor(NetworkTypeInterceptor networkTypeInterceptor) {
        sNetworkTypeInterceptor = networkTypeInterceptor;
    }

    public static boolean is2G(Context context) {
        NetworkType networkType = getNetworkType(context);
        return networkType == NetworkType.MOBILE || networkType == NetworkType.MOBILE_2G;
    }

    public static boolean isWifi(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return (sNetworkTypeInterceptor == null || sNetworkTypeInterceptor.getNetworkType() == NetworkType.NONE) ? 1 == activeNetworkInfo.getType() : sNetworkTypeInterceptor.getNetworkType() == NetworkType.WIFI;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
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

    public static String getMacAddress(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                String macAddressNew = getMacAddressNew(context);
                if (!StringUtils.isEmpty(macAddressNew)) {
                    return macAddressNew;
                }
            }
            WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR)).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getMacAddress();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String getMacAddressNew(Context context) throws SocketException {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                byte[] hardwareAddress = networkInterfaceNextElement.getHardwareAddress();
                if (hardwareAddress != null && hardwareAddress.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b2)));
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
            return null;
        } catch (SocketException unused) {
            return null;
        }
    }

    public static NetworkType getNetworkTypeFast(Context context) {
        checkNetworkTypeInit(context);
        adjustNetwork(context);
        return sNetworkType;
    }

    public static boolean is2GFast(Context context) {
        checkNetworkTypeInit(context);
        adjustNetwork(context);
        return sNetworkType.is2G();
    }

    public static boolean isWifiFast(Context context) {
        checkNetworkTypeInit(context);
        adjustNetwork(context);
        return sNetworkType.isWifi();
    }

    public static boolean isNetworkAvailableFast(Context context) {
        checkNetworkTypeInit(context);
        adjustNetwork(context);
        return sNetworkType.isAvailable();
    }

    private static void checkNetworkTypeInit(Context context) {
        if (sNetworkTypeInterceptor != null && sNetworkTypeInterceptor.getNetworkType() != NetworkType.NONE) {
            sNetworkType = sNetworkTypeInterceptor.getNetworkType();
            return;
        }
        registerReceiver(context);
        if (sNetworkType == NetworkType.UNKNOWN) {
            sNetworkType = getNetworkType(context);
        }
    }

    private static void adjustNetwork(Context context) {
        if (System.currentTimeMillis() - lastAdjustTime > INTERVAL) {
            sNetworkType = getNetworkType(context);
            lastAdjustTime = System.currentTimeMillis();
        }
    }

    private static void registerReceiver(Context context) {
        if (sIsReceiverRegisted || context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        context.getApplicationContext().registerReceiver(new NetworkConnectChangeReceiver(), intentFilter);
        sIsReceiverRegisted = true;
    }

    private static boolean isNRConnected(TelephonyManager telephonyManager) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int i;
        try {
            Object objInvoke = Class.forName(telephonyManager.getClass().getName()).getDeclaredMethod("getServiceState", new Class[0]).invoke(telephonyManager, new Object[0]);
            Method[] declaredMethods = Class.forName(objInvoke.getClass().getName()).getDeclaredMethods();
            int length = declaredMethods.length;
            while (i < length) {
                Method method = declaredMethods[i];
                i = (method.getName().equals("getNrStatus") || method.getName().equals("getNrState")) ? 0 : i + 1;
                method.setAccessible(true);
                return ((Integer) method.invoke(objInvoke, new Object[0])).intValue() == 3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static NetworkType getNetworkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return NetworkType.WIFI;
                }
                if (type == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    switch (telephonyManager.getNetworkType()) {
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
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        default:
                            return NetworkType.MOBILE;
                        case 13:
                            if (Build.VERSION.SDK_INT >= 28 && isNRConnected(telephonyManager)) {
                                return NetworkType.MOBILE_5G;
                            }
                            return NetworkType.MOBILE_4G;
                        case 20:
                            return NetworkType.MOBILE_5G;
                    }
                }
                return NetworkType.MOBILE;
            }
            return NetworkType.NONE;
        } catch (Throwable unused) {
            return NetworkType.MOBILE;
        }
    }

    public static String getNetworkTypeDetail(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return isWifi5GHz(context) ? "wifi5g" : OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR;
                }
                if (type != 0) {
                    return "unknown";
                }
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                switch (telephonyManager.getNetworkType()) {
                    case 2:
                        return "edge";
                    case 3:
                        return "umts";
                    case 4:
                        return "cdma";
                    case 5:
                        return "evdo_0";
                    case 6:
                        return "evdo_a";
                    case 7:
                    case 11:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    default:
                        return String.valueOf(telephonyManager.getNetworkType());
                    case 8:
                        return "hsdpa";
                    case 9:
                        return "hsupa";
                    case 10:
                        return "hspa";
                    case 12:
                        return "evdo_b";
                    case 13:
                        return "lte";
                    case 14:
                        return "ehrpd";
                    case 15:
                        return "hspap";
                    case 20:
                        return "nr";
                }
            }
            return a.f3089a;
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isWifi5GHz(android.content.Context r3) {
        /*
            android.content.Context r3 = r3.getApplicationContext()
            java.lang.String r0 = "wifi"
            java.lang.Object r3 = r3.getSystemService(r0)
            android.net.wifi.WifiManager r3 = (android.net.wifi.WifiManager) r3
            android.net.wifi.WifiInfo r0 = r3.getConnectionInfo()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 21
            if (r1 < r2) goto L1b
            int r3 = r0.getFrequency()
            goto L5a
        L1b:
            java.lang.String r0 = r0.getSSID()
            if (r0 == 0) goto L59
            int r1 = r0.length()
            r2 = 2
            if (r1 <= r2) goto L59
            int r1 = r0.length()
            r2 = 1
            int r1 = r1 - r2
            java.lang.String r0 = r0.substring(r2, r1)
            java.util.List r3 = r3.getScanResults()
            if (r3 == 0) goto L59
            boolean r1 = r3.isEmpty()
            if (r1 != 0) goto L59
            java.util.Iterator r3 = r3.iterator()
        L42:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L59
            java.lang.Object r1 = r3.next()
            android.net.wifi.ScanResult r1 = (android.net.wifi.ScanResult) r1
            java.lang.String r2 = r1.SSID
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L42
            int r3 = r1.frequency
            goto L5a
        L59:
            r3 = 0
        L5a:
            boolean r3 = is5GHz(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.NetworkUtils.isWifi5GHz(android.content.Context):boolean");
    }

    public static String getNetworkAccessType(Context context) {
        return getNetworkAccessType(getNetworkType(context));
    }

    public static String getNetworkAccessType(NetworkType networkType) {
        return networkType == NetworkType.WIFI ? OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR : networkType == NetworkType.WIFI_24GHZ ? "wifi24ghz" : networkType == NetworkType.WIFI_5GHZ ? "wifi5ghz" : networkType == NetworkType.MOBILE_2G ? "2g" : networkType == NetworkType.MOBILE_3G ? "3g" : networkType == NetworkType.MOBILE_3G_H ? "3gh" : networkType == NetworkType.MOBILE_3G_HP ? "3ghp" : networkType == NetworkType.MOBILE_4G ? "4g" : networkType == NetworkType.MOBILE_5G ? "5g" : networkType == NetworkType.MOBILE ? "mobile" : "";
    }

    public static String format(List<Pair<String, String>> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (Pair<String, String> pair : list) {
            String strEncode = encode((String) pair.first, str);
            String str2 = (String) pair.second;
            String strEncode2 = str2 != null ? encode(str2, str) : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(strEncode);
            sb.append(NAME_VALUE_SEPARATOR);
            sb.append(strEncode2);
        }
        return sb.toString();
    }

    private static String encode(String str, String str2) {
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
