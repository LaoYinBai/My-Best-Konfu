package com.mbridge.msdk.videocommon.d;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.videocommon.b.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RewardSettingManager.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static b e;
    private Object b = new Object();
    private boolean d = false;
    private static Map<String, c> c = new HashMap();
    public static a a = null;

    private b() {
    }

    public static b a() {
        if (e == null) {
            synchronized (b.class) {
                if (e == null) {
                    e = new b();
                }
            }
        }
        return e;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.mbridge.msdk.videocommon.d.a b() {
        /*
            r8 = this;
            com.mbridge.msdk.videocommon.d.a r0 = com.mbridge.msdk.videocommon.d.b.a
            if (r0 != 0) goto La6
            com.mbridge.msdk.foundation.a.a.a r0 = com.mbridge.msdk.foundation.a.a.a.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "reward_"
            r1.append(r2)
            com.mbridge.msdk.foundation.controller.a r2 = com.mbridge.msdk.foundation.controller.a.f()
            java.lang.String r2 = r2.k()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = r0.b(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L8e
            com.mbridge.msdk.videocommon.d.a r0 = com.mbridge.msdk.videocommon.d.a.a(r0)
            if (r0 == 0) goto L6b
            long r1 = r0.b()
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r0.g()
            long r5 = r5 + r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L6b
            boolean r1 = com.mbridge.msdk.foundation.tools.s.c
            if (r1 == 0) goto L69
            java.lang.String r1 = "RewardSettingManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = "app setting nexttime is not ready  [settingNextRequestTime= "
            r2.append(r7)
            r2.append(r5)
            java.lang.String r5 = " currentTime = "
            r2.append(r5)
            r2.append(r3)
            java.lang.String r3 = "]"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.mbridge.msdk.foundation.tools.s.b(r1, r2)
        L69:
            r1 = 0
            goto L73
        L6b:
            java.lang.String r1 = "RewardSettingManager"
            java.lang.String r2 = "app setting timeout or not exists"
            com.mbridge.msdk.foundation.tools.s.b(r1, r2)
            r1 = 1
        L73:
            if (r1 != 0) goto L76
            return r0
        L76:
            com.mbridge.msdk.foundation.controller.a r0 = com.mbridge.msdk.foundation.controller.a.f()
            java.lang.String r0 = r0.k()
            com.mbridge.msdk.foundation.controller.a r1 = com.mbridge.msdk.foundation.controller.a.f()
            java.lang.String r1 = r1.l()
            r8.b(r0, r1)
            com.mbridge.msdk.videocommon.d.a r0 = r8.c()
            return r0
        L8e:
            com.mbridge.msdk.foundation.controller.a r0 = com.mbridge.msdk.foundation.controller.a.f()
            java.lang.String r0 = r0.k()
            com.mbridge.msdk.foundation.controller.a r1 = com.mbridge.msdk.foundation.controller.a.f()
            java.lang.String r1 = r1.l()
            r8.b(r0, r1)
            com.mbridge.msdk.videocommon.d.a r0 = r8.c()
            return r0
        La6:
            com.mbridge.msdk.videocommon.d.a r0 = com.mbridge.msdk.videocommon.d.b.a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.videocommon.d.b.b():com.mbridge.msdk.videocommon.d.a");
    }

    private void b(String str, String str2) {
        if (this.d) {
            return;
        }
        this.d = true;
        new com.mbridge.msdk.videocommon.c.a().a(com.mbridge.msdk.foundation.controller.a.f().j(), str, str2);
    }

    public final void a(String str, String str2, String str3, com.mbridge.msdk.videocommon.c.c cVar) {
        new com.mbridge.msdk.videocommon.c.a().a(com.mbridge.msdk.foundation.controller.a.f().j(), str, str2, str3, cVar);
    }

    public final c a(String str, String str2) {
        synchronized (this.b) {
            String str3 = "reward_" + str + "_" + str2;
            if (c.containsKey(str3)) {
                return c.get(str3);
            }
            c cVarC = c.c(com.mbridge.msdk.foundation.a.a.a.a().b(str3));
            if (a(cVarC)) {
                return null;
            }
            c.put(str3, cVarC);
            return cVarC;
        }
    }

    public final c a(String str, String str2, boolean z) {
        synchronized (this.b) {
            String str3 = "reward_" + str + "_" + str2;
            if (c.containsKey(str3)) {
                return c.get(str3);
            }
            c cVarC = c.c(com.mbridge.msdk.foundation.a.a.a.a().b(str3));
            if (!a(cVarC)) {
                c.put(str3, cVarC);
                return cVarC;
            }
            if (cVarC != null) {
                return cVarC;
            }
            return b(z);
        }
    }

    public final a c() {
        a aVar = new a();
        HashMap map = new HashMap(5);
        map.put("1", 1000);
        map.put("9", 1000);
        map.put("8", 1000);
        HashMap map2 = new HashMap(3);
        map2.put("1", new d("Virtual Item", 1));
        aVar.a(map);
        aVar.b(map2);
        aVar.a(43200L);
        aVar.b(5400L);
        aVar.c(3600L);
        aVar.d(3600L);
        aVar.e(5L);
        aVar.a(1);
        return aVar;
    }

    private final boolean a(c cVar) {
        a aVarB = b();
        if (aVarB == null || cVar == null) {
            return true;
        }
        long jC = aVarB.c();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jW = cVar.w() + jC;
        if (jW <= jCurrentTimeMillis) {
            return true;
        }
        if (!s.c) {
            return false;
        }
        s.b("RewardSettingManager", "unit setting  nexttime is not ready  [settingNextRequestTime= " + jW + " currentTime = " + jCurrentTimeMillis + "]");
        return false;
    }

    public final void a(String str, String str2, String str3) {
        synchronized (this.b) {
            String str4 = "reward_" + str + "_" + str2;
            com.mbridge.msdk.foundation.a.a.a.a().a(str4, str3);
            c.put(str4, c.c(str3));
            if (!TextUtils.isEmpty(c.a)) {
                c(c.a, str2);
            }
        }
    }

    public final void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a().a(com.mbridge.msdk.foundation.controller.a.f().k(), str, false);
        String str2 = c.a;
        if (TextUtils.isEmpty(str2) || !TextUtils.isEmpty(H5DownLoadManager.getInstance().getH5ResAddress(str2))) {
            return;
        }
        c(str2, str);
    }

    private void c(final String str, final String str2) {
        if (TextUtils.isEmpty(str)) {
            com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, str2, 2, "alert url is exception ,url:" + str);
            return;
        }
        H5DownLoadManager.getInstance().downloadH5Res(str, new H5DownLoadManager.ZipDownloadListener() { // from class: com.mbridge.msdk.videocommon.d.b.1
            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onSuccess(String str3) {
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, str2, 1, "");
            }

            @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
            public final void onFailed(String str3, String str4) {
                com.mbridge.msdk.foundation.same.report.d.a(com.mbridge.msdk.foundation.controller.a.f().j(), str, str2, 2, str4);
            }
        });
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public static boolean b(String str) {
        JSONArray jSONArrayOptJSONArray;
        try {
            if (!TextUtils.isEmpty(str) && (jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("unitSetting")) != null) {
                String strOptString = jSONArrayOptJSONArray.optJSONObject(0).optString("unitId");
                if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                    if (!TextUtils.isEmpty(strOptString)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static c b(boolean z) {
        c cVar = new c();
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new com.mbridge.msdk.videocommon.b.b(1, 15, null));
            cVar.a((List<com.mbridge.msdk.videocommon.b.b>) arrayList);
            cVar.n(1);
            cVar.m(1);
            cVar.o(1);
            cVar.s(1);
            cVar.a(1.0d);
            cVar.b(1.0d);
            cVar.t(2);
            cVar.p(2);
            cVar.q(1);
            cVar.r(1);
            cVar.i(3);
            cVar.j(80);
            cVar.k(100);
            cVar.l(0);
            cVar.h(2);
            cVar.f(-1);
            cVar.x(70);
            cVar.e(2);
            if (z) {
                cVar.d(5);
            } else {
                cVar.d(-1);
            }
            cVar.c(0);
            cVar.b(0);
            cVar.g(1);
            cVar.u(1);
            cVar.a(0);
            cVar.w(3);
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            arrayList2.add(4);
            arrayList2.add(6);
            cVar.a(arrayList2);
            cVar.y(1);
            cVar.z(1);
            cVar.A(60);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return cVar;
    }
}
