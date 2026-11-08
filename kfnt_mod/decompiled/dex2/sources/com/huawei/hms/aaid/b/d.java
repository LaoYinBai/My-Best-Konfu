package com.huawei.hms.aaid.b;

import android.text.TextUtils;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HEX;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    public static String a(String str, String str2) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes(C.UTF8_NAME);
            if (TextUtils.isEmpty(str2)) {
                str2 = AaidIdConstant.SIGNATURE_SHA256;
            }
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(bytes);
            return HEX.encodeHexString(messageDigest.digest(), false);
        } catch (UnsupportedEncodingException unused) {
            HMSLog.e("SHACoder", "trans failed .");
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            HMSLog.e("SHACoder", "encrypt failed .");
            return null;
        }
    }
}
