package com.kwad.sdk.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.common.PackageConstants;
import com.kwad.sdk.core.response.model.AdTemplate;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private static final Map<String, String> a = new HashMap();

    static {
        a.put("HUAWEI", PackageConstants.SERVICES_PACKAGE_APPMARKET);
        a.put("OPPO", "com.oppo.market");
        a.put("vivo", "com.bbk.appstore");
        a.put("xiaomi", "com.xiaomi.market");
        a.put("OnePlus", "com.oppo.market");
        a.put("Meizu", "com.meizu.mstore");
        a.put("samsung", "com.sec.android.app.samsungapps");
        a.put("SMARTISAN", "com.smartisanos.appstore");
        a.put("Realme", "com.oppo.market");
        a.put("HONOR", PackageConstants.SERVICES_PACKAGE_APPMARKET);
    }

    private static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(Context context, String str, AdTemplate adTemplate) {
        if (!an.b() || adTemplate.mAdScene == null || adTemplate.mAdScene.adStyle == 4 || com.kwad.sdk.core.download.a.d.a(context, str) != 1) {
            return false;
        }
        adTemplate.mXiaomiAppStoreDetailViewOpen = true;
        return true;
    }

    public static boolean a(Context context, String str, String str2) throws URISyntaxException {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        if ("samsung".equals(Build.BRAND)) {
            str = "http://apps.samsung.com/appquery/appDetail.as?appId=" + str2;
        }
        try {
            String str3 = a.get(Build.BRAND);
            Intent uri = Intent.parseUri(str, 1);
            uri.addFlags(268435456);
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(uri, 0)) {
                if (!a(resolveInfo)) {
                    String str4 = resolveInfo.activityInfo.packageName;
                    if (str4.equals(str3) || a(str4)) {
                        uri.setComponent(new ComponentName(str4, resolveInfo.activityInfo.name));
                        context.startActivity(uri);
                        return true;
                    }
                }
            }
            return a(context, str);
        } catch (Exception unused) {
            return a(context, str);
        }
    }

    private static boolean a(ResolveInfo resolveInfo) {
        return resolveInfo == null || resolveInfo.activityInfo == null || TextUtils.isEmpty(resolveInfo.activityInfo.packageName);
    }

    private static boolean a(String str) {
        return "OPPO".equals(Build.BRAND) && "com.heytap.market".equals(str);
    }
}
