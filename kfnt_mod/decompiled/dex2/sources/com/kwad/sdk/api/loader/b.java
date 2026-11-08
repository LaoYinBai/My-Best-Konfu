package com.kwad.sdk.api.loader;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class b {
    private static void a(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            a(file2);
        }
    }

    private static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    private static void a(String str, String str2) throws IOException {
        b(new FileInputStream(str), new FileOutputStream(str2));
    }

    static boolean a(Context context, String str, String str2) throws Exception {
        String strB = g.b(context, str2);
        a(new File(strB));
        String strD = g.d(context, str2);
        String strE = g.e(context, str2);
        String strF = g.f(context, str2);
        try {
            a(str, strD);
            b(context, str, strF);
            return j.a(context, strD, strE, strF).c() != null;
        } catch (Exception e) {
            a(new File(strD));
            a(new File(strE));
            a(new File(strF));
            a(new File(strB));
            throw e;
        }
    }

    private static void b(Context context, String str, String str2) throws Throwable {
        ZipFile zipFile;
        String str3 = s.a() ? "lib/arm64-v8a/" : "lib/armeabi-v7a/";
        try {
            zipFile = new ZipFile(str);
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    if (!zipEntryNextElement.isDirectory()) {
                        String name = zipEntryNextElement.getName();
                        if (!TextUtils.isEmpty(name) && !name.contains("../") && name.endsWith(".so") && name.startsWith(str3)) {
                            b(zipFile.getInputStream(zipEntryNextElement), new FileOutputStream(new File(str2, name.substring(str3.length()))));
                        }
                    }
                }
                try {
                    zipFile.close();
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                th = th;
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            zipFile = null;
        }
    }

    private static void b(InputStream inputStream, OutputStream outputStream) throws IOException {
        try {
            a(inputStream, outputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception unused) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception unused2) {
                }
            }
        } finally {
        }
    }
}
