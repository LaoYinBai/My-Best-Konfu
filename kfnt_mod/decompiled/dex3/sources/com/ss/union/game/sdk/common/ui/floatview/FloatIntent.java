package com.ss.union.game.sdk.common.ui.floatview;

import android.app.Activity;
import android.os.Bundle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FloatIntent {
    public static final int MODE_SINGLE_INSTANCE = 1;
    public static final int MODE_STANDARD = 2;
    public Activity activity;
    public Bundle bundle;
    public int mode = 1;
    public Class<? extends BaseFloatView> targetClass;
    public int x;
    public int y;

    public FloatIntent(Activity activity, Class<? extends BaseFloatView> cls) {
        this.activity = activity;
        this.targetClass = cls;
    }

    public FloatIntent params(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

    public FloatIntent setX(int i) {
        this.x = i;
        return this;
    }

    public FloatIntent setY(int i) {
        this.y = i;
        return this;
    }
}
