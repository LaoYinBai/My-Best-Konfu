package com.sigmob.windad.natives;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
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
import com.sigmob.windad.base.WindAdAdapter;
import com.sigmob.windad.base.WindAdNativeConnector;
import com.sigmob.windad.common.AdapterClsUtil;
import com.sigmob.windad.common.PointEntityWindUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindNativeAdRequestController implements WindAdNativeConnector {
    private int A;
    private final String a;
    private WindAdRequestLoadLister d;
    private Context e;
    private WindAdInitListener h;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private WindAdRequest x;
    private final int b = 1000;
    private final int c = 2000;
    public AdStatus adStatus = AdStatus.AdStatusNone;
    private int g = 1;
    private int i = 0;
    private long j = 15000;
    private int k = 0;
    private String r = "";
    private String y = "";
    private String z = "";
    private List<ADStrategy> t = new ArrayList();
    private Map<String, List> u = new HashMap();
    private Map<String, WindAdAdapterError> v = new HashMap();
    private Map<String, WindAdAdapter> w = new HashMap(1);
    private long s = 0;
    private long q = 0;
    private Handler f = new Handler(Looper.getMainLooper()) { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1000) {
                if (WindNativeAdRequestController.this.adStatus == AdStatus.AdStatusLoading) {
                    SigmobLog.e("loadAd Timeout load id " + WindNativeAdRequestController.this.r);
                    WindNativeAdRequestController.this.s = 0L;
                    WindNativeAdRequestController.this.a("error", null, WindAdError.ERROR_SIGMOB_AD_TIME_OUT.getErrorCode(), WindAdError.ERROR_SIGMOB_AD_TIME_OUT.getMessage());
                    WindNativeAdRequestController.this.d();
                    if (WindNativeAdRequestController.this.d != null) {
                        WindNativeAdRequestController.this.d.onVideoAdLoadFail(WindAdError.ERROR_SIGMOB_AD_TIME_OUT, WindNativeAdRequestController.this.a);
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
                    WindNativeAdRequestController.this.adapterDidFailToLoadAd(null, aDStrategy, new WindAdAdapterError(WindAdError.ERROR_SIGMOB_AD_TIME_OUT.getErrorCode(), String.format("loadAd strategy name %s, placementId %s Timeout", aDStrategy.getName(), aDStrategy.getPlacement_id())));
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

        void onVideoAdLoadSuccess(List<NativeADData> list, String str);
    }

    public enum WindFilter {
        WindFilterKeepGoing,
        WindFilterLoadingBreak,
        WindFilterPlayBreak,
        WindFilterLoadInterval,
        WindFilterReadyLoadInterval
    }

    public WindNativeAdRequestController(Context context, WindAdRequest windAdRequest, WindAdInitListener windAdInitListener, WindAdRequestLoadLister windAdRequestLoadLister) {
        this.e = context;
        this.h = windAdInitListener;
        this.d = windAdRequestLoadLister;
        this.x = windAdRequest;
        this.a = windAdRequest.getPlacementId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final WindAdError windAdError) {
        this.s = 0L;
        this.f.removeMessages(1000);
        this.f.post(new Runnable() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.10
            @Override // java.lang.Runnable
            public void run() {
                if (WindNativeAdRequestController.this.d != null) {
                    WindNativeAdRequestController.this.d.onVideoAdLoadFail(windAdError, WindNativeAdRequestController.this.a);
                }
            }
        });
    }

    private void a(WindAdRequest windAdRequest) {
        this.q = System.currentTimeMillis();
        this.r = UUID.randomUUID().toString();
        this.x.setLoadId(this.r);
        h.a().a(PointCategory.LOAD, this.r);
        PointEntityWindUtils.WindInit(PointCategory.REQUEST_AGGRE_STRATEGY, this.x, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.3
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    PointEntityWind pointEntityWind = (PointEntityWind) obj;
                    pointEntityWind.setLoad_count(String.valueOf(WindNativeAdRequestController.this.k + 1));
                    pointEntityWind.setLoading_filters(String.valueOf(WindNativeAdRequestController.this.m));
                    pointEntityWind.setGdpr_filters(String.valueOf(WindNativeAdRequestController.this.l));
                    pointEntityWind.setPlaying_filters(String.valueOf(WindNativeAdRequestController.this.o));
                    pointEntityWind.setInterval_filters(String.valueOf(WindNativeAdRequestController.this.p));
                    pointEntityWind.setPldempty_filters(String.valueOf(WindNativeAdRequestController.this.A));
                    pointEntityWind.setLoad_id(WindNativeAdRequestController.this.r);
                    pointEntityWind.setInit_filters(String.valueOf(WindNativeAdRequestController.this.n));
                    pointEntityWind.setIs_enter_sdk("1");
                    pointEntityWind.setFeed_cnt(String.valueOf(WindNativeAdRequestController.this.x.getAdCount()));
                }
            }
        });
        this.k = 0;
        this.m = 0;
        this.l = 0;
        this.o = 0;
        this.p = 0;
        this.A = 0;
        this.n = 0;
        o.a(WindAds.sharedAds().getOptions().getUseMediation(), SDKConfig.sharedInstance().getStrategyUrl(), this.x.getAdType(), this.a, this.x.getUserId(), new o.a() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.4
            @Override // com.sigmob.sdk.common.e.o.a
            public void onErrorResponse(String str, int i, int i2, String str2) {
                SigmobLog.e("error_code  " + i2 + " msg: " + str2);
                WindNativeAdRequestController.this.d();
                WindNativeAdRequestController.this.s = 0L;
                WindAdError windAdError = WindAdError.getWindAdError(i2);
                if (windAdError == null) {
                    windAdError = WindAdError.ERROR_SIGMOB_STRATEGY_EMPTY;
                    windAdError.setMessage("error_code  " + i2 + " msg: " + str2);
                }
                PointEntityWindUtils.WindError("error", WindNativeAdRequestController.this.x, null, i2, str2, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.4.2
                    @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
                    public void onAddExtra(Object obj) {
                        if (obj instanceof PointEntityWind) {
                            PointEntityWind pointEntityWind = (PointEntityWind) obj;
                            pointEntityWind.setIs_success(Constants.FAIL);
                            pointEntityWind.setIs_out_sdk(Constants.FAIL);
                            pointEntityWind.setLoad_id(WindNativeAdRequestController.this.r);
                        }
                    }
                });
                WindNativeAdRequestController.this.a(windAdError);
            }

            @Override // com.sigmob.sdk.common.e.o.a
            public void onSuccess(List<ADStrategy> list, final n nVar, String str) {
                WindNativeAdRequestController.this.t = list;
                WindNativeAdRequestController.this.s = System.currentTimeMillis();
                if (nVar.a > 0) {
                    WindNativeAdRequestController.this.g = nVar.a;
                }
                if (nVar.b != 0) {
                    WindNativeAdRequestController.this.j = nVar.b * 1000;
                }
                WindNativeAdRequestController.this.e();
                PointEntityWindUtils.WindInit(PointCategory.RESPOND_AGGRE_STRATEGY, WindNativeAdRequestController.this.x, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.4.1
                    @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
                    public void onAddExtra(Object obj) {
                        if (obj instanceof PointEntityWind) {
                            PointEntityWind pointEntityWind = (PointEntityWind) obj;
                            if (nVar.e) {
                                pointEntityWind.setAb_test(String.valueOf(nVar.d));
                            }
                            pointEntityWind.setGroup_id(nVar.c);
                            pointEntityWind.setIs_success("1");
                            pointEntityWind.setVlist_platform(WindNativeAdRequestController.this.a());
                            pointEntityWind.setVlist_element(WindNativeAdRequestController.this.b());
                            pointEntityWind.setLoad_id(WindNativeAdRequestController.this.r);
                        }
                    }
                });
                WindNativeAdRequestController.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, final ADStrategy aDStrategy, int i, String str2) {
        PointEntityWindUtils.WindError(str, this.x, aDStrategy, i, str2, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.7
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    PointEntityWind pointEntityWind = (PointEntityWind) obj;
                    pointEntityWind.setLoad_id(WindNativeAdRequestController.this.r);
                    if (aDStrategy == null || TextUtils.isEmpty(aDStrategy.getOffer_id())) {
                        return;
                    }
                    pointEntityWind.setOffer_id(aDStrategy.getOffer_id());
                }
            }
        });
    }

    private void b(final WindAdError windAdError) {
        if (this.f != null) {
            this.f.post(new Runnable() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.11
                @Override // java.lang.Runnable
                public void run() {
                    if (WindNativeAdRequestController.this.d != null) {
                        WindNativeAdRequestController.this.d.onVideoAdLoadFail(windAdError, WindNativeAdRequestController.this.a);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            List<ADStrategy> listSubList = (this.g == 0 || this.g >= this.t.size()) ? this.t : this.t.subList(0, this.g);
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
            if (listSubList.size() < this.t.size()) {
                for (ADStrategy aDStrategy : this.t.subList(listSubList.size(), this.t.size())) {
                    sb.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb.append(aDStrategy.getName());
                    sb2.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                    sb2.append(aDStrategy.getPlacement_id());
                }
            }
            sb.append("]");
            sb2.append("]");
            this.y = sb.toString();
            this.z = sb2.toString();
            SigmobLog.d("updateViolist: " + this.y + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP + this.z);
        } catch (Exception unused) {
            this.y = "";
            this.z = "";
        }
    }

    private String f() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.v.keySet()) {
            HashMap map = new HashMap();
            this.v.get(str);
            map.put(str, this.v.get(str).toString());
            arrayList.add(map);
        }
        return b.a(arrayList);
    }

    private void g() {
        if (this.i < this.t.size()) {
            ADStrategy aDStrategy = this.t.get(this.i);
            SigmobLog.d(" internalLoadAd backupIndex " + this.i + " name " + aDStrategy.getName());
            this.i = this.i + 1;
            adapterInitAndLoad(aDStrategy);
        }
    }

    protected String a() {
        if (this.t.size() == 0) {
            return null;
        }
        if (TextUtils.isEmpty(this.y)) {
            e();
        }
        return this.y;
    }

    @Override // com.sigmob.windad.base.WindAdBaseConnector
    public void adapterDidAdClick(WindAdAdapter windAdAdapter, final ADStrategy aDStrategy) {
        SigmobLog.i("adapterDidAdClick() called with: strategy = [" + aDStrategy + "]");
        h.a().a("click", this.r);
        PointEntityWindUtils.WindTracking("click", this.x, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.12
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    PointEntityWind pointEntityWind = (PointEntityWind) obj;
                    pointEntityWind.setLoad_id(WindNativeAdRequestController.this.r);
                    if (aDStrategy == null || TextUtils.isEmpty(aDStrategy.getOffer_id())) {
                        return;
                    }
                    pointEntityWind.setOffer_id(aDStrategy.getOffer_id());
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdNativeConnector
    public void adapterDidFailToLoadAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError) {
        WindAdError windAdError;
        ADStrategy strategy = getStrategy(aDStrategy);
        SigmobLog.e("adapterDidFailToLoadAd() called with: strategy = [" + strategy.getName() + "], error = [" + windAdAdapterError + "]");
        a("error", strategy, windAdAdapterError.getErrorCode(), windAdAdapterError.getMessage());
        this.f.removeMessages(2000, strategy);
        this.v.put(strategy.getName() + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + strategy.getPlacement_id(), windAdAdapterError);
        g();
        if (this.v.keySet().size() == this.t.size() && this.adStatus == AdStatus.AdStatusLoading) {
            this.s = 0L;
            d();
            this.f.removeMessages(2000);
            this.f.removeMessages(1000);
            if (strategy.getName().equals(Constants.SDK_FOLDER) && this.t.size() == 1) {
                windAdError = WindAdError.getWindAdError(windAdAdapterError.getErrorCode());
            } else {
                windAdError = WindAdError.ERROR_SIGMOB_REQUEST;
                windAdError.setMessage(f());
            }
            if (windAdError == null) {
                windAdError = WindAdError.RTB_SIG_DSP_NO_ADS_ERROR;
                windAdError.setMessage(f());
            }
            this.v.clear();
            a(windAdError);
        }
    }

    @Override // com.sigmob.windad.base.WindAdNativeConnector
    public void adapterDidFailToPlayingAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError) {
        try {
            SigmobLog.d("adapterDidFailToPlayingAd() called with: strategy = [" + aDStrategy + "] " + windAdAdapterError.toString());
            WindAdError windAdError = WindAdError.ERROR_SIGMOB_AD_PLAY;
            HashMap map = new HashMap();
            map.put(aDStrategy.getName(), windAdAdapterError);
            windAdError.setMessage(b.a(map));
            a("error", aDStrategy, windAdAdapterError.getErrorCode(), windAdAdapterError.getMessage());
        } catch (Exception e) {
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

    @Override // com.sigmob.windad.base.WindAdNativeConnector
    public void adapterDidLoadNativeAdSuccessAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy, final List list) {
        ADStrategy strategy = getStrategy(aDStrategy);
        this.f.removeMessages(2000, strategy);
        PointEntityWindUtils.WindTracking(PointCategory.READY, this.x, strategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.8
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    PointEntityWind pointEntityWind = (PointEntityWind) obj;
                    pointEntityWind.setLoad_id(WindNativeAdRequestController.this.r);
                    pointEntityWind.setAd_cnt(String.valueOf(list.size()));
                }
            }
        });
        strategy.setReady();
        this.i = this.t.size();
        if (this.u != null) {
            this.u.put(strategy.getADStrategyID(), list);
        }
        SigmobLog.d("adapterDidLoadNativeAdSuccessAd() called with: strategy = [" + strategy + "]");
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
        this.f.post(new Runnable() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.9
            @Override // java.lang.Runnable
            public void run() {
                if (WindNativeAdRequestController.this.d != null) {
                    WindNativeAdRequestController.this.d.onVideoAdLoadSuccess(list, WindNativeAdRequestController.this.a);
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdNativeConnector
    public void adapterDidShowLangPageAd(WindAdAdapter windAdAdapter, final ADStrategy aDStrategy) {
        SigmobLog.i("adapterDidPlayEndAd() called with: strategy = [" + aDStrategy + "]");
        PointEntityWindUtils.WindTracking(PointCategory.LANDING_PAGE_SHOW, this.x, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.14
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    PointEntityWind pointEntityWind = (PointEntityWind) obj;
                    pointEntityWind.setLoad_id(WindNativeAdRequestController.this.r);
                    if (aDStrategy == null || TextUtils.isEmpty(aDStrategy.getOffer_id())) {
                        return;
                    }
                    pointEntityWind.setOffer_id(aDStrategy.getOffer_id());
                }
            }
        });
    }

    @Override // com.sigmob.windad.base.WindAdNativeConnector
    public void adapterDidStartPlayingAd(WindAdAdapter windAdAdapter, final ADStrategy aDStrategy) {
        SigmobLog.i("adapterDidStartPlayingAd() called with: strategy = [" + aDStrategy + "]");
        h.a().a(PointCategory.START, this.r);
        final int iIndexOf = this.t.indexOf(aDStrategy) + 1;
        PointEntityWindUtils.WindTracking(PointCategory.START, this.x, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.13
            @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
            public void onAddExtra(Object obj) {
                if (obj instanceof PointEntityWind) {
                    PointEntityWind pointEntityWind = (PointEntityWind) obj;
                    pointEntityWind.setLoad_id(WindNativeAdRequestController.this.r);
                    pointEntityWind.setSub_category(String.valueOf(iIndexOf));
                    if (aDStrategy == null || TextUtils.isEmpty(aDStrategy.getOffer_id())) {
                        return;
                    }
                    pointEntityWind.setOffer_id(aDStrategy.getOffer_id());
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
            PointEntityWindUtils.WindError("error", this.x, aDStrategy, WindAdError.ERROR_SIGMOB_NOT_FOUD_ADAPTER.getErrorCode(), aDStrategy.getName() + " can't get class String", null);
            adapterDidFailToLoadAd(null, aDStrategy, new WindAdAdapterError(0, aDStrategy.getName() + " can't get class String"));
            return false;
        }
        final WindAdAdapter windAdAdapterInitializeChannelAdapter = initializeChannelAdapter(aDStrategy, adapterClsName);
        if (windAdAdapterInitializeChannelAdapter != null) {
            this.f.removeMessages(2000, aDStrategy);
            this.f.sendMessageDelayed(Message.obtain(this.f, 2000, aDStrategy), this.j);
            if (windAdAdapterInitializeChannelAdapter.getAdapterVersion() < SDKConfig.getRequireAdapterVersion(aDStrategy.getChannel_id())) {
                SigmobLog.e(aDStrategy.getName() + "adapter version is error!");
                adapterDidFailToLoadAd(null, aDStrategy, new WindAdAdapterError(WindAdError.ERROR_SIGMOB_AD_ADAPTER_ERROR.getErrorCode(), aDStrategy.getName() + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER + WindAdError.ERROR_SIGMOB_AD_ADAPTER_ERROR.getMessage()));
                return false;
            }
            if (windAdAdapterInitializeChannelAdapter.isInit()) {
                PointEntityWindUtils.WindTracking(PointCategory.LOAD, this.x, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.5
                    @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
                    public void onAddExtra(Object obj) {
                        if (obj instanceof PointEntityWind) {
                            PointEntityWind pointEntityWind = (PointEntityWind) obj;
                            pointEntityWind.setLoad_id(WindNativeAdRequestController.this.r);
                            pointEntityWind.setIs_out_sdk("1");
                        }
                    }
                });
                this.x.setLoadId(this.r);
                aDStrategy.setSig_load_id(this.r);
                this.f.post(new Runnable() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.6
                    @Override // java.lang.Runnable
                    public void run() {
                        windAdAdapterInitializeChannelAdapter.loadAd(null, null, WindNativeAdRequestController.this.x, aDStrategy);
                    }
                });
                return false;
            }
            windAdAdapterError = new WindAdAdapterError(WindAdError.ERROR_SIGMOB_INIT_FAIL.getErrorCode(), aDStrategy.getName() + "is not have init success!");
        } else {
            windAdAdapterError = new WindAdAdapterError(WindAdError.ERROR_SIGMOB_NOT_FOUD_ADAPTER.getErrorCode(), WindAdError.ERROR_SIGMOB_NOT_FOUD_ADAPTER.getMessage());
        }
        adapterDidFailToLoadAd(null, aDStrategy, windAdAdapterError);
        return false;
    }

    protected String b() {
        if (this.t.size() == 0) {
            return null;
        }
        if (TextUtils.isEmpty(this.z)) {
            e();
        }
        return this.z;
    }

    protected void c() {
        int size = this.g <= 0 ? this.t.size() : Math.min(this.t.size(), this.g);
        SigmobLog.d(String.format(" internalLoadAd  length %d, size %d, mMaxConcurrent %d", Integer.valueOf(size), Integer.valueOf(this.t.size()), Integer.valueOf(this.g)));
        this.i = size;
        for (int i = 0; i < size; i++) {
            adapterInitAndLoad(this.t.get(i));
        }
    }

    protected void d() {
        this.adStatus = AdStatus.AdStatusNone;
        this.u.clear();
        Iterator<ADStrategy> it = this.t.iterator();
        while (it.hasNext()) {
            it.next().resetReady();
        }
    }

    public void destroy() {
        SigmobLog.i("controller destroy");
        if (this.f != null) {
            this.f.removeCallbacksAndMessages(null);
        }
        if (this.t != null) {
            Iterator<ADStrategy> it = this.t.iterator();
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
        this.h = null;
        if (this.w != null) {
            this.w.clear();
            this.w = null;
        }
    }

    public WindAdAdapter getAdapterWithADStrategy(ADStrategy aDStrategy) {
        if (aDStrategy == null || this.w == null) {
            return null;
        }
        return this.w.get(aDStrategy.getADStrategyID());
    }

    public List<NativeADData> getNativeADDataList() {
        if (this.t == null) {
            return null;
        }
        for (int i = 0; i < this.t.size(); i++) {
            ADStrategy aDStrategy = this.t.get(i);
            if (this.u != null && this.u.containsKey(aDStrategy.getADStrategyID())) {
                return this.u.get(aDStrategy.getADStrategyID());
            }
        }
        return null;
    }

    public ADStrategy getStrategy(ADStrategy aDStrategy) {
        for (ADStrategy aDStrategy2 : this.t) {
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
                    PointEntityWindUtils.WindTracking(PointCategory.PLATFORM_AGGRE_INIT, this.x, aDStrategy, new PointEntityWindUtils.OnPointEntityExtraInfo() { // from class: com.sigmob.windad.natives.WindNativeAdRequestController.2
                        @Override // com.sigmob.windad.common.PointEntityWindUtils.OnPointEntityExtraInfo
                        public void onAddExtra(Object obj) {
                            if (obj instanceof PointEntityWind) {
                                ((PointEntityWind) obj).setLoad_id(WindNativeAdRequestController.this.r);
                            }
                        }
                    });
                }
                aDStrategy.setPersonalizedAdvertisingOn(WindAds.sharedAds().isPersonalizedAdvertisingOn());
                windAdAdapter.initializeSdk(WindAds.sharedAds().getContext(), aDStrategy);
                if (this.w != null) {
                    this.w.put(aDStrategy.getADStrategyID(), windAdAdapter);
                }
                if (WindAdLifecycleManager.getInstance() != null) {
                    WindAdLifecycleManager.getInstance().addLifecycleListener(windAdAdapter);
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

    public WindFilter judgeWhetherExecuteLoadRequest() {
        if (SDKConfig.sharedInstance().getLoadPeriodTime() + this.q > System.currentTimeMillis()) {
            SigmobLog.e("load interval Time error");
            return WindFilter.WindFilterLoadInterval;
        }
        if (this.adStatus == AdStatus.AdStatusLoading) {
            return WindFilter.WindFilterLoadingBreak;
        }
        if (SDKConfig.sharedInstance().getLoadExpired() + this.s <= System.currentTimeMillis()) {
            return WindFilter.WindFilterKeepGoing;
        }
        SigmobLog.e("Ad is Ready you can play it directly");
        return WindFilter.WindFilterReadyLoadInterval;
    }

    public void loadAd(WindAdRequest windAdRequest) {
        WindAdError windAdError;
        String str;
        WindAdError windAdError2;
        if (windAdRequest == null || TextUtils.isEmpty(windAdRequest.getPlacementId())) {
            PointEntityWindError pointEntityWindErrorWindError = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_PLACEMNT_ID_IS_EMPTY.getErrorCode(), "WindVideoAdRequest can't is null");
            pointEntityWindErrorWindError.setAdtype(String.valueOf(5));
            pointEntityWindErrorWindError.commit();
            this.A++;
            this.k++;
            SigmobLog.e("PlacementId with WindAdRequest can't is null");
            windAdError = WindAdError.ERROR_SIGMOB_PLACEMNT_ID_IS_EMPTY;
        } else if (!WindAds.sharedAds().isInit()) {
            SigmobLog.e("WindAds not initialize");
            PointEntityWindError pointEntityWindErrorWindError2 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_NOT_INIT.getErrorCode(), "WindAds not initialize");
            pointEntityWindErrorWindError2.setAdtype(String.valueOf(5));
            pointEntityWindErrorWindError2.commit();
            this.n++;
            this.k++;
            windAdError = WindAdError.ERROR_SIGMOB_NOT_INIT;
        } else if (windAdRequest.getAdCount() <= 0) {
            PointEntityWindError pointEntityWindErrorWindError3 = PointEntityWindError.WindError("error", WindAdError.ERROR_SIGMOB_AD_COUNT_IS_EMPTY.getErrorCode(), "request ad count can't is null");
            pointEntityWindErrorWindError3.setAdtype(String.valueOf(5));
            pointEntityWindErrorWindError3.commit();
            SigmobLog.e("request ad count can't is null");
            windAdError = WindAdError.ERROR_SIGMOB_AD_COUNT_IS_EMPTY;
        } else {
            if (a.b()) {
                switch (judgeWhetherExecuteLoadRequest()) {
                    case WindFilterKeepGoing:
                        str = "WindFilterKeepGoing";
                        SigmobLog.i(str);
                        loadAd(windAdRequest, false);
                        break;
                    case WindFilterReadyLoadInterval:
                        str = "WindFilterReadyLoadInterval";
                        SigmobLog.i(str);
                        loadAd(windAdRequest, false);
                        break;
                    case WindFilterLoadingBreak:
                        this.k++;
                        this.m++;
                        SigmobLog.e("WindFilterLoadingBreak " + this.x.getPlacementId());
                        windAdError2 = WindAdError.ERROR_AD_LOAD_FAIL_LOADING;
                        b(windAdError2);
                        break;
                    case WindFilterLoadInterval:
                        this.k++;
                        this.p++;
                        SigmobLog.e("WindFilterLoadInterval " + this.x.getPlacementId());
                        windAdError2 = WindAdError.ERROR_AD_LOAD_FAIL_INTERVAL;
                        b(windAdError2);
                        break;
                }
            }
            SigmobLog.e(WindAdError.ERROR_SIGMOB_GDPR_DENIED.toString());
            PointEntityWindUtils.WindError("error", this.x, null, WindAdError.ERROR_SIGMOB_GDPR_DENIED.getErrorCode(), WindAdError.ERROR_SIGMOB_GDPR_DENIED.getMessage(), null);
            this.l++;
            this.k++;
            windAdError = WindAdError.ERROR_SIGMOB_GDPR_DENIED;
        }
        a(windAdError);
    }

    public void loadAd(WindAdRequest windAdRequest, boolean z) {
        this.adStatus = AdStatus.AdStatusLoading;
        a.a(windAdRequest.getUserId());
        this.f.sendMessageDelayed(Message.obtain(this.f, 1000), SDKConfig.sharedInstance().loadAdTimeout());
        this.v.clear();
        this.u.clear();
        if (z) {
            SigmobLog.i("load ad use lastStrategy loadId " + this.r);
            c();
            return;
        }
        SigmobLog.i("load ad not use lastStrategy loadId " + this.r);
        this.t.clear();
        this.s = 0L;
        a(windAdRequest);
    }
}
