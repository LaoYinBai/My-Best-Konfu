package com.qq.e.ads.rewardvideo;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import com.qq.e.ads.AbstractAD;
import com.qq.e.ads.LiteAbstractAD;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.constants.Constants;
import com.qq.e.comm.constants.LoadAdParams;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.pi.RVADI;
import com.qq.e.comm.util.AdErrorConvertor;
import com.qq.e.comm.util.VideoAdValidity;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RewardVideoAD extends LiteAbstractAD<RVADI> {
    public static final int REWARD_TYPE_PAGE = 1;
    public static final int REWARD_TYPE_VIDEO = 0;
    private RewardVideoADListener g;
    private volatile boolean h;
    private boolean i;
    private Map<String, String> j;
    private LoadAdParams k;
    private ServerSideVerificationOptions l;
    private boolean m;

    public static class ADListenerAdapter implements ADListener {
        public static final int EVENT_TYPE_ON_AD_CLICK = 6;
        public static final int EVENT_TYPE_ON_AD_CLOSE = 8;
        public static final int EVENT_TYPE_ON_AD_EXPOSE = 4;
        public static final int EVENT_TYPE_ON_AD_LOADED = 1;
        public static final int EVENT_TYPE_ON_AD_SHOW = 3;
        public static final int EVENT_TYPE_ON_ERROR = 9;
        public static final int EVENT_TYPE_ON_REWARD = 5;
        public static final int EVENT_TYPE_ON_VIDEO_CACHED = 2;
        public static final int EVENT_TYPE_ON_VIDEO_COMPLETE = 7;
        private CacheCallback a;
        public RewardVideoADListener adListener;
        private WeakReference<RewardVideoAD> b;

        interface CacheCallback {
            void onCached();

            void onLoaded();
        }

        public ADListenerAdapter(RewardVideoADListener rewardVideoADListener) {
            this.adListener = rewardVideoADListener;
        }

        public ADListenerAdapter(RewardVideoADListener rewardVideoADListener, CacheCallback cacheCallback) {
            this.adListener = rewardVideoADListener;
            this.a = cacheCallback;
        }

        @Override // com.qq.e.comm.adevent.ADListener
        public void onADEvent(ADEvent aDEvent) {
            switch (aDEvent.getType()) {
                case 1:
                    this.adListener.onADLoad();
                    CacheCallback cacheCallback = this.a;
                    if (cacheCallback != null) {
                        cacheCallback.onLoaded();
                        break;
                    }
                    break;
                case 2:
                    CacheCallback cacheCallback2 = this.a;
                    if (cacheCallback2 != null) {
                        cacheCallback2.onCached();
                    }
                    this.adListener.onVideoCached();
                    break;
                case 3:
                    this.adListener.onADShow();
                    break;
                case 4:
                    this.adListener.onADExpose();
                    break;
                case 5:
                    if (aDEvent.getParas().length >= 1 && (aDEvent.getParas()[0] instanceof String)) {
                        HashMap map = new HashMap();
                        map.put(ServerSideVerificationOptions.TRANS_ID, aDEvent.getParas()[0]);
                        this.adListener.onReward(map);
                        break;
                    } else {
                        AbstractAD.a(RewardVideoAD.class, aDEvent);
                        break;
                    }
                    break;
                case 6:
                    if (this.b != null && aDEvent.getParas().length == 1) {
                        Object obj = aDEvent.getParas()[0];
                        if ((obj instanceof String) && this.b.get() != null) {
                            this.b.get().setExt((String) obj);
                        }
                    }
                    this.adListener.onADClick();
                    break;
                case 7:
                    this.adListener.onVideoComplete();
                    break;
                case 8:
                    this.adListener.onADClose();
                    break;
                case 9:
                    if (aDEvent.getParas().length >= 1 && (aDEvent.getParas()[0] instanceof Integer)) {
                        this.adListener.onError(AdErrorConvertor.formatErrorCode(((Integer) aDEvent.getParas()[0]).intValue()));
                        break;
                    } else {
                        AbstractAD.a(RewardVideoAD.class, aDEvent);
                        break;
                    }
                    break;
            }
        }

        public void setBase(RewardVideoAD rewardVideoAD) {
            this.b = new WeakReference<>(rewardVideoAD);
        }
    }

    public RewardVideoAD(Context context, String str, RewardVideoADListener rewardVideoADListener) {
        this(context, str, rewardVideoADListener, true);
    }

    public RewardVideoAD(Context context, String str, RewardVideoADListener rewardVideoADListener, boolean z) {
        this.j = new HashMap();
        this.k = null;
        this.g = rewardVideoADListener;
        this.m = z;
        a(context, str);
    }

    public RewardVideoAD(Context context, String str, RewardVideoADListener rewardVideoADListener, boolean z, String str2) {
        this.j = new HashMap();
        this.k = null;
        this.g = rewardVideoADListener;
        this.m = z;
        a(context, str, str2);
    }

    @Deprecated
    public RewardVideoAD(Context context, String str, String str2, RewardVideoADListener rewardVideoADListener) {
        this(context, str, str2, rewardVideoADListener, true);
    }

    @Deprecated
    public RewardVideoAD(Context context, String str, String str2, RewardVideoADListener rewardVideoADListener, boolean z) {
        this(context, str2, rewardVideoADListener, z);
        c();
    }

    protected Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        ADListenerAdapter aDListenerAdapter = new ADListenerAdapter(this.g, new ADListenerAdapter.CacheCallback() { // from class: com.qq.e.ads.rewardvideo.RewardVideoAD.1
            @Override // com.qq.e.ads.rewardvideo.RewardVideoAD.ADListenerAdapter.CacheCallback
            public void onCached() {
                RewardVideoAD.this.i = true;
            }

            @Override // com.qq.e.ads.rewardvideo.RewardVideoAD.ADListenerAdapter.CacheCallback
            public void onLoaded() {
                RewardVideoAD.this.i = false;
            }
        });
        aDListenerAdapter.setBase(this);
        return pOFactory.getRewardVideoADDelegate(context, str, str2, str3, aDListenerAdapter);
    }

    protected void b(int i) {
        RewardVideoADListener rewardVideoADListener = this.g;
        if (rewardVideoADListener != null) {
            rewardVideoADListener.onError(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public VideoAdValidity checkValidity() {
        Object obj;
        return hasShown() ? VideoAdValidity.SHOWED : SystemClock.elapsedRealtime() > getExpireTimestamp() - 1000 ? VideoAdValidity.OVERDUE : (this.i || (obj = ((AbstractAD) this).a) == null || ((RVADI) obj).getRewardAdType() != 0) ? VideoAdValidity.VALID : VideoAdValidity.NONE_CACHE;
    }

    public String getAdNetWorkName() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((RVADI) obj).getAdNetWorkName();
        }
        a("getAdNetWorkName");
        return null;
    }

    public long getExpireTimestamp() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((RVADI) obj).getExpireTimestamp();
        }
        a("getExpireTimestamp");
        return 0L;
    }

    public Map<String, String> getExts() {
        return this.j;
    }

    public int getRewardAdType() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((RVADI) obj).getRewardAdType();
        }
        a("getRewardAdType");
        return 0;
    }

    public int getVideoDuration() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((RVADI) obj).getVideoDuration();
        }
        a("getVideoDuration");
        return 0;
    }

    public boolean hasShown() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            return ((RVADI) obj).hasShown();
        }
        a("hasShown");
        return false;
    }

    public void loadAD() {
        if (a()) {
            if (!b()) {
                this.h = true;
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                ((RVADI) obj).loadAD();
            } else {
                a("loadAD");
            }
        }
    }

    public void setExt(String str) {
        this.j.put(Constants.KEYS.EXPOSED_CLICK_URL_KEY, str);
    }

    public void setLoadAdParams(LoadAdParams loadAdParams) {
        this.k = loadAdParams;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((RVADI) obj).setLoadAdParams(loadAdParams);
        }
    }

    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        this.l = serverSideVerificationOptions;
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((RVADI) obj).setServerSideVerificationOptions(serverSideVerificationOptions);
        }
    }

    public void showAD() {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((RVADI) obj).showAD();
        } else {
            a("showAD");
        }
    }

    public void showAD(Activity activity) {
        Object obj = ((AbstractAD) this).a;
        if (obj != null) {
            ((RVADI) obj).showAD(activity);
        } else {
            a("showAD");
        }
    }

    protected void a(Object obj) {
        RVADI rvadi = (RVADI) obj;
        rvadi.setVolumeOn(this.m);
        rvadi.setLoadAdParams(this.k);
        rvadi.setServerSideVerificationOptions(this.l);
        if (this.h) {
            loadAD();
        }
    }
}
