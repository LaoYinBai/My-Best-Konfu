package com.sigmob.sdk.nativead;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.a.b;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.LoadAdRequest;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import com.sigmob.sdk.common.mta.PointEntitySigmobError;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class m implements b.a {
    private Context a;
    private Handler b;
    private p c;
    private LoadAdRequest d;

    public m(Context context, LoadAdRequest loadAdRequest, p pVar) {
        if (context == null) {
            if (this.c != null) {
                this.c.onNativeAdLoadFail(600502, "SigmobNativeAd context can't null");
            }
        } else {
            this.d = loadAdRequest;
            this.b = new Handler(Looper.getMainLooper());
            this.c = pVar;
            this.a = context.getApplicationContext();
        }
    }

    private s a(BaseAdUnit baseAdUnit) {
        if (baseAdUnit != null) {
            return new t(this.a, baseAdUnit);
        }
        return null;
    }

    private void a(final List<s> list, final int i, final String str) {
        this.b.post(new Runnable() { // from class: com.sigmob.sdk.nativead.m.1
            @Override // java.lang.Runnable
            public void run() {
                p pVar;
                int errorCode;
                String message;
                if (m.this.c != null) {
                    if (i != 0) {
                        pVar = m.this.c;
                        errorCode = i;
                        message = str;
                    } else if (list != null && list.size() != 0) {
                        m.this.c.onNativeAdLoaded(list);
                        return;
                    } else {
                        pVar = m.this.c;
                        errorCode = SigmobError.ERROR_REQUEST_NEED_AD_SLOTS_INFO.getErrorCode();
                        message = SigmobError.ERROR_REQUEST_NEED_AD_SLOTS_INFO.getMessage();
                    }
                    pVar.onNativeAdLoadFail(errorCode, message);
                }
            }
        });
    }

    public void a() {
        SigmobError sigmobErrorC;
        if (!com.sigmob.sdk.a.a()) {
            a(null, SigmobError.ERROR_SIGMOB_NOT_INIT.getErrorCode(), SigmobError.ERROR_SIGMOB_NOT_INIT.getMessage());
            return;
        }
        if (this.d == null || TextUtils.isEmpty(this.d.getPlacementId())) {
            a(null, SigmobError.ERROR_SIGMOB_PLACEMENTID_EMPTY.getErrorCode(), SigmobError.ERROR_SIGMOB_PLACEMENTID_EMPTY.getMessage());
            return;
        }
        try {
            if (com.sigmob.sdk.a.b() != null && (sigmobErrorC = com.sigmob.sdk.a.b().c()) != null) {
                SigmobLog.e("check loadAd error " + sigmobErrorC.toString());
                PointEntitySigmobError.SigmobError(PointCategory.LOAD, sigmobErrorC.getErrorCode(), sigmobErrorC.getMessage()).commit();
                a(null, sigmobErrorC.getErrorCode(), sigmobErrorC.getMessage());
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.sigmob.sdk.base.common.t.a(PointCategory.REQUEST, PointCategory.INIT, (BaseAdUnit) null, new t.a() { // from class: com.sigmob.sdk.nativead.m.2
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                    pointEntitySigmob.setAdtype(String.valueOf(5));
                    pointEntitySigmob.setPlacement_id(m.this.d.getPlacementId());
                }
            }
        });
        com.sigmob.sdk.base.a.b.a(SDKConfig.sharedInstance().getAdsUrl(), this.d, this);
    }

    @Override // com.sigmob.sdk.base.a.b.a
    public void a(int i, String str, String str2, final LoadAdRequest loadAdRequest) {
        com.sigmob.sdk.base.common.t.a(PointCategory.SERVER_ERROR, i, str, (BaseAdUnit) null, new t.a() { // from class: com.sigmob.sdk.nativead.m.5
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmobError) {
                    PointEntitySigmobError pointEntitySigmobError = (PointEntitySigmobError) obj;
                    pointEntitySigmobError.setRequest_id(loadAdRequest.getRequestId());
                    pointEntitySigmobError.setPlacement_id(loadAdRequest.getPlacementId());
                    pointEntitySigmobError.setAdtype(String.valueOf(loadAdRequest.getAdType()));
                }
            }
        });
        com.sigmob.sdk.base.common.t.a(PointCategory.RESPOND, Constants.FAIL, (BaseAdUnit) null, new t.a() { // from class: com.sigmob.sdk.nativead.m.6
            @Override // com.sigmob.sdk.base.common.t.a
            public void a(Object obj) {
                if (obj instanceof PointEntitySigmob) {
                    PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                    pointEntitySigmob.setAdtype(String.valueOf(loadAdRequest.getAdType()));
                    pointEntitySigmob.setPlacement_id(loadAdRequest.getPlacementId());
                    pointEntitySigmob.setLoad_id(loadAdRequest.getLoadId());
                    pointEntitySigmob.setRequest_id(loadAdRequest.getRequestId());
                }
            }
        });
        a(null, i, str);
    }

    @Override // com.sigmob.sdk.base.a.b.a
    public void a(List<BaseAdUnit> list, final LoadAdRequest loadAdRequest) {
        String str;
        String str2;
        t.a aVar;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            BaseAdUnit baseAdUnit = list.get(i);
            s sVarA = a(baseAdUnit);
            if (sVarA != null) {
                arrayList.add(sVarA);
                com.sigmob.sdk.base.common.d.f().f(baseAdUnit);
            }
        }
        if (arrayList.size() > 0) {
            str = PointCategory.RESPOND;
            str2 = "1";
            aVar = new t.a() { // from class: com.sigmob.sdk.nativead.m.3
                @Override // com.sigmob.sdk.base.common.t.a
                public void a(Object obj) {
                    if (obj instanceof PointEntitySigmob) {
                        PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                        pointEntitySigmob.setAdtype(String.valueOf(loadAdRequest.getAdType()));
                        pointEntitySigmob.setPlacement_id(loadAdRequest.getPlacementId());
                        pointEntitySigmob.setLoad_id(loadAdRequest.getLoadId());
                        pointEntitySigmob.setRequest_id(loadAdRequest.getRequestId());
                    }
                }
            };
        } else {
            str = PointCategory.RESPOND;
            str2 = Constants.FAIL;
            aVar = new t.a() { // from class: com.sigmob.sdk.nativead.m.4
                @Override // com.sigmob.sdk.base.common.t.a
                public void a(Object obj) {
                    if (obj instanceof PointEntitySigmob) {
                        PointEntitySigmob pointEntitySigmob = (PointEntitySigmob) obj;
                        pointEntitySigmob.setAdtype(String.valueOf(loadAdRequest.getAdType()));
                        pointEntitySigmob.setPlacement_id(loadAdRequest.getPlacementId());
                        pointEntitySigmob.setLoad_id(loadAdRequest.getLoadId());
                        pointEntitySigmob.setRequest_id(loadAdRequest.getRequestId());
                    }
                }
            };
        }
        com.sigmob.sdk.base.common.t.a(str, str2, (BaseAdUnit) null, aVar);
        a(arrayList, 0, null);
    }
}
