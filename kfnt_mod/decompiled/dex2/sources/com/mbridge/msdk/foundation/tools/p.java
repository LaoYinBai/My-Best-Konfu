package com.mbridge.msdk.foundation.tools;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: SameFileMD5.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class p {
    protected static char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest b = null;
    private static String c = "SameFileMD5";

    static {
        b = null;
        try {
            b = MessageDigest.getInstance(SameMD5.TAG);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(p.class.getName() + "初始化失败，MessageDigest不支持MD5Util.");
            e.printStackTrace();
        }
    }

    public static String a(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            b.update(fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length()));
            byte[] bArrDigest = b.digest();
            String strA = a(bArrDigest, 0, bArrDigest.length);
            fileInputStream.close();
            return strA;
        } catch (Exception e2) {
            e = e2;
            fileInputStream2 = fileInputStream;
            s.b(c, "FILE EXCEPTION", e);
            if (fileInputStream2 == null) {
                return "";
            }
            fileInputStream2.close();
            return "";
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    private static String a(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 * 2);
        int i3 = i2 + i;
        while (i < i3) {
            byte b2 = bArr[i];
            char c2 = a[(b2 & 240) >> 4];
            char c3 = a[b2 & 15];
            stringBuffer.append(c2);
            stringBuffer.append(c3);
            i++;
        }
        return stringBuffer.toString();
    }
}
