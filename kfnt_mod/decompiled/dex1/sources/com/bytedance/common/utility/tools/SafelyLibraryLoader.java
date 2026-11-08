package com.bytedance.common.utility.tools;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.appsflyer.share.Constants;
import com.bytedance.common.utility.io.IOUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SafelyLibraryLoader {
    private static final String LIB_DIR = "libso";
    private static List<String> sLoadedLibs = new ArrayList();

    public static synchronized boolean loadLibrary(Context context, String str) {
        if (sLoadedLibs.contains(str)) {
            return true;
        }
        try {
            System.loadLibrary(str);
            sLoadedLibs.add(str);
        } catch (UnsatisfiedLinkError e) {
            File libraryFile = getLibraryFile(context, str);
            if (libraryFile == null) {
                return false;
            }
            if (libraryFile.exists()) {
                libraryFile.delete();
            }
            String strUnpackLibrary = unpackLibrary(context, str, libraryFile);
            if (strUnpackLibrary != null) {
                Log.e("loadLibrary", e.getMessage() + "[" + strUnpackLibrary + "]");
                return false;
            }
            try {
                System.load(libraryFile.getAbsolutePath());
                sLoadedLibs.add(str);
            } catch (Throwable unused) {
                return false;
            }
        } catch (Throwable unused2) {
            return false;
        }
        return true;
    }

    private static File getLibraryFolder(Context context) {
        if (context == null || context.getFilesDir() == null) {
            return null;
        }
        File file = new File(context.getFilesDir(), LIB_DIR);
        if (!file.exists()) {
            IOUtils.mkdir(file.getAbsolutePath());
        }
        return file;
    }

    private static File getLibraryFile(Context context, String str) {
        String strMapLibraryName = System.mapLibraryName(str);
        File libraryFolder = getLibraryFolder(context);
        if (libraryFolder != null) {
            return new File(libraryFolder, strMapLibraryName);
        }
        return null;
    }

    private static String unpackLibrary(Context context, String str, File file) throws Throwable {
        InputStream inputStream;
        ZipFile zipFile;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
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
                        IOUtils.close((Closeable) null);
                        IOUtils.close((Closeable) null);
                        IOUtils.close(zipFile);
                        return str3;
                    }
                    entry = entry2;
                }
                file.createNewFile();
                InputStream inputStream2 = zipFile.getInputStream(entry);
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (Throwable th) {
                    inputStream = inputStream2;
                    th = th;
                }
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int i = inputStream2.read(bArr);
                        if (i <= 0) {
                            IOUtils.setPermissions(file.getAbsolutePath(), 493);
                            IOUtils.close(fileOutputStream);
                            IOUtils.close(inputStream2);
                            IOUtils.close(zipFile);
                            return null;
                        }
                        fileOutputStream.write(bArr, 0, i);
                    }
                } catch (Throwable th2) {
                    fileOutputStream2 = fileOutputStream;
                    inputStream = inputStream2;
                    th = th2;
                    IOUtils.close(fileOutputStream2);
                    IOUtils.close(inputStream);
                    IOUtils.close(zipFile);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            zipFile = null;
        }
    }
}
