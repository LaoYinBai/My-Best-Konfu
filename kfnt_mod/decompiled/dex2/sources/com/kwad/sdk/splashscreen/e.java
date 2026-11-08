package com.kwad.sdk.splashscreen;

import android.text.TextUtils;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.splashscreen.a.i;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private String a;
    private boolean b;
    private int c = 2;

    public static e a(AdTemplate adTemplate, AdInfo adInfo, com.kwad.sdk.core.download.a.b bVar) {
        String str;
        e eVar = new e();
        if (adInfo == null || !com.kwad.sdk.core.response.a.a.B(adInfo) || bVar == null) {
            String strAB = com.kwad.sdk.core.config.b.aB();
            if (TextUtils.isEmpty(strAB)) {
                strAB = "点击跳过详情页或第三方应用";
            }
            str = "或" + strAB;
        } else {
            int iC = bVar.c();
            com.kwad.sdk.core.d.a.c("SplashActionBarModel", "createModel localStatus: " + iC);
            str = "或点击" + a(adTemplate, adInfo, iC);
        }
        eVar.a(str);
        eVar.a(com.kwad.sdk.core.response.a.b.a(adInfo));
        eVar.a(i.a(adInfo));
        return eVar;
    }

    public static String a(AdTemplate adTemplate, AdInfo adInfo, int i) {
        return i != 8 ? i != 12 ? com.kwad.sdk.core.response.a.a.A(adInfo) : com.kwad.sdk.core.response.a.a.m(adInfo) : com.kwad.sdk.core.response.a.a.a(adTemplate);
    }

    public String a() {
        return this.a;
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public int b() {
        return this.c;
    }
}
