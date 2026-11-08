package com.huawei.a.a.b.d;

import android.os.Build;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    protected static com.huawei.a.a.b.c.b.e a() {
        com.huawei.a.a.b.e.a.b("hmsSdk", "generate UploadData");
        f.a().b();
        if (!TextUtils.isEmpty(f.a().d())) {
            return new com.huawei.a.a.b.c.b.e(f.a().c());
        }
        com.huawei.a.a.b.e.a.c("hmsSdk", "event chifer is empty");
        return null;
    }

    protected static com.huawei.a.a.e.a a(String str, String str2) {
        com.huawei.a.a.e.a aVar = new com.huawei.a.a.e.a();
        aVar.a(com.huawei.a.a.c.b.a.a().d(str, str2));
        return aVar;
    }

    protected static com.huawei.a.a.e.b a(String str, String str2, String str3, String str4) {
        com.huawei.a.a.e.b bVar = new com.huawei.a.a.e.b();
        bVar.f(str);
        bVar.a(com.huawei.a.a.a.b.e());
        bVar.c(str2);
        bVar.e(str4);
        StringBuffer stringBuffer = new StringBuffer("hmshi");
        stringBuffer.append(str3);
        stringBuffer.append("qrt");
        bVar.d(stringBuffer.toString());
        return bVar;
    }

    protected static com.huawei.a.a.e.c a(String str, String str2, String str3) {
        com.huawei.a.a.e.c cVar = new com.huawei.a.a.e.c();
        cVar.a(com.huawei.a.a.a.b.b());
        cVar.b(com.huawei.a.a.a.b.d());
        cVar.c(str3);
        cVar.d(com.huawei.a.a.c.b.a.a().e(str2, str));
        return cVar;
    }

    protected static Map<String, String> b(String str, String str2) {
        HashMap map = new HashMap();
        map.put("App-Id", com.huawei.a.a.a.b.e());
        map.put("App-Ver", com.huawei.a.a.a.b.f());
        map.put("Sdk-Name", "hianalytics");
        map.put("Sdk-Ver", "2.2.0.305");
        map.put("Device-Type", Build.MODEL);
        map.put("servicetag", str);
        com.huawei.a.a.b.e.a.a("hmsSdk", "sendData RequestId : %s", str2);
        map.put("Request-Id", str2);
        return map;
    }
}
