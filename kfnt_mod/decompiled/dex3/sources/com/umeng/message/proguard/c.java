package com.umeng.message.proguard;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static byte[] a = "uLi4/f4+Pb39.T19".getBytes();
    private static byte[] b = ("nmeug.f9/Om+L823").getBytes();

    public static String a(String... strArr) throws Exception {
        String str;
        String str2;
        String str3 = null;
        if (strArr.length == 2) {
            str2 = strArr[0];
            str = strArr[1];
        } else if (strArr.length == 3) {
            String str4 = strArr[0];
            String str5 = strArr[1];
            str3 = strArr[2];
            str = str5;
            str2 = str4;
        } else {
            str = null;
            str2 = null;
        }
        if (str3 != null && str3.length() == 16) {
            a = str3.getBytes();
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(a, "AES"), new IvParameterSpec(b));
        return d.d(cipher.doFinal(str2.getBytes(str)));
    }

    public static String a(String str, String str2) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(a, "AES"), new IvParameterSpec(b));
        return new String(cipher.doFinal(d.b(str)), str2);
    }

    public static String a(String str) throws Exception {
        if ("uLi4/f4+Pb39.T19".length() == 16) {
            a = "uLi4/f4+Pb39.T19".getBytes();
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, new SecretKeySpec(a, "AES"), new IvParameterSpec(b));
        return d.d(cipher.doFinal(str.getBytes("UTF-8")));
    }
}
