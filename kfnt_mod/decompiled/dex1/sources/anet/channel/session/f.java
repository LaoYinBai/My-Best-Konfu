package anet.channel.session;

import anet.channel.RequestCb;
import anet.channel.request.Request;
import anet.channel.statist.RequestStatistic;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Request f500a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ RequestCb f501b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ RequestStatistic f502c;
    final /* synthetic */ d d;

    f(d dVar, Request request, RequestCb requestCb, RequestStatistic requestStatistic) {
        this.d = dVar;
        this.f500a = request;
        this.f501b = requestCb;
        this.f502c = requestStatistic;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        this.f500a.f475a.sendBeforeTime = System.currentTimeMillis() - this.f500a.f475a.reqStart;
        b.a(this.f500a, new g(this));
    }
}
