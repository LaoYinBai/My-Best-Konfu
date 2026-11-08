package com.huawei.updatesdk.service.c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    public static boolean a(File file, File file2) {
        boolean z = false;
        if (!file.renameTo(file2)) {
            if (!b(file, file2)) {
                com.huawei.updatesdk.a.a.b.a.a.a.d("PkgManageUtils", "can not copy the file to new Path");
                return false;
            }
            z = true;
        }
        if (z && !file.delete()) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("PkgManageUtils", "can not delete old file");
        }
        return true;
    }

    private static boolean a(InputStream inputStream, File file) throws IOException {
        try {
            if (file.exists() && !file.delete()) {
                com.huawei.updatesdk.a.a.b.a.a.a.b("PkgManageUtils", "destFile delete error.");
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i >= 0) {
                        fileOutputStream.write(bArr, 0, i);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            com.huawei.updatesdk.a.a.b.a.a.a.a("PkgManageUtils", "", e);
                        }
                    }
                }
                fileOutputStream.flush();
                try {
                    fileOutputStream.getFD().sync();
                } catch (IOException e2) {
                    com.huawei.updatesdk.a.a.b.a.a.a.a("PkgManageUtils", "", e2);
                }
                try {
                    fileOutputStream.close();
                    return true;
                } catch (IOException e3) {
                    com.huawei.updatesdk.a.a.b.a.a.a.a("PkgManageUtils", "", e3);
                    return true;
                }
            } finally {
            }
        } catch (IOException unused) {
            return false;
        }
    }

    private static boolean b(File file, File file2) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                boolean zA = a(fileInputStream, file2);
                fileInputStream.close();
                return zA;
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
        } catch (IOException unused) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("PkgManageUtils", "copyFile IOException");
            return false;
        }
    }
}
