package com.ss.android.socialbase.appdownloader.depend;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AbsAppDownloadDepend implements IAppDownloadDepend {
    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend
    public boolean getAllowBootReceiver() {
        return true;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend
    public boolean getAllowNetwork(Context context) {
        return true;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend
    public IDownloadAlertDialogBuilder getThemedAlertDlgBuilder(Context context) {
        return null;
    }

    public boolean isForbiddenInstallForInvalidatePackageName() {
        return false;
    }

    public boolean needClearWhenTaskReset() {
        return true;
    }
}
