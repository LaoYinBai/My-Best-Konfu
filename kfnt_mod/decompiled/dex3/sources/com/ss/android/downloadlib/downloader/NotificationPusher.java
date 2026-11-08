package com.ss.android.downloadlib.downloader;

import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.EventAppendUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.notification.AppNotificationItem;
import com.ss.android.socialbase.appdownloader.notification.NotificationIconCache;
import com.ss.android.socialbase.appdownloader.notification.NotificationPermissionHelper;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotificationPusher {

    public interface ERROR_CODE {
        public static final int ACTIVATED = 1003;
        public static final int INFO_NULL = 1005;
        public static final int INSTALLED = 1002;
        public static final int NOT_PAUSED = 1001;
        public static final int NO_PERMISSION = 1004;
    }

    public interface NOTIFY_TYPE {
        public static final int CONTINUE = 1;
        public static final int INSTALL = 2;
        public static final int OPEN_APP = 3;
    }

    private static class SingleTonHolder {
        private static NotificationPusher INSTANCE = new NotificationPusher();

        private SingleTonHolder() {
        }
    }

    public static NotificationPusher getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private NotificationPusher() {
    }

    public void notifyContinueDownload(NativeDownloadModel nativeDownloadModel) {
        innerNotifyContinueDownload(nativeDownloadModel, 5L);
    }

    public void delayNotifyContinueDownload(NativeDownloadModel nativeDownloadModel) {
        if (nativeDownloadModel == null) {
            return;
        }
        innerNotifyContinueDownload(nativeDownloadModel, DownloadSetting.obtain(nativeDownloadModel.getDownloadId()).optInt(DownloadSettingKeys.NOTI_CONTINUE_DELAY_SECS, 5));
    }

    private void innerNotifyContinueDownload(@NonNull final NativeDownloadModel nativeDownloadModel, long j) {
        final int downloadId = nativeDownloadModel.getDownloadId();
        if (DownloadSetting.obtain(downloadId).optInt(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.NOTIFICATION_OPT_2) != 1) {
            return;
        }
        makeSureIcon(downloadId);
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.downloader.NotificationPusher.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(downloadId);
                JSONObject jSONObject = new JSONObject();
                ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.KEY_TYPE, 1);
                EventAppendUtils.appendInfoJson(downloadInfo, jSONObject);
                if (downloadInfo != null && -2 == downloadInfo.getRealStatus() && !downloadInfo.isPauseReserveOnWifi()) {
                    NotificationPusher.this.addNotification(downloadId, nativeDownloadModel, jSONObject);
                } else {
                    ToolUtils.safePut(jSONObject, "error_code", 1001);
                }
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.NOTIFICATION_TRY_SHOW, jSONObject, nativeDownloadModel);
            }
        }, j * 1000);
    }

    public void notifyInstallApp(@NonNull NativeDownloadModel nativeDownloadModel) {
        innerNotifyInstallApp(nativeDownloadModel, 5L);
    }

    public void delayNotifyInstallApp(@NonNull NativeDownloadModel nativeDownloadModel) {
        innerNotifyInstallApp(nativeDownloadModel, DownloadSetting.obtain(nativeDownloadModel.getDownloadId()).optInt(DownloadSettingKeys.NOTI_INSTALL_DELAY_SECS, 5));
    }

    private void innerNotifyInstallApp(@NonNull final NativeDownloadModel nativeDownloadModel, long j) {
        final int downloadId = nativeDownloadModel.getDownloadId();
        if (DownloadSetting.obtain(downloadId).optInt(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.NOTIFICATION_OPT_2) != 1) {
            return;
        }
        makeSureIcon(downloadId);
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.downloader.NotificationPusher.2
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(downloadId);
                JSONObject jSONObject = new JSONObject();
                ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.KEY_TYPE, 2);
                EventAppendUtils.appendInfoJson(downloadInfo, jSONObject);
                if (!ToolUtils.isInstalledApp(nativeDownloadModel)) {
                    NotificationPusher.this.addNotification(downloadId, nativeDownloadModel, jSONObject);
                } else {
                    ToolUtils.safePut(jSONObject, "error_code", 1002);
                }
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.NOTIFICATION_TRY_SHOW, jSONObject, nativeDownloadModel);
            }
        }, j * 1000);
    }

    public void notifyOpenApp(@NonNull NativeDownloadModel nativeDownloadModel) {
        innerNotifyOpenApp(nativeDownloadModel, 5L);
    }

    public void delayNotifyOpenApp(@NonNull NativeDownloadModel nativeDownloadModel) {
        innerNotifyOpenApp(nativeDownloadModel, DownloadSetting.obtain(nativeDownloadModel.getDownloadId()).optInt(DownloadSettingKeys.NOTI_OPEN_DELAY_SECS, 5));
    }

    public void innerNotifyOpenApp(@NonNull final NativeDownloadModel nativeDownloadModel, long j) {
        final int downloadId = nativeDownloadModel.getDownloadId();
        if (DownloadSetting.obtain(downloadId).optInt(com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys.NOTIFICATION_OPT_2) != 1) {
            return;
        }
        makeSureIcon(downloadId);
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.downloader.NotificationPusher.3
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(downloadId);
                JSONObject jSONObject = new JSONObject();
                ToolUtils.safePut(jSONObject, EventConstants.ExtraJson.KEY_TYPE, 3);
                EventAppendUtils.appendInfoJson(downloadInfo, jSONObject);
                if (!ToolUtils.isAppActivatedByPKG(nativeDownloadModel.getPackageName())) {
                    NotificationPusher.this.addNotification(downloadId, nativeDownloadModel, jSONObject);
                } else {
                    ToolUtils.safePut(jSONObject, "error_code", 1003);
                }
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.NOTIFICATION_TRY_SHOW, jSONObject, nativeDownloadModel);
            }
        }, j * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addNotification(int i, NativeDownloadModel nativeDownloadModel, JSONObject jSONObject) throws JSONException {
        if (!NotificationPermissionHelper.isNotificationEnabled()) {
            ToolUtils.safePut(jSONObject, "error_code", 1004);
            return;
        }
        DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(i);
        if (downloadInfo == null) {
            ToolUtils.safePut(jSONObject, "error_code", 1005);
            return;
        }
        if (DownloadNotificationManager.getInstance().getNotificationItem(i) != null) {
            DownloadNotificationManager.getInstance().cancelNotification(i);
        }
        AppNotificationItem appNotificationItem = new AppNotificationItem(GlobalInfo.getContext(), i, downloadInfo.getTitle(), downloadInfo.getSavePath(), downloadInfo.getName(), downloadInfo.getExtra());
        appNotificationItem.setCurBytes(downloadInfo.getCurBytes());
        appNotificationItem.setTotalBytes(downloadInfo.getTotalBytes());
        appNotificationItem.refreshStatus(downloadInfo.getStatus(), null, false, false);
        DownloadNotificationManager.getInstance().addNotification(appNotificationItem);
        appNotificationItem.updateNotification(null, false);
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.NOTIFICATION_SHOW, jSONObject, nativeDownloadModel);
    }

    public void makeSureIcon(int i) {
        DownloadInfo downloadInfo;
        if (NotificationIconCache.inst().getAppIconByInfoId(i) != null || (downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(i)) == null) {
            return;
        }
        NotificationIconCache.inst().parseAndCacheIcon(i, downloadInfo.getIconUrl());
    }
}
