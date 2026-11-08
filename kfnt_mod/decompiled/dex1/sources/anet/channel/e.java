package anet.channel;

import anet.channel.SessionRequest;
import anet.channel.entity.EventCb;
import anet.channel.util.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e implements EventCb {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ SessionRequest.IConnCb f426a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ long f427b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ SessionRequest f428c;

    e(SessionRequest sessionRequest, SessionRequest.IConnCb iConnCb, long j) {
        this.f428c = sessionRequest;
        this.f426a = iConnCb;
        this.f427b = j;
    }

    @Override // anet.channel.entity.EventCb
    public void onEvent(Session session, int i, anet.channel.entity.b bVar) {
        if (session == null) {
            return;
        }
        int i2 = bVar == null ? 0 : bVar.f433b;
        String str = bVar == null ? "" : bVar.f434c;
        if (i == 2) {
            ALog.d("awcn.SessionRequest", null, session != null ? session.p : null, "Session", session, "EventType", Integer.valueOf(i), "Event", bVar);
            this.f428c.a(session, i2, str);
            if (this.f428c.f382b.c(this.f428c, session)) {
                this.f426a.onDisConnect(session, this.f427b, i);
                return;
            } else {
                this.f426a.onFailed(session, this.f427b, i, i2);
                return;
            }
        }
        if (i == 256) {
            ALog.d("awcn.SessionRequest", null, session != null ? session.p : null, "Session", session, "EventType", Integer.valueOf(i), "Event", bVar);
            this.f428c.a(session, i2, str);
            this.f426a.onFailed(session, this.f427b, i, i2);
        } else {
            if (i != 512) {
                return;
            }
            ALog.d("awcn.SessionRequest", null, session != null ? session.p : null, "Session", session, "EventType", Integer.valueOf(i), "Event", bVar);
            this.f428c.a(session, 0, (String) null);
            this.f426a.onSuccess(session, this.f427b);
        }
    }
}
