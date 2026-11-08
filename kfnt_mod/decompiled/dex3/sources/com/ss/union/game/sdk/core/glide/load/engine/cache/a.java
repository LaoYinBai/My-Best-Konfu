package com.ss.union.game.sdk.core.glide.load.engine.cache;

import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class a {
    private final Map<String, C0080a> a = new HashMap();
    private final b b = new b();

    a() {
    }

    void a(String str) {
        C0080a c0080aA;
        synchronized (this) {
            c0080aA = this.a.get(str);
            if (c0080aA == null) {
                c0080aA = this.b.a();
                this.a.put(str, c0080aA);
            }
            c0080aA.b++;
        }
        c0080aA.a.lock();
    }

    void b(String str) {
        C0080a c0080a;
        synchronized (this) {
            c0080a = (C0080a) Preconditions.checkNotNull(this.a.get(str));
            if (c0080a.b < 1) {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + c0080a.b);
            }
            c0080a.b--;
            if (c0080a.b == 0) {
                C0080a c0080aRemove = this.a.remove(str);
                if (!c0080aRemove.equals(c0080a)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + c0080a + ", but actually removed: " + c0080aRemove + ", safeKey: " + str);
                }
                this.b.a(c0080aRemove);
            }
        }
        c0080a.a.unlock();
    }

    /* renamed from: com.ss.union.game.sdk.core.glide.load.engine.cache.a$a, reason: collision with other inner class name */
    private static class C0080a {
        final Lock a = new ReentrantLock();
        int b;

        C0080a() {
        }
    }

    private static class b {
        private static final int a = 10;
        private final Queue<C0080a> b = new ArrayDeque();

        b() {
        }

        C0080a a() {
            C0080a c0080aPoll;
            synchronized (this.b) {
                c0080aPoll = this.b.poll();
            }
            return c0080aPoll == null ? new C0080a() : c0080aPoll;
        }

        void a(C0080a c0080a) {
            synchronized (this.b) {
                if (this.b.size() < 10) {
                    this.b.offer(c0080a);
                }
            }
        }
    }
}
