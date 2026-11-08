package com.bytedance.a;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ad implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final Runnable f3333a;

    /* renamed from: b, reason: collision with root package name */
    public final String f3334b;

    /* renamed from: c, reason: collision with root package name */
    public final String f3335c = Log.getStackTraceString(new RuntimeException("origin stacktrace"));

    public ad(Runnable runnable, String str) {
        this.f3333a = runnable;
        this.f3334b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f3333a.run();
        } catch (Exception e) {
            de.a("", e);
            t.a("TrackerDr", "Thread:" + this.f3334b + " exception\n" + this.f3335c, e);
        }
    }
}
