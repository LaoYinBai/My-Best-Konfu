package com.bytedance.bdturing;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.Toast;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static final int f3739a = 0;

    /* renamed from: b, reason: collision with root package name */
    public static final int f3740b = 1;

    /* renamed from: c, reason: collision with root package name */
    public static final int f3741c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 998;
    public static final int i = 999;
    public static final int j = 1;
    public static final int k = 2;
    private static final String l = "BdTuring";
    private j m;
    private com.bytedance.bdturing.a.c n;
    private c o;
    private i p;
    private i q;
    private boolean r;
    private b s;

    /* renamed from: com.bytedance.bdturing.a$a, reason: collision with other inner class name */
    private static class C0106a {

        /* renamed from: a, reason: collision with root package name */
        private static a f3753a = new a();

        private C0106a() {
        }
    }

    private a() {
        this.r = false;
    }

    public static a a() {
        return C0106a.f3753a;
    }

    private boolean b(c cVar) {
        return (cVar == null || cVar.n() == null) ? false : true;
    }

    public synchronized a a(final c cVar) {
        if (this.r) {
            return this;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!b(cVar)) {
            return null;
        }
        this.o = cVar;
        this.m = j.a();
        this.m.b();
        e.a(cVar);
        this.n = new com.bytedance.bdturing.a.c(cVar);
        this.m.a(new Runnable() { // from class: com.bytedance.bdturing.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.this.n.a(cVar.n());
            }
        });
        this.r = true;
        e.a(System.currentTimeMillis() - jCurrentTimeMillis);
        return this;
    }

    public void a(final Activity activity, final int i2, final b bVar) throws JSONException, InterruptedException {
        g.a(l, "BdTuring showVerifyDialog");
        if (!this.r || bVar == null) {
            return;
        }
        e.a(this.o.r(), this.o.t(), this.o.s());
        if (Build.VERSION.SDK_INT < 17) {
            Toast.makeText(activity, "系统版本过低，请升级系统版本", 1).show();
            bVar.a(i);
            e.a(i2, Build.VERSION.SDK_INT);
            return;
        }
        this.m.a(new Runnable() { // from class: com.bytedance.bdturing.a.2
            @Override // java.lang.Runnable
            public void run() {
                final String strA = a.this.n.a(activity, i2);
                if (activity.isFinishing()) {
                    return;
                }
                activity.runOnUiThread(new Runnable() { // from class: com.bytedance.bdturing.a.2.1
                    @Override // java.lang.Runnable
                    public void run() throws JSONException {
                        if (a.this.p != null && a.this.p.isShowing()) {
                            if (a.this.s != null) {
                                a.this.p.a();
                                a.this.s.a(a.h);
                                e.b(i2);
                                a.this.s = null;
                            }
                            a.this.p.c();
                        }
                        a.this.a(activity, i2, bVar, strA);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity, int i2, b bVar, String str) throws JSONException {
        e.a();
        this.s = bVar;
        this.p = new i(activity, i2, this.o.o(), str, this.s);
        this.p.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.bytedance.bdturing.a.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (dialogInterface.equals(a.this.p)) {
                    a.this.q = a.this.p;
                    a.this.p = null;
                }
            }
        });
        this.p.show();
        e.a(i2);
    }

    public void b() {
        if (this.r && this.p != null && this.p.isShowing()) {
            this.p.dismiss();
        }
    }

    public c c() {
        return this.o;
    }

    public void a(boolean z) {
        if (z) {
            g.a();
        } else {
            g.b();
        }
    }

    i d() {
        if (this.p != null) {
            return this.p;
        }
        return this.q;
    }

    void a(i iVar) {
        if (this.q == iVar) {
            this.q = null;
        }
    }

    void e() {
        if (this.n != null) {
            this.n.a();
        }
    }
}
