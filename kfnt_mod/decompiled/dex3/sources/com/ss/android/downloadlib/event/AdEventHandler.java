package com.ss.android.downloadlib.event;

import android.os.Build;
import androidx.annotation.NonNull;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.DownloadEventModel;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadad.api.model.InnerUnifyData;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.AdDownloadCompletedEventHandlerImpl;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelBox;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.addownload.model.SharedPrefsManager;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.EventAppendUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.appdownloader.notification.NotificationPermissionHelper;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdEventHandler {

    public @interface EventType {
        public static final int CLICK_CONTINUE = 4;
        public static final int CLICK_INSTALL = 5;
        public static final int CLICK_PAUSE = 3;
        public static final int CLICK_START = 2;
        public static final int STORAGE_DENY = 1;
    }

    private static class SingleTonHolder {
        private static AdEventHandler INSTANCE = new AdEventHandler();

        private SingleTonHolder() {
        }
    }

    public static AdEventHandler getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private AdEventHandler() {
    }

    public void sendClickEvent(long j, int i) {
        ModelBox modelBox = ModelManager.getInstance().getModelBox(j);
        if (modelBox.notValid()) {
            TTDownloaderMonitor.inst().monitorDataError("sendClickEvent ModelBox notValid");
            return;
        }
        if (modelBox.event.isEnableClickEvent()) {
            int i2 = 1;
            String clickItemTag = i == 1 ? modelBox.event.getClickItemTag() : modelBox.event.getClickButtonTag();
            String notEmptyStr = ToolUtils.getNotEmptyStr(modelBox.event.getClickLabel(), "click");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt(EventConstants.ExtraJson.CLICK_TYPE, Integer.valueOf(i));
                jSONObject.putOpt(EventConstants.ExtraJson.PERMISSION_NOTIFICATION, Integer.valueOf(NotificationPermissionHelper.isNotificationEnabled() ? 1 : 2));
                if (!DownloadUtils.isNetworkConnected(GlobalInfo.getContext())) {
                    i2 = 2;
                }
                jSONObject.putOpt(EventConstants.ExtraJson.NETWORK_AVAILABLE, Integer.valueOf(i2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sendEvent(clickItemTag, notEmptyStr, jSONObject, modelBox);
            if (!"click".equals(notEmptyStr) || modelBox.model == null) {
                return;
            }
            ClickEventHelper.getInstance().recordClick(j, modelBox.model.getLogExtra());
        }
    }

    public void sendEvent(long j, @EventType int i, DownloadInfo downloadInfo) throws JSONException {
        ModelBox modelBox = ModelManager.getInstance().getModelBox(j);
        if (modelBox.notValid()) {
            TTDownloaderMonitor.inst().monitorDataError("sendEvent ModelBox notValid");
            return;
        }
        String notEmptyStr = null;
        JSONObject jSONObject = new JSONObject();
        ToolUtils.safePut(jSONObject, "download_scene", Integer.valueOf(modelBox.getDownloadScene()));
        switch (i) {
            case 1:
                notEmptyStr = ToolUtils.getNotEmptyStr(modelBox.event.getStorageDenyLabel(), EventConstants.Label.STORAGE_DENY);
                break;
            case 2:
                notEmptyStr = ToolUtils.getNotEmptyStr(modelBox.event.getClickStartLabel(), "click_start");
                EventAppendUtils.appendClickStartInfo(downloadInfo, jSONObject);
                break;
            case 3:
                notEmptyStr = ToolUtils.getNotEmptyStr(modelBox.event.getClickPauseLabel(), "click_pause");
                EventAppendUtils.appendClickPauseInfo(downloadInfo, jSONObject);
                break;
            case 4:
                notEmptyStr = ToolUtils.getNotEmptyStr(modelBox.event.getClickContinueLabel(), "click_continue");
                EventAppendUtils.appendInfoJson(downloadInfo, jSONObject);
                break;
            case 5:
                if (downloadInfo != null) {
                    try {
                        EventAppendUtils.appendAhExtraInfo(jSONObject, downloadInfo.getId());
                        AdDownloadCompletedEventHandlerImpl.appendSpaceExtraInfo(jSONObject, downloadInfo);
                    } catch (Throwable unused) {
                    }
                }
                notEmptyStr = ToolUtils.getNotEmptyStr(modelBox.event.getClickInstallLabel(), "click_install");
                break;
        }
        onEvent(null, notEmptyStr, jSONObject, 0L, 1, modelBox);
    }

    public void sendEvent(long j, @EventType int i) throws JSONException {
        sendEvent(j, i, (DownloadInfo) null);
    }

    public void sendRecommendEvent(String str, int i, ModelBox modelBox) throws JSONException {
        onEvent(null, str, null, i, 0, modelBox);
    }

    public void sendQuickAppEvent(long j, boolean z, int i) {
        ModelBox modelBox = ModelManager.getInstance().getModelBox(j);
        if (modelBox.notValid()) {
            TTDownloaderMonitor.inst().monitorDataError("sendQuickAppEvent ModelBox notValid");
            return;
        }
        if (modelBox.model.getQuickAppModel() == null) {
            return;
        }
        if (modelBox.model instanceof AdDownloadModel) {
            ((AdDownloadModel) modelBox.model).setFunnelType(3);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.CLICK_TYPE, Integer.valueOf(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendEvent(z ? EventConstants.Label.DEEPLINK_QUICKAPP_SUCCESS : EventConstants.Label.DEEPLINK_QUICKAPP_FAILED, jSONObject, modelBox);
    }

    public void sendDownloadFailedEvent(long j, BaseException baseException) {
        ModelBox modelBox = ModelManager.getInstance().getModelBox(j);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("download_time", 0);
            if (baseException != null) {
                jSONObject.putOpt(EventConstants.ExtraJson.FAIL_STATUS, Integer.valueOf(baseException.getErrorCode()));
                jSONObject.putOpt(EventConstants.ExtraJson.FAIL_MSG, baseException.getErrorMessage());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendEvent("download_failed", jSONObject, modelBox);
    }

    public void sendDownloadExceptionEvent(long j, BaseException baseException) throws JSONException {
        sendDownloadExceptionEvent(j, baseException, null);
    }

    public void sendDownloadExceptionEvent(long j, BaseException baseException, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (baseException != null) {
            try {
                jSONObject.putOpt("error_code", Integer.valueOf(baseException.getErrorCode()));
                jSONObject.putOpt(EventConstants.ExtraJson.KEY_MESSAGE, baseException.getErrorMessage());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        sendUnityEvent(EventConstants.UnityLabel.DOWNLOAD_SDK_ERROR, jSONObject, j);
    }

    public void sendDownloadResumeEvent(DownloadInfo downloadInfo) {
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        if (nativeModelByInfo == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            EventAppendUtils.appendInfoJson(downloadInfo, jSONObject);
            nativeModelByInfo.setRecentDownloadResumeTime(System.currentTimeMillis());
            sendEvent(nativeModelByInfo.getEventTag(), EventConstants.Label.DOWNLOAD_RESUME, jSONObject, nativeModelByInfo);
            SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeModelByInfo);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void sendInstallFinishEvent(JSONObject jSONObject, @NonNull NativeDownloadModel nativeDownloadModel) throws JSONException {
        sendEvent(nativeDownloadModel.getEventTag(), "install_finish", jSONObject, nativeDownloadModel);
    }

    public void sendDownloadCancelEvent(DownloadInfo downloadInfo, BaseException baseException) throws JSONException {
        NativeDownloadModel nativeModelByInfo;
        if (downloadInfo == null || (nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo)) == null || nativeModelByInfo.hasSendInstallFinish.get()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            AdDownloadCompletedEventHandlerImpl.monitorDownloadInfo(jSONObject, downloadInfo);
            jSONObject.putOpt(EventConstants.ExtraJson.FAIL_STATUS, Integer.valueOf(nativeModelByInfo.getLastFailedErrCode()));
            jSONObject.putOpt(EventConstants.ExtraJson.FAIL_MSG, nativeModelByInfo.getLastFailedErrMsg());
            jSONObject.put(EventConstants.ExtraJson.KEY_DOWNLOAD_FAILED_TIMES, nativeModelByInfo.getDownloadFailedTimes());
            if (downloadInfo.getTotalBytes() > 0) {
                jSONObject.put("download_percent", downloadInfo.getCurBytes() / downloadInfo.getTotalBytes());
            }
            jSONObject.put("download_status", downloadInfo.getRealStatus());
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (nativeModelByInfo.getTimeStamp() > 0) {
                jSONObject.put(EventConstants.ExtraJson.KEY_TIME_FROM_START_DOWNLOAD, jCurrentTimeMillis - nativeModelByInfo.getTimeStamp());
            }
            if (nativeModelByInfo.getRecentDownloadResumeTime() > 0) {
                jSONObject.put(EventConstants.ExtraJson.KEY_TIME_FROM_DOWNLOAD_RESUME, jCurrentTimeMillis - nativeModelByInfo.getRecentDownloadResumeTime());
            }
            jSONObject.put(EventConstants.ExtraJson.KEY_IS_UPDATE_DOWNLOAD, nativeModelByInfo.isUpdateDownload() ? 1 : 2);
            jSONObject.put(EventConstants.ExtraJson.KEY_CAN_SHOW_NOTIFICATION, NotificationPermissionHelper.isNotificationEnabled() ? 1 : 2);
            jSONObject.put(EventConstants.ExtraJson.KEY_HAS_SEND_DOWNLOAD_FAILED_FINALLY, nativeModelByInfo.hasSendDownloadFailedFinally.get() ? 1 : 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendEvent(nativeModelByInfo.getEventTag(), EventConstants.Label.DOWNLOAD_CANCEL, jSONObject, nativeModelByInfo);
    }

    public void sendDownloadFailedEvent(DownloadInfo downloadInfo, BaseException baseException) throws JSONException {
        if (downloadInfo == null) {
            return;
        }
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        if (nativeModelByInfo == null) {
            TTDownloaderMonitor.inst().monitorDataError("sendDownloadFailedEvent nativeModel null");
            return;
        }
        if (nativeModelByInfo.hasSendInstallFinish.get()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            EventAppendUtils.appendInfoJson(downloadInfo, jSONObject);
            AdDownloadCompletedEventHandlerImpl.monitorDownloadInfo(jSONObject, downloadInfo);
            if (baseException != null) {
                jSONObject.putOpt(EventConstants.ExtraJson.FAIL_STATUS, Integer.valueOf(baseException.getErrorCode()));
                jSONObject.putOpt(EventConstants.ExtraJson.FAIL_MSG, baseException.getErrorMessage());
                nativeModelByInfo.setLastFailedErrCode(baseException.getErrorCode());
                nativeModelByInfo.setLastFailedErrMsg(baseException.getErrorMessage());
            }
            nativeModelByInfo.increaseDownloadFailedTimes();
            jSONObject.put(EventConstants.ExtraJson.KEY_DOWNLOAD_FAILED_TIMES, nativeModelByInfo.getDownloadFailedTimes());
            if (downloadInfo.getTotalBytes() > 0) {
                jSONObject.put("download_percent", downloadInfo.getCurBytes() / downloadInfo.getTotalBytes());
            }
            jSONObject.put(EventConstants.ExtraJson.KEY_HAS_SEND_DOWNLOAD_FAILED_FINALLY, nativeModelByInfo.hasSendDownloadFailedFinally.get() ? 1 : 2);
            EventAppendUtils.appendPatchApplyArgJson(nativeModelByInfo, jSONObject);
            jSONObject.put(EventConstants.ExtraJson.KEY_IS_UPDATE_DOWNLOAD, nativeModelByInfo.isUpdateDownload() ? 1 : 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sendEvent(nativeModelByInfo.getEventTag(), "download_failed", jSONObject, nativeModelByInfo);
        SharedPrefsManager.getInstance().saveNativeDownloadModel(nativeModelByInfo);
    }

    public void sendCleanEvent(String str, long j) {
        sendCleanEvent(str, j, null);
    }

    public void sendCleanEvent(String str, long j, JSONObject jSONObject) {
        NativeDownloadModel next;
        NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(j);
        if (j <= 0 || nativeDownloadModel == null) {
            Iterator<NativeDownloadModel> it = ModelManager.getInstance().getAllNativeModels().values().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next != null) {
                    break;
                }
            }
            next = nativeDownloadModel;
        } else {
            next = nativeDownloadModel;
        }
        if (next != null) {
            sendUnityEvent(str, jSONObject, next);
        }
    }

    public void sendUnityEvent(String str, long j) throws JSONException {
        sendUnityEvent(str, (JSONObject) null, j);
    }

    public void sendUnityEvent(String str, InnerUnifyData innerUnifyData) {
        sendUnityEvent(str, (JSONObject) null, innerUnifyData);
    }

    public void sendUnityEvent(String str, JSONObject jSONObject, long j) throws JSONException {
        InnerUnifyData nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(j);
        if (nativeDownloadModel != null) {
            sendUnityEvent(str, jSONObject, nativeDownloadModel);
            return;
        }
        ModelBox modelBox = ModelManager.getInstance().getModelBox(j);
        if (modelBox.notValid()) {
            TTDownloaderMonitor.inst().monitorDataError("sendUnityEvent ModelBox notValid");
        } else {
            sendUnityEvent(str, jSONObject, modelBox);
        }
    }

    public void sendUnityEvent(String str, JSONObject jSONObject, InnerUnifyData innerUnifyData) {
        JSONObject jSONObject2 = new JSONObject();
        ToolUtils.safePut(jSONObject2, EventConstants.ExtraJson.UNITY_LABEL, str);
        sendEvent(EventConstants.Tag.EMBEDED_AD, EventConstants.Label.UNITY, ToolUtils.copyJson(jSONObject, jSONObject2), innerUnifyData);
    }

    public void sendEvent(String str, @NonNull DownloadModel downloadModel, @NonNull DownloadEventConfig downloadEventConfig, @NonNull DownloadController downloadController) {
        sendEvent(str, new ModelBox(downloadModel.getId(), downloadModel, downloadEventConfig, downloadController));
    }

    public void sendEvent(String str, String str2, JSONObject jSONObject, @NonNull DownloadModel downloadModel, @NonNull DownloadEventConfig downloadEventConfig, @NonNull DownloadController downloadController) throws JSONException {
        sendEvent(str, str2, jSONObject, new ModelBox(downloadModel.getId(), downloadModel, downloadEventConfig, downloadController));
    }

    public void sendEvent(String str, long j) {
        NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(j);
        if (nativeDownloadModel != null) {
            sendEvent(str, nativeDownloadModel);
        } else {
            sendEvent(str, ModelManager.getInstance().getModelBox(j));
        }
    }

    public void sendEvent(String str, InnerUnifyData innerUnifyData) {
        sendEvent((String) null, str, innerUnifyData);
    }

    public void sendEvent(String str, JSONObject jSONObject, InnerUnifyData innerUnifyData) {
        sendEvent((String) null, str, jSONObject, innerUnifyData);
    }

    public void sendEvent(String str, String str2, InnerUnifyData innerUnifyData) throws JSONException {
        sendEvent(str, str2, (JSONObject) null, innerUnifyData);
    }

    public void sendEvent(String str, String str2, JSONObject jSONObject, InnerUnifyData innerUnifyData) throws JSONException {
        onEvent(str, str2, jSONObject, 0L, 0, innerUnifyData);
    }

    private void onEvent(String str, String str2, JSONObject jSONObject, long j, int i, InnerUnifyData innerUnifyData) throws JSONException {
        if (innerUnifyData == null) {
            TTDownloaderMonitor.inst().monitorDataError("onEvent data null");
            return;
        }
        if ((innerUnifyData instanceof ModelBox) && ((ModelBox) innerUnifyData).notValid()) {
            TTDownloaderMonitor.inst().monitorDataError("onEvent ModelBox notValid");
            return;
        }
        try {
            DownloadEventModel.Builder logExtra = new DownloadEventModel.Builder().setTag(ToolUtils.getNotEmptyStr(str, innerUnifyData.getEventTag(), EventConstants.Tag.EMBEDED_AD)).setLabel(str2).setIsAd(innerUnifyData.isAd()).setAdId(innerUnifyData.getId()).setLogExtra(innerUnifyData.getLogExtra());
            if (j <= 0) {
                j = innerUnifyData.getExtValue();
            }
            DownloadEventModel.Builder extraObject = logExtra.setExtValue(j).setRefer(innerUnifyData.getEventRefer()).setClickTrackUrl(innerUnifyData.getClickTrackUrl()).setExtJson(ToolUtils.mergeJson(getBaseJson(innerUnifyData), jSONObject)).setParamsJson(innerUnifyData.getParamsJson()).setExtraObject(innerUnifyData.getExtraEventObject());
            if (i <= 0) {
                i = 2;
            }
            onEvent(extraObject.setEventSource(i).setIsV3(innerUnifyData.isV3Event()).build());
        } catch (Exception e) {
            TTDownloaderMonitor.inst().monitorException(e, "onEvent");
        }
    }

    private JSONObject getBaseJson(InnerUnifyData innerUnifyData) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            ToolUtils.copyJson(innerUnifyData.getExtra(), jSONObject);
            ToolUtils.copyJson(innerUnifyData.getEventExtra(), jSONObject);
            jSONObject.putOpt("download_url", innerUnifyData.getDownloadUrl());
            jSONObject.putOpt("package_name", innerUnifyData.getPackageName());
            jSONObject.putOpt(EventConstants.ExtraJson.ANDROID_INT, Integer.valueOf(Build.VERSION.SDK_INT));
            jSONObject.putOpt(EventConstants.ExtraJson.ROM_NAME, RomUtils.getName());
            jSONObject.putOpt(EventConstants.ExtraJson.ROM_VERSION, RomUtils.getVersion());
            jSONObject.putOpt(EventConstants.ExtraJson.TTDOWNLOADER, 1);
            jSONObject.putOpt(EventConstants.ExtraJson.FUNNEL_TYPE, Integer.valueOf(innerUnifyData.getFunnelType()));
            if (innerUnifyData.getFunnelType() == 2) {
                EventAppendUtils.appendMarketParams(jSONObject, innerUnifyData);
            }
        } catch (Exception e) {
            GlobalInfo.getTTMonitor().monitorException(e, "getBaseJson");
        }
        return jSONObject;
    }

    private void onEvent(DownloadEventModel downloadEventModel) {
        if (GlobalInfo.getDownloadEventLogger() == null) {
            return;
        }
        if (downloadEventModel.isV3()) {
            GlobalInfo.getDownloadEventLogger().onV3Event(downloadEventModel);
        } else {
            GlobalInfo.getDownloadEventLogger().onEvent(downloadEventModel);
        }
    }
}
