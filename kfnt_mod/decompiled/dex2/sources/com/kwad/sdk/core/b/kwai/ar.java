package com.kwad.sdk.core.b.kwai;

import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kwad.sdk.contentalliance.coupon.model.CouponStatusInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ar implements com.kwad.sdk.core.d<CouponStatusInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(CouponStatusInfo couponStatusInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        couponStatusInfo.statusCode = jSONObject.optInt(HiAnalyticsConstant.HaKey.BI_KEY_RESULT, new Integer("-1").intValue());
        couponStatusInfo.statusName = jSONObject.optString("statusName");
        if (jSONObject.opt("statusName") == JSONObject.NULL) {
            couponStatusInfo.statusName = "";
        }
        couponStatusInfo.currTotalAmount = jSONObject.optDouble("currTotalAmount");
        couponStatusInfo.couponLeftTimes = jSONObject.optInt("couponLeftTimes");
        couponStatusInfo.isNewUser = jSONObject.optBoolean("isNewUser");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(CouponStatusInfo couponStatusInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, HiAnalyticsConstant.HaKey.BI_KEY_RESULT, couponStatusInfo.statusCode);
        com.kwad.sdk.utils.t.a(jSONObject, "statusName", couponStatusInfo.statusName);
        com.kwad.sdk.utils.t.a(jSONObject, "currTotalAmount", couponStatusInfo.currTotalAmount);
        com.kwad.sdk.utils.t.a(jSONObject, "couponLeftTimes", couponStatusInfo.couponLeftTimes);
        com.kwad.sdk.utils.t.a(jSONObject, "isNewUser", couponStatusInfo.isNewUser);
        return jSONObject;
    }
}
