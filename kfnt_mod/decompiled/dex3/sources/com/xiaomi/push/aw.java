package com.xiaomi.push;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class aw {
    static int a;

    public static ar a(Context context) {
        if (av.m104a(context)) {
            a = 1;
            return new av(context);
        }
        if (ap.m99a(context)) {
            a = 2;
            return new ap(context);
        }
        if (ay.a(context)) {
            a = 4;
            return new ay(context);
        }
        if (bc.a(context)) {
            a = 5;
            return new bc(context);
        }
        if (au.a(context)) {
            a = 3;
            return new as(context);
        }
        a = 0;
        return new bb();
    }
}
