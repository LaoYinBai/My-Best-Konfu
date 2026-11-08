package com.sigmob.a.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a {
    public static long a(InputStream inputStream, OutputStream outputStream) {
        return a(inputStream, outputStream, 8024);
    }

    public static long a(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        long j = 0;
        while (true) {
            int i2 = inputStream.read(bArr);
            if (-1 == i2) {
                return j;
            }
            outputStream.write(bArr, 0, i2);
            j += i2;
        }
    }
}
