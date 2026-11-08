package com.ss.android.socialbase.appdownloader;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadDepend;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventListener;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadFileUriProvider;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadLaunchResumeListener;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadMonitorListener;
import com.ss.android.socialbase.appdownloader.depend.IAppStatusChangeListener;
import com.ss.android.socialbase.appdownloader.depend.IBeforeAppInstallInterceptor;
import com.ss.android.socialbase.appdownloader.depend.IDownloadNotificationBuilder;
import com.ss.android.socialbase.appdownloader.depend.INotificationPermissionRequestCallback;
import com.ss.android.socialbase.appdownloader.impls.DefaultDownloadLaunchHandler;
import com.ss.android.socialbase.appdownloader.notification.DownloadNotificationListener;
import com.ss.android.socialbase.appdownloader.notification.NotificationIconCache;
import com.ss.android.socialbase.appdownloader.notification.NotificationPermissionHelper;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.depend.IInstallAppHandler;
import com.ss.android.socialbase.downloader.depend.IOpenInstallerListener;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.impls.RetryScheduler;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.ss.android.socialbase.downloader.network.connectionpool.DownloadPreconnecter;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.umeng.message.common.a;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppDownloader {
    private static final String TAG = "AppDownloader";
    private static volatile AppDownloader instance;
    private static boolean isInited;
    private static boolean isRegistered;
    private IAppDownloadDepend appDownloadDepend;
    private IAppDownloadEventHandler appDownloadEventHandler;
    private IAppDownloadLaunchResumeListener appDownloadLaunchResumeListener;
    private IAppDownloadMonitorListener appDownloadMonitorListener;
    private IDownloadNotificationBuilder appDownloadNotificationBuilder;
    private IAppDownloadFileUriProvider appFileUriProvider;
    private IBeforeAppInstallInterceptor beforeAppInstallInterceptor;
    private DownloadReceiver downloadReceiver;
    private String fileProviderAuthority;
    private IInstallAppHandler installAppHandler;
    private String notificationChannelId;
    private IOpenInstallerListener openInstallerListener;
    private boolean useReflectParseRes = false;

    @Deprecated
    public IAppStatusChangeListener getAppStatusChangeListener() {
        return null;
    }

    @Deprecated
    public void setAppStatusChangeListener(IAppStatusChangeListener iAppStatusChangeListener) {
    }

    @Deprecated
    public void setUsePackageFlagConfiguration(boolean z) {
    }

    public IAppDownloadDepend getAppDownloadDepend() {
        return this.appDownloadDepend;
    }

    public IAppDownloadEventHandler getAppDownloadEventHandler() {
        return this.appDownloadEventHandler;
    }

    public IAppDownloadMonitorListener getAppDownloadMonitorListener() {
        return this.appDownloadMonitorListener;
    }

    public void setFileProviderAuthority(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.fileProviderAuthority = str;
    }

    public String getFileProviderAuthority() {
        return this.fileProviderAuthority;
    }

    public void setAppDownloadDepend(IAppDownloadDepend iAppDownloadDepend) {
        if (iAppDownloadDepend == null) {
            return;
        }
        this.appDownloadDepend = iAppDownloadDepend;
    }

    public IAppDownloadFileUriProvider getAppFileUriProvider() {
        return this.appFileUriProvider;
    }

    public void setAppFileUriProvider(IAppDownloadFileUriProvider iAppDownloadFileUriProvider) {
        this.appFileUriProvider = iAppDownloadFileUriProvider;
    }

    public void setAppDownloadEventHandler(IAppDownloadEventHandler iAppDownloadEventHandler) {
        this.appDownloadEventHandler = iAppDownloadEventHandler;
    }

    public boolean isUsePackageFlagConfiguration() {
        return DownloadSetting.getGlobalSettings().optInt(DownloadSettingKeys.PACKAGE_FLAG_CONFIG, 1) == 1;
    }

    public IBeforeAppInstallInterceptor getBeforeAppInstallInterceptor() {
        return this.beforeAppInstallInterceptor;
    }

    public void setBeforeAppInstallInterceptor(IBeforeAppInstallInterceptor iBeforeAppInstallInterceptor) {
        this.beforeAppInstallInterceptor = iBeforeAppInstallInterceptor;
    }

    public File getGlobalSaveDir() {
        return Downloader.getInstance(DownloadComponentManager.getAppContext()).getGlobalSaveDir();
    }

    public void setAppDownloadMonitorListener(IAppDownloadMonitorListener iAppDownloadMonitorListener) {
        this.appDownloadMonitorListener = iAppDownloadMonitorListener;
    }

    public boolean useReflectParseRes() {
        return this.useReflectParseRes;
    }

    public void setUseReflectParseRes(boolean z) {
        this.useReflectParseRes = z;
    }

    @Deprecated
    public void setMinResumeFailedIntervalTime(long j) throws JSONException {
        if (j < 0) {
            j = 0;
        }
        try {
            DownloadComponentManager.getDownloadSetting().put(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.FAILED_RESUME_MIN_HOURS, j / 3600000.0d);
        } catch (Exception unused) {
        }
    }

    @Deprecated
    public void setMinResumeUnInstallIntervalTime(long j) throws JSONException {
        if (j < 0) {
            j = 0;
        }
        try {
            DownloadComponentManager.getDownloadSetting().put(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.UNINSTALL_RESUME_MIN_HOURS, j / 3600000.0d);
        } catch (Exception unused) {
        }
    }

    @Deprecated
    public void setMaxResumeFailedNotificationShowCount(int i) throws JSONException {
        if (i < 0) {
            i = 0;
        }
        try {
            DownloadComponentManager.getDownloadSetting().put(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.FAILED_RESUME_MAX_COUNT, i);
        } catch (Exception unused) {
        }
    }

    @Deprecated
    public void setMaxResumeUnInstallNotificationShowCount(int i) throws JSONException {
        if (i < 0) {
            i = 0;
        }
        try {
            DownloadComponentManager.getDownloadSetting().put(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.UNINSTALL_RESUME_MAX_COUNT, i);
        } catch (Exception unused) {
        }
    }

    @Deprecated
    public long getMinResumeFailedIntervalTime() {
        return ((long) DownloadComponentManager.getDownloadSetting().optDouble(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.FAILED_RESUME_MIN_HOURS, 12.0d)) * 3600000;
    }

    @Deprecated
    public long getMinResumeUnInstallIntervalTime() {
        return ((long) DownloadComponentManager.getDownloadSetting().optDouble(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.UNINSTALL_RESUME_MIN_HOURS, 12.0d)) * 3600000;
    }

    @Deprecated
    public int getMaxFailedNotificationShowCount() {
        return DownloadComponentManager.getDownloadSetting().optInt(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.FAILED_RESUME_MAX_COUNT, 0);
    }

    @Deprecated
    public int getMaxUnInstallNotificationShowCount() {
        return DownloadComponentManager.getDownloadSetting().optInt(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.UNINSTALL_RESUME_MAX_COUNT, 0);
    }

    public String getNotificationChannelId() {
        return this.notificationChannelId;
    }

    public void setNotificationChannelId(String str) {
        this.notificationChannelId = str;
    }

    private AppDownloader() {
    }

    public static AppDownloader getInstance() {
        if (instance == null) {
            synchronized (AppDownloader.class) {
                if (instance == null) {
                    instance = new AppDownloader();
                }
            }
        }
        return instance;
    }

    @Deprecated
    public void init(Context context, String str, IAppDownloadDepend iAppDownloadDepend, IAppDownloadEventHandler iAppDownloadEventHandler, IAppDownloadMonitorListener iAppDownloadMonitorListener) {
        if (iAppDownloadDepend != null) {
            this.appDownloadDepend = iAppDownloadDepend;
        }
        if (iAppDownloadEventHandler != null) {
            this.appDownloadEventHandler = iAppDownloadEventHandler;
        }
        if (iAppDownloadMonitorListener != null) {
            this.appDownloadMonitorListener = iAppDownloadMonitorListener;
        }
        initInner(context);
    }

    private void initInner(Context context) {
        if (context == null || isInited) {
            return;
        }
        DownloadConstants.setMimeApk("application/vnd.android.package-archive");
        DownloadComponentManager.setAppContext(context);
        DownloadComponentManager.setDownloadLaunchHandler(new DefaultDownloadLaunchHandler());
        registerDownloadReceiver();
        initRetryScheduler();
        isInited = true;
    }

    public void setDefaultSavePath(String str) {
        Downloader.getInstance(DownloadComponentManager.getAppContext()).setDefaultSavePath(str);
    }

    public void setDefaultSaveTempPath(String str) {
        Downloader.getInstance(DownloadComponentManager.getAppContext()).setDefaultSaveTempPath(str);
    }

    public void init(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.fileProviderAuthority = str;
        }
        initInner(context);
    }

    private void registerDownloadReceiver() {
        if (isRegistered) {
            return;
        }
        if (this.downloadReceiver == null) {
            this.downloadReceiver = new DownloadReceiver();
        }
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
            intentFilter.addAction("android.ss.intent.action.DOWNLOAD_COMPLETE");
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter2.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter2.addDataScheme(a.u);
            IntentFilter intentFilter3 = new IntentFilter();
            intentFilter3.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter3.addDataScheme("file");
            DownloadComponentManager.getAppContext().registerReceiver(this.downloadReceiver, intentFilter);
            DownloadComponentManager.getAppContext().registerReceiver(this.downloadReceiver, intentFilter2);
            DownloadComponentManager.getAppContext().registerReceiver(this.downloadReceiver, intentFilter3);
            isRegistered = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unRegisterDownloadReceiver() {
        try {
            if (!isRegistered || this.downloadReceiver == null) {
                return;
            }
            DownloadComponentManager.getAppContext().unregisterReceiver(this.downloadReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRetryScheduler() {
        if (Build.VERSION.SDK_INT >= 21) {
            RetryScheduler.setRetryScheduleHandler(new RetryScheduler.RetryScheduleHandler() { // from class: com.ss.android.socialbase.appdownloader.AppDownloader.1
                @Override // com.ss.android.socialbase.downloader.impls.RetryScheduler.RetryScheduleHandler
                public void scheduleRetry(DownloadInfo downloadInfo, long j, boolean z, int i) {
                    RetryJobSchedulerService.tryStartScheduleRetry(downloadInfo, j, z, i);
                }

                @Override // com.ss.android.socialbase.downloader.impls.RetryScheduler.RetryScheduleHandler
                public void cancelRetry(int i) {
                    RetryJobSchedulerService.tryCancelScheduleRetry(i);
                }
            });
        }
    }

    public static boolean startInstall(Context context, int i) {
        return AppDownloadUtils.startViewIntent(context, i, true) == 1;
    }

    public void handleStatusClick(Context context, int i, int i2) {
        try {
            switch (i2) {
                case -4:
                case -1:
                    Downloader.getInstance(context).restart(i);
                    break;
                case -3:
                    AppDownloadUtils.startViewIntent(context, i, true);
                    break;
                case -2:
                    Downloader.getInstance(context).resume(i);
                    break;
                case 0:
                case 6:
                default:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                    Downloader.getInstance(context).pause(i);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void preconnect(int i, String str, String str2, List<HttpHeader> list, boolean z, boolean z2) {
        List<HttpHeader> listPutDefaultUa = putDefaultUa(list);
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = AppDownloadUtils.getAppDownloadPath();
            }
            DownloadPreconnecter.preconnect(i, str, str2, listPutDefaultUa, z, z2);
        } catch (Throwable th) {
            Log.w(TAG, "preconnect: t = " + th);
        }
    }

    public int addDownloadTask(AppTaskBuilder appTaskBuilder) {
        int i;
        String str;
        int i2;
        JSONObject jSONObject;
        DownloadInfo downloadInfo;
        if (appTaskBuilder == null || appTaskBuilder.getContext() == null) {
            return 0;
        }
        try {
            List<HttpHeader> listPutDefaultUa = putDefaultUa(appTaskBuilder.getHeaders());
            String url = appTaskBuilder.getUrl();
            if (TextUtils.isEmpty(url)) {
                return 0;
            }
            final int antiHijackErrorCode = appTaskBuilder.getAntiHijackErrorCode();
            final boolean z = antiHijackErrorCode == 0;
            String saveName = appTaskBuilder.getSaveName();
            final String name = appTaskBuilder.getName();
            if (TextUtils.isEmpty(saveName)) {
                saveName = AppDownloadUtils.getValidName(url, name, appTaskBuilder.getMimeType(), z);
            }
            if (saveName.length() > 255) {
                saveName = saveName.substring(saveName.length() - 255);
            }
            if (TextUtils.isEmpty(name)) {
                name = saveName;
            }
            String mimeType = appTaskBuilder.getMimeType();
            if (saveName.endsWith(Constants.APK_SUFFIX) && !AppDownloadUtils.isApkMineType(appTaskBuilder.getMimeType())) {
                mimeType = "application/vnd.android.package-archive";
            }
            String savePath = appTaskBuilder.getSavePath();
            if (TextUtils.isEmpty(appTaskBuilder.getSavePath())) {
                savePath = AppDownloadUtils.getAppDownloadPath();
            }
            String str2 = savePath;
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(saveName)) {
                int downloadId = DownloadComponentManager.getDownloadId(url, str2);
                if (DownloadSetting.obtain(appTaskBuilder.getDownloadSetting()).optBugFix(DownloadSettingKeys.BugFix.FIX_RESUME_TASK_OVERRIDE_SETTINGS) && (downloadInfo = Downloader.getInstance(DownloadComponentManager.getAppContext()).getDownloadInfo(downloadId)) != null) {
                    try {
                        appTaskBuilder.downloadSetting(new JSONObject(downloadInfo.getDownloadSettingString()));
                    } catch (Throwable unused) {
                    }
                }
                DownloadSetting.addTaskDownloadSetting(downloadId, appTaskBuilder.getDownloadSetting());
                boolean zIsForce = appTaskBuilder.isForce();
                boolean z2 = (DownloadSetting.obtain(downloadId).optInt(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.MODIFY_FORCE, 1) == 1 && !zIsForce && DownloadUtils.isFileExist(str2, saveName) && Downloader.getInstance(appTaskBuilder.getContext()).getDownloadInfo(downloadId) == null) ? true : zIsForce;
                IDownloadListener notificationListener = appTaskBuilder.getNotificationListener();
                if (notificationListener != null || (!appTaskBuilder.isShowNotification() && !appTaskBuilder.isAutoInstallWithoutNotification())) {
                    i = downloadId;
                    str = str2;
                } else if (appTaskBuilder.getNotificationItem() != null) {
                    notificationListener = new DownloadNotificationListener(appTaskBuilder.getNotificationItem());
                    i = downloadId;
                    str = str2;
                } else {
                    i = downloadId;
                    str = str2;
                    notificationListener = new DownloadNotificationListener(appTaskBuilder.getContext(), downloadId, name, str, saveName, appTaskBuilder.getExtra());
                }
                IDownloadDepend depend = appTaskBuilder.getDepend();
                if (depend == null) {
                    depend = new IDownloadDepend() { // from class: com.ss.android.socialbase.appdownloader.AppDownloader.2
                        @Override // com.ss.android.socialbase.downloader.depend.IDownloadDepend
                        public void monitorLogSend(DownloadInfo downloadInfo2, BaseException baseException, int i3) {
                            if (AppDownloader.this.appDownloadMonitorListener != null) {
                                AppDownloader.this.appDownloadMonitorListener.onAppDownloadMonitorSend(downloadInfo2, baseException, i3);
                            }
                        }
                    };
                }
                List<IDownloadCompleteHandler> downloadCompleteHandlers = DownloadComponentManager.getDownloadCompleteHandlers();
                if (!downloadCompleteHandlers.isEmpty()) {
                    Iterator<IDownloadCompleteHandler> it = downloadCompleteHandlers.iterator();
                    while (it.hasNext()) {
                        appTaskBuilder.addDownloadCompleteHandler(it.next());
                    }
                }
                String extra = appTaskBuilder.getExtra();
                try {
                    if (!TextUtils.isEmpty(extra)) {
                        jSONObject = new JSONObject(extra);
                    } else {
                        jSONObject = new JSONObject();
                    }
                    jSONObject.put(Constants.AUTO_INSTALL_WITH_NOTIFICATION, appTaskBuilder.isAutoInstallWithNotification());
                    jSONObject.put(DownloadConstants.AUTO_INSTALL_WITHOUT_NOTIFICATION, appTaskBuilder.isAutoInstallWithoutNotification());
                    extra = jSONObject.toString();
                } catch (Throwable unused2) {
                }
                boolean z3 = appTaskBuilder.isShowNotification() || appTaskBuilder.isAutoInstallWithoutNotification();
                if (!z3 || DownloadSetting.obtain(i).optInt("enable_notification_ui") < 1) {
                    i2 = i;
                } else {
                    i2 = i;
                    NotificationIconCache.inst().parseAndCacheIcon(i2, appTaskBuilder.getIconUrl());
                }
                final DownloadTask autoInstall = Downloader.with(appTaskBuilder.getContext()).url(url).backUpUrls(appTaskBuilder.getBackUpUrls()).name(saveName).title(name).savePath(str).onlyWifi(appTaskBuilder.isNeedWifi()).extraHeaders(listPutDefaultUa).depend(depend).retryCount(appTaskBuilder.getRetryCount()).backUpUrlRetryCount(appTaskBuilder.getBackUpUrlRetryCount()).showNotification(z3).extra(extra).mimeType(mimeType).minProgressTimeMsInterval(appTaskBuilder.getMinProgressTimeMsInterval()).maxProgressCount(appTaskBuilder.getMaxProgressCount()).mainThreadListener(appTaskBuilder.getMainThreadListener()).notificationListener(notificationListener).notificationEventListener(createNotificationEventListener(appTaskBuilder.getAppDownloadEventListener())).force(z2).autoResumed(appTaskBuilder.isAutoResumed()).showNotificationForAutoResumed(appTaskBuilder.isShowNotificationForAutoResumed()).chunkStategy(appTaskBuilder.getChunkStrategy()).chunkAdjustCalculator(appTaskBuilder.getChunkAdjustCalculator()).needHttpsToHttpRetry(appTaskBuilder.isNeedHttpsToHttpRetry()).packageName(appTaskBuilder.getPackageName()).md5(appTaskBuilder.getMd5()).expectFileLength(appTaskBuilder.getExpectFileLength()).needRetryDelay(appTaskBuilder.isNeedRetryDelay()).retryDelayTimeArray(appTaskBuilder.getRetryDelayTimeArray()).needDefaultHttpServiceBackUp(appTaskBuilder.isNeedDefaultHttpServiceBackUp()).needReuseFirstConnection(appTaskBuilder.isNeedReuseFirstConnection()).needReuseChunkRunnable(appTaskBuilder.isNeedReuseChunkRunnable()).needIndependentProcess(appTaskBuilder.isNeedIndependentProcess()).enqueueType(appTaskBuilder.getEnqueueType()).monitorDepend(appTaskBuilder.getMonitorDepend()).retryDelayTimeCalculator(appTaskBuilder.getRetryDelayTimeCalculator()).headConnectionAvailable(appTaskBuilder.isHeadConnectionAvailable()).fileUriProvider(appTaskBuilder.getFileUriProvider()).diskSpaceHandler(appTaskBuilder.getDiskSpaceHandler()).needChunkDowngradeRetry(appTaskBuilder.isNeedChunkDowngradeRetry()).notificationClickCallback(appTaskBuilder.getNotificationClickCallback()).downloadSetting(appTaskBuilder.getDownloadSetting()).iconUrl(appTaskBuilder.getIconUrl()).needSDKMonitor(appTaskBuilder.isNeedSDKMonitor()).monitorScene(appTaskBuilder.getMonitorScene()).extraMonitorStatus(appTaskBuilder.getExtraMonitorStatus()).executorGroup(appTaskBuilder.getExecutorGroup()).throttleNetSpeed(appTaskBuilder.getThrottleNetSpeed()).distinctDirectory(appTaskBuilder.isDistinctDirectory()).setAutoInstall(appTaskBuilder.isAutoInstall());
                if (autoInstall != null && !appTaskBuilder.getDownloadCompleteHandlers().isEmpty()) {
                    autoInstall.setDownloadCompleteHandlers(appTaskBuilder.getDownloadCompleteHandlers());
                }
                if (autoInstall != null) {
                    if (z3 && appTaskBuilder.isNeedNotificationPermission() && appTaskBuilder.getActivity() != null && !appTaskBuilder.getActivity().isFinishing() && !NotificationPermissionHelper.isNotificationEnabled()) {
                        NotificationPermissionHelper.showNotificationRequestDialog(appTaskBuilder.getActivity(), new INotificationPermissionRequestCallback() { // from class: com.ss.android.socialbase.appdownloader.AppDownloader.3
                            @Override // com.ss.android.socialbase.appdownloader.depend.INotificationPermissionRequestCallback
                            public void onGranted() {
                                Logger.d(AppDownloader.TAG, "notification permission granted, start download :" + name);
                                AppDownloader.this.startDownload(autoInstall, antiHijackErrorCode, z);
                            }

                            @Override // com.ss.android.socialbase.appdownloader.depend.INotificationPermissionRequestCallback
                            public void onDenied() {
                                Logger.d(AppDownloader.TAG, "notification permission denied, start download :" + name);
                                AppDownloader.this.startDownload(autoInstall, antiHijackErrorCode, z);
                            }
                        });
                    } else {
                        Logger.d(TAG, "notification permission need not request, start download :" + name);
                        startDownload(autoInstall, antiHijackErrorCode, z);
                        autoInstall.getDownloadInfo();
                    }
                }
                return i2;
            }
            return 0;
        } catch (Throwable th) {
            DownloadMonitorHelper.monitorSendWithTaskMonitor(appTaskBuilder.getMonitorDepend(), null, new BaseException(1003, DownloadUtils.getErrorMsgWithTagPrefix(th, "addDownloadTask")), 0);
            Logger.e(TAG, String.format("add download task error:%s", th));
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDownload(DownloadTask downloadTask, int i, boolean z) {
        if (downloadTask == null) {
            return;
        }
        downloadTask.download();
        DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
        if (downloadInfo != null) {
            downloadInfo.setAntiHijackErrorCode(i);
        }
        if (downloadInfo == null || !z) {
            return;
        }
        downloadInfo.setSavePathRedirected(z);
    }

    private List<HttpHeader> putDefaultUa(List<HttpHeader> list) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        if (list != null && list.size() > 0) {
            for (HttpHeader httpHeader : list) {
                if (httpHeader != null && !TextUtils.isEmpty(httpHeader.getName()) && !TextUtils.isEmpty(httpHeader.getValue())) {
                    if (httpHeader.getName().equals("User-Agent")) {
                        z = true;
                    }
                    arrayList.add(new HttpHeader(httpHeader.getName(), httpHeader.getValue()));
                }
            }
        }
        if (!z) {
            arrayList.add(new HttpHeader("User-Agent", Constants.DEFAULT_USER_AGENT));
        }
        return arrayList;
    }

    public String getMimeType(String str, String str2) {
        return (TextUtils.isEmpty(str) || !str.endsWith(Constants.APK_SUFFIX) || AppDownloadUtils.isApkMineType(str2)) ? str2 : "application/vnd.android.package-archive";
    }

    public void setAppDownloadEventListener(int i, IAppDownloadEventListener iAppDownloadEventListener) {
        if (iAppDownloadEventListener == null) {
            return;
        }
        Downloader.getInstance(DownloadComponentManager.getAppContext()).setDownloadNotificationEventListener(i, createNotificationEventListener(iAppDownloadEventListener));
    }

    private IDownloadNotificationEventListener createNotificationEventListener(final IAppDownloadEventListener iAppDownloadEventListener) {
        if (iAppDownloadEventListener == null) {
            return null;
        }
        return new IDownloadNotificationEventListener() { // from class: com.ss.android.socialbase.appdownloader.AppDownloader.4
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public void onNotificationEvent(int i, DownloadInfo downloadInfo, String str, String str2) {
                if (i != 1 && i != 3) {
                    switch (i) {
                        case 8:
                            iAppDownloadEventListener.onAppInstallError(i, downloadInfo.getPackageName(), str, str2);
                            break;
                        case 9:
                            iAppDownloadEventListener.onAppInstalled(DownloadComponentManager.getAppContext(), str);
                            break;
                        case 10:
                            iAppDownloadEventListener.onAppDownloadCancel(downloadInfo);
                            break;
                    }
                }
                iAppDownloadEventListener.onAppDownloadEvent(i, str, downloadInfo.getStatus(), downloadInfo.getDownloadTime());
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public boolean interceptAfterNotificationSuccess(boolean z) {
                return iAppDownloadEventListener.installIntercept(z);
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public String getNotifyProcessName() {
                return iAppDownloadEventListener.getNotifyProcessName();
            }
        };
    }

    public int getDownloadId(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return 0;
        }
        try {
            DownloadInfo appDownloadInfo = getAppDownloadInfo(context, str);
            if (appDownloadInfo != null) {
                return appDownloadInfo.getId();
            }
            String path = null;
            try {
                path = AppDownloadUtils.getAppDownloadPath();
            } catch (Throwable unused) {
            }
            if (TextUtils.isEmpty(path)) {
                path = context.getFilesDir().getPath();
            }
            return Downloader.getInstance(context).getDownloadId(str, path);
        } catch (Throwable th) {
            Logger.d(TAG, String.format("getAppDownloadInfo error:%s", th.getMessage()));
            return 0;
        }
    }

    public DownloadInfo getAppDownloadInfo(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        try {
            DownloadInfo downloadInfoInDir = getDownloadInfoInDir(context, str, getGlobalSaveDir());
            if (downloadInfoInDir == null) {
                downloadInfoInDir = getDownloadInfoInDir(context, str, context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
            }
            if (downloadInfoInDir == null) {
                downloadInfoInDir = getDownloadInfoInDir(context, str, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            }
            if (downloadInfoInDir == null) {
                downloadInfoInDir = getDownloadInfoInDir(context, str, context.getFilesDir());
            }
            return (downloadInfoInDir == null && DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.BUGFIX_GET_DOWNLOAD_INFO_BY_LIST)) ? getDownloadInfoByUrlWithAntiHijack(context, str) : downloadInfoInDir;
        } catch (Throwable th) {
            Logger.d(TAG, String.format("getAppDownloadInfo error:%s", th.getMessage()));
            return null;
        }
    }

    private DownloadInfo getDownloadInfoInDir(Context context, String str, File file) {
        if (context == null || TextUtils.isEmpty(str) || file == null) {
            return null;
        }
        return Downloader.getInstance(context).getDownloadInfo(str, file.getAbsolutePath());
    }

    private DownloadInfo getDownloadInfoByUrlWithAntiHijack(Context context, String str) {
        List<DownloadInfo> downloadInfoList = Downloader.getInstance(context).getDownloadInfoList(str);
        if (downloadInfoList == null) {
            return null;
        }
        for (DownloadInfo downloadInfo : downloadInfoList) {
            if (downloadInfo != null && downloadInfo.isSavePathRedirected()) {
                return downloadInfo;
            }
        }
        return null;
    }

    public List<DownloadInfo> getUnCompletedDownloadAppInfos(Context context) {
        return Downloader.getInstance(context).getUnCompletedDownloadInfosWithMimeType("application/vnd.android.package-archive");
    }

    public List<DownloadInfo> getDownloadingDownloadInfosWithMimeType(Context context) {
        return Downloader.getInstance(context).getDownloadingDownloadInfosWithMimeType("application/vnd.android.package-archive");
    }

    public IDownloadNotificationBuilder getAppDownloadNotificationBuilder() {
        return this.appDownloadNotificationBuilder;
    }

    public void setAppDownloadNotificationBuilder(IDownloadNotificationBuilder iDownloadNotificationBuilder) {
        this.appDownloadNotificationBuilder = iDownloadNotificationBuilder;
    }

    public IAppDownloadLaunchResumeListener getAppDownloadLaunchResumeListener() {
        return this.appDownloadLaunchResumeListener;
    }

    public void setAppDownloadLaunchResumeListener(IAppDownloadLaunchResumeListener iAppDownloadLaunchResumeListener) {
        this.appDownloadLaunchResumeListener = iAppDownloadLaunchResumeListener;
    }

    public IReserveWifiStatusListener getReserveWifiStatusListener() {
        return Downloader.getInstance(DownloadComponentManager.getAppContext()).getReserveWifiStatusListener();
    }

    public void setReserveWifiStatusListener(IReserveWifiStatusListener iReserveWifiStatusListener) {
        Downloader.getInstance(DownloadComponentManager.getAppContext()).setReserveWifiStatusListener(iReserveWifiStatusListener);
    }

    public void setInstallAppHandler(IInstallAppHandler iInstallAppHandler) {
        this.installAppHandler = iInstallAppHandler;
    }

    public IInstallAppHandler getInstallAppHandler() {
        return this.installAppHandler;
    }

    public void setOpenInstallerListener(IOpenInstallerListener iOpenInstallerListener) {
        this.openInstallerListener = iOpenInstallerListener;
    }

    public IOpenInstallerListener getOpenInstallerListener() {
        return this.openInstallerListener;
    }
}
