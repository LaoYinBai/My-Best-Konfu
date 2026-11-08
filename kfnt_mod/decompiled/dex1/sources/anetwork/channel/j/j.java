package anetwork.channel.j;

import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.SessionGetCallback;
import anet.channel.request.Request;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.HttpUrl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class j implements SessionGetCallback {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ RequestStatistic f730a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ long f731b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Request f732c;
    final /* synthetic */ SessionCenter d;
    final /* synthetic */ HttpUrl e;
    final /* synthetic */ boolean f;
    final /* synthetic */ g g;

    j(g gVar, RequestStatistic requestStatistic, long j, Request request, SessionCenter sessionCenter, HttpUrl httpUrl, boolean z) {
        this.g = gVar;
        this.f730a = requestStatistic;
        this.f731b = j;
        this.f732c = request;
        this.d = sessionCenter;
        this.e = httpUrl;
        this.f = z;
    }

    @Override // anet.channel.SessionGetCallback
    public void onSessionGetSuccess(Session session) {
        ALog.i("anet.NetworkTask", "onSessionGetSuccess", this.g.f720a.f738c, "Session", session);
        this.f730a.connWaitTime = System.currentTimeMillis() - this.f731b;
        this.f730a.spdyRequestSend = true;
        this.g.a(session, this.f732c);
    }

    @Override // anet.channel.SessionGetCallback
    public void onSessionGetFail() {
        ALog.e("anet.NetworkTask", "onSessionGetFail", this.g.f720a.f738c, "url", this.f730a.url);
        this.f730a.connWaitTime = System.currentTimeMillis() - this.f731b;
        this.g.a(this.g.a(null, this.d, this.e, this.f), this.f732c);
    }
}
