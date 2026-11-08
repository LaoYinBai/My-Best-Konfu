package com.mbridge.msdk.foundation.same.a;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StaticDataPoll.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    public static Map<String, Long> a = new HashMap();
    public static Map<String, List<com.mbridge.msdk.foundation.same.d.a>> b = new HashMap();
    public static Map<String, List<com.mbridge.msdk.foundation.same.d.a>> c = new HashMap();
    public static Map<String, List<com.mbridge.msdk.foundation.same.d.a>> d = new HashMap();
    public static Map<String, List<com.mbridge.msdk.foundation.same.d.a>> e = new HashMap();
    public static Map<String, List<com.mbridge.msdk.foundation.same.d.a>> f = new HashMap();
    public static Map<String, List<com.mbridge.msdk.foundation.same.d.a>> g = new HashMap();
    public static Map<String, List<com.mbridge.msdk.foundation.same.d.a>> h = new HashMap();
    public static Map<String, List<com.mbridge.msdk.foundation.same.d.a>> i = new HashMap();
    public static Map<String, List<com.mbridge.msdk.foundation.same.d.a>> j = new HashMap();
    public static ConcurrentHashMap<String, ConcurrentHashMap<String, com.mbridge.msdk.foundation.entity.c>> k = null;
    public static ConcurrentHashMap<String, CopyOnWriteArrayList<String>> l = null;
    public static ConcurrentHashMap<String, ConcurrentHashMap<String, String>> m = null;
    private static final String n = "d";

    public static void a(String str, CampaignEx campaignEx, String str2) {
        Map<String, List<com.mbridge.msdk.foundation.same.d.a>> mapD = d(str2);
        if (campaignEx == null || mapD == null) {
            return;
        }
        try {
            if (!TextUtils.isEmpty(campaignEx.getId())) {
                com.mbridge.msdk.foundation.same.d.a aVar = new com.mbridge.msdk.foundation.same.d.a(campaignEx.getId(), campaignEx.getRequestId());
                if (mapD.containsKey(str)) {
                    List<com.mbridge.msdk.foundation.same.d.a> list = mapD.get(str);
                    if (list != null) {
                        list.add(aVar);
                    }
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(aVar);
                    mapD.put(str, arrayList);
                }
            }
        } catch (Throwable th) {
            s.b(n, th.getMessage(), th);
        }
    }

    public static void b(String str, CampaignEx campaignEx, String str2) {
        Map<String, List<com.mbridge.msdk.foundation.same.d.a>> mapD = d(str2);
        if (campaignEx == null || mapD == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(campaignEx.getId())) {
                return;
            }
            com.mbridge.msdk.foundation.same.d.a aVar = new com.mbridge.msdk.foundation.same.d.a(campaignEx.getId(), campaignEx.getRequestId());
            if (mapD.containsKey(str)) {
                List<com.mbridge.msdk.foundation.same.d.a> list = mapD.get(str);
                if (list != null && list.size() == 20) {
                    list.remove(0);
                }
                if (list != null) {
                    list.add(aVar);
                    return;
                }
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(aVar);
            mapD.put(str, arrayList);
        } catch (Throwable th) {
            s.b(n, th.getMessage(), th);
        }
    }

    public static String a(String str, String str2) throws JSONException {
        Map<String, List<com.mbridge.msdk.foundation.same.d.a>> mapD;
        List<com.mbridge.msdk.foundation.same.d.a> list;
        JSONArray jSONArray = new JSONArray();
        if (!TextUtils.isEmpty(str2)) {
            mapD = d(str2);
        } else {
            mapD = null;
            if (c.containsKey(str)) {
                mapD = c;
            } else if (d.containsKey(str)) {
                mapD = d;
            } else if (e.containsKey(str)) {
                mapD = e;
            } else if (f.containsKey(str)) {
                mapD = f;
            } else if (g.containsKey(str)) {
                mapD = g;
            } else if (h.containsKey(str)) {
                mapD = h;
            } else if (i.containsKey(str)) {
                mapD = i;
            } else if (j.containsKey(str)) {
                mapD = j;
            }
        }
        if (mapD != null) {
            try {
                if (z.b(str) && mapD.containsKey(str) && (list = mapD.get(str)) != null && list.size() > 0) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("2", list.get(i2).a());
                        jSONObject.put("1", list.get(i2).b());
                        jSONArray.put(jSONObject);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return jSONArray.toString();
    }

    public static void a(String str, String str2, int i2) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList;
        int size;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return;
        }
        if (l == null) {
            l = new ConcurrentHashMap<>();
        }
        try {
            if (l.containsKey(str)) {
                copyOnWriteArrayList = l.get(str);
                copyOnWriteArrayList.add(str2);
            } else {
                CopyOnWriteArrayList<String> copyOnWriteArrayList2 = new CopyOnWriteArrayList<>();
                copyOnWriteArrayList2.add(str2);
                l.put(str, copyOnWriteArrayList2);
                copyOnWriteArrayList = copyOnWriteArrayList2;
            }
            if (copyOnWriteArrayList == null || (size = copyOnWriteArrayList.size() - i2) < 0) {
                return;
            }
            for (int i3 = 0; i3 < size; i3++) {
                copyOnWriteArrayList.remove(i3);
            }
        } catch (Exception e2) {
            s.a(n, e2.getMessage());
        }
    }

    public static void a(String str) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList;
        if (TextUtils.isEmpty(str) || l == null || !l.containsKey(str) || (copyOnWriteArrayList = l.get(str)) == null || copyOnWriteArrayList.size() <= 0) {
            return;
        }
        copyOnWriteArrayList.remove(0);
    }

    public static String b(String str) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList;
        return (TextUtils.isEmpty(str) || l == null || !l.containsKey(str) || (copyOnWriteArrayList = l.get(str)) == null || copyOnWriteArrayList.size() <= 0) ? "" : copyOnWriteArrayList.get(0);
    }

    public static void a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        try {
            if (m == null) {
                m = new ConcurrentHashMap<>();
            }
            if (m.containsKey(str)) {
                if (m.get(str) == null) {
                    ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
                    concurrentHashMap.put(str2, str3);
                    m.put(str, concurrentHashMap);
                    return;
                }
                return;
            }
            ConcurrentHashMap<String, String> concurrentHashMap2 = new ConcurrentHashMap<>();
            concurrentHashMap2.put(str2, str3);
            m.put(str, concurrentHashMap2);
        } catch (Exception e2) {
            s.a(n, e2.getMessage());
        }
    }

    public static com.mbridge.msdk.foundation.entity.d b(String str, String str2) {
        ConcurrentHashMap<String, String> concurrentHashMap;
        com.mbridge.msdk.foundation.entity.d dVar = new com.mbridge.msdk.foundation.entity.d();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return dVar;
        }
        try {
            if (m != null && m.containsKey(str) && (concurrentHashMap = m.get(str)) != null && concurrentHashMap.containsKey(str2)) {
                dVar.b(1);
                dVar.c(concurrentHashMap.get(str2));
            }
        } catch (Exception e2) {
            s.a(n, e2.getMessage());
        }
        return dVar;
    }

    public static void c(String str, String str2) {
        ConcurrentHashMap<String, String> concurrentHashMap;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            if (m == null || !m.containsKey(str) || (concurrentHashMap = m.get(str2)) == null || !concurrentHashMap.containsKey(str2)) {
                return;
            }
            concurrentHashMap.remove(str2);
        } catch (Exception e2) {
            s.a(n, e2.getMessage());
        }
    }

    public static void a(String str, String str2, String str3, long j2, long j3, long j4) {
        if (k == null) {
            k = new ConcurrentHashMap<>();
        }
        try {
            if (k.containsKey(str)) {
                ConcurrentHashMap<String, com.mbridge.msdk.foundation.entity.c> concurrentHashMap = k.get(str);
                if (concurrentHashMap != null) {
                    com.mbridge.msdk.foundation.entity.c cVar = concurrentHashMap.get(str2);
                    if (cVar == null) {
                        com.mbridge.msdk.foundation.entity.c cVar2 = new com.mbridge.msdk.foundation.entity.c();
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(str3);
                        cVar2.a(arrayList);
                        cVar2.a(j2);
                        cVar2.b(j3);
                        cVar2.c(j4);
                        concurrentHashMap.put(str2, cVar2);
                        return;
                    }
                    if (cVar.c() != null) {
                        cVar.a(j2);
                        cVar.b(j3);
                        cVar.c(j4);
                        cVar.c().add(str3);
                        return;
                    }
                    return;
                }
                return;
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(str3);
            ConcurrentHashMap<String, com.mbridge.msdk.foundation.entity.c> concurrentHashMap2 = new ConcurrentHashMap<>();
            com.mbridge.msdk.foundation.entity.c cVar3 = new com.mbridge.msdk.foundation.entity.c();
            cVar3.a(arrayList2);
            cVar3.a(j2);
            cVar3.b(j3);
            cVar3.c(j4);
            concurrentHashMap2.put(str2, cVar3);
            k.put(str, concurrentHashMap2);
        } catch (Throwable th) {
            s.d(n, th.getMessage());
        }
    }

    public static void d(String str, String str2) {
        if (TextUtils.isEmpty(str) || k == null || !k.containsKey(str)) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            k.remove(str);
            return;
        }
        ConcurrentHashMap<String, com.mbridge.msdk.foundation.entity.c> concurrentHashMap = k.get(str);
        if (concurrentHashMap == null || !concurrentHashMap.containsKey(str2)) {
            return;
        }
        concurrentHashMap.remove(str2);
    }

    public static ConcurrentHashMap<String, com.mbridge.msdk.foundation.entity.c> c(String str) {
        if (k == null) {
            f fVarA = f.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
            if (fVarA != null) {
                fVarA.f(str, "");
                k = new ConcurrentHashMap<>();
            }
        } else if (k.containsKey(str)) {
            return k.get(str);
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Map<java.lang.String, java.util.List<com.mbridge.msdk.foundation.same.d.a>> d(java.lang.String r1) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -1611837702: goto L4e;
                case -1396342996: goto L44;
                case -1052618729: goto L3a;
                case -934326481: goto L30;
                case -895866265: goto L26;
                case 538816457: goto L1c;
                case 604727084: goto L12;
                case 1844104930: goto L8;
                default: goto L7;
            }
        L7:
            goto L58
        L8:
            java.lang.String r0 = "interactive"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L58
            r1 = 2
            goto L59
        L12:
            java.lang.String r0 = "interstitial"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L58
            r1 = 4
            goto L59
        L1c:
            java.lang.String r0 = "h5_native"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L58
            r1 = 7
            goto L59
        L26:
            java.lang.String r0 = "splash"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L58
            r1 = 6
            goto L59
        L30:
            java.lang.String r0 = "reward"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L58
            r1 = 1
            goto L59
        L3a:
            java.lang.String r0 = "native"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L58
            r1 = 0
            goto L59
        L44:
            java.lang.String r0 = "banner"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L58
            r1 = 5
            goto L59
        L4e:
            java.lang.String r0 = "videofeeds"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L58
            r1 = 3
            goto L59
        L58:
            r1 = -1
        L59:
            switch(r1) {
                case 0: goto L73;
                case 1: goto L70;
                case 2: goto L6d;
                case 3: goto L6a;
                case 4: goto L67;
                case 5: goto L64;
                case 6: goto L61;
                case 7: goto L5e;
                default: goto L5c;
            }
        L5c:
            r1 = 0
            goto L75
        L5e:
            java.util.Map<java.lang.String, java.util.List<com.mbridge.msdk.foundation.same.d.a>> r1 = com.mbridge.msdk.foundation.same.a.d.j
            goto L75
        L61:
            java.util.Map<java.lang.String, java.util.List<com.mbridge.msdk.foundation.same.d.a>> r1 = com.mbridge.msdk.foundation.same.a.d.i
            goto L75
        L64:
            java.util.Map<java.lang.String, java.util.List<com.mbridge.msdk.foundation.same.d.a>> r1 = com.mbridge.msdk.foundation.same.a.d.h
            goto L75
        L67:
            java.util.Map<java.lang.String, java.util.List<com.mbridge.msdk.foundation.same.d.a>> r1 = com.mbridge.msdk.foundation.same.a.d.g
            goto L75
        L6a:
            java.util.Map<java.lang.String, java.util.List<com.mbridge.msdk.foundation.same.d.a>> r1 = com.mbridge.msdk.foundation.same.a.d.f
            goto L75
        L6d:
            java.util.Map<java.lang.String, java.util.List<com.mbridge.msdk.foundation.same.d.a>> r1 = com.mbridge.msdk.foundation.same.a.d.e
            goto L75
        L70:
            java.util.Map<java.lang.String, java.util.List<com.mbridge.msdk.foundation.same.d.a>> r1 = com.mbridge.msdk.foundation.same.a.d.d
            goto L75
        L73:
            java.util.Map<java.lang.String, java.util.List<com.mbridge.msdk.foundation.same.d.a>> r1 = com.mbridge.msdk.foundation.same.a.d.c
        L75:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.same.a.d.d(java.lang.String):java.util.Map");
    }
}
