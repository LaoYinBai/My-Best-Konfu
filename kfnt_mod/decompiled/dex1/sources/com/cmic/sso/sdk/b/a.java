package com.cmic.sso.sdk.b;

import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.bytedance.sdk.account.utils.UrlBuilder;
import com.cmic.sso.sdk.b.b.d;
import com.cmic.sso.sdk.b.b.g;
import com.cmic.sso.sdk.b.c.c;
import com.cmic.sso.sdk.b.d.b;
import com.cmic.sso.sdk.d.s;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private String f5383a;

    /* renamed from: b, reason: collision with root package name */
    private String f5384b;

    public c a(c cVar, b bVar, com.cmic.sso.sdk.a aVar) {
        List<String> list;
        Map<String, List<String>> mapB = bVar.b();
        if (TextUtils.isEmpty(this.f5383a) && (list = mapB.get("pplocation")) != null && list.size() > 0) {
            this.f5383a = list.get(0);
        }
        s.b(aVar, String.valueOf(bVar.a()));
        List<String> list2 = mapB.get("Location");
        if (list2 == null || list2.isEmpty()) {
            list2 = mapB.get("Location".toLowerCase());
        }
        if (list2 != null && list2.size() > 0) {
            this.f5384b = list2.get(0);
            if (!TextUtils.isEmpty(this.f5384b)) {
                String strB = aVar.b(OnekeyLoginConstants.OPERATOR_TYPE, OnekeyLoginConstants.CU_RESULT_SUCCESS);
                if (OnekeyLoginConstants.UNICOM_TYPE.equals(strB)) {
                    s.a(aVar, "getUnicomMobile");
                } else if (OnekeyLoginConstants.TELECOM_TYPE.equals(strB)) {
                    s.a(aVar, "getTelecomMobile");
                } else {
                    s.a(aVar, "NONE");
                }
            }
        }
        c cVarA = a(this.f5384b, cVar.f(), "GET", new com.cmic.sso.sdk.b.b.c(cVar.k().a()));
        cVarA.a(cVar.h());
        return cVarA;
    }

    public c b(c cVar, b bVar, com.cmic.sso.sdk.a aVar) {
        String strB = aVar.b(OnekeyLoginConstants.OPERATOR_TYPE, OnekeyLoginConstants.CU_RESULT_SUCCESS);
        if (OnekeyLoginConstants.UNICOM_TYPE.equals(strB)) {
            s.a(aVar, "getNewUnicomPhoneNumberNotify");
        } else if (OnekeyLoginConstants.TELECOM_TYPE.equals(strB)) {
            s.a(aVar, "getNewTelecomPhoneNumberNotify");
        } else {
            s.a(aVar, "NONE");
        }
        s.b(aVar, String.valueOf(bVar.a()));
        c cVarA = a("http://www.cmpassport.com/unisdk" + this.f5383a, cVar.f(), "POST", new d(cVar.k().a(), "1.0", bVar.c()));
        cVarA.a(cVar.h());
        this.f5383a = null;
        return cVarA;
    }

    private c a(String str, String str2, String str3, g gVar) {
        c cVar = new c(str, gVar, str3, str2);
        if (str3.equals("GET")) {
            cVar.a("Content-Type", UrlBuilder.CONTENT_TYPE);
        }
        return cVar;
    }

    public String a() {
        return this.f5383a;
    }
}
