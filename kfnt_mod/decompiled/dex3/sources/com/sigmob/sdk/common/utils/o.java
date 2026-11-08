package com.sigmob.sdk.common.utils;

import android.content.Context;
import com.ss.android.downloadad.api.download.AdDownloadModel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class o {
    public static int a(Context context, String str) {
        return a(context, str, AdDownloadModel.JsonKey.ID);
    }

    private static int a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }

    public static int b(Context context, String str) {
        return a(context, str, "layout");
    }

    public static int c(Context context, String str) {
        return a(context, str, "drawable");
    }
}
