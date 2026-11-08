package com.unity3d.player;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class g {
    protected static boolean a;

    protected static void Log(int i, String str) {
        if (a) {
            return;
        }
        if (i == 6) {
            Log.e("Unity", str);
        }
        if (i == 5) {
            Log.w("Unity", str);
        }
    }
}
