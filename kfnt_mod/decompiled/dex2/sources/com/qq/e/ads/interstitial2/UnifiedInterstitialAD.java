package com.qq.e.ads.interstitial2;

import android.app.Activity;
import android.content.Context;
import com.qq.e.ads.AbstractAD;
import com.qq.e.ads.LiteAbstractAD;
import com.qq.e.ads.cfg.VideoOption;
import com.qq.e.ads.rewardvideo.ServerSideVerificationOptions;
import com.qq.e.comm.constants.LoadAdParams;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.pi.UIADI;
import com.qq.e.comm.util.AdErrorConvertor;
import com.qq.e.comm.util.GDTLogger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class UnifiedInterstitialAD extends LiteAbstractAD<UIADI> {
    private AtomicInteger g;
    private AtomicInteger h;
    private volatile UnifiedInterstitialMediaListener i;
    private volatile VideoOption j;
    private volatile int k;
    private volatile int l;
    private volatile LoadAdParams m;
    private UnifiedInterstitialADListener n;
    private volatile ADRewardListener o;
    private ServerSideVerificationOptions p;

    public UnifiedInterstitialAD(Activity activity, String str, UnifiedInterstitialADListener unifiedInterstitialADListener) {
        this(activity, str, unifiedInterstitialADListener, (Map) null);
    }

    public UnifiedInterstitialAD(Activity activity, String str, UnifiedInterstitialADListener unifiedInterstitialADListener, Map map) {
        this.g = new AtomicInteger(0);
        this.h = new AtomicInteger(0);
        this.n = unifiedInterstitialADListener;
        a(activity, str);
    }

    public UnifiedInterstitialAD(Activity activity, String str, UnifiedInterstitialADListener unifiedInterstitialADListener, Map map, String str2) {
        this.g = new AtomicInteger(0);
        this.h = new AtomicInteger(0);
        this.n = unifiedInterstitialADListener;
        a(activity, str, str2);
    }

    @Deprecated
    public UnifiedInterstitialAD(Activity activity, String str, String str2, UnifiedInterstitialADListener unifiedInterstitialADListener) {
        this(activity, str, str2, unifiedInterstitialADListener, (Map) null);
    }

    @Deprecated
    public UnifiedInterstitialAD(Activity activity, String str, String str2, UnifiedInterstitialADListener unifiedInterstitialADListener, Map map) {
        this(activity, str2, unifiedInterstitialADListener, map);
        c();
    }

    protected Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getUnifiedInterstitialADDelegate((Activity) context, str, str2, str3, this.n);
    }

    protected /* bridge */ /* synthetic */ void a(Object obj) {
        d();
    }

    protected void b(int i) {
        UnifiedInterstitialADListener unifiedInterstitialADListener = this.n;
        if (unifiedInterstitialADListener != null) {
            unifiedInterstitialADListener.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public void close() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).close();
        }
    }

    protected void d() {
        setVideoOption(this.j);
        setMediaListener(this.i);
        setMinVideoDuration(this.k);
        setMaxVideoDuration(this.l);
        setRewardListener(this.o);
        setLoadAdParams(this.m);
        setServerSideVerificationOptions(this.p);
        while (this.g.getAndDecrement() > 0) {
            loadAD();
        }
        while (this.h.getAndDecrement() > 0) {
            loadFullScreenAD();
        }
    }

    public void destroy() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).destory();
        } else {
            a("destroy");
        }
    }

    public String getAdNetWorkName() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((UIADI) obj).getAdNetWorkName();
        }
        a("getAdNetWorkName");
        return null;
    }

    public int getAdPatternType() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((UIADI) obj).getAdPatternType();
        }
        a("getAdPatternType");
        return 0;
    }

    public Map getExt() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return UIADI.ext;
        }
        a("getExt");
        return null;
    }

    public int getVideoDuration() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((UIADI) obj).getVideoDuration();
        }
        a("getVideoDuration");
        return 0;
    }

    public boolean isValid() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((UIADI) obj).isValid();
        }
        a("isValid");
        return false;
    }

    public void loadAD() {
        if (a()) {
            if (!b()) {
                this.g.incrementAndGet();
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                ((UIADI) obj).loadAd();
            } else {
                a("loadAD");
            }
        }
    }

    public void loadFullScreenAD() {
        if (a()) {
            if (!b()) {
                this.h.incrementAndGet();
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                ((UIADI) obj).loadFullScreenAD();
            } else {
                a("loadFullScreenAD");
            }
        }
    }

    public void setLoadAdParams(LoadAdParams loadAdParams) {
        this.m = loadAdParams;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).setLoadAdParams(this.m);
        }
    }

    public void setMaxVideoDuration(int i) {
        this.l = i;
        if (this.l > 0 && this.k > this.l) {
            GDTLogger.e("maxVideoDuration 设置值非法，不得小于minVideoDuration");
        }
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).setMaxVideoDuration(i);
        }
    }

    public void setMediaListener(UnifiedInterstitialMediaListener unifiedInterstitialMediaListener) {
        this.i = unifiedInterstitialMediaListener;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).setMediaListener(unifiedInterstitialMediaListener);
        }
    }

    public void setMinVideoDuration(int i) {
        this.k = i;
        if (this.l > 0 && this.k > this.l) {
            GDTLogger.e("minVideoDuration 设置值非法，不得大于maxVideoDuration");
        }
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).setMinVideoDuration(i);
        }
    }

    public void setRewardListener(ADRewardListener aDRewardListener) {
        this.o = aDRewardListener;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).setRewardListener(aDRewardListener);
        }
    }

    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        this.p = serverSideVerificationOptions;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).setServerSideVerificationOptions(serverSideVerificationOptions);
        }
    }

    public void setVideoOption(VideoOption videoOption) {
        this.j = videoOption;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).setVideoOption(videoOption);
        }
    }

    @Deprecated
    public void setVideoPlayPolicy(int i) {
    }

    public void show() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).show();
        } else {
            a("show");
        }
    }

    public void show(Activity activity) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).show(activity);
        } else {
            a("show");
        }
    }

    public void showAsPopupWindow() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).showAsPopupWindow();
        } else {
            a("showAsPopupWindow");
        }
    }

    public void showAsPopupWindow(Activity activity) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).showAsPopupWindow(activity);
        } else {
            a("showAsPopupWindow");
        }
    }

    public void showFullScreenAD(Activity activity) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI) obj).showFullScreenAD(activity);
        } else {
            a("showFullScreenAD");
        }
    }
}
