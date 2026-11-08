package com.ss.android.token;

import android.os.Build;
import android.util.Base64;
import com.ss.union.game.sdk.feedback.picture.select.d;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class RSAUtils {
    private static String PRIVATE_KEY = "MIICXAIBAAKBgQCyEFRPAsPPveGVFOB1pdduJpiNMghV1vlADTtncJxefJ9M2vg9\nT1ghyVYg4HlYeT8AxOv926Ex83tdEiiHhf9o43s05REzFmL+ew+lp3HZPDdTeWuH\nNX46aUcODhnXTZUm00I4+0BJrqbj34pRlKXpJAfLmsp5CsAEN7qMPpg2QwIDAQAB\nAoGBAKXLOITcwS+D0C+8YYaJZP5F55LIPCSY5jkyWNZmbuI3TSgFVk/XueqziYNR\nHws20WfI8ybKQdu9pmaxSpVtObKFNh4QOs3utDmNCWj5VZQQ1CrKjotchsCUyJdG\nfG5IcuORAoGGfTGi2OZ2HBEYnbyS9qkcm7iIeHoPi6hiOl7ZAkEA2hpE+g9U85R/\nSt20XAnV3ub01r4aErrm1o4zgpNSrXDrxt8vcgsRME+AzNVN2gkn0FNWgBxdXvUu\ndi6Ke9rz1QJBANEBCUOwB5708PefN4ZJ9N0p/iyzXf6nXU7hKWL0RHngLMBVpu4h\ncqPPsqc68qsIIeuYC/T09ibiyr9e6G8txbcCQAW9nUUrtCO5K+SPk1i81YlJcPZa\nCJunmb3Z/lcYg5uXFk2kwD3zNp+b6Br8HlF33jloiCVVGcwPDgsETJbX+4UCQAnx\nkK1FvLcVVDmTu6KJ9jpnew5JYllxQQE1uTBaCr/5qGdmK9D8imW8xfUq2szEUEjT\nLQ50cRmchKTiMRDJDrUCQB9x1+tXAaDMiV/3mzHFqCafIMAASo51cLfuV7XS+r/2\n53L/BRf/4c2m0Zq9pJ5kuKoH//WIiXgNzYeHnqgEayk=";
    private static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyEFRPAsPPveGVFOB1pdduJpiN\nMghV1vlADTtncJxefJ9M2vg9T1ghyVYg4HlYeT8AxOv926Ex83tdEiiHhf9o43s0\n5REzFmL+ew+lp3HZPDdTeWuHNX46aUcODhnXTZUm00I4+0BJrqbj34pRlKXpJAfL\nmsp5CsAEN7qMPpg2QwIDAQAB";
    private static String RSA = "RSA";
    private static String RSA_PKCS1 = "RSA/ECB/PKCS1Padding";

    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(d.d);
    }

    public static KeyPair generateRSAKeyPair(int i) throws NoSuchAlgorithmException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(i);
            return keyPairGenerator.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encryptData(byte[] bArr, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance(RSA_PKCS1);
            cipher.init(1, publicKey);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decryptData(byte[] bArr, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance(RSA_PKCS1);
            cipher.init(2, privateKey);
            return cipher.doFinal(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static PublicKey getPublicKey(byte[] bArr) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(bArr));
    }

    public static PrivateKey getPrivateKey(byte[] bArr) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }

    public static PublicKey getPublicKey(String str, String str2) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return KeyFactory.getInstance(RSA).generatePublic(new RSAPublicKeySpec(new BigInteger(str), new BigInteger(str2)));
    }

    public static PrivateKey getPrivateKey(String str, String str2) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return KeyFactory.getInstance(RSA).generatePrivate(new RSAPublicKeySpec(new BigInteger(str), new BigInteger(str2)));
    }

    public static PublicKey loadPublicKey(String str) throws Exception {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NullPointerException unused) {
            throw new Exception("公钥数据为空");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("公钥非法");
        }
    }

    public static PrivateKey loadPrivateKey(String str) throws Exception {
        KeyFactory keyFactory;
        try {
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(str, 0));
            if (Build.VERSION.SDK_INT >= 28) {
                keyFactory = KeyFactory.getInstance("RSA");
            } else {
                keyFactory = KeyFactory.getInstance("RSA", "BC");
            }
            return (RSAPrivateKey) keyFactory.generatePrivate(pKCS8EncodedKeySpec);
        } catch (NullPointerException unused) {
            throw new Exception("私钥数据为空");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("私钥非法");
        }
    }

    public static PrivateKey loadPrivateKey() throws Exception {
        return loadPrivateKey(PRIVATE_KEY);
    }

    public static PublicKey loadPublicKey(InputStream inputStream) throws Exception {
        try {
            return loadPublicKey(readKey(inputStream));
        } catch (IOException unused) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException unused2) {
            throw new Exception("公钥输入流为空");
        }
    }

    public static PrivateKey loadPrivateKey(InputStream inputStream) throws Exception {
        try {
            return loadPrivateKey(readKey(inputStream));
        } catch (IOException unused) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException unused2) {
            throw new Exception("私钥输入流为空");
        }
    }

    private static String readKey(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                if (line.charAt(0) != '-') {
                    sb.append(line);
                    sb.append('\r');
                }
            } else {
                return sb.toString();
            }
        }
    }

    public static void printPublicKeyInfo(PublicKey publicKey) {
        RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
        System.out.println("----------RSAPublicKey----------");
        System.out.println("Modulus.length=" + rSAPublicKey.getModulus().bitLength());
        System.out.println("Modulus=" + rSAPublicKey.getModulus().toString());
        System.out.println("PublicExponent.length=" + rSAPublicKey.getPublicExponent().bitLength());
        System.out.println("PublicExponent=" + rSAPublicKey.getPublicExponent().toString());
    }

    public static void printPrivateKeyInfo(PrivateKey privateKey) {
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) privateKey;
        System.out.println("----------RSAPrivateKey ----------");
        System.out.println("Modulus.length=" + rSAPrivateKey.getModulus().bitLength());
        System.out.println("Modulus=" + rSAPrivateKey.getModulus().toString());
        System.out.println("PrivateExponent.length=" + rSAPrivateKey.getPrivateExponent().bitLength());
        System.out.println("PrivatecExponent=" + rSAPrivateKey.getPrivateExponent().toString());
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String byteArrayToHex(byte[] bArr) {
        char[] charArray = "0123456789abcdef".toCharArray();
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & UByte.MAX_VALUE;
            int i3 = i * 2;
            cArr[i3] = charArray[i2 >>> 4];
            cArr[i3 + 1] = charArray[i2 & 15];
        }
        return new String(cArr);
    }
}
