package anet.channel.session;

import anet.channel.RequestCb;
import anet.channel.request.Request;
import anet.channel.session.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Request f498a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ d f499b;

    e(d dVar, Request request) {
        this.f499b = dVar;
        this.f498a = request;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        b.a aVarA = b.a(this.f498a, (RequestCb) null);
        if (aVarA.f494a > 0) {
            this.f499b.notifyStatus(4, new anet.channel.entity.b(1));
        } else {
            this.f499b.handleCallbacks(256, new anet.channel.entity.b(256, aVarA.f494a, "Http connect fail"));
        }
    }
}
