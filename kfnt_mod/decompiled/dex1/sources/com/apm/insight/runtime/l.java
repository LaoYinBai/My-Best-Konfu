package com.apm.insight.runtime;

import android.os.SystemClock;
import android.util.Printer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private static Printer f3044a;

    /* renamed from: b, reason: collision with root package name */
    private static l f3045b;
    private static final Printer f = new Printer() { // from class: com.apm.insight.runtime.l.1
        @Override // android.util.Printer
        public void println(String str) {
            if (str == null) {
                return;
            }
            if (str.charAt(0) == '>') {
                l.a().a(str);
            } else if (str.charAt(0) == '<') {
                l.a().b(str);
            }
            if (l.f3044a == null || l.f3044a == l.f) {
                return;
            }
            l.f3044a.println(str);
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private long f3046c = -1;
    private final List<Printer> d = new ArrayList();
    private final List<Printer> e = new ArrayList();
    private boolean g = false;

    private l() {
    }

    public static l a() {
        if (f3045b == null) {
            synchronized (l.class) {
                if (f3045b == null) {
                    f3045b = new l();
                }
            }
        }
        return f3045b;
    }

    private static void a(List<? extends Printer> list, String str) {
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Printer printer = list.get(i);
                if (printer == null) {
                    return;
                }
                printer.println(str);
            }
        } catch (Throwable th) {
            com.apm.insight.l.q.a(th);
        }
    }

    void a(String str) {
        com.apm.insight.b.f.a(false);
        this.f3046c = -1L;
        try {
            a(this.d, str);
        } catch (Exception e) {
            com.apm.insight.l.q.a((Throwable) e);
        }
    }

    void b(String str) {
        this.f3046c = SystemClock.uptimeMillis();
        try {
            a(this.e, str);
        } catch (Exception e) {
            com.apm.insight.l.q.b((Throwable) e);
        }
    }

    public boolean b() {
        return this.f3046c != -1 && SystemClock.uptimeMillis() - this.f3046c > 5000;
    }
}
