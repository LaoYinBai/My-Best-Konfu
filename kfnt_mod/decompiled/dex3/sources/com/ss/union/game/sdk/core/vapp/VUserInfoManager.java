package com.ss.union.game.sdk.core.vapp;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VUserInfoManager {
    public static void refreshDeviceVUserInfo() {
        try {
            c.e();
        } catch (Throwable th) {
            VLog.e("refreshDeviceVUserInfo error " + Log.getStackTraceString(th));
        }
    }

    public static void refreshAccountVUserInfo() {
        try {
            c.f();
        } catch (Throwable th) {
            VLog.e("refreshAccountVUserInfo error " + Log.getStackTraceString(th));
        }
    }
}
