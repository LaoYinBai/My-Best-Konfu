package com.qq.e.ads.interstitial3;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.qq.e.ads.AbstractAD;
import com.qq.e.ads.LiteAbstractAD;
import com.qq.e.ads.interstitial2.ADRewardListener;
import com.qq.e.ads.nativ.express2.VideoOption2;
import com.qq.e.ads.rewardvideo.ServerSideVerificationOptions;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.pi.UIADI2;
import com.qq.e.comm.util.AdErrorConvertor;
import com.qq.e.comm.util.CallbackUtil;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.VideoAdValidity;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ExpressInterstitialAD extends LiteAbstractAD<UIADI2> {
    private boolean g;
    private boolean h;
    private VideoOption2 i;
    private AdListenerAdapter j;
    private ExpressInterstitialAdListener k;
    private ServerSideVerificationOptions l;

    private static class AdListenerAdapter implements ADListener {
        ExpressInterstitialAdListener a;
        ADRewardListener b;

        AdListenerAdapter(ExpressInterstitialAdListener expressInterstitialAdListener) {
            this.a = expressInterstitialAdListener;
        }

        public void onADEvent(ADEvent aDEvent) {
            if (this.a == null || aDEvent == null) {
                return;
            }
            switch (aDEvent.getType()) {
                case 100:
                    this.a.onAdLoaded();
                    break;
                case 101:
                    this.a.onVideoCached();
                    break;
                case 102:
                    this.a.onShow();
                    break;
                case 103:
                    this.a.onExpose();
                    break;
                case 104:
                    if (this.b != null) {
                        Object obj = aDEvent.getParas()[0];
                        Map<String, Object> map = null;
                        if (obj instanceof String) {
                            HashMap map2 = new HashMap();
                            map2.put(HiAnalyticsConstant.HaKey.BI_KEY_TRANSID, String.valueOf(aDEvent.getParas()[0]));
                            map = map2;
                        } else if (obj instanceof Map) {
                            map = (Map) obj;
                        }
                        ADRewardListener aDRewardListener = this.b;
                        if (aDRewardListener != null && map != null) {
                            aDRewardListener.onReward(map);
                            break;
                        }
                    }
                    break;
                case 105:
                    this.a.onClick();
                    break;
                case 106:
                    this.a.onClose();
                    break;
                case 107:
                    if (aDEvent.getParas().length >= 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        this.a.onError(AdErrorConvertor.formatErrorCode(((Integer) aDEvent.getParas()[0]).intValue()));
                        break;
                    } else {
                        AbstractAD.a(ExpressInterstitialAD.class, aDEvent);
                        break;
                    }
                    break;
                case 108:
                    this.a.onVideoComplete();
                    break;
                case 109:
                    if (CallbackUtil.hasRenderSuccessCallback(this.a)) {
                        this.a.onRenderSuccess();
                        break;
                    }
                    break;
                case 110:
                    if (CallbackUtil.hasRenderFailCallback(this.a)) {
                        this.a.onRenderFail();
                        break;
                    }
                    break;
            }
        }

        public void setRewardListener(ADRewardListener aDRewardListener) {
            this.b = aDRewardListener;
        }
    }

    public ExpressInterstitialAD(Context context, String str, ExpressInterstitialAdListener expressInterstitialAdListener) {
        GDTLogger.e("ExpressInterstitialAD 接口将于2022年1月移除，请使用 UnifiedInterstitialAD 接口");
        this.k = expressInterstitialAdListener;
        a(context, str);
    }

    public ExpressInterstitialAD(Context context, String str, String str2, ExpressInterstitialAdListener expressInterstitialAdListener) {
        this(context, str2, expressInterstitialAdListener);
        c();
    }

    protected UIADI2 a(Context context, POFactory pOFactory, String str, String str2) {
        AdListenerAdapter adListenerAdapter = new AdListenerAdapter(this.k);
        this.j = adListenerAdapter;
        return pOFactory.getExpressInterstitialADDelegate(context, str, str2, adListenerAdapter);
    }

    protected /* bridge */ /* synthetic */ Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return a(context, pOFactory, str, str2);
    }

    protected /* bridge */ /* synthetic */ void a(Object obj) {
        d();
    }

    protected void b(int i) {
        ExpressInterstitialAdListener expressInterstitialAdListener = this.k;
        if (expressInterstitialAdListener != null) {
            expressInterstitialAdListener.onError(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public VideoAdValidity checkValidity() {
        boolean zIsVideoCached;
        if (hasShown()) {
            return VideoAdValidity.SHOWED;
        }
        if (SystemClock.elapsedRealtime() > getExpireTimestamp()) {
            return VideoAdValidity.OVERDUE;
        }
        if (isVideoAd()) {
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                zIsVideoCached = ((UIADI2) obj).isVideoCached();
            } else {
                a("isVideoCached");
                zIsVideoCached = false;
            }
            if (!zIsVideoCached) {
                return VideoAdValidity.NONE_CACHE;
            }
        }
        return VideoAdValidity.VALID;
    }

    public void closeHalfScreenAD() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI2) obj).closeHalfScreenAD();
        } else {
            a("closeHalfScreenAD");
        }
    }

    protected void d() {
        ((UIADI2) ((AbstractAD) this).a).setVideoOption(this.i);
        ((UIADI2) ((AbstractAD) this).a).setServerSideVerificationOptions(this.l);
        if (this.g) {
            ((UIADI2) ((AbstractAD) this).a).loadHalfScreenAD();
            this.g = false;
        }
        if (this.h) {
            ((UIADI2) ((AbstractAD) this).a).loadFullScreenAD();
            this.h = false;
        }
    }

    public void destroy() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI2) obj).destroy();
        } else {
            a("destroy");
        }
    }

    public long getExpireTimestamp() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((UIADI2) obj).getExpireTimestamp();
        }
        a("getExpireTimestamp");
        return 0L;
    }

    public int getVideoDuration() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((UIADI2) obj).getVideoDuration();
        }
        a("getVideoDuration");
        return 0;
    }

    public boolean hasShown() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((UIADI2) obj).hasShown();
        }
        a("hasShown");
        return false;
    }

    public boolean isVideoAd() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((UIADI2) obj).isVideoAd();
        }
        a("isVideoAd");
        return false;
    }

    public void loadFullScreenAD() {
        if (a()) {
            if (!b()) {
                this.h = true;
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                ((UIADI2) obj).loadFullScreenAD();
            } else {
                a("loadFullScreenAD");
            }
        }
    }

    public void loadHalfScreenAD() {
        if (a()) {
            if (!b()) {
                this.g = true;
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                ((UIADI2) obj).loadHalfScreenAD();
            } else {
                a("loadHalfScreenAD");
            }
        }
    }

    public void setRewardListener(ADRewardListener aDRewardListener) {
        AdListenerAdapter adListenerAdapter = this.j;
        if (adListenerAdapter != null) {
            adListenerAdapter.setRewardListener(aDRewardListener);
        }
    }

    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI2) obj).setServerSideVerificationOptions(serverSideVerificationOptions);
        } else {
            this.l = serverSideVerificationOptions;
        }
    }

    public void setVideoOption(VideoOption2 videoOption2) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI2) obj).setVideoOption(videoOption2);
        } else {
            this.i = videoOption2;
        }
    }

    public void showFullScreenAD(Activity activity) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI2) obj).showFullScreenAD(activity);
        } else {
            a("showFullScreenAD");
        }
    }

    public void showHalfScreenAD(Activity activity) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((UIADI2) obj).showHalfScreenAD(activity);
        } else {
            a("showHalfScreenAD");
        }
    }
}
