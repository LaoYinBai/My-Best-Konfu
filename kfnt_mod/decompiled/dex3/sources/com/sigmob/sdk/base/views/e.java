package com.sigmob.sdk.base.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.sigdsp.pb.AdPrivacy;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import com.sigmob.sdk.common.utils.k;
import com.sigmob.sdk.common.utils.q;
import com.tachikoma.core.utility.UriUtil;
import java.io.File;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e extends Dialog implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener {
    private b a;
    private Map<String, String> b;
    private Context c;
    private Window d;
    private int e;
    private int f;
    private int g;
    private int h;
    private CircleWebView i;
    private BaseAdUnit j;
    private boolean k;
    private ImageView l;
    private String m;
    private File n;

    static class a {
        private Map<String, String> a;

        public a(Map<String, String> map) {
            this.a = map;
        }

        @JavascriptInterface
        public String getPrivacyInfo() {
            try {
                if (this.a == null || this.a.size() <= 0) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry<String, String> entry : this.a.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
                return jSONObject.toString();
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    public interface b {
        void a();

        void a(String str, String str2);

        void b();
    }

    public e(Context context, BaseAdUnit baseAdUnit) {
        int i;
        super(context, com.sigmob.sdk.base.b.d());
        this.d = null;
        this.k = false;
        this.m = "";
        this.c = context.getApplicationContext();
        this.j = baseAdUnit;
        this.i = b();
        this.l = d();
        this.g = this.c.getResources().getDisplayMetrics().widthPixels;
        this.h = this.c.getResources().getDisplayMetrics().heightPixels;
        if (this.h > this.g) {
            this.e = (this.h * 1) / 2;
            i = this.g;
        } else {
            this.e = (this.h * 5) / 6;
            i = this.h;
        }
        this.f = i;
    }

    private ImageView d() {
        this.l = new ImageView(this.c);
        this.l.setImageBitmap(d.CLOSE.a());
        this.l.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.l.setImageAlpha(127);
        this.l.setClickable(true);
        this.l.setOnClickListener(new View.OnClickListener() { // from class: com.sigmob.sdk.base.views.e.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (e.this.a != null) {
                    e.this.a.a();
                }
            }
        });
        return this.l;
    }

    private void e() {
        this.d = getWindow();
        if (this.d != null) {
            this.d.setGravity(80);
            int iE = com.sigmob.sdk.base.b.e();
            if (iE != 0) {
                this.d.setWindowAnimations(iE);
            }
            this.d.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = this.d.getAttributes();
            attributes.width = this.f;
            attributes.height = this.e;
            this.d.setAttributes(attributes);
        }
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public boolean a() {
        return this.k;
    }

    public CircleWebView b() {
        CircleWebView circleWebView;
        String str;
        if (this.j != null) {
            MaterialMeta material = this.j.getMaterial();
            if (material == null || material.ad_privacy == null) {
                SigmobLog.i("ad_privacy is null");
            } else {
                AdPrivacy adPrivacy = material.ad_privacy;
                if (!TextUtils.isEmpty(adPrivacy.privacy_info_url)) {
                    this.m = adPrivacy.privacy_info_url;
                }
                if (adPrivacy.privacy_template_info != null && adPrivacy.privacy_template_info.size() > 0) {
                    this.b = adPrivacy.privacy_template_info;
                }
                if (!TextUtils.isEmpty(adPrivacy.privacy_template_url)) {
                    String strA = k.a(adPrivacy.privacy_template_url);
                    this.n = new File(com.sigmob.sdk.common.utils.d.g(com.sigmob.sdk.common.utils.d.b), strA + ".html");
                }
            }
        }
        if (TextUtils.isEmpty(this.m) && (this.n == null || !this.n.exists() || this.b == null)) {
            this.k = false;
            return null;
        }
        this.k = true;
        this.i = new CircleWebView(this.c);
        this.i.a(true);
        this.i.setWebViewClient(new WebViewClient() { // from class: com.sigmob.sdk.base.views.e.2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str2) {
                super.onPageFinished(webView, str2);
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str2, Bitmap bitmap) {
                super.onPageStarted(webView, str2, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                SigmobLog.i("onReceivedError:" + webResourceError.toString());
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                SigmobLog.i("onReceivedSslError:" + sslError.toString());
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str2) {
                try {
                    SigmobLog.i("shouldOverrideUrlLoading:" + str2);
                    Uri uri = Uri.parse(str2);
                    if ("sigmobAd".equalsIgnoreCase(uri.getScheme())) {
                        String host = uri.getHost();
                        if (!TextUtils.isEmpty(host)) {
                            if ("closeFourElements".equals(host)) {
                                if (e.this.a != null) {
                                    e.this.a.a();
                                }
                            } else if ("buttonClick".equals(host)) {
                                Map<String, String> mapA = com.sigmob.sdk.common.a.a(uri);
                                String str3 = mapA.get("url");
                                String str4 = mapA.get("x");
                                if (TextUtils.isEmpty(str4)) {
                                    str4 = Constants.FAIL;
                                }
                                String str5 = mapA.get("y");
                                if (TextUtils.isEmpty(str5)) {
                                    str5 = Constants.FAIL;
                                }
                                String str6 = String.format("%s,%s,%s,%s", str4, str5, str4, str5);
                                SigMacroCommon macroCommon = e.this.j.getMacroCommon();
                                if (macroCommon instanceof SigMacroCommon) {
                                    macroCommon.addMarcoKey(SigMacroCommon._DOWNX_, str4);
                                    macroCommon.addMarcoKey(SigMacroCommon._DOWNY_, str5);
                                    macroCommon.addMarcoKey(SigMacroCommon._UPX_, str4);
                                    macroCommon.addMarcoKey(SigMacroCommon._UPY_, str5);
                                }
                                e.this.j.getClickCommon().down = new q(Integer.parseInt(str4), Integer.parseInt(str5));
                                e.this.j.getClickCommon().up = new q(Integer.parseInt(str4), Integer.parseInt(str5));
                                if (e.this.a != null) {
                                    e.this.a.a(str3, str6);
                                }
                            }
                            return true;
                        }
                    }
                } catch (Throwable th) {
                    SigmobLog.e("FourElementsDialog:" + th.getMessage());
                }
                if (!TextUtils.isEmpty(str2) && str2.startsWith(Constants.HTTP)) {
                    webView.loadUrl(str2);
                }
                return true;
            }
        });
        if (!TextUtils.isEmpty(this.m)) {
            circleWebView = this.i;
            str = this.m;
        } else {
            if (this.n == null || !this.n.exists()) {
                this.k = false;
                return this.i;
            }
            this.i.addJavascriptInterface(new a(this.b), "sigPrivacy");
            circleWebView = this.i;
            str = UriUtil.FILE_PREFIX + this.n.getAbsolutePath();
        }
        circleWebView.loadUrl(str);
        return this.i;
    }

    public void c() {
        if (this.i != null) {
            this.i.destroy();
            this.i = null;
        }
        if (this.a != null) {
            this.a = null;
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        if (TextUtils.isEmpty(this.m)) {
            relativeLayout.setBackgroundColor(0);
        } else {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(-1);
            float fB = com.sigmob.sdk.common.utils.c.b(20.0f, this.c);
            gradientDrawable.setCornerRadii(new float[]{fB, fB, fB, fB, 0.0f, 0.0f, 0.0f, 0.0f});
            int iB = com.sigmob.sdk.common.utils.c.b(10.0f, this.c);
            relativeLayout.setPadding(iB, iB, iB, iB);
            relativeLayout.setBackground(gradientDrawable);
        }
        setContentView(relativeLayout);
        setOnShowListener(this);
        setOnDismissListener(this);
        SigmobLog.i("FourElementsDialog onCreate:" + this.f + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + this.e);
        if (this.i != null) {
            relativeLayout.addView(this.i, new RelativeLayout.LayoutParams(-1, -1));
        }
        if (this.l != null && !TextUtils.isEmpty(this.m)) {
            int iB2 = com.sigmob.sdk.common.utils.c.b(18.0f, this.c);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iB2, iB2);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            int i = iB2 / 2;
            layoutParams.setMargins(0, i, i, 0);
            relativeLayout.addView(this.l, layoutParams);
        }
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        e();
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        SigmobLog.i("FourElementsDialog  onDismiss");
        if (this.a != null) {
            this.a.a();
        }
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        SigmobLog.i("FourElementsDialog  onShow");
        if (this.a != null) {
            this.a.b();
        }
    }
}
