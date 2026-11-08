package com.qq.e.ads.nativ;

import android.app.Activity;
import android.content.Context;
import com.qq.e.ads.nativ.NativeExpressADView;
import com.qq.e.ads.nativ.express2.NativeExpressADData2;
import com.qq.e.comm.compliance.DownloadConfirmCallBack;
import com.qq.e.comm.compliance.DownloadConfirmListener;
import com.qq.e.comm.constants.Constants;
import com.qq.e.comm.pi.AdData;
import com.qq.e.comm.pi.NEADI;
import com.qq.e.comm.pi.NEADVI;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeExpressADViewImpl extends NativeExpressADView {
    private AdData a;
    private NativeExpressADView.ViewBindStatusListener b;
    private final NativeExpressADCore c;

    public NativeExpressADViewImpl(NEADI neadi, NEADVI neadvi, NativeExpressADData2 nativeExpressADData2, Context context, ADSize aDSize, String str, String str2, String str3, JSONObject jSONObject, HashMap<String, Object> map) {
        super(context);
        this.c = new NativeExpressADCore(this, neadi, neadvi, nativeExpressADData2, context, aDSize, str2, jSONObject, map);
        this.a = a(map);
    }

    private AdData a(HashMap<String, Object> map) {
        JSONObject jSONObject;
        if (map != null) {
            try {
                jSONObject = (JSONObject) map.get(Constants.KEYS.AD_INFO);
            } catch (JSONException unused) {
            }
        } else {
            jSONObject = null;
        }
        Object obj = jSONObject != null ? jSONObject.get(Constants.KEYS.AD_INFO) : null;
        if (obj instanceof AdData) {
            return (AdData) obj;
        }
        return null;
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    public void destroy() {
        this.c.destroy();
    }

    @Override // com.qq.e.comm.compliance.ApkDownloadComplianceInterface
    public String getApkInfoUrl() {
        return this.c.getApkInfoUrl();
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    public AdData getBoundData() {
        return this.a;
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    public int getECPM() {
        return this.c.getECPM();
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    public String getECPMLevel() {
        return this.c.getECPMLevel();
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    public void negativeFeedback() {
        this.c.negativeFeedback();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        NativeExpressADView.ViewBindStatusListener viewBindStatusListener = this.b;
        if (viewBindStatusListener != null) {
            viewBindStatusListener.onAttachedToWindow();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NativeExpressADView.ViewBindStatusListener viewBindStatusListener = this.b;
        if (viewBindStatusListener != null) {
            viewBindStatusListener.onDetachedFromWindow();
        }
    }

    @Override // com.qq.e.comm.compliance.DownloadConfirmListener
    public void onDownloadConfirm(Activity activity, int i, String str, DownloadConfirmCallBack downloadConfirmCallBack) {
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        NativeExpressADView.ViewBindStatusListener viewBindStatusListener = this.b;
        if (viewBindStatusListener != null) {
            viewBindStatusListener.onFinishTemporaryDetach();
        }
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        NativeExpressADView.ViewBindStatusListener viewBindStatusListener = this.b;
        if (viewBindStatusListener != null) {
            viewBindStatusListener.onStartTemporaryDetach();
        }
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    public void preloadVideo() {
        this.c.preloadVideo();
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    public void render() {
        this.c.render();
    }

    @Override // com.qq.e.comm.pi.IBidding
    public void sendLossNotification(int i, int i2, String str) {
        this.c.sendLossNotification(i, i2, str);
    }

    @Override // com.qq.e.comm.pi.IBidding
    public void sendWinNotification(int i) {
        this.c.sendWinNotification(i);
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    @Deprecated
    public void setAdSize(ADSize aDSize) {
        this.c.setAdSize(aDSize);
    }

    @Override // com.qq.e.comm.pi.IBidding
    public void setBidECPM(int i) {
        this.c.setBidECPM(i);
    }

    @Override // com.qq.e.comm.compliance.ApkDownloadComplianceInterface
    public void setDownloadConfirmListener(DownloadConfirmListener downloadConfirmListener) {
        this.c.setDownloadConfirmListener(downloadConfirmListener);
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    public void setMediaListener(NativeExpressMediaListener nativeExpressMediaListener) {
        this.c.a(nativeExpressMediaListener);
    }

    @Override // com.qq.e.ads.nativ.NativeExpressADView
    public void setViewBindStatusListener(NativeExpressADView.ViewBindStatusListener viewBindStatusListener) {
        this.b = viewBindStatusListener;
    }
}
