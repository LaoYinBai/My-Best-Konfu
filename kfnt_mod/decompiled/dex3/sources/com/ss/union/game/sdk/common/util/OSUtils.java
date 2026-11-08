package com.ss.union.game.sdk.common.util;

import android.text.TextUtils;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OSUtils {
    private static final String a = "ro.miui.ui.version.name";
    private static final String b = "ro.build.version.emui";
    private static final String c = "ro.build.display.id";

    public static boolean isMIUI() {
        return !TextUtils.isEmpty(a(a, ""));
    }

    public static boolean isMIUI6Later() {
        String mIUIVersion = getMIUIVersion();
        if (mIUIVersion.isEmpty()) {
            return false;
        }
        try {
            return Integer.valueOf(mIUIVersion.substring(1)).intValue() >= 6;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static String getMIUIVersion() {
        return isMIUI() ? a(a, "") : "";
    }

    public static boolean isEMUI() {
        return !TextUtils.isEmpty(a(b, ""));
    }

    public static String getEMUIVersion() {
        return isEMUI() ? a(b, "") : "";
    }

    public static boolean isEMUI3_1() {
        String eMUIVersion = getEMUIVersion();
        return "EmotionUI 3".equals(eMUIVersion) || eMUIVersion.contains("EmotionUI_3.1");
    }

    public static boolean isEMUI3_0() {
        return getEMUIVersion().contains("EmotionUI_3.0");
    }

    public static boolean isEMUI3_x() {
        return isEMUI3_0() || isEMUI3_1();
    }

    public static boolean isFlymeOS() {
        return a().toLowerCase().contains(BDAccountPlatformEntity.PLAT_NAME_FLYME);
    }

    public static boolean isFlymeOS4Later() {
        int iIntValue;
        String flymeOSVersion = getFlymeOSVersion();
        if (flymeOSVersion.isEmpty()) {
            return false;
        }
        try {
            if (flymeOSVersion.toLowerCase().contains("os")) {
                iIntValue = Integer.valueOf(flymeOSVersion.substring(9, 10)).intValue();
            } else {
                iIntValue = Integer.valueOf(flymeOSVersion.substring(6, 7)).intValue();
            }
            return iIntValue >= 4;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static boolean isFlymeOS5() {
        int iIntValue;
        String flymeOSVersion = getFlymeOSVersion();
        if (flymeOSVersion.isEmpty()) {
            return false;
        }
        try {
            if (flymeOSVersion.toLowerCase().contains("os")) {
                iIntValue = Integer.valueOf(flymeOSVersion.substring(9, 10)).intValue();
            } else {
                iIntValue = Integer.valueOf(flymeOSVersion.substring(6, 7)).intValue();
            }
            return iIntValue == 5;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static String getFlymeOSVersion() {
        return isFlymeOS() ? a(c, "") : "";
    }

    private static String a() {
        return a(c, "");
    }

    private static String a(String str, String str2) throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class, String.class).invoke(cls, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }
}
