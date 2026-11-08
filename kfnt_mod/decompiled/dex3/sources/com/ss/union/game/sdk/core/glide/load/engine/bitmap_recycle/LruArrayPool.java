package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class LruArrayPool implements ArrayPool {
    static final int a = 8;
    private static final int b = 4194304;
    private static final int c = 2;
    private final c<a, Object> d;
    private final b e;
    private final Map<Class<?>, NavigableMap<Integer, Integer>> f;
    private final Map<Class<?>, com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a<?>> g;
    private final int h;
    private int i;

    public LruArrayPool() {
        this.d = new c<>();
        this.e = new b();
        this.f = new HashMap();
        this.g = new HashMap();
        this.h = 4194304;
    }

    public LruArrayPool(int i) {
        this.d = new c<>();
        this.e = new b();
        this.f = new HashMap();
        this.g = new HashMap();
        this.h = i;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool
    @Deprecated
    public <T> void put(T t, Class<T> cls) {
        put(t);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> void put(T t) {
        Class<?> cls = t.getClass();
        com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a<T> aVarB = b(cls);
        int arrayLength = aVarB.getArrayLength(t);
        int elementSizeInBytes = aVarB.getElementSizeInBytes() * arrayLength;
        if (a(elementSizeInBytes)) {
            a aVarA = this.e.a(arrayLength, cls);
            this.d.a(aVarA, t);
            NavigableMap<Integer, Integer> navigableMapA = a(cls);
            Integer num = (Integer) navigableMapA.get(Integer.valueOf(aVarA.a));
            Integer numValueOf = Integer.valueOf(aVarA.a);
            int iIntValue = 1;
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            navigableMapA.put(numValueOf, Integer.valueOf(iIntValue));
            this.i += elementSizeInBytes;
            c();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> T getExact(int i, Class<T> cls) {
        return (T) a(this.e.a(i, cls), cls);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized <T> T get(int i, Class<T> cls) {
        a aVarA;
        Integer numCeilingKey = a((Class<?>) cls).ceilingKey(Integer.valueOf(i));
        if (a(i, numCeilingKey)) {
            aVarA = this.e.a(numCeilingKey.intValue(), cls);
        } else {
            aVarA = this.e.a(i, cls);
        }
        return (T) a(aVarA, cls);
    }

    private <T> T a(a aVar, Class<T> cls) {
        com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a<T> aVarB = b(cls);
        T t = (T) a(aVar);
        if (t != null) {
            this.i -= aVarB.getArrayLength(t) * aVarB.getElementSizeInBytes();
            a(aVarB.getArrayLength(t), (Class<?>) cls);
        }
        if (t != null) {
            return t;
        }
        if (Log.isLoggable(aVarB.getTag(), 2)) {
            Log.v(aVarB.getTag(), "Allocated " + aVar.a + " bytes");
        }
        return aVarB.newArray(aVar.a);
    }

    private <T> T a(a aVar) {
        return (T) this.d.a((c<a, Object>) aVar);
    }

    private boolean a(int i) {
        return i <= this.h / 2;
    }

    private boolean a(int i, Integer num) {
        return num != null && (b() || num.intValue() <= i * 8);
    }

    private boolean b() {
        return this.i == 0 || this.h / this.i >= 2;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized void clearMemory() {
        b(0);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool
    public synchronized void trimMemory(int i) {
        try {
            if (i >= 40) {
                clearMemory();
            } else if (i >= 20 || i == 15) {
                b(this.h / 2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private void c() {
        b(this.h);
    }

    private void b(int i) {
        while (this.i > i) {
            Object objA = this.d.a();
            Preconditions.checkNotNull(objA);
            com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a aVarA = a((LruArrayPool) objA);
            this.i -= aVarA.getArrayLength(objA) * aVarA.getElementSizeInBytes();
            a(aVarA.getArrayLength(objA), objA.getClass());
            if (Log.isLoggable(aVarA.getTag(), 2)) {
                Log.v(aVarA.getTag(), "evicted: " + aVarA.getArrayLength(objA));
            }
        }
    }

    private void a(int i, Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMapA = a(cls);
        Integer num = (Integer) navigableMapA.get(Integer.valueOf(i));
        if (num == null) {
            throw new NullPointerException("Tried to decrement empty size, size: " + i + ", this: " + this);
        }
        if (num.intValue() == 1) {
            navigableMapA.remove(Integer.valueOf(i));
        } else {
            navigableMapA.put(Integer.valueOf(i), Integer.valueOf(num.intValue() - 1));
        }
    }

    private NavigableMap<Integer, Integer> a(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.f.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f.put(cls, treeMap);
        return treeMap;
    }

    private <T> com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a<T> a(T t) {
        return b(t.getClass());
    }

    private <T> com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a<T> b(Class<T> cls) {
        com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a<T> byteArrayAdapter = (com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a) this.g.get(cls);
        if (byteArrayAdapter == null) {
            if (cls.equals(int[].class)) {
                byteArrayAdapter = new IntegerArrayAdapter();
            } else if (cls.equals(byte[].class)) {
                byteArrayAdapter = new ByteArrayAdapter();
            } else {
                throw new IllegalArgumentException("No array pool found for: " + cls.getSimpleName());
            }
            this.g.put(cls, byteArrayAdapter);
        }
        return byteArrayAdapter;
    }

    int a() {
        int iIntValue = 0;
        for (Class<?> cls : this.f.keySet()) {
            for (Integer num : this.f.get(cls).keySet()) {
                iIntValue += num.intValue() * ((Integer) this.f.get(cls).get(num)).intValue() * b(cls).getElementSizeInBytes();
            }
        }
        return iIntValue;
    }

    private static final class b extends BaseKeyPool<a> {
        b() {
        }

        a a(int i, Class<?> cls) {
            a aVarC = c();
            aVarC.a(i, cls);
            return aVarC;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BaseKeyPool
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a(this);
        }
    }

    private static final class a implements e {
        int a;
        private final b b;
        private Class<?> c;

        a(b bVar) {
            this.b = bVar;
        }

        void a(int i, Class<?> cls) {
            this.a = i;
            this.c = cls;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.c == aVar.c;
        }

        public String toString() {
            return "Key{size=" + this.a + "array=" + this.c + '}';
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.e
        public void a() {
            this.b.offer(this);
        }

        public int hashCode() {
            return (this.a * 31) + (this.c != null ? this.c.hashCode() : 0);
        }
    }
}
