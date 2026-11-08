package com.bytedance.msdk.base;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {
    public static String a(int i) {
        return i == 3 ? "gdt" : i == 2 ? "admob" : i == 1 ? "pangle" : i == 4 ? "mintegral" : i == 5 ? "unity" : i == 6 ? "baidu" : i == 7 ? "ks" : i == 8 ? "sigmob" : i == 9 ? "klevin" : i == -1 ? "custom" : "";
    }

    public static String a(int i, int i2) {
        switch (i) {
            case 1:
                return "Banner";
            case 2:
                return "Interstitial";
            case 3:
            case 4:
                return "Splash";
            case 5:
                return "Native";
            case 6:
            default:
                return null;
            case 7:
                return "RewardVideo";
            case 8:
                return "FullVideo";
            case 9:
                return "NativeDraw";
            case 10:
                if (i2 == 1) {
                    return "Interstitial";
                }
                if (i2 == 2) {
                    return "FullVideo";
                }
                return null;
        }
    }

    public static String a(int i, int i2, int i3) {
        switch (i) {
            case 1:
                return "Banner";
            case 2:
                return "Interstitial";
            case 3:
            case 4:
                return "Splash";
            case 5:
                return i3 == 1 ? "Native-模板渲染" : "Native";
            case 6:
            default:
                return null;
            case 7:
                return "RewardVideo";
            case 8:
                return "FullVideo";
            case 9:
                return "NativeDraw";
            case 10:
                if (i2 == 1) {
                    return "InterstitialFull—Interstitial";
                }
                if (i2 == 2) {
                    return "InterstitialFull—FullVideo";
                }
                return null;
        }
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? str : str.equalsIgnoreCase("gdt") ? "gdt" : str.equalsIgnoreCase("admob") ? "admob" : str.equalsIgnoreCase("pangle") ? "pangle" : str.equalsIgnoreCase("mintegral") ? "mintegral" : str.equalsIgnoreCase("unity") ? "unity" : str.equalsIgnoreCase("baidu") ? "baidu" : str.equalsIgnoreCase("ks") ? "ks" : str.equalsIgnoreCase("sigmob") ? "sigmob" : str.equalsIgnoreCase("klevin") ? "klevin" : str;
    }

    public static int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (str.startsWith("gdt")) {
            return 3;
        }
        if (str.startsWith("admob")) {
            return 2;
        }
        if (str.equalsIgnoreCase("pangle")) {
            return 1;
        }
        if (str.equalsIgnoreCase("mintegral")) {
            return 4;
        }
        if (str.equalsIgnoreCase("unity")) {
            return 5;
        }
        if (str.equalsIgnoreCase("baidu")) {
            return 6;
        }
        if (str.equalsIgnoreCase("ks")) {
            return 7;
        }
        if (str.equalsIgnoreCase("sigmob")) {
            return 8;
        }
        return str.equalsIgnoreCase("klevin") ? 9 : -1;
    }
}
