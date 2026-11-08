package com.qq.e.ads.rewardvideo2;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import com.qq.e.ads.AbstractAD;
import com.qq.e.ads.LiteAbstractAD;
import com.qq.e.ads.rewardvideo.ServerSideVerificationOptions;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.pi.RVADI2;
import com.qq.e.comm.util.AdErrorConvertor;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.VideoAdValidity;
import java.util.HashMap;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ExpressRewardVideoAD extends LiteAbstractAD<RVADI2> {
    private boolean g;
    private boolean h;
    private ServerSideVerificationOptions i;
    private ExpressRewardVideoAdListener j;

    private static class AdListenerAdapter implements ADListener {
        ExpressRewardVideoAdListener a;

        AdListenerAdapter(ExpressRewardVideoAdListener expressRewardVideoAdListener) {
            this.a = expressRewardVideoAdListener;
        }

        @Override // com.qq.e.comm.adevent.ADListener
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
                    if (aDEvent.getParas().length >= 1 && (aDEvent.getParas()[0] instanceof String)) {
                        HashMap map = new HashMap();
                        map.put(ServerSideVerificationOptions.TRANS_ID, aDEvent.getParas()[0]);
                        this.a.onReward(map);
                        break;
                    } else {
                        AbstractAD.a(ExpressRewardVideoAD.class, aDEvent);
                        break;
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
                        AbstractAD.a(ExpressRewardVideoAD.class, aDEvent);
                        break;
                    }
                    break;
                case 108:
                    this.a.onVideoComplete();
                    break;
            }
        }
    }

    public ExpressRewardVideoAD(Context context, String str, ExpressRewardVideoAdListener expressRewardVideoAdListener) {
        this.h = true;
        GDTLogger.e("ExpressRewardVideoAD 接口将于2022年1月移除，请使用 RewardVideoAD 接口");
        this.j = expressRewardVideoAdListener;
        a(context, str);
    }

    @Deprecated
    public ExpressRewardVideoAD(Context context, String str, String str2, ExpressRewardVideoAdListener expressRewardVideoAdListener) {
        this(context, str2, expressRewardVideoAdListener);
        c();
    }

    protected RVADI2 a(Context context, POFactory pOFactory, String str, String str2) {
        return pOFactory.getExpressRewardVideoADDelegate(context, str, str2, new AdListenerAdapter(this.j));
    }

    protected /* bridge */ /* synthetic */ Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return a(context, pOFactory, str, str2);
    }

    protected void a(Object obj) {
        RVADI2 rvadi2 = (RVADI2) obj;
        rvadi2.setVolumeOn(this.h);
        rvadi2.setServerSideVerificationOptions(this.i);
        if (this.g) {
            rvadi2.loadAD();
            this.g = false;
        }
    }

    protected void b(int i) {
        ExpressRewardVideoAdListener expressRewardVideoAdListener = this.j;
        if (expressRewardVideoAdListener != null) {
            expressRewardVideoAdListener.onError(AdErrorConvertor.formatErrorCode(i));
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
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            zIsVideoCached = ((RVADI2) obj).isVideoCached();
        } else {
            a("isVideoCached");
            zIsVideoCached = false;
        }
        return !zIsVideoCached ? VideoAdValidity.NONE_CACHE : VideoAdValidity.VALID;
    }

    public void destroy() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((RVADI2) obj).destroy();
        }
    }

    public long getExpireTimestamp() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((RVADI2) obj).getExpireTimestamp();
        }
        a("getExpireTimestamp");
        return 0L;
    }

    public int getVideoDuration() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((RVADI2) obj).getVideoDuration();
        }
        a("getVideoDuration");
        return 0;
    }

    public boolean hasShown() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((RVADI2) obj).hasShown();
        }
        a("hasShown");
        return false;
    }

    public void loadAD() {
        if (!b()) {
            this.g = true;
            return;
        }
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((RVADI2) obj).loadAD();
        } else {
            a("loadAD");
        }
    }

    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((RVADI2) obj).setServerSideVerificationOptions(serverSideVerificationOptions);
        } else {
            this.i = serverSideVerificationOptions;
        }
    }

    public void setVolumeOn(boolean z) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((RVADI2) obj).setVolumeOn(z);
        } else {
            this.h = z;
        }
    }

    public void showAD(Activity activity) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((RVADI2) obj).showAD(activity);
        } else {
            a("showAD");
        }
    }
}
