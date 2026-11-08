package com.kwad.sdk.core.download.a;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.kwad.sdk.R;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.api.loader.Wrapper;
import com.kwad.sdk.core.download.kwai.g;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.utils.x;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.net.URISyntaxException;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private static boolean a = false;

    public static int a(Context context, AdTemplate adTemplate, int i) throws URISyntaxException {
        if (adTemplate == null || context == null) {
            return 0;
        }
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        String strAJ = com.kwad.sdk.core.response.a.a.aJ(adInfoJ);
        String strU = com.kwad.sdk.core.response.a.a.u(adInfoJ);
        int iA = a(context, strAJ);
        if (iA == 1) {
            com.kwad.sdk.core.report.a.a(adTemplate, i);
            a(context, strAJ, strU, adTemplate, i);
        } else if (iA == -1) {
            com.kwad.sdk.core.report.a.b(adTemplate, i);
        }
        return iA;
    }

    public static int a(Context context, String str) throws URISyntaxException {
        if (TextUtils.isEmpty(str) || context == null) {
            return 0;
        }
        try {
            Intent uri = Intent.parseUri(str, 0);
            uri.setAction("android.intent.action.VIEW");
            uri.setFlags(268435456);
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(uri, C.DEFAULT_BUFFER_SEGMENT_SIZE);
            if (listQueryIntentActivities == null || listQueryIntentActivities.size() <= 0) {
                return 0;
            }
            context.startActivity(uri);
            return 1;
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
            return -1;
        }
    }

    private static void a(final Context context, final String str, final String str2, final AdTemplate adTemplate, final int i) {
        if (a()) {
            return;
        }
        b(true);
        final int iD = x.a().d();
        int iAf = com.kwad.sdk.core.config.b.af();
        final boolean z = iAf > 0 && com.kwad.sdk.core.config.b.ag();
        if (Math.abs(iAf) > 0) {
            ax.a(new Runnable() { // from class: com.kwad.sdk.core.download.a.d.1
                @Override // java.lang.Runnable
                public void run() {
                    d.b(false);
                    if (!x.a().b()) {
                        com.kwad.sdk.core.report.a.c(adTemplate, i);
                        return;
                    }
                    int iD2 = x.a().d();
                    if (context == null || !z || iD2 == 0 || iD2 != iD) {
                        return;
                    }
                    d.b(context, str, str2, adTemplate);
                }
            }, null, r0 * 1000);
        } else {
            b(false);
        }
    }

    private static void a(g.a aVar, AdTemplate adTemplate) {
        aVar.a().show();
        com.kwad.sdk.core.report.a.c(adTemplate, 158, null);
    }

    private static boolean a() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static void b(final Context context, final String str, String str2, final AdTemplate adTemplate) {
        g.a aVarA = new g.a(Wrapper.wrapContextIfNeed(context)).a(String.format(Wrapper.wrapContextIfNeed(context).getString(R.string.ksad_deep_link_dialog_content), str2)).a(new g.b() { // from class: com.kwad.sdk.core.download.a.d.2
            @Override // com.kwad.sdk.core.download.kwai.g.b
            public void a(DialogInterface dialogInterface) {
                com.kwad.sdk.core.report.a.f(adTemplate, 159);
                dialogInterface.dismiss();
                ax.a(new Runnable() { // from class: com.kwad.sdk.core.download.a.d.2.1
                    @Override // java.lang.Runnable
                    public void run() throws URISyntaxException {
                        d.a(context, str);
                    }
                }, null, 100L);
            }

            @Override // com.kwad.sdk.core.download.kwai.g.b
            public void b(DialogInterface dialogInterface) {
                com.kwad.sdk.core.report.a.f(adTemplate, 160);
                dialogInterface.dismiss();
            }

            @Override // com.kwad.sdk.core.download.kwai.g.b
            public void c(DialogInterface dialogInterface) {
            }
        });
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        } else {
            if (!(context instanceof ResContext)) {
                return;
            }
            ResContext resContext = (ResContext) context;
            if (!(resContext.getDelegatedContext() instanceof Activity) || ((Activity) resContext.getDelegatedContext()).isFinishing()) {
                return;
            }
        }
        a(aVarA, adTemplate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(boolean z) {
        a = z;
    }
}
