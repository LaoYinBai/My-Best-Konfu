package com.ss.android.download.api.common;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.ss.android.download.api.config.DownloadPermissionChecker;
import com.ss.android.download.api.config.IPermissionCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultPermissionChecker implements DownloadPermissionChecker {
    private IPermissionCallback mPermissionCallback;

    @Override // com.ss.android.download.api.config.DownloadPermissionChecker
    public void requestPermission(@NonNull Activity activity, @NonNull String[] strArr, IPermissionCallback iPermissionCallback) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.mPermissionCallback = iPermissionCallback;
            activity.requestPermissions(strArr, 1);
        } else if (iPermissionCallback != null) {
            iPermissionCallback.onGranted();
        }
    }

    @Override // com.ss.android.download.api.config.DownloadPermissionChecker
    public boolean hasPermission(@Nullable Context context, @NonNull String str) {
        return context != null && ContextCompat.checkSelfPermission(context, str) == 0;
    }

    @Override // com.ss.android.download.api.config.DownloadPermissionChecker
    public void onRequestPermissionsResult(@NonNull Activity activity, int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (iArr.length <= 0 || this.mPermissionCallback == null) {
            return;
        }
        if (iArr[0] == -1) {
            this.mPermissionCallback.onDenied(strArr[0]);
        } else if (iArr[0] == 0) {
            this.mPermissionCallback.onGranted();
        }
    }
}
