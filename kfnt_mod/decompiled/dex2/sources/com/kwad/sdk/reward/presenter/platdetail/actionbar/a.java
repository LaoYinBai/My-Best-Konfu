package com.kwad.sdk.reward.presenter.platdetail.actionbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl;
import com.kwad.sdk.reward.widget.actionbar.ActionBarAppLandscape;
import com.kwad.sdk.reward.widget.actionbar.ActionBarAppPortrait;
import com.kwad.sdk.reward.widget.actionbar.ActionBarH5;
import com.kwad.sdk.utils.az;
import com.kwad.sdk.widget.KsLogoView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g {
    private KsLogoView b;
    private ActionBarAppLandscape c;
    private ActionBarAppPortrait d;
    private ActionBarH5 e;
    private AdTemplate f;
    private AdInfo g;
    private com.kwad.sdk.core.download.a.b h;
    private RewardActionBarControl i;
    private boolean k;

    @Nullable
    private ValueAnimator l;
    private boolean j = false;
    private RewardActionBarControl.b m = new RewardActionBarControl.b() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.a.1
        @Override // com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl.b
        public void a(boolean z) {
            a.this.k = true;
            a.this.b(z);
        }
    };
    private com.kwad.sdk.contentalliance.detail.video.d n = new e() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.a.2
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            super.c();
            a.this.i.a(a.this.a.k.f(), a.this.a.k.g());
        }
    };
    private f o = new f() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.a.3
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            a.this.k = false;
            a.this.c(false);
        }
    };

    private void a(final View view, int i) {
        e();
        view.setVisibility(0);
        this.l = az.a(view, 0, i);
        this.l.setInterpolator(new DecelerateInterpolator(2.0f));
        this.l.setDuration(300L);
        this.l.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.a.7
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                view.setVisibility(0);
            }
        });
        this.l.start();
    }

    private void b(final View view, int i) {
        e();
        view.setVisibility(0);
        this.l = az.a(view, i, 0);
        this.l.setInterpolator(new DecelerateInterpolator(2.0f));
        this.l.setDuration(300L);
        this.l.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.a.8
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                view.setVisibility(8);
            }
        });
        this.l.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.b.setVisibility(0);
        if (!com.kwad.sdk.core.response.a.a.B(this.g)) {
            f(z);
        } else if (this.a.f == 1) {
            d(z);
        } else {
            e(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        View view;
        if (this.j) {
            this.j = false;
            this.b.setVisibility(8);
            if (com.kwad.sdk.core.response.a.a.B(this.g)) {
                if (this.a.f == 1) {
                    if (!z) {
                        this.c.setVisibility(8);
                        return;
                    }
                    view = this.c;
                } else {
                    if (!z) {
                        this.d.setVisibility(8);
                        return;
                    }
                    view = this.d;
                }
            } else {
                if (!z) {
                    this.e.setVisibility(8);
                    return;
                }
                view = this.e;
            }
            b(view, com.kwad.sdk.a.kwai.a.a(q(), 90.0f));
        }
    }

    private void d(boolean z) {
        this.c.a(this.f, this.h, new ActionBarAppLandscape.a() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.a.4
            @Override // com.kwad.sdk.reward.widget.actionbar.ActionBarAppLandscape.a
            public void a(boolean z2) {
                a.this.a(z2);
            }
        });
        if (z) {
            a(this.c, com.kwad.sdk.a.kwai.a.a(q(), 90.0f));
        } else {
            this.c.setVisibility(0);
        }
    }

    private void e() {
        if (this.l != null) {
            this.l.removeAllListeners();
            this.l.cancel();
        }
    }

    private void e(boolean z) {
        this.d.a(this.f, this.h, new ActionBarAppPortrait.a() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.a.5
            @Override // com.kwad.sdk.reward.widget.actionbar.ActionBarAppPortrait.a
            public void a(boolean z2) {
                a.this.a(z2);
            }
        });
        if (z) {
            a(this.d, com.kwad.sdk.a.kwai.a.a(q(), 90.0f));
        } else {
            this.d.setVisibility(0);
        }
    }

    private void f(boolean z) {
        this.e.a(this.f, new ActionBarH5.a() { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.a.6
            @Override // com.kwad.sdk.reward.widget.actionbar.ActionBarH5.a
            public void a(boolean z2) {
                a.this.a(z2);
            }
        });
        if (z) {
            a(this.e, com.kwad.sdk.a.kwai.a.a(q(), 90.0f));
        } else {
            this.e.setVisibility(0);
        }
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.f = this.a.g;
        this.g = com.kwad.sdk.core.response.a.d.j(this.f);
        this.b.a(this.f);
        this.h = this.a.l;
        this.i = this.a.n;
        this.i.a(this.m);
        this.a.k.a(this.n);
        this.a.a(this.o);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.i.a((RewardActionBarControl.b) null);
        this.a.k.b(this.n);
        this.a.b(this.o);
        e();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (KsLogoView) a(R.id.ksad_ad_label_play_bar);
        this.c = (ActionBarAppLandscape) a(R.id.ksad_video_play_bar_app_landscape);
        this.d = (ActionBarAppPortrait) a(R.id.ksad_video_play_bar_app_portrait);
        this.e = (ActionBarH5) a(R.id.ksad_video_play_bar_h5);
    }
}
