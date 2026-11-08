package com.baidu.mobads.sdk.internal;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobads.sdk.api.AdSize;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cr {
    private static final int f = 5;

    /* renamed from: c, reason: collision with root package name */
    private RelativeLayout f3218c;
    private TextView d;
    private CountDownTimer e;
    private cu g;
    private Activity j;
    private Boolean k;

    /* renamed from: a, reason: collision with root package name */
    public final String f3216a = "html5_intersitial";
    private boolean h = false;
    private boolean i = false;

    /* renamed from: b, reason: collision with root package name */
    protected final bb f3217b = bb.a();

    private RelativeLayout.LayoutParams h() {
        return null;
    }

    public void a() {
    }

    public void a(int i, int i2) {
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return true;
    }

    public cr(Context context, RelativeLayout relativeLayout, Boolean bool, AdSize adSize, String str) {
    }

    public void a(Activity activity, RelativeLayout relativeLayout) {
        try {
            this.f3217b.a("showInterstitialAdInit");
            if (this.h && !this.i) {
                this.i = true;
                this.h = false;
                this.j = activity;
                a();
                c();
                return;
            }
            if (this.i) {
                this.f3217b.b("interstitial ad is showing now");
            } else {
                if (this.h) {
                    return;
                }
                this.f3217b.b("interstitial ad is not ready");
            }
        } catch (Exception e) {
            this.f3217b.a(e);
        }
    }

    private boolean e() {
        return b();
    }

    protected boolean b() {
        return AdSize.InterstitialForVideoBeforePlay.getValue() == 0;
    }

    protected void c() {
        if (this.j == null) {
            return;
        }
        this.j.runOnUiThread(new cs(this));
    }

    public boolean d() {
        return this.h;
    }

    private View f() {
        this.e = new ct(this, 6000L, 1000L).start();
        return this.f3218c;
    }

    private void g() {
        if (this.f3218c != null && this.f3218c.getParent() != null) {
            ((ViewGroup) this.f3218c.getParent()).removeView(this.f3218c);
        }
        if (this.e != null) {
            this.f3217b.a("cancel countDownTimer before it finished");
            try {
                this.e.cancel();
            } catch (Exception e) {
                this.f3217b.a(e);
            }
        }
    }
}
