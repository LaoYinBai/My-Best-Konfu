package com.ss.sys.ck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import c.a.a.a.a.bg;
import com.ss.union.game.sdk.core.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends AlertDialog {
    private Context a;
    private ScrollView b;
    private TextView c;
    private Button d;
    private Button e;
    private String f;
    private String g;
    private String h;
    private d i;

    /* renamed from: com.ss.sys.ck.a$a, reason: collision with other inner class name */
    class RunnableC0045a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ DisplayMetrics b;

        RunnableC0045a(int i, DisplayMetrics displayMetrics) {
            this.a = i;
            this.b = displayMetrics;
        }

        @Override // java.lang.Runnable
        public void run() {
            ScrollView scrollView;
            LinearLayout.LayoutParams layoutParams;
            bg.d("mMsgScroll.getMeasuredHeight()=" + a.this.b.getMeasuredHeight());
            if (a.this.b.getMeasuredHeight() > this.a / 2) {
                scrollView = a.this.b;
                layoutParams = new LinearLayout.LayoutParams(this.b.widthPixels - (a.this.a.getResources().getDimensionPixelOffset(R.dimen.alert_dialog_confirm_margin) * 2), this.a / 2);
            } else {
                scrollView = a.this.b;
                layoutParams = new LinearLayout.LayoutParams(this.b.widthPixels - (a.this.a.getResources().getDimensionPixelOffset(R.dimen.alert_dialog_confirm_margin) * 2), -2);
            }
            scrollView.setLayoutParams(layoutParams);
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.dismiss();
            if (a.this.i != null) {
                a.this.i.a();
            }
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.dismiss();
            if (a.this.i != null) {
                a.this.i.b();
            }
        }
    }

    public interface d {
        void a();

        void b();
    }

    public a(Context context, String str, String str2, String str3) {
        super(context, R.style.style_alert_confirm_dialog);
        this.f = "";
        this.g = "";
        this.h = "";
        setCanceledOnTouchOutside(false);
        this.a = context;
        this.f = str;
        this.g = str2;
        this.h = str3;
    }

    private void a() {
        if (!TextUtils.isEmpty(this.f)) {
            this.c.setText(this.f);
        }
        if (!TextUtils.isEmpty(this.g)) {
            this.d.setText(this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            this.e.setText(this.h);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        this.c.setLayoutParams(layoutParams);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.a).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels <= 480) {
            this.c.setTextSize(15.0f);
        }
        this.b.post(new RunnableC0045a(displayMetrics.heightPixels, displayMetrics));
    }

    private void b() {
        this.d.setOnClickListener(new b());
        this.e.setOnClickListener(new c());
    }

    private void c() {
        this.b = (ScrollView) findViewById(R.id.alertdialogconfirm_message_scroll);
        this.c = (TextView) findViewById(R.id.alertdialogconfirm_message);
        this.d = (Button) findViewById(R.id.alertdialogconfirm_retry);
        this.e = (Button) findViewById(R.id.alertdialogconfirm_cancel);
    }

    public void a(d dVar) {
        this.i = dVar;
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.alert_dialog_retry_cancel);
        c();
        a();
        b();
    }
}
