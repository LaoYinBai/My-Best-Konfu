package com.qq.e.comm.util;

import com.taobao.accs.data.Message;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FileUtil {
    public static boolean copyTo(InputStream inputStream, File file) throws IOException {
        if (inputStream == null || file == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[Message.FLAG_REQ_BIT1];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i <= 0) {
                            tryClose(inputStream);
                            tryClose(fileOutputStream2);
                            return true;
                        }
                        fileOutputStream2.write(bArr, 0, i);
                    }
                } catch (Throwable unused) {
                    fileOutputStream = fileOutputStream2;
                    try {
                        GDTLogger.d(String.format("Exception while copy from InputStream to File %s", file.getAbsolutePath()));
                        return false;
                    } finally {
                        tryClose(inputStream);
                        tryClose(fileOutputStream);
                    }
                }
            }
        } catch (Throwable unused2) {
        }
        return false;
    }

    public static String getFileName(String str) {
        return Md5Util.encode(str);
    }

    public static String getTempFileName(String str) {
        return Md5Util.encode(str) + ".temp";
    }

    public static boolean renameTo(File file, File file2) {
        if (file == null || file2 == null || !file.exists()) {
            return false;
        }
        if (file.renameTo(file2)) {
            return true;
        }
        return copyTo(null, file2);
    }

    public static void tryClose(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void tryClose(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception unused) {
            }
        }
    }
}
