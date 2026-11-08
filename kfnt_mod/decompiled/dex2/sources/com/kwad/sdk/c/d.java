package com.kwad.sdk.c;

import android.content.Context;
import android.os.Build;
import android.webkit.WebResourceResponse;
import com.kwad.sdk.c.kwai.e;
import com.kwad.sdk.c.kwai.f;
import com.kwad.sdk.crash.utils.h;
import com.kwad.sdk.utils.q;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    private Context c;
    private Map<String, com.kwad.sdk.c.kwai.b> d = new HashMap();
    private final Map<f, e> a = new ConcurrentHashMap(16);
    private final Lock b = new ReentrantLock();

    static class a implements com.kwad.sdk.c.a.c {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x005f A[ORIG_RETURN, RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
        @Override // com.kwad.sdk.c.a.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean a(com.kwad.sdk.c.kwai.d r7) throws java.lang.Throwable {
            /*
                r6 = this;
                java.lang.String r0 = r7.a()
                java.lang.String r1 = r7.e()
                java.lang.String r1 = com.kwad.sdk.utils.c.a(r1)
                r2 = 0
                if (r1 == 0) goto L1b
                int r3 = r1.length()
                r4 = 10
                if (r3 <= r4) goto L1b
                java.lang.String r1 = r1.substring(r2, r4)
            L1b:
                boolean r3 = android.text.TextUtils.isEmpty(r0)
                if (r3 != 0) goto L65
                boolean r3 = android.text.TextUtils.isEmpty(r1)
                if (r3 != 0) goto L65
                boolean r0 = r1.equalsIgnoreCase(r0)
                if (r0 != 0) goto L2e
                goto L65
            L2e:
                r0 = 0
                java.lang.String r7 = r7.e()     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                java.io.InputStream r7 = com.kwad.sdk.utils.q.b(r7)     // Catch: java.lang.Throwable -> L4d java.io.IOException -> L4f
                if (r7 == 0) goto L48
                int r0 = r7.available()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L43
                goto L49
            L3e:
                r0 = move-exception
                r5 = r0
                r0 = r7
                r7 = r5
                goto L61
            L43:
                r0 = move-exception
                r5 = r0
                r0 = r7
                r7 = r5
                goto L50
            L48:
                r0 = 0
            L49:
                com.kwad.sdk.utils.q.b(r7)
                goto L5d
            L4d:
                r7 = move-exception
                goto L61
            L4f:
                r7 = move-exception
            L50:
                java.lang.String r1 = "resource file is error "
                java.lang.String r7 = r7.getMessage()     // Catch: java.lang.Throwable -> L4d
                com.kwad.sdk.core.d.a.e(r1, r7)     // Catch: java.lang.Throwable -> L4d
                com.kwad.sdk.utils.q.b(r0)
                r0 = 0
            L5d:
                if (r0 == 0) goto L60
                r2 = 1
            L60:
                return r2
            L61:
                com.kwad.sdk.utils.q.b(r0)
                throw r7
            L65:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.c.d.a.a(com.kwad.sdk.c.kwai.d):boolean");
        }
    }

    public d(Context context) {
        this.c = context;
    }

    private WebResourceResponse a(InputStream inputStream, e eVar, com.kwad.sdk.c.kwai.b bVar) {
        if (Build.VERSION.SDK_INT < 21) {
            return new WebResourceResponse(eVar.c(), C.UTF8_NAME, inputStream);
        }
        HashMap map = new HashMap();
        map.put("Access-Control-Allow-Origin", bVar.e.a);
        map.put("Access-Control-Allow-Credentials", "true");
        map.put("Timing-Allow-Origin", bVar.e.b);
        map.put("content-type", bVar.e.c);
        map.put("Date", bVar.e.d);
        return new WebResourceResponse(bVar.d, "", bVar.a, "OK", map, inputStream);
    }

    private com.kwad.sdk.c.kwai.b a(com.kwad.sdk.c.kwai.d dVar, String str) {
        String strD = com.kwad.sdk.c.b.a.d(this.c, dVar.f());
        com.kwad.sdk.c.kwai.b bVar = this.d.get(str);
        if (bVar != null) {
            return bVar;
        }
        try {
            return a(str, strD);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private com.kwad.sdk.c.kwai.b a(String str, String str2) throws JSONException, IOException {
        FileInputStream fileInputStream;
        String strB;
        JSONObject jSONObject;
        File file = new File(str2);
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        try {
            try {
                strB = h.b(inputStreamReader);
            } catch (IOException e2) {
                e2.printStackTrace();
                q.b(fileInputStream);
                q.b(inputStreamReader);
                strB = null;
            }
            if (strB != null) {
                try {
                    jSONObject = new JSONObject(strB);
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            } else {
                jSONObject = null;
            }
            Iterator<String> itKeys = jSONObject != null ? jSONObject.keys() : null;
            if (itKeys != null) {
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                    com.kwad.sdk.c.kwai.b bVar = new com.kwad.sdk.c.kwai.b();
                    bVar.parseJson(jSONObject2);
                    this.d.put(next, bVar);
                }
            }
            return this.d.get(str);
        } finally {
            q.b(fileInputStream);
            q.b(inputStreamReader);
        }
    }

    private void a(f fVar) {
        if (this.b.tryLock()) {
            this.a.remove(fVar);
            this.b.unlock();
        }
    }

    public WebResourceResponse a(String str, com.kwad.sdk.c.kwai.d dVar, String str2) throws IOException {
        e eVarD;
        f fVar = new f(str);
        if (this.a.get(fVar) != null) {
            eVarD = this.a.get(fVar);
        } else {
            StringBuilder sb = new StringBuilder(com.kwad.sdk.c.b.a.c(this.c, dVar.f()));
            sb.append("/");
            sb.append(fVar.a());
            eVarD = new e().a(dVar.a).b(str).e(dVar.a()).c(sb.toString()).d(URLConnection.getFileNameMap().getContentTypeFor(sb.toString()));
            this.a.put(new f(eVarD.a()), eVarD);
        }
        if (!com.kwad.sdk.c.b.c.a(eVarD.c())) {
            a(fVar);
            return null;
        }
        InputStream inputStreamB = q.b(eVarD.b());
        if (inputStreamB != null) {
            com.kwad.sdk.c.kwai.b bVarA = a(dVar, fVar.a());
            if (bVarA == null) {
                return null;
            }
            return a(inputStreamB, eVarD, bVarA);
        }
        com.kwad.sdk.core.d.a.a("HybridResourceManagerImpl", "getResource [" + str + "] inputStream is null");
        a(fVar);
        return null;
    }

    public String a(String str) {
        return new f(str).b();
    }
}
