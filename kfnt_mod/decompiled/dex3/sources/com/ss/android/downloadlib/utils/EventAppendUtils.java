package com.ss.android.downloadlib.utils;

import androidx.annotation.NonNull;
import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.SharedPrefsManager;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.socialbase.appdownloader.AhUtils;
import com.ss.android.socialbase.appdownloader.AppUtils;
import com.ss.android.socialbase.appdownloader.notification.NotificationPermissionHelper;
import com.ss.android.socialbase.appdownloader.util.AnUtils;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class EventAppendUtils {
    public static void appendClickStartInfo(DownloadInfo downloadInfo, JSONObject jSONObject) {
        try {
            appendInfoJson(downloadInfo, jSONObject);
            NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
            if (nativeModelByInfo == null) {
                return;
            }
            jSONObject.put(EventConstants.ExtraJson.KEY_IS_UPDATE_DOWNLOAD, nativeModelByInfo.isUpdateDownload() ? 1 : 2);
            appendPatchApplyArgJson(nativeModelByInfo, jSONObject);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void appendClickPauseInfo(DownloadInfo downloadInfo, JSONObject jSONObject) {
        NativeDownloadModel nativeModelByInfo;
        if (jSONObject == null || (nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo)) == null) {
            return;
        }
        try {
            appendInfoJson(downloadInfo, jSONObject);
            jSONObject.putOpt(EventConstants.ExtraJson.TIME_AFTER_CLICK, Long.valueOf(System.currentTimeMillis() - nativeModelByInfo.getClickDownloadTime()));
            jSONObject.putOpt(EventConstants.ExtraJson.CLICK_DOWNLOAD_SIZE, Long.valueOf(nativeModelByInfo.getClickDownloadSize()));
            jSONObject.putOpt(EventConstants.ExtraJson.DOWNLOAD_LENGTH, Long.valueOf(downloadInfo.getCurBytes()));
            jSONObject.putOpt(EventConstants.ExtraJson.DOWNLOAD_APK_SIZE, Long.valueOf(downloadInfo.getTotalBytes()));
            nativeModelByInfo.increaseClickPauseTimes();
            SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeModelByInfo);
            jSONObject.put(EventConstants.ExtraJson.KEY_CLICK_PAUSE_TIMES, nativeModelByInfo.getClickPauseTimes());
            long totalBytes = downloadInfo.getTotalBytes();
            long curBytes = downloadInfo.getCurBytes();
            jSONObject.put("download_percent", (curBytes < 0 || totalBytes <= 0) ? 0.0d : curBytes / totalBytes);
            jSONObject.put("download_status", downloadInfo.getRealStatus());
            long jCurrentTimeMillis = System.currentTimeMillis();
            long timeStamp = nativeModelByInfo.getTimeStamp();
            if (timeStamp > 0) {
                jSONObject.put(EventConstants.ExtraJson.KEY_TIME_FROM_START_DOWNLOAD, jCurrentTimeMillis - timeStamp);
            }
            long recentDownloadResumeTime = nativeModelByInfo.getRecentDownloadResumeTime();
            if (recentDownloadResumeTime > 0) {
                jSONObject.put(EventConstants.ExtraJson.KEY_TIME_FROM_DOWNLOAD_RESUME, jCurrentTimeMillis - recentDownloadResumeTime);
            }
            jSONObject.putOpt(EventConstants.ExtraJson.FAIL_STATUS, Integer.valueOf(nativeModelByInfo.getLastFailedErrCode()));
            jSONObject.putOpt(EventConstants.ExtraJson.FAIL_MSG, nativeModelByInfo.getLastFailedErrMsg());
            jSONObject.put(EventConstants.ExtraJson.KEY_DOWNLOAD_FAILED_TIMES, nativeModelByInfo.getDownloadFailedTimes());
            jSONObject.put(EventConstants.ExtraJson.KEY_CAN_SHOW_NOTIFICATION, NotificationPermissionHelper.isNotificationEnabled() ? 1 : 2);
            jSONObject.put(EventConstants.ExtraJson.KEY_FIRST_SPEED_TIME, downloadInfo.getFirstSpeedTime());
            jSONObject.put(EventConstants.ExtraJson.KEY_ALL_CONNECT_TIME, downloadInfo.getAllConnectTime());
            jSONObject.put(EventConstants.ExtraJson.KEY_DOWNLOAD_PREPARE_TIME, downloadInfo.getDownloadPrepareTime());
            jSONObject.put("download_time", downloadInfo.getRealDownloadTime() + downloadInfo.getAllConnectTime() + downloadInfo.getDownloadPrepareTime());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void appendInfoJson(DownloadInfo downloadInfo, JSONObject jSONObject) {
        if (downloadInfo != null) {
            try {
                jSONObject.putOpt("total_bytes", Long.valueOf(downloadInfo.getTotalBytes()));
                jSONObject.putOpt("cur_bytes", Long.valueOf(downloadInfo.getCurBytes()));
                jSONObject.putOpt("chunk_count", Integer.valueOf(downloadInfo.getChunkCount()));
                jSONObject.putOpt("app_name", downloadInfo.getTitle());
                jSONObject.putOpt("network_quality", downloadInfo.getNetworkQuality());
                jSONObject.putOpt("save_path", downloadInfo.getSavePath());
                jSONObject.putOpt("file_name", downloadInfo.getName());
                jSONObject.putOpt("download_status", Integer.valueOf(downloadInfo.getRealStatus()));
                NativeDownloadModel nativeModelByInfoId = ModelManager.getInstance().getNativeModelByInfoId(downloadInfo.getId());
                if (nativeModelByInfoId != null) {
                    jSONObject.putOpt(EventConstants.ExtraJson.CLICK_DOWNLOAD_TIME, Long.valueOf(nativeModelByInfoId.getClickDownloadTime()));
                    jSONObject.putOpt(EventConstants.ExtraJson.CLICK_DOWNLOAD_SIZE, Long.valueOf(nativeModelByInfoId.getClickDownloadSize()));
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        jSONObject.putOpt(EventConstants.ExtraJson.PERMISSION_NOTIFICATION, Integer.valueOf(NotificationPermissionHelper.isNotificationEnabled() ? 1 : 2));
        jSONObject.putOpt(EventConstants.ExtraJson.NETWORK_AVAILABLE, Integer.valueOf(DownloadUtils.isNetworkConnected(GlobalInfo.getContext()) ? 1 : 2));
        jSONObject.putOpt(EventConstants.ExtraJson.NETWORK_IS_WIFI, Integer.valueOf(DownloadUtils.isWifi(GlobalInfo.getContext()) ? 1 : 2));
    }

    public static void appendPatchApplyArgJson(NativeDownloadModel nativeDownloadModel, JSONObject jSONObject) {
        if (jSONObject == null || nativeDownloadModel == null) {
            return;
        }
        try {
            jSONObject.put(EventConstants.ExtraJson.KEY_IS_PATCH_APPLY_HANDLED, nativeDownloadModel.isPatchApplyHandled() ? 1 : 0);
            jSONObject.put(EventConstants.ExtraJson.KEY_ORIGIN_MIME_TYPE, nativeDownloadModel.getMimeType());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void appendAhExtraInfo(JSONObject jSONObject, int i) {
        if (jSONObject == null) {
            return;
        }
        JSONArray jSONArrayOptJSONArray = DownloadSetting.obtain(i).optJSONArray(DownloadSettingKeys.KEY_AH_REPORT_CONFIG);
        if (jSONArrayOptJSONArray != null) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                try {
                    String string = jSONArrayOptJSONArray.getString(i2);
                    AppUtils.AppInfo appInfoQueryPackageInfo = AnUtils.queryPackageInfo(string);
                    if (appInfoQueryPackageInfo != null) {
                        jSONObject.put(string.replaceAll("\\.", "_"), appInfoQueryPackageInfo.getVersionCode() + "_" + appInfoQueryPackageInfo.getVersionName());
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        try {
            jSONObject.put(EventConstants.ExtraJson.KEY_IS_UNKNOWN_SOURCE_ENABLED, AhUtils.isUnknownSourceEnabled(DownloadComponentManager.getAppContext()) ? 1 : 2);
        } catch (Throwable unused) {
        }
    }

    public static JSONObject appendAppLinkParams(@NonNull JSONObject jSONObject, InnerUnifyData innerUnifyData) throws JSONException {
        ToolUtils.safePut(jSONObject, "open_url", ToolUtils.getNotEmptyStr(innerUnifyData.getOpenUrl(), "open_url_not_exist"));
        return jSONObject;
    }

    public static JSONObject appendMarketParams(@NonNull JSONObject jSONObject, InnerUnifyData innerUnifyData) {
        ToolUtils.safePut(jSONObject, RomUtils.getMarketPackageName().replaceAll("\\.", "_"), Integer.valueOf(ToolUtils.getVersionCode(GlobalInfo.getContext(), RomUtils.getMarketPackageName())));
        return jSONObject;
    }
}
