package b.a.c;

import b.ag;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private final Set<ag> f802a = new LinkedHashSet();

    public synchronized void a(ag agVar) {
        this.f802a.add(agVar);
    }

    public synchronized void b(ag agVar) {
        this.f802a.remove(agVar);
    }

    public synchronized boolean c(ag agVar) {
        return this.f802a.contains(agVar);
    }
}
