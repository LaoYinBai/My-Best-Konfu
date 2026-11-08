package com.sigmob.sdk.mraid;

import android.annotation.SuppressLint;
import android.os.Handler;
import com.sigmob.sdk.base.common.p;
import com.sigmob.sdk.common.utils.m;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l {

    @SuppressLint({"UseSparseArrays"})
    private static final Map<String, a> b = Collections.synchronizedMap(new HashMap());
    static final b a = new b();
    private static Handler c = new Handler();

    public static class a {
        private final WeakReference<Object> a;
        private final p b;
        private final c c;

        public WeakReference<Object> a() {
            return this.a;
        }

        public p b() {
            return this.b;
        }

        public c c() {
            return this.c;
        }
    }

    private static class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            l.a();
        }
    }

    public static a a(String str) {
        m.a(str);
        return b.remove(str);
    }

    static synchronized void a() {
        Iterator<Map.Entry<String, a>> it = b.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, a> next = it.next();
            if (next.getValue().a().get() == null) {
                next.getValue().b().a();
                it.remove();
            }
        }
        if (!b.isEmpty()) {
            c.removeCallbacks(a);
            c.postDelayed(a, 3000000L);
        }
    }
}
