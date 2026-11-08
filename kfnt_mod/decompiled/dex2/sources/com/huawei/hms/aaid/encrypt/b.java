package com.huawei.hms.aaid.encrypt;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    public static String a(Context context, String str) {
        String strDecrypter;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            strDecrypter = PushEncrypter.decrypter(context, new com.huawei.hms.aaid.b.c(context, str).b(AaidIdConstant.PushClientSelfInfo.TOKEN_INFO));
        } catch (Exception e) {
            HMSLog.e("PushDataEncrypterManager", "getSecureData" + e.getMessage());
            strDecrypter = "";
        }
        if (TextUtils.isEmpty(strDecrypter)) {
            HMSLog.d("PushDataEncrypterManager", "getSecureData not exist");
        }
        return strDecrypter;
    }

    public static void b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            new com.huawei.hms.aaid.b.c(context, str).e(AaidIdConstant.PushClientSelfInfo.TOKEN_INFO);
        } catch (Exception e) {
            HMSLog.e("PushDataEncrypterManager", "removeSecureData" + e.getMessage());
        }
    }

    public static boolean a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return new com.huawei.hms.aaid.b.c(context, str).a(AaidIdConstant.PushClientSelfInfo.TOKEN_INFO, PushEncrypter.encrypter(context, str2));
        } catch (Exception e) {
            HMSLog.e("PushDataEncrypterManager", "saveSecureData" + e.getMessage());
            return false;
        }
    }
}
