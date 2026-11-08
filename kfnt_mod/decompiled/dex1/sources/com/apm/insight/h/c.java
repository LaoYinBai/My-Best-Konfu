package com.apm.insight.h;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import androidx.annotation.Nullable;
import com.apm.insight.l.i;
import com.apm.insight.l.k;
import com.appsflyer.share.Constants;
import dalvik.system.BaseDexClassLoader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static List<String> f2878a = new ArrayList();

    @Nullable
    public static String a(Context context, String str, File file) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String strA = a(applicationInfo.sourceDir, str, file);
        if (strA == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 21) {
            return "low";
        }
        String strA2 = strA;
        for (String str2 : applicationInfo.splitSourceDirs) {
            strA2 = a(str2, str, file);
            if (strA2 == null) {
                return null;
            }
        }
        try {
            ClassLoader classLoader = c.class.getClassLoader();
            while (!(classLoader instanceof BaseDexClassLoader) && classLoader.getParent() != null) {
                classLoader = classLoader.getParent();
            }
            if (!(classLoader instanceof BaseDexClassLoader)) {
                return strA2;
            }
            Field declaredField = BaseDexClassLoader.class.getDeclaredField("pathList");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(classLoader);
            Field declaredField2 = obj.getClass().getDeclaredField("nativeLibraryDirectories");
            declaredField2.setAccessible(true);
            for (String str3 : (String[]) declaredField2.get(obj)) {
                File file2 = new File(str3, System.mapLibraryName(str));
                if (file2.exists()) {
                    i.a(file2, file);
                    k.a(file.getAbsolutePath(), 493);
                    return null;
                }
            }
            return "not_found";
        } catch (Throwable th) {
            return th.getMessage();
        }
    }

    public static String a(String str, String str2, File file) throws Throwable {
        InputStream inputStream;
        ZipFile zipFile;
        String message;
        ZipEntry entry;
        FileOutputStream fileOutputStream = null;
        try {
            zipFile = new ZipFile(new File(str), 1);
            try {
                entry = zipFile.getEntry("lib/" + Build.CPU_ABI + Constants.URL_PATH_DELIMITER + System.mapLibraryName(str2));
            } catch (Throwable th) {
                th = th;
                inputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            zipFile = null;
        }
        if (entry == null) {
            int iIndexOf = Build.CPU_ABI.indexOf(45);
            StringBuilder sb = new StringBuilder();
            sb.append("lib/");
            String str3 = Build.CPU_ABI;
            if (iIndexOf <= 0) {
                iIndexOf = Build.CPU_ABI.length();
            }
            sb.append(str3.substring(0, iIndexOf));
            sb.append(Constants.URL_PATH_DELIMITER);
            sb.append(System.mapLibraryName(str2));
            String string = sb.toString();
            ZipEntry entry2 = zipFile.getEntry(string);
            if (entry2 == null) {
                message = "Library entry not found:" + string;
                k.a((Closeable) null);
                k.a((Closeable) null);
                k.a(zipFile);
                return message;
            }
            entry = entry2;
        }
        file.createNewFile();
        InputStream inputStream2 = zipFile.getInputStream(entry);
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = inputStream2.read(bArr);
                    if (i <= 0) {
                        k.a(file.getAbsolutePath(), 493);
                        k.a(fileOutputStream2);
                        k.a(inputStream2);
                        k.a(zipFile);
                        return null;
                    }
                    fileOutputStream2.write(bArr, 0, i);
                }
            } catch (Throwable th3) {
                fileOutputStream = fileOutputStream2;
                inputStream = inputStream2;
                th = th3;
                k.a(fileOutputStream);
                k.a(inputStream);
                k.a(zipFile);
                throw th;
            }
        } catch (Throwable th4) {
            inputStream = inputStream2;
            th = th4;
        }
    }
}
