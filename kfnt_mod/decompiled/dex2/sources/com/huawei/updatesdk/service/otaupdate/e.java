package com.huawei.updatesdk.service.otaupdate;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.e.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends AsyncTask<Void, Void, com.huawei.updatesdk.a.b.d.a.d> {
    private Context a;
    private CheckUpdateCallBack b;
    private UpdateParams c;
    private Toast d;
    private boolean e = false;
    private List<String> f = new ArrayList();
    private com.huawei.updatesdk.a.b.d.b g;

    public e(Context context, UpdateParams updateParams, CheckUpdateCallBack checkUpdateCallBack) {
        this.c = updateParams;
        this.a = context;
        this.b = checkUpdateCallBack;
        h.a().a(updateParams.getServiceZone());
        if (TextUtils.isEmpty(h.a().b())) {
            h.a().c(updateParams.getAppStorePkgName());
        }
    }

    private PackageInfo a(Context context, String str) {
        PackageInfo packageInfoA = com.huawei.updatesdk.service.e.c.a(str, context);
        if (packageInfoA != null) {
            return packageInfoA;
        }
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.packageName = str;
        packageInfo.versionName = "1.0";
        packageInfo.versionCode = 1;
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.targetSdkVersion = 19;
        packageInfo.applicationInfo = applicationInfo;
        return packageInfo;
    }

    private com.huawei.updatesdk.a.b.d.a.d a(Context context, List<String> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(context, it.next()));
        }
        com.huawei.updatesdk.service.appmgr.bean.a aVarA = com.huawei.updatesdk.service.appmgr.bean.a.a(arrayList);
        aVarA.a(0);
        this.g = new com.huawei.updatesdk.a.b.d.b(aVarA, null);
        return this.g.a();
    }

    private void a(Context context, ApkUpgradeInfo apkUpgradeInfo) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) AppUpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("app_update_parm", apkUpgradeInfo);
        bundle.putBoolean("app_must_btn", this.c.isMustBtnOne());
        intent.putExtras(bundle);
        if (!(context instanceof Activity)) {
            intent.setFlags(268435456);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("CheckOtaAndUpdataTask", "go AppUpdateActivity error: " + e.toString());
        }
    }

    private void a(List<ApkUpgradeInfo> list) {
        if (i.a(list)) {
            return;
        }
        for (ApkUpgradeInfo apkUpgradeInfo : list) {
            if (apkUpgradeInfo != null && !TextUtils.isEmpty(apkUpgradeInfo.getFullDownUrl_())) {
                apkUpgradeInfo.setDownurl_(apkUpgradeInfo.getFullDownUrl_());
            }
        }
    }

    private boolean a() {
        return !this.e && TextUtils.isEmpty(this.c.getTargetPkgName()) && i.a(this.c.getPackageList());
    }

    private void b() {
        if (this.d != null) {
            this.d.cancel();
        }
    }

    private void b(com.huawei.updatesdk.a.b.d.a.d dVar) {
        if (this.b != null) {
            Intent intent = new Intent();
            intent.putExtra("status", 6);
            if (dVar.d() != null) {
                intent.putExtra(UpdateKey.FAIL_CODE, dVar.d().ordinal());
            }
            intent.putExtra(UpdateKey.FAIL_REASON, dVar.e());
            intent.putExtra(UpdateKey.RESPONSE_CODE, dVar.b());
            this.b.onUpdateInfo(intent);
            this.b.onUpdateStoreError(dVar.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public com.huawei.updatesdk.a.b.d.a.d doInBackground(Void... voidArr) {
        com.huawei.updatesdk.a.a.b.a.a.a.a("CheckOtaAndUpdataTask", "CheckOtaAndUpdataTask doInBackground");
        com.huawei.updatesdk.service.d.a.c.a(this);
        if (Build.VERSION.SDK_INT < 24 && com.huawei.updatesdk.a.a.c.a.b.d() != 0 && com.huawei.updatesdk.service.e.c.a(com.huawei.updatesdk.a.b.a.a.a().b()) == c.a.NOT_INSTALLED && !com.huawei.updatesdk.service.e.c.c()) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("CheckOtaAndUpdataTask", "api <24,HiApp not installed and no permission");
            return null;
        }
        com.huawei.updatesdk.a.b.d.a.c.a(com.huawei.updatesdk.a.a.c.a.b.a(g.c(this.a, "upsdk_store_url")));
        h.a().a(this.a);
        String targetPkgName = this.c.getTargetPkgName();
        if (TextUtils.isEmpty(targetPkgName)) {
            targetPkgName = this.a.getPackageName();
        }
        if (!i.a(this.c.getPackageList())) {
            this.f.addAll(this.c.getPackageList());
        } else if (!TextUtils.isEmpty(targetPkgName)) {
            this.f.add(targetPkgName);
        }
        com.huawei.updatesdk.service.a.a.a().a(h.a().c());
        return a(this.a, this.f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(com.huawei.updatesdk.a.b.d.a.d dVar) {
        com.huawei.updatesdk.service.d.a.c.a().remove(this);
        b();
        if (dVar == null) {
            if (this.b != null) {
                Intent intent = new Intent();
                intent.putExtra("status", 3);
                this.b.onUpdateInfo(intent);
                return;
            }
            return;
        }
        int iB = dVar.b();
        ArrayList<ApkUpgradeInfo> arrayList = null;
        if (dVar.a() == 0 && dVar.c() == 0) {
            arrayList = ((com.huawei.updatesdk.service.appmgr.bean.b) dVar).list_;
            a(arrayList);
            if (i.a(arrayList) && this.b != null) {
                Intent intent2 = new Intent();
                intent2.putExtra("status", 3);
                intent2.putExtra(UpdateKey.RESPONSE_CODE, iB);
                this.b.onUpdateInfo(intent2);
            }
        } else {
            b(dVar);
            com.huawei.updatesdk.a.a.b.a.a.a.d("CheckOtaAndUpdataTask", "get app update msg failed,responseCode is " + dVar.a());
        }
        if (i.a(arrayList)) {
            if (a()) {
                Toast.makeText(this.a, g.b(this.a, "upsdk_update_check_no_new_version"), 0).show();
                return;
            }
            return;
        }
        ApkUpgradeInfo apkUpgradeInfo = arrayList.get(0);
        if (this.b != null) {
            Intent intent3 = new Intent();
            intent3.putExtra(UpdateKey.INFO, (Serializable) apkUpgradeInfo);
            intent3.putParcelableArrayListExtra(UpdateKey.INFO_LIST, arrayList);
            intent3.putExtra("status", 7);
            intent3.putExtra(UpdateKey.RESPONSE_CODE, iB);
            this.b.onUpdateInfo(intent3);
        }
        if (apkUpgradeInfo != null) {
            Log.i("CheckOtaAndUpdataTask", "ApkUpgradeInfo,version = " + apkUpgradeInfo.getVersion_() + ",versionCode = " + apkUpgradeInfo.getVersionCode_() + ",detailId = " + apkUpgradeInfo.getDetailId_() + ",devType = " + apkUpgradeInfo.getDevType_() + ",oldVersionCode = " + apkUpgradeInfo.getOldVersionCode_());
        } else {
            Log.e("CheckOtaAndUpdataTask", "info == null");
        }
        if (this.c.isShowImmediate()) {
            a(this.a, apkUpgradeInfo);
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        if (this.g != null) {
            this.g.d();
        }
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        d.a().a(this.b);
        if (a()) {
            this.d = Toast.makeText(this.a, g.b(this.a, "upsdk_checking_update_prompt"), 1);
            this.d.show();
        }
    }
}
