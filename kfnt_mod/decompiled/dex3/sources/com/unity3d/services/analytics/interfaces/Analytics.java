package com.unity3d.services.analytics.interfaces;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Analytics {
    public static void initialize(IAnalytics iAnalytics) {
        com.unity3d.services.analytics.core.api.Analytics.setAnalyticsInterface(iAnalytics);
    }
}
