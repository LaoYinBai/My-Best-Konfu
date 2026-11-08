package com.qq.e.ads.nativ.express2;

import android.view.View;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.qq.e.comm.pi.IBidding;
import java.util.Map;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface NativeExpressADData2 extends ApkDownloadComplianceInterface, IBidding {
    void destroy();

    View getAdView();

    int getECPM();

    String getECPMLevel();

    Map<String, Object> getExtraInfo();

    int getVideoDuration();

    boolean isVideoAd();

    void render();

    void setAdEventListener(AdEventListener adEventListener);

    void setMediaListener(MediaEventListener mediaEventListener);
}
