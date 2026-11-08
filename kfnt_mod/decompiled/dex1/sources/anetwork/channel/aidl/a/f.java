package anetwork.channel.aidl.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ byte f662a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ Object f663b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ e f664c;

    f(e eVar, byte b2, Object obj) {
        this.f664c = eVar;
        this.f662a = b2;
        this.f663b = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f664c.b(this.f662a, this.f663b);
    }
}
