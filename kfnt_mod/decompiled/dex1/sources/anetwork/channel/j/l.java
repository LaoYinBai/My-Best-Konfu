package anetwork.channel.j;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class l {

    /* renamed from: a, reason: collision with root package name */
    public final anetwork.channel.entity.h f736a;

    /* renamed from: b, reason: collision with root package name */
    public anetwork.channel.f.a f737b;

    /* renamed from: c, reason: collision with root package name */
    public final String f738c;
    public volatile AtomicBoolean d = new AtomicBoolean();
    public volatile a e = null;
    public volatile Future f = null;

    public l(anetwork.channel.entity.h hVar, anetwork.channel.f.a aVar) {
        this.f736a = hVar;
        this.f738c = hVar.e;
        this.f737b = aVar;
    }

    public void a() {
        Future future = this.f;
        if (future != null) {
            future.cancel(true);
            this.f = null;
        }
    }

    public void b() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
    }
}
