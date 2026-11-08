package com.sigmob.windad.rewardedVideo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindRewardInfo {
    private final int a;
    private final String b;
    private final boolean c;

    public WindRewardInfo(int i, String str, boolean z) {
        this.a = i;
        this.b = str;
        this.c = z;
    }

    public int getAdFormat() {
        return this.a;
    }

    public String getPlacementId() {
        return this.b;
    }

    public boolean isComplete() {
        return this.c;
    }

    public String toString() {
        return "WindRewardInfo{adType=" + this.a + ", placementId='" + this.b + "', isComplete=" + this.c + '}';
    }
}
