package com.ss.union.game.sdk.core.vapp.b;

import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.vapp.a.d;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static void a(String str, String str2, String str3, com.ss.union.game.sdk.core.vapp.b.a.a<com.ss.union.game.sdk.core.vapp.a.a> aVar) {
        CoreNetClient.post(b.a).param("bind_token", str).param("game_account_type", "DEVICE").param("game_app_id", AppIdManager.lgAppID()).param("device_id", str2).param("iid", str3).json(a(aVar));
    }

    public static void b(String str, String str2, String str3, com.ss.union.game.sdk.core.vapp.b.a.a<com.ss.union.game.sdk.core.vapp.a.a> aVar) {
        CoreNetClient.post(b.a).param("bind_token", str).param("game_account_type", "ACCOUNT").param("game_app_id", AppIdManager.lgAppID()).param("game_open_id", str2).param("game_login_token", str3).json(a(aVar));
    }

    public static void c(String str, String str2, String str3, com.ss.union.game.sdk.core.vapp.b.a.a<com.ss.union.game.sdk.core.vapp.a.b> aVar) {
        CoreNetClient.post(b.c).param("game_account_type", "DEVICE").param("game_app_id", AppIdManager.lgAppID()).param("device_id", str).param("iid", str2).param("tool_id", str3).json(a(aVar));
    }

    public static void d(String str, String str2, String str3, com.ss.union.game.sdk.core.vapp.b.a.a<com.ss.union.game.sdk.core.vapp.a.b> aVar) {
        CoreNetClient.post(b.c).param("game_account_type", "ACCOUNT").param("game_app_id", AppIdManager.lgAppID()).param("game_open_id", str).param("game_login_token", str2).param("tool_id", str3).json(a(aVar));
    }

    public static void a(String str, String str2, com.ss.union.game.sdk.core.vapp.b.a.a<d> aVar) {
        CoreNetClient.post(b.b).param("game_account_type", "DEVICE").param("game_app_id", AppIdManager.lgAppID()).param("device_id", str).param("iid", str2).json(a(aVar));
    }

    public static void b(String str, String str2, com.ss.union.game.sdk.core.vapp.b.a.a<d> aVar) {
        CoreNetClient.post(b.b).param("game_account_type", "ACCOUNT").param("game_app_id", AppIdManager.lgAppID()).param("game_open_id", str).param("game_login_token", str2).json(a(aVar));
    }

    public static <T> ICoreNetCallback<JSONObject, ACoreRequestPost> a(final com.ss.union.game.sdk.core.vapp.b.a.a<T> aVar) {
        return new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.vapp.b.a.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (aVar != null) {
                    aVar.a(coreNetResponse.code(), coreNetResponse.message(), null);
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (aVar != null) {
                    int iOptInt = coreNetResponse.data.optInt("code");
                    String strOptString = coreNetResponse.data.optString("message");
                    if (iOptInt != 0) {
                        aVar.a(iOptInt, strOptString, coreNetResponse.data.optJSONObject("data"));
                        return;
                    }
                    JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                    if (jSONObjectOptJSONObject != null) {
                        aVar.a((com.ss.union.game.sdk.core.vapp.b.a.a) aVar.a(jSONObjectOptJSONObject));
                    } else {
                        aVar.a(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC, null);
                    }
                }
            }
        };
    }

    /* renamed from: com.ss.union.game.sdk.core.vapp.b.a$a, reason: collision with other inner class name */
    public static class C0093a {
        private static int a = 60001;
        private static int b = 60002;

        public static boolean a(int i) {
            return i == b || i == a;
        }

        public static boolean b(int i) {
            return i == b;
        }

        public static boolean c(int i) {
            return i == a;
        }
    }
}
