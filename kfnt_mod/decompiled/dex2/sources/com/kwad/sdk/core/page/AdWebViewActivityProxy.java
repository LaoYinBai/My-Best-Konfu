package com.kwad.sdk.core.page;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.annotation.Keep;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.core.KsAdSdkDynamicImpl;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.api.loader.Wrapper;
import com.kwad.sdk.api.proxy.app.AdWebViewActivity;
import com.kwad.sdk.core.page.c;
import com.kwad.sdk.core.page.widget.b;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.u;
import com.kwad.sdk.utils.v;
import org.json.JSONObject;

@KsAdSdkDynamicImpl(AdWebViewActivity.class)
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AdWebViewActivityProxy extends com.kwad.sdk.h.a implements c.a {
    public static final String KEY_LANDING_PAGE_TYPE = "key_landing_page_type";
    public static final String KEY_MINI_WINDOW = "key_mini_window";
    private static final String KEY_PAGE_TITLE = "key_page_title";
    private static final String KEY_PAGE_URL = "key_page_url";
    public static final String KEY_TEMPLATE = "key_template_json";
    private static final String TAG = "AdWebViewActivityProxy";
    public static boolean showingAdWebViewActivity = false;
    private c landingPageViewHelper;
    private AdTemplate mAdTemplate;
    private Context mContext;
    private boolean mIsVPlusShown = false;
    private boolean mNormalMode;

    public static class a {
        private String a;
        private String b;
        private AdTemplate c;

        /* renamed from: com.kwad.sdk.core.page.AdWebViewActivityProxy$a$a, reason: collision with other inner class name */
        public static class C0042a {
            private String a;
            private String b;
            private AdTemplate c;

            public C0042a a(AdTemplate adTemplate) {
                this.c = adTemplate;
                return this;
            }

            public C0042a a(String str) {
                this.a = str;
                return this;
            }

            public a a() {
                return new a(this.a, this.b, this.c);
            }

            public C0042a b(String str) {
                this.b = str;
                return this;
            }
        }

        private a(String str, String str2, AdTemplate adTemplate) {
            this.a = str;
            this.b = str2;
            this.c = adTemplate;
        }
    }

    private boolean isFormAdExitInterceptEnable() {
        if (this.mNormalMode || this.mAdTemplate == null || com.kwad.sdk.core.response.a.d.v(this.mAdTemplate)) {
            return false;
        }
        if (com.kwad.sdk.core.config.b.D() && this.mAdTemplate.mIsFromContent) {
            return true;
        }
        return com.kwad.sdk.core.config.b.E() && !this.mAdTemplate.mIsFromContent;
    }

    public static void launch(Context context, a aVar) {
        if (context == null || TextUtils.isEmpty(aVar.a) || TextUtils.isEmpty(aVar.b)) {
            return;
        }
        KsAdSDKImpl.putComponentProxy(AdWebViewActivity.class, AdWebViewActivityProxy.class);
        Intent intent = new Intent(context, (Class<?>) AdWebViewActivity.class);
        intent.putExtra(KEY_PAGE_TITLE, aVar.a);
        intent.putExtra(KEY_PAGE_URL, aVar.b);
        intent.putExtra("key_template_json", aVar.c.toJson().toString());
        context.startActivity(intent);
    }

    public static void launch(Context context, AdTemplate adTemplate) {
        if (com.ksad.download.c.b.a(context)) {
            launch(context, adTemplate, 0);
        } else {
            u.a(context, v.a(context));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void launch(Context context, AdTemplate adTemplate, int i) {
        if (context == 0 || adTemplate == null) {
            return;
        }
        KsAdSDKImpl.putComponentProxy(AdWebViewActivity.class, AdWebViewActivityProxy.class);
        Intent intent = new Intent(context, (Class<?>) AdWebViewActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("key_template_json", adTemplate.toJson().toString());
        intent.putExtra(KEY_LANDING_PAGE_TYPE, i);
        if (adTemplate.mMiniWindowId != null) {
            intent.putExtra(KEY_MINI_WINDOW, adTemplate.mMiniWindowId);
        }
        context.startActivity(intent);
        boolean z = context instanceof ResContext;
        Object delegatedContext = context;
        if (z) {
            delegatedContext = ((ResContext) context).getDelegatedContext();
        }
        if (delegatedContext instanceof Activity) {
            ((Activity) delegatedContext).overridePendingTransition(0, 0);
        }
    }

    private void showMiniWindowIfNeeded() {
        final String stringExtra = getIntent().getStringExtra(KEY_MINI_WINDOW);
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        new Handler().post(new Runnable() { // from class: com.kwad.sdk.core.page.AdWebViewActivityProxy.1
            @Override // java.lang.Runnable
            public void run() {
                Rect rect = new Rect();
                DisplayMetrics displayMetrics = AdWebViewActivityProxy.this.getActivity().getResources().getDisplayMetrics();
                rect.right = displayMetrics.widthPixels;
                rect.left = rect.right - (displayMetrics.widthPixels / 4);
                rect.bottom = (int) (displayMetrics.heightPixels * 0.83f);
                rect.top = rect.bottom - (((displayMetrics.widthPixels / 4) * 16) / 9);
                new com.kwad.sdk.splashscreen.b(AdWebViewActivityProxy.this.getActivity(), stringExtra, false, null).a(rect);
            }
        });
    }

    @Override // com.kwad.sdk.h.a
    protected String getPageName() {
        return TAG;
    }

    @Override // com.kwad.sdk.core.page.c.a
    public void onBackBtnClicked(View view) {
        onBackPressed();
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onBackPressed() {
        if (!isFormAdExitInterceptEnable()) {
            super.onBackPressed();
            return;
        }
        com.kwad.sdk.core.page.widget.b bVar = new com.kwad.sdk.core.page.widget.b(getActivity(), new b.a() { // from class: com.kwad.sdk.core.page.AdWebViewActivityProxy.2
            @Override // com.kwad.sdk.core.page.widget.b.a
            public void a(DialogInterface dialogInterface) {
                com.kwad.sdk.core.report.a.f(AdWebViewActivityProxy.this.mAdTemplate, 104);
                dialogInterface.dismiss();
            }

            @Override // com.kwad.sdk.core.page.widget.b.a
            public void b(DialogInterface dialogInterface) {
                AdWebViewActivityProxy.super.onBackPressed();
                com.kwad.sdk.core.report.a.f(AdWebViewActivityProxy.this.mAdTemplate, 105);
            }

            @Override // com.kwad.sdk.core.page.widget.b.a
            public void c(DialogInterface dialogInterface) {
                com.kwad.sdk.core.report.a.f(AdWebViewActivityProxy.this.mAdTemplate, 106);
                dialogInterface.dismiss();
            }
        });
        com.kwad.sdk.core.report.a.c(this.mAdTemplate, 103, null);
        bVar.show();
    }

    @Override // com.kwad.sdk.core.page.c.a
    public void onCloseBtnClicked(View view) {
        if (!isFormAdExitInterceptEnable()) {
            finish();
            return;
        }
        com.kwad.sdk.core.page.widget.b bVar = new com.kwad.sdk.core.page.widget.b(getActivity(), new b.a() { // from class: com.kwad.sdk.core.page.AdWebViewActivityProxy.3
            @Override // com.kwad.sdk.core.page.widget.b.a
            public void a(DialogInterface dialogInterface) {
                com.kwad.sdk.core.report.a.f(AdWebViewActivityProxy.this.mAdTemplate, 104);
                dialogInterface.dismiss();
            }

            @Override // com.kwad.sdk.core.page.widget.b.a
            public void b(DialogInterface dialogInterface) {
                AdWebViewActivityProxy.this.finish();
                com.kwad.sdk.core.report.a.f(AdWebViewActivityProxy.this.mAdTemplate, 105);
            }

            @Override // com.kwad.sdk.core.page.widget.b.a
            public void c(DialogInterface dialogInterface) {
                com.kwad.sdk.core.report.a.f(AdWebViewActivityProxy.this.mAdTemplate, 106);
                dialogInterface.dismiss();
            }
        });
        com.kwad.sdk.core.report.a.c(this.mAdTemplate, 103, null);
        bVar.show();
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onCreate(Bundle bundle) {
        c cVar;
        c.b bVarA;
        super.onCreate(bundle);
        this.mContext = Wrapper.wrapContextIfNeed(getActivity());
        String stringExtra = getIntent().getStringExtra("key_template_json");
        String stringExtra2 = getIntent().getStringExtra(KEY_PAGE_TITLE);
        String stringExtra3 = getIntent().getStringExtra(KEY_PAGE_URL);
        int intExtra = getIntent().getIntExtra(KEY_LANDING_PAGE_TYPE, 0);
        showingAdWebViewActivity = true;
        try {
            AdTemplate adTemplate = new AdTemplate();
            adTemplate.parseJson(new JSONObject(stringExtra));
            this.mAdTemplate = adTemplate;
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
        }
        if (!TextUtils.isEmpty(stringExtra2) && !TextUtils.isEmpty(stringExtra3)) {
            this.mNormalMode = true;
            bVarA = new c.C0043c().a(true).b(false).a(stringExtra2).b(stringExtra3).a();
            this.landingPageViewHelper = new c(this.mContext, this.mAdTemplate, 1, false);
            this.landingPageViewHelper.a(this);
            cVar = this.landingPageViewHelper;
        } else {
            if (this.mAdTemplate == null) {
                finish();
                return;
            }
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.mAdTemplate);
            this.landingPageViewHelper = new c(this.mContext, this.mAdTemplate, intExtra, true);
            this.landingPageViewHelper.a(this);
            cVar = this.landingPageViewHelper;
            bVarA = new c.C0043c().b(com.kwad.sdk.core.response.a.b.j(this.mAdTemplate) ? com.kwad.sdk.core.response.a.b.i(this.mAdTemplate) : com.kwad.sdk.core.response.a.a.G(adInfoJ)).a();
        }
        cVar.a(bVarA);
        setContentView(this.landingPageViewHelper.a());
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    protected void onDestroy() {
        showingAdWebViewActivity = false;
        if (this.landingPageViewHelper != null) {
            this.landingPageViewHelper.g();
        }
        super.onDestroy();
        if (this.mAdTemplate == null || !this.mAdTemplate.interactLandingPageShowing) {
            return;
        }
        this.mAdTemplate.interactLandingPageShowing = false;
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onPause() {
        showingAdWebViewActivity = false;
        if (this.landingPageViewHelper != null) {
            this.landingPageViewHelper.f();
        }
        overridePendingTransition(0, 0);
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onResume() {
        super.onResume();
        showingAdWebViewActivity = true;
        if (this.landingPageViewHelper != null) {
            this.landingPageViewHelper.e();
        }
        if (this.mIsVPlusShown) {
            return;
        }
        showMiniWindowIfNeeded();
        this.mIsVPlusShown = true;
    }
}
