package com.sigmob.sdk.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.ab;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.ClickCommon;
import com.sigmob.sdk.base.models.CurrentAppOrientation;
import com.sigmob.sdk.base.models.ExposureChange;
import com.sigmob.sdk.base.models.MraidEnv;
import com.sigmob.sdk.base.models.PlacementType;
import com.sigmob.sdk.base.models.SigMacroCommon;
import com.sigmob.sdk.base.models.VideoItem;
import com.sigmob.sdk.base.models.ViewState;
import com.sigmob.sdk.common.models.SigmobError;
import com.sigmob.sdk.common.models.sigdsp.pb.Ad;
import com.sigmob.sdk.common.models.sigdsp.pb.MaterialMeta;
import com.sigmob.sdk.common.models.sigdsp.pb.RvAdSetting;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntitySigmob;
import com.sigmob.sdk.common.utils.n;
import com.sigmob.sdk.common.utils.q;
import com.sigmob.sdk.mraid.CloseableLayout;
import com.sigmob.sdk.mraid.MraidWebView;
import com.sigmob.sdk.mraid.f;
import com.ss.union.game.sdk.core.base.event.INetUploadStrategy;
import com.tachikoma.core.component.anim.AnimationProperty;
import com.tachikoma.core.component.input.TextAlign;
import com.tachikoma.core.utility.UriUtil;
import com.umeng.message.common.inter.ITagManager;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private final BaseAdUnit a;
    private final PlacementType b;
    private final f c;
    private String d;
    private b e;
    private MraidWebView f;
    private ab g;
    private boolean h;
    private final WebViewClient i;

    /* renamed from: com.sigmob.sdk.mraid.a$a, reason: collision with other inner class name */
    static class C0021a {
        private final BaseAdUnit a;

        public C0021a(BaseAdUnit baseAdUnit) {
            this.a = baseAdUnit;
        }

        @JavascriptInterface
        public String addMacro(JSONObject jSONObject) {
            try {
                String string = jSONObject.has(com.ss.union.game.sdk.redemptionCode.a.p) ? jSONObject.getString(com.ss.union.game.sdk.redemptionCode.a.p) : null;
                String string2 = jSONObject.has("value") ? jSONObject.getString("value") : null;
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                    this.a.getMacroCommon().addMarcoKey(string, string2);
                    return a.a(200, "addMacro success", (Object) null);
                }
                return a.a(300, "key or value is empty", (Object) null);
            } catch (Throwable th) {
                return a.a(500, "addMacro add fail " + th.getMessage(), (Object) null);
            }
        }

        @JavascriptInterface
        public String excuteRewardAdTrack(JSONObject jSONObject) {
            try {
                String string = jSONObject.getString(INetUploadStrategy.KEY_EVENT);
                int iA = com.sigmob.sdk.base.a.c.a(this.a, string, true);
                if (iA == 0) {
                    return a.a(200, "excuteRewardAdTrack success", (Object) null);
                }
                if (iA == -1) {
                    return a.a(300, "event is empty", (Object) null);
                }
                if (iA == -2) {
                    return a.a(300, string + " can't find in trackers", (Object) null);
                }
                return a.a(400, "unknown error: " + iA, (Object) null);
            } catch (Throwable th) {
                return a.a(500, "unknown error: " + th.getMessage(), (Object) null);
            }
        }

        @JavascriptInterface
        public String func(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.has("func") ? jSONObject.getString("func") : null;
                if (TextUtils.isEmpty(string)) {
                    return a.a(400, "func is empty", (Object) null);
                }
                n.a aVar = new n.a(this, string.replace(Constants.COLON_SEPARATOR, ""));
                aVar.a(JSONObject.class, jSONObject);
                return (String) aVar.a();
            } catch (Throwable th) {
                return a.a(500, th.getMessage(), (Object) null);
            }
        }

        @JavascriptInterface
        public String getAppInfo(JSONObject jSONObject) {
            Object objY;
            int I;
            BaseAdUnit baseAdUnit;
            try {
                JSONObject jSONObject2 = new JSONObject();
                if (jSONObject != null && jSONObject.has("arguments")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("arguments");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        String string = jSONArray.getString(i);
                        try {
                            switch (string) {
                                case "os":
                                    jSONObject2.put(string, 2);
                                    break;
                                case "imei":
                                    objY = com.sigmob.sdk.common.a.n().y();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "android_id":
                                    objY = com.sigmob.sdk.common.a.n().t();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "google_aid":
                                    objY = com.sigmob.sdk.common.a.n().w();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "clienttype":
                                    objY = com.sigmob.sdk.common.a.e();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "app_version":
                                    objY = com.sigmob.sdk.common.a.n().R();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "sdk_version":
                                    objY = com.sigmob.sdk.common.Constants.SDK_VERSION;
                                    jSONObject2.put(string, objY);
                                    break;
                                case "os_version":
                                    objY = com.sigmob.sdk.common.a.c();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "client_pixel":
                                    objY = String.format("%sx%s", Integer.valueOf(com.sigmob.sdk.common.a.n().ai().widthPixels), Integer.valueOf(com.sigmob.sdk.common.a.n().ai().heightPixels));
                                    jSONObject2.put(string, objY);
                                    break;
                                case "device_width":
                                    objY = com.sigmob.sdk.common.a.n().K();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "device_height":
                                    objY = com.sigmob.sdk.common.a.n().J();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "screen_density":
                                    I = com.sigmob.sdk.common.a.n().I();
                                    jSONObject2.put(string, I);
                                    break;
                                case "network_type":
                                    I = com.sigmob.sdk.common.a.n().S();
                                    jSONObject2.put(string, I);
                                    break;
                                case "pkgname":
                                    objY = com.sigmob.sdk.common.a.n().P();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "screenangle":
                                    I = Math.abs(com.sigmob.sdk.common.a.n().Q().intValue() - 1) * 90;
                                    jSONObject2.put(string, I);
                                    break;
                                case "creative_type":
                                    I = this.a.getCreativeType();
                                    jSONObject2.put(string, I);
                                    break;
                                case "ad_type":
                                    I = this.a.getAd_type();
                                    jSONObject2.put(string, I);
                                    break;
                                case "request_id":
                                    objY = this.a.getUuid();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "placement_id":
                                    baseAdUnit = this.a;
                                    objY = baseAdUnit.getadslot_id();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "appid":
                                    objY = com.sigmob.sdk.common.a.n().ab();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "ad_source_logo":
                                    objY = this.a.getAd_source_logo();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "ad_source_channel":
                                    objY = this.a.getAd_source_channel();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "adslot_id":
                                    baseAdUnit = this.a;
                                    objY = baseAdUnit.getadslot_id();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "vid":
                                    objY = this.a.getAd().vid;
                                    jSONObject2.put(string, objY);
                                    break;
                                case "crid":
                                    objY = this.a.getCrid();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "camp_id":
                                    objY = this.a.getCamp_id();
                                    jSONObject2.put(string, objY);
                                    break;
                                case "cust_id":
                                    objY = this.a.getAd().cust_id;
                                    jSONObject2.put(string, objY);
                                    break;
                                case "bid_price":
                                    objY = this.a.getAd().bid_price;
                                    jSONObject2.put(string, objY);
                                    break;
                                case "product_id":
                                    objY = this.a.getAd().product_id;
                                    jSONObject2.put(string, objY);
                                    break;
                                case "settlement_price_enc":
                                    objY = this.a.getAd().settlement_price_enc;
                                    jSONObject2.put(string, objY);
                                    break;
                                case "is_override":
                                    objY = this.a.getAd().is_override;
                                    jSONObject2.put(string, objY);
                                    break;
                                case "forbiden_parse_landingpage":
                                    objY = this.a.getAd().forbiden_parse_landingpage;
                                    jSONObject2.put(string, objY);
                                    break;
                                case "display_orientation":
                                    objY = this.a.getAd().display_orientation;
                                    jSONObject2.put(string, objY);
                                    break;
                                case "expired_time":
                                    objY = this.a.getAd().expired_time;
                                    jSONObject2.put(string, objY);
                                    break;
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }
                return a.a(200, "getAppInfo success ", jSONObject2);
            } catch (Throwable th) {
                return a.a(500, "getAppInfo error: " + th.getMessage(), (Object) null);
            }
        }

        @JavascriptInterface
        public String hello(JSONObject jSONObject) {
            try {
                return jSONObject.toString();
            } catch (Throwable unused) {
                return null;
            }
        }

        @JavascriptInterface
        public String javascriptAddDcLog(final JSONObject jSONObject) {
            try {
                if (jSONObject == null) {
                    return a.a(400, "not params", (Object) null);
                }
                if (!jSONObject.has("_ac_type")) {
                    return a.a(300, "_ac_type is empty", (Object) null);
                }
                t.a(jSONObject.getString("_ac_type"), this.a, new t.a() { // from class: com.sigmob.sdk.mraid.a.a.1
                    @Override // com.sigmob.sdk.base.common.t.a
                    public void a(Object obj) {
                        HashMap map = new HashMap();
                        Iterator<String> itKeys = jSONObject.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            if (next.equalsIgnoreCase(com.sigmob.sdk.common.Constants.EXT)) {
                                try {
                                    try {
                                        JSONObject jSONObject2 = new JSONObject(jSONObject.optString(next));
                                        Iterator<String> itKeys2 = jSONObject2.keys();
                                        while (itKeys2.hasNext()) {
                                            String next2 = itKeys2.next();
                                            map.put(next2, jSONObject2.optString(next2));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } catch (Exception unused) {
                                    map.put(com.sigmob.sdk.common.Constants.EXT, Base64.encodeToString(jSONObject.getString(com.sigmob.sdk.common.Constants.EXT).getBytes(), 2));
                                }
                            } else if (!next.equalsIgnoreCase("_ac_type")) {
                                try {
                                    map.put(next, jSONObject.getString(next));
                                } catch (Throwable unused2) {
                                }
                            }
                        }
                        map.put("source", "js");
                        if (obj instanceof PointEntitySigmob) {
                            ((PointEntitySigmob) obj).setOptions(map);
                        }
                    }
                });
                return a.a(200, "add dc log success", (Object) null);
            } catch (Throwable th) {
                return a.a(500, th.toString(), (Object) null);
            }
        }

        @JavascriptInterface
        public String tracking(JSONObject jSONObject) {
            try {
                String strOptString = jSONObject.optString(INetUploadStrategy.KEY_EVENT);
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("urls");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                    return a.a(300, "urls is empty", (Object) null);
                }
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    com.sigmob.sdk.base.common.e eVar = new com.sigmob.sdk.base.common.e(jSONArrayOptJSONArray.optString(i), strOptString, this.a.getUuid());
                    eVar.a(Integer.valueOf(this.a.getTrackingRetryNum()));
                    eVar.a("js");
                    com.sigmob.sdk.base.a.c.a(eVar, this.a, false);
                }
                return a.a(200, "tracking success", (Object) null);
            } catch (Throwable th) {
                return a.a(500, "unknown error: " + th.getMessage(), (Object) null);
            }
        }
    }

    public interface b {
        void a();

        void a(int i, int i2, int i3, int i4, CloseableLayout.a aVar, boolean z);

        void a(SigmobError sigmobError);

        void a(String str, Map<String, String> map);

        void a(URI uri);

        void a(URI uri, com.sigmob.sdk.videoAd.f fVar, String str);

        void a(URI uri, boolean z);

        void a(boolean z);

        void a(boolean z, g gVar);

        boolean a(ConsoleMessage consoleMessage);

        boolean a(String str, JsResult jsResult);

        void b();

        void b(String str, Map<String, String> map);

        void b(boolean z);

        void c();

        void d();

        void e();
    }

    a(BaseAdUnit baseAdUnit, PlacementType placementType) {
        this(baseAdUnit, placementType, new f());
    }

    a(BaseAdUnit baseAdUnit, PlacementType placementType, f fVar) {
        this.i = new j() { // from class: com.sigmob.sdk.mraid.a.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                a.this.l();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                SigmobLog.e("Error: " + str);
                super.onReceivedError(webView, i, str, str2);
            }

            @Override // android.webkit.WebViewClient
            public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
                a.this.a(renderProcessGoneDetail);
                return true;
            }

            @Override // com.sigmob.sdk.mraid.j, android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                if (str.endsWith(a.this.a.getVideoPath()) && a.this.a.isVideoExist()) {
                    try {
                        return new WebResourceResponse("video/mp4", "UTF-8", new FileInputStream(a.this.a.getVideoPath()));
                    } catch (FileNotFoundException e) {
                        SigmobLog.e(e.getMessage());
                    }
                }
                return super.shouldInterceptRequest(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return a.this.a(webView, str);
            }
        };
        this.a = baseAdUnit;
        this.b = placementType;
        this.c = fVar;
    }

    private int a(int i, int i2, int i3) throws com.sigmob.sdk.mraid.b {
        if (i >= i2 && i <= i3) {
            return i;
        }
        throw new com.sigmob.sdk.mraid.b("Integer parameter out of range: " + i);
    }

    private CloseableLayout.a a(String str, CloseableLayout.a aVar) throws com.sigmob.sdk.mraid.b {
        if (TextUtils.isEmpty(str)) {
            return aVar;
        }
        if (str.equals("top-left")) {
            return CloseableLayout.a.TOP_LEFT;
        }
        if (str.equals("top-right")) {
            return CloseableLayout.a.TOP_RIGHT;
        }
        if (str.equals(TextAlign.CENTER)) {
            return CloseableLayout.a.CENTER;
        }
        if (str.equals("bottom-left")) {
            return CloseableLayout.a.BOTTOM_LEFT;
        }
        if (str.equals("bottom-right")) {
            return CloseableLayout.a.BOTTOM_RIGHT;
        }
        if (str.equals("top-center")) {
            return CloseableLayout.a.TOP_CENTER;
        }
        if (str.equals("bottom-center")) {
            return CloseableLayout.a.BOTTOM_CENTER;
        }
        throw new com.sigmob.sdk.mraid.b("Invalid close position: " + str);
    }

    public static String a(int i, String str, Object obj) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", i);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("message", str);
            }
            if (obj != null) {
                jSONObject.put("data", obj);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            return th.getMessage();
        }
    }

    private String a(Rect rect) {
        return rect.left + Constants.ACCEPT_TIME_SEPARATOR_SP + rect.top + Constants.ACCEPT_TIME_SEPARATOR_SP + rect.width() + Constants.ACCEPT_TIME_SEPARATOR_SP + rect.height();
    }

    public static String a(String str) {
        if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("undefined")) {
            return null;
        }
        return str;
    }

    private URI a(String str, URI uri) {
        return str == null ? uri : g(str);
    }

    private void a(e eVar) {
        c("window.mraidbridge.nativeCallComplete(" + JSONObject.quote(eVar.a()) + com.umeng.message.proguard.l.t);
    }

    private void a(e eVar, String str) {
        c("window.mraidbridge.notifyErrorEvent(" + JSONObject.quote(eVar.a()) + ", " + JSONObject.quote(str) + com.umeng.message.proguard.l.t);
    }

    private boolean a(String str, boolean z) {
        return str == null ? z : f(str);
    }

    private String b(Rect rect) {
        return rect.width() + Constants.ACCEPT_TIME_SEPARATOR_SP + rect.height();
    }

    private URI b(String str, String str2) throws com.sigmob.sdk.mraid.b {
        try {
            try {
                return str == null ? new URI(str2) : new URI(str);
            } catch (URISyntaxException unused) {
                return new URI(str2);
            }
        } catch (URISyntaxException unused2) {
            throw new com.sigmob.sdk.mraid.b("Invalid URL parameter: " + str);
        }
    }

    private int d(String str) throws com.sigmob.sdk.mraid.b {
        try {
            return Integer.parseInt(str, 10);
        } catch (NumberFormatException unused) {
            throw new com.sigmob.sdk.mraid.b("Invalid numeric parameter: " + str);
        }
    }

    private g e(String str) throws com.sigmob.sdk.mraid.b {
        if ("portrait".equals(str)) {
            return g.PORTRAIT;
        }
        if ("landscape".equals(str)) {
            return g.LANDSCAPE;
        }
        if ("none".equals(str)) {
            return g.NONE;
        }
        throw new com.sigmob.sdk.mraid.b("Invalid orientation: " + str);
    }

    private boolean f(String str) throws com.sigmob.sdk.mraid.b {
        if (ITagManager.STATUS_TRUE.equals(str)) {
            return true;
        }
        if ("false".equals(str)) {
            return false;
        }
        throw new com.sigmob.sdk.mraid.b("Invalid boolean parameter: " + str);
    }

    private URI g(String str) throws com.sigmob.sdk.mraid.b {
        if (str == null) {
            throw new com.sigmob.sdk.mraid.b("Parameter cannot be null");
        }
        try {
            return new URI(str);
        } catch (URISyntaxException unused) {
            throw new com.sigmob.sdk.mraid.b("Invalid URL parameter: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.h) {
            return;
        }
        this.h = true;
        if (this.e != null) {
            this.e.a();
        }
    }

    public String a() {
        return this.d;
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    public void a(Context context, String str) {
        if (this.f == null) {
            SigmobLog.e("MRAID bridge called setContentHtml before WebView was attached");
            return;
        }
        this.f.addJavascriptInterface(new C0021a(this.a), "sigandroid");
        this.h = false;
        File fileA = com.sigmob.sdk.common.utils.d.a(str, com.sigmob.sdk.common.utils.k.a(str) + ".html");
        if (fileA == null || TextUtils.isEmpty(fileA.getAbsolutePath())) {
            this.f.loadDataWithBaseURL(com.sigmob.sdk.common.e.f.h() + "://localhost/", str, "text/html", "UTF-8", null);
            return;
        }
        this.f.loadUrl(UriUtil.FILE_PREFIX + fileA.getAbsolutePath());
    }

    public void a(Location location) {
        c("mraidbridge.setLocation(" + location.getLatitude() + Constants.ACCEPT_TIME_SEPARATOR_SP + location.getLongitude() + ",\"" + location.getProvider() + "\");");
    }

    @TargetApi(26)
    void a(RenderProcessGoneDetail renderProcessGoneDetail) {
        SigmobError sigmobError = (renderProcessGoneDetail == null || !renderProcessGoneDetail.didCrash()) ? SigmobError.RENDER_PROCESS_GONE_UNSPECIFIED : SigmobError.RENDER_PROCESS_GONE_WITH_CRASH;
        SigmobLog.e("handleRenderProcessGone " + sigmobError.toString());
        b();
        if (this.e != null) {
            this.e.a(sigmobError);
        }
    }

    void a(ValueCallback valueCallback) {
        a("mraidbridge.getPlayProgress();", valueCallback);
    }

    void a(CurrentAppOrientation currentAppOrientation) {
        c("mraidbridge.fireChangeEvent({" + currentAppOrientation.toString().replace("=", Constants.COLON_SEPARATOR) + "});");
    }

    void a(ExposureChange exposureChange) {
        c("mraidbridge.fireChangeEvent({" + exposureChange.toString().replace("=", Constants.COLON_SEPARATOR) + "});");
    }

    void a(MraidEnv mraidEnv) {
        c("mraidbridge.fireChangeEvent(" + com.sigmob.sdk.common.d.b.a((Object) mraidEnv, "env", true) + ");");
    }

    void a(PlacementType placementType) {
        c("mraidbridge.setPlacementType(" + JSONObject.quote(placementType.toJavascriptString()) + com.umeng.message.proguard.l.t);
    }

    void a(VideoItem videoItem) {
        c("sigmob.fireChangeEvent(" + com.sigmob.sdk.common.d.b.a((Object) videoItem, PointCategory.VIDEO, false) + ");");
    }

    void a(ViewState viewState) {
        c("mraidbridge.setState(" + JSONObject.quote(viewState.toJavascriptString()) + com.umeng.message.proguard.l.t);
    }

    void a(Ad ad) {
        c("sigmob.fireChangeEvent(" + com.sigmob.sdk.common.d.b.a((Object) ad, "ad", true) + ");");
    }

    void a(MaterialMeta materialMeta) {
        c("sigmob.fireChangeEvent(" + com.sigmob.sdk.common.d.b.a((Object) materialMeta, ClickCommon.CLICK_AREA_MATERIAL, true) + ");");
    }

    void a(RvAdSetting rvAdSetting) {
        c("sigmob.fireChangeEvent(" + com.sigmob.sdk.common.d.b.a((Object) rvAdSetting, "rvSetting", false) + ");");
    }

    @SuppressLint({"AddJavascriptInterface"})
    public void a(MraidWebView mraidWebView) {
        this.f = mraidWebView;
        this.f.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 17 && this.b == PlacementType.INTERSTITIAL) {
            mraidWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.f.setScrollContainer(false);
        this.f.setVerticalScrollBarEnabled(false);
        this.f.setHorizontalScrollBarEnabled(false);
        this.f.setBackgroundColor(0);
        this.f.setWebViewClient(this.i);
        this.f.setWebChromeClient(new WebChromeClient() { // from class: com.sigmob.sdk.mraid.a.2
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return a.this.e != null ? a.this.e.a(consoleMessage) : super.onConsoleMessage(consoleMessage);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return a.this.e != null ? a.this.e.a(str2, jsResult) : super.onJsAlert(webView, str, str2, jsResult);
            }

            @Override // android.webkit.WebChromeClient
            public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
                return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
            }

            @Override // android.webkit.WebChromeClient
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
                super.onShowCustomView(view, customViewCallback);
            }
        });
        this.g = new ab(this.f.getContext(), this.f, this.a);
        this.f.setOnTouchListener(new View.OnTouchListener() { // from class: com.sigmob.sdk.mraid.a.3
            private MotionEvent b;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) throws UnsupportedEncodingException {
                a.this.g.onTouchEvent(motionEvent);
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1:
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                }
                if (motionEvent.getAction() == 1) {
                    a.this.d = String.format("%d,%d,%d,%d", Integer.valueOf((int) this.b.getRawX()), Integer.valueOf((int) this.b.getRawY()), Integer.valueOf((int) motionEvent.getRawX()), Integer.valueOf((int) motionEvent.getRawY()));
                    if (this.b == null) {
                        this.b = motionEvent;
                    }
                    SigMacroCommon macroCommon = a.this.a.getMacroCommon();
                    if (macroCommon instanceof SigMacroCommon) {
                        SigMacroCommon sigMacroCommon = macroCommon;
                        sigMacroCommon.addMarcoKey(SigMacroCommon._DOWNX_, String.valueOf((int) this.b.getRawX()));
                        sigMacroCommon.addMarcoKey(SigMacroCommon._DOWNY_, String.valueOf((int) this.b.getRawY()));
                        sigMacroCommon.addMarcoKey(SigMacroCommon._UPX_, String.valueOf((int) motionEvent.getRawX()));
                        sigMacroCommon.addMarcoKey(SigMacroCommon._UPY_, String.valueOf((int) motionEvent.getRawY()));
                    }
                } else if (motionEvent.getAction() == 0) {
                    this.b = MotionEvent.obtain(motionEvent);
                }
                return false;
            }
        });
        this.f.setVisibilityChangedListener(new MraidWebView.a() { // from class: com.sigmob.sdk.mraid.a.4
            @Override // com.sigmob.sdk.mraid.MraidWebView.a
            public void a(boolean z) {
                if (a.this.e != null) {
                    a.this.e.a(z);
                }
            }
        });
    }

    void a(b bVar) {
        this.e = bVar;
    }

    void a(final e eVar, Map<String, String> map) throws com.sigmob.sdk.mraid.b {
        if (this.e == null) {
            throw new com.sigmob.sdk.mraid.b("Invalid state to execute this command");
        }
        if (this.f == null) {
            throw new com.sigmob.sdk.mraid.b("The current WebView is being destroyed");
        }
        switch (eVar) {
            case CLOSE:
                this.e.c();
                return;
            case UNLOAD:
                this.e.d();
                return;
            case OPENFOURELEMENTS:
                this.e.e();
                return;
            case RESIZE:
                this.e.a(a(d(map.get(AnimationProperty.WIDTH)), 0, 100000), a(d(map.get(AnimationProperty.HEIGHT)), 0, 100000), a(d(map.get("offsetX")), -100000, 100000), a(d(map.get("offsetY")), -100000, 100000), a(map.get("customClosePosition"), CloseableLayout.a.TOP_RIGHT), a(map.get("allowOffscreen"), true));
                return;
            case EXPAND:
                this.e.a(a(map.get("url"), (URI) null), a(map.get("shouldUseCustomClose"), false));
                return;
            case USE_CUSTOM_CLOSE:
                this.e.b(a(map.get("shouldUseCustomClose"), false));
                return;
            case OPEN:
                this.e.a(b(map.get("url"), "https://m.sigmob.com"), com.sigmob.sdk.videoAd.f.BrowserType, a(map.get(com.sigmob.sdk.common.Constants.EXT)));
                return;
            case SET_ORIENTATION_PROPERTIES:
                this.e.a(f(map.get("allowOrientationChange")), e(map.get("forceOrientation")));
                return;
            case PLAY_VIDEO:
                this.e.a(g(map.get("uri")));
                return;
            case STORE_PICTURE:
                this.c.a(this.f.getContext(), g(map.get("uri")).toString(), new f.a() { // from class: com.sigmob.sdk.mraid.a.5
                });
                return;
            case CREATE_CALENDAR_EVENT:
                this.c.a(this.f.getContext(), map);
                return;
            case VPAID:
                this.e.a(map.get(INetUploadStrategy.KEY_EVENT), map);
                return;
            case EXTENSION:
                this.e.b(map.get(INetUploadStrategy.KEY_EVENT), map);
                return;
            case UNSPECIFIED:
                throw new com.sigmob.sdk.mraid.b("Unspecified MRAID Javascript command");
            default:
                return;
        }
    }

    public void a(h hVar) {
        c("mraidbridge.setScreenSize(" + b(hVar.a()) + ");mraidbridge.setMaxSize(" + b(hVar.b()) + ");mraidbridge.setCurrentPosition(" + a(hVar.c()) + ");mraidbridge.setDefaultPosition(" + a(hVar.d()) + com.umeng.message.proguard.l.t);
        StringBuilder sb = new StringBuilder();
        sb.append("mraidbridge.notifySizeChangeEvent(");
        sb.append(b(hVar.c()));
        sb.append(com.umeng.message.proguard.l.t);
        c(sb.toString());
    }

    void a(String str, ValueCallback valueCallback) {
        if (this.f == null) {
            SigmobLog.e("Attempted to inject Javascript into MRAID WebView while was not attached:\n\t" + str);
            return;
        }
        SigmobLog.e("Injecting Javascript into MRAID WebView:\n\t" + str);
        if (Build.VERSION.SDK_INT >= 19) {
            this.f.evaluateJavascript(str, valueCallback);
            return;
        }
        SigmobLog.e("Injecting Javascript into MRAID WebView:\n\t can't support less KITKAT" + str);
    }

    void a(String str, String str2) throws UnsupportedEncodingException {
        this.d = String.format("%s,%s,%s,%s", str, str2, str, str2);
        SigMacroCommon macroCommon = this.a.getMacroCommon();
        if (macroCommon instanceof SigMacroCommon) {
            SigMacroCommon sigMacroCommon = macroCommon;
            sigMacroCommon.addMarcoKey(SigMacroCommon._DOWNX_, str);
            sigMacroCommon.addMarcoKey(SigMacroCommon._DOWNY_, str2);
            sigMacroCommon.addMarcoKey(SigMacroCommon._UPX_, str);
            sigMacroCommon.addMarcoKey(SigMacroCommon._UPY_, str2);
        }
        this.a.getClickCommon().down = new q(Integer.parseInt(str), Integer.parseInt(str2));
        this.a.getClickCommon().up = new q(Integer.parseInt(str), Integer.parseInt(str2));
    }

    void a(boolean z) {
        c("mraidbridge.setIsViewable(" + z + com.umeng.message.proguard.l.t);
    }

    void a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        c("mraidbridge.setSupports(" + z + Constants.ACCEPT_TIME_SEPARATOR_SP + z2 + Constants.ACCEPT_TIME_SEPARATOR_SP + z3 + Constants.ACCEPT_TIME_SEPARATOR_SP + z4 + Constants.ACCEPT_TIME_SEPARATOR_SP + z5 + Constants.ACCEPT_TIME_SEPARATOR_SP + z6 + Constants.ACCEPT_TIME_SEPARATOR_SP + z7 + com.umeng.message.proguard.l.t);
    }

    boolean a(WebView webView, String str) {
        try {
            new URI(str);
            Uri uri = Uri.parse(str);
            String scheme = uri.getScheme();
            String host = uri.getHost();
            if (com.sigmob.sdk.common.Constants.SDK_FOLDER.equals(scheme)) {
                if ("failLoad".equals(host) && this.b == PlacementType.INLINE && this.e != null) {
                    this.e.b();
                }
                return true;
            }
            if (!"mraid".equals(scheme)) {
                if (!TextUtils.isEmpty(str) && str.startsWith(com.sigmob.sdk.common.Constants.HTTP)) {
                    webView.loadUrl(str);
                }
                return true;
            }
            e eVarA = e.a(host);
            try {
                a(eVarA, com.sigmob.sdk.common.a.a(uri));
            } catch (Throwable th) {
                a(eVarA, th.getMessage());
            }
            a(eVarA);
            return true;
        } catch (URISyntaxException unused) {
            SigmobLog.e("Invalid MRAID URL: " + str);
            a(e.UNSPECIFIED, "Mraid command sent an invalid URL");
            return true;
        }
    }

    void b() {
        if (this.f != null) {
            this.f.destroy();
            this.f = null;
        }
    }

    void b(ValueCallback valueCallback) {
        a("mraidbridge.getAdDuration();", valueCallback);
    }

    @SuppressLint({"JavascriptInterface"})
    public void b(String str) {
        if (this.f == null) {
            SigmobLog.e("MRAID bridge called setContentHtml while WebView was not attached");
            return;
        }
        this.f.addJavascriptInterface(new C0021a(this.a), "sigandroid");
        this.h = false;
        this.f.loadUrl(str);
    }

    void c() {
        c("mraidbridge.notifyReadyEvent();");
    }

    void c(String str) {
        if (this.f == null) {
            SigmobLog.e("Attempted to inject Javascript into MRAID WebView while was not attached:\n\t" + str);
            return;
        }
        SigmobLog.e("Injecting Javascript into MRAID WebView:\n\t" + str);
        if (Build.VERSION.SDK_INT >= 19) {
            this.f.evaluateJavascript(str, null);
            return;
        }
        this.f.loadUrl("javascript:" + str);
    }

    void d() {
        c("sigmob.notifyApkDownloadStartEvent();");
    }

    void e() {
        c("sigmob.notifyApkDownloadFailEvent();");
    }

    void f() {
        c("sigmob.notifyApkDownloadEndEvent();");
    }

    void g() {
        c("sigmob.notifyApkDownloadInstalledEvent();");
    }

    void h() {
        c("mraidbridge.startAd();");
    }

    boolean i() {
        MraidWebView mraidWebView = this.f;
        return mraidWebView != null && mraidWebView.a();
    }

    boolean j() {
        return this.f != null;
    }

    boolean k() {
        return this.h;
    }
}
