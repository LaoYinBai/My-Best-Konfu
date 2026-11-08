package com.sigmob.volley.toolbox;

import com.sigmob.volley.b;
import com.sigmob.volley.p;
import com.sigmob.volley.s;
import com.sigmob.volley.t;
import com.umeng.message.util.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements com.sigmob.volley.f {
    protected static final boolean a = t.b;
    protected final c b;
    private final a c;

    public b(a aVar) {
        this(aVar, new c(4096));
    }

    public b(a aVar, c cVar) {
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

    protected static Map<String, String> a(List<com.sigmob.volley.e> list) {
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

    private static void a(String str, com.sigmob.volley.l<?> lVar) {
        lVar.a(String.format("%s-Redirect [timeout=%s]", str, Integer.valueOf(lVar.p())));
    }

    private static void a(String str, com.sigmob.volley.l<?> lVar, s sVar) throws s {
        p pVarR = lVar.r();
        int iP = lVar.p();
        try {
            pVarR.a(sVar);
            lVar.a(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(iP)));
        } catch (s e) {
            lVar.a(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(iP)));
            throw e;
        }
    }

    private byte[] a(InputStream inputStream, int i) throws Throwable {
        byte[] byteArray;
        n nVar = new n(this.b, i);
        byte[] bArr = null;
        try {
            try {
                byte[] bArrA = this.b.a(com.ss.union.game.sdk.feedback.picture.select.d.d);
                long j = 0;
                while (true) {
                    try {
                        int i2 = inputStream.read(bArrA);
                        if (i2 == -1 || (i >= 1 && j >= i)) {
                            break;
                        }
                        nVar.write(bArrA, 0, i2);
                        j += i2;
                    } catch (Throwable th) {
                        th = th;
                        bArr = bArrA;
                        t.c("readError", th.getMessage());
                        byteArray = nVar.toByteArray();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused) {
                                t.a("Error occurred when closing InputStream", new Object[0]);
                            }
                        }
                        this.b.a(bArr);
                        nVar.close();
                        return byteArray;
                    }
                }
                byteArray = nVar.toByteArray();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                        t.a("Error occurred when closing InputStream", new Object[0]);
                    }
                }
                this.b.a(bArrA);
            } catch (Throwable th2) {
                th = th2;
            }
            nVar.close();
            return byteArray;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:170:0x021a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0163 A[Catch: all -> 0x0158, TRY_ENTER, TryCatch #11 {all -> 0x0158, blocks: (B:4:0x0012, B:81:0x0163, B:83:0x017e, B:96:0x01a4, B:98:0x01aa, B:99:0x01b1, B:100:0x01b5, B:101:0x01ba, B:105:0x01c3, B:107:0x01c9, B:108:0x01d1, B:109:0x01d6, B:111:0x01d9, B:113:0x01df, B:114:0x01e7, B:115:0x01ec, B:116:0x01ed, B:117:0x01f2, B:118:0x01f3, B:119:0x01fb, B:120:0x0201, B:127:0x021a, B:128:0x021f, B:130:0x0221, B:131:0x023b, B:132:0x023c), top: B:163:0x0012 }] */
    @Override // com.sigmob.volley.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.sigmob.volley.i a(com.sigmob.volley.l<?> r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 638
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sigmob.volley.toolbox.b.a(com.sigmob.volley.l):com.sigmob.volley.i");
    }
}
