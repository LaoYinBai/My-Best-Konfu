package com.kwad.tachikoma;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import com.kuaishou.tachikoma.api.TachikomaApi;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.SdkConfig;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.core.report.CommercialAction;
import com.kwad.sdk.core.report.i;
import com.kwad.sdk.plugin.g;
import com.kwad.sdk.utils.aq;
import com.kwad.tachikoma.config.TKConfigResultData;
import com.kwad.tachikoma.config.b;
import com.kwad.tachikoma.kwai.a;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends g {
    private TKConfigResultData a;
    private final AtomicBoolean b = new AtomicBoolean(false);
    private volatile boolean c;

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        i.c("ad_client_error_log", new CommercialAction.TKDownloadMsg().setErrorReason(str).setDownloadState(2));
    }

    private void b(final Context context) {
        i.c("ad_client_apm_log", new CommercialAction.TKDownloadMsg().setDownloadState(0));
        com.kwad.tachikoma.kwai.a.a(context, new a.InterfaceC0086a() { // from class: com.kwad.tachikoma.c.2
            @Override // com.kwad.tachikoma.kwai.a.InterfaceC0086a
            public void a() {
                try {
                    com.kwad.a.b.a(context, "kwad-j2v8");
                    try {
                        com.kwad.a.b.a(context, "kwad-fb");
                        try {
                            com.kwad.a.b.a(context, "kwad-yoga");
                            c.this.b.set(true);
                            com.kwai.sodler.lib.a.b("TachikomaPluginImpl", "loadSo success");
                            c.this.c(KsAdSDKImpl.get().getContext());
                        } catch (Throwable th) {
                            c.this.a("kwad-yoga" + th.getMessage());
                        }
                    } catch (Throwable th2) {
                        com.kwai.sodler.lib.a.b("TachikomaPluginImpl", "loadSo fail:kwad-fb" + th2.getMessage());
                        c.this.a("kwad-fb" + th2.getMessage());
                    }
                } catch (Throwable th3) {
                    com.kwai.sodler.lib.a.b("TachikomaPluginImpl", "loadSo fail:kwad-j2v8" + th3.getMessage());
                    c.this.a("kwad-j2v8" + th3.getMessage());
                }
            }

            @Override // com.kwad.tachikoma.kwai.a.InterfaceC0086a
            public void a(int i) {
                c.this.b.set(false);
                c.this.a(String.valueOf(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void c(Context context) {
        boolean z = context instanceof ResContext;
        Context delegatedContext = context;
        if (z) {
            delegatedContext = ((ResContext) context).getDelegatedContext();
        }
        TachikomaApi.getInstance().init((Application) delegatedContext.getApplicationContext());
        TachikomaApi.getInstance().setInitResult(true);
        TachikomaApi.getInstance().setHostService(new b());
        i.c("ad_client_apm_log", new CommercialAction.TKDownloadMsg().setDownloadState(1));
    }

    @Override // com.kwad.sdk.plugin.g
    public String a(@NonNull Context context) {
        return aq.e(context);
    }

    @Override // com.kwad.sdk.plugin.d
    public synchronized void a(Context context, SdkConfig sdkConfig) {
        if (context == null) {
            return;
        }
        if (this.c) {
            this.c = true;
        } else {
            com.kwad.tachikoma.config.b.a(context, new b.a() { // from class: com.kwad.tachikoma.c.1
                @Override // com.kwad.tachikoma.config.b.a
                public void a(TKConfigResultData tKConfigResultData) {
                    c.this.a = tKConfigResultData;
                }

                @Override // com.kwad.tachikoma.config.b.a
                public void b(@NonNull TKConfigResultData tKConfigResultData) {
                    c.this.a = tKConfigResultData;
                }
            });
            b(context);
        }
    }

    @Override // com.kwad.sdk.plugin.g
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public d a(Context context, String str) {
        d dVar = new d(context);
        dVar.setJsFileName(str);
        return dVar;
    }

    @Override // com.kwad.sdk.plugin.g
    public boolean b() {
        boolean z = this.a != null && this.a.data.e != -1 && com.kwad.tachikoma.config.b.b() && this.b.get();
        com.kwai.sodler.lib.a.b("TachikomaPluginImpl", "Tachikoma isReady:" + z + ", sIsTkSoLoaded:" + this.b + ",loadFinish:" + com.kwad.tachikoma.config.b.b());
        return z;
    }

    @Override // com.kwad.sdk.plugin.g
    public void c() {
        com.kwad.tachikoma.config.b.c();
    }

    @Override // com.kwad.sdk.plugin.g
    public String d() {
        return com.kwad.tachikoma.config.b.a() != null ? com.kwad.tachikoma.config.b.a().data.b : "0.0.1";
    }
}
