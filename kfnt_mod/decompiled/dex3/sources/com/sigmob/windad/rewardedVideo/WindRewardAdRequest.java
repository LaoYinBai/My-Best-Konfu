package com.sigmob.windad.rewardedVideo;

import com.sigmob.windad.WindAdRequest;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindRewardAdRequest extends WindAdRequest {
    private boolean c;
    private boolean d;

    public WindRewardAdRequest(String str, String str2, Map<String, Object> map) {
        super(str, str2, map);
        this.a = 1;
    }

    public static WindRewardAdRequest getWindVideoAdRequest(WindAdRequest windAdRequest) {
        if (windAdRequest != null) {
            return new WindRewardAdRequest(windAdRequest.getPlacementId(), windAdRequest.getUserId(), windAdRequest.getOptions());
        }
        return null;
    }

    public boolean isEnableKeepOn() {
        return this.d;
    }

    public boolean isEnableScreenLockDisPlayAd() {
        return this.c;
    }

    public void setEnableKeepOn(boolean z) {
        this.d = z;
    }

    public void setEnableScreenLockDisPlayAd(boolean z) {
        this.c = z;
    }
}
