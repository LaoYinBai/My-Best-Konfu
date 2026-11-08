package com.mbridge.msdk.d;

import com.mbridge.msdk.d.a;

/* compiled from: TimerController.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {

    /* compiled from: TimerController.java */
    static class a {
        static b a = new b();
    }

    private b() {
    }

    public static b getInstance() {
        return a.a;
    }

    public void start() {
        com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
        if (aVarB == null) {
            aVarB = com.mbridge.msdk.b.b.a().b();
        }
        if (aVarB.d() > 0) {
            a.C0107a.a.a(r0 * 1000);
        }
    }

    public void addRewardList(String str, String str2) {
        a.C0107a.a.a(str, str2);
    }

    public void addInterstitialList(String str, String str2) {
        a.C0107a.a.b(str, str2);
    }
}
