package com.sigmob.windad.base;

import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.base.WindAdAdapter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface WindAdConnector<T extends WindAdAdapter> extends WindAdBaseConnector {
    void adapterDidCloseAd(T t, ADStrategy aDStrategy, boolean z);

    void adapterDidFailToLoadAd(T t, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError);

    void adapterDidFailToPlayingAd(T t, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError);

    void adapterDidLoadAdSuccessAd(T t, ADStrategy aDStrategy);

    void adapterDidPlayEndAd(T t, ADStrategy aDStrategy);

    void adapterDidPreLoadFailAd(T t, ADStrategy aDStrategy);

    void adapterDidPreLoadSuccessAd(T t, ADStrategy aDStrategy);

    void adapterDidRewardAd(T t, ADStrategy aDStrategy);

    void adapterDidSkipAd(T t, ADStrategy aDStrategy);

    void adapterDidStartPlayingAd(T t, ADStrategy aDStrategy);
}
