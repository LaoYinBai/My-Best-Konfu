package com.qq.e.ads;

import com.qq.e.ads.cfg.BrowserType;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;
import com.qq.e.comm.pi.ADI;
import com.qq.e.comm.util.AdError;
import com.qq.e.comm.util.GDTLogger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class NativeAbstractAD<T extends ADI> extends AbstractAD<T> {
    private DownAPPConfirmPolicy f;

    public interface BasicADListener {
        void onNoAD(AdError adError);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.qq.e.ads.AbstractAD
    public void a(T t) {
        DownAPPConfirmPolicy downAPPConfirmPolicy = this.f;
        if (downAPPConfirmPolicy != null) {
            setDownAPPConfirmPolicy(downAPPConfirmPolicy);
        }
    }

    @Deprecated
    public void setBrowserType(BrowserType browserType) {
        GDTLogger.w("setBrowserType已废弃，调用无效");
    }

    public void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.f = downAPPConfirmPolicy;
        T t = this.f5481a;
        if (t == null || downAPPConfirmPolicy == null) {
            return;
        }
        t.setDownAPPConfirmPolicy(downAPPConfirmPolicy);
    }
}
