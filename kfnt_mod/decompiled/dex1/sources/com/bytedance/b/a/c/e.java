package com.bytedance.b.a.c;

import java.io.Closeable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
