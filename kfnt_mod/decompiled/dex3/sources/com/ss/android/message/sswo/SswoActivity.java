package com.ss.android.message.sswo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SswoActivity extends Activity {
    private static WeakReference<SswoActivity> a;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("SswoActivity", "onCreate");
        }
        b();
        a = new WeakReference<>(this);
    }

    private void b() {
        try {
            Window window = getWindow();
            window.setGravity(51);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = 35;
            attributes.y = 0;
            attributes.height = 1;
            attributes.width = 1;
            window.setAttributes(attributes);
        } catch (Throwable th) {
            com.bytedance.common.push.utility.a.e("SswoActivity", th.getMessage(), th);
        }
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent(context, (Class<?>) SswoActivity.class);
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            context.startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(Context context) {
        SswoActivity sswoActivity;
        try {
            if (a == null || (sswoActivity = a.get()) == null || sswoActivity.isFinishing()) {
                return;
            }
            sswoActivity.finish();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean a() {
        SswoActivity sswoActivity;
        try {
            if (a == null || (sswoActivity = a.get()) == null) {
                return true;
            }
            return sswoActivity.isFinishing();
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        if (a.a(this).e() || isFinishing()) {
            return;
        }
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("SswoActivity", "onDestroy");
        }
        if (a == null || a.get() != this) {
            return;
        }
        a.clear();
        a = null;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!isFinishing()) {
            finish();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isFinishing()) {
            finish();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
