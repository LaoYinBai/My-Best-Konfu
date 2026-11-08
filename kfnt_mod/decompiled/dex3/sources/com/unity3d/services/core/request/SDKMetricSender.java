package com.unity3d.services.core.request;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SDKMetricSender implements ISDKMetricSender {
    @Override // com.unity3d.services.core.request.ISDKMetricSender
    public void SendSDKMetricEvent(SDKMetricEvents sDKMetricEvents) {
        ISDKMetrics sDKMetrics;
        if (sDKMetricEvents == null || (sDKMetrics = SDKMetrics.getInstance()) == null) {
            return;
        }
        sDKMetrics.sendEvent(sDKMetricEvents.toString());
    }

    @Override // com.unity3d.services.core.request.ISDKMetricSender
    public void SendSDKMetricEventWithTag(SDKMetricEvents sDKMetricEvents, HashMap map) {
        ISDKMetrics sDKMetrics;
        if (sDKMetricEvents == null || (sDKMetrics = SDKMetrics.getInstance()) == null) {
            return;
        }
        sDKMetrics.sendEventWithTags(sDKMetricEvents.toString(), map);
    }
}
