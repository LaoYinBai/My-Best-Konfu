package com.yxcorp.kuaishou.addfp.a.b.b;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class c implements com.yxcorp.kuaishou.addfp.a.b.a, com.yxcorp.kuaishou.addfp.a.b.b {
    private Context a;
    private String b = "";
    private String c = "";
    private String d = "";
    private boolean e = false;
    private com.yxcorp.kuaishou.addfp.a.a.a f;
    private a g;

    public c(Context context, com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        this.a = context;
        this.f = aVar;
        this.g = new a(context);
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void a(com.yxcorp.kuaishou.addfp.a.a.a aVar) {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                String string = Settings.Global.getString(this.a.getContentResolver(), "pps_oaid");
                if (!TextUtils.isEmpty(string)) {
                    this.b = string;
                    this.e = true;
                    if (this.f != null) {
                        this.f.a(this.e, this);
                    }
                }
            } catch (Throwable unused) {
            }
        }
        this.g.a(this);
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.b
    public final void a(String str) {
        try {
            if (TextUtils.isEmpty(str) || !str.equals(this.b)) {
                this.b = str;
                if (TextUtils.isEmpty(this.b)) {
                    if (this.f != null) {
                        this.f.a(false, null);
                    }
                } else {
                    this.e = true;
                    if (this.f != null) {
                        this.f.a(this.e, this);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean a() {
        return false;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return null;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final String b() {
        return this.b;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final boolean c() {
        return this.e;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.a
    public final void d() {
        if (this.g != null) {
            this.g.a();
        }
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.b
    public final void e() {
        if (this.f != null) {
            this.f.a(false, this);
        }
    }
}
