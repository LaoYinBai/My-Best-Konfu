package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import android.annotation.SuppressLint;
import android.content.Context;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.a;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: b, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static volatile b f1967b;

    /* renamed from: a, reason: collision with root package name */
    private final bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.a f1968a;

    private b(Context context) {
        if (context == null) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d();
        } else {
            context.getApplicationContext();
        }
        this.f1968a = new a.b().a(10000L, TimeUnit.MILLISECONDS).b(10000L, TimeUnit.MILLISECONDS).c(10000L, TimeUnit.MILLISECONDS).a(true).a();
    }

    public static b b() {
        if (f1967b == null) {
            synchronized (b.class) {
                if (f1967b == null) {
                    f1967b = new b(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
                }
            }
        }
        return f1967b;
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.a a() {
        return this.f1968a;
    }
}
