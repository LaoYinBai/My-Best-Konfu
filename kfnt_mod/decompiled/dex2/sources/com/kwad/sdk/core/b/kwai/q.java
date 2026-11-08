package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdProductInfo;
import com.kwad.sdk.core.response.model.CouponInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class q implements com.kwad.sdk.core.d<AdProductInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdProductInfo adProductInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        adProductInfo.icon = jSONObject.optString("icon");
        if (jSONObject.opt("icon") == JSONObject.NULL) {
            adProductInfo.icon = "";
        }
        adProductInfo.name = jSONObject.optString("name");
        if (jSONObject.opt("name") == JSONObject.NULL) {
            adProductInfo.name = "";
        }
        adProductInfo.price = jSONObject.optString("price");
        if (jSONObject.opt("price") == JSONObject.NULL) {
            adProductInfo.price = "";
        }
        adProductInfo.originPrice = jSONObject.optString("originPrice");
        if (jSONObject.opt("originPrice") == JSONObject.NULL) {
            adProductInfo.originPrice = "";
        }
        adProductInfo.couponList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("couponList");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                CouponInfo couponInfo = new CouponInfo();
                couponInfo.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                adProductInfo.couponList.add(couponInfo);
            }
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdProductInfo adProductInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "icon", adProductInfo.icon);
        com.kwad.sdk.utils.t.a(jSONObject, "name", adProductInfo.name);
        com.kwad.sdk.utils.t.a(jSONObject, "price", adProductInfo.price);
        com.kwad.sdk.utils.t.a(jSONObject, "originPrice", adProductInfo.originPrice);
        com.kwad.sdk.utils.t.a(jSONObject, "couponList", adProductInfo.couponList);
        return jSONObject;
    }
}
