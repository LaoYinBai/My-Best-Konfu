package com.bytedance.pangle.helper;

import android.app.Application;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusConstants;
import java.io.File;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PluginDirHelper {
    public static final String VERSION_PREFIX = "version-";
    private static File sBaseDir;
    private static File sPluginDir;

    private static void init() {
        if (sBaseDir == null) {
            File file = new File(Zeus.getAppApplication().getFilesDir(), ZeusConstants.BASE_LIB_NAME + "_p");
            sBaseDir = file;
            ensureDirExists(file);
        }
    }

    public static String ensureDirExists(File file) {
        if (file == null) {
            return null;
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getPath();
    }

    private static String buildPath(String... strArr) {
        init();
        File file = sBaseDir;
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    file = new File(file, str);
                }
            }
        }
        return ensureDirExists(file);
    }

    public static void setPluginDir(File file) {
        sPluginDir = file;
    }

    public static String getBaseDir() {
        init();
        return sBaseDir.getPath();
    }

    public static String getDownloadDir() {
        Application appApplication = Zeus.getAppApplication();
        if (sPluginDir == null) {
            sPluginDir = new File(appApplication.getFilesDir(), "." + ZeusConstants.BASE_LIB_NAME + "_d");
        }
        return ensureDirExists(sPluginDir);
    }

    public static String getPushDir() {
        Application appApplication = Zeus.getAppApplication();
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                return null;
            }
            File externalFilesDir = appApplication.getExternalFilesDir("." + ZeusConstants.BASE_LIB_NAME + "_d");
            if (externalFilesDir != null) {
                return ensureDirExists(externalFilesDir);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPackageDir(String str) {
        return buildPath(str);
    }

    public static String getPackageVersionDir(String str, int i) {
        return buildPath(str, VERSION_PREFIX.concat(String.valueOf(i)));
    }

    public static String getDataDir(String str) {
        return buildPath(str, "data");
    }

    public static String getSourceFile(String str, int i) {
        return new File(buildPath(str, VERSION_PREFIX.concat(String.valueOf(i)), "apk"), "base-1.apk").getPath();
    }

    public static String getDalvikCacheDir(String str, int i) {
        return buildPath(str, VERSION_PREFIX.concat(String.valueOf(i)), "dalvik-cache");
    }

    public static String getNativeLibraryDir(String str, int i) {
        return buildPath(str, VERSION_PREFIX.concat(String.valueOf(i)), "lib");
    }

    public static boolean isPackageVersionDirExists(@NonNull String str, int i) {
        return new File(getBaseDir() + File.separator + str + File.separator + VERSION_PREFIX + i).exists();
    }
}
