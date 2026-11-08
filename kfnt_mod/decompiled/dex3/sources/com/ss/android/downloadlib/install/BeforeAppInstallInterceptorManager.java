package com.ss.android.downloadlib.install;

import com.ss.android.socialbase.appdownloader.depend.IAppInstallInterceptCallback;
import com.ss.android.socialbase.appdownloader.depend.IBeforeAppInstallInterceptor;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BeforeAppInstallInterceptorManager implements IBeforeAppInstallInterceptor {
    private static volatile BeforeAppInstallInterceptorManager sInstance;
    private List<IBeforeAppInstallInterceptor> mInterceptors = new ArrayList();

    public static BeforeAppInstallInterceptorManager getInstance() {
        if (sInstance == null) {
            synchronized (BeforeAppInstallInterceptorManager.class) {
                if (sInstance == null) {
                    sInstance = new BeforeAppInstallInterceptorManager();
                }
            }
        }
        return sInstance;
    }

    private BeforeAppInstallInterceptorManager() {
        this.mInterceptors.add(new AppInstallParamsInterceptor());
        this.mInterceptors.add(new AppInstallInvokeInterceptor());
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IBeforeAppInstallInterceptor
    public void intercept(DownloadInfo downloadInfo, IAppInstallInterceptCallback iAppInstallInterceptCallback) {
        if (downloadInfo != null && this.mInterceptors.size() != 0) {
            executeInterceptors(downloadInfo, 0, iAppInstallInterceptCallback);
        } else if (iAppInstallInterceptCallback != null) {
            iAppInstallInterceptCallback.onInterceptFinish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeInterceptors(final DownloadInfo downloadInfo, final int i, final IAppInstallInterceptCallback iAppInstallInterceptCallback) {
        if (i == this.mInterceptors.size() || i < 0) {
            iAppInstallInterceptCallback.onInterceptFinish();
        } else {
            this.mInterceptors.get(i).intercept(downloadInfo, new IAppInstallInterceptCallback() { // from class: com.ss.android.downloadlib.install.BeforeAppInstallInterceptorManager.1
                @Override // com.ss.android.socialbase.appdownloader.depend.IAppInstallInterceptCallback
                public void onInterceptFinish() {
                    BeforeAppInstallInterceptorManager.this.executeInterceptors(downloadInfo, i + 1, iAppInstallInterceptCallback);
                }
            });
        }
    }
}
