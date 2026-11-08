package com.huawei.hms.aaid.encrypt;

import com.huawei.hms.support.log.HMSLog;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return "";
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            cArr[i2] = a[(b & 240) >> 4];
            cArr[i2 + 1] = a[b & 15];
        }
        return new String(cArr);
    }

    public static byte[] a(String str) throws UnsupportedEncodingException {
        byte[] bArr = new byte[str.length() / 2];
        try {
            byte[] bytes = str.getBytes(C.UTF8_NAME);
            for (int i = 0; i < bArr.length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("0x");
                int i2 = i * 2;
                sb.append(new String(new byte[]{bytes[i2]}, C.UTF8_NAME));
                bArr[i] = (byte) (((byte) (Byte.decode(sb.toString()).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{bytes[i2 + 1]}, C.UTF8_NAME)).byteValue());
            }
        } catch (UnsupportedEncodingException e) {
            HMSLog.e("BaseUtil", "hexString2ByteArray error" + e.getMessage());
        } catch (NumberFormatException e2) {
            HMSLog.e("BaseUtil", "hexString2ByteArray error" + e2.getMessage());
        }
        return bArr;
    }
}
