package com.ss.android.downloadlib;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.CleanSpaceHelper;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.optimize.CleanSpaceTask;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.downloadlib.utils.EventAppendUtils;
import com.ss.android.downloadlib.utils.TLogger;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadMonitorListener;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BaseDownloadMonitorListener implements IAppDownloadMonitorListener {
    private static String TAG = "BaseDownloadMonitorListener";
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadMonitorListener
    public void onAppDownloadMonitorSend(DownloadInfo downloadInfo, BaseException baseException, int i) {
        final DownloadModel downloadModel;
        if (downloadInfo == null) {
            return;
        }
        if (i == -1 && baseException != null) {
            JSONObject jSONObject = new JSONObject();
            EventAppendUtils.appendInfoJson(downloadInfo, jSONObject);
            AdDownloadCompletedEventHandlerImpl.monitorDownloadInfo(jSONObject, downloadInfo);
            TLogger.e("download_failed", jSONObject.toString());
        }
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        if (nativeModelByInfo == null) {
            return;
        }
        try {
            if (i != -1) {
                if (i == -3) {
                    AdDownloadCompletedEventHandlerImpl.onDownloadFinish(downloadInfo, nativeModelByInfo);
                    return;
                }
                if (i == 2001) {
                    AdDownloadCompletedEventHandlerImpl.getInstance().onAppJumpInstall(downloadInfo, nativeModelByInfo, 2001);
                    return;
                } else {
                    if (i == 11) {
                        AdDownloadCompletedEventHandlerImpl.getInstance().onAppJumpInstall(downloadInfo, nativeModelByInfo, 2000);
                        if (nativeModelByInfo.isHandleNoEnoughSpaceInstall()) {
                            return;
                        }
                        showCleanDialogAfterInstallFailedByNoEnoughSpace(downloadInfo, nativeModelByInfo);
                        return;
                    }
                    return;
                }
            }
            BaseException baseException2 = null;
            if (baseException != null) {
                if (DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.TOAST_WITHOUT_NETWORK, 0) == 1 && baseException.getErrorCode() == 1049) {
                    this.mMainHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.BaseDownloadMonitorListener.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GlobalInfo.getDownloadUIFactory().showToastWithDuration(5, GlobalInfo.getContext(), null, "无网络，请检查网络设置", null, 0);
                        }
                    });
                }
                if (DownloadUtils.isInsufficientSpaceError(baseException)) {
                    if (GlobalInfo.getCleanManager() != null) {
                        GlobalInfo.getCleanManager().setModelId(nativeModelByInfo.getId());
                    }
                    AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.DOWNLOAD_FAILED_FOR_SPACE, nativeModelByInfo);
                    if (!nativeModelByInfo.isHandleNoEnoughSpaceDownload()) {
                        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.DOWNLOAD_CAN_RESTART, nativeModelByInfo);
                        tryClearStorageSpace(downloadInfo);
                    }
                    if ((GlobalInfo.getCleanManager() == null || !GlobalInfo.getCleanManager().enableShowDownloadCleanDialog()) && (downloadModel = ModelManager.getInstance().getDownloadModel(nativeModelByInfo.getId())) != null && downloadModel.isShowToast()) {
                        final DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadInfo.getId());
                        if (downloadSettingObtain.optInt(DownloadSettingKeys.KEY_SHOW_NO_ENOUGH_SPACE_TOAST, 0) == 1) {
                            this.mMainHandler.post(new Runnable() { // from class: com.ss.android.downloadlib.BaseDownloadMonitorListener.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    GlobalInfo.getDownloadUIFactory().showToastWithDuration(2, GlobalInfo.getContext(), downloadModel, downloadSettingObtain.optString(DownloadSettingKeys.KEY_NO_ENOUGH_SPACE_TOAST_TEXT, "您的存储空间不足，请清理后再试"), null, 0);
                                }
                            });
                        }
                    }
                }
                baseException2 = new BaseException(baseException.getErrorCode(), ToolUtils.getFixLengthString(baseException.getMessage(), GlobalInfo.getDownloadSettings().optInt("exception_msg_length", 500)));
            }
            AdEventHandler.getInstance().sendDownloadFailedEvent(downloadInfo, baseException2);
            DownloadDispatcher.getInstance().notifyDownloadFailed(downloadInfo, baseException, "");
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "onAppDownloadMonitorSend");
        }
    }

    private void showCleanDialogAfterInstallFailedByNoEnoughSpace(final DownloadInfo downloadInfo, final NativeDownloadModel nativeDownloadModel) {
        final long availableSpaceBytes = ToolUtils.getAvailableSpaceBytes(Environment.getDataDirectory(), -1L);
        long jMin = Math.min(524288000L, ToolUtils.getTotalSpaceBytes(Environment.getDataDirectory()) / 10);
        final long totalBytes = downloadInfo.getTotalBytes();
        final double d = (totalBytes * 2.5d) + jMin;
        if (availableSpaceBytes > -1 && totalBytes > -1) {
            double d2 = availableSpaceBytes;
            if (d2 < d && d - d2 > CleanSpaceHelper.getLastScanSpaceSize()) {
                CleanSpaceHelper.startScan(downloadInfo.getId());
            }
        }
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.downloadlib.BaseDownloadMonitorListener.3
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                if (ToolUtils.isInstalledApp(nativeDownloadModel)) {
                    AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                    return;
                }
                if (availableSpaceBytes <= -1 || totalBytes <= -1 || availableSpaceBytes >= d) {
                    return;
                }
                AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.CLEAN_SPACE_INSTALL, CleanSpaceHelper.getInstallExtJson("install_no_enough_space"), nativeDownloadModel);
                if (CleanSpaceHelper.tryShowInstallFailCleanDialog(downloadInfo, ((long) d) - availableSpaceBytes)) {
                    AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                    nativeDownloadModel.setHandleNoEnoughSpaceInstall(true);
                }
            }
        });
    }

    private void tryClearStorageSpace(@NonNull DownloadInfo downloadInfo) {
        if (DownloadSettingUtils.cleanSpaceEnable(downloadInfo.getId())) {
            DownloadComponentManager.getInstance().submitIOTask(new CleanSpaceTask(downloadInfo));
        }
    }
}
