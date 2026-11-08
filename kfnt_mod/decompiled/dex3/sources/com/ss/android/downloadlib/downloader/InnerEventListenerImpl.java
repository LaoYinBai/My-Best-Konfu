package com.ss.android.downloadlib.downloader;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.AdDownloadCompletedEventHandlerImpl;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.monitor.InnerEventListener;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class InnerEventListenerImpl implements InnerEventListener {
    @Override // com.ss.android.socialbase.downloader.monitor.InnerEventListener
    public void onEvent(int i, String str, JSONObject jSONObject) throws JSONException {
        NativeDownloadModel nativeModelByInfo;
        DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(i);
        if (downloadInfo == null || (nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo)) == null) {
            return;
        }
        if (MonitorConstants.EventLabel.INSTALL_VIEW_RESULT.equals(str)) {
            jSONObject = ToolUtils.copyJson(jSONObject);
            AdDownloadCompletedEventHandlerImpl.monitorDownloadInfo(jSONObject, downloadInfo);
            ToolUtils.safePut(jSONObject, "model_id", Long.valueOf(nativeModelByInfo.getId()));
        }
        AdEventHandler.getInstance().sendEvent(str, jSONObject, nativeModelByInfo);
    }

    @Override // com.ss.android.socialbase.downloader.monitor.InnerEventListener
    public void onUnityEvent(int i, String str, JSONObject jSONObject) {
        NativeDownloadModel nativeModelByInfo;
        DownloadInfo downloadInfo = Downloader.getInstance(GlobalInfo.getContext()).getDownloadInfo(i);
        if (downloadInfo == null || (nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo)) == null) {
            return;
        }
        AdEventHandler.getInstance().sendUnityEvent(str, jSONObject, nativeModelByInfo);
    }
}
