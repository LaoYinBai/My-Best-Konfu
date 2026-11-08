package com.kwad.sdk.utils;

import androidx.annotation.NonNull;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.foundation.tools.SameMD5;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class aa {
    static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @NonNull
    public static String a(String str) {
        return a(str.getBytes());
    }

    public static String a(byte[] bArr) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SameMD5.TAG);
            messageDigest.update(bArr);
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            int length = bArrDigest.length;
            for (int i = 0; i < length; i++) {
                int i2 = bArrDigest[i];
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    sb.append(PushConstants.PUSH_TYPE_NOTIFY);
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        if (i < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = i2 * 2;
        char[] cArr = new char[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = bArr[i5 + i] & FileDownloadStatus.error;
            int i7 = i4 + 1;
            cArr[i4] = a[i6 >> 4];
            i4 = i7 + 1;
            cArr[i7] = a[i6 & 15];
        }
        return new String(cArr, 0, i3);
    }

    public static String b(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            return a(bArrDigest, 0, bArrDigest.length);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
            return "";
        }
    }
}
