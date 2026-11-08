package com.huawei.updatesdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.huawei.updatesdk.a.a.c.b.b;
import com.huawei.updatesdk.service.a.a;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.d.a.c;
import com.huawei.updatesdk.service.otaupdate.AppUpdateActivity;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.huawei.updatesdk.service.otaupdate.UpdateParams;
import com.huawei.updatesdk.service.otaupdate.d;
import com.huawei.updatesdk.service.otaupdate.e;
import com.huawei.updatesdk.service.otaupdate.g;
import com.huawei.updatesdk.service.otaupdate.h;
import com.kwad.sdk.collector.AppStatusRules;
import java.util.List;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class UpdateSdkAPI {
    public static final String TAG = "UpdateSdk";

    public static void checkAppUpdate(Context context, CheckUpdateCallBack checkUpdateCallBack, boolean z, boolean z2) {
        if (context == null) {
            return;
        }
        if (b.a(context)) {
            init(context);
            new e(context, new UpdateParams.Builder().setIsShowImmediate(z).setMustBtnOne(z2).build(), checkUpdateCallBack).executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
            return;
        }
        if (checkUpdateCallBack != null) {
            Intent intent = new Intent();
            intent.putExtra("status", 2);
            checkUpdateCallBack.onUpdateInfo(intent);
        }
        Toast.makeText(context, g.b(context, "upsdk_no_available_network_prompt_toast"), 0).show();
    }

    public static void checkAppUpdate(Context context, UpdateParams updateParams, CheckUpdateCallBack checkUpdateCallBack) {
        if (context == null || updateParams == null || checkUpdateCallBack == null) {
            return;
        }
        init(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jB = a.a().b();
        if (updateParams.getMinIntervalDay() == 0 || Math.abs(jCurrentTimeMillis - jB) >= updateParams.getMinIntervalDay() * AppStatusRules.DEFAULT_START_TIME) {
            if (b.a(context)) {
                a.a().a(jCurrentTimeMillis);
                new e(context, updateParams, checkUpdateCallBack).executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
            } else {
                Intent intent = new Intent();
                intent.putExtra("status", 2);
                checkUpdateCallBack.onUpdateInfo(intent);
            }
        }
    }

    public static void checkClientOTAUpdate(Context context, CheckUpdateCallBack checkUpdateCallBack, boolean z, int i, boolean z2) {
        if (context == null || !b.a(context)) {
            return;
        }
        init(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jB = a.a().b();
        if (i == 0 || Math.abs(jCurrentTimeMillis - jB) >= i * AppStatusRules.DEFAULT_START_TIME) {
            a.a().a(jCurrentTimeMillis);
            e eVar = new e(context, new UpdateParams.Builder().setIsShowImmediate(z).setMustBtnOne(z2).setMinIntervalDay(i).build(), checkUpdateCallBack);
            eVar.a(true);
            eVar.executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
        }
    }

    public static void checkTargetAppUpdate(Context context, String str, CheckUpdateCallBack checkUpdateCallBack) {
        if (context == null || TextUtils.isEmpty(str) || checkUpdateCallBack == null) {
            if (checkUpdateCallBack != null) {
                Intent intent = new Intent();
                intent.putExtra("status", 1);
                checkUpdateCallBack.onUpdateInfo(intent);
                return;
            }
            return;
        }
        if (b.a(context)) {
            init(context);
            new e(context, new UpdateParams.Builder().setMustBtnOne(false).setTargetPkgName(str).build(), checkUpdateCallBack).executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
        } else {
            Intent intent2 = new Intent();
            intent2.putExtra("status", 2);
            checkUpdateCallBack.onUpdateInfo(intent2);
        }
    }

    private static void init(Context context) {
        com.huawei.updatesdk.a.b.a.a.a(context);
        com.huawei.updatesdk.service.d.a.b.a();
        Log.i("updatesdk", "UpdateSDK version is: 2.0.6.300 ,flavor: envrelease ,pkgName: " + context.getPackageName());
    }

    public static void releaseCallBack() {
        d.a().a((CheckUpdateCallBack) null);
        List<AsyncTask> listA = c.a();
        if (listA == null || listA.isEmpty()) {
            return;
        }
        for (AsyncTask asyncTask : listA) {
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
            Log.d(TAG, "cancel task");
        }
    }

    public static void setAppStorePkgName(String str) {
        h.a().c(str);
    }

    public static void setServiceZone(String str) {
        h.a().a(str);
    }

    public static void showUpdateDialog(Context context, ApkUpgradeInfo apkUpgradeInfo, boolean z) {
        if (context == null || apkUpgradeInfo == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) AppUpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("app_update_parm", apkUpgradeInfo);
        bundle.putBoolean("app_must_btn", z);
        intent.putExtras(bundle);
        if (!(context instanceof Activity)) {
            intent.setFlags(268435456);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "go AppUpdateActivity error: " + e.toString());
        }
    }
}
