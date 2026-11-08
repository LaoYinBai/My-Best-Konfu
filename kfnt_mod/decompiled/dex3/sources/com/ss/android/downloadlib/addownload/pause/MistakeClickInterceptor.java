package com.ss.android.downloadlib.addownload.pause;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MistakeClickInterceptor implements IPauseInterceptor {
    @Override // com.ss.android.downloadlib.addownload.pause.IPauseInterceptor
    public boolean interceptor(NativeDownloadModel nativeDownloadModel, int i, IPauseCallback iPauseCallback) throws JSONException {
        if (nativeDownloadModel == null || !enable(nativeDownloadModel.getDownloadId())) {
            return false;
        }
        if (System.currentTimeMillis() - nativeDownloadModel.getClickDownloadTime() > getMistakeClickInterval(nativeDownloadModel.getDownloadId())) {
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(EventConstants.ExtraJson.PAUSE_OPTIMISE_TYPE, "mistake_click");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendUnityEvent(EventConstants.UnityLabel.PAUSE_OPTIMISE, jSONObject, nativeDownloadModel);
        return true;
    }

    private long getMistakeClickInterval(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.PAUSE_OPTIMISE_MISTAKE_CLICK_INTERVAL, 300);
    }

    private boolean enable(int i) {
        return DownloadSetting.obtain(i).optInt(DownloadSettingKeys.PAUSE_OPTIMISE_MISTAKE_CLICK_INTERVAL_SWITCH, 0) == 1;
    }
}
