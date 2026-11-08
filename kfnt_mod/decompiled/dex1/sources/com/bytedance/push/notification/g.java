package com.bytedance.push.notification;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.push.PushBody;
import com.bytedance.push.c.l;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g implements com.bytedance.push.c.g {

    /* renamed from: a, reason: collision with root package name */
    private final h f4898a;

    /* renamed from: b, reason: collision with root package name */
    private final com.bytedance.push.c.d f4899b;

    /* renamed from: c, reason: collision with root package name */
    private final l f4900c;
    private final com.bytedance.push.d f;
    private final List<Long> e = new ArrayList();
    private final d d = new d();

    public g(com.bytedance.push.d dVar) {
        this.f4898a = dVar.m;
        this.f4899b = dVar.l;
        this.f4900c = dVar.s;
        this.f = dVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [android.app.Application, android.content.Context] */
    /* JADX WARN: Type inference failed for: r7v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v19, types: [com.bytedance.push.h.e, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r7v20, types: [com.bytedance.push.notification.h] */
    @Override // com.bytedance.push.c.g
    public void a(String str, int i, String str2) throws Throwable {
        PushBody pushBody;
        PushBody pushBody2 = null;
        PushBody pushBody3 = null;
        boolean z = false;
        z = false;
        boolean z2 = false;
        z = false;
        z = false;
        boolean z3 = false;
        try {
            try {
                pushBody = new PushBody(new JSONObject(str));
                try {
                    if (!pushBody.checkValid()) {
                        com.bytedance.push.i.l().b("Show", "PushBody error : " + pushBody);
                    }
                    boolean zA = a(pushBody);
                    try {
                        if (this.f4898a != null && !zA) {
                            ?? r7 = this.f4898a;
                            ?? A = com.ss.android.message.a.a();
                            r7.a(A, i, pushBody);
                            z2 = A;
                        }
                        ?? IsEmpty = TextUtils.isEmpty(pushBody.targetSecUid);
                        boolean z4 = z2;
                        PushBody pushBody4 = IsEmpty;
                        if (IsEmpty == 0) {
                            String str3 = pushBody.targetSecUid;
                            String strA = a();
                            ?? Equals = TextUtils.equals(str3, strA);
                            z4 = strA;
                            pushBody4 = Equals;
                            if (Equals == 0) {
                                ?? eVar = new com.bytedance.push.h.e(i, pushBody);
                                com.bytedance.common.push.utility.b.b.a(eVar);
                                z4 = strA;
                                pushBody4 = eVar;
                            }
                        }
                        a(i, pushBody, zA);
                        z = z4;
                        pushBody2 = pushBody4;
                    } catch (JSONException e) {
                        z3 = zA;
                        e = e;
                        pushBody3 = pushBody;
                        e.printStackTrace();
                        if (pushBody3 != null && !TextUtils.isEmpty(pushBody3.targetSecUid) && !TextUtils.equals(pushBody3.targetSecUid, a())) {
                            com.bytedance.common.push.utility.b.b.a(new com.bytedance.push.h.e(i, pushBody3));
                        }
                        a(i, pushBody3, z3);
                        z = z3;
                        pushBody2 = pushBody3;
                    } catch (Throwable th) {
                        z = zA;
                        th = th;
                        if (pushBody != null && !TextUtils.isEmpty(pushBody.targetSecUid) && !TextUtils.equals(pushBody.targetSecUid, a())) {
                            com.bytedance.common.push.utility.b.b.a(new com.bytedance.push.h.e(i, pushBody));
                        }
                        a(i, pushBody, z);
                        throw th;
                    }
                } catch (JSONException e2) {
                    e = e2;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                pushBody = pushBody2;
            }
        } catch (JSONException e3) {
            e = e3;
        }
    }

    private void a(final int i, final PushBody pushBody, final boolean z) {
        Runnable runnable = new Runnable() { // from class: com.bytedance.push.notification.g.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                if (pushBody != null) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("rule_id", pushBody.id);
                        jSONObject.put("sender", i);
                        jSONObject.put("push_sdk_version", String.valueOf(30200));
                        jSONObject.put("push_sdk_version_name", "3.2.0-honor");
                        jSONObject.put("ttpush_sec_target_uid", pushBody.targetSecUid);
                        jSONObject.put("local_sec_uid", g.this.a());
                        jSONObject.put("is_self", g.this.a(pushBody.targetSecUid) ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
                        jSONObject.put("client_time", System.currentTimeMillis());
                        jSONObject.put("real_filter", z ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
                        if (!TextUtils.isEmpty(pushBody.groupId)) {
                            jSONObject.put("ttpush_group_id", pushBody.groupId);
                        }
                        g.this.f4899b.a("push_show_ug", jSONObject);
                        com.bytedance.push.i.l().a("Show", "push_show_ug:" + jSONObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        if (Looper.getMainLooper() == Looper.myLooper()) {
            com.bytedance.common.push.utility.b.b.a(runnable);
        } else {
            runnable.run();
        }
    }

    private boolean a(PushBody pushBody) {
        if (pushBody == null) {
            com.bytedance.push.utils.e.a("Show", "PushBody is null,filter");
            return true;
        }
        if (TextUtils.isEmpty(pushBody.targetSecUid)) {
            return false;
        }
        com.bytedance.push.c.b bVar = com.bytedance.push.i.j().d().x;
        if (bVar != null) {
            return pushBody.needFilterMsgByUid && !TextUtils.equals(bVar.a(), pushBody.targetSecUid);
        }
        com.bytedance.push.utils.e.a("Show", "account service is null，not filter");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        com.bytedance.push.c.b bVar = com.bytedance.push.i.j().d().x;
        if (bVar == null || TextUtils.isEmpty(str)) {
            com.bytedance.push.utils.e.a("Show", "account service is null，hasLoggedInBefore return false");
            return false;
        }
        List<String> listB = bVar.b();
        return listB != null && listB.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a() {
        com.bytedance.push.c.b bVar = com.bytedance.push.i.j().d().x;
        if (bVar == null) {
            return "";
        }
        String strA = bVar.a();
        return !TextUtils.isEmpty(strA) ? strA : "";
    }

    @Override // com.bytedance.push.c.g
    public void a(Context context, String str, int i) {
        if (this.d.a(str, i)) {
            com.bytedance.push.i.l().b("Click", "onClickMsg#重复click:" + str + ", from = " + i);
            return;
        }
        try {
            PushBody pushBody = new PushBody(new JSONObject(str));
            JSONObject jSONObjectA = this.f4900c != null ? this.f4900c.a(context, i, pushBody) : null;
            if (this.f.y) {
                return;
            }
            a(context, pushBody.id, pushBody.groupId, pushBody.targetSecUid, true, jSONObjectA);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, final long j, final String str, final String str2, final boolean z, final JSONObject jSONObject) {
        Runnable runnable = new Runnable() { // from class: com.bytedance.push.notification.g.2
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                JSONObject jSONObject2 = jSONObject;
                if (jSONObject2 == null) {
                    try {
                        jSONObject2 = new JSONObject();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (z) {
                    jSONObject2.put("click_position", "notify");
                } else {
                    jSONObject2.put("click_position", "alert");
                }
                jSONObject2.put("ttpush_sec_target_uid", str2);
                jSONObject2.put("local_sec_uid", g.this.a());
                jSONObject2.put("client_time", System.currentTimeMillis());
                jSONObject2.put("real_filter", OnekeyLoginConstants.CU_RESULT_SUCCESS);
                jSONObject2.put("rule_id", j);
                jSONObject2.put("push_sdk_version", String.valueOf(30200));
                jSONObject2.put("push_sdk_version_name", "3.2.0-honor");
                if (!TextUtils.isEmpty(str)) {
                    jSONObject2.put("ttpush_group_id", str);
                }
                synchronized (g.this.e) {
                    if (!g.this.e.contains(Long.valueOf(j))) {
                        g.this.e.add(Long.valueOf(j));
                        g.this.f4899b.a("push_click", jSONObject2);
                        com.bytedance.push.i.l().a("Click", "push_click:" + jSONObject2);
                        if (j <= 0) {
                            com.bytedance.push.i.l().b("Click", "error ruleId:" + j);
                            return;
                        }
                        return;
                    }
                    com.bytedance.push.i.l().b("Click", "重复click:" + jSONObject2);
                }
            }
        };
        if (Looper.getMainLooper() == Looper.myLooper()) {
            com.bytedance.common.push.utility.b.b.a(runnable);
        } else {
            runnable.run();
        }
    }
}
