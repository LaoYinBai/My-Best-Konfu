package com.bytedance.msdk.adapter.init;

import android.content.Context;
import com.bytedance.msdk.adapter.util.Logger;
import com.qq.e.comm.managers.GDTADManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtAdsInit {
    public static void initGdtAdsSDK(Context context, String str) {
        try {
            Logger.e("TTMediationSDK_SDK_Init", "init GDT SDK start......");
            GDTADManager.getInstance().initWith(context, str);
            Logger.e("TTMediationSDK_SDK_Init", "init GDT SDK  finish......");
        } catch (Throwable th) {
            Logger.e("TTMediationSDK_SDK_Init", "init GDT SDK error......e:" + th.toString());
            th.printStackTrace();
        }
    }
}
