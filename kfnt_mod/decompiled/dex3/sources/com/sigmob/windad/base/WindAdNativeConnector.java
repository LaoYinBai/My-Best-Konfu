package com.sigmob.windad.base;

import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.base.WindAdAdapter;
import com.sigmob.windad.natives.NativeADData;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface WindAdNativeConnector<T extends WindAdAdapter> extends WindAdBaseConnector {
    void adapterDidFailToLoadAd(T t, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError);

    void adapterDidFailToPlayingAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError);

    void adapterDidLoadNativeAdSuccessAd(T t, ADStrategy aDStrategy, List<NativeADData> list);

    void adapterDidShowLangPageAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy);

    void adapterDidStartPlayingAd(WindAdAdapter windAdAdapter, ADStrategy aDStrategy);
}
