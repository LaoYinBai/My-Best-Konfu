package anet.channel;

import anet.channel.entity.EventCb;
import anet.channel.strategy.ConnEvent;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f implements EventCb {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Session f438a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ SessionRequest f439b;

    f(SessionRequest sessionRequest, Session session) {
        this.f439b = sessionRequest;
        this.f438a = session;
    }

    @Override // anet.channel.entity.EventCb
    public void onEvent(Session session, int i, anet.channel.entity.b bVar) {
        ALog.d("awcn.SessionRequest", "Receive session event", null, "eventType", Integer.valueOf(i));
        ConnEvent connEvent = new ConnEvent();
        if (i == 512) {
            connEvent.isSuccess = true;
        }
        StrategyCenter.getInstance().notifyConnEvent(this.f438a.getRealHost(), this.f438a.getConnStrategy(), connEvent);
    }
}
