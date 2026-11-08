package com.ksad.download;

import android.content.Context;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class b {
    private static Context a;
    private static File b;
    private static a c;

    public static Context a() {
        return a;
    }

    public static void a(Context context) {
        a = context.getApplicationContext();
    }

    public static void a(a aVar) {
        c = aVar;
    }

    public static void a(File file) {
        b = file;
    }

    public static void a(String str) {
        c.installApp(a(), str);
    }

    public static File b() {
        return b;
    }
}
