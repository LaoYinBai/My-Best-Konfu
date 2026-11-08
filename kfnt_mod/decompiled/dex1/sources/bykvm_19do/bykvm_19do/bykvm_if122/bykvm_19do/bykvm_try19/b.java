package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19;

import com.bytedance.msdk.api.AdSlot;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private String f1909a;

    /* renamed from: b, reason: collision with root package name */
    private int f1910b;
    private int e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;

    /* renamed from: c, reason: collision with root package name */
    private int f1911c = 1;
    private int d = 0;
    public int ifTest = 0;

    public static AdSlot getShallowCopy(AdSlot adSlot) {
        if (adSlot == null) {
            return null;
        }
        AdSlot.Builder builder = new AdSlot.Builder();
        builder.setImageAdSize(adSlot.getImgAcceptedWidth(), adSlot.getImgAcceptedHeight()).setSupportDeepLink(adSlot.isSupportDeepLink()).setAdCount(adSlot.getAdCount()).setRewardName(adSlot.getRewardName()).setRewardAmount(adSlot.getRewardAmount()).setMediaExtra(adSlot.getMediaExtra()).setCustomData(adSlot.getCustomData()).setUserID(adSlot.getUserID()).setOrientation(adSlot.getOrientation()).setAdType(adSlot.getAdType()).setTTVideoOption(adSlot.getTTVideoOption()).setTTRequestExtraParams(adSlot.getReuestParam()).setAdStyleType(adSlot.getAdStyleType()).setBannerSize(adSlot.getBannerSize()).setAdmobNativeAdOptions(adSlot.getAdmobNativeAdOptions()).setGdtNativeAdLogoParams(adSlot.getGdtNativeAdLogoParams()).setSplashButtonType(adSlot.getSplashButtonType()).setDownloadType(adSlot.getDownloadType()).setBidNotify(adSlot.isBidNotify()).setTestSlotId(adSlot.getTestSlotId());
        AdSlot adSlotBuild = builder.build();
        adSlotBuild.setAdUnitId(adSlot.getAdUnitId());
        adSlotBuild.setVersion(adSlot.getVersion());
        adSlotBuild.setWaterfallId(adSlot.getWaterfallId());
        adSlotBuild.setAdloadSeq(adSlot.getAdloadSeq());
        adSlotBuild.setLinkedId(adSlot.getLinkedId());
        adSlotBuild.setSegmentId(adSlot.getSegmentId());
        adSlotBuild.setSegmentVersion(adSlot.getSegmentVersion());
        adSlotBuild.setReqType(adSlot.getReqType());
        adSlotBuild.setmWaterfallExtra(adSlot.getmWaterfallExtra());
        adSlotBuild.setTransparentParams(adSlot.getTransparentParams());
        adSlotBuild.setIfTest(adSlot.getIfTest());
        return adSlotBuild;
    }

    public int getAdloadSeq() {
        return this.f1910b;
    }

    public String getFailCallback() {
        return this.j;
    }

    public int getIfTest() {
        return this.ifTest;
    }

    public String getLinkedId() {
        return this.f1909a;
    }

    public int getPrimeRitReqType() {
        return this.f1911c;
    }

    public int getReqType() {
        return this.d;
    }

    public int getSegmentId() {
        return this.e;
    }

    public String getSegmentVersion() {
        return this.f;
    }

    public String getTransparentParams() {
        return this.g;
    }

    public String getWinCallback() {
        return this.i;
    }

    public String getmWaterfallExtra() {
        return this.h;
    }

    public void setAdloadSeq(int i) {
        this.f1910b = i;
    }

    public void setFailCallback(String str) {
        this.j = str;
    }

    public void setIfTest(int i) {
        this.ifTest = i;
    }

    public void setLinkedId(String str) {
        this.f1909a = str;
    }

    public void setPrimeRitReqType(int i) {
        this.f1911c = i;
    }

    public void setReqType(int i) {
        this.d = i;
    }

    public void setSegmentId(int i) {
        this.e = i;
    }

    public void setSegmentVersion(String str) {
        this.f = str;
    }

    public void setTransparentParams(String str) {
        this.g = str;
    }

    public void setWinCallback(String str) {
        this.i = str;
    }

    public void setmWaterfallExtra(String str) {
        this.h = str;
    }
}
