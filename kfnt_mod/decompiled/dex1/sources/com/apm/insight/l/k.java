package com.apm.insight.l;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class k {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(String str, int i) {
        com.apm.insight.h.a.a("android.os.FileUtils", "setPermissions", str, Integer.valueOf(i), -1, -1);
    }

    public static void a(ZipFile zipFile) throws IOException {
        if (zipFile == null) {
            return;
        }
        try {
            zipFile.close();
        } catch (IOException unused) {
        }
    }
}
