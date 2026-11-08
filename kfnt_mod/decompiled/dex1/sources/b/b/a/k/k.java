package b.b.a.k;

import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    public static o f1138a;

    static {
        o oVar;
        try {
            Object objInvoke = Class.forName("com.bytedance.applog.et_verify.EventVerify").getMethod("inst", new Class[0]).invoke(null, new Object[0]);
            oVar = objInvoke instanceof o ? (o) objInvoke : null;
        } catch (Exception unused) {
            oVar = null;
        } catch (Throwable th) {
            v.a("can't find event verify, should compile with ET", (Throwable) null);
            throw th;
        }
        if (oVar == null) {
            v.a("can't find event verify, should compile with ET", (Throwable) null);
        } else {
            v.a(oVar.toString());
        }
        f1138a = oVar;
    }

    public static void a(String str, JSONArray jSONArray) {
        o oVar = f1138a;
        if (oVar != null) {
            oVar.a(str, jSONArray);
        }
    }

    public static boolean a() {
        o oVar = f1138a;
        if (oVar != null) {
            return oVar.a();
        }
        return false;
    }
}
