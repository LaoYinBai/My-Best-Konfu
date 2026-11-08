package anet.channel;

import anet.channel.entity.EventCb;
import anet.channel.util.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f392a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ anet.channel.entity.b f393b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Session f394c;

    a(Session session, int i, anet.channel.entity.b bVar) {
        this.f394c = session;
        this.f392a = i;
        this.f393b = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.f394c.f373b != null) {
                for (EventCb eventCb : this.f394c.f373b.keySet()) {
                    if (eventCb != null && (this.f394c.f373b.get(eventCb).intValue() & this.f392a) != 0) {
                        try {
                            eventCb.onEvent(this.f394c, this.f392a, this.f393b);
                        } catch (Exception e) {
                            ALog.e("awcn.Session", e.toString(), this.f394c.p, new Object[0]);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            ALog.e("awcn.Session", "handleCallbacks", this.f394c.p, e2, new Object[0]);
        }
    }
}
