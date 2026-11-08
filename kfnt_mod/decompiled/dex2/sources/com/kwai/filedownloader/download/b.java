package com.kwai.filedownloader.download;

import com.kwai.filedownloader.e.c;
import com.kwai.filedownloader.kwai.c;
import com.kwai.filedownloader.services.c;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private com.kwai.filedownloader.services.c a;
    private c.a b;
    private c.b c;
    private c.e d;
    private volatile com.kwai.filedownloader.a.a e;
    private c.d f;

    private static final class a {
        private static final b a = new b();
    }

    public static b a() {
        return a.a;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00be  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(com.kwai.filedownloader.a.a.InterfaceC0090a r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 445
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.filedownloader.download.b.a(com.kwai.filedownloader.a.a$a):void");
    }

    private c.a f() {
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = i().e();
            }
        }
        return this.b;
    }

    private c.b g() {
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = i().d();
            }
        }
        return this.c;
    }

    private c.e h() {
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = i().c();
            }
        }
        return this.d;
    }

    private com.kwai.filedownloader.services.c i() {
        if (this.a != null) {
            return this.a;
        }
        synchronized (this) {
            if (this.a == null) {
                this.a = new com.kwai.filedownloader.services.c();
            }
        }
        return this.a;
    }

    public int a(int i, String str, String str2, long j) {
        return f().a(i, str, str2, j);
    }

    public com.kwai.filedownloader.d.a a(File file) {
        return h().a(file);
    }

    public com.kwai.filedownloader.kwai.b a(String str) {
        try {
            return g().a(str);
        } catch (Throwable unused) {
            this.c = new c.b();
            return this.c.a(str);
        }
    }

    public void a(c.a aVar) {
        synchronized (this) {
            this.a = new com.kwai.filedownloader.services.c(aVar);
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
        }
    }

    public c.d b() {
        if (this.f != null) {
            return this.f;
        }
        synchronized (this) {
            if (this.f == null) {
                this.f = i().f();
            }
        }
        return this.f;
    }

    public void b(c.a aVar) {
        synchronized (this) {
            this.a = new com.kwai.filedownloader.services.c(aVar);
        }
    }

    public synchronized com.kwai.filedownloader.a.a c() {
        if (this.e != null) {
            return this.e;
        }
        this.e = i().b();
        a(this.e.b());
        return this.e;
    }

    public int d() {
        return i().a();
    }

    public boolean e() {
        return h().a();
    }
}
