package com.ss.android.downloadlib.exception;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Safe {

    public interface Run<T> {
        T run();
    }

    public static <T> T tryCatch(boolean z, String str, @NonNull Run<T> run) throws JSONException {
        try {
            return run.run();
        } catch (Throwable th) {
            if (th instanceof MonitorException) {
                throw th;
            }
            TTDownloaderMonitor.inst().monitorException(z, th, str);
            if (TextUtils.isEmpty(str)) {
                throw th;
            }
            return null;
        }
    }

    public static <T> T noCatch(Run<T> run) {
        return (T) tryCatch(true, (String) null, (Run) run);
    }

    public static void noCatch(final Runnable runnable) {
        noCatch(new Run<Void>() { // from class: com.ss.android.downloadlib.exception.Safe.1
            @Override // com.ss.android.downloadlib.exception.Safe.Run
            public Void run() {
                runnable.run();
                return null;
            }
        });
    }

    public static <T> T tryCatch(@NonNull String str, Run<T> run) {
        return (T) tryCatch(true, str, (Run) run);
    }

    public static void tryCatch(boolean z, @NonNull String str, final Runnable runnable) throws JSONException {
        tryCatch(z, str, new Run<Void>() { // from class: com.ss.android.downloadlib.exception.Safe.2
            @Override // com.ss.android.downloadlib.exception.Safe.Run
            public Void run() {
                runnable.run();
                return null;
            }
        });
    }

    public static void tryCatch(@NonNull String str, Runnable runnable) throws JSONException {
        tryCatch(true, str, runnable);
    }
}
