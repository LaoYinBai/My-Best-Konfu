package com.huawei.a.a.b.a;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g implements f {
    private PublicKey a(byte[] bArr) {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr));
    }

    private byte[] a(byte[] bArr, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String str;
        String str2;
        try {
            if (publicKey == null) {
                throw new UnsupportedEncodingException("The loaded public key is null");
            }
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING");
            cipher.init(1, publicKey);
            return cipher.doFinal(bArr);
        } catch (UnsupportedEncodingException unused) {
            str = "Rsa";
            str2 = "rsaEncrypt(): getBytes - Unsupported coding format!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return new byte[0];
        } catch (InvalidKeyException unused2) {
            str = "Rsa";
            str2 = "rsaEncrypt(): init - Invalid key!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return new byte[0];
        } catch (NoSuchAlgorithmException unused3) {
            str = "Rsa";
            str2 = "rsaEncrypt(): getInstance - No such algorithm,transformation";
            com.huawei.a.a.b.e.a.c(str, str2);
            return new byte[0];
        } catch (BadPaddingException unused4) {
            str = "Rsa";
            str2 = "rsaEncrypt():False filling parameters!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return new byte[0];
        } catch (IllegalBlockSizeException unused5) {
            str = "Rsa";
            str2 = "rsaEncrypt(): doFinal - The provided block is not filled with";
            com.huawei.a.a.b.e.a.c(str, str2);
            return new byte[0];
        } catch (NoSuchPaddingException unused6) {
            str = "Rsa";
            str2 = "rsaEncrypt():  No such filling parameters ";
            com.huawei.a.a.b.e.a.c(str, str2);
            return new byte[0];
        }
    }

    @Override // com.huawei.a.a.b.a.f
    public String a(String str, String str2) {
        com.huawei.a.a.b.e.a.b("Rsa", "No RSA decryption method");
        return "";
    }

    @Override // com.huawei.a.a.b.a.f
    public String a(String str, byte[] bArr) {
        String str2;
        String str3;
        if (bArr == null || bArr.length == 0) {
            com.huawei.a.a.b.e.a.c("Rsa", "encrypt: content is empty or null");
            return "";
        }
        try {
            return d.a(a(bArr, a(d.a(str))));
        } catch (NoSuchAlgorithmException unused) {
            str2 = "Rsa";
            str3 = "encrypt(): getInstance - No such algorithm,transformation";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return "";
        } catch (InvalidKeySpecException unused2) {
            str2 = "Rsa";
            str3 = "encrypt(): Invalid key specification";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return "";
        }
    }

    @Override // com.huawei.a.a.b.a.f
    public String b(String str, String str2) {
        try {
            return a(str, str2.getBytes(C.UTF8_NAME));
        } catch (UnsupportedEncodingException unused) {
            com.huawei.a.a.b.e.a.b("Rsa", "Unsupported encoding exception,utf-8");
            return "";
        }
    }
}
