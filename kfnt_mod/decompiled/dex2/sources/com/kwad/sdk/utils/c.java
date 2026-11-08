package com.kwad.sdk.utils;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.mbridge.msdk.foundation.tools.SameMD5;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    @Nullable
    @WorkerThread
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return b(new File(str));
    }

    public static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @Nullable
    @WorkerThread
    public static byte[] a(File file) throws IOException {
        if (file == null) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SameMD5.TAG);
            byte[] bArr = new byte[4096];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    return messageDigest.digest();
                }
                messageDigest.update(bArr, 0, i);
            }
        } catch (Exception e) {
            Log.e("FileMD5Utils", "getting file md5 digest error.", e);
            return null;
        } finally {
            a(fileInputStream);
        }
    }

    @Nullable
    @WorkerThread
    public static String b(File file) {
        try {
            byte[] bArrA = a(file);
            if (bArrA != null && bArrA.length != 0) {
                return aa.a(bArrA, 0, bArrA.length);
            }
            return null;
        } catch (IOException | NoSuchAlgorithmException e) {
            Log.e("FileMD5Utils", "cannot calculate md5 of file", e);
            return null;
        }
    }
}
