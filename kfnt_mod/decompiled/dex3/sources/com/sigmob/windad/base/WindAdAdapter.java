package com.sigmob.windad.base;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.sigmob.sdk.common.models.ADStrategy;
import com.sigmob.windad.LifecycleListener;
import com.sigmob.windad.WindAdRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class WindAdAdapter implements LifecycleListener {
    public abstract void destroy();

    public abstract int getAdapterVersion();

    public abstract void initWithAdConnector(WindAdBaseConnector windAdBaseConnector);

    public abstract void initializeSdk(Context context, ADStrategy aDStrategy);

    public abstract boolean isInit();

    public abstract boolean isReady(ADStrategy aDStrategy);

    public abstract void loadAd(Activity activity, ViewGroup viewGroup, WindAdRequest windAdRequest, ADStrategy aDStrategy);

    public abstract void presentVideoAd(Activity activity, ViewGroup viewGroup, ADStrategy aDStrategy);
}
