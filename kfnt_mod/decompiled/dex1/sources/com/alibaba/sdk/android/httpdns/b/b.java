package com.alibaba.sdk.android.httpdns.b;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static f f2325a = null;

    /* renamed from: a, reason: collision with other field name */
    private static com.alibaba.sdk.android.httpdns.c.a f10a = null;

    /* renamed from: a, reason: collision with other field name */
    private static boolean f11a = false;

    public static List<e> a() {
        ArrayList arrayList = new ArrayList();
        if (!f11a) {
            return arrayList;
        }
        arrayList.addAll(f2325a.a());
        return arrayList;
    }

    public static void a(Context context) {
        a(context, null);
    }

    public static void a(Context context, com.alibaba.sdk.android.httpdns.c.a aVar) {
        f2325a = new a(context);
        f10a = aVar;
        if (f10a == null) {
            f10a = com.alibaba.sdk.android.httpdns.c.a.a();
        }
    }

    public static void a(e eVar) {
        if (eVar == null) {
            return;
        }
        f2325a.a(eVar);
    }

    public static void a(boolean z) {
        f11a = z;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m7a() {
        return f11a;
    }

    public static void b(Context context) {
        if (context != null) {
            f10a.m12c(context);
        }
    }

    public static void b(e eVar) {
        if (eVar == null) {
            return;
        }
        f2325a.b(eVar);
    }

    public static String g() {
        return f10a.g();
    }
}
