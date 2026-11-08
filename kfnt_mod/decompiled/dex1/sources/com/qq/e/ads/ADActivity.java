package com.qq.e.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.qq.e.comm.pi.ACTD;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ADActivity extends Activity {
    public static final String NOTCH_CONTAINER_TAG = "NOTCH_CONTAINER";

    /* renamed from: a, reason: collision with root package name */
    protected ACTD f5479a;

    /* renamed from: b, reason: collision with root package name */
    private FrameLayout f5480b;

    private void a() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        super.setContentView(linearLayout);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setTag(NOTCH_CONTAINER_TAG);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.addView(frameLayout);
        this.f5480b = new FrameLayout(this);
        this.f5480b.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.addView(this.f5480b);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ACTD actd = this.f5479a;
        if (actd != null) {
            actd.onActivityResult(i, i2, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        ACTD actd = this.f5479a;
        if (actd != null) {
            actd.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ACTD actd = this.f5479a;
        if (actd != null) {
            actd.onConfigurationChanged(configuration);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onCreate(android.os.Bundle r5) {
        /*
            r4 = this;
            r0 = 0
            com.qq.e.comm.managers.GDTADManager r1 = com.qq.e.comm.managers.GDTADManager.getInstance()     // Catch: java.lang.Throwable -> L6e
            com.qq.e.comm.managers.plugin.PM r1 = r1.getPM()     // Catch: java.lang.Throwable -> L6e
            com.qq.e.comm.pi.POFactory r1 = r1.getPOFactory()     // Catch: java.lang.Throwable -> L6e
            if (r1 != 0) goto L11
            goto L8a
        L11:
            android.content.Intent r2 = r4.getIntent()     // Catch: java.lang.Throwable -> L6e
            java.lang.Class r3 = r1.getClass()     // Catch: java.lang.Throwable -> L6e
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch: java.lang.Throwable -> L6e
            r2.setExtrasClassLoader(r3)     // Catch: java.lang.Throwable -> L6e
            android.os.Bundle r2 = r2.getExtras()     // Catch: java.lang.Throwable -> L6e
            if (r2 != 0) goto L27
            goto L8a
        L27:
            java.lang.String r3 = "gdt_activity_delegate_name"
            java.lang.String r3 = r2.getString(r3)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r0 = "appid"
            java.lang.String r0 = r2.getString(r0)     // Catch: java.lang.Throwable -> L6c
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L6c
            if (r2 != 0) goto L8a
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L6c
            if (r0 == 0) goto L40
            goto L8a
        L40:
            com.qq.e.comm.managers.GDTADManager r0 = com.qq.e.comm.managers.GDTADManager.getInstance()     // Catch: java.lang.Throwable -> L6c
            boolean r0 = r0.isInitialized()     // Catch: java.lang.Throwable -> L6c
            if (r0 == 0) goto L8a
            com.qq.e.comm.pi.ACTD r0 = r1.getActivityDelegate(r3, r4)     // Catch: java.lang.Throwable -> L6c
            r4.f5479a = r0     // Catch: java.lang.Throwable -> L6c
            if (r0 != 0) goto L8a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6c
            r0.<init>()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r1 = "创建 ADActivity Delegate "
            r0.append(r1)     // Catch: java.lang.Throwable -> L6c
            r0.append(r3)     // Catch: java.lang.Throwable -> L6c
            java.lang.String r1 = " 失败"
            r0.append(r1)     // Catch: java.lang.Throwable -> L6c
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L6c
            com.qq.e.comm.util.GDTLogger.e(r0)     // Catch: java.lang.Throwable -> L6c
            goto L8a
        L6c:
            r0 = move-exception
            goto L71
        L6e:
            r1 = move-exception
            r3 = r0
            r0 = r1
        L71:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "创建ADActivity Delegate"
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = "发生异常"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.qq.e.comm.util.GDTLogger.e(r1, r0)
        L8a:
            com.qq.e.comm.pi.ACTD r0 = r4.f5479a
            if (r0 == 0) goto L92
            r0.onBeforeCreate(r5)
            goto L9c
        L92:
            r4.finish()     // Catch: java.lang.Throwable -> L96
            goto L9c
        L96:
            r0 = move-exception
            java.lang.String r1 = "ADActivity onCreate 发生异常"
            com.qq.e.comm.util.GDTLogger.e(r1, r0)
        L9c:
            super.onCreate(r5)     // Catch: java.lang.Throwable -> La0
            goto La6
        La0:
            r0 = move-exception
            java.lang.String r1 = "ADActivity onCreate 发生异常"
            com.qq.e.comm.util.GDTLogger.e(r1, r0)
        La6:
            com.qq.e.comm.pi.ACTD r0 = r4.f5479a
            if (r0 == 0) goto Lad
            r0.onAfterCreate(r5)
        Lad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.ads.ADActivity.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ACTD actd = this.f5479a;
        if (actd != null) {
            actd.onDestroy();
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        ACTD actd = this.f5479a;
        if (actd != null) {
            actd.onPause();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        ACTD actd = this.f5479a;
        if (actd != null) {
            actd.onResume();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        ACTD actd = this.f5479a;
        if (actd != null) {
            actd.onStop();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        a();
        LayoutInflater.from(this).inflate(i, (ViewGroup) this.f5480b, true);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        a();
        this.f5480b.addView(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a();
        this.f5480b.addView(view, layoutParams);
    }
}
