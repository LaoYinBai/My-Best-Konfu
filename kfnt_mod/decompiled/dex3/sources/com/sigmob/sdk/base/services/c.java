package com.sigmob.sdk.base.services;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.BaseBroadcastReceiver;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c extends BroadcastReceiver {
    c() {
    }

    private void a(Context context, long j, BaseAdUnit baseAdUnit) throws Throwable {
        String str = "download info is null";
        if (baseAdUnit != null) {
            Map<String, Object> mapA = com.sigmob.sdk.base.common.l.a(a(context), j);
            com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_DOWNLOAD_FINISH);
            if (mapA != null) {
                String str2 = (String) mapA.get("fileName");
                int iIntValue = ((Integer) mapA.get("status")).intValue();
                int iIntValue2 = ((Integer) mapA.get("reason")).intValue();
                Uri uri = (Uri) mapA.get("uri");
                if (str2 != null && iIntValue == 8) {
                    try {
                        String strA = com.sigmob.sdk.common.utils.d.a(context, uri);
                        com.sigmob.sdk.base.common.l.b(context, strA, baseAdUnit);
                        t.a(baseAdUnit, PointCategory.DOWNLOAD_END, com.sigmob.sdk.common.a.a(context, strA), "1");
                        HashMap map = new HashMap();
                        map.put("result", "1");
                        BaseBroadcastReceiver.a(context, baseAdUnit.getUuid(), map, IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_END);
                        return;
                    } catch (Throwable th) {
                        SigmobLog.e(th.getMessage());
                        t.a(PointCategory.APP_INSTALL_START, SigmobError.ERROR_SIGMOB_INSTALL_FAIL.getErrorCode(), th.getMessage(), baseAdUnit);
                        t.b(PointCategory.APP_INSTALL_START, Constants.FAIL, baseAdUnit);
                        return;
                    }
                }
                try {
                    String str3 = String.format("status %d, reason %d", Integer.valueOf(iIntValue), Integer.valueOf(iIntValue2));
                    try {
                        SigmobLog.i("handleDownloadComplete:fail:" + com.sigmob.sdk.common.utils.d.b(com.sigmob.sdk.common.utils.d.a(context, uri)));
                        str = str3;
                    } catch (Throwable th2) {
                        th = th2;
                        str = str3;
                        SigmobLog.e(th.getMessage());
                        Toast.makeText(context, "下载失败", 0).show();
                        t.a("download_failed", 2, str, baseAdUnit);
                        t.b(PointCategory.DOWNLOAD_END, Constants.FAIL, baseAdUnit);
                        HashMap map2 = new HashMap();
                        map2.put("result", Constants.FAIL);
                        BaseBroadcastReceiver.a(context, baseAdUnit.getUuid(), map2, IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_END);
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            try {
                Toast.makeText(context, "下载失败", 0).show();
            } catch (Throwable th4) {
                SigmobLog.e(th4.getMessage());
            }
            t.a("download_failed", 2, str, baseAdUnit);
            t.b(PointCategory.DOWNLOAD_END, Constants.FAIL, baseAdUnit);
            HashMap map22 = new HashMap();
            map22.put("result", Constants.FAIL);
            BaseBroadcastReceiver.a(context, baseAdUnit.getUuid(), map22, IntentActions.ACTION_INTERSTITIAL_DOWNLOAD_END);
        }
    }

    DownloadManager a(Context context) {
        return (DownloadManager) context.getSystemService("download");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws Throwable {
        long longExtra = intent.getLongExtra(DownloadConstants.EXTRA_DOWNLOAD_ID, -1L);
        BaseAdUnit baseAdUnit = null;
        com.sigmob.sdk.base.common.l.b(longExtra, (String) null);
        File file = new File(com.sigmob.sdk.common.utils.d.d(), longExtra + MsgConstant.CACHE_LOG_FILE_EXT);
        Object objD = com.sigmob.sdk.common.utils.d.d(file.getAbsolutePath());
        if (objD != null && (objD instanceof BaseAdUnit)) {
            baseAdUnit = (BaseAdUnit) objD;
            BaseAdUnit baseAdUnitA = com.sigmob.sdk.base.common.d.a(baseAdUnit.getUuid());
            if (baseAdUnitA != null) {
                baseAdUnit = baseAdUnitA;
            }
        }
        com.sigmob.sdk.common.utils.d.b(file.getAbsolutePath());
        String action = intent.getAction();
        char c = 65535;
        if (action.hashCode() == 1248865515 && action.equals("android.intent.action.DOWNLOAD_COMPLETE")) {
            c = 0;
        }
        if (c == 0 && baseAdUnit != null) {
            a(context, longExtra, baseAdUnit);
        }
    }
}
