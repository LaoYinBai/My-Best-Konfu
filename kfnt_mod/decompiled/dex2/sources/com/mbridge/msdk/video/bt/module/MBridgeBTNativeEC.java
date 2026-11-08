package com.mbridge.msdk.video.bt.module;

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
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.heytap.mcssdk.mode.CommandMessage;
import com.mbridge.msdk.click.c;
import com.mbridge.msdk.foundation.controller.a;
import com.mbridge.msdk.foundation.same.c.b;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.video.js.a.j;
import com.mbridge.msdk.video.module.a.a.d;
import com.mbridge.msdk.video.module.a.a.i;
import com.mbridge.msdk.videocommon.view.RoundImageView;
import com.mbridge.msdk.videocommon.view.StarLevelView;
import com.mbridge.msdk.widget.MBDownloadProgressBar;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBridgeBTNativeEC extends BTBaseView {
    private TextView A;
    private StarLevelView B;
    private boolean C;
    private boolean D;
    private int E;
    private Runnable F;
    private boolean G;
    private View H;
    private MBDownloadProgressBar I;
    private String J;
    private j K;
    private WebView L;
    private ViewGroup p;
    private ViewGroup q;
    private RelativeLayout r;
    private ImageView s;
    private RoundImageView t;
    private ImageView u;
    private ImageView v;
    private ImageView w;
    private ImageView x;
    private TextView y;
    private TextView z;

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    public void onDestory() {
    }

    public MBridgeBTNativeEC(Context context) {
        super(context);
        this.C = false;
        this.D = false;
        this.E = 0;
        this.G = false;
    }

    public MBridgeBTNativeEC(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.C = false;
        this.D = false;
        this.E = 0;
        this.G = false;
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    public void setUnitId(String str) {
        this.J = str;
    }

    public void setJSCommon(j jVar) {
        this.K = jVar;
    }

    public void setCreateWebView(WebView webView) {
        this.L = webView;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() throws JSONException {
        super.onAttachedToWindow();
        if (this.F == null) {
            this.F = new Runnable() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC.1
                @Override // java.lang.Runnable
                public final void run() {
                    MBridgeBTNativeEC.this.D = true;
                    if (MBridgeBTNativeEC.this.H != null) {
                        MBridgeBTNativeEC.this.H.setVisibility(0);
                    }
                }
            };
        }
        if (this.F != null) {
            postDelayed(this.F, this.E * 1000);
        }
        if (!this.h && this.L != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", this.d);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("unitId", this.J);
                jSONObject.put("data", jSONObject2);
                s.a(BTBaseView.TAG, "NativeEC Call H5 onCloseBtnClicked " + jSONObject.toString());
            } catch (JSONException e) {
                s.a(BTBaseView.TAG, e.getMessage());
            }
            h.a().a(this.L, "onCloseBtnClicked", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        }
        if (this.L != null) {
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("id", this.d);
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("unitId", this.J);
                jSONObject3.put("data", jSONObject4);
                s.a(BTBaseView.TAG, "NativeEC Call H5 onEndCardShow " + jSONObject3.toString());
            } catch (JSONException e2) {
                s.a(BTBaseView.TAG, e2.getMessage());
            }
            h.a().a(this.L, "onNativeECShow", Base64.encodeToString(jSONObject3.toString().getBytes(), 2));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.F != null) {
            removeCallbacks(this.F);
        }
    }

    public void preLoadData() {
        Bitmap bitmapBlurBitmap;
        try {
            if (this.b == null || !this.h) {
                return;
            }
            if (this.e != null) {
                this.E = this.e.o();
            }
            b.a(this.a.getApplicationContext()).a(this.b.getImageUrl(), new d(this.t, this.b, this.J));
            b.a(this.a.getApplicationContext()).a(this.b.getIconUrl(), new i(this.u, w.b(a.f().j(), 8.0f)));
            this.y.setText(this.b.getAppName());
            this.z.setText(this.b.getAppDesc());
            this.A.setText(this.b.getNumberRating() + ")");
            this.B.removeAllViews();
            this.I.setUniqueKey(this.b.getAkdlui());
            this.I.setLinkType(this.b.getLinkType());
            this.I.setCtaldtypeUrl(this.b.getMof_template_url());
            this.I.setText(this.b.getAdCall());
            double rating = this.b.getRating();
            if (rating <= 0.0d) {
                rating = 5.0d;
            }
            this.B.initScore(rating);
            if (Build.VERSION.SDK_INT < 17) {
                this.s.setVisibility(8);
                return;
            }
            try {
                Bitmap bitmapA = a(this.t.getDrawable());
                if (bitmapA != null && (bitmapBlurBitmap = blurBitmap(bitmapA)) != null) {
                    this.s.setImageBitmap(bitmapBlurBitmap);
                }
            } catch (Throwable unused) {
                this.s.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.b.getendcard_url()) && this.b.getendcard_url().contains("alecfc=1")) {
                this.C = true;
            }
            if (!TextUtils.isEmpty(this.b.getendcard_url()) && this.b.getendcard_url().contains("wlgo=1")) {
                this.G = true;
            }
            String language = Locale.getDefault().getLanguage();
            if (!TextUtils.isEmpty(language) && language.equals("zh")) {
                this.v.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("mbridge_reward_flag_cn", "drawable", a.f().d())));
            } else {
                this.v.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("mbridge_reward_flag_en", "drawable", a.f().d())));
            }
            if (!this.G) {
                this.v.setVisibility(4);
                this.x.setVisibility(4);
            }
            com.mbridge.msdk.b.a aVarB = com.mbridge.msdk.b.b.a().b(a.f().k());
            if (aVarB != null) {
                final String strAe = aVarB.ae();
                if (TextUtils.isEmpty(strAe)) {
                    this.w.setVisibility(8);
                }
                this.w.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC.2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        c.b(MBridgeBTNativeEC.this.a, strAe);
                    }
                });
            } else {
                this.w.setVisibility(8);
            }
            if (this.D) {
                return;
            }
            this.H.setVisibility(8);
        } catch (Throwable th) {
            s.a(BTBaseView.TAG, th.getMessage());
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    public void onSelfConfigurationChanged(Configuration configuration) {
        super.onSelfConfigurationChanged(configuration);
        this.g = configuration.orientation;
        s.d(BTBaseView.TAG, " native onSelfConfigurationChanged:" + this.g);
        if (this.g == 2) {
            removeView(this.p);
            a(this.q);
        } else {
            removeView(this.q);
            a(this.p);
        }
    }

    private void a(View view) {
        if (view == null) {
            init(this.a);
            preLoadData();
            return;
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        addView(view);
        b(view);
        a();
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    protected final void a() {
        try {
            if (this.h) {
                this.r.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC.3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) throws JSONException {
                        if (MBridgeBTNativeEC.this.C) {
                            MBridgeBTNativeEC.a(MBridgeBTNativeEC.this, 1, view.getContext());
                            MBridgeBTNativeEC.a(MBridgeBTNativeEC.this, view.getX(), view.getY());
                        }
                    }
                });
                this.H.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC.4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) throws JSONException {
                        if (MBridgeBTNativeEC.this.L != null) {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("id", MBridgeBTNativeEC.this.d);
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("unitId", MBridgeBTNativeEC.this.J);
                                jSONObject.put("data", jSONObject2);
                                s.a(BTBaseView.TAG, "NativeEC Call H5 onCloseBtnClicked " + jSONObject.toString());
                            } catch (JSONException e) {
                                s.a(BTBaseView.TAG, e.getMessage());
                            }
                            h.a().a(MBridgeBTNativeEC.this.L, "onCloseBtnClicked", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
                        }
                    }
                });
                this.I.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC.5
                    @Override // com.mbridge.msdk.widget.a
                    protected final void a(View view) throws JSONException {
                        MBridgeBTNativeEC.a(MBridgeBTNativeEC.this, 0, view.getContext());
                        MBridgeBTNativeEC.a(MBridgeBTNativeEC.this, view.getX(), view.getY());
                    }
                });
                this.u.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC.6
                    @Override // com.mbridge.msdk.widget.a
                    protected final void a(View view) throws JSONException {
                        if (MBridgeBTNativeEC.this.I.NotProgressBarInterceptedClick()) {
                            MBridgeBTNativeEC.a(MBridgeBTNativeEC.this, 0, view.getContext());
                        }
                        MBridgeBTNativeEC.a(MBridgeBTNativeEC.this, view.getX(), view.getY());
                    }
                });
                this.t.setOnClickListener(new com.mbridge.msdk.widget.a() { // from class: com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC.7
                    @Override // com.mbridge.msdk.widget.a
                    protected final void a(View view) throws JSONException {
                        if (MBridgeBTNativeEC.this.I.NotProgressBarInterceptedClick()) {
                            MBridgeBTNativeEC.a(MBridgeBTNativeEC.this, 0, view.getContext());
                        }
                        MBridgeBTNativeEC.a(MBridgeBTNativeEC.this, view.getX(), view.getY());
                    }
                });
            }
        } catch (Throwable th) {
            s.a(BTBaseView.TAG, th.getMessage());
        }
    }

    private boolean b(View view) {
        try {
            this.r = (RelativeLayout) view.findViewById(findID("mbridge_native_ec_layout"));
            this.s = (ImageView) view.findViewById(findID("mbridge_iv_adbanner_bg"));
            this.t = (RoundImageView) view.findViewById(findID("mbridge_iv_adbanner"));
            this.u = (ImageView) view.findViewById(findID("mbridge_iv_icon"));
            this.v = (ImageView) view.findViewById(findID("mbridge_iv_flag"));
            this.w = (ImageView) view.findViewById(findID("mbridge_iv_link"));
            this.y = (TextView) view.findViewById(findID("mbridge_tv_apptitle"));
            this.z = (TextView) view.findViewById(findID("mbridge_tv_appdesc"));
            this.A = (TextView) view.findViewById(findID("mbridge_tv_number"));
            this.B = (StarLevelView) view.findViewById(findID("mbridge_sv_starlevel"));
            this.H = view.findViewById(findID("mbridge_iv_close"));
            this.I = (MBDownloadProgressBar) view.findViewById(findID("mbridge_tv_cta"));
            this.x = (ImageView) view.findViewById(findID("mbridge_iv_logo"));
            return isNotNULL(this.s, this.t, this.u, this.y, this.z, this.A, this.B, this.H, this.I);
        } catch (Throwable th) {
            s.b(BTBaseView.TAG, th.getMessage(), th);
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
            scriptIntrinsicBlurCreate.setRadius(10.0f);
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
            s.a(BTBaseView.TAG, th.getMessage());
            return null;
        }
    }

    @Override // com.mbridge.msdk.video.bt.module.BTBaseView
    public void init(Context context) {
        boolean zB;
        int iFindLayout = findLayout(isLandscape() ? "mbridge_reward_endcard_native_land" : "mbridge_reward_endcard_native_hor");
        if (iFindLayout > 0) {
            if (isLandscape()) {
                this.q = (ViewGroup) this.f.inflate(iFindLayout, (ViewGroup) null);
                addView(this.q);
                zB = b(this.q);
            } else {
                this.p = (ViewGroup) this.f.inflate(iFindLayout, (ViewGroup) null);
                addView(this.p);
                zB = b(this.p);
            }
            this.h = zB;
            a();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ void a(com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC r2, int r3, android.content.Context r4) {
        /*
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L12 org.json.JSONException -> L14
            r1.<init>()     // Catch: java.lang.Throwable -> L12 org.json.JSONException -> L14
            java.lang.String r0 = com.mbridge.msdk.foundation.same.a.o     // Catch: org.json.JSONException -> L10 java.lang.Throwable -> L12
            org.json.JSONObject r3 = r2.a(r3)     // Catch: org.json.JSONException -> L10 java.lang.Throwable -> L12
            r1.put(r0, r3)     // Catch: org.json.JSONException -> L10 java.lang.Throwable -> L12
            goto L19
        L10:
            r3 = move-exception
            goto L16
        L12:
            r2 = move-exception
            goto L67
        L14:
            r3 = move-exception
            r1 = r0
        L16:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L12
        L19:
            com.mbridge.msdk.video.js.a.j r3 = r2.K     // Catch: java.lang.Throwable -> L12
            if (r3 == 0) goto L70
            if (r4 == 0) goto L2a
            android.content.Context r3 = r4.getApplicationContext()     // Catch: java.lang.Throwable -> L12
            if (r4 == r3) goto L2a
            com.mbridge.msdk.video.js.a.j r3 = r2.K     // Catch: java.lang.Throwable -> L12
            r3.a(r4)     // Catch: java.lang.Throwable -> L12
        L2a:
            com.mbridge.msdk.foundation.entity.CampaignEx r3 = r2.b     // Catch: java.lang.Throwable -> L12
            if (r3 == 0) goto L57
            com.mbridge.msdk.video.bt.a.c r3 = com.mbridge.msdk.video.bt.a.c.a()     // Catch: java.lang.Throwable -> L12
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L12
            r4.<init>()     // Catch: java.lang.Throwable -> L12
            java.lang.String r0 = r2.J     // Catch: java.lang.Throwable -> L12
            r4.append(r0)     // Catch: java.lang.Throwable -> L12
            java.lang.String r0 = "_"
            r4.append(r0)     // Catch: java.lang.Throwable -> L12
            com.mbridge.msdk.foundation.entity.CampaignEx r0 = r2.b     // Catch: java.lang.Throwable -> L12
            java.lang.String r0 = r0.getRequestId()     // Catch: java.lang.Throwable -> L12
            r4.append(r0)     // Catch: java.lang.Throwable -> L12
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L12
            android.app.Activity r3 = r3.d(r4)     // Catch: java.lang.Throwable -> L12
            com.mbridge.msdk.video.js.a.j r4 = r2.K     // Catch: java.lang.Throwable -> L12
            r4.a(r3)     // Catch: java.lang.Throwable -> L12
        L57:
            com.mbridge.msdk.video.js.a.j r2 = r2.K     // Catch: java.lang.Throwable -> L12
            r3 = 1
            if (r1 == 0) goto L61
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Throwable -> L12
            goto L63
        L61:
            java.lang.String r4 = ""
        L63:
            r2.click(r3, r4)     // Catch: java.lang.Throwable -> L12
            goto L70
        L67:
            java.lang.String r3 = "BTBaseView"
            java.lang.String r2 = r2.getMessage()
            com.mbridge.msdk.foundation.tools.s.a(r3, r2)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC.a(com.mbridge.msdk.video.bt.module.MBridgeBTNativeEC, int, android.content.Context):void");
    }

    static /* synthetic */ void a(MBridgeBTNativeEC mBridgeBTNativeEC, float f, float f2) throws JSONException {
        if (mBridgeBTNativeEC.L != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(CommandMessage.CODE, n);
                jSONObject.put("id", mBridgeBTNativeEC.d);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", String.valueOf(f));
                jSONObject2.put("y", String.valueOf(f2));
                jSONObject.put("data", jSONObject2);
                h.a().a(mBridgeBTNativeEC.L, "onClicked", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            } catch (Exception unused) {
                com.mbridge.msdk.video.bt.a.c.a().a(mBridgeBTNativeEC.L, "onClicked", mBridgeBTNativeEC.d);
            }
        }
    }
}
