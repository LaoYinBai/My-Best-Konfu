package com.ss.android.downloadlib.addownload;

import android.content.Context;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ss.android.download.api.config.IPermissionCallback;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.download.DownloadStatusChangeListener2;
import com.ss.android.download.api.model.DownloadShortInfo;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.DownloadDispatcher;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.optimize.AppDownloadDiskSpaceHandler;
import com.ss.android.downloadlib.applink.AdAppLinkUtils;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.event.ClickEventHelper;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.DownloadDataAdapterUtils;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.downloadlib.utils.PermissionUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.downloadlib.utils.WeakHandler;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.AppTaskBuilder;
import com.ss.android.socialbase.downloader.depend.AbsDownloadExtListener;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadHelper implements WeakHandler.IHandler {
    private static final String TAG = "DownloadHelper";
    private long adId;
    private ModelBox box;
    private CommonDownloadHandler handler;
    private boolean mHasSendFileStatusEvent = false;
    private OnProgressReceivedListener mProgressReceivedListener;

    public interface OnProgressReceivedListener {
        void onReceivedProgress(DownloadInfo downloadInfo);
    }

    @Override // com.ss.android.downloadlib.utils.WeakHandler.IHandler
    public void handleMsg(Message message) {
    }

    public DownloadHelper(CommonDownloadHandler commonDownloadHandler) {
        this.handler = commonDownloadHandler;
    }

    public void setAdId(long j) {
        this.adId = j;
        this.box = ModelManager.getInstance().getModelBox(j);
        if (this.box.notValid()) {
            TTDownloaderMonitor.inst().monitorDataError("setAdId ModelBox notValid");
        }
    }

    public void resetData(DownloadInfo downloadInfo) {
        this.mHasSendFileStatusEvent = false;
        sendClickStartWhenUnbind(downloadInfo);
    }

    public void sendClickStartWhenUnbind(@Nullable DownloadInfo downloadInfo) {
        if (this.mProgressReceivedListener != null) {
            this.mProgressReceivedListener.onReceivedProgress(downloadInfo);
            this.mProgressReceivedListener = null;
        }
    }

    boolean shouldInterceptClick(Context context, int i, boolean z) {
        if (ToolUtils.isInstalledApp(this.box.model)) {
            NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(this.box.id);
            if (nativeDownloadModel != null) {
                DownloadNotificationManager.getInstance().cancelNotification(nativeDownloadModel.getDownloadId());
            }
            return AdAppLinkUtils.tryAppLinkWhenClick(this.box);
        }
        if (shouldOpenMarket(i) && !TextUtils.isEmpty(this.box.model.getPackageName()) && GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_DISABLE_MARKET) != 1) {
            return AdAppLinkUtils.tryOpenMarketWhenClick(this.box, i);
        }
        if (!z || this.box.controller.getDownloadMode() != 4 || this.handler.shouldOpenQuickApp()) {
            return false;
        }
        this.handler.tryPerformItemClick(true);
        return true;
    }

    private boolean shouldOpenMarket(int i) {
        if (this.box.controller.getDownloadMode() == 2 && i == 2) {
            return true;
        }
        return this.box.controller.getDownloadMode() == 2 && i == 1 && GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.DISABLE_LP_IF_MARKET, 0) == 1;
    }

    boolean shouldInterceptButtonClick() {
        return ToolUtils.isInstalledApp(this.box.model) && !DownloadInsideHelper.isAllowNormalLink(this.box.controller.getLinkMode());
    }

    public int getButtonClickType(boolean z) {
        return (shouldInterceptButtonClick() && z) ? 1 : 0;
    }

    boolean shouldResponseItemClick(boolean z) {
        return !z && this.box.controller.getDownloadMode() == 1;
    }

    private boolean shouldAddToDownloadManage() {
        return isDownloadDataValid() && canAddToDownloadManage();
    }

    private boolean isDownloadDataValid() {
        return (this.box.model == null || TextUtils.isEmpty(this.box.model.getPackageName()) || TextUtils.isEmpty(this.box.model.getDownloadUrl())) ? false : true;
    }

    private boolean canAddToDownloadManage() {
        return this.box.controller.isAddToDownloadManage();
    }

    void checkConditionBeforeDownload(@NonNull final IPermissionCallback iPermissionCallback) {
        if (!TextUtils.isEmpty(this.box.model.getFilePath())) {
            String filePath = this.box.model.getFilePath();
            if (filePath.startsWith(Environment.getDataDirectory().getAbsolutePath())) {
                iPermissionCallback.onGranted();
                return;
            } else {
                try {
                    if (filePath.startsWith(GlobalInfo.getContext().getExternalCacheDir().getParent())) {
                        iPermissionCallback.onGranted();
                        return;
                    }
                } catch (Exception unused) {
                }
            }
        }
        checkPermission(new IPermissionCallback() { // from class: com.ss.android.downloadlib.addownload.DownloadHelper.1
            @Override // com.ss.android.download.api.config.IPermissionCallback
            public void onGranted() {
                iPermissionCallback.onGranted();
            }

            @Override // com.ss.android.download.api.config.IPermissionCallback
            public void onDenied(String str) throws JSONException {
                GlobalInfo.getDownloadUIFactory().showToastWithDuration(1, GlobalInfo.getContext(), DownloadHelper.this.box.model, "您已禁止使用存储权限，请授权后再下载", null, 1);
                AdEventHandler.getInstance().sendEvent(DownloadHelper.this.adId, 1);
                iPermissionCallback.onDenied(str);
            }
        });
    }

    private void checkPermission(final IPermissionCallback iPermissionCallback) {
        if (!PermissionUtils.hasPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            PermissionUtils.request(new String[]{MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE}, new PermissionUtils.Listener() { // from class: com.ss.android.downloadlib.addownload.DownloadHelper.2
                @Override // com.ss.android.downloadlib.utils.PermissionUtils.Listener
                public void onGranted() {
                    if (iPermissionCallback != null) {
                        iPermissionCallback.onGranted();
                    }
                }

                @Override // com.ss.android.downloadlib.utils.PermissionUtils.Listener
                public void onDenied(String str) {
                    if (iPermissionCallback != null) {
                        iPermissionCallback.onDenied(str);
                    }
                }
            });
        } else if (iPermissionCallback != null) {
            iPermissionCallback.onGranted();
        }
    }

    void handleUiChangeMessage(Message message, DownloadShortInfo downloadShortInfo, Map<Integer, Object> map) {
        if (message == null || message.what != 3) {
            return;
        }
        DownloadInfo downloadInfo = (DownloadInfo) message.obj;
        if (message.arg1 != 1 && message.arg1 != 6 && message.arg1 == 2) {
            if (downloadInfo.getIsFirstDownload()) {
                DownloadDispatcher.getInstance().notifyDownloadStart(this.box.model, this.box.controller, this.box.event);
                downloadInfo.setFirstDownload(false);
            }
            AdEventHandler.getInstance().sendDownloadResumeEvent(downloadInfo);
        }
        downloadShortInfo.updateFromNewDownloadInfo(downloadInfo);
        DownloadPercentHelper.handleDownloadShortInfoSize(downloadShortInfo);
        int notificationType = AppDownloadUtils.getNotificationType(downloadInfo.getStatus());
        long totalBytes = downloadInfo.getTotalBytes();
        int curBytes = totalBytes > 0 ? (int) ((downloadInfo.getCurBytes() * 100) / totalBytes) : 0;
        if ((totalBytes > 0 || DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_CLICK_START)) && this.mProgressReceivedListener != null) {
            this.mProgressReceivedListener.onReceivedProgress(downloadInfo);
            this.mProgressReceivedListener = null;
        }
        for (DownloadStatusChangeListener downloadStatusChangeListener : getStatusListeners(map)) {
            switch (notificationType) {
                case 1:
                    if (downloadInfo.getStatus() != 11) {
                        downloadStatusChangeListener.onDownloadActive(downloadShortInfo, DownloadPercentHelper.handlePercent(downloadInfo.getId(), curBytes));
                        break;
                    } else {
                        Iterator<DownloadStatusChangeListener2> it = getDownloadStatusChangeListener2s(map).iterator();
                        while (it.hasNext()) {
                            it.next().onWaitingDownloadCompleteHandler(downloadInfo);
                        }
                        break;
                    }
                case 2:
                    downloadStatusChangeListener.onDownloadPaused(downloadShortInfo, DownloadPercentHelper.handlePercent(downloadInfo.getId(), curBytes));
                    break;
                case 3:
                    if (downloadInfo.getStatus() == -4) {
                        downloadStatusChangeListener.onIdle();
                        break;
                    } else if (downloadInfo.getStatus() == -1) {
                        downloadStatusChangeListener.onDownloadFailed(downloadShortInfo);
                        break;
                    } else if (downloadInfo.getStatus() != -3) {
                        break;
                    } else if (ToolUtils.isInstalledApp(this.box.model)) {
                        downloadStatusChangeListener.onInstalled(downloadShortInfo);
                        break;
                    } else {
                        downloadStatusChangeListener.onDownloadFinished(downloadShortInfo);
                        break;
                    }
            }
        }
    }

    void sendEventIfRecommend(DownloadInfo downloadInfo) {
        if (!DownloadInsideHelper.isRecommendAd(this.box.model) || this.mHasSendFileStatusEvent) {
            return;
        }
        AdEventHandler.getInstance().sendRecommendEvent(EventConstants.Label.FILE_STATUS, (downloadInfo == null || !ToolUtils.isDownloadFileExist(downloadInfo.getTargetFilePath())) ? 2 : 1, this.box);
        this.mHasSendFileStatusEvent = true;
    }

    void sendClickStartEventAfterReceivedProgress() {
        if (this.mProgressReceivedListener == null) {
            this.mProgressReceivedListener = new OnProgressReceivedListener() { // from class: com.ss.android.downloadlib.addownload.DownloadHelper.3
                @Override // com.ss.android.downloadlib.addownload.DownloadHelper.OnProgressReceivedListener
                public void onReceivedProgress(DownloadInfo downloadInfo) throws JSONException {
                    AdEventHandler.getInstance().sendEvent(DownloadHelper.this.adId, 2, downloadInfo);
                }
            };
        }
    }

    boolean shouldInstallOrOpenApp(DownloadInfo downloadInfo) {
        return shouldOpenApp() || shouldStartInstallView(downloadInfo);
    }

    private boolean shouldStartInstallView(DownloadInfo downloadInfo) {
        return !ToolUtils.isInstalledApp(this.box.model) && hasDownloadSuccessful(downloadInfo);
    }

    private boolean shouldOpenApp() {
        return ToolUtils.isInstalledApp(this.box.model) && DownloadInsideHelper.isAllowNormalLink(this.box.controller.getLinkMode());
    }

    private boolean hasDownloadSuccessful(DownloadInfo downloadInfo) {
        return downloadInfo != null && downloadInfo.getStatus() == -3 && DownloadUtils.isFileExist(downloadInfo.getSavePath(), downloadInfo.getName());
    }

    int startDownloadWithNewDownloader(Context context, IDownloadListener iDownloadListener) {
        if (context == null) {
            return 0;
        }
        Map<String, String> headers = this.box.model.getHeaders();
        ArrayList arrayList = new ArrayList();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry != null) {
                    arrayList.add(new HttpHeader(entry.getKey(), entry.getValue()));
                }
            }
        }
        String strGenerateDownloadExtra = DownloadDataAdapterUtils.generateDownloadExtra(String.valueOf(this.box.model.getId()), this.box.model.getNotificationJumpUrl(), this.box.model.isShowToast(), String.valueOf(this.box.model.getModelType()));
        DownloadSetting setting = DownloadSettingUtils.getSetting(this.box.model);
        JSONObject taskSettingJson = DownloadSettingUtils.getTaskSettingJson(this.box.model);
        if (!this.box.controller.enableAH()) {
            taskSettingJson = ToolUtils.copyJson(taskSettingJson);
            ToolUtils.safePut(taskSettingJson, com.ss.android.socialbase.downloader.setting.DownloadSettingKeys.KEY_AH_PLANS, new JSONArray());
        }
        int executorGroup = this.box.model.getExecutorGroup();
        if (this.box.model.isAd() || DownloadInsideHelper.isGame(this.box.model)) {
            executorGroup = 4;
        }
        String fileSavePath = getFileSavePath(setting);
        DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(DownloadComponentManager.getDownloadId(this.box.model.getDownloadUrl(), fileSavePath));
        if (downloadInfo != null && 3 == this.box.model.getModelType()) {
            downloadInfo.setFirstDownload(true);
        }
        AppTaskBuilder appTaskBuilderDistinctDirectory = new AppTaskBuilder(context, this.box.model.getDownloadUrl()).backUpUrls(this.box.model.getBackupUrls()).name(this.box.model.getName()).extra(strGenerateDownloadExtra).headers(arrayList).showNotification(this.box.model.isShowNotification()).needWifi(this.box.model.isNeedWifi()).saveName(this.box.model.getFileName()).savePath(fileSavePath).iconUrl(this.box.model.getAppIcon()).md5(this.box.model.getMd5()).monitorScene(this.box.model.getSdkMonitorScene()).expectFileLength(this.box.model.getExpectFileLength()).mainThreadListener(iDownloadListener).needIndependentProcess(this.box.model.needIndependentProcess() || setting.optInt("need_independent_process", 0) == 1).fileUriProvider(this.box.model.getDownloadFileUriProvider()).autoInstallWithoutNotification(this.box.model.autoInstallWithoutNotification()).packageName(this.box.model.getPackageName()).minProgressTimeMsInterval(1000).maxProgressCount(100).downloadSetting(taskSettingJson).needDefaultHttpServiceBackUp(true).needReuseFirstConnection(true).retryCount(setting.optInt("retry_count", 5)).backUpUrlRetryCount(setting.optInt(DownloadSettingKeys.KEY_BACK_UP_URL_RETRY_COUNT, 0)).needReuseFirstConnection(true).headConnectionAvailable(setting.optInt(DownloadSettingKeys.KEY_NEED_HEAD_CONNECTION, 0) == 1).needHttpsToHttpRetry(setting.optInt(DownloadSettingKeys.KEY_NEED_HTTPS_TO_HTTP_RETRY, 0) == 1).needChunkDowngradeRetry(setting.optInt("need_chunk_downgrade_retry", 1) == 1).needRetryDelay(setting.optInt("need_retry_delay", 0) == 1).retryDelayTimeArray(setting.optString(DownloadSettingKeys.KEY_RETRY_DELAY_TIME_ARRAY)).needReuseChunkRunnable(setting.optInt(DownloadSettingKeys.KEY_NEED_REUSE_RUNNABLE, 0) == 1).executorGroup(executorGroup).autoInstall(this.box.model.isAutoInstall()).distinctDirectory(this.box.model.distinctDir());
        if (!TextUtils.isEmpty(this.box.model.getMimeType())) {
            appTaskBuilderDistinctDirectory.mimeType(this.box.model.getMimeType());
        } else {
            appTaskBuilderDistinctDirectory.mimeType("application/vnd.android.package-archive");
        }
        if (setting.optInt(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.NOTIFICATION_OPT_2, 0) == 1) {
            appTaskBuilderDistinctDirectory.showNotification(false);
            appTaskBuilderDistinctDirectory.autoInstallWithoutNotification(true);
        }
        AppDownloadDiskSpaceHandler appDownloadDiskSpaceHandler = null;
        if (setting.optInt(DownloadSettingKeys.KEY_CLEAR_SPACE_USE_DISK_HANDLER, 0) == 1) {
            appDownloadDiskSpaceHandler = new AppDownloadDiskSpaceHandler();
            appTaskBuilderDistinctDirectory.diskSpaceHandler(appDownloadDiskSpaceHandler);
        }
        int iAddDownloadTaskWithNewDownloader = DownloadInsideHelper.addDownloadTaskWithNewDownloader(this.box, shouldAddToDownloadManage(), appTaskBuilderDistinctDirectory);
        if (appDownloadDiskSpaceHandler != null) {
            appDownloadDiskSpaceHandler.setDownloadId(iAddDownloadTaskWithNewDownloader);
        }
        return iAddDownloadTaskWithNewDownloader;
    }

    private String getFileSavePath(DownloadSetting downloadSetting) throws JSONException {
        if (!TextUtils.isEmpty(this.box.model.getFilePath())) {
            return this.box.model.getFilePath();
        }
        DownloadInfo appDownloadInfo = AppDownloader.getInstance().getAppDownloadInfo(GlobalInfo.getContext(), this.box.model.getDownloadUrl());
        boolean zHasPermission = PermissionUtils.hasPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE);
        String externalDownloadsDir = getExternalDownloadsDir();
        if (appDownloadInfo != null && !TextUtils.isEmpty(appDownloadInfo.getSavePath())) {
            String savePath = appDownloadInfo.getSavePath();
            if (zHasPermission || savePath.startsWith(Environment.getDataDirectory().getAbsolutePath())) {
                return savePath;
            }
            try {
                if (!TextUtils.isEmpty(externalDownloadsDir)) {
                    if (savePath.startsWith(externalDownloadsDir)) {
                        return savePath;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Downloader.getInstance(DownloadComponentManager.getAppContext()).cancel(appDownloadInfo.getId());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.KEY_CODE, Integer.valueOf(zHasPermission ? 1 : 2));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.LABEL_EXTERNAL_PERMISSION, jSONObject, this.box);
        String appDownloadPath = null;
        try {
            appDownloadPath = AppDownloadUtils.getAppDownloadPath();
        } catch (Exception unused) {
        }
        int savePathType = DownloadSettingUtils.getSavePathType(downloadSetting);
        if (savePathType != 0) {
            if (savePathType == 4 || (!zHasPermission && savePathType == 2)) {
                File filesDir = GlobalInfo.getContext().getFilesDir();
                if (!filesDir.exists()) {
                    filesDir.mkdirs();
                }
                if (filesDir.exists()) {
                    return filesDir.getAbsolutePath();
                }
            } else if ((savePathType == 3 || (!zHasPermission && savePathType == 1)) && !TextUtils.isEmpty(externalDownloadsDir)) {
                return externalDownloadsDir;
            }
        }
        return appDownloadPath;
    }

    @Nullable
    public String getExternalDownloadsDir() {
        File externalFilesDir = GlobalInfo.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDir == null) {
            return null;
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        if (externalFilesDir.exists()) {
            return externalFilesDir.getAbsolutePath();
        }
        return null;
    }

    void sendEventWithNewDownloader(DownloadInfo downloadInfo, boolean z) {
        if (this.box.model == null || downloadInfo == null || downloadInfo.getId() == 0) {
            return;
        }
        int status = downloadInfo.getStatus();
        if (status == -1 || status == -4 || DownloadInsideHelper.isRecommendAd(this.box.model)) {
            AdEventHandler.getInstance().sendClickEvent(this.adId, 2);
        } else if (z && ClickEventHelper.getInstance().sendClickEventWithClickButton() && (status == -2 || status == -3)) {
            AdEventHandler.getInstance().sendClickEvent(this.adId, 2);
        }
        switch (status) {
            case -4:
            case -1:
                sendClickStartEventAfterReceivedProgress();
                ModelManager.getInstance().putNativeModel(new NativeDownloadModel(this.box.model, this.box.event, this.box.controller, downloadInfo.getId()));
                break;
            case -3:
                if (ToolUtils.isInstalledApp(this.box.model)) {
                    TTDownloaderMonitor.inst().monitorPathError("SUCCESSED isInstalledApp");
                    break;
                } else {
                    AdEventHandler.getInstance().sendEvent(this.adId, 5, downloadInfo);
                    if (z && ClickEventHelper.getInstance().sendClicEventkWithIdAndReqId() && !ClickEventHelper.getInstance().hasClickRecord(this.adId, this.box.model.getLogExtra())) {
                        AdEventHandler.getInstance().sendClickEvent(this.adId, 2);
                        break;
                    }
                }
                break;
            case -2:
                AdEventHandler.getInstance().sendEvent(this.adId, 4, downloadInfo);
                if (z && ClickEventHelper.getInstance().sendClicEventkWithIdAndReqId() && !ClickEventHelper.getInstance().hasClickRecord(this.adId, this.box.model.getLogExtra())) {
                    AdEventHandler.getInstance().sendClickEvent(this.adId, 2);
                    break;
                }
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
                AdEventHandler.getInstance().sendEvent(this.adId, 3, downloadInfo);
                break;
        }
    }

    void refreshUIFromDownloadInfo(DownloadInfo downloadInfo, DownloadShortInfo downloadShortInfo, List<DownloadStatusChangeListener> list) {
        if (list.isEmpty()) {
            return;
        }
        if (downloadInfo == null || downloadShortInfo == null) {
            Iterator<DownloadStatusChangeListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onIdle();
            }
            return;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        int curBytes = downloadInfo.getTotalBytes() > 0 ? (int) ((downloadInfo.getCurBytes() * 100) / downloadInfo.getTotalBytes()) : 0;
        int i = curBytes >= 0 ? curBytes : 0;
        downloadShortInfo.updateFromNewDownloadInfo(downloadInfo);
        DownloadPercentHelper.handleDownloadShortInfoSize(downloadShortInfo);
        for (DownloadStatusChangeListener downloadStatusChangeListener : list) {
            switch (downloadInfo.getStatus()) {
                case -4:
                case 0:
                    if (ToolUtils.isInstalledApp(this.box.model)) {
                        downloadShortInfo.status = -3;
                        downloadStatusChangeListener.onInstalled(downloadShortInfo);
                        break;
                    } else {
                        downloadStatusChangeListener.onIdle();
                        break;
                    }
                case -3:
                    if (ToolUtils.isInstalledApp(this.box.model)) {
                        downloadStatusChangeListener.onInstalled(downloadShortInfo);
                        break;
                    } else {
                        downloadStatusChangeListener.onDownloadFinished(downloadShortInfo);
                        break;
                    }
                case -2:
                    downloadStatusChangeListener.onDownloadPaused(downloadShortInfo, DownloadPercentHelper.handlePercent(downloadInfo.getId(), i));
                    break;
                case -1:
                    downloadStatusChangeListener.onDownloadFailed(downloadShortInfo);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                    downloadStatusChangeListener.onDownloadActive(downloadShortInfo, DownloadPercentHelper.handlePercent(downloadInfo.getId(), i));
                    break;
                case 11:
                    if (downloadStatusChangeListener instanceof DownloadStatusChangeListener2) {
                        ((DownloadStatusChangeListener2) downloadStatusChangeListener).onWaitingDownloadCompleteHandler(downloadInfo);
                        break;
                    } else {
                        downloadStatusChangeListener.onDownloadActive(downloadShortInfo, DownloadPercentHelper.handlePercent(downloadInfo.getId(), i));
                        break;
                    }
            }
        }
    }

    static class AppDownloadListener extends AbsDownloadExtListener {
        private WeakHandler mUiMessageHandler;

        AppDownloadListener(WeakHandler weakHandler) {
            this.mUiMessageHandler = weakHandler;
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onPrepare(DownloadInfo downloadInfo) {
            sendUiChangeMessage(downloadInfo, 1);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onStart(DownloadInfo downloadInfo) {
            sendUiChangeMessage(downloadInfo, 2);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onProgress(DownloadInfo downloadInfo) {
            sendUiChangeMessage(downloadInfo, 4);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onPause(DownloadInfo downloadInfo) {
            sendUiChangeMessage(downloadInfo, -2);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onSuccessed(DownloadInfo downloadInfo) {
            sendUiChangeMessage(downloadInfo, -3);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onFailed(DownloadInfo downloadInfo, BaseException baseException) {
            sendUiChangeMessage(downloadInfo, -1);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onCanceled(DownloadInfo downloadInfo) {
            sendUiChangeMessage(downloadInfo, -4);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadExtListener, com.ss.android.socialbase.downloader.depend.IDownloadExtListener
        public void onWaitingDownloadCompleteHandler(DownloadInfo downloadInfo) {
            sendUiChangeMessage(downloadInfo, 11);
        }

        private void sendUiChangeMessage(DownloadInfo downloadInfo, int i) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 3;
            messageObtain.obj = downloadInfo;
            messageObtain.arg1 = i;
            this.mUiMessageHandler.sendMessage(messageObtain);
        }
    }

    @NonNull
    public static List<DownloadStatusChangeListener> getStatusListeners(Map<Integer, Object> map) {
        ArrayList arrayList = new ArrayList();
        if (map == null || map.isEmpty()) {
            return arrayList;
        }
        for (Object obj : map.values()) {
            if (obj instanceof DownloadStatusChangeListener) {
                arrayList.add((DownloadStatusChangeListener) obj);
            } else if (obj instanceof SoftReference) {
                SoftReference softReference = (SoftReference) obj;
                if (softReference.get() instanceof DownloadStatusChangeListener) {
                    arrayList.add((DownloadStatusChangeListener) softReference.get());
                }
            }
        }
        return arrayList;
    }

    @NonNull
    public static List<DownloadStatusChangeListener2> getDownloadStatusChangeListener2s(Map<Integer, Object> map) {
        ArrayList arrayList = new ArrayList();
        if (map == null || map.isEmpty()) {
            return arrayList;
        }
        for (Object obj : map.values()) {
            if (obj instanceof DownloadStatusChangeListener2) {
                arrayList.add((DownloadStatusChangeListener2) obj);
            } else if (obj instanceof SoftReference) {
                SoftReference softReference = (SoftReference) obj;
                if (softReference.get() instanceof DownloadStatusChangeListener2) {
                    arrayList.add((DownloadStatusChangeListener2) softReference.get());
                }
            }
        }
        return arrayList;
    }
}
