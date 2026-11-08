package com.kwad.sdk.crash.kwai;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static Context a;
    private static String b;

    public static File a() {
        File file = !TextUtils.isEmpty(b) ? new File(b) : new File(a(a), "kwad_ex");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    private static File a(Context context) {
        File dataDir = Build.VERSION.SDK_INT >= 24 ? context.getDataDir() : null;
        if (dataDir != null) {
            return dataDir;
        }
        File file = new File(Environment.getDataDirectory().getPath() + "/data/" + context.getPackageName());
        if (file.exists()) {
            return file;
        }
        return new File("/data/data/" + context.getPackageName());
    }

    public static void a(@NonNull Context context, @Nullable String str) {
        a = context;
        b = str;
    }

    public static File b() {
        return new File(a(), "java_crash/dump");
    }
}
