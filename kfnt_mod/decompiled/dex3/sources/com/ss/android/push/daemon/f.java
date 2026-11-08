package com.ss.android.push.daemon;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f {
    private static b a;

    public interface b {
        boolean a(String str);
    }

    static class a implements b {
        a() {
        }

        @Override // com.ss.android.push.daemon.f.b
        public boolean a(String str) {
            System.loadLibrary(str);
            return true;
        }
    }

    static void a(b bVar) {
        a = bVar;
    }

    public static void a(String str) {
        if (a == null || !a.a(str)) {
            new a().a(str);
        }
    }
}
