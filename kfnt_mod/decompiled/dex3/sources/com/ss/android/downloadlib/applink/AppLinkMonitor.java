package com.ss.android.downloadlib.applink;

import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.common.AppStatusManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppLinkMonitor implements AppStatusManager.AppStatusChangeListener {
    public static final long DELAY_TIME = 5000;
    private long lastForegroundStamp;

    @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppBackground() {
    }

    private static class SingleTonHolder {
        private static AppLinkMonitor INSTANCE = new AppLinkMonitor();

        private SingleTonHolder() {
        }
    }

    public static AppLinkMonitor getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private AppLinkMonitor() {
        this.lastForegroundStamp = 0L;
        AppStatusManager.getInstance().registerAppSwitchListener(this);
    }

    @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppForeground() {
        this.lastForegroundStamp = System.currentTimeMillis();
    }

    public void checkAppLinkResult(final AppLinkEventCallback appLinkEventCallback, final long j) {
        if (appLinkEventCallback == null) {
            return;
        }
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.applink.AppLinkMonitor.1
            @Override // java.lang.Runnable
            public void run() {
                if (!AppStatusManager.getInstance().isAppFocus() || System.currentTimeMillis() - AppLinkMonitor.this.lastForegroundStamp <= j) {
                    appLinkEventCallback.onResult(true);
                } else {
                    appLinkEventCallback.onResult(false);
                }
            }
        }, j);
    }

    public void checkAppLinkResult(AppLinkEventCallback appLinkEventCallback) {
        checkAppLinkResult(appLinkEventCallback, DELAY_TIME);
    }

    public void checkMarketOrInstaller(AppLinkEventCallback appLinkEventCallback) {
        if (appLinkEventCallback == null) {
            return;
        }
        checkAppLinkResult(appLinkEventCallback, GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.CHECK_AN_RESULT_DELAY, 1200) > 0 ? r1 : 1200);
    }
}
