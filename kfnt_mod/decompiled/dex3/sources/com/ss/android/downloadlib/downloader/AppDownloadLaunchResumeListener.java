package com.ss.android.downloadlib.downloader;

import androidx.annotation.WorkerThread;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.SharedPrefsManager;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadLaunchResumeListener;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.constants.SpJsonConstants;
import com.ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppDownloadLaunchResumeListener implements IAppDownloadLaunchResumeListener, IDownloadCacheSyncStatusListener {
    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadLaunchResumeListener
    public void onLaunchResume(List<DownloadInfo> list) {
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener
    public void onStart() {
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadLaunchResumeListener
    public void onResumeDownload(DownloadInfo downloadInfo, boolean z) {
        if (downloadInfo == null) {
            return;
        }
        trySendDownloadUnCompleteEvent(downloadInfo, downloadInfo.getRealStatus(), z);
    }

    @WorkerThread
    public void trySendDownloadUnCompleteEvent(DownloadInfo downloadInfo, int i, boolean z) {
        ModelManager.getInstance().init();
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        if (nativeModelByInfo == null) {
            return;
        }
        try {
            if (z) {
                nativeModelByInfo.setLastFailedResumeCount(downloadInfo.getFailedResumeCount());
            } else if (nativeModelByInfo.getLastFailedResumeCount() == -1) {
                return;
            } else {
                nativeModelByInfo.setLastFailedResumeCount(-1);
            }
            SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeModelByInfo);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("download_id", downloadInfo.getId());
            jSONObject.put("name", downloadInfo.getName());
            jSONObject.put("url", downloadInfo.getUrl());
            jSONObject.put("download_time", downloadInfo.getDownloadTime());
            jSONObject.put("download_status", i);
            jSONObject.put("cur_bytes", downloadInfo.getCurBytes());
            jSONObject.put("total_bytes", downloadInfo.getTotalBytes());
            jSONObject.put(MonitorConstants.EXTRA_DOWNLOAD_ONLY_WIFI, downloadInfo.isOnlyWifi() ? 1 : 0);
            jSONObject.put("chunk_count", downloadInfo.getChunkCount());
            jSONObject.put(EventConstants.ExtraJson.KEY_LAUNCH_RESUMED, z ? 1 : 2);
            jSONObject.put("failed_resume_count", downloadInfo.getFailedResumeCount());
            AdEventHandler.getInstance().sendEvent(EventConstants.Tag.EMBEDED_AD, EventConstants.Label.DOWNLOAD_UNCOMPLETED, jSONObject, nativeModelByInfo);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ss.android.socialbase.downloader.depend.IDownloadCacheSyncStatusListener
    public void onSuccess() {
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.downloader.AppDownloadLaunchResumeListener.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                DownloadInfo downloadInfo;
                int spIntVal;
                ModelManager.getInstance().init();
                for (NativeDownloadModel nativeDownloadModel : ModelManager.getInstance().getAllNativeModels().values()) {
                    int downloadId = nativeDownloadModel.getDownloadId();
                    if (downloadId != 0) {
                        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadId);
                        if (downloadSettingObtain.optInt(DownloadSettingKeys.NOTIFICATION_OPT_2) == 1 && (downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(downloadId)) != null) {
                            if (ToolUtils.isInstalledApp(nativeDownloadModel) && !ToolUtils.isAppActivatedByPKG(nativeDownloadModel.getPackageName())) {
                                int spIntVal2 = downloadInfo.getSpIntVal(SpJsonConstants.RESTART_NOTIFY_OPEN_APP_COUNT);
                                if (spIntVal2 < downloadSettingObtain.optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.NOTI_OPEN_RESTART_TIMES, 1)) {
                                    NotificationPusher.getInstance().notifyOpenApp(nativeDownloadModel);
                                    downloadInfo.setSpValue(SpJsonConstants.RESTART_NOTIFY_OPEN_APP_COUNT, String.valueOf(spIntVal2 + 1));
                                }
                            } else if (downloadInfo.getRealStatus() == -2) {
                                int spIntVal3 = downloadInfo.getSpIntVal(SpJsonConstants.RESTART_NOTIFY_CONTINUE_COUNT);
                                if (spIntVal3 < downloadSettingObtain.optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.NOTI_CONTINUE_RESTART_TIMES, 1)) {
                                    NotificationPusher.getInstance().notifyContinueDownload(nativeDownloadModel);
                                    downloadInfo.setSpValue(SpJsonConstants.RESTART_NOTIFY_CONTINUE_COUNT, String.valueOf(spIntVal3 + 1));
                                }
                            } else if (downloadInfo.getRealStatus() == -3 && DownloadUtils.isFileDownloaded(downloadInfo) && !ToolUtils.isInstalledApp(nativeDownloadModel) && (spIntVal = downloadInfo.getSpIntVal(SpJsonConstants.RESTART_NOTIFY_INSTALL_COUNT)) < downloadSettingObtain.optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.NOTI_INSTALL_RESTART_TIMES, 1)) {
                                NotificationPusher.getInstance().notifyInstallApp(nativeDownloadModel);
                                downloadInfo.setSpValue(SpJsonConstants.RESTART_NOTIFY_INSTALL_COUNT, String.valueOf(spIntVal + 1));
                            }
                        }
                    }
                }
            }
        }, AppLinkMonitor.DELAY_TIME);
    }
}
