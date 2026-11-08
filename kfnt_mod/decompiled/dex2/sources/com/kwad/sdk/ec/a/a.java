package com.kwad.sdk.ec.a;

import android.net.Uri;
import android.text.TextUtils;
import com.kwad.sdk.KsAdSDKImpl;
import com.meizu.cloud.pushsdk.constants.PushConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static String a(String str, int i, String str2) {
        return TextUtils.isEmpty(str2) ? str : (i == 0 || i == 3) ? a(str, str2) : str;
    }

    private static String a(String str, String str2) {
        Uri.Builder builderAppendQueryParameter = Uri.parse(str).buildUpon().appendQueryParameter("returnBack", "liveunion_" + KsAdSDKImpl.get().getAppId());
        if (TextUtils.isEmpty(str2)) {
            str2 = PushConstants.PUSH_TYPE_NOTIFY;
        }
        return builderAppendQueryParameter.appendQueryParameter("back_url", str2).toString();
    }
}
