package com.alipay.sdk.sys;

import android.content.Context;
import com.alipay.sdk.util.c;
import com.ta.utdid2.device.UTDevice;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static b f2636a;

    /* renamed from: b, reason: collision with root package name */
    private Context f2637b;

    private b() {
    }

    public static b a() {
        if (f2636a == null) {
            f2636a = new b();
        }
        return f2636a;
    }

    public Context b() {
        return this.f2637b;
    }

    public void a(Context context) {
        com.alipay.sdk.data.b.b();
        this.f2637b = context.getApplicationContext();
    }

    public com.alipay.sdk.data.b c() {
        return com.alipay.sdk.data.b.b();
    }

    public static boolean d() {
        for (String str : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    public String e() {
        try {
            return UTDevice.getUtdid(this.f2637b);
        } catch (Throwable th) {
            c.a(th);
            return "getUtdidEx";
        }
    }
}
