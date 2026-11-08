package com.sigmob.sdk.common.e;

import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.volley.p;
import com.sigmob.volley.s;
import java.net.URL;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends k<com.sigmob.volley.i> {
    private final InterfaceC0019a b;
    private String c;

    /* renamed from: com.sigmob.sdk.common.e.a$a, reason: collision with other inner class name */
    public interface InterfaceC0019a {
        void onErrorResponse(s sVar);

        void onSuccess();
    }

    public a(String str, String str2, InterfaceC0019a interfaceC0019a) {
        super(str, 1, null);
        this.c = "";
        this.b = interfaceC0019a;
        this.c = str2;
        a((p) new com.sigmob.volley.c(10000, 2, 0.0f));
        a(false);
    }

    public static void a(String str, InterfaceC0019a interfaceC0019a) {
        if ((str == null || str.length() == 0) && interfaceC0019a != null) {
            interfaceC0019a.onErrorResponse(new s("body is empty"));
        }
        if (f.f() == null) {
            if (interfaceC0019a != null) {
                interfaceC0019a.onErrorResponse(new s("BuriedPointRequestQueue is empty"));
                return;
            }
            return;
        }
        try {
            if (m.b(new URL(SDKConfig.sharedInstance().getLogUrl()).getHost())) {
                f.f().a(new a(SDKConfig.sharedInstance().getLogUrl(), str, interfaceC0019a));
            } else if (interfaceC0019a != null) {
                interfaceC0019a.onErrorResponse(new s("network is disconnect "));
            }
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            if (interfaceC0019a != null) {
                interfaceC0019a.onErrorResponse(new s("network is disconnect "));
            }
        }
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    protected com.sigmob.volley.n<com.sigmob.volley.i> a(com.sigmob.volley.i iVar) {
        return com.sigmob.volley.n.a(iVar, null);
    }

    @Override // com.sigmob.volley.l
    public void a(s sVar) {
        InterfaceC0019a interfaceC0019a;
        synchronized (this.a) {
            interfaceC0019a = this.b;
        }
        SigmobLog.i("send dclog: " + h() + " onErrorResponse");
        if (interfaceC0019a != null) {
            interfaceC0019a.onErrorResponse(sVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sigmob.volley.l
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(com.sigmob.volley.i iVar) {
        InterfaceC0019a interfaceC0019a;
        synchronized (this.a) {
            interfaceC0019a = this.b;
        }
        SigmobLog.i("send dclog: " + h() + " success");
        if (interfaceC0019a != null) {
            interfaceC0019a.onSuccess();
        }
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    public byte[] b() {
        return this.c.getBytes();
    }

    @Override // com.sigmob.sdk.common.e.k, com.sigmob.volley.l
    public Map<String, String> c() {
        Map<String, String> mapC = super.c();
        mapC.put("gz", "on");
        return mapC;
    }

    @Override // com.sigmob.volley.l
    public int d() {
        return 100;
    }
}
