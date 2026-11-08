package com.ss.android.download.api.utils;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.sigmob.sdk.common.Constants;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.addownload.GlobalInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MarketUriUtils {
    public static boolean isMarketUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            return false;
        }
        if (GlobalInfo.getDownloadSettings().optInt("market_url_opt", 1) == 0) {
            return "market".equals(scheme);
        }
        return "market".equals(scheme) || "appmarket".equals(scheme) || "oaps".equals(scheme) || "oppomarket".equals(scheme) || "mimarket".equals(scheme) || "vivomarket".equals(scheme) || "vivoMarket".equals(scheme) || "gomarket".equals(scheme) || "goMarket".equals(scheme) || "mstore".equals(scheme) || "samsungapps".equals(scheme);
    }

    public static String getPackageNameFromUri(@NonNull Uri uri) {
        return ToolUtils.getNotEmptyStr(uri.getQueryParameter(AdDownloadModel.JsonKey.ID), uri.getQueryParameter("packagename"), uri.getQueryParameter("pkg"), uri.getQueryParameter("package_name"), uri.getQueryParameter(Constants.APPID));
    }
}
