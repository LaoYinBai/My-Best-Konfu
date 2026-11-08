package b.b.a.k;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class q implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f1142a;

    public q(Context context) {
        this.f1142a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        p.f1141b.b(this.f1142a).edit().putBoolean("_install_started_v2", true).apply();
    }
}
