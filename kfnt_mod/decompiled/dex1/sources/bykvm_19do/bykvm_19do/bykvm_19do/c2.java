package bykvm_19do.bykvm_19do.bykvm_19do;

import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c2 {

    /* renamed from: a, reason: collision with root package name */
    private static final LinkedList<u> f1320a = new LinkedList<>();

    /* renamed from: b, reason: collision with root package name */
    private static final LinkedList<u> f1321b = new LinkedList<>();

    public static void a(u uVar) {
        synchronized (f1320a) {
            if (f1320a.size() > 200) {
                u uVarPoll = f1320a.poll();
                l0.b("drop event in cache", null);
                f1321b.add(uVarPoll);
            }
            f1320a.add(uVar);
        }
    }

    public static void a() {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        synchronized (f1320a) {
            linkedList.addAll(f1320a);
            linkedList2.addAll(f1321b);
            f1320a.clear();
            f1321b.clear();
        }
        while (!linkedList.isEmpty()) {
            u1.a((u) linkedList.poll());
        }
        while (!linkedList2.isEmpty()) {
            u uVar = (u) linkedList2.poll();
            c0 c0Var = null;
            if (uVar instanceof c0) {
                c0Var = (c0) uVar;
            } else if (uVar instanceof w) {
                w wVar = (w) uVar;
                c0Var = new c0("" + wVar.j() + wVar.k(), "16", 1);
            } else if (uVar instanceof y) {
                c0Var = new c0(((y) uVar).k(), "16", 1);
            }
            u1.a(c0Var);
        }
    }
}
