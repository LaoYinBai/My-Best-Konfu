package com.kwad.sdk;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.kwad.sdk.export.proxy.AdDownloadProxy;
import com.kwad.sdk.export.proxy.AdHttpProxy;
import com.kwad.sdk.export.proxy.AdInstallProxy;
import com.kwad.sdk.utils.aq;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    @Nullable
    public static AdDownloadProxy a(Context context, AdInstallProxy adInstallProxy, boolean z) {
        try {
            return com.kwad.sdk.core.download.a.f.a(context, aq.c(context), adInstallProxy, z && b(context));
        } catch (Throwable unused) {
            return null;
        }
    }

    @NonNull
    public static AdHttpProxy a() {
        try {
            return com.kwad.sdk.core.network.kwai.b.a() != null ? new com.kwad.sdk.core.network.b.b() : new com.kwad.sdk.core.network.b.a();
        } catch (Throwable unused) {
            return new com.kwad.sdk.core.network.b.a();
        }
    }

    public static File a(Context context) {
        String externalStorageState;
        File externalCacheDir;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (IncompatibleClassChangeError | NullPointerException unused) {
            externalStorageState = "";
        }
        String path = null;
        if ("mounted".equals(externalStorageState) || !Environment.isExternalStorageRemovable()) {
            try {
                externalCacheDir = context.getExternalCacheDir();
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
                externalCacheDir = null;
            }
            if (externalCacheDir != null) {
                path = externalCacheDir.getPath();
            }
        }
        if (TextUtils.isEmpty(path)) {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + "ksadsdk");
    }

    @NonNull
    public static AdInstallProxy b() {
        return new com.kwad.sdk.core.download.c.b();
    }

    private static boolean b(Context context) {
        try {
            new NotificationCompat.Builder(context, "");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
