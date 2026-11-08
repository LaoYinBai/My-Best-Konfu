package com.apm.insight;

import com.apm.insight.runtime.m;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public c() {
        com.apm.insight.f.a.a();
    }

    public void a(String str) {
        a(str, "EnsureNotReachHere", null);
    }

    public void a(String str, String str2, Map<String, String> map) {
        if (h.i().isEnsureEnable()) {
            com.apm.insight.f.b.a(Thread.currentThread().getStackTrace(), 5, str, str2, map);
        }
    }

    public void a(String str, Throwable th) {
        m.a(th, str);
    }

    public void a(Throwable th, String str) {
        if (a(th)) {
            com.apm.insight.f.b.a(th, str, true);
        }
    }

    public boolean a(Throwable th) {
        return h.i().isEnsureEnable() && !com.apm.insight.l.j.a(th);
    }
}
