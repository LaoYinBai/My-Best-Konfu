package com.meizu.cloud.pushsdk.base;

import com.huawei.hms.framework.common.ContainerUtils;
import com.liulishuo.filedownloader.model.FileDownloadStatus;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class d {
    private static final char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final char b = (char) Integer.parseInt("00000011", 2);
    private static final char c = (char) Integer.parseInt("00001111", 2);
    private static final char d = (char) Integer.parseInt("00111111", 2);
    private final String e;
    private char[] f;
    private int g = 0;

    public d(String str) {
        this.e = str;
        a();
    }

    private void a() {
        char[] cArr = new char[a.length];
        this.g = this.e.charAt(0) % '\r';
        for (int i = 0; i < a.length; i++) {
            cArr[i] = a[(this.g + i) % a.length];
        }
        this.f = cArr;
    }

    public String a(byte[] bArr) {
        String str;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(((bArr.length + 2) / 3) * 4);
        int i = 0;
        int length = bArr.length;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bArr[i] & FileDownloadStatus.error;
            if (i2 == length) {
                sb.append(this.f[i3 >>> 2]);
                sb.append(this.f[(i3 & b) << 4]);
                str = "==";
            } else {
                int i4 = i2 + 1;
                int i5 = bArr[i2] & FileDownloadStatus.error;
                if (i4 == length) {
                    sb.append(this.f[i3 >>> 2]);
                    sb.append(this.f[((i3 & b) << 4) | (i5 >>> 4)]);
                    sb.append(this.f[(c & i5) << 2]);
                    str = ContainerUtils.KEY_VALUE_DELIMITER;
                } else {
                    int i6 = i4 + 1;
                    int i7 = bArr[i4] & FileDownloadStatus.error;
                    sb.append(this.f[i3 >>> 2]);
                    sb.append(this.f[((i3 & b) << 4) | (i5 >>> 4)]);
                    sb.append(this.f[((i5 & c) << 2) | (i7 >>> 6)]);
                    sb.append(this.f[d & i7]);
                    i = i6;
                }
            }
            sb.append(str);
            break;
        }
        return sb.toString();
    }
}
