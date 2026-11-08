package com.sigmob.windad.base;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.WindowInsets;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.services.h;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.a;
import com.sigmob.sdk.common.d.b;
import com.sigmob.sdk.common.e.n;
import com.sigmob.sdk.common.e.o;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.common.models.AdStatus;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntityWind;
import com.sigmob.sdk.common.mta.PointEntityWindError;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.WindAdLifecycleManager;
import com.sigmob.windad.WindAdRequest;
import com.sigmob.windad.WindAds;
import com.sigmob.windad.common.AdapterClsUtil;
import com.sigmob.windad.common.PointEntityWindUtils;
import com.sigmob.windad.rewardedVideo.WindRewardInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindAdRequestController implements WindAdConnector {
    private int C;
    private final String a;
    private WindAdRequestLoadLister d;
    private WindAdRequestShowLister e;
    private WindAdInitListener h;
    private String i;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private WindAdRequest z;
    private final int b = 1000;
    private final int c = 2000;
    public AdStatus adStatus = AdStatus.AdStatusNone;
    private int g = 4;
    private int j = 0;
    private long k = 15000;
    private int l = 0;
    private String s = "";
    private String A = "";
    private String B = "";
    private List<ADStrategy> u = new ArrayList();
    private List<ADStrategy> v = new ArrayList();
    private Map<String, ADStrategy> w = new HashMap();
    private Map<String, WindAdAdapterError> x = new HashMap();
    private Map<String, WindAdAdapter> y = new HashMap(1);
    private long t = 0;
    private long r = 0;
    private Handler f = new Handler(Looper.getMainLooper()) { // from class: com.sigmob.windad.base.WindAdRequestController.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1000) {
                if (WindAdRequestController.this.adStatus == AdStatus.AdStatusLoading) {
                    SigmobLog.e("loadAd Timeout load id " + WindAdRequestController.this.s);
                    WindAdRequestController.this.t = 0L;
                    WindAdRequestController.this.a("error", null, WindAdError.ERROR_SIGMOB_AD_TIME_OUT.getErrorCode(), WindAdError.ERROR_SIGMOB_AD_TIME_OUT.getMessage());
                    WindAdRequestController.this.c();
                    if (WindAdRequestController.this.d != null) {
                        WindAdRequestController.this.d.onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_AD_TIME_OUT, WindAdRequestController.this.a);
                        return;
                    }
                    return;
                }
                return;
            }
            if (i != 2000) {
                return;
            }
            try {
                Object obj = message.obj;
                if (obj instanceof ADStrategy) {
                    ADStrategy aDStrategy = (ADStrategy) obj;
                    WindAdRequestController.this.adapterDidFailToLoadAd(null, aDStrategy, new WindAdAdapterError(WindAdError.ERROR_SIGMOB_AD_TIME_OUT.getErrorCode(), String.format("loadAd strategy name %s, placementId %s Timeout", aDStrategy.getName(), aDStrategy.getPlacement_id())));
                }
            } catch (Throwable th) {
                SigmobLog.e("loadAd strategy name" + th.getMessage());
            }
        }
    };

    public interface WindAdInitListener {
        void adapterDidInitFailWithStrategy(ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError);

        void adapterDidInitSuccessWithStrategy(ADStrategy aDStrategy);
    }

    public interface WindAdRequestLoadLister {
        void onVideoAdLoadFail(WindAdError windAdError, String str);

        void onVideoAdLoadStart(String str);

        void onVideoAdLoadSuccess(String str);

        void onVideoAdPreLoadFail(String str);

        void onVideoAdPreLoadSuccess(String str);
    }

    public interface WindAdRequestShowLister {
        void onVideoAdClicked(String str);

        void onVideoAdClosed(WindRewardInfo windRewardInfo, String str);

        void onVideoAdPlayComplete(String str);

        void onVideoAdPlayEnd(String str);

        void onVideoAdPlayError(WindAdError windAdError, String str);

        void onVideoAdPlayStart(String str);
    }

    public enum WindFilter {
        WindFilterKeepGoing,
        WindFilterLoadingBreak,
        WindFilterPlayBreak,
        WindFilterLoadInterval,
        WindFilterReadyLoadInterval
    }

    interface onFindReadyAdapterLister {
        void onFailed(String str);

        void onSuccess(WindAdAdapter windAdAdapter, ADStrategy aDStrategy);
    }

    public WindAdRequestController(WindAdRequest windAdRequest, WindAdInitListener windAdInitListener, WindAdRequestLoadLister windAdRequestLoadLister, WindAdRequestShowLister windAdRequestShowLister) {
        this.h = windAdInitListener;
        this.d = windAdRequestLoadLister;
        this.e = windAdRequestShowLister;
        this.z = windAdRequest;
        this.a = windAdRequest.getPlacementId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final WindAdError windAdError) {
        this.t = 0L;
        this.f.removeMessages(1000);
        this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.9
            @Override // java.lang.Runnable
            public void run() {
                if (WindAdRequestController.this.d != null) {
                    WindAdRequestController.this.d.onVideoAdLoadFail(windAdError, WindAdRequestController.this.a);
                }
            }
        });
    }

    private void a(WindAdRequest windAdRequest) {
        this.r = System.currentTimeMillis();
        this.s = UUID.randomUUID().toString();
        this.z = windAdRequest;
        this.z.setLoadId(this.s);
        h.a().a(PointCategory.LOAD, this.s);
        PointEntityWindUtils.WindInit(PointCategory.REQUEST_AGGRE_STRATEGY, this.z, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.7
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    PointEntityWind pointEntityWind = (PointEntityWind) obj;
                    pointEntityWind.setLoad_count(String.valueOf(WindAdRequestController.this.l + 1));
                    pointEntityWind.setLoading_filters(String.valueOf(WindAdRequestController.this.n));
                    pointEntityWind.setGdpr_filters(String.valueOf(WindAdRequestController.this.m));
                    pointEntityWind.setPlaying_filters(String.valueOf(WindAdRequestController.this.p));
                    pointEntityWind.setInterval_filters(String.valueOf(WindAdRequestController.this.q));
                    pointEntityWind.setPldempty_filters(String.valueOf(WindAdRequestController.this.C));
                    pointEntityWind.setLoad_id(WindAdRequestController.this.s);
                    pointEntityWind.setInit_filters(String.valueOf(WindAdRequestController.this.o));
                    pointEntityWind.setIs_enter_sdk("1");
                }
            }
        });
        this.l = 0;
        this.n = 0;
        this.m = 0;
        this.p = 0;
        this.q = 0;
        this.C = 0;
        this.o = 0;
        o.a aVar = new o.a() { // from class: com.sigmob.windad.base.WindAdRequestController.8
            @Override // com.sigmob.sdk.common.e.o.a
            public void onErrorResponse(String str, int i, int i2, String str2) {
                SigmobLog.e("error_code  " + i2 + " msg: " + str2);
                WindAdRequestController.this.c();
                WindAdRequestController.this.t = 0L;
                WindAdError windAdError = WindAdError.getWindAdError(i2);
                if (windAdError == null) {
                    windAdError = WindAdError.ERROR_SIGMOB_STRATEGY_EMPTY;
                    windAdError.setMessage("error_code  " + i2 + " msg: " + str2);
                }
                PointEntityWindUtils.WindError("error", WindAdRequestController.this.z, null, i2, str2, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.8.2
                    @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
                    public void onAddExtra(Object obj) {
                        if (obj instanceof PointEntityWind) {
                            PointEntityWind pointEntityWind = (PointEntityWind) obj;
                            pointEntityWind.setIs_success(Constants.FAIL);
                            pointEntityWind.setIs_out_sdk(Constants.FAIL);
                            pointEntityWind.setLoad_id(WindAdRequestController.this.s);
                        }
                    }
                });
                WindAdRequestController.this.a(windAdError);
            }

            @Override // com.sigmob.sdk.common.e.o.a
            public void onSuccess(List<ADStrategy> list, final n nVar, String str) {
                WindAdRequestController.this.u = list;
                WindAdRequestController.this.t = System.currentTimeMillis();
                if (nVar.a > 0) {
                    WindAdRequestController.this.g = nVar.a;
                }
                if (nVar.b != 0) {
                    WindAdRequestController.this.k = nVar.b * 1000;
                }
                WindAdRequestController.this.e();
                PointEntityWindUtils.WindInit(PointCategory.RESPOND_AGGRE_STRATEGY, WindAdRequestController.this.z, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.8.1
                    @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
                    public void onAddExtra(Object obj) {
                        if (obj instanceof PointEntityWind) {
                            PointEntityWind pointEntityWind = (PointEntityWind) obj;
                            if (nVar.e) {
                                pointEntityWind.setAb_test(String.valueOf(nVar.d));
                            }
                            pointEntityWind.setGroup_id(nVar.c);
                            pointEntityWind.setIs_success("1");
                            pointEntityWind.setVlist_platform(WindAdRequestController.this.a());
                            pointEntityWind.setVlist_element(WindAdRequestController.this.b());
                            pointEntityWind.setLoad_id(WindAdRequestController.this.s);
                        }
                    }
                });
                WindAdRequestController.this.d();
            }
        };
        if (TextUtils.isEmpty(windAdRequest.getBidToken())) {
            o.a(WindAds.sharedAds().getOptions().getUseMediation(), SDKConfig.sharedInstance().getStrategyUrl(), this.z.getAdType(), this.a, this.z.getUserId(), aVar);
        } else {
            o.a(WindAds.sharedAds().getOptions().getUseMediation(), windAdRequest.getBidToken(), WindAds.sharedAds().getOptions().getAppId(), WindAds.sharedAds().getOptions().getAppKey(), this.a, this.z.getAdType(), aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, ADStrategy aDStrategy, int i, String str2) {
        PointEntityWindUtils.WindError(str, this.z, aDStrategy, i, str2, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.6
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    ((PointEntityWind) obj).setLoad_id(WindAdRequestController.this.s);
                }
            }
        });
    }

    private void b(final WindAdError windAdError) {
        if (this.f != null) {
            this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.10
                @Override // java.lang.Runnable
                public void run() {
                    if (WindAdRequestController.this.d != null) {
                        WindAdRequestController.this.d.onVideoAdLoadFail(windAdError, WindAdRequestController.this.a);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final WindAdError windAdError) {
        this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.23
            @Override // java.lang.Runnable
            public void run() {
                if (WindAdRequestController.this.e != null) {
                    WindAdRequestController.this.e.onVideoAdPlayError(windAdError, WindAdRequestController.this.a);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            List<ADStrategy> listSubList = (this.g == 0 || this.g >= this.u.size()) ? this.u : this.u.subList(0, this.g);
            sb.append("[[");
            sb2.append("[[");
            Iterator<ADStrategy> it = listSubList.iterator();
            while (it.hasNext()) {
                ADStrategy next = it.next();
                sb.append(next.getName());
                sb2.append(next.getPlacement_id());
                if (it.hasNext()) {
                    sb.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb2.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
            }
            sb.append("]");
            sb2.append("]");
            if (listSubList.size() < this.u.size()) {
                for (ADStrategy aDStrategy : this.u.subList(listSubList.size(), this.u.size())) {
                    sb.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(aDStrategy.getName());
                    sb2.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb2.append(aDStrategy.getPlacement_id());
                }
            }
            sb.append("]");
            sb2.append("]");
            this.A = sb.toString();
            this.B = sb2.toString();
            SigmobLog.d("updateViolist: " + this.A + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP + this.B);
        } catch (Exception unused) {
            this.A = "";
            this.B = "";
        }
    }

    private String f() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.x.keySet()) {
            HashMap map = new HashMap();
            map.put(str, this.x.get(str).toString());
            arrayList.add(map);
        }
        return b.a(arrayList);
    }

    private void g() {
        if (this.j < this.u.size()) {
            ADStrategy aDStrategy = this.u.get(this.j);
            SigmobLog.d(" internalLoadAd backupIndex " + this.j + " name " + aDStrategy.getName());
            this.j = this.j + 1;
            adapterInitAndLoad(aDStrategy);
        }
    }

    protected String a() {
        if (this.u.size() == 0) {
            return null;
        }
        if (TextUtils.isEmpty(this.A)) {
            e();
        }
        return this.A;
    }

    @Override // com.sigmob.windad.base.WindAdBaseConnector
    public void adapterDidAdClick(WindAdAdapter windAdAdapter, final ADStrategy aDStrategy) {
        h.a().a("click", this.s);
        PointEntityWindUtils.WindTracking("click", this.z, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.11
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    ((PointEntityWind) obj).setLoad_id(WindAdRequestController.this.s);
                }
            }
        });
        this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.12
            @Override // java.lang.Runnable
            public void run() {
                if (WindAdRequestController.this.e != null) {
                    WindAdRequestController.this.e.onVideoAdClicked(aDStrategy.getSig_placement_id());
                }
            }
        });
        SigmobLog.i("adapterDidAdClick() called with: strategy = [" + aDStrategy + "]");
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidCloseAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy, boolean z) {
        SigmobLog.d("adapterDidCloseAd() called with: strategy = [" + aDStrategy + "] " + z);
        if (WindAdLifecycleManager.getInstance() != null) {
            WindAdLifecycleManager.getInstance().removeLifecycleListener(windAdAdapter);
        }
        h.a().a("close", this.s);
        this.r = 0L;
        this.t = 0L;
        if (z) {
            PointEntityWindUtils.WindTracking("reward", this.z, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.20
                @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
                public void onAddExtra(Object obj) {
                    if (obj instanceof PointEntityWind) {
                        ((PointEntityWind) obj).setLoad_id(WindAdRequestController.this.s);
                    }
                }
            });
        }
        PointEntityWindUtils.WindTracking("close", this.z, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.21
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    ((PointEntityWind) obj).setLoad_id(WindAdRequestController.this.s);
                }
            }
        });
        final WindRewardInfo windRewardInfo = new WindRewardInfo(this.z.getAdType(), aDStrategy.getSig_placement_id(), z);
        this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.22
            @Override // java.lang.Runnable
            public void run() {
                if (WindAdRequestController.this.e != null) {
                    WindAdRequestController.this.e.onVideoAdClosed(windRewardInfo, WindAdRequestController.this.a);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidFailToLoadAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError) {
        WindAdError windAdError;
        ADStrategy strategy = getStrategy(aDStrategy);
        SigmobLog.e("adapterDidFailToLoadAd() called with: strategy = [" + strategy.getName() + "], error = [" + windAdAdapterError + "]");
        a("error", strategy, windAdAdapterError.getErrorCode(), windAdAdapterError.getMessage());
        this.f.removeMessages(2000, strategy);
        this.x.put(strategy.getName() + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + strategy.getPlacement_id(), windAdAdapterError);
        g();
        if (this.x.keySet().size() == this.u.size() && this.adStatus == AdStatus.AdStatusLoading) {
            this.t = 0L;
            c();
            this.f.removeMessages(2000);
            this.f.removeMessages(1000);
            if (strategy.getName().equals(Constants.SDK_FOLDER) && this.u.size() == 1) {
                windAdError = WindAdError.getWindAdError(windAdAdapterError.getErrorCode());
            } else {
                windAdError = WindAdError.ERROR_SIGMOB_REQUEST;
                windAdError.setMessage(f());
            }
            if (windAdError == null) {
                windAdError = WindAdError.RTB_SIG_DSP_NO_ADS_ERROR;
                windAdError.setMessage(f());
            }
            this.x.clear();
            a(windAdError);
        }
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidFailToPlayingAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError) {
        try {
            SigmobLog.d("adapterDidFailToPlayingAd() called with: strategy = [" + aDStrategy + "] " + windAdAdapterError.toString());
            WindAdError windAdError = WindAdError.ERROR_SIGMOB_AD_PLAY;
            HashMap map = new HashMap();
            map.put(aDStrategy.getName(), windAdAdapterError);
            windAdError.setMessage(b.a(map));
            a("error", aDStrategy, windAdAdapterError.getErrorCode(), windAdAdapterError.getMessage());
            c(windAdError);
        } catch (Exception e) {
            WindAdError windAdError2 = WindAdError.ERROR_SIGMOB_AD_PLAY;
            windAdError2.setMessage(e.getMessage());
            c(windAdError2);
            e.printStackTrace();
        }
    }

    @Override // com.sigmob.windad.base.WindAdBaseConnector
    public void adapterDidInitFail(WindAdAdapter windAdAdapter, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError) {
        if (this.h != null) {
            this.h.adapterDidInitFailWithStrategy(aDStrategy, windAdAdapterError);
        }
    }

    @Override // com.sigmob.windad.base.WindAdBaseConnector
    public void adapterDidInitSuccess(WindAdAdapter windAdAdapter, ADStrategy aDStrategy) {
        if (this.h != null) {
            this.h.adapterDidInitSuccessWithStrategy(aDStrategy);
        }
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidLoadAdSuccessAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy) {
        ADStrategy strategy = getStrategy(aDStrategy);
        this.f.removeMessages(2000, strategy);
        PointEntityWindUtils.WindTracking(PointCategory.READY, this.z, strategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.15
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    ((PointEntityWind) obj).setLoad_id(WindAdRequestController.this.s);
                }
            }
        });
        strategy.setReady();
        this.j = this.u.size();
        if (this.w != null) {
            this.w.put(strategy.getADStrategyID(), strategy);
        }
        SigmobLog.d("adapterDidLoadAdSuccessAd() called with: strategy = [" + strategy + "]");
        if (this.adStatus == AdStatus.AdStatusReady) {
            SigmobLog.d("has send notify videoAd load success, don't do again");
            return;
        }
        if (this.adStatus != AdStatus.AdStatusLoading) {
            SigmobLog.d("not do videoAd load ad");
            return;
        }
        this.adStatus = AdStatus.AdStatusReady;
        this.f.removeMessages(2000);
        this.f.removeMessages(1000);
        this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.16
            @Override // java.lang.Runnable
            public void run() {
                if (WindAdRequestController.this.d != null) {
                    WindAdRequestController.this.d.onVideoAdLoadSuccess(WindAdRequestController.this.a);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidPlayEndAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy) {
        this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.19
            @Override // java.lang.Runnable
            public void run() {
                if (WindAdRequestController.this.e != null) {
                    WindAdRequestController.this.e.onVideoAdPlayEnd(WindAdRequestController.this.a);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidPreLoadFailAd(WindAdAdapter windAdAdapter, final ADStrategy aDStrategy) {
        if (this.adStatus != AdStatus.AdStatusLoading || this.x.size() < this.u.size()) {
            SigmobLog.d("not do videoAd load ad");
        } else {
            this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.14
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (WindAdRequestController.this.d != null) {
                            WindAdRequestController.this.d.onVideoAdPreLoadFail(aDStrategy.getSig_placement_id());
                        }
                    } catch (Throwable th) {
                        SigmobLog.e(th.getMessage());
                    }
                }
            });
        }
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidPreLoadSuccessAd(WindAdAdapter windAdAdapter, final ADStrategy aDStrategy) {
        if (this.adStatus != AdStatus.AdStatusLoading) {
            SigmobLog.d("not do videoAd load ad");
        } else {
            this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.13
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (WindAdRequestController.this.d != null) {
                            WindAdRequestController.this.d.onVideoAdPreLoadSuccess(aDStrategy.getSig_placement_id());
                        }
                    } catch (Throwable th) {
                        SigmobLog.e(th.getMessage());
                    }
                }
            });
        }
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidRewardAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy) {
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidSkipAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy) {
    }

    @Override // com.sigmob.windad.base.WindAdConnector
    public void adapterDidStartPlayingAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy) {
        ADStrategy strategy = getStrategy(aDStrategy);
        h.a().a(PointCategory.START, this.s);
        if (this.w.containsKey(strategy.getADStrategyID())) {
            this.w.remove(strategy.getADStrategyID());
        }
        final int iIndexOf = this.u.indexOf(strategy) + 1;
        PointEntityWindUtils.WindTracking(PointCategory.START, this.z, strategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.17
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    PointEntityWind pointEntityWind = (PointEntityWind) obj;
                    pointEntityWind.setLoad_id(WindAdRequestController.this.s);
                    pointEntityWind.setAd_scene(WindAdRequestController.this.i);
                    pointEntityWind.setSub_category(String.valueOf(iIndexOf));
                }
            }
        });
        SigmobLog.d("adapterDidStartPlayingAd() called with: strategy = [" + strategy + "] " + iIndexOf);
        this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.18
            @Override // java.lang.Runnable
            public void run() {
                if (WindAdRequestController.this.e != null) {
                    WindAdRequestController.this.e.onVideoAdPlayStart(WindAdRequestController.this.a);
                }
            }
        });
    }

    public boolean adapterInitAndLoad(final ADStrategy aDStrategy) {
        String adapterClsName;
        WindAdAdapterError windAdAdapterError;
        try {
            SigmobLog.i("adapterInitAndLoad: " + aDStrategy.getName());
            adapterClsName = AdapterClsUtil.getAdapterClsName(aDStrategy);
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            adapterDidFailToLoadAd(null, aDStrategy, new WindAdAdapterError(0, aDStrategy.getName() + " catch throwable " + th.getMessage()));
        }
        if (TextUtils.isEmpty(adapterClsName)) {
            PointEntityWindUtils.WindError("error", this.z, aDStrategy, WindAdError.ERROR_SIGMOB_NOT_FOUD_ADAPTER.getErrorCode(), aDStrategy.getName() + " can't get class String", null);
            adapterDidFailToLoadAd(null, aDStrategy, new WindAdAdapterError(0, aDStrategy.getName() + " can't get class String"));
            return false;
        }
        final WindAdAdapter windAdAdapterInitializeChannelAdapter = initializeChannelAdapter(aDStrategy, adapterClsName);
        if (windAdAdapterInitializeChannelAdapter != null) {
            if (this.v != null && this.v.size() > 0) {
                for (int i = 0; i < this.v.size(); i++) {
                    ADStrategy aDStrategy2 = this.v.get(i);
                    if (aDStrategy.getADStrategyID().equals(aDStrategy2.getADStrategyID()) && aDStrategy2.getReadyTime() != 0 && !aDStrategy2.isExpired() && windAdAdapterInitializeChannelAdapter.isReady(aDStrategy2)) {
                        SigmobLog.i("上次的策略中存在已经ready的adapter,不必再去加载:" + aDStrategy.getName() + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + aDStrategy.getPlacement_id());
                        adapterDidLoadAdSuccessAd(windAdAdapterInitializeChannelAdapter, aDStrategy);
                        return false;
                    }
                }
            }
            if (aDStrategy.getReadyTime() == 0 || aDStrategy.isExpired() || !windAdAdapterInitializeChannelAdapter.isReady(aDStrategy)) {
                this.f.removeMessages(2000, aDStrategy);
                this.f.sendMessageDelayed(Message.obtain(this.f, 2000, aDStrategy), this.k);
                if (windAdAdapterInitializeChannelAdapter.getAdapterVersion() < SDKConfig.getRequireAdapterVersion(aDStrategy.getChannel_id())) {
                    SigmobLog.e(aDStrategy.getName() + "adapter version is error!");
                    adapterDidFailToLoadAd(null, aDStrategy, new WindAdAdapterError(WindAdError.ERROR_SIGMOB_AD_ADAPTER_ERROR.getErrorCode(), aDStrategy.getName() + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + WindAdError.ERROR_SIGMOB_AD_ADAPTER_ERROR.getMessage()));
                    return false;
                }
                if (windAdAdapterInitializeChannelAdapter.isInit()) {
                    PointEntityWindUtils.WindTracking(PointCategory.LOAD, this.z, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.4
                        @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
                        public void onAddExtra(Object obj) {
                            if (obj instanceof PointEntityWind) {
                                PointEntityWind pointEntityWind = (PointEntityWind) obj;
                                pointEntityWind.setLoad_id(WindAdRequestController.this.s);
                                pointEntityWind.setIs_out_sdk("1");
                            }
                        }
                    });
                    this.z.setLoadId(this.s);
                    aDStrategy.setSig_load_id(this.s);
                    this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.5
                        @Override // java.lang.Runnable
                        public void run() {
                            windAdAdapterInitializeChannelAdapter.loadAd(WindAds.sharedAds().getActivity(), null, WindAdRequestController.this.z, aDStrategy);
                        }
                    });
                } else {
                    windAdAdapterError = new WindAdAdapterError(WindAdError.ERROR_SIGMOB_INIT_FAIL.getErrorCode(), aDStrategy.getName() + "is not have init success!");
                }
            } else {
                SigmobLog.i("策略没过期，ready不播，连续load然后复用已经ready的adapter:" + aDStrategy.getName() + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + aDStrategy.getPlacement_id());
                adapterDidLoadAdSuccessAd(windAdAdapterInitializeChannelAdapter, aDStrategy);
            }
            return false;
        }
        windAdAdapterError = new WindAdAdapterError(WindAdError.ERROR_SIGMOB_NOT_FOUD_ADAPTER.getErrorCode(), WindAdError.ERROR_SIGMOB_NOT_FOUD_ADAPTER.getMessage());
        adapterDidFailToLoadAd(null, aDStrategy, windAdAdapterError);
        return false;
    }

    protected String b() {
        if (this.u.size() == 0) {
            return null;
        }
        if (TextUtils.isEmpty(this.B)) {
            e();
        }
        return this.B;
    }

    protected void c() {
        this.adStatus = AdStatus.AdStatusNone;
        this.w.clear();
        Iterator<ADStrategy> it = this.u.iterator();
        while (it.hasNext()) {
            it.next().resetReady();
        }
    }

    protected void d() {
        int size = this.g <= 0 ? this.u.size() : Math.min(this.u.size(), this.g);
        SigmobLog.d(String.format(" internalLoadAd  length %d, size %d, mMaxConcurrent %d", Integer.valueOf(size), Integer.valueOf(this.u.size()), Integer.valueOf(this.g)));
        this.j = size;
        for (int i = 0; i < size; i++) {
            adapterInitAndLoad(this.u.get(i));
        }
    }

    public void destroy() {
        SigmobLog.i("controller destroy");
        if (this.f != null) {
            this.f.removeCallbacksAndMessages(null);
        }
        if (this.u != null) {
            Iterator<ADStrategy> it = this.u.iterator();
            while (it.hasNext()) {
                WindAdAdapter adapterWithADStrategy = getAdapterWithADStrategy(it.next());
                if (adapterWithADStrategy != null) {
                    SigmobLog.i("controller destroy adAdapter destroy:" + adapterWithADStrategy.getClass().getSimpleName());
                    if (WindAdLifecycleManager.getInstance() != null) {
                        WindAdLifecycleManager.getInstance().removeLifecycleListener(adapterWithADStrategy);
                    }
                    adapterWithADStrategy.destroy();
                }
            }
        }
        this.d = null;
        this.e = null;
        this.h = null;
        if (this.y != null) {
            this.y.clear();
            this.y = null;
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        SigmobLog.w("lid " + this.z.getLoadId() + " pid " + this.z.getPlacementId() + "finalize");
    }

    public AdStatus getAdStatus() {
        return this.adStatus;
    }

    public WindAdAdapter getAdapterWithADStrategy(ADStrategy aDStrategy) {
        if (aDStrategy == null || this.y == null) {
            return null;
        }
        return this.y.get(aDStrategy.getADStrategyID());
    }

    public void getReadyAdapter(onFindReadyAdapterLister onfindreadyadapterlister) {
        WindAdAdapter adapterWithADStrategy;
        if (this.adStatus != AdStatus.AdStatusReady) {
            SigmobLog.e("adStatus not ready  is " + this.adStatus);
            if (onfindreadyadapterlister != null) {
                onfindreadyadapterlister.onFailed("READY_EMPTY");
                return;
            }
            return;
        }
        if (this.u.isEmpty()) {
            SigmobLog.e("strategies is empty");
            if (onfindreadyadapterlister != null) {
                onfindreadyadapterlister.onFailed("STRATEGY_EMPTY");
                return;
            }
            return;
        }
        if (this.w.isEmpty()) {
            SigmobLog.e("readyList is empty");
            if (onfindreadyadapterlister != null) {
                onfindreadyadapterlister.onFailed("READY_EMPTY");
                return;
            }
            return;
        }
        for (ADStrategy aDStrategy : this.u) {
            if (this.w.containsValue(aDStrategy) && !aDStrategy.isExpired() && (adapterWithADStrategy = getAdapterWithADStrategy(aDStrategy)) != null && adapterWithADStrategy.isReady(aDStrategy)) {
                SigmobLog.i("getReadyAdapter isReady true " + aDStrategy.getName() + "load Id " + this.s);
                if (onfindreadyadapterlister != null) {
                    onfindreadyadapterlister.onSuccess(adapterWithADStrategy, aDStrategy);
                    return;
                }
            }
        }
        if (onfindreadyadapterlister != null) {
            onfindreadyadapterlister.onFailed("READY_EMPTY");
        }
    }

    public Map<String, ADStrategy> getReadyList() {
        return this.w;
    }

    public List<ADStrategy> getStrategies() {
        return this.u;
    }

    public ADStrategy getStrategy(ADStrategy aDStrategy) {
        for (ADStrategy aDStrategy2 : this.u) {
            if (aDStrategy2.getPlacement_id().equalsIgnoreCase(aDStrategy.getPlacement_id())) {
                return aDStrategy2;
            }
        }
        return aDStrategy;
    }

    public WindAdAdapter initializeChannelAdapter(ADStrategy aDStrategy, String str) throws ClassNotFoundException {
        WindAdAdapter adapterWithADStrategy = getAdapterWithADStrategy(aDStrategy);
        if (adapterWithADStrategy != null) {
            SigmobLog.d("find already initialize Adapter: " + aDStrategy.getName());
            return adapterWithADStrategy;
        }
        try {
            Class<?> cls = Class.forName(str);
            if (cls.getSuperclass() == WindAdAdapter.class) {
                WindAdAdapter windAdAdapter = (WindAdAdapter) cls.newInstance();
                windAdAdapter.initWithAdConnector(this);
                if (!windAdAdapter.isInit()) {
                    PointEntityWindUtils.WindTracking(PointCategory.PLATFORM_AGGRE_INIT, this.z, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.base.WindAdRequestController.3
                        @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
                        public void onAddExtra(Object obj) {
                            if (obj instanceof PointEntityWind) {
                                ((PointEntityWind) obj).setLoad_id(WindAdRequestController.this.s);
                            }
                        }
                    });
                }
                aDStrategy.setPersonalizedAdvertisingOn(WindAds.sharedAds().isPersonalizedAdvertisingOn());
                windAdAdapter.initializeSdk(WindAds.sharedAds().getContext(), aDStrategy);
                if (this.y != null) {
                    this.y.put(aDStrategy.getADStrategyID(), windAdAdapter);
                }
                SigmobLog.i("initializeChannelAdapter: " + aDStrategy.getName() + " cls: " + str + " success");
                return windAdAdapter;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SigmobLog.e("initializeChannelAdapter: " + aDStrategy.getName() + " cls: " + str + " failed!");
        return null;
    }

    public boolean isReady() {
        WindAdAdapter adapterWithADStrategy;
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.adStatus != AdStatus.AdStatusReady) {
            return false;
        }
        for (ADStrategy aDStrategy : this.u) {
            if (this.w.containsValue(aDStrategy) && !aDStrategy.isExpired() && (adapterWithADStrategy = getAdapterWithADStrategy(aDStrategy)) != null && adapterWithADStrategy.isReady(aDStrategy)) {
                SigmobLog.i("isReady true " + aDStrategy.getName() + "load Id " + this.s);
                return true;
            }
        }
        return false;
    }

    public WindFilter judgeWhetherExecuteLoadRequest() {
        if (SDKConfig.sharedInstance().getLoadPeriodTime() + this.r > System.currentTimeMillis()) {
            SigmobLog.e("load interval Time error");
            return WindFilter.WindFilterLoadInterval;
        }
        if (this.adStatus == AdStatus.AdStatusLoading) {
            return WindFilter.WindFilterLoadingBreak;
        }
        if (SDKConfig.sharedInstance().getLoadExpired() + this.t <= System.currentTimeMillis()) {
            return WindFilter.WindFilterKeepGoing;
        }
        SigmobLog.e("Ad is Ready you can play it directly");
        return WindFilter.WindFilterReadyLoadInterval;
    }

    public void loadAd(WindAdRequest windAdRequest) {
        WindAdError windAdError;
        WindAdError windAdError2;
        boolean z = true;
        if (windAdRequest == null || TextUtils.isEmpty(windAdRequest.getPlacementId())) {
            PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_PLACEMNT_ID_IS_EMPTY.getErrorCode(), "WindVideoAdRequest can't is null");
            pointEntityWindErrorWindError.setAdtype(String.valueOf(1));
            pointEntityWindErrorWindError.commit();
            this.C++;
            this.l++;
            SigmobLog.e("PlacementId with WindAdRequest can't is null");
            windAdError = WindAdError.ERROR_SIGMOB_PLACEMNT_ID_IS_EMPTY;
        } else if (WindAds.sharedAds().isInit()) {
            a.a(windAdRequest.getUserId());
            if (a.b()) {
                switch (judgeWhetherExecuteLoadRequest()) {
                    case WindFilterKeepGoing:
                        SigmobLog.i("WindFilterKeepGoing");
                        z = false;
                        loadAd(windAdRequest, z);
                        break;
                    case WindFilterReadyLoadInterval:
                        SigmobLog.i("WindFilterReadyLoadInterval");
                        loadAd(windAdRequest, z);
                        break;
                    case WindFilterLoadingBreak:
                        this.l++;
                        this.n++;
                        SigmobLog.e("WindFilterLoadingBreak " + windAdRequest.getPlacementId());
                        windAdError2 = WindAdError.ERROR_AD_LOAD_FAIL_LOADING;
                        b(windAdError2);
                        break;
                    case WindFilterLoadInterval:
                        this.l++;
                        this.q++;
                        SigmobLog.e("WindFilterLoadInterval " + windAdRequest.getPlacementId());
                        windAdError2 = WindAdError.ERROR_AD_LOAD_FAIL_INTERVAL;
                        b(windAdError2);
                        break;
                }
            }
            SigmobLog.e(WindAdError.ERROR_SIGMOB_GDPR_DENIED.toString());
            PointEntityWindUtils.WindError("error", windAdRequest, null, WindAdError.ERROR_SIGMOB_GDPR_DENIED.getErrorCode(), WindAdError.ERROR_SIGMOB_GDPR_DENIED.getMessage(), null);
            this.m++;
            this.l++;
            windAdError = WindAdError.ERROR_SIGMOB_GDPR_DENIED;
        } else {
            SigmobLog.e("WindAds not initialize");
            this.o++;
            this.l++;
            PointEntityWindError pointEntityWindErrorWindError2 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_NOT_INIT.getErrorCode(), "WindAds not initialize");
            pointEntityWindErrorWindError2.setAdtype(String.valueOf(windAdRequest.getAdType()));
            pointEntityWindErrorWindError2.commit();
            windAdError = WindAdError.ERROR_SIGMOB_NOT_INIT;
        }
        a(windAdError);
    }

    public void loadAd(WindAdRequest windAdRequest, boolean z) {
        this.adStatus = AdStatus.AdStatusLoading;
        this.f.sendMessageDelayed(Message.obtain(this.f, 1000), SDKConfig.sharedInstance().loadAdTimeout());
        if (this.d != null) {
            this.d.onVideoAdLoadStart(this.a);
        }
        this.x.clear();
        if (z) {
            SigmobLog.i("load ad use lastStrategy loadId " + this.s);
            d();
            return;
        }
        SigmobLog.i("load ad not use lastStrategy loadId " + this.s);
        if (this.v != null) {
            this.v.clear();
            this.v.addAll(this.u);
        }
        this.u.clear();
        this.t = 0L;
        a(windAdRequest);
    }

    public void showAd(final Activity activity, final HashMap<String, String> map) {
        WindowInsets rootWindowInsets;
        if (activity != null && Build.VERSION.SDK_INT >= 23 && (rootWindowInsets = activity.getWindow().getDecorView().getRootWindowInsets()) != null) {
            a.n().a(rootWindowInsets);
        }
        if (this.y != null) {
            Iterator<String> it = this.y.keySet().iterator();
            while (it.hasNext()) {
                WindAdAdapter windAdAdapter = this.y.get(it.next());
                if (WindAdLifecycleManager.getInstance() != null) {
                    WindAdLifecycleManager.getInstance().addLifecycleListener(windAdAdapter);
                }
            }
        }
        getReadyAdapter(new onFindReadyAdapterLister() { // from class: com.sigmob.windad.base.WindAdRequestController.2
            /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
            @Override // com.sigmob.windad.base.WindAdRequestController.onFindReadyAdapterLister
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onFailed(java.lang.String r5) {
                /*
                    r4 = this;
                    int r0 = r5.hashCode()
                    r1 = -1158356639(0xffffffffbaf4e161, float:-0.0018682891)
                    if (r0 == r1) goto L19
                    r1 = -799775887(0xffffffffd0546371, float:-1.4253147E10)
                    if (r0 == r1) goto Lf
                    goto L23
                Lf:
                    java.lang.String r0 = "READY_EMPTY"
                    boolean r0 = r5.equals(r0)
                    if (r0 == 0) goto L23
                    r0 = 1
                    goto L24
                L19:
                    java.lang.String r0 = "STRATEGY_EMPTY"
                    boolean r0 = r5.equals(r0)
                    if (r0 == 0) goto L23
                    r0 = 0
                    goto L24
                L23:
                    r0 = -1
                L24:
                    r1 = 0
                    switch(r0) {
                        case 0: goto L51;
                        case 1: goto L3d;
                        default: goto L28;
                    }
                L28:
                    com.sigmob.windad.base.WindAdRequestController r0 = com.sigmob.windad.base.WindAdRequestController.this
                    com.sigmob.windad.WindAdError r2 = com.sigmob.windad.WindAdError.ERROR_SIGMOB_NOT_FOUD_ADAPTER
                    com.sigmob.windad.base.WindAdRequestController.a(r0, r2)
                    com.sigmob.windad.base.WindAdRequestController r0 = com.sigmob.windad.base.WindAdRequestController.this
                    java.lang.String r2 = "error"
                    com.sigmob.windad.WindAdError r3 = com.sigmob.windad.WindAdError.ERROR_SIGMOB_NOT_FOUD_ADAPTER
                    int r3 = r3.getErrorCode()
                    com.sigmob.windad.base.WindAdRequestController.a(r0, r2, r1, r3, r5)
                    goto L6b
                L3d:
                    com.sigmob.windad.base.WindAdRequestController r5 = com.sigmob.windad.base.WindAdRequestController.this
                    com.sigmob.windad.WindAdError r0 = com.sigmob.windad.WindAdError.ERROR_SIGMOB_NOT_READY
                    com.sigmob.windad.base.WindAdRequestController.a(r5, r0)
                    com.sigmob.windad.base.WindAdRequestController r5 = com.sigmob.windad.base.WindAdRequestController.this
                    java.lang.String r0 = "error"
                    com.sigmob.windad.WindAdError r2 = com.sigmob.windad.WindAdError.ERROR_SIGMOB_NOT_READY
                    int r2 = r2.getErrorCode()
                    com.sigmob.windad.WindAdError r3 = com.sigmob.windad.WindAdError.ERROR_SIGMOB_NOT_READY
                    goto L64
                L51:
                    com.sigmob.windad.base.WindAdRequestController r5 = com.sigmob.windad.base.WindAdRequestController.this
                    com.sigmob.windad.WindAdError r0 = com.sigmob.windad.WindAdError.ERROR_REQUEST_APP_NOT_SET_STRATEGY
                    com.sigmob.windad.base.WindAdRequestController.a(r5, r0)
                    com.sigmob.windad.base.WindAdRequestController r5 = com.sigmob.windad.base.WindAdRequestController.this
                    java.lang.String r0 = "error"
                    com.sigmob.windad.WindAdError r2 = com.sigmob.windad.WindAdError.ERROR_REQUEST_APP_NOT_SET_STRATEGY
                    int r2 = r2.getErrorCode()
                    com.sigmob.windad.WindAdError r3 = com.sigmob.windad.WindAdError.ERROR_REQUEST_APP_NOT_SET_STRATEGY
                L64:
                    java.lang.String r3 = r3.getMessage()
                    com.sigmob.windad.base.WindAdRequestController.a(r5, r0, r1, r2, r3)
                L6b:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sigmob.windad.base.WindAdRequestController.AnonymousClass2.onFailed(java.lang.String):void");
            }

            @Override // com.sigmob.windad.base.WindAdRequestController.onFindReadyAdapterLister
            public void onSuccess(final WindAdAdapter windAdAdapter2, final ADStrategy aDStrategy) {
                if (windAdAdapter2 == null) {
                    WindAdRequestController.this.a("error", null, WindAdError.ERROR_REQUEST_APP_NOT_SET_STRATEGY.getErrorCode(), WindAdError.ERROR_REQUEST_APP_NOT_SET_STRATEGY.getMessage());
                    WindAdRequestController.this.c(WindAdError.ERROR_SIGMOB_NOT_FOUD_ADAPTER);
                    return;
                }
                WindAdRequestController.this.adStatus = AdStatus.AdStatusPlaying;
                aDStrategy.resetReady();
                WindAdRequestController.this.t = 0L;
                WindAdRequestController.this.z.setLoadId(WindAdRequestController.this.s);
                aDStrategy.setSig_load_id(WindAdRequestController.this.s);
                if (map != null) {
                    WindAdRequestController.this.i = (String) map.get("ad_scene");
                } else {
                    WindAdRequestController.this.i = null;
                }
                aDStrategy.setAdScene(WindAdRequestController.this.i);
                WindAdRequestController.this.f.post(new Runnable() { // from class: com.sigmob.windad.base.WindAdRequestController.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        windAdAdapter2.presentVideoAd(activity, null, aDStrategy);
                    }
                });
            }
        });
    }
}
