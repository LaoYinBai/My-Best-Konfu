package com.mbridge.msdk.foundation.same.a;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* compiled from: LruMemoryCache.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a implements c<String, Bitmap> {
    private final LinkedHashMap<String, Bitmap> a;
    private final int b;
    private int c;

    @Override // com.mbridge.msdk.foundation.same.a.c
    public final /* synthetic */ void a(String str) {
        String str2 = str;
        if (str2 == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            Bitmap bitmapRemove = this.a.remove(str2);
            if (bitmapRemove != null) {
                this.c -= b(str2, bitmapRemove);
            }
        }
    }

    public a(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.b = i;
        this.a = new LinkedHashMap<>(0, 0.75f, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.mbridge.msdk.foundation.same.a.c
    /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public Bitmap b(String str) {
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            Bitmap bitmap = this.a.get(str);
            if (bitmap == null || bitmap.isRecycled()) {
                return null;
            }
            return bitmap;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.mbridge.msdk.foundation.same.a.c
    public boolean a(String str, Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.c += b(str, bitmap);
            Bitmap bitmapPut = this.a.put(str, bitmap);
            if (bitmapPut != null) {
                this.c -= b(str, bitmapPut);
            }
        }
        a(this.b);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0055, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0074, code lost:
    
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(int r4) {
        /*
            r3 = this;
        L0:
            monitor-enter(r3)
            int r0 = r3.c     // Catch: java.lang.Throwable -> L75
            if (r0 < 0) goto L56
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r0 = r3.a     // Catch: java.lang.Throwable -> L75
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L11
            int r0 = r3.c     // Catch: java.lang.Throwable -> L75
            if (r0 != 0) goto L56
        L11:
            int r0 = r3.c     // Catch: java.lang.Throwable -> L75
            if (r0 <= r4) goto L54
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r0 = r3.a     // Catch: java.lang.Throwable -> L75
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L75
            if (r0 == 0) goto L1e
            goto L54
        L1e:
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r0 = r3.a     // Catch: java.lang.Throwable -> L75
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L75
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L75
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L75
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L75
            if (r0 != 0) goto L32
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L75
            goto L55
        L32:
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L75
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L75
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L75
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0     // Catch: java.lang.Throwable -> L75
            int r0 = r3.b(r1, r0)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L75
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r2 = r3.a     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L75
            java.lang.Object r1 = r2.remove(r1)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L75
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L75
            r1.recycle()     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L75
            int r1 = r3.c     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L75
            int r1 = r1 - r0
            r3.c = r1     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L75
        L52:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L75
            goto L0
        L54:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L75
        L55:
            return
        L56:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L75
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L75
            r0.<init>()     // Catch: java.lang.Throwable -> L75
            java.lang.Class r1 = r3.getClass()     // Catch: java.lang.Throwable -> L75
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L75
            r0.append(r1)     // Catch: java.lang.Throwable -> L75
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: java.lang.Throwable -> L75
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L75
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L75
            throw r4     // Catch: java.lang.Throwable -> L75
        L75:
            r4 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L75
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.same.a.a.a(int):void");
    }

    @Override // com.mbridge.msdk.foundation.same.a.c
    public final Collection<String> a() {
        HashSet hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.a.keySet());
        }
        return hashSet;
    }

    @Override // com.mbridge.msdk.foundation.same.a.c
    public final void b() {
        a(-1);
    }

    private int b(String str, Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public final synchronized String toString() {
        return String.format("LruCache[maxSize=%d]", Integer.valueOf(this.b));
    }
}
