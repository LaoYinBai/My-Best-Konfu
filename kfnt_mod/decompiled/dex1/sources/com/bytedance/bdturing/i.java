package com.bytedance.bdturing;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ss.union.game.sdk.account.R;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class i extends Dialog {
    private static final int A = 300;
    private static final int B = 304;
    private static final long C = 10000;
    private static final String D = "mask_click_close";
    private static final String E = "back_close";
    private static final String F = "app_close";
    private static final String G = "override_close";

    /* renamed from: a, reason: collision with root package name */
    private static final String f3797a = "VerifyDialog";
    private static final int z = 0;
    private String H;
    private int I;
    private f J;

    /* renamed from: b, reason: collision with root package name */
    private ViewGroup f3798b;

    /* renamed from: c, reason: collision with root package name */
    private com.bytedance.bdturing.c.f f3799c;
    private VerifyWebView d;
    private ViewGroup e;
    private TextView f;
    private Button g;
    private Button h;
    private ViewGroup.LayoutParams i;
    private Application j;
    private int k;
    private int l;
    private int m;
    private double n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private String u;
    private String v;
    private b w;
    private h x;
    private DialogInterface.OnDismissListener y;

    i(Activity activity, int i, boolean z2, String str, b bVar) {
        super(activity, R.style.DialogTheme);
        this.f3798b = null;
        this.i = null;
        this.k = 300;
        this.l = 331;
        this.m = -1;
        this.n = 0.5d;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = true;
        this.s = true;
        this.v = null;
        this.x = new h();
        this.H = F;
        this.J = new f() { // from class: com.bytedance.bdturing.i.8
            @Override // com.bytedance.bdturing.f
            public void a(int i2, String str2, String str3, String str4, String str5) throws JSONException {
                boolean z3 = i2 == 0;
                e.a(i2, str2, i.this.m);
                if (i.this.s && i.this.w != null) {
                    if (z3) {
                        i.this.w.a(i2, str4, str5);
                    } else {
                        i.this.w.a(i2);
                    }
                    i.this.w = null;
                }
                i.this.q = true;
                i.this.dismiss();
            }

            @Override // com.bytedance.bdturing.f
            public void a(int i2, int i3) {
                i.this.a(i2, i3, false);
            }

            @Override // com.bytedance.bdturing.f
            public void a() throws JSONException {
                i.this.p = true;
                j.a().a(7, (Object) null);
                e.c(i.this.I);
            }

            @Override // com.bytedance.bdturing.f
            public void a(int i2, String str2) throws JSONException {
                i.this.p = false;
                i.this.v = i.this.a(i2);
                i.this.a(300, 304, true);
                e.a(i.this.I, i2, str2);
            }

            @Override // com.bytedance.bdturing.f
            public void b() {
                i.this.b();
            }
        };
        this.I = i;
        this.t = z2;
        this.u = str;
        this.w = bVar;
        if (this.w == null) {
            this.w = new a();
        }
        c cVarC = com.bytedance.bdturing.a.a().c();
        this.j = (Application) cVarC.n();
        if (cVarC != null) {
            this.o = cVarC.y();
            if (this.I == 2) {
                this.m = cVarC.w();
            }
        }
        f();
        h();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3798b = (ViewGroup) LayoutInflater.from(this.j).inflate(R.layout.layout_verify_dialog, (ViewGroup) null);
        setContentView(this.f3798b);
        d();
        e();
        setCanceledOnTouchOutside(this.o);
        setCancelable(true);
        g.a(f3797a, "loadUrl = " + this.u);
        this.f3799c = new com.bytedance.bdturing.c.f(this.J, this.d);
        this.d.loadUrl(this.u);
    }

    private void d() {
        this.e = (ViewGroup) findViewById(R.id.view_feedback);
        this.f = (TextView) findViewById(R.id.text_feedback_content);
        this.g = (Button) findViewById(R.id.btn_feedback);
        this.h = (Button) findViewById(R.id.btn_feedback_close);
        this.d = new VerifyWebView(this.j);
        this.f3798b.addView(this.d);
    }

    private void e() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.bytedance.bdturing.i.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_feedback_close) {
                    i.this.H = e.d;
                } else if (id == R.id.btn_feedback) {
                    i.this.H = e.e;
                }
                i.this.dismiss();
            }
        };
        this.g.setOnClickListener(onClickListener);
        this.h.setOnClickListener(onClickListener);
        this.i = this.d.getLayoutParams();
        if (this.i != null) {
            this.i.width = -1;
            this.i.height = -1;
            this.d.setLayoutParams(this.i);
        }
        this.d.setCallback(this.J);
        this.d.a(this.t);
        g();
    }

    private void f() {
        if (this.I == 2) {
            this.k = com.bytedance.bdturing.a.b.a();
            this.l = com.bytedance.bdturing.a.b.b();
            this.n = com.bytedance.bdturing.a.b.c();
        } else if (this.I == 1) {
            this.k = -1;
            this.l = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, final boolean z2) {
        if (i == this.k && i2 == this.l) {
            return;
        }
        this.k = i;
        this.l = i2;
        if (isShowing()) {
            getWindow().getDecorView().post(new Runnable() { // from class: com.bytedance.bdturing.i.2
                @Override // java.lang.Runnable
                public void run() {
                    i.this.g();
                    if (z2) {
                        i.this.e.setVisibility(0);
                        i.this.f.setText(i.this.v);
                        i.this.d.setVisibility(8);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        int i;
        int i2;
        if (this.k > 0 && this.l > 0) {
            DisplayMetrics displayMetrics = this.j.getResources().getDisplayMetrics();
            i = (int) ((displayMetrics.density * (this.k + 0)) + 0.5f);
            i2 = (int) ((displayMetrics.density * (this.l + 0)) + 0.5f);
            if (g.c()) {
                Toast.makeText(this.j, "density = " + displayMetrics.density + ", width = " + i, 1).show();
                g.a(f3797a, "density = " + displayMetrics.density + ", width = " + i);
            }
        } else {
            i = this.k;
            i2 = this.l;
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = i;
        attributes.height = i2;
        attributes.gravity = 17;
        attributes.dimAmount = (float) this.n;
        try {
            getWindow().setAttributes(attributes);
        } catch (IllegalArgumentException e) {
            g.a(e);
        }
        if (this.i == null) {
            this.i = this.d.getLayoutParams();
            if (this.i != null) {
                this.i.width = -1;
                this.i.height = -1;
                this.d.setLayoutParams(this.i);
            }
        }
    }

    public void a() {
        this.s = false;
    }

    public synchronized void b() {
        g.a(f3797a, "clearResource()");
        if (this.j == null && this.f3799c == null) {
            return;
        }
        if (this.d != null) {
            this.d.post(new Runnable() { // from class: com.bytedance.bdturing.i.3

                /* renamed from: b, reason: collision with root package name */
                private WebView f3804b;

                {
                    this.f3804b = i.this.d;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.f3804b.stopLoading();
                    this.f3804b.loadUrl("about:blank");
                    this.f3804b.clearCache(true);
                    this.f3804b.clearHistory();
                    ViewParent parent = this.f3804b.getParent();
                    if (parent != null && (parent instanceof ViewGroup)) {
                        ((ViewGroup) parent).removeView(this.f3804b);
                    }
                    this.f3804b.destroy();
                }
            });
            this.d = null;
        }
        this.j = null;
        this.f3799c.a();
        this.f3799c = null;
        if (isShowing()) {
            if (this.r) {
                getWindow().getDecorView().post(new Runnable() { // from class: com.bytedance.bdturing.i.4
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            i.super.dismiss();
                        } catch (IllegalArgumentException e) {
                            g.a(e);
                        }
                    }
                });
            } else {
                g.c(f3797a, "VerifyDialog has been detached");
            }
        }
        com.bytedance.bdturing.a.a().a(this);
        j.a().a(5, (Object) null);
    }

    public boolean a(String str) {
        if (this.d == null || this.f3799c == null) {
            g.c(f3797a, "(mWebView == null || mJsBridge == null) ");
            return false;
        }
        this.f3799c.a(str);
        return true;
    }

    private void b(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("style", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a(com.bytedance.bdturing.c.i.a(1, com.bytedance.bdturing.c.i.k, "call", jSONObject, com.bytedance.bdturing.c.i.k));
    }

    public void c() {
        this.H = G;
        dismiss();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.y != null) {
            this.y.onDismiss(this);
        }
        j.a().a(8, this, C);
        getWindow().getDecorView().post(new Runnable() { // from class: com.bytedance.bdturing.i.5
            @Override // java.lang.Runnable
            public void run() {
                i.super.hide();
            }
        });
        if (this.s && this.w != null) {
            if (this.p) {
                this.w.a(2);
            } else {
                this.w.a(3);
            }
            this.w = null;
        }
        if (!this.q) {
            b(this.H);
        }
        if (!this.p) {
            e.a(this.H);
            b();
        }
        j.a().a(11, (Object) null);
    }

    @Override // android.app.Dialog
    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.y = onDismissListener;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        g.a(f3797a, "onDetachedFromWindow");
        this.r = false;
        super.onDetachedFromWindow();
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.x.a(false, motionEvent);
        if (this.o) {
            if (this.e.getVisibility() == 0) {
                this.H = e.f3794c;
            } else {
                this.H = D;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void h() {
        setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.bytedance.bdturing.i.6
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
            }
        });
        setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.bytedance.bdturing.i.7
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                int action = keyEvent.getAction();
                if (keyCode != 4 || action != 1) {
                    return false;
                }
                if (i.this.e.getVisibility() == 0) {
                    i.this.H = e.f;
                    return false;
                }
                if (i.this.d == null || !i.this.d.canGoBack()) {
                    i.this.H = i.E;
                    return false;
                }
                i.this.d.goBack();
                return true;
            }
        });
    }

    @Override // android.app.Dialog
    protected void onStop() {
        g.d(f3797a, " onStop ");
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(int i) {
        return this.j.getResources().getString(R.string.feedback_text_content, Integer.valueOf(i));
    }

    private class a implements b {
        @Override // com.bytedance.bdturing.b
        public void a(int i) {
        }

        @Override // com.bytedance.bdturing.b
        public void a(int i, String str, String str2) {
        }

        private a() {
        }
    }
}
