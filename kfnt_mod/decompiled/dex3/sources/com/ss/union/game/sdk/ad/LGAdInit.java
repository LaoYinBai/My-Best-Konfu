package com.ss.union.game.sdk.ad;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAdInit {
    public static void init(String str, String str2, String str3, boolean z) {
        com.ss.union.game.sdk.ad.ad_mediation.a.a(str, str2, str3, z);
        new com.ss.union.game.sdk.ad.b.a().start(false);
    }

    public static boolean isAdSDkInitFinish() {
        return com.ss.union.game.sdk.ad.ad_mediation.a.a();
    }

    public static void syncDidToAd(String str) {
        com.ss.union.game.sdk.ad.ad_mediation.a.a(str);
    }
}
