package com.bytedance.msdk.adapter.init;

import android.app.Activity;
import com.bytedance.msdk.adapter.util.Logger;
import com.unity3d.ads.UnityAds;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UnitySdkInit {
    public static void initUnitySDK(Activity activity, String str) {
        try {
            Logger.i("TTMediationSDK_SDK_Init", "init UnitySDK. start....appId=" + str);
            UnityAds.initialize(activity, str, Logger.isDebug());
            Logger.i("TTMediationSDK_SDK_Init", "init UnitySDK. finish....");
        } catch (Throwable th) {
            Logger.i("TTMediationSDK_SDK_Init", "init UnitySDK. fail....error msg = " + th.toString());
        }
    }
}
