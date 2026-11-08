package com.qq.e.comm.util;

import android.util.Base64;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private PublicKey a;
    private final boolean b;

    private static final class b {
        public static final a a = new a();
    }

    private a() {
        boolean z;
        try {
            this.a = a("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKta2b5Vw5YkWHCAj4rJCwS227\r/35FZ29e4I6pS2B8zSq2RgBpXUuMg7oZF1Qt3x0iyg8PeyblyNeCRB6gIMehFThe\r1Y7m1FaQyaZp+CJYOTLM4/THKp9UndrEgJ/5a83vP1375YCV2lMvWARrNlBep4RN\rnESUJhQz58Gr/F39TwIDAQAB");
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        this.b = z;
    }

    public static a a() {
        return b.a;
    }

    private PublicKey a(String str) throws Exception {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NullPointerException unused) {
            throw new Exception("公钥数据为空");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("公钥非法");
        }
    }

    public boolean a(String str, String str2) {
        String strTrim;
        if (StringUtil.isEmpty(str2)) {
            return false;
        }
        if (!this.b) {
            return true;
        }
        if (this.a != null) {
            byte[] bArrDecode = Base64.decode(str, 0);
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(2, this.a);
                strTrim = new String(cipher.doFinal(bArrDecode), "UTF-8").trim();
            } catch (Throwable unused) {
                GDTLogger.d("ErrorWhileVerifySigNature");
            }
        } else {
            strTrim = null;
        }
        boolean zEquals = str2.equals(strTrim);
        GDTLogger.d("Verify Result" + zEquals + "src=" + str2 + " & target=" + strTrim);
        return zEquals;
    }
}
