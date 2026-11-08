package com.bytedance.sdk.account.platform.onekey;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.bytedance.common.utility.Logger;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CommonUtils {
    private static final Pattern pattern = Pattern.compile("[0-9]*");
    private static volatile String mobileImsi = "";
    private static volatile String carrierOperator = "";
    private static volatile int simCardReady = 0;
    private static TelephonyManager telephonyManager = null;

    private CommonUtils() {
    }

    public static boolean hasPermission(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return (context == null || TextUtils.isEmpty(str) || context.checkSelfPermission(str) != 0) ? false : true;
        }
        return true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String getCarrierType(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        char c2 = 65535;
        int iHashCode = str.hashCode();
        if (iHashCode != 49679502) {
            switch (iHashCode) {
                case 49679470:
                    if (str.equals("46000")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 49679471:
                    if (str.equals("46001")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 49679472:
                    if (str.equals("46002")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 49679473:
                    if (str.equals("46003")) {
                        c2 = 4;
                        break;
                    }
                    break;
                default:
                    switch (iHashCode) {
                        case 49679475:
                            if (str.equals("46005")) {
                                c2 = 5;
                                break;
                            }
                            break;
                        case 49679476:
                            if (str.equals("46006")) {
                                c2 = '\b';
                                break;
                            }
                            break;
                        case 49679477:
                            if (str.equals("46007")) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case 49679478:
                            if (str.equals("46008")) {
                                c2 = 3;
                                break;
                            }
                            break;
                        case 49679479:
                            if (str.equals("46009")) {
                                c2 = '\t';
                                break;
                            }
                            break;
                    }
            }
        } else if (str.equals("46011")) {
            c2 = 6;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "1";
            case 4:
            case 5:
            case 6:
                return OnekeyLoginConstants.TELECOM_TYPE;
            case 7:
            case '\b':
            case '\t':
                return OnekeyLoginConstants.UNICOM_TYPE;
            default:
                return null;
        }
    }

    public static String getMobileImsi(Context context) {
        if (TextUtils.isEmpty(mobileImsi)) {
            mobileImsi = getMobileImsiInner(context);
        }
        return mobileImsi;
    }

    @SuppressLint({"MissingPermission"})
    private static String getMobileImsiInner(Context context) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Method method;
        boolean z;
        String strSubstring;
        TelephonyManager telephonyManager2;
        Logger.d("TimeValueData", "call getMobileImsiInner");
        String string = "";
        int i = Build.VERSION.SDK_INT;
        try {
            Class<?> cls = Class.forName("android.telephony.SubscriptionManager");
            if (i >= 24) {
                method = cls.getMethod("getDefaultDataSubscriptionId", new Class[0]);
            } else {
                method = cls.getMethod("getDefaultDataSubId", new Class[0]);
            }
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, new Object[0]);
            if (objInvoke instanceof Long) {
                z = true;
            } else {
                if (!(objInvoke instanceof Integer)) {
                    return naiveGetImsi(context);
                }
                z = false;
            }
            Object objInvoke2 = null;
            TelephonyManager telephonyManager3 = getTelephonyManager(context);
            if (i >= 24) {
                if (!z) {
                    Method declaredMethod = TelephonyManager.class.getDeclaredMethod("createForSubscriptionId", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    telephonyManager2 = (TelephonyManager) declaredMethod.invoke(telephonyManager3, (Integer) objInvoke);
                } else {
                    Method declaredMethod2 = TelephonyManager.class.getDeclaredMethod("createForSubscriptionId", Long.TYPE);
                    declaredMethod2.setAccessible(true);
                    telephonyManager2 = (TelephonyManager) declaredMethod2.invoke(telephonyManager3, (Long) objInvoke);
                }
                string = telephonyManager2.getSubscriberId();
            } else if (!z) {
                Method declaredMethod3 = TelephonyManager.class.getDeclaredMethod("getSubscriberId", Integer.TYPE);
                declaredMethod3.setAccessible(true);
                objInvoke2 = declaredMethod3.invoke(telephonyManager3, (Integer) objInvoke);
            } else {
                Method declaredMethod4 = TelephonyManager.class.getDeclaredMethod("getSubscriberId", Long.TYPE);
                declaredMethod4.setAccessible(true);
                objInvoke2 = declaredMethod4.invoke(telephonyManager3, (Long) objInvoke);
            }
            if (objInvoke2 != null) {
                string = objInvoke2.toString();
            }
            if (!isNumeric(string) || string == null) {
                string = "";
            }
            if (string.length() > 15) {
                strSubstring = string.substring(0, 15);
            } else if (string.length() <= 0 || string.length() >= 15) {
                strSubstring = string;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i2 = 0; i2 < 15 - string.length(); i2++) {
                    stringBuffer.append(OnekeyLoginConstants.CU_RESULT_SUCCESS);
                }
                strSubstring = string + stringBuffer.toString();
            }
            string = strSubstring;
        } catch (Exception e) {
            Log.e("CommonUtils", "getMobileImsi: " + e);
        }
        return TextUtils.isEmpty(string) ? naiveGetImsi(context) : string;
    }

    @SuppressLint({"MissingPermission"})
    private static String naiveGetImsi(Context context) {
        try {
            return getTelephonyManager(context).getSubscriberId();
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean isNumeric(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return pattern.matcher(str).matches();
    }

    public static String getCarrierOperator(Context context) {
        if (TextUtils.isEmpty(carrierOperator)) {
            carrierOperator = getCarrierOperatorInner(context);
        }
        return carrierOperator;
    }

    @SuppressLint({"MissingPermission"})
    private static String getCarrierOperatorInner(Context context) {
        TelephonyManager telephonyManager2;
        try {
            Logger.d("TimeValueData", "call getCarrierOperatorInner");
            if (context == null || (telephonyManager2 = getTelephonyManager(context)) == null) {
                return null;
            }
            return telephonyManager2.getSimOperator();
        } catch (Exception e) {
            Log.e("CommonUtils", "getCarrierOperator: " + e);
        }
        return null;
    }

    public static boolean isSimCardReady(Context context) {
        if (simCardReady == 0) {
            simCardReady = isSimCardReadyInner(context) ? 1 : -1;
        }
        return simCardReady > 0;
    }

    @SuppressLint({"MissingPermission"})
    private static boolean isSimCardReadyInner(Context context) {
        TelephonyManager telephonyManager2;
        try {
            Logger.d("TimeValueData", "call isSimCardReadyInner");
            if (context == null || (telephonyManager2 = getTelephonyManager(context)) == null) {
                return true;
            }
            return telephonyManager2.getSimState() == 5;
        } catch (Exception e) {
            Log.e("CommonUtils", "isSimCardReady: " + e);
            return true;
        }
    }

    @Nullable
    public static TelephonyManager getTelephonyManager(Context context) {
        if (telephonyManager == null) {
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
        }
        return telephonyManager;
    }

    public static void cleanSimCache() {
        simCardReady = 0;
        mobileImsi = "";
        carrierOperator = "";
        NetworkTypeHelper.carrierType = "";
    }
}
