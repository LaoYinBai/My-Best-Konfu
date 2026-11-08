package com.huawei.hms.common.internal;

import android.app.Activity;
import android.content.Intent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DialogRedirectImpl extends DialogRedirect {
    private final Activity a;
    private final int b;
    private final Intent c;

    DialogRedirectImpl(Intent intent, Activity activity, int i) {
        this.c = intent;
        this.a = activity;
        this.b = i;
    }

    @Override // com.huawei.hms.common.internal.DialogRedirect
    public final void redirect() {
        if (this.c != null) {
            this.a.startActivityForResult(this.c, this.b);
        }
    }
}
