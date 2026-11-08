package com.kwai.sodler.lib.b;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    public static File a(Context context, File file, String str, File file2) throws IOException {
        com.kwai.sodler.lib.a.a("plugin.so", "--");
        String strB = a.b();
        File file3 = null;
        if (TextUtils.isEmpty(strB)) {
            com.kwai.sodler.lib.a.e("plugin.so", "Cpu abis is null.");
        } else {
            com.kwai.sodler.lib.a.a("plugin.so", "Try install soLib, supported abi = " + strB);
            File file4 = new File(file, "lib" + File.separator + strB + File.separator + str);
            if (file4.exists()) {
                File file5 = new File(file2, str);
                if (!file4.renameTo(file5)) {
                    throw new IOException("Rename soLib fail.");
                }
                com.kwai.sodler.lib.a.a("plugin.so", "Rename soLib, from = " + file4.getAbsolutePath() + ", to = " + file5.getAbsolutePath());
                file3 = file5;
            }
        }
        if (file3 == null) {
            com.kwai.sodler.lib.a.b("plugin.so", "Can not install " + str + ", NO_MATCHING_ABIS");
        }
        com.kwai.sodler.lib.a.a("plugin.so", "--");
        return file3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:75:0x015c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15, types: [java.io.BufferedInputStream, java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Set<java.lang.String> a(java.io.File r9, java.io.File r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.sodler.lib.b.c.a(java.io.File, java.io.File):java.util.Set");
    }
}
