package com.sigmob.sdk.b;

import android.content.Context;
import android.os.Environment;
import com.sigmob.logger.SigmobLog;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class r {
    public static File a(Context context) {
        return new File(a(context, true), "video-cache");
    }

    private static File a(Context context, boolean z) {
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            externalStorageState = "";
        }
        File fileB = (z && "mounted".equals(externalStorageState)) ? b(context) : null;
        if (fileB == null) {
            fileB = context.getCacheDir();
        }
        if (fileB != null) {
            return fileB;
        }
        String str = "/data/data/" + context.getPackageName() + "/cache/";
        SigmobLog.w("Can't define system cache directory! '" + str + "%s' will be used.");
        return new File(str);
    }

    private static File b(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        SigmobLog.w("Unable to create external cache directory");
        return null;
    }
}
