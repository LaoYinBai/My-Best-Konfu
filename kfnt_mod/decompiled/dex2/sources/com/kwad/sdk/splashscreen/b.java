package com.kwad.sdk.splashscreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsSplashScreenAd;
import com.kwad.sdk.api.loader.Wrapper;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.utils.bd;
import com.kwad.sdk.utils.z;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private Context a;

    @Nullable
    private WindowManager b;
    private ImageView c;
    private com.kwad.sdk.splashscreen.b.a d;
    private KsSplashScreenAd.SplashScreenAdInteractionListener e;
    private ViewGroup f;
    private FrameLayout g;
    private View h;
    private ImageView i;
    private boolean j;
    private long k;
    private boolean l;
    private long m;
    private boolean n;
    private final WindowManager.LayoutParams o = new WindowManager.LayoutParams();

    /* renamed from: com.kwad.sdk.splashscreen.b$3, reason: invalid class name */
    class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ Rect a;

        AnonymousClass3(Rect rect) {
            this.a = rect;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            b.this.o.x = this.a.left - com.kwad.sdk.a.kwai.a.a(b.this.a, 6.0f);
            b.this.o.y = this.a.top - com.kwad.sdk.a.kwai.a.a(b.this.a, 6.0f);
            b.this.o.width = (this.a.right - this.a.left) + com.kwad.sdk.a.kwai.a.a(b.this.a, 12.0f);
            b.this.o.height = (this.a.bottom - this.a.top) + com.kwad.sdk.a.kwai.a.a(b.this.a, 12.0f);
            b.this.c.animate().cancel();
            b.this.i.setImageDrawable(new BitmapDrawable(b.this.a.getResources(), b.this.d.a));
            ViewParent parent = b.this.d.a().getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(b.this.d.a());
            }
            b.this.g.addView(b.this.d.a(), new ViewGroup.MarginLayoutParams(-1, -1));
            if (b.this.b != null) {
                try {
                    b.this.b.addView(b.this.f, b.this.o);
                } catch (Exception e) {
                    com.kwad.sdk.core.kwai.a.a(e);
                    com.kwad.sdk.core.d.a.b(e);
                }
            }
            b.this.d.d();
            b.this.d.a(new com.kwad.sdk.contentalliance.detail.video.d() { // from class: com.kwad.sdk.splashscreen.b.3.1
                private boolean b = false;

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void a() {
                }

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void a(int i, int i2) {
                }

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void a(long j, long j2) {
                    b.this.b();
                    b.this.c();
                }

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void b() {
                }

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void c() {
                }

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void d() {
                }

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void e() {
                }

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void f() {
                    if (this.b) {
                        return;
                    }
                    this.b = true;
                    if (b.this.e != null) {
                        b.this.e.onAdShowEnd();
                    }
                    ax.a(new Runnable() { // from class: com.kwad.sdk.splashscreen.b.3.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            b.this.d.h();
                        }
                    }, 0L);
                    if (b.this.b != null) {
                        try {
                            if (Build.VERSION.SDK_INT < 19 || b.this.f.isAttachedToWindow()) {
                                WindowManager windowManager = b.this.b;
                                ViewGroup viewGroup = b.this.f;
                                windowManager.removeView(viewGroup);
                            }
                        } catch (Exception e2) {
                            com.kwad.sdk.core.kwai.a.a(e2);
                        }
                    }
                }

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void g() {
                }

                @Override // com.kwad.sdk.contentalliance.detail.video.d
                public void h() {
                }
            });
            b.this.c.postDelayed(new bd(new Runnable() { // from class: com.kwad.sdk.splashscreen.b.3.2
                @Override // java.lang.Runnable
                public void run() {
                    b.this.l = true;
                    b.this.a();
                }
            }), 100L);
            b.this.m = System.currentTimeMillis() + 100;
            b.this.i.postDelayed(new bd(new Runnable() { // from class: com.kwad.sdk.splashscreen.b.3.3
                @Override // java.lang.Runnable
                public void run() {
                    b.this.i.setVisibility(8);
                    b.this.j = true;
                }
            }), 200L);
            b.this.k = System.currentTimeMillis() + 200;
        }
    }

    @RequiresApi(api = 21)
    private static class a extends ViewOutlineProvider {
        private float a;

        a(float f) {
            this.a = f;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            int i = (int) this.a;
            int i2 = (int) this.a;
            outline.setRoundRect(new Rect(i, i2, (rect.right - rect.left) - i, (rect.bottom - rect.top) - i2), this.a);
        }
    }

    @SuppressLint({"ClickableViewAccessibility", "InflateParams"})
    public b(Context context, String str, boolean z, KsSplashScreenAd.SplashScreenAdInteractionListener splashScreenAdInteractionListener) {
        if (context == null) {
            return;
        }
        this.a = Wrapper.wrapContextIfNeed(context);
        this.e = splashScreenAdInteractionListener;
        this.n = z;
        this.b = (WindowManager) this.a.getSystemService("window");
        this.d = SplashPlayModuleCache.a().a(str);
        if (this.d == null || this.b == null) {
            return;
        }
        this.c = new ImageView(this.a);
        this.c.setImageDrawable(new BitmapDrawable(this.a.getResources(), this.d.a));
        final AdTemplate adTemplateB = this.d.b();
        final com.kwad.sdk.core.download.a.b bVar = new com.kwad.sdk.core.download.a.b(adTemplateB);
        this.f = (ViewGroup) LayoutInflater.from(this.a).inflate(R.layout.ksad_splash_vplush, (ViewGroup) null);
        this.i = (ImageView) this.f.findViewById(R.id.ksad_splash_frame);
        this.g = (FrameLayout) this.f.findViewById(R.id.ksad_splash_texture);
        this.h = this.f.findViewById(R.id.ksad_splash_close_btn);
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.splashscreen.b.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (b.this.n) {
                    com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(adTemplateB).a(bVar).a(false).a(2).a(new a.b() { // from class: com.kwad.sdk.splashscreen.b.1.1
                        @Override // com.kwad.sdk.core.download.a.a.b
                        public void a() throws JSONException {
                            if (b.this.e != null) {
                                b.this.e.onAdClicked();
                            }
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("duration", b.this.d.c());
                            } catch (JSONException e) {
                                com.kwad.sdk.core.d.a.b(e);
                            }
                            com.kwad.sdk.core.report.a.a(adTemplateB, 114, (z.a) null, jSONObject);
                        }
                    }));
                    if (b.this.b != null) {
                        try {
                            b.this.b.removeView(b.this.f);
                        } catch (Exception e) {
                            com.kwad.sdk.core.kwai.a.a(e);
                            com.kwad.sdk.core.d.a.b(e);
                        }
                    }
                }
            }
        });
        this.g.setClickable(true);
        this.g.setOnTouchListener(new View.OnTouchListener() { // from class: com.kwad.sdk.splashscreen.b.2
            float a = 0.0f;
            float b = 0.0f;
            float c = 0.0f;
            float d = 0.0f;
            float e = 0.0f;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (b.this.o == null) {
                    return false;
                }
                if (motionEvent.getActionMasked() == 0) {
                    this.a = motionEvent.getRawX();
                    this.b = motionEvent.getRawY();
                    this.d = b.this.o.x;
                    this.c = b.this.o.y;
                    this.e = SystemClock.elapsedRealtime();
                    System.out.println(" actionDownX " + this.a + " actionDownX " + this.a);
                } else {
                    if (motionEvent.getActionMasked() == 2) {
                        float rawX = motionEvent.getRawX() - this.a;
                        float rawY = motionEvent.getRawY() - this.b;
                        if (Math.sqrt((rawX * rawX) + (rawY * rawY)) > 15.0d) {
                            b.this.o.x = (int) (this.d + rawX);
                            b.this.o.y = (int) (this.c + rawY);
                            if (b.this.b != null) {
                                try {
                                    b.this.b.updateViewLayout(b.this.f, b.this.o);
                                } catch (Exception e) {
                                    com.kwad.sdk.core.kwai.a.a(e);
                                    com.kwad.sdk.core.d.a.b(e);
                                }
                            }
                        }
                        return true;
                    }
                    if (motionEvent.getActionMasked() == 1) {
                        float rawX2 = motionEvent.getRawX() - this.a;
                        float rawY2 = motionEvent.getRawY() - this.b;
                        float fElapsedRealtime = SystemClock.elapsedRealtime() - this.e;
                        if (Math.sqrt((rawX2 * rawX2) + (rawY2 * rawY2)) < 15.0d && fElapsedRealtime > 30.0f && fElapsedRealtime < 300.0f) {
                            view.performClick();
                        }
                    }
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            this.b.removeView(this.c);
        } catch (Exception e) {
            com.kwad.sdk.core.kwai.a.a(e);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.d.a().setOutlineProvider(new a(com.kwad.sdk.a.kwai.a.a(this.a, 1.0f)));
            this.d.a().setClipToOutline(true);
            this.i.setOutlineProvider(new a(com.kwad.sdk.a.kwai.a.a(this.a, 1.0f)));
            this.i.setClipToOutline(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.l || System.currentTimeMillis() <= this.m) {
            return;
        }
        this.l = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.j || System.currentTimeMillis() <= this.k) {
            return;
        }
        this.j = true;
        this.i.setVisibility(8);
    }

    @SuppressLint({"RtlHardcoded"})
    public boolean a(Rect rect) {
        if (this.d == null || this.b == null) {
            return false;
        }
        Rect rect2 = new Rect();
        rect2.left = rect.left - com.kwad.sdk.a.kwai.a.a(this.a, 6.0f);
        rect2.right = rect.right - com.kwad.sdk.a.kwai.a.a(this.a, 6.0f);
        rect2.top = rect.top - com.kwad.sdk.a.kwai.a.a(this.a, 6.0f);
        rect2.bottom = rect.bottom + com.kwad.sdk.a.kwai.a.a(this.a, 6.0f);
        DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
        this.o.type = 1003;
        this.o.flags = 8;
        this.o.gravity = 51;
        this.o.format = 1;
        this.o.width = displayMetrics.widthPixels;
        this.o.height = displayMetrics.heightPixels;
        this.o.x = 0;
        this.o.y = 0;
        float f = (rect2.right - rect2.left) / displayMetrics.widthPixels;
        float f2 = (rect2.bottom - rect2.top) / displayMetrics.heightPixels;
        this.c.setPivotX((rect2.left * displayMetrics.widthPixels) / ((rect2.left + displayMetrics.widthPixels) - rect2.right));
        this.c.setPivotY((rect2.top * displayMetrics.heightPixels) / ((rect2.top + displayMetrics.heightPixels) - rect2.bottom));
        if (this.b != null) {
            try {
                this.b.addView(this.c, this.o);
            } catch (Exception e) {
                com.kwad.sdk.core.kwai.a.a(e);
                com.kwad.sdk.core.d.a.b(e);
            }
        }
        this.c.animate().scaleX(f).scaleY(f2).setDuration(600L).start();
        this.c.animate().setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new AnonymousClass3(rect2));
        if (this.e != null) {
            this.e.onAdShowStart();
        }
        com.kwad.sdk.core.report.a.c(this.d.b(), 115, null);
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.splashscreen.b.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                if (b.this.b != null) {
                    try {
                        b.this.b.removeView(b.this.f);
                        b.this.d.h();
                    } catch (Exception e2) {
                        com.kwad.sdk.core.kwai.a.a(e2);
                    }
                }
                if (b.this.e != null) {
                    b.this.e.onSkippedAd();
                }
                if (b.this.d != null) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("duration", b.this.d.c());
                    } catch (JSONException e3) {
                        com.kwad.sdk.core.d.a.b(e3);
                    }
                    com.kwad.sdk.core.report.a.a(b.this.d.b(), 1, jSONObject);
                }
            }
        });
        return true;
    }
}
