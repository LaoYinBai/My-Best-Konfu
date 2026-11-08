package com.apm.insight.runtime;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class p {

    /* renamed from: a, reason: collision with root package name */
    private static volatile u f3058a;

    /* renamed from: b, reason: collision with root package name */
    private static volatile Handler f3059b;

    public static HandlerThread a() {
        if (f3058a == null) {
            synchronized (p.class) {
                if (f3058a == null) {
                    f3058a = new u("default_npth_thread");
                    f3058a.b();
                }
            }
        }
        return f3058a.c();
    }

    public static u b() {
        if (f3058a == null) {
            a();
        }
        return f3058a;
    }
}
