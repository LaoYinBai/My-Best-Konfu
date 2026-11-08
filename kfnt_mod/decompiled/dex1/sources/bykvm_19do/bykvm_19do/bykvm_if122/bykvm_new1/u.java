package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class u {

    /* renamed from: b, reason: collision with root package name */
    private static Map<String, u> f2031b = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private SharedPreferences f2032a;

    private u(String str, Context context) {
        if (context != null) {
            this.f2032a = context.getApplicationContext().getSharedPreferences(str, 0);
        }
    }

    public static u a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            str = "tt_ad_mediation_sdk_sp";
        }
        u uVar = f2031b.get(str);
        if (uVar != null) {
            return uVar;
        }
        u uVar2 = new u(str, context);
        f2031b.put(str, uVar2);
        return uVar2;
    }

    public String b() {
        return a("any_door_id", (String) null);
    }

    public long c(@NonNull String str) {
        return a(str, -1L);
    }

    public String d(@NonNull String str) {
        try {
            return a(str, "");
        } catch (Throwable unused) {
            return null;
        }
    }

    public void e(String str) {
        b("any_door_id", str);
    }

    public void f(@NonNull String str) {
        try {
            this.f2032a.edit().remove(str).apply();
        } catch (Throwable unused) {
        }
    }

    public void b(@NonNull String str, @NonNull String str2) {
        try {
            this.f2032a.edit().putString(str, str2).apply();
        } catch (Throwable unused) {
        }
    }

    public void b(@NonNull String str, int i) {
        try {
            this.f2032a.edit().putInt(str, i).apply();
        } catch (Throwable unused) {
        }
    }

    public int b(@NonNull String str) {
        return a(str, -1);
    }

    public void b(@NonNull String str, long j) {
        try {
            this.f2032a.edit().putLong(str, j).apply();
        } catch (Throwable unused) {
        }
    }

    public String a(@NonNull String str, @NonNull String str2) {
        try {
            return this.f2032a.getString(str, str2);
        } catch (Throwable unused) {
            return str2;
        }
    }

    public void b(@NonNull String str, float f) {
        try {
            this.f2032a.edit().putFloat(str, f).apply();
        } catch (Throwable unused) {
        }
    }

    public int a(@NonNull String str, int i) {
        try {
            return this.f2032a.getInt(str, i);
        } catch (Throwable unused) {
            return i;
        }
    }

    public void b(@NonNull String str, boolean z) {
        try {
            this.f2032a.edit().putBoolean(str, z).apply();
        } catch (Throwable unused) {
        }
    }

    public long a(@NonNull String str, long j) {
        try {
            return this.f2032a.getLong(str, j);
        } catch (Throwable unused) {
            return j;
        }
    }

    public float a(@NonNull String str, float f) {
        try {
            return this.f2032a.getFloat(str, f);
        } catch (Throwable unused) {
            return f;
        }
    }

    public boolean a(@NonNull String str) {
        return a(str, false);
    }

    public boolean a(@NonNull String str, boolean z) {
        try {
            return this.f2032a.getBoolean(str, z);
        } catch (Throwable unused) {
            return z;
        }
    }

    public void a() {
        try {
            this.f2032a.edit().clear().commit();
        } catch (Throwable unused) {
        }
    }
}
