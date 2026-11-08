package com.ss.union.game.sdk.pay.c;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.pay.callback.CreateOrderCallback;
import com.ss.union.game.sdk.pay.callback.LGPayCallback;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static void a(String str, String str2, String str3, final CreateOrderCallback createOrderCallback) {
        CoreNetClient.post(c.f).param("app_id", str).param("bd_did", str2).param("external_order_payload", str3).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.pay.c.b.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) throws JSONException {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (createOrderCallback != null) {
                    createOrderCallback.onFailure(coreNetResponse.code(), coreNetResponse.message());
                }
                if (TextUtils.isEmpty(coreNetResponse.httpBody)) {
                    com.ss.union.game.sdk.pay.b.a.a(106, 0);
                } else if (coreNetResponse.isServerError()) {
                    com.ss.union.game.sdk.pay.b.a.a(coreNetResponse.code(), 1);
                } else {
                    com.ss.union.game.sdk.pay.b.a.a(106, 3);
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) throws JSONException {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.pay.a.a aVarA = com.ss.union.game.sdk.pay.a.a.a(coreNetResponse.data.optJSONObject("data"));
                if (TextUtils.isEmpty(aVarA.c)) {
                    createOrderCallback.onFailure(106, "创建订单接口请求失败,未获取到失败信息!");
                    com.ss.union.game.sdk.pay.b.a.a(106, 2);
                } else {
                    createOrderCallback.onSuccess(aVarA);
                    com.ss.union.game.sdk.pay.b.a.c();
                }
            }
        });
    }

    public static void a(String str, String str2, final LGPayCallback lGPayCallback) {
        CoreNetClient.post(c.g).param("type", "CLIENT").param("app_id", AppIdManager.lgAppID()).param("cp_user_id", str2).param("order_no", str).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.pay.c.b.2
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (lGPayCallback != null) {
                    lGPayCallback.onPayResult(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (lGPayCallback != null) {
                    try {
                        String strOptString = coreNetResponse.data.getJSONObject("data").optString("trade_status");
                        if ("PAID".equals(strOptString)) {
                            lGPayCallback.onPayResult(0, "支付成功");
                        } else {
                            lGPayCallback.onPayResult(104, strOptString);
                        }
                    } catch (Throwable th) {
                        lGPayCallback.onPayResult(102, "订单查询结果解析出错: " + th.getMessage());
                    }
                }
            }
        });
    }
}
