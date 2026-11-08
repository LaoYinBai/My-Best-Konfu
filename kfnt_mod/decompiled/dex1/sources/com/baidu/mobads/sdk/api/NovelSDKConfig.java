package com.baidu.mobads.sdk.api;

import android.app.Application;
import com.baidu.mobads.sdk.internal.af;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NovelSDKConfig {
    public static boolean isInitNovelSDK() {
        return af.e();
    }

    public static void attachBaseContext(Application application, String str, String str2) {
        af.a(application, str, str2);
    }
}
