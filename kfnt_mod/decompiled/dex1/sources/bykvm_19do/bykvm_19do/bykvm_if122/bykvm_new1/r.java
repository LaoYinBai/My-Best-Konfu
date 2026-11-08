package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.text.TextUtils;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class r {

    /* renamed from: a, reason: collision with root package name */
    private static volatile String f2026a = "";

    static {
        Executors.newSingleThreadExecutor();
        try {
            if (TextUtils.isEmpty(f2026a)) {
                f2026a = u.a("tt_device_info", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).a("oaid", "");
            }
        } catch (Throwable unused) {
        }
    }

    public static String a() {
        try {
            if (TextUtils.isEmpty(f2026a)) {
                f2026a = m.d();
                a(f2026a);
            }
        } catch (Throwable unused) {
        }
        return f2026a == null ? "" : f2026a;
    }

    private static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        u.a("tt_device_info", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).b("oaid", str);
    }
}
