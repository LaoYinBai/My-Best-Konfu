package com.huawei.hms.update.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.hms.common.PackageConstants;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h extends a {
    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 2005;
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) throws IllegalAccessException, InstantiationException {
        super.onBridgeActivityCreate(activity);
        if (this.c == null) {
            return;
        }
        this.f = 5;
        if (this.c.isNeedConfirm() && !TextUtils.isEmpty(this.h)) {
            a(i.class);
        } else {
            if (d()) {
                return;
            }
            if (!a(false)) {
                b(8, this.f);
            } else {
                a(8, this.f);
            }
        }
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        super.onBridgeActivityDestroy();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        if (this.e && this.b != null) {
            return this.b.onBridgeActivityResult(i, i2, intent);
        }
        if (this.f != 5 || i != getRequestCode()) {
            return false;
        }
        if (a(this.g, this.i)) {
            b(0, this.f);
            return true;
        }
        b(8, this.f);
        return true;
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
        super.onBridgeConfigurationChanged();
    }

    @Override // com.huawei.hms.update.ui.a
    public void a(b bVar) {
        HMSLog.i("HiappWizard", "Enter onCancel.");
        if (bVar instanceof i) {
            a();
        }
    }

    @Override // com.huawei.hms.update.ui.a
    public void b(b bVar) {
        HMSLog.i("HiappWizard", "Enter onDoWork.");
        if (bVar instanceof i) {
            bVar.c();
            if (d()) {
                return;
            }
            if (!a(false)) {
                b(8, this.f);
            } else {
                a(8, this.f);
            }
        }
    }

    private boolean d() {
        Activity activityB = b();
        if (activityB == null || activityB.isFinishing() || TextUtils.isEmpty(this.g)) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("com.huawei.appmarket.intent.action.AppDetail");
            intent.putExtra("APP_PACKAGENAME", this.g);
            a(intent);
            activityB.startActivityForResult(intent, getRequestCode());
            return true;
        } catch (ActivityNotFoundException unused) {
            HMSLog.e("HiappWizard", "can not open hiapp");
            return false;
        }
    }

    private void a(Intent intent) {
        List<ResolveInfo> listQueryIntentActivities = b().getPackageManager().queryIntentActivities(intent, C.DEFAULT_BUFFER_SEGMENT_SIZE);
        if (listQueryIntentActivities == null || listQueryIntentActivities.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(PackageConstants.SERVICES_PACKAGE_APPMARKET_TV);
        arrayList.add(PackageConstants.SERVICES_PACKAGE_APPMARKET_CAR);
        arrayList.add(PackageConstants.SERVICES_PACKAGE_APPMARKET);
        if (listQueryIntentActivities.size() == 1) {
            return;
        }
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            if (arrayList.contains(resolveInfo.activityInfo.packageName)) {
                intent.setPackage(resolveInfo.activityInfo.packageName);
                return;
            }
        }
    }

    @Override // com.huawei.hms.update.ui.a
    void a(Class<? extends b> cls) throws IllegalAccessException, InstantiationException {
        c();
        try {
            b bVarNewInstance = cls.newInstance();
            if (!TextUtils.isEmpty(this.h) && (bVarNewInstance instanceof i)) {
                this.h = ResourceLoaderUtil.getString("hms_update_title");
                ((i) bVarNewInstance).a(this.h);
            }
            bVarNewInstance.a(this);
            this.d = bVarNewInstance;
        } catch (IllegalAccessException | IllegalStateException | InstantiationException e) {
            HMSLog.e("HiappWizard", "In showDialog, Failed to show the dialog." + e.getMessage());
        }
    }

    @Override // com.huawei.hms.update.ui.a
    void a() {
        b(13, this.f);
    }

    @Override // com.huawei.hms.update.ui.a, com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
        if (this.e && this.b != null) {
            this.b.onKeyUp(i, keyEvent);
            return;
        }
        if (4 == i) {
            HMSLog.i("HiappWizard", "In onKeyUp, Call finish.");
            Activity activityB = b();
            if (activityB == null || activityB.isFinishing()) {
                return;
            }
            activityB.setResult(0, null);
            activityB.finish();
        }
    }
}
