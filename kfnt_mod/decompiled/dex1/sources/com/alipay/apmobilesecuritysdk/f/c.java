package com.alipay.apmobilesecuritysdk.f;

import android.os.Process;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f2509a;

    c(b bVar) {
        this.f2509a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!this.f2509a.f2508c.isEmpty()) {
                Runnable runnable = (Runnable) this.f2509a.f2508c.get(0);
                this.f2509a.f2508c.remove(0);
                if (runnable != null) {
                    runnable.run();
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            b.b(this.f2509a);
            throw th;
        }
        b.b(this.f2509a);
    }
}
