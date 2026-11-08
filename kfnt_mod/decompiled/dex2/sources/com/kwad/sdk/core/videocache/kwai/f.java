package com.kwad.sdk.core.videocache.kwai;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.kwad.sdk.core.videocache.l;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import java.util.HashMap;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f implements c {
    private String b(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return (iLastIndexOf == -1 || iLastIndexOf <= str.lastIndexOf(47) || (iLastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(iLastIndexOf + 1, str.length());
    }

    private String c(String str) {
        Uri uri = Uri.parse(str);
        if (uri.getHost() == null || !uri.getHost().contains("yximgs.com")) {
            return str;
        }
        Uri.Builder builderBuildUpon = uri.buildUpon();
        HashMap map = new HashMap();
        if (uri.isHierarchical()) {
            Set<String> queryParameterNames = uri.getQueryParameterNames();
            for (String str2 : queryParameterNames) {
                map.put(str2, uri.getQueryParameter(str2));
            }
            builderBuildUpon.clearQuery();
            for (String str3 : queryParameterNames) {
                if (!RemoteMessageConst.Notification.TAG.equals(str3) && !AppIconSetting.DEFAULT_LARGE_ICON.equals(str3) && str3 != null) {
                    builderBuildUpon.appendQueryParameter(str3, (String) map.get(str3));
                }
            }
        }
        return builderBuildUpon.toString();
    }

    @Override // com.kwad.sdk.core.videocache.kwai.c
    public String a(String str) {
        String strC = c(str);
        String strB = b(strC);
        String strD = l.d(strC);
        if (TextUtils.isEmpty(strB)) {
            return strD;
        }
        return strD + "." + strB;
    }
}
