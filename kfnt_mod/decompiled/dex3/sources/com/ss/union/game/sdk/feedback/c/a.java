package com.ss.union.game.sdk.feedback.c;

import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestGet;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.result.LGSDKResult;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.feedback.module.LGFeedbackStatus;
import com.ss.union.game.sdk.feedback.module.d;
import com.ss.union.game.sdk.feedback.module.e;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(final b<e> bVar) {
        ((ACoreRequestGet) CoreNetClient.get(c.a).urlParam("app_id", AppIdManager.lgAppID())).api(new ICoreNetCallback<JSONObject, ACoreRequestGet>() { // from class: com.ss.union.game.sdk.feedback.c.a.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) {
                super.onNetError(aCoreRequestGet, coreNetResponse);
                if (bVar != null) {
                    bVar.a(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestGet aCoreRequestGet, CoreNetResponse<JSONObject, ACoreRequestGet> coreNetResponse) {
                super.onNetSuccess(aCoreRequestGet, coreNetResponse);
                if (bVar != null) {
                    JSONArray jSONArrayOptJSONArray = coreNetResponse.data.optJSONArray("data");
                    if (jSONArrayOptJSONArray != null) {
                        e eVar = new e();
                        eVar.a(jSONArrayOptJSONArray);
                        bVar.a(eVar);
                        return;
                    }
                    bVar.a(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                }
            }
        });
    }

    public static void a(String str, String str2, String str3, String str4, final b bVar) {
        ACoreRequestPost aCoreRequestPostParam = CoreNetClient.post(c.b).param("app_id", AppIdManager.lgAppID()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).param("feedback_type_name", str).param(com.umeng.commonsdk.framework.c.a, str2);
        if (str3 == null) {
            str3 = "";
        }
        ACoreRequestPost aCoreRequestPostParam2 = aCoreRequestPostParam.param(com.ss.union.game.sdk.account.a.a.x, str3);
        if (str4 == null) {
            str4 = "";
        }
        aCoreRequestPostParam2.param("images", str4).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.feedback.c.a.2
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (bVar != null) {
                    bVar.a(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (bVar != null) {
                    bVar.a(null);
                }
            }
        });
    }

    public static void b(final b<LGFeedbackStatus> bVar) {
        if (d(bVar)) {
            return;
        }
        CoreNetClient.post(c.d).param("app_id", AppIdManager.lgAppID()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.feedback.c.a.3
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (bVar != null) {
                    bVar.a(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (!"success".equals(coreNetResponse.data.optString("message"))) {
                    if (bVar != null) {
                        bVar.a(-202, "系统错误，请稍后再试");
                        return;
                    }
                    return;
                }
                try {
                    JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                    LGFeedbackStatus lGFeedbackStatus = new LGFeedbackStatus(jSONObjectOptJSONObject.optInt("unread_thread_count"), jSONObjectOptJSONObject.optInt("unread_reply_count"));
                    if (bVar != null) {
                        bVar.a(lGFeedbackStatus);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (bVar != null) {
                        bVar.a(-1, e.getMessage());
                    }
                }
            }
        });
    }

    public static void a(List<File> list, final b<List<com.ss.union.game.sdk.feedback.module.b>> bVar) {
        if (d(bVar)) {
            return;
        }
        CoreNetClient.post(c.c).param("app_id", AppIdManager.lgAppID()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).param("images", list).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.feedback.c.a.4
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (bVar != null) {
                    bVar.a(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (!"success".equals(coreNetResponse.data.optString("message"))) {
                    if (bVar != null) {
                        bVar.a(-202, "系统错误，请稍后再试");
                        return;
                    }
                    return;
                }
                ArrayList arrayList = new ArrayList();
                try {
                    JSONArray jSONArrayOptJSONArray = coreNetResponse.data.optJSONArray("data");
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                        com.ss.union.game.sdk.feedback.module.b bVar2 = new com.ss.union.game.sdk.feedback.module.b();
                        bVar2.a(jSONObjectOptJSONObject);
                        arrayList.add(bVar2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                bVar.a(arrayList);
            }
        });
    }

    public static void c(final b<List<com.ss.union.game.sdk.feedback.module.c>> bVar) {
        if (d(bVar)) {
            return;
        }
        CoreNetClient.post(c.e).param("app_id", AppIdManager.lgAppID()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.feedback.c.a.5
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (bVar != null) {
                    bVar.a(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (!"success".equals(coreNetResponse.data.optString("message"))) {
                    if (bVar != null) {
                        bVar.a(-202, "系统错误，请稍后再试");
                        return;
                    }
                    return;
                }
                try {
                    List<com.ss.union.game.sdk.feedback.module.c> listA = com.ss.union.game.sdk.feedback.module.c.a(coreNetResponse.data.optJSONObject("data").optJSONArray("threads"));
                    if (bVar != null) {
                        bVar.a(listA);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (bVar != null) {
                        bVar.a(-1, e.getMessage());
                    }
                }
            }
        });
    }

    public static void a(long j, final b<d> bVar) {
        if (d(bVar)) {
            return;
        }
        CoreNetClient.post(c.f).param("app_id", AppIdManager.lgAppID()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).param("thread_id", String.valueOf(j)).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.feedback.c.a.6
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (bVar != null) {
                    bVar.a(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) throws JSONException {
                if (!"success".equals(coreNetResponse.data.optString("message"))) {
                    if (bVar != null) {
                        bVar.a(-202, "系统错误，请稍后再试");
                    }
                } else {
                    d dVar = new d();
                    dVar.a(coreNetResponse.data.optString("data"));
                    bVar.a(dVar);
                }
            }
        });
    }

    public static void a(long j, boolean z, final b<Boolean> bVar) {
        if (d(bVar)) {
            return;
        }
        CoreNetClient.post(c.g).param("app_id", AppIdManager.lgAppID()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).param("thread_id", String.valueOf(j)).param("is_helpful", String.valueOf(z)).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.feedback.c.a.7
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (bVar != null) {
                    bVar.a(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (!"success".equals(coreNetResponse.data.optString("message"))) {
                    if (bVar != null) {
                        bVar.a(-202, "系统错误，请稍后再试");
                    }
                } else if (bVar != null) {
                    bVar.a(true);
                }
            }
        });
    }

    public static void a(long j, String str, String[] strArr, final b<Boolean> bVar) {
        if (d(bVar)) {
            return;
        }
        ACoreRequestPost aCoreRequestPostParam = CoreNetClient.post(c.h).param("app_id", AppIdManager.lgAppID()).param(User.KEY_OPEN_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("login_token", LGAccountDataUtil.getLoginToken()).param("thread_id", String.valueOf(j)).param(com.umeng.commonsdk.framework.c.a, str);
        if (strArr != null) {
            aCoreRequestPostParam.param("images", a(strArr));
        }
        aCoreRequestPostParam.api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.feedback.c.a.8
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (bVar != null) {
                    bVar.a(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                if (!"success".equals(coreNetResponse.data.optString("message"))) {
                    if (bVar != null) {
                        bVar.a(-202, "系统错误，请稍后再试");
                    }
                } else if (bVar != null) {
                    bVar.a(true);
                }
            }
        });
    }

    private static String a(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
            if (i < strArr.length - 1) {
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        return sb.toString();
    }

    protected static <T> boolean d(b<T> bVar) {
        return e(bVar) || f(bVar);
    }

    protected static <T> boolean e(b<T> bVar) {
        if (NetworkUtils.isNetworkAvailable()) {
            return false;
        }
        bVar.a(LGSDKResult.ERRNO_SDK_NO_NETWORK, LGSDKResult.ERROR_MESSAGE_SDK_NO_NETWORK);
        return true;
    }

    protected static <T> boolean f(b<T> bVar) {
        if (LGSDKCore.isSdkInitSuccess()) {
            return false;
        }
        bVar.a(-204, "SDK还未初始化完成");
        return true;
    }
}
