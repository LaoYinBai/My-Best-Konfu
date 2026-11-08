package com.bytedance.a;

import com.bytedance.a.y;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class t {

    /* renamed from: a, reason: collision with root package name */
    public static int f3538a = 2;

    /* renamed from: b, reason: collision with root package name */
    public static y f3539b = new y.a();

    public static void a(String str, String str2) {
        y yVar = f3539b;
        if (yVar == null || f3538a > 2) {
            return;
        }
        ((y.a) yVar).a(str, str2, null);
    }

    public static void a(String str, String str2, Throwable th) {
        y yVar = f3539b;
        if (yVar == null || f3538a > 3) {
            return;
        }
        ((y.a) yVar).b(str, str2, th);
    }
}
