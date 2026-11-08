package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class f {

    /* renamed from: c, reason: collision with root package name */
    private static final String f1816c = "f";

    /* renamed from: a, reason: collision with root package name */
    private final Set<String> f1817a;

    /* renamed from: b, reason: collision with root package name */
    private Looper f1818b;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.onGranted();
        }
    }

    class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f1820a;

        b(String str) {
            this.f1820a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.onDenied(this.f1820a);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.onGranted();
        }
    }

    class d implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f1823a;

        d(String str) {
            this.f1823a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.onDenied(this.f1823a);
        }
    }

    static /* synthetic */ class e {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1825a = new int[bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.d.values().length];

        static {
            try {
                f1825a[bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.d.GRANTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1825a[bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.d.DENIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1825a[bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.d.NOT_FOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public f() {
        this.f1817a = new HashSet(1);
        this.f1818b = Looper.getMainLooper();
    }

    protected final synchronized boolean a(@NonNull String str, int i) {
        if (i == 0) {
            return a(str, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.d.GRANTED);
        }
        return a(str, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.d.DENIED);
    }

    public abstract void onDenied(String str);

    public abstract void onGranted();

    public synchronized boolean shouldIgnorePermissionNotFound(String str) {
        Log.d(f1816c, "Permission not found: " + str);
        return true;
    }

    public f(@NonNull Looper looper) {
        this.f1817a = new HashSet(1);
        this.f1818b = Looper.getMainLooper();
        this.f1818b = looper;
    }

    protected final synchronized boolean a(@NonNull String str, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_case1.d dVar) {
        this.f1817a.remove(str);
        int i = e.f1825a[dVar.ordinal()];
        if (i != 1) {
            if (i == 2) {
                new Handler(this.f1818b).post(new b(str));
                return true;
            }
            if (i == 3) {
                if (shouldIgnorePermissionNotFound(str)) {
                    if (this.f1817a.isEmpty()) {
                        new Handler(this.f1818b).post(new c());
                        return true;
                    }
                } else {
                    new Handler(this.f1818b).post(new d(str));
                    return true;
                }
            }
        } else if (this.f1817a.isEmpty()) {
            new Handler(this.f1818b).post(new a());
            return true;
        }
        return false;
    }

    protected final synchronized void a(@NonNull String[] strArr) {
        Collections.addAll(this.f1817a, strArr);
    }
}
