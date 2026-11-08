package com.bytedance.sdk.account.utils;

import android.text.TextUtils;
import com.appsflyer.share.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class Utils {
    public static String getUrl(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return str + str2 + Constants.URL_PATH_DELIMITER;
    }
}
