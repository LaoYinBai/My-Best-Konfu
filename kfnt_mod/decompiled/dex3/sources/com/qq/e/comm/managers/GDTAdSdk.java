package com.qq.e.comm.managers;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GDTAdSdk {
    public static IGDTAdManager getGDTAdManger() {
        return GDTADManager.getInstance();
    }

    public static void init(Context context, String str) {
        GDTADManager.getInstance().initWith(context, str);
    }
}
