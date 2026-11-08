package com.ss.union.game.sdk.nick.c;

import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.callback.CoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static void a(final String str, final CoreNetCallback<String> coreNetCallback) {
        CoreNetClient.post(b.a).param("app_id", AppIdManager.lgAppID()).param(com.umeng.message.common.a.u, GlobalApplicationUtils.getContext().getPackageName()).param("token", LGAccountDataUtil.getLoginToken()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param(User.KEY_NICK_NAME, str).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.nick.c.a.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.nick.a.a("randomNickName error code = " + coreNetResponse.code() + " msg = " + coreNetResponse.message());
                coreNetCallback.onError(coreNetResponse.code(), coreNetResponse.message());
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.nick.a.a("randomNickName success");
                User currentUserLocal = LGAccountDataUtil.getCurrentUserLocal();
                if (currentUserLocal != null) {
                    currentUserLocal.nick_name = str;
                    LGAccountDataUtil.saveUserData(currentUserLocal);
                }
                coreNetCallback.onSuccess(str);
            }
        });
    }

    public static void a(final CoreNetCallback<String> coreNetCallback) {
        CoreNetClient.post(b.b).param("app_id", AppIdManager.lgAppID()).param(com.umeng.message.common.a.u, GlobalApplicationUtils.getContext().getPackageName()).param("token", LGAccountDataUtil.getLoginToken()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.nick.c.a.2
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.nick.a.a("randomNickName error code = " + coreNetResponse.code() + " msg = " + coreNetResponse.message());
                coreNetCallback.onError(coreNetResponse.code(), coreNetResponse.message());
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.nick.a.a("randomNickName success");
                try {
                    coreNetCallback.onSuccess(coreNetResponse.data.optJSONObject("data").optString(User.KEY_NICK_NAME));
                } catch (Throwable unused) {
                    coreNetCallback.onError(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                }
            }
        });
    }
}
