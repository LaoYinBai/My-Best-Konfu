package com.ss.sys.ck;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import c.a.a.a.a.u;
import com.ss.sys.ck.b;
import com.ss.union.game.sdk.core.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e extends Dialog {
    protected SCWebView a;
    private Activity b;
    private int c;
    private int d;
    private String e;
    private b.C0046b f;
    private String g;

    public e(Activity activity, int i, int i2, View view, int i3, String str, String str2) {
        super(activity, R.style.DialogTheme);
        setContentView(view);
        this.b = activity;
        this.e = str;
        this.g = str2;
        setCancelable(true);
    }

    public void a() {
        DisplayMetrics displayMetrics = this.b.getResources().getDisplayMetrics();
        int i = displayMetrics.heightPixels;
        this.c = i;
        int i2 = displayMetrics.widthPixels;
        this.d = i2;
        float f = displayMetrics.density;
        if (i < i2) {
            this.d = (i * 3) / 4;
        }
        int i3 = (this.d * 4) / 5;
        this.d = i3;
        float f2 = i3;
        b.C0046b c0046b = this.f;
        float f3 = c0046b.b;
        this.c = (int) (f2 * f3);
        int i4 = (int) ((f2 / f) + 0.5f);
        int i5 = c0046b.c;
        if (i4 < i5) {
            float f4 = i5;
            this.d = (int) (f * f4);
            this.c = (int) (displayMetrics.density * f4 * f3);
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = this.d;
        attributes.height = this.c;
        float f5 = b.c;
        if (f5 >= 0.0f) {
            attributes.dimAmount = f5;
        }
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
    }

    public void a(int i) {
        this.f = b.d.containsKey(Integer.valueOf(i)) ? b.d.get(Integer.valueOf(i)) : new b.C0046b(1005, 1.1533333f, 200);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        SCWebView sCWebView = this.a;
        if (sCWebView != null) {
            ViewParent parent = sCWebView.getParent();
            if (parent != null) {
                new u.a(this.a.getActionList().toString()).start();
                this.a.loadUrl("javascript:prompt('" + b.a + "',points.getLogToString())");
                ((ViewGroup) parent).removeView(this.a);
            }
            this.a.removeAllViews();
        }
        Activity activity = this.b;
        if (activity != null && !activity.isFinishing()) {
            super.dismiss();
        }
        this.b = null;
    }

    @Override // android.app.Dialog
    public void hide() {
        super.hide();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        SCWebView sCWebView = (SCWebView) findViewById(R.id.sec_webview);
        this.a = sCWebView;
        sCWebView.b();
        this.a.b(this.e, this.g);
        this.a.loadUrl(this.e);
        this.a.buildLayer();
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
    }
}
