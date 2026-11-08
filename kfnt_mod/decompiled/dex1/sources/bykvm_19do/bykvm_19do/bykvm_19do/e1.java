package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Base64;
import android.util.Log;
import bykvm_19do.bykvm_19do.bykvm_19do.a1;
import com.bytedance.mapplog_dr.VivoIdentifier;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class e1 implements a1 {

    /* renamed from: b, reason: collision with root package name */
    private static final String f1328b = a("cGVyc2lzdC5zeXMuaWRlbnRpZmllcmlkLnN1cHBvcnRlZA==");

    /* renamed from: c, reason: collision with root package name */
    private static final o0<Boolean> f1329c = new a();

    /* renamed from: a, reason: collision with root package name */
    private VivoIdentifier f1330a;

    static class a extends o0<Boolean> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // bykvm_19do.bykvm_19do.bykvm_19do.o0
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Boolean a(Object... objArr) {
            return Boolean.valueOf("1".equals(e1.b(e1.f1328b, OnekeyLoginConstants.CU_RESULT_SUCCESS)));
        }
    }

    e1(Context context) {
        try {
            j1.a(context);
        } catch (Throwable unused) {
        }
        try {
            this.f1330a = new VivoIdentifier();
            this.f1330a.preloadOaid(context);
        } catch (Throwable th) {
            t0.b("OaidVivo", Log.getStackTraceString(th));
        }
    }

    static boolean a() {
        return f1329c.b(new Object[0]).booleanValue();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    public a1.a b(Context context) {
        VivoIdentifier vivoIdentifier = this.f1330a;
        if (vivoIdentifier == null) {
            return null;
        }
        return vivoIdentifier.getOaid(context);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    public boolean a(Context context) {
        return a();
    }

    public static String a(String str) {
        try {
            return new String(Base64.decode(str.getBytes("UTF-8"), 2));
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2) {
        try {
            return SystemProperties.get(str, str2);
        } catch (Throwable unused) {
            return str2;
        }
    }
}
