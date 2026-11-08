package com.mbridge.msdk.out;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface InterstitialListener {
    void onInterstitialAdClick(MBridgeIds mBridgeIds);

    void onInterstitialClosed(MBridgeIds mBridgeIds);

    void onInterstitialLoadFail(MBridgeIds mBridgeIds, String str);

    void onInterstitialLoadSuccess(MBridgeIds mBridgeIds);

    void onInterstitialShowFail(MBridgeIds mBridgeIds, String str);

    void onInterstitialShowSuccess(MBridgeIds mBridgeIds);
}
