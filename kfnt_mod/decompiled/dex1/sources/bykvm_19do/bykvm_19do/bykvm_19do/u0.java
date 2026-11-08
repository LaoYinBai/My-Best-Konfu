package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class u0 {

    static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final Runnable f1388a;

        /* renamed from: b, reason: collision with root package name */
        private final String f1389b;

        /* renamed from: c, reason: collision with root package name */
        private final String f1390c = Log.getStackTraceString(new RuntimeException("origin stacktrace"));

        a(Runnable runnable, String str) {
            this.f1388a = runnable;
            this.f1389b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f1388a.run();
            } catch (Exception e) {
                e.printStackTrace();
                t0.b("TrackerDr", "Thread:" + this.f1389b + " exception\n" + this.f1390c, e);
            }
        }
    }

    static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128) != null;
        } catch (Throwable unused) {
            return false;
        }
    }

    static void a(String str, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = "TrackerDr";
        }
        new Thread(new a(runnable, str), str).start();
    }
}
