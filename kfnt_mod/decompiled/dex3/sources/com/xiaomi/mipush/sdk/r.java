package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.dk;
import com.xiaomi.push.hj;
import com.xiaomi.push.hw;
import com.xiaomi.push.ii;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class r implements dk {
    private Context a;

    public r(Context context) {
        this.a = context;
    }

    @Override // com.xiaomi.push.dk
    public String a() {
        return b.m63a(this.a).d();
    }

    @Override // com.xiaomi.push.dk
    public void a(ii iiVar, hj hjVar, hw hwVar) {
        an.a(this.a).a((an) iiVar, hjVar, hwVar);
    }
}
