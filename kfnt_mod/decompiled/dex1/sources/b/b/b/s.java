package b.b.b;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Base64;
import android.util.Log;
import b.b.b.p;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class s implements p {

    /* renamed from: a, reason: collision with root package name */
    public static final String f1182a;

    /* renamed from: b, reason: collision with root package name */
    public static final b<Boolean> f1183b;

    /* renamed from: c, reason: collision with root package name */
    public y f1184c;

    public static class a extends b<Boolean> {
        @Override // b.b.b.b
        public Boolean a(Object[] objArr) {
            String str;
            try {
                str = SystemProperties.get(s.f1182a, OnekeyLoginConstants.CU_RESULT_SUCCESS);
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
        f1182a = str;
        f1183b = new a();
    }

    public s(Context context) {
        try {
            this.f1184c = new y();
            this.f1184c.a(context);
        } catch (Throwable th) {
            g.b("OaidVivo", Log.getStackTraceString(th));
        }
    }

    public static boolean a() {
        return f1183b.b(new Object[0]).booleanValue();
    }

    @Override // b.b.b.p
    public boolean b(Context context) {
        return f1183b.b(new Object[0]).booleanValue();
    }

    @Override // b.b.b.p
    public p.a c(Context context) {
        y yVar = this.f1184c;
        if (yVar == null) {
            return null;
        }
        return yVar.a();
    }
}
