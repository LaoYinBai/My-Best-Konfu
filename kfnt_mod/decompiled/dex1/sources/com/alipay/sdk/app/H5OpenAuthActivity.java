package com.alipay.sdk.app;

import android.content.Intent;
import android.net.Uri;
import com.alipay.sdk.sys.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class H5OpenAuthActivity extends H5PayActivity {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2530a = false;

    @Override // com.alipay.sdk.app.H5PayActivity
    public void a() {
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            com.alipay.sdk.sys.a aVarA = a.C0080a.a(intent);
            if (aVarA == null) {
                finish();
                return;
            }
            try {
                super.startActivity(intent);
                Uri data = intent != null ? intent.getData() : null;
                if (data == null || !data.toString().startsWith("alipays://platformapi/startapp")) {
                    return;
                }
                finish();
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(aVarA, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.ak, th, (intent == null || intent.getData() == null) ? "null" : intent.getData().toString());
                this.f2530a = true;
                throw th;
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    @Override // com.alipay.sdk.app.H5PayActivity, android.app.Activity
    protected void onDestroy() {
        if (this.f2530a) {
            try {
                com.alipay.sdk.sys.a aVarA = a.C0080a.a(getIntent());
                if (aVarA != null) {
                    com.alipay.sdk.app.statistic.a.b(this, aVarA, "", aVarA.q);
                }
            } catch (Throwable unused) {
            }
        }
        super.onDestroy();
    }
}
