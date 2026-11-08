package com.qq.e.comm.managers.setting;

import com.qq.e.comm.pi.CustomLandingPageListener;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class GlobalSetting {
    public static final String BD_SDK_WRAPPER = "BD";
    public static final String KS_SDK_WRAPPER = "KS";
    public static final String TT_SDK_WRAPPER = "TT";
    private static volatile CustomLandingPageListener a = null;
    private static volatile Integer b = null;
    private static volatile boolean c = false;
    private static volatile boolean d = true;
    private static volatile Integer e;
    private static volatile Boolean f;
    private static volatile Boolean g;
    private static volatile Boolean h;
    private static final Map<String, String> i = new HashMap();
    private static volatile String j = null;
    private static volatile String k = null;
    private static volatile String l = null;
    private static volatile String m = null;
    private static volatile String n = null;

    public static Boolean getAgreeReadAndroidId() {
        return h;
    }

    public static Boolean getAgreeReadDeviceId() {
        return g;
    }

    public static Integer getChannel() {
        return b;
    }

    public static String getCustomADActivityClassName() {
        return j;
    }

    public static CustomLandingPageListener getCustomLandingPageListener() {
        return a;
    }

    public static String getCustomLandscapeActivityClassName() {
        return m;
    }

    public static String getCustomPortraitActivityClassName() {
        return k;
    }

    public static String getCustomRewardvideoLandscapeActivityClassName() {
        return n;
    }

    public static String getCustomRewardvideoPortraitActivityClassName() {
        return l;
    }

    public static Integer getPersonalizedState() {
        return e;
    }

    public static Map<String, String> getPreloadAdapterMaps() {
        return i;
    }

    public static boolean isAgreePrivacyStrategy() {
        return f == null || f.booleanValue();
    }

    public static boolean isAgreeReadAndroidId() {
        if (h == null) {
            return true;
        }
        return h.booleanValue();
    }

    public static boolean isAgreeReadDeviceId() {
        if (g == null) {
            return true;
        }
        return g.booleanValue();
    }

    public static boolean isEnableMediationTool() {
        return c;
    }

    public static boolean isEnableVideoDownloadingCache() {
        return d;
    }

    public static void setAgreePrivacyStrategy(boolean z) {
        if (f == null) {
            f = Boolean.valueOf(z);
        }
    }

    public static void setAgreeReadAndroidId(boolean z) {
        h = Boolean.valueOf(z);
    }

    public static void setAgreeReadDeviceId(boolean z) {
        g = Boolean.valueOf(z);
    }

    public static void setChannel(int i2) {
        if (b == null) {
            b = Integer.valueOf(i2);
        }
    }

    public static void setCustomADActivityClassName(String str) {
        j = str;
    }

    public static void setCustomLandingPageListener(CustomLandingPageListener customLandingPageListener) {
        a = customLandingPageListener;
    }

    public static void setCustomLandscapeActivityClassName(String str) {
        m = str;
    }

    public static void setCustomPortraitActivityClassName(String str) {
        k = str;
    }

    public static void setCustomRewardvideoLandscapeActivityClassName(String str) {
        n = str;
    }

    public static void setCustomRewardvideoPortraitActivityClassName(String str) {
        l = str;
    }

    public static void setEnableMediationTool(boolean z) {
        c = z;
    }

    public static void setEnableVideoDownloadingCache(boolean z) {
        d = z;
    }

    public static void setPersonalizedState(int i2) {
        e = Integer.valueOf(i2);
    }

    public static void setPreloadAdapters(Map<String, String> map) {
        if (map == null) {
            return;
        }
        i.putAll(map);
    }
}
