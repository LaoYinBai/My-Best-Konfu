package com.sigmob.sdk.base.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.p;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.PlacementType;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.mraid.CloseableLayout;
import com.sigmob.sdk.mraid.MraidWebView;
import com.sigmob.sdk.mraid.c;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g extends Dialog implements DialogInterface.OnShowListener {
    protected p a;
    private View b;
    private Window c;
    private a d;
    private RelativeLayout e;
    private com.sigmob.sdk.videoAd.a f;
    private BaseAdUnit g;
    private com.sigmob.sdk.mraid.c h;
    private Context i;
    private String j;
    private int k;
    private int l;
    private boolean m;

    public interface a {
        void a();
    }

    public g(Context context, String str, BaseAdUnit baseAdUnit, com.sigmob.sdk.videoAd.a aVar) {
        super(context, com.sigmob.sdk.base.b.d());
        this.c = null;
        this.k = 0;
        this.l = 0;
        this.m = false;
        this.i = context.getApplicationContext();
        this.g = baseAdUnit;
        this.f = aVar;
        this.j = str;
        this.b = b();
    }

    private void d() {
        this.c = getWindow();
        if (this.c != null) {
            this.c.setGravity(80);
            int iE = com.sigmob.sdk.base.b.e();
            if (iE != 0) {
                this.c.setWindowAnimations(iE);
            }
            this.c.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = this.c.getAttributes();
            int i = this.i.getResources().getDisplayMetrics().widthPixels;
            int i2 = this.i.getResources().getDisplayMetrics().heightPixels;
            if (this.l - 1 <= i) {
                attributes.width = -1;
            } else {
                attributes.width = this.l;
            }
            if (this.k - 1 <= i2) {
                attributes.height = -1;
            } else {
                attributes.height = this.k;
            }
            SigmobLog.i("RecommendDialog windowDeploy: " + i + "====" + i2 + "====" + attributes.width + "====" + attributes.height);
            this.c.setAttributes(attributes);
        }
    }

    public void a(com.sigmob.sdk.base.common.a aVar) {
        if (this.a != null) {
            this.a.a(aVar, 0);
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public boolean a() {
        if (this.l <= 0 || this.k <= 0) {
            this.m = true;
        }
        return this.m;
    }

    public View b() {
        if (this.h == null) {
            this.h = new com.sigmob.sdk.mraid.c(this.i, this.g, PlacementType.INTERSTITIAL);
        }
        this.h.a(new c.f() { // from class: com.sigmob.sdk.base.views.g.1
            @Override // com.sigmob.sdk.mraid.c.f
            public void a() {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void a(Integer num) {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void a(Map<String, String> map) {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void b() {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void b(Map<String, String> map) {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void c() {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void d() {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void e() {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void f() {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void g() {
            }

            @Override // com.sigmob.sdk.mraid.c.f
            public void h() {
            }
        });
        this.h.a(new c.a() { // from class: com.sigmob.sdk.base.views.g.2
            @Override // com.sigmob.sdk.mraid.c.a
            public void a() {
                SigmobLog.d("RecommendDialog onEndCardShow()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(float f) {
                SigmobLog.d("RecommendDialog onReward()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(int i, int i2, int i3, int i4, CloseableLayout.a aVar, boolean z) {
                int i5 = g.this.i.getResources().getDisplayMetrics().widthPixels;
                int i6 = g.this.i.getResources().getDisplayMetrics().heightPixels;
                SigmobLog.i("RecommendDialog Origin onResize :" + i + "==" + i2 + "==" + i3 + "==" + i4 + "==" + z);
                g.this.l = com.sigmob.sdk.common.utils.c.b((float) i, g.this.i);
                g.this.k = com.sigmob.sdk.common.utils.c.b((float) i2, g.this.i);
                int iB = com.sigmob.sdk.common.utils.c.b((float) i3, g.this.i);
                int iB2 = com.sigmob.sdk.common.utils.c.b((float) i4, g.this.i);
                if (g.this.b != null) {
                    if (!z) {
                        if (iB < 0) {
                            iB = 0;
                        }
                        if (iB > i5) {
                            iB = i5;
                        }
                        if (iB2 < 0) {
                            iB2 = 0;
                        }
                        if (iB2 > i6) {
                            iB2 = i6;
                        }
                        if (g.this.l + iB > i5) {
                            g.this.l = i5 - iB;
                        }
                        if (g.this.k + iB2 > i6) {
                            g.this.k = i6 - iB2;
                        }
                    }
                    SigmobLog.i("RecommendDialog onResize: " + g.this.l + "==" + g.this.k + "==" + iB + "==" + iB2);
                    if (g.this.l <= 0 || g.this.k <= 0) {
                        g.this.m = true;
                    }
                    g.this.b.setX(iB);
                    g.this.b.setY(iB2);
                    g.this.b.setLayoutParams(new RelativeLayout.LayoutParams(g.this.l, g.this.k));
                    g.this.b.requestLayout();
                }
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(View view) {
                SigmobLog.d("RecommendDialog onLoaded()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(SigmobError sigmobError) {
                SigmobLog.i("RecommendDialog onRenderProcessGone:" + sigmobError.toString());
                g.this.m = true;
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(String str) throws UnsupportedEncodingException {
                boolean z;
                SigmobLog.i("RecommendDialog onCompanionClick:" + str);
                boolean z2 = true;
                if (TextUtils.isEmpty(str)) {
                    z = true;
                } else {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int iOptInt = jSONObject.optInt("type");
                        g.this.h.a(String.valueOf(jSONObject.optInt("x")), String.valueOf(jSONObject.optInt("y")));
                        if (iOptInt != 1) {
                            g.this.a(com.sigmob.sdk.base.common.a.AD_COMPANION_CLICK);
                        } else {
                            z2 = false;
                        }
                        z = z2;
                    } catch (Exception unused) {
                        g.this.h.a(Constants.FAIL, Constants.FAIL);
                        g.this.a(com.sigmob.sdk.base.common.a.AD_COMPANION_CLICK);
                    }
                }
                g.this.f.b(g.this.g, g.this.i, (String) null, com.sigmob.sdk.base.a.COMPANION, g.this.h.a(), z);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(URI uri, com.sigmob.sdk.videoAd.f fVar, String str) throws UnsupportedEncodingException {
                boolean z;
                boolean z2;
                int iOptInt;
                int iOptInt2;
                int iOptInt3;
                boolean zOptBoolean;
                SigmobLog.i("RecommendDialog  onOpen:" + uri + "======" + fVar + "=====" + str);
                boolean z3 = false;
                boolean z4 = true;
                if (TextUtils.isEmpty(str)) {
                    g.this.h.a(Constants.FAIL, Constants.FAIL);
                    g.this.a(com.sigmob.sdk.base.common.a.AD_CLICK);
                    z2 = true;
                    z = false;
                } else {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        iOptInt = jSONObject.optInt("type");
                        iOptInt2 = jSONObject.optInt("x");
                        iOptInt3 = jSONObject.optInt("y");
                        zOptBoolean = jSONObject.optBoolean("disable_landing");
                    } catch (Exception unused) {
                    }
                    try {
                        g.this.h.a(String.valueOf(iOptInt2), String.valueOf(iOptInt3));
                        if (iOptInt != 1) {
                            g.this.a(com.sigmob.sdk.base.common.a.AD_CLICK);
                        } else {
                            z4 = false;
                        }
                        z = zOptBoolean;
                        z2 = z4;
                    } catch (Exception unused2) {
                        z3 = zOptBoolean;
                        g.this.h.a(Constants.FAIL, Constants.FAIL);
                        g.this.a(com.sigmob.sdk.base.common.a.AD_CLICK);
                        z = z3;
                        z2 = true;
                        g.this.f.a(g.this.g, g.this.i, uri.toString(), com.sigmob.sdk.base.a.ENDCARD, g.this.h.a(), z2, z);
                    }
                }
                g.this.f.a(g.this.g, g.this.i, uri.toString(), com.sigmob.sdk.base.a.ENDCARD, g.this.h.a(), z2, z);
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void a(boolean z) {
                SigmobLog.d("RecommendDialog onMute()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void b() {
                SigmobLog.d("RecommendDialog onShowSkipTime()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void b(float f) {
                SigmobLog.d("RecommendDialog onSkip()");
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void c() {
                SigmobLog.i("RecommendDialog onFailedToLoad()");
                g.this.m = true;
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void d() {
                SigmobLog.i("RecommendDialog onUnload()");
                g.this.dismiss();
                g.this.a(com.sigmob.sdk.base.common.a.AD_CLOSE_CARD_CLOSE);
                g.this.c();
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void e() {
            }

            @Override // com.sigmob.sdk.mraid.c.a
            public void f() {
                SigmobLog.i("RecommendDialog onClose()");
                g.this.a(com.sigmob.sdk.base.common.a.AD_FINISH);
                if (g.this.d != null) {
                    g.this.d.a();
                }
            }
        });
        if (!TextUtils.isEmpty(this.g.getCloseCardHtmlData())) {
            this.h.a(this.g.getCloseCardHtmlData(), new c.b() { // from class: com.sigmob.sdk.base.views.g.3
                @Override // com.sigmob.sdk.mraid.c.b
                public void a(MraidWebView mraidWebView, p pVar) {
                    if (pVar != null) {
                        g.this.a = pVar;
                        return;
                    }
                    g.this.a = new p(g.this.i);
                    g.this.a.a(g.this.i, g.this.g, g.this.j);
                }
            });
        }
        return this.h.o();
    }

    public void c() {
        if (this.h != null) {
            this.d = null;
            this.h.k();
            this.h = null;
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = new RelativeLayout(getContext());
        this.e.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.e.setBackgroundColor(0);
        setContentView(this.e);
        setOnShowListener(this);
        SigmobLog.i("RecommendDialog onCreate:" + this.l + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + this.k);
        if (this.b != null) {
            this.e.addView(this.b);
        }
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        d();
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        SigmobLog.i("RecommendDialog  onShow");
        a(com.sigmob.sdk.base.common.a.AD_CLOSE_CARD_SHOW);
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        getWindow().getDecorView().setSystemUiVisibility(6918);
    }
}
