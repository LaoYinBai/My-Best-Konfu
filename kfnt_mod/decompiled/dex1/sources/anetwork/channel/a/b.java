package anetwork.channel.a;

import anet.channel.util.ALog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static List<a> f624a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private static final ReentrantReadWriteLock f625b = new ReentrantReadWriteLock();

    /* renamed from: c, reason: collision with root package name */
    private static final ReentrantReadWriteLock.ReadLock f626c = f625b.readLock();
    private static final ReentrantReadWriteLock.WriteLock d = f625b.writeLock();

    private static class a implements Comparable<a> {

        /* renamed from: a, reason: collision with root package name */
        final anetwork.channel.a.a f627a;

        /* renamed from: b, reason: collision with root package name */
        final c f628b;

        /* renamed from: c, reason: collision with root package name */
        final int f629c;

        a(anetwork.channel.a.a aVar, c cVar, int i) {
            this.f627a = aVar;
            this.f628b = cVar;
            this.f629c = i;
        }

        @Override // java.lang.Comparable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            return this.f629c - aVar.f629c;
        }
    }

    public static void a(anetwork.channel.a.a aVar, c cVar, int i) {
        try {
            if (aVar == null) {
                throw new IllegalArgumentException("cache is null");
            }
            if (cVar == null) {
                throw new IllegalArgumentException("prediction is null");
            }
            d.lock();
            f624a.add(new a(aVar, cVar, i));
            Collections.sort(f624a);
        } finally {
            d.unlock();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
    
        r3 = r1.f627a;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static anetwork.channel.a.a a(java.lang.String r3, java.util.Map<java.lang.String, java.lang.String> r4) {
        /*
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = anetwork.channel.a.b.f626c     // Catch: java.lang.Throwable -> L29
            r0.lock()     // Catch: java.lang.Throwable -> L29
            java.util.List<anetwork.channel.a.b$a> r0 = anetwork.channel.a.b.f624a     // Catch: java.lang.Throwable -> L29
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L29
        Lb:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L29
            if (r1 == 0) goto L27
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L29
            anetwork.channel.a.b$a r1 = (anetwork.channel.a.b.a) r1     // Catch: java.lang.Throwable -> L29
            anetwork.channel.a.c r2 = r1.f628b     // Catch: java.lang.Throwable -> L29
            boolean r2 = r2.handleCache(r3, r4)     // Catch: java.lang.Throwable -> L29
            if (r2 == 0) goto Lb
            anetwork.channel.a.a r3 = r1.f627a     // Catch: java.lang.Throwable -> L29
        L21:
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r4 = anetwork.channel.a.b.f626c
            r4.unlock()
            return r3
        L27:
            r3 = 0
            goto L21
        L29:
            r3 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r4 = anetwork.channel.a.b.f626c
            r4.unlock()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.a.b.a(java.lang.String, java.util.Map):anetwork.channel.a.a");
    }

    public static void a() {
        ALog.w("anet.CacheManager", "clearAllCache", null, new Object[0]);
        Iterator<a> it = f624a.iterator();
        while (it.hasNext()) {
            try {
                it.next().f627a.clear();
            } catch (Exception unused) {
            }
        }
    }
}
