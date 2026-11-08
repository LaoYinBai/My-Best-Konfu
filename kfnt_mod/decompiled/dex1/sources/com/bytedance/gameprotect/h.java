package com.bytedance.gameprotect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {
    private static volatile h d;

    /* renamed from: b, reason: collision with root package name */
    private String f3869b;

    /* renamed from: a, reason: collision with root package name */
    private volatile int f3868a = 0;

    /* renamed from: c, reason: collision with root package name */
    private List<IEmulatorCallback> f3870c = new ArrayList();

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ IEmulatorCallback f3871a;

        a(IEmulatorCallback iEmulatorCallback) {
            this.f3871a = iEmulatorCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            h.b().c(this.f3871a);
        }
    }

    static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ IEmulatorCallback f3872a;

        b(IEmulatorCallback iEmulatorCallback) {
            this.f3872a = iEmulatorCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            h.b().a(this.f3872a);
        }
    }

    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f3873a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3874b;

        c(String str, int i) {
            this.f3873a = str;
            this.f3874b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            h.b().a(this.f3873a, this.f3874b);
        }
    }

    private h() {
    }

    public static void a(int i, int i2, String str) {
        if (i != 1) {
            return;
        }
        k.a(new c(str, i2));
    }

    public static void a(IEmulatorCallback iEmulatorCallback, long j) {
        k.a(new a(iEmulatorCallback));
        if (j != 0) {
            k.a(new b(iEmulatorCallback), j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized h b() {
        if (d == null) {
            d = new h();
        }
        return d;
    }

    private void b(IEmulatorCallback iEmulatorCallback) {
        String str;
        boolean z = true;
        if (this.f3868a == 1) {
            str = this.f3869b;
            z = false;
        } else {
            if (this.f3868a != 2) {
                iEmulatorCallback.OnFailure(this.f3869b);
                return;
            }
            str = this.f3869b;
        }
        iEmulatorCallback.OnSuccess(z, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IEmulatorCallback iEmulatorCallback) {
        synchronized (h.class) {
            if (this.f3868a == 0) {
                this.f3870c.add(iEmulatorCallback);
            } else {
                b(iEmulatorCallback);
            }
        }
    }

    public void a(IEmulatorCallback iEmulatorCallback) {
        synchronized (h.class) {
            if (this.f3868a == 0) {
                iEmulatorCallback.OnFailure("GP_Emulator_Err_TimeOut");
                this.f3870c.remove(iEmulatorCallback);
            }
        }
    }

    public void a(String str, int i) {
        synchronized (h.class) {
            this.f3868a = i;
            this.f3869b = str;
            Iterator<IEmulatorCallback> it = this.f3870c.iterator();
            while (it.hasNext()) {
                b(it.next());
                it.remove();
            }
        }
    }
}
