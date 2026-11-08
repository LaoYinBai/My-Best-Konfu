package com.mbridge.msdk.splash.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mbridge.msdk.click.b;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.c.c;
import com.mbridge.msdk.foundation.tools.r;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.confirmation.e;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.splash.d.d;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBSplashPopView extends RelativeLayout {
    public static final int TYPE_POP_DEFAULT = 1;
    public static final int TYPE_POP_LARGE = 4;
    public static final int TYPE_POP_MEDIUM = 3;
    public static final int TYPE_POP_SMALL = 2;
    private static final AtomicInteger c = new AtomicInteger(1);
    View.OnClickListener a;
    View.OnClickListener b;
    private String d;
    private String e;
    private int f;
    private CampaignEx g;
    private d h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private int p;
    private Handler q;
    private boolean r;
    private b s;
    private Runnable t;

    static /* synthetic */ int l(MBSplashPopView mBSplashPopView) {
        int i = mBSplashPopView.p;
        mBSplashPopView.p = i - 1;
        return i;
    }

    public MBSplashPopView(Context context, a aVar, d dVar) {
        super(context);
        this.f = 1;
        this.p = -1;
        this.q = new Handler();
        this.r = false;
        this.t = new Runnable() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.4
            @Override // java.lang.Runnable
            public final void run() {
                if (MBSplashPopView.this.o != null) {
                    if (MBSplashPopView.this.p == 0) {
                        MBSplashPopView.this.p = -1;
                        MBSplashPopView.this.g();
                        MBSplashPopView.this.q.removeCallbacks(MBSplashPopView.this.t);
                        if (MBSplashPopView.this.h != null) {
                            MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 5);
                            return;
                        }
                        return;
                    }
                    MBSplashPopView.l(MBSplashPopView.this);
                    MBSplashPopView.this.o.setText(String.valueOf(MBSplashPopView.this.p));
                    MBSplashPopView.this.q.postDelayed(MBSplashPopView.this.t, 1000L);
                }
            }
        };
        this.a = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.h != null) {
                    MBSplashPopView.a(MBSplashPopView.this, MBSplashPopView.this.g);
                }
            }
        };
        this.b = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.p <= 0 && MBSplashPopView.this.h != null) {
                    MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 4);
                }
            }
        };
        if (aVar == null) {
            throw new IllegalArgumentException("Parameters is NULL, can't gen view.");
        }
        this.e = aVar.b();
        this.d = aVar.a();
        this.f = aVar.c();
        this.g = aVar.d();
        this.h = dVar;
        a();
    }

    public MBSplashPopView(Context context) {
        super(context);
        this.f = 1;
        this.p = -1;
        this.q = new Handler();
        this.r = false;
        this.t = new Runnable() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.4
            @Override // java.lang.Runnable
            public final void run() {
                if (MBSplashPopView.this.o != null) {
                    if (MBSplashPopView.this.p == 0) {
                        MBSplashPopView.this.p = -1;
                        MBSplashPopView.this.g();
                        MBSplashPopView.this.q.removeCallbacks(MBSplashPopView.this.t);
                        if (MBSplashPopView.this.h != null) {
                            MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 5);
                            return;
                        }
                        return;
                    }
                    MBSplashPopView.l(MBSplashPopView.this);
                    MBSplashPopView.this.o.setText(String.valueOf(MBSplashPopView.this.p));
                    MBSplashPopView.this.q.postDelayed(MBSplashPopView.this.t, 1000L);
                }
            }
        };
        this.a = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.h != null) {
                    MBSplashPopView.a(MBSplashPopView.this, MBSplashPopView.this.g);
                }
            }
        };
        this.b = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.p <= 0 && MBSplashPopView.this.h != null) {
                    MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 4);
                }
            }
        };
        this.f = 1;
        s.b("MBSplashPopView", "Please call setPopViewType() to init.");
    }

    public MBSplashPopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = 1;
        this.p = -1;
        this.q = new Handler();
        this.r = false;
        this.t = new Runnable() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.4
            @Override // java.lang.Runnable
            public final void run() {
                if (MBSplashPopView.this.o != null) {
                    if (MBSplashPopView.this.p == 0) {
                        MBSplashPopView.this.p = -1;
                        MBSplashPopView.this.g();
                        MBSplashPopView.this.q.removeCallbacks(MBSplashPopView.this.t);
                        if (MBSplashPopView.this.h != null) {
                            MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 5);
                            return;
                        }
                        return;
                    }
                    MBSplashPopView.l(MBSplashPopView.this);
                    MBSplashPopView.this.o.setText(String.valueOf(MBSplashPopView.this.p));
                    MBSplashPopView.this.q.postDelayed(MBSplashPopView.this.t, 1000L);
                }
            }
        };
        this.a = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.h != null) {
                    MBSplashPopView.a(MBSplashPopView.this, MBSplashPopView.this.g);
                }
            }
        };
        this.b = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.p <= 0 && MBSplashPopView.this.h != null) {
                    MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 4);
                }
            }
        };
        this.f = 1;
        s.b("MBSplashPopView", "Please call setPopViewType() to init.");
    }

    public MBSplashPopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = 1;
        this.p = -1;
        this.q = new Handler();
        this.r = false;
        this.t = new Runnable() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.4
            @Override // java.lang.Runnable
            public final void run() {
                if (MBSplashPopView.this.o != null) {
                    if (MBSplashPopView.this.p == 0) {
                        MBSplashPopView.this.p = -1;
                        MBSplashPopView.this.g();
                        MBSplashPopView.this.q.removeCallbacks(MBSplashPopView.this.t);
                        if (MBSplashPopView.this.h != null) {
                            MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 5);
                            return;
                        }
                        return;
                    }
                    MBSplashPopView.l(MBSplashPopView.this);
                    MBSplashPopView.this.o.setText(String.valueOf(MBSplashPopView.this.p));
                    MBSplashPopView.this.q.postDelayed(MBSplashPopView.this.t, 1000L);
                }
            }
        };
        this.a = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.h != null) {
                    MBSplashPopView.a(MBSplashPopView.this, MBSplashPopView.this.g);
                }
            }
        };
        this.b = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.p <= 0 && MBSplashPopView.this.h != null) {
                    MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 4);
                }
            }
        };
        this.f = 1;
        s.b("MBSplashPopView", "Please call setPopViewType() to init.");
    }

    public MBSplashPopView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f = 1;
        this.p = -1;
        this.q = new Handler();
        this.r = false;
        this.t = new Runnable() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.4
            @Override // java.lang.Runnable
            public final void run() {
                if (MBSplashPopView.this.o != null) {
                    if (MBSplashPopView.this.p == 0) {
                        MBSplashPopView.this.p = -1;
                        MBSplashPopView.this.g();
                        MBSplashPopView.this.q.removeCallbacks(MBSplashPopView.this.t);
                        if (MBSplashPopView.this.h != null) {
                            MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 5);
                            return;
                        }
                        return;
                    }
                    MBSplashPopView.l(MBSplashPopView.this);
                    MBSplashPopView.this.o.setText(String.valueOf(MBSplashPopView.this.p));
                    MBSplashPopView.this.q.postDelayed(MBSplashPopView.this.t, 1000L);
                }
            }
        };
        this.a = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.h != null) {
                    MBSplashPopView.a(MBSplashPopView.this, MBSplashPopView.this.g);
                }
            }
        };
        this.b = new View.OnClickListener() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (MBSplashPopView.this.p <= 0 && MBSplashPopView.this.h != null) {
                    MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 4);
                }
            }
        };
        this.f = 1;
        s.b("MBSplashPopView", "Please call setPopViewType() to init.");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.h != null) {
            this.h.a(new MBridgeIds(this.d, this.e), getWidth(), getHeight(), this.f);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        release();
    }

    public void setPopViewType(a aVar, d dVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Parameters is NULL, can't gen view.");
        }
        this.e = aVar.b();
        this.d = aVar.a();
        this.f = aVar.c();
        this.g = aVar.d();
        this.h = dVar;
        a();
    }

    public void startCountDown() {
        this.q.removeCallbacks(this.t);
        if (this.g == null || this.f != 1) {
            return;
        }
        int flbSkipTime = this.g.getFlbSkipTime();
        if (flbSkipTime > 0) {
            this.p = flbSkipTime;
            if (this.o != null) {
                this.o.setText(String.valueOf(this.p));
                this.q.postDelayed(this.t, 1000L);
                return;
            }
            return;
        }
        g();
    }

    public void reStartCountDown() {
        if (this.r) {
            this.r = false;
            if (this.p == -1 || this.p == 0) {
                g();
            } else if (this.o != null) {
                this.o.setText(String.valueOf(this.p));
                this.q.postDelayed(this.t, 1000L);
            }
        }
    }

    public void pauseCountDown() {
        this.r = true;
        if (this.o != null) {
            this.q.removeCallbacks(this.t);
        }
    }

    public void release() {
        try {
            this.q.removeCallbacks(this.t);
            this.t = null;
            detachAllViewsFromParent();
            this.g = null;
            this.h = null;
        } catch (Exception e) {
            s.d("MBSplashPopView", e.getMessage());
        }
    }

    private void a() {
        if (this.g == null) {
        }
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        switch (this.f) {
            case 1:
                b();
                break;
            case 2:
                c();
                break;
            case 3:
                d();
                break;
            case 4:
                e();
                break;
        }
    }

    private void b() {
        ImageView imageView = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w.b(getContext(), 80.0f), w.b(getContext(), 80.0f));
        layoutParams.addRule(9);
        layoutParams.topMargin = w.b(getContext(), 16.0f);
        imageView.setId(generateViewId());
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundResource(getResources().getIdentifier("mbridge_splash_popview_default", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
        this.i = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(w.b(getContext(), 60.0f), w.b(getContext(), 60.0f));
        layoutParams2.addRule(6, imageView.getId());
        layoutParams2.topMargin = w.b(getContext(), 7.0f);
        layoutParams2.leftMargin = w.b(getContext(), 10.0f);
        this.i.setId(generateViewId());
        this.i.setLayoutParams(layoutParams2);
        this.i.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if (this.g != null && !TextUtils.isEmpty(this.g.getIconUrl())) {
            a(this.g.getIconUrl(), true);
        }
        this.o = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(5, imageView.getId());
        layoutParams3.addRule(8, imageView.getId());
        layoutParams3.leftMargin = w.b(getContext(), 62.0f);
        layoutParams3.bottomMargin = w.b(getContext(), 70.0f);
        this.o.setId(generateViewId());
        this.o.setTextSize(10.0f);
        this.o.setTextColor(-1);
        this.o.setGravity(17);
        this.o.setMinWidth(w.b(getContext(), 16.0f));
        this.o.setMaxHeight(w.b(getContext(), 16.0f));
        this.o.setLayoutParams(layoutParams3);
        this.o.setBackgroundResource(getResources().getIdentifier("mbridge_cm_circle_50black", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
        addView(imageView);
        addView(this.o);
        addView(this.i);
        if (this.g != null && this.g.getFlbSkipTime() <= 0) {
            g();
        }
        setOnClickListener(this.a);
        this.o.setOnClickListener(this.b);
    }

    private void c() {
        int iB = w.b(getContext(), 4.0f);
        this.i = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w.b(getContext(), 28.0f), w.b(getContext(), 28.0f));
        layoutParams.addRule(9);
        this.i.setId(generateViewId());
        this.i.setLayoutParams(layoutParams);
        this.i.setPadding(iB, iB, iB, iB);
        this.i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        a(this.g.getIconUrl(), false);
        this.m = new TextView(getContext());
        this.m.setId(generateViewId());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, this.i.getId());
        layoutParams2.addRule(6, this.i.getId());
        layoutParams2.addRule(8, this.i.getId());
        layoutParams2.leftMargin = w.b(getContext(), 4.0f);
        layoutParams2.rightMargin = w.b(getContext(), 40.0f);
        this.m.setLayoutParams(layoutParams2);
        this.m.setGravity(16);
        this.m.setTextSize(10.0f);
        this.m.setSelected(true);
        this.m.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.m.setMarqueeRepeatLimit(-1);
        this.m.setSingleLine(true);
        this.m.setTextColor(-16777216);
        this.m.setText(this.g.getAppName());
        setBackgroundResource(getResources().getIdentifier("mbridge_shape_corners_bg", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
        addView(this.i);
        addView(this.m);
        f();
        setOnClickListener(this.a);
    }

    private void d() {
        int iB = w.b(getContext(), 4.0f);
        this.i = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w.b(getContext(), 50.0f), w.b(getContext(), 50.0f));
        layoutParams.addRule(9);
        this.i.setId(generateViewId());
        this.i.setLayoutParams(layoutParams);
        this.i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.i.setPadding(iB, iB, iB, iB);
        a(this.g.getIconUrl(), false);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(1, this.i.getId());
        layoutParams2.addRule(6, this.i.getId());
        layoutParams2.addRule(8, this.i.getId());
        layoutParams2.leftMargin = w.b(getContext(), 8.0f);
        layoutParams2.rightMargin = w.b(getContext(), 8.0f);
        relativeLayout.setLayoutParams(layoutParams2);
        relativeLayout.setGravity(16);
        this.m = new TextView(getContext());
        this.m.setId(generateViewId());
        this.m.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.m.setGravity(16);
        this.m.setTextSize(12.0f);
        this.m.setSelected(true);
        this.m.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.m.setMarqueeRepeatLimit(-1);
        this.m.setSingleLine(true);
        this.m.setTextColor(-16777216);
        this.m.setText(this.g.getAppName());
        this.n = new TextView(getContext());
        this.n.setId(generateViewId());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(5, this.m.getId());
        layoutParams3.addRule(3, this.m.getId());
        layoutParams3.topMargin = w.b(getContext(), 4.0f);
        layoutParams3.rightMargin = w.b(getContext(), 36.0f);
        this.n.setGravity(16);
        this.n.setLayoutParams(layoutParams3);
        this.n.setTextSize(8.0f);
        this.n.setTextColor(-10066330);
        this.n.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.n.setMarqueeRepeatLimit(-1);
        this.n.setSelected(true);
        this.n.setSingleLine(true);
        this.n.setText(this.g.getAppDesc());
        relativeLayout.addView(this.m);
        relativeLayout.addView(this.n);
        setBackgroundResource(getResources().getIdentifier("mbridge_shape_corners_bg", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
        addView(this.i);
        addView(relativeLayout);
        f();
        setOnClickListener(this.a);
    }

    private void e() {
        this.k = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, w.b(getContext(), 131.0f));
        layoutParams.addRule(10);
        layoutParams.addRule(14);
        this.k.setScaleType(ImageView.ScaleType.FIT_XY);
        this.k.setId(generateViewId());
        this.k.setLayoutParams(layoutParams);
        a(this.g.getImageUrl());
        this.j = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, w.b(getContext(), 131.0f));
        layoutParams2.addRule(10);
        layoutParams2.addRule(14);
        this.j.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.j.setId(generateViewId());
        this.j.setLayoutParams(layoutParams2);
        b(this.g.getImageUrl());
        this.i = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(w.b(getContext(), 50.0f), w.b(getContext(), 50.0f));
        layoutParams3.addRule(9);
        layoutParams3.addRule(3, this.k.getId());
        layoutParams3.topMargin = 20;
        this.i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.i.setId(generateViewId());
        this.i.setLayoutParams(layoutParams3);
        a(this.g.getIconUrl(), false);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(1, this.i.getId());
        layoutParams4.addRule(6, this.i.getId());
        layoutParams4.addRule(8, this.i.getId());
        layoutParams4.leftMargin = w.b(getContext(), 8.0f);
        layoutParams4.rightMargin = w.b(getContext(), 8.0f);
        relativeLayout.setLayoutParams(layoutParams4);
        relativeLayout.setGravity(16);
        this.m = new TextView(getContext());
        this.m.setId(generateViewId());
        this.m.setGravity(16);
        this.m.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.m.setTextSize(12.0f);
        this.m.setTextColor(-16777216);
        this.m.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.m.setMarqueeRepeatLimit(-1);
        this.m.setSelected(true);
        this.m.setSingleLine(true);
        this.m.setText(this.g.getAppName());
        this.n = new TextView(getContext());
        this.n.setId(generateViewId());
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(5, this.m.getId());
        layoutParams5.addRule(3, this.m.getId());
        layoutParams5.topMargin = w.b(getContext(), 4.0f);
        layoutParams5.rightMargin = w.b(getContext(), 36.0f);
        this.n.setGravity(16);
        this.n.setLayoutParams(layoutParams5);
        this.n.setTextSize(8.0f);
        this.n.setTextColor(-10066330);
        this.n.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.n.setMarqueeRepeatLimit(-1);
        this.n.setSelected(true);
        this.n.setSingleLine(true);
        this.n.setText(this.g.getAppDesc());
        relativeLayout.addView(this.m);
        relativeLayout.addView(this.n);
        addView(this.k);
        addView(this.j);
        addView(this.i);
        addView(relativeLayout);
        f();
        setOnClickListener(this.a);
    }

    private void f() {
        int identifier;
        this.l = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w.b(getContext(), 32.0f), w.b(getContext(), 13.0f));
        layoutParams.addRule(11);
        layoutParams.addRule(8, this.i.getId());
        this.l.setLayoutParams(layoutParams);
        String language = "ZH";
        try {
            language = getResources().getConfiguration().locale.getLanguage();
        } catch (Throwable th) {
            s.d("MBSplashPopView", th.getMessage());
        }
        if (language.toUpperCase().equals("CN") || language.toUpperCase().equals("ZH")) {
            identifier = getResources().getIdentifier("mbridge_splash_ad", "drawable", com.mbridge.msdk.foundation.controller.a.f().d());
        } else {
            identifier = getResources().getIdentifier("mbridge_splash_ad_en", "drawable", com.mbridge.msdk.foundation.controller.a.f().d());
        }
        this.l.setBackgroundResource(identifier);
        addView(this.l);
    }

    private void a(String str, final boolean z) {
        com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(str, new c() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.1
            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onSuccessLoad(Bitmap bitmap, String str2) {
                Bitmap bitmapA;
                try {
                    if (bitmap.isRecycled()) {
                        return;
                    }
                    if (!z) {
                        bitmapA = r.a(bitmap, 1, 16);
                    } else {
                        int width = bitmap.getWidth();
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmapCreateBitmap);
                        Paint paint = new Paint();
                        paint.setAntiAlias(true);
                        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
                        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
                        bitmapA = bitmapCreateBitmap;
                    }
                    ImageView imageView = MBSplashPopView.this.i;
                    if (bitmapA != null) {
                        bitmap = bitmapA;
                    }
                    imageView.setImageBitmap(bitmap);
                } catch (Throwable th) {
                    s.d("MBSplashPopView", th.getMessage());
                }
            }

            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onFailedLoad(String str2, String str3) {
                s.d("MBSplashPopView", str2);
            }
        });
    }

    private void a(String str) {
        com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(str, new c() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.2
            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onSuccessLoad(Bitmap bitmap, String str2) {
                try {
                    if (bitmap.isRecycled()) {
                        return;
                    }
                    MBSplashPopView.this.k.setImageBitmap(com.mbridge.msdk.widget.custom.b.b.a(bitmap, 10));
                } catch (Throwable th) {
                    s.d("MBSplashPopView", th.getMessage());
                }
            }

            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onFailedLoad(String str2, String str3) {
                s.d("MBSplashPopView", str2);
            }
        });
    }

    private void b(String str) {
        com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(str, new c() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.3
            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onSuccessLoad(Bitmap bitmap, String str2) {
                try {
                    if (bitmap.isRecycled()) {
                        return;
                    }
                    MBSplashPopView.this.j.setImageBitmap(r.a(bitmap, 1, 16));
                } catch (Throwable th) {
                    s.d("MBSplashPopView", th.getMessage());
                }
            }

            @Override // com.mbridge.msdk.foundation.same.c.c
            public final void onFailedLoad(String str2, String str3) {
                s.d("MBSplashPopView", str2);
            }
        });
    }

    public static int generateViewId() {
        int i;
        int i2;
        do {
            i = c.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!c.compareAndSet(i, i2));
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CampaignEx campaignEx) {
        if (this.s == null) {
            this.s = new b(com.mbridge.msdk.foundation.controller.a.f().j(), this.e);
        }
        campaignEx.setCampaignUnitId(this.e);
        this.s.c(campaignEx);
        if (!campaignEx.isReportClick()) {
            campaignEx.setReportClick(true);
            com.mbridge.msdk.splash.e.a.a(com.mbridge.msdk.foundation.controller.a.f().j(), campaignEx);
        }
        if (this.h != null) {
            this.h.b(new MBridgeIds(this.d, this.e));
            this.h.a(new MBridgeIds(this.d, this.e), 6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.o != null) {
            ViewGroup.LayoutParams layoutParams = this.o.getLayoutParams();
            layoutParams.width = w.b(getContext(), 16.0f);
            layoutParams.height = w.b(getContext(), 16.0f);
            this.o.setLayoutParams(layoutParams);
            this.o.setText("");
            this.o.setBackgroundResource(getResources().getIdentifier("mbridge_splash_popview_close", "drawable", com.mbridge.msdk.foundation.controller.a.f().d()));
        }
    }

    public static class a {
        private String a;
        private String b;
        private int c;
        private CampaignEx d;

        public a(String str, String str2, int i, CampaignEx campaignEx) {
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = campaignEx;
        }

        public final String a() {
            return this.a;
        }

        public final String b() {
            return this.b;
        }

        public final int c() {
            return this.c;
        }

        public final CampaignEx d() {
            return this.d;
        }
    }

    static /* synthetic */ void a(MBSplashPopView mBSplashPopView, final CampaignEx campaignEx) {
        try {
            com.mbridge.msdk.splash.e.a.b(campaignEx, mBSplashPopView.e);
            if (campaignEx != null && campaignEx.needShowIDialog()) {
                final com.mbridge.msdk.widget.dialog.a aVar = new com.mbridge.msdk.widget.dialog.a() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.7
                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void a() {
                        MBSplashPopView.this.a(campaignEx);
                    }

                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void b() {
                        MBSplashPopView.this.reStartCountDown();
                    }

                    @Override // com.mbridge.msdk.widget.dialog.a
                    public final void c() {
                        a();
                    }
                };
                if (com.mbridge.msdk.click.c.a(campaignEx) && campaignEx.needShowIDialog()) {
                    if (mBSplashPopView.s == null) {
                        mBSplashPopView.s = new b(com.mbridge.msdk.foundation.controller.a.f().j(), mBSplashPopView.e);
                    }
                    final Context context = mBSplashPopView.getContext();
                    mBSplashPopView.getHandler().post(new Runnable() { // from class: com.mbridge.msdk.splash.view.MBSplashPopView.8
                        @Override // java.lang.Runnable
                        public final void run() {
                            if (!MBSplashPopView.this.s.b(campaignEx)) {
                                MBSplashPopView.this.pauseCountDown();
                                if (context != context.getApplicationContext()) {
                                    e.a().a("", campaignEx, context, MBSplashPopView.this.e, aVar);
                                    return;
                                } else {
                                    e.a().a("", campaignEx, MBSplashPopView.this.getContext(), MBSplashPopView.this.e, aVar);
                                    return;
                                }
                            }
                            MBSplashPopView.this.pauseCountDown();
                            if (MBSplashPopView.this.h != null) {
                                MBSplashPopView.this.h.b(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e));
                                MBSplashPopView.this.h.a(new MBridgeIds(MBSplashPopView.this.d, MBSplashPopView.this.e), 6);
                            }
                        }
                    });
                    return;
                }
            }
        } catch (Throwable th) {
            s.a("MBSplashPopView", th.getMessage());
            mBSplashPopView.reStartCountDown();
        }
        mBSplashPopView.a(campaignEx);
    }
}
