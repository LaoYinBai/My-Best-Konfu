package com.mbridge.msdk.splash.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.splash.js.b;
import com.mbridge.msdk.splash.js.c;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBSplashView extends RelativeLayout {
    private static String a = "MBSplashView";
    private int b;
    private MBSplashWebview c;
    private ViewGroup d;
    private View e;
    private View f;
    private int g;
    private boolean h;
    private boolean i;
    private ViewGroup j;
    private RelativeLayout.LayoutParams k;
    private b l;

    public void setH5Ready(boolean z) {
        this.h = z;
    }

    public void setVideoReady(boolean z) {
        this.i = z;
    }

    public boolean isH5Ready() {
        return this.h;
    }

    public boolean isVideoReady() {
        return this.i;
    }

    public void resetLoadState() {
        this.i = false;
        this.h = false;
    }

    public b getSplashJSBridgeImpl() {
        return this.l;
    }

    public void setSplashJSBridgeImpl(b bVar) {
        this.l = bVar;
        if (this.c != null) {
            this.c.setObject(this.l);
        }
    }

    public MBSplashView(Context context) {
        this(context, null);
    }

    public MBSplashView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MBSplashView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b();
    }

    private void b() {
        setBackgroundColor(0);
        this.b = getResources().getConfiguration().orientation;
    }

    public MBSplashWebview getSplashWebview() {
        return this.c;
    }

    public void setSplashWebview(MBSplashWebview mBSplashWebview) {
        this.c = mBSplashWebview;
        if (this.l != null) {
            mBSplashWebview.setObject(this.l);
        }
    }

    public void changeCloseBtnState(int i) {
        if (this.e != null) {
            if (i == 2) {
                this.e.setVisibility(8);
            } else {
                this.e.setVisibility(0);
            }
        }
    }

    public View getCloseView() {
        return this.e;
    }

    public void setCloseView(View view) {
        this.e = view;
        if (view != null) {
            view.setContentDescription("closeButton");
        }
    }

    public View getIconVg() {
        return this.f;
    }

    public void setIconVg(View view, RelativeLayout.LayoutParams layoutParams) {
        this.f = view;
        this.k = layoutParams;
    }

    public void show() {
        if (this.l != null && this.j != null && (this.j.getContext() instanceof Activity)) {
            this.l.a(this.j.getContext());
        }
        if (this.f == null) {
            if (this.c != null && this.c.getParent() == null) {
                addView(this.c, new ViewGroup.LayoutParams(-1, -1));
            }
            c();
        } else {
            if (this.d == null) {
                this.d = new RelativeLayout(getContext());
                this.d.setId(2147482647);
            }
            if (this.b == 2) {
                this.g = w.h(getContext());
                if (this.c != null && this.c.getParent() == null) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams.addRule(0, this.d.getId());
                    addView(this.c, layoutParams);
                }
                c();
                if (this.d != null && this.d.getParent() == null) {
                    int i = this.k.width;
                    if (i > this.g / 4) {
                        i = this.g / 4;
                    }
                    this.d.addView(this.f, i, -1);
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i, -1);
                    layoutParams2.addRule(11);
                    layoutParams2.addRule(13);
                    addView(this.d, layoutParams2);
                }
            } else {
                this.g = w.g(getContext());
                if (this.c != null && this.c.getParent() == null) {
                    RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams3.addRule(2, this.d.getId());
                    addView(this.c, layoutParams3);
                }
                c();
                if (this.d != null && this.d.getParent() == null) {
                    int i2 = this.k.height;
                    if (i2 > this.g / 4) {
                        i2 = this.g / 4;
                    }
                    this.d.addView(this.f, -1, i2);
                    RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, i2);
                    layoutParams4.addRule(12);
                    addView(this.d, layoutParams4);
                }
            }
        }
        if (this.e != null) {
            if (this.e.getParent() == null) {
                RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(w.b(getContext(), 100.0f), w.b(getContext(), 30.0f));
                layoutParams5.addRule(10);
                layoutParams5.addRule(11);
                layoutParams5.rightMargin = w.b(getContext(), 10.0f);
                layoutParams5.topMargin = w.b(getContext(), 10.0f);
                addView(this.e, layoutParams5);
            } else {
                bringChildToFront(this.e);
            }
        }
        clearResState();
    }

    public void clearResState() {
        this.i = false;
        this.h = false;
    }

    public void updateCountdown(int i) {
        if (this.c != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("countdown", i);
                h.a().a((WebView) this.c, "updateCountdown", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void c() {
        if (this.c != null) {
            this.c.setObject(this.l);
            this.c.post(new Runnable() { // from class: com.mbridge.msdk.splash.view.MBSplashView.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        s.a(MBSplashView.a, "webviewshow");
                        String string = "";
                        try {
                            int[] iArr = new int[2];
                            MBSplashView.this.c.getLocationOnScreen(iArr);
                            s.d(MBSplashView.a, "coordinate:" + iArr[0] + "--" + iArr[1]);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("startX", w.a(com.mbridge.msdk.foundation.controller.a.f().j(), (float) iArr[0]));
                            jSONObject.put("startY", w.a(com.mbridge.msdk.foundation.controller.a.f().j(), (float) iArr[1]));
                            string = jSONObject.toString();
                        } catch (Throwable th) {
                            s.b(MBSplashView.a, th.getMessage(), th);
                        }
                        int[] iArr2 = new int[2];
                        MBSplashView.this.c.getLocationInWindow(iArr2);
                        MBSplashView.transInfoForMraid(MBSplashView.this.c, iArr2[0], iArr2[1], MBSplashView.this.c.getWidth(), MBSplashView.this.c.getHeight());
                        h.a().a((WebView) MBSplashView.this.c, "webviewshow", Base64.encodeToString(string.toString().getBytes(), 2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void transInfoForMraid(WebView webView, int i, int i2, int i3, int i4) {
        s.d(a, "transInfoForMraid");
        try {
            int i5 = com.mbridge.msdk.foundation.controller.a.f().j().getResources().getConfiguration().orientation;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("orientation", i5 == 2 ? "landscape" : i5 == 1 ? "portrait" : "undefined");
            jSONObject.put("locked", "true");
            float fW = o.w(com.mbridge.msdk.foundation.controller.a.f().j());
            float fX = o.x(com.mbridge.msdk.foundation.controller.a.f().j());
            HashMap mapY = o.y(com.mbridge.msdk.foundation.controller.a.f().j());
            int iIntValue = ((Integer) mapY.get("width")).intValue();
            int iIntValue2 = ((Integer) mapY.get("height")).intValue();
            HashMap map = new HashMap();
            map.put("placementType", "Interstitial");
            map.put("state", "default");
            map.put("viewable", "true");
            map.put("currentAppOrientation", jSONObject);
            float f = i;
            float f2 = i2;
            float f3 = i3;
            float f4 = i4;
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(webView, f, f2, f3, f4);
            com.mbridge.msdk.mbjscommon.mraid.a.a().b(webView, f, f2, f3, f4);
            com.mbridge.msdk.mbjscommon.mraid.a.a().b(webView, fW, fX);
            com.mbridge.msdk.mbjscommon.mraid.a.a().c(webView, iIntValue, iIntValue2);
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(webView, map);
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(webView);
        } catch (Throwable th) {
            s.b(a, "transInfoForMraid", th);
        }
    }

    public void destroy() {
        removeAllViews();
        if (this.j != null) {
            this.j.removeAllViews();
        }
        if (this.c == null || this.c.isDestoryed()) {
            return;
        }
        this.c.release();
        c.a(this.c, "onSystemDestory", "");
    }

    public ViewGroup getDevContainer() {
        return this.j;
    }

    public void setDevContainer(ViewGroup viewGroup) {
        this.j = viewGroup;
    }
}
