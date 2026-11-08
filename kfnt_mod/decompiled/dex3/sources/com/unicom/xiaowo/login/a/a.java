package com.unicom.xiaowo.login.a;

import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a {
    private static final char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static byte[] b = "0000000000000000".getBytes();

    static {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public static String a() {
        String str = "";
        try {
            String string = UUID.randomUUID().toString();
            if (TextUtils.isEmpty(string)) {
                return "";
            }
            String strReplace = string.replace(Constants.ACCEPT_TIME_SEPARATOR_SERVER, "");
            try {
                return strReplace.length() >= 16 ? strReplace.substring(0, 16) : strReplace;
            } catch (Exception e) {
                e = e;
                str = strReplace;
                e.printStackTrace();
                return str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static String a(String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            return a(cipher.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String a(byte[] bArr) {
        String str;
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer((bArr.length * 3) / 2);
        int i = length - 3;
        int i2 = 0;
        loop0: while (true) {
            int i3 = 0;
            while (i2 <= i) {
                int i4 = ((bArr[i2] & UByte.MAX_VALUE) << 16) | ((bArr[i2 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i2 + 2] & UByte.MAX_VALUE);
                stringBuffer.append(a[(i4 >> 18) & 63]);
                stringBuffer.append(a[(i4 >> 12) & 63]);
                stringBuffer.append(a[(i4 >> 6) & 63]);
                stringBuffer.append(a[i4 & 63]);
                i2 += 3;
                int i5 = i3 + 1;
                if (i3 >= 14) {
                    break;
                }
                i3 = i5;
            }
            stringBuffer.append(" ");
        }
        int i6 = length + 0;
        if (i2 != i6 - 2) {
            if (i2 == i6 - 1) {
                int i7 = (bArr[i2] & UByte.MAX_VALUE) << 16;
                stringBuffer.append(a[(i7 >> 18) & 63]);
                stringBuffer.append(a[(i7 >> 12) & 63]);
                str = "==";
            }
            return stringBuffer.toString();
        }
        int i8 = ((bArr[i2 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i2] & UByte.MAX_VALUE) << 16);
        stringBuffer.append(a[(i8 >> 18) & 63]);
        stringBuffer.append(a[(i8 >> 12) & 63]);
        stringBuffer.append(a[(i8 >> 6) & 63]);
        str = "=";
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    private static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i << 1;
            int iDigit = Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4);
            if (iDigit > 127) {
                iDigit -= 256;
            }
            bArr[i] = (byte) iDigit;
        }
        return bArr;
    }

    public static String b(String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(b);
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, ivParameterSpec);
            return new String(cipher.doFinal(a(str)));
        } catch (Exception unused) {
            return null;
        }
    }
}
