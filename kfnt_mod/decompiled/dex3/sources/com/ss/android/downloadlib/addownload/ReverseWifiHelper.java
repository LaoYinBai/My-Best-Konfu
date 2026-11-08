package com.ss.android.downloadlib.addownload;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.addownload.dialog.ISelectOperationListener;
import com.ss.android.downloadlib.addownload.pause.IPauseCallback;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.exception.TTDownloaderMonitor;
import com.ss.android.downloadlib.utils.DownloadSettingUtils;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.impls.RetryScheduler;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ReverseWifiHelper {
    private static ISelectOperationListener sReverseWifiListener;

    public static boolean willPause(int i) {
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 7 || i == 8;
    }

    public static ISelectOperationListener getReverseWifiListener() {
        return sReverseWifiListener;
    }

    public static void setReverseWifiListener(ISelectOperationListener iSelectOperationListener) {
        sReverseWifiListener = iSelectOperationListener;
    }

    public static boolean tryReverseWifi(final NativeDownloadModel nativeDownloadModel, DownloadInfo downloadInfo, int i, final IPauseCallback iPauseCallback) throws JSONException {
        if (nativeDownloadModel == null) {
            TTDownloaderMonitor.inst().monitorDataError("tryReverseWifi nativeModel null");
            return false;
        }
        if (downloadInfo == null) {
            TTDownloaderMonitor.inst().monitorDataError("tryReverseWifi info null");
            return false;
        }
        final int id = downloadInfo.getId();
        boolean zPauseReserveOnWifiIsOpen = DownloadSettingUtils.pauseReserveOnWifiIsOpen(nativeDownloadModel);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.SWITCH_STATUS, Integer.valueOf(zPauseReserveOnWifiIsOpen ? 1 : 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.PAUSE_RESERVE_WIFI_SWITCH_STATUS, jSONObject, nativeDownloadModel);
        if (!zPauseReserveOnWifiIsOpen || !willPause(i) || DownloadUtils.isWifi(GlobalInfo.getContext()) || downloadInfo.hasPauseReservedOnWifi()) {
            return false;
        }
        setReverseWifiListener(new ISelectOperationListener() { // from class: com.ss.android.downloadlib.addownload.ReverseWifiHelper.1
            @Override // com.ss.android.downloadlib.addownload.dialog.ISelectOperationListener
            public void confirm() throws JSONException {
                ReverseWifiHelper.setReverseWifiListener(null);
                DownloadInfo downloadInfo2 = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(id);
                if (downloadInfo2 != null) {
                    downloadInfo2.startPauseReserveOnWifi();
                    RetryScheduler.getInstance().tryStartScheduleRetry(downloadInfo2);
                    AdEventHandler.getInstance().sendEvent(EventConstants.Label.PAUSE_RESERVE_WIFI_CONFIRM, nativeDownloadModel);
                }
                iPauseCallback.pause(nativeDownloadModel);
            }

            @Override // com.ss.android.downloadlib.addownload.dialog.ISelectOperationListener
            public void cancel() throws JSONException {
                ReverseWifiHelper.setReverseWifiListener(null);
                DownloadInfo downloadInfo2 = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(id);
                if (downloadInfo2 != null) {
                    downloadInfo2.stopPauseReserveOnWifi();
                }
                AdEventHandler.getInstance().sendEvent(EventConstants.Label.PAUSE_RESERVE_WIFI_CANCEL, nativeDownloadModel);
                iPauseCallback.pause(nativeDownloadModel);
            }
        });
        TTDelegateActivity.showReverseWifiDialog(nativeDownloadModel);
        return true;
    }
}
