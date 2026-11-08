package com.bytedance.push.third.a;

import android.content.Context;
import com.bytedance.push.third.IPushAdapter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends b {

    /* renamed from: a, reason: collision with root package name */
    private Context f4972a;

    public d(Context context) {
        this.f4972a = context;
    }

    @Override // com.bytedance.push.third.a.b
    public boolean a(IPushAdapter iPushAdapter, int i) {
        return iPushAdapter != null && com.ss.android.message.a.b.a(this.f4972a, "com.android.vending") && com.ss.android.message.a.b.a(this.f4972a, "com.google.android.gms");
    }
}
