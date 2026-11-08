package com.huawei.updatesdk.a.a.c;

import android.text.TextUtils;
import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class g {
    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLEncoder.encode(str, C.UTF8_NAME).replace("+", "%20").replace("*", "%2A").replace("~", "%7E");
        } catch (UnsupportedEncodingException e) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("StringUtils", "encode2utf8 error", e);
            return null;
        }
    }

    public static boolean b(String str) {
        return str != null && str.trim().startsWith("{") && str.trim().endsWith("}");
    }

    public static String c(String str) throws NoSuchAlgorithmException {
        String strE = e(str);
        if (strE == null) {
            return null;
        }
        return strE.toLowerCase(Locale.getDefault());
    }

    public static byte[] d(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int iDigit = Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4);
            if (iDigit > 127) {
                iDigit -= 256;
            }
            bArr[i] = Byte.valueOf(String.valueOf(iDigit)).byteValue();
        }
        return bArr;
    }

    private static String e(String str) throws NoSuchAlgorithmException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(AaidIdConstant.SIGNATURE_SHA256);
            messageDigest.update(str.getBytes(C.UTF8_NAME));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : messageDigest.digest()) {
                stringBuffer.append(String.format(Locale.ROOT, "%02X", Byte.valueOf(b)));
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("StringUtils", "sha256EncryptStr error:Exception");
            return null;
        }
    }
}
