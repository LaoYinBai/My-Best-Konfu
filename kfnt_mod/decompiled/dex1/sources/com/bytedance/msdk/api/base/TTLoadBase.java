package com.bytedance.msdk.api.base;

import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import com.bytedance.msdk.api.AdLoadInfo;
import com.bytedance.msdk.api.GMAdEcpmInfo;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class TTLoadBase {
    public abstract List<AdLoadInfo> getAdLoadInfoList();

    public abstract int getAdNetworkPlatformId();

    public abstract String getAdNetworkRitId();

    public abstract GMAdEcpmInfo getBestEcpm();

    public abstract List<GMAdEcpmInfo> getCacheList();

    public abstract List<GMAdEcpmInfo> getMultiBiddingEcpm();

    public abstract String getPreEcpm();

    public boolean hasPlatFormPermission() {
        return a.f().p();
    }
}
