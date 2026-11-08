package com.meizu.cloud.pushsdk.util;

import android.text.TextUtils;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static final Charset a = Charset.forName(C.UTF8_NAME);

    public static String a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                return null;
            }
            return new String(a(a(str), b(str2)), a);
        } catch (Exception e) {
            DebugLogger.e("RSAUtils", "decrypt " + e.getMessage());
            return null;
        }
    }

    private static RSAPublicKey a(String str) {
        String str2;
        StringBuilder sb;
        String message;
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(b(str)));
        } catch (NoSuchAlgorithmException e) {
            str2 = "RSAUtils";
            sb = new StringBuilder();
            sb.append("loadPublicKey NoSuchAlgorithmException ");
            message = e.getMessage();
            sb.append(message);
            DebugLogger.e(str2, sb.toString());
            return null;
        } catch (InvalidKeySpecException e2) {
            str2 = "RSAUtils";
            sb = new StringBuilder();
            sb.append("loadPublicKey InvalidKeySpecException ");
            message = e2.getMessage();
            sb.append(message);
            DebugLogger.e(str2, sb.toString());
            return null;
        }
    }

    private static byte[] a(PublicKey publicKey, byte[] bArr) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicKey);
        return cipher.doFinal(bArr);
    }

    private static byte[] b(String str) {
        return com.meizu.cloud.pushsdk.b.g.a.a(str);
    }
}
