package com.sigmob.sdk.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.AdActivity;
import com.sigmob.sdk.base.common.BaseBroadcastReceiver;
import com.sigmob.sdk.base.common.l;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.common.x;
import com.sigmob.sdk.base.common.y;
import com.sigmob.sdk.base.common.z;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.base.views.AdLogoView;
import com.sigmob.sdk.base.views.ShakeView;
import com.sigmob.sdk.base.views.e;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.utils.j;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.common.utils.q;
import com.sigmob.volley.s;
import com.sigmob.volley.toolbox.k;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import com.umeng.commonsdk.proguard.o;
import java.io.UnsupportedEncodingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class SplashAdView extends RelativeLayout {
    private static float a = 25.0f;
    private static float b = 13.0f;
    private e c;
    private Context d;
    private RelativeLayout e;
    private BaseAdUnit f;
    private d g;
    private k.c h;
    private boolean i;
    private TextView j;
    private RelativeLayout k;
    private RelativeLayout l;
    private View.OnTouchListener m;
    private float n;
    private ShakeView o;
    private boolean p;
    private SensorManager q;
    private boolean r;
    private SensorEventListener s;

    public SplashAdView(Context context) {
        super(context);
        this.n = 1.0f;
        this.s = new SensorEventListener() { // from class: com.sigmob.sdk.splash.SplashAdView.1
            private long b;

            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent sensorEvent) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.b < 2000) {
                    return;
                }
                float f = sensorEvent.values[0];
                float f2 = sensorEvent.values[1];
                float f3 = sensorEvent.values[2];
                if (f > 15.0f || f2 > 15.0f || f3 > 15.0f) {
                    SplashAdView.this.o.a(2);
                    this.b = jCurrentTimeMillis;
                    SplashAdView.this.o.postDelayed(new Runnable() { // from class: com.sigmob.sdk.splash.SplashAdView.1.1
                        @Override // java.lang.Runnable
                        public void run() throws UnsupportedEncodingException {
                            SigMacroCommon macroCommon = SplashAdView.this.f.getMacroCommon();
                            if (macroCommon instanceof SigMacroCommon) {
                                macroCommon.addMarcoKey(SigMacroCommon._AUTOCLICK_, String.valueOf(2));
                            }
                            SplashAdView.this.a((String) null);
                        }
                    }, 400L);
                }
            }
        };
        this.d = context.getApplicationContext();
    }

    private void a(final Context context, final BaseAdUnit baseAdUnit, final String str) {
        String str2;
        SigmobLog.i("handleUrlAction: ");
        boolean zIsEmpty = TextUtils.isEmpty(baseAdUnit.getMaterial().deeplink_url);
        final MaterialMeta material = baseAdUnit.getMaterial();
        final int iIntValue = material.interaction_type.intValue();
        if (zIsEmpty) {
            str2 = material.landing_page;
        } else {
            Uri uri = Uri.parse(material.deeplink_url);
            if (j.a(this.d, new Intent("android.intent.action.VIEW", uri)) && x.FOLLOW_DEEP_LINK.a(uri)) {
                str2 = material.deeplink_url;
                a(true);
            } else {
                if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                    t.a(baseAdUnit, material.deeplink_url);
                }
                str2 = material.landing_page;
                com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_OPEN_DEEPLINK_FAIL);
            }
        }
        new y.a().a(x.IGNORE_ABOUT_SCHEME, x.OPEN_WITH_LANDPAGE, x.FOLLOW_DEEP_LINK).a(new y.b() { // from class: com.sigmob.sdk.splash.SplashAdView.4
            @Override // com.sigmob.sdk.base.common.y.b
            public void a(String str3, x xVar) {
            }

            @Override // com.sigmob.sdk.base.common.y.b
            public void b(String str3, x xVar) {
            }

            @Override // com.sigmob.sdk.base.common.y.b
            public void c(String str3, x xVar) {
                SigmobLog.i("urlHandlingSucceeded: " + xVar.name() + " url: " + str3);
                if (x.FOLLOW_DEEP_LINK == xVar) {
                    if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                        t.a(baseAdUnit, str3);
                    }
                    SplashAdView.this.g.d(context, 0, baseAdUnit);
                    com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_OPEN_DEEPLINK);
                    t.a("click", (String) null, SplashAdView.this.f, "1", str3, str);
                    return;
                }
                t.a("click", (String) null, SplashAdView.this.f, Constants.FAIL, str3, str);
                if (iIntValue == 3) {
                    l.a(context, baseAdUnit.getMacroCommon().macroProcess(material.landing_page), baseAdUnit);
                    SplashAdView.this.a(!SplashAdView.this.g.e());
                } else {
                    if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                        t.a(baseAdUnit, str3);
                    }
                    AdActivity.a(SplashAdView.this.d, AdActivity.class, SplashAdView.this.f.getUuid());
                    SplashAdView.this.a(false);
                }
            }

            @Override // com.sigmob.sdk.base.common.y.b
            public void d(String str3, x xVar) {
                SigmobLog.i("urlHandlingFailed: " + xVar.name() + " url: " + str3);
                if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                    t.a(baseAdUnit, str3);
                }
                if (x.FOLLOW_DEEP_LINK == xVar) {
                    try {
                        t.a("click", (String) null, SplashAdView.this.f, Constants.FAIL, str3, str);
                        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_OPEN_DEEPLINK_FAIL);
                        if (iIntValue == 3) {
                            l.a(context, baseAdUnit.getMacroCommon().macroProcess(material.landing_page), baseAdUnit);
                            SplashAdView.this.a(!SplashAdView.this.g.e());
                        } else {
                            final String strMacroProcess = baseAdUnit.getMacroCommon().macroProcess(SplashAdView.this.f.getMaterial().landing_page);
                            z.a(strMacroProcess, new z.a() { // from class: com.sigmob.sdk.splash.SplashAdView.4.1
                                @Override // com.sigmob.sdk.base.common.z.a
                                public void a(String str4) {
                                    if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                                        t.a(baseAdUnit, str4);
                                    }
                                    AdActivity.a(SplashAdView.this.d, AdActivity.class, SplashAdView.this.f.getUuid());
                                    SplashAdView.this.a(false);
                                }

                                @Override // com.sigmob.sdk.base.common.z.a
                                public void a(String str4, Throwable th) {
                                    if (!baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()) {
                                        t.a(baseAdUnit, strMacroProcess);
                                    }
                                    AdActivity.a(SplashAdView.this.d, AdActivity.class, SplashAdView.this.f.getUuid());
                                    SplashAdView.this.a(false);
                                }
                            });
                        }
                        return;
                    } catch (Throwable th) {
                        SigmobLog.e("handle Click Action error: " + th.getMessage());
                    }
                } else {
                    t.a("click", (String) null, SplashAdView.this.f, Constants.FAIL, str3, str);
                }
                SplashAdView.this.a(true);
            }
        }).a(baseAdUnit.isSkipSigmobBrowser()).a(baseAdUnit).b(baseAdUnit.getAd().forbiden_parse_landingpage.booleanValue()).a().a(context, this.f.getMacroCommon().macroProcess(str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        SigmobLog.i("handleClick: ");
        if (this.p) {
            return;
        }
        this.p = true;
        this.g.a(this.d, (q) null, (q) null, (com.sigmob.sdk.base.a) null, this.f);
        BaseBroadcastReceiver.a(this.d, this.f.getUuid(), IntentActions.ACTION_INTERSTITIAL_CLICK);
        MaterialMeta material = this.f.getMaterial();
        if (material.interaction_type.intValue() != 2) {
            a(this.d, this.f, str);
        } else {
            this.g.a(this.d, this.f, this.f.getMacroCommon().macroProcess(material.landing_page), (com.sigmob.sdk.base.a) null, str, true);
        }
    }

    private void b(final Context context) {
        this.c = e.a(context, this.f);
        addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
        this.l = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        int iB = com.sigmob.sdk.common.utils.c.b(a, this.d);
        layoutParams.setMargins(iB, (int) (iB * 2.5d), iB, iB);
        this.l.setClickable(true);
        addView(this.l, layoutParams);
        this.e = new RelativeLayout(context);
        this.e.setId(com.sigmob.sdk.common.a.r());
        this.e.setOnTouchListener(new View.OnTouchListener() { // from class: com.sigmob.sdk.splash.SplashAdView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    BaseBroadcastReceiver.a(context, SplashAdView.this.f.getUuid(), IntentActions.ACTION_SPLAH_SKIP);
                }
                return true;
            }
        });
        this.m = new View.OnTouchListener() { // from class: com.sigmob.sdk.splash.SplashAdView.3
            private MotionEvent b;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.b = MotionEvent.obtain(motionEvent);
                } else if (motionEvent.getAction() == 1) {
                    if (this.b == null) {
                        this.b = motionEvent;
                    }
                    String str = String.format("%d,%d,%d,%d", Integer.valueOf((int) this.b.getRawX()), Integer.valueOf((int) this.b.getRawY()), Integer.valueOf((int) motionEvent.getRawX()), Integer.valueOf((int) motionEvent.getRawY()));
                    try {
                        SigMacroCommon macroCommon = SplashAdView.this.f.getMacroCommon();
                        if (macroCommon instanceof SigMacroCommon) {
                            macroCommon.addMarcoKey(SigMacroCommon._AUTOCLICK_, String.valueOf(2));
                            macroCommon.addMarcoKey(SigMacroCommon._DOWNX_, String.valueOf((int) this.b.getRawX()));
                            macroCommon.addMarcoKey(SigMacroCommon._DOWNY_, String.valueOf((int) this.b.getRawY()));
                            macroCommon.addMarcoKey(SigMacroCommon._UPX_, String.valueOf((int) motionEvent.getRawX()));
                            macroCommon.addMarcoKey(SigMacroCommon._UPY_, String.valueOf((int) motionEvent.getRawY()));
                        }
                    } catch (Throwable th) {
                        SigmobLog.e("splash click macro set " + th.getMessage());
                    }
                    SplashAdView.this.l.setClickable(false);
                    SplashAdView.this.l.setOnTouchListener(null);
                    SplashAdView.this.a(str);
                }
                return true;
            }
        };
        if (this.f.enable_full_click()) {
            this.l.setOnTouchListener(this.m);
        }
    }

    private View c(Context context) {
        TextView textView = new TextView(context);
        textView.setId(com.sigmob.sdk.common.a.r());
        textView.setText(com.sigmob.sdk.base.b.f());
        textView.setTextSize(1, 10.0f);
        textView.setTextColor(Color.parseColor("#dadada"));
        textView.setGravity(17);
        int iB = com.sigmob.sdk.common.utils.c.b(5.0f, context);
        textView.setPadding(iB, 0, iB, 0);
        textView.setBackgroundColor(Color.parseColor("#B3505050"));
        return textView;
    }

    private void d(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, com.sigmob.sdk.common.utils.c.b(15.0f, context));
        if (this.k != null) {
            layoutParams.addRule(4, this.e.getId());
            layoutParams.addRule(9);
            layoutParams.setMargins(com.sigmob.sdk.common.utils.c.b(a * this.n, context) / 2, 0, 0, 0);
        } else {
            layoutParams.addRule(12);
            layoutParams.addRule(11);
            if (!this.i) {
                layoutParams.setMargins(0, 0, 0, com.sigmob.sdk.common.a.n().ad());
            }
        }
        relativeLayout.addView(c(context));
        addView(relativeLayout, layoutParams);
    }

    private void e(Context context) {
        int iB = com.sigmob.sdk.common.utils.c.b(this.n * 30.0f, context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, iB);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        int i = iB / 2;
        int color = Color.parseColor("#B3505050");
        layoutParams.setMargins(0, i, i, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(i);
        if (Build.VERSION.SDK_INT >= 16) {
            this.e.setBackground(gradientDrawable);
        } else {
            this.e.setBackgroundDrawable(gradientDrawable);
        }
        this.e.setPadding(i, 0, i, 0);
        addView(this.e, layoutParams);
        this.j = new TextView(context);
        this.j.setId(com.sigmob.sdk.common.a.r());
        this.j.setTextSize(1, (int) b);
        this.j.setTextColor(Color.parseColor("#dadada"));
        this.j.setGravity(17);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams2.addRule(15);
        this.e.addView(this.j, layoutParams2);
    }

    private void f(Context context) {
        this.o = new ShakeView(context);
        this.o.a(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, 0, 0, com.sigmob.sdk.common.utils.c.c(40.0f, getContext()));
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        addView(this.o, layoutParams);
        this.q = (SensorManager) context.getSystemService(o.Z);
        if (this.q != null) {
            this.q.registerListener(this.s, this.q.getDefaultSensor(1), 3);
        }
    }

    private View g(Context context) {
        final ImageView imageView = new ImageView(context);
        imageView.setBackgroundColor(Color.alpha(0));
        imageView.setId(com.sigmob.sdk.common.a.r());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        k kVarB = com.sigmob.sdk.common.e.f.b();
        if (kVarB != null) {
            this.h = kVarB.a(this.f.getAd_source_logo(), new k.d() { // from class: com.sigmob.sdk.splash.SplashAdView.5
                @Override // com.sigmob.volley.toolbox.k.d
                public void a(k.c cVar, boolean z) {
                    if (cVar.b() != null) {
                        imageView.setImageBitmap(cVar.b());
                    }
                }

                @Override // com.sigmob.volley.n.a
                public void onErrorResponse(s sVar) {
                }
            });
        }
        return imageView;
    }

    private void h(Context context) {
        if (TextUtils.isEmpty(this.f.getAd_source_logo())) {
            return;
        }
        RelativeLayout relativeLayout = new RelativeLayout(context);
        View viewG = g(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.sigmob.sdk.common.utils.c.b(13.0f, context), com.sigmob.sdk.common.utils.c.b(13.0f, context));
        if (!this.i) {
            layoutParams.setMargins(0, 0, 0, com.sigmob.sdk.common.a.n().ad());
        }
        layoutParams.addRule(12);
        layoutParams.addRule(11);
        relativeLayout.addView(viewG, new RelativeLayout.LayoutParams(-1, -1));
        if (this.k != null) {
            layoutParams.addRule(2, this.k.getId());
        }
        addView(relativeLayout, layoutParams);
    }

    private void i(Context context) {
        AdLogoView adLogoView = new AdLogoView(getContext(), 0);
        adLogoView.a(this.f.getAd_source_logo());
        if (!this.f.getInvisibleAdLabel()) {
            adLogoView.b(com.sigmob.sdk.base.b.f());
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        int iB = com.sigmob.sdk.common.utils.c.b(5.0f, context);
        layoutParams.addRule(12);
        layoutParams.addRule(9);
        layoutParams.setMargins(iB, 0, 0, iB);
        addView(adLogoView, layoutParams);
    }

    protected void a() {
        super.setVisibility(8);
        if (this.c != null) {
            this.c.setVisibility(8);
        }
    }

    public void a(Context context) {
        String str = "";
        if (this.f.getMaterial() != null && this.f.getMaterial().template_type.intValue() != 5) {
            str = this.f.getMaterial().interaction_type.intValue() == com.sigmob.sdk.videoAd.f.BrowserType.a() ? "点击查看详情" : "点击跳转第三方应用";
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int iC = com.sigmob.sdk.common.utils.c.c(70.0f, context);
        this.k = new RelativeLayout(context);
        this.k.setOnTouchListener(this.m);
        this.k.setClickable(true);
        this.k.setId(com.sigmob.sdk.common.a.r());
        int i = iC / 2;
        this.k.setPadding(i, 0, i, 0);
        this.k.setGravity(16);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        gradientDrawable.setAlpha(102);
        gradientDrawable.setCornerRadius(i);
        if (Build.VERSION.SDK_INT >= 16) {
            this.k.setBackground(gradientDrawable);
        } else {
            this.k.setBackgroundDrawable(gradientDrawable);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, iC);
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        int i2 = (iC * 2) / 7;
        layoutParams.setMargins(i2, 0, i2, iC);
        addView(this.k, layoutParams);
        TextView textView = new TextView(context);
        textView.setId(com.sigmob.sdk.common.a.r());
        textView.setTextColor(-1);
        textView.setSingleLine();
        textView.setGravity(17);
        textView.setText(str);
        textView.setTextSize(1, 20.0f);
        textView.getPaint().setFakeBoldText(true);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.k.addView(textView, layoutParams2);
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageBitmap(com.sigmob.sdk.base.views.d.SKIP_ICON.a());
        int i3 = iC / 7;
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i3, (iC * 3) / 14);
        layoutParams3.addRule(1, textView.getId());
        layoutParams3.addRule(15);
        layoutParams3.setMargins(i3, 0, 0, 0);
        this.k.addView(imageView, layoutParams3);
    }

    void a(boolean z) {
        if (z) {
            BaseBroadcastReceiver.a(this.d, this.f.getUuid(), IntentActions.ACTION_INTERSTITIAL_DISMISS);
            this.e.setOnTouchListener(null);
            this.g.c();
        }
        if (this.q != null) {
            this.q.unregisterListener(this.s);
        }
        this.c.setOnTouchListener(null);
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
    }

    public boolean a(BaseAdUnit baseAdUnit) {
        try {
            this.f = baseAdUnit;
            this.g = (d) baseAdUnit.getAdConfig();
            this.g.a(getContext(), baseAdUnit, new e.b() { // from class: com.sigmob.sdk.splash.SplashAdView.6
                @Override // com.sigmob.sdk.base.views.e.b
                public void a() {
                    SplashAdView.this.a(true);
                }

                @Override // com.sigmob.sdk.base.views.e.b
                public void a(String str, String str2) {
                }

                @Override // com.sigmob.sdk.base.views.e.b
                public void b() {
                    SplashAdView.this.r = true;
                }
            });
            b(this.d);
            if (this.f.isUse_floating_btn()) {
                a(this.d);
            }
            switch (this.f.getMaterial().template_type.intValue()) {
                case 2:
                case 3:
                    d(this.d);
                    break;
                case 4:
                    i(this.d);
                    break;
                case 5:
                    i(this.d);
                    f(this.d);
                    break;
                default:
                    h(this.d);
                    break;
            }
            e(this.d);
            return true;
        } catch (Throwable th) {
            SigmobLog.e("setupAd error", th);
            return false;
        }
    }

    protected void b() {
        super.setVisibility(4);
    }

    public boolean c() {
        return this.c.a(this.f);
    }

    public void d() {
        try {
            SigMacroCommon macroCommon = this.f.getMacroCommon();
            if (macroCommon instanceof SigMacroCommon) {
                macroCommon.addMarcoKey(SigMacroCommon._AUTOCLICK_, String.valueOf(this.g.f()));
            }
        } catch (Throwable th) {
            SigmobLog.e("splash click macro set " + th.getMessage());
        }
        a(String.format("%d,%d,%d,%d", 0, 0, 0, 0));
    }

    public void e() {
        this.c.b();
    }

    public void f() {
        this.c.c();
    }

    public int getDuration() {
        return this.c.getDuration();
    }

    public void setDuration(int i) {
        TextView textView;
        String strA;
        if (this.f == null) {
            return;
        }
        if (i <= 0 || this.j == null) {
            if (this.r) {
                return;
            }
            a(true);
            return;
        }
        SigmobLog.e("duration " + String.valueOf(i));
        switch (this.f.getMaterial().template_type.intValue()) {
            case 2:
            case 3:
            case 4:
            case 5:
                textView = this.j;
                strA = com.sigmob.sdk.base.b.a(Integer.valueOf(i));
                break;
            default:
                textView = this.j;
                strA = com.sigmob.sdk.base.b.b(Integer.valueOf(i));
                break;
        }
        textView.setText(strA);
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        if (this.c != null) {
            this.c.setOnTouchListener(onTouchListener);
        }
    }

    public void setShowAppLogo(boolean z) {
        this.i = z;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i == 0) {
            this.e.setVisibility(0);
            this.c.a();
            this.g.a(this.d, 0, this.f);
            super.setVisibility(i);
            return;
        }
        try {
            t.a(PointCategory.SPLASHADBLOCK, SigmobError.ERROR_SPLASH_ADBLOCK.getErrorCode(), m.a.a(), this.f);
            SigmobLog.e("debug " + m.a.a());
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
        }
    }
}
