package com.huawei.agconnect.a;

import android.content.Context;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a {
    private static final Map<String, a> a = new HashMap();
    private static final Object b = new Object();

    public abstract String a(String str);

    public abstract void a(InputStream inputStream);

    public static a a(Context context) {
        a aVar;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        synchronized (b) {
            aVar = a.get(context.getPackageName());
            if (aVar == null) {
                aVar = new com.huawei.agconnect.a.a.a(context);
                a.put(context.getPackageName(), aVar);
            }
        }
        return aVar;
    }
}
