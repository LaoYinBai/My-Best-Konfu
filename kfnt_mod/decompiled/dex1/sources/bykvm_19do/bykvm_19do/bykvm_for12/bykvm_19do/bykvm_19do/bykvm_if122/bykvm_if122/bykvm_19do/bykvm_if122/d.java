package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122;

import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.c0;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private final Set<c0> f1553a = new LinkedHashSet();

    public synchronized void a(c0 c0Var) {
        this.f1553a.remove(c0Var);
    }

    public synchronized void b(c0 c0Var) {
        this.f1553a.add(c0Var);
    }

    public synchronized boolean c(c0 c0Var) {
        return this.f1553a.contains(c0Var);
    }
}
