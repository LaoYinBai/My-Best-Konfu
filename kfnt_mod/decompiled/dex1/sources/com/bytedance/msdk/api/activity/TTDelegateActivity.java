package com.bytedance.msdk.api.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.e;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.f;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.c;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.n;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.v2.GMAdConstant;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTDelegateActivity extends Activity {
    public static final String INTENT_PERMISSIONS = "permissions";
    public static final String INTENT_TYPE = "type";
    public static final int INTENT_TYPE_ALL_PERMISSIONS_CHECK = 3;
    public static final int INTENT_TYPE_PERMISSIONS_CHECK = 1;
    public static final int INTENT_TYPE_REQUEST_PERMISSION = 2;
    public static final String PERMISSION_CONTENT_KEY = "permission_content_key";
    public static final String PERMISSION_ID_KEY = "permission_id_key";

    /* renamed from: a, reason: collision with root package name */
    ExecutorService f4309a = Executors.newSingleThreadExecutor();

    /* renamed from: b, reason: collision with root package name */
    private Intent f4310b;

    public static class updateDeviceInfoRunnable implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            n.t(a.d());
        }
    }

    private void a() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
    }

    private void b() {
        try {
            int intExtra = this.f4310b.getIntExtra("type", 0);
            if (intExtra == 1) {
                a(intExtra, this.f4310b.getIntArrayExtra(INTENT_PERMISSIONS));
            } else if (intExtra == 2) {
                a(this.f4310b.getStringExtra(PERMISSION_ID_KEY), this.f4310b.getStringArrayExtra(PERMISSION_CONTENT_KEY));
            } else if (intExtra != 3) {
                finish();
            } else {
                a(intExtra, (int[]) null);
            }
        } catch (Exception unused) {
            finish();
        }
    }

    public static void requestPermission(String str, String[] strArr) {
        Intent intent = new Intent(a.d(), (Class<?>) TTDelegateActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("type", 2);
        intent.putExtra(PERMISSION_ID_KEY, str);
        intent.putExtra(PERMISSION_CONTENT_KEY, strArr);
        if (a.d() != null) {
            c.a(a.d(), intent, new c.a() { // from class: com.bytedance.msdk.api.activity.TTDelegateActivity.1
                @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.c.a
                public void startActivityFail(Throwable th) {
                    Logger.e("requestPermission->startActivity error :" + th.toString());
                }

                @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.c.a
                public void startActivitySuccess() {
                }
            });
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        a();
        this.f4310b = getIntent();
        if (a.d() == null) {
            a.b(this);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (a.d() == null) {
            a.b(this);
        }
        try {
            setIntent(intent);
            this.f4310b = intent;
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        e.a().a(this, strArr, iArr);
        this.f4309a.execute(new updateDeviceInfoRunnable());
        finish();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (getIntent() != null) {
            b();
        }
    }

    private void a(final String str, String[] strArr) {
        if (!TextUtils.isEmpty(str) && strArr != null && strArr.length > 0) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    e.a().a(this, strArr, new f() { // from class: com.bytedance.msdk.api.activity.TTDelegateActivity.2
                        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.f
                        public void onDenied(String str2) {
                            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.a.a(str, str2);
                            TTDelegateActivity.this.finish();
                        }

                        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.f
                        public void onGranted() {
                            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.a.a(str);
                            TTDelegateActivity.this.finish();
                        }
                    });
                    return;
                } catch (Exception unused) {
                    finish();
                    return;
                }
            } else {
                Logger.d(GMAdConstant.TAG, "已经有权限");
                finish();
                return;
            }
        }
        finish();
    }

    private void a(int i, int[] iArr) {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                ArrayList arrayList = new ArrayList();
                if (i != 1) {
                    if (i == 3) {
                        arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
                        arrayList.add("android.permission.ACCESS_FINE_LOCATION");
                        arrayList.add("android.permission.READ_PHONE_STATE");
                        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
                    }
                } else if (iArr != null && iArr.length > 0) {
                    for (int i2 : iArr) {
                        if (i2 == 1) {
                            arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
                            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
                        } else if (i2 == 2) {
                            arrayList.add("android.permission.READ_PHONE_STATE");
                        } else if (i2 == 3) {
                            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
                        }
                    }
                }
                String[] strArr = new String[arrayList.size()];
                arrayList.toArray(strArr);
                e.a().a(this, strArr, new f() { // from class: com.bytedance.msdk.api.activity.TTDelegateActivity.3
                    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.f
                    public void onDenied(String str) {
                        Logger.d("TMe", "-------=----- onDenied: " + str);
                        "android.permission.READ_PHONE_STATE".equals(str);
                        TTDelegateActivity.this.f4309a.execute(new updateDeviceInfoRunnable());
                        TTDelegateActivity.this.finish();
                    }

                    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.f
                    public void onGranted() {
                        Logger.d("TMe", "-------=----- onGranted");
                        TTDelegateActivity.this.f4309a.execute(new updateDeviceInfoRunnable());
                        TTDelegateActivity.this.finish();
                    }
                });
                return;
            } catch (Exception unused) {
                finish();
                return;
            }
        }
        Logger.d(GMAdConstant.TAG, "已经有Read phone state权限");
        finish();
    }
}
