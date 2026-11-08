package com.qq.e.ads.nativ;

import android.content.Context;
import android.widget.FrameLayout;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.qq.e.comm.compliance.DownloadConfirmListener;
import com.qq.e.comm.pi.AdData;
import com.qq.e.comm.pi.IBidding;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class NativeExpressADView extends FrameLayout implements DownloadConfirmListener, ApkDownloadComplianceInterface, IBidding {
    public Map<String, String> ext;

    public interface ViewBindStatusListener {
        void onAttachedToWindow();

        void onDetachedFromWindow();

        void onFinishTemporaryDetach();

        void onStartTemporaryDetach();
    }

    public NativeExpressADView(Context context) {
        super(context);
        this.ext = new HashMap();
    }

    public abstract void destroy();

    public abstract AdData getBoundData();

    public abstract int getECPM();

    public abstract String getECPMLevel();

    public abstract void negativeFeedback();

    public abstract void preloadVideo();

    public abstract void render();

    @Deprecated
    public abstract void setAdSize(ADSize aDSize);

    public abstract void setMediaListener(NativeExpressMediaListener nativeExpressMediaListener);

    public abstract void setViewBindStatusListener(ViewBindStatusListener viewBindStatusListener);
}
