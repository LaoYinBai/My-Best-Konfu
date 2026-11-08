package com.sigmob.volley.toolbox;

import android.os.Looper;
import com.sigmob.volley.toolbox.f;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g {
    final com.sigmob.volley.m a;
    private final int b;
    private final LinkedList<a> c = new LinkedList<>();

    public class a {
        f.a a;
        int b;
        private f d;
        private d e;

        a(d dVar, f.a aVar) {
            this.a = aVar;
            this.e = dVar;
        }

        boolean a() {
            if (this.b != 0) {
                return false;
            }
            this.d = g.this.b(this.e, new f.a() { // from class: com.sigmob.volley.toolbox.g.a.1
                boolean a;

                @Override // com.sigmob.volley.toolbox.f.a
                public void a(d dVar) {
                    a.this.b = 3;
                    if (a.this.a != null && !this.a) {
                        a.this.a.a(dVar);
                    }
                    g.this.a(a.this);
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void a(d dVar, long j, long j2) {
                    if (a.this.a != null) {
                        a.this.a.a(dVar, j, j2);
                    }
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void b(d dVar) {
                    this.a = true;
                    if (a.this.a != null) {
                        a.this.a.b(dVar);
                    }
                    g.this.a(a.this);
                }

                @Override // com.sigmob.volley.toolbox.f.a
                public void c(d dVar) {
                    if (a.this.a != null && !this.a) {
                        a.this.a.c(dVar);
                    }
                    g.this.a(a.this);
                }
            });
            this.b = 1;
            if (g.this.a == null) {
                return false;
            }
            g.this.a.a(this.d);
            return true;
        }

        public boolean b() {
            return this.b == 1;
        }

        public String c() {
            return this.e.a;
        }

        public String d() {
            return this.e.c;
        }

        public f.a e() {
            return this.a;
        }

        public d f() {
            return this.e;
        }

        public boolean g() {
            if (this.b == 0) {
                this.b = 4;
                g.this.a(this);
                if (this.a != null) {
                    this.a.b(this.e);
                }
                return true;
            }
            if (this.b == 4 || this.b == 3) {
                return false;
            }
            if (this.b == 1) {
                this.d.j();
            }
            this.b = 4;
            g.this.a(this);
            return true;
        }
    }

    public g(com.sigmob.volley.m mVar, int i) {
        this.b = i;
        this.a = mVar;
    }

    private void d() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("FileDownloader must be invoked from the main thread.");
        }
    }

    public a a(d dVar, f.a aVar) {
        d();
        a aVarA = a(dVar.c, dVar.a);
        if (aVarA == null) {
            aVarA = new a(dVar, aVar);
            synchronized (this.c) {
                this.c.add(aVarA);
            }
            a();
        }
        return aVarA;
    }

    public a a(String str, String str2) {
        synchronized (this.c) {
            Iterator<a> it = this.c.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next.d().equals(str) && next.c().equals(str2)) {
                    return next;
                }
            }
            return null;
        }
    }

    void a() {
        synchronized (this.c) {
            int i = 0;
            Iterator<a> it = this.c.iterator();
            while (it.hasNext()) {
                if (it.next().b()) {
                    i++;
                }
            }
            if (i >= this.b) {
                return;
            }
            Iterator<a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                if (it2.next().a() && (i = i + 1) == this.b) {
                    return;
                }
            }
        }
    }

    void a(a aVar) {
        synchronized (this.c) {
            this.c.remove(aVar);
        }
        a();
    }

    public f b(d dVar, f.a aVar) {
        return new f(dVar, aVar);
    }

    public void b() {
        synchronized (this.c) {
            while (this.c.size() > 0) {
                this.c.get(0).g();
            }
        }
    }

    public List<a> c() {
        LinkedList<a> linkedList;
        synchronized (this.c) {
            linkedList = this.c;
        }
        return linkedList;
    }
}
