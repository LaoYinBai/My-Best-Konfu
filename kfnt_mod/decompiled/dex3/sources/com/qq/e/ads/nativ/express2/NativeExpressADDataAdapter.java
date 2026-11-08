package com.qq.e.ads.nativ.express2;

import android.app.Activity;
import android.view.View;
import com.qq.e.comm.adevent.ADEvent;
import com.qq.e.comm.adevent.ADEventListener;
import com.qq.e.comm.adevent.ADListener;
import com.qq.e.comm.compliance.DownloadConfirmCallBack;
import com.qq.e.comm.compliance.DownloadConfirmListener;
import java.util.Map;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeExpressADDataAdapter implements NativeExpressADData2, DownloadConfirmListener {
    private NativeExpressADData2 a;
    private MediaEventListener b;
    private AdEventListener c;
    private DownloadConfirmListener d;

    public NativeExpressADDataAdapter(NativeExpressADData2 nativeExpressADData2) {
        this.a = nativeExpressADData2;
        if (nativeExpressADData2 instanceof ADEventListener) {
            ((ADEventListener) nativeExpressADData2).setAdListener(new ADListener() { // from class: com.qq.e.ads.nativ.express2.NativeExpressADDataAdapter.1
                @Override // com.qq.e.comm.adevent.ADListener
                public void onADEvent(ADEvent aDEvent) {
                    if (aDEvent.getType() < 201) {
                        NativeExpressADDataAdapter.a(NativeExpressADDataAdapter.this, aDEvent);
                    } else if (aDEvent.getType() < 301) {
                        NativeExpressADDataAdapter.b(NativeExpressADDataAdapter.this, aDEvent);
                    }
                }
            });
        }
    }

    static void a(NativeExpressADDataAdapter nativeExpressADDataAdapter, ADEvent aDEvent) {
        if (nativeExpressADDataAdapter.c != null) {
            switch (aDEvent.getType()) {
                case 101:
                    nativeExpressADDataAdapter.c.onClick();
                    break;
                case 102:
                    nativeExpressADDataAdapter.c.onExposed();
                    break;
                case 103:
                    nativeExpressADDataAdapter.c.onRenderSuccess();
                    break;
                case 104:
                    nativeExpressADDataAdapter.c.onRenderFail();
                    break;
                case 105:
                    nativeExpressADDataAdapter.c.onAdClosed();
                    break;
            }
        }
    }

    static void b(NativeExpressADDataAdapter nativeExpressADDataAdapter, ADEvent aDEvent) {
        if (nativeExpressADDataAdapter.b != null) {
            switch (aDEvent.getType()) {
                case 201:
                    nativeExpressADDataAdapter.b.onVideoCache();
                    break;
                case 202:
                    nativeExpressADDataAdapter.b.onVideoStart();
                    break;
                case 203:
                    nativeExpressADDataAdapter.b.onVideoResume();
                    break;
                case 204:
                case MediaEventListener.EVENT_VIDEO_STOP /* 205 */:
                    nativeExpressADDataAdapter.b.onVideoPause();
                    break;
                case MediaEventListener.EVENT_VIDEO_COMPLETE /* 206 */:
                    nativeExpressADDataAdapter.b.onVideoComplete();
                    break;
                case MediaEventListener.EVENT_VIDEO_ERROR /* 207 */:
                    nativeExpressADDataAdapter.b.onVideoError();
                    break;
            }
        }
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public void destroy() {
        this.a.destroy();
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public View getAdView() {
        return this.a.getAdView();
    }

    @Override // com.qq.e.comm.compliance.ApkDownloadComplianceInterface
    public String getApkInfoUrl() {
        NativeExpressADData2 nativeExpressADData2 = this.a;
        if (nativeExpressADData2 != null) {
            return nativeExpressADData2.getApkInfoUrl();
        }
        return null;
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public int getECPM() {
        return this.a.getECPM();
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public String getECPMLevel() {
        return this.a.getECPMLevel();
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public Map<String, Object> getExtraInfo() {
        return this.a.getExtraInfo();
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public int getVideoDuration() {
        return this.a.getVideoDuration();
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public boolean isVideoAd() {
        return this.a.isVideoAd();
    }

    @Override // com.qq.e.comm.compliance.DownloadConfirmListener
    public void onDownloadConfirm(Activity activity, int i, String str, DownloadConfirmCallBack downloadConfirmCallBack) {
        DownloadConfirmListener downloadConfirmListener = this.d;
        if (downloadConfirmListener != null) {
            downloadConfirmListener.onDownloadConfirm(activity, i, str, downloadConfirmCallBack);
        }
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public void render() {
        this.a.render();
    }

    @Override // com.qq.e.comm.pi.IBidding
    public void sendLossNotification(int i, int i2, String str) {
        this.a.sendLossNotification(i, i2, str);
    }

    @Override // com.qq.e.comm.pi.IBidding
    public void sendWinNotification(int i) {
        this.a.sendWinNotification(i);
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public void setAdEventListener(AdEventListener adEventListener) {
        this.c = adEventListener;
    }

    @Override // com.qq.e.comm.pi.IBidding
    public void setBidECPM(int i) {
        this.a.setBidECPM(i);
    }

    @Override // com.qq.e.comm.compliance.ApkDownloadComplianceInterface
    public void setDownloadConfirmListener(DownloadConfirmListener downloadConfirmListener) {
        this.d = downloadConfirmListener;
        NativeExpressADData2 nativeExpressADData2 = this.a;
        if (nativeExpressADData2 != null) {
            nativeExpressADData2.setDownloadConfirmListener(this);
        }
    }

    @Override // com.qq.e.ads.nativ.express2.NativeExpressADData2
    public void setMediaListener(MediaEventListener mediaEventListener) {
        this.b = mediaEventListener;
    }
}
