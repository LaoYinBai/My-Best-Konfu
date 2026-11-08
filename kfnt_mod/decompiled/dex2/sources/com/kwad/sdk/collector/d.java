package com.kwad.sdk.collector;

import android.content.Context;
import android.text.TextUtils;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.utils.AbiUtil;
import com.kwai.sodler.lib.ext.PluginError;
import com.kwai.sodler.lib.ext.b;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static final AtomicBoolean a = new AtomicBoolean(false);

    public interface a {
        void a();

        void a(String str);
    }

    public static void a(Context context, final a aVar) {
        if (a.get()) {
            return;
        }
        if (KsAdSDKImpl.get().getIsExternal()) {
            b(aVar);
            return;
        }
        a.set(true);
        boolean zB = AbiUtil.b();
        String str = zB ? "kwappstatus-v8a" : "kwappstatus-v7a";
        String strAd = com.kwad.sdk.core.config.b.ad();
        if (TextUtils.isEmpty(strAd)) {
            strAd = "https://static.yximgs.com/udata/pkg/KS-Android-KSAdSDk/ks_so-appStatusArmeabiv7aRelease-3.3.14.apk";
        }
        String strAe = com.kwad.sdk.core.config.b.ae();
        if (TextUtils.isEmpty(strAe)) {
            strAe = "https://static.yximgs.com/udata/pkg/KS-Android-KSAdSDk/ks_so-appStatusArm64v8aRelease-3.3.14.apk";
        }
        if (zB) {
            strAd = strAe;
        }
        String strAh = com.kwad.sdk.core.config.b.ah();
        if (TextUtils.isEmpty(strAh)) {
            strAh = PushConstants.PUSH_FLYME_3_CHANGE_VERSION_START;
        }
        com.kwai.sodler.kwai.b.a(context);
        com.kwai.sodler.lib.a.b bVar = new com.kwai.sodler.lib.a.b();
        bVar.c = strAd;
        bVar.e = true;
        bVar.a = str;
        bVar.b = strAh;
        bVar.g = false;
        com.kwai.sodler.kwai.b.a(str, bVar, new b.a() { // from class: com.kwad.sdk.collector.d.1
            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void a(com.kwai.sodler.lib.h hVar) {
                super.a(hVar);
            }

            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void a(com.kwai.sodler.lib.h hVar, PluginError pluginError) {
                super.a(hVar, pluginError);
                if (aVar != null) {
                    aVar.a(pluginError == null ? "load error" : pluginError.toString());
                }
            }

            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void a(com.kwai.sodler.lib.h hVar, com.kwai.sodler.lib.g gVar) {
                super.a(hVar, gVar);
                d.b(aVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(a aVar) {
        try {
            System.loadLibrary("kwappstatus");
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.b(th);
            com.kwad.sdk.crash.a.a(th);
        }
        if (aVar != null) {
            aVar.a();
        }
    }
}
