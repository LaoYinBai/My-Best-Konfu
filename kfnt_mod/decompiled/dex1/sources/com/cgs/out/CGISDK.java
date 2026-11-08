package com.cgs.out;

import android.app.Activity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface CGISDK {
    public static final int REGION_MAINLAND = 100;
    public static final int REGION_OVERSEAS = 200;
    public static final int REGION_UNSET = -255;

    void callbacksLogger(Activity activity);

    void loggerAdClose(long j);

    void loggerAdStart(long j, long j2);

    void reportNow(String str);

    void reportNow(String str, boolean z);
}
