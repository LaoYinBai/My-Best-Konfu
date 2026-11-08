package com.huawei.hms.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class SystemUtils {
    public static boolean isChinaROM() {
        String strA = a();
        if (!TextUtils.isEmpty(strA)) {
            return AdvanceSetting.CLEAR_NOTIFICATION.equalsIgnoreCase(strA);
        }
        String strB = b();
        if (!TextUtils.isEmpty(strB)) {
            return strB.toLowerCase(Locale.US).contains(AdvanceSetting.CLEAR_NOTIFICATION);
        }
        String localCountry = getLocalCountry();
        if (TextUtils.isEmpty(localCountry)) {
            return false;
        }
        return AdvanceSetting.CLEAR_NOTIFICATION.equalsIgnoreCase(localCountry);
    }

    private static String a() {
        return getSystemProperties("ro.product.locale.region", "");
    }

    private static String b() {
        return getSystemProperties("ro.product.locale", "");
    }

    public static String getLocalCountry() {
        Locale locale = Locale.getDefault();
        return locale != null ? locale.getCountry() : "";
    }

    public static String getSystemProperties(String str, String str2) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, str, str2);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            HMSLog.e("SystemUtils", "An exception occurred while reading: getSystemProperties:" + str);
            return str2;
        }
    }

    public static String getNetType(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) ? "" : activeNetworkInfo.getTypeName();
    }

    public static boolean isEMUI() {
        HMSLog.i("SystemUtils", "is Emui :" + HwBuildEx.VERSION.EMUI_SDK_INT);
        return HwBuildEx.VERSION.EMUI_SDK_INT > 0;
    }
}
