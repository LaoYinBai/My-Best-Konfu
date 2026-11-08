package com.huawei.hms.aaid.init;

import android.content.Context;
import android.content.pm.PackageManager;
import com.huawei.hms.aaid.b.c;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AutoInitHelper {
    public static boolean isAutoInitEnabled(Context context) {
        c cVar = new c(context, AaidIdConstant.PushClientSelfInfo.FILE_NAME);
        if (cVar.d(AaidIdConstant.PushClientSelfInfo.PUSH_KIT_AUTO_INIT_ENABLED)) {
            return cVar.a(AaidIdConstant.PushClientSelfInfo.PUSH_KIT_AUTO_INIT_ENABLED);
        }
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean(AaidIdConstant.PushClientSelfInfo.PUSH_KIT_AUTO_INIT_ENABLED);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static void doAutoInit(Context context) {
        try {
            if (isAutoInitEnabled(context)) {
                HMSLog.i("AutoInit", "Push init start");
                new Thread(new a(context)).start();
            }
        } catch (Exception e) {
            HMSLog.e("AutoInit", "Push init failed", e);
        }
    }

    public static void setAutoInitEnabled(Context context, boolean z) {
        c cVar = new c(context, AaidIdConstant.PushClientSelfInfo.FILE_NAME);
        boolean zA = cVar.a(AaidIdConstant.PushClientSelfInfo.PUSH_KIT_AUTO_INIT_ENABLED);
        cVar.a(AaidIdConstant.PushClientSelfInfo.PUSH_KIT_AUTO_INIT_ENABLED, z);
        if (!z || zA) {
            return;
        }
        doAutoInit(context);
    }
}
