package com.ss.android.downloadlib.addownload.model;

import com.ss.android.downloadad.api.model.NativeDownloadModel;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.downloadlib.event.AdEventHandler;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PackageManagerChecker {
    private static volatile PackageManagerChecker sInstance;

    private PackageManagerChecker() {
    }

    public static PackageManagerChecker getInstance() {
        if (sInstance == null) {
            synchronized (InstalledAppManager.class) {
                if (sInstance == null) {
                    sInstance = new PackageManagerChecker();
                }
            }
        }
        return sInstance;
    }

    public void checkVersionCode(int i, int i2, NativeDownloadModel nativeDownloadModel) {
        if (nativeDownloadModel == null) {
            return;
        }
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(nativeDownloadModel.getDownloadId());
        if (downloadSettingObtain.optInt(DownloadSettingKeys.REPORT_API_HIJACK, 0) == 0) {
            return;
        }
        int i3 = i2 - i;
        if (i <= 0 || i3 <= downloadSettingObtain.optInt(DownloadSettingKeys.CHECK_API_HIJACK_VERSION_CODE_DIFF, 500)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(EventConstants.ExtraJson.KEY_VERSION_CODE_DIFF, i3);
            jSONObject.put(EventConstants.ExtraJson.KEY_INSTALLED_VERSION_CODE, i2);
            jSONObject.put(EventConstants.ExtraJson.KEY_HIJACK_TYPE, 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.getInstance().sendEvent(EventConstants.Label.API_HIJACK, jSONObject, nativeDownloadModel);
    }
}
