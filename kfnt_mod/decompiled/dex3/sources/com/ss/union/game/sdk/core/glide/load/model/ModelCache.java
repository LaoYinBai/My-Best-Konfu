package com.ss.union.game.sdk.core.glide.load.model;

import com.ss.union.game.sdk.core.glide.util.LruCache;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.Queue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ModelCache<A, B> {
    private static final int a = 250;
    private final LruCache<a<A>, B> b;

    public ModelCache() {
        this(250L);
    }

    public ModelCache(long j) {
        this.b = new LruCache<a<A>, B>(j) { // from class: com.ss.union.game.sdk.core.glide.load.model.ModelCache.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.ss.union.game.sdk.core.glide.util.LruCache
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onItemEvicted(a<A> aVar, B b) {
                aVar.a();
            }
        };
    }

    public B get(A a2, int i, int i2) {
        a<A> aVarA = a.a(a2, i, i2);
        B b = this.b.get(aVarA);
        aVarA.a();
        return b;
    }

    public void put(A a2, int i, int i2, B b) {
        this.b.put(a.a(a2, i, i2), b);
    }

    public void clear() {
        this.b.clearMemory();
    }

    static final class a<A> {
        private static final Queue<a<?>> a = Util.createQueue(0);
        private int b;
        private int c;
        private A d;

        static <A> a<A> a(A a2, int i, int i2) {
            a<A> aVar;
            synchronized (a) {
                aVar = (a) a.poll();
            }
            if (aVar == null) {
                aVar = new a<>();
            }
            aVar.b(a2, i, i2);
            return aVar;
        }

        private a() {
        }

        private void b(A a2, int i, int i2) {
            this.d = a2;
            this.c = i;
            this.b = i2;
        }

        public void a() {
            synchronized (a) {
                a.offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.c == aVar.c && this.b == aVar.b && this.d.equals(aVar.d);
        }

        public int hashCode() {
            return (((this.b * 31) + this.c) * 31) + this.d.hashCode();
        }
    }
}
