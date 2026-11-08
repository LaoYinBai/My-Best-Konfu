package com.kwad.sdk.core.video.kwai;

import android.content.Context;
import android.text.TextUtils;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.utils.AbiUtil;
import com.kwai.sodler.lib.ext.PluginError;
import com.kwai.sodler.lib.ext.b;
import com.kwai.sodler.lib.g;
import com.kwai.sodler.lib.h;
import com.kwai.video.ksvodplayerkit.KSVodPlayerCoreInitConfig;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static AtomicBoolean b;

    public static void a(Context context) {
        com.kwad.sdk.core.d.a.a("kp init");
        KSVodPlayerCoreInitConfig.updatePlayerConfig(com.kwad.sdk.core.config.b.g());
        if (!b()) {
            d(context);
        }
        c(context);
    }

    public static boolean a() {
        if (b == null) {
            b = new AtomicBoolean(true);
        }
        return b.get();
    }

    public static boolean b() {
        return a.get() || KsAdSDKImpl.get().getIsExternal() || com.kwad.sdk.b.e.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Context context) {
        try {
            d.a(context);
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.b(th);
        }
    }

    private static void d(final Context context) {
        String strV;
        String str;
        if (AbiUtil.b()) {
            strV = com.kwad.sdk.core.config.b.w();
            if (TextUtils.isEmpty(strV)) {
                strV = "https://static.yximgs.com/udata/pkg/KS-Android-KSAdSDk/so/ks_so-kwaiPlayerArm64v8aRelease-3.3.20.apk";
            }
            str = "kwaiplayer-v8a";
        } else {
            strV = com.kwad.sdk.core.config.b.v();
            if (TextUtils.isEmpty(strV)) {
                strV = "https://static.yximgs.com/udata/pkg/KS-Android-KSAdSDk/so/ks_so-kwaiPlayerArmeabiv7aRelease-3.3.20.apk";
            }
            str = "kwaiplayer-v7a";
        }
        com.kwai.sodler.kwai.b.a(context);
        com.kwai.sodler.lib.a.b bVar = new com.kwai.sodler.lib.a.b();
        bVar.c = strV;
        bVar.e = true;
        bVar.a = str;
        bVar.b = "3.4";
        bVar.g = true;
        com.kwai.sodler.kwai.b.a(str, bVar, new b.a() { // from class: com.kwad.sdk.core.video.kwai.e.1
            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void a(h hVar) {
                super.a(hVar);
                e.a.set(false);
            }

            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void a(h hVar, PluginError pluginError) {
                e.a.set(false);
            }

            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void a(h hVar, g gVar) {
                e.a.set(true);
                e.c(context);
                e.e();
            }

            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void b(h hVar) {
                super.b(hVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
    }
}
