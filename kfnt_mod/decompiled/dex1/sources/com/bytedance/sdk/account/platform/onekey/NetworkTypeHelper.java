package com.bytedance.sdk.account.platform.onekey;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class NetworkTypeHelper {
    public static final String CARRIER_UNKNOWN = "carrier unknown";
    public static final String SIM_CARD_NOT_READY = "sim card not ready";
    static volatile String carrierType = "";

    public static boolean isMobileEnabled(int i) {
        return i == 1 || i == 3;
    }

    private NetworkTypeHelper() {
    }

    static String getCarrierType(Context context) {
        if (TextUtils.isEmpty(carrierType)) {
            carrierType = getCarrierTypeInner(context);
        }
        return carrierType;
    }

    static String getCarrierTypeInner(Context context) {
        try {
            Logger.d("TimeValueData", "call getCarrierTypeInner");
            if (!CommonUtils.isSimCardReady(context)) {
                return SIM_CARD_NOT_READY;
            }
            String carrierOperator = "";
            if (CommonUtils.hasPermission(context, "android.permission.READ_PHONE_STATE")) {
                String mobileImsi = CommonUtils.getMobileImsi(context);
                if (!TextUtils.isEmpty(mobileImsi) && mobileImsi.length() > 5) {
                    carrierOperator = mobileImsi.substring(0, 5);
                }
            }
            if (TextUtils.isEmpty(carrierOperator)) {
                carrierOperator = CommonUtils.getCarrierOperator(context);
            }
            String carrierType2 = CommonUtils.getCarrierType(carrierOperator);
            return TextUtils.isEmpty(carrierType2) ? CARRIER_UNKNOWN : "1".equals(carrierType2) ? "mobile" : OnekeyLoginConstants.TELECOM_TYPE.equals(carrierType2) ? "telecom" : OnekeyLoginConstants.UNICOM_TYPE.equals(carrierType2) ? OnekeyLoginConstants.UNICOM : "others";
        } catch (Exception e) {
            e.printStackTrace();
            return "others";
        }
    }

    static int getNetworkStatus(Context context) {
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        if (context == null) {
            return -1;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            if (Build.VERSION.SDK_INT >= 23 && (activeNetwork = connectivityManager.getActiveNetwork()) != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) != null) {
                boolean zHasTransport = networkCapabilities.hasTransport(0);
                boolean zHasTransport2 = networkCapabilities.hasTransport(1);
                if (isDataMobileEnabled(context, connectivityManager) && zHasTransport2) {
                    return 3;
                }
                if (zHasTransport2) {
                    return 2;
                }
                if (zHasTransport) {
                    return 1;
                }
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                return type == 1 ? isDataMobileEnabled(context, connectivityManager) ? 3 : 2 : type == 0 ? 1 : 0;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static boolean isDataMobileEnabled(Context context, ConnectivityManager connectivityManager) {
        Method declaredMethod;
        try {
            if (CommonUtils.isSimCardReady(context) && (declaredMethod = connectivityManager.getClass().getDeclaredMethod("getMobileDataEnabled", new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getCarrierLog(Context context) {
        try {
            String networkOperatorName = CommonUtils.getTelephonyManager(context).getNetworkOperatorName();
            return !TextUtils.isEmpty(networkOperatorName) ? networkOperatorName : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getNetTypeLog(Context context) {
        return getNetworkAccessType(context);
    }

    public static String getNetworkAccessType(Context context) {
        return getNetworkAccessType(getNetworkType(context));
    }

    public static String getNetworkAccessType(NetworkType networkType) {
        return networkType == NetworkType.WIFI ? OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR : networkType == NetworkType.MOBILE_2G ? "2g" : networkType == NetworkType.MOBILE_3G ? "3g" : networkType == NetworkType.MOBILE_4G ? "4g" : networkType == NetworkType.MOBILE ? "mobile" : "";
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
                    switch (CommonUtils.getTelephonyManager(context).getNetworkType()) {
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

    public enum NetworkType {
        UNKNOWN(-1),
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5);

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

        public boolean isAvailable() {
            return (this == UNKNOWN || this == NONE) ? false : true;
        }
    }
}
