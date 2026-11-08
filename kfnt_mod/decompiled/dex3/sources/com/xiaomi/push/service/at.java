package com.xiaomi.push.service;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class at {
    private static long a = 0;

    /* renamed from: a, reason: collision with other field name */
    private static String f892a = "";

    public static String a() {
        if (TextUtils.isEmpty(f892a)) {
            f892a = com.xiaomi.push.bm.a(4);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f892a);
        long j = a;
        a = 1 + j;
        sb.append(j);
        return sb.toString();
    }
}
