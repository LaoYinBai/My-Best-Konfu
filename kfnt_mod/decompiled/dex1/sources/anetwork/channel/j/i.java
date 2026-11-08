package anetwork.channel.j;

import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.HttpUrl;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginConfig;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ SessionCenter f727a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ HttpUrl f728b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ RequestStatistic f729c;
    final /* synthetic */ HttpUrl d;
    final /* synthetic */ boolean e;
    final /* synthetic */ g f;

    i(g gVar, SessionCenter sessionCenter, HttpUrl httpUrl, RequestStatistic requestStatistic, HttpUrl httpUrl2, boolean z) {
        this.f = gVar;
        this.f727a = sessionCenter;
        this.f728b = httpUrl;
        this.f729c = requestStatistic;
        this.d = httpUrl2;
        this.e = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Session session = this.f727a.get(this.f728b, anet.channel.entity.c.f435a, OnekeyLoginConfig.OVER_TIME);
        this.f729c.connWaitTime = System.currentTimeMillis() - jCurrentTimeMillis;
        this.f729c.spdyRequestSend = session != null;
        this.f.a(this.f.a(session, this.f727a, this.d, this.e), this.f.f720a.f736a.a());
    }
}
