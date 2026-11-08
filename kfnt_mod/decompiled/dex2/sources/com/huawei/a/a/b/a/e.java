package com.huawei.a.a.b.a;

import android.text.TextUtils;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements f {
    @Override // com.huawei.a.a.b.a.f
    public String a(String str, String str2) {
        com.huawei.a.a.b.e.a.b("HmacSHA256Crypt", "No HMAC_SHA256 decryption method");
        return "";
    }

    @Override // com.huawei.a.a.b.a.f
    public String a(String str, byte[] bArr) throws NoSuchAlgorithmException, InvalidKeyException {
        String str2;
        String str3;
        if (bArr == null || bArr.length == 0) {
            com.huawei.a.a.b.e.a.c("HmacSHA256Crypt", "encrypt: content is empty or null");
            return "";
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(Charset.forName(C.UTF8_NAME)), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            return d.a(mac.doFinal(bArr));
        } catch (InvalidKeyException unused) {
            str2 = "HmacSHA256Crypt";
            str3 = "Exception has happened when digest2byte,From Invalid key!";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return "";
        } catch (NoSuchAlgorithmException unused2) {
            str2 = "HmacSHA256Crypt";
            str3 = "When digest2byte executed Exception has happened!From Algorithm error !";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return "";
        }
    }

    @Override // com.huawei.a.a.b.a.f
    public String b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            com.huawei.a.a.b.e.a.c("HmacSHA256Crypt", "key or content is empty");
            return "";
        }
        try {
            return a(str, str2.getBytes(C.UTF8_NAME));
        } catch (UnsupportedEncodingException unused) {
            com.huawei.a.a.b.e.a.b("HmacSHA256Crypt", "Unsupported encoding exception,utf-8");
            return "";
        }
    }
}
