package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12;

import android.content.Context;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_19do.bykvm_19do.b;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.g;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.h;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.c;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.f;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.v;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private v f1409a;

    /* renamed from: b, reason: collision with root package name */
    private g f1410b;

    /* renamed from: c, reason: collision with root package name */
    private int f1411c;

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        int f1412a;

        /* renamed from: b, reason: collision with root package name */
        int f1413b;

        /* renamed from: c, reason: collision with root package name */
        int f1414c;
        boolean d = true;

        public b() {
            new ArrayList();
            this.f1412a = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
            this.f1413b = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
            this.f1414c = NetCallback.ERROR_CODE_REQUEST_SUCCESS;
        }

        public b a(long j, TimeUnit timeUnit) {
            this.f1412a = a("timeout", j, timeUnit);
            return this;
        }

        public b b(long j, TimeUnit timeUnit) {
            this.f1413b = a("timeout", j, timeUnit);
            return this;
        }

        public b c(long j, TimeUnit timeUnit) {
            this.f1414c = a("timeout", j, timeUnit);
            return this;
        }

        public b a(boolean z) {
            this.d = z;
            return this;
        }

        private static int a(String str, long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException(str + " < 0");
            }
            if (timeUnit != null) {
                long millis = timeUnit.toMillis(j);
                if (millis > 2147483647L) {
                    throw new IllegalArgumentException(str + " too large.");
                }
                if (millis != 0 || j <= 0) {
                    return (int) millis;
                }
                throw new IllegalArgumentException(str + " too small.");
            }
            throw new NullPointerException("unit == null");
        }

        public a a() {
            return new a(this);
        }
    }

    public static void c() {
        bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_19do.bykvm_19do.b.a(b.EnumC0045b.DEBUG);
    }

    public void a(Context context, boolean z, boolean z2, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12.b bVar) {
        if (context == null) {
            throw new IllegalArgumentException("tryInitAdTTNet context is null");
        }
        if (bVar == null) {
            throw new IllegalArgumentException("tryInitAdTTNet ITTAdNetDepend is null");
        }
        this.f1411c = bVar.c();
        g gVar = this.f1410b;
        if (gVar != null) {
            gVar.a(this.f1411c);
        }
        h.a().a(this.f1411c).a(z2);
        h.a().a(this.f1411c).a(bVar);
        h.a().a(this.f1411c).a(context, f.b(context));
        if (f.c(context) || (!f.b(context) && z)) {
            h.a().a(this.f1411c, context).d();
            h.a().a(this.f1411c, context).f();
        }
        if (f.b(context)) {
            h.a().a(this.f1411c, context).d();
            h.a().a(this.f1411c, context).f();
        }
    }

    public c b() {
        return new c(this.f1409a);
    }

    private a(b bVar) {
        v.b bVarB = new v.b().a(bVar.f1412a, TimeUnit.MILLISECONDS).c(bVar.f1414c, TimeUnit.MILLISECONDS).b(bVar.f1413b, TimeUnit.MILLISECONDS);
        if (bVar.d) {
            this.f1410b = new g();
            bVarB.a(this.f1410b);
        }
        this.f1409a = bVarB.a();
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.a a() {
        return new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122.a(this.f1409a);
    }
}
