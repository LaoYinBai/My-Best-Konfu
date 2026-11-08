package b.b.b;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final Runnable f1166a;

    /* renamed from: b, reason: collision with root package name */
    public final String f1167b;

    /* renamed from: c, reason: collision with root package name */
    public final String f1168c = Log.getStackTraceString(new RuntimeException("origin stacktrace"));

    public i(Runnable runnable, String str) {
        this.f1166a = runnable;
        this.f1167b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f1166a.run();
        } catch (Exception e) {
            e.printStackTrace();
            g.a("TrackerDr", "Thread:" + this.f1167b + " exception\n" + this.f1168c, e);
        }
    }
}
