package com.sigmob.sdk.common.e;

import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.common.models.ModelBuilderCreator;
import com.sigmob.sdk.common.models.sigdsp.pb.AdSlot;
import com.sigmob.sdk.common.models.sigdsp.pb.App;
import com.sigmob.sdk.common.models.sigdsp.pb.BidRequest;
import com.sigmob.sdk.common.models.sigdsp.pb.Device;
import com.sigmob.sdk.common.models.sigdsp.pb.DeviceId;
import com.sigmob.sdk.common.models.sigdsp.pb.Network;
import com.sigmob.sdk.common.models.ssp.pb.MediationApp;
import com.sigmob.sdk.common.models.ssp.pb.MediationChannel;
import com.sigmob.sdk.common.models.ssp.pb.MediationElement;
import com.sigmob.sdk.common.models.ssp.pb.StrategyResponseV4;
import com.sigmob.sdk.common.models.ssp.pb.StrategyWaterFall;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.volley.p;
import com.sigmob.volley.s;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class o extends k<StrategyResponseV4> {
    private final a b;
    private final int c;
    private final String d;
    private final String e;
    private Network.Builder f;
    private AdSlot.Builder g;
    private Device.Builder h;
    private App.Builder i;
    private boolean j;

    public interface a {
        void onErrorResponse(String str, int i, int i2, String str2);

        void onSuccess(List<ADStrategy> list, n nVar, String str);
    }

    public o(boolean z, String str, int i, String str2, String str3, a aVar) {
        super(str, 1, null);
        m.a.a(aVar);
        this.j = z;
        this.d = str2;
        this.b = aVar;
        this.c = i;
        this.e = str3;
        a((p) new com.sigmob.volley.c(10000, 0, 0.0f));
        a(false);
    }

    public static void a(boolean z, String str, int i, String str2, String str3, a aVar) {
        if (f.a() == null) {
            if (aVar != null) {
                aVar.onErrorResponse(str2, i, 600100, "RequestQueue is null");
                return;
            }
            return;
        }
        try {
            if (m.b(new URL(str).getHost())) {
                f.a().a(new o(z, str, i, str2, str3, aVar));
            } else if (aVar != null) {
                aVar.onErrorResponse(str2, i, 600100, "can't connection server");
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            if (aVar != null) {
                aVar.onErrorResponse(str2, i, 600100, "LoadStrategies catch " + th.getMessage());
            }
        }
    }

    public static void a(boolean z, String str, String str2, String str3, String str4, int i, a aVar) {
        try {
            ArrayList arrayList = new ArrayList();
            ADStrategy aDStrategy = new ADStrategy(z, str, i, str4, "strategy_id", true, 1);
            aDStrategy.setElement_id(1);
            aDStrategy.setPlacement_id(str4);
            aDStrategy.setAppId(str2);
            aDStrategy.setAppKey(str3);
            aDStrategy.addOptions(new HashMap<>());
            aDStrategy.setName(Constants.SDK_FOLDER);
            aDStrategy.setExpired_time(3600);
            aDStrategy.setExtraCloseCallBack(true);
            aDStrategy.setChannel_id(9);
            aDStrategy.setRightObject(true);
            arrayList.add(aDStrategy);
            n nVar = new n(1, 15, "strategy_id", 1, true);
            if (arrayList.size() > 0) {
                aVar.onSuccess(arrayList, nVar, str4);
            } else {
                aVar.onErrorResponse(str4, i, 600103, "strategyResponse list is null");
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            if (aVar != null) {
                aVar.onErrorResponse(str4, i, 600100, "LoadStrategies catch " + th.getMessage());
            }
        }
    }

    private void e() {
        this.i = ModelBuilderCreator.createApp();
        this.h = ModelBuilderCreator.createDevice();
        DeviceId.Builder builderCreateDeviceId = ModelBuilderCreator.createDeviceId();
        if (TextUtils.isEmpty(this.e)) {
            builderCreateDeviceId.user_id(this.e);
        }
        this.h.did(builderCreateDeviceId.build());
        this.f = ModelBuilderCreator.createNetwork();
        this.g = ModelBuilderCreator.createAdSlot();
        this.g.adslot_type.add(Integer.valueOf(this.c));
        if (TextUtils.isEmpty(this.d)) {
            return;
        }
        this.g.adslot_id(this.d);
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    protected com.sigmob.volley.n<StrategyResponseV4> a(com.sigmob.volley.i iVar) {
        try {
            StrategyResponseV4 strategyResponseV4Decode = StrategyResponseV4.ADAPTER.decode(iVar.b);
            com.sigmob.sdk.common.a.n().c(strategyResponseV4Decode.uid);
            return com.sigmob.volley.n.a(strategyResponseV4Decode, com.sigmob.volley.toolbox.h.a(iVar));
        } catch (Throwable th) {
            return com.sigmob.volley.n.a(new com.sigmob.volley.k(th));
        }
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    public String a() {
        return "application/octet-stream";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sigmob.volley.l
    public void a(StrategyResponseV4 strategyResponseV4) {
        a aVar;
        String str;
        int i;
        String str2;
        SigmobLog.d("strategy response: " + strategyResponseV4);
        try {
            if (strategyResponseV4.strategy_waterfall != null && strategyResponseV4.strategy_waterfall.element_ids.size() > 0) {
                StrategyWaterFall strategyWaterFall = strategyResponseV4.strategy_waterfall;
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < strategyWaterFall.element_ids.size(); i2++) {
                    ADStrategy aDStrategy = new ADStrategy(this.j, this.c, this.d, strategyResponseV4.strategy_id, strategyResponseV4.enable_ab_test.booleanValue(), strategyWaterFall.ab_flag.intValue());
                    HashMap<String, String> map = new HashMap<>();
                    int iIntValue = strategyWaterFall.element_ids.get(i2).intValue();
                    if (strategyResponseV4.elements == null || strategyResponseV4.elements.size() <= 0) {
                        SigmobLog.e("deliverResponse: strategyResponseV4.elements is null");
                    } else {
                        for (int i3 = 0; i3 < strategyResponseV4.elements.size(); i3++) {
                            MediationElement mediationElement = strategyResponseV4.elements.get(i3);
                            if (iIntValue == mediationElement.element_id.intValue()) {
                                aDStrategy.setElement_id(iIntValue);
                                int iIntValue2 = mediationElement.mediation_app_id.intValue();
                                Map<String, String> map2 = mediationElement.options;
                                map.putAll(map2);
                                aDStrategy.setPlacement_id(map2.get(Constants.PLACEMENTID));
                                if (strategyResponseV4.apps == null || strategyResponseV4.apps.size() <= 0) {
                                    SigmobLog.e("deliverResponse: strategyResponseV4.apps is null");
                                } else {
                                    for (int i4 = 0; i4 < strategyResponseV4.apps.size(); i4++) {
                                        MediationApp mediationApp = strategyResponseV4.apps.get(i4);
                                        if (iIntValue2 == mediationApp.app_id.intValue()) {
                                            int iIntValue3 = mediationApp.mediation_channel_id.intValue();
                                            Map<String, String> map3 = mediationApp.options;
                                            map.putAll(map3);
                                            aDStrategy.setAppId(map3.get(Constants.APPID));
                                            aDStrategy.setAppKey(map3.get(Constants.APPKEY));
                                            aDStrategy.addOptions(map);
                                            if (strategyResponseV4.channels == null || strategyResponseV4.channels.size() <= 0) {
                                                SigmobLog.e("deliverResponse: strategyResponseV4.channel is null");
                                            } else {
                                                for (int i5 = 0; i5 < strategyResponseV4.channels.size(); i5++) {
                                                    MediationChannel mediationChannel = strategyResponseV4.channels.get(i5);
                                                    if (iIntValue3 == mediationChannel.channel_id.intValue()) {
                                                        aDStrategy.setName(mediationChannel.name);
                                                        aDStrategy.setExpired_time(mediationChannel.ad_expire_time.intValue());
                                                        aDStrategy.setExtraCloseCallBack(mediationChannel.enable_extra_close_callback.booleanValue());
                                                        aDStrategy.setChannel_id(mediationChannel.channel_id.intValue());
                                                        aDStrategy.setRightObject(true);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (aDStrategy.isRightObject()) {
                        arrayList.add(aDStrategy);
                    } else {
                        SigmobLog.e("This adStrategy is not pass: " + aDStrategy.toString());
                    }
                }
                n nVar = new n(strategyResponseV4.strategy_waterfall.concurrent_count.intValue(), strategyResponseV4.single_channel_timeout.intValue(), strategyResponseV4.strategy_id, strategyResponseV4.strategy_waterfall.ab_flag.intValue(), strategyResponseV4.enable_ab_test.booleanValue());
                if (arrayList.size() > 0) {
                    this.b.onSuccess(arrayList, nVar, this.d);
                    return;
                }
                aVar = this.b;
                str = this.d;
                i = this.c;
                str2 = "strategyResponse list is null";
            } else {
                if (strategyResponseV4.code.intValue() != 0) {
                    this.b.onErrorResponse(this.d, this.c, strategyResponseV4.code.intValue(), strategyResponseV4.error_message);
                    SigmobLog.e(this.d + "SigMob adStrategy error " + strategyResponseV4.code);
                    return;
                }
                aVar = this.b;
                str = this.d;
                i = this.c;
                str2 = "strategyResponse code 0";
            }
            aVar.onErrorResponse(str, i, 600103, str2);
        } catch (Throwable th) {
            SigmobLog.e("strategy exception ", th);
            this.b.onErrorResponse(this.d, this.c, 600103, th.getMessage());
        }
    }

    @Override // com.sigmob.volley.l
    public void a(s sVar) {
        this.b.onErrorResponse(this.d, this.c, 600100, sVar.getMessage());
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    public byte[] b() {
        BidRequest bidRequestBuild;
        e();
        try {
            BidRequest.Builder builderCreateBidRequest = ModelBuilderCreator.createBidRequest();
            builderCreateBidRequest.app(this.i.build());
            builderCreateBidRequest.slots.add(this.g.build());
            builderCreateBidRequest.device(this.h.build());
            builderCreateBidRequest.network(this.f.build());
            builderCreateBidRequest.disable_mediation = true;
            if (builderCreateBidRequest.options == null) {
                builderCreateBidRequest.options = new HashMap();
            }
            Map<String, String> map = builderCreateBidRequest.options;
            com.sigmob.sdk.common.a.n();
            map.put("gdpr_consent_status", com.sigmob.sdk.common.a.l());
            builderCreateBidRequest.disable_mediation = Boolean.valueOf(true ^ this.j);
            bidRequestBuild = builderCreateBidRequest.build();
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            bidRequestBuild = null;
        }
        if (bidRequestBuild == null) {
            SigmobLog.e("builder Ads Post entry fail ");
            return null;
        }
        SigmobLog.d(h() + " send strategy request: " + bidRequestBuild.toString());
        return bidRequestBuild.encode();
    }
}
