package com.ss.union.game.sdk.mail.a;

import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestGet;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.util.CheckSignUtils;
import com.ss.union.game.sdk.mail.callback.LGMailAckCallBack;
import com.ss.union.game.sdk.mail.callback.LGMailFetchCallBack;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(final LGMailFetchCallBack lGMailFetchCallBack) {
        String strLgAppID = AppIdManager.lgAppID();
        String str = DeviceUtils.getVersionCode() + "";
        ((ACoreRequestGet) ((ACoreRequestGet) ((ACoreRequestGet) ((ACoreRequestGet) ((ACoreRequestGet) CoreNetClient.get(b.a).urlParam("app_id", strLgAppID)).urlParam("os", "android")).urlParam("version", str)).urlParam(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId())).urlParam("login_token", LGAccountDataUtil.getLoginToken())).api(new ICoreNetCallback<JSONObject, ACoreRequestGet>() { // from class: com.ss.union.game.sdk.mail.a.a.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) {
                super.onNetError(aCoreRequestGet, coreNetResponse);
                if (lGMailFetchCallBack != null) {
                    lGMailFetchCallBack.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) {
                JSONArray jSONArrayOptJSONArray;
                super.onNetSuccess(aCoreRequestGet, coreNetResponse);
                if (lGMailFetchCallBack != null) {
                    try {
                        if (CheckSignUtils.checkSign(coreNetResponse.httpBody, coreNetResponse.headerIgnoreCase(CheckSignUtils.KEY_HTTP_HEADER_SIGN))) {
                            JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                            if (jSONObjectOptJSONObject != null && (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("mails")) != null) {
                                lGMailFetchCallBack.onSuccess(jSONArrayOptJSONArray.toString());
                            } else {
                                lGMailFetchCallBack.onFail(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                            }
                        } else {
                            lGMailFetchCallBack.onFail(2, com.ss.union.game.sdk.mail.a.b);
                        }
                    } catch (Throwable unused) {
                        lGMailFetchCallBack.onFail(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                    }
                }
            }
        });
    }

    public static void a(int[] iArr, final LGMailAckCallBack lGMailAckCallBack) {
        if (iArr == null || iArr.length <= 0) {
            lGMailAckCallBack.onFail(5, com.ss.union.game.sdk.mail.a.d);
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i : iArr) {
            jSONArray.put(i);
        }
        String strLgAppID = AppIdManager.lgAppID();
        CoreNetClient.post(b.b).param("app_id", strLgAppID).param("os", "android").param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).param("mail_ids", jSONArray.toString()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.mail.a.a.2
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (lGMailAckCallBack != null) {
                    lGMailAckCallBack.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (lGMailAckCallBack != null) {
                    try {
                        if (CheckSignUtils.checkSign(coreNetResponse.httpBody, coreNetResponse.headerIgnoreCase(CheckSignUtils.KEY_HTTP_HEADER_SIGN))) {
                            lGMailAckCallBack.onSuccess();
                        } else {
                            lGMailAckCallBack.onFail(2, com.ss.union.game.sdk.mail.a.b);
                        }
                    } catch (Throwable unused) {
                        lGMailAckCallBack.onFail(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                    }
                }
            }
        });
    }
}
