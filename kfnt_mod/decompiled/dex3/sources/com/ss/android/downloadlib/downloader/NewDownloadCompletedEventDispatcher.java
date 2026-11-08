package com.ss.android.downloadlib.downloader;

import android.content.Context;
import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.AdDownloadCompletedEventHandlerImpl;
import com.ss.android.downloadlib.DownloadDispatcher;
import com.ss.android.downloadlib.addownload.AdDelayTaskManager;
import com.ss.android.downloadlib.addownload.AdDownloadSecurityManager;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NewDownloadCompletedEventDispatcher implements IAppDownloadEventHandler {
    private Context mContext;

    public NewDownloadCompletedEventDispatcher(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler
    public void handleAppInstalled(Context context, String str) {
        AdDownloadCompletedEventHandlerImpl.getInstance().onAppInstalled(str);
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler
    public void handleDownloadEvent(int i, int i2, String str, int i3, long j) throws JSONException {
        DownloadInfo downloadInfo;
        NativeDownloadModel nativeModelByInfo;
        Context context = this.mContext;
        if (context == null || (downloadInfo = Downloader.getInstance(context).getDownloadInfo(i)) == null || downloadInfo.getStatus() == 0 || (nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo)) == null) {
            return;
        }
        if (i2 == 1) {
            AdDownloadCompletedEventHandlerImpl.onDownloadFinish(downloadInfo, nativeModelByInfo);
            if ("application/vnd.android.package-archive".equals(downloadInfo.getMimeType())) {
                AdDelayTaskManager.inst().trySendDelayInstallMsg(downloadInfo, nativeModelByInfo.getId(), nativeModelByInfo.getExtValue(), nativeModelByInfo.getPackageName(), downloadInfo.getTitle(), nativeModelByInfo.getLogExtra(), downloadInfo.getTargetFilePath());
                return;
            }
            return;
        }
        if (i2 != 3) {
            switch (i2) {
                case 5:
                    AdEventHandler.getInstance().sendEvent("download_notification", EventConstants.Label.NOTIFICATION_PAUSE, nativeModelByInfo);
                    break;
                case 6:
                    AdEventHandler.getInstance().sendEvent("download_notification", EventConstants.Label.NOTIFICATION_CONTINUE, nativeModelByInfo);
                    break;
                case 7:
                    AdEventHandler.getInstance().sendEvent("download_notification", EventConstants.Label.NOTIFICATION_CLICK, nativeModelByInfo);
                    break;
            }
            return;
        }
        AdEventHandler.getInstance().sendEvent("download_notification", EventConstants.Label.NOTIFICATION_INSTALL, AdDownloadCompletedEventHandlerImpl.appendSpaceExtraInfo(new JSONObject(), downloadInfo), nativeModelByInfo);
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler
    public boolean installIntercept(int i, boolean z) {
        if (GlobalInfo.getDownloadAutoInstallInterceptListener() != null) {
            return GlobalInfo.getDownloadAutoInstallInterceptListener().installIntercept(z);
        }
        return false;
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler
    public void handleDownloadCancel(DownloadInfo downloadInfo) throws JSONException {
        if (downloadInfo == null) {
            return;
        }
        DownloadDispatcher.getInstance().notifyDownloadCanceled(downloadInfo);
        if (DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.REPORT_DOWNLOAD_CANCEL, 1) == 1) {
            AdEventHandler.getInstance().sendDownloadCancelEvent(downloadInfo, new BaseException(DownloadErrorCode.ERROR_CANCELED, ""));
        } else {
            AdEventHandler.getInstance().sendDownloadFailedEvent(downloadInfo, new BaseException(DownloadErrorCode.ERROR_CANCELED, ""));
        }
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler
    public void handleAppInstallError(int i, int i2, String str, String str2, String str3) {
        DownloadInfo downloadInfo;
        if (this.mContext == null || (downloadInfo = Downloader.getInstance(this.mContext).getDownloadInfo(i)) == null || downloadInfo.getStatus() != -3) {
            return;
        }
        downloadInfo.setPackageName(str2);
        AdDownloadSecurityManager.inst().dealInvalidateDownloadFile(this.mContext, downloadInfo);
    }

    @Override // com.ss.android.socialbase.appdownloader.depend.IAppDownloadEventHandler
    public boolean isForbidInvalidatePackageInstall() {
        return AdDownloadSecurityManager.inst().isEnableInvalidateDownloadFile();
    }
}
