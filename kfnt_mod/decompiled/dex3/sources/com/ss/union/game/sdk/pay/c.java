package com.ss.union.game.sdk.pay;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.pay.callback.CreateOrderCallback;
import com.ss.union.game.sdk.pay.callback.IPayNoAccountService;
import com.ss.union.game.sdk.pay.callback.LGPayCallback;
import com.ss.union.game.sdk.pay.fragment.NoAccountPayFragment;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements IPayNoAccountService {
    static final c a = new c();
    private static final int c = 1000;
    private long b = 0;

    @Override // com.ss.union.game.sdk.pay.callback.IPayNoAccountService
    public void tryPayNoAccount(@NonNull String str, @NonNull String str2, @NonNull String str3, long j, @NonNull String str4, @NonNull String str5, String str6, long j2, int i, String str7, final LGPayCallback lGPayCallback) throws JSONException {
        com.ss.union.game.sdk.pay.e.a.c("no account pay start 1");
        com.ss.union.game.sdk.pay.b.a.a(ConfigManager.AppConfig.appName());
        if (lGPayCallback == null) {
            com.ss.union.game.sdk.pay.e.a.c("no account pay callback cannot be null");
            return;
        }
        LGPayCallback lGPayCallback2 = new LGPayCallback() { // from class: com.ss.union.game.sdk.pay.c.1
            @Override // com.ss.union.game.sdk.pay.callback.LGPayCallback
            public void onPayResult(int i2, String str8) {
                com.ss.union.game.sdk.pay.e.a.c("no account pay result: code = " + i2 + " msg = " + str8);
                lGPayCallback.onPayResult(i2, str8);
            }
        };
        if (a(str, str2, str3, j, str4, ConfigManager.PayConfig.getPayKey(), true, lGPayCallback2)) {
            com.ss.union.game.sdk.pay.b.a.a(103);
            return;
        }
        String strB = com.ss.union.game.sdk.pay.e.b.b(str, str2, str3, j + "", str4, str5, str6, j2, i, str7, "MD5");
        if (TextUtils.isEmpty(strB)) {
            lGPayCallback2.onPayResult(103, "生成签名信息失败，请检查参数");
            com.ss.union.game.sdk.pay.b.a.a(103);
        } else {
            a(str, strB, lGPayCallback2);
        }
    }

    @Override // com.ss.union.game.sdk.pay.callback.IPayNoAccountService
    public void tryPayNoAccount(String str, final LGPayCallback lGPayCallback) {
        String strOptString;
        JSONException e;
        com.ss.union.game.sdk.pay.e.a.c("no account pay start 2");
        com.ss.union.game.sdk.pay.b.a.a(ConfigManager.AppConfig.appName());
        if (lGPayCallback == null) {
            com.ss.union.game.sdk.pay.e.a.c("no account pay callback cannot be null");
            return;
        }
        LGPayCallback lGPayCallback2 = new LGPayCallback() { // from class: com.ss.union.game.sdk.pay.c.2
            @Override // com.ss.union.game.sdk.pay.callback.LGPayCallback
            public void onPayResult(int i, String str2) {
                com.ss.union.game.sdk.pay.e.a.c("no account pay result: code = " + i + " msg = " + str2);
                lGPayCallback.onPayResult(i, str2);
            }
        };
        try {
            JSONObject jSONObject = new JSONObject(str);
            strOptString = jSONObject.optString("cp_user_id");
            try {
                if (a(strOptString, jSONObject.optString("order_no"), jSONObject.optString("subject"), jSONObject.optLong("total_amount"), jSONObject.optString("notify_url"), null, false, lGPayCallback2)) {
                    com.ss.union.game.sdk.pay.b.a.a(103);
                    return;
                }
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                a(strOptString, str, lGPayCallback2);
            }
        } catch (JSONException e3) {
            strOptString = null;
            e = e3;
        }
        a(strOptString, str, lGPayCallback2);
    }

    private void a(String str, String str2, LGPayCallback lGPayCallback) {
        if (a() || a(str2, lGPayCallback)) {
            return;
        }
        a(AppIdManager.lgAppID(), str, AppLogManager.getInstance().getDid(), str2, lGPayCallback);
    }

    private boolean a(String str, LGPayCallback lGPayCallback) throws JSONException {
        if (lGPayCallback == null) {
            return true;
        }
        com.ss.union.game.sdk.pay.b.a.b();
        if (TextUtils.isEmpty(str)) {
            lGPayCallback.onPayResult(103, "生成签名信息失败，请检查参数");
            com.ss.union.game.sdk.pay.b.a.a(103);
            return true;
        }
        String strLgAppID = AppIdManager.lgAppID();
        String did = AppLogManager.getInstance().getDid();
        String str2 = null;
        if (TextUtils.isEmpty(strLgAppID)) {
            str2 = "app_id";
        } else if (TextUtils.isEmpty(did)) {
            str2 = "device_id";
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        lGPayCallback.onPayResult(103, String.format("创建订单参数 %s 获取失败,请稍后重试.", str2));
        com.ss.union.game.sdk.pay.b.a.a(103);
        return true;
    }

    private void a(String str, final String str2, String str3, String str4, final LGPayCallback lGPayCallback) {
        com.ss.union.game.sdk.pay.e.a.c("pay checkoutNativePayMode start");
        com.ss.union.game.sdk.pay.e.a.c("pay createOrder start");
        com.ss.union.game.sdk.pay.c.b.a(str, str3, str4, new CreateOrderCallback() { // from class: com.ss.union.game.sdk.pay.c.3
            @Override // com.ss.union.game.sdk.pay.callback.CreateOrderCallback
            public void onFailure(int i, String str5) {
                com.ss.union.game.sdk.pay.e.a.c("pay createOrder fail code " + i + " msg " + str5);
                lGPayCallback.onPayResult(i, str5);
            }

            @Override // com.ss.union.game.sdk.pay.callback.CreateOrderCallback
            public void onSuccess(com.ss.union.game.sdk.pay.a.a aVar) {
                com.ss.union.game.sdk.pay.e.a.c("pay createOrder success " + aVar.c);
                NoAccountPayFragment.a(aVar.c, aVar.a, aVar.b, str2, new LGPayCallback() { // from class: com.ss.union.game.sdk.pay.c.3.1
                    @Override // com.ss.union.game.sdk.pay.callback.LGPayCallback
                    public void onPayResult(int i, String str5) {
                        lGPayCallback.onPayResult(i, str5);
                    }
                });
            }
        });
    }

    @Override // com.ss.union.game.sdk.pay.callback.IPayNoAccountService
    public String generateSignNoAccount(@NonNull String str, @NonNull String str2, @NonNull String str3, String str4, @NonNull String str5, @NonNull String str6, String str7, long j, int i, String str8, String str9) {
        return com.ss.union.game.sdk.pay.e.b.b(str, str2, str3, str4 + "", str5, str6, str7, j, i, str8, "MD5");
    }

    private boolean a(String str, String str2, String str3, long j, String str4, String str5, boolean z, LGPayCallback lGPayCallback) {
        if (lGPayCallback == null) {
            return true;
        }
        if (!LGSDKCore.isSdkInitSuccess()) {
            lGPayCallback.onPayResult(-204, "SDK还未初始化完成");
            return true;
        }
        String str6 = null;
        if (TextUtils.isEmpty(str)) {
            str6 = "cp_user_id 不能为空";
        } else if (TextUtils.isEmpty(str2)) {
            str6 = "order_no 不能为空";
        } else if (TextUtils.isEmpty(str3)) {
            str6 = "subject 不能为空";
        } else if (TextUtils.isEmpty(str4)) {
            str6 = "notify_url 不能为空";
        } else if (z && TextUtils.isEmpty(str5)) {
            str6 = "pay_key 不能为空";
        } else if (j <= 0) {
            str6 = "支付金额必须大于 0";
        }
        if (TextUtils.isEmpty(str6)) {
            return false;
        }
        lGPayCallback.onPayResult(103, String.format("支付请求参数检查 %s，请稍后重试。", str6));
        return true;
    }

    private boolean a() {
        if (System.currentTimeMillis() - this.b < 1000) {
            return true;
        }
        this.b = System.currentTimeMillis();
        return false;
    }
}
