package com.sigmob.volley.toolbox;

import com.sigmob.volley.b;
import com.sigmob.volley.p;
import com.sigmob.volley.s;
import com.sigmob.volley.t;
import com.umeng.message.util.HttpRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e implements com.sigmob.volley.f {
    protected static final boolean a = t.b;
    protected final c b;
    private final a c;

    public e(a aVar) {
        this(aVar, new c(4096));
    }

    public e(a aVar, c cVar) {
        this.c = aVar;
        this.b = cVar;
    }

    private static List<com.sigmob.volley.e> a(List<com.sigmob.volley.e> list, b.a aVar) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        if (!list.isEmpty()) {
            Iterator<com.sigmob.volley.e> it = list.iterator();
            while (it.hasNext()) {
                treeSet.add(it.next().a());
            }
        }
        ArrayList arrayList = new ArrayList(list);
        if (aVar.h != null) {
            if (!aVar.h.isEmpty()) {
                for (com.sigmob.volley.e eVar : aVar.h) {
                    if (!treeSet.contains(eVar.a())) {
                        arrayList.add(eVar);
                    }
                }
            }
        } else if (!aVar.g.isEmpty()) {
            for (Map.Entry<String, String> entry : aVar.g.entrySet()) {
                if (!treeSet.contains(entry.getKey())) {
                    arrayList.add(new com.sigmob.volley.e(entry.getKey(), entry.getValue()));
                }
            }
        }
        return arrayList;
    }

    private Map<String, String> a(b.a aVar) {
        if (aVar == null) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap();
        if (aVar.b != null) {
            map.put(HttpRequest.HEADER_IF_NONE_MATCH, aVar.b);
        }
        if (aVar.d > 0) {
            map.put("If-Modified-Since", h.a(aVar.d));
        }
        return map;
    }

    public static Map<String, String> a(List<com.sigmob.volley.e> list) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < list.size(); i++) {
            com.sigmob.volley.e eVar = list.get(i);
            treeMap.put(eVar.a(), eVar.b());
        }
        return treeMap;
    }

    private void a(long j, com.sigmob.volley.l<?> lVar, byte[] bArr, int i) {
        if (a || j > 3000) {
            Object[] objArr = new Object[5];
            objArr[0] = lVar;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(i);
            objArr[4] = Integer.valueOf(lVar.r().b());
            t.b("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    public static void a(String str, com.sigmob.volley.l<?> lVar, s sVar) throws s {
        p pVarR = lVar.r();
        int iP = lVar.p();
        try {
            pVarR.a(sVar);
            lVar.a(String.format("%s-retry [RetryCount %s]  [timeout=%s]", str, Integer.valueOf(pVarR.b()), Integer.valueOf(iP)));
        } catch (s e) {
            lVar.a(String.format("%s-timeout-giveup [RetryCount %s] [timeout=%s]", str, Integer.valueOf(pVarR.b()), Integer.valueOf(iP)));
            throw e;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:94|16|(6:(3:93|18|(1:20)(7:22|88|25|26|90|27|(2:33|34)(2:31|32)))(1:23)|90|27|(1:29)|33|34)|24|88|25|26) */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00da, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00db, code lost:
    
        r23 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0185 A[SYNTHETIC] */
    @Override // com.sigmob.volley.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.sigmob.volley.i a(com.sigmob.volley.l<?> r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 435
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.volley.toolbox.e.a(com.sigmob.volley.l):com.sigmob.volley.i");
    }
}
