package com.unity3d.splash.services.core.cache;

import com.unity3d.splash.services.core.log.DeviceLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CacheDirectory {
    private static final String TEST_FILE_NAME = "UnityAdsTest.txt";
    private String _cacheDirName;
    private boolean _initialized = false;
    private File _cacheDirectory = null;
    private CacheDirectoryType _type = null;

    public CacheDirectory(String str) {
        this._cacheDirName = str;
    }

    private void createNoMediaFile(File file) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            if (new File(file, ".nomedia").createNewFile()) {
                DeviceLog.debug("Successfully created .nomedia file");
            } else {
                DeviceLog.debug("Using existing .nomedia file");
            }
        } catch (Exception e) {
            DeviceLog.exception("Failed to create .nomedia file", e);
        }
    }

    public File createCacheDirectory(File file, String str) {
        if (file == null) {
            return null;
        }
        File file2 = new File(file, str);
        file2.mkdirs();
        if (file2.isDirectory()) {
            return file2;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.io.File getCacheDirectory(android.content.Context r4) throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            r3 = this;
            boolean r0 = r3._initialized
            if (r0 == 0) goto L7
            java.io.File r4 = r3._cacheDirectory
            return r4
        L7:
            r0 = 1
            r3._initialized = r0
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 18
            r2 = 0
            if (r0 <= r1) goto L5b
            java.lang.String r0 = "mounted"
            java.lang.String r1 = android.os.Environment.getExternalStorageState()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L56
            java.io.File r0 = r4.getExternalCacheDir()     // Catch: java.lang.Exception -> L28
            java.lang.String r1 = r3._cacheDirName     // Catch: java.lang.Exception -> L28
            java.io.File r0 = r3.createCacheDirectory(r0, r1)     // Catch: java.lang.Exception -> L28
            goto L2f
        L28:
            r0 = move-exception
            java.lang.String r1 = "Creating external cache directory failed"
            com.unity3d.splash.services.core.log.DeviceLog.exception(r1, r0)
            r0 = r2
        L2f:
            boolean r1 = r3.testCacheDirectory(r0)
            if (r1 == 0) goto L5b
            r3.createNoMediaFile(r0)
            r3._cacheDirectory = r0
            com.unity3d.splash.services.core.cache.CacheDirectoryType r4 = com.unity3d.splash.services.core.cache.CacheDirectoryType.EXTERNAL
            r3._type = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "Unity Ads is using external cache directory: "
            r4.<init>(r1)
            java.lang.String r0 = r0.getAbsolutePath()
            r4.append(r0)
            java.lang.String r4 = r4.toString()
        L50:
            com.unity3d.splash.services.core.log.DeviceLog.debug(r4)
            java.io.File r4 = r3._cacheDirectory
            return r4
        L56:
            java.lang.String r0 = "External media not mounted"
            com.unity3d.splash.services.core.log.DeviceLog.debug(r0)
        L5b:
            java.io.File r4 = r4.getFilesDir()
            boolean r0 = r3.testCacheDirectory(r4)
            if (r0 == 0) goto L7e
            r3._cacheDirectory = r4
            com.unity3d.splash.services.core.cache.CacheDirectoryType r0 = com.unity3d.splash.services.core.cache.CacheDirectoryType.INTERNAL
            r3._type = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unity Ads is using internal cache directory: "
            r0.<init>(r1)
            java.lang.String r4 = r4.getAbsolutePath()
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            goto L50
        L7e:
            java.lang.String r4 = "Unity Ads failed to initialize cache directory"
            com.unity3d.splash.services.core.log.DeviceLog.error(r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.splash.services.core.cache.CacheDirectory.getCacheDirectory(android.content.Context):java.io.File");
    }

    public CacheDirectoryType getType() {
        return this._type;
    }

    public boolean testCacheDirectory(File file) throws IllegalAccessException, NoSuchMethodException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (file != null && file.isDirectory()) {
            try {
                byte[] bytes = "test".getBytes("UTF-8");
                byte[] bArr = new byte[bytes.length];
                File file2 = new File(file, TEST_FILE_NAME);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
                fileOutputStream.close();
                FileInputStream fileInputStream = new FileInputStream(file2);
                int i = fileInputStream.read(bArr, 0, bArr.length);
                fileInputStream.close();
                if (!file2.delete()) {
                    DeviceLog.debug("Failed to delete testfile " + file2.getAbsoluteFile());
                    return false;
                }
                if (i != bArr.length) {
                    DeviceLog.debug("Read buffer size mismatch");
                    return false;
                }
                if (new String(bArr, "UTF-8").equals("test")) {
                    return true;
                }
                DeviceLog.debug("Read buffer content mismatch");
                return false;
            } catch (Exception e) {
                DeviceLog.debug("Unity Ads exception while testing cache directory " + file.getAbsolutePath() + ": " + e.getMessage());
            }
        }
        return false;
    }
}
