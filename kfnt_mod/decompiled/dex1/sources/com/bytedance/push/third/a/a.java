package com.bytedance.push.third.a;

import android.content.Context;
import com.bytedance.push.third.IPushAdapter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends b {

    /* renamed from: a, reason: collision with root package name */
    private Context f4970a;

    public a(Context context) {
        this.f4970a = context;
    }

    @Override // com.bytedance.push.third.a.b
    public boolean a(IPushAdapter iPushAdapter, int i) {
        return iPushAdapter != null && iPushAdapter.isPushAvailable(this.f4970a, i);
    }
}
