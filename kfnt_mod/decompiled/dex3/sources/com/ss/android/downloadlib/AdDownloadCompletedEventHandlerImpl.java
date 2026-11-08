package com.ss.android.downloadlib;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.ss.android.downloadad.api.AdDownloadCompletedEventHandler;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.CommonDownloadHandler;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.dialog.AdDownloadDialogManager;
import com.ss.android.downloadlib.addownload.model.InstalledAppManager;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.SharedPrefsManager;
import com.ss.android.downloadlib.addownload.optimize.DownloadOptimizationManager;
import com.ss.android.downloadlib.addownload.upload.UploadHelper;
import com.ss.android.downloadlib.applink.AdAppLinkUtils;
import com.ss.android.downloadlib.applink.AppInstallFinishInterceptor;
import com.ss.android.downloadlib.applink.AppLinkEventCallback;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.android.downloadlib.applink.AppLinkOptimiseHelper;
import com.ss.android.downloadlib.applink.IAppDeepLinkCallback;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.downloader.NotificationPusher;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.AntiHijackUtils;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.downloadlib.utils.EventAppendUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.AhAttempt;
import com.ss.android.socialbase.appdownloader.AhUtils;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.constants.InstallErrorCode;
import com.ss.android.socialbase.appdownloader.notification.NotificationPermissionHelper;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.constants.DownloadStatus;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.depend.IOpenInstallerListener;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.network.NetTrafficManager;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.umeng.commonsdk.proguard.b;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdDownloadCompletedEventHandlerImpl implements AdDownloadCompletedEventHandler, AhUtils.OnAhAttemptListener, AppStatusManager.AppStatusChangeListener, IOpenInstallerListener {
    private static final int AD_FINISH_EVENT_EXPIRE_HOURS = 168;
    private static final int AD_START_EVENT_EXPIRE_HOURS = 168;
    private static final long CHECK_EVENT_STATUS_MIN_INTERVAL = 120000;
    private static final int INSTALL_FINISH_TRY_COUNT = 15;
    private static final long INSTALL_SPACE_THRESHOLD = 524288000;
    private static String TAG = "AdDownloadCompletedEventHandlerImpl";
    private static volatile AdDownloadCompletedEventHandlerImpl sInstance;
    private CheckInstallFailedRunnable mCheckInstallFailedRunnable;
    private long mLastCheckEventStatusTimeMills;

    private AdDownloadCompletedEventHandlerImpl() {
        AhUtils.setOnAhAttemptListener(this);
        AppStatusManager.getInstance().registerAppSwitchListener(this);
    }

    public static AdDownloadCompletedEventHandlerImpl getInstance() {
        if (sInstance == null) {
            synchronized (AdDownloadCompletedEventHandlerImpl.class) {
                if (sInstance == null) {
                    sInstance = new AdDownloadCompletedEventHandlerImpl();
                }
            }
        }
        return sInstance;
    }

    @WorkerThread
    public static synchronized void onDownloadFinish(DownloadInfo downloadInfo, NativeDownloadModel nativeDownloadModel) {
        if (downloadInfo == null) {
            TTDownloaderMonitor.inst().monitorDataError("onDownloadFinish info null");
            return;
        }
        if (nativeDownloadModel == null) {
            TTDownloaderMonitor.inst().monitorDataError("onDownloadFinish nativeModel null");
            return;
        }
        if (nativeDownloadModel.getDownloadStatus() != 1) {
            return;
        }
        NotificationPusher.getInstance().delayNotifyInstallApp(nativeDownloadModel);
        String strCheckPackageNameError = checkPackageNameError(downloadInfo, nativeDownloadModel);
        ModelManager.getInstance().correctModelsPkgName(downloadInfo.getUrl(), strCheckPackageNameError);
        Map<Long, NativeDownloadModel> mapCorrectNativeModelsPkgName = ModelManager.getInstance().correctNativeModelsPkgName(downloadInfo.getUrl(), strCheckPackageNameError);
        nativeDownloadModel.setTimeStamp(System.currentTimeMillis());
        nativeDownloadModel.setDownloadStatus(2);
        nativeDownloadModel.setPackageName(strCheckPackageNameError);
        mapCorrectNativeModelsPkgName.put(Long.valueOf(nativeDownloadModel.getId()), nativeDownloadModel);
        SharedPrefsManager.getInstance().saveNativeDownloadModels(mapCorrectNativeModelsPkgName.values());
        sendDownloadFinishEvent(nativeDownloadModel);
        DownloadDispatcher.getInstance().notifyDownloadFinished(downloadInfo, strCheckPackageNameError);
        if ("application/vnd.android.package-archive".equals(downloadInfo.getMimeType())) {
            getInstance().tryListenInstallFinish(nativeDownloadModel);
            getInstance().tryRecordFileMd5(downloadInfo, nativeDownloadModel);
            if (nativeDownloadModel.enableBackDialog()) {
                AdDownloadDialogManager.Instance().addUninstalledInfo(downloadInfo.getId(), nativeDownloadModel.getId(), nativeDownloadModel.getExtValue(), strCheckPackageNameError, downloadInfo.getTitle(), nativeDownloadModel.getLogExtra(), downloadInfo.getTargetFilePath());
            }
            UploadHelper.uploadAppInfoWithNewDownloader(downloadInfo, nativeDownloadModel.getId(), nativeDownloadModel.getLogExtra(), strCheckPackageNameError);
        }
    }

    @WorkerThread
    public synchronized void onAppInstalled(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (ToolUtils.isMainThread()) {
            throw new RuntimeException("handleAppInstalled in main thread.");
        }
        final NativeDownloadModel nativeModelByPkg = ModelManager.getInstance().getNativeModelByPkg(str);
        if (nativeModelByPkg == null) {
            InstalledAppManager.getInstance().add(str);
            return;
        }
        CommonDownloadHandler commonDownloadHandler = DownloadDispatcher.getInstance().getCommonDownloadHandler(nativeModelByPkg.getDownloadUrl());
        if (commonDownloadHandler != null) {
            commonDownloadHandler.notifyInstallFinish();
        }
        if (nativeModelByPkg.hasSendInstallFinish.get()) {
            return;
        }
        if (DownloadSetting.obtain(nativeModelByPkg.getDownloadId()).optInt(DownloadSettingKeys.NOTIFICATION_OPT_2) == 1) {
            DownloadNotificationManager.getInstance().cancelNotification(nativeModelByPkg.getDownloadId());
        }
        new AppInstallFinishInterceptor().invokeApp(nativeModelByPkg, new IAppDeepLinkCallback() { // from class: com.ss.android.downloadlib.AdDownloadCompletedEventHandlerImpl.1
            @Override // com.ss.android.downloadlib.applink.IAppDeepLinkCallback
            public void onAppLink(boolean z) {
                Logger.d(AdDownloadCompletedEventHandlerImpl.TAG, "appBackForeground->" + z);
                if (z) {
                    if (!(AppLinkOptimiseHelper.invokeSwitchIsOpen(nativeModelByPkg) ? AdAppLinkUtils.tryAutoDeepLink(str, nativeModelByPkg) : false) && AppLinkOptimiseHelper.dialogSwitchIsOpen(nativeModelByPkg) && nativeModelByPkg.getInstallScene() == 4) {
                        AdDownloadDialogManager.Instance().tryShowOpenAppDialog(nativeModelByPkg);
                        return;
                    }
                    return;
                }
                if (AdAppLinkUtils.tryAutoDeepLink(str, nativeModelByPkg) || nativeModelByPkg.getInstallScene() != 4) {
                    return;
                }
                AdDownloadDialogManager.Instance().tryShowOpenAppDialog(nativeModelByPkg);
            }
        }, DownloadSettingUtils.getSetting(nativeModelByPkg).optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.TRY_APPLINK_DELAY_AFTER_INSTALLED, 0));
        NotificationPusher.getInstance().delayNotifyOpenApp(nativeModelByPkg);
        trySendInstallFinishEvent(str, nativeModelByPkg);
        AdDownloadDialogManager.Instance().resetReadyInstallPackage(str);
        DownloadInfo downloadInfo = getDownloadInfo(Downloader.getInstance(GlobalInfo.getContext()).getSuccessedDownloadInfosWithMimeType("application/vnd.android.package-archive"), str);
        if (downloadInfo != null) {
            if (DownloadSetting.obtain(downloadInfo.getId()).optInt("no_hide_notification") != 1) {
                DownloadNotificationManager.getInstance().hideNotification(downloadInfo.getId());
            }
            DownloadDispatcher.getInstance().notifyInstalled(downloadInfo, str);
            DownloadOptimizationManager.deleteFileWhenInstall(downloadInfo);
        } else {
            DownloadDispatcher.getInstance().notifyInstalled(null, str);
        }
    }

    void onAppJumpInstall(DownloadInfo downloadInfo, NativeDownloadModel nativeDownloadModel, int i) {
        long jMax;
        if (downloadInfo == null || nativeDownloadModel == null) {
            return;
        }
        checkIfInstallCanceled();
        long jCurrentTimeMillis = System.currentTimeMillis();
        nativeDownloadModel.setJumpInstallTime(jCurrentTimeMillis);
        nativeDownloadModel.setAvailableSpaceBytes(ToolUtils.getAvailableSpaceBytes(Environment.getDataDirectory(), -1L));
        if (i != 2000) {
            jMax = 2000;
        } else {
            long jOptLong = DownloadSetting.obtain(downloadInfo.getId()).optLong(com.ss.android.downloadlib.constants.DownloadSettingKeys.CHECK_INSTALL_FAILED_DELAY_TIME, CHECK_EVENT_STATUS_MIN_INTERVAL);
            if (jOptLong < 0) {
                return;
            } else {
                jMax = Math.max(jOptLong, b.d);
            }
        }
        long j = jMax;
        CheckInstallFailedRunnable checkInstallFailedRunnable = new CheckInstallFailedRunnable(nativeDownloadModel.getId(), downloadInfo.getId(), jCurrentTimeMillis, i);
        DownloadComponentManager.getInstance().submitScheduledTask(checkInstallFailedRunnable, j);
        this.mCheckInstallFailedRunnable = checkInstallFailedRunnable;
        SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeDownloadModel);
    }

    private static class CheckInstallFailedRunnable implements Runnable {
        private long cancelInstallTime;
        private int downloadId;
        private int failStatus;
        private long jumpInstallTime;
        private long modelId;

        private CheckInstallFailedRunnable(long j, int i, long j2, int i2) {
            this.modelId = j;
            this.downloadId = i;
            this.jumpInstallTime = j2;
            this.failStatus = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void markInstallCanceled() {
            this.cancelInstallTime = System.currentTimeMillis();
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (trySendInstallFailedEvent()) {
                    AdDownloadCompletedEventHandlerImpl.getInstance().postSendInstallFinishHijack(this.modelId, this.downloadId);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        /* JADX WARN: Can't wrap try/catch for region: R(21:12|(1:19)(20:16|(0)|21|52|22|23|54|24|(1:26)|27|(1:29)(1:30)|31|(1:34)|35|(1:37)(1:38)|39|(1:41)|42|49|50)|20|21|52|22|23|54|24|(0)|27|(0)(0)|31|(1:34)|35|(0)(0)|39|(0)|42|49|50) */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00f2, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00f4, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00f5, code lost:
        
            r3 = r6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00f7, code lost:
        
            r0.printStackTrace();
         */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00b0 A[Catch: Exception -> 0x00f2, TryCatch #1 {Exception -> 0x00f2, blocks: (B:24:0x008f, B:26:0x00b0, B:31:0x00c2, B:34:0x00cf, B:39:0x00de, B:42:0x00ee), top: B:54:0x008f }] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00bf  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00c1  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00db  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00dd  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00ed  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        boolean trySendInstallFailedEvent() throws org.json.JSONException {
            /*
                Method dump skipped, instructions count: 261
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.AdDownloadCompletedEventHandlerImpl.CheckInstallFailedRunnable.trySendInstallFailedEvent():boolean");
        }

        private int parseInstallFailedReason(boolean z, NativeDownloadModel nativeDownloadModel, DownloadInfo downloadInfo, boolean z2, JSONObject jSONObject) {
            DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadInfo.getId());
            int i = 1;
            if (downloadSettingObtain.optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.KEY_INSTALL_FAILED_CHECK_TTMD5, 1) == 1) {
                int iCheckMd5Status = downloadInfo.checkMd5Status();
                try {
                    jSONObject.put(EventConstants.ExtraJson.TTMD5_STATUS, iCheckMd5Status);
                } catch (Throwable unused) {
                }
                if (!DownloadUtils.isMd5Valid(iCheckMd5Status)) {
                    return 2005;
                }
            }
            if (this.failStatus != 2000) {
                return this.failStatus;
            }
            if (downloadSettingObtain.optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.KEY_INSTALL_FAILED_CHECK_SIGNATURE, 1) == 1 && ToolUtils.isInstalledApp(GlobalInfo.getContext(), nativeDownloadModel.getPackageName())) {
                if (!ToolUtils.isSignatureMatch(ToolUtils.getSignaturesByApkFilePath(GlobalInfo.getContext(), downloadInfo.getTargetFilePath()), ToolUtils.getSignaturesByPackageName(GlobalInfo.getContext(), nativeDownloadModel.getPackageName()))) {
                    return 2006;
                }
            }
            if (!z) {
                return 2002;
            }
            if (this.cancelInstallTime <= this.jumpInstallTime) {
                return 2000;
            }
            try {
                jSONObject.put(EventConstants.ExtraJson.KEY_INSTALL_TIME, this.cancelInstallTime - this.jumpInstallTime);
                if (nativeDownloadModel.getJumpInstallTime() <= this.jumpInstallTime) {
                    i = 0;
                }
                jSONObject.put(EventConstants.ExtraJson.KEY_INSTALL_AGAIN, i);
            } catch (Throwable unused2) {
            }
            if (z2) {
                return InstallErrorCode.ERROR_CANCELED;
            }
            return 2003;
        }
    }

    public void postSendInstallFinishHijack(final long j, int i) {
        long jOptLong = DownloadSetting.obtain(i).optLong(com.ss.android.downloadlib.constants.DownloadSettingKeys.CHECK_INSTALL_FINISH_HIJACK_DELAY_TIME, 900000L);
        if (jOptLong < 0) {
            return;
        }
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.AdDownloadCompletedEventHandlerImpl.2
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                AdDownloadCompletedEventHandlerImpl.getInstance().trySendInstallFinishHijack(j);
            }
        }, Math.max(jOptLong, 300000L));
    }

    public void trySendInstallFinishHijack(long j) throws JSONException {
        InstalledAppManager.AppInfo latestInstalledApp;
        int iIntValue;
        try {
            NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(j);
            if (nativeDownloadModel != null && !ToolUtils.isInstalledApp(nativeDownloadModel) && !nativeDownloadModel.hasSendInstallFinish.get()) {
                Pair<InstalledAppManager.AppInfo, Integer> pairCheckIfHijackedByAnotherInstalledApp = InstalledAppManager.getInstance().checkIfHijackedByAnotherInstalledApp(nativeDownloadModel);
                if (pairCheckIfHijackedByAnotherInstalledApp != null) {
                    latestInstalledApp = (InstalledAppManager.AppInfo) pairCheckIfHijackedByAnotherInstalledApp.first;
                    iIntValue = ((Integer) pairCheckIfHijackedByAnotherInstalledApp.second).intValue();
                } else {
                    latestInstalledApp = InstalledAppManager.getInstance().getLatestInstalledApp(nativeDownloadModel);
                    iIntValue = -1;
                }
                if (latestInstalledApp == null) {
                    return;
                }
                InstalledAppManager.getInstance().remove(latestInstalledApp.pkgName);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(EventConstants.ExtraJson.KEY_INSTALLED_APP_NAME, latestInstalledApp.appName);
                jSONObject.put(EventConstants.ExtraJson.KEY_INSTALLED_PKG_NAME, latestInstalledApp.pkgName);
                if (iIntValue != -1) {
                    jSONObject.put("error_code", iIntValue);
                    EventAppendUtils.appendAhExtraInfo(jSONObject, nativeDownloadModel.getDownloadId());
                    AdEventHandler.getInstance().sendEvent(EventConstants.Label.INSTALL_FINISH_HIJACK, jSONObject, nativeDownloadModel);
                    return;
                }
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.INSTALL_FINISH_MAY_HIJACK, jSONObject, nativeDownloadModel);
            }
        } catch (Throwable th) {
            TTDownloaderMonitor.inst().monitorException(th, "trySendInstallFinishHijack");
        }
    }

    public void trySendInstallFinishEvent(String str, NativeDownloadModel nativeDownloadModel) throws JSONException {
        if (nativeDownloadModel != null && ToolUtils.isInstalledApp(nativeDownloadModel) && nativeDownloadModel.hasSendInstallFinish.compareAndSet(false, true)) {
            AdEventHandler.getInstance().sendEvent(nativeDownloadModel.getEventTag(), "install_finish", generateInstallFinishEventParams(nativeDownloadModel, str, nativeDownloadModel.getInstallScene() != 4 ? 3 : 4), nativeDownloadModel);
            SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeDownloadModel);
        }
    }

    private static DownloadInfo getDownloadInfo(List<DownloadInfo> list, String str) {
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
            return null;
        }
        for (DownloadInfo downloadInfo : list) {
            if (downloadInfo != null) {
                if (str.equals(downloadInfo.getPackageName())) {
                    return downloadInfo;
                }
                if (ToolUtils.isPackageMatchApk(GlobalInfo.getContext(), downloadInfo.getTargetFilePath(), str)) {
                    return downloadInfo;
                }
            }
        }
        return null;
    }

    public static JSONObject monitorDownloadInfo(JSONObject jSONObject, DownloadInfo downloadInfo) {
        if (jSONObject == null || downloadInfo == null) {
            return jSONObject;
        }
        int i = 1;
        if (DownloadSetting.obtain(downloadInfo.getId()).optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.KEY_DOWNLOAD_EVENT_OPT, 1) == 0) {
            return jSONObject;
        }
        try {
            jSONObject.put("download_id", downloadInfo.getId());
            jSONObject.put("name", downloadInfo.getName());
            jSONObject.put("cur_bytes", downloadInfo.getCurBytes());
            jSONObject.put("total_bytes", downloadInfo.getTotalBytes());
            jSONObject.put("network_quality", downloadInfo.getNetworkQuality());
            jSONObject.put(MonitorConstants.EXTRA_CUR_NETWORK_QUALITY, NetTrafficManager.getInstance().getCurrentNetworkQuality().name());
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ONLY_WIFI, downloadInfo.isOnlyWifi() ? 1 : 0);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_NEED_HTTPS_DEGRADE, downloadInfo.isNeedHttpsToHttpRetry() ? 1 : 0);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_HTTPS_DEGRADE_RETRY_USED, downloadInfo.isHttpsToHttpRetryUsed() ? 1 : 0);
            jSONObject.put("chunk_count", downloadInfo.getChunkCount());
            jSONObject.put("retry_count", downloadInfo.getRetryCount());
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CUR_RETRY_TIME, downloadInfo.getCurRetryTime());
            jSONObject.put("need_retry_delay", downloadInfo.isNeedRetryDelay() ? 1 : 0);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_BACKUP_URL_USED, downloadInfo.isBackUpUrlUsed() ? 1 : 0);
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_HEAD_CONNECTION_ERROR_MSG, downloadInfo.getHeadConnectionException() != null ? downloadInfo.getHeadConnectionException() : "");
            jSONObject.put("need_independent_process", downloadInfo.isNeedIndependentProcess() ? 1 : 0);
            jSONObject.put(MonitorConstants.EXTRA_TOTAL_RETRY_COUNT, downloadInfo.getTotalRetryCount());
            jSONObject.put(MonitorConstants.EXTRA_CUR_RETRY_TIME_IN_TOTAL, downloadInfo.getCurRetryTimeInTotal());
            jSONObject.put(MonitorConstants.EXTRA_REAL_DOWNLOAD_TIME, downloadInfo.getRealDownloadTime());
            jSONObject.put(EventConstants.ExtraJson.KEY_FIRST_SPEED_TIME, downloadInfo.getFirstSpeedTime());
            jSONObject.put(EventConstants.ExtraJson.KEY_ALL_CONNECT_TIME, downloadInfo.getAllConnectTime());
            jSONObject.put(EventConstants.ExtraJson.KEY_DOWNLOAD_PREPARE_TIME, downloadInfo.getDownloadPrepareTime());
            jSONObject.put("download_time", downloadInfo.getRealDownloadTime() + downloadInfo.getAllConnectTime() + downloadInfo.getDownloadPrepareTime());
            jSONObject.put(MonitorConstants.EXTRA_CHUNK_DOWNGRADE_UESD, downloadInfo.isChunkDowngradeRetryUsed() ? 1 : 0);
            jSONObject.put("need_chunk_downgrade_retry", downloadInfo.isNeedChunkDowngradeRetry() ? 1 : 0);
            jSONObject.put("failed_resume_count", downloadInfo.getFailedResumeCount());
            jSONObject.put(MonitorConstants.EXTRA_PRECONNECT_LEVEL, downloadInfo.getPreconnectLevel());
            jSONObject.put("md5", downloadInfo.getMd5());
            jSONObject.put("expect_file_length", downloadInfo.getExpectFileLength());
            jSONObject.put("retry_schedule_count", downloadInfo.getRetryScheduleCount());
            jSONObject.put("rw_concurrent", downloadInfo.isRwConcurrent() ? 1 : 0);
            double curBytes = downloadInfo.getCurBytes() / 1048576.0d;
            double realDownloadTime = downloadInfo.getRealDownloadTime() / 1000.0d;
            if (curBytes > 0.0d && realDownloadTime > 0.0d) {
                double d = curBytes / realDownloadTime;
                try {
                    jSONObject.put("download_speed", d);
                } catch (Exception unused) {
                }
                Logger.d(TAG, "download speed : " + d + "MB/s");
            }
            try {
                jSONObject.put(EventConstants.ExtraJson.KEY_IS_DOWNLOAD_SERVICE_FOREGROUND, Downloader.getInstance(GlobalInfo.getContext()).isDownloadServiceForeground(downloadInfo.getId()) ? 1 : 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (downloadInfo.getBackUpUrls() != null) {
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_BACKUP_URL_COUNT, downloadInfo.getBackUpUrls().size());
                jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_CUR_BACKUP_URL_INDEX, downloadInfo.getCurBackUpUrlIndex());
            }
            jSONObject.put(EventConstants.ExtraJson.KEY_CLEAR_SPACE_RESTART_TIMES, DownloadOptimizationManager.getInstance().getRestartTaskTimes(downloadInfo.getUrl()));
            jSONObject.put("mime_type", downloadInfo.getMimeType());
            if (!DownloadUtils.isNetworkConnected(GlobalInfo.getContext())) {
                i = 2;
            }
            jSONObject.put(EventConstants.ExtraJson.NETWORK_AVAILABLE, i);
            jSONObject.put("status_code", downloadInfo.getHttpStatusCode());
            appendSpaceExtraInfo(jSONObject, downloadInfo);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject appendSpaceExtraInfo(JSONObject jSONObject, DownloadInfo downloadInfo) {
        if (jSONObject == null || downloadInfo == null || DownloadSetting.obtain(downloadInfo.getId()).optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.KEY_DOWNLOAD_EVENT_OPT, 1) == 0) {
            return jSONObject;
        }
        try {
            long externalAvailableSpaceBytes = ToolUtils.getExternalAvailableSpaceBytes(0L);
            double d = externalAvailableSpaceBytes;
            jSONObject.put("available_space", d / 1048576.0d);
            long totalBytes = downloadInfo.getTotalBytes();
            double d2 = totalBytes;
            jSONObject.put(EventConstants.ExtraJson.APK_SIZE, d2 / 1048576.0d);
            if (externalAvailableSpaceBytes > 0 && totalBytes > 0) {
                jSONObject.put(EventConstants.ExtraJson.KEY_AVAILABLE_SPACE_RATIO, d / d2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public void tryRecordFileMd5(DownloadInfo downloadInfo, final NativeDownloadModel nativeDownloadModel) {
        if (downloadInfo == null || nativeDownloadModel == null || DownloadSetting.obtain(downloadInfo.getId()).optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.KEY_INSTALL_FINISH_CHECK_TTMD5, 1) == 0) {
            return;
        }
        final String targetFilePath = downloadInfo.getTargetFilePath();
        if (TextUtils.isEmpty(targetFilePath)) {
            return;
        }
        DownloadComponentManager.getInstance().submitIOTask(new Runnable() { // from class: com.ss.android.downloadlib.AdDownloadCompletedEventHandlerImpl.3
            @Override // java.lang.Runnable
            public void run() {
                String strComputeFileTTMd5 = AntiHijackUtils.computeFileTTMd5(targetFilePath);
                if (TextUtils.isEmpty(strComputeFileTTMd5)) {
                    return;
                }
                GlobalInfo.getContext().getSharedPreferences(DownloadConstants.SP_MD5, 0).edit().putString(String.valueOf(nativeDownloadModel.getId()), strComputeFileTTMd5).apply();
            }
        });
    }

    private static void sendDownloadFinishEvent(NativeDownloadModel nativeDownloadModel) throws JSONException {
        if (nativeDownloadModel == null) {
            return;
        }
        String downloadFinishReason = TextUtils.isEmpty(nativeDownloadModel.getDownloadFinishReason()) ? "" : nativeDownloadModel.getDownloadFinishReason();
        DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(nativeDownloadModel.getDownloadId());
        nativeDownloadModel.setDownloadFinishReason("");
        SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeDownloadModel);
        JSONObject jSONObjectMonitorDownloadInfo = monitorDownloadInfo(new JSONObject(), downloadInfo);
        try {
            jSONObjectMonitorDownloadInfo.putOpt(EventConstants.ExtraJson.DOWNLOAD_FINISH_REASON, downloadFinishReason);
            jSONObjectMonitorDownloadInfo.putOpt(EventConstants.ExtraJson.FINISH_FROM_RESERVE_WIFI, Integer.valueOf(downloadInfo.isDownloadFromReserveWifi() ? 1 : 0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        EventAppendUtils.appendAhExtraInfo(jSONObjectMonitorDownloadInfo, downloadInfo.getId());
        try {
            jSONObjectMonitorDownloadInfo.put(EventConstants.ExtraJson.KEY_DOWNLOAD_FAILED_TIMES, nativeModelByInfo.getDownloadFailedTimes());
            jSONObjectMonitorDownloadInfo.put(EventConstants.ExtraJson.KEY_CAN_SHOW_NOTIFICATION, NotificationPermissionHelper.isNotificationEnabled() ? 1 : 2);
            if (downloadInfo.getExpectFileLength() > 0 && downloadInfo.getTotalBytes() > 0) {
                jSONObjectMonitorDownloadInfo.put(EventConstants.ExtraJson.KEY_FILE_LENGTH_GAP, downloadInfo.getExpectFileLength() - downloadInfo.getTotalBytes());
            }
            jSONObjectMonitorDownloadInfo.put(EventConstants.ExtraJson.TTMD5_STATUS, downloadInfo.getTTMd5CheckStatus());
            jSONObjectMonitorDownloadInfo.put(EventConstants.ExtraJson.KEY_HAS_SEND_DOWNLOAD_FAILED_FINALLY, nativeModelByInfo.hasSendDownloadFailedFinally.get() ? 1 : 2);
            jSONObjectMonitorDownloadInfo.put(EventConstants.ExtraJson.KEY_IS_UPDATE_DOWNLOAD, nativeModelByInfo.isUpdateDownload() ? 1 : 2);
            EventAppendUtils.appendPatchApplyArgJson(nativeModelByInfo, jSONObjectMonitorDownloadInfo);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AdEventHandler.getInstance().sendEvent("download_finish", jSONObjectMonitorDownloadInfo, nativeDownloadModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public void tryListenInstallFinishEvent(NativeDownloadModel nativeDownloadModel) {
        SystemClock.sleep(20000L);
        int i = 15;
        while (i > 0) {
            if (ToolUtils.isInstalledApp(nativeDownloadModel)) {
                onAppInstalled(nativeDownloadModel.getPackageName());
                return;
            }
            i--;
            if (i == 0) {
                return;
            } else {
                SystemClock.sleep(20000L);
            }
        }
    }

    private int checkApkHijack(NativeDownloadModel nativeDownloadModel, DownloadInfo downloadInfo, String str, JSONObject jSONObject) throws PackageManager.NameNotFoundException {
        int apkFileVersionCode = AppDownloadUtils.getApkFileVersionCode(GlobalInfo.getContext(), downloadInfo);
        int versionCode = ToolUtils.getVersionCode(GlobalInfo.getContext(), str);
        if (apkFileVersionCode > 0 && versionCode > 0 && apkFileVersionCode != versionCode) {
            return versionCode > apkFileVersionCode ? DownloadConstants.INSTALL_FINISH_VERSION_HIGHER : DownloadConstants.INSTALL_FINISH_VERSION_LOWER;
        }
        if (DownloadSetting.obtain(nativeDownloadModel.getDownloadId()).optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.KEY_INSTALL_FINISH_CHECK_TTMD5, 1) != 1) {
            return 3001;
        }
        String string = GlobalInfo.getContext().getSharedPreferences(DownloadConstants.SP_MD5, 0).getString(String.valueOf(nativeDownloadModel.getId()), null);
        if (TextUtils.isEmpty(string) && downloadInfo != null) {
            string = AntiHijackUtils.computeFileTTMd5(downloadInfo.getTargetFilePath());
        }
        int iCheckTTMd5 = AntiHijackUtils.checkTTMd5(string, AntiHijackUtils.getApkFilePath(str));
        try {
            jSONObject.put(EventConstants.ExtraJson.TTMD5_STATUS, iCheckTTMd5);
        } catch (Throwable unused) {
        }
        if (iCheckTTMd5 == 0) {
            return 3000;
        }
        return iCheckTTMd5 == 1 ? 3002 : 3001;
    }

    public static String checkPackageNameError(@NonNull DownloadInfo downloadInfo, @NonNull NativeDownloadModel nativeDownloadModel) throws JSONException {
        File file = new File(downloadInfo.getSavePath(), downloadInfo.getName());
        String str = null;
        if (file.exists()) {
            try {
                PackageInfo packageArchiveInfo = GlobalInfo.getContext().getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), AppDownloadUtils.getPackageInfoFlag());
                if (packageArchiveInfo != null) {
                    str = packageArchiveInfo.packageName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(str) && !str.equals(downloadInfo.getPackageName())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(EventConstants.ExtraJson.REAL_PACKAGE_NAME, str);
                jSONObject.put(EventConstants.ExtraJson.INPUT_PACKAGE_NAME, downloadInfo.getPackageName());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            AdEventHandler.getInstance().sendEvent(EventConstants.Tag.EMBEDED_AD, EventConstants.Label.PACKAGE_NAME_ERROR, jSONObject, nativeDownloadModel);
            return str;
        }
        return downloadInfo.getPackageName();
    }

    @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppForeground() {
        Logger.d(TAG, "onAppForeground()");
        checkIfInstallCanceled();
        checkEventStatus(5);
    }

    @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppBackground() {
        Logger.d(TAG, "onAppBackground()");
        checkEventStatus(6);
    }

    synchronized void checkIfInstallCanceled() {
        CheckInstallFailedRunnable checkInstallFailedRunnable = this.mCheckInstallFailedRunnable;
        if (checkInstallFailedRunnable != null) {
            checkInstallFailedRunnable.markInstallCanceled();
            this.mCheckInstallFailedRunnable = null;
        }
    }

    @Override // com.ss.android.downloadad.api.AdDownloadCompletedEventHandler
    public void checkEventStatus(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.mLastCheckEventStatusTimeMills < CHECK_EVENT_STATUS_MIN_INTERVAL) {
            return;
        }
        DownloadComponentManager.getInstance().submitScheduledTask(new CheckAdEventRunnable(i), this.mLastCheckEventStatusTimeMills > 0 ? 2000L : 8000L);
        this.mLastCheckEventStatusTimeMills = jCurrentTimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public void trySendAndRefreshAdEvent(@NonNull ConcurrentHashMap<Long, NativeDownloadModel> concurrentHashMap, int i) throws JSONException {
        ArrayList arrayList = new ArrayList();
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (NativeDownloadModel nativeDownloadModel : concurrentHashMap.values()) {
            if (nativeDownloadModel.hasSendInstallFinish.get()) {
                if (jCurrentTimeMillis - nativeDownloadModel.getTimeStamp() >= DownloadSetting.obtain(nativeDownloadModel.getDownloadId()).optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.START_EVENT_EXPIRE_HOURS, 168) * 60 * 60 * 1000) {
                    arrayList.add(Long.valueOf(nativeDownloadModel.getId()));
                }
            } else if (nativeDownloadModel.getDownloadStatus() == 1) {
                if (trySendDownloadFailedFinallyEvent(nativeDownloadModel) <= 0 && jCurrentTimeMillis - nativeDownloadModel.getTimeStamp() >= DownloadSetting.obtain(nativeDownloadModel.getDownloadId()).optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.START_EVENT_EXPIRE_HOURS, 168) * 60 * 60 * 1000) {
                    arrayList.add(Long.valueOf(nativeDownloadModel.getId()));
                }
            } else if (nativeDownloadModel.getDownloadStatus() == 2) {
                if (!nativeDownloadModel.isListeningInstallFinish()) {
                    if (ToolUtils.isInstalledApp(nativeDownloadModel)) {
                        if (nativeDownloadModel.getInstallScene() == 4) {
                            i = nativeDownloadModel.getInstallScene();
                        }
                        AdEventHandler.getInstance().sendInstallFinishEvent(generateInstallFinishEventParams(nativeDownloadModel, nativeDownloadModel.getPackageName(), i), nativeDownloadModel);
                        arrayList.add(Long.valueOf(nativeDownloadModel.getId()));
                        DownloadOptimizationManager.deleteDownloadFileWhenInstall(nativeDownloadModel);
                    } else if (jCurrentTimeMillis - nativeDownloadModel.getTimeStamp() >= DownloadSetting.obtain(nativeDownloadModel.getDownloadId()).optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.FINISH_EVENT_EXPIRE_HOURS, 168) * 60 * 60 * 1000) {
                        arrayList.add(Long.valueOf(nativeDownloadModel.getId()));
                    } else if (TextUtils.isEmpty(nativeDownloadModel.getPackageName())) {
                        arrayList.add(Long.valueOf(nativeDownloadModel.getId()));
                    }
                }
            } else {
                arrayList.add(Long.valueOf(nativeDownloadModel.getId()));
            }
        }
        ModelManager.getInstance().removeNativeModelsById(arrayList);
    }

    @Override // com.ss.android.socialbase.appdownloader.AhUtils.OnAhAttemptListener
    public void onAhAttemptResult(DownloadInfo downloadInfo, AhAttempt ahAttempt) {
        JSONObject jSONObjectTrySendAhResultEvent;
        if (downloadInfo == null || ahAttempt == null) {
            return;
        }
        JSONArray jSONArrayOptJSONArray = DownloadSetting.obtain(downloadInfo.getId()).optJSONArray(com.ss.android.downloadlib.constants.DownloadSettingKeys.KEY_AH_REPORT_CONFIG);
        if (ahAttempt.error_code != 0) {
            downloadInfo.getTempCacheData().remove(Constants.INTENT);
        }
        if (jSONArrayOptJSONArray == null || (jSONObjectTrySendAhResultEvent = trySendAhResultEvent(downloadInfo, ahAttempt)) == null) {
            return;
        }
        downloadInfo.getTempCacheData().put("ah_ext_json", jSONObjectTrySendAhResultEvent);
    }

    @Override // com.ss.android.socialbase.downloader.depend.IOpenInstallerListener
    public void onOpenInstaller(@Nullable final DownloadInfo downloadInfo, @Nullable String str) throws JSONException {
        if (downloadInfo == null) {
            TTDownloaderMonitor.inst().monitorDataError("info is null");
        } else if ((DownloadSetting.obtain(downloadInfo).optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.CHECK_APPLINK_MODE) & 2) != 0) {
            final JSONObject jSONObject = (JSONObject) downloadInfo.getTempCacheData().get("ah_ext_json");
            AppLinkMonitor.getInstance().checkMarketOrInstaller(new AppLinkEventCallback() { // from class: com.ss.android.downloadlib.AdDownloadCompletedEventHandlerImpl.4
                @Override // com.ss.android.downloadlib.applink.AppLinkEventCallback
                public void onResult(boolean z) throws JSONException {
                    if (!z) {
                        Intent intent = (Intent) downloadInfo.getTempCacheData().get(Constants.INTENT);
                        if (intent != null) {
                            downloadInfo.getTempCacheData().remove(Constants.INTENT);
                            AppDownloadUtils.startPackageInstaller(GlobalInfo.getContext(), intent);
                            ToolUtils.safePut(jSONObject, "backup", 1);
                        } else {
                            ToolUtils.safePut(jSONObject, "backup", 2);
                        }
                    }
                    NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
                    if (nativeModelByInfo != null) {
                        AdEventHandler.getInstance().sendUnityEvent(z ? EventConstants.UnityLabel.INSTALLER_DELAY_SUCCESS : EventConstants.UnityLabel.INSTALLER_DELAY_FAILED, jSONObject, nativeModelByInfo);
                    } else {
                        TTDownloaderMonitor.inst().monitorPathError("ah nativeModel=null");
                    }
                    if (z) {
                        GlobalInfo.getOpenAppListener().onOpenApp(GlobalInfo.getContext(), null, null, null, null, 1);
                    }
                }
            });
        }
    }

    private int trySendDownloadFailedFinallyEvent(NativeDownloadModel nativeDownloadModel) {
        int realStatus;
        double dOptDouble = DownloadSetting.obtain(nativeDownloadModel.getDownloadId()).optDouble(com.ss.android.downloadlib.constants.DownloadSettingKeys.DOWNLOAD_FAILED_FINALLY_HOURS, 48.0d);
        if (dOptDouble <= 0.0d) {
            return -1;
        }
        if (System.currentTimeMillis() - nativeDownloadModel.getTimeStamp() < dOptDouble * 60.0d * 60.0d * 1000.0d) {
            return 1;
        }
        if (nativeDownloadModel.hasSendDownloadFailedFinally.get()) {
            return 0;
        }
        DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(nativeDownloadModel.getDownloadId());
        if (downloadInfo == null || (realStatus = downloadInfo.getRealStatus()) == -3 || realStatus == -4) {
            return -1;
        }
        if (!DownloadStatus.isDownloading(realStatus) && nativeDownloadModel.hasSendDownloadFailedFinally.compareAndSet(false, true)) {
            try {
                JSONObject jSONObject = new JSONObject();
                monitorDownloadInfo(jSONObject, downloadInfo);
                jSONObject.putOpt("download_status", Integer.valueOf(realStatus));
                jSONObject.putOpt(EventConstants.ExtraJson.FAIL_STATUS, Integer.valueOf(nativeDownloadModel.getLastFailedErrCode()));
                jSONObject.putOpt(EventConstants.ExtraJson.FAIL_MSG, nativeDownloadModel.getLastFailedErrMsg());
                jSONObject.put(EventConstants.ExtraJson.KEY_DOWNLOAD_FAILED_TIMES, nativeDownloadModel.getDownloadFailedTimes());
                if (downloadInfo.getTotalBytes() > 0) {
                    jSONObject.put("download_percent", downloadInfo.getCurBytes() / downloadInfo.getTotalBytes());
                }
                jSONObject.put(EventConstants.ExtraJson.KEY_IS_UPDATE_DOWNLOAD, nativeDownloadModel.isUpdateDownload() ? 1 : 2);
                AdEventHandler.getInstance().sendEvent(nativeDownloadModel.getEventTag(), EventConstants.Label.DOWNLOAD_FAILED_FINALLY, jSONObject, nativeDownloadModel);
                SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeDownloadModel);
                return 0;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return 1;
    }

    private JSONObject trySendAhResultEvent(@NonNull DownloadInfo downloadInfo, AhAttempt ahAttempt) throws JSONException {
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        if (nativeModelByInfo == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        ahAttempt.appendJSONObject(jSONObject);
        try {
            jSONObject.put("download_id", downloadInfo.getId());
            jSONObject.put("name", downloadInfo.getName());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        EventAppendUtils.appendAhExtraInfo(jSONObject, downloadInfo.getId());
        AdEventHandler.getInstance().sendEvent(EventConstants.Tag.EMBEDED_AD, EventConstants.Label.ANTI_HIJACK_RESULT, jSONObject, nativeModelByInfo);
        return jSONObject;
    }

    public void tryListenInstallFinish(NativeDownloadModel nativeDownloadModel) {
        DownloadComponentManager.getInstance().submitCPUTask(new ListenInstallFinishRunnable(nativeDownloadModel));
    }

    private class ListenInstallFinishRunnable implements Runnable {
        private final NativeDownloadModel nativeModel;

        public ListenInstallFinishRunnable(NativeDownloadModel nativeDownloadModel) {
            this.nativeModel = nativeDownloadModel;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    this.nativeModel.setIsListeningInstallFinish(true);
                    AdDownloadCompletedEventHandlerImpl.this.tryListenInstallFinishEvent(this.nativeModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } finally {
                this.nativeModel.setIsListeningInstallFinish(false);
            }
        }
    }

    @WorkerThread
    private class CheckAdEventRunnable implements Runnable {
        private final int mScene;

        public CheckAdEventRunnable(int i) {
            this.mScene = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ModelManager.getInstance().init();
                ConcurrentHashMap<Long, NativeDownloadModel> allNativeModels = ModelManager.getInstance().getAllNativeModels();
                if (allNativeModels == null || allNativeModels.isEmpty()) {
                    return;
                }
                AdDownloadCompletedEventHandlerImpl.this.trySendAndRefreshAdEvent(allNativeModels, this.mScene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private JSONObject generateInstallFinishEventParams(NativeDownloadModel nativeDownloadModel, String str, int i) {
        AhAttempt ahAttemptFromJson;
        JSONObject jSONObject = new JSONObject();
        try {
            DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(nativeDownloadModel.getDownloadId());
            jSONObject.putOpt(DownloadConstants.EVENT_SCENE, Integer.valueOf(i));
            EventAppendUtils.appendAhExtraInfo(jSONObject, nativeDownloadModel.getDownloadId());
            EventAppendUtils.appendPatchApplyArgJson(nativeDownloadModel, jSONObject);
            jSONObject.put(EventConstants.ExtraJson.KEY_IS_UPDATE_DOWNLOAD, nativeDownloadModel.isUpdateDownload() ? 1 : 2);
            jSONObject.put(EventConstants.ExtraJson.KEY_INSTALL_AFTER_BACK_APP, nativeDownloadModel.isInstallAfterBackApp() ? 1 : 2);
            jSONObject.putOpt(EventConstants.ExtraJson.CLEAN_SPACE_INSTALL_PARAMS, nativeDownloadModel.isInstallAfterCleanSpace() ? "1" : "2");
            if (downloadInfo != null) {
                monitorDownloadInfo(jSONObject, downloadInfo);
                try {
                    jSONObject.put(EventConstants.ExtraJson.KEY_UNINSTALL_RESUME_COUNT, downloadInfo.getUninstallResumeCount());
                    if (nativeDownloadModel.getJumpInstallTime() > 0) {
                        jSONObject.put(EventConstants.ExtraJson.KEY_INSTALL_TIME, System.currentTimeMillis() - nativeDownloadModel.getJumpInstallTime());
                    }
                } catch (Throwable unused) {
                }
                String string = DownloadUtils.getString(downloadInfo.getTempCacheData().get(AhAttempt.JSON_TITLE), null);
                if (!TextUtils.isEmpty(string) && (ahAttemptFromJson = AhAttempt.fromJson(string)) != null) {
                    ahAttemptFromJson.appendJSONObject(jSONObject);
                }
            }
            int iCheckApkHijack = checkApkHijack(nativeDownloadModel, downloadInfo, str, jSONObject);
            jSONObject.put(EventConstants.ExtraJson.FAIL_STATUS, iCheckApkHijack);
            if (iCheckApkHijack == 3000) {
                jSONObject.put("hijack", 2);
            } else if (iCheckApkHijack == 3001) {
                jSONObject.put("hijack", 0);
            } else {
                jSONObject.put("hijack", 1);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public void trySendClearSpaceEvent(DownloadInfo downloadInfo, long j, long j2, long j3, long j4, long j5, boolean z) throws JSONException {
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        if (nativeModelByInfo == null) {
            TTDownloaderMonitor.inst().monitorDataError("trySendClearSpaceEvent nativeModel null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_SPACE_BEFORE, Double.valueOf(j / 1048576.0d));
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_SPACE_CLEANED, Double.valueOf((j2 - j) / 1048576.0d));
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_CLEAN_UP_TIME_COST, Long.valueOf(j4));
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_IS_DOWNLOAD_RESTARTED, Integer.valueOf(z ? 1 : 0));
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_BYTE_REQUIRED, Long.valueOf(j3));
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_BYTE_REQUIRED_AFTER, Double.valueOf((j3 - j2) / 1048576.0d));
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_CLEAR_SLEEP_TIME, Long.valueOf(j5));
            EventAppendUtils.appendInfoJson(downloadInfo, jSONObject);
            AdEventHandler.getInstance().sendUnityEvent("cleanup", jSONObject, nativeModelByInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
