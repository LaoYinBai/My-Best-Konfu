package com.mbridge.msdk.mbbanner.common.bridge;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbjscommon.mraid.b;
import com.mbridge.msdk.mbjscommon.windvane.WindVaneWebView;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class BannerExpandDialog extends Dialog {
    private final String a;
    private String b;
    private boolean c;
    private FrameLayout d;
    private WindVaneWebView e;
    private TextView f;
    private String g;
    private List<CampaignEx> h;
    private com.mbridge.msdk.mbbanner.common.b.a i;
    private b j;

    public BannerExpandDialog(Context context, Bundle bundle, com.mbridge.msdk.mbbanner.common.b.a aVar) {
        super(context);
        this.a = "BannerExpandDialog";
        this.j = new b() { // from class: com.mbridge.msdk.mbbanner.common.bridge.BannerExpandDialog.4
            @Override // com.mbridge.msdk.mbjscommon.mraid.b
            public final void expand(String str, boolean z) {
            }

            @Override // com.mbridge.msdk.mbjscommon.mraid.b
            public final void open(String str) {
                try {
                    s.d("BannerExpandDialog", str);
                    if (BannerExpandDialog.this.h.size() > 1) {
                        com.mbridge.msdk.foundation.controller.a.f().j().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        str = null;
                    }
                    if (BannerExpandDialog.this.i != null) {
                        BannerExpandDialog.this.i.a(true, str);
                    }
                } catch (Throwable th) {
                    s.b("BannerExpandDialog", "open", th);
                }
            }

            @Override // com.mbridge.msdk.mbjscommon.mraid.b
            public final void close() {
                BannerExpandDialog.this.dismiss();
            }

            @Override // com.mbridge.msdk.mbjscommon.mraid.b
            public final void unload() {
                close();
            }

            @Override // com.mbridge.msdk.mbjscommon.mraid.b
            public final void useCustomClose(boolean z) {
                try {
                    BannerExpandDialog.this.f.setVisibility(z ? 4 : 0);
                } catch (Throwable th) {
                    s.b("BannerExpandDialog", "useCustomClose", th);
                }
            }
        };
        if (bundle != null) {
            this.b = bundle.getString("url");
            this.c = bundle.getBoolean("shouldUseCustomClose");
        }
        this.i = aVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        this.d = new FrameLayout(getContext());
        this.d.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.e = new WindVaneWebView(getContext().getApplicationContext());
        this.e.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.d.addView(this.e);
        this.f = new TextView(getContext());
        this.f.setBackgroundColor(0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(96, 96);
        layoutParams.gravity = 8388661;
        layoutParams.setMargins(30, 30, 30, 30);
        this.f.setLayoutParams(layoutParams);
        this.f.setVisibility(this.c ? 4 : 0);
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.mbbanner.common.bridge.BannerExpandDialog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BannerExpandDialog.this.dismiss();
            }
        });
        this.d.addView(this.f);
        setContentView(this.d);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
            getWindow().getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            getWindow().setAttributes(attributes);
            if (Build.VERSION.SDK_INT >= 16) {
                getWindow().getDecorView().setSystemUiVisibility(Build.VERSION.SDK_INT >= 19 ? 4615 : 519);
            }
        }
        this.e.setWebViewListener(new com.mbridge.msdk.mbjscommon.c.b() { // from class: com.mbridge.msdk.mbbanner.common.bridge.BannerExpandDialog.2
            @Override // com.mbridge.msdk.mbjscommon.c.b, com.mbridge.msdk.mbjscommon.windvane.d
            public final void a(WebView webView, String str) {
                super.a(webView, str);
                StringBuilder sb = new StringBuilder("javascript:");
                sb.append(com.mbridge.msdk.b.b.b.a().b());
                if (Build.VERSION.SDK_INT <= 19) {
                    webView.loadUrl(sb.toString());
                } else {
                    webView.evaluateJavascript(sb.toString(), new ValueCallback<String>() { // from class: com.mbridge.msdk.mbbanner.common.bridge.BannerExpandDialog.2.1
                        @Override // android.webkit.ValueCallback
                        public final /* bridge */ /* synthetic */ void onReceiveValue(String str2) {
                        }
                    });
                }
                BannerExpandDialog.a(BannerExpandDialog.this);
            }
        });
        this.e.setObject(this.j);
        this.e.loadUrl(this.b);
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.mbridge.msdk.mbbanner.common.bridge.BannerExpandDialog.3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                if (BannerExpandDialog.this.i != null) {
                    BannerExpandDialog.this.i.a(false);
                }
                BannerExpandDialog.this.e.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
                BannerExpandDialog.this.d.removeView(BannerExpandDialog.this.e);
                BannerExpandDialog.this.e.release();
                BannerExpandDialog.this.e = null;
                BannerExpandDialog.this.i = null;
            }
        });
    }

    public void setCampaignList(String str, List<CampaignEx> list) {
        this.g = str;
        this.h = list;
    }

    static /* synthetic */ void a(BannerExpandDialog bannerExpandDialog) {
        try {
            int i = com.mbridge.msdk.foundation.controller.a.f().j().getResources().getConfiguration().orientation;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("orientation", i == 2 ? "landscape" : i == 1 ? "portrait" : "undefined");
            jSONObject.put("locked", "true");
            float fW = o.w(com.mbridge.msdk.foundation.controller.a.f().j());
            float fX = o.x(com.mbridge.msdk.foundation.controller.a.f().j());
            HashMap mapY = o.y(com.mbridge.msdk.foundation.controller.a.f().j());
            int iIntValue = ((Integer) mapY.get("width")).intValue();
            int iIntValue2 = ((Integer) mapY.get("height")).intValue();
            HashMap map = new HashMap();
            map.put("placementType", "Interstitial");
            map.put("state", "expanded");
            map.put("viewable", "true");
            map.put("currentAppOrientation", jSONObject);
            bannerExpandDialog.e.getLocationInWindow(new int[2]);
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(bannerExpandDialog.e, r2[0], r2[1], bannerExpandDialog.e.getWidth(), bannerExpandDialog.e.getHeight());
            com.mbridge.msdk.mbjscommon.mraid.a.a().b(bannerExpandDialog.e, r2[0], r2[1], bannerExpandDialog.e.getWidth(), bannerExpandDialog.e.getHeight());
            com.mbridge.msdk.mbjscommon.mraid.a.a().b(bannerExpandDialog.e, fW, fX);
            com.mbridge.msdk.mbjscommon.mraid.a.a().c(bannerExpandDialog.e, iIntValue, iIntValue2);
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(bannerExpandDialog.e, map);
            com.mbridge.msdk.mbjscommon.mraid.a.a().a(bannerExpandDialog.e);
        } catch (Throwable th) {
            s.b("BannerExpandDialog", "notifyMraid", th);
        }
    }
}
