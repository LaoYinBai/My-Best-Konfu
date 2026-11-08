package com.vivo.push.sdk.service;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.vivo.push.util.n;
import com.vivo.push.util.y;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LinkProxyActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        PackageManager packageManager;
        List<ResolveInfo> listQueryIntentServices;
        ResolveInfo resolveInfo;
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            n.d("LinkProxyActivity", "enter RequestPermissionsActivity onCreate, intent is null, finish");
            finish();
            return;
        }
        boolean z = false;
        try {
            Window window = getWindow();
            window.setGravity(8388659);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = 0;
            attributes.y = 0;
            attributes.height = 1;
            attributes.width = 1;
            window.setAttributes(attributes);
        } catch (Throwable th) {
            n.b("LinkProxyActivity", "enter onCreate error ", th);
        }
        String packageName = getPackageName();
        n.d("LinkProxyActivity", hashCode() + " enter onCreate " + packageName);
        if ("com.vivo.abe".equals(packageName)) {
            try {
                if (intent == null) {
                    n.d("LinkProxyActivity", "adapterToService intent is null");
                } else if (intent.getExtras() == null) {
                    n.d("LinkProxyActivity", "adapterToService getExtras() is null");
                } else {
                    Intent intent2 = (Intent) intent.getExtras().get("previous_intent");
                    if (intent2 == null) {
                        n.d("LinkProxyActivity", "adapterToService proxyIntent is null");
                    } else {
                        y.a(this, intent2);
                    }
                }
            } catch (Exception e) {
                n.a("LinkProxyActivity", e.toString(), e);
            }
        } else {
            try {
                if (intent.getExtras() != null) {
                    Intent intent3 = (Intent) intent.getExtras().get("previous_intent");
                    if (intent3 != null && (packageManager = getPackageManager()) != null && (listQueryIntentServices = packageManager.queryIntentServices(intent3, 576)) != null && !listQueryIntentServices.isEmpty() && (resolveInfo = listQueryIntentServices.get(0)) != null && resolveInfo.serviceInfo != null && resolveInfo.serviceInfo.exported) {
                        z = true;
                    }
                    if (z) {
                        startService(intent3);
                    } else {
                        n.b("LinkProxyActivity", "service's exported is " + z);
                    }
                }
            } catch (Exception e2) {
                n.a("LinkProxyActivity", e2.toString(), e2);
            }
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        n.d("LinkProxyActivity", hashCode() + " onDestory " + getPackageName());
    }
}
