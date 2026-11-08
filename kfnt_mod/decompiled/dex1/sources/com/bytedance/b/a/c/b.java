package com.bytedance.b.a.c;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private final LinkedList<m> f3670a = new LinkedList<>();

    /* renamed from: b, reason: collision with root package name */
    private final LinkedList<c> f3671b = new LinkedList<>();

    /* renamed from: c, reason: collision with root package name */
    private final LinkedList<a> f3672c = new LinkedList<>();
    private int d = 200;
    private boolean e;

    public void a(m mVar) {
        if (mVar == null) {
            return;
        }
        synchronized (this.f3670a) {
            if (this.f3670a.size() > this.d) {
                this.f3670a.poll();
            }
            this.f3670a.add(mVar);
        }
    }

    public void a(c cVar) {
        if (cVar == null) {
            return;
        }
        synchronized (this.f3671b) {
            if (this.f3671b.size() > this.d) {
                this.f3671b.poll();
            }
            this.f3671b.add(cVar);
        }
    }

    public void a(a aVar) {
        if (aVar == null) {
            return;
        }
        synchronized (this.f3672c) {
            if (this.f3672c.size() > this.d) {
                this.f3672c.poll();
            }
            this.f3672c.add(aVar);
        }
    }

    public void a(final k kVar) {
        if (this.e) {
            return;
        }
        this.e = true;
        com.bytedance.b.a.a.d.a.a().a(new Runnable() { // from class: com.bytedance.b.a.c.b.1
            @Override // java.lang.Runnable
            public void run() {
                LinkedList linkedList;
                LinkedList linkedList2;
                LinkedList linkedList3;
                try {
                    synchronized (b.this.f3670a) {
                        linkedList = new LinkedList(b.this.f3670a);
                        b.this.f3670a.clear();
                    }
                    Iterator it = linkedList.iterator();
                    while (it.hasNext()) {
                        b.this.a(kVar, (m) it.next());
                    }
                    synchronized (b.this.f3671b) {
                        linkedList2 = new LinkedList(b.this.f3671b);
                        b.this.f3671b.clear();
                    }
                    Iterator it2 = linkedList2.iterator();
                    while (it2.hasNext()) {
                        b.this.a(kVar, (c) it2.next());
                    }
                    synchronized (b.this.f3672c) {
                        linkedList3 = new LinkedList(b.this.f3672c);
                        b.this.f3672c.clear();
                    }
                    Iterator it3 = linkedList3.iterator();
                    while (it3.hasNext()) {
                        b.this.a(kVar, (a) it3.next());
                    }
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(k kVar, m mVar) {
        if (mVar == null || TextUtils.isEmpty(mVar.f3731a)) {
            return;
        }
        kVar.b(mVar.f3731a, mVar.f3732b, mVar.f3733c, mVar.d, mVar.e, mVar.f, mVar.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(k kVar, c cVar) {
        if (cVar == null) {
            return;
        }
        kVar.b(cVar.f3675a, cVar.f3676b, cVar.f3677c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(k kVar, a aVar) {
        if (aVar == null || TextUtils.isEmpty(aVar.f3667a)) {
            return;
        }
        if (aVar.f3667a.equals(h.s)) {
            kVar.b(aVar.f3668b, aVar.f3669c, aVar.d, aVar.e, aVar.f, aVar.g, aVar.h);
        } else if (aVar.f3667a.equals(h.r)) {
            kVar.a(aVar.f3668b, aVar.f3669c, aVar.d, aVar.e, aVar.f, aVar.g, aVar.h);
        }
    }
}
