package com.huawei.updatesdk.service.otaupdate;

import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static final Object a = new Object();
    private static d b = null;
    private CheckUpdateCallBack c;

    public static d a() {
        d dVar;
        synchronized (a) {
            if (b == null) {
                b = new d();
            }
            dVar = b;
        }
        return dVar;
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.onMarketStoreError(i);
        }
    }

    public void a(Intent intent) {
        if (this.c != null) {
            this.c.onMarketInstallInfo(intent);
        }
    }

    public void a(CheckUpdateCallBack checkUpdateCallBack) {
        this.c = checkUpdateCallBack;
    }

    public void b(Intent intent) {
        if (this.c != null) {
            this.c.onUpdateInfo(intent);
        }
    }
}
