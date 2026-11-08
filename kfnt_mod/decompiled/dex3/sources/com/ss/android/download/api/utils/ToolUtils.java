package com.ss.android.download.api.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ToolUtils {
    public static long optLong(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return 0L;
        }
        try {
            return Long.valueOf(jSONObject.optString(str)).longValue();
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    public static String getNameByPackageName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public static Drawable getIconByPackageName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationIcon(packageManager.getApplicationInfo(str, 128));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean shouldDownloadWithPatchApply(DownloadSetting downloadSetting, String str) {
        if (downloadSetting == null || downloadSetting.optInt(DownloadSettingKeys.APK_UPDATE_HANDLER_ENABLE, 1) != 1) {
            return false;
        }
        return Constants.MIME_PATCH.equals(str);
    }

    public static JSONObject copyJson(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        if (jSONObject != null && jSONObject2 != null) {
            try {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jSONObject2.put(next, jSONObject.get(next));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject2;
    }

    @NonNull
    public static JSONObject copyJson(JSONObject jSONObject) {
        return copyJson(jSONObject, new JSONObject());
    }

    @NonNull
    public static JSONObject mergeJson(JSONObject... jSONObjectArr) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (jSONObjectArr == null || jSONObjectArr.length == 0) {
            return jSONObject;
        }
        for (JSONObject jSONObject2 : jSONObjectArr) {
            if (jSONObject2 != null) {
                copyJson(jSONObject2, jSONObject);
            }
        }
        return jSONObject;
    }

    public static String getNotEmptyStr(String... strArr) {
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }
}
