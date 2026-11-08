package com.huawei.hms.push.a;

import android.content.Context;
import android.content.Intent;
import java.util.concurrent.Callable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements Callable<Void> {
    private Context a;
    private Intent b;
    private String c;

    public b(Context context, Intent intent, String str) {
        this.a = context;
        this.b = intent;
        this.c = str;
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        this.a.sendBroadcast(this.b);
        com.huawei.hms.push.utils.b.a(this.a, "push.setNotifyFlag", this.c, com.huawei.hms.push.constant.a.SUCCESS);
        return null;
    }
}
