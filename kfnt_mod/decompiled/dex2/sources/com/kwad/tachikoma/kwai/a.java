package com.kwad.tachikoma.kwai;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.config.b;
import com.kwad.sdk.core.report.CommercialAction;
import com.kwad.sdk.core.report.i;
import com.kwad.sdk.utils.AbiUtil;
import com.kwai.sodler.lib.ext.PluginError;
import com.kwai.sodler.lib.ext.b;
import com.kwai.sodler.lib.g;
import com.kwai.sodler.lib.h;
import com.mbridge.msdk.MBridgeConstans;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static long b;

    /* renamed from: com.kwad.tachikoma.kwai.a$a, reason: collision with other inner class name */
    public interface InterfaceC0086a {
        void a();

        void a(int i);
    }

    public static void a(Context context, @NonNull final InterfaceC0086a interfaceC0086a) {
        String strX;
        String str;
        if (a.get()) {
            return;
        }
        a.set(true);
        if (AbiUtil.b()) {
            strX = b.y();
            if (TextUtils.isEmpty(strX)) {
                strX = "https://static.yximgs.com/udata/pkg/commercial_res_test/ks_so-TachikomaArm64v8aRelease-3.3.13.apk";
            }
            str = "sotk-v8a";
        } else {
            strX = b.x();
            if (TextUtils.isEmpty(strX)) {
                strX = "https://static.yximgs.com/udata/pkg/commercial_res_test/ks_so-TachikomaArmeabiv7aRelease-3.3.13.apk";
            }
            str = "sotk-v7a";
        }
        com.kwai.sodler.kwai.b.a(context);
        com.kwai.sodler.lib.a.b bVar = new com.kwai.sodler.lib.a.b();
        bVar.c = strX;
        bVar.e = true;
        bVar.a = str;
        bVar.b = MBridgeConstans.NATIVE_VIDEO_VERSION;
        bVar.g = false;
        com.kwai.sodler.kwai.b.a(str, bVar, new b.a() { // from class: com.kwad.tachikoma.kwai.a.1
            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void a(h hVar) {
                super.a(hVar);
            }

            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void a(h hVar, PluginError pluginError) {
                interfaceC0086a.a(pluginError.getCode());
                i.b("ad_client_error_log", new CommercialAction.TKDownloadMsg().setDownloadState(2).setErrorReason(pluginError.getCode() == 4008 ? "md5" : "net"));
            }

            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void a(h hVar, g gVar) {
                interfaceC0086a.a();
                if (a.b > 0) {
                    i.b("ad_client_apm_log", new CommercialAction.TKDownloadMsg().setDownloadTime(SystemClock.elapsedRealtime() - a.b).setDownloadState(1));
                }
            }

            @Override // com.kwai.sodler.lib.ext.b.a, com.kwai.sodler.lib.ext.b
            public void b(h hVar) {
                super.b(hVar);
                long unused = a.b = SystemClock.elapsedRealtime();
                i.b("ad_client_apm_log", new CommercialAction.TKDownloadMsg().setDownloadState(0));
            }
        });
    }
}
