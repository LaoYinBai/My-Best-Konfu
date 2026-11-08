package anet.channel.session;

import anet.channel.strategy.ConnEvent;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class h implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ TnetSpdySession f504a;

    h(TnetSpdySession tnetSpdySession) {
        this.f504a = tnetSpdySession;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f504a.y) {
            ALog.e("awcn.TnetSpdySession", "send msg time out!", this.f504a.p, "pingUnRcv:", Boolean.valueOf(this.f504a.y));
            try {
                this.f504a.handleCallbacks(2048, null);
                if (this.f504a.q != null) {
                    this.f504a.q.closeReason = "ping time out";
                }
                ConnEvent connEvent = new ConnEvent();
                connEvent.isSuccess = false;
                StrategyCenter.getInstance().notifyConnEvent(this.f504a.d, this.f504a.k, connEvent);
                this.f504a.close(true);
            } catch (Exception unused) {
            }
        }
    }
}
