package com.ss.android.download.api;

import androidx.annotation.NonNull;
import com.ss.android.download.api.config.AppStatusChangeListener;
import com.ss.android.download.api.config.DownloadActionListener;
import com.ss.android.download.api.config.DownloadAutoInstallInterceptListener;
import com.ss.android.download.api.config.DownloadClearSpaceListener;
import com.ss.android.download.api.config.DownloadEventLogger;
import com.ss.android.download.api.config.DownloadNetworkFactory;
import com.ss.android.download.api.config.DownloadPermissionChecker;
import com.ss.android.download.api.config.DownloadSettings;
import com.ss.android.download.api.config.DownloadTLogger;
import com.ss.android.download.api.config.DownloadUIFactory;
import com.ss.android.download.api.config.IApkUpdateHandler;
import com.ss.android.download.api.config.ICleanManager;
import com.ss.android.download.api.config.IDownloadCustomChecker;
import com.ss.android.download.api.config.IDownloaderMonitor;
import com.ss.android.download.api.config.IEncryptor;
import com.ss.android.download.api.config.IOpenAppListener;
import com.ss.android.download.api.config.IUrlHandler;
import com.ss.android.download.api.config.IUserInfoListener;
import com.ss.android.download.api.guide.IInstallGuideViewListener;
import com.ss.android.download.api.model.AppInfo;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadFileUriProvider;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadMonitorListener;
import com.ss.android.socialbase.downloader.downloader.DownloaderBuilder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface DownloadConfigure {
    void configEnd();

    DownloadConfigure initDownloader(DownloaderBuilder downloaderBuilder);

    DownloadConfigure setActionListener(@NonNull DownloadActionListener downloadActionListener);

    DownloadConfigure setApkUpdateHandler(@NonNull IApkUpdateHandler iApkUpdateHandler);

    DownloadConfigure setAppDownloadFileUriProvider(IAppDownloadFileUriProvider iAppDownloadFileUriProvider);

    DownloadConfigure setAppInfo(@NonNull AppInfo appInfo);

    DownloadConfigure setAppStatusChangeListener(@NonNull AppStatusChangeListener appStatusChangeListener);

    DownloadConfigure setCleanManager(ICleanManager iCleanManager);

    DownloadConfigure setDownloadAutoInstallInterceptListener(@NonNull DownloadAutoInstallInterceptListener downloadAutoInstallInterceptListener);

    DownloadConfigure setDownloadClearSpaceListener(DownloadClearSpaceListener downloadClearSpaceListener);

    DownloadConfigure setDownloadCustomChecker(IDownloadCustomChecker iDownloadCustomChecker);

    DownloadConfigure setDownloadMonitorListener(@NonNull IAppDownloadMonitorListener iAppDownloadMonitorListener);

    DownloadConfigure setDownloadNetworkFactory(@NonNull DownloadNetworkFactory downloadNetworkFactory);

    DownloadConfigure setDownloadPermissionChecker(@NonNull DownloadPermissionChecker downloadPermissionChecker);

    DownloadConfigure setDownloadSettings(@NonNull DownloadSettings downloadSettings);

    DownloadConfigure setDownloadTLogger(DownloadTLogger downloadTLogger);

    DownloadConfigure setDownloadUIFactory(@NonNull DownloadUIFactory downloadUIFactory);

    DownloadConfigure setDownloaderMonitor(IDownloaderMonitor iDownloaderMonitor);

    DownloadConfigure setEncryptor(IEncryptor iEncryptor);

    DownloadConfigure setEventLogger(@NonNull DownloadEventLogger downloadEventLogger);

    DownloadConfigure setFileProviderAuthority(String str);

    DownloadConfigure setInstallGuideViewListener(IInstallGuideViewListener iInstallGuideViewListener);

    DownloadConfigure setLogLevel(int i);

    DownloadConfigure setOpenAppListener(IOpenAppListener iOpenAppListener);

    DownloadConfigure setUrlHandler(IUrlHandler iUrlHandler);

    DownloadConfigure setUseReflectParseRes(boolean z);

    DownloadConfigure setUserInfoListener(IUserInfoListener iUserInfoListener);
}
