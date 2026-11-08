package com.sigmob.windad.base;

import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.windad.WindAdAdapterError;
import com.sigmob.windad.base.WindAdAdapter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface WindAdBaseConnector<T extends WindAdAdapter> {
    void adapterDidAdClick(T t, ADStrategy aDStrategy);

    void adapterDidInitFail(T t, ADStrategy aDStrategy, WindAdAdapterError windAdAdapterError);

    void adapterDidInitSuccess(T t, ADStrategy aDStrategy);
}
