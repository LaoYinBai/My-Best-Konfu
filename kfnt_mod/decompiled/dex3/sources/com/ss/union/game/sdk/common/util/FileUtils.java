package com.ss.union.game.sdk.common.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.feedback.picture.select.d;
import com.umeng.message.MsgConstant;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FileUtils {
    private static String a = "base/";
    private static String b = ".sys/";

    public static void init(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            a = str;
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        b = str2;
    }

    public static File getOutDirPrivate(String str) {
        Context context = GlobalApplicationUtils.getContext();
        String strA = a(str);
        if (!a()) {
            return null;
        }
        File file = new File(context.getExternalFilesDir(strA).getAbsolutePath());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getOutDirPublic(String str) {
        String strA = a(str);
        if (!isExternalPathExist()) {
            return null;
        }
        File file = new File(Environment.getExternalStorageDirectory(), strA);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getInnerDir(String str) {
        Context context = GlobalApplicationUtils.getContext();
        File file = new File(context.getFilesDir(), a(str));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getInnerCacheDir(String str) {
        Context context = GlobalApplicationUtils.getContext();
        File file = new File(context.getCacheDir(), a(str));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getOutOfControlDir(String str) {
        if (!isExternalPathExist()) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        File file = new File(Environment.getExternalStorageDirectory(), b + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static String a(String str) {
        if (str == null) {
            str = "";
        }
        return a + str;
    }

    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        }
        if (!file.isDirectory()) {
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                deleteFile(file2);
            }
        }
        file.delete();
        return true;
    }

    public static boolean deleteFile(String str) {
        try {
            return deleteFile(new File(str));
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isExternalPathExist() {
        Context context = GlobalApplicationUtils.getContext();
        return a(context) && b(context) && a();
    }

    private static boolean a(Context context) {
        try {
            boolean z = false;
            boolean z2 = false;
            for (String str : context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions) {
                if (MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE.equals(str)) {
                    z = true;
                }
                if ("android.permission.READ_EXTERNAL_STORAGE".equals(str)) {
                    z2 = true;
                }
            }
            return z && z2;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean b(Context context) {
        try {
            return Build.VERSION.SDK_INT >= 23 ? context.checkSelfPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) == 0 && context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0 : context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) == 0 && context.checkCallingOrSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean a() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static long getCacheSize() {
        Context context = GlobalApplicationUtils.getContext();
        if (context != null) {
            return a(getOutDirPublic("")) + 0 + a(getOutDirPrivate("")) + a(getInnerDir("")) + a(context.getCacheDir());
        }
        return 0L;
    }

    private static long a(File file) {
        long jA = 0;
        if (file == null) {
            return 0L;
        }
        if (file.isFile()) {
            return file.length();
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                jA += a(file2);
            }
        }
        return jA;
    }

    public static void clearCache() {
        try {
            Context context = GlobalApplicationUtils.getContext();
            if (context != null) {
                deleteFile(getOutDirPublic(""));
                deleteFile(getOutDirPrivate(""));
                deleteFile(getInnerDir(""));
                deleteFile(context.getCacheDir());
            }
        } catch (Throwable unused) {
        }
    }

    public static String getFormatSize() {
        return new BigDecimal(Float.toString((getCacheSize() / 1024.0f) / 1024.0f)).setScale(2, 4).toString() + "M";
    }

    public static File getFileUri(Uri uri) {
        try {
            return new File(new URI(uri.toString()));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String readFile(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[d.d];
        while (true) {
            int i = fileInputStream.read(bArr);
            if (i != -1) {
                sb.append((CharSequence) new String(bArr), 0, i);
            } else {
                fileInputStream.close();
                return sb.toString();
            }
        }
    }

    public static void writeToExternal(String str, String str2) {
        FileOutputStream fileOutputStream;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (FileUtils.class) {
            File file = new File(str2);
            if (!file.exists()) {
                createFile(file.getPath(), true);
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Exception e) {
                    e = e;
                }
            } catch (IOException unused) {
            }
            try {
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();
            } catch (Exception e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                LogUtils.log("FileUtils.writeToExternal", "Exception" + Log.getStackTraceString(e));
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        }
    }

    public static File createFile(String str, boolean z) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            if (!z) {
                file.mkdirs();
            } else {
                try {
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file.createNewFile();
                } catch (IOException e) {
                    LogUtils.log("FileUtils.createFile", "Exception" + Log.getStackTraceString(e));
                }
            }
        }
        return file;
    }

    public static boolean copyInputStreamToFile(InputStream inputStream, File file, boolean z) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        createFile(file.getAbsolutePath(), true);
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z), 8192);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, i);
            }
            bufferedOutputStream.flush();
            try {
                inputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                bufferedOutputStream.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            return true;
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            try {
                inputStream.close();
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                    throw th;
                } catch (Exception e8) {
                    e8.printStackTrace();
                    throw th;
                }
            }
            throw th;
        }
    }
}
