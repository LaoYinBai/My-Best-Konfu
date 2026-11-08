package com.qq.e.ads.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import com.qq.e.ads.AbstractAD;
import com.qq.e.ads.LiteAbstractAD;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.constants.ErrorCode;
import com.qq.e.comm.constants.LoadAdParams;
import com.qq.e.comm.pi.NSPVI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.AdErrorConvertor;
import com.qq.e.comm.util.GDTLogger;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class SplashAD extends LiteAbstractAD<NSPVI> {
    public static final int EVENT_TYPE_AD_CLICKED = 4;
    public static final int EVENT_TYPE_AD_DISMISSED = 1;
    public static final int EVENT_TYPE_AD_EXPOSURE = 6;
    public static final int EVENT_TYPE_AD_LOADED = 7;
    public static final int EVENT_TYPE_AD_PRESENT = 3;
    public static final int EVENT_TYPE_AD_TICK = 5;
    public static final int EVENT_TYPE_AD_ZOOM_OUT = 8;
    public static final int EVENT_TYPE_AD_ZOOM_OUT_PLAY_FINISH = 9;
    public static final int EVENT_TYPE_NO_AD = 2;
    private volatile ViewGroup g;
    private volatile SplashADListener h;
    private volatile LoadAdParams i;
    private volatile boolean j;
    private volatile boolean k;
    private volatile boolean l;
    private volatile int m;
    private volatile byte[] n;
    private View o;
    private int p;
    private View q;

    private class ADListenerAdapter implements ADListener {
        private ADListenerAdapter() {
        }

        @Override // com.qq.e.comm.adevent.ADListener
        public void onADEvent(ADEvent aDEvent) {
            if (SplashAD.this.h == null) {
                GDTLogger.d("SplashADListener == null");
                return;
            }
            Object[] paras = aDEvent.getParas();
            switch (aDEvent.getType()) {
                case 1:
                    SplashAD.this.h.onADDismissed();
                    break;
                case 2:
                    if (paras.length > 0 && (paras[0] instanceof Integer)) {
                        SplashAD.this.h.onNoAD(AdErrorConvertor.formatErrorCode(((Integer) paras[0]).intValue()));
                        break;
                    } else {
                        AbstractAD.a(SplashAD.class, aDEvent);
                        break;
                    }
                case 3:
                    SplashAD.this.h.onADPresent();
                    break;
                case 4:
                    SplashAD.this.h.onADClicked();
                    break;
                case 5:
                    if (paras.length != 1 || !(paras[0] instanceof Long)) {
                        AbstractAD.a(SplashAD.class, aDEvent);
                        break;
                    } else {
                        SplashAD.this.h.onADTick(((Long) paras[0]).longValue());
                        break;
                    }
                    break;
                case 6:
                    SplashAD.this.h.onADExposure();
                    break;
                case 7:
                    if (paras.length != 1 || !(paras[0] instanceof Long)) {
                        AbstractAD.a(SplashAD.class, aDEvent);
                        break;
                    } else {
                        SplashAD.this.h.onADLoaded(((Long) paras[0]).longValue());
                        break;
                    }
                case 8:
                    if (SplashAD.this.h instanceof SplashADZoomOutListener) {
                        ((SplashADZoomOutListener) SplashAD.this.h).onZoomOut();
                        break;
                    }
                    break;
                case 9:
                    if (SplashAD.this.h instanceof SplashADZoomOutListener) {
                        ((SplashADZoomOutListener) SplashAD.this.h).onZoomOutPlayFinish();
                        break;
                    }
                    break;
            }
        }
    }

    @Deprecated
    public SplashAD(Context context, View view, String str, SplashADListener splashADListener, int i) {
        this(context, view, str, splashADListener, i, (View) null);
    }

    @Deprecated
    public SplashAD(Context context, View view, String str, SplashADListener splashADListener, int i, View view2) {
        this(context, view, str, splashADListener, i, (Map) null, view2);
    }

    @Deprecated
    public SplashAD(Context context, View view, String str, SplashADListener splashADListener, int i, Map map, View view2) {
        this.j = false;
        this.h = splashADListener;
        this.o = view;
        this.p = i;
        this.q = view2;
        a(context, str);
    }

    @Deprecated
    public SplashAD(Context context, View view, String str, String str2, SplashADListener splashADListener, int i) {
        this(context, view, str, str2, splashADListener, i, (View) null);
    }

    @Deprecated
    public SplashAD(Context context, View view, String str, String str2, SplashADListener splashADListener, int i, View view2) {
        this(context, view, str, str2, splashADListener, i, null, view2);
    }

    @Deprecated
    public SplashAD(Context context, View view, String str, String str2, SplashADListener splashADListener, int i, Map map, View view2) {
        this(context, view, str2, splashADListener, i, map, view2);
        c();
    }

    public SplashAD(Context context, String str, SplashADListener splashADListener) {
        this(context, str, splashADListener, 0);
    }

    public SplashAD(Context context, String str, SplashADListener splashADListener, int i) {
        this(context, (View) null, str, splashADListener, i);
    }

    public SplashAD(Context context, String str, SplashADListener splashADListener, int i, View view) {
        this(context, (View) null, str, splashADListener, i, (Map) null, view);
    }

    public SplashAD(Context context, String str, SplashADListener splashADListener, int i, Map map, View view) {
        this(context, (View) null, str, splashADListener, i, map, view);
    }

    public SplashAD(Context context, String str, SplashADListener splashADListener, int i, Map map, View view, String str2) {
        this.j = false;
        this.h = splashADListener;
        this.p = i;
        this.q = view;
        a(context, str, str2);
    }

    @Deprecated
    public SplashAD(Context context, String str, String str2, SplashADListener splashADListener) {
        this(context, str, str2, splashADListener, 0);
    }

    @Deprecated
    public SplashAD(Context context, String str, String str2, SplashADListener splashADListener, int i) {
        this(context, (View) null, str, str2, splashADListener, i);
    }

    private void a(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            GDTLogger.e("传入参数有误：传入container参数为空");
            a(ErrorCode.CONSTRUCTOR_PARAM_ERROR);
            return;
        }
        Object obj = ((AbstractAD) this).a;
        if (obj == null) {
            this.l = z;
            this.g = viewGroup;
        } else if (z) {
            ((NSPVI) obj).fetchFullScreenAndShowIn(viewGroup);
        } else {
            ((NSPVI) obj).fetchAndShowIn(viewGroup);
        }
    }

    private void a(boolean z) {
        if (a()) {
            if (!b()) {
                this.l = z;
                this.k = true;
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj == null) {
                a("fetchAdInner");
            } else if (z) {
                ((NSPVI) obj).fetchFullScreenAdOnly();
            } else {
                ((NSPVI) obj).fetchAdOnly();
            }
        }
    }

    private void b(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            GDTLogger.e("传入参数错误，container参数为空");
            a(ErrorCode.CONSTRUCTOR_PARAM_ERROR);
            return;
        }
        Object obj = ((AbstractAD) this).a;
        if (obj == null) {
            this.g = viewGroup;
        } else if (z) {
            ((NSPVI) obj).showFullScreenAd(viewGroup);
        } else {
            ((NSPVI) obj).showAd(viewGroup);
        }
    }

    protected Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getNativeSplashAdView(context, str, str2, str3);
    }

    protected void b(int i) {
        if (this.h != null) {
            this.h.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public void fetchAdOnly() {
        a(false);
    }

    public void fetchAndShowIn(ViewGroup viewGroup) {
        a(viewGroup, false);
    }

    public void fetchFullScreenAdOnly() {
        a(true);
    }

    public void fetchFullScreenAndShowIn(ViewGroup viewGroup) {
        a(viewGroup, true);
    }

    public String getAdNetWorkName() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((NSPVI) obj).getAdNetWorkName();
        }
        a("getAdNetWorkName");
        return null;
    }

    public Map getExt() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return NSPVI.ext;
        }
        a("getExt");
        return null;
    }

    public Bitmap getZoomOutBitmap() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((NSPVI) obj).getZoomOutBitmap();
        }
        a("getZoomOutBitmap");
        return null;
    }

    public void preLoad() {
        if (a()) {
            if (!b()) {
                this.j = true;
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                ((NSPVI) obj).preload();
            } else {
                a("preLoad");
            }
        }
    }

    @Deprecated
    public void setAdLogoMargin(int i, int i2) {
    }

    public void setDeveloperLogo(int i) {
        Object obj = ((AbstractAD) this).a;
        if (obj == null) {
            this.m = i;
        } else {
            ((NSPVI) obj).setDeveloperLogo(i);
        }
    }

    public void setDeveloperLogo(byte[] bArr) {
        Object obj = ((AbstractAD) this).a;
        if (obj == null) {
            this.n = bArr;
        } else {
            ((NSPVI) obj).setDeveloperLogo(bArr);
        }
    }

    public void setLoadAdParams(LoadAdParams loadAdParams) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NSPVI) obj).setLoadAdParams(loadAdParams);
        } else {
            this.i = loadAdParams;
        }
    }

    @Deprecated
    public void setPreloadView(View view) {
    }

    public void showAd(ViewGroup viewGroup) {
        b(viewGroup, false);
    }

    public void showFullScreenAd(ViewGroup viewGroup) {
        b(viewGroup, true);
    }

    public void zoomOutAnimationFinish() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((NSPVI) obj).zoomOutAnimationFinish();
        } else {
            a("zoomOutAnimationFinish");
        }
    }

    protected void a(Object obj) {
        NSPVI nspvi = (NSPVI) obj;
        if (this.i != null) {
            nspvi.setLoadAdParams(this.i);
        }
        if (this.m != 0) {
            nspvi.setDeveloperLogo(this.m);
        }
        if (this.n != null) {
            nspvi.setDeveloperLogo(this.n);
        }
        nspvi.setFetchDelay(this.p);
        nspvi.setAdListener(new ADListenerAdapter());
        nspvi.setSkipView(this.o);
        nspvi.setFloatView(this.q);
        if ((this.h instanceof SplashADZoomOutListener) && ((SplashADZoomOutListener) this.h).isSupportZoomOut()) {
            nspvi.setSupportZoomOut(true);
        }
        if (this.g != null) {
            if (this.l) {
                fetchFullScreenAndShowIn(this.g);
            } else {
                fetchAndShowIn(this.g);
            }
        }
        if (this.j) {
            nspvi.preload();
            this.j = false;
        }
        if (this.k) {
            if (this.l) {
                nspvi.fetchFullScreenAdOnly();
            } else {
                nspvi.fetchAdOnly();
            }
            this.k = false;
        }
    }
}
