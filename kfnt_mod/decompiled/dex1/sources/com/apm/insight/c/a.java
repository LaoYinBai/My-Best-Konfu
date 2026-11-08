package com.apm.insight.c;

import com.apm.insight.h;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    public static void a() {
        File[] fileArrListFiles;
        File file = new File(h.g().getFilesDir(), "apminsight/crashCommand");
        if (file.exists() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                try {
                    String[] strArrSplit = file2.getName().split("_");
                    if (strArrSplit[0].equals(String.valueOf(0))) {
                        a(strArrSplit);
                    }
                    file2.delete();
                } catch (Throwable th) {
                    com.apm.insight.b.a().a("NPTH_CATCH", th);
                    try {
                        file2.delete();
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    private static void a(String... strArr) {
    }
}
