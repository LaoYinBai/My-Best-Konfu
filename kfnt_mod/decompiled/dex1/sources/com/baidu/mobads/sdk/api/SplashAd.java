package com.baidu.mobads.sdk.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.internal.a;
import com.baidu.mobads.sdk.internal.ak;
import com.baidu.mobads.sdk.internal.ay;
import com.baidu.mobads.sdk.internal.bb;
import com.baidu.mobads.sdk.internal.bz;
import com.baidu.mobads.sdk.internal.cl;
import com.baidu.mobads.sdk.internal.cy;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SplashAd {
    private static final int BOTTOM_VIEW_ID = 4097;
    public static final String KEY_DISPLAY_CLICK_REGION = "display_region";
    public static final String KEY_DISPLAY_DOWNLOADINFO = "displayDownloadInfo";
    public static final String KEY_FETCHAD = "fetchAd";
    public static final String KEY_LIMIT_REGION_CLICK = "region_click";
    public static final String KEY_LOAD_AFTER_CACHE_END = "loadAfterCacheEnd";
    public static final String KEY_POPDIALOG_DOWNLOAD = "use_dialog_frame";
    public static final String KEY_PREFER_FULLSCREEN = "prefer_fullscreen";
    public static final String KEY_SHAKE_LOGO_SIZE = "shake_logo_size";
    public static final String KEY_TIMEOUT = "timeout";
    public static final String KEY_USE_ADAPTIVE_AD = "adaptive_ad";
    private static final int RT_SPLASH_LOAD_AD_TIMEOUT = 4200;
    private String mAdPlaceId;
    private cy mAdProd;
    private String mAppSid;
    private Context mContext;
    private Boolean mDisplayClickRegion;
    private boolean mDisplayDownInfo;
    private SplashAdDownloadDialogListener mDownloadDialogListener;
    private boolean mFetchAd;
    private boolean mFetchNotShow;
    private boolean mIsAdaptiveSplashAd;
    private Boolean mLimitRegionClick;
    private SplashAdListener mListener;
    private RequestParameters mParameter;
    private Boolean mPopDialogIfDL;
    private int mShakeLogoSize;
    private int mTimeout;
    private int mTipStyle;
    private ViewGroup mViewParent;
    private bz splashAdView;

    public interface OnFinishListener {
        void onFinishActivity();
    }

    public interface SplashAdDownloadDialogListener {
        void adDownloadWindowClose();

        void adDownloadWindowShow();

        void onADPermissionClose();

        void onADPermissionShow();

        void onADPrivacyLpClose();

        void onADPrivacyLpShow();
    }

    public interface SplashFocusAdListener {
        void onAdClick();

        void onAdClose();

        void onAdIconShow();

        void onLpClosed();
    }

    public SplashAd(Context context, String str, SplashAdListener splashAdListener) {
        this(context, str, null, splashAdListener);
    }

    public SplashAd(Context context, String str, RequestParameters requestParameters, SplashAdListener splashAdListener) {
        this.mTipStyle = 4;
        this.mFetchAd = true;
        this.mFetchNotShow = false;
        this.mDisplayDownInfo = true;
        this.mPopDialogIfDL = false;
        this.mLimitRegionClick = false;
        this.mDisplayClickRegion = false;
        this.mTimeout = RT_SPLASH_LOAD_AD_TIMEOUT;
        this.mShakeLogoSize = 60;
        this.mIsAdaptiveSplashAd = false;
        this.mListener = new SplashInteractionListener() { // from class: com.baidu.mobads.sdk.api.SplashAd.1
            @Override // com.baidu.mobads.sdk.api.SplashAdListener
            public void onADLoaded() {
            }

            @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
            public void onAdCacheFailed() {
            }

            @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
            public void onAdCacheSuccess() {
            }

            @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
            public void onAdClick() {
            }

            @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
            public void onAdDismissed() {
            }

            @Override // com.baidu.mobads.sdk.api.SplashAdListener
            public void onAdFailed(String str2) {
            }

            @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
            public void onAdPresent() {
            }

            @Override // com.baidu.mobads.sdk.api.SplashInteractionListener
            public void onLpClosed() {
            }
        };
        this.mContext = context;
        this.mAdPlaceId = str;
        if (splashAdListener != null) {
            this.mListener = splashAdListener;
        }
        if (TextUtils.isEmpty(str)) {
            this.mListener.onAdFailed("请您输入正确的广告位ID");
            return;
        }
        this.mParameter = requestParameters;
        if (this.mParameter == null || this.mParameter.getExtras() == null) {
            return;
        }
        String str2 = this.mParameter.getExtras().get(KEY_FETCHAD);
        if (!TextUtils.isEmpty(str2)) {
            this.mFetchAd = Boolean.parseBoolean(str2);
        }
        String str3 = this.mParameter.getExtras().get(KEY_DISPLAY_DOWNLOADINFO);
        if (!TextUtils.isEmpty(str3)) {
            this.mDisplayDownInfo = Boolean.parseBoolean(str3);
        }
        String str4 = this.mParameter.getExtras().get(KEY_POPDIALOG_DOWNLOAD);
        if (!TextUtils.isEmpty(str4)) {
            this.mPopDialogIfDL = Boolean.valueOf(str4);
        }
        String str5 = this.mParameter.getExtras().get(KEY_LIMIT_REGION_CLICK);
        if (!TextUtils.isEmpty(str5)) {
            this.mLimitRegionClick = Boolean.valueOf(str5);
        }
        String str6 = this.mParameter.getExtras().get(KEY_SHAKE_LOGO_SIZE);
        if (!TextUtils.isEmpty(str6)) {
            this.mShakeLogoSize = Integer.parseInt(str6);
        }
        String str7 = this.mParameter.getExtras().get(KEY_DISPLAY_CLICK_REGION);
        if (!TextUtils.isEmpty(str7)) {
            this.mDisplayClickRegion = Boolean.valueOf(str7);
        }
        String str8 = this.mParameter.getExtras().get("timeout");
        if (!TextUtils.isEmpty(str8)) {
            this.mTimeout = Integer.parseInt(str8);
        }
        String str9 = this.mParameter.getExtras().get(KEY_USE_ADAPTIVE_AD);
        if (TextUtils.isEmpty(str9)) {
            return;
        }
        this.mIsAdaptiveSplashAd = Boolean.parseBoolean(str9);
    }

    public final void load() {
        this.splashAdView = new bz(this.mContext);
        this.splashAdView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (this.mAdProd != null) {
            this.mAdProd.r();
            this.mAdProd = null;
        }
        float fE = ak.e(this.mContext);
        Rect rectA = ak.a(this.mContext);
        int iWidth = rectA.width();
        int iHeight = rectA.height();
        if (this.mParameter != null && this.mParameter.isCustomSize()) {
            if (this.mParameter.getWidth() > 0) {
                iWidth = (int) (this.mParameter.getWidth() * fE);
            }
            if (this.mParameter.getHeight() > 0) {
                iHeight = (int) (this.mParameter.getHeight() * fE);
            }
        }
        int i = iHeight;
        int i2 = iWidth;
        if (i2 < 200.0f * fE || i < fE * 150.0f) {
            bb.a().c(cl.a().a(ay.SHOW_STANDARD_UNFIT, "开屏显示区域太小,宽度至少200dp,高度至少150dp"));
            if (this.mListener == null || !(this.mListener instanceof SplashInteractionListener)) {
                return;
            }
            ((SplashInteractionListener) this.mListener).onAdDismissed();
            return;
        }
        this.mAdProd = new cy(this.mContext, this.splashAdView, this.mAdPlaceId, i2, i, this.mTipStyle, this.mTimeout, this.mDisplayDownInfo, this.mPopDialogIfDL.booleanValue(), this.mDisplayClickRegion.booleanValue(), this.mLimitRegionClick.booleanValue());
        this.mAdProd.a(this.mShakeLogoSize);
        this.mAdProd.f(this.mAppSid);
        this.mAdProd.o = true;
        if (this.mParameter != null) {
            this.mAdProd.a(this.mParameter);
        }
        this.mAdProd.a(this.mListener);
        this.mFetchNotShow = true;
        this.mAdProd.a(this.mDownloadDialogListener);
        this.mAdProd.b_();
    }

    public String getBiddingToken() {
        this.splashAdView = new bz(this.mContext);
        this.splashAdView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (this.mAdProd != null) {
            this.mAdProd.r();
            this.mAdProd = null;
        }
        float fE = ak.e(this.mContext);
        Rect rectA = ak.a(this.mContext);
        int iWidth = rectA.width();
        int iHeight = rectA.height();
        if (this.mParameter != null && this.mParameter.isCustomSize()) {
            if (this.mParameter.getWidth() > 0) {
                iWidth = (int) (this.mParameter.getWidth() * fE);
            }
            if (this.mParameter.getHeight() > 0) {
                iHeight = (int) (this.mParameter.getHeight() * fE);
            }
        }
        int i = iHeight;
        int i2 = iWidth;
        if (i2 < 200.0f * fE || i < fE * 150.0f) {
            bb.a().c(cl.a().a(ay.SHOW_STANDARD_UNFIT, "开屏显示区域太小,宽度至少200dp,高度至少150dp"));
            if (this.mListener != null && (this.mListener instanceof SplashInteractionListener)) {
                ((SplashInteractionListener) this.mListener).onAdDismissed();
            }
            return null;
        }
        this.mAdProd = new cy(this.mContext, this.splashAdView, this.mAdPlaceId, i2, i, this.mTipStyle, this.mTimeout, this.mDisplayDownInfo, this.mPopDialogIfDL.booleanValue(), this.mDisplayClickRegion.booleanValue(), this.mLimitRegionClick.booleanValue());
        this.mAdProd.a(this.mShakeLogoSize);
        this.mAdProd.f(this.mAppSid);
        this.mAdProd.o = true;
        if (this.mParameter != null) {
            this.mAdProd.a(this.mParameter);
        }
        this.mAdProd.a(this.mListener);
        this.mFetchNotShow = true;
        this.mAdProd.a(this.mDownloadDialogListener);
        return this.mAdProd.m();
    }

    public void setBiddingData(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.b(str);
        }
    }

    private final void setAppLogoId(int i) {
        setAppLogo(Integer.valueOf(i));
    }

    private final void setAppLogoData(byte[] bArr) {
        setAppLogo(bArr);
    }

    private void setAppLogo(Object obj) {
        if (this.mAdProd != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("event_type", "splash_logo");
                HashMap map = new HashMap();
                map.put("appLogo", obj);
                this.mAdProd.a(jSONObject, map);
            } catch (Throwable th) {
                bb.a().d(th);
            }
        }
    }

    public final void show(ViewGroup viewGroup) {
        showWithBottomView(viewGroup, null);
    }

    private final void showWithBottomView(ViewGroup viewGroup, View view) {
        this.mViewParent = viewGroup;
        if (viewGroup == null) {
            if (this.mListener != null) {
                this.mListener.onAdFailed("传入容器不可以为空");
                return;
            }
            return;
        }
        if (this.mIsAdaptiveSplashAd && view == null) {
            if (this.mListener != null) {
                this.mListener.onAdFailed("使用自适应开屏广告能力, 需要使用showWithBottomView方法并传入合适尺寸的底部logo");
                return;
            }
            return;
        }
        if (this.splashAdView != null && this.mAdProd != null) {
            if (this.splashAdView.getParent() != null) {
                this.mAdProd.r();
                callAdFailed("展现失败，请重新load");
                return;
            }
            if (view != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(12);
                view.setId(4097);
                this.splashAdView.addView(view, layoutParams);
            }
            this.splashAdView.a(new bz.a() { // from class: com.baidu.mobads.sdk.api.SplashAd.2
                @Override // com.baidu.mobads.sdk.internal.bz.a
                public boolean onKeyDown(int i, KeyEvent keyEvent) {
                    return false;
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onLayoutComplete(int i, int i2) {
                    if (SplashAd.this.mFetchNotShow) {
                        if (SplashAd.this.mAdProd != null) {
                            SplashAd.this.mAdProd.o = false;
                            SplashAd.this.mFetchNotShow = false;
                            SplashAd.this.mAdProd.f();
                            return;
                        }
                        SplashAd.this.callAdFailed("展现失败，请检查splashAd参数是否正确");
                    }
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onAttachedToWindow() {
                    if (SplashAd.this.mAdProd != null) {
                        SplashAd.this.mAdProd.o();
                    }
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onDetachedFromWindow() {
                    if (SplashAd.this.mAdProd != null) {
                        SplashAd.this.mAdProd.p();
                    }
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onWindowVisibilityChanged(int i) {
                    if (SplashAd.this.mAdProd != null) {
                        SplashAd.this.mAdProd.b(i);
                    }
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onWindowFocusChanged(boolean z) {
                    if (SplashAd.this.mAdProd != null) {
                        SplashAd.this.mAdProd.a(z);
                    }
                }
            });
            this.mViewParent.addView(this.splashAdView);
            return;
        }
        if (this.mAdProd != null) {
            this.mAdProd.r();
        }
        callAdFailed("展现失败，请检查splashAd参数是否正确");
    }

    public boolean isReady() {
        if (this.mAdProd == null || this.mAdProd.j == null) {
            return false;
        }
        return this.mAdProd.j.isAdReady();
    }

    public String getECPMLevel() {
        a aVarG;
        return (this.mAdProd == null || (aVarG = this.mAdProd.g()) == null) ? "" : aVarG.y();
    }

    public void biddingSuccess(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.a(true, str);
        }
    }

    public void biddingFail(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.a(false, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callAdFailed(String str) {
        if (this.mListener != null) {
            this.mListener.onAdFailed(str);
        }
    }

    public void setListener(SplashAdListener splashAdListener) {
        this.mListener = splashAdListener;
        if (this.mAdProd != null) {
            this.mAdProd.a(this.mListener);
        }
    }

    public void setAppSid(String str) {
        this.mAppSid = str;
    }

    public void destroy() {
        if (this.mAdProd != null) {
            this.mAdProd.d();
        }
        this.mListener = null;
    }

    private void addZeroPxSurfaceViewAvoidBlink(ViewGroup viewGroup, Context context) {
        try {
            viewGroup.addView(new SurfaceView(context), new RelativeLayout.LayoutParams(0, 0));
        } catch (Exception e) {
            bb.a().a(e);
        }
    }

    public void loadAndShow(ViewGroup viewGroup) {
        if (viewGroup == null) {
            if (this.mListener != null) {
                this.mListener.onAdFailed("传入容器不可以为空");
            }
        } else if (this.mIsAdaptiveSplashAd) {
            if (this.mListener != null) {
                this.mListener.onAdFailed("使用自适应开屏广告能力, 需要使用showWithBottomView方法并传入合适尺寸的底部logo");
            }
        } else {
            addZeroPxSurfaceViewAvoidBlink(viewGroup, this.mContext);
            final bz bzVar = new bz(this.mContext);
            bzVar.a(new bz.a() { // from class: com.baidu.mobads.sdk.api.SplashAd.3
                @Override // com.baidu.mobads.sdk.internal.bz.a
                public boolean onKeyDown(int i, KeyEvent keyEvent) {
                    return false;
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onLayoutComplete(int i, int i2) {
                    if (SplashAd.this.mAdProd != null) {
                        return;
                    }
                    float fE = ak.e(SplashAd.this.mContext);
                    if (SplashAd.this.mParameter != null && SplashAd.this.mParameter.isCustomSize()) {
                        if (SplashAd.this.mParameter.getWidth() > 0) {
                            i = (int) (SplashAd.this.mParameter.getWidth() * fE);
                        }
                        if (SplashAd.this.mParameter.getHeight() > 0) {
                            i2 = (int) (SplashAd.this.mParameter.getHeight() * fE);
                        }
                    }
                    int i3 = i;
                    int i4 = i2;
                    if (i3 < 200.0f * fE || i4 < fE * 150.0f) {
                        bb.a().c(cl.a().a(ay.SHOW_STANDARD_UNFIT, "开屏显示区域太小,宽度至少200dp,高度至少150dp"));
                        if (SplashAd.this.mListener == null || !(SplashAd.this.mListener instanceof SplashInteractionListener)) {
                            return;
                        }
                        ((SplashInteractionListener) SplashAd.this.mListener).onAdDismissed();
                        return;
                    }
                    SplashAd.this.mAdProd = new cy(SplashAd.this.mContext, bzVar, SplashAd.this.mAdPlaceId, i3, i4, SplashAd.this.mTipStyle, SplashAd.this.mTimeout, SplashAd.this.mDisplayDownInfo, SplashAd.this.mPopDialogIfDL.booleanValue(), SplashAd.this.mDisplayClickRegion.booleanValue(), SplashAd.this.mLimitRegionClick.booleanValue());
                    SplashAd.this.mAdProd.a(SplashAd.this.mShakeLogoSize);
                    SplashAd.this.mAdProd.f(SplashAd.this.mAppSid);
                    SplashAd.this.mAdProd.a(SplashAd.this.mListener);
                    if (SplashAd.this.mParameter != null) {
                        SplashAd.this.mAdProd.a(SplashAd.this.mParameter);
                    }
                    SplashAd.this.mAdProd.o = false;
                    SplashAd.this.mAdProd.a(SplashAd.this.mDownloadDialogListener);
                    SplashAd.this.mAdProd.b_();
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onAttachedToWindow() {
                    if (SplashAd.this.mAdProd != null) {
                        SplashAd.this.mAdProd.o();
                    }
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onDetachedFromWindow() {
                    if (SplashAd.this.mAdProd != null) {
                        SplashAd.this.mAdProd.p();
                    }
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onWindowVisibilityChanged(int i) {
                    if (SplashAd.this.mAdProd != null) {
                        SplashAd.this.mAdProd.b(i);
                    }
                }

                @Override // com.baidu.mobads.sdk.internal.bz.a
                public void onWindowFocusChanged(boolean z) {
                    if (SplashAd.this.mAdProd != null) {
                        SplashAd.this.mAdProd.a(z);
                    }
                }
            });
            bzVar.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            viewGroup.addView(bzVar);
        }
    }

    public void finishAndJump(Intent intent) {
        finishAndJump(intent, null);
    }

    public void finishAndJump(Intent intent, OnFinishListener onFinishListener) {
        if (this.mAdProd != null) {
            this.mAdProd.a(intent, onFinishListener);
        }
    }

    public static void registerEnterTransition(Activity activity, SplashFocusAdListener splashFocusAdListener) {
        cy.a(activity, (JSONObject) null, splashFocusAdListener);
    }

    public static void registerEnterTransition(Activity activity, SplashFocusParams splashFocusParams, SplashFocusAdListener splashFocusAdListener) {
        cy.a(activity, splashFocusParams.getFocusParams(), splashFocusAdListener);
    }

    public static void registerEnterTransition(Activity activity, int i, int i2, SplashFocusAdListener splashFocusAdListener) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("right_margin", i);
            jSONObject.put("bottom_margin", i2);
        } catch (JSONException e) {
            bb.a().a(e);
        }
        cy.a(activity, jSONObject, splashFocusAdListener);
    }

    @Deprecated
    public static void registerEnterTransition(Activity activity, int i, int i2, int i3, SplashFocusAdListener splashFocusAdListener) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("right_margin", i);
            jSONObject.put("bottom_margin", i2);
            jSONObject.put("anim_offset_y", i3);
        } catch (JSONException e) {
            bb.a().a(e);
        }
        cy.a(activity, jSONObject, splashFocusAdListener);
    }

    public void setDownloadDialogListener(SplashAdDownloadDialogListener splashAdDownloadDialogListener) {
        this.mDownloadDialogListener = splashAdDownloadDialogListener;
        if (this.mAdProd != null) {
            this.mAdProd.a(splashAdDownloadDialogListener);
        }
    }
}
