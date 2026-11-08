package com.xiaomi.clientreport.manager;

import com.xiaomi.clientreport.data.PerfClientReport;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c implements Runnable {
    final /* synthetic */ PerfClientReport a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ a f39a;

    c(a aVar, PerfClientReport perfClientReport) {
        this.f39a = aVar;
        this.a = perfClientReport;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f39a.b(this.a);
    }
}
