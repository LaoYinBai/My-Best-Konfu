package com.apm.insight.b;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Printer;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f2807a;

    /* renamed from: b, reason: collision with root package name */
    private static Printer f2808b;
    private static e d;
    private static volatile a e;

    /* renamed from: c, reason: collision with root package name */
    private static final CopyOnWriteArrayList<e> f2809c = new CopyOnWriteArrayList<>();
    private static volatile boolean f = false;
    private static long g = 0;
    private static int h = 0;

    public interface a {
        void a(long j);
    }

    public static void a() {
        if (f2807a) {
            return;
        }
        f2807a = true;
        f2808b = new Printer() { // from class: com.apm.insight.b.i.1
            @Override // android.util.Printer
            public void println(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                boolean z = false;
                if (str.charAt(0) == '>') {
                    z = true;
                } else if (str.charAt(0) != '<') {
                    return;
                }
                i.a(z, str);
            }
        };
        j.a();
        j.a(f2808b);
    }

    public static void a(e eVar) {
        synchronized (f2809c) {
            f2809c.add(eVar);
        }
    }

    public static void a(boolean z, String str) {
        long jNanoTime = System.nanoTime();
        e.f2776a = jNanoTime / 1000000;
        e.f2777b = SystemClock.currentThreadTimeMillis();
        if (z && d != null && d.a()) {
            d.a(str);
        }
        CopyOnWriteArrayList<e> copyOnWriteArrayList = f2809c;
        for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
            e eVar = copyOnWriteArrayList.get(i);
            if (eVar == null || !eVar.a()) {
                if (!z && eVar.f2778c) {
                    eVar.b("");
                }
            } else if (z) {
                if (!eVar.f2778c) {
                    eVar.a(str);
                }
            } else if (eVar.f2778c) {
                eVar.b(str);
            }
        }
        if (!z && d != null && d.a()) {
            d.b("");
        }
        if (f) {
            g += System.nanoTime() - jNanoTime;
            int i2 = h;
            h = i2 + 1;
            if (i2 >= 1000) {
                if (e != null) {
                    e.a(g);
                }
                h = 0;
                g = 0L;
                f = false;
            }
        }
    }
}
