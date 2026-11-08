package com.bytedance.a;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Base64;
import android.util.Log;
import com.bytedance.a.bm;
import com.bytedance.dr.VivoIdentifier;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class cb implements bm {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3452a;

    /* renamed from: b, reason: collision with root package name */
    public static final dt<Boolean> f3453b;

    /* renamed from: c, reason: collision with root package name */
    public VivoIdentifier f3454c;

    public static class a extends dt<Boolean> {
        @Override // com.bytedance.a.dt
        public Boolean a(Object[] objArr) {
            String str;
            try {
                str = SystemProperties.get(cb.f3452a, OnekeyLoginConstants.CU_RESULT_SUCCESS);
            } catch (Throwable unused) {
                str = OnekeyLoginConstants.CU_RESULT_SUCCESS;
            }
            return Boolean.valueOf("1".equals(str));
        }
    }

    static {
        String str;
        try {
            str = new String(Base64.decode("cGVyc2lzdC5zeXMuaWRlbnRpZmllcmlkLnN1cHBvcnRlZA==".getBytes("UTF-8"), 2));
        } catch (Exception unused) {
            str = "";
        }
        f3452a = str;
        f3453b = new a();
    }

    public cb(Context context) {
        try {
            this.f3454c = new VivoIdentifier();
            this.f3454c.preloadOaid(context);
        } catch (Throwable th) {
            t.a("OaidVivo", Log.getStackTraceString(th), null);
        }
    }

    public static boolean a() {
        return f3453b.b(new Object[0]).booleanValue();
    }

    @Override // com.bytedance.a.bm
    public bm.a a(Context context) {
        VivoIdentifier vivoIdentifier = this.f3454c;
        if (vivoIdentifier == null) {
            return null;
        }
        return vivoIdentifier.getOaid(context);
    }

    @Override // com.bytedance.a.bm
    public boolean c(Context context) {
        return f3453b.b(new Object[0]).booleanValue();
    }
}
