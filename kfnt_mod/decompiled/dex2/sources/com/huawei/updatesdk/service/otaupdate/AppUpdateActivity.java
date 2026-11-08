package com.huawei.updatesdk.service.otaupdate;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.hms.common.PackageConstants;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.c.g;
import com.huawei.updatesdk.service.e.c;
import com.huawei.updatesdk.service.f.a;
import com.huawei.updatesdk.service.otaupdate.c;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.Serializable;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AppUpdateActivity extends Activity implements com.huawei.updatesdk.service.b.b, com.huawei.updatesdk.service.otaupdate.b {
    private AlertDialog b;
    private com.huawei.updatesdk.service.f.a c;
    private com.huawei.updatesdk.service.f.a d;
    private ProgressBar e;
    private TextView f;
    private com.huawei.updatesdk.a.b.b.b l;
    private d q;
    private String a = PackageConstants.SERVICES_PACKAGE_APPMARKET;
    private boolean g = false;
    private boolean h = false;
    private ApkUpgradeInfo i = null;
    private boolean j = false;
    private boolean k = false;
    private int m = -99;
    private int n = -99;
    private int o = -99;
    private Intent p = null;

    public class a implements c.a {
        public a() {
        }

        @Override // com.huawei.updatesdk.service.otaupdate.c.a
        public void a(Boolean bool) {
            if (bool.booleanValue()) {
                try {
                    Intent intent = new Intent("com.huawei.appmarket.intent.action.ThirdUpdateRemindAction");
                    intent.setPackage(PackageConstants.SERVICES_PACKAGE_APPMARKET);
                    AppUpdateActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    com.huawei.updatesdk.a.a.b.a.a.a.d("AppUpdateActivity", "goHiappUpgrade error: " + e.toString());
                }
            }
            AppUpdateActivity.this.finish();
        }
    }

    private static class b implements DialogInterface.OnDismissListener {
        private b() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            Intent intent = new Intent();
            intent.putExtra(UpdateKey.DIALOG_STATUS, UpdateDialogStatusCode.DISMISS);
            com.huawei.updatesdk.service.otaupdate.d.a().b(intent);
        }
    }

    private static class c implements DialogInterface.OnShowListener {
        private c() {
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            Intent intent = new Intent();
            intent.putExtra(UpdateKey.DIALOG_STATUS, UpdateDialogStatusCode.SHOW);
            com.huawei.updatesdk.service.otaupdate.d.a().b(intent);
        }
    }

    private class d extends com.huawei.updatesdk.a.b.c.a {
        private d() {
        }

        @Override // com.huawei.updatesdk.a.b.c.a
        public void a(Context context, com.huawei.updatesdk.a.b.c.b bVar) {
            if (bVar.d()) {
                if (AppUpdateActivity.this.c != null) {
                    AppUpdateActivity.this.c.c();
                }
                AppUpdateActivity.this.f();
                String strB = bVar.b();
                String dataString = bVar.c().getDataString();
                if (dataString == null || dataString.length() < 9) {
                    return;
                }
                String strSubstring = dataString.substring(8);
                if ("android.intent.action.PACKAGE_ADDED".equals(strB) && AppUpdateActivity.this.a.equals(strSubstring)) {
                    com.huawei.updatesdk.service.otaupdate.d.a().a(AppUpdateActivity.this.a(6, 0, -1));
                    g.a.a(strSubstring, 1);
                    AppUpdateActivity.this.a(AppUpdateActivity.this.i.getPackage_(), AppUpdateActivity.this.i.getDetailId_());
                    if (AppUpdateActivity.this.j) {
                        AppUpdateActivity.this.d(AppUpdateActivity.this.i);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent a(int i, int i2, int i3) {
        Intent intent = new Intent();
        intent.putExtra(UpdateKey.MARKET_DLD_STATUS, i3);
        intent.putExtra(UpdateKey.MARKET_INSTALL_STATE, i);
        intent.putExtra(UpdateKey.MARKET_INSTALL_TYPE, i2);
        return intent;
    }

    private void a() {
        com.huawei.updatesdk.service.otaupdate.a.a(this, this.q);
        com.huawei.updatesdk.service.b.c.b().b(this);
        if (this.l != null) {
            this.l.b();
        }
        f.a((com.huawei.updatesdk.service.otaupdate.b) null);
    }

    private void a(View view) {
        try {
            ScrollView scrollView = (ScrollView) view.findViewById(g.a(this, "scroll_layout"));
            if (Build.VERSION.SDK_INT >= 22) {
                TypedValue typedValue = new TypedValue();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getTheme().resolveAttribute(R.attr.dialogPreferredPadding, typedValue, true);
                ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                int iComplexToDimensionPixelSize = TypedValue.complexToDimensionPixelSize(typedValue.data, displayMetrics);
                scrollView.setPadding(iComplexToDimensionPixelSize, 0, iComplexToDimensionPixelSize, 0);
            }
        } catch (Exception e) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("AppUpdateActivity", e.toString());
        }
    }

    private void a(com.huawei.updatesdk.a.b.c.b bVar) {
        if (this.e == null) {
            return;
        }
        this.e.setProgress(i.a(bVar.a("download_apk_already", 0), bVar.a("download_apk_size", 0)));
        this.f.setText(i.a((int) ((this.e.getProgress() / this.e.getMax()) * 100.0f)));
    }

    private void a(ApkUpgradeInfo apkUpgradeInfo, TextView textView) {
        if (textView == null) {
            return;
        }
        if (apkUpgradeInfo.getDiffSize_() <= 0) {
            textView.setVisibility(8);
            return;
        }
        String strA = i.a(this, apkUpgradeInfo.getSize_());
        SpannableString spannableString = new SpannableString(strA);
        spannableString.setSpan(new StrikethroughSpan(), 0, strA.length(), 33);
        spannableString.setSpan(new TextAppearanceSpan("HwChinese-medium", 0, (int) textView.getTextSize(), null, null), 0, spannableString.length(), 33);
        textView.setText(spannableString);
    }

    private void a(String str) {
        Intent intent = new Intent("com.huawei.appmarket.intent.action.ThirdUpdateAction");
        intent.setPackage(this.a);
        intent.putExtra("APP_PACKAGENAME", str);
        intent.putExtra("APP_MUST_UPDATE_BTN", this.k);
        try {
            this.h = false;
            startActivityForResult(intent, PushConstants.PUSHSERVICE_INFO_SEND_MESSAGE_BY_NOTIFICATION_SERVICE_ERROR);
        } catch (ActivityNotFoundException e) {
            com.huawei.updatesdk.a.a.b.a.a.a.d("AppUpdateActivity", "goHiappUpgrade error: " + e.toString());
            this.h = true;
            Intent intent2 = new Intent();
            intent2.putExtra("status", 8);
            com.huawei.updatesdk.service.otaupdate.d.a().b(intent2);
            d(this.i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            this.m = 1;
            finish();
            return;
        }
        Intent intent = new Intent("com.huawei.appmarket.appmarket.intent.action.AppDetail.withdetailId");
        intent.setPackage(this.a);
        intent.putExtra("appDetailId", str2);
        intent.putExtra("thirdId", str);
        intent.addFlags(268468224);
        try {
            startActivity(intent);
            if (this.j) {
                return;
            }
            finish();
        } catch (ActivityNotFoundException unused) {
            f.a(this);
            f.a(this.a);
            Intent intent2 = new Intent();
            intent2.putExtra("status", 8);
            com.huawei.updatesdk.service.otaupdate.d.a().b(intent2);
            if (this.d != null) {
                this.d.c();
            }
        }
    }

    private void b() {
        if (this.d != null) {
            this.d.a(new b());
            this.d.a(new c());
        }
    }

    private void b(com.huawei.updatesdk.a.b.c.b bVar) {
        Bundle bundleA = bVar.a();
        if (bundleA != null) {
            int i = bundleA.getInt("download_status_param", -1);
            com.huawei.updatesdk.service.otaupdate.d.a().a(a(-1, -1, i));
            if (com.huawei.updatesdk.a.b.b.a.a.b(i)) {
                return;
            }
            f();
            if (com.huawei.updatesdk.a.b.b.a.a.a(i)) {
                Toast.makeText(this, getString(g.b(this, "upsdk_third_app_dl_install_failed")), 0).show();
                finish();
            }
        }
    }

    private void b(final ApkUpgradeInfo apkUpgradeInfo) {
        final com.huawei.updatesdk.service.f.a aVarA = com.huawei.updatesdk.service.f.a.a(this, null, getString(g.b(this, "upsdk_install")));
        aVarA.a(new com.huawei.updatesdk.service.f.b() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.6
            @Override // com.huawei.updatesdk.service.f.b
            public void a() {
                if (com.huawei.updatesdk.a.a.c.b.b.a(AppUpdateActivity.this)) {
                    AppUpdateActivity.this.e(apkUpgradeInfo);
                    aVarA.c();
                } else {
                    Toast.makeText(AppUpdateActivity.this, g.b(AppUpdateActivity.this, "upsdk_no_available_network_prompt_toast"), 0).show();
                    AppUpdateActivity.this.finish();
                }
            }

            @Override // com.huawei.updatesdk.service.f.b
            public void b() {
                aVarA.c();
                if (AppUpdateActivity.this.j) {
                    AppUpdateActivity.this.d(AppUpdateActivity.this.i);
                } else {
                    AppUpdateActivity.this.m = 4;
                    AppUpdateActivity.this.finish();
                }
            }
        });
        String string = getString(g.b(this, "upsdk_app_download_info_new"));
        aVarA.a(new a.b() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.7
            @Override // com.huawei.updatesdk.service.f.a.b
            public void a() {
                AppUpdateActivity.this.finish();
            }
        });
        aVarA.a(a.EnumC0016a.CONFIRM, string);
        aVarA.a(new DialogInterface.OnKeyListener() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.8
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4 || keyEvent.getAction() != 0) {
                    return false;
                }
                if (AppUpdateActivity.this.j) {
                    aVarA.c();
                    AppUpdateActivity.this.d(AppUpdateActivity.this.i);
                    return true;
                }
                AppUpdateActivity.this.m = 4;
                AppUpdateActivity.this.finish();
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        this.c = com.huawei.updatesdk.service.f.a.a(this, null, getString(g.b(this, "upsdk_third_app_dl_cancel_download_prompt_ex")));
        this.c.a(new com.huawei.updatesdk.service.f.b() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.3
            @Override // com.huawei.updatesdk.service.f.b
            public void a() {
                AppUpdateActivity.this.e.setProgress(0);
                AppUpdateActivity.this.e.setMax(0);
                AppUpdateActivity.this.f.setText("");
                AppUpdateActivity.this.f();
                if (AppUpdateActivity.this.l != null) {
                    AppUpdateActivity.this.l.a();
                }
                AppUpdateActivity.this.c.c();
                if (AppUpdateActivity.this.j) {
                    AppUpdateActivity.this.d(AppUpdateActivity.this.i);
                } else {
                    AppUpdateActivity.this.m = 4;
                    AppUpdateActivity.this.finish();
                }
            }

            @Override // com.huawei.updatesdk.service.f.b
            public void b() {
                AppUpdateActivity.this.c.c();
            }
        });
        String string = getString(g.b(this, "upsdk_third_app_dl_sure_cancel_download"));
        this.c.a(new a.b() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.4
            @Override // com.huawei.updatesdk.service.f.a.b
            public void a() {
                AppUpdateActivity.this.finish();
            }
        });
        this.c.a(a.EnumC0016a.CONFIRM, string);
    }

    private long c(ApkUpgradeInfo apkUpgradeInfo) {
        return (apkUpgradeInfo.getPackingType_() != 1 || apkUpgradeInfo.getBundleSize_() <= 0) ? apkUpgradeInfo.getDiffSize_() > 0 ? apkUpgradeInfo.getDiffSize_() : apkUpgradeInfo.getSize_() : apkUpgradeInfo.getBundleSize_();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (com.huawei.updatesdk.service.e.c.a(this, this.a)) {
            a(this.i.getPackage_(), this.i.getDetailId_());
            return;
        }
        if (com.huawei.updatesdk.a.b.a.a.a() == null) {
            com.huawei.updatesdk.a.b.a.a.a(this);
        }
        f.a(this);
        f.a(this.a);
        this.d.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i == 5 || i == 4) {
            Toast.makeText(this, getString(g.b(this, "upsdk_third_app_dl_install_failed")), 0).show();
            g.a.a(this.a, -1000001);
            finish();
        }
        if (i == 7) {
            g.a.a(this.a, -1000001);
            if (this.g) {
                d(this.i);
            } else {
                finish();
            }
        }
    }

    private void c(final com.huawei.updatesdk.a.b.c.b bVar) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.1
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundleA = bVar.a();
                if (bundleA == null) {
                    return;
                }
                int i = bundleA.getInt("INSTALL_STATE");
                com.huawei.updatesdk.service.otaupdate.d.a().a(AppUpdateActivity.this.a(i, bundleA.getInt("INSTALL_TYPE"), -1));
                AppUpdateActivity.this.c(i);
            }
        });
    }

    private void c(final String str) {
        if (this.b == null || !this.b.isShowing()) {
            this.b = new AlertDialog.Builder(this).create();
            View viewInflate = LayoutInflater.from(this).inflate(g.d(this, "upsdk_app_dl_progress_dialog"), (ViewGroup) null);
            this.e = (ProgressBar) viewInflate.findViewById(g.a(this, "third_app_dl_progressbar"));
            this.e.setMax(100);
            this.f = (TextView) viewInflate.findViewById(g.a(this, "third_app_dl_progress_text"));
            viewInflate.findViewById(g.a(this, "cancel_bg")).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AppUpdateActivity.this.b(str);
                }
            });
            this.b.setView(viewInflate);
            this.b.setCancelable(false);
            this.b.setCanceledOnTouchOutside(false);
            if (!com.huawei.updatesdk.service.otaupdate.a.a(this)) {
                this.b.show();
            }
            this.f.setText(i.a(0));
        }
    }

    private void d() {
        int iB = com.huawei.updatesdk.service.e.b.a().b();
        if (iB < 11 || iB >= 17) {
            return;
        }
        this.d.a(g.e(this, "upsdk_update_all_button"), g.f(this, "upsdk_white"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ApkUpgradeInfo apkUpgradeInfo) {
        if (apkUpgradeInfo == null) {
            finish();
            return;
        }
        String string = getString(g.b(this, "upsdk_ota_title"));
        String string2 = getString(g.b(this, "upsdk_ota_notify_updatebtn"));
        String string3 = getString(g.b(this, "upsdk_ota_cancel"));
        d(string);
        if (1 == apkUpgradeInfo.getIsCompulsoryUpdate_()) {
            string3 = getString(g.b(this, "upsdk_ota_force_cancel_new"));
            if (this.k) {
                this.d.a();
            }
            this.g = true;
        }
        this.d.a(new com.huawei.updatesdk.service.f.b() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.9
            @Override // com.huawei.updatesdk.service.f.b
            public void a() {
                AppUpdateActivity.this.o = 101;
                if (com.huawei.updatesdk.a.a.c.b.b.a(AppUpdateActivity.this)) {
                    AppUpdateActivity.this.c();
                    return;
                }
                Toast.makeText(AppUpdateActivity.this, g.b(AppUpdateActivity.this, "upsdk_no_available_network_prompt_toast"), 0).show();
                AppUpdateActivity.this.m = 2;
                AppUpdateActivity.this.finish();
            }

            @Override // com.huawei.updatesdk.service.f.b
            public void b() {
                AppUpdateActivity.this.d.c();
                AppUpdateActivity.this.m = 4;
                AppUpdateActivity.this.o = 100;
                if (AppUpdateActivity.this.j) {
                    AppUpdateActivity.this.finish();
                } else {
                    new com.huawei.updatesdk.service.otaupdate.c().a(AppUpdateActivity.this, AppUpdateActivity.this.new a());
                }
            }
        });
        b();
        this.d.a(new a.b() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.10
            @Override // com.huawei.updatesdk.service.f.a.b
            public void a() {
                AppUpdateActivity.this.finish();
            }
        });
        if (this.g) {
            this.d.a(false);
        } else {
            this.d.a(new DialogInterface.OnKeyListener() { // from class: com.huawei.updatesdk.service.otaupdate.AppUpdateActivity.2
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i != 4 || keyEvent.getAction() != 0) {
                        return false;
                    }
                    AppUpdateActivity.this.m = 4;
                    AppUpdateActivity.this.finish();
                    return true;
                }
            });
        }
        this.d.a(a.EnumC0016a.CONFIRM, string2);
        this.d.a(a.EnumC0016a.CANCEL, string3);
        d();
    }

    private void d(String str) {
        View viewInflate = LayoutInflater.from(this).inflate(g.d(this, "upsdk_ota_update_view"), (ViewGroup) null);
        ((TextView) viewInflate.findViewById(g.a(this, "content_textview"))).setText(TextUtils.isEmpty(this.i.getNewFeatures_()) ? getString(g.b(this, "upsdk_choice_update")) : this.i.getNewFeatures_());
        ((TextView) viewInflate.findViewById(g.a(this, "version_textview"))).setText(this.i.getVersion_());
        ((TextView) viewInflate.findViewById(g.a(this, "appsize_textview"))).setText(i.a(this, c(this.i)));
        ((TextView) viewInflate.findViewById(g.a(this, "name_textview"))).setText(this.i.getName_());
        a(this.i, (TextView) viewInflate.findViewById(g.a(this, "allsize_textview")));
        a(viewInflate);
        this.d = com.huawei.updatesdk.service.f.a.a(this, str, null);
        this.d.a(viewInflate);
    }

    private void e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this.q = new d();
        com.huawei.updatesdk.service.otaupdate.a.a(this, intentFilter, this.q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ApkUpgradeInfo apkUpgradeInfo) {
        e();
        c(apkUpgradeInfo.getPackage_());
        this.l = new com.huawei.updatesdk.a.b.b.b(new com.huawei.updatesdk.a.b.b.a(apkUpgradeInfo.getDownurl_(), apkUpgradeInfo.getSize_(), apkUpgradeInfo.getSha256_()));
        this.l.executeOnExecutor(Executors.newSingleThreadExecutor(), new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            if (this.b == null || !this.b.isShowing()) {
                return;
            }
            this.b.dismiss();
            this.b = null;
        } catch (IllegalArgumentException unused) {
            com.huawei.updatesdk.a.a.b.a.a.a.a("AppUpdateActivity", "progressDialog dismiss IllegalArgumentException");
        }
    }

    @Override // com.huawei.updatesdk.service.otaupdate.b
    public void a(int i) {
        Toast.makeText(this, getString(g.b(this, "upsdk_getting_message_fail_prompt_toast")), 0).show();
        com.huawei.updatesdk.service.otaupdate.d.a().a(i);
        finish();
    }

    @Override // com.huawei.updatesdk.service.b.b
    public void a(int i, com.huawei.updatesdk.a.b.c.b bVar) {
        if (bVar == null) {
            return;
        }
        if (i == 0) {
            b(bVar);
        } else if (1 == i) {
            a(bVar);
        } else if (2 == i) {
            c(bVar);
        }
    }

    @Override // com.huawei.updatesdk.service.otaupdate.b
    public void a(ApkUpgradeInfo apkUpgradeInfo) {
        if (apkUpgradeInfo != null) {
            com.huawei.updatesdk.service.b.c.b().a(this);
            b(apkUpgradeInfo);
        } else {
            Toast.makeText(this, getString(g.b(this, "upsdk_getting_message_fail_prompt_toast")), 0).show();
            finish();
        }
    }

    @Override // com.huawei.updatesdk.service.otaupdate.b
    public void b(int i) {
        Toast.makeText(this, getString(g.b(this, "upsdk_connect_server_fail_prompt_toast")), 0).show();
        com.huawei.updatesdk.service.otaupdate.d.a().a(i);
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        this.p = new Intent();
        this.p.putExtra("status", this.m);
        this.p.putExtra(UpdateKey.FAIL_CODE, this.n);
        this.p.putExtra(UpdateKey.MUST_UPDATE, this.g);
        this.p.putExtra(UpdateKey.BUTTON_STATUS, this.o);
        super.finish();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1002) {
            if (intent != null) {
                com.huawei.updatesdk.a.b.c.b bVarA = com.huawei.updatesdk.a.b.c.b.a(intent);
                this.m = i2;
                this.n = bVarA.a("installResultCode", -99);
                if (this.i.getIsCompulsoryUpdate_() == 1) {
                    this.g = bVarA.a(UpdateKey.MUST_UPDATE, false);
                }
            }
            if (this.i.getIsCompulsoryUpdate_() == 1 && i2 == 4) {
                this.g = true;
            }
            this.o = i2 == 4 ? 100 : 101;
            if (this.h) {
                return;
            }
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        Bundle bundleA = com.huawei.updatesdk.a.b.c.b.a(getIntent()).a();
        if (bundleA == null) {
            super.finish();
            return;
        }
        Serializable serializable = bundleA.getSerializable("app_update_parm");
        if (serializable == null || !(serializable instanceof ApkUpgradeInfo)) {
            this.m = 3;
            finish();
            return;
        }
        this.i = (ApkUpgradeInfo) serializable;
        this.k = bundleA.getBoolean("app_must_btn", false);
        if (this.i.getIsCompulsoryUpdate_() == 1) {
            this.j = true;
        }
        if (!TextUtils.isEmpty(h.a().b())) {
            this.a = h.a().b();
        }
        if (this.i.getDevType_() == 1 && com.huawei.updatesdk.service.e.c.a(this) == c.a.INSTALLED) {
            a(this.i.getPackage_());
        } else {
            d(this.i);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.c != null) {
            this.c.c();
            this.c = null;
        }
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
        f();
        a();
        super.onDestroy();
        finishActivity(PushConstants.PUSHSERVICE_INFO_SEND_MESSAGE_BY_NOTIFICATION_SERVICE_ERROR);
        if (this.p != null) {
            com.huawei.updatesdk.service.otaupdate.d.a().b(this.p);
        }
    }
}
