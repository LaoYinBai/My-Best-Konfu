package com.umeng.commonsdk.statistics.common;

import com.sigmob.sdk.common.Constants;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.tachikoma.core.utility.UriUtil;
import com.umeng.commonsdk.proguard.az;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DataHelper {
    private static final byte[] iv = {10, 1, 11, 5, 4, az.m, 7, 9, 23, 3, 1, 6, 8, 12, az.k, 91};
    public static long ENVELOPE_EXTRA_LENGTH = DownloadConstants.KB;
    public static long ENVELOPE_ENTITY_RAW_LENGTH_MAX = 2097152;
    public static long ENVELOPE_LENGTH_MAX = 204800;

    public static boolean largeThanMaxSize(long j, long j2) {
        return j > j2;
    }

    public static byte[] reverseHexString(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[length / 2];
        int i = 0;
        while (i < length) {
            int i2 = i + 2;
            bArr[i / 2] = (byte) Integer.valueOf(str.substring(i, i2), 16).intValue();
            i = i2;
        }
        return bArr;
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(b)));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    public static byte[] hash(byte[] bArr) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(bArr);
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(bArr);
    }

    public static int random(int i, String str) throws Throwable {
        if (new Random().nextFloat() < 0.001d) {
            int i2 = 0;
            if (str == null) {
                MLog.e("--->", "null signature..");
            }
            try {
                i2 = Integer.parseInt(str.substring(9, 11), 16);
            } catch (Exception unused) {
            }
            return (i2 | 128) * 1000;
        }
        int iNextInt = new Random().nextInt(i);
        if (iNextInt > 255000 || iNextInt < 128000) {
            return iNextInt;
        }
        return 127000;
    }

    public static String convertExceptionToString(Throwable th) throws IOException {
        if (th == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                cause.printStackTrace(printWriter);
            }
            String string = stringWriter.toString();
            try {
                printWriter.close();
                stringWriter.close();
                return string;
            } catch (Exception unused) {
                return string;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String assembleURL(String str) {
        return UriUtil.HTTPS_PREFIX + str + "/unify_logs";
    }

    public static String assembleStatelessURL(String str) {
        return UriUtil.HTTPS_PREFIX + str;
    }

    public static String encryptBySHA1(String str) throws NoSuchAlgorithmException {
        byte[] bytes = str.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(bytes);
            return bytes2Hex(messageDigest.digest());
        } catch (Exception unused) {
            return null;
        }
    }

    static String bytes2Hex(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                str = str + Constants.FAIL;
            }
            str = str + hexString;
        }
        return str;
    }
}
