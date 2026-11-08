package com.bytedance.msdk.api.v2.ad.custom.reward;

import com.bytedance.msdk.api.reward.RewardItem;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IGMCustomRewardEvent {
    void callRewardClick();

    void callRewardSkippedVideo();

    void callRewardVerify(RewardItem rewardItem);

    void callRewardVideoComplete();

    void callRewardVideoError();

    void callRewardedAdClosed();

    void callRewardedAdShow();
}
