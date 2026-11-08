package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_19do.bykvm_19do;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private EnumC0045b f1404a;

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_19do.bykvm_19do.b$b, reason: collision with other inner class name */
    public enum EnumC0045b {
        DEBUG,
        INFO,
        ERROR,
        OFF
    }

    public interface c {
    }

    private static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final b f1408a = new b();
    }

    public static void a(EnumC0045b enumC0045b) {
        synchronized (b.class) {
            d.f1408a.f1404a = enumC0045b;
        }
    }

    private b() {
        EnumC0045b enumC0045b = EnumC0045b.OFF;
        new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_19do.bykvm_19do.a();
    }
}
