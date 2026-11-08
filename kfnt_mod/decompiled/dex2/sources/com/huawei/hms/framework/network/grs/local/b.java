package com.huawei.hms.framework.network.grs.local;

import android.content.Context;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static Map<String, b> a = new ConcurrentHashMap(16);
    private a b;

    public b(Context context) {
        a(context);
        a.put(context.getPackageName(), this);
    }

    public static b a(String str) {
        return a.get(str);
    }

    public com.huawei.hms.framework.network.grs.local.model.a a() {
        return this.b.a();
    }

    public String a(GrsBaseInfo grsBaseInfo, String str, String str2) {
        return this.b.a(grsBaseInfo, str, str2);
    }

    public Map<String, String> a(GrsBaseInfo grsBaseInfo, String str) {
        return this.b.a(grsBaseInfo, str);
    }

    public void a(Context context) {
        this.b = new d(context);
        if (this.b.b()) {
            return;
        }
        this.b = new c(context);
    }

    public void a(GrsBaseInfo grsBaseInfo) {
        this.b.a(grsBaseInfo);
    }
}
