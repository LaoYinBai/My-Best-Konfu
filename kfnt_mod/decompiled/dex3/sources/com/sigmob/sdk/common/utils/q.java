package com.sigmob.sdk.common.utils;

import android.view.MotionEvent;
import android.view.View;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class q implements Serializable {
    private final int a;
    private final int b;

    public q(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public static q a(View view, MotionEvent motionEvent) {
        if (view == null) {
            return null;
        }
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new q(rawX - iArr[0], rawY - iArr[1]);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}
