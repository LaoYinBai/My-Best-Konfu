package com.kwad.sdk.c.b;

import android.content.Context;
import android.text.TextUtils;
import com.kwad.sdk.utils.q;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static File a(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            return filesDir;
        }
        return new File("/data/data/" + context.getPackageName() + "/file/");
    }

    public static String a(Context context, String str) {
        String strB = b(context);
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        return strB + File.separator + str;
    }

    public static void a(String str, String str2) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                q.b(zipInputStream);
                return;
            }
            String name = nextEntry.getName();
            if (!nextEntry.isDirectory()) {
                File file = new File(str2 + File.separator + name);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = zipInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i);
                    fileOutputStream.flush();
                }
                q.b(fileOutputStream);
            } else if (!name.contains("../")) {
                new File(str2 + File.separator + name.substring(0, name.length() - 1)).mkdirs();
            }
        }
    }

    public static boolean a(String str) {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public static String b(Context context) {
        String str = a(context) + File.separator + "offlinepackage";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static String b(Context context, String str) {
        String strB = b(context);
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        return strB + File.separator + str + ".zip";
    }

    public static String c(Context context) {
        String strB = b(context);
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        a(strB);
        return strB + File.separator + "packageIndex.json";
    }

    public static String c(Context context, String str) {
        String strB = b(context);
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        return strB + File.separator + str;
    }

    public static String d(Context context, String str) {
        String strB = b(context);
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        return strB + File.separator + str + File.separator + "_manifest_.json";
    }
}
