package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class s0 {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1376a = s0.class.getSimpleName() + "#";

    /* renamed from: b, reason: collision with root package name */
    private static o0<z0> f1377b = new a();

    static class a extends o0<z0> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // bykvm_19do.bykvm_19do.bykvm_19do.o0
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public z0 a(Object... objArr) {
            return new z0((Context) objArr[0]);
        }
    }

    public static void a(Context context) {
        f1377b.b(context).a();
    }

    public static String a(SharedPreferences sharedPreferences) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        String strA = q0.a(sharedPreferences);
        t0.b("TrackerDr", f1376a + "getCdid takes " + (SystemClock.elapsedRealtime() - jElapsedRealtime) + " ms");
        return strA;
    }

    public static Map<String, String> a(Context context, SharedPreferences sharedPreferences) throws Throwable {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Map<String, String> mapA = f1377b.b(context).a(100L);
        t0.b("TrackerDr", f1376a + "getOaid takes " + (SystemClock.elapsedRealtime() - jElapsedRealtime) + " ms");
        return mapA;
    }

    public static String a(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optString("id", null);
        }
        return null;
    }

    public static void a(e eVar) {
        z0.a(eVar);
    }
}
