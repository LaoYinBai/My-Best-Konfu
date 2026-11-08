package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d1 {

    /* renamed from: a, reason: collision with root package name */
    private final SharedPreferences f1322a;

    @WorkerThread
    d1(Context context) {
        this.f1322a = context.getSharedPreferences("device_register_oaid_refine", 0);
    }

    @WorkerThread
    void a(@Nullable c1 c1Var) {
        if (c1Var == null) {
            return;
        }
        this.f1322a.edit().putString("oaid", c1Var.b().toString()).apply();
    }

    @Nullable
    @WorkerThread
    c1 a() {
        return c1.a(this.f1322a.getString("oaid", ""));
    }
}
