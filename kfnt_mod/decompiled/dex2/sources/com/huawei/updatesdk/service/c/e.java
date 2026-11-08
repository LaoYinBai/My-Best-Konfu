package com.huawei.updatesdk.service.c;

import android.content.Context;
import com.huawei.updatesdk.service.c.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements Runnable {
    private b a;
    private Context b;

    public e(Context context, b bVar) {
        this.b = context;
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.huawei.updatesdk.a.a.b.a.a.a.a("PackageManagerRunnable", "PackageManagerRunnable run!!!!" + this.a.toString());
        if (this.a.e() == c.b.INSTALL) {
            a.a(this.b, this.a);
        }
    }
}
