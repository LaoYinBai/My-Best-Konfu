package com.mbridge.msdk.video.module;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mbridge.msdk.click.c;
import com.mbridge.msdk.foundation.controller.a;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.video.js.factory.b;
import com.mbridge.msdk.video.module.a.a.d;
import com.mbridge.msdk.video.module.a.a.i;
import com.mbridge.msdk.videocommon.view.RoundImageView;
import com.mbridge.msdk.videocommon.view.StarLevelView;
import com.mbridge.msdk.widget.MBDownloadProgressBar;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeNativeEndCardView extends MBridgeBaseView {
    private int A;
    private boolean B;
    private AlphaAnimation C;
    private int D;
    private int E;
    private int F;
    private int G;
    private View H;
    private MBDownloadProgressBar I;
    private String J;
    private ViewGroup i;
    private ViewGroup j;
    private RelativeLayout k;
    private ImageView l;
    private RoundImageView m;
    private ImageView n;
    private ImageView o;
    private ImageView p;
    private ImageView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private StarLevelView u;
    private Runnable v;
    private RelativeLayout w;
    private b x;
    private boolean y;
    private boolean z;

    public void setCloseBtnDelay(int i) {
        this.A = i;
    }

    public void setUnitId(String str) {
        this.J = str;
    }

    public MBridgeNativeEndCardView(Context context) {
        super(context);
        this.y = false;
        this.z = false;
        this.A = 0;
        this.B = false;
        init(context);
    }

    public MBridgeNativeEndCardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y = false;
        this.z = false;
        this.A = 0;
        this.B = false;
        init(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.v == null) {
            this.v = new Runnable() { // from class: com.mbridge.msdk.video.module.MBridgeNativeEndCardView.1
                @Override // java.lang.Runnable
                public final void run() {
                    MBridgeNativeEndCardView.this.z = true;
                    if (MBridgeNativeEndCardView.this.H != null) {
                        MBridgeNativeEndCardView.this.H.setVisibility(0);
                    }
                }
            };
        }
        if (this.v != null) {
            postDelayed(this.v, this.A * 1000);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.v != null) {
            removeCallbacks(this.v);
        }
    }

    public void preLoadData(b bVar) {
        Bitmap bitmapBlurBitmap;
        this.x = bVar;
        try {
            if (this.b == null || !this.f) {
                return;
            }
            com.mbridge.msdk.foundation.same.c.b.a(this.a.getApplicationContext()).a(this.b.getImageUrl(), new d(this.m, this.b, this.J));
            com.mbridge.msdk.foundation.same.c.b.a(this.a.getApplicationContext()).a(this.b.getIconUrl(), new i(this.n, w.b(a.f().j(), 8.0f)));
            this.r.setText(this.b.getAppName());
            this.s.setText(this.b.getAppDesc());
            this.t.setText(this.b.getNumberRating() + ")");
            this.u.removeAllViews();
            this.I.setUniqueKey(this.b.getAkdlui());
            this.I.setLinkType(this.b.getLinkType());
            this.I.setCtaldtypeUrl(this.b.getendcard_url());
            this.I.setText(this.b.getAdCall());
            double rating = this.b.getRating();
            if (rating <= 0.0d) {
                rating = 5.0d;
            }
            this.u.initScore(rating);
            if (Build.VERSION.SDK_INT < 17) {
                this.l.setVisibility(8);
                return;
            }
            try {
                Bitmap bitmapA = a(this.m.getDrawable());
                if (bitmapA != null && (bitmapBlurBitmap = blurBitmap(bitmapA)) != null) {
                    this.l.setImageBitmap(bitmapBlurBitmap);
                }
            } catch (Throwable unused) {
                this.l.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.b.getendcard_url()) && this.b.getendcard_url().contains("alecfc=1")) {
                this.y = true;
            }
            if (!TextUtils.isEmpty(this.b.getendcard_url()) && this.b.getendcard_url().contains("wlgo=1")) {
                this.B = true;
            }
            String language = Locale.getDefault().getLanguage();
            if (!TextUtils.isEmpty(language) && language.equals("zh")) {
                this.o.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("mbridge_reward_flag_cn", "drawable", a.f().d())));
            } else {
                this.o.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("mbridge_reward_flag_en", "drawable", a.f().d())));
            }
            if (!this.B) {
                this.o.setVisibility(4);
                this.q.setVisibility(4);
            }
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(a.f().k());
            if (aVarB != null) {
                final String strAe = aVarB.ae();
                if (TextUtils.isEmpty(strAe)) {
                    this.p.setVisibility(8);
                }
                this.p.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.module.MBridgeNativeEndCardView.2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        c.b(MBridgeNativeEndCardView.this.a, strAe);
                    }
                });
            } else {
                this.p.setVisibility(8);
            }
            if (this.z) {
                return;
            }
            this.H.setVisibility(8);
        } catch (Throwable th) {
            s.a(MBridgeBaseView.TAG, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void onSelfConfigurationChanged(Configuration configuration) {
        super.onSelfConfigurationChanged(configuration);
        this.w.setVisibility(4);
        this.d = configuration.orientation;
        s.d(MBridgeBaseView.TAG, " native onSelfConfigurationChanged:" + this.d);
        if (this.d == 2) {
            removeView(this.i);
            a(this.j);
        } else {
            removeView(this.j);
            a(this.i);
        }
    }

    public boolean canBackPress() {
        return this.H != null && this.H.getVisibility() == 0;
    }

    public void notifyShowListener() {
        this.e.a(110, "");
    }

    private void a(View view) {
        if (view == null) {
            init(this.a);
            preLoadData(this.x);
        } else {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            addView(view);
            b(view);
            c();
        }
        a();
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    protected final void c() {
        if (this.f) {
            this.k.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.module.MBridgeNativeEndCardView.3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) throws JSONException {
                    if (MBridgeNativeEndCardView.this.y) {
                        MBridgeNativeEndCardView.a(MBridgeNativeEndCardView.this, 1);
                    }
                }
            });
            this.H.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.module.MBridgeNativeEndCardView.4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MBridgeNativeEndCardView.this.e.a(104, "");
                }
            });
            this.I.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.video.module.MBridgeNativeEndCardView.5
                @Override // com.mbridge.msdk.widget.a
                protected final void a(View view) throws JSONException {
                    MBridgeNativeEndCardView.a(MBridgeNativeEndCardView.this, 0);
                }
            });
            this.n.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.video.module.MBridgeNativeEndCardView.6
                @Override // com.mbridge.msdk.widget.a
                protected final void a(View view) throws JSONException {
                    if (MBridgeNativeEndCardView.this.I.NotProgressBarInterceptedClick()) {
                        MBridgeNativeEndCardView.a(MBridgeNativeEndCardView.this, 0);
                    }
                }
            });
            this.m.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.video.module.MBridgeNativeEndCardView.7
                @Override // com.mbridge.msdk.widget.a
                protected final void a(View view) throws JSONException {
                    if (MBridgeNativeEndCardView.this.I.NotProgressBarInterceptedClick()) {
                        MBridgeNativeEndCardView.a(MBridgeNativeEndCardView.this, 0);
                    }
                }
            });
        }
    }

    private boolean b(View view) {
        try {
            this.k = (RelativeLayout) view.findViewById(findID("mbridge_native_ec_layout"));
            this.l = (ImageView) view.findViewById(findID("mbridge_iv_adbanner_bg"));
            this.m = (RoundImageView) view.findViewById(findID("mbridge_iv_adbanner"));
            this.n = (ImageView) view.findViewById(findID("mbridge_iv_icon"));
            this.o = (ImageView) view.findViewById(findID("mbridge_iv_flag"));
            this.p = (ImageView) view.findViewById(findID("mbridge_iv_link"));
            this.r = (TextView) view.findViewById(findID("mbridge_tv_apptitle"));
            this.s = (TextView) view.findViewById(findID("mbridge_tv_appdesc"));
            this.t = (TextView) view.findViewById(findID("mbridge_tv_number"));
            this.u = (StarLevelView) view.findViewById(findID("mbridge_sv_starlevel"));
            this.H = view.findViewById(findID("mbridge_iv_close"));
            this.I = (MBDownloadProgressBar) view.findViewById(findID("mbridge_tv_cta"));
            this.q = (ImageView) view.findViewById(findID("mbridge_iv_logo"));
            this.w = (RelativeLayout) view.findViewById(findID("mbridge_native_ec_controller"));
            return isNotNULL(this.l, this.m, this.n, this.r, this.s, this.t, this.u, this.H, this.I);
        } catch (Throwable th) {
            s.b(MBridgeBaseView.TAG, th.getMessage(), th);
            return false;
        }
    }

    public Bitmap blurBitmap(Bitmap bitmap) {
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            RenderScript renderScriptCreate = RenderScript.create(this.a.getApplicationContext());
            ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
            Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmap);
            Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateBitmap);
            scriptIntrinsicBlurCreate.setRadius(25.0f);
            scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
            scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
            allocationCreateFromBitmap2.copyTo(bitmapCreateBitmap);
            bitmap.recycle();
            renderScriptCreate.destroy();
            return bitmapCreateBitmap;
        } catch (Throwable unused) {
            return null;
        }
    }

    private Bitmap a(Drawable drawable) {
        try {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        } catch (Throwable th) {
            s.a(MBridgeBaseView.TAG, th.getMessage());
            return null;
        }
    }

    public void setNotchPadding(int i, int i2, int i3, int i4) {
        s.d(MBridgeBaseView.TAG, "NOTCH NativeEndCard " + String.format("%1s-%2s-%3s-%4s", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        this.D = i;
        this.E = i2;
        this.F = i3;
        this.G = i4;
        a();
    }

    private void a() {
        this.w.postDelayed(new Runnable() { // from class: com.mbridge.msdk.video.module.MBridgeNativeEndCardView.8
            @Override // java.lang.Runnable
            public final void run() {
                MBridgeNativeEndCardView.this.w.setPadding(MBridgeNativeEndCardView.this.D, MBridgeNativeEndCardView.this.F, MBridgeNativeEndCardView.this.E, MBridgeNativeEndCardView.this.G);
                MBridgeNativeEndCardView.this.w.startAnimation(MBridgeNativeEndCardView.this.C);
                MBridgeNativeEndCardView.this.w.setVisibility(0);
                if (MBridgeNativeEndCardView.this.H.getVisibility() == 0 || !MBridgeNativeEndCardView.this.z) {
                    return;
                }
                MBridgeNativeEndCardView.this.H.setVisibility(0);
            }
        }, 200L);
    }

    @Override // com.mbridge.msdk.video.module.MBridgeBaseView
    public void init(Context context) {
        boolean zB;
        int iFindLayout = findLayout(isLandscape() ? "mbridge_reward_endcard_native_land" : "mbridge_reward_endcard_native_hor");
        if (iFindLayout > 0) {
            if (isLandscape()) {
                this.j = (ViewGroup) this.c.inflate(iFindLayout, (ViewGroup) null);
                addView(this.j);
                zB = b(this.j);
            } else {
                this.i = (ViewGroup) this.c.inflate(iFindLayout, (ViewGroup) null);
                addView(this.i);
                zB = b(this.i);
            }
            this.f = zB;
            c();
            if (!this.f) {
                this.e.a(104, "");
            }
            this.C = new AlphaAnimation(0.0f, 100.0f);
            this.C.setDuration(200L);
        }
    }

    static /* synthetic */ void a(MBridgeNativeEndCardView mBridgeNativeEndCardView, int i) throws JSONException {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
        } catch (JSONException e) {
            e = e;
            jSONObject = null;
        }
        try {
            jSONObject.put(com.mbridge.msdk.foundation.same.a.o, mBridgeNativeEndCardView.a(i));
        } catch (JSONException e2) {
            e = e2;
            e.printStackTrace();
            mBridgeNativeEndCardView.e.a(105, jSONObject);
        }
        mBridgeNativeEndCardView.e.a(105, jSONObject);
    }
}
