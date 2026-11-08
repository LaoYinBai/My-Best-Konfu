package com.sigmob.windad.Drift;

import com.sigmob.windad.WindAdAdapterError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface WindDriftAdListener {
    void onDriftAdClosed(String str);

    void onDriftAdError(WindAdAdapterError windAdAdapterError, String str);

    void onDriftAdExposure(String str);

    void onDriftAdLoadSuccess(String str);

    void onDriftAdViewClosed(String str);
}
