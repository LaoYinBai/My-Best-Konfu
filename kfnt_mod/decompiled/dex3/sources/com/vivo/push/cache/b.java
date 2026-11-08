package com.vivo.push.cache;

import android.content.Context;
import com.vivo.push.util.n;
import com.vivo.push.util.y;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b {
    private static volatile b a;
    private d b;

    private b() {
    }

    public static synchronized b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public final d a(Context context) {
        if (this.b != null) {
            return this.b;
        }
        try {
            String str = y.a(context) ? "com.vivo.push.cache.ServerConfigManagerImpl" : "com.vivo.push.cache.ClientConfigManagerImpl";
            Method method = Class.forName(str).getMethod("getInstance", Context.class);
            n.d("ConfigManagerFactory", "createConfig success is " + str);
            this.b = (d) method.invoke(null, context);
            return this.b;
        } catch (Exception e) {
            e.printStackTrace();
            n.b("ConfigManagerFactory", "createConfig error", e);
            return null;
        }
    }
}
