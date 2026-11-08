package com.bytedance.msdk.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.c;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.h;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.c0;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.q;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.x;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.y;
import com.alipay.sdk.util.e;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.error.AdBreakError;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.msdk.base.a;
import com.bytedance.msdk.core.corelogic.b;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class TTAbsAdLoaderAdapter {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3913a = false;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3914b = false;

    /* renamed from: c, reason: collision with root package name */
    private AdapterLoaderListener f3915c;
    private i d;
    private String e;
    private int f;
    private int g;
    private double h;
    private String i;
    private String j;
    private long k;
    private long l;
    private int m;
    protected AdSlot mAdSlot;
    protected h mTTExtraModel;
    protected String mWaterfallAbTestParam;
    private int n;
    private int o;
    private g p;
    private int q;
    private int r;
    private String s;
    private boolean t;
    private int u;

    public interface AdapterLoaderListener {
        void onAdFailed(AdError adError, d dVar);

        void onAdLoaded(TTBaseAd tTBaseAd, d dVar);

        void onAdLoaded(List<TTBaseAd> list, d dVar);

        void onAdVideoCache();
    }

    private class CallBackRunnable implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        private String f3920b;

        /* renamed from: c, reason: collision with root package name */
        private TTBaseAd f3921c;
        private List<TTBaseAd> d;
        private AdError e;

        CallBackRunnable(String str, TTBaseAd tTBaseAd, List<TTBaseAd> list, AdError adError) {
            this.f3920b = str;
            this.f3921c = tTBaseAd;
            this.d = list;
            this.e = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TTAbsAdLoaderAdapter.this.f3915c != null) {
                d dVar = new d();
                dVar.a(TTAbsAdLoaderAdapter.this.mAdSlot.getAdType()).e(TTAbsAdLoaderAdapter.this.u).b(TTAbsAdLoaderAdapter.this.r).c(TTAbsAdLoaderAdapter.this.f).d(TTAbsAdLoaderAdapter.this.g).a(TTAbsAdLoaderAdapter.this.getAdNetWorkName()).b(TTAbsAdLoaderAdapter.this.s);
                if ("adload_ads".equals(this.f3920b) || "adload_ad".equals(this.f3920b)) {
                    TTBaseAd tTBaseAd = null;
                    boolean zEquals = "adload_ads".equals(this.f3920b);
                    int i = AdError.ERROR_CODE_NO_AD;
                    if (!zEquals) {
                        if (this.f3921c != null) {
                            i = AdError.ERROR_CODE_AD_LOAD_SUCCESS;
                        }
                        TTAbsAdLoaderAdapter.this.b(this.f3921c);
                        TTAbsAdLoaderAdapter.this.a(i, this.f3921c, 1);
                        if (TTAbsAdLoaderAdapter.this.f3915c != null) {
                            TTAbsAdLoaderAdapter.this.f3915c.onAdLoaded(this.f3921c, dVar);
                        }
                        if (TTAbsAdLoaderAdapter.this.isServerBidding()) {
                            TTBaseAd tTBaseAd2 = this.f3921c;
                            TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = TTAbsAdLoaderAdapter.this;
                            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(tTBaseAd2, tTAbsAdLoaderAdapter.mAdSlot, tTAbsAdLoaderAdapter.l);
                            return;
                        }
                        return;
                    }
                    List<TTBaseAd> list = this.d;
                    int size = list != null ? list.size() : 0;
                    List<TTBaseAd> list2 = this.d;
                    if (list2 != null && list2.size() > 0) {
                        i = AdError.ERROR_CODE_AD_LOAD_SUCCESS;
                    }
                    for (TTBaseAd tTBaseAd3 : this.d) {
                        if (tTBaseAd3 != null) {
                            TTAbsAdLoaderAdapter.this.b(tTBaseAd3);
                            if (tTBaseAd == null) {
                                tTBaseAd = tTBaseAd3;
                            }
                        }
                    }
                    TTAbsAdLoaderAdapter.this.a(i, tTBaseAd, size);
                    if (TTAbsAdLoaderAdapter.this.f3915c != null) {
                        TTAbsAdLoaderAdapter.this.f3915c.onAdLoaded(this.d, dVar);
                    }
                    if (!TTAbsAdLoaderAdapter.this.isServerBidding() || x.b(this.d)) {
                        return;
                    }
                    TTBaseAd tTBaseAd4 = this.d.get(0);
                    TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter2 = TTAbsAdLoaderAdapter.this;
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(tTBaseAd4, tTAbsAdLoaderAdapter2.mAdSlot, tTAbsAdLoaderAdapter2.l);
                    return;
                }
                if (!e.f2649a.equals(this.f3920b)) {
                    if ("ad_video_cache".equals(this.f3920b)) {
                        TTBaseAd tTBaseAd5 = this.f3921c;
                        if (tTBaseAd5 == null || !(tTBaseAd5.getAdType() == 10 || this.f3921c.getAdType() == 8 || this.f3921c.getAdType() == 7)) {
                            Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(TTAbsAdLoaderAdapter.this.e, TTLogUtil.TAG_EVENT_FILL) + "onAdVideoCache-----ttAd=" + this.f3921c);
                            return;
                        }
                        TTAbsAdLoaderAdapter.this.b(this.f3921c);
                        AdError adError = this.e;
                        if (adError != null && adError.code == 30010 && TTAbsAdLoaderAdapter.this.a()) {
                            ThreadHelper.postDelayOnMSDKThread(new Runnable() { // from class: com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.CallBackRunnable.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    CallBackRunnable.this.f3921c.setCacheSuccess(true);
                                    CallBackRunnable callBackRunnable = CallBackRunnable.this;
                                    TTAbsAdLoaderAdapter.this.a(callBackRunnable.f3921c);
                                    if (TTAbsAdLoaderAdapter.this.isServerBidding()) {
                                        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(CallBackRunnable.this.f3921c, TTAbsAdLoaderAdapter.this.mAdSlot);
                                    }
                                    if (TTAbsAdLoaderAdapter.this.f3915c != null) {
                                        TTAbsAdLoaderAdapter.this.f3915c.onAdVideoCache();
                                    }
                                }
                            }, 1000L);
                            return;
                        }
                        this.f3921c.setCacheSuccess(true);
                        TTAbsAdLoaderAdapter.this.a(this.f3921c);
                        if (TTAbsAdLoaderAdapter.this.isServerBidding()) {
                            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.f3921c, TTAbsAdLoaderAdapter.this.mAdSlot);
                        }
                        if (TTAbsAdLoaderAdapter.this.f3915c != null) {
                            TTAbsAdLoaderAdapter.this.f3915c.onAdVideoCache();
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (TTAbsAdLoaderAdapter.this.r != 2) {
                    AdError adError2 = this.e;
                    TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter3 = TTAbsAdLoaderAdapter.this;
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.b(adError2, tTAbsAdLoaderAdapter3.mAdSlot, tTAbsAdLoaderAdapter3.d, TTAbsAdLoaderAdapter.this.m, TTAbsAdLoaderAdapter.this.n, TTAbsAdLoaderAdapter.this.o, TTAbsAdLoaderAdapter.this.getSdkVersion(), TTAbsAdLoaderAdapter.this.l);
                } else {
                    AdError adError3 = this.e;
                    TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter4 = TTAbsAdLoaderAdapter.this;
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(adError3, tTAbsAdLoaderAdapter4.mAdSlot, tTAbsAdLoaderAdapter4.d, TTAbsAdLoaderAdapter.this.m, TTAbsAdLoaderAdapter.this.n, TTAbsAdLoaderAdapter.this.o, TTAbsAdLoaderAdapter.this.getSdkVersion(), TTAbsAdLoaderAdapter.this.l);
                }
                b bVar = new b(this.e);
                bVar.a(TTAbsAdLoaderAdapter.this.mWaterfallAbTestParam);
                if (this.e != null) {
                    if (com.bytedance.msdk.base.b.f4464c) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(TTLogUtil.getTagThirdLevelByEvent(TTAbsAdLoaderAdapter.this.e, TTLogUtil.TAG_EVENT_FILL_FAIL));
                        sb.append("AdNetWorkName[");
                        sb.append(TTAbsAdLoaderAdapter.this.getAdNetWorkName());
                        sb.append("] AdUnitId[");
                        sb.append(TTAbsAdLoaderAdapter.this.s);
                        sb.append("] AdType[");
                        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter5 = TTAbsAdLoaderAdapter.this;
                        sb.append(tTAbsAdLoaderAdapter5.a(tTAbsAdLoaderAdapter5.mAdSlot, tTAbsAdLoaderAdapter5.getAdNetWorkName(), a.a(TTAbsAdLoaderAdapter.this.mAdSlot.getAdType(), TTAbsAdLoaderAdapter.this.u, TTAbsAdLoaderAdapter.this.mAdSlot.getAdStyleType())));
                        sb.append("] 请求失败 (loadSort=");
                        sb.append(TTAbsAdLoaderAdapter.this.f);
                        sb.append(",showSort=");
                        sb.append(TTAbsAdLoaderAdapter.this.g);
                        sb.append("),error=");
                        sb.append(this.e.thirdSdkErrorCode);
                        sb.append(",msg=");
                        sb.append(this.e.thirdSdkErrorMessage);
                        Logger.e("TTMediationSDK", sb.toString());
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(TTLogUtil.getTagThirdLevelByEvent(TTAbsAdLoaderAdapter.this.e, TTLogUtil.TAG_EVENT_FILL_FAIL));
                        sb2.append("AdNetWorkName[");
                        sb2.append(TTAbsAdLoaderAdapter.this.getAdNetWorkName());
                        sb2.append("] AdType[");
                        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter6 = TTAbsAdLoaderAdapter.this;
                        sb2.append(tTAbsAdLoaderAdapter6.a(tTAbsAdLoaderAdapter6.mAdSlot, tTAbsAdLoaderAdapter6.getAdNetWorkName(), a.a(TTAbsAdLoaderAdapter.this.mAdSlot.getAdType(), TTAbsAdLoaderAdapter.this.u, TTAbsAdLoaderAdapter.this.mAdSlot.getAdStyleType())));
                        sb2.append("] 请求失败 error=");
                        sb2.append(this.e.thirdSdkErrorCode);
                        sb2.append(",msg=");
                        sb2.append(this.e.thirdSdkErrorMessage);
                        Logger.e("TTMediationSDK", sb2.toString());
                    }
                    if (TTAbsAdLoaderAdapter.this.d != null) {
                        String str = this.e.thirdSdkErrorCode + "";
                        String strA = TTAbsAdLoaderAdapter.this.a(this.e.thirdSdkErrorMessage);
                        Logger.e("TTMediationSDK", "errorCode = " + str + " errorCodeList = " + strA);
                        c cVarA = c.a();
                        String strE = TTAbsAdLoaderAdapter.this.d.e();
                        String strD = TTAbsAdLoaderAdapter.this.d.d();
                        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter7 = TTAbsAdLoaderAdapter.this;
                        cVarA.a(strE, strD, tTAbsAdLoaderAdapter7.a(tTAbsAdLoaderAdapter7.d.e(), str, strA));
                    }
                }
                if (TTAbsAdLoaderAdapter.this.f3915c != null) {
                    TTAbsAdLoaderAdapter.this.f3915c.onAdFailed(bVar, dVar);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(AdSlot adSlot, String str, String str2) {
        return adSlot == null ? "" : str2;
    }

    public abstract void destroy();

    public final int getAdLoadCount() {
        return this.q;
    }

    public abstract String getAdNetWorkName();

    public AdSlot getAdSlot() {
        return this.mAdSlot;
    }

    public final String getAdSlotId() {
        return this.s;
    }

    public String getAdapterRit() {
        return this.e;
    }

    public final String getAdm() {
        g gVar;
        if (isServerBidding() && (gVar = this.p) != null) {
            return gVar.b();
        }
        return null;
    }

    public String getClientReqId() {
        String str = !TextUtils.isEmpty(this.i) ? this.i : null;
        if (TextUtils.isEmpty(this.s)) {
            return str;
        }
        return (str + "_") + this.s;
    }

    public int getIfTest() {
        h hVar = this.mTTExtraModel;
        if (hVar != null) {
            return hVar.f1929b;
        }
        return 0;
    }

    public abstract String getSdkVersion();

    public boolean hasNotifyFail() {
        return this.f3914b;
    }

    public boolean hasNotifySuccess() {
        return this.f3913a;
    }

    public boolean isClientBidding() {
        return this.r == 1;
    }

    public boolean isMultiBidding() {
        return this.r == 3;
    }

    public boolean isServerBidding() {
        return this.r == 2;
    }

    public abstract void loadAd(Context context, Map<String, Object> map);

    public final void loadAdInter(@NonNull final Context context, @NonNull i iVar, @NonNull final Map<String, Object> map, @NonNull AdSlot adSlot, h hVar, int i) {
        this.f3913a = false;
        this.f3914b = false;
        this.d = iVar;
        this.e = adSlot.getAdUnitId();
        this.q = adSlot.getAdCount();
        this.i = adSlot.getLinkedId();
        this.u = iVar.x();
        this.f = iVar.m();
        this.g = iVar.w();
        this.s = iVar.d();
        this.p = iVar.v();
        this.j = iVar.i();
        this.mAdSlot = adSlot;
        this.mTTExtraModel = hVar;
        this.r = iVar.f();
        this.mWaterfallAbTestParam = iVar.y();
        this.m = i;
        Object obj = map.get("key_mediation_rit_req_type");
        this.n = obj != null ? ((Integer) obj).intValue() : 1;
        Object obj2 = map.get("key_mediation_rit_req_type_src");
        this.o = obj2 != null ? ((Integer) obj2).intValue() : 1;
        Object obj3 = map.get("key_is_from_developer_req");
        boolean z = obj3 != null && ((Boolean) obj3).booleanValue();
        Object obj4 = map.get("const_is_custom");
        boolean z2 = obj4 != null && ((Boolean) obj4).booleanValue();
        if (!c.a().a(iVar.e(), iVar.d())) {
            AdBreakError adBreakError = new AdBreakError(AdError.ERROR_CODE_ADN_LOAD_FAIL_ERROR_CONTROL, AdError.getMessage(AdError.ERROR_CODE_ADN_LOAD_FAIL_ERROR_CONTROL));
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(iVar, adSlot, getSdkVersion(), z, 2, i, this.n, this.o, adBreakError);
            notifyAdFailed(adBreakError);
            return;
        }
        if (iVar != null && !iVar.C()) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(iVar, adSlot, getSdkVersion(), z, this.n == 4 ? 3 : 0, i, this.n, this.o, null);
        }
        if (!isServerBidding() && !isClientBidding() && !isMultiBidding()) {
            this.h = iVar.h();
        }
        this.k = System.currentTimeMillis();
        if (iVar != null && !z2) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().e(iVar.d(), getSdkVersion());
        }
        if (iVar == null || !(TextUtils.equals("mintegral", iVar.e()) || TextUtils.equals("baidu", iVar.e()) || TextUtils.equals("admob", iVar.e()))) {
            loadAd(context, map);
        } else {
            ThreadHelper.runOnUiThread(new Runnable() { // from class: com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    TTAbsAdLoaderAdapter.this.loadAd(context, map);
                }
            });
        }
    }

    public final void notifyAdFailed(AdError adError) {
        this.f3914b = true;
        if (this.f3913a) {
            return;
        }
        this.f3913a = true;
        this.l = System.currentTimeMillis() - this.k;
        a(e.f2649a, (TTBaseAd) null, (List<TTBaseAd>) null, adError);
    }

    public final void notifyAdLoaded(TTBaseAd tTBaseAd) {
        if (this.f3913a) {
            return;
        }
        this.f3913a = true;
        this.l = System.currentTimeMillis() - this.k;
        a("adload_ad", tTBaseAd, (List<TTBaseAd>) null, (AdError) null);
        i iVar = this.d;
        if (iVar != null && iVar.r() == 10 && this.u == 1) {
            notifyAdVideoCache(tTBaseAd, null);
        }
    }

    public final void notifyAdVideoCache(TTBaseAd tTBaseAd, AdError adError) {
        a("ad_video_cache", tTBaseAd, (List<TTBaseAd>) null, adError);
    }

    public void removeFromParent(View view) {
        c0.a(view);
    }

    public final void setAdapterListener(AdapterLoaderListener adapterLoaderListener) {
        this.f3915c = adapterLoaderListener;
    }

    public void setTotalTimedOut(boolean z) {
        this.t = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(TTBaseAd tTBaseAd) {
        i iVar;
        if (tTBaseAd == null) {
            return;
        }
        if (tTBaseAd.getCpm() == 0.0d) {
            double d = this.h;
            if (d != 0.0d) {
                tTBaseAd.setCpm(d);
            }
        }
        if (isServerBidding() && (iVar = this.d) != null) {
            tTBaseAd.setServerBiddingShowCpm(iVar.u());
            tTBaseAd.setServerBiddingLoadCpm(this.d.t());
        }
        tTBaseAd.setAdNetworkSlotType(this.r);
        tTBaseAd.setLoadSort(this.f);
        tTBaseAd.setShowSort(this.g);
        tTBaseAd.setSdkVersion(getSdkVersion());
        i iVar2 = this.d;
        tTBaseAd.setAdNetWorkName(iVar2 != null ? iVar2.e() : getAdNetWorkName());
        tTBaseAd.setExchangeRate(this.j);
        tTBaseAd.setAdNetworkSlotId(this.s);
        tTBaseAd.setRit(this.e);
        tTBaseAd.putEventParam("waterfall_abtest", this.mWaterfallAbTestParam);
        tTBaseAd.setSdkNum(a.b(getAdNetWorkName()));
        tTBaseAd.setMediationRitReqTypeFromRealReq(this.n);
        tTBaseAd.setMediationRitReqTypeSrcFromRealReq(this.o);
        tTBaseAd.setLinkIdFromRealReq(this.i);
        tTBaseAd.setMediationRitReqType(this.n);
        tTBaseAd.setMediationRitReqTypeSrc(this.o);
        i iVar3 = this.d;
        tTBaseAd.setOriginType(iVar3 != null ? iVar3.p() : -1);
        tTBaseAd.setSubAdType(this.u);
        y.a(tTBaseAd, this.d, null, false);
        h hVar = this.mTTExtraModel;
        if (hVar != null) {
            tTBaseAd.putEventParam("if_test", Integer.valueOf(hVar.f1929b));
            tTBaseAd.putEventParam("server_bidding_extra", this.mTTExtraModel.f1928a);
        }
    }

    private void a(String str, TTBaseAd tTBaseAd, List<TTBaseAd> list, AdError adError) {
        ThreadHelper.runOnMSDKThread(new CallBackRunnable(str, tTBaseAd, list, adError));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3) || !str.equals("gdt")) {
            return str2;
        }
        return str2 + "_" + str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005a, code lost:
    
        r7 = new java.lang.StringBuilder();
        r0 = r0.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0067, code lost:
    
        if (r0.hasNext() == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0069, code lost:
    
        r7.append((java.lang.String) r0.next());
        r7.append("_");
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007c, code lost:
    
        if (r7.length() <= 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007e, code lost:
    
        r7.delete(r7.length() - 1, r7.length());
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008f, code lost:
    
        return r7.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(java.lang.String r7) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 == 0) goto Le
            java.lang.String r7 = ""
            return r7
        Le:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            char[] r7 = r7.toCharArray()
            r2 = 0
            r3 = 0
        L19:
            int r4 = r7.length
            if (r3 >= r4) goto L5a
            char r4 = r7[r3]
            r5 = 48
            if (r4 < r5) goto L36
            r5 = 57
            if (r4 > r5) goto L36
            r1.append(r4)
            int r4 = r7.length
            int r4 = r4 + (-1)
            if (r3 != r4) goto L4a
            java.lang.String r7 = r1.toString()
            r0.add(r7)
            goto L5a
        L36:
            int r4 = r1.length()
            if (r4 <= 0) goto L4a
            java.lang.String r4 = r1.toString()
            r0.add(r4)
            int r4 = r1.length()
            r1.delete(r2, r4)
        L4a:
            int r4 = r0.size()
            if (r4 <= 0) goto L57
            java.lang.Object r7 = r0.get(r2)
            java.lang.String r7 = (java.lang.String) r7
            return r7
        L57:
            int r3 = r3 + 1
            goto L19
        L5a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.util.Iterator r0 = r0.iterator()
        L63:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L78
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            r7.append(r1)
            java.lang.String r1 = "_"
            r7.append(r1)
            goto L63
        L78:
            int r0 = r7.length()
            if (r0 <= 0) goto L8b
            int r0 = r7.length()
            int r0 = r0 + (-1)
            int r1 = r7.length()
            r7.delete(r0, r1)
        L8b:
            java.lang.String r7 = r7.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.a(java.lang.String):java.lang.String");
    }

    public final void notifyAdLoaded(List<TTBaseAd> list) {
        if (this.f3913a) {
            return;
        }
        this.f3913a = true;
        this.l = System.currentTimeMillis() - this.k;
        a("adload_ads", (TTBaseAd) null, list, (AdError) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, TTBaseAd tTBaseAd, int i2) {
        h hVar;
        String str = i == 20000 ? AdError.AD_LOAD_SUCCESS_MSG : AdError.AD_NO_FILL;
        int i3 = 0;
        if ((isClientBidding() || isMultiBidding()) && (hVar = this.mTTExtraModel) != null && hVar.d) {
            i3 = 3;
        }
        int i4 = this.t ? 1 : i3;
        if (this.r != 2 && tTBaseAd != null) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(tTBaseAd, i, str, this.l, this.mAdSlot, this.m, i2, i4);
        }
        if (com.bytedance.msdk.base.b.f4464c) {
            Logger.i("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.e, TTLogUtil.TAG_EVENT_FILL) + "AdNetWorkName[" + getAdNetWorkName() + "] AdUnitId[" + this.s + "] AdType[" + a(this.mAdSlot, getAdNetWorkName(), a.a(this.mAdSlot.getAdType(), this.u, this.mAdSlot.getAdStyleType())) + "] 请求成功 (loadSort=" + this.f + ",showSort=" + this.g + ")");
            return;
        }
        Logger.i("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.e, TTLogUtil.TAG_EVENT_FILL) + "AdNetWorkName[" + getAdNetWorkName() + "] AdType[" + a(this.mAdSlot, getAdNetWorkName(), a.a(this.mAdSlot.getAdType(), this.u, this.mAdSlot.getAdStyleType())) + "] 请求成功");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(TTBaseAd tTBaseAd) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.mAdSlot, tTBaseAd);
        if (com.bytedance.msdk.base.b.f4464c) {
            Logger.i("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.e, TTLogUtil.TAG_EVENT_FILL) + "AdNetWorkName[" + getAdNetWorkName() + "] AdUnitId[" + this.s + "] AdType[" + a.a(this.mAdSlot.getAdType(), this.u, this.mAdSlot.getAdStyleType()) + "] 视频缓存成功 (loadSort=" + this.f + ",showSort=" + this.g + ")");
            return;
        }
        Logger.i("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.e, TTLogUtil.TAG_EVENT_FILL) + "AdNetWorkName[" + getAdNetWorkName() + "] AdType[" + a.a(this.mAdSlot.getAdType(), this.u, this.mAdSlot.getAdStyleType()) + "] 视频缓存成功 ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        return bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().F() && (q.e(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()) || q.f(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()));
    }
}
