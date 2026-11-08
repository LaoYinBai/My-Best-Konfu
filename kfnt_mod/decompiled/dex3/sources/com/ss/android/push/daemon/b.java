package com.ss.android.push.daemon;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public final a a;
    public final a b;
    public InterfaceC0035b c;

    /* renamed from: com.ss.android.push.daemon.b$b, reason: collision with other inner class name */
    public interface InterfaceC0035b {
        void a();

        void b(Context context);

        void c(Context context);
    }

    public b(a aVar, a aVar2, InterfaceC0035b interfaceC0035b) {
        this.a = aVar;
        this.b = aVar2;
        this.c = interfaceC0035b;
    }

    public static class a {
        public final String a;
        public final String b;
        public final String c;

        public a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }
    }
}
