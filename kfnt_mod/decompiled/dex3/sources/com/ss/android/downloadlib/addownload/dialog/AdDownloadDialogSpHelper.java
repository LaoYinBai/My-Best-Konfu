package com.ss.android.downloadlib.addownload.dialog;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.AppInfo;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class AdDownloadDialogSpHelper {
    static final String KEY_INSTALLED_LIST = "key_installed_list";
    static final String KEY_UNINSTALLED_LIST = "key_uninstalled_list";

    AdDownloadDialogSpHelper() {
    }

    @NonNull
    CopyOnWriteArrayList<AppInfo> loadInfoFromSp(String str, String str2) {
        CopyOnWriteArrayList<AppInfo> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        try {
            String string = GlobalInfo.getContext().getSharedPreferences(str, 0).getString(str2, "");
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    AppInfo appInfoFromJson = AppInfo.fromJson(jSONObject.optJSONObject(itKeys.next()));
                    if (appInfoFromJson != null) {
                        copyOnWriteArrayList.add(appInfoFromJson);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copyOnWriteArrayList;
    }

    void updateInfoToSp(String str, String str2, CopyOnWriteArrayList<AppInfo> copyOnWriteArrayList) throws JSONException {
        if (copyOnWriteArrayList == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator<AppInfo> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                AppInfo next = it.next();
                if (next != null) {
                    jSONObject.put(String.valueOf(next.mAdId), next.toJSon());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalInfo.getContext().getSharedPreferences(str, 0).edit().putString(str2, jSONObject.toString()).apply();
    }

    void cleanSpKey(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        GlobalInfo.getContext().getSharedPreferences(str, 0).edit().putString(str2, "").apply();
    }
}
