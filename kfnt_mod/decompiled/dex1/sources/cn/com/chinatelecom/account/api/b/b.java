package cn.com.chinatelecom.account.api.b;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.com.chinatelecom.account.api.CtSetting;
import cn.com.chinatelecom.account.api.Helper;
import cn.com.chinatelecom.account.api.a.d;
import cn.com.chinatelecom.account.api.c.c;
import cn.com.chinatelecom.account.api.c.e;
import cn.com.chinatelecom.account.api.d.g;
import cn.com.chinatelecom.account.api.e.f;
import cn.com.chinatelecom.account.api.e.j;
import com.facebook.stetho.dumpapp.Framer;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static final byte[] f2213a = {122, 99, 122, 102};

    /* renamed from: b, reason: collision with root package name */
    public static final byte[] f2214b = {44, 104, Framer.EXIT_FRAME_PREFIX, 99, 109, 75, 122, 122, 55};

    /* renamed from: c, reason: collision with root package name */
    public static final byte[] f2215c = {44, 121, 126, 55};
    public static final byte[] d = {44, 104, 126, 55};
    public static final byte[] e = {44, 107, 127, 126, 98, 94, 115, 122, 111, 55};
    private static final String f = "b";
    private c g;

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject a(Context context, String str, String str2, Network network, String str3, String str4) {
        try {
            g.a aVar = new g.a();
            aVar.a(str4);
            aVar.a(false, cn.com.chinatelecom.account.api.d.c.a(), d.a(cn.com.chinatelecom.account.api.e.b.f));
            aVar.b(str3);
            aVar.a(network);
            aVar.a(CtSetting.getConnTimeout(null));
            aVar.b(CtSetting.getReadTimeout(null));
            HashMap map = new HashMap();
            map.put(d.a(f2213a), Helper.dnprecohdjs());
            aVar.a(map);
            JSONObject jSONObjectA = cn.com.chinatelecom.account.api.e.a.a(context, new cn.com.chinatelecom.account.api.d.b(context).a(str2, "", 0, aVar.a()), str, network, true, str3);
            f.b(str3, jSONObjectA, str2);
            return jSONObjectA;
        } catch (Throwable th) {
            JSONObject jSONObjectG = j.g();
            f.a(str3).g("gpm ：" + th.getMessage()).a(80102).e(d.a(j.k));
            CtAuth.warn(f, "GPM Throwable", th);
            return jSONObjectG;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.g != null) {
            this.g.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, String str2, long j, String str3, a aVar) throws JSONException {
        f.a(str2).a(i).e(str).b(j).g(str3);
        f.c(str2);
        String strA = j.a(i, str, str2);
        if (aVar != null) {
            aVar.callbackPreCode(strA);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final String str, final String str2, final Network network, long j, final String str3, final String str4, final a aVar) {
        new cn.com.chinatelecom.account.api.c.d().a(new e(j) { // from class: cn.com.chinatelecom.account.api.b.b.3
            @Override // cn.com.chinatelecom.account.api.c.e
            public void a() {
                JSONObject jSONObjectA = b.this.a(context, str, str2, network, str3, str4);
                synchronized (this) {
                    if (!c()) {
                        a(true);
                        d();
                        if (aVar != null) {
                            try {
                                jSONObjectA.put("reqId", str3);
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                            aVar.callbackPreCode(jSONObjectA.toString());
                        }
                        f.c(str3);
                    }
                }
                if (network != null) {
                    b.this.a();
                }
            }

            @Override // cn.com.chinatelecom.account.api.c.e
            public void b() {
                super.b();
                synchronized (this) {
                    if (!c()) {
                        a(true);
                        b.this.a(80000, d.a(j.f2301a), str3, 0L, "", aVar);
                    }
                }
                if (network != null) {
                    b.this.a();
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r9, cn.com.chinatelecom.account.api.b.a r10) {
        /*
            r8 = this;
            cn.com.chinatelecom.account.api.CtAuth r0 = cn.com.chinatelecom.account.api.CtAuth.getInstance()
            android.content.Context r0 = r0.getContext()
            java.lang.String r1 = ""
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            r3 = 0
            if (r2 != 0) goto L3b
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L35
            r2.<init>(r9)     // Catch: org.json.JSONException -> L35
            java.lang.String r9 = "st"
            java.lang.String r9 = r2.optString(r9)     // Catch: org.json.JSONException -> L35
            java.lang.String r1 = "bt"
            java.lang.String r1 = r2.optString(r1)     // Catch: org.json.JSONException -> L32
            java.lang.String r4 = "authType"
            java.lang.String r2 = r2.optString(r4)     // Catch: org.json.JSONException -> L2d
            r3 = r2
            r7 = r1
            r1 = r9
            r9 = r7
            goto L3c
        L2d:
            r2 = move-exception
            r7 = r1
            r1 = r9
            r9 = r7
            goto L37
        L32:
            r2 = move-exception
            r1 = r9
            goto L36
        L35:
            r2 = move-exception
        L36:
            r9 = r3
        L37:
            r2.printStackTrace()
            goto L3c
        L3b:
            r9 = r3
        L3c:
            long r4 = cn.com.chinatelecom.account.api.e.a.a(r0)
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            byte[] r6 = cn.com.chinatelecom.account.api.b.b.f2214b
            java.lang.String r6 = cn.com.chinatelecom.account.api.a.d.a(r6)
            r2.append(r6)
            java.lang.String r6 = cn.com.chinatelecom.account.api.CtAuth.mAppId
            r2.append(r6)
            byte[] r6 = cn.com.chinatelecom.account.api.b.b.f2215c
            java.lang.String r6 = cn.com.chinatelecom.account.api.a.d.a(r6)
            r2.append(r6)
            r2.append(r1)
            byte[] r1 = cn.com.chinatelecom.account.api.b.b.d
            java.lang.String r1 = cn.com.chinatelecom.account.api.a.d.a(r1)
            r2.append(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r9)
            if (r1 == 0) goto L70
            java.lang.String r9 = "tyx"
        L70:
            r2.append(r9)
            byte[] r9 = cn.com.chinatelecom.account.api.b.b.e
            java.lang.String r9 = cn.com.chinatelecom.account.api.a.d.a(r9)
            r2.append(r9)
            boolean r9 = android.text.TextUtils.isEmpty(r3)
            if (r9 == 0) goto L84
            java.lang.String r3 = "2"
        L84:
            r2.append(r3)
            java.lang.String r9 = r2.toString()
            java.lang.String r9 = cn.com.chinatelecom.account.api.e.h.a(r0, r4, r9)
            if (r10 == 0) goto L94
            r10.callbackPreCodeParams(r9)
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.b.b.a(java.lang.String, cn.com.chinatelecom.account.api.b.a):void");
    }

    public void a(String str, String str2, a aVar) {
        Context context = CtAuth.getInstance().getContext();
        int totalTimeout = CtSetting.getTotalTimeout(null);
        String strA = cn.com.chinatelecom.account.api.e.d.a();
        f.a(strA).a(cn.com.chinatelecom.account.api.e.d.a(context)).c("preCodeByJs").b(cn.com.chinatelecom.account.api.e.g.e(context)).f(cn.com.chinatelecom.account.api.e.g.i(context));
        a(context, str, str2, null, totalTimeout, strA, "preCodeByJs", aVar);
    }

    public void b(final String str, final String str2, final a aVar) {
        final Context context = CtAuth.getInstance().getContext();
        final int totalTimeout = CtSetting.getTotalTimeout(null);
        final String strA = cn.com.chinatelecom.account.api.e.d.a();
        f.a(strA).a(cn.com.chinatelecom.account.api.e.d.a(context)).c("preCodeByJs").b("BOTH").f(cn.com.chinatelecom.account.api.e.g.i(context));
        if (Build.VERSION.SDK_INT >= 21) {
            this.g = new c(context);
            this.g.a(new cn.com.chinatelecom.account.api.c.b() { // from class: cn.com.chinatelecom.account.api.b.b.1
                @Override // cn.com.chinatelecom.account.api.c.b
                public void a() throws JSONException {
                    b.this.a();
                    b.this.a(80800, d.a(j.o), strA, 2500L, "", aVar);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(long j) throws JSONException {
                    b.this.a();
                    b.this.a(80801, d.a(j.p), strA, j, "", aVar);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(Network network, long j) throws JSONException {
                    f.a(strA).b(j);
                    long j2 = totalTimeout - j;
                    if (j2 > 100) {
                        b.this.a(context, str, str2, network, j2, strA, "preCodeByJs", aVar);
                    } else {
                        b.this.a();
                        b.this.a(80000, d.a(j.f2301a), strA, j, "", aVar);
                    }
                }
            });
        } else {
            this.g = new c(context);
            this.g.a(new cn.com.chinatelecom.account.api.c.b() { // from class: cn.com.chinatelecom.account.api.b.b.2
                @Override // cn.com.chinatelecom.account.api.c.b
                public void a() throws JSONException {
                    b.this.a(80800, d.a(j.o), strA, 2500L, "Switching network timeout (4.x)", aVar);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(long j) throws JSONException {
                    b.this.a(80801, d.a(j.p), strA, j, "Switching network failed (4.x)", aVar);
                }

                @Override // cn.com.chinatelecom.account.api.c.b
                public void a(Network network, long j) throws JSONException {
                    f.a(strA).b(j);
                    long j2 = totalTimeout - j;
                    if (j2 > 100) {
                        b.this.a(context, str, str2, network, j2, strA, "preCodeByJs", aVar);
                    } else {
                        b.this.a(80000, d.a(j.f2301a), strA, j, "", aVar);
                    }
                }
            }, str2);
        }
    }
}
