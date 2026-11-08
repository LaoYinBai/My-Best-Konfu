package com.xiaomi.push;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class cw extends cq {
    cq a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ cu f214a;
    final /* synthetic */ cq b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    cw(cu cuVar, String str, cq cqVar) {
        super(str);
        this.f214a = cuVar;
        this.b = cqVar;
        this.a = this.b;
        this.f200b = this.f200b;
        if (this.b != null) {
            this.f = this.b.f;
        }
    }

    @Override // com.xiaomi.push.cq
    public synchronized ArrayList<String> a(boolean z) {
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        if (this.a != null) {
            arrayList.addAll(this.a.a(true));
        }
        synchronized (cu.b) {
            cq cqVar = cu.b.get(this.f200b);
            if (cqVar != null) {
                Iterator<String> it = cqVar.a(true).iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (arrayList.indexOf(next) == -1) {
                        arrayList.add(next);
                    }
                }
                arrayList.remove(this.f200b);
                arrayList.add(this.f200b);
            }
        }
        return arrayList;
    }

    @Override // com.xiaomi.push.cq
    public synchronized void a(String str, cp cpVar) {
        if (this.a != null) {
            this.a.a(str, cpVar);
        }
    }

    @Override // com.xiaomi.push.cq
    public boolean b() {
        return false;
    }
}
