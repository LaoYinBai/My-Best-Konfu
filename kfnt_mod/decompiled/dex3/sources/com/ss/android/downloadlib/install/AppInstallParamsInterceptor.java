package com.ss.android.downloadlib.install;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.socialbase.appdownloader.depend.IAppInstallInterceptCallback;
import com.ss.android.socialbase.appdownloader.depend.IBeforeAppInstallInterceptor;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInstallParamsInterceptor implements IBeforeAppInstallInterceptor {
    @Override // com.ss.android.socialbase.appdownloader.depend.IBeforeAppInstallInterceptor
    public void intercept(DownloadInfo downloadInfo, IAppInstallInterceptCallback iAppInstallInterceptCallback) {
        NativeDownloadModel nativeModelByInfo;
        if (downloadInfo != null && (nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo)) != null) {
            downloadInfo.setLinkMode(nativeModelByInfo.getLinkMode());
        }
        if (iAppInstallInterceptCallback != null) {
            iAppInstallInterceptCallback.onInterceptFinish();
        }
    }
}
