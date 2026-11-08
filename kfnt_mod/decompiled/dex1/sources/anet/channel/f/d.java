package anet.channel.f;

import anet.channel.Session;
import anet.channel.entity.EventCb;
import anet.channel.strategy.ConnEvent;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d implements EventCb {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ IConnStrategy f444a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f445b;

    d(c cVar, IConnStrategy iConnStrategy) {
        this.f445b = cVar;
        this.f444a = iConnStrategy;
    }

    @Override // anet.channel.entity.EventCb
    public void onEvent(Session session, int i, anet.channel.entity.b bVar) {
        ConnEvent connEvent = new ConnEvent();
        if (i == 1) {
            connEvent.isSuccess = true;
        }
        StrategyCenter.getInstance().notifyConnEvent(a.f440a, this.f444a, connEvent);
        session.close(false);
    }
}
