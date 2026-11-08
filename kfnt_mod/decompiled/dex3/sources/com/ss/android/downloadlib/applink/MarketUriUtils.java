package com.ss.android.downloadlib.applink;

import android.net.Uri;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MarketUriUtils {
    public static boolean isMarketUri(Uri uri) {
        return com.ss.android.download.api.utils.MarketUriUtils.isMarketUri(uri);
    }

    public static String getPackageNameFromUri(@NonNull Uri uri) {
        return com.ss.android.download.api.utils.MarketUriUtils.getPackageNameFromUri(uri);
    }
}
