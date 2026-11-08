package b.b.a.b;

import b.b.a.b.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1040a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e f1041b;

    public c(e eVar, int i) {
        this.f1041b = eVar;
        this.f1040a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        a aVar = this.f1041b.e;
        if (aVar != null) {
            ((b.a) aVar).a(this.f1040a);
        }
    }
}
