package com.kwad.sdk.splashscreen.a;

import android.content.Context;
import android.widget.Toast;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.mvp.Presenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h extends Presenter {
    private com.kwad.sdk.splashscreen.g a;
    private com.kwad.sdk.core.download.a.c b;

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context) {
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.a = (com.kwad.sdk.splashscreen.g) p();
        this.b = new com.kwad.sdk.core.download.a.c() { // from class: com.kwad.sdk.splashscreen.a.h.1
            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
            }

            @Override // com.kwad.sdk.core.download.a.c, com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadStarted() {
                super.onDownloadStarted();
                Context contextQ = h.this.q();
                if (h.this.q() instanceof ResContext) {
                    contextQ = ((ResContext) h.this.q()).getDelegatedContext();
                }
                if (contextQ != null) {
                    String str = "已开始下载";
                    String strB = h.b(contextQ);
                    if (strB != null) {
                        str = strB + ":已开始下载";
                    }
                    Toast.makeText(contextQ, str, 0).show();
                }
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
            }
        };
        this.a.g.a(this.b);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        if (this.b != null) {
            this.a.g.b(this.b);
        }
    }
}
