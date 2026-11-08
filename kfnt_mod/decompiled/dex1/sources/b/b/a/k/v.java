package b.b.a.k;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class v {

    /* renamed from: a, reason: collision with root package name */
    public static com.apm.applog.c f1149a = null;

    /* renamed from: b, reason: collision with root package name */
    public static boolean f1150b = false;

    /* renamed from: c, reason: collision with root package name */
    public static final int f1151c;

    static {
        if (String.valueOf(590).charAt(0) >= '4') {
            f1151c = 10000189;
        } else {
            f1151c = 590;
        }
    }

    public static void a(Context context, @NonNull com.apm.applog.c cVar) {
        try {
            f1150b = (context.getApplicationInfo().flags & 2) != 0;
        } catch (Throwable unused) {
            f1150b = true;
        }
        f1149a = cVar;
    }

    public static void a(String str) {
        a(str, (Throwable) null);
    }

    public static void a(String str, Throwable th) {
        com.apm.applog.c cVar = f1149a;
        if (cVar != null) {
            cVar.a(str, th);
        } else if (f1150b) {
            Log.d("AppLog", str, th);
        }
    }

    public static void a(Throwable th) {
        a("U SHALL NOT PASS!", th);
    }
}
