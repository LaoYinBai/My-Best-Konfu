package com.bytedance.b.a.a.c;

import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final ConcurrentHashMap<String, c> f3606a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private static final ConcurrentHashMap<String, b> f3607b = new ConcurrentHashMap<>();

    public static void a(String str, c cVar) {
        f3606a.put(str, cVar);
    }

    public static c a(String str) {
        return f3606a.get(str);
    }

    public static void a(String str, b bVar) {
        f3607b.put(str, bVar);
    }

    public static b b(String str) {
        return f3607b.get(str);
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        return f3607b.get(str).a(str2);
    }
}
