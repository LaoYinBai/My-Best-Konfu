package com.ss.android.downloadlib.applink;

import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.DownloadComponentManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInstallFinishInterceptor {
    public void invokeApp(@NonNull final NativeDownloadModel nativeDownloadModel, @NonNull final IAppDeepLinkCallback iAppDeepLinkCallback, int i) {
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.applink.AppInstallFinishInterceptor.1
            @Override // java.lang.Runnable
            public void run() {
                if (KllkOptimiseHelper.canInvokeAppFormOppoBackground(nativeDownloadModel)) {
                    iAppDeepLinkCallback.onAppLink(false);
                } else if (!AppLinkOptimiseHelper.switchIsOpen(nativeDownloadModel)) {
                    iAppDeepLinkCallback.onAppLink(false);
                } else {
                    AppLinkOptimiseHelper.invoke(nativeDownloadModel, new IAppLinkOptimiseCallback() { // from class: com.ss.android.downloadlib.applink.AppInstallFinishInterceptor.1.1
                        @Override // com.ss.android.downloadlib.applink.IAppLinkOptimiseCallback
                        public void invoke(boolean z) {
                            iAppDeepLinkCallback.onAppLink(z);
                        }
                    });
                }
            }
        }, i);
    }
}
