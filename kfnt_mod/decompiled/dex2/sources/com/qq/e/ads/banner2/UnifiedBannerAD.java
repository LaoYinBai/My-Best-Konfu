package com.qq.e.ads.banner2;

import android.app.Activity;
import android.content.Context;
import com.qq.e.ads.AbstractAD;
import com.qq.e.ads.LiteAbstractAD;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.comm.constants.LoadAdParams;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.pi.UBVI;
import com.qq.e.comm.util.AdErrorConvertor;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class UnifiedBannerAD extends LiteAbstractAD<UBVI> {
    private UnifiedBannerADListener g;
    private DownAPPConfirmPolicy h;
    private AtomicInteger i = new AtomicInteger(0);
    private int j = 30;
    private LoadAdParams k = null;
    private UnifiedBannerView l;

    UnifiedBannerAD(Activity activity, UnifiedBannerView unifiedBannerView, String str, UnifiedBannerADListener unifiedBannerADListener) {
        this.g = unifiedBannerADListener;
        this.l = unifiedBannerView;
        a(activity, str);
    }

    UnifiedBannerAD(Activity activity, UnifiedBannerView unifiedBannerView, String str, String str2, UnifiedBannerADListener unifiedBannerADListener) {
        this.g = unifiedBannerADListener;
        this.l = unifiedBannerView;
        a(activity, str, str2);
    }

    protected Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getUnifiedBannerViewDelegate(this.l, (Activity) context, str, str2, str3, this.g);
    }

    void a(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        Object obj;
        this.h = downAPPConfirmPolicy;
        if (downAPPConfirmPolicy == null || (obj = ((AbstractAD) this).a) == null) {
            return;
        }
        ((UBVI) obj).setDownAPPConfirmPolicy(downAPPConfirmPolicy);
    }

    protected /* bridge */ /* synthetic */ void a(Object obj) {
        d();
    }

    protected void b(int i) {
        UnifiedBannerADListener unifiedBannerADListener = this.g;
        if (unifiedBannerADListener != null) {
            unifiedBannerADListener.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    void c(int i) {
        this.j = i;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UBVI) obj).setRefresh(i);
        }
    }

    protected void d() {
        Object obj;
        DownAPPConfirmPolicy downAPPConfirmPolicy = this.h;
        if (downAPPConfirmPolicy != null) {
            this.h = downAPPConfirmPolicy;
            if (downAPPConfirmPolicy != null && (obj = ((AbstractAD) this).a) != null) {
                ((UBVI) obj).setDownAPPConfirmPolicy(downAPPConfirmPolicy);
            }
        }
        int i = this.j;
        this.j = i;
        Object obj2 = ((AbstractAD) this).a;
        if (obj2 != null) {
            ((UBVI) obj2).setRefresh(i);
        }
        LoadAdParams loadAdParams = this.k;
        this.k = loadAdParams;
        Object obj3 = ((AbstractAD) this).a;
        if (obj3 != null) {
            ((UBVI) obj3).setLoadAdParams(loadAdParams);
        }
        while (this.i.getAndDecrement() > 0) {
            loadAD();
        }
    }

    void destroy() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UBVI) obj).destroy();
        } else {
            a("destroy");
        }
    }

    public String getAdNetWorkName() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((UBVI) obj).getAdNetWorkName();
        }
        a("getAdNetWorkName");
        return null;
    }

    Map getExt() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return UBVI.ext;
        }
        a("getExt");
        return null;
    }

    void loadAD() {
        if (a()) {
            if (!b()) {
                this.i.incrementAndGet();
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                ((UBVI) obj).fetchAd();
            } else {
                a("loadAD");
            }
        }
    }

    void onWindowFocusChanged(boolean z) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UBVI) obj).onWindowFocusChanged(z);
        }
    }

    void setLoadAdParams(LoadAdParams loadAdParams) {
        this.k = loadAdParams;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UBVI) obj).setLoadAdParams(loadAdParams);
        }
    }
}
