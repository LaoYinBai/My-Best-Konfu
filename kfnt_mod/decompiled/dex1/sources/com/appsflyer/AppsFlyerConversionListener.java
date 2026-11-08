package com.appsflyer;

import androidx.annotation.WorkerThread;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface AppsFlyerConversionListener {
    @WorkerThread
    void onAppOpenAttribution(Map<String, String> map);

    void onAttributionFailure(String str);

    @WorkerThread
    void onInstallConversionDataLoaded(Map<String, String> map);

    void onInstallConversionFailure(String str);
}
