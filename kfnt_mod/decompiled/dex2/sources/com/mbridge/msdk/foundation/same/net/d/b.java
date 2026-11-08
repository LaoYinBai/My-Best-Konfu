package com.mbridge.msdk.foundation.same.net.d;

import com.mbridge.msdk.foundation.same.net.j;
import com.mbridge.msdk.foundation.same.net.k;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: FileDownloader.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private j a;
    private int b;
    private LinkedList<a> c = new LinkedList<>();

    public b(j jVar, int i) {
        this.a = jVar;
        this.b = i;
    }

    public final a a(File file, String str, com.mbridge.msdk.foundation.same.net.e<Void> eVar) {
        a aVar = new a(file, str, eVar);
        synchronized (this) {
            this.c.add(aVar);
        }
        a();
        return aVar;
    }

    private void a() {
        synchronized (this) {
            int i = 0;
            Iterator<a> it = this.c.iterator();
            while (it.hasNext()) {
                if (it.next().a()) {
                    i++;
                }
            }
            if (i >= this.b) {
                return;
            }
            Iterator<a> it2 = this.c.iterator();
            while (it2.hasNext()) {
                if (a.a(it2.next()) && (i = i + 1) == this.b) {
                    return;
                }
            }
        }
    }

    /* compiled from: FileDownloader.java */
    public class a {
        private String b;
        private File c;
        private com.mbridge.msdk.foundation.same.net.e<Void> d;
        private com.mbridge.msdk.foundation.same.net.d.a e;
        private int f;

        private a(File file, String str, com.mbridge.msdk.foundation.same.net.e<Void> eVar) {
            this.c = file;
            this.d = eVar;
            this.b = str;
        }

        public final boolean a() {
            return this.f == 1;
        }

        static /* synthetic */ boolean a(a aVar) {
            if (aVar.f != 0) {
                return false;
            }
            b bVar = b.this;
            aVar.e = new com.mbridge.msdk.foundation.same.net.d.a(aVar.c, aVar.b);
            aVar.e.a(new com.mbridge.msdk.foundation.same.net.f<Void>() { // from class: com.mbridge.msdk.foundation.same.net.d.b.a.1
                boolean a;

                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void a() {
                    a.this.d.a();
                }

                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void b() {
                    if (this.a) {
                        return;
                    }
                    a.this.f = 3;
                    a.this.d.b();
                    b.a(b.this, a.this);
                }

                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void a(k kVar) {
                    if (this.a) {
                        return;
                    }
                    a.this.d.a(kVar);
                }

                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void a(com.mbridge.msdk.foundation.same.net.a.a aVar2) {
                    if (this.a) {
                        return;
                    }
                    a.this.d.a(aVar2);
                }

                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void c() {
                    a.this.d.c();
                    this.a = true;
                }

                @Override // com.mbridge.msdk.foundation.same.net.f, com.mbridge.msdk.foundation.same.net.e
                public final void a(long j, long j2) {
                    a.this.d.a(j, j2);
                }
            });
            aVar.f = 1;
            b.this.a.a(aVar.e);
            return true;
        }
    }

    static /* synthetic */ void a(b bVar, a aVar) {
        synchronized (bVar) {
            bVar.c.remove(aVar);
        }
        bVar.a();
    }
}
