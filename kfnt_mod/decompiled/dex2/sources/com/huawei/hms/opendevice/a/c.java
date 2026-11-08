package com.huawei.hms.opendevice.a;

import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & FileDownloadStatus.error);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] a(String str) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        byte[] bArr = new byte[upperCase.length() / 2];
        try {
            byte[] bytes = upperCase.getBytes(C.UTF8_NAME);
            for (int i = 0; i < bArr.length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("0x");
                int i2 = i * 2;
                sb.append(new String(new byte[]{bytes[i2]}, C.UTF8_NAME));
                bArr[i] = (byte) (((byte) (Byte.decode(sb.toString()).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{bytes[i2 + 1]}, C.UTF8_NAME)).byteValue());
            }
        } catch (UnsupportedEncodingException | NumberFormatException e) {
            HMSLog.e("HexUtil", "hex string 2 byte array exception : " + e.getMessage());
        }
        return bArr;
    }
}
