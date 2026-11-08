package com.ss.union.game.sdk.pay.c;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ss.android.pushmanager.PushCommonConstants;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.pay.callback.CreateOrderCallback;
import com.ss.union.game.sdk.pay.callback.LGPayCallback;
import com.ss.union.game.sdk.pay.callback.LGPayGalaxySupportCallback;
import com.ss.union.game.sdk.pay.callback.LGPaySupportCallback;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static void a(long j, final LGPaySupportCallback lGPaySupportCallback) {
        CoreNetClient.post(c.a).param("app_id", AppIdManager.lgAppID()).param(com.umeng.message.common.a.u, DeviceUtils.getAppPackageName()).param(PushCommonConstants.KEY_USER_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("bd_did", AppLogManager.getInstance().getDid()).param("token", LGAccountDataUtil.getLoginToken()).param("total_amount", String.valueOf(j)).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.pay.c.a.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (lGPaySupportCallback != null) {
                    lGPaySupportCallback.onResponse(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                boolean zOptBoolean;
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (lGPaySupportCallback != null) {
                    try {
                        zOptBoolean = coreNetResponse.data.getJSONObject("data").optBoolean("can_be_paid", false);
                    } catch (Throwable unused) {
                        zOptBoolean = false;
                    }
                    if (zOptBoolean) {
                        lGPaySupportCallback.onResponse(0, "当前用户可支付");
                    } else {
                        lGPaySupportCallback.onResponse(-1, "服务端错误,请稍后重试");
                    }
                }
            }
        });
    }

    public static void a(String str, String str2, long j, final LGPayGalaxySupportCallback lGPayGalaxySupportCallback) {
        CoreNetClient.post(c.b).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("token", LGAccountDataUtil.getLoginToken()).param("product_id", str).param("subject", str2).param("total_amount", j).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.pay.c.a.2
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (lGPayGalaxySupportCallback != null) {
                    lGPayGalaxySupportCallback.onResponse(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:12:0x002a  */
            /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onNetSuccess(com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost r5, com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse<org.json.JSONObject, com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost> r6) {
                /*
                    r4 = this;
                    super.onNetSuccess(r5, r6)
                    com.ss.union.game.sdk.pay.callback.LGPayGalaxySupportCallback r5 = r1
                    if (r5 == 0) goto L3b
                    r5 = 0
                    T r0 = r6.data     // Catch: java.lang.Throwable -> L26
                    org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch: java.lang.Throwable -> L26
                    java.lang.String r1 = "data"
                    org.json.JSONObject r0 = r0.optJSONObject(r1)     // Catch: java.lang.Throwable -> L26
                    java.lang.String r1 = "enough"
                    boolean r1 = r0.optBoolean(r1, r5)     // Catch: java.lang.Throwable -> L26
                    java.lang.String r2 = "money"
                    int r2 = r0.optInt(r2, r5)     // Catch: java.lang.Throwable -> L27
                    java.lang.String r3 = "cost"
                    int r0 = r0.optInt(r3, r5)     // Catch: java.lang.Throwable -> L28
                    r5 = r0
                    goto L28
                L26:
                    r1 = 0
                L27:
                    r2 = 0
                L28:
                    if (r1 == 0) goto L30
                    com.ss.union.game.sdk.pay.callback.LGPayGalaxySupportCallback r6 = r1
                    r6.onSupport(r2, r5)
                    goto L3b
                L30:
                    com.ss.union.game.sdk.pay.callback.LGPayGalaxySupportCallback r5 = r1
                    int r6 = r6.code()
                    java.lang.String r0 = "余额不足"
                    r5.onResponse(r6, r0)
                L3b:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.pay.c.a.AnonymousClass2.onNetSuccess(com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost, com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse):void");
            }
        });
    }

    public static void a(String str, final LGPayCallback lGPayCallback) {
        CoreNetClient.post(c.c).param(PushCommonConstants.KEY_USER_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("token", LGAccountDataUtil.getLoginToken()).param("external_order_payload", str).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.pay.c.a.3
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (lGPayCallback != null) {
                    lGPayCallback.onPayResult(coreNetResponse.code(), coreNetResponse.message());
                }
                a.b(false);
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (lGPayCallback != null) {
                    try {
                        com.ss.union.game.sdk.pay.e.a.b("pay decreaseGalaxy success order_no =" + coreNetResponse.data.optJSONObject("data").optString("order_no"));
                        lGPayCallback.onPayResult(0, "支付成功");
                        a.b(true);
                    } catch (Throwable th) {
                        lGPayCallback.onPayResult(102, "订单查询结果解析出错: " + th.getMessage());
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(boolean z) {
        View viewInflate;
        try {
            if (z) {
                String string = ResourceUtils.getString("lg_pay_galaxy_toast_success");
                viewInflate = View.inflate(ActivityUtils.getTopActivity(), ResourceUtils.getLayoutIdByName("lg_pay_galaxy_toast_pay_success"), null);
                ((TextView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_pag_galaxy_toast_textview"))).setText(string);
            } else {
                viewInflate = View.inflate(ActivityUtils.getTopActivity(), ResourceUtils.getLayoutIdByName("lg_pay_galaxy_toast_fail"), null);
                ((ImageView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_user_feedback_submit_result_toast_icon"))).setImageResource(ResourceUtils.getDrawableIdByName("lg_pay_galaxy_toast_fail_image"));
                ((TextView) viewInflate.findViewById(ResourceUtils.getIdByName("lg_user_feedback_submit_result_toast_content"))).setText(ResourceUtils.getString("lg_pay_galaxy_toast_fail"));
            }
            ToastUtils.getInstance().toast(new ToastUtils.Builder().view(viewInflate).toast("placeHolder"));
        } catch (Throwable unused) {
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, final CreateOrderCallback createOrderCallback) {
        CoreNetClient.post(c.d).param("app_id", str).param(PushCommonConstants.KEY_USER_ID, str2).param("token", str3).param("bd_did", str4).param("external_order_payload", str5).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.pay.c.a.4
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

    public static void b(String str, final LGPayCallback lGPayCallback) {
        CoreNetClient.post(c.e).param("type", "CLIENT").param("app_id", AppIdManager.lgAppID()).param(PushCommonConstants.KEY_USER_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("token", LGAccountDataUtil.getLoginToken()).param("order_no", str).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.pay.c.a.5
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
