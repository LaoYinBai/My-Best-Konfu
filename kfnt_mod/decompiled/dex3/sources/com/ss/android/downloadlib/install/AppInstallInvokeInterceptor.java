package com.ss.android.downloadlib.install;

import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.applink.AppInstallOptimiseHelper;
import com.ss.android.downloadlib.applink.AppLinkOptimiseHelper;
import com.ss.android.downloadlib.applink.KllkOptimiseHelper;
import com.ss.android.downloadlib.guide.install.IAppInstallCallback;
import com.ss.android.socialbase.appdownloader.depend.IAppInstallInterceptCallback;
import com.ss.android.socialbase.appdownloader.depend.IBeforeAppInstallInterceptor;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInstallInvokeInterceptor implements IBeforeAppInstallInterceptor {
    @Override // com.ss.android.socialbase.appdownloader.depend.IBeforeAppInstallInterceptor
    public void intercept(DownloadInfo downloadInfo, final IAppInstallInterceptCallback iAppInstallInterceptCallback) {
        intercept(downloadInfo, new IAppInstallCallback() { // from class: com.ss.android.downloadlib.install.AppInstallInvokeInterceptor.1
            @Override // com.ss.android.downloadlib.guide.install.IAppInstallCallback
            public void onInstallApp() {
                iAppInstallInterceptCallback.onInterceptFinish();
            }
        });
    }

    public void intercept(final DownloadInfo downloadInfo, @NonNull final IAppInstallCallback iAppInstallCallback) {
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        if (nativeModelByInfo != null && KllkOptimiseHelper.canInvokeAppFormOppoBackground(nativeModelByInfo)) {
            TTDelegateActivity.installApk(nativeModelByInfo, new IAppInstallCallback() { // from class: com.ss.android.downloadlib.install.AppInstallInvokeInterceptor.2
                @Override // com.ss.android.downloadlib.guide.install.IAppInstallCallback
                public void onInstallApp() {
                    AppInstallInvokeInterceptor.this.installApp(downloadInfo, iAppInstallCallback);
                }
            });
        } else {
            installApp(downloadInfo, iAppInstallCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installApp(DownloadInfo downloadInfo, @NonNull final IAppInstallCallback iAppInstallCallback) {
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        boolean zSwitchIsOpen = AppLinkOptimiseHelper.switchIsOpen(nativeModelByInfo);
        boolean zInstallSwitchIsOpen = AppLinkOptimiseHelper.installSwitchIsOpen(nativeModelByInfo);
        if (!zSwitchIsOpen || !zInstallSwitchIsOpen) {
            iAppInstallCallback.onInstallApp();
        } else {
            AppInstallOptimiseHelper.invoke(nativeModelByInfo, new IAppInstallCallback() { // from class: com.ss.android.downloadlib.install.AppInstallInvokeInterceptor.3
                @Override // com.ss.android.downloadlib.guide.install.IAppInstallCallback
                public void onInstallApp() {
                    iAppInstallCallback.onInstallApp();
                }
            });
        }
    }
}
