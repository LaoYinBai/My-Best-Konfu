package com.bytedance.pangle;

import android.os.Build;
import com.bytedance.pangle.flipped.FlippedV2Impl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {
    public static void a() {
        com.bytedance.pangle.flipped.c aVar;
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 30 || (Build.VERSION.SDK_INT == 29 && Build.VERSION.PREVIEW_SDK_INT > 0)) {
            aVar = new FlippedV2Impl();
        } else {
            if (Build.VERSION.SDK_INT >= 28 || (Build.VERSION.SDK_INT == 27 && Build.VERSION.PREVIEW_SDK_INT > 0)) {
                z = true;
            }
            if (z) {
                aVar = new com.bytedance.pangle.flipped.b();
            } else {
                aVar = new com.bytedance.pangle.flipped.a();
            }
        }
        aVar.invokeHiddenApiRestrictions();
    }
}
