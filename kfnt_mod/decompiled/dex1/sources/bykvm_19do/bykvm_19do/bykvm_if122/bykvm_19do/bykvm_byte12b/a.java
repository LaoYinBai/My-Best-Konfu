package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_byte12b;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.i;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.api.v2.GMAdDislike;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomTTBaseAd;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdAppInfo;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMVideoListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import com.bytedance.msdk.base.TTBaseAd;
import com.bytedance.msdk.core.corelogic.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements GMNativeAd, GMVideoListener {

    /* renamed from: a, reason: collision with root package name */
    private TTBaseAd f1803a;

    /* renamed from: b, reason: collision with root package name */
    private AdSlot f1804b;

    /* renamed from: c, reason: collision with root package name */
    private GMNativeAdListener f1805c;
    private c d;
    private GMVideoListener e;

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_byte12b.a$a, reason: collision with other inner class name */
    class C0060a implements GMNativeExpressAdListener {
        C0060a() {
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener
        public void onAdClick() {
            if (a.this.f1805c != null) {
                a.this.f1805c.onAdClick();
            }
            g.c(a.this.f1803a, a.this.f1804b);
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener
        public void onAdShow() {
            if (a.this.f1805c != null) {
                a.this.f1805c.onAdShow();
            }
            if (a.this.f1804b != null && a.this.f1803a != null) {
                i.b().a(a.this.f1804b.getAdUnitId() + "");
                i.b().j(a.this.f1804b.getAdUnitId() + "");
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(a.this.f1804b.getAdUnitId(), TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + a.this.f1803a.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(a.this.f1803a.getAdNetworkPlatformId()));
            }
            g.e(a.this.f1803a, a.this.f1804b);
            f.a(a.this.f1804b != null ? a.this.f1804b.getAdUnitId() : "", a.this.f1803a);
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener
        public void onRenderFail(View view, String str, int i) {
            if (a.this.f1805c instanceof GMNativeExpressAdListener) {
                ((GMNativeExpressAdListener) a.this.f1805c).onRenderFail(view, str, i);
            }
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener
        public void onRenderSuccess(float f, float f2) {
            if (a.this.f1805c instanceof GMNativeExpressAdListener) {
                ((GMNativeExpressAdListener) a.this.f1805c).onRenderSuccess(f, f2);
            }
        }
    }

    class b implements GMNativeAdListener {
        b() {
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener
        public void onAdClick() {
            if (a.this.f1805c != null) {
                a.this.f1805c.onAdClick();
            }
            g.c(a.this.f1803a, a.this.f1804b);
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAdListener
        public void onAdShow() {
            if (a.this.f1805c != null) {
                a.this.f1805c.onAdShow();
            }
            if (a.this.f1804b != null && a.this.f1803a != null) {
                i.b().a(a.this.f1804b.getAdUnitId() + "");
                i.b().j(a.this.f1804b.getAdUnitId() + "");
                Logger.d("TTMediationSDK", TTLogUtil.getTagThirdLevelByEvent(a.this.f1804b.getAdUnitId(), TTLogUtil.TAG_EVENT_SHOW_LISTEN) + "adSlotId：" + a.this.f1803a.getAdNetworkSlotId() + "，广告类型：" + com.bytedance.msdk.base.a.a(a.this.f1803a.getAdNetworkPlatformId()));
            }
            g.e(a.this.f1803a, a.this.f1804b);
            f.a(a.this.f1804b != null ? a.this.f1804b.getAdUnitId() : "", a.this.f1803a);
        }
    }

    public interface c {
        void a();
    }

    public a(TTBaseAd tTBaseAd, AdSlot adSlot, c cVar) {
        this.f1803a = tTBaseAd;
        this.f1804b = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.b.getShallowCopy(adSlot);
        this.d = cVar;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public boolean canAdReuse() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.canAdReuse();
        }
        return false;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void destroy() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            try {
                tTBaseAd.onDestroy();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public String getActionText() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getActionText();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public int getAdImageMode() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getImageMode();
        }
        return -1;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    @Deprecated
    public View getAdLogoView() {
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public int getAdNetworkPlatformId() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getAdNetworkPlatformId();
        }
        return -2;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public String getAdNetworkRitId() {
        TTBaseAd tTBaseAd = this.f1803a;
        return tTBaseAd != null ? tTBaseAd.getAdNetworkSlotId() : "-2";
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public String getDescription() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getAdDescription();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public GMAdDislike getDislikeDialog(Activity activity) {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getDislikeDialog(activity);
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public View getExpressView() throws JSONException {
        g.a(this.f1804b);
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd == null || !tTBaseAd.isExpressAd()) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(this.f1803a);
            g.a(arrayList, this.f1804b);
            return null;
        }
        c cVar = this.d;
        if (cVar != null) {
            cVar.a();
        }
        g.d(this.f1803a, this.f1804b);
        return this.f1803a.getAdView();
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public String getIconUrl() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getIconUrl();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public int getImageHeight() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getImageHeight();
        }
        return 0;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public List<String> getImageList() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getImages();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public String getImageUrl() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getImageUrl();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public int getImageWidth() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getImageWidth();
        }
        return 0;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public int getInteractionType() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getInteractionType();
        }
        return -1;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public Map<String, Object> getMediaExtraInfo() {
        TTBaseAd tTBaseAd = this.f1803a;
        return tTBaseAd instanceof GMCustomTTBaseAd ? ((GMCustomTTBaseAd) tTBaseAd).getMediaExtraInfo() : new HashMap();
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public GMNativeAdAppInfo getNativeAdAppInfo() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd == null || !tTBaseAd.hasAppInfo()) {
            return null;
        }
        GMNativeAdAppInfo gMNativeAdAppInfo = new GMNativeAdAppInfo();
        gMNativeAdAppInfo.setAppName(this.f1803a.getAppName());
        gMNativeAdAppInfo.setAuthorName(this.f1803a.getAuthorName());
        gMNativeAdAppInfo.setPackageSizeBytes(this.f1803a.getPackageSizeBytes());
        gMNativeAdAppInfo.setPermissionsUrl(this.f1803a.getPermissionsUrl());
        gMNativeAdAppInfo.setPrivacyAgreement(this.f1803a.getPrivacyAgreement());
        gMNativeAdAppInfo.setVersionName(this.f1803a.getVersionName());
        gMNativeAdAppInfo.setPermissionsMap(this.f1803a.getPermissionsMap());
        gMNativeAdAppInfo.setAppInfoExtra(this.f1803a.getAppInfoExtra());
        return gMNativeAdAppInfo;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public String getPackageName() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getPackageName();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public String getPreEcpm() {
        return this.f1803a == null ? "-2" : (bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.f().p() || !(this.f1803a.isServerBiddingAd() || this.f1803a.isClientBiddingAd() || this.f1803a.isMultiBiddingAd())) ? this.f1803a.getNetWorkPlatFormCpm() : "-3";
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public int getSdkNumType() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getAdNetworkPlatformId();
        }
        return -1;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public String getSource() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getSource();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public double getStarRating() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getStarRating();
        }
        return 0.0d;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public TTBaseAd getTTBaseAd() {
        return this.f1803a;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public String getTitle() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getAdTitle();
        }
        return null;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public int getVideoHeight() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getVideoHeight();
        }
        return 0;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public int getVideoWidth() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.getVideoWidth();
        }
        return 0;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public boolean hasDislike() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.hasDislike();
        }
        return false;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public boolean isExpressAd() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.isExpressAd();
        }
        return false;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public boolean isHasShown() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.isHasShown();
        }
        return true;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public boolean isServerBidding() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            return tTBaseAd.isServerBiddingAd();
        }
        return false;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void onPause() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            tTBaseAd.onPause();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMVideoListener
    public void onVideoCompleted() {
        GMVideoListener gMVideoListener = this.e;
        if (gMVideoListener != null) {
            gMVideoListener.onVideoCompleted();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMVideoListener
    public void onVideoError(AdError adError) {
        g.a(this.f1803a, this.f1804b, (AdError) null, 2);
        GMVideoListener gMVideoListener = this.e;
        if (gMVideoListener != null) {
            gMVideoListener.onVideoError(adError);
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMVideoListener
    public void onVideoPause() {
        GMVideoListener gMVideoListener = this.e;
        if (gMVideoListener != null) {
            gMVideoListener.onVideoPause();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMVideoListener
    public void onVideoResume() {
        GMVideoListener gMVideoListener = this.e;
        if (gMVideoListener != null) {
            gMVideoListener.onVideoResume();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMVideoListener
    public void onVideoStart() {
        GMVideoListener gMVideoListener = this.e;
        if (gMVideoListener != null) {
            gMVideoListener.onVideoStart();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void registerView(@NonNull ViewGroup viewGroup, @NonNull List<View> list, @Nullable List<View> list2, GMViewBinder gMViewBinder) throws JSONException {
        g.a(this.f1804b);
        if (this.f1803a == null) {
            g.a((List<TTBaseAd>) null, this.f1804b);
            return;
        }
        a();
        this.f1803a.registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
        c cVar = this.d;
        if (cVar != null) {
            cVar.a();
        }
        g.d(this.f1803a, this.f1804b);
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void render() {
        if (this.f1803a != null) {
            a();
            this.f1803a.render();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void resume() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            tTBaseAd.onResume();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void setDislikeCallback(Activity activity, GMDislikeCallback gMDislikeCallback) {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            tTBaseAd.setDislikeCallback(activity, gMDislikeCallback);
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void setNativeAdListener(GMNativeAdListener gMNativeAdListener) {
        this.f1805c = gMNativeAdListener;
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void setVideoListener(GMVideoListener gMVideoListener) {
        this.e = gMVideoListener;
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            tTBaseAd.setTTVideoListener(this);
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void unregisterView() {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            tTBaseAd.unregisterView();
        }
    }

    public void a(boolean z) {
        TTBaseAd tTBaseAd = this.f1803a;
        if (tTBaseAd != null) {
            tTBaseAd.setHasShown(z);
        }
    }

    private void a() {
        if (this.f1803a.isExpressAd()) {
            this.f1803a.setTTNativeAdListener(new C0060a());
        } else {
            this.f1803a.setTTNativeAdListener(new b());
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeAd
    public void registerView(@NonNull Activity activity, @NonNull ViewGroup viewGroup, @NonNull List<View> list, @Nullable List<View> list2, GMViewBinder gMViewBinder) throws JSONException {
        g.a(this.f1804b);
        if (this.f1803a != null) {
            a();
            this.f1803a.registerViewForInteraction(activity, viewGroup, list, list2, gMViewBinder);
            c cVar = this.d;
            if (cVar != null) {
                cVar.a();
            }
            g.d(this.f1803a, this.f1804b);
            return;
        }
        g.a((List<TTBaseAd>) null, this.f1804b);
    }
}
