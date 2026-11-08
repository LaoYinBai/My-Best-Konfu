package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class q0 {

    /* renamed from: a, reason: collision with root package name */
    private static final AtomicBoolean f1370a = new AtomicBoolean(false);

    /* renamed from: b, reason: collision with root package name */
    private static String f1371b = "";

    static String a(SharedPreferences sharedPreferences) {
        if (f1370a.compareAndSet(false, true)) {
            f1371b = sharedPreferences.getString("cdid", "");
            if (TextUtils.isEmpty(f1371b)) {
                f1371b = UUID.randomUUID().toString();
                sharedPreferences.edit().putString("cdid", f1371b).apply();
            }
        }
        return f1371b;
    }
}
