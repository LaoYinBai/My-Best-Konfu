package anet.channel.strategy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f558a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ StrategyInfoHolder f559b;

    e(StrategyInfoHolder strategyInfoHolder, String str) {
        this.f559b = strategyInfoHolder;
        this.f558a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f559b.a(this.f558a, true);
    }
}
