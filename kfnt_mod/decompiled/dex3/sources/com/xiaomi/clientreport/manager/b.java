package com.xiaomi.clientreport.manager;

import com.xiaomi.clientreport.data.EventClientReport;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b implements Runnable {
    final /* synthetic */ EventClientReport a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ a f38a;

    b(a aVar, EventClientReport eventClientReport) {
        this.f38a = aVar;
        this.a = eventClientReport;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f38a.b(this.a);
    }
}
