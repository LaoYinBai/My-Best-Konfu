package anet.channel.d;

import anet.channel.Session;
import anet.channel.entity.EventCb;
import anet.channel.request.Request;
import anet.channel.session.TnetSpdySession;
import anet.channel.statist.HorseRaceStat;
import anet.channel.strategy.l;
import anet.channel.util.ALog;
import anet.channel.util.HttpUrl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class e implements EventCb {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ HorseRaceStat f420a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ long f421b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f422c;
    final /* synthetic */ l.e d;
    final /* synthetic */ TnetSpdySession e;

    e(HorseRaceStat horseRaceStat, long j, String str, l.e eVar, TnetSpdySession tnetSpdySession) {
        this.f420a = horseRaceStat;
        this.f421b = j;
        this.f422c = str;
        this.d = eVar;
        this.e = tnetSpdySession;
    }

    @Override // anet.channel.entity.EventCb
    public void onEvent(Session session, int i, anet.channel.entity.b bVar) {
        if (this.f420a.connTime != 0) {
            return;
        }
        this.f420a.connTime = System.currentTimeMillis() - this.f421b;
        if (i == 1) {
            ALog.i("awcn.NetworkDetector", "tnetSpdySession connect success", this.f422c, new Object[0]);
            this.f420a.connRet = 1;
            HttpUrl httpUrl = HttpUrl.parse(session.getHost() + this.d.f584c);
            if (httpUrl == null) {
                return;
            }
            this.e.request(new Request.Builder().setUrl(httpUrl).setReadTimeout(this.d.f583b.d).setRedirectEnable(false).setSeq(this.f422c).build(), new f(this));
            return;
        }
        this.f420a.connErrorCode = bVar.f433b;
        synchronized (this.f420a) {
            this.f420a.notify();
        }
    }
}
