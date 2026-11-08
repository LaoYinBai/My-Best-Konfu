package com.kwad.sdk.api.loader;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class g {
    private static File a;

    private static File a(Context context) {
        if (a == null) {
            a = c(new File(context.getApplicationInfo().dataDir, "ksad_dynamic"));
        }
        return a;
    }

    static File a(Context context, String str) {
        return new File(a(context), "dynamic-" + System.currentTimeMillis() + "-" + str + ".apk");
    }

    static void a(File file) {
        File[] fileArrListFiles;
        if (!file.isFile() && (fileArrListFiles = file.listFiles()) != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                a(file2);
            }
        }
        file.delete();
    }

    static String b(Context context, String str) {
        return c(new File(a(context), "apk-" + str)).getPath();
    }

    static void b(File file) {
        try {
            a(file);
        } catch (Exception unused) {
        }
    }

    static File c(Context context, String str) {
        return c(new File(a(context), "apk-" + str));
    }

    private static File c(File file) {
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        if (file.exists() && file.isDirectory()) {
            return file;
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.exists() && file.isDirectory()) {
            return file;
        }
        throw new RuntimeException("Can not ensureDir:" + file);
    }

    static String d(Context context, String str) {
        return new File(b(context, str), "dynamic.apk").getPath();
    }

    static String e(Context context, String str) {
        return c(new File(b(context, str), "dex")).getPath();
    }

    static String f(Context context, String str) {
        return c(new File(b(context, str), "libs")).getPath();
    }

    static void g(final Context context, final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        i.a(new Runnable() { // from class: com.kwad.sdk.api.loader.g.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    File[] fileArrListFiles = g.c(context, str).getParentFile().listFiles();
                    if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                        return;
                    }
                    for (File file : fileArrListFiles) {
                        if (f.a(str, file.getName().substring(file.getName().indexOf("-") + 1))) {
                            g.a(file);
                        }
                    }
                } catch (Exception unused) {
                }
            }
        });
    }
}
