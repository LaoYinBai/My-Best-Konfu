package com.ss.union.game.sdk.core.vapp.scheme.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class a implements b {
    protected Uri a;
    protected String b;
    protected String c;
    protected String d;
    protected Intent e;

    @Override // com.ss.union.game.sdk.core.vapp.scheme.a.b
    public boolean a(Context context) {
        return true;
    }

    public a(Intent intent) {
        this.e = intent;
        this.a = intent.getData();
        if (this.a != null) {
            this.b = this.a.getHost();
            this.c = this.a.getAuthority();
            this.d = this.a.getPath();
        }
    }
}
