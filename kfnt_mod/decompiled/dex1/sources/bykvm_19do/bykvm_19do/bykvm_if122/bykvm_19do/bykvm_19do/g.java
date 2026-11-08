package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener;
import com.bytedance.msdk.api.v2.ad.banner.GMBannerAdLoadCallback;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.core.views.RefreshableBannerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private int f1789a;

    /* renamed from: b, reason: collision with root package name */
    private final Activity f1790b;

    /* renamed from: c, reason: collision with root package name */
    private final String f1791c;
    private AdSlot d;
    private GMAdSlotBase e;
    private bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c f;
    private bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c g;
    private final RefreshableBannerView h;
    private final Handler i;
    private final Handler j;
    private GMBannerAdListener k;
    private final View.OnAttachStateChangeListener l = new a();
    private final GMBannerAdListener m = new b();
    private final Runnable n = new c();

    class a implements View.OnAttachStateChangeListener {
        a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            if (g.this.f1789a == 0) {
                return;
            }
            g.this.n();
            g.this.o();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            g.this.m();
        }
    }

    class b implements GMBannerAdListener {
        b() {
        }

        @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
        public void onAdClicked() {
            if (g.this.k != null) {
                g.this.k.onAdClicked();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
        public void onAdClosed() {
            g.this.m();
            if (g.this.k != null) {
                g.this.k.onAdClosed();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
        public void onAdLeftApplication() {
            if (g.this.k != null) {
                g.this.k.onAdLeftApplication();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
        public void onAdOpened() {
            if (g.this.k != null) {
                g.this.k.onAdOpened();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
        public void onAdShow() {
            if (g.this.k != null) {
                g.this.k.onAdShow();
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.banner.GMBannerAdListener
        public void onAdShowFail(AdError adError) {
            if (g.this.k != null) {
                g.this.k.onAdShowFail(adError);
            }
        }
    }

    class c implements Runnable {

        class a implements Runnable {

            /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.g$c$a$a, reason: collision with other inner class name */
            class RunnableC0059a implements Runnable {
                RunnableC0059a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (g.this.f != null) {
                        g.this.f.x();
                    }
                    g gVar = g.this;
                    gVar.f = gVar.g;
                    g.this.n();
                }
            }

            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (g.this.f1789a == 0 || g.this.g == null) {
                    return;
                }
                View viewK = g.this.g.K();
                if (viewK != null) {
                    g.this.h.a(viewK);
                    g.this.j.postDelayed(new RunnableC0059a(), 250L);
                } else {
                    if (g.this.g != null) {
                        g.this.g.x();
                    }
                    g.this.n();
                }
                g.this.o();
            }
        }

        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.j.post(new a());
        }
    }

    class d implements RefreshableBannerView.c {
        d() {
        }

        @Override // com.bytedance.msdk.core.views.RefreshableBannerView.c
        public void a(boolean z) {
            if (!z) {
                g.this.m();
            } else {
                if (g.this.f1789a == 0) {
                    return;
                }
                g.this.o();
            }
        }
    }

    public g(Activity activity, String str) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVarB;
        this.f1789a = 0;
        this.f1790b = activity;
        this.f1791c = str;
        this.f = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c(activity, str);
        this.h = new RefreshableBannerView(activity);
        this.h.addOnAttachStateChangeListener(this.l);
        this.h.setVisibilityChangeListener(new d());
        this.i = new Handler(ThreadHelper.getMSDKThreadLooper());
        this.j = new Handler(Looper.getMainLooper());
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c cVarF = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f();
        if (cVarF == null || (cVarB = cVarF.b(this.f1791c)) == null) {
            return;
        }
        int iK = cVarB.k();
        if (iK < 10000 || iK > 180000) {
            Logger.d("TMe", "---==-----banner轮播时间下发不在10*1000～180*1000范围内：" + iK + "，禁止banner轮播");
            return;
        }
        this.f1789a = iK;
        Logger.d("TMe", "---==-----banner轮播时间：" + this.f1789a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.i.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.g = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c(this.f1790b, this.f1791c);
        this.g.a(this.m);
        this.g.a(this.d, this.e, (GMBannerAdLoadCallback) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.i.removeCallbacksAndMessages(null);
        this.i.postDelayed(this.n, this.f1789a);
    }

    public int j() {
        return this.f1789a;
    }

    public String k() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar != null) {
            return cVar.j();
        }
        return null;
    }

    @VisibleForTesting
    public boolean l() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar == null) {
            return false;
        }
        cVar.L();
        return false;
    }

    public List<AdLoadInfo> b() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        return cVar != null ? cVar.getAdLoadInfoList() : new ArrayList();
    }

    public int c() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar != null) {
            return cVar.z();
        }
        return -2;
    }

    @NonNull
    public String d() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        return cVar != null ? cVar.A() : "-2";
    }

    @Nullable
    public View e() {
        View viewK;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar == null || (viewK = cVar.K()) == null) {
            return null;
        }
        this.h.removeAllViews();
        ViewParent parent = viewK.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(viewK);
        }
        this.h.addView(viewK);
        return this.h;
    }

    public GMAdEcpmInfo f() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }

    public List<GMAdEcpmInfo> g() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar != null) {
            return cVar.c();
        }
        return null;
    }

    public List<GMAdEcpmInfo> h() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar != null) {
            return cVar.f();
        }
        return null;
    }

    @NonNull
    public String i() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        return cVar != null ? cVar.B() : "-2";
    }

    public void a(AdSlot adSlot, GMAdSlotBase gMAdSlotBase, GMBannerAdLoadCallback gMBannerAdLoadCallback) {
        this.d = adSlot;
        this.e = gMAdSlotBase;
        this.f.a(adSlot, gMAdSlotBase, gMBannerAdLoadCallback);
    }

    public void a(GMBannerAdListener gMBannerAdListener) {
        this.k = gMBannerAdListener;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar != null) {
            cVar.a(this.m);
        }
    }

    public void a() {
        this.j.removeCallbacksAndMessages(null);
        m();
        this.h.removeOnAttachStateChangeListener(this.l);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar != null) {
            cVar.x();
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar2 = this.g;
        if (cVar2 != null) {
            cVar2.x();
        }
    }

    public void a(boolean z) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_19do.c cVar = this.f;
        if (cVar != null) {
            cVar.a(z);
        }
    }
}
