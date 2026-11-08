package com.bytedance.a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.a.bm;
import com.bytedance.a.da;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class dy<SERVICE> implements bm {

    /* renamed from: a, reason: collision with root package name */
    public final String f3518a;

    /* renamed from: b, reason: collision with root package name */
    public dt<Boolean> f3519b = new a();

    public class a extends dt<Boolean> {
        public a() {
        }

        @Override // com.bytedance.a.dt
        public Boolean a(Object[] objArr) {
            return Boolean.valueOf(m.a((Context) objArr[0], dy.this.f3518a));
        }
    }

    public dy(String str) {
        this.f3518a = str;
    }

    @Override // com.bytedance.a.bm
    public bm.a a(Context context) {
        String str = (String) new da(context, b(context), a()).a();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        bm.a aVar = new bm.a();
        aVar.f3415b = str;
        return aVar;
    }

    public abstract da.b<SERVICE, String> a();

    public abstract Intent b(Context context);

    @Override // com.bytedance.a.bm
    public boolean c(Context context) {
        if (context == null) {
            return false;
        }
        return this.f3519b.b(context).booleanValue();
    }
}
