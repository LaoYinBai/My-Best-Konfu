package com.sigmob.sdk.base.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.BaseBroadcastReceiver;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.common.mta.PointCategory;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a extends BroadcastReceiver {
    a() {
    }

    private void a(Context context, PackageInfo packageInfo, String str) {
        t.a((BaseAdUnit) null, PointCategory.APP_INSTALL_MONITOR, packageInfo, str);
    }

    private void a(Context context, BaseAdUnit baseAdUnit, PackageInfo packageInfo) {
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_INSTALL_FINISH);
        t.a(baseAdUnit, PointCategory.APP_INSTALL_END, packageInfo, "1");
        HashMap map = new HashMap();
        map.put("result", "1");
        BaseBroadcastReceiver.a(context, baseAdUnit.getUuid(), map, IntentActions.ACTION_INTERSTITIAL_INSTALL_END);
        if (baseAdUnit.getMaterial().interaction_type.intValue() == com.sigmob.sdk.videoAd.f.DownloadOpenDeepLinkType.a()) {
            try {
                com.sigmob.sdk.common.utils.j.a(context, Uri.parse(baseAdUnit.getMacroCommon().macroProcess(baseAdUnit.getMaterial().deeplink_url)));
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_OPEN_DEEPLINK);
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00a8  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceive(android.content.Context r10, android.content.Intent r11) {
        /*
            Method dump skipped, instructions count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.services.a.onReceive(android.content.Context, android.content.Intent):void");
    }
}
