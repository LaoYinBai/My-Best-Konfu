package com.facebook.stetho.common;

import java.io.FileInputStream;
import java.io.IOException;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ProcessUtil {
    private static final int CMDLINE_BUFFER_SIZE = 64;
    private static String sProcessName;
    private static boolean sProcessNameRead;

    @Nullable
    public static synchronized String getProcessName() {
        if (!sProcessNameRead) {
            sProcessNameRead = true;
            try {
                sProcessName = readProcessName();
            } catch (IOException unused) {
            }
        }
        return sProcessName;
    }

    private static String readProcessName() throws Throwable {
        byte[] bArr = new byte[64];
        FileInputStream fileInputStream = new FileInputStream("/proc/self/cmdline");
        boolean z = false;
        try {
            int i = fileInputStream.read(bArr);
            try {
                int iIndexOf = indexOf(bArr, 0, i, (byte) 0);
                if (iIndexOf > 0) {
                    i = iIndexOf;
                }
                String str = new String(bArr, 0, i);
                Util.close(fileInputStream, false);
                return str;
            } catch (Throwable th) {
                th = th;
                z = true;
                Util.close(fileInputStream, true ^ z);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static int indexOf(byte[] bArr, int i, int i2, byte b2) {
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (bArr[i3] == b2) {
                return i3;
            }
        }
        return -1;
    }
}
