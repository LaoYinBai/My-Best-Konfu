package com.sigmob.sdk.base.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CircleWebView extends WebView {
    private float a;
    private int b;
    private int c;
    private int d;
    private int e;
    private Paint f;
    private Paint g;

    public CircleWebView(Context context) {
        this(context, null);
    }

    public CircleWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 40.0f;
        a(getSettings());
        a(context);
    }

    private void a(Context context) {
        this.f = new Paint();
        this.f.setColor(-1);
        this.f.setAntiAlias(true);
        this.f.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.g = new Paint();
        this.g.setXfermode(null);
    }

    private void a(Canvas canvas) {
        Path path = new Path();
        path.moveTo(this.d, this.a);
        path.lineTo(this.d, this.e);
        path.lineTo(this.a, this.e);
        path.arcTo(new RectF(this.d, this.e, this.d + (this.a * 2.0f), this.e + (this.a * 2.0f)), -90.0f, -90.0f);
        path.close();
        canvas.drawPath(path, this.f);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void a(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setCacheMode(2);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        if (Build.VERSION.SDK_INT >= 17) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
        webSettings.setAllowFileAccess(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        if (Build.VERSION.SDK_INT < 16) {
            webSettings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            webSettings.setSafeBrowsingEnabled(false);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(0);
        }
    }

    private void b(Canvas canvas) {
        Path path = new Path();
        path.moveTo(this.d + this.b, this.e + this.a);
        path.lineTo(this.d + this.b, this.e);
        path.lineTo((this.d + this.b) - this.a, this.e);
        path.arcTo(new RectF((this.d + this.b) - (this.a * 2.0f), this.e, this.d + this.b, this.e + (this.a * 2.0f)), -90.0f, 90.0f);
        path.close();
        canvas.drawPath(path, this.f);
    }

    public void a(boolean z) {
        WebSettings settings;
        WebSettings.PluginState pluginState;
        if (Build.VERSION.SDK_INT >= 18) {
            return;
        }
        if (z) {
            settings = getSettings();
            pluginState = WebSettings.PluginState.ON;
        } else {
            settings = getSettings();
            pluginState = WebSettings.PluginState.OFF;
        }
        settings.setPluginState(pluginState);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.d = getScrollX();
        this.e = getScrollY();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.d + this.b, this.e + this.c, Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmapCreateBitmap);
        super.draw(canvas2);
        a(canvas2);
        b(canvas2);
        canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, this.g);
        bitmapCreateBitmap.recycle();
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.b = getMeasuredWidth();
        this.c = getMeasuredHeight();
    }

    public void setRadius(float f) {
        this.a = f;
    }
}
