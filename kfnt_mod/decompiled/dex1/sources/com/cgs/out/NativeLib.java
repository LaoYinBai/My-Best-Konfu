package com.cgs.out;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NativeLib {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f5341a = false;

    /* renamed from: b, reason: collision with root package name */
    public static boolean f5342b = false;

    public static boolean canALC() {
        return true;
    }

    public static boolean canWCB() {
        return true;
    }

    public static Object comm(int i, Object obj) {
        CGManager cGManager = CGManager.getInstance();
        if (cGManager == null) {
            return null;
        }
        if (i != 16777219) {
            if (i == 16777222 && obj != null) {
                cGManager.b((byte[]) obj);
            }
        } else if (obj != null) {
            cGManager.a((byte[]) obj);
        }
        return null;
    }

    public static boolean getALC() {
        return f5341a;
    }

    public static boolean getWCB() {
        return f5342b;
    }

    public static final native Object native_bridge(int i, Context context, Object obj);

    public static final native Object native_oncall(int i, Context context, Object obj);

    public static void setALC(boolean z) {
        f5341a = z;
    }

    public static void setWCB(boolean z) {
        f5342b = z;
    }
}
