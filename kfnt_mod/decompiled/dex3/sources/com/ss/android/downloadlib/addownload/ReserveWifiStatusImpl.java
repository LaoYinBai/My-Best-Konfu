package com.ss.android.downloadlib.addownload;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.addownload.model.ModelManager;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ReserveWifiStatusImpl implements IReserveWifiStatusListener {
    @Override // com.ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener
    public void onStatusChanged(DownloadInfo downloadInfo, int i, int i2) throws JSONException {
        NativeDownloadModel nativeModelByInfo = ModelManager.getInstance().getNativeModelByInfo(downloadInfo);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.RESERVE_WIFI_SOURCE, Integer.valueOf(i2));
            jSONObject.putOpt(EventConstants.ExtraJson.RESERVE_WIFI_STATUS, Integer.valueOf(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.PAUSE_RESERVE_WIFI, jSONObject, nativeModelByInfo);
    }
}
