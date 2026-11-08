package com.bytedance.sdk.open.aweme.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AppUtil {
    public static boolean isAppInstalled(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 1) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String buildComponentClassName(String str, String str2) {
        return str + "." + str2;
    }

    public static boolean openApp(Context context, String str) {
        if (!isAppInstalled(context, str) || context == null) {
            return false;
        }
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getPlatformSDKVersion(Context context, String str, String str2) throws PackageManager.NameNotFoundException {
        if (context == null || TextUtils.isEmpty(str) || !isAppInstalled(context, str)) {
            return -1;
        }
        try {
            ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(new ComponentName(str, buildComponentClassName(str, str2)), 128);
            if (activityInfo != null && activityInfo.metaData != null) {
                return activityInfo.metaData.getInt(ParamKeyConstants.META_PLATFORM_SDK_VERSION, -1);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
