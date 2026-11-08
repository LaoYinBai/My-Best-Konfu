package com.ss.android.downloadlib.applink;

import android.os.Build;
import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.guide.install.IAppInstallCallback;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.logger.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInstallOptimiseHelper {
    private static final String TAG = "AppInstallOptimiseHelper";

    public static void invoke(final NativeDownloadModel nativeDownloadModel, @NonNull final IAppInstallCallback iAppInstallCallback) {
        boolean zIsAppForeground = AppStatusManager.getInstance().isAppForeground();
        if (!zIsAppForeground && Build.VERSION.SDK_INT >= 29) {
            ToolUtils.tryMoveAppToFront();
        }
        boolean zIsAppForeground2 = AppStatusManager.getInstance().isAppForeground();
        if (!zIsAppForeground && zIsAppForeground2 && nativeDownloadModel != null) {
            nativeDownloadModel.setDeeplinkAfterBackApp(true);
        }
        iAppInstallCallback.onInstallApp();
        Logger.d(TAG, "AppInstallOptimiseHelper-->isAppForegroundSecond:::" + zIsAppForeground2);
        if (zIsAppForeground2) {
            return;
        }
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.downloadlib.applink.AppInstallOptimiseHelper.1
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                Logger.d(AppInstallOptimiseHelper.TAG, "AppInstallOptimiseHelper-->onAppForeground");
                AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                if (ToolUtils.isInstalledApp(nativeDownloadModel)) {
                    return;
                }
                nativeDownloadModel.setInstallAfterBackApp(true);
                AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.INSTALL_DELAY_INVOKE, nativeDownloadModel);
                iAppInstallCallback.onInstallApp();
            }
        });
    }
}
