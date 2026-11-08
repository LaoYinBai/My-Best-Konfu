package com.huawei.hms.support.api.push.c.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.aaid.encrypt.PushEncrypter;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    public static String a(Context context, String str) {
        String strDecrypter;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            strDecrypter = PushEncrypter.decrypter(context, new com.huawei.hms.support.api.push.c.a.a.c(context, str).a(AaidIdConstant.PushClientSelfInfo.TOKEN_INFO));
        } catch (Exception e) {
            HMSLog.e("PushDataEncrypterManager", "getSecureData" + e.getMessage());
            strDecrypter = "";
        }
        if (TextUtils.isEmpty(strDecrypter)) {
            HMSLog.d("PushDataEncrypterManager", "getSecureData not exist");
        }
        return strDecrypter;
    }

    public static boolean a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return new com.huawei.hms.support.api.push.c.a.a.c(context, str).a(AaidIdConstant.PushClientSelfInfo.TOKEN_INFO, PushEncrypter.encrypter(context, str2));
        } catch (Exception e) {
            HMSLog.e("PushDataEncrypterManager", "saveSecureData" + e.getMessage());
            return false;
        }
    }
}
