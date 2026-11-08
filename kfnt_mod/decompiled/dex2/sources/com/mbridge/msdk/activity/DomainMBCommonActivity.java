package com.mbridge.msdk.activity;

import android.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.mbridge.msdk.b.a;
import com.mbridge.msdk.b.b;
import com.mbridge.msdk.click.c;
import com.mbridge.msdk.foundation.db.f;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.v;
import com.mbridge.msdk.foundation.webview.BrowserView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DomainMBCommonActivity extends Activity {
    private CampaignEx b;
    private BrowserView c;
    String a = "";
    private BrowserView.a d = new BrowserView.a() { // from class: com.mbridge.msdk.activity.DomainMBCommonActivity.1
        @Override // com.mbridge.msdk.foundation.webview.BrowserView.a
        public final void a(WebView webView, String str, Bitmap bitmap) {
        }

        @Override // com.mbridge.msdk.foundation.webview.BrowserView.a
        public final void a() {
            DomainMBCommonActivity.this.finish();
        }

        @Override // com.mbridge.msdk.foundation.webview.BrowserView.a
        public final boolean a(WebView webView, String str) {
            s.d("MBCommonActivity", "shouldOverrideUrlLoading  " + str);
            if (v.a.a(str) && v.a.a(DomainMBCommonActivity.this, str, null)) {
                DomainMBCommonActivity.this.finish();
            }
            return DomainMBCommonActivity.this.a(webView, str);
        }
    };

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) throws Throwable {
        super.onCreate(bundle);
        requestWindowFeature(1);
        String stringExtra = getIntent().getStringExtra("intent_flag");
        String stringExtra2 = getIntent().getStringExtra("intent_jsonobject");
        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("shortcuts") && !TextUtils.isEmpty(stringExtra2)) {
            try {
                this.b = CampaignEx.parseCampaignWithBackData(new JSONObject(stringExtra2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                a aVarB = b.a().b(com.mbridge.msdk.foundation.controller.a.f().k());
                if (aVarB == null) {
                    aVarB = b.a().b();
                }
                CampaignEx campaignExE = f.a(i.a(this)).e(this.b.getId(), aVarB.g());
                com.mbridge.msdk.foundation.controller.a.f().a(this);
                if (campaignExE != null && !TextUtils.isEmpty(this.b.getImpressionURL()) && !TextUtils.isEmpty(aVarB.g()) && campaignExE.getIsClick() == 0) {
                    com.mbridge.msdk.click.b.a((Context) this, this.b, aVarB.g(), this.b.getImpressionURL(), false, true);
                }
                this.b.setIsClick(1);
                ContentValues contentValues = new ContentValues();
                contentValues.put("is_click", (Integer) 1);
                f.a(i.a(this)).a(this.b.getId(), contentValues);
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                setContentView(linearLayout);
                return;
            } catch (Exception e2) {
                finish();
                e2.printStackTrace();
                return;
            }
        }
        this.a = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(this.a)) {
            this.b = (CampaignEx) getIntent().getSerializableExtra("mvcommon");
            this.c = new BrowserView(this, this.b);
            this.c.setListener(this.d);
            this.c.loadUrl(this.a);
            if (this.c != null) {
                setContentView(this.c);
                return;
            }
            return;
        }
        Toast.makeText(this, "Error: no data", 0).show();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.c != null) {
            this.c.destroy();
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        String stringExtra = getIntent().getStringExtra("intent_flag");
        if (TextUtils.isEmpty(stringExtra) || !stringExtra.equals("shortcuts")) {
            return;
        }
        super.setTheme(R.style.Theme.Translucent.NoTitleBar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(WebView webView, String str) {
        String str2;
        try {
        } catch (Throwable th) {
            s.d("MBCommonActivity", th.getMessage());
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri uri = Uri.parse(str);
        if (uri.getScheme().equals("http") || uri.getScheme().equals("https")) {
            return false;
        }
        if (uri.getScheme().equals("intent")) {
            Intent uri2 = Intent.parseUri(str, 1);
            try {
                str2 = uri2.getPackage();
            } catch (Throwable th2) {
                s.d("MBCommonActivity", th2.getMessage());
            }
            if (!TextUtils.isEmpty(str2) && getPackageManager().getLaunchIntentForPackage(str2) != null) {
                uri2.setFlags(268435456);
                startActivityForResult(uri2, 0);
                finish();
                return true;
            }
            try {
                String stringExtra = uri2.getStringExtra("browser_fallback_url");
                if (!TextUtils.isEmpty(stringExtra)) {
                    Uri uri3 = Uri.parse(str);
                    if (uri3.getScheme().equals("http") || uri3.getScheme().equals("https")) {
                        webView.loadUrl(stringExtra);
                        return false;
                    }
                    str = stringExtra;
                }
            } catch (Throwable th3) {
                s.d("MBCommonActivity", th3.getMessage());
            }
            s.d("MBCommonActivity", th.getMessage());
            return false;
        }
        if (!c.e(this, str)) {
            return false;
        }
        s.d("MBCommonActivity", "openDeepLink");
        finish();
        return true;
    }
}
