package com.qq.e.ads.hybrid;

import android.content.Context;
import com.kwai.sodler.lib.ext.PluginError;
import com.qq.e.ads.AbstractAD;
import com.qq.e.comm.pi.HADI;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.AdErrorConvertor;
import com.qq.e.comm.util.GDTLogger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class HybridAD extends AbstractAD<HADI> implements HADI {
    private HybridADListener f;
    private CountDownLatch g;
    private HybridADSetting h;

    public HybridAD(Context context, HybridADSetting hybridADSetting, HybridADListener hybridADListener) {
        this.g = new CountDownLatch(1);
        this.h = hybridADSetting;
        this.f = hybridADListener;
        a(context, "NO_POS_ID");
    }

    @Deprecated
    public HybridAD(Context context, String str, HybridADSetting hybridADSetting, HybridADListener hybridADListener) {
        this(context, hybridADSetting, hybridADListener);
        c();
    }

    protected HADI a(POFactory pOFactory) {
        return pOFactory.getHybridAD(this.h, this.f);
    }

    protected /* bridge */ /* synthetic */ Object a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return a(pOFactory);
    }

    protected /* bridge */ /* synthetic */ void a(Object obj) {
        d();
    }

    protected void b(int i) {
        HybridADListener hybridADListener = this.f;
        if (hybridADListener != null) {
            hybridADListener.onError(AdErrorConvertor.formatErrorCode(i));
        }
        this.g.countDown();
    }

    protected void d() {
        this.g.countDown();
    }

    public void loadUrl(final String str) {
        if (a()) {
            if (!b()) {
                new Thread(new Runnable() { // from class: com.qq.e.ads.hybrid.HybridAD.1
                    @Override // java.lang.Runnable
                    public void run() throws InterruptedException {
                        try {
                            HybridAD.this.g.await(30L, TimeUnit.SECONDS);
                            if (!HybridAD.this.b() || ((AbstractAD) HybridAD.this).a == null) {
                                GDTLogger.e("初始化错误：广告实例未被初始化");
                                HybridAD.this.a((HybridAD) PluginError.ERROR_UPD_CANCELED);
                            } else {
                                ((HADI) ((AbstractAD) HybridAD.this).a).loadUrl(str);
                            }
                        } catch (InterruptedException unused) {
                            GDTLogger.e("初始化错误：广告实例未被初始化");
                            HybridAD.this.a((HybridAD) PluginError.ERROR_UPD_CANCELED);
                        }
                    }
                }).start();
                return;
            }
            Object obj = ((AbstractAD) this).a;
            if (obj != null) {
                ((HADI) obj).loadUrl(str);
            } else {
                a("loadUrl");
            }
        }
    }
}
