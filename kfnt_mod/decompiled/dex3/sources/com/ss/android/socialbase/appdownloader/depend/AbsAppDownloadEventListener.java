package com.ss.android.socialbase.appdownloader.depend;

import android.content.Context;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AbsAppDownloadEventListener implements IAppDownloadEventListener {
    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventListener
    public String getNotifyProcessName() {
        return null;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventListener
    public boolean installIntercept(boolean z) {
        return false;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventListener
    public void onAppDownloadCancel(DownloadInfo downloadInfo) {
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventListener
    public void onAppDownloadEvent(int i, String str, int i2, long j) {
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventListener
    public void onAppInstallError(int i, String str, String str2, String str3) {
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventListener
    public void onAppInstalled(Context context, String str) {
    }
}
