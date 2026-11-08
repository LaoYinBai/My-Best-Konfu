package com.huawei.hms.framework.common;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.android.os.BuildEx;
import com.huawei.libcore.io.ExternalStorageFile;
import com.huawei.libcore.io.ExternalStorageFileInputStream;
import com.huawei.libcore.io.ExternalStorageFileOutputStream;
import com.huawei.libcore.io.ExternalStorageRandomAccessFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class CreateFileUtil {
    private static final String BUILDEX_NAME = "com.huawei.android.os.BuildEx";
    private static final String EXTERNAL_FILE_NAME = "com.huawei.libcore.io.ExternalStorageFile";
    private static final String EXTERNAL_INPUTSTREAM_NAME = "com.huawei.libcore.io.ExternalStorageFileInputStream";
    private static final String EXTERNAL_OUTPUTSTREAM_NAME = "com.huawei.libcore.io.ExternalStorageFileOutputStream";
    private static final String RANDOM_ACCESS_FILE_NAME = "com.huawei.libcore.io.ExternalStorageRandomAccessFile";
    private static final String TAG = "CreateFileUtil";

    private static boolean checkCompatible(String str) {
        try {
            tryLoadClass(str);
            return true;
        } catch (Exception unused) {
            Logger.w(TAG, str + "ClassNotFoundException");
            return false;
        }
    }

    public static void deleteSecure(File file) {
        if (file == null || !file.exists() || file.delete()) {
            return;
        }
        Logger.w(TAG, "deleteSecure exception");
    }

    public static void deleteSecure(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        deleteSecure(newFile(str));
    }

    public static String getCacheDirPath(Context context) {
        if (context == null) {
            return "";
        }
        if (Build.VERSION.SDK_INT >= 24) {
            context = context.createDeviceProtectedStorageContext();
        }
        return context.getCacheDir().getPath();
    }

    public static boolean isPVersion() {
        return checkCompatible(BUILDEX_NAME) && BuildEx.VERSION.EMUI_SDK_INT >= 17;
    }

    public static File newFile(String str) {
        if (str == null) {
            return null;
        }
        if (isPVersion() && checkCompatible(EXTERNAL_FILE_NAME)) {
            return new ExternalStorageFile(str);
        }
        return new File(str);
    }

    public static FileInputStream newFileInputStream(String str) throws FileNotFoundException {
        if (str == null) {
            Logger.w(TAG, "newFileInputStream  file is null");
            throw new FileNotFoundException("file is null");
        }
        if (isPVersion() && checkCompatible(EXTERNAL_INPUTSTREAM_NAME)) {
            return new ExternalStorageFileInputStream(str);
        }
        return new FileInputStream(str);
    }

    public static FileOutputStream newFileOutputStream(File file) throws FileNotFoundException {
        if (file == null) {
            Logger.e(TAG, "newFileOutputStream  file is null");
            throw new FileNotFoundException("file is null");
        }
        if (isPVersion() && checkCompatible(EXTERNAL_OUTPUTSTREAM_NAME)) {
            return new ExternalStorageFileOutputStream(file);
        }
        return new FileOutputStream(file);
    }

    public static RandomAccessFile newRandomAccessFile(String str, String str2) throws FileNotFoundException {
        if (str == null) {
            Logger.w(TAG, "newFileOutputStream  file is null");
            throw new FileNotFoundException("file is null");
        }
        if (isPVersion() && checkCompatible(RANDOM_ACCESS_FILE_NAME)) {
            return new ExternalStorageRandomAccessFile(str, str2);
        }
        return new RandomAccessFile(str, str2);
    }

    private static void tryLoadClass(String str) throws ClassNotFoundException {
        ClassLoader classLoader = CreateFileUtil.class.getClassLoader();
        if (classLoader == null) {
            throw new ClassNotFoundException("not found classloader");
        }
        classLoader.loadClass(str);
    }
}
