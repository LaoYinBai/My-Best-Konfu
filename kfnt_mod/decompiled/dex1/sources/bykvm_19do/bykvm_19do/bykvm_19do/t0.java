package bykvm_19do.bykvm_19do.bykvm_19do;

import com.bytedance.mapplog_dr.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class t0 {

    /* renamed from: a, reason: collision with root package name */
    private static int f1383a;

    /* renamed from: b, reason: collision with root package name */
    private static com.bytedance.mapplog_dr.a f1384b;

    static {
        a(2);
        a(new a.C0113a());
    }

    public static void a(com.bytedance.mapplog_dr.a aVar) {
        f1384b = aVar;
    }

    public static void b(String str, String str2) {
        b(str, str2, null);
    }

    public static void a(int i) {
        f1383a = i;
    }

    public static void b(String str, String str2, Throwable th) {
        com.bytedance.mapplog_dr.a aVar = f1384b;
        if (aVar == null || f1383a > 3) {
            return;
        }
        aVar.b(str, str2, th);
    }

    public static void a(String str, String str2) {
        a(str, str2, null);
    }

    public static void a(String str, String str2, Throwable th) {
        com.bytedance.mapplog_dr.a aVar = f1384b;
        if (aVar == null || f1383a > 2) {
            return;
        }
        aVar.a(str, str2, th);
    }
}
