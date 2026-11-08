package com.ss.android.socialbase.appdownloader.depend;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAppDownloadDepend {
    boolean getAllowBootReceiver();

    boolean getAllowNetwork(Context context);

    IDownloadAlertDialogBuilder getThemedAlertDlgBuilder(Context context);
}
