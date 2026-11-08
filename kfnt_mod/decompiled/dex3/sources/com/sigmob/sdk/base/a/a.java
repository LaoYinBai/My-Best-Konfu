package com.sigmob.sdk.base.a;

import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.a.b;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.LoadAdRequest;
import com.sigmob.sdk.common.e.k;
import com.sigmob.sdk.common.models.ModelBuilderCreator;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.models.sigdsp.pb.Ad;
import com.sigmob.sdk.common.models.sigdsp.pb.AdCache;
import com.sigmob.sdk.common.models.sigdsp.pb.AdSlot;
import com.sigmob.sdk.common.models.sigdsp.pb.App;
import com.sigmob.sdk.common.models.sigdsp.pb.BidRequest;
import com.sigmob.sdk.common.models.sigdsp.pb.BidResponse;
import com.sigmob.sdk.common.models.sigdsp.pb.Device;
import com.sigmob.sdk.common.models.sigdsp.pb.HeaderBidding;
import com.sigmob.sdk.common.models.sigdsp.pb.Network;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.volley.i;
import com.sigmob.volley.n;
import com.sigmob.volley.p;
import com.sigmob.volley.s;
import com.sigmob.volley.toolbox.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a extends k<BidResponse> {
    private final b.a b;
    private final int c;
    private final String d;
    private final LoadAdRequest e;
    private final Map<String, AdCache> f;
    private Network.Builder g;
    private AdSlot.Builder h;
    private Device.Builder i;
    private App.Builder j;

    public a(String str, Map<String, AdCache> map, LoadAdRequest loadAdRequest, b.a aVar) {
        super(str, 1, null);
        m.a.a(aVar);
        this.d = loadAdRequest.getPlacementId();
        this.b = aVar;
        this.c = loadAdRequest.getAdType();
        this.f = map;
        this.e = loadAdRequest;
        a((p) new com.sigmob.volley.c(10000, 2, 0.0f));
        a(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e() {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.sdk.base.a.a.e():void");
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    protected n<BidResponse> a(i iVar) {
        try {
            SigmobLog.d("ads Response start" + System.currentTimeMillis());
            BidResponse bidResponseDecode = BidResponse.ADAPTER.decode(iVar.b);
            return bidResponseDecode != null ? n.a(bidResponseDecode, h.a(iVar)) : n.a(new com.sigmob.volley.k(iVar));
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return n.a(new com.sigmob.volley.k(th));
        }
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    public String a() {
        return "application/octet-stream";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sigmob.volley.l
    public void a(BidResponse bidResponse) {
        if (bidResponse != null) {
            SigmobLog.d("ads Response: " + System.currentTimeMillis() + " [" + bidResponse + "]");
            this.e.setRequestId(bidResponse.request_id);
            if (bidResponse.ads.size() <= 0) {
                this.b.a(bidResponse.error_code.intValue(), null, bidResponse.request_id, this.e);
                return;
            }
            try {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < bidResponse.ads.size(); i++) {
                    Ad ad = bidResponse.ads.get(i);
                    if (ad != null && ad.ad_type.intValue() == this.e.getAdType() && !TextUtils.isEmpty(ad.crid) && ad.materials.size() != 0) {
                        arrayList.add(BaseAdUnit.adUnit(ad, bidResponse.request_id, this.e.getLoadId(), bidResponse.slot_ad_setting));
                    }
                }
                this.b.a(arrayList, this.e);
                return;
            } catch (Throwable th) {
                SigmobLog.e("ads Response: error ", th);
            }
        }
        this.b.a(SigmobError.ERROR_SIGMOB_INFORMATION_LOSE.getErrorCode(), "bidResponse is null", null, this.e);
    }

    @Override // com.sigmob.volley.l
    public void a(s sVar) {
        b.a aVar;
        SigmobError sigmobError;
        if (sVar instanceof com.sigmob.volley.k) {
            aVar = this.b;
            sigmobError = SigmobError.ERROR_SIGMOB_INFORMATION_LOSE;
        } else {
            SigmobLog.e(this.d + " ERROR_SIGMOB_NETWORK " + sVar.getMessage());
            aVar = this.b;
            sigmobError = SigmobError.ERROR_SIGMOB_NETWORK;
        }
        aVar.a(sigmobError.getErrorCode(), sVar.getMessage(), null, this.e);
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    public byte[] b() {
        BidRequest bidRequestBuild;
        e();
        try {
            BidRequest.Builder builderCreateBidRequest = ModelBuilderCreator.createBidRequest();
            builderCreateBidRequest.app(this.j.build());
            builderCreateBidRequest.slots.add(this.h.build());
            builderCreateBidRequest.device(this.i.build());
            builderCreateBidRequest.network(this.g.build());
            if (this.e != null && this.e.getOptions() != null) {
                HashMap map = new HashMap();
                for (String str : this.e.getOptions().keySet()) {
                    if (this.e.getOptions().get(str) != null) {
                        map.put(str, this.e.getOptions().get(str).toString());
                    }
                }
                builderCreateBidRequest.options(map);
                builderCreateBidRequest.ad_is_expired = Boolean.valueOf(this.e.isExpired());
                builderCreateBidRequest.request_scene_type = Integer.valueOf(this.e.getRequest_scene_type());
                builderCreateBidRequest.disable_mediation = Boolean.valueOf(!this.e.isUseMediation());
            }
            if (this.e != null && !TextUtils.isEmpty(this.e.getBidToken())) {
                HeaderBidding.Builder builder = new HeaderBidding.Builder();
                builder.bid_token(this.e.getBidToken());
                builderCreateBidRequest.header_bidding(builder.build());
            }
            bidRequestBuild = builderCreateBidRequest.build();
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            bidRequestBuild = null;
        }
        if (bidRequestBuild == null) {
            SigmobLog.e("builder Ads Post entry fail ");
            return null;
        }
        SigmobLog.d(h() + " send Bid request: " + bidRequestBuild.toString());
        return bidRequestBuild.encode();
    }
}
