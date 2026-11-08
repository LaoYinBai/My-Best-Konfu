package com.ss.union.game.sdk.core.glide.load.engine.executor;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class a {
    private static final String a = "GlideRuntimeCompat";
    private static final String b = "cpu[0-9]+";
    private static final String c = "/sys/devices/system/cpu/";

    private a() {
    }

    static int a() {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        return Build.VERSION.SDK_INT < 17 ? Math.max(b(), iAvailableProcessors) : iAvailableProcessors;
    }

    private static int b() {
        File[] fileArrListFiles;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                File file = new File(c);
                final Pattern patternCompile = Pattern.compile(b);
                fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: com.ss.union.game.sdk.core.glide.load.engine.executor.a.1
                    @Override // java.io.FilenameFilter
                    public boolean accept(File file2, String str) {
                        return patternCompile.matcher(str).matches();
                    }
                });
            } catch (Throwable th) {
                if (Log.isLoggable(a, 6)) {
                    Log.e(a, "Failed to calculate accurate cpu count", th);
                }
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                fileArrListFiles = null;
            }
            return Math.max(1, fileArrListFiles != null ? fileArrListFiles.length : 0);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }
}
