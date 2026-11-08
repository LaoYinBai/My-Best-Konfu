package com.kwad.sdk.core.download;

import android.R;
import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.UiThread;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.ag;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.utils.i;
import com.kwad.sdk.utils.x;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private static int a = 1;
    private static int b = 1;
    private static e c;
    private InstallTipsViewHelper d;
    private Map<String, Integer> e = new HashMap();
    private Map<String, Integer> f = new HashMap();

    public static e a() {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    c = new e();
                }
            }
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final AdInfo adInfo, final AdTemplate adTemplate, final boolean z) {
        ax.a(new Runnable() { // from class: com.kwad.sdk.core.download.e.3
            @Override // java.lang.Runnable
            public void run() {
                if (e.this.d != null) {
                    ax.a(new Runnable() { // from class: com.kwad.sdk.core.download.e.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (e.this.d != null) {
                                e.this.d.b();
                                e.this.d.c();
                                e.this.d = null;
                            }
                            e.this.b(adInfo, adTemplate, z);
                        }
                    }, null, 500L);
                } else {
                    e.this.b(adInfo, adTemplate, z);
                }
            }
        });
    }

    private void a(Map<String, Integer> map, String str) {
        map.put(str, map.containsKey(str) ? Integer.valueOf(map.get(str).intValue() + 1) : 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public void b(AdInfo adInfo, AdTemplate adTemplate, boolean z) {
        com.kwad.sdk.core.d.a.a("InstallTipsManager", "addToWindow");
        Activity activityC = x.a().c();
        if (activityC != null) {
            View viewFindViewById = activityC.getWindow().getDecorView().findViewById(R.id.content);
            if (viewFindViewById instanceof FrameLayout) {
                InstallTipsViewHelper installTipsViewHelper = new InstallTipsViewHelper(z, adInfo, adTemplate);
                installTipsViewHelper.a((FrameLayout) viewFindViewById);
                String strValueOf = String.valueOf(adInfo.adBaseInfo.creativeId);
                if (z) {
                    a(this.e, strValueOf);
                    com.kwad.sdk.core.report.a.c(adTemplate, 92, null);
                } else {
                    com.kwad.sdk.core.report.a.d(adTemplate, 93, null);
                    a(this.f, strValueOf);
                }
                this.d = installTipsViewHelper;
            }
        }
    }

    public void a(InstallTipsViewHelper installTipsViewHelper) {
        if (installTipsViewHelper == null || this.d == null || !installTipsViewHelper.equals(this.d)) {
            return;
        }
        this.d = null;
    }

    public void a(final AdInfo adInfo, final AdTemplate adTemplate) {
        int iL = com.kwad.sdk.core.config.b.L();
        com.kwad.sdk.core.d.a.a("InstallTipsManager", "onDownloadFinished installTipsTime: " + iL);
        if (iL <= 0 || adInfo == null || adTemplate == null || adTemplate.mAdScene.getAdStyle() == 0) {
            return;
        }
        String strValueOf = String.valueOf(adInfo.adBaseInfo.creativeId);
        int iIntValue = 0;
        if (this.e.containsKey(strValueOf)) {
            iIntValue = this.e.get(strValueOf).intValue();
            this.e.put(strValueOf, Integer.valueOf(iIntValue));
        }
        if (iIntValue >= a) {
            return;
        }
        i.a(new Runnable() { // from class: com.kwad.sdk.core.download.e.1
            @Override // java.lang.Runnable
            public void run() {
                com.kwad.sdk.core.d.a.a("InstallTipsManager", "onDownloadFinished run() adInfo.status: " + adInfo.status);
                if (adInfo.status == 12 || adInfo.status == 10) {
                    return;
                }
                e.this.a(adInfo, adTemplate, true);
            }
        }, iL, TimeUnit.SECONDS);
    }

    public void b(final AdInfo adInfo, final AdTemplate adTemplate) {
        int iAv = com.kwad.sdk.core.config.b.av();
        if (iAv < 0) {
            return;
        }
        String strValueOf = String.valueOf(adInfo.adBaseInfo.creativeId);
        int iIntValue = 0;
        if (this.f.containsKey(strValueOf)) {
            iIntValue = this.f.get(strValueOf).intValue();
            this.f.put(strValueOf, Integer.valueOf(iIntValue));
        }
        if (iIntValue >= b) {
            return;
        }
        i.a(new Runnable() { // from class: com.kwad.sdk.core.download.e.2
            @Override // java.lang.Runnable
            public void run() {
                if (ag.b(KsAdSDK.getContext(), com.kwad.sdk.core.response.a.a.v(adInfo)) == 1) {
                    return;
                }
                e.this.a(adInfo, adTemplate, false);
            }
        }, iAv, TimeUnit.SECONDS);
    }
}
