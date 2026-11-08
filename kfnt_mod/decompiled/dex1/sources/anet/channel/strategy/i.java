package anet.channel.strategy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f566a;

    i(g gVar) {
        this.f566a = gVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f566a.a()) {
            return;
        }
        this.f566a.f563b.c();
    }
}
