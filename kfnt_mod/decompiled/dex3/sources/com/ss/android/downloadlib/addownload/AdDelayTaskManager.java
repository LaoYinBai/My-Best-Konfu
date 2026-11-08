package com.ss.android.downloadlib.addownload;

import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.dialog.AdDownloadDialogManager;
import com.ss.android.downloadlib.addownload.model.AppInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.downloadlib.utils.WeakHandler;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.android.socialbase.appdownloader.AppDownloader;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.appdownloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.android.socialbase.downloader.utils.SystemUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdDelayTaskManager implements WeakHandler.IHandler {
    private static final String TAG = "AdDelayTaskManager";
    private static AdDelayTaskManager sInstance;
    private WeakHandler mHandler = new WeakHandler(Looper.getMainLooper(), this);
    private long mLastSendMsgTimeStamp;

    private @interface Arg {
        public static final int DELAY_INSTALL = 1;
        public static final int MIUI_SILENT_INSTALL_CHECK = 2;
    }

    public static AdDelayTaskManager inst() {
        if (sInstance == null) {
            synchronized (AdDelayTaskManager.class) {
                if (sInstance == null) {
                    sInstance = new AdDelayTaskManager();
                }
            }
        }
        return sInstance;
    }

    private AdDelayTaskManager() {
    }

    public void trySendDelayInstallMsg(@NonNull DownloadInfo downloadInfo, long j, long j2, String str, String str2, String str3, String str4) {
        AppInfo appInfo = new AppInfo(downloadInfo.getId(), j, j2, str, str2, str3, str4);
        if (DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.BACK_MIUI_SILENT_INSTALL, 1) == 0 && ((RomUtils.isMiuiV10() || RomUtils.isMiuiV11()) && SystemUtils.checkServiceExists(GlobalInfo.getContext(), AppDownloadUtils.MIUI_SECURITY_PKG, AppDownloadUtils.MIUI_SECURITY_NAME))) {
            if (DownloadUtils.getBoolean(downloadInfo.getTempCacheData().get(Constants.EXTRA_SILENT_INSTALL_SUCCEED), false)) {
                Message messageObtainMessage = this.mHandler.obtainMessage(200, appInfo);
                messageObtainMessage.arg1 = 2;
                this.mHandler.sendMessageDelayed(messageObtainMessage, r1.optInt(com.ss.android.downloadlib.constants.DownloadSettingKeys.KEY_CHECK_SILENT_INSTALL_INTERVAL, BaseConstants.Time.MINUTE));
                return;
            }
            NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(appInfo.mAdId);
            JSONObject jSONObject = new JSONObject();
            int i = -1;
            try {
                jSONObject.put(EventConstants.ExtraJson.KEY_TYPE, "miui_silent_install");
                jSONObject.put(EventConstants.ExtraJson.KEY_MESSAGE, "miui_silent_install_failed: has not started service");
                i = 5;
            } catch (Exception unused) {
            }
            GlobalInfo.getMonitorListener().onAppDownloadMonitorSend(null, new BaseException(i, jSONObject.toString()), i);
            AdEventHandler.getInstance().sendEvent(EventConstants.Tag.EMBEDED_AD, EventConstants.Label.ANTI_HIJACK_RESULT, jSONObject, nativeDownloadModel);
        }
        if (DownloadSettingUtils.isEnableStartInstallAgain()) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mLastSendMsgTimeStamp;
            long installInterval = DownloadSettingUtils.getInstallInterval();
            if (jCurrentTimeMillis < DownloadSettingUtils.getNextInstallMinInterval()) {
                long nextInstallMinInterval = DownloadSettingUtils.getNextInstallMinInterval() - jCurrentTimeMillis;
                installInterval += nextInstallMinInterval;
                this.mLastSendMsgTimeStamp = System.currentTimeMillis() + nextInstallMinInterval;
            } else {
                this.mLastSendMsgTimeStamp = System.currentTimeMillis();
            }
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(200, appInfo), installInterval);
        }
    }

    private void handleStartInstallMsg(AppInfo appInfo, @Arg int i) throws JSONException {
        if (GlobalInfo.getAppStatusChangeListener() == null || GlobalInfo.getAppStatusChangeListener().isAppInBackground() || appInfo == null) {
            return;
        }
        if (2 == i) {
            NativeDownloadModel nativeDownloadModel = ModelManager.getInstance().getNativeDownloadModel(appInfo.mAdId);
            JSONObject jSONObject = new JSONObject();
            int i2 = -1;
            try {
                jSONObject.put(EventConstants.ExtraJson.KEY_TYPE, "miui_silent_install");
                if (ToolUtils.isInstalledApp(GlobalInfo.getContext(), appInfo.mPackageName)) {
                    jSONObject.put(EventConstants.ExtraJson.KEY_MESSAGE, "miui_silent_install_succeed");
                    i2 = 4;
                } else {
                    jSONObject.put(EventConstants.ExtraJson.KEY_MESSAGE, "miui_silent_install_failed: has started service");
                    i2 = 5;
                }
            } catch (Exception unused) {
            }
            GlobalInfo.getMonitorListener().onAppDownloadMonitorSend(null, new BaseException(i2, jSONObject.toString()), i2);
            AdEventHandler.getInstance().sendEvent(EventConstants.Tag.EMBEDED_AD, EventConstants.Label.ANTI_HIJACK_RESULT, jSONObject, nativeDownloadModel);
        }
        if (ToolUtils.isInstalledApp(GlobalInfo.getContext(), appInfo.mPackageName)) {
            AdEventHandler.getInstance().sendEvent(EventConstants.Label.DELAY_INSTALLED, appInfo.mAdId);
            return;
        }
        if (!ToolUtils.exists(appInfo.mFileName)) {
            AdEventHandler.getInstance().sendEvent(EventConstants.Label.DELAY_INSTALL_LOST, appInfo.mAdId);
        } else if (AdDownloadDialogManager.Instance().isPackageReadyToInstall(appInfo.mPackageName)) {
            AdEventHandler.getInstance().sendEvent(EventConstants.Label.DELAY_INSTALL_CONFLICT, appInfo.mAdId);
        } else {
            AdEventHandler.getInstance().sendEvent(EventConstants.Label.DELAY_INSTALL_START, appInfo.mAdId);
            AppDownloader.startInstall(GlobalInfo.getContext(), (int) appInfo.mDownloadId);
        }
    }

    @Override // com.ss.android.downloadlib.utils.WeakHandler.IHandler
    public void handleMsg(Message message) throws JSONException {
        if (message.what != 200) {
            return;
        }
        handleStartInstallMsg((AppInfo) message.obj, message.arg1);
    }
}
