package com.ss.android.socialbase.appdownloader.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.socialbase.appdownloader.AppUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.umeng.commonsdk.proguard.o;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AnUtils {
    private static final HashMap<String, AppUtils.AppInfo> sInstalledPackage = new HashMap<>();

    public static boolean enableAmPlan(JSONArray jSONArray, String str) {
        if (jSONArray == null || TextUtils.isEmpty(str)) {
            return false;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && str.equals(jSONObjectOptJSONObject.optString("type")) && checkAnConfig(jSONObjectOptJSONObject)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkAnConfig(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        return checkOsApiLevel(jSONObject) && checkRequirements(jSONObject.optJSONArray(DownloadSettingKeys.AhPlans.KEY_AH_DEVICE_REQUIREMENTS)) && checkSecure(jSONObject);
    }

    public static boolean checkOsApiLevel(JSONObject jSONObject) throws NumberFormatException {
        if (jSONObject == null) {
            return true;
        }
        int i = Build.VERSION.SDK_INT;
        String strOptString = jSONObject.optString(DownloadSettingKeys.AhPlans.KEY_ALLOW_OS_API_RANGE);
        int iOptInt = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_MIN_OS_API, -1);
        if (TextUtils.isEmpty(strOptString)) {
            return iOptInt <= 0 || i >= iOptInt;
        }
        try {
            String[] strArrSplit = strOptString.split("[-,]");
            for (int i2 = 0; i2 < strArrSplit.length; i2 += 2) {
                int i3 = Integer.parseInt(strArrSplit[i2]);
                int i4 = Integer.parseInt(strArrSplit[i2 + 1]);
                if (i >= i3 && i <= i4) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkSecure(JSONObject jSONObject) {
        return jSONObject == null || SecurityUtils.isSecure() || jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_SECURITY_MODE) != 1;
    }

    public static boolean checkDeviceRomMatch(JSONObject jSONObject) {
        if (jSONObject == null) {
            return true;
        }
        String strOptString = jSONObject.optString(DownloadSettingKeys.AhPlans.KEY_AH_DEVICE_ROM);
        if (TextUtils.isEmpty(strOptString)) {
            return true;
        }
        String name = RomUtils.getName();
        return !TextUtils.isEmpty(name) && strOptString.toLowerCase().contains(name.toLowerCase());
    }

    public static boolean checkRequirements(JSONArray jSONArray) {
        int length;
        if (jSONArray == null || (length = jSONArray.length()) == 0) {
            return true;
        }
        boolean z = false;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString(DownloadSettingKeys.AhPlans.DeviceRequirements.KEY_ANTI_HIJACK_PACKAGE_NAMES);
                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray(DownloadSettingKeys.AhPlans.DeviceRequirements.KEY_ANTI_HIJACK_VERSION_ALLOW);
                JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray(DownloadSettingKeys.AhPlans.DeviceRequirements.KEY_ANTI_HIJACK_VERSION_BLOCK);
                String strOptString2 = jSONObjectOptJSONObject.optString(DownloadSettingKeys.AhPlans.DeviceRequirements.KEY_ALLOW_VERSION_RANGE);
                if (TextUtils.isEmpty(strOptString)) {
                    return false;
                }
                boolean zIsVersionFit = z;
                for (String marketPackageName : strOptString.split(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                    if ("market".equals(marketPackageName)) {
                        marketPackageName = RomUtils.getMarketPackageName();
                    }
                    AppUtils.AppInfo packageInfo = getPackageInfo(marketPackageName);
                    if (packageInfo != null && !(zIsVersionFit = isVersionFit(jSONArrayOptJSONArray, jSONArrayOptJSONArray2, strOptString2, packageInfo))) {
                        return false;
                    }
                }
                z = zIsVersionFit;
            }
        }
        return z;
    }

    private static boolean isVersionFit(JSONArray jSONArray, JSONArray jSONArray2, String str, @NonNull AppUtils.AppInfo appInfo) throws NumberFormatException {
        String versionName = appInfo.getVersionName();
        int versionCode = appInfo.getVersionCode();
        String str2 = versionCode + "_" + versionName;
        if (!TextUtils.isEmpty(str)) {
            try {
                String[] strArrSplit = str.split("[-,]");
                for (int i = 0; i < strArrSplit.length; i += 2) {
                    int i2 = Integer.parseInt(strArrSplit[i]);
                    int i3 = Integer.parseInt(strArrSplit[i + 1]);
                    if (versionCode >= i2 && versionCode <= i3) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (jSONArray != null && jSONArray.length() > 0) {
            if (jsonArrayContain(jSONArray, str2)) {
                return true;
            }
        } else if (jSONArray2 != null && jSONArray2.length() > 0 && !jsonArrayContain(jSONArray2, str2)) {
            return true;
        }
        return false;
    }

    private static boolean jsonArrayContain(JSONArray jSONArray, String str) {
        if (jSONArray == null || str == null) {
            return false;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            if (str.equalsIgnoreCase(jSONArray.optString(i).trim())) {
                return true;
            }
        }
        return false;
    }

    private static AppUtils.AppInfo getPackageInfo(String str) {
        if (sInstalledPackage.containsKey(str)) {
            AppUtils.AppInfo appInfo = sInstalledPackage.get(str);
            if (appInfo != null) {
                return appInfo;
            }
            return null;
        }
        AppUtils.AppInfo appInfo2 = AppUtils.getAppInfo(str);
        sInstalledPackage.put(str, appInfo2);
        if (appInfo2 != null) {
            return appInfo2;
        }
        return null;
    }

    public static AppUtils.AppInfo queryPackageInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                AppUtils.AppInfo packageInfo = getPackageInfo(str);
                if (packageInfo != null) {
                    return packageInfo;
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static boolean modifyPackageName(JSONObject jSONObject, Context context, String str) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        if (TextUtils.isEmpty(str) || context == null || jSONObject == null) {
            return false;
        }
        String strOptString = jSONObject.optString(o.at);
        try {
            String strDecrypt = DecryptUtils.decrypt(jSONObject.optString("az"), strOptString);
            String strDecrypt2 = DecryptUtils.decrypt(jSONObject.optString("ba"), strOptString);
            Field declaredField = ContextWrapper.class.getDeclaredField(strDecrypt);
            declaredField.setAccessible(true);
            Object obj = declaredField.get(context);
            Field declaredField2 = obj.getClass().getDeclaredField(strDecrypt2);
            declaredField2.setAccessible(true);
            declaredField2.set(obj, str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
