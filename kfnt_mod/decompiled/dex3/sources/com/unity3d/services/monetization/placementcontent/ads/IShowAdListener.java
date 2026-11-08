package com.unity3d.services.monetization.placementcontent.ads;

import com.unity3d.ads.UnityAds;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IShowAdListener {
    void onAdFinished(String str, UnityAds.FinishState finishState);

    void onAdStarted(String str);
}
