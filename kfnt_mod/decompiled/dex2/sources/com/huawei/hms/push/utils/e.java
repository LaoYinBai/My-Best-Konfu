package com.huawei.hms.push.utils;

import android.content.Context;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.aaid.init.AutoInitHelper;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    public static com.huawei.hms.push.constant.a a(Context context) {
        if (!b(context)) {
            if (AutoInitHelper.isAutoInitEnabled(context)) {
                HMSLog.e("TokenUtil", "Token not exist, try auto init");
                AutoInitHelper.doAutoInit(context);
                return com.huawei.hms.push.constant.a.ERROR_AUTO_INITIALIZING;
            }
            HMSLog.e("TokenUtil", "Token not exist");
            return com.huawei.hms.push.constant.a.ERROR_NO_TOKEN;
        }
        return com.huawei.hms.push.constant.a.SUCCESS;
    }

    private static boolean b(Context context) {
        return new com.huawei.hms.support.api.push.c.a.a.c(context, AaidIdConstant.PushClientSelfInfo.FILE_NAME).b(AaidIdConstant.PushClientSelfInfo.TOKEN_INFO);
    }
}
