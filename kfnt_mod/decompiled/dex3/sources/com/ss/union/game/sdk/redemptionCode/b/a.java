package com.ss.union.game.sdk.redemptionCode.b;

import android.net.Uri;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.HMacMD5;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeConfirmCallback;
import com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeInquireCallback;
import com.xiaomi.mipush.sdk.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static void a(String str, final LGRedemptionCodeInquireCallback lGRedemptionCodeInquireCallback) {
        CoreNetClient.post(b.a).param("app_id", AppIdManager.lgAppID()).param(com.umeng.message.common.a.u, DeviceUtils.getAppPackageName()).param("token", LGAccountDataUtil.getLoginToken()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("code", str).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.redemptionCode.b.a.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (lGRedemptionCodeInquireCallback != null) {
                    lGRedemptionCodeInquireCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (lGRedemptionCodeInquireCallback != null) {
                    if (a.b(coreNetResponse.data)) {
                        JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                        if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(jSONObjectOptJSONObject.optString("code", ""))) {
                            lGRedemptionCodeInquireCallback.onSuccess(jSONObjectOptJSONObject.optString("code", ""));
                            return;
                        } else {
                            lGRedemptionCodeInquireCallback.onFail(4, com.ss.union.game.sdk.redemptionCode.a.j);
                            return;
                        }
                    }
                    lGRedemptionCodeInquireCallback.onFail(4, com.ss.union.game.sdk.redemptionCode.a.j);
                }
            }
        });
    }

    public static void a(String str, final LGRedemptionCodeConfirmCallback lGRedemptionCodeConfirmCallback) {
        CoreNetClient.post(b.b).param("app_id", AppIdManager.lgAppID()).param(com.umeng.message.common.a.u, DeviceUtils.getAppPackageName()).param("token", LGAccountDataUtil.getLoginToken()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("code", str).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.redemptionCode.b.a.2
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (lGRedemptionCodeConfirmCallback != null) {
                    lGRedemptionCodeConfirmCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (lGRedemptionCodeConfirmCallback != null) {
                    if (a.b(coreNetResponse.data)) {
                        JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                        if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(jSONObjectOptJSONObject.optString("code", ""))) {
                            lGRedemptionCodeConfirmCallback.onSuccess(jSONObjectOptJSONObject.optString("code", ""));
                            return;
                        } else {
                            lGRedemptionCodeConfirmCallback.onFail(4, com.ss.union.game.sdk.redemptionCode.a.j);
                            return;
                        }
                    }
                    lGRedemptionCodeConfirmCallback.onFail(4, com.ss.union.game.sdk.redemptionCode.a.j);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(JSONObject jSONObject) {
        try {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("code");
            String strOptString = jSONObjectOptJSONObject2.optString("code");
            long jOptLong = jSONObjectOptJSONObject2.optLong(com.ss.union.game.sdk.redemptionCode.a.l);
            String strOptString2 = jSONObjectOptJSONObject2.optString(com.ss.union.game.sdk.redemptionCode.a.m);
            String strA = a(jSONObjectOptJSONObject2.optJSONArray(com.ss.union.game.sdk.redemptionCode.a.n));
            long jOptLong2 = jSONObjectOptJSONObject2.optLong(com.ss.union.game.sdk.redemptionCode.a.r);
            long jOptLong3 = jSONObjectOptJSONObject2.optLong(com.ss.union.game.sdk.redemptionCode.a.s);
            long jOptLong4 = jSONObjectOptJSONObject2.optLong(com.ss.union.game.sdk.redemptionCode.a.t);
            String strOptString3 = jSONObjectOptJSONObject.optString("sign");
            String strA2 = a(LGAccountDataUtil.getLoginToken(), strOptString, jOptLong3, jOptLong, strOptString2, jOptLong2, jOptLong4, strA);
            com.ss.union.game.sdk.c.a.g("本地sign是：" + strA2 + ",服务端sign是：" + strOptString3);
            return TextUtils.equals(strOptString3.toLowerCase(), strA2.toLowerCase());
        } catch (Throwable unused) {
            return false;
        }
    }

    private static String a(String str, String str2, long j, long j2, String str3, long j3, long j4, String str4) {
        com.ss.union.game.sdk.c.a.g("tools是： " + str4);
        StringBuilder sb = new StringBuilder();
        sb.append("code");
        sb.append("=");
        sb.append(a(str2));
        sb.append("&");
        sb.append(com.ss.union.game.sdk.redemptionCode.a.s);
        sb.append("=");
        sb.append(a(j + ""));
        sb.append("&");
        sb.append(com.ss.union.game.sdk.redemptionCode.a.l);
        sb.append("=");
        sb.append(a(j2 + ""));
        sb.append("&");
        sb.append(com.ss.union.game.sdk.redemptionCode.a.m);
        sb.append("=");
        sb.append(a(str3 + ""));
        sb.append("&");
        sb.append(com.ss.union.game.sdk.redemptionCode.a.r);
        sb.append("=");
        sb.append(a(j3 + ""));
        sb.append("&");
        sb.append(com.ss.union.game.sdk.redemptionCode.a.t);
        sb.append("=");
        sb.append(a(j4 + ""));
        sb.append("&");
        sb.append(com.ss.union.game.sdk.redemptionCode.a.n);
        sb.append("=");
        sb.append(a(str4 + ""));
        String str5 = str + "light__game__sdk";
        com.ss.union.game.sdk.c.a.g("content是： " + sb.toString());
        try {
            return HMacMD5.hmacMD5(str5.getBytes(), sb.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            String strOptString = jSONObjectOptJSONObject.optString("name", "");
            String strOptString2 = jSONObjectOptJSONObject.optString(com.ss.union.game.sdk.redemptionCode.a.p, "");
            long jOptLong = jSONObjectOptJSONObject.optLong(com.ss.union.game.sdk.redemptionCode.a.q, 0L);
            sb.append(com.ss.union.game.sdk.redemptionCode.a.q);
            sb.append("=");
            sb.append(a(jOptLong + ""));
            sb.append("&");
            sb.append(com.ss.union.game.sdk.redemptionCode.a.p);
            sb.append("=");
            sb.append(a(strOptString2 + ""));
            sb.append("&");
            sb.append("name");
            sb.append("=");
            sb.append(a(strOptString + ""));
            sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return Uri.encode(str);
        } catch (Throwable unused) {
            return str;
        }
    }
}
