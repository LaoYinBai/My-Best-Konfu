package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class s {

    /* renamed from: a, reason: collision with root package name */
    private static Boolean f2473a;

    public static final boolean a(Context context) {
        if (f2473a != null) {
            return f2473a.booleanValue();
        }
        try {
            Boolean boolValueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            f2473a = boolValueOf;
            return boolValueOf.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
