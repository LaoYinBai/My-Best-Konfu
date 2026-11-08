package com.ss.android.socialbase.appdownloader.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppInstallUtils {
    public static void startInstall(DownloadInfo downloadInfo) {
        installApk(downloadInfo);
    }

    private static void installApk(final DownloadInfo downloadInfo) {
        final Context appContext = DownloadComponentManager.getAppContext();
        boolean z = true;
        if (((downloadInfo.isAutoResumed() && !downloadInfo.isShowNotificationForNetworkResumed()) || AppDownloadUtils.canNotAutoInstall(downloadInfo.getExtra()) || TextUtils.isEmpty(downloadInfo.getMimeType()) || !downloadInfo.getMimeType().equals("application/vnd.android.package-archive")) && DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.AUTO_INSTALL_WHEN_RESUME, 0) != 1) {
            z = false;
        }
        final int iStartViewIntent = z ? AppDownloadUtils.startViewIntent(appContext, downloadInfo.getId(), false) : 2;
        DownloadComponentManager.getCPUThreadExecutor().execute(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.util.AppInstallUtils.1
            @Override // java.lang.Runnable
            public void run() {
                IAppDownloadEventHandler appDownloadEventHandler = AppDownloader.getInstance().getAppDownloadEventHandler();
                IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(appContext).getDownloadNotificationEventListener(downloadInfo.getId());
                if (appDownloadEventHandler == null && downloadNotificationEventListener == null) {
                    return;
                }
                File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
                if (file.exists()) {
                    try {
                        PackageInfo packageInfo = AppDownloadUtils.getPackageInfo(downloadInfo, file);
                        if (packageInfo != null) {
                            String packageName = (iStartViewIntent == 1 || TextUtils.isEmpty(downloadInfo.getPackageName())) ? packageInfo.packageName : downloadInfo.getPackageName();
                            if (appDownloadEventHandler != null) {
                                appDownloadEventHandler.handleDownloadEvent(downloadInfo.getId(), 1, packageName, -3, downloadInfo.getDownloadTime());
                            }
                            if (downloadNotificationEventListener != null) {
                                downloadNotificationEventListener.onNotificationEvent(1, downloadInfo, packageName, "");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
