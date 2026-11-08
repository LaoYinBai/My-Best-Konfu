package com.kwad.a;

import android.content.Context;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {

    public interface a {
        void a(Context context, String[] strArr, String str, File file, com.kwad.a.c cVar);
    }

    /* renamed from: com.kwad.a.b$b, reason: collision with other inner class name */
    public interface InterfaceC0021b {
        void a(String str);

        String[] a();

        void b(String str);

        String c(String str);

        String d(String str);
    }

    public interface c {
        void a(String str);

        void a(String str, Throwable th);
    }

    public interface d {
        void a(String str);
    }

    public static void a(Context context, String str) {
        a(context, str, null, null);
    }

    public static void a(Context context, String str, String str2, c cVar) {
        new com.kwad.a.c().a(context, str, str2, cVar);
    }
}
