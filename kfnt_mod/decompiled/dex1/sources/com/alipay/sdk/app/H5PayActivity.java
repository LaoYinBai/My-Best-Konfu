package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.alipay.sdk.packet.e;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.k;
import com.alipay.sdk.widget.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class H5PayActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    private com.alipay.sdk.widget.c f2531a;

    /* renamed from: b, reason: collision with root package name */
    private String f2532b;

    /* renamed from: c, reason: collision with root package name */
    private String f2533c;
    private String d;
    private String e;
    private boolean f;
    private String g;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        b();
        super.onCreate(bundle);
        try {
            com.alipay.sdk.sys.a aVarA = a.C0080a.a(getIntent());
            if (aVarA == null) {
                finish();
                return;
            }
            if (!com.alipay.sdk.data.a.p().b()) {
                setRequestedOrientation(1);
            } else {
                setRequestedOrientation(3);
            }
            try {
                Bundle extras = getIntent().getExtras();
                this.f2532b = extras.getString("url", null);
                if (!k.d(this.f2532b)) {
                    finish();
                    return;
                }
                this.d = extras.getString("cookie", null);
                this.f2533c = extras.getString(e.q, null);
                this.e = extras.getString(d.m, null);
                this.g = extras.getString("version", com.alipay.sdk.widget.c.f2681b);
                this.f = extras.getBoolean("backisexit", false);
                try {
                    d dVar = new d(this, aVarA, this.g);
                    setContentView(dVar);
                    dVar.a(this.e, this.f2533c, this.f);
                    dVar.a(this.f2532b, this.d);
                    dVar.a(this.f2532b);
                    this.f2531a = dVar;
                } catch (Throwable th) {
                    com.alipay.sdk.app.statistic.a.a(aVarA, com.alipay.sdk.app.statistic.b.f2572b, "GetInstalledAppEx", th);
                    finish();
                }
            } catch (Exception unused) {
                finish();
            }
        } catch (Exception unused2) {
            finish();
        }
    }

    private void b() {
        try {
            super.requestWindowFeature(1);
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        com.alipay.sdk.widget.c cVar = this.f2531a;
        if (cVar == null) {
            finish();
            return;
        }
        if (cVar.c()) {
            cVar.b();
            return;
        }
        if (!cVar.b()) {
            super.onBackPressed();
        }
        b.a(b.c());
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        a();
        super.finish();
    }

    public void a() {
        Object obj = PayTask.f2547a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.f2531a != null) {
            this.f2531a.a();
        }
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        try {
            super.setRequestedOrientation(i);
        } catch (Throwable th) {
            try {
                com.alipay.sdk.app.statistic.a.a(a.C0080a.a(getIntent()), com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.v, th);
            } catch (Throwable unused) {
            }
        }
    }
}
