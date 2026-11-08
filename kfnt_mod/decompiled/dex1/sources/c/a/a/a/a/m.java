package c.a.a.a.a;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m extends Thread {

    /* renamed from: a, reason: collision with root package name */
    s f2106a;

    /* renamed from: b, reason: collision with root package name */
    p f2107b;

    public m(@NonNull s sVar, @NonNull p pVar) {
        this.f2106a = sVar;
        this.f2107b = pVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            this.f2106a.b();
            this.f2107b.a(this.f2106a.a());
        } catch (Exception e) {
            this.f2107b.a(e);
        }
    }
}
