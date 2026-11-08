package com.bytedance.msdk.core.corelogic;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.d;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.h;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.x;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.y;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import com.bytedance.msdk.api.v2.GMAdConstant;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class d {
    protected bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c d;
    protected Map<Integer, List<i>> e;
    protected String f;
    protected Handler g;
    protected AdSlot h;
    protected boolean i;
    protected boolean j;
    protected boolean k;
    protected List<Integer> n;
    protected int v;
    protected GMAdSlotBase y;

    /* renamed from: a, reason: collision with root package name */
    protected final AtomicBoolean f4485a = new AtomicBoolean(false);

    /* renamed from: b, reason: collision with root package name */
    protected final AtomicBoolean f4486b = new AtomicBoolean(false);

    /* renamed from: c, reason: collision with root package name */
    protected final AtomicBoolean f4487c = new AtomicBoolean(false);
    private int l = -1;
    protected final List<i> m = new CopyOnWriteArrayList();
    protected List<TTBaseAd> o = new CopyOnWriteArrayList();
    protected List<TTBaseAd> p = new CopyOnWriteArrayList();
    protected List<TTBaseAd> q = new CopyOnWriteArrayList();
    protected List<TTBaseAd> r = new CopyOnWriteArrayList();
    protected List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.e> s = new CopyOnWriteArrayList();
    protected Map<String, Object> t = new HashMap();
    protected g u = new g();
    protected AtomicBoolean w = new AtomicBoolean(false);
    protected h x = new h();

    class a implements d.a {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AdSlot f4488a;

        a(AdSlot adSlot) {
            this.f4488a = adSlot;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.d.a
        public void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a aVar) {
            List<TTBaseAd> list;
            List<TTBaseAd> list2;
            if (d.this.x != null && aVar != null && !TextUtils.isEmpty(aVar.f1871c)) {
                d.this.x.f1928a = aVar.f1871c;
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = d.this.d;
            String strV = cVar != null ? cVar.v() : null;
            if (aVar != null) {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(this.f4488a, aVar.f, aVar.g, strV, aVar.f1871c, d.this.u.d() ? 1 : 0, aVar.h);
            }
            if (aVar == null || (x.a(aVar.f1869a) && TextUtils.isEmpty(aVar.d))) {
                d.this.l = 2;
                if (d.this.f4485a.get() || d.this.f4486b.get()) {
                    return;
                }
                Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(d.this.f) + "serverBidding响应失败了.......... ");
                d.this.u.a((String) null);
                d.this.u.a(0, true);
                d.this.a();
            } else {
                d.this.l = 1;
                if (d.this.f4485a.get() || d.this.f4486b.get()) {
                    return;
                }
                if (aVar.e != null) {
                    d.this.s.clear();
                    d.this.s.addAll(aVar.e);
                    d dVar = d.this;
                    dVar.d(dVar.s);
                }
                Handler handler = d.this.g;
                if (handler != null) {
                    handler.removeMessages(4);
                    d.this.g.removeMessages(1);
                    d.this.g.removeMessages(3);
                }
                d.this.m.clear();
                List<i> list3 = aVar.f1869a;
                if (list3 != null) {
                    d.this.m.addAll(list3);
                }
                d.this.c(aVar.d);
                d dVar2 = d.this;
                dVar2.c(dVar2.m);
                if (x.a(d.this.m)) {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(d.this.f) + "serverBidding响应回来..........没有P层，且普通广告全都没有胜出.....直接返回");
                    List<TTBaseAd> list4 = d.this.q;
                    if ((list4 == null || list4.size() <= 0) && (((list = d.this.o) == null || list.size() <= 0) && ((list2 = d.this.p) == null || list2.size() <= 0))) {
                        d.this.a(new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.getMessage(AdError.ERROR_CODE_AD_LOAD_FAIL)), (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
                    } else {
                        d.this.n();
                    }
                } else {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(d.this.f) + "serverBidding响应回来..........开始从头开始请求waterFallConfig ");
                    d.this.a(0, false);
                }
            }
            if (aVar == null || !aVar.f1870b) {
                return;
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f()).d().c();
        }
    }

    private void a(TTBaseAd tTBaseAd) {
        AdSlot adSlot;
        if (tTBaseAd == null || (adSlot = this.h) == null || !adSlot.isBidNotify()) {
            return;
        }
        tTBaseAd.bidWinNotify(null);
    }

    private GMAdEcpmInfo b(TTBaseAd tTBaseAd) {
        if (tTBaseAd == null) {
            return null;
        }
        GMAdEcpmInfo gMAdEcpmInfo = new GMAdEcpmInfo();
        gMAdEcpmInfo.setAdNetworkPlatformId(tTBaseAd.getAdNetworkPlatformId());
        gMAdEcpmInfo.setAdNetworkRitId(tTBaseAd.getAdNetworkSlotId());
        gMAdEcpmInfo.setLevelTag(tTBaseAd.getLevelTag());
        gMAdEcpmInfo.setPreEcpm((bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().q() || !tTBaseAd.isServerBiddingAd()) ? tTBaseAd.getMultiCpm() : "-3");
        gMAdEcpmInfo.setReqBiddingType(tTBaseAd.getAdNetworkSlotType());
        gMAdEcpmInfo.setErrorMsg(tTBaseAd.getErrorMsg());
        return gMAdEcpmInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.e> list) {
        if (x.a(this.r) || x.a(list)) {
            return;
        }
        for (TTBaseAd tTBaseAd : this.r) {
            Iterator<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.e> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.e next = it.next();
                    if (tTBaseAd != null && next != null && !TextUtils.isEmpty(tTBaseAd.getAdNetworkSlotId()) && tTBaseAd.getAdNetworkSlotId().equals(next.b())) {
                        tTBaseAd.setErrorMsg(next.a());
                        break;
                    }
                }
            }
        }
    }

    private List<i> x() {
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar = this.d;
        if (cVar == null || cVar.u() == null || this.d.u().size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (i iVar : this.d.u()) {
            if (iVar.f() == 100) {
                arrayList.add(iVar);
            }
        }
        return arrayList;
    }

    protected void a() {
        List<TTBaseAd> list;
        List<TTBaseAd> list2;
        AdError adError;
        List<TTBaseAd> list3;
        List<TTBaseAd> list4;
        List<TTBaseAd> list5;
        List<TTBaseAd> list6;
        if (this.d == null || x.a(this.p)) {
            if (this.u.h()) {
                Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "所有广告都已经加载完成....... ");
                List<TTBaseAd> list7 = this.q;
                if ((list7 == null || list7.size() <= 0) && (((list = this.o) == null || list.size() <= 0) && ((list2 = this.p) == null || list2.size() <= 0))) {
                    adError = new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.getMessage(AdError.ERROR_CODE_AD_LOAD_FAIL));
                    a(adError, (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
                    return;
                }
                n();
            }
            return;
        }
        Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "存在多阶底价clientBidding广告，走客户端兜底比价逻辑....... ");
        for (TTBaseAd tTBaseAd : this.p) {
            if (tTBaseAd != null) {
                y.a(tTBaseAd, this.d.a(tTBaseAd.getAdNetworkSlotId()), this.h, true);
            }
        }
        w();
        List<i> listA = this.d.a(this.p.get(0).getCpm());
        this.m.clear();
        if (listA != null) {
            this.m.addAll(listA);
        }
        c(this.m);
        if (x.a(this.m)) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "没有P层，且普通广告全都没有胜出.....直接返回.......");
            List<TTBaseAd> list8 = this.q;
            if ((list8 == null || list8.size() <= 0) && (((list5 = this.o) == null || list5.size() <= 0) && ((list6 = this.p) == null || list6.size() <= 0))) {
                adError = new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.getMessage(AdError.ERROR_CODE_AD_LOAD_FAIL));
                a(adError, (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
                return;
            }
            n();
        }
        if (!this.u.h()) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "本地过滤完成..........开始从头开始请求waterFallConfig ");
            Handler handler = this.g;
            if (handler != null) {
                handler.removeMessages(4);
                this.g.removeMessages(1);
                this.g.removeMessages(3);
            }
            a(0, false);
            return;
        }
        Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f) + "所有广告都已经加载完成....... ");
        List<TTBaseAd> list9 = this.q;
        if ((list9 == null || list9.size() <= 0) && (((list3 = this.o) == null || list3.size() <= 0) && ((list4 = this.p) == null || list4.size() <= 0))) {
            adError = new AdError(AdError.ERROR_CODE_AD_LOAD_FAIL, AdError.getMessage(AdError.ERROR_CODE_AD_LOAD_FAIL));
            a(adError, (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d) null);
            return;
        }
        n();
    }

    protected void a(int i) {
        h hVar = this.x;
        if (hVar != null) {
            hVar.f1929b = i;
        }
    }

    abstract void a(int i, boolean z);

    abstract void a(AdError adError, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar);

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: Infinite loop detected, blocks: 3, insns: 0 */
    /* JADX WARN: Failed to find 'out' block for switch in B:19:0x0049. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:38:0x0086. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b6 A[LOOP:2: B:38:0x0086->B:45:0x00b6, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x008a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0042 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0073 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x006d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x008a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00a6 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0086 -> B:30:0x006d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0086 -> B:40:0x008a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00b3 -> B:30:0x006d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00b3 -> B:40:0x008a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void a(com.bytedance.msdk.api.AdSlot r8, java.util.List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> r9) {
        /*
            r7 = this;
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.d r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.e.a()
            r1 = 96
            r2 = 81
            r3 = 3
            r4 = 1
            if (r0 == 0) goto L65
            r5 = 0
            r7.l = r5
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.h r5 = r7.x
            if (r5 == 0) goto L15
            r5.d = r4
        L15:
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.b r5 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.b
            r5.<init>()
            r5.f1872a = r8
            r5.f1873b = r9
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c r9 = r7.d
            r5.d = r9
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.List<com.bytedance.msdk.base.TTBaseAd> r6 = r7.r
            if (r6 == 0) goto L2e
            r9.addAll(r6)
        L2e:
            r5.f1874c = r9
            if (r8 != 0) goto L34
            r9 = 1
            goto L38
        L34:
            int r9 = r8.getPrimeRitReqType()
        L38:
            r5.e = r9
            com.bytedance.msdk.core.corelogic.d$a r9 = new com.bytedance.msdk.core.corelogic.d$a
            r9.<init>(r8)
            r0.a(r5, r9)
        L42:
            r8 = 57
            switch(r1) {
                case 95: goto L6d;
                case 96: goto L67;
                case 97: goto L48;
                default: goto L47;
            }
        L47:
            goto L42
        L48:
            r9 = -1
            switch(r8) {
                case 82: goto L4d;
                case 83: goto L4d;
                case 84: goto L57;
                default: goto L4c;
            }
        L4c:
            goto L84
        L4d:
            int r8 = r3 * r3
            r0 = 5
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r8 = r8 - r0
            if (r8 != r9) goto L42
        L57:
            r8 = 71
            int r8 = r8 * r8
            r0 = 25
            int r0 = r0 * 25
            int r0 = r0 * 34
            int r8 = r8 - r0
            if (r8 != r9) goto L42
            goto L8a
        L65:
            r8 = 58
        L67:
            switch(r8) {
                case 57: goto L6d;
                case 58: goto L6d;
                case 59: goto L6b;
                default: goto L6a;
            }
        L6a:
            goto L65
        L6b:
            goto L6b
        L6d:
            r8 = 40
            switch(r8) {
                case 39: goto L84;
                case 40: goto L83;
                case 41: goto L73;
                default: goto L72;
            }
        L72:
            goto L6d
        L73:
            r8 = 29
            int r9 = 29 - r4
            int r9 = r9 * 29
            int r8 = r8 * 2
            int r8 = r8 - r4
            int r9 = r9 * r8
            int r9 = r9 % 6
            if (r9 == 0) goto L83
            goto L6d
        L83:
            return
        L84:
            r8 = 81
        L86:
            switch(r8) {
                case 94: goto L8a;
                case 95: goto La6;
                case 96: goto L6d;
                default: goto L89;
            }
        L89:
            goto Lb6
        L8a:
            r8 = 9
            int r9 = r8 * r8
            int r9 = r9 * 9
            r0 = 28
            int r5 = r0 * r0
            int r5 = r5 * 28
            int r9 = r9 + r5
            r5 = 19
            int r6 = r5 * r5
            int r6 = r6 * 19
            int r9 = r9 + r6
            int r8 = r8 * 28
            int r8 = r8 * 19
            int r8 = r8 * 3
            if (r9 >= r8) goto L6d
        La6:
            r8 = 53
            int r9 = 53 - r4
            int r9 = r9 * 53
            int r8 = r8 * 2
            int r8 = r8 - r4
            int r9 = r9 * r8
            int r9 = r9 % 6
            if (r9 == 0) goto L8a
            goto L6d
        Lb6:
            r8 = 96
            goto L86
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.d.a(com.bytedance.msdk.api.AdSlot, java.util.List):void");
    }

    protected void a(TTBaseAd tTBaseAd, GMAdConstant.BiddingLossReason biddingLossReason) {
        AdSlot adSlot;
        if (tTBaseAd == null || (adSlot = this.h) == null || biddingLossReason == null || !adSlot.isBidNotify()) {
            return;
        }
        HashMap map = new HashMap();
        map.put("bidding_lose_reason", biddingLossReason);
        tTBaseAd.bidLoseNotify(map);
    }

    public void a(List<TTBaseAd> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a((TTBaseAd) it.next());
        }
        if (this.p.size() == 0) {
            return;
        }
        ArrayList<TTBaseAd> arrayList2 = new ArrayList();
        arrayList2.addAll(this.p);
        for (TTBaseAd tTBaseAd : arrayList2) {
            boolean z = true;
            Iterator<TTBaseAd> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                TTBaseAd next = it2.next();
                if (next != null && tTBaseAd != null && !TextUtils.isEmpty(next.getAdNetworkSlotId()) && next.getAdNetworkSlotId().equals(tTBaseAd.getAdNetworkSlotId())) {
                    z = false;
                    break;
                }
            }
            if (z) {
                a(tTBaseAd, GMAdConstant.BiddingLossReason.LOW_PRICE);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0035, code lost:
    
        if (((((13 * 13) * 13) + ((9 * 9) * 9)) + ((8 * 8) * 8)) >= (((13 * 9) * 8) * 3)) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:?, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.lang.String r6) {
        /*
            r5 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 0
            if (r0 == 0) goto L60
        L7:
            r6 = 101(0x65, float:1.42E-43)
            switch(r6) {
                case 99: goto Ld;
                case 100: goto L1b;
                case 101: goto L5f;
                default: goto Lc;
            }
        Lc:
            goto L7
        Ld:
            r6 = 61
            int r6 = r6 * r6
            r0 = 21
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r6 = r6 - r0
            r0 = -1
            if (r6 != r0) goto L38
        L1b:
            r6 = 13
            int r0 = r6 * r6
            int r0 = r0 * 13
            r2 = 9
            int r3 = r2 * r2
            int r3 = r3 * 9
            int r0 = r0 + r3
            r3 = 8
            int r4 = r3 * r3
            int r4 = r4 * 8
            int r0 = r0 + r4
            int r6 = r6 * 9
            int r6 = r6 * 8
            int r6 = r6 * 3
            if (r0 >= r6) goto L8b
            goto L5f
        L38:
            r6 = 84
            int r6 = r6 * r6
            r2 = 26
            int r2 = r2 * r2
            int r2 = r2 * 34
            int r6 = r6 - r2
            if (r6 != r0) goto L8b
            r6 = 88
            int r0 = r6 * r6
            int r0 = r0 * 88
            r2 = 5
            int r3 = r2 * r2
            int r3 = r3 * 5
            int r0 = r0 + r3
            r3 = 2
            int r4 = r3 * r3
            int r4 = r4 * 2
            int r0 = r0 + r4
            int r6 = r6 * 5
            int r6 = r6 * 2
            int r6 = r6 * 3
            if (r0 >= r6) goto L8b
        L5f:
            return r1
        L60:
            boolean r0 = r5.m()
            if (r0 == 0) goto L8b
            boolean r0 = r5.s()
            if (r0 == 0) goto L8b
            r0 = 1
            java.util.List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> r2 = r5.m
            java.util.Iterator r2 = r2.iterator()
        L73:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L8a
            java.lang.Object r3 = r2.next()
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i r3 = (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i) r3
            java.lang.String r3 = r3.d()
            boolean r3 = r6.equals(r3)
            if (r3 == 0) goto L73
            goto L8b
        L8a:
            r1 = 1
        L8b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.d.a(java.lang.String):boolean");
    }

    public GMAdEcpmInfo b() {
        List<TTBaseAd> listP;
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().o() || (listP = p()) == null || listP.size() == 0) {
            return null;
        }
        return b(listP.get(0));
    }

    protected void b(String str) {
        h hVar = this.x;
        if (hVar != null) {
            hVar.f1930c = str;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d7 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0102 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x011d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0127 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ec A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ed A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00fa -> B:35:0x00fc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void b(java.util.List<bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i> r8) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.msdk.core.corelogic.d.b(java.util.List):void");
    }

    public List<GMAdEcpmInfo> c() {
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().o()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (TTBaseAd tTBaseAd : p()) {
            if (tTBaseAd != null) {
                arrayList.add(b(tTBaseAd));
            }
        }
        return arrayList;
    }

    protected void c(String str) {
        List<TTBaseAd> list = this.p;
        if (list == null || list.size() == 0) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.p.clear();
            return;
        }
        for (TTBaseAd tTBaseAd : this.p) {
            if (tTBaseAd != null && !str.equals(tTBaseAd.getAdNetworkSlotId())) {
                this.p.remove(tTBaseAd);
            }
        }
    }

    protected void c(List<i> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        List<i> listX = x();
        if (!x.a(listX)) {
            list.addAll(listX);
        }
        Map<Integer, List<i>> mapA = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.c.a(list);
        if (mapA == null) {
            mapA = new HashMap<>();
        }
        this.e = mapA;
        this.n = new ArrayList();
        this.n.addAll(this.e.keySet());
        c.a(this.n);
        this.u.b(this.n);
        this.u.c(list);
        b(list);
        while (true) {
            switch (95) {
                case 94:
                    int i = ((17 + 1) * 17) % 2;
                    return;
                case 95:
                    return;
                case 96:
                    if (((45 + 1) * 45) % 2 != 0) {
                        break;
                    } else {
                        return;
                    }
            }
        }
    }

    protected int d() {
        List<Integer> list = this.n;
        if (list != null && list.size() != 0) {
            for (int i = 0; i < this.n.size(); i++) {
                if (this.n.get(i).intValue() == -100) {
                    return i;
                }
            }
        }
        return -1;
    }

    protected int e() {
        h hVar = this.x;
        if (hVar != null) {
            return hVar.f1929b;
        }
        return 0;
    }

    public List<GMAdEcpmInfo> f() {
        TTBaseAd tTBaseAd = null;
        if (!bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().o()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (TTBaseAd tTBaseAd2 : this.r) {
            if (tTBaseAd2 != null) {
                arrayList.add(b(tTBaseAd2));
            }
        }
        Iterator<TTBaseAd> it = this.p.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TTBaseAd next = it.next();
            if (next != null && next.isServerBiddingAd()) {
                tTBaseAd = next;
                break;
            }
        }
        if (tTBaseAd != null) {
            arrayList.add(b(tTBaseAd));
        }
        return arrayList;
    }

    public int g() {
        return -1;
    }

    protected String h() {
        h hVar = this.x;
        if (hVar == null) {
            return null;
        }
        return hVar.f1928a;
    }

    protected String i() {
        h hVar = this.x;
        if (hVar != null) {
            return hVar.f1930c;
        }
        return null;
    }

    public String j() {
        return this.f;
    }

    protected boolean k() {
        return this.j;
    }

    public boolean l() {
        return bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().p();
    }

    protected boolean m() {
        return this.i;
    }

    abstract void n();

    protected boolean o() {
        return false;
    }

    protected List<TTBaseAd> p() {
        ArrayList arrayList = new ArrayList();
        if (!x.a(this.q)) {
            arrayList.addAll(this.q);
        }
        if (!x.a(this.o)) {
            arrayList.addAll(this.o);
        }
        if (!x.a(this.p)) {
            arrayList.addAll(this.p);
        }
        return arrayList;
    }

    protected boolean q() {
        return this.k;
    }

    protected void r() {
        if (this.u.a()) {
            return;
        }
        Message message = new Message();
        message.what = 1;
        message.arg1 = 10001;
        Handler handler = this.g;
        if (handler != null) {
            handler.removeMessages(1);
            this.g.sendMessage(message);
        }
    }

    protected boolean s() {
        return this.l == 1;
    }

    protected boolean t() {
        return this.l > 0;
    }

    protected boolean u() {
        return this.l > -1;
    }

    protected void v() {
        h hVar;
        if (!m() || (hVar = this.x) == null || TextUtils.isEmpty(hVar.f1928a)) {
            return;
        }
        Iterator<TTBaseAd> it = this.q.iterator();
        while (it.hasNext()) {
            it.next().putEventParam("server_bidding_extra", this.x.f1928a);
        }
        Iterator<TTBaseAd> it2 = this.p.iterator();
        while (it2.hasNext()) {
            it2.next().putEventParam("server_bidding_extra", this.x.f1928a);
        }
        Iterator<TTBaseAd> it3 = this.o.iterator();
        while (it3.hasNext()) {
            it3.next().putEventParam("server_bidding_extra", this.x.f1928a);
        }
    }

    protected double w() {
        if (x.a(this.p)) {
            return 0.0d;
        }
        f.a(this.p, f.a());
        if (this.p.size() <= 0) {
            return 0.0d;
        }
        TTBaseAd tTBaseAd = this.p.get(0);
        this.p.clear();
        this.p.add(tTBaseAd);
        return tTBaseAd.getCpm();
    }
}
