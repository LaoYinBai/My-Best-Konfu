package com.unity3d.services.core.request;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ISDKMetricSender {
    void SendSDKMetricEvent(SDKMetricEvents sDKMetricEvents);

    void SendSDKMetricEventWithTag(SDKMetricEvents sDKMetricEvents, HashMap<String, String> map);
}
