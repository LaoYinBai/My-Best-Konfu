package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.update.provider.UpdateProvider;
import com.huawei.hms.update.ui.d;
import com.huawei.hms.update.ui.j;
import com.huawei.hms.utils.FileUtil;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.updatesdk.UpdateSdkAPI;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.huawei.updatesdk.service.otaupdate.UpdateKey;
import com.kwai.sodler.lib.ext.PluginError;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class l extends a implements com.huawei.hms.update.a.a.b {
    private com.huawei.hms.update.a.a.a k;
    private com.huawei.hms.update.a.a.c l;
    private int m = 0;

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return PluginError.ERROR_UPD_REQUEST;
    }

    private static Uri a(Context context, File file) {
        PackageManagerHelper packageManagerHelper = new PackageManagerHelper(context);
        String packageName = context.getPackageName();
        String str = packageName + UpdateProvider.AUTHORITIES_SUFFIX;
        boolean z = true;
        if (Build.VERSION.SDK_INT <= 23 || (context.getApplicationInfo().targetSdkVersion <= 23 && !packageManagerHelper.hasProvider(packageName, str))) {
            z = false;
        }
        if (z) {
            return UpdateProvider.getUriForFile(context, str, file);
        }
        return Uri.fromFile(file);
    }

    private static void a(final com.huawei.hms.update.a.a.b bVar, final int i, final com.huawei.hms.update.a.a.c cVar) {
        if (bVar != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.hms.update.ui.l.1
                @Override // java.lang.Runnable
                public void run() {
                    bVar.a(i, cVar);
                }
            });
        }
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) throws IllegalAccessException, InstantiationException {
        super.onBridgeActivityCreate(activity);
        if (this.c == null) {
            return;
        }
        this.f = 6;
        if (a(activity)) {
            if (this.c.isNeedConfirm() && !TextUtils.isEmpty(this.h)) {
                a(i.class);
            } else {
                a(c.class);
                a(this);
            }
        }
    }

    private boolean a(Activity activity) {
        if (HMSPackageManager.getInstance(activity).isApkUpdateNecessary(this.c.c())) {
            return true;
        }
        c();
        SystemManager.getInstance().notifyUpdateResult(0);
        return false;
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        g();
        super.onBridgeActivityDestroy();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        if (this.e && this.b != null) {
            return this.b.onBridgeActivityResult(i, i2, intent);
        }
        if (this.f != 6 || i != getRequestCode()) {
            return false;
        }
        if (a(this.g, this.i)) {
            b(0, this.f);
            return true;
        }
        e();
        return true;
    }

    @Override // com.huawei.hms.update.a.a.b
    public void a(int i, com.huawei.hms.update.a.a.c cVar) throws IllegalAccessException, InstantiationException {
        HMSLog.i("UpdateWizard", "Enter onCheckUpdate, status: " + com.huawei.hms.update.a.a.d.a(i));
        if (i == 1000) {
            this.l = cVar;
            d();
        }
        switch (i) {
            case 1201:
            case 1202:
            case 1203:
                a(j.b.class);
                break;
            default:
                a(j.b.class);
                break;
        }
    }

    private void d() throws IllegalAccessException, InstantiationException {
        Activity activityB = b();
        String netType = activityB != null ? SystemUtils.getNetType(activityB.getBaseContext()) : "";
        HMSLog.i("UpdateWizard", "current network is " + netType);
        if ("WIFI".equals(netType)) {
            a(e.class);
            f();
            HMSLog.i("UpdateWizard", "current network is wifi");
        } else {
            a(d.b.class);
            HMSLog.i("UpdateWizard", "current network is not wifi");
        }
    }

    @Override // com.huawei.hms.update.a.a.b
    public void a(int i, int i2, int i3, File file) throws IllegalAccessException, InstantiationException {
        HMSLog.i("UpdateWizard", "Enter onDownloadPackage, status: " + com.huawei.hms.update.a.a.d.a(i) + ", reveived: " + i2 + ", total: " + i3);
        if (i == 2000) {
            c();
            if (file == null) {
                e();
                return;
            } else if (FileUtil.verifyHash(this.l.e, file)) {
                a(file);
                return;
            } else {
                HMSLog.i("UpdateWizard", "Hash value mismatch for download file");
            }
        }
        switch (i) {
            case PushConstants.BROADCAST_MESSAGE_ARRIVE /* 2100 */:
                if (this.d != null && (this.d instanceof e)) {
                    int i4 = 0;
                    if (i2 >= 0 && i3 > 0) {
                        i4 = (int) ((i2 * 100) / i3);
                    }
                    this.m = i4;
                    ((e) this.d).b(i4);
                    break;
                }
                break;
            case 2101:
                break;
            default:
                switch (i) {
                    case PushConstants.ON_TIME_NOTIFICATION /* 2201 */:
                        a(j.c.class);
                        break;
                    case PushConstants.DELAY_NOTIFICATION /* 2202 */:
                        a(d.c.class);
                        break;
                    case 2203:
                    case 2204:
                        a(j.d.class);
                        break;
                }
        }
    }

    private void a(File file) {
        Activity activityB = b();
        if (activityB == null || activityB.isFinishing()) {
            return;
        }
        Uri uriA = a(activityB, file);
        if (uriA == null) {
            HMSLog.e("UpdateWizard", "In startInstaller, Failed to creates a Uri from a file.");
            e();
            return;
        }
        if (a(activityB)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(uriA, "application/vnd.android.package-archive");
            intent.setFlags(3);
            intent.putExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", true);
            try {
                activityB.startActivityForResult(intent, getRequestCode());
            } catch (ActivityNotFoundException e) {
                HMSLog.e("UpdateWizard", "In startInstaller, Failed to start package installer." + e.getMessage());
                e();
            }
        }
    }

    @Override // com.huawei.hms.update.ui.a
    public void a(b bVar) throws IllegalAccessException, InstantiationException {
        HMSLog.i("UpdateWizard", "Enter onCancel.");
        if (bVar instanceof i) {
            a();
            return;
        }
        if (bVar instanceof c) {
            g();
            a();
            return;
        }
        if (bVar instanceof e) {
            g();
            a(d.C0010d.class);
            return;
        }
        if (bVar instanceof d.C0010d) {
            a(e.class);
            f();
        } else if (bVar instanceof d.c) {
            a();
        } else if (bVar instanceof d.b) {
            a();
        } else {
            e();
        }
    }

    @Override // com.huawei.hms.update.ui.a
    public void b(b bVar) throws IllegalAccessException, InstantiationException {
        HMSLog.i("UpdateWizard", "Enter onDoWork.");
        if (bVar instanceof i) {
            bVar.c();
            a(c.class);
            a(this);
            return;
        }
        if (bVar instanceof d.C0010d) {
            bVar.c();
            a();
            return;
        }
        if (bVar instanceof d.c) {
            a(e.class);
            f();
            return;
        }
        if (bVar instanceof d.b) {
            a(e.class);
            f();
        } else if (bVar instanceof j.b) {
            e();
        } else if (bVar instanceof j.c) {
            e();
        } else if (bVar instanceof j.d) {
            e();
        }
    }

    private void e() {
        if (!a(false)) {
            b(8, this.f);
        } else {
            a(8, this.f);
        }
    }

    @Override // com.huawei.hms.update.ui.a
    void a(Class<? extends b> cls) throws IllegalAccessException, InstantiationException {
        if (a(b())) {
            c();
            try {
                b bVarNewInstance = cls.newInstance();
                if (!TextUtils.isEmpty(this.h) && (bVarNewInstance instanceof i)) {
                    this.h = ResourceLoaderUtil.getString("hms_update_title");
                    ((i) bVarNewInstance).a(this.h);
                }
                if (this.m > 0 && (bVarNewInstance instanceof e)) {
                    ((e) bVarNewInstance).a(this.m);
                }
                bVarNewInstance.a(this);
                this.d = bVarNewInstance;
            } catch (IllegalAccessException | IllegalStateException | InstantiationException e) {
                HMSLog.e("UpdateWizard", "In showDialog, Failed to show the dialog." + e.getMessage());
            }
        }
    }

    @Override // com.huawei.hms.update.ui.a
    void a() {
        b(13, this.f);
    }

    private void a(final com.huawei.hms.update.a.a.b bVar) {
        if (bVar == null) {
            return;
        }
        Activity activityB = b();
        if (activityB == null || activityB.isFinishing()) {
            a(bVar, 1201, (com.huawei.hms.update.a.a.c) null);
        } else if (a(activityB)) {
            UpdateSdkAPI.checkTargetAppUpdate(activityB, this.c.b(), new CheckUpdateCallBack() { // from class: com.huawei.hms.update.ui.l.2
                @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
                public void onMarketInstallInfo(Intent intent) {
                }

                @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
                public void onUpdateInfo(Intent intent) {
                    if (intent != null) {
                        l.this.a(intent, bVar);
                    }
                }

                @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
                public void onMarketStoreError(int i) {
                    HMSLog.e("UpdateWizard", "onMarketStoreError responseCode: " + i);
                }

                @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
                public void onUpdateStoreError(int i) {
                    HMSLog.e("UpdateWizard", "onUpdateStoreError responseCode: " + i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent, com.huawei.hms.update.a.a.b bVar) {
        try {
            int intExtra = intent.getIntExtra("status", -99);
            HMSLog.i("UpdateWizard", "CheckUpdateCallBack status is " + intExtra);
            String stringExtra = intent.getStringExtra(UpdateKey.FAIL_REASON);
            if (!TextUtils.isEmpty(stringExtra)) {
                HMSLog.e("UpdateWizard", "checkTargetAppUpdate reason is " + stringExtra);
            }
            if (intExtra != 7) {
                if (intExtra == 3) {
                    a(bVar, 1202, (com.huawei.hms.update.a.a.c) null);
                    return;
                } else {
                    a(bVar, 1201, (com.huawei.hms.update.a.a.c) null);
                    return;
                }
            }
            Serializable serializableExtra = intent.getSerializableExtra(UpdateKey.INFO);
            if (serializableExtra instanceof ApkUpgradeInfo) {
                ApkUpgradeInfo apkUpgradeInfo = (ApkUpgradeInfo) serializableExtra;
                String package_ = apkUpgradeInfo.getPackage_();
                int versionCode_ = apkUpgradeInfo.getVersionCode_();
                String downurl_ = apkUpgradeInfo.getDownurl_();
                int size_ = apkUpgradeInfo.getSize_();
                String sha256_ = apkUpgradeInfo.getSha256_();
                if (TextUtils.isEmpty(package_) || !package_.equals(this.c.b())) {
                    a(bVar, 1201, (com.huawei.hms.update.a.a.c) null);
                    return;
                }
                if (versionCode_ < this.c.c()) {
                    HMSLog.e("UpdateWizard", "CheckUpdateCallBack versionCode is " + versionCode_ + "bean.getClientVersionCode() is " + this.c.c());
                    a(bVar, 1203, (com.huawei.hms.update.a.a.c) null);
                    return;
                }
                if (TextUtils.isEmpty(downurl_) || TextUtils.isEmpty(sha256_)) {
                    a(bVar, 1201, (com.huawei.hms.update.a.a.c) null);
                } else {
                    a(bVar, 1000, new com.huawei.hms.update.a.a.c(package_, versionCode_, downurl_, size_, sha256_));
                }
            }
        } catch (Exception e) {
            HMSLog.e("UpdateWizard", "intent has some error" + e.getMessage());
            a(bVar, 1201, (com.huawei.hms.update.a.a.c) null);
        }
    }

    private void f() throws IllegalAccessException, InstantiationException {
        Activity activityB = b();
        if (activityB == null || activityB.isFinishing()) {
            a(j.c.class);
        } else if (a(activityB)) {
            g();
            this.k = new com.huawei.hms.update.a.c(new com.huawei.hms.update.a.d(activityB));
            this.k.a(this, this.l);
        }
    }

    private void g() {
        if (this.k != null) {
            this.k.a();
            this.k = null;
        }
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        if (this.e && this.b != null) {
            this.b.onKeyUp(i, keyEvent);
            return;
        }
        if (4 == i) {
            HMSLog.i("UpdateWizard", "In onKeyUp, Call finish.");
            Activity activityB = b();
            if (activityB == null || activityB.isFinishing()) {
                return;
            }
            activityB.setResult(0, null);
            activityB.finish();
        }
    }
}
