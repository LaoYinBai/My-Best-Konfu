package com.cmic.sso.sdk.d;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.common.utility.StringEncryptUtils;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {
    public static boolean a(Context context, boolean z) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (keyStore.getCertificate("CMCC_SDK") != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (z) {
            return a(context);
        }
        return false;
    }

    private static boolean a(Context context) throws InterruptedException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                keyPairGenerator.initialize(new KeyGenParameterSpec.Builder("CMCC_SDK", 3).setDigests(StringEncryptUtils.SHA_256, "SHA-512").setEncryptionPaddings("PKCS1Padding").setKeySize(2048).build());
                Thread.sleep(1000L);
                keyPairGenerator.generateKeyPair();
                return true;
            } catch (Exception e) {
                c.a("KeystoreUtil", e.getMessage());
                return false;
            }
        }
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(1, 30);
        try {
            if (Build.VERSION.SDK_INT < 18) {
                return false;
            }
            KeyPairGeneratorSpec keyPairGeneratorSpecBuild = new KeyPairGeneratorSpec.Builder(context).setAlias("CMCC_SDK").setSubject(new X500Principal("CN=CMCC_SDK")).setSerialNumber(BigInteger.TEN).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
            KeyPairGenerator keyPairGenerator2 = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            keyPairGenerator2.initialize(keyPairGeneratorSpecBuild);
            Thread.sleep(1000L);
            keyPairGenerator2.generateKeyPair();
            return true;
        } catch (Exception e2) {
            c.a("KeystoreUtil", e2.getMessage());
            return false;
        }
    }

    private static byte[] a() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    static String a(Context context, String str) {
        byte[] bArrB = b(context);
        if (bArrB != null) {
            return a.a(bArrB, str);
        }
        return null;
    }

    private static synchronized byte[] b(Context context) {
        byte[] bArrDoFinal;
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (TextUtils.isEmpty(b())) {
                if (!a(context, false)) {
                    return null;
                }
                bArrDoFinal = a();
                PublicKey publicKey = keyStore.getCertificate("CMCC_SDK").getPublicKey();
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(1, publicKey);
                a(Base64.encodeToString(cipher.doFinal(bArrDoFinal), 0));
            } else {
                String strB = b();
                if (TextUtils.isEmpty(strB)) {
                    bArrDoFinal = null;
                } else {
                    byte[] bArrDecode = Base64.decode(strB, 0);
                    PrivateKey privateKey = (PrivateKey) keyStore.getKey("CMCC_SDK", null);
                    if (privateKey == null) {
                        return null;
                    }
                    Cipher cipher2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                    cipher2.init(2, privateKey);
                    bArrDoFinal = cipher2.doFinal(bArrDecode);
                }
            }
            return bArrDoFinal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static String b(Context context, String str) {
        byte[] bArrB;
        if (TextUtils.isEmpty(str) || (bArrB = b(context)) == null) {
            return null;
        }
        return a.b(bArrB, str);
    }

    private static void a(String str) {
        k.a("AES_KEY", str);
    }

    private static String b() {
        return k.b("AES_KEY", "");
    }
}
