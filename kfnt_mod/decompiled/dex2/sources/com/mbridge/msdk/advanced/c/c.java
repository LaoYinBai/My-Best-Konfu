package com.mbridge.msdk.advanced.c;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import com.mbridge.msdk.advanced.view.MBNativeAdvancedView;
import com.mbridge.msdk.advanced.view.MBNativeAdvancedWebview;
import com.mbridge.msdk.advanced.view.MBOutNativeAdvancedViewGroup;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.ac;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.windvane.h;
import com.mbridge.msdk.out.MBridgeIds;
import com.mbridge.msdk.out.NativeAdvancedAdListener;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeAdvancedProvider.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
    private static String b = "NativeAdvancedProvider";
    private MBOutNativeAdvancedViewGroup B;
    private boolean C;
    private boolean D;
    private boolean E;
    private String c;
    private String d;
    private MBridgeIds e;
    private com.mbridge.msdk.advanced.b.a f;
    private com.mbridge.msdk.advanced.b.b g;
    private b h;
    private NativeAdvancedAdListener i;
    private d j;
    private MBNativeAdvancedView k;
    private MBNativeAdvancedWebview l;
    private com.mbridge.msdk.advanced.view.a m;
    private com.mbridge.msdk.b.d n;
    private boolean o;
    private com.mbridge.msdk.b.c p;
    private JSONObject z;
    private int q = -1;
    private boolean r = false;
    private int s = 0;
    private boolean t = false;
    private int u = 0;
    private boolean v = false;
    private int w = 0;
    private int x = 0;
    private Object y = new Object();
    private boolean A = false;
    private boolean F = true;
    public boolean a = false;
    private ViewTreeObserver.OnScrollChangedListener G = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.mbridge.msdk.advanced.c.c.1
        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public final void onScrollChanged() {
            if (c.this.F) {
                c.this.F = false;
                if (c.this.B != null) {
                    c.this.B.postDelayed(new Runnable() { // from class: com.mbridge.msdk.advanced.c.c.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            c.this.F = true;
                        }
                    }, 1000L);
                }
                c.this.h();
            }
        }
    };

    public final boolean a() {
        return this.o;
    }

    public final void a(boolean z) {
        this.o = z;
    }

    public final MBOutNativeAdvancedViewGroup b() {
        return this.B;
    }

    public c(String str, String str2, Activity activity) {
        ViewGroup.LayoutParams layoutParams;
        this.d = str;
        this.c = str2;
        this.e = new MBridgeIds(str, str2);
        if (this.g == null) {
            this.g = new com.mbridge.msdk.advanced.b.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.d, this.c);
            this.g.a(this);
        }
        if (this.l == null) {
            this.l = new MBNativeAdvancedWebview(com.mbridge.msdk.foundation.controller.a.f().j());
            if (this.m == null) {
                this.m = new com.mbridge.msdk.advanced.view.a(this.c, this.g.b(), this);
            }
            this.l.setWebViewClient(this.m);
        }
        if (this.k == null) {
            this.k = new MBNativeAdvancedView(activity != null ? activity : com.mbridge.msdk.foundation.controller.a.f().j());
            this.k.setAdvancedNativeWebview(this.l);
            if (this.l != null && this.l.getParent() == null) {
                this.k.addView(this.l, new ViewGroup.LayoutParams(-1, -1));
            }
        }
        if (this.B == null) {
            this.B = new MBOutNativeAdvancedViewGroup(com.mbridge.msdk.foundation.controller.a.f().j());
            if (this.w == 0 || this.x == 0) {
                layoutParams = new ViewGroup.LayoutParams(-1, -1);
            } else {
                layoutParams = new ViewGroup.LayoutParams(this.w, this.x);
            }
            this.B.setLayoutParams(layoutParams);
            this.B.setProvider(this);
            this.B.addView(this.k);
            this.B.getViewTreeObserver().addOnScrollChangedListener(this.G);
        }
        if (this.p == null) {
            this.p = new com.mbridge.msdk.b.c();
        }
        this.p.a(com.mbridge.msdk.foundation.controller.a.f().j(), com.mbridge.msdk.foundation.controller.a.f().k(), com.mbridge.msdk.foundation.controller.a.f().l(), this.c);
        com.mbridge.msdk.advanced.common.b.a();
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        this.A = true;
        b(jSONObject);
    }

    private void b(JSONObject jSONObject) throws JSONException {
        if (this.A) {
            this.z = jSONObject;
            if (this.l == null || this.l.isDestoryed()) {
                return;
            }
            com.mbridge.msdk.advanced.js.b.a(this.l, "setStyleList", "", jSONObject);
        }
    }

    private void f(int i) {
        if (this.l == null || this.l.isDestoryed()) {
            return;
        }
        try {
            if (this.l != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("netstat", i);
                h.a().a((WebView) this.l, "onNetstatChanged", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
            }
        } catch (Throwable th) {
            s.a(b, th.getMessage());
        }
    }

    public final int c() {
        return this.q;
    }

    public final void a(NativeAdvancedAdListener nativeAdvancedAdListener) {
        this.i = nativeAdvancedAdListener;
    }

    public final void a(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            if (this.i != null) {
                this.i.onLoadFailed(this.e, "bid  token is null or empty");
                return;
            }
            return;
        }
        b(str);
    }

    public final void b(String str) throws Throwable {
        this.h = new b(this, this.e);
        this.h.a(this.i);
        this.h.a(str);
        a(str, 2);
    }

    public final boolean c(String str) {
        return (this.B == null || com.mbridge.msdk.advanced.b.c.a(this.k, this.d, this.c, str, this.q, false, true) == null) ? false : true;
    }

    public final void a(int i) throws JSONException {
        this.r = true;
        g(i);
    }

    private void g(int i) throws JSONException {
        if (this.r) {
            this.q = i;
            if (this.l == null || this.l.isDestoryed()) {
                return;
            }
            if (this.q == 1) {
                this.g.a(true);
                com.mbridge.msdk.advanced.js.b.a(this.l, "showCloseButton", "", null);
            } else if (this.q == 0) {
                this.g.a(false);
                com.mbridge.msdk.advanced.js.b.a(this.l, "hideCloseButton", "", null);
            }
        }
    }

    public final void b(int i) throws JSONException {
        this.t = true;
        h(i);
    }

    private void h(int i) throws JSONException {
        if (this.t) {
            this.s = i;
            if (this.l == null || this.l.isDestoryed()) {
                return;
            }
            com.mbridge.msdk.advanced.js.b.a(this.l, "setVolume", CampaignEx.JSON_NATIVE_VIDEO_MUTE, Integer.valueOf(i));
        }
    }

    public final void c(int i) throws JSONException {
        this.v = true;
        i(i);
    }

    private void i(int i) throws JSONException {
        if (this.v) {
            this.u = i;
            if (this.l == null || this.l.isDestoryed()) {
                return;
            }
            com.mbridge.msdk.advanced.js.b.a(this.l, "setVideoPlayMode", "autoPlay", Integer.valueOf(i));
        }
    }

    private void f() {
        CampaignEx campaignExA = com.mbridge.msdk.advanced.b.c.a(this.k, this.d, this.c, "", this.q, true, true);
        if (campaignExA != null) {
            if (this.n == null) {
                this.n = com.mbridge.msdk.b.b.a().e(com.mbridge.msdk.foundation.controller.a.f().k(), this.c);
            }
            this.j = new d(this, this.i, this.n.e(), campaignExA);
            s.a(b, "show start");
            if (this.w == 0 || this.x == 0) {
                if (this.j != null) {
                    this.j.a(this.e, "width or height is 0  or width or height is too small");
                    return;
                }
                return;
            }
            a(campaignExA, false);
        }
    }

    final void a(CampaignEx campaignEx, boolean z) {
        g();
        if (this.B == null || this.B.getParent() == null) {
            return;
        }
        if (campaignEx != null && z) {
            if (this.n == null) {
                this.n = com.mbridge.msdk.b.b.a().e(com.mbridge.msdk.foundation.controller.a.f().k(), this.c);
            }
            this.j = new d(this, this.i, this.n.e(), campaignEx);
        }
        if (this.g == null) {
            this.g = new com.mbridge.msdk.advanced.b.b(com.mbridge.msdk.foundation.controller.a.f().j(), this.d, this.c);
            this.g.a(this);
        }
        a(campaignEx);
    }

    public final String d() {
        return this.a ? this.g != null ? this.g.a() : "" : this.f != null ? this.f.a() : "";
    }

    private void a(CampaignEx campaignEx) {
        if (com.mbridge.msdk.advanced.b.c.a(this.k, campaignEx, this.d, this.c)) {
            this.g.a(this.j);
            s.d(b, "start show process");
            this.g.a(campaignEx, this.k, true);
        }
    }

    private void g() throws JSONException {
        g(this.q);
        h(this.s);
        i(this.u);
        b(this.z);
        f(o.C(com.mbridge.msdk.foundation.controller.a.f().j()));
    }

    private void a(String str, int i) throws Throwable {
        this.F = true;
        synchronized (this.y) {
            if (this.o) {
                if (this.h != null) {
                    this.h.a("current unit is loading", i);
                    this.o = true;
                }
                return;
            }
            this.o = true;
            if (this.w == 0 || this.x == 0) {
                if (this.h != null) {
                    this.h.a("width or height is 0  or width or height is too small", i);
                    return;
                }
                return;
            }
            if (this.k == null) {
                if (this.h != null) {
                    this.h.a("view is not ready", i);
                    return;
                }
                return;
            }
            this.k.clearResStateAndRemoveClose();
            this.n = com.mbridge.msdk.b.b.a().g(com.mbridge.msdk.foundation.controller.a.f().k(), this.c);
            if (this.n == null) {
                this.n = com.mbridge.msdk.b.d.c(this.c);
            }
            if (this.f == null) {
                this.f = new com.mbridge.msdk.advanced.b.a(this.d, this.c, 0L);
            }
            if (this.h != null) {
                this.h.a(str);
                this.f.a(this.h);
            }
            this.k.resetLoadState();
            this.f.a(this.k);
            this.f.a(this.n);
            this.f.a(this.w, this.x);
            this.f.a(this.q);
            this.f.a(str, i);
        }
    }

    public final String d(String str) {
        return this.f != null ? this.f.a(str) : "";
    }

    public final void d(int i) {
        switch (i) {
            case 1:
                this.C = true;
                break;
            case 2:
                this.D = true;
                break;
            case 3:
                this.E = true;
                break;
        }
        h();
    }

    public final void e(int i) {
        switch (i) {
            case 1:
                this.C = false;
                break;
            case 2:
                this.D = false;
                break;
            case 3:
                this.E = false;
                break;
        }
        if (this.g != null) {
            this.g.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.C && this.D && this.E && !ac.a(this.k.getAdvancedNativeWebview()) && this.B.getAlpha() >= 0.5f && this.B.getVisibility() == 0) {
            if (this.g != null) {
                this.g.d();
            }
            f();
        }
    }

    public final void e() throws Throwable {
        if (this.i != null) {
            this.i = null;
        }
        if (this.h != null) {
            this.h = null;
        }
        if (this.j != null) {
            this.j = null;
        }
        if (this.f != null) {
            this.f.a((MBNativeAdvancedView) null);
            this.f.b();
        }
        if (this.g != null) {
            this.g.c();
        }
        if (this.k != null) {
            this.k.destroy();
        }
        com.mbridge.msdk.advanced.common.b.a();
        if (this.m != null) {
            this.m.a();
        }
        if (this.B != null) {
            this.B.getViewTreeObserver().removeOnScrollChangedListener(this.G);
            this.B.removeAllViews();
            this.B = null;
        }
    }

    public final void a(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        this.x = i;
        this.w = i2;
        this.B.setLayoutParams(new ViewGroup.LayoutParams(i2, i));
    }
}
