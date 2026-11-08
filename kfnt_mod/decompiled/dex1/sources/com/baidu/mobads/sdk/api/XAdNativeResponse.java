package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import androidx.core.app.NotificationCompat;
import com.baidu.mobads.sdk.api.NativeResponse;
import com.baidu.mobads.sdk.internal.a;
import com.baidu.mobads.sdk.internal.ah;
import com.baidu.mobads.sdk.internal.bb;
import com.baidu.mobads.sdk.internal.by;
import com.baidu.mobads.sdk.internal.co;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class XAdNativeResponse implements NativeResponse {
    private static final String TAG = "NativeResponse";
    private boolean isDownloadApp;
    private int mAdActionType = 1;
    private NativeResponse.AdDislikeListener mAdDislikeListener;
    private a mAdInstanceInfo;
    private NativeResponse.AdInteractionListener mAdInteractionListener;
    private NativeResponse.AdPrivacyListener mAdPrivacyListener;
    private Context mCxt;
    private co mFeedsProd;
    private by mUriUtils;

    public XAdNativeResponse(Context context, co coVar, a aVar) {
        this.isDownloadApp = false;
        this.mCxt = context;
        this.mAdInstanceInfo = aVar;
        this.mFeedsProd = coVar;
        if (this.mAdInstanceInfo.p() == 2) {
            this.isDownloadApp = true;
        }
        this.mUriUtils = by.a();
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getAdLogoUrl() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.h() : "https://cpro.baidustatic.com/cpro/logo/sdk/mob-adIcon_2x.png";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getBaiduLogoUrl() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.i() : "https://cpro.baidustatic.com/cpro/logo/sdk/new-bg-logo.png";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getTitle() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.a() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getDesc() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.b() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getIconUrl() {
        if (this.mAdInstanceInfo == null) {
            return "";
        }
        String strC = this.mAdInstanceInfo.c();
        return TextUtils.isEmpty(strC) ? this.mAdInstanceInfo.d() : strC;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getImageUrl() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.d() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public int getAdActionType() {
        return this.mAdActionType;
    }

    public void setAdActionType(int i) {
        this.mAdActionType = i;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public boolean isNeedDownloadApp() {
        return this.isDownloadApp;
    }

    public void setIsDownloadApp(boolean z) {
        this.isDownloadApp = z;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void pauseAppDownload() throws JSONException {
        if (this.mCxt == null || !this.isDownloadApp || this.mFeedsProd == null) {
            return;
        }
        JSONObject jSONObjectQ = this.mAdInstanceInfo.Q();
        try {
            jSONObjectQ.put("pk", getAppPackage());
            jSONObjectQ.put("msg", "pauseDownload");
        } catch (JSONException unused) {
        }
        ah.a(this.mCxt.getApplicationContext()).a(getAppPackage());
        this.mFeedsProd.a(jSONObjectQ);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void resumeAppDownload() throws JSONException {
        if (!this.isDownloadApp || this.mFeedsProd == null || this.mAdInstanceInfo == null) {
            return;
        }
        JSONObject jSONObjectQ = this.mAdInstanceInfo.Q();
        try {
            jSONObjectQ.put("msg", "resumeDownload");
        } catch (JSONException unused) {
        }
        this.mFeedsProd.a(jSONObjectQ);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public int getDownloadStatus() {
        if (!this.isDownloadApp || this.mCxt == null) {
            return -1;
        }
        return ah.a(this.mCxt.getApplicationContext()).a(this.mCxt.getApplicationContext(), getAppPackage());
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public boolean isAdAvailable(Context context) {
        return this.mAdInstanceInfo != null && System.currentTimeMillis() - this.mAdInstanceInfo.x() <= this.mAdInstanceInfo.D();
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public long getAppSize() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.j();
        }
        return 0L;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public boolean isAutoPlay() {
        return this.mAdInstanceInfo != null && this.mAdInstanceInfo.k() == 1;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public boolean isNonWifiAutoPlay() {
        return this.mAdInstanceInfo == null || this.mAdInstanceInfo.l() == 1;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getAppPackage() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.m() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public List<String> getMultiPicUrls() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.E();
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public Map<String, String> getExtras() {
        HashMap map = new HashMap();
        if (this.mFeedsProd != null) {
            map.put("appsid", this.mFeedsProd.n);
        }
        return map;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void clearImpressionTaskWhenBack() {
        if (this.mFeedsProd != null) {
            this.mFeedsProd.p();
        }
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void permissionClick() throws JSONException {
        if (this.mAdInstanceInfo == null || this.mFeedsProd == null) {
            return;
        }
        String strC = this.mAdInstanceInfo.C();
        JSONObject jSONObjectQ = this.mAdInstanceInfo.Q();
        try {
            jSONObjectQ.put("permissionUrl", strC);
            jSONObjectQ.put("msg", "permissionClick");
        } catch (JSONException unused) {
        }
        this.mFeedsProd.a(jSONObjectQ);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void privacyClick() throws JSONException {
        if (this.mAdInstanceInfo == null || this.mFeedsProd == null) {
            return;
        }
        String strB = this.mAdInstanceInfo.B();
        JSONObject jSONObjectQ = this.mAdInstanceInfo.Q();
        try {
            jSONObjectQ.put("privacy_link", strB);
            jSONObjectQ.put("msg", "privacyClick");
        } catch (JSONException unused) {
        }
        this.mFeedsProd.a(jSONObjectQ);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void unionLogoClick() {
        if (this.mFeedsProd == null || this.mUriUtils == null) {
            return;
        }
        String strC = this.mUriUtils.c("http://union.baidu.com/");
        JSONObject jSONObjectQ = this.mAdInstanceInfo.Q();
        try {
            jSONObjectQ.put("unionUrl", strC);
            jSONObjectQ.put("msg", "unionLogoClick");
        } catch (Throwable unused) {
        }
        this.mFeedsProd.a(jSONObjectQ);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void recordImpression(View view) {
        if (this.mFeedsProd == null || this.mAdInstanceInfo == null) {
            return;
        }
        this.mFeedsProd.a(view, this.mAdInstanceInfo.Q());
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void handleClick(View view) {
        handleClick(view, -1);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void handleClick(View view, int i) {
        handleClick(view, i, false);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void handleClick(View view, boolean z) {
        handleClick(view, -1, z);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void handleClick(View view, int i, boolean z) {
        if (this.mFeedsProd == null || this.mAdInstanceInfo == null) {
            return;
        }
        JSONObject jSONObjectQ = this.mAdInstanceInfo.Q();
        try {
            jSONObjectQ.put(NotificationCompat.CATEGORY_PROGRESS, i);
            jSONObjectQ.put(SplashAd.KEY_POPDIALOG_DOWNLOAD, z);
            jSONObjectQ.put("isDownloadApp", this.isDownloadApp);
        } catch (Throwable unused) {
        }
        this.mFeedsProd.b(view, jSONObjectQ);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getPublisher() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.A() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getAppPrivacyLink() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.B() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getAppPermissionLink() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.C() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getAppVersion() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.z() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getMarketingPendant() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.J() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getActButtonString() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.K() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getVideoUrl() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.n() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public int getDuration() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.v();
        }
        return 0;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public NativeResponse.MaterialType getMaterialType() {
        if (this.mAdInstanceInfo == null) {
            return NativeResponse.MaterialType.NORMAL;
        }
        if ("video".equals(this.mAdInstanceInfo.w())) {
            return NativeResponse.MaterialType.VIDEO;
        }
        if (a.f.equals(this.mAdInstanceInfo.w())) {
            return NativeResponse.MaterialType.HTML;
        }
        return NativeResponse.MaterialType.NORMAL;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getHtmlSnippet() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.o() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public WebView getWebView() {
        if (this.mFeedsProd != null) {
            return (WebView) this.mFeedsProd.v();
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getAdMaterialType() {
        if (this.mAdInstanceInfo == null) {
            return NativeResponse.MaterialType.NORMAL.getValue();
        }
        if ("video".equals(this.mAdInstanceInfo.w())) {
            return NativeResponse.MaterialType.VIDEO.getValue();
        }
        if (a.f.equals(this.mAdInstanceInfo.w())) {
            return NativeResponse.MaterialType.HTML.getValue();
        }
        return NativeResponse.MaterialType.NORMAL.getValue();
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public int getStyleType() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.u();
        }
        return 0;
    }

    private int getActionType() {
        return this.mAdInstanceInfo.p();
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public int getContainerWidth() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.r();
        }
        return 0;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public int getContainerHeight() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.s();
        }
        return 0;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public int getContainerSizeType() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.t();
        }
        return 0;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getECPMLevel() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.y() : "";
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void biddingSuccess(String str) {
        if (this.mAdInstanceInfo == null || this.mFeedsProd == null) {
            return;
        }
        this.mFeedsProd.a(this.mAdInstanceInfo.F(), true, str);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void biddingFail(String str) {
        if (this.mAdInstanceInfo == null || this.mFeedsProd == null) {
            return;
        }
        this.mFeedsProd.a(this.mAdInstanceInfo.F(), false, str);
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void registerViewForInteraction(View view, NativeResponse.AdInteractionListener adInteractionListener) {
        recordImpression(view);
        this.mAdInteractionListener = adInteractionListener;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void registerViewForInteraction(View view, List<View> list, List<View> list2, NativeResponse.AdInteractionListener adInteractionListener) {
        this.mAdInteractionListener = adInteractionListener;
        if (this.mFeedsProd != null) {
            try {
                HashMap map = new HashMap();
                map.put("adView", view);
                map.put("clickViews", list);
                map.put("creativeViews", list2);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("msg", "registerViewForInteraction");
                jSONObject.put("uniqueId", getUniqueId());
                jSONObject.put("isDownloadApp", this.isDownloadApp);
                this.mFeedsProd.a(jSONObject, map);
            } catch (Throwable th) {
                bb.a().c(TAG, "registerViewForInteraction failed: " + th.getMessage());
            }
        }
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public void setAdPrivacyListener(NativeResponse.AdPrivacyListener adPrivacyListener) {
        this.mAdPrivacyListener = adPrivacyListener;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public int getMainPicWidth() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.e();
        }
        return 0;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public int getMainPicHeight() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.f();
        }
        return 0;
    }

    @Override // com.baidu.mobads.sdk.api.NativeResponse
    public String getBrandName() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.g() : "";
    }

    public JSONObject getExtraParams() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.G();
        }
        return null;
    }

    public void preloadVideoMaterial() throws JSONException {
        if (this.mFeedsProd == null || this.mAdInstanceInfo == null) {
            return;
        }
        JSONObject jSONObjectQ = this.mAdInstanceInfo.Q();
        try {
            jSONObjectQ.put("msg", "preloadVideoMaterial");
        } catch (JSONException unused) {
        }
        this.mFeedsProd.a(jSONObjectQ);
    }

    public String getUniqueId() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.F() : "";
    }

    public void onAdClick() {
        if (this.mAdInteractionListener != null) {
            this.mAdInteractionListener.onAdClick();
        }
    }

    public void onADExposed() {
        if (this.mAdInteractionListener != null) {
            this.mAdInteractionListener.onADExposed();
        }
    }

    public void onADExposureFailed(int i) {
        if (this.mAdInteractionListener != null) {
            this.mAdInteractionListener.onADExposureFailed(i);
        }
    }

    public void onADStatusChanged() {
        if (this.mAdInteractionListener != null) {
            this.mAdInteractionListener.onADStatusChanged();
        }
    }

    public void onADPermissionShow(boolean z) {
        if (this.mAdPrivacyListener != null) {
            if (z) {
                this.mAdPrivacyListener.onADPermissionShow();
            } else {
                this.mAdPrivacyListener.onADPermissionClose();
            }
        }
    }

    public void onAdDownloadWindow(boolean z) {
        if (this.mAdPrivacyListener == null || !(this.mAdPrivacyListener instanceof NativeResponse.AdDownloadWindowListener)) {
            return;
        }
        if (z) {
            ((NativeResponse.AdDownloadWindowListener) this.mAdPrivacyListener).adDownloadWindowShow();
        } else {
            ((NativeResponse.AdDownloadWindowListener) this.mAdPrivacyListener).adDownloadWindowClose();
        }
    }

    public void onADPrivacyClick() {
        if (this.mAdPrivacyListener != null) {
            this.mAdPrivacyListener.onADPrivacyClick();
        }
    }

    public void setAdDislikeListener(NativeResponse.AdDislikeListener adDislikeListener) {
        this.mAdDislikeListener = adDislikeListener;
    }

    public NativeResponse.AdDislikeListener getAdDislikeListener() {
        return this.mAdDislikeListener;
    }

    public void onAdUnionClick() {
        if (this.mAdInteractionListener != null) {
            this.mAdInteractionListener.onAdUnionClick();
        }
    }

    public String getMarketingICONUrl() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.H() : "";
    }

    public String getMarketingDesc() {
        return this.mAdInstanceInfo != null ? this.mAdInstanceInfo.I() : "";
    }

    public int getBtnStyleType() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.L();
        }
        return 0;
    }

    public List<String> getBtnStyleColors() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.M();
        }
        return null;
    }

    private IAdInterListener getAdInterListener() {
        if (this.mFeedsProd != null) {
            return this.mFeedsProd.j;
        }
        return null;
    }

    public List<String> getThirdTrackers(String str) {
        if (this.mAdInstanceInfo == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObjectN = this.mAdInstanceInfo.N();
            if (jSONObjectN != null) {
                Iterator<String> itKeys = jSONObjectN.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (next.equals(str)) {
                        JSONArray jSONArrayOptJSONArray = jSONObjectN.optJSONArray(next);
                        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                            arrayList.add(jSONArrayOptJSONArray.optString(i));
                        }
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public int isRegionClick() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.O();
        }
        return 2;
    }

    public int isShowDialog() {
        if (this.mAdInstanceInfo != null) {
            return this.mAdInstanceInfo.P();
        }
        return 2;
    }

    private String getProd() {
        return this.mFeedsProd != null ? this.mFeedsProd.f() : "";
    }
}
