package com.tachikoma.core.utility;

import android.content.res.Resources;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKFlexible {
    private static final float BASE_WIDTH = 750.0f;
    private static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;

    public static float px2rem(int i) {
        return i / (SCREEN_WIDTH / BASE_WIDTH);
    }

    public static int rem2px(float f) {
        return (int) (f * (SCREEN_WIDTH / BASE_WIDTH));
    }
}
