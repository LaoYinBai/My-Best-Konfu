package anet.channel;

import anet.channel.SessionRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Session f447a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ SessionRequest.a f448b;

    g(SessionRequest.a aVar, Session session) {
        this.f448b = aVar;
        this.f447a = session;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            SessionRequest.this.a(this.f448b.f386c, this.f447a.getConnType().getType(), anet.channel.util.i.a(SessionRequest.this.f381a.f378c), (SessionGetCallback) null, 0L);
        } catch (Exception unused) {
        }
    }
}
