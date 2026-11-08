package com.kwad.sdk.core.diskcache.a;

import android.text.TextUtils;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.foundation.tools.SameMD5;
import java.io.Closeable;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class d {
    static Object a(InputStream inputStream) throws Throwable {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2 = null;
        try {
            objectInputStream = new ObjectInputStream(inputStream);
            try {
                try {
                    Object object = objectInputStream.readObject();
                    a((Closeable) objectInputStream);
                    return object;
                } catch (Exception e) {
                    e = e;
                    com.kwad.sdk.core.d.a.a(e);
                    a((Closeable) objectInputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                objectInputStream2 = objectInputStream;
                a((Closeable) objectInputStream2);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            objectInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            a((Closeable) objectInputStream2);
            throw th;
        }
    }

    static String a(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SameMD5.TAG);
            messageDigest.update(str.getBytes());
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return String.valueOf(str.hashCode());
        }
    }

    private static String a(byte[] bArr) {
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

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
        if ((obj instanceof String) && TextUtils.isEmpty((String) obj)) {
            throw new NullPointerException(str);
        }
    }

    static boolean a(OutputStream outputStream, Object obj) throws Throwable {
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(outputStream);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            a(objectOutputStream);
            return true;
        } catch (Exception e2) {
            e = e2;
            objectOutputStream2 = objectOutputStream;
            com.kwad.sdk.core.d.a.a(e);
            a(objectOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            a(objectOutputStream2);
            throw th;
        }
    }
}
