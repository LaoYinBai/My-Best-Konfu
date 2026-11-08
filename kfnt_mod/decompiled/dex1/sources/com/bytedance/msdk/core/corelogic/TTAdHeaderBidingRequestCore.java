package com.bytedance.msdk.core.corelogic;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Keep;
import androidx.core.app.NotificationManagerCompat;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.x;
import com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter;
import com.bytedance.msdk.adapter.ThirdSdkInit;
import com.bytedance.msdk.adapter.listener.ITTAdatperCallback;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.error.AdBreakError;
import com.bytedance.msdk.api.error.AdFreqError;
import com.bytedance.msdk.api.error.AdPacingError;
import com.bytedance.msdk.api.v2.GMNetworkRequestInfo;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter;
import com.bytedance.msdk.api.v2.ad.custom.bean.GMCustomAdConfig;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class TTAdHeaderBidingRequestCore extends com.bytedance.msdk.core.corelogic.d implements TTAbsAdLoaderAdapter.AdapterLoaderListener {
    private long A;
    private long B;
    private long C;
    private bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c D;
    protected Context E;
    protected ITTAdatperCallback F;
    protected TTBaseAd I;
    protected GMNetworkRequestInfo N;
    private boolean R;
    private long z;
    protected volatile boolean G = false;
    protected volatile boolean H = false;
    private final Map<String, TTAbsAdLoaderAdapter> J = new HashMap();
    private int K = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
    private int L = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
    private final AtomicBoolean M = new AtomicBoolean(false);
    private ConcurrentHashMap<String, AdLoadInfo> O = new ConcurrentHashMap<>();
    private long P = 0;
    private long Q = 0;
    private final Map<Integer, List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i>> S = new HashMap();
    private boolean T = false;
    private final Comparator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> U = new c(this);
    private final Comparator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> V = new d(this);

    class a implements Runnable {

        /* renamed from: com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore$a$a, reason: collision with other inner class name */
        class RunnableC0132a implements Runnable {
            RunnableC0132a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                TTAdHeaderBidingRequestCore.this.E();
            }
        }

        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!TTAdHeaderBidingRequestCore.this.f4485a.get() || TTAdHeaderBidingRequestCore.this.f4487c.get()) {
                return;
            }
            TTAdHeaderBidingRequestCore.this.f4487c.set(true);
            AdSlot adSlot = TTAdHeaderBidingRequestCore.this.h;
            if ((adSlot != null && adSlot.getAdType() == 7) || TTAdHeaderBidingRequestCore.this.h.getAdType() == 8) {
                Logger.w("TTMediationSDK", TTLogUtil.getTagSecondLevel(TTAdHeaderBidingRequestCore.this.f) + "m-sdk----设置 cacheTimeout 已到时间【" + bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().c(TTAdHeaderBidingRequestCore.this.h.getAdUnitId()) + "】，给外部invokeAdVideoCache ...");
            }
            if (TTAdHeaderBidingRequestCore.this.o()) {
                return;
            }
            TTAdHeaderBidingRequestCore.this.V();
            ThreadHelper.runOnUiThread(new RunnableC0132a());
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TTAdHeaderBidingRequestCore.this.E();
        }
    }

    class c implements Comparator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> {
        c(TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore) {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar2) {
            return iVar.m() == iVar2.m() ? iVar.w() - iVar2.w() : iVar.m() - iVar2.m();
        }
    }

    class d implements Comparator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> {
        d(TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore) {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar2) {
            return (iVar.q() > iVar2.q() ? 1 : (iVar.q() == iVar2.q() ? 0 : -1)) >= 0 ? -1 : 1;
        }
    }

    class e extends Handler {
        e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            TTAdHeaderBidingRequestCore.this.a(message);
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
            List<Integer> list;
            Map<Integer, List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i>> map;
            TTAdHeaderBidingRequestCore.this.P = SystemClock.elapsedRealtime();
            if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c.i()) {
                Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(TTAdHeaderBidingRequestCore.this.f) + "MSDK未进行初始化！！！");
                TTAdHeaderBidingRequestCore.this.e(1);
                TTAdHeaderBidingRequestCore.this.a(new AdBreakError(1, AdError.getMessage(1)), (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
                return;
            }
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore = TTAdHeaderBidingRequestCore.this;
            if (tTAdHeaderBidingRequestCore.E == null) {
                Logger.e("TTMediationSDK", "Context为null！！！");
                TTAdHeaderBidingRequestCore.this.e(AdError.ERROR_CODE_CONTEXT_ERROR);
                TTAdHeaderBidingRequestCore.this.a(new AdBreakError(AdError.ERROR_CODE_CONTEXT_ERROR, AdError.getMessage(AdError.ERROR_CODE_CONTEXT_ERROR)), (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
                return;
            }
            if (tTAdHeaderBidingRequestCore.h == null) {
                Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(TTAdHeaderBidingRequestCore.this.f) + "AdSlot can't be null！！");
                TTAdHeaderBidingRequestCore.this.e(AdError.ERROR_CODE_ADSLOT_CONFIG_ERROR);
                TTAdHeaderBidingRequestCore.this.a(new AdBreakError(AdError.ERROR_CODE_ADSLOT_CONFIG_ERROR, AdError.getMessage(AdError.ERROR_CODE_ADSLOT_CONFIG_ERROR)), (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
                return;
            }
            if (tTAdHeaderBidingRequestCore.d == null && tTAdHeaderBidingRequestCore.D != null) {
                TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore2 = TTAdHeaderBidingRequestCore.this;
                tTAdHeaderBidingRequestCore2.d = tTAdHeaderBidingRequestCore2.D.b(TTAdHeaderBidingRequestCore.this.f);
                TTAdHeaderBidingRequestCore.this.H();
            }
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore3 = TTAdHeaderBidingRequestCore.this;
            tTAdHeaderBidingRequestCore3.G = true;
            tTAdHeaderBidingRequestCore3.Y();
            if (com.bytedance.msdk.core.corelogic.a.a().b()) {
                Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(TTAdHeaderBidingRequestCore.this.f) + "请求过于频繁，触发了熔断机制");
                TTAdHeaderBidingRequestCore.this.e(AdError.ERROR_CODE_REQUEST_CIRCUIT_BREAKER);
                TTAdHeaderBidingRequestCore.this.a(new AdBreakError(AdError.ERROR_CODE_REQUEST_CIRCUIT_BREAKER, AdError.getMessage(AdError.ERROR_CODE_REQUEST_CIRCUIT_BREAKER)), (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
                return;
            }
            String testSlotId = TTAdHeaderBidingRequestCore.this.h.getTestSlotId();
            if (!TextUtils.isEmpty(testSlotId) && TTAdHeaderBidingRequestCore.this.e() == 2) {
                TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore4 = TTAdHeaderBidingRequestCore.this;
                tTAdHeaderBidingRequestCore4.d = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_goto109.a.a(tTAdHeaderBidingRequestCore4.d, testSlotId);
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = TTAdHeaderBidingRequestCore.this.d;
                int iR = cVar != null ? cVar.r() : 0;
                TTAdHeaderBidingRequestCore.this.H();
                Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(TTAdHeaderBidingRequestCore.this.f) + "测试工具加载广告........totalWaterFallCount: " + iR + "  ,rit_id:" + TTAdHeaderBidingRequestCore.this.f + " ,slot_id:" + testSlotId);
            }
            TTAdHeaderBidingRequestCore.this.I();
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore5 = TTAdHeaderBidingRequestCore.this;
            if (tTAdHeaderBidingRequestCore5.d == null) {
                if (tTAdHeaderBidingRequestCore5.h.getAdType() == 3 && !bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().t() && TTAdHeaderBidingRequestCore.this.N != null) {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(TTAdHeaderBidingRequestCore.this.f) + "settings config.......AdUnitId = " + TTAdHeaderBidingRequestCore.this.f + "  开屏广告走了开发者自定义兜底方案   adnName:" + com.bytedance.msdk.base.a.a(TTAdHeaderBidingRequestCore.this.N.getAdNetworkFlatFromId()));
                    TTAdHeaderBidingRequestCore.this.e(AdError.ERROR_CODE_CONFIG_ERROR);
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(TTAdHeaderBidingRequestCore.this.h, -4);
                    TTAdHeaderBidingRequestCore.this.R();
                    return;
                }
                if (TTAdHeaderBidingRequestCore.this.D == null || TTAdHeaderBidingRequestCore.this.D.e() == null || TTAdHeaderBidingRequestCore.this.D.e().size() == 0) {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(TTAdHeaderBidingRequestCore.this.f) + "settings config.......没有settings config配置信息,AdUnitId = " + TTAdHeaderBidingRequestCore.this.h.getAdUnitId());
                    TTAdHeaderBidingRequestCore.this.e(AdError.ERROR_CODE_REQUEST_BUT_CONFIG_ERROR);
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(TTAdHeaderBidingRequestCore.this.h, 1);
                } else {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(TTAdHeaderBidingRequestCore.this.f) + "settings config.......注意，AdUnitId = " + TTAdHeaderBidingRequestCore.this.h.getAdUnitId() + " 的配置信息为 null ！！");
                    TTAdHeaderBidingRequestCore.this.e(AdError.ERROR_CODE_REQUEST_BUT_CONFIG_ERROR);
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(TTAdHeaderBidingRequestCore.this.h, 2);
                }
                TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore6 = TTAdHeaderBidingRequestCore.this;
                tTAdHeaderBidingRequestCore6.a(tTAdHeaderBidingRequestCore6.f, tTAdHeaderBidingRequestCore6.M);
                return;
            }
            tTAdHeaderBidingRequestCore5.v = tTAdHeaderBidingRequestCore5.h.getAdCount();
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore7 = TTAdHeaderBidingRequestCore.this;
            tTAdHeaderBidingRequestCore7.e = tTAdHeaderBidingRequestCore7.d.t();
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore8 = TTAdHeaderBidingRequestCore.this;
            tTAdHeaderBidingRequestCore8.n = new ArrayList(tTAdHeaderBidingRequestCore8.d.e());
            if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().e(TTAdHeaderBidingRequestCore.this.f) || (list = TTAdHeaderBidingRequestCore.this.n) == null || list.size() == 0 || (map = TTAdHeaderBidingRequestCore.this.e) == null || map.size() == 0) {
                Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(TTAdHeaderBidingRequestCore.this.f) + "settings config.......注意，AdUnitId = " + TTAdHeaderBidingRequestCore.this.h.getAdUnitId() + "  没有对应的waterfall配置信息");
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(TTAdHeaderBidingRequestCore.this.h, 3);
                TTAdHeaderBidingRequestCore.this.e(AdError.ERROR_CODE_REQUEST_BUT_CONFIG_ERROR);
                TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore9 = TTAdHeaderBidingRequestCore.this;
                tTAdHeaderBidingRequestCore9.a(tTAdHeaderBidingRequestCore9.f, tTAdHeaderBidingRequestCore9.M);
                return;
            }
            if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().b(TTAdHeaderBidingRequestCore.this.f + "")) {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.g gVarH = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().h(TTAdHeaderBidingRequestCore.this.f + "");
                Pair<String, String> pairF = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().f(TTAdHeaderBidingRequestCore.this.f);
                if (gVarH == null || pairF == null) {
                    return;
                }
                AdFreqError adFreqError = new AdFreqError(AdError.ERROR_CODE_EXCEED_FREQCTL, AdError.getMessage(AdError.ERROR_CODE_EXCEED_FREQCTL), (String) pairF.second, (String) pairF.first);
                TTAdHeaderBidingRequestCore.this.e(adFreqError.code);
                TTAdHeaderBidingRequestCore.this.a(adFreqError, (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
                return;
            }
            if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().c(TTAdHeaderBidingRequestCore.this.f + "") && !TTAdHeaderBidingRequestCore.this.o()) {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.h hVarI = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().i(TTAdHeaderBidingRequestCore.this.f + "");
                if (hVarI != null) {
                    AdPacingError adPacingError = new AdPacingError(AdError.ERROR_CODE_EXCEED_PACING, AdError.getMessage(AdError.ERROR_CODE_EXCEED_PACING), bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i.b().g(TTAdHeaderBidingRequestCore.this.f) + "", hVarI.f());
                    TTAdHeaderBidingRequestCore.this.e(adPacingError.code);
                    TTAdHeaderBidingRequestCore.this.a(adPacingError, (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
                    return;
                }
                return;
            }
            com.bytedance.msdk.core.corelogic.c.a(TTAdHeaderBidingRequestCore.this.n);
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b bVarA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a();
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore10 = TTAdHeaderBidingRequestCore.this;
            if (bVarA.a(tTAdHeaderBidingRequestCore10.f, tTAdHeaderBidingRequestCore10.e()) != 0) {
                boolean z = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().f(TTAdHeaderBidingRequestCore.this.f) == null;
                if (!TTAdHeaderBidingRequestCore.this.o() && !z && TTAdHeaderBidingRequestCore.this.N()) {
                    TTAdHeaderBidingRequestCore.this.T = true;
                    TTAdHeaderBidingRequestCore.this.K();
                    return;
                }
                if (!TTAdHeaderBidingRequestCore.this.o() && !z) {
                    TTAdHeaderBidingRequestCore.this.h.setPrimeRitReqType(3);
                }
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b bVarA2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a();
                TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore11 = TTAdHeaderBidingRequestCore.this;
                bVarA2.b(tTAdHeaderBidingRequestCore11.f, tTAdHeaderBidingRequestCore11.h);
            }
            TTAdHeaderBidingRequestCore.this.T();
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore12 = TTAdHeaderBidingRequestCore.this;
            tTAdHeaderBidingRequestCore12.u.a(tTAdHeaderBidingRequestCore12.n);
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore13 = TTAdHeaderBidingRequestCore.this;
            tTAdHeaderBidingRequestCore13.u.b(tTAdHeaderBidingRequestCore13.d.r());
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore14 = TTAdHeaderBidingRequestCore.this;
            Handler handler = tTAdHeaderBidingRequestCore14.g;
            if (handler != null) {
                handler.sendEmptyMessageDelayed(2, tTAdHeaderBidingRequestCore14.B);
            }
            TTAdHeaderBidingRequestCore.this.a(0, false);
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f()).d().c();
        }
    }

    class g implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ GMCustomBaseAdapter f4470a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i f4471b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f4472c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        g(GMCustomBaseAdapter gMCustomBaseAdapter, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar, int i, int i2, int i3) {
            this.f4470a = gMCustomBaseAdapter;
            this.f4471b = iVar;
            this.f4472c = i;
            this.d = i2;
            this.e = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f4470a.loadInner(TTAdHeaderBidingRequestCore.this.E, TTAdHeaderBidingRequestCore.this.h, TTAdHeaderBidingRequestCore.this.y, this.f4471b, TTAdHeaderBidingRequestCore.this.a(this.f4472c, this.d, this.f4471b), TTAdHeaderBidingRequestCore.this.x, this.e, TTAdHeaderBidingRequestCore.this);
            } catch (Exception e) {
                TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore = TTAdHeaderBidingRequestCore.this;
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("", tTAdHeaderBidingRequestCore.I, tTAdHeaderBidingRequestCore.h, this.f4471b, new AdError(AdError.ERROR_CODE_CUSTOM_LOAD, "自定义Adapter 加载广告内部错误 " + e.getClass().getName()));
                e.printStackTrace();
            }
        }
    }

    class h implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f4473a;

        h(String str) {
            this.f4473a = str;
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
            TTAdHeaderBidingRequestCore.this.d(this.f4473a);
        }
    }

    class i implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f4475a;

        i(String str) {
            this.f4475a = str;
        }

        @Override // java.lang.Runnable
        public void run() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore = TTAdHeaderBidingRequestCore.this;
            ThirdSdkInit.initTTKlevinSDK(tTAdHeaderBidingRequestCore.E, tTAdHeaderBidingRequestCore.N.getAppId());
            TTAdHeaderBidingRequestCore.this.d(this.f4475a);
        }
    }

    class j implements Runnable {
        j() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (TTAdHeaderBidingRequestCore.this.g != null) {
                    TTAdHeaderBidingRequestCore.this.g.removeCallbacksAndMessages(null);
                }
                if (TTAdHeaderBidingRequestCore.this.q != null) {
                    TTAdHeaderBidingRequestCore.this.q.clear();
                }
                if (TTAdHeaderBidingRequestCore.this.o != null) {
                    TTAdHeaderBidingRequestCore.this.o.clear();
                }
                if (TTAdHeaderBidingRequestCore.this.p != null) {
                    TTAdHeaderBidingRequestCore.this.p.clear();
                }
                if (TTAdHeaderBidingRequestCore.this.I != null) {
                    TTAdHeaderBidingRequestCore.this.I.onDestroy();
                }
                if (TTAdHeaderBidingRequestCore.this.J != null) {
                    for (Map.Entry entry : TTAdHeaderBidingRequestCore.this.J.entrySet()) {
                        if (entry != null) {
                            TTAbsAdLoaderAdapter tTAbsAdLoaderAdapter = (TTAbsAdLoaderAdapter) entry.getValue();
                            tTAbsAdLoaderAdapter.setAdapterListener(null);
                            tTAbsAdLoaderAdapter.destroy();
                        }
                    }
                    TTAdHeaderBidingRequestCore.this.J.clear();
                }
                TTAdHeaderBidingRequestCore.this.t.clear();
                TTAdHeaderBidingRequestCore.this.u.f();
            } catch (Throwable unused) {
            }
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore = TTAdHeaderBidingRequestCore.this;
            tTAdHeaderBidingRequestCore.F = null;
            tTAdHeaderBidingRequestCore.D = null;
            TTAdHeaderBidingRequestCore tTAdHeaderBidingRequestCore2 = TTAdHeaderBidingRequestCore.this;
            tTAdHeaderBidingRequestCore2.e = null;
            tTAdHeaderBidingRequestCore2.E = null;
            tTAdHeaderBidingRequestCore2.N = null;
        }
    }

    class k implements Runnable {
        k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TTAdHeaderBidingRequestCore.this.D();
        }
    }

    class l implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AdError f4479a;

        l(AdError adError) {
            this.f4479a = adError;
        }

        @Override // java.lang.Runnable
        public void run() {
            TTAdHeaderBidingRequestCore.this.a(this.f4479a);
        }
    }

    class m implements Runnable {
        m() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TTAdHeaderBidingRequestCore.this.E();
        }
    }

    protected TTAdHeaderBidingRequestCore(Context context, String str) {
        Map<Integer, List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i>> mapT;
        this.E = context;
        this.f = str;
        this.D = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f();
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.c cVar = this.D;
        if (cVar != null) {
            a(cVar.l());
            b(this.D.n());
            this.d = this.D.b(this.f);
            H();
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar2 = this.d;
        if (cVar2 != null && (mapT = cVar2.t()) != null) {
            for (Map.Entry<Integer, List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i>> entry : mapT.entrySet()) {
                List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> value = entry.getValue();
                if (value != null && value.size() > 0 && value.get(0).f() == 100) {
                    this.S.put(entry.getKey(), entry.getValue());
                }
            }
        }
        this.g = new e(ThreadHelper.getMSDKThreadLooper());
    }

    private int J() {
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> list;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        int size = 0;
        if (cVar == null) {
            return 0;
        }
        Map<Integer, List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i>> mapT = cVar.t();
        if (mapT != null && mapT.size() != 0) {
            ArrayList<Integer> arrayList = new ArrayList();
            arrayList.addAll(mapT.keySet());
            for (Integer num : arrayList) {
                if (this.u.e(num.intValue()) && (list = mapT.get(num)) != null) {
                    size += list.size();
                }
            }
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K() {
        AdSlot adSlotA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().a(this.f);
        if (adSlotA != null) {
            this.h.setLinkedId(adSlotA.getLinkedId());
            this.h.setPrimeRitReqType(adSlotA.getPrimeRitReqType());
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().b(this.f, this.h);
        T();
        S();
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.c cVarC = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().c(this.f);
        if (cVarC != null) {
            this.x = cVarC.x;
            this.O = cVarC.y();
        }
        n();
    }

    private boolean L() {
        List<TTBaseAd> list = this.q;
        if (list != null) {
            Iterator<TTBaseAd> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().isCacheSuccess()) {
                    return true;
                }
            }
        }
        List<TTBaseAd> list2 = this.p;
        if (list2 != null) {
            Iterator<TTBaseAd> it2 = list2.iterator();
            while (it2.hasNext()) {
                if (it2.next().isCacheSuccess()) {
                    return true;
                }
            }
        }
        List<TTBaseAd> list3 = this.o;
        if (list3 == null) {
            return false;
        }
        Iterator<TTBaseAd> it3 = list3.iterator();
        while (it3.hasNext()) {
            if (it3.next().isCacheSuccess()) {
                return true;
            }
        }
        return false;
    }

    private boolean M() {
        return !o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean N() {
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f> listG;
        Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "mLoadSortList: " + this.n);
        if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().a(this.f, this.h) && (listG = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().g(this.f)) != null && listG.size() > 0) {
            for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f fVar : listG) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(fVar.f1866a);
                d(arrayList);
            }
            if (this.q.size() > 0 || this.p.size() > 0 || this.o.size() > 0) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "--==-- hit cache!!! -----");
                return true;
            }
        }
        Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "--==-- not hit cache!!! -----");
        return false;
    }

    private boolean O() {
        Map<String, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f> mapB = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().b(this.f);
        return mapB != null && mapB.size() > 0;
    }

    private boolean P() {
        return o() && g() == 1;
    }

    private boolean Q() {
        return o() && g() == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0086, code lost:
    
        if (((((96 - 1) * 96) * ((96 * 2) - 1)) % 6) != 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0094, code lost:
    
        if (((((22 - 1) * 22) * ((22 * 2) - 1)) % 6) == 0) goto L81;
     */
    /* JADX WARN: Removed duplicated region for block: B:70:0x014a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x006e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void R() throws java.lang.IllegalAccessException, java.lang.InstantiationException, java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instructions count: 384
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore.R():void");
    }

    private void S() {
        if (this.w.get() || this.R) {
            return;
        }
        this.Q = SystemClock.elapsedRealtime();
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.h, this.d, this.Q - this.P, h());
        this.w.set(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.h, cVar != null ? cVar.v() : null, !o(), h(), 0);
    }

    private void U() {
        TTAbsAdLoaderAdapter value;
        for (Map.Entry<String, TTAbsAdLoaderAdapter> entry : this.J.entrySet()) {
            if (entry != null && (value = entry.getValue()) != null) {
                value.setTotalTimedOut(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        AdSlot adSlot = this.h;
        if ((adSlot == null || adSlot.getAdType() != 7) && this.h.getAdType() != 8) {
            return;
        }
        AdSlot adSlot2 = this.h;
        TTBaseAd tTBaseAd = this.I;
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.Q;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(adSlot2, tTBaseAd, jElapsedRealtime, cVar != null ? cVar.v() : null);
    }

    private void W() {
        if (this.g != null) {
            if (!L()) {
                this.g.postDelayed(new a(), bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().c(this.h.getAdUnitId()));
                return;
            }
            if (!this.f4485a.get() || this.f4487c.get()) {
                return;
            }
            this.f4487c.set(true);
            AdSlot adSlot = this.h;
            if ((adSlot != null && adSlot.getAdType() == 7) || this.h.getAdType() == 8) {
                Logger.w("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "从onAdLoaded给外部invokeAdVideoCache ...");
            }
            if (o()) {
                return;
            }
            V();
            ThreadHelper.runOnUiThread(new m());
        }
    }

    private void X() {
        Handler handler = this.g;
        if (handler != null) {
            handler.removeMessages(4);
            this.g.removeMessages(1);
            this.g.removeMessages(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y() {
        this.I = null;
        this.M.set(false);
        this.w.set(false);
        this.p.clear();
        this.o.clear();
        this.q.clear();
        this.f4485a.set(false);
        this.f4486b.set(false);
        this.f4487c.set(false);
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        com.bytedance.msdk.core.corelogic.g gVar = this.u;
        if (gVar != null) {
            gVar.f();
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.h hVar = this.x;
        if (hVar != null) {
            hVar.f1928a = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Object> a(int i2, int i3, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar) {
        return com.bytedance.msdk.core.corelogic.f.a(iVar, this.h, this.t, null, this.N, i2, i3, !o());
    }

    private void a(String str, String str2, String str3, int i2, String str4) {
        AdLoadInfo adLoadInfo = this.O.get(str);
        if (adLoadInfo == null) {
            adLoadInfo = new AdLoadInfo();
        }
        adLoadInfo.setMediationRit(str).setAdnName(str2).setErrCode(i2).setErrMsg(str4).setAdType(str3);
        this.O.put(str, adLoadInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, AtomicBoolean atomicBoolean) {
        if (o()) {
            return;
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d();
        dVar.c(-1);
        dVar.d(-1);
        if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().d(str)) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "settings config.......AdUnitId = " + str + "  暂无config配置信息");
            a(new AdError(AdError.ERROR_CODE_NO_CONFIG, AdError.getMessage(AdError.ERROR_CODE_NO_CONFIG)), dVar);
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f()).d().c();
            return;
        }
        a(new AdError(AdError.ERROR_CODE_ADSLOT_ID_ERROR, AdError.getMessage(AdError.ERROR_CODE_ADSLOT_ID_ERROR)), dVar);
        while (true) {
            char c2 = 14;
            while (true) {
                switch (c2) {
                    case '\f':
                        break;
                    case '\r':
                        return;
                    case 14:
                    default:
                        c2 = '\r';
                }
            }
        }
    }

    private boolean a(int i2, int i3, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar, int i4) {
        String strA = com.bytedance.msdk.core.corelogic.f.a(iVar.n(), com.bytedance.msdk.core.corelogic.f.a(iVar.e()), com.bytedance.msdk.base.a.a(iVar.r(), iVar.x()));
        if (strA == null || !com.bytedance.msdk.core.corelogic.f.b(strA)) {
            a(iVar, i2, i3);
            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "构建adapter完整全类名异常：className=" + strA);
            return false;
        }
        TTAbsAdLoaderAdapter tTAbsAdLoaderAdapterA = com.bytedance.msdk.core.corelogic.f.a(this.J, iVar, strA);
        if (tTAbsAdLoaderAdapterA == null || this.E == null) {
            a(iVar, i2, i3);
            return false;
        }
        Logger.e("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(this.f, TTLogUtil.TAG_EVENT_REQUEST) + "创建adapter成功!! class=" + strA + "adn_version:" + tTAbsAdLoaderAdapterA.getSdkVersion());
        tTAbsAdLoaderAdapterA.setAdapterListener(this);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.h hVar = this.x;
        if (hVar != null) {
            iVar.g(hVar.f1928a);
        }
        if (iVar.f() == 2 && iVar.v() != null) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(iVar, this.h, tTAbsAdLoaderAdapterA.getSdkVersion());
        }
        tTAbsAdLoaderAdapterA.loadAdInter(this.E, iVar, a(i2, i3, iVar), this.h, this.x, i4);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(this.f, iVar.d(), iVar, i4);
        return true;
    }

    private boolean a(Class<?> cls, Class<?> cls2, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar) {
        String strB;
        if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.b.a(cls, cls2)) {
            strB = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.b.b(cls, cls2);
        } else {
            strB = "自定义ADN初始化对象失败  ---------  初始化类 " + cls.getSimpleName() + " 需要继承自自定义ADN的广告对象基类 " + cls2.getSimpleName();
        }
        if (TextUtils.isEmpty(strB)) {
            return true;
        }
        Logger.e("TTMediationSDK_SDK_Init", strB);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("", this.I, this.h, iVar, new AdError(AdError.ERROR_CODE_CUSTOM_LOAD, strB));
        return false;
    }

    private boolean a(String str, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar, int i2) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(this.f, str, iVar, i2);
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f> listA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(str, this.h);
        if (listA == null || listA.size() <= 0) {
            return false;
        }
        TTBaseAd tTBaseAd = listA.get(0).f1866a;
        AdSlot adSlot = this.h;
        int mediationRitReqType = tTBaseAd.getMediationRitReqType(adSlot != null ? adSlot.getLinkedId() : null);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.b(iVar, this.h, 3, mediationRitReqType);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(iVar, this.h, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(str), !o(), 1, i2, 3, mediationRitReqType, null);
        ArrayList arrayList = new ArrayList();
        for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f fVar : listA) {
            fVar.f1866a.setMediationRitReqType(3);
            fVar.f1866a.setMediationRitReqTypeSrc(mediationRitReqType);
            a(fVar.f1866a, str);
            arrayList.add(fVar.f1866a);
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(listA.get(0).f1866a, 0, "adn cache命中", 0L, this.h, i2, arrayList.size(), 2);
        onAdLoaded(arrayList, listA.get(0).f1867b);
        return true;
    }

    private int b(int i2) {
        List<Integer> list = this.n;
        if (list == null) {
            return -1;
        }
        return list.indexOf(Integer.valueOf(i2)) + 1;
    }

    private void b(AdError adError) {
        this.R = true;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.h, adError, cVar != null ? cVar.v() : null, h());
    }

    private void b(List<TTBaseAd> list, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar) {
        if (list == null || list.size() <= 0) {
            return;
        }
        String adNetworkSlotId = list.get(0).getAdNetworkSlotId();
        if (list.get(0).canAdReuse() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, adNetworkSlotId, e())) {
            ArrayList arrayList = new ArrayList();
            for (TTBaseAd tTBaseAd : list) {
                if (tTBaseAd != null) {
                    arrayList.add(new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f(tTBaseAd, dVar, 0L, SystemClock.elapsedRealtime(), this.h));
                }
            }
            if (arrayList.size() > 0) {
                TTBaseAd tTBaseAd2 = ((bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f) arrayList.get(0)).f1866a;
                Logger.d("TTMediationSDK", "--==-- 广告复用:广告缓存成功 -------" + tTBaseAd2.getAdNetWorkName() + ", adType: " + com.bytedance.msdk.base.a.a(tTBaseAd2.getAdType(), tTBaseAd2.getSubAdType()) + ", adnSlotId: " + tTBaseAd2.getAdNetworkSlotId() + ", ad个数: " + arrayList.size());
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().a(adNetworkSlotId, arrayList);
            }
        }
    }

    private boolean b(int i2, int i3, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar, int i4) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        String str = "";
        if (this.y == null) {
            Logger.e("TTMediationSDK_SDK_Init", "自定义Adapter 老API不支持自定义Adapter，请使用新的API");
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("", this.I, this.h, iVar, new AdError(AdError.ERROR_CODE_CUSTOM_LOAD, "自定义Adapter 老API不支持自定义Adapter，请使用新的API"));
            return false;
        }
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.a aVar = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().d().get(iVar.e());
        if (aVar == null) {
            str = "自定义Adapter 配置为空";
            a(iVar, i2, i3);
        } else {
            Logger.i("TTMediationSDK_SDK_Init", "自定义Adapter 获取配置wfcBean = " + iVar);
            try {
                GMCustomAdConfig className = aVar.c().getClassName(iVar.r(), iVar.x());
                if (className == null) {
                    str = "自定义Adapter 配置的className为空，无法反射创建自定义广告Adapter对象 ";
                } else {
                    Class<?> cls = Class.forName(className.getClassName());
                    if (a(cls, className.getClazz(), iVar)) {
                        try {
                            Object objNewInstance = cls.newInstance();
                            if (objNewInstance instanceof GMCustomBaseAdapter) {
                                GMCustomBaseAdapter gMCustomBaseAdapter = (GMCustomBaseAdapter) objNewInstance;
                                Logger.i("TTMediationSDK_SDK_Init", "自定义Adapter 反射创建初始化对象为 " + gMCustomBaseAdapter);
                                if (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c.a(iVar.e()) != null) {
                                    ThreadHelper.runOnThreadPool(new g(gMCustomBaseAdapter, iVar, i2, i3, i4));
                                    return true;
                                }
                                Logger.e("TTMediationSDK_SDK_Init", "自定义Adapter 创建广告对象失败  ---------  没有初始化，不能加载广告，请检查初始化配置");
                                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("", this.I, this.h, iVar, new AdError(AdError.ERROR_CODE_CUSTOM_LOAD, "自定义Adapter 创建广告对象失败  ---------  没有初始化，不能加载广告，请检查初始化配置"));
                            } else {
                                String str2 = "自定义ADN 创建广告对象失败  ---------  对象类型错误  对象类型为 " + GMCustomBaseAdapter.class.getName();
                                Logger.e("TTMediationSDK_SDK_Init", str2);
                                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("", this.I, this.h, iVar, new AdError(AdError.ERROR_CODE_CUSTOM_LOAD, str2));
                            }
                            a(iVar, i2, i3);
                            return true;
                        } catch (IllegalAccessException e2) {
                            str = "自定义Adapter IllegalAccessException";
                            e2.printStackTrace();
                        } catch (InstantiationException e3) {
                            str = "自定义Adapter InstantiationException";
                            e3.printStackTrace();
                        }
                    }
                }
            } catch (ClassNotFoundException e4) {
                str = "自定义Adapter ClassNotFoundException";
                e4.printStackTrace();
            }
        }
        Logger.e("TTMediationSDK_SDK_Init", str);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a("", this.I, this.h, iVar, new AdError(AdError.ERROR_CODE_CUSTOM_LOAD, str));
        a(iVar, i2, i3);
        return false;
    }

    private boolean c(int i2) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i next;
        List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> list = this.e.get(Integer.valueOf(i2));
        if (list != null) {
            Iterator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> it = list.iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next != null && next.C() && TextUtils.equals(next.e(), "pangle")) {
                    break;
                }
            }
            next = null;
        } else {
            next = null;
        }
        return next != null && next.p() == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Handler handler;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVarA = com.bytedance.msdk.core.corelogic.f.a(this.N.getAdNetworkSlotId(), str, -4, -4);
        if (this.z != 0 && (handler = this.g) != null) {
            handler.removeMessages(3);
            this.g.sendEmptyMessageDelayed(3, this.z);
        }
        a(iVarA, -1);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f()).d().c();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0035, code lost:
    
        if ((((96 * 96) + (39 * 39)) - ((96 * 39) * 2)) >= 0) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0025, code lost:
    
        if (((((8 * 8) * 8) + ((22 * 22) * 22)) + ((5 * 5) * 5)) >= (((8 * 22) * 5) * 3)) goto L75;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0035 -> B:7:0x000c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(int r10) {
        /*
            Method dump skipped, instructions count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore.d(int):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i2) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.h, cVar != null ? cVar.v() : null, !o(), h(), i2);
    }

    private List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> f(List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> list) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> it = list.iterator();
        while (it.hasNext()) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i next = it.next();
            if (next.C()) {
                it.remove();
                arrayList.add(next);
            } else if (next.A() || next.z()) {
                it.remove();
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i();
                iVar.a(next.d());
                iVar.b(next.f());
                iVar.b(next.e());
                iVar.a(next.c());
                iVar.f(next.n());
                iVar.f(next.m());
                iVar.i(next.w());
                iVar.h(next.r());
                iVar.d("" + next.h());
                iVar.e(next.i());
                iVar.h(next.y());
                iVar.g(next.s());
                iVar.a(next.v());
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f fVarC = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().c(this.f, next.d());
                if (fVarC != null) {
                    iVar.d("" + fVarC.f1866a.getCpm());
                }
                arrayList2.add(iVar);
            }
        }
        if (k() && arrayList2.size() == 0 && (cVar = this.d) != null && cVar.u() != null) {
            for (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar2 : this.d.u()) {
                if (iVar2.z() || iVar2.A()) {
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar3 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i();
                    iVar3.a(iVar2.d());
                    iVar3.b(iVar2.f());
                    iVar3.b(iVar2.e());
                    iVar3.a(iVar2.c());
                    iVar3.f(iVar2.n());
                    iVar3.f(iVar2.m());
                    iVar3.i(iVar2.w());
                    iVar3.h(iVar2.r());
                    iVar3.d("" + iVar2.h());
                    iVar3.e(iVar2.i());
                    iVar3.h(iVar2.y());
                    iVar3.g(iVar2.s());
                    iVar3.a(iVar2.v());
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.f fVarC2 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().c(this.f, iVar2.d());
                    if (fVarC2 != null) {
                        iVar3.d("" + fVarC2.f1866a.getCpm());
                    }
                    arrayList2.add(iVar3);
                }
            }
        }
        Collections.sort(list, this.U);
        Collections.sort(arrayList2, this.V);
        list.addAll(arrayList2);
        list.addAll(arrayList);
        return list;
    }

    public String A() {
        TTBaseAd tTBaseAd = this.I;
        return tTBaseAd != null ? tTBaseAd.getAdNetworkSlotId() : "-2";
    }

    public String B() {
        return this.I == null ? "-2" : (l() || !(this.I.isServerBiddingAd() || this.I.isClientBiddingAd() || this.I.isMultiBiddingAd())) ? this.I.getNetWorkPlatFormCpm() : "-3";
    }

    public List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> C() {
        boolean z;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVarA;
        ArrayList arrayList = new ArrayList();
        List<Integer> list = this.n;
        if (list == null || list.size() <= 0) {
            z = false;
        } else {
            z = false;
            for (int i2 = 0; i2 < list.size(); i2++) {
                int iIntValue = list.get(i2).intValue();
                if (iIntValue == -100) {
                    z = true;
                }
                if (this.e.get(Integer.valueOf(iIntValue)) != null) {
                    arrayList.addAll(this.e.get(Integer.valueOf(iIntValue)));
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(this.p);
        if (!z && k() && t() && !x.b(arrayList2)) {
            TTBaseAd tTBaseAd = (TTBaseAd) arrayList2.get(0);
            if (this.d != null && ((tTBaseAd.isMultiBiddingAd() || tTBaseAd.isClientBiddingAd()) && (iVarA = this.d.a(((TTBaseAd) arrayList2.get(0)).getAdNetworkSlotId())) != null)) {
                arrayList.add(iVarA);
            }
        }
        Iterator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                it.remove();
            }
        }
        if (m() || k()) {
            return f(arrayList);
        }
        Collections.sort(arrayList, this.U);
        return arrayList;
    }

    protected abstract void D();

    protected abstract void E();

    protected void F() {
        AdSlot adSlot = this.h;
        if ((adSlot != null && adSlot.getAdType() == 7) || this.h.getAdType() == 8) {
            Logger.w("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "广告缓存成功！给外部回调：invokeAdVideoCacheOnMainUI........");
        }
        if (o()) {
            return;
        }
        V();
        ThreadHelper.runOnUiThread(new b());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000c. Please report as an issue. */
    protected void G() {
        ThreadHelper.runOnMSDKThread(new f());
        while (true) {
            char c2 = ']';
            char c3 = ']';
            while (true) {
                switch (c2) {
                    case '\\':
                        switch (c3) {
                        }
                        c2 = '^';
                        c3 = 'K';
                        break;
                    case ']':
                        while (true) {
                            switch (c3) {
                                case '[':
                                case '\\':
                                case ']':
                                    break;
                                default:
                                    c3 = '[';
                            }
                        }
                        c2 = '^';
                        c3 = 'K';
                        break;
                }
                if (c3 > 4) {
                    return;
                }
            }
        }
    }

    protected void H() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        if (cVar != null) {
            cVar.c();
            this.z = this.d.f();
            this.A = this.d.d();
            this.B = this.d.q();
            this.C = this.d.h();
            this.j = this.d.y();
            this.i = this.d.z();
            this.k = this.d.g() == 0;
            Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "mRitConfig=" + this.d.toString());
        }
    }

    protected void I() {
        this.h.setIfTest(e());
        this.h.setTransparentParams(i());
        this.h.setAdUnitId(this.f);
        this.h.setAdloadSeq(com.bytedance.msdk.core.corelogic.f.d());
        this.h.setLinkedId(com.bytedance.msdk.core.corelogic.f.c());
        this.h.getReuestParam().getExtraObject().put("tt_request_ad_type", Integer.valueOf(this.h.getAdStyleType()));
        this.h.setReqType(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122.b.a().a(this.f, e()));
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        if (cVar != null) {
            this.h.setWaterfallId(cVar.x());
            this.h.setVersion(this.d.s());
            this.h.setSegmentId(this.d.o());
            this.h.setSegmentVersion(this.d.p());
            this.h.setmWaterfallExtra(this.d.w());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:76:0x027d, code lost:
    
        if ((((10 * 10) + (40 * 40)) - ((10 * 40) * 2)) >= 0) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x02e0, code lost:
    
        if (((((65 * 65) * 65) + ((47 * 47) * 47)) + ((13 * 13) * 13)) >= (((65 * 47) * 13) * 3)) goto L130;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x02ff, code lost:
    
        if ((((76 * 76) + (25 * 25)) + (12 * 12)) < (((76 * 25) + (25 * 12)) + (76 * 12))) goto L91;
     */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0336 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x034d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x036c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[LOOP:1: B:100:0x0330->B:119:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0281  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(int r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 932
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore.a(int, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x01d4, code lost:
    
        switch(r0) {
            case 30: goto L110;
            case 31: goto L99;
            case 32: goto L98;
            default: goto L68;
        };
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01d2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01ba A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01bc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:110:? A[LOOP:1: B:62:0x01c0->B:110:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01c0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01df A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01d4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0282 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x01cf -> B:71:0x01d2). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x01d4 -> B:71:0x01d2). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void a(android.os.Message r4) {
        /*
            Method dump skipped, instructions count: 740
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore.a(android.os.Message):void");
    }

    protected void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVar, int i2, int i3) {
        if (iVar != null) {
            this.u.a(iVar.d());
            iVar.g(h());
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(iVar, this.h, i2, i3);
        }
    }

    protected abstract void a(AdError adError);

    @Override // com.bytedance.msdk.core.corelogic.d
    protected void a(AdError adError, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar) {
        if (adError == null) {
            adError = new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.getMessage(AdError.ERROR_CODE_AD_LOAD_FAIL));
        }
        X();
        if (this.f4486b.get() || this.f4485a.get()) {
            return;
        }
        this.f4486b.set(true);
        if (g() != 1 || (adError instanceof AdFreqError) || (adError instanceof AdPacingError) || (adError instanceof AdBreakError)) {
            b(adError);
        }
        if (o()) {
            return;
        }
        Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "广告加载失败！给外部回调：invokeAdLoadFailCallbackOnMainUI...... error Code = " + adError.code + " error Message = " + adError.message);
        ThreadHelper.runOnUiThread(new l(adError));
    }

    protected void a(TTBaseAd tTBaseAd) {
        AdSlot adSlot = this.h;
        int mediationRitReqType = tTBaseAd.getMediationRitReqType(adSlot != null ? adSlot.getLinkedId() : null);
        tTBaseAd.setMediationRitReqType(3);
        tTBaseAd.setMediationRitReqTypeSrc(mediationRitReqType);
    }

    protected void a(TTBaseAd tTBaseAd, String str) {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i iVarC = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_19do.a.a().c(this.f, str);
        if (tTBaseAd == null || iVarC == null) {
            return;
        }
        tTBaseAd.setLoadSort(iVarC.m());
        tTBaseAd.setShowSort(iVarC.w());
        tTBaseAd.setExchangeRate(iVarC.i());
        tTBaseAd.setAdNetworkSlotType(iVarC.f());
        tTBaseAd.setCpm(iVarC.h());
    }

    protected void a(List<TTBaseAd> list, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar) {
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0052, code lost:
    
        if (a(r3, r8, r9) != false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e4, code lost:
    
        if (r6 <= 'D') goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00e6, code lost:
    
        if (r1 != 5) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f8, code lost:
    
        if (r6 >= 3) goto L53;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00de A[LOOP:0: B:40:0x00de->B:63:0x00de, LOOP_START, PHI: r1 r6
  0x00de: PHI (r1v8 char) = (r1v6 char), (r1v9 char) binds: [B:38:0x00da, B:63:0x00de] A[DONT_GENERATE, DONT_INLINE]
  0x00de: PHI (r6v3 char) = (r6v2 char), (r6v4 char) binds: [B:38:0x00da, B:63:0x00de] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00de A[LOOP_START, PHI: r1 r6
  0x00de: PHI (r1v8 char) = (r1v6 char), (r1v9 char) binds: [B:38:0x00da, B:63:0x00de] A[DONT_GENERATE, DONT_INLINE]
  0x00de: PHI (r6v3 char) = (r6v2 char), (r6v4 char) binds: [B:38:0x00da, B:63:0x00de] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f8 A[PHI: r1 r6 r8
  0x00f8: PHI (r1v11 char) = (r1v6 char), (r1v8 char), (r1v8 char), (r1v12 char) binds: [B:38:0x00da, B:50:0x00f0, B:57:0x00f8, B:35:0x00ca] A[DONT_GENERATE, DONT_INLINE]
  0x00f8: PHI (r6v6 char) = (r6v2 char), (r6v3 char), (r6v3 char), (r6v0 char) binds: [B:38:0x00da, B:50:0x00f0, B:57:0x00f8, B:35:0x00ca] A[DONT_GENERATE, DONT_INLINE]
  0x00f8: PHI (r8v5 boolean) = (r8v4 boolean), (r8v4 boolean), (r8v4 boolean), (r8v6 boolean) binds: [B:38:0x00da, B:50:0x00f0, B:57:0x00f8, B:35:0x00ca] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00d7 -> B:38:0x00da). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00da -> B:53:0x00f8). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i r8, int r9) throws java.lang.IllegalAccessException, java.lang.InstantiationException, java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i, int):boolean");
    }

    protected void d(List<TTBaseAd> list) {
        if (x.b(list)) {
            return;
        }
        TTBaseAd tTBaseAd = list.get(0);
        if (tTBaseAd.isPAd()) {
            this.q.addAll(list);
            com.bytedance.msdk.core.corelogic.f.a(this.q, (Comparator<TTBaseAd>) null);
            if (Logger.isDebug()) {
                for (TTBaseAd tTBaseAd2 : list) {
                    Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "广告加载成功...........返回了P层广告_当前P层广告缓存池数量：" + this.q.size() + ",slotId：" + tTBaseAd2.getAdNetworkSlotId() + ",广告类型：" + tTBaseAd2.getAdNetWorkName() + ",loadSort=" + tTBaseAd2.getLoadSort() + ",showSort=" + tTBaseAd2.getShowSort() + ",CPM=" + tTBaseAd2.getCpm());
                }
                return;
            }
            return;
        }
        if (tTBaseAd.isServerBiddingAd()) {
            this.u.c();
            this.p.addAll(list);
            for (TTBaseAd tTBaseAd3 : list) {
                Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "广告加载成功...........返回了ServerBidding层广告_当前ServerBidding层广告缓存池数量：" + this.p.size() + ",slotId：" + tTBaseAd3.getAdNetworkSlotId() + ",广告类型：" + tTBaseAd3.getAdNetWorkName() + ",loadSort=" + tTBaseAd3.getLoadSort() + ",showSort=" + tTBaseAd3.getShowSort() + ",CPM=" + tTBaseAd3.getCpm());
            }
            return;
        }
        if (!tTBaseAd.isMultiBiddingAd() && !tTBaseAd.isClientBiddingAd()) {
            this.o.addAll(list);
            com.bytedance.msdk.core.corelogic.f.a(this.o, (Comparator<TTBaseAd>) null);
            if (Logger.isDebug()) {
                for (TTBaseAd tTBaseAd4 : list) {
                    Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "广告加载成功...........返回了普通层广告_当前普通广告缓存池数量：" + this.o.size() + ",slotId：" + tTBaseAd4.getAdNetworkSlotId() + ",广告类型：" + tTBaseAd4.getAdNetWorkName() + ",loadSort=" + tTBaseAd4.getLoadSort() + ",showSort=" + tTBaseAd4.getShowSort() + ",CPM=" + tTBaseAd4.getCpm());
                }
                return;
            }
            return;
        }
        this.p.addAll(list);
        String str = tTBaseAd.isMultiBiddingAd() ? "多阶底价" : "clientBidding";
        for (TTBaseAd tTBaseAd5 : list) {
            Logger.d("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "广告加载成功...........返回了" + str + "层广告_当前" + str + "层广告缓存池数量：" + this.p.size() + ",slotId：" + tTBaseAd5.getAdNetworkSlotId() + ",广告类型：" + tTBaseAd5.getAdNetWorkName() + ",loadSort=" + tTBaseAd5.getLoadSort() + ",showSort=" + tTBaseAd5.getShowSort() + ",CPM=" + tTBaseAd5.getCpm());
        }
    }

    public HashMap<String, TTBaseAd> e(List<TTBaseAd> list) {
        HashMap<String, TTBaseAd> map = new HashMap<>();
        if (list != null && list.size() > 0) {
            for (TTBaseAd tTBaseAd : list) {
                if (tTBaseAd != null) {
                    map.put(tTBaseAd.getAdNetworkSlotId(), tTBaseAd);
                }
            }
        }
        return map;
    }

    @Keep
    public List<AdLoadInfo> getAdLoadInfoList() {
        AdLoadInfo value;
        ArrayList arrayList = new ArrayList(this.O.size());
        for (Map.Entry<String, AdLoadInfo> entry : this.O.entrySet()) {
            if (entry != null && (value = entry.getValue()) != null && !TextUtils.equals(AdLoadInfo.AD_LOADED, value.getErrMsg()) && !TextUtils.equals(AdLoadInfo.AD_LOADING, value.getErrMsg())) {
                arrayList.add(value);
            }
        }
        return arrayList;
    }

    @Override // com.bytedance.msdk.core.corelogic.d
    protected void n() {
        X();
        if (this.f4485a.get() || this.f4486b.get()) {
            return;
        }
        Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "广告加载成功！给外部回调：invokeAdLoadCallbackOnMainUI........P.size:" + this.q.size() + "   bidding.size:" + this.p.size() + "   normal.size:" + this.o.size());
        this.f4485a.set(true);
        v();
        if ((Q() || (M() && !this.T)) && m()) {
            List<TTBaseAd> listP = p();
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.b(listP.size() > 0 ? listP.get(0) : null, this.h);
        }
        if (o()) {
            return;
        }
        AdSlot adSlot = this.h;
        TTBaseAd tTBaseAd = this.I;
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.P;
        int iJ = J();
        int iB = b(this.K);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(adSlot, tTBaseAd, jElapsedRealtime, iJ, iB, 0, cVar != null ? cVar.v() : null);
        ThreadHelper.runOnUiThread(new k());
        W();
    }

    public void onAdFailed(AdError adError, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar) {
        List<TTBaseAd> list;
        List<TTBaseAd> list2;
        AdSlot adSlot;
        if (this.g == null || dVar == null) {
            return;
        }
        if (dVar != null && adError != null) {
            a(dVar.d(), dVar.c(), com.bytedance.msdk.base.a.a(dVar.b(), dVar.g()), adError.thirdSdkErrorCode, adError.thirdSdkErrorMessage);
            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "广告加载失败...........adnName:" + dVar.c() + " ,slotId:" + dVar.d() + ",slotType:" + dVar.a() + ",loadSort:" + dVar.e() + ",showSort:" + dVar.f() + " ，adError:" + adError.toString());
        }
        com.bytedance.msdk.core.corelogic.f.a(adError, dVar);
        if (dVar != null && dVar.a() == 0 && a(dVar.d())) {
            return;
        }
        if (dVar.k()) {
            this.u.c();
        }
        this.u.a(dVar.d());
        this.u.c(dVar.e());
        if (this.f4485a.get() || this.f4486b.get()) {
            return;
        }
        List<TTBaseAd> list3 = this.q;
        if ((list3 == null || list3.size() <= 0) && (((list = this.o) == null || list.size() <= 0) && ((list2 = this.p) == null || list2.size() <= 0))) {
            if (!this.u.d() && this.u.h() && (adSlot = this.h) != null && !TextUtils.isEmpty(adSlot.getTestSlotId()) && e() == 2) {
                a(adError, dVar);
                return;
            }
        } else if (!this.u.d() && this.u.h()) {
            Logger.i("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "所有层级和所有waterfall都已完成直接回调...");
            n();
            return;
        }
        if (this.u.d(dVar.e()) == 0) {
            int iB = b(dVar.e());
            Logger.w("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "onAdFailed--》 加载下一层-nextIdx=" + iB);
            if (dVar.j()) {
                Handler handler = this.g;
                if (handler != null) {
                    handler.removeMessages(4);
                }
            } else {
                Handler handler2 = this.g;
                if (handler2 != null) {
                    handler2.removeMessages(1);
                }
            }
            a(iB, true);
        }
        if (this.u.d() || this.u.h()) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "所有广告都加载失败....");
            a(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.getMessage(AdError.ERROR_CODE_AD_LOAD_FAIL)), dVar);
        }
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.AdapterLoaderListener
    public void onAdLoaded(TTBaseAd tTBaseAd, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(tTBaseAd);
        onAdLoaded(arrayList, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x0234, code lost:
    
        r3 = 15;
     */
    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.AdapterLoaderListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onAdLoaded(java.util.List<com.bytedance.msdk.base.TTBaseAd> r8, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d r9) {
        /*
            Method dump skipped, instructions count: 590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.TTAdHeaderBidingRequestCore.onAdLoaded(java.util.List, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d):void");
    }

    @Override // com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter.AdapterLoaderListener
    public void onAdVideoCache() {
        if (this.g == null || this.f4486b.get() || this.f4487c.get() || !this.f4485a.get()) {
            return;
        }
        this.f4487c.set(true);
        F();
    }

    public void x() {
        ThreadHelper.runOnMSDKThread(new j());
    }

    public ConcurrentHashMap<String, AdLoadInfo> y() {
        return this.O;
    }

    public int z() {
        TTBaseAd tTBaseAd = this.I;
        if (tTBaseAd != null) {
            return tTBaseAd.getAdNetworkPlatformId();
        }
        return -2;
    }
}
