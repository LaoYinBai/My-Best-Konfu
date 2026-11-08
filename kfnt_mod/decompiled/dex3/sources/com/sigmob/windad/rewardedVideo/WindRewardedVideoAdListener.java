package com.sigmob.windad.rewardedVideo;

import com.sigmob.windad.WindAdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface WindRewardedVideoAdListener {
    void onVideoAdClicked(String str);

    void onVideoAdClosed(WindRewardInfo windRewardInfo, String str);

    void onVideoAdLoadError(WindAdError windAdError, String str);

    void onVideoAdLoadSuccess(String str);

    void onVideoAdPlayEnd(String str);

    void onVideoAdPlayError(WindAdError windAdError, String str);

    void onVideoAdPlayStart(String str);

    void onVideoAdPreLoadFail(String str);

    void onVideoAdPreLoadSuccess(String str);
}
