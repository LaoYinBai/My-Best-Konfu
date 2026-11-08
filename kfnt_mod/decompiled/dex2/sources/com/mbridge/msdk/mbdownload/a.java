package com.mbridge.msdk.mbdownload;

import android.content.Context;

/* compiled from: DeltaUpdate.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private static boolean a;

    public static String a(Context context) {
        return context.getApplicationInfo().sourceDir;
    }

    static {
        try {
            System.loadLibrary("bspatch");
            a = true;
        } catch (UnsatisfiedLinkError unused) {
            a = false;
        }
    }
}
