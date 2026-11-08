package com.kwad.sdk.core.h;

import androidx.annotation.NonNull;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    static volatile boolean a = true;
    static volatile boolean b = false;
    static volatile Set<c> c = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private static volatile b d = null;
    private static volatile int e = 204800;

    public static b a() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b();
                }
            }
        }
        return d;
    }

    public static synchronized InputStream a(@NonNull InputStream inputStream) {
        c cVar;
        cVar = new c(inputStream, e / (c.size() + 1));
        c.add(cVar);
        return cVar;
    }

    public static synchronized void a(c cVar) {
        if (c.contains(cVar)) {
            c.remove(cVar);
        }
    }

    public void a(boolean z, int i) {
        if (i > 0) {
            e = i * 1024;
        }
        a = z;
    }

    public InputStream b(InputStream inputStream) {
        return a(inputStream);
    }

    public boolean b() {
        return a;
    }

    public int c() {
        return e / 1024;
    }

    public synchronized int d() {
        int iA;
        iA = 0;
        try {
            Iterator<c> it = c.iterator();
            while (it.hasNext()) {
                iA += (int) it.next().a();
            }
        } catch (Exception unused) {
        }
        return iA;
    }
}
