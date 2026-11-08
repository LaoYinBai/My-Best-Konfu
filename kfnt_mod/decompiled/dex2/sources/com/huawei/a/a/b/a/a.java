package com.huawei.a.a.b.a;

import android.text.TextUtils;
import android.util.Pair;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements f {
    private static final Charset a = Charset.forName(C.UTF8_NAME);

    private Pair<byte[], byte[]> a(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 32) {
            return new Pair<>(new byte[0], new byte[0]);
        }
        return new Pair<>(d.a(str.substring(0, 32)), d.a(str.substring(32)));
    }

    private String a(String str, byte[] bArr, byte[] bArr2) {
        return (bArr2 == null || str == null || bArr == null || bArr.length == 0) ? "" : a(bArr, d.a(b(str, bArr, bArr2)));
    }

    private String a(byte[] bArr, String str) {
        if (bArr == null || bArr.length == 0) {
            return str;
        }
        if (str == null) {
            str = "";
        }
        return d.a(bArr).concat(str);
    }

    private String a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        String str;
        String str2;
        if (bArr.length == 0 || bArr2.length == 0 || bArr3.length == 0) {
            com.huawei.a.a.b.e.a.c("Aes", "decrypt: parameter exception");
            return "";
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
            return new String(cipher.doFinal(bArr3), a);
        } catch (InvalidAlgorithmParameterException unused) {
            str = "Aes";
            str2 = "aesDecrypt(): init - Invalid algorithm parameters !";
            com.huawei.a.a.b.e.a.c(str, str2);
            return "";
        } catch (InvalidKeyException unused2) {
            str = "Aes";
            str2 = "aesDecrypt(): init - Invalid key!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return "";
        } catch (NoSuchAlgorithmException unused3) {
            str = "Aes";
            str2 = "aesDecrypt(): getInstance - No such algorithm,transformation";
            com.huawei.a.a.b.e.a.c(str, str2);
            return "";
        } catch (BadPaddingException unused4) {
            str = "Aes";
            str2 = "aesDecrypt(): False filling parameters!";
            com.huawei.a.a.b.e.a.c(str, str2);
            return "";
        } catch (IllegalBlockSizeException unused5) {
            str = "Aes";
            str2 = "aesDecrypt(): doFinal - The provided block is not filled with";
            com.huawei.a.a.b.e.a.c(str, str2);
            return "";
        } catch (NoSuchPaddingException unused6) {
            str = "Aes";
            str2 = "aesDecrypt():  No such filling parameters ";
            com.huawei.a.a.b.e.a.c(str, str2);
            return "";
        }
    }

    private byte[] b(String str, byte[] bArr, byte[] bArr2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str) || bArr == null || bArr.length == 0) {
            return new byte[0];
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, new SecretKeySpec(d.a(str), "AES"), new IvParameterSpec(bArr));
            return cipher.doFinal(bArr2);
        } catch (InvalidAlgorithmParameterException unused) {
            str2 = "Aes";
            str3 = "aesEncrypt(): init - Invalid algorithm parameters !";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return new byte[0];
        } catch (InvalidKeyException unused2) {
            str2 = "Aes";
            str3 = "aesEncrypt(): init - Invalid key!";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return new byte[0];
        } catch (NoSuchAlgorithmException unused3) {
            str2 = "Aes";
            str3 = "aesEncrypt(): getInstance - No such algorithm,transformation";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return new byte[0];
        } catch (BadPaddingException unused4) {
            str2 = "Aes";
            str3 = "aesEncrypt(): False filling parameters!";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return new byte[0];
        } catch (IllegalBlockSizeException unused5) {
            str2 = "Aes";
            str3 = "aesEncrypt(): doFinal - The provided block is not filled with";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return new byte[0];
        } catch (NoSuchPaddingException unused6) {
            str2 = "Aes";
            str3 = "aesEncrypt(): No such filling parameters ";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return new byte[0];
        }
    }

    @Override // com.huawei.a.a.b.a.f
    public String a(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            com.huawei.a.a.b.e.a.c("Aes", "decrypt: content or key is empty");
            return "";
        }
        byte[] bArrA = d.a(str);
        Pair<byte[], byte[]> pairA = a(str2);
        return a(bArrA, (byte[]) pairA.first, (byte[]) pairA.second);
    }

    @Override // com.huawei.a.a.b.a.f
    public String a(String str, byte[] bArr) {
        return a(str, d.a(), bArr);
    }

    @Override // com.huawei.a.a.b.a.f
    public String b(String str, String str2) {
        try {
            return a(str, str2.getBytes(C.UTF8_NAME));
        } catch (UnsupportedEncodingException unused) {
            com.huawei.a.a.b.e.a.b("Aes", "Unsupported Encoding Exception : utf-8");
            return "";
        }
    }
}
