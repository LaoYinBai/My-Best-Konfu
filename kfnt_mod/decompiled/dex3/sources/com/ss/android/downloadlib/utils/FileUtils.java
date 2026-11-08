package com.ss.android.downloadlib.utils;

import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FileUtils {
    public static long getLastModifiedTime(File file) {
        if (file == null || !file.exists()) {
            return 0L;
        }
        return getLastModifiedTime(file, file.lastModified(), 0);
    }

    private static long getLastModifiedTime(File file, long j, int i) {
        File[] fileArrListFiles;
        if (file == null || !file.exists()) {
            return j;
        }
        long jMax = Math.max(j, file.lastModified());
        int i2 = i + 1;
        if (i2 >= 50) {
            return jMax;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                jMax = Math.max(jMax, getLastModifiedTime(file2, jMax, i2));
            }
        }
        return jMax;
    }
}
