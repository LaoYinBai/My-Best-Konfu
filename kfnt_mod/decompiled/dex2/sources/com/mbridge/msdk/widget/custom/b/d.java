package com.mbridge.msdk.widget.custom.b;

import android.util.Log;

/* compiled from: Logger.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    public static boolean a = false;
    public static String b = "com.mbridge.msdk.widget.custom.baseview.MB";

    public static void a(String str) {
        if (a) {
            new SecurityManager();
            Log.e(new Throwable().getStackTrace()[1].getFileName(), str);
        }
    }
}
