package com.bytedance.common.utility.io;

import android.os.StatFs;
import android.text.TextUtils;
import com.appsflyer.share.Constants;
import com.bytedance.common.utility.reflect.JavaCalls;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.zip.ZipFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class IOUtils {
    private IOUtils() {
    }

    public static void deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    public static boolean exists(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return new File(str).exists();
    }

    public static boolean mkdir(String str) {
        return new File(str).mkdirs();
    }

    public static boolean copyFile(File file, File file2) throws IOException {
        if (!file.exists() || !file.isFile() || file2.isDirectory()) {
            return false;
        }
        if (file2.exists()) {
            file2.delete();
        }
        try {
            byte[] bArr = new byte[2048];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i != -1) {
                    bufferedOutputStream.write(bArr, 0, i);
                } else {
                    bufferedInputStream.close();
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyFile(String str, String str2) {
        return copyFile(new File(str), new File(str2));
    }

    public static String readFileFirstLine(String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(str));
        } catch (Exception unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            String line = bufferedReader.readLine();
            bufferedReader.close();
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return line;
        } catch (Exception unused2) {
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static void deletePath(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null) {
                return;
            }
            for (String str2 : list) {
                if (str2 != null) {
                    String str3 = str.endsWith(File.separator) ? str + str2 : str + File.separator + str2;
                    File file2 = new File(str3);
                    if (file2.isFile()) {
                        file2.delete();
                    }
                    if (file2.isDirectory()) {
                        deletePath(str3);
                    }
                }
            }
            file.delete();
        }
    }

    public static void clearPath(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            for (String str2 : file.list()) {
                String str3 = str.endsWith(File.separator) ? str + str2 : str + File.separator + str2;
                File file2 = new File(str3);
                if (file2.isFile()) {
                    file2.delete();
                }
                if (file2.isDirectory()) {
                    deletePath(str3);
                }
            }
        }
    }

    public static long getFileSize(String str) {
        File[] fileArrListFiles;
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        File file = new File(str);
        if (!file.exists()) {
            return 0L;
        }
        long length = file.length();
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                try {
                    length += getFileSize(file2.getAbsolutePath());
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                    return 0L;
                } catch (StackOverflowError e2) {
                    e2.printStackTrace();
                    return 0L;
                }
            }
        }
        return length;
    }

    public static String getFileName(String str) {
        if (str.endsWith(Constants.URL_PATH_DELIMITER)) {
            str = str.substring(0, str.length() - 1);
        }
        int iLastIndexOf = str.lastIndexOf(Constants.URL_PATH_DELIMITER);
        return (iLastIndexOf <= 0 || iLastIndexOf >= str.length() + (-1)) ? str : str.substring(iLastIndexOf + 1);
    }

    public static String getParentFilePath(String str) {
        if (str.endsWith(Constants.URL_PATH_DELIMITER)) {
            str = str.substring(0, str.length() - 1);
        }
        int iLastIndexOf = str.lastIndexOf(Constants.URL_PATH_DELIMITER);
        if (iLastIndexOf >= 0) {
            return str.substring(0, iLastIndexOf);
        }
        return null;
    }

    public static String getFileNameWithoutExtension(String str) {
        int iLastIndexOf;
        String fileName = getFileName(str);
        return (fileName == null || fileName.length() <= 0 || (iLastIndexOf = fileName.lastIndexOf(46)) <= 0) ? fileName : fileName.substring(0, iLastIndexOf);
    }

    public static String getFileExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(".");
        return (iLastIndexOf < 0 || iLastIndexOf >= str.length() + (-1)) ? "" : str.substring(iLastIndexOf + 1).toUpperCase(Locale.getDefault());
    }

    public static boolean renameFile(String str, String str2) {
        File file = new File(str);
        File file2 = new File(str2);
        if (file.exists()) {
            return file.renameTo(file2);
        }
        return false;
    }

    public static long getAvailableBytes(String str) {
        try {
            if (TextUtils.isEmpty(str) || !new File(str).exists()) {
                return 0L;
            }
            StatFs statFs = new StatFs(str);
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long getAllBytes(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            if (!TextUtils.isEmpty(str) && new File(str).exists()) {
                StatFs statFs = new StatFs(str);
                return statFs.getBlockSize() * statFs.getBlockCount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static boolean canWrite(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        File file2 = new File(file, "." + System.currentTimeMillis());
        boolean zMkdir = file2.mkdir();
        return zMkdir ? file2.delete() : zMkdir;
    }

    public static void setPermissions(String str, int i) {
        JavaCalls.callStaticMethod("android.os.FileUtils", str, Integer.valueOf(i), -1, -1);
    }

    public static void write(String str, String str2, boolean z) throws Throwable {
        FileWriter fileWriter;
        FileWriter fileWriter2 = null;
        try {
            try {
                try {
                    File file = new File(str);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    fileWriter = new FileWriter(str, z);
                } catch (Exception unused) {
                    return;
                }
            } catch (IOException e) {
                e = e;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            fileWriter.write(str2);
            fileWriter.close();
        } catch (IOException e2) {
            e = e2;
            fileWriter2 = fileWriter;
            e.printStackTrace();
            if (fileWriter2 == null) {
                return;
            }
            fileWriter2.close();
        } catch (Throwable th3) {
            th = th3;
            fileWriter2 = fileWriter;
            if (fileWriter2 != null) {
                try {
                    fileWriter2.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    public static void close(Closeable closeable) throws IOException {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public static void close(ZipFile zipFile) throws IOException {
        if (zipFile == null) {
            return;
        }
        try {
            zipFile.close();
        } catch (IOException unused) {
        }
    }
}
