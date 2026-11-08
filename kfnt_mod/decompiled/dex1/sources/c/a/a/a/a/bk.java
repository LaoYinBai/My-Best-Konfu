package c.a.a.a.a;

import android.content.Context;
import android.os.Build;
import com.appsflyer.share.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bk {

    /* renamed from: a, reason: collision with root package name */
    private static List<String> f2088a = new ArrayList();

    private static File a(Context context) {
        if (context == null || context.getFilesDir() == null) {
            return null;
        }
        File file = new File(context.getFilesDir(), "libso");
        if (!file.exists()) {
            bc.a(file.getAbsolutePath());
        }
        return file;
    }

    private static String a(Context context, String str, File file) throws IOException {
        Throwable th;
        InputStream inputStream;
        ZipFile zipFile;
        FileOutputStream fileOutputStream = null;
        try {
            zipFile = new ZipFile(new File(context.getApplicationInfo().sourceDir), 1);
            try {
                ZipEntry entry = zipFile.getEntry("lib/" + Build.CPU_ABI + Constants.URL_PATH_DELIMITER + System.mapLibraryName(str));
                if (entry == null) {
                    int iIndexOf = Build.CPU_ABI.indexOf(45);
                    StringBuilder sb = new StringBuilder();
                    sb.append("lib/");
                    String str2 = Build.CPU_ABI;
                    if (iIndexOf <= 0) {
                        iIndexOf = Build.CPU_ABI.length();
                    }
                    sb.append(str2.substring(0, iIndexOf));
                    sb.append(Constants.URL_PATH_DELIMITER);
                    sb.append(System.mapLibraryName(str));
                    String string = sb.toString();
                    ZipEntry entry2 = zipFile.getEntry(string);
                    if (entry2 == null) {
                        String str3 = "Library entry not found:" + string;
                        bc.a((Closeable) null);
                        bc.a((Closeable) null);
                        bc.a(zipFile);
                        return str3;
                    }
                    entry = entry2;
                }
                file.createNewFile();
                inputStream = zipFile.getInputStream(entry);
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        byte[] bArr = new byte[16384];
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i <= 0) {
                                bc.a(file.getAbsolutePath(), 493);
                                bc.a(fileOutputStream2);
                                bc.a(inputStream);
                                bc.a(zipFile);
                                return null;
                            }
                            fileOutputStream2.write(bArr, 0, i);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        try {
                            return th.getMessage();
                        } finally {
                            bc.a(fileOutputStream);
                            bc.a(inputStream);
                            bc.a(zipFile);
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                inputStream = null;
            }
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
            zipFile = null;
        }
    }

    public static synchronized boolean a(Context context, String str) {
        if (f2088a.contains(str)) {
            return true;
        }
        try {
            System.loadLibrary(str);
            f2088a.add(str);
        } catch (UnsatisfiedLinkError unused) {
            File fileB = b(context, str);
            if (fileB == null) {
                return false;
            }
            if (fileB.exists()) {
                fileB.delete();
            }
            if (a(context, str, fileB) != null) {
                return false;
            }
            try {
                System.load(fileB.getAbsolutePath());
                f2088a.add(str);
            } catch (Throwable unused2) {
                return false;
            }
        } catch (Throwable unused3) {
            return false;
        }
        return true;
    }

    private static File b(Context context, String str) {
        String strMapLibraryName = System.mapLibraryName(str);
        File fileA = a(context);
        if (fileA != null) {
            return new File(fileA, strMapLibraryName);
        }
        return null;
    }
}
