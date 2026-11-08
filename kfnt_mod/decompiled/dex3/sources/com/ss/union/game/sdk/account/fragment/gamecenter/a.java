package com.ss.union.game.sdk.account.fragment.gamecenter;

import com.ss.union.game.sdk.vcenter.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static g a;

    public static void a(boolean z, int i, String str) {
        if (a == null) {
            return;
        }
        try {
            a.a(z, i, str);
        } catch (Throwable th) {
            a = null;
            throw th;
        }
        a = null;
    }
}
