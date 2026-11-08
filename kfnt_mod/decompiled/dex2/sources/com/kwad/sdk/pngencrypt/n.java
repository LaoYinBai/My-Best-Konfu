package com.kwad.sdk.pngencrypt;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class n {
    public static Charset a = Charset.forName("ISO-8859-1");
    public static Charset b = Charset.forName(C.UTF8_NAME);
    private static ThreadLocal<Boolean> c = new ThreadLocal<Boolean>() { // from class: com.kwad.sdk.pngencrypt.n.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean initialValue() {
            return Boolean.FALSE;
        }
    };

    public static double a(int i) {
        return i / 100000.0d;
    }

    static final int a(int i, int i2, int i3) {
        int i4 = (i + i2) - i3;
        int i5 = i4 >= i ? i4 - i : i - i4;
        int i6 = i4 >= i2 ? i4 - i2 : i2 - i4;
        int i7 = i4 >= i3 ? i4 - i3 : i3 - i4;
        return (i5 > i6 || i5 > i7) ? i6 <= i7 ? i2 : i3 : i;
    }

    public static int a(InputStream inputStream) {
        try {
            return inputStream.read();
        } catch (IOException e) {
            com.kwad.sdk.core.d.a.a(e);
            return -1;
        }
    }

    public static int a(byte[] bArr, int i) {
        return bArr[i] & FileDownloadStatus.error;
    }

    public static byte[] a() {
        return new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
    }

    public static int b(InputStream inputStream) throws IOException {
        try {
            int i = inputStream.read();
            int i2 = inputStream.read();
            int i3 = inputStream.read();
            int i4 = inputStream.read();
            if (i == -1 || i2 == -1 || i3 == -1 || i4 == -1) {
                return -1;
            }
            return (i << 24) | (i2 << 16) | ((i3 << 8) + i4);
        } catch (IOException e) {
            com.kwad.sdk.core.d.a.a(e);
            return -1;
        }
    }

    public static int b(byte[] bArr, int i) {
        return (bArr[i + 1] & FileDownloadStatus.error) | ((bArr[i] & FileDownloadStatus.error) << 8);
    }

    public static final int c(byte[] bArr, int i) {
        return (bArr[i + 3] & FileDownloadStatus.error) | ((bArr[i] & FileDownloadStatus.error) << 24) | ((bArr[i + 1] & FileDownloadStatus.error) << 16) | ((bArr[i + 2] & FileDownloadStatus.error) << 8);
    }
}
