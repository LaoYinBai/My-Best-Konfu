package com.ss.android.downloadlib.addownload;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.android.download.api.common.DefaultDownloadUIFactory;
import com.ss.android.download.api.common.DefaultPermissionChecker;
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
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.guide.IInstallGuideViewListener;
import com.ss.android.download.api.inner.ITTDownloaderMonitor;
import com.ss.android.download.api.model.AppInfo;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadMonitorListener;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.io.File;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GlobalInfo {
    public static final JSONObject EMPTY_JSON = new JSONObject();
    private static final String SDK_VERSION = "1.7.0";
    private static DownloadAutoInstallInterceptListener mDownloadAutoInstallInterceptListener;
    private static IApkUpdateHandler sApkUpdateHandler;
    private static AppInfo sAppInfo;
    private static AppStatusChangeListener sAppStatusChangeListener;
    private static ICleanManager sCleanManager;
    private static Context sContext;
    private static DownloadActionListener sDownloadActionListener;
    private static DownloadClearSpaceListener sDownloadClearSpaceListener;
    private static IDownloadCustomChecker sDownloadCustomChecker;
    private static DownloadEventLogger sDownloadEventLogger;
    private static DownloadNetworkFactory sDownloadNetworkFactory;
    private static DownloadPermissionChecker sDownloadPermissionChecker;
    private static DownloadSettings sDownloadSettings;
    private static DownloadTLogger sDownloadTLogger;
    private static DownloadUIFactory sDownloadUIFactory;
    private static IDownloaderMonitor sDownloaderMonitor;
    private static IEncryptor sEncryptor;
    private static IInstallGuideViewListener sInstallGuideViewListener;
    private static IAppDownloadMonitorListener sMonitorListener;
    private static IOpenAppListener sOpenAppListener;
    private static ITTDownloaderMonitor sTTDownloaderMonitor;
    private static IUrlHandler sUrlHandler;
    private static IUserInfoListener sUserInfoListener;

    public static String getSdkVersion() {
        return SDK_VERSION;
    }

    public static void setContext(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            throw new IllegalArgumentException("Context is null");
        }
        sContext = context.getApplicationContext();
    }

    public static void makeSureContext(Context context) {
        if (sContext != null || context == null || context.getApplicationContext() == null) {
            return;
        }
        sContext = context.getApplicationContext();
    }

    public static Context getContext() {
        if (sContext == null) {
            throw new IllegalArgumentException("Context is null");
        }
        return sContext;
    }

    public static void setDownloadEventLogger(@NonNull DownloadEventLogger downloadEventLogger) {
        sDownloadEventLogger = downloadEventLogger;
    }

    public static void setDownloadActionListener(@NonNull DownloadActionListener downloadActionListener) {
        sDownloadActionListener = downloadActionListener;
    }

    public static void setDownloadUIFactory(@NonNull DownloadUIFactory downloadUIFactory) {
        sDownloadUIFactory = downloadUIFactory;
    }

    public static void setDownloadNetworkFactory(@NonNull DownloadNetworkFactory downloadNetworkFactory) {
        sDownloadNetworkFactory = downloadNetworkFactory;
    }

    public static void setDownloadPermissionChecker(@NonNull DownloadPermissionChecker downloadPermissionChecker) {
        sDownloadPermissionChecker = downloadPermissionChecker;
    }

    public static void setMonitorListener(@NonNull IAppDownloadMonitorListener iAppDownloadMonitorListener) {
        sMonitorListener = iAppDownloadMonitorListener;
    }

    public static void setApkUpdateHandler(IApkUpdateHandler iApkUpdateHandler) {
        sApkUpdateHandler = iApkUpdateHandler;
    }

    public static void setDownloadSettings(@NonNull DownloadSettings downloadSettings) {
        sDownloadSettings = downloadSettings;
    }

    public static void setAppInfo(@NonNull AppInfo appInfo) {
        sAppInfo = appInfo;
    }

    public static void setAppStatusChangeListener(@NonNull AppStatusChangeListener appStatusChangeListener) {
        sAppStatusChangeListener = appStatusChangeListener;
    }

    public static void setCleanManager(ICleanManager iCleanManager) {
        sCleanManager = iCleanManager;
    }

    public static void setInstallGuideViewListener(IInstallGuideViewListener iInstallGuideViewListener) {
        sInstallGuideViewListener = iInstallGuideViewListener;
    }

    public static void setUserInfoListener(IUserInfoListener iUserInfoListener) {
        sUserInfoListener = iUserInfoListener;
    }

    public static IUserInfoListener getUserInfoListener() {
        return sUserInfoListener;
    }

    public static DownloadEventLogger getDownloadEventLogger() {
        return sDownloadEventLogger;
    }

    @NonNull
    public static DownloadActionListener getDownloadActionListener() {
        if (sDownloadActionListener == null) {
            sDownloadActionListener = new DownloadActionListener() { // from class: com.ss.android.downloadlib.addownload.GlobalInfo.1
                @Override // com.ss.android.download.api.config.DownloadActionListener
                public void onItemClick(@Nullable Context context, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig) {
                }

                @Override // com.ss.android.download.api.config.DownloadActionListener
                public void onItemStart(@Nullable Context context, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController) {
                }

                @Override // com.ss.android.download.api.config.DownloadActionListener
                public void onOpenApp(@Nullable Context context, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig, String str, @NonNull String str2) {
                }
            };
        }
        return sDownloadActionListener;
    }

    @NonNull
    public static DownloadUIFactory getDownloadUIFactory() {
        if (sDownloadUIFactory == null) {
            sDownloadUIFactory = new DefaultDownloadUIFactory();
        }
        return sDownloadUIFactory;
    }

    public static DownloadNetworkFactory getDownloadNetworkFactory() {
        return sDownloadNetworkFactory;
    }

    @NonNull
    public static DownloadPermissionChecker getDownloadPermissionChecker() {
        if (sDownloadPermissionChecker == null) {
            sDownloadPermissionChecker = new DefaultPermissionChecker();
        }
        return sDownloadPermissionChecker;
    }

    public static IAppDownloadMonitorListener getMonitorListener() {
        if (sMonitorListener == null) {
            sMonitorListener = new IAppDownloadMonitorListener() { // from class: com.ss.android.downloadlib.addownload.GlobalInfo.2
                @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadMonitorListener
                public void onAppDownloadMonitorSend(DownloadInfo downloadInfo, BaseException baseException, int i) {
                }
            };
        }
        return sMonitorListener;
    }

    public static void setDownloadCustomChecker(IDownloadCustomChecker iDownloadCustomChecker) {
        sDownloadCustomChecker = iDownloadCustomChecker;
    }

    public static IDownloadCustomChecker getDownloadCustomChecker() {
        return sDownloadCustomChecker;
    }

    public static void setDownloaderMonitor(IDownloaderMonitor iDownloaderMonitor) {
        sDownloaderMonitor = iDownloaderMonitor;
    }

    @NonNull
    public static IDownloaderMonitor getDownloaderMonitor() {
        if (sDownloaderMonitor == null) {
            sDownloaderMonitor = new IDownloaderMonitor() { // from class: com.ss.android.downloadlib.addownload.GlobalInfo.3
                @Override // com.ss.android.download.api.config.IDownloaderMonitor
                public void monitorDuration(String str, JSONObject jSONObject, JSONObject jSONObject2) {
                }

                @Override // com.ss.android.download.api.config.IDownloaderMonitor
                public void monitorStatusAndDuration(String str, int i, JSONObject jSONObject, JSONObject jSONObject2) {
                }

                @Override // com.ss.android.download.api.config.IDownloaderMonitor
                public void monitorStatusRate(String str, int i, JSONObject jSONObject) {
                }
            };
        }
        return sDownloaderMonitor;
    }

    @NonNull
    public static JSONObject getDownloadSettings() {
        if (sDownloadSettings == null || sDownloadSettings.get() == null) {
            return EMPTY_JSON;
        }
        return sDownloadSettings.get();
    }

    @NonNull
    public static AppInfo getAppInfo() {
        if (sAppInfo == null) {
            sAppInfo = new AppInfo.Builder().build();
        }
        return sAppInfo;
    }

    public static IApkUpdateHandler getApkUpdateHandler() {
        return sApkUpdateHandler;
    }

    @Nullable
    public static AppStatusChangeListener getAppStatusChangeListener() {
        return sAppStatusChangeListener;
    }

    @Nullable
    public static ICleanManager getCleanManager() {
        return sCleanManager;
    }

    public static void setFileProviderAuthority(String str) {
        AppDownloader.getInstance().setFileProviderAuthority(str);
    }

    public static void setLogLevel(int i) {
        Logger.setLogLevel(i);
    }

    public static void setDownloadAutoInstallInterceptListener(DownloadAutoInstallInterceptListener downloadAutoInstallInterceptListener) {
        mDownloadAutoInstallInterceptListener = downloadAutoInstallInterceptListener;
    }

    public static DownloadAutoInstallInterceptListener getDownloadAutoInstallInterceptListener() {
        return mDownloadAutoInstallInterceptListener;
    }

    public static void setDownloadClearSpaceListener(DownloadClearSpaceListener downloadClearSpaceListener) {
        sDownloadClearSpaceListener = downloadClearSpaceListener;
    }

    public static DownloadClearSpaceListener getDownloadClearSpaceListener() {
        return sDownloadClearSpaceListener;
    }

    public static void setDownloadTLogger(DownloadTLogger downloadTLogger) {
        sDownloadTLogger = downloadTLogger;
    }

    public static DownloadTLogger getDownloadTLogger() {
        return sDownloadTLogger;
    }

    public static void setEncryptor(IEncryptor iEncryptor) {
        sEncryptor = iEncryptor;
    }

    @NonNull
    public static IEncryptor getEncryptor() {
        return sEncryptor;
    }

    public static IUrlHandler getUrlHandler() {
        return sUrlHandler;
    }

    public static void setUrlHandler(IUrlHandler iUrlHandler) {
        sUrlHandler = iUrlHandler;
    }

    public static void setTTDownloaderMonitor(ITTDownloaderMonitor iTTDownloaderMonitor) {
        sTTDownloaderMonitor = iTTDownloaderMonitor;
    }

    @NonNull
    public static ITTDownloaderMonitor getTTMonitor() {
        if (sTTDownloaderMonitor == null) {
            sTTDownloaderMonitor = new ITTDownloaderMonitor() { // from class: com.ss.android.downloadlib.addownload.GlobalInfo.4
                @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
                public void monitorDataError(String str) {
                }

                @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
                public void monitorDataError(boolean z, String str) {
                }

                @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
                public void monitorException(Throwable th, String str) {
                }

                @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
                public void monitorException(boolean z, Throwable th, String str) {
                }

                @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
                public void monitorPathError(String str) {
                }

                @Override // com.ss.android.download.api.inner.ITTDownloaderMonitor
                public void monitorPathError(boolean z, String str) {
                }
            };
        }
        return sTTDownloaderMonitor;
    }

    public static void setOpenAppListener(IOpenAppListener iOpenAppListener) {
        sOpenAppListener = iOpenAppListener;
    }

    @NonNull
    public static IOpenAppListener getOpenAppListener() {
        if (sOpenAppListener == null) {
            sOpenAppListener = new IOpenAppListener() { // from class: com.ss.android.downloadlib.addownload.GlobalInfo.5
                @Override // com.ss.android.download.api.config.IOpenAppListener
                public void onOpenApp(@Nullable Context context, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig, String str, int i) {
                }
            };
        }
        return sOpenAppListener;
    }

    public static String getDownloadDirPath() {
        try {
            int i = getContext().getApplicationInfo().targetSdkVersion;
            if (Build.VERSION.SDK_INT >= 29 && ((i == 29 && !Environment.isExternalStorageLegacy()) || i > 29)) {
                return getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            }
            return Environment.getExternalStorageDirectory().getPath() + File.separator + getDownloadSettings().optString(DownloadSettingKeys.DEFAULT_SAVE_DIR_NAME, BaseConstants.DOWNLOAD_DIR);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean initCheck() {
        return (sDownloadEventLogger == null || sDownloadNetworkFactory == null || sDownloadSettings == null || sAppStatusChangeListener == null || sEncryptor == null) ? false : true;
    }
}
