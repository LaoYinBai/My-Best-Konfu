package com.ss.union.game.sdk.vcenter.account.a;

import com.bytedance.applog.AppLog;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.constant.LGUris;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.vcenter.account.callback.c;
import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class a implements c {
    private static String a = LGUris.path("/game_sdk/light_game/galaxy/authorized_login");
    private static String b = LGUris.path("/game_sdk/light_game/galaxy/authorized_bind");

    private String a(int i) {
        if (i == 2) {
            return b;
        }
        return a;
    }

    public final void a(com.ss.union.game.sdk.vcenter.b.b bVar, final int i, final c cVar) {
        User currentUserLocal;
        try {
            ACoreRequestPost aCoreRequestPostPost = CoreNetClient.post(a(i));
            aCoreRequestPostPost.param("app_id", AppIdManager.lgAppID());
            aCoreRequestPostPost.param(com.umeng.message.common.a.u, GlobalApplicationUtils.getContext().getPackageName());
            aCoreRequestPostPost.param("device_id", AppLog.getDid());
            aCoreRequestPostPost.param(TransactionDetailsUtilities.RECEIPT, bVar.b());
            if (i == 2 && (currentUserLocal = LGAccountDataUtil.getCurrentUserLocal()) != null) {
                aCoreRequestPostPost.param("game_open_id", currentUserLocal.open_id);
                aCoreRequestPostPost.param("game_token", currentUserLocal.token);
            }
            aCoreRequestPostPost.api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.vcenter.account.a.a.1
                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                    a.this.a(i, coreNetResponse.code(), "请求失败: " + coreNetResponse.message(), "请求失败: " + coreNetResponse.message(), cVar);
                }

                /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
                /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
                @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void onNetSuccess(com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost r9, com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse<org.json.JSONObject, com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost> r10) {
                    /*
                        r8 = this;
                        java.lang.String r9 = "请求成功，解析成user=null"
                        java.lang.String r0 = r10.httpBody
                        boolean r0 = android.text.TextUtils.isEmpty(r0)
                        r1 = 0
                        r2 = -1
                        if (r0 != 0) goto L54
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L4d
                        java.lang.String r3 = r10.httpBody     // Catch: java.lang.Exception -> L4d
                        r0.<init>(r3)     // Catch: java.lang.Exception -> L4d
                        java.lang.String r3 = "message"
                        java.lang.String r3 = r0.getString(r3)     // Catch: java.lang.Exception -> L4d
                        java.lang.String r4 = "success"
                        boolean r3 = r4.equals(r3)     // Catch: java.lang.Exception -> L4d
                        if (r3 == 0) goto L3a
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L4d
                        java.lang.String r10 = r10.httpBody     // Catch: java.lang.Exception -> L4d
                        r0.<init>(r10)     // Catch: java.lang.Exception -> L4d
                        java.lang.String r10 = "data"
                        org.json.JSONObject r10 = r0.optJSONObject(r10)     // Catch: java.lang.Exception -> L4d
                        java.lang.Object r10 = java.util.Objects.requireNonNull(r10)     // Catch: java.lang.Exception -> L4d
                        org.json.JSONObject r10 = (org.json.JSONObject) r10     // Catch: java.lang.Exception -> L4d
                        com.ss.union.game.sdk.core.base.account.model.User r10 = com.ss.union.game.sdk.core.base.account.model.User.parseUser(r10)     // Catch: java.lang.Exception -> L4d
                        r1 = r10
                        goto L51
                    L3a:
                        java.lang.String r10 = "error_code"
                        int r10 = r0.optInt(r10)     // Catch: java.lang.Exception -> L4d
                        java.lang.String r2 = "error_msg"
                        java.lang.String r0 = r0.optString(r2)     // Catch: java.lang.Exception -> L49
                        r2 = r10
                        r9 = r0
                        goto L51
                    L49:
                        r0 = move-exception
                        r2 = r10
                        r10 = r0
                        goto L4e
                    L4d:
                        r10 = move-exception
                    L4e:
                        r10.printStackTrace()
                    L51:
                        r6 = r9
                        r4 = r2
                        goto L56
                    L54:
                        r6 = r9
                        r4 = -1
                    L56:
                        if (r1 != 0) goto L63
                        com.ss.union.game.sdk.vcenter.account.a.a r2 = com.ss.union.game.sdk.vcenter.account.a.a.this
                        int r3 = r2
                        com.ss.union.game.sdk.vcenter.account.callback.c r7 = r3
                        r5 = r6
                        com.ss.union.game.sdk.vcenter.account.a.a.a(r2, r3, r4, r5, r6, r7)
                        goto L6c
                    L63:
                        com.ss.union.game.sdk.vcenter.account.a.a r9 = com.ss.union.game.sdk.vcenter.account.a.a.this
                        int r10 = r2
                        com.ss.union.game.sdk.vcenter.account.callback.c r0 = r3
                        com.ss.union.game.sdk.vcenter.account.a.a.a(r9, r10, r1, r0)
                    L6c:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.vcenter.account.a.a.AnonymousClass1.onNetSuccess(com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost, com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse):void");
                }
            });
        } catch (Exception e) {
            VLog.Account.d("执行网络请求错误: " + e.getMessage());
            a(i, -1, "请求执行失败: " + e.getMessage(), "请求执行失败: " + e.getMessage(), cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final int i2, final String str, final String str2, final c cVar) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.a.a.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    cVar.a(i, i2, str, str2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                a.this.a(i, i2, str, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i, final User user, final c cVar) {
        MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.vcenter.account.a.a.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    cVar.a(i, user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                a.this.a(i, user);
            }
        });
    }
}
