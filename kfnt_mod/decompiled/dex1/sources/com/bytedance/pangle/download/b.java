package com.bytedance.pangle.download;

import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {
    private static b d;
    private int e = 0;

    /* renamed from: a, reason: collision with root package name */
    boolean f4561a = true;

    /* renamed from: b, reason: collision with root package name */
    List<a> f4562b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    boolean f4563c = false;

    static /* synthetic */ int c(b bVar) {
        int i = bVar.e;
        bVar.e = i + 1;
        return i;
    }

    static /* synthetic */ int d(b bVar) {
        int i = bVar.e;
        bVar.e = i - 1;
        return i;
    }

    private b() {
    }

    public static b a() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b();
                }
            }
        }
        return d;
    }
}
