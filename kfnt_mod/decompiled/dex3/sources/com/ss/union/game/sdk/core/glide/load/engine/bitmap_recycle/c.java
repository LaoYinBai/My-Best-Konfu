package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c<K extends e, V> {
    private final a<K, V> a = new a<>();
    private final Map<K, a<K, V>> b = new HashMap();

    c() {
    }

    public void a(K k, V v) {
        a<K, V> aVar = this.b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            b(aVar);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        aVar.a(v);
    }

    public V a(K k) {
        a<K, V> aVar = this.b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        a(aVar);
        return aVar.a();
    }

    public V a() {
        for (a aVar = this.a.c; !aVar.equals(this.a); aVar = aVar.c) {
            V v = (V) aVar.a();
            if (v != null) {
                return v;
            }
            d(aVar);
            this.b.remove(aVar.a);
            ((e) aVar.a).a();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z = false;
        for (a aVar = this.a.b; !aVar.equals(this.a); aVar = aVar.b) {
            z = true;
            sb.append('{');
            sb.append(aVar.a);
            sb.append(':');
            sb.append(aVar.b());
            sb.append("}, ");
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }

    private void a(a<K, V> aVar) {
        d(aVar);
        aVar.c = this.a;
        aVar.b = this.a.b;
        c(aVar);
    }

    private void b(a<K, V> aVar) {
        d(aVar);
        aVar.c = this.a.c;
        aVar.b = this.a;
        c(aVar);
    }

    private static <K, V> void c(a<K, V> aVar) {
        aVar.b.c = aVar;
        aVar.c.b = aVar;
    }

    private static <K, V> void d(a<K, V> aVar) {
        aVar.c.b = aVar.b;
        aVar.b.c = aVar.c;
    }

    private static class a<K, V> {
        final K a;
        a<K, V> b;
        a<K, V> c;
        private List<V> d;

        a() {
            this(null);
        }

        a(K k) {
            this.c = this;
            this.b = this;
            this.a = k;
        }

        public V a() {
            int iB = b();
            if (iB > 0) {
                return this.d.remove(iB - 1);
            }
            return null;
        }

        public int b() {
            if (this.d != null) {
                return this.d.size();
            }
            return 0;
        }

        public void a(V v) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(v);
        }
    }
}
