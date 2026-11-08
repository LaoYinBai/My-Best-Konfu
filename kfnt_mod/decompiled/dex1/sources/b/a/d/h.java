package b.a.d;

import b.af;
import b.x;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class h extends af {

    /* renamed from: a, reason: collision with root package name */
    @Nullable
    private final String f827a;

    /* renamed from: b, reason: collision with root package name */
    private final long f828b;

    /* renamed from: c, reason: collision with root package name */
    private final c.e f829c;

    public h(@Nullable String str, long j, c.e eVar) {
        this.f827a = str;
        this.f828b = j;
        this.f829c = eVar;
    }

    @Override // b.af
    public x a() {
        if (this.f827a != null) {
            return x.a(this.f827a);
        }
        return null;
    }

    @Override // b.af
    public long b() {
        return this.f828b;
    }

    @Override // b.af
    public c.e c() {
        return this.f829c;
    }
}
