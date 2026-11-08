package b.b.a.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f1127a;

    public e(f fVar) {
        this.f1127a = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (int i = 0; i < this.f1127a.f1128a.size(); i++) {
            try {
                for (String str : new String[]{"openudid", "clientudid", "serial_number", "sim_serial_number", "udid", com.bytedance.b.a.c.h.x}) {
                    try {
                        this.f1127a.a(this.f1127a.f1128a.get(i), str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
    }
}
