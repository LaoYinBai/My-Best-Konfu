package com.bytedance.sdk.permission.dynamic_permission.ui;

import a.a.a.a.a.a.a;
import a.a.a.a.a.a.c;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.bytedance.sdk.permission.dynamic_permission.in.IDynamicPermission;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DynamicPermissionDelegateActivity extends Activity {
    public static void a(Context context) {
        Intent intent = new Intent(context, (Class<?>) DynamicPermissionDelegateActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        String[] strArrA;
        super.onCreate(bundle);
        IDynamicPermission iDynamicPermission = a.C0000a.f319a.f318a;
        if (!(iDynamicPermission instanceof c) || (strArrA = ((c) iDynamicPermission).f320a.a()) == null || a.a.a.a.a.c.a.a()) {
            finish();
        } else {
            requestPermissions(strArrA, 9999);
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (9999 == i) {
            IDynamicPermission iDynamicPermission = a.C0000a.f319a.f318a;
            if (iDynamicPermission instanceof c) {
                ((c) iDynamicPermission).f320a.a(this, strArr, iArr);
            }
            finish();
        }
    }
}
