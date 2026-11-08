package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.d;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.x;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f implements d {
    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.d
    public void a(b bVar, @NonNull d.a aVar) throws JSONException {
        if (bVar == null || bVar.f1872a == null || bVar.d == null || x.a(bVar.f1873b)) {
            aVar.a(null);
            return;
        }
        String strV = bVar.d.v();
        Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(bVar.f1872a.getAdUnitId()) + "开启server bidding网络请求......：");
        g.b(bVar.f1872a, strV);
        bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.d().a(bVar.f1872a, bVar.f1873b, bVar.f1874c, bVar.d, bVar.e, new a(bVar, System.currentTimeMillis(), new int[]{4}, aVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<i> a(List<i> list, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c cVar) {
        ArrayList arrayList = null;
        if (list != null && list.size() != 0 && cVar != null && cVar.u() != null && cVar.u().size() != 0) {
            arrayList = new ArrayList();
            for (i iVar : cVar.u()) {
                Iterator<i> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        i next = it.next();
                        if (iVar != null && next != null && iVar.f() == 0 && !TextUtils.isEmpty(iVar.d()) && iVar.d().equals(next.d())) {
                            arrayList.add(iVar);
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    class a implements a.d {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ b f1875a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f1876b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int[] f1877c;
        final /* synthetic */ d.a d;

        a(b bVar, long j, int[] iArr, d.a aVar) {
            this.f1875a = bVar;
            this.f1876b = j;
            this.f1877c = iArr;
            this.d = aVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.d
        public void a(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.f fVar) {
            String strF;
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a aVar;
            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding网络请求响返回.............");
            long jCurrentTimeMillis = System.currentTimeMillis() - this.f1876b;
            b bVar = this.f1875a;
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a aVar2 = null;
            iVarA = null;
            i iVarA = null;
            if (bVar == null || bVar.d == null || fVar == null || fVar.d() == null) {
                this.f1877c[0] = 4;
                Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding网络请求响应失败......没有返回serverBiddingModel相关数据");
            } else {
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.g gVarD = fVar.d();
                AdSlot adSlot = this.f1875a.f1872a;
                if (adSlot != null) {
                    adSlot.setWinCallback(gVarD.g());
                    this.f1875a.f1872a.setFailCallback(gVarD.c());
                }
                if (gVarD.a() == 2) {
                    aVar = null;
                    iVarA = this.f1875a.d.a(gVarD.f());
                    strF = null;
                } else if (TextUtils.isEmpty(gVarD.f())) {
                    strF = null;
                    aVar = null;
                } else {
                    strF = gVarD.f();
                    aVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a();
                    aVar.d = strF;
                }
                if (iVarA == null && TextUtils.isEmpty(strF)) {
                    this.f1877c[0] = 4;
                    Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding网络请求响应失败......：数据有返回，但没有返回winner数据......");
                    aVar2 = aVar;
                } else {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding网络请求响应成功......：");
                    CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                    if (iVarA != null && iVarA.f() == 2) {
                        iVarA.a(gVarD);
                        copyOnWriteArrayList.add(iVarA);
                    }
                    List listA = f.this.a(fVar.c(), this.f1875a.d);
                    if (listA != null && listA.size() > 0) {
                        if (iVarA != null) {
                            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding网络请求响应成功......waterfall+server bidding物料  sbSlotId：" + iVarA.d());
                            this.f1877c[0] = 1;
                        } else {
                            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding网络请求响应成功......waterfall+client bidding或多阶底价广告 clientMultiSlotId：" + strF);
                            this.f1877c[0] = 6;
                        }
                        copyOnWriteArrayList.addAll(listA);
                    } else if (iVarA != null) {
                        Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding网络请求响应成功......server bidding物料  sbSlotId：" + iVarA.d());
                        this.f1877c[0] = 3;
                    } else {
                        Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding网络请求响应成功......client bidding或多阶底价广告 clientMultiSlotId：" + strF);
                        this.f1877c[0] = 7;
                    }
                    aVar2 = aVar == null ? new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a() : aVar;
                    aVar2.f1869a = copyOnWriteArrayList;
                }
            }
            if (fVar != null) {
                String strB = fVar.b();
                if (!TextUtils.isEmpty(strB)) {
                    if (aVar2 == null) {
                        aVar2 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a();
                    }
                    aVar2.f1871c = strB;
                }
                if (fVar.e()) {
                    Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding发现config过期了，需要重新拉取配置......：");
                    if (aVar2 == null) {
                        aVar2 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a();
                    }
                    aVar2.f1870b = true;
                }
                if (!x.a(fVar.a())) {
                    if (aVar2 == null) {
                        aVar2 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a();
                    }
                    aVar2.e = fVar.a();
                }
            }
            if (aVar2 == null) {
                aVar2 = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a();
            }
            aVar2.f = this.f1877c[0];
            aVar2.g = jCurrentTimeMillis;
            d.a aVar3 = this.d;
            if (aVar3 != null) {
                aVar3.a(aVar2);
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_char12.a.d
        public void a(AdError adError) {
            Logger.e("TTMediationSDK", TTLogUtil.getTagSecondLevel(this.f1875a.f1872a.getAdUnitId()) + "server bidding网络请求响应失败......onFail");
            this.f1877c[0] = 5;
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a aVar = new bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_if122.a();
            aVar.f = this.f1877c[0];
            aVar.g = System.currentTimeMillis() - this.f1876b;
            aVar.h = adError;
            d.a aVar2 = this.d;
            if (aVar2 != null) {
                aVar2.a(aVar);
            }
        }
    }
}
