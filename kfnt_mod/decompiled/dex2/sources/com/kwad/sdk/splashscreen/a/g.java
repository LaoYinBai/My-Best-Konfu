package com.kwad.sdk.splashscreen.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.widget.KsShakeView;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.utils.ax;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends Presenter implements View.OnClickListener, com.kwad.sdk.core.g.a, com.kwad.sdk.widget.d {
    private static long a = 400;
    private AdTemplate b;
    private View c;
    private KsShakeView d;
    private TextView e;
    private com.kwad.sdk.core.g.b f;
    private Vibrator g;
    private boolean h;

    @Nullable
    private com.kwad.sdk.splashscreen.g i;
    private com.kwad.sdk.splashscreen.a j;
    private com.kwad.sdk.splashscreen.e k;

    private void e() {
        if (this.e != null) {
            this.e.setText(this.k.a());
        }
        if (this.d != null) {
            this.d.a(this.k.b());
        }
    }

    private void f() {
        if (this.i != null) {
            if (this.j == null) {
                this.j = new com.kwad.sdk.splashscreen.a(this.b) { // from class: com.kwad.sdk.splashscreen.a.g.2
                    @Override // com.kwad.sdk.splashscreen.a
                    @SuppressLint({"SetTextI18n"})
                    protected void a(String str) {
                        if (g.this.e != null) {
                            g.this.e.setText("或点击" + str);
                        }
                    }
                };
            } else {
                this.j.a(this.b);
            }
            com.kwad.sdk.core.download.a.b bVar = this.i.g;
            if (bVar != null) {
                bVar.a(this.j);
            }
        }
    }

    private void g() {
        if (this.c == null || this.i == null) {
            return;
        }
        this.c.setVisibility(0);
        com.kwad.sdk.core.report.a.c(this.b, 19, null);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        Object objP = p();
        if (objP instanceof com.kwad.sdk.splashscreen.g) {
            this.i = (com.kwad.sdk.splashscreen.g) objP;
        }
        if (this.i == null) {
            return;
        }
        this.b = this.i.d;
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.b);
        this.k = com.kwad.sdk.splashscreen.e.a(this.b, adInfoJ, this.i.g);
        float fO = com.kwad.sdk.core.response.a.b.o(this.b);
        if (this.f == null) {
            this.f = new com.kwad.sdk.core.g.b(fO);
            this.f.a(this);
        } else {
            this.f.a(fO);
        }
        e();
        if (com.kwad.sdk.core.response.a.a.B(adInfoJ)) {
            f();
        }
        g();
        this.f.a(q());
        this.d.post(new Runnable() { // from class: com.kwad.sdk.splashscreen.a.g.1
            @Override // java.lang.Runnable
            public void run() {
                g.this.d.a();
            }
        });
        com.kwad.sdk.splashscreen.kwai.a.a(q());
        this.h = com.kwad.sdk.core.response.a.c.g(this.i.d);
        new com.kwad.sdk.widget.f(this.d.getContext(), this.d, this);
    }

    @Override // com.kwad.sdk.core.g.a
    public void a(final double d) {
        boolean zIsShown = this.d.isShown();
        com.kwad.sdk.core.d.a.a("SplashShakePresenter", "onShakeEvent called isShakeViewShown: " + zIsShown);
        if (zIsShown) {
            this.d.a(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.splashscreen.a.g.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (g.this.i != null) {
                        g.this.i.a(g.this.q(), 157, 2, d);
                    }
                }
            });
            if (this.g != null) {
                if (Build.VERSION.SDK_INT >= 26) {
                    this.g.vibrate(VibrationEffect.createOneShot(a, -1));
                } else {
                    this.g.vibrate(a);
                }
            }
            ax.a(new Runnable() { // from class: com.kwad.sdk.splashscreen.a.g.4
                @Override // java.lang.Runnable
                public void run() {
                    g.this.f.a();
                }
            }, null, 1000L);
        }
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        com.kwad.sdk.core.d.a.a("SplashShakePresenter", "onSingleTap: " + view);
        if (com.kwad.sdk.core.response.a.b.c(com.kwad.sdk.core.response.a.d.j(this.b)) && this.i != null) {
            this.i.a(q(), 158, 1);
        }
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        com.kwad.sdk.core.d.a.a("SplashShakePresenter", "onSlide: enableSlickClick: " + this.h);
        if (this.h && this.i != null) {
            this.i.a(view.getContext(), 153, 1);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        com.kwad.sdk.core.d.a.a("SplashShakePresenter", "onUnbind");
        if (this.f != null) {
            this.f.b(q());
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        Context contextQ = q();
        if (contextQ != null) {
            this.g = (Vibrator) contextQ.getSystemService("vibrator");
        }
        this.c = a(R.id.ksad_shake_layout);
        this.e = (TextView) a(R.id.ksad_shake_action);
        this.d = (KsShakeView) a(R.id.ksad_shake_view);
        this.d.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        com.kwad.sdk.core.d.a.a("SplashShakePresenter", "onClick: " + view);
        if (!view.equals(this.d) || this.i == null || this.b == null || !com.kwad.sdk.core.response.a.b.c(com.kwad.sdk.core.response.a.d.j(this.b))) {
            return;
        }
        this.i.a(q(), 158, 1);
    }
}
