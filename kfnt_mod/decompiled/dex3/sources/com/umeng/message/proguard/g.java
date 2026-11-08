package com.umeng.message.proguard;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g {
    public static int a;

    public static byte[] a(String str, String str2) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (h.d(str)) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setInput(str.getBytes(str2));
        deflater.finish();
        byte[] bArr = new byte[8192];
        a = 0;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int iDeflate = deflater.deflate(bArr);
                    a += iDeflate;
                    byteArrayOutputStream.write(bArr, 0, iDeflate);
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
            deflater.end();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    public static String a(byte[] bArr, String str) throws DataFormatException, UnsupportedEncodingException {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Inflater inflater = new Inflater();
        byte[] bArr2 = new byte[100];
        inflater.setInput(bArr, 0, bArr.length);
        StringBuilder sb = new StringBuilder();
        while (!inflater.needsInput()) {
            sb.append(new String(bArr2, 0, inflater.inflate(bArr2), str));
        }
        inflater.end();
        return sb.toString();
    }
}
