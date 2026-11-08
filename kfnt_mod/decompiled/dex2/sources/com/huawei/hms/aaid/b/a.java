package com.huawei.hms.aaid.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HEX;
import com.huawei.hms.utils.Util;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static String a(String str) {
        return b(str + a(32));
    }

    private static String b(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String strA = d.a(str, AaidIdConstant.SIGNATURE_SHA256);
        if (TextUtils.isEmpty(strA) || strA.length() != 64) {
            return UUID.randomUUID().toString();
        }
        return c(strA).toString();
    }

    private static String a(int i) {
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return HEX.encodeHexString(bArr, false);
    }

    private static UUID c(String str) {
        return new UUID(d(str.substring(0, 32)), d(str.substring(32, 64)));
    }

    private static long d(String str) {
        String str2 = "0x" + str.substring(0, 8);
        return ((((Long.decode("0x" + str.substring(8, 16)).longValue() | (Long.decode(str2).longValue() << 16)) << 16) | Long.decode("0x" + str.substring(16, 24)).longValue()) << 16) | Long.decode("0x" + str.substring(24, 32)).longValue();
    }

    public static String a(Context context) {
        if (context == null) {
            HMSLog.e("AaidUtils", "getSign failed because context is null.");
            return null;
        }
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(64)) {
            if (packageInfo.packageName.equals(context.getPackageName())) {
                return packageInfo.signatures[0].toCharsString();
            }
        }
        return null;
    }

    public static TokenReq a(String str, String str2, Context context) {
        TokenReq tokenReq = new TokenReq();
        tokenReq.setPackageName(context.getPackageName());
        if (TextUtils.isEmpty(str)) {
            tokenReq.setAppId(Util.getAppId(context));
        }
        if (TextUtils.isEmpty(str2)) {
            tokenReq.setScope("HCM");
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(AaidIdConstant.PushClientSelfInfo.FILE_NAME, 0);
        if (!a(AaidIdConstant.PushClientSelfInfo.HAS_REQUEST_AGREEMENT, sharedPreferences)) {
            tokenReq.setFirstTime(true);
            sharedPreferences.edit().putBoolean(AaidIdConstant.PushClientSelfInfo.HAS_REQUEST_AGREEMENT, true).commit();
        } else {
            tokenReq.setFirstTime(false);
        }
        return tokenReq;
    }

    public static synchronized String b(Context context) {
        String strA;
        c cVar = new c(context, "aaid");
        if (cVar.d("aaid")) {
            strA = cVar.b("aaid");
        } else {
            strA = a(context.getPackageName() + a(context));
            cVar.a("aaid", strA);
            cVar.a(AaidIdConstant.CREATE_TIME, Long.valueOf(System.currentTimeMillis()));
        }
        return strA;
    }

    private static boolean a(String str, SharedPreferences sharedPreferences) {
        return sharedPreferences != null && sharedPreferences.getBoolean(str, false);
    }
}
