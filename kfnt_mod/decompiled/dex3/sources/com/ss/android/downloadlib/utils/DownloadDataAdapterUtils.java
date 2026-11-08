package com.ss.android.downloadlib.utils;

import android.text.TextUtils;
import com.ss.android.downloadlib.constants.DownloadConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadDataAdapterUtils {
    public static String generateDownloadExtra(String str, String str2, boolean z, String str3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("extra", str);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("notification_jump_url", str2);
            }
            jSONObject.put(DownloadConstants.KEY_TOAST, z);
            jSONObject.put(DownloadConstants.KEY_BUSINESS_TYPE, str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
