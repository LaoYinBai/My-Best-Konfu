package com.sigmob.sdk.base.common;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.IntentActions;
import com.sigmob.sdk.base.views.BaseWebView;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import com.ss.union.game.sdk.core.base.event.INetUploadStrategy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class q extends g {
    BaseWebView g;
    private int h;
    private BaseAdUnit i;
    private RelativeLayout j;
    private TextView k;
    private String l;

    public q(Activity activity, BaseAdUnit baseAdUnit, Bundle bundle, Bundle bundle2, String str, h hVar) {
        int i;
        super(activity, str, hVar);
        this.i = baseAdUnit;
        if (this.i == null) {
            a(IntentActions.ACTION_INTERSTITIAL_FAIL);
            h().a();
            return;
        }
        switch (this.i.getAd().display_orientation.intValue()) {
            case 1:
                i = 7;
                break;
            case 2:
                i = 6;
                break;
            default:
                i = bundle.getInt(Constants.REQUESTED_ORIENTATION, 3);
                break;
        }
        this.h = i;
        if (this.i.getAd_type() != 5) {
            h().a(this.h);
        }
        k().requestWindowFeature(8);
        k().getWindow().addFlags(com.ss.union.game.sdk.feedback.picture.select.d.d);
    }

    private void a() {
        if (this.g == null) {
            try {
                this.g = new BaseWebView(k());
                this.g.setScrollBarStyle(0);
                this.g.setWebViewClient(new WebViewClient() { // from class: com.sigmob.sdk.base.common.q.1
                    @Override // android.webkit.WebViewClient
                    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                        SigmobLog.e(webResourceError.toString());
                        super.onReceivedError(webView, webResourceRequest, webResourceError);
                    }

                    @Override // android.webkit.WebViewClient
                    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                        String message;
                        Context context;
                        String scheme;
                        try {
                            context = webView.getContext();
                            Uri uri = Uri.parse(str);
                            scheme = uri.getScheme();
                            if ("sigmobAd".equalsIgnoreCase(scheme)) {
                                String host = uri.getHost();
                                if (!TextUtils.isEmpty(host) && ("track".equals(host) || "active".equals(host))) {
                                    String queryParameter = uri.getQueryParameter("data");
                                    String queryParameter2 = uri.getQueryParameter(INetUploadStrategy.KEY_EVENT);
                                    if (!TextUtils.isEmpty(queryParameter2) && !TextUtils.isEmpty(queryParameter)) {
                                        final String str2 = new String(Base64.decode(queryParameter, 0), "utf-8");
                                        t.a(host, queryParameter2, q.this.i, new t.a() { // from class: com.sigmob.sdk.base.common.q.1.1
                                            @Override // com.sigmob.sdk.base.common.t.a
                                            public void a(Object obj) {
                                                if (obj instanceof PointEntitySigmob) {
                                                    ((PointEntitySigmob) obj).setUrl(str2);
                                                }
                                            }
                                        });
                                    }
                                    return true;
                                }
                            }
                        } catch (com.sigmob.sdk.common.c.b e) {
                            message = e.getMessage();
                            SigmobLog.e(message);
                            return true;
                        } catch (Throwable th) {
                            message = th.getMessage();
                            SigmobLog.e(message);
                            return true;
                        }
                        if (!TextUtils.isEmpty(scheme) && !scheme.equals(Constants.HTTP) && !scheme.equals(Constants.HTTPS)) {
                            com.sigmob.sdk.common.utils.j.a(context, Uri.parse(str));
                            t.a(PointCategory.LAND_PAGE, "click", q.this.i, "1", str, q.this.l);
                            return true;
                        }
                        SigmobLog.i("load Url: " + str);
                        webView.loadUrl(str);
                        return true;
                    }
                });
            } catch (Throwable th) {
                SigmobLog.e(th.getMessage());
                this.f.a();
            }
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        LinearLayout linearLayout = new LinearLayout(k());
        linearLayout.addView(this.g, 0, layoutParams);
        this.f.onSetContentView(linearLayout);
        this.g.setDownloadListener(new DownloadListener() { // from class: com.sigmob.sdk.base.common.q.2
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                l.a(q.this.b, str, q.this.i);
                t.a(PointCategory.LAND_PAGE, "click", q.this.i, Constants.FAIL, str, q.this.l);
                SigmobLog.d("onDownloadStart() called with: url = [" + str + "], userAgent = [" + str2 + "], contentDisposition = [" + str3 + "], mimetype = [" + str4 + "], contentLength = [" + j + "]");
            }
        });
        this.g.setOnTouchListener(new View.OnTouchListener() { // from class: com.sigmob.sdk.base.common.q.3
            MotionEvent a = null;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                q qVar;
                String str;
                Object[] objArr;
                SigmobLog.d(motionEvent.toString());
                if (motionEvent.getAction() == 0) {
                    this.a = MotionEvent.obtain(motionEvent);
                } else if (motionEvent.getAction() == 1) {
                    if (this.a == null) {
                        qVar = q.this;
                        str = "%d,%d,%d,%d";
                        objArr = new Object[]{Integer.valueOf((int) motionEvent.getRawX()), Integer.valueOf((int) motionEvent.getRawY()), Integer.valueOf((int) motionEvent.getRawX()), Integer.valueOf((int) motionEvent.getRawY())};
                    } else {
                        qVar = q.this;
                        str = "%d,%d,%d,%d";
                        objArr = new Object[]{Integer.valueOf((int) this.a.getRawX()), Integer.valueOf((int) this.a.getRawY()), Integer.valueOf((int) motionEvent.getRawX()), Integer.valueOf((int) motionEvent.getRawY())};
                    }
                    qVar.l = String.format(str, objArr);
                }
                return false;
            }
        });
    }

    private void l() {
        if (this.j == null) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(9);
            layoutParams2.addRule(15);
            this.k = new TextView(this.b);
            this.k.setPadding(com.sigmob.sdk.common.utils.c.b(5.0f, this.b), 0, 0, 0);
            this.k.setText(com.sigmob.sdk.base.b.h());
            this.k.setTextColor(-1);
            this.k.setLayoutParams(layoutParams2);
            this.k.setOnClickListener(new View.OnClickListener() { // from class: com.sigmob.sdk.base.common.q.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    q.this.h().a();
                }
            });
            this.k.setId(com.sigmob.sdk.common.a.r());
            this.j = new RelativeLayout(i());
            this.j.setLayoutParams(layoutParams);
            this.j.setBackgroundColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
            this.j.addView(this.k);
        }
    }

    private void m() {
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(-1, -1, 17);
        ActionBar actionBar = k().getActionBar();
        if (actionBar != null) {
            l();
            actionBar.setCustomView(this.j, layoutParams);
            actionBar.setDisplayOptions(16);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Configuration configuration) {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Bundle bundle) {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void b() {
        int iC = com.sigmob.sdk.base.b.c();
        if (iC > 0) {
            k().getTheme().applyStyle(iC, true);
        }
        a();
        m();
        this.g.loadUrl(this.i.getMacroCommon().macroProcess(this.i.getMaterial().landing_page));
        a(IntentActions.ACTION_LANDPAGE_SHOW);
    }

    @Override // com.sigmob.sdk.base.common.g
    public void c() {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void d() {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void e() {
        a(IntentActions.ACTION_LANDPAGE_DISMISS);
        if (this.k != null) {
            this.k.setOnClickListener(null);
            this.k = null;
        }
        if (this.g != null) {
            this.g.destroy();
            this.g = null;
        }
    }

    @Override // com.sigmob.sdk.base.common.g
    public void f() {
    }

    @Override // com.sigmob.sdk.base.common.g
    public boolean g() {
        if (!this.g.canGoBack()) {
            return true;
        }
        this.g.goBack();
        return false;
    }
}
