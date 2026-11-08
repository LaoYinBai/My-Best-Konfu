package com.sigmob.windad.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.nativead.g;
import com.sigmob.sdk.nativead.k;
import com.sigmob.sdk.nativead.n;
import com.sigmob.sdk.nativead.s;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdError;
import com.sigmob.windad.base.WindAdAdapter;
import com.sigmob.windad.base.WindAdNativeConnector;
import com.sigmob.windad.natives.NativeADData;
import com.sigmob.windad.natives.NativeADEventListener;
import com.sigmob.windad.natives.WindNativeAdContainer;
import com.sigmob.windad.natives.WindNativeAdRender;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigmobNativeAdData implements NativeADData {
    private s a;
    private NativeADEventListener b;
    private View c;
    private WindAdAdapter d;
    private ADStrategy e;
    private WindAdNativeConnector f;

    public SigmobNativeAdData(s sVar, WindAdAdapter windAdAdapter, ADStrategy aDStrategy, WindAdNativeConnector windAdNativeConnector) {
        this.a = sVar;
        this.d = windAdAdapter;
        this.e = aDStrategy;
        this.f = windAdNativeConnector;
        if (this.e == null || this.a == null) {
            return;
        }
        this.e.setOffer_id(String.valueOf(this.a.hashCode()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setViewSize(View view, int i, int i2) {
        LinearLayout.LayoutParams layoutParams;
        if (view.getParent() instanceof FrameLayout) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams2.width = i;
            layoutParams2.height = i2;
            layoutParams = layoutParams2;
        } else if (view.getParent() instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams3.width = i;
            layoutParams3.height = i2;
            layoutParams = layoutParams3;
        } else {
            if (!(view.getParent() instanceof LinearLayout)) {
                return;
            }
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams4.width = i;
            layoutParams4.height = i2;
            layoutParams = layoutParams4;
        }
        view.setLayoutParams(layoutParams);
        view.requestLayout();
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void bindImageViews(Context context, List<ImageView> list, int i) {
        if (this.a == null || list.isEmpty()) {
            return;
        }
        this.a.a(list, i);
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void bindMediaView(Context context, ViewGroup viewGroup, final NativeADData.NativeADMediaListener nativeADMediaListener) {
        if (this.a != null) {
            this.a.a(viewGroup, new g() { // from class: com.sigmob.windad.Adapter.SigmobNativeAdData.2
                @Override // com.sigmob.sdk.nativead.g
                public void onProgressUpdate(long j, long j2) {
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoAdComplete() {
                    if (nativeADMediaListener != null) {
                        nativeADMediaListener.onVideoCompleted();
                    }
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoAdPaused() {
                    if (nativeADMediaListener != null) {
                        nativeADMediaListener.onVideoPause();
                    }
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoAdResume() {
                    if (nativeADMediaListener != null) {
                        nativeADMediaListener.onVideoResume();
                    }
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoAdStartPlay() {
                    if (nativeADMediaListener != null) {
                        nativeADMediaListener.onVideoStart();
                    }
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoError(int i, int i2) {
                    if (nativeADMediaListener != null) {
                        WindAdError windAdError = WindAdError.ERROR_SIGMOB_AD_PLAY;
                        windAdError.setMessage("onVideoError errorCode " + i + " extraCode  " + i);
                        nativeADMediaListener.onVideoError(windAdError);
                    }
                }

                @Override // com.sigmob.sdk.nativead.g
                public void onVideoLoad() {
                    if (nativeADMediaListener != null) {
                        nativeADMediaListener.onVideoLoad();
                    }
                }
            });
        }
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void bindViewForInteraction(Context context, View view, List<View> list, List<View> list2, View view2, NativeADEventListener nativeADEventListener) {
        this.b = nativeADEventListener;
        this.c = view2;
        if (this.a != null) {
            this.a.a((ViewGroup) view, list2, new n() { // from class: com.sigmob.windad.Adapter.SigmobNativeAdData.1
                @Override // com.sigmob.sdk.nativead.n
                public void onAdClicked(s sVar) {
                    if (SigmobNativeAdData.this.b != null) {
                        SigmobNativeAdData.this.b.onADClicked();
                    }
                    if (SigmobNativeAdData.this.f != null) {
                        SigmobNativeAdData.this.f.adapterDidAdClick(SigmobNativeAdData.this.d, SigmobNativeAdData.this.e);
                    }
                }

                public void onAdCtaChanged(String str, s sVar) {
                    if (SigmobNativeAdData.this.b != null) {
                        SigmobNativeAdData.this.b.onADStatusChanged(str);
                    }
                }

                @Override // com.sigmob.sdk.nativead.n
                public void onAdDetailDismiss(s sVar) {
                }

                @Override // com.sigmob.sdk.nativead.n
                public void onAdDetailShow(s sVar) {
                    if (SigmobNativeAdData.this.f != null) {
                        SigmobNativeAdData.this.f.adapterDidShowLangPageAd(SigmobNativeAdData.this.d, SigmobNativeAdData.this.e);
                    }
                }

                @Override // com.sigmob.sdk.nativead.n
                public void onAdError(int i, String str, s sVar) {
                    if (SigmobNativeAdData.this.b != null) {
                        WindAdError windAdError = WindAdError.ERROR_SIGMOB_REQUEST;
                        windAdError.setMessage(str);
                        SigmobNativeAdData.this.b.onADError(windAdError);
                    }
                    if (SigmobNativeAdData.this.f != null) {
                        SigmobNativeAdData.this.f.adapterDidFailToPlayingAd(SigmobNativeAdData.this.d, SigmobNativeAdData.this.e, new WindAdAdapterError(i, str));
                    }
                }

                @Override // com.sigmob.sdk.nativead.n
                public void onAdShow(s sVar) {
                    if (SigmobNativeAdData.this.b != null) {
                        SigmobNativeAdData.this.b.onADExposed();
                    }
                    if (SigmobNativeAdData.this.f != null) {
                        SigmobNativeAdData.this.f.adapterDidStartPlayingAd(SigmobNativeAdData.this.d, SigmobNativeAdData.this.e);
                    }
                }
            });
        }
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void connectAdToView(Activity activity, WindNativeAdContainer windNativeAdContainer, WindNativeAdRender windNativeAdRender) {
        if (windNativeAdRender != null) {
            View viewCreateView = windNativeAdRender.createView(activity, getAdPatternType());
            windNativeAdRender.renderAdView(viewCreateView, this);
            if (windNativeAdContainer != null) {
                windNativeAdContainer.removeAllViews();
                windNativeAdContainer.addView(viewCreateView, new ViewGroup.LayoutParams(-1, -2));
            }
        }
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void destroy() {
        if (this.a != null) {
            this.a.h();
        }
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public Bitmap getAdLogo() {
        if (this.a != null) {
            return this.a.b();
        }
        return null;
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public int getAdPatternType() {
        if (this.a == null) {
            return 0;
        }
        switch (this.a.g()) {
        }
        return 0;
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public String getCTAText() {
        return (this.a == null || TextUtils.isEmpty(this.a.a())) ? "" : this.a.a();
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public String getDesc() {
        return this.a != null ? this.a.d() : "";
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public String getIconUrl() {
        return this.a != null ? this.a.e() : "";
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public String getTitle() {
        return this.a != null ? this.a.c() : "";
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void pauseVideo() {
        if (this.a != null) {
            this.a.j();
        }
    }

    public void resume() {
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void resumeVideo() {
        if (this.a != null) {
            this.a.k();
        }
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void setDislikeInteractionCallback(Activity activity, final NativeADData.DislikeInteractionCallback dislikeInteractionCallback) {
        if (this.c == null || this.a == null) {
            return;
        }
        final k kVarA = this.a.a(activity);
        if (kVarA != null) {
            kVarA.a(new k.a() { // from class: com.sigmob.windad.Adapter.SigmobNativeAdData.3
                @Override // com.sigmob.sdk.nativead.k.a
                public void onCancel() {
                    if (dislikeInteractionCallback != null) {
                        dislikeInteractionCallback.onCancel();
                    }
                }

                @Override // com.sigmob.sdk.nativead.k.a
                public void onSelected(int i, String str, boolean z) {
                    if (dislikeInteractionCallback != null) {
                        dislikeInteractionCallback.onSelected(i, str, z);
                    }
                }

                @Override // com.sigmob.sdk.nativead.k.a
                public void onShow() {
                    if (dislikeInteractionCallback != null) {
                        dislikeInteractionCallback.onShow();
                    }
                }
            });
        }
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.sigmob.windad.Adapter.SigmobNativeAdData.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (kVarA != null) {
                    kVarA.a();
                }
            }
        });
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void startVideo() {
        if (this.a != null) {
            this.a.i();
        }
    }

    @Override // com.sigmob.windad.natives.NativeADData
    public void stopVideo() {
        if (this.a != null) {
            this.a.l();
        }
    }
}
