package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.al;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class gv extends al.b {
    final /* synthetic */ Context a;

    gv(Context context) {
        this.a = context;
    }

    @Override // com.xiaomi.push.al.b
    public void b() {
        ArrayList arrayList;
        synchronized (gu.f443a) {
            arrayList = new ArrayList(gu.f445a);
            gu.f445a.clear();
        }
        gu.b(this.a, arrayList);
    }
}
