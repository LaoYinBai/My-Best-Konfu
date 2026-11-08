package com.ss.union.game.sdk.pay;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ss.android.pushmanager.PushCommonConstants;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.utils.DanJuanUtils;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import com.ss.union.game.sdk.pay.callback.CreateOrderCallback;
import com.ss.union.game.sdk.pay.callback.ILGPayService;
import com.ss.union.game.sdk.pay.callback.LGPayCallback;
import com.ss.union.game.sdk.pay.callback.LGPaySupportCallback;
import com.ss.union.game.sdk.pay.callback.PayTipsForUnnamedCallback;
import com.ss.union.game.sdk.pay.callback.PayTipsForYoungCallback;
import com.ss.union.game.sdk.pay.fragment.PayFragment;
import com.ss.union.game.sdk.pay.fragment.PayTipsForUnnamedFragment;
import com.ss.union.game.sdk.pay.fragment.PayTipsForYoungFragment;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d implements ILGPayService {
    static final d a = new d();
    private static final int c = 1000;
    private long b = 0;

    @Override // com.ss.union.game.sdk.pay.callback.ILGPayService
    public void tryPay(@NonNull String str, @NonNull String str2, long j, @NonNull String str3, @NonNull String str4, String str5, long j2, int i, String str6, final LGPayCallback lGPayCallback) throws JSONException {
        com.ss.union.game.sdk.pay.e.a.b("pay start 1");
        com.ss.union.game.sdk.pay.b.a.a(ConfigManager.AppConfig.appName());
        if (lGPayCallback == null) {
            com.ss.union.game.sdk.pay.e.a.b("pay callback cannot be null");
            return;
        }
        LGPayCallback lGPayCallback2 = new LGPayCallback() { // from class: com.ss.union.game.sdk.pay.d.1
            @Override // com.ss.union.game.sdk.pay.callback.LGPayCallback
            public void onPayResult(int i2, String str7) {
                com.ss.union.game.sdk.pay.e.a.b("pay result: code = " + i2 + " msg = " + str7);
                lGPayCallback.onPayResult(i2, str7);
            }
        };
        if (a(str, str2, j, str3, ConfigManager.PayConfig.getPayKey(), true, lGPayCallback2)) {
            com.ss.union.game.sdk.pay.b.a.a(103);
            return;
        }
        String strA = com.ss.union.game.sdk.pay.e.b.a(LGAccountDataUtil.getCurrentUserOpenId(), str, str2, j + "", str3, str4, str5, j2, i, str6, "MD5");
        if (TextUtils.isEmpty(strA)) {
            lGPayCallback2.onPayResult(103, "生成签名信息失败，请检查参数");
            com.ss.union.game.sdk.pay.b.a.a(103);
        } else {
            a(strA, lGPayCallback2);
        }
    }

    @Override // com.ss.union.game.sdk.pay.callback.ILGPayService
    public void tryPay(String str, final LGPayCallback lGPayCallback) {
        com.ss.union.game.sdk.pay.e.a.b("pay start 2");
        com.ss.union.game.sdk.pay.b.a.a(ConfigManager.AppConfig.appName());
        if (lGPayCallback == null) {
            com.ss.union.game.sdk.pay.e.a.b("pay callback cannot be null");
            return;
        }
        LGPayCallback lGPayCallback2 = new LGPayCallback() { // from class: com.ss.union.game.sdk.pay.d.2
            @Override // com.ss.union.game.sdk.pay.callback.LGPayCallback
            public void onPayResult(int i, String str2) {
                com.ss.union.game.sdk.pay.e.a.b("pay result: code = " + i + " msg = " + str2);
                lGPayCallback.onPayResult(i, str2);
            }
        };
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (a(jSONObject.optString("order_no"), jSONObject.optString("subject"), jSONObject.optLong("total_amount"), jSONObject.optString("notify_url"), (String) null, false, lGPayCallback2)) {
                com.ss.union.game.sdk.pay.b.a.a(103);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(str, lGPayCallback2);
    }

    private boolean a() {
        if (System.currentTimeMillis() - this.b < 1000) {
            return true;
        }
        this.b = System.currentTimeMillis();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, LGPayCallback lGPayCallback) {
        if (a() || b(str, lGPayCallback)) {
            return;
        }
        if (DanJuanUtils.isRunningDanJuanVirtual() && !LGAccountDataUtil.isVisitor()) {
            a(lGPayCallback, str);
        } else {
            a(AppIdManager.lgAppID(), LGAccountDataUtil.getCurrentUserOpenId(), LGAccountDataUtil.getLoginToken(), AppLogManager.getInstance().getDid(), str, lGPayCallback);
        }
    }

    private boolean b(String str, LGPayCallback lGPayCallback) throws JSONException {
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
        String currentUserOpenId = LGAccountDataUtil.getCurrentUserOpenId();
        String loginToken = LGAccountDataUtil.getLoginToken();
        String did = AppLogManager.getInstance().getDid();
        String str2 = null;
        if (TextUtils.isEmpty(strLgAppID)) {
            str2 = "app_id";
        } else if (TextUtils.isEmpty(currentUserOpenId)) {
            str2 = PushCommonConstants.KEY_USER_ID;
        } else if (TextUtils.isEmpty(loginToken)) {
            str2 = "token";
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

    private boolean c(final String str, final LGPayCallback lGPayCallback) {
        if (!GameOptionConfig.GameOption.AntiAddiction.Pay.isEnableAntiAddiction() || LGAccountDataUtil.isIdentifyValidated()) {
            return false;
        }
        com.ss.union.game.sdk.pay.b.a.e();
        com.ss.union.game.sdk.pay.e.a.b("pay PayTipsForUnnamedFragment show");
        PayTipsForUnnamedFragment.a(new PayTipsForUnnamedCallback() { // from class: com.ss.union.game.sdk.pay.d.3
            @Override // com.ss.union.game.sdk.pay.callback.PayTipsForUnnamedCallback
            public void onSuccess(boolean z, boolean z2) {
                com.ss.union.game.sdk.pay.e.a.b("pay PayTipsForUnnamedFragment success isValid " + z + " isAdult " + z2);
                d.this.a(str, lGPayCallback);
            }

            @Override // com.ss.union.game.sdk.pay.callback.PayTipsForUnnamedCallback
            public void onCancel() {
                com.ss.union.game.sdk.pay.e.a.b("pay PayTipsForUnnamedFragment cancel ");
                lGPayCallback.onPayResult(104, "取消支付");
            }
        });
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(final com.ss.union.game.sdk.pay.callback.LGPayCallback r9, final java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "pay checkoutGalaxyPayMode start"
            com.ss.union.game.sdk.pay.e.a.b(r0)
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            r2 = 0
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L29
            r4.<init>(r10)     // Catch: org.json.JSONException -> L29
            java.lang.String r5 = "total_amount"
            long r5 = r4.optLong(r5)     // Catch: org.json.JSONException -> L29
            java.lang.String r2 = "product_id"
            java.lang.String r2 = r4.optString(r2)     // Catch: org.json.JSONException -> L26
            java.lang.String r0 = "subject"
            java.lang.String r0 = r4.optString(r0)     // Catch: org.json.JSONException -> L23
            goto L30
        L23:
            r0 = move-exception
            r4 = r0
            goto L2c
        L26:
            r2 = move-exception
            r4 = r2
            goto L2b
        L29:
            r4 = move-exception
            r5 = r2
        L2b:
            r2 = r0
        L2c:
            r4.printStackTrace()
            r0 = r1
        L30:
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            r3 = 0
            r4 = 1
            r7 = 103(0x67, float:1.44E-43)
            if (r1 == 0) goto L48
            java.lang.String r10 = "小鱼币请求参数 %s 为空，检查product_id"
            java.lang.Object[] r0 = new java.lang.Object[r4]
            r0[r3] = r2
            java.lang.String r10 = java.lang.String.format(r10, r0)
            r9.onPayResult(r7, r10)
            return
        L48:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L5c
            java.lang.String r10 = "小鱼币请求参数 %s 为空，检查subject"
            java.lang.Object[] r1 = new java.lang.Object[r4]
            r1[r3] = r0
            java.lang.String r10 = java.lang.String.format(r10, r1)
            r9.onPayResult(r7, r10)
            return
        L5c:
            com.ss.union.game.sdk.pay.d$4 r1 = new com.ss.union.game.sdk.pay.d$4
            r1.<init>()
            com.ss.union.game.sdk.pay.c.a.a(r2, r0, r5, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.pay.d.a(com.ss.union.game.sdk.pay.callback.LGPayCallback, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, String str4, String str5, final LGPayCallback lGPayCallback) {
        com.ss.union.game.sdk.pay.e.a.b("pay checkoutNativePayMode start");
        if (c(str5, lGPayCallback)) {
            return;
        }
        com.ss.union.game.sdk.pay.e.a.b("pay createOrder start");
        com.ss.union.game.sdk.pay.c.a.a(str, str2, str3, str4, str5, new CreateOrderCallback() { // from class: com.ss.union.game.sdk.pay.d.5
            @Override // com.ss.union.game.sdk.pay.callback.CreateOrderCallback
            public void onFailure(final int i, final String str6) {
                com.ss.union.game.sdk.pay.e.a.b("pay createOrder fail code " + i + " msg " + str6);
                if (61002 == i || 61003 == i || 61004 == i) {
                    com.ss.union.game.sdk.pay.b.a.g();
                    com.ss.union.game.sdk.pay.e.a.b("pay  PayTipsForYoungFragment show");
                    PayTipsForYoungFragment.a(new PayTipsForYoungCallback() { // from class: com.ss.union.game.sdk.pay.d.5.1
                        @Override // com.ss.union.game.sdk.pay.callback.PayTipsForYoungCallback
                        public void onClose() {
                            com.ss.union.game.sdk.pay.e.a.b("pay  PayTipsForYoungFragment close");
                            lGPayCallback.onPayResult(i, str6);
                        }
                    });
                    return;
                }
                lGPayCallback.onPayResult(i, str6);
            }

            @Override // com.ss.union.game.sdk.pay.callback.CreateOrderCallback
            public void onSuccess(com.ss.union.game.sdk.pay.a.a aVar) {
                com.ss.union.game.sdk.pay.e.a.b("pay createOrder success " + aVar.c);
                PayFragment.a(aVar.c, aVar.a, aVar.b, new LGPayCallback() { // from class: com.ss.union.game.sdk.pay.d.5.2
                    @Override // com.ss.union.game.sdk.pay.callback.LGPayCallback
                    public void onPayResult(int i, String str6) {
                        lGPayCallback.onPayResult(i, str6);
                    }
                });
            }
        });
    }

    @Override // com.ss.union.game.sdk.pay.callback.ILGPayService
    public String generateSign(@NonNull String str, @NonNull String str2, @NonNull String str3, String str4, @NonNull String str5, @NonNull String str6, String str7, long j, int i, String str8, String str9) {
        return com.ss.union.game.sdk.pay.e.b.a(LGAccountDataUtil.getCurrentUserOpenId(), str2, str3, str4 + "", str5, str6, str7, j, i, str8, "MD5");
    }

    private boolean a(String str, String str2, long j, String str3, String str4, boolean z, LGPayCallback lGPayCallback) throws JSONException {
        if (lGPayCallback == null) {
            return true;
        }
        if (!LGSDKCore.isSdkInitSuccess()) {
            lGPayCallback.onPayResult(-204, "SDK还未初始化完成");
            return true;
        }
        if (!LGAccountDataUtil.isLogined() || TextUtils.isEmpty(LGAccountDataUtil.getCurrentUserOpenId())) {
            lGPayCallback.onPayResult(101, "无法支付，请登录后再调用支付");
            com.ss.union.game.sdk.pay.b.a.a(101);
            return true;
        }
        String str5 = null;
        if (TextUtils.isEmpty(str)) {
            str5 = "order_no 不能为空";
        } else if (TextUtils.isEmpty(str2)) {
            str5 = "subject 不能为空";
        } else if (TextUtils.isEmpty(str3)) {
            str5 = "notify_url 不能为空";
        } else if (z && TextUtils.isEmpty(str4)) {
            str5 = "pay_key 不能为空";
        } else if (j <= 0) {
            str5 = "支付金额必须大于 0";
        }
        if (TextUtils.isEmpty(str5)) {
            return false;
        }
        lGPayCallback.onPayResult(103, String.format("支付请求参数检查 %s，请稍后重试。", str5));
        return true;
    }

    @Override // com.ss.union.game.sdk.pay.callback.ILGPayService
    public void queryPaySupport(long j, final LGPaySupportCallback lGPaySupportCallback) {
        com.ss.union.game.sdk.pay.e.a.b("queryPaySupport start");
        if (lGPaySupportCallback == null) {
            com.ss.union.game.sdk.pay.e.a.b("pay callback cannot be null");
            return;
        }
        LGPaySupportCallback lGPaySupportCallback2 = new LGPaySupportCallback() { // from class: com.ss.union.game.sdk.pay.d.6
            @Override // com.ss.union.game.sdk.pay.callback.LGPaySupportCallback
            public void onResponse(int i, String str) {
                com.ss.union.game.sdk.pay.e.a.b("queryPaySupport result: code = " + i + " msg = " + str);
                lGPaySupportCallback.onResponse(i, str);
            }
        };
        if (!LGSDKCore.isSdkInitSuccess()) {
            lGPaySupportCallback2.onResponse(-204, "SDK还未初始化完成");
            return;
        }
        if (!LGAccountDataUtil.isLogined()) {
            lGPaySupportCallback2.onResponse(101, "当前未登录，不可支付，请先登录.");
        } else if (j <= 0) {
            lGPaySupportCallback2.onResponse(4, "输入金额必须大于0");
        } else {
            com.ss.union.game.sdk.pay.c.a.a(j, lGPaySupportCallback2);
        }
    }
}
