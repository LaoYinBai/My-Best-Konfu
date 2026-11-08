package com.ss.android.download.api.config;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface DownloadPermissionChecker {
    boolean hasPermission(@NonNull Context context, @NonNull String str);

    void onRequestPermissionsResult(@NonNull Activity activity, int i, @NonNull String[] strArr, @NonNull int[] iArr);

    void requestPermission(@NonNull Activity activity, @NonNull String[] strArr, IPermissionCallback iPermissionCallback);
}
