package com.alibaba.sdk.android.httpdns.probe;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IPProbeService {

    public enum a {
        NO_PROBING,
        PROBING
    }

    a getProbeStatus(String str);

    void launchIPProbeTask(String str, int i, String[] strArr);

    void setIPListUpdateCallback(b bVar);

    boolean stopIPProbeTask(String str);
}
