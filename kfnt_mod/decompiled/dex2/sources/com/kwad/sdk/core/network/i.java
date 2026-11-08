package com.kwad.sdk.core.network;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.core.network.g;
import com.kwad.sdk.export.proxy.AdHttpProxy;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class i<R extends g, T extends BaseResultData> extends a<R> {

    @Nullable
    private h<R, T> a = null;
    private final com.kwad.sdk.core.network.a.b b = new com.kwad.sdk.core.network.a.b();

    private void a(@NonNull R r) {
        if (this.a == null) {
            return;
        }
        this.a.a(r);
    }

    private void a(@NonNull R r, int i, String str) {
        if (this.a == null) {
            return;
        }
        this.a.a(r, i, str);
    }

    private void a(@NonNull R r, T t) {
        if (this.a == null) {
            return;
        }
        this.a.a(r, t);
    }

    private void a(String str, String str2) {
        try {
            l.a().a(str, new JSONObject(str2).optString("requestSessionData"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.kwad.sdk.core.network.a
    protected void a(R r, c cVar) {
        if (cVar == null) {
            a(r, f.c.n, f.c.o);
            this.b.a("responseBase is null");
            com.kwad.sdk.core.d.a.e("Networking", "request responseBase is null");
            return;
        }
        if (TextUtils.isEmpty(cVar.b) || cVar.a != 200) {
            a(r, cVar.a, "网络错误");
            this.b.a("httpCodeError:" + cVar.a + ":" + cVar.b);
            StringBuilder sb = new StringBuilder();
            sb.append("request responseBase httpCodeError:");
            sb.append(cVar.a);
            com.kwad.sdk.core.d.a.e("Networking", sb.toString());
            return;
        }
        try {
            a(r.a(), cVar.b);
            BaseResultData baseResultDataB = b(cVar.b);
            this.b.d();
            if (baseResultDataB.isResultOk()) {
                if (baseResultDataB.isDataEmpty()) {
                    a(r, f.e.n, f.e.o);
                    return;
                } else {
                    a((i<R, T>) r, (R) baseResultDataB);
                    return;
                }
            }
            a(r, baseResultDataB.result, baseResultDataB.errorMsg);
            this.b.a("serverCodeError:" + baseResultDataB.result + ":" + baseResultDataB.errorMsg);
        } catch (Exception e) {
            a(r, f.d.n, f.d.o);
            com.kwad.sdk.core.d.a.b(e);
            this.b.a("parseDataError:" + e.getMessage());
        }
    }

    public void a(@NonNull h<R, T> hVar) {
        this.b.a();
        this.a = hVar;
        d();
    }

    @NonNull
    protected abstract T b(String str);

    protected boolean c() {
        return true;
    }

    @Override // com.kwad.sdk.core.network.a
    public void e() {
        super.e();
        this.a = null;
    }

    @Override // com.kwad.sdk.core.network.a
    @WorkerThread
    protected void f() throws Throwable {
        R rB;
        Exception e;
        c cVarDoPost = null;
        g gVar = null;
        g gVar2 = null;
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                rB = b();
                try {
                    this.b.b();
                    a((i<R, T>) rB);
                    if (com.ksad.download.c.b.a(KsAdSDKImpl.get().getContext())) {
                        try {
                            String strA = rB.a();
                            AdHttpProxy proxyForHttp = KsAdSDKImpl.get().getProxyForHttp();
                            cVarDoPost = g() ? proxyForHttp.doPost(strA, rB.c(), rB.e()) : proxyForHttp.doPost(strA, rB.c(), rB.d());
                        } catch (Exception e2) {
                            com.kwad.sdk.core.d.a.b(e2);
                            this.b.a("requestError:" + e2.getMessage());
                        }
                        this.b.c();
                        try {
                            a((i<R, T>) rB, cVarDoPost);
                        } catch (Exception e3) {
                            this.b.a("onResponseError:" + e3.getMessage());
                            com.kwad.sdk.core.d.a.b(e3);
                        }
                    } else {
                        a(rB, f.c.n, f.c.o);
                    }
                } catch (Exception e4) {
                    e = e4;
                    try {
                        this.b.a("requestError:" + e.getMessage());
                    } catch (Exception unused) {
                    }
                    com.kwad.sdk.core.d.a.a(e);
                    if (c() && com.kwad.sdk.core.config.b.ap()) {
                        this.b.a(rB.a(), rB.b());
                        this.b.e();
                    }
                    return;
                }
            } catch (Exception unused2) {
                return;
            }
        } catch (Exception e5) {
            rB = null;
            e = e5;
        } catch (Throwable th2) {
            th = th2;
            try {
                if (c() && com.kwad.sdk.core.config.b.ap()) {
                    this.b.a(gVar2.a(), gVar.b());
                    this.b.e();
                }
            } catch (Exception unused3) {
            }
            throw th;
        }
        if (c() && com.kwad.sdk.core.config.b.ap()) {
            this.b.a(rB.a(), rB.b());
            this.b.e();
        }
    }

    protected boolean g() {
        return true;
    }
}
