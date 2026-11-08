package com.ss.android.downloadlib;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.download.api.DownloadConfigure;
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
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.DownloadInsideHelper;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.applink.AdAppLinkUtils;
import com.ss.android.downloadlib.downloader.ApkUpdateManager;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.OpenAppUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.DownloadHandlerService;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadFileUriProvider;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadMonitorListener;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import com.ss.android.socialbase.appdownloader.util.SecurityUtils;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.DownloaderBuilder;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class DownloadConfigureImpl implements DownloadConfigure {
    DownloadConfigureImpl() {
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setApkUpdateHandler(@NonNull IApkUpdateHandler iApkUpdateHandler) {
        GlobalInfo.setApkUpdateHandler(iApkUpdateHandler);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloadPermissionChecker(@NonNull DownloadPermissionChecker downloadPermissionChecker) {
        GlobalInfo.setDownloadPermissionChecker(downloadPermissionChecker);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setEventLogger(@NonNull DownloadEventLogger downloadEventLogger) {
        GlobalInfo.setDownloadEventLogger(downloadEventLogger);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloadUIFactory(@NonNull DownloadUIFactory downloadUIFactory) {
        GlobalInfo.setDownloadUIFactory(downloadUIFactory);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloadNetworkFactory(@NonNull DownloadNetworkFactory downloadNetworkFactory) {
        GlobalInfo.setDownloadNetworkFactory(downloadNetworkFactory);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setActionListener(@NonNull DownloadActionListener downloadActionListener) {
        GlobalInfo.setDownloadActionListener(downloadActionListener);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloadMonitorListener(@NonNull IAppDownloadMonitorListener iAppDownloadMonitorListener) {
        GlobalInfo.setMonitorListener(iAppDownloadMonitorListener);
        AppDownloader.getInstance().setAppDownloadMonitorListener(iAppDownloadMonitorListener);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloadSettings(@NonNull DownloadSettings downloadSettings) {
        GlobalInfo.setDownloadSettings(downloadSettings);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setAppInfo(@NonNull AppInfo appInfo) {
        GlobalInfo.setAppInfo(appInfo);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setFileProviderAuthority(String str) {
        GlobalInfo.setFileProviderAuthority(str);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setAppStatusChangeListener(@NonNull final AppStatusChangeListener appStatusChangeListener) {
        GlobalInfo.setAppStatusChangeListener(appStatusChangeListener);
        AppStatusManager.getInstance().setInnerAppStatusChangeCaller(new AppStatusManager.InnerAppStatusChangeCaller() { // from class: com.ss.android.downloadlib.DownloadConfigureImpl.1
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.InnerAppStatusChangeCaller
            public boolean isAppInBackground() {
                return appStatusChangeListener.isAppInBackground();
            }
        });
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setLogLevel(int i) {
        GlobalInfo.setLogLevel(i);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloadAutoInstallInterceptListener(@NonNull DownloadAutoInstallInterceptListener downloadAutoInstallInterceptListener) {
        GlobalInfo.setDownloadAutoInstallInterceptListener(downloadAutoInstallInterceptListener);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloadClearSpaceListener(DownloadClearSpaceListener downloadClearSpaceListener) {
        GlobalInfo.setDownloadClearSpaceListener(downloadClearSpaceListener);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloadCustomChecker(IDownloadCustomChecker iDownloadCustomChecker) {
        GlobalInfo.setDownloadCustomChecker(iDownloadCustomChecker);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloadTLogger(DownloadTLogger downloadTLogger) {
        GlobalInfo.setDownloadTLogger(downloadTLogger);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setUrlHandler(IUrlHandler iUrlHandler) {
        GlobalInfo.setUrlHandler(iUrlHandler);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setDownloaderMonitor(IDownloaderMonitor iDownloaderMonitor) {
        GlobalInfo.setDownloaderMonitor(iDownloaderMonitor);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure initDownloader(DownloaderBuilder downloaderBuilder) {
        if (downloaderBuilder.getNotificationClickCallback() == null) {
            downloaderBuilder.notificationClickCallback(new INotificationClickCallback() { // from class: com.ss.android.downloadlib.DownloadConfigureImpl.2
                @Override // com.ss.android.socialbase.downloader.depend.INotificationClickCallback
                public boolean onClickWhenSuccess(DownloadInfo downloadInfo) {
                    return false;
                }

                @Override // com.ss.android.socialbase.downloader.depend.INotificationClickCallback
                public boolean onClickWhenUnSuccess(DownloadInfo downloadInfo) {
                    DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadInfo.getId());
                    if (downloadSettingObtain.optInt(DownloadSettingKeys.NOTIFICATION_OPT_2) == 1) {
                        if (downloadInfo.getStatus() == -2) {
                            DownloadHandlerService.handleActionClickWithoutType(GlobalInfo.getContext(), downloadInfo, AppDownloader.getInstance().getAppDownloadEventHandler(), Downloader.getInstance(GlobalInfo.getContext()).getDownloadNotificationEventListener(downloadInfo.getId()));
                        }
                        return true;
                    }
                    boolean zHandleJumpWhenUnSuccess = handleJumpWhenUnSuccess(downloadInfo);
                    if (downloadSettingObtain.optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.DISABLE_DELETE_DIALOG, 0) == 1) {
                        return true;
                    }
                    return zHandleJumpWhenUnSuccess;
                }

                private boolean handleJumpWhenUnSuccess(DownloadInfo downloadInfo) {
                    String notificationJumpUrl;
                    IUrlHandler urlHandler = GlobalInfo.getUrlHandler();
                    if (urlHandler == null) {
                        return false;
                    }
                    NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
                    if (nativeModelByInfo != null && nativeModelByInfo.isAd()) {
                        notificationJumpUrl = DownloadSetting.obtain(downloadInfo.getId()).optString(com.ss.android.downloadlib.constants.DownloadSettingKeys.AD_NOTIFICATION_JUMP_URL, null);
                    } else {
                        notificationJumpUrl = DownloadInsideHelper.getNotificationJumpUrl(downloadInfo);
                    }
                    if (TextUtils.isEmpty(notificationJumpUrl)) {
                        return false;
                    }
                    return urlHandler.openUrl(GlobalInfo.getContext(), notificationJumpUrl);
                }

                @Override // com.ss.android.socialbase.downloader.depend.INotificationClickCallback
                public boolean onClickWhenInstalled(DownloadInfo downloadInfo) throws JSONException {
                    if (downloadInfo == null) {
                        return false;
                    }
                    NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
                    if (nativeModelByInfo != null) {
                        AdAppLinkUtils.tryAppLinkWhenClickNotification(nativeModelByInfo);
                    } else {
                        OpenAppUtils.tryOpenByPackage(GlobalInfo.getContext(), downloadInfo.getPackageName());
                    }
                    DownloadNotificationManager.getInstance().cancelNotification(downloadInfo.getId());
                    return true;
                }
            });
        }
        downloaderBuilder.addDownloadCompleteHandler(new ApkUpdateManager());
        Downloader.initOrCover(downloaderBuilder, true);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setAppDownloadFileUriProvider(IAppDownloadFileUriProvider iAppDownloadFileUriProvider) {
        AppDownloader.getInstance().setAppFileUriProvider(iAppDownloadFileUriProvider);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setUseReflectParseRes(boolean z) {
        AppDownloader.getInstance().setUseReflectParseRes(z);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setCleanManager(ICleanManager iCleanManager) {
        GlobalInfo.setCleanManager(iCleanManager);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setInstallGuideViewListener(IInstallGuideViewListener iInstallGuideViewListener) {
        GlobalInfo.setInstallGuideViewListener(iInstallGuideViewListener);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setUserInfoListener(IUserInfoListener iUserInfoListener) {
        GlobalInfo.setUserInfoListener(iUserInfoListener);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setEncryptor(IEncryptor iEncryptor) {
        GlobalInfo.setEncryptor(iEncryptor);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public DownloadConfigure setOpenAppListener(IOpenAppListener iOpenAppListener) {
        GlobalInfo.setOpenAppListener(iOpenAppListener);
        return this;
    }

    @Override // com.ss.android.download.api.DownloadConfigure
    public void configEnd() throws JSONException {
        if (!GlobalInfo.initCheck()) {
            TTDownloaderMonitor.inst().monitorDataError("ttdownloader init error");
        }
        GlobalInfo.setTTDownloaderMonitor(TTDownloaderMonitor.inst());
        try {
            AppDownloader.getInstance().setDefaultSavePath(GlobalInfo.getDownloadDirPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppDownloader.getInstance().setOpenInstallerListener(AdDownloadCompletedEventHandlerImpl.getInstance());
        DownloadComponentManager.getInstance().submitIOTask(new Runnable() { // from class: com.ss.android.downloadlib.DownloadConfigureImpl.3
            @Override // java.lang.Runnable
            public void run() {
                RomUtils.check("");
                if (RomUtils.isMiuiV12()) {
                    com.ss.android.socialbase.downloader.downloader.DownloadComponentManager.setNotAutoRebootService(true);
                }
                SecurityUtils.init(GlobalInfo.getContext());
            }
        });
    }
}
