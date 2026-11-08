package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class v implements Runnable {
    final /* synthetic */ Context a;

    v(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            PackageInfo packageInfo = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 4612);
            u.c(this.a);
            u.d(this.a, packageInfo);
            u.c(this.a, packageInfo);
        } catch (Throwable th) {
            Log.e("ManifestChecker", "", th);
        }
    }
}
