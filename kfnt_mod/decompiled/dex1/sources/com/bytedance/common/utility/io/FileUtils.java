package com.bytedance.common.utility.io;

import android.os.Environment;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class FileUtils {
    private static final byte[] GIF87A = {71, 73, 70, 56, 55, 97};
    private static final byte[] GIF89A = {71, 73, 70, 56, 57, 97};
    private static final byte[] JPEG = {-1, -40, -1};
    private static final byte[] PNG = {-119, 80, 78, 71, 13, 10, 26, 10};
    private static final String TAG = "FileUtils";

    public enum ImageType {
        UNKNOWN,
        JPG,
        PNG,
        GIF
    }

    private FileUtils() {
    }

    public static InputStream getInputStream(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (Exception e) {
            try {
                e.printStackTrace();
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public static byte[] getByteArray(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) fileInputStream.read();
            }
            fileInputStream.close();
            return bArr;
        } catch (Exception e) {
            try {
                e.printStackTrace();
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public static boolean copyFile(String str, String str2, String str3) throws Throwable {
        FileInputStream fileInputStream;
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(str2) || StringUtils.isEmpty(str3)) {
            return false;
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean zSaveInputStream = saveInputStream(fileInputStream, str2, str3);
            try {
                fileInputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return zSaveInputStream;
        } catch (Exception e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            e.printStackTrace();
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean saveInputStream(InputStream inputStream, String str, String str2) throws Throwable {
        if (inputStream == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = new File(str);
                if (!file.exists() && !file.mkdirs()) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, str2));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, i);
                    }
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                    inputStream.close();
                    if (inputStream == null) {
                        return true;
                    }
                    try {
                        inputStream.close();
                        return true;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return true;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    Logger.d(TAG, "save inputstream error: " + e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused2) {
                        }
                    }
                    if (inputStream == null) {
                        throw th;
                    }
                    try {
                        inputStream.close();
                        throw th;
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e6) {
            e = e6;
        }
    }

    public static ImageType getImageType(String str) {
        if (!StringUtils.isEmpty(str)) {
            return getImageType(new File(str));
        }
        return ImageType.UNKNOWN;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x0072 -> B:62:0x0075). Please report as a decompilation issue!!! */
    public static ImageType getImageType(File file) throws Throwable {
        FileInputStream fileInputStream;
        byte[] bArr;
        if (file == null || !file.exists()) {
            return ImageType.UNKNOWN;
        }
        ImageType imageType = ImageType.UNKNOWN;
        FileInputStream fileInputStream2 = null;
        FileInputStream fileInputStream3 = null;
        fileInputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                }
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            fileInputStream2 = fileInputStream2;
        }
        try {
            bArr = new byte[8];
            fileInputStream.read(bArr);
        } catch (Exception e3) {
            e = e3;
            fileInputStream3 = fileInputStream;
            e.printStackTrace();
            fileInputStream2 = fileInputStream3;
            if (fileInputStream3 != null) {
                fileInputStream3.close();
                fileInputStream2 = fileInputStream3;
            }
            return imageType;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        if (!checkSignature(bArr, GIF89A) && !checkSignature(bArr, GIF87A)) {
            if (checkSignature(bArr, JPEG)) {
                ImageType imageType2 = ImageType.JPG;
                try {
                    fileInputStream.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                return imageType2;
            }
            byte[] bArr2 = PNG;
            if (!checkSignature(bArr, bArr2)) {
                fileInputStream.close();
                fileInputStream2 = bArr2;
                return imageType;
            }
            ImageType imageType3 = ImageType.PNG;
            try {
                fileInputStream.close();
            } catch (Exception e6) {
                e6.printStackTrace();
            }
            return imageType3;
        }
        ImageType imageType4 = ImageType.GIF;
        try {
            fileInputStream.close();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        return imageType4;
    }

    public static boolean isGif(File file) {
        return getImageType(file) == ImageType.GIF;
    }

    public static boolean exists(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    private static boolean checkSignature(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void chmod(String str, int i) {
        Method method;
        int iIntValue;
        try {
            Class<?> cls = Class.forName("android.os.FileUtils");
            if (cls == null || (method = cls.getMethod("setPermissions", String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE)) == null || (iIntValue = ((Integer) method.invoke(null, str, Integer.valueOf(i), -1, -1)).intValue()) == 0) {
                return;
            }
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() returned " + iIntValue + " for '" + str + "', probably didn't work.");
        } catch (ClassNotFoundException unused) {
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() failed - ClassNotFoundException.");
        } catch (IllegalAccessException unused2) {
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() failed - IllegalAccessException.");
        } catch (NoSuchMethodException unused3) {
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() failed - NoSuchMethodException.");
        } catch (InvocationTargetException unused4) {
            Logger.d("NetHackDbg", "android.os.FileUtils.setPermissions() failed - InvocationTargetException.");
        } catch (Throwable unused5) {
        }
    }

    public static String getFileNameWithoutExtension(String str) {
        int iLastIndexOf;
        String name = new File(str).getName();
        return (StringUtils.isEmpty(name) || -1 == (iLastIndexOf = name.lastIndexOf("."))) ? "" : name.substring(0, iLastIndexOf);
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(".");
        return iLastIndexOf == -1 ? "" : str.substring(iLastIndexOf + 1);
    }

    public static void clearDir(String str) throws Exception {
        File file = new File(str);
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles.length;
            for (int i = 0; i < length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    removeDir(fileArrListFiles[i].getAbsolutePath());
                } else if (fileArrListFiles[i].isFile()) {
                    fileArrListFiles[i].delete();
                }
            }
        }
    }

    public static void removeDir(String str) throws Exception {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles.length;
            for (int i = 0; i < length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    removeDir(fileArrListFiles[i].getAbsolutePath());
                } else {
                    fileArrListFiles[i].delete();
                }
            }
            file.delete();
        }
    }

    public static void clearDir(String str, Set<String> set) throws Exception {
        File file = new File(str);
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles.length;
            for (int i = 0; i < length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    removeDir(fileArrListFiles[i].getAbsolutePath(), set);
                } else if (fileArrListFiles[i].isFile()) {
                    String name = fileArrListFiles[i].getName();
                    if (set == null || !set.contains(name)) {
                        fileArrListFiles[i].delete();
                    }
                }
            }
        }
    }

    public static void removeDir(String str, Set<String> set) throws Exception {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles.length;
            for (int i = 0; i < length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    removeDir(fileArrListFiles[i].getAbsolutePath(), set);
                } else {
                    String name = fileArrListFiles[i].getName();
                    if (set == null || !set.contains(name)) {
                        fileArrListFiles[i].delete();
                    }
                }
            }
        }
    }

    public static long getDirectorySize(File file, boolean z) {
        long length = 0;
        if (!file.exists() && file.isDirectory()) {
            return 0L;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                length += getDirectorySize(file2, z);
            } else if (file2.isFile()) {
                length += file2.length();
            }
        }
        return length;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCacheDirPath(android.content.Context r3) throws java.lang.NullPointerException {
        /*
            if (r3 == 0) goto L48
            r0 = 0
            java.io.File r1 = r3.getCacheDir()     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L13
            java.io.File r3 = r3.getCacheDir()     // Catch: java.lang.Throwable -> L39
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L39
        L11:
            r0 = r3
            goto L39
        L13:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L39
            r1.<init>()     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = "/data/data/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = r3.getPackageName()     // Catch: java.lang.Throwable -> L39
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = "/cache/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L39
            r2 = 0
            java.io.File r3 = r3.getDir(r1, r2)     // Catch: java.lang.Throwable -> L39
            if (r3 == 0) goto L39
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L39
            goto L11
        L39:
            boolean r3 = com.bytedance.common.utility.StringUtils.isEmpty(r0)
            if (r3 != 0) goto L40
            return r0
        L40:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Cannot Create Cache Dir"
            r3.<init>(r0)
            throw r3
        L48:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Context is NUll"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.io.FileUtils.getCacheDirPath(android.content.Context):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getFilesDirPath(android.content.Context r3) throws java.lang.NullPointerException {
        /*
            if (r3 == 0) goto L48
            r0 = 0
            java.io.File r1 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L13
            java.io.File r3 = r3.getFilesDir()     // Catch: java.lang.Throwable -> L39
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L39
        L11:
            r0 = r3
            goto L39
        L13:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L39
            r1.<init>()     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = "/data/data/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = r3.getPackageName()     // Catch: java.lang.Throwable -> L39
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = "/files/"
            r1.append(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L39
            r2 = 0
            java.io.File r3 = r3.getDir(r1, r2)     // Catch: java.lang.Throwable -> L39
            if (r3 == 0) goto L39
            java.lang.String r3 = r3.getPath()     // Catch: java.lang.Throwable -> L39
            goto L11
        L39:
            boolean r3 = com.bytedance.common.utility.StringUtils.isEmpty(r0)
            if (r3 != 0) goto L40
            return r0
        L40:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Cannot Create Files Dir"
            r3.<init>(r0)
            throw r3
        L48:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r0 = "Context is NUll"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.io.FileUtils.getFilesDirPath(android.content.Context):java.lang.String");
    }

    public static boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isExternalStorageReadable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }
}
