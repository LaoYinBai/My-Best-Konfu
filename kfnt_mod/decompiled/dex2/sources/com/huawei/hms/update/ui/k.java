package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.heytap.mcssdk.mode.CommandMessage;
import com.huawei.hms.common.PackageConstants;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class k extends com.huawei.hms.update.ui.a {
    private BroadcastReceiver k;
    private Handler l = new Handler();
    private int m = 0;
    private Handler n = new Handler() { // from class: com.huawei.hms.update.ui.k.1
        @Override // android.os.Handler
        public void handleMessage(Message message) throws IllegalAccessException, InstantiationException {
            Bundle bundle = (Bundle) message.obj;
            switch (message.what) {
                case 101:
                    k.this.a(bundle);
                    break;
                case 102:
                    k.this.b(bundle);
                    break;
                case 103:
                    k.this.c(bundle);
                    break;
            }
        }
    };

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 2000;
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        super.onBridgeActivityCreate(activity);
        if (this.c == null) {
            return;
        }
        this.f = 0;
        if (a(activity)) {
            return;
        }
        if (!a(true)) {
            b(8, this.f);
        } else {
            a(8, this.f);
        }
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        this.l.removeCallbacksAndMessages(null);
        e();
        super.onBridgeActivityDestroy();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        if (this.e && this.b != null) {
            return this.b.onBridgeActivityResult(i, i2, intent);
        }
        HMSLog.i("SilentUpdateWizard", "onBridgeActivityResult requestCode is " + i + "resultCode is " + i2);
        if (i != getRequestCode()) {
            return false;
        }
        if (i2 == 0) {
            d();
            b(20000);
            return true;
        }
        if (i2 == 4) {
            a();
            return true;
        }
        if (!a(true)) {
            b(i2, this.f);
        } else {
            a(i2, this.f);
        }
        return true;
    }

    private boolean a(Activity activity) throws JSONException {
        if (TextUtils.isEmpty(this.g)) {
            return false;
        }
        Intent intent = new Intent("com.huawei.appmarket.intent.action.ThirdUpdateAction");
        intent.setPackage(PackageConstants.SERVICES_PACKAGE_APPMARKET);
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pkgName", this.g);
            jSONObject.put("versioncode", this.i);
            jSONArray.put(jSONObject);
            intent.putExtra(CommandMessage.PARAMS, jSONArray.toString());
            intent.putExtra("isHmsOrApkUpgrade", this.c.a());
            intent.putExtra("buttonDlgY", ResourceLoaderUtil.getString("hms_install"));
            intent.putExtra("buttonDlgN", ResourceLoaderUtil.getString("hms_cancel"));
            intent.putExtra("upgradeDlgContent", ResourceLoaderUtil.getString("hms_update_message_new", "%P"));
            try {
                HMSLog.i("SilentUpdateWizard", "start silent activity of AppMarket");
                activity.startActivityForResult(intent, getRequestCode());
                HMSLog.i("SilentUpdateWizard", "start silent activity finished");
                return true;
            } catch (ActivityNotFoundException unused) {
                HMSLog.e("SilentUpdateWizard", "ActivityNotFoundException");
                return false;
            }
        } catch (JSONException e) {
            HMSLog.e("SilentUpdateWizard", "create hmsJsonObject fail" + e.getMessage());
            return false;
        }
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        super.onBridgeConfigurationChanged();
    }

    private void d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.appmarket.service.downloadservice.Receiver");
        intentFilter.addAction("com.huawei.appmarket.service.downloadservice.progress.Receiver");
        intentFilter.addAction("com.huawei.appmarket.service.installerservice.Receiver");
        this.k = new com.huawei.hms.update.c.a(this.n);
        Activity activityB = b();
        if (activityB != null) {
            activityB.registerReceiver(this.k, intentFilter);
        }
    }

    private void e() {
        Activity activityB = b();
        if (activityB == null || this.k == null) {
            return;
        }
        activityB.unregisterReceiver(this.k);
        this.k = null;
    }

    private void b(int i) {
        this.l.removeCallbacksAndMessages(null);
        this.l.postDelayed(new a(), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        this.l.removeCallbacksAndMessages(null);
        e();
        c();
        if (!a(false)) {
            b(i, this.f);
        } else {
            a(i, this.f);
        }
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        super.onKeyUp(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        String string = bundle.containsKey("UpgradePkgName") ? bundle.getString("UpgradePkgName") : null;
        if (string != null && string.equals(this.g) && bundle.containsKey("downloadtask.status")) {
            int i = bundle.getInt("downloadtask.status");
            HMSLog.i("SilentUpdateWizard", "handleDownloadStatus-status is " + i);
            if (i == 3 || i == 5 || i == 6 || i == 8) {
                c(i);
            } else if (i == 4) {
                b(60000);
            } else {
                b(20000);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Bundle bundle) throws IllegalAccessException, InstantiationException {
        String string = bundle.containsKey("UpgradePkgName") ? bundle.getString("UpgradePkgName") : null;
        if (string != null && string.equals(this.g) && bundle.containsKey("UpgradeDownloadProgress") && bundle.containsKey("UpgradeAppName")) {
            int i = bundle.getInt("UpgradeDownloadProgress");
            HMSLog.i("SilentUpdateWizard", "handlerDownloadProgress-progress is " + i);
            b(20000);
            if (i >= 99) {
                i = 99;
            }
            this.m = i;
            if (this.d == null) {
                a(e.class);
            }
            if (this.d != null) {
                ((e) this.d).b(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Bundle bundle) {
        if (bundle.containsKey("packagename") && bundle.containsKey("status")) {
            String string = bundle.getString("packagename");
            int i = bundle.getInt("status");
            HMSLog.i("SilentUpdateWizard", "handlerInstallStatus-status is " + i);
            if (string == null || !string.equals(this.g)) {
                return;
            }
            if (i == 2) {
                this.l.removeCallbacksAndMessages(null);
                if (this.d != null) {
                    ((e) this.d).b(100);
                }
                b(0, this.f);
                return;
            }
            if (i == -1 || i == -2) {
                c(i);
            } else {
                b(60000);
            }
        }
    }

    @Override // com.huawei.hms.update.ui.a
    void a(Class<? extends b> cls) throws IllegalAccessException, InstantiationException {
        try {
            b bVarNewInstance = cls.newInstance();
            if (this.m > 0 && (bVarNewInstance instanceof e)) {
                ((e) bVarNewInstance).a(this.m);
            }
            bVarNewInstance.a(this);
            this.d = bVarNewInstance;
        } catch (IllegalAccessException | IllegalStateException | InstantiationException e) {
            HMSLog.e("SilentUpdateWizard", "In showDialog, Failed to show the dialog." + e.getMessage());
        }
    }

    @Override // com.huawei.hms.update.ui.a
    void a() {
        b(13, this.f);
    }

    private class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k.this.c(14);
        }
    }
}
