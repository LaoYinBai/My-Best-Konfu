package com.ss.android.socialbase.appdownloader.util;

import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DecryptUtils {
    public static String decrypt(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        byte[] bArrDecode = Base64.decode(DownloadUtils.hexToString(str), 0);
        int length = str2.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (byte b : bArrDecode) {
            if (i >= length) {
                i %= length;
            }
            sb.append((char) (b ^ str2.charAt(i)));
            i++;
        }
        return sb.toString();
    }

    public static String decryptSetting(@NonNull JSONObject jSONObject, String str, String str2) {
        return decrypt(jSONObject.optString(str), str2);
    }

    public static String decryptOpenUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new String(Base64.decode(DownloadUtils.hexToString(str), 0));
    }
}
