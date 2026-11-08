package com.mbridge.msdk.out;

import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface AdapterListener {
    void onAdClicked(Campaign campaign);

    void onAdLoaded(List<Campaign> list);

    void onError(String str);

    void onFrameAdLoaded(List<Frame> list);
}
