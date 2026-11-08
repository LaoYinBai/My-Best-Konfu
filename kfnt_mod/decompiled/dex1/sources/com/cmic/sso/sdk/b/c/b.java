package com.cmic.sso.sdk.b.c;

import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.cmic.sso.sdk.b.b.e;
import com.cmic.sso.sdk.d.r;
import java.net.SocketException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends c {

    /* renamed from: b, reason: collision with root package name */
    private e f5428b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f5429c;

    b(String str, e eVar, String str2, String str3) {
        super(str, eVar, str2, str3);
        this.f5429c = false;
        this.f5428b = eVar;
    }

    public void a(com.cmic.sso.sdk.a aVar) throws SocketException {
        if (this.f5429c) {
            return;
        }
        String strA = r.a(true);
        String strA2 = r.a(true, "1".equals(aVar.b(OnekeyLoginConstants.OPERATOR_TYPE, "")));
        aVar.a("ipv4List", strA);
        aVar.a("ipv6List", strA2);
        e.a aVarC = this.f5428b.c();
        if (!aVar.b("isCloseIpv4", false)) {
            aVarC.a(strA);
        }
        if (!aVar.b("isCloseIpv6", false)) {
            aVarC.b(strA2);
        }
        aVarC.v(aVarC.w(aVar.b("appkey")));
        this.f5428b.a(aVarC);
        this.f5428b.a(true);
        this.f5430a = this.f5428b.b().toString();
        this.f5429c = true;
    }
}
