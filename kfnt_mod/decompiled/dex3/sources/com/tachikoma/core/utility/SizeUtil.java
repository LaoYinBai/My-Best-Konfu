package com.tachikoma.core.utility;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SizeUtil {
    public static int dp2Px(float f) {
        return (int) ((TKContextUtil.getContext().getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int dp2Px(int i) {
        return (int) ((TKContextUtil.getContext().getResources().getDisplayMetrics().density * i) + 0.5f);
    }

    public static int px2Dp(float f) {
        return (int) (f / TKContextUtil.getContext().getResources().getDisplayMetrics().density);
    }

    public static int px2Dp(int i) {
        return (int) (i / TKContextUtil.getContext().getResources().getDisplayMetrics().density);
    }

    public static float px2DpAsFloat(float f) {
        return (f * 1.0f) / TKContextUtil.getContext().getResources().getDisplayMetrics().density;
    }
}
