package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private EnumC0049b f1447a;

    /* renamed from: b, reason: collision with root package name */
    private c f1448b;

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.b$b, reason: collision with other inner class name */
    public enum EnumC0049b {
        DEBUG,
        INFO,
        ERROR,
        OFF
    }

    public interface c {
        void a(String str, String str2);

        void b(String str, String str2);
    }

    private static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final b f1452a = new b();
    }

    public static void a(String str, String str2) {
        if (d.f1452a.f1447a.compareTo(EnumC0049b.DEBUG) <= 0) {
            d.f1452a.f1448b.a(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (d.f1452a.f1447a.compareTo(EnumC0049b.ERROR) <= 0) {
            d.f1452a.f1448b.b(str, str2);
        }
    }

    private b() {
        this.f1447a = EnumC0049b.OFF;
        this.f1448b = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.a();
    }
}
