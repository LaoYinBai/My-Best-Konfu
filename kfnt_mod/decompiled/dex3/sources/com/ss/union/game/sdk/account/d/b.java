package com.ss.union.game.sdk.account.d;

import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.impl.BDAccountDelegate;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.union.game.sdk.account.callback.ICloseAccountCallback;
import com.ss.union.game.sdk.account.callback.IQueryAccountInfoCallback;
import com.ss.union.game.sdk.account.callback.IUnbindAccountCallback;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.deviceInfo.common.AppInfoHelper;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static String a(int i) {
        return i == 2 ? "BIND" : "NORMAL";
    }

    public static void a(c cVar) {
        CoreNetClient.post(a.b).param("app_id", AppIdManager.lgAppID()).param("bd_did", AppLogManager.getInstance().getDid()).api(b(cVar));
    }

    public static void a(String str, c cVar) {
        CoreNetClient.post(a.c).param("login_token", str).param("bd_did", AppLogManager.getInstance().getDid()).api(b(cVar));
    }

    public static void a(String str, final int i, final c cVar) {
        BDAccountDelegate.createPlatformAPI(GlobalApplicationUtils.getContext()).ssoWithAuthCodeLogin(AppIdManager.dyPlatformAppId(), BDAccountPlatformEntity.PLAT_NAME_DOUYIN_NEW, str, 0L, (Map) null, new AbsApiCall<UserApiResponse>() { // from class: com.ss.union.game.sdk.account.d.b.1
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(UserApiResponse userApiResponse) {
                if (userApiResponse != null && userApiResponse.success) {
                    b.a(i, LGAccountConstant.LoginType.LOGIN_TYPE_DY, cVar);
                } else if (cVar != null) {
                    cVar.a(userApiResponse.error, userApiResponse.errorMsg);
                }
            }
        });
    }

    public static void a(int i, LGAccountConstant.LoginType loginType, c cVar) {
        ACoreRequestPost aCoreRequestPostPost = CoreNetClient.post(a.d);
        aCoreRequestPostPost.param().putAll(a(a(i), loginType));
        aCoreRequestPostPost.api(b(cVar));
    }

    public static void a(int i, c cVar) {
        Map<String, String> mapA = a(a(i), LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE);
        ACoreRequestPost aCoreRequestPostPost = CoreNetClient.post(a.d);
        aCoreRequestPostPost.param().putAll(mapA);
        aCoreRequestPostPost.api(b(cVar));
    }

    public static void a(String str, String str2, int i, c cVar) {
        ACoreRequestPost aCoreRequestPostParam = CoreNetClient.post(a.a).param("app_id", AppIdManager.lgAppID()).param("device_id", AppLogManager.getInstance().getDid()).param("telephone", str).param("code", str2).param("type", a(i));
        User currentUserLocal = LGAccountDataUtil.getCurrentUserLocal();
        if (currentUserLocal != null) {
            aCoreRequestPostParam.param(User.KEY_OPEN_ID, currentUserLocal.open_id);
            aCoreRequestPostParam.param("token", currentUserLocal.token);
        }
        aCoreRequestPostParam.api(b(cVar));
    }

    public static void a(final IQueryAccountInfoCallback iQueryAccountInfoCallback) {
        CoreNetClient.post(a.e).param("app_id", AppIdManager.lgAppID()).param(com.umeng.message.common.a.u, DeviceUtils.getAppPackageName()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.account.d.b.2
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (iQueryAccountInfoCallback != null) {
                    iQueryAccountInfoCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) throws JSONException {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (iQueryAccountInfoCallback != null) {
                    iQueryAccountInfoCallback.onQuerySuccess(AppInfoHelper.insertAppInfoToJsonObject(coreNetResponse.data.optJSONObject("data")).toString());
                }
            }
        });
    }

    public static void a(final IUnbindAccountCallback iUnbindAccountCallback) {
        com.ss.union.game.sdk.account.a.a.a();
        CoreNetClient.post(a.f).param("app_id", AppIdManager.lgAppID()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.account.d.b.3
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.account.a.a.c();
                if (iUnbindAccountCallback != null) {
                    iUnbindAccountCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.account.a.a.b();
                if (iUnbindAccountCallback != null) {
                    JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                    if (jSONObjectOptJSONObject != null) {
                        User user = User.parseUser(jSONObjectOptJSONObject);
                        LGAccountDataUtil.saveUserData(user);
                        iUnbindAccountCallback.onUnbindSuccess(user);
                        return;
                    }
                    iUnbindAccountCallback.onFail(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                }
            }
        });
    }

    public static void a(final ICloseAccountCallback iCloseAccountCallback) {
        com.ss.union.game.sdk.account.a.a.c(LGAccountDataUtil.getLoginType());
        CoreNetClient.post(a.g).param("app_id", AppIdManager.lgAppID()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.account.d.b.4
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.account.a.a.e(LGAccountDataUtil.getLoginType());
                if (iCloseAccountCallback != null) {
                    iCloseAccountCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.account.a.a.d(LGAccountDataUtil.getLoginType());
                LGAccountDataUtil.clearLocalUserData();
                if (iCloseAccountCallback != null) {
                    iCloseAccountCallback.onSuccess(coreNetResponse.data.optString("message"));
                }
            }
        });
    }

    public static ICoreNetCallback<JSONObject, ACoreRequestPost> b(final c cVar) {
        return new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.account.d.b.5
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (cVar != null) {
                    cVar.a(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (cVar != null) {
                    JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                    if (jSONObjectOptJSONObject != null) {
                        cVar.a(User.parseUser(jSONObjectOptJSONObject));
                    } else {
                        cVar.a(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                    }
                }
            }
        };
    }

    public static Map<String, String> a(String str, LGAccountConstant.LoginType loginType) {
        String str2 = "";
        String str3 = "";
        User currentUserLocal = LGAccountDataUtil.getCurrentUserLocal();
        if (currentUserLocal != null) {
            str2 = currentUserLocal.token;
            str3 = currentUserLocal.open_id;
        }
        HashMap map = new HashMap();
        map.put("type", str);
        map.put(User.KEY_LOGIN_TYPE, loginType.getName());
        map.put("app_id", AppIdManager.lgAppID());
        map.put("device_id", AppLogManager.getInstance().getDid());
        map.put("token", str2);
        map.put(User.KEY_OPEN_ID, str3);
        map.put("need_ohayoo_logo", String.valueOf(ConfigManager.AppConfig.appIsOhayooProduct()));
        map.put("need_ohayoo_nickname", String.valueOf(ConfigManager.LoginConfig.isUseNickNameSystem()));
        return map;
    }
}
