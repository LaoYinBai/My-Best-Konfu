package com.sigmob.windad.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.a;
import com.sigmob.sdk.base.models.LoadAdRequest;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.sdk.nativead.m;
import com.sigmob.sdk.nativead.p;
import com.sigmob.sdk.nativead.s;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.WindAdRequest;
import com.sigmob.windad.base.WindAdAdapter;
import com.sigmob.windad.base.WindAdBaseConnector;
import com.sigmob.windad.base.WindAdNativeConnector;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigmobNativeUnifiedAdapter extends WindAdAdapter {
    private WindAdNativeConnector a;
    private m d;
    private LoadAdRequest g;
    private Context h;
    private WindAdAdapter b = this;
    private int c = 3400;
    private boolean e = false;
    private List<SigmobNativeAdData> f = new ArrayList();

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void destroy() {
        if (this.d != null) {
            this.d = null;
        }
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public int getAdapterVersion() {
        return this.c;
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void initWithAdConnector(WindAdBaseConnector windAdBaseConnector) {
        this.a = (WindAdNativeConnector) windAdBaseConnector;
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void initializeSdk(Context context, ADStrategy aDStrategy) {
        this.h = context;
        a.a(context);
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public boolean isInit() {
        return a.a();
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public boolean isReady(ADStrategy aDStrategy) {
        return this.e;
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void loadAd(Activity activity, ViewGroup viewGroup, WindAdRequest windAdRequest, final ADStrategy aDStrategy) {
        try {
            this.e = false;
            final String placement_id = aDStrategy.getPlacement_id();
            SigmobLog.i(getClass().getSimpleName() + " loadAd " + placement_id);
            Object obj = aDStrategy.getOptions().get(Constants.SHOW_DOWNLOAD_DIALOG);
            if (obj == null || !obj.equals("1")) {
                com.sigmob.sdk.common.a.n().f(false);
            } else {
                com.sigmob.sdk.common.a.n().f(true);
            }
            this.g = new LoadAdRequest(aDStrategy.isUseMediation(), aDStrategy.getBidToken(), windAdRequest.getUserId(), aDStrategy.getPlacement_id(), aDStrategy.getAdType(), aDStrategy.getSig_load_id(), windAdRequest.getOptions());
            this.g.setAdCount(windAdRequest.getAdCount());
            this.d = new m(this.h, this.g, new p() { // from class: com.sigmob.windad.Adapter.SigmobNativeUnifiedAdapter.1
                @Override // com.sigmob.sdk.nativead.p
                public void onNativeAdLoadFail(int i, String str) {
                    SigmobLog.i(SigmobNativeUnifiedAdapter.this.b.getClass().getSimpleName() + " onNativeAdLoadFail " + i + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + str);
                    if (SigmobNativeUnifiedAdapter.this.a != null) {
                        SigmobNativeUnifiedAdapter.this.a.adapterDidFailToLoadAd(SigmobNativeUnifiedAdapter.this.b, aDStrategy, new WindAdAdapterError(i, str));
                    }
                }

                @Override // com.sigmob.sdk.nativead.p
                public void onNativeAdLoaded(List<s> list) {
                    SigmobLog.i(SigmobNativeUnifiedAdapter.this.b.getClass().getSimpleName() + " onNativeAdLoaded()");
                    if (list == null || list.isEmpty()) {
                        if (SigmobNativeUnifiedAdapter.this.a != null) {
                            SigmobNativeUnifiedAdapter.this.a.adapterDidFailToLoadAd(SigmobNativeUnifiedAdapter.this.b, aDStrategy, new WindAdAdapterError(0, "list is null or size be 0 " + placement_id));
                            return;
                        }
                        return;
                    }
                    SigmobNativeUnifiedAdapter.this.e = true;
                    SigmobNativeUnifiedAdapter.this.f.clear();
                    for (int i = 0; i < list.size(); i++) {
                        SigmobNativeUnifiedAdapter.this.f.add(new SigmobNativeAdData(list.get(i), SigmobNativeUnifiedAdapter.this.b, aDStrategy, SigmobNativeUnifiedAdapter.this.a));
                    }
                    if (SigmobNativeUnifiedAdapter.this.a != null) {
                        SigmobNativeUnifiedAdapter.this.a.adapterDidLoadNativeAdSuccessAd(SigmobNativeUnifiedAdapter.this.b, aDStrategy, SigmobNativeUnifiedAdapter.this.f);
                    }
                }
            });
            this.d.a();
        } catch (Throwable th) {
            SigmobLog.i(getClass().getSimpleName() + " catch throwable " + th);
            if (this.a != null) {
                this.a.adapterDidFailToLoadAd(this.b, aDStrategy, new WindAdAdapterError(0, th.getMessage()));
            }
        }
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onCreate(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onDestroy(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onPause(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onResume(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onStart(Activity activity) {
    }

    @Override // com.sigmob.windad.LifecycleListener
    public void onStop(Activity activity) {
    }

    @Override // com.sigmob.windad.base.WindAdAdapter
    public void presentVideoAd(Activity activity, ViewGroup viewGroup, ADStrategy aDStrategy) {
    }
}
