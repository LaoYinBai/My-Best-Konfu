package com.bytedance.dr;

import android.content.Context;
import android.text.TextUtils;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import com.bytedance.a.bh;
import com.bytedance.a.bm;
import com.bytedance.a.de;
import com.bytedance.a.t;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class VivoIdentifier {
    public String oaid;
    public String resultCode;
    public final AtomicBoolean mSecondCallGetDeviceIds = new AtomicBoolean();
    public volatile boolean mProloading = false;
    public final Object mLock = new Object();
    public final IIdentifierListener listener = new IIdentifierListener() { // from class: com.bytedance.dr.VivoIdentifier.1
        public void OnSupport(boolean z, IdSupplier idSupplier) {
            try {
                t.a(bh.f3405b, "OaidMiit#OnSupport isSupport=" + z + ", supplier=" + idSupplier, null);
                if (z && idSupplier != null) {
                    VivoIdentifier.this.setupOaid(idSupplier.getOAID());
                    synchronized (VivoIdentifier.this.mLock) {
                        VivoIdentifier.this.mProloading = false;
                        VivoIdentifier.this.mLock.notifyAll();
                    }
                }
            } catch (Throwable th) {
                de.a("", th);
            }
        }
    };

    private int callFromReflect(Context context) {
        return MdidSdkHelper.InitSdk(context, true, this.listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setupOaid(String str) {
        t.a(bh.f3405b, "OaidMiit#setupOaid id=" + str + ", oaid=" + this.oaid, null);
        if (!TextUtils.isEmpty(str) && !str.equals(this.oaid)) {
            this.oaid = str;
        }
    }

    public bm.a getOaid(Context context) {
        synchronized (this.mLock) {
            if (this.mProloading) {
                try {
                    this.mLock.wait(10000L);
                } catch (Throwable th) {
                    de.a("", th);
                }
            }
        }
        bm.a aVar = new bm.a();
        aVar.f3415b = this.oaid;
        return aVar;
    }

    public void preloadOaid(Context context) {
        synchronized (this.mLock) {
            this.mProloading = true;
            try {
                int iCallFromReflect = callFromReflect(context);
                if (iCallFromReflect == 1008612) {
                    t.a(bh.f3405b, "OaidMiit#getDeviceIds 不支持的设备", null);
                    this.resultCode = "不支持的设备";
                } else if (iCallFromReflect == 1008613) {
                    t.a(bh.f3405b, "OaidMiit#getDeviceIds 加载配置文件出错", null);
                    this.resultCode = "加载配置文件出错";
                } else if (iCallFromReflect == 1008611) {
                    t.a(bh.f3405b, "OaidMiit#getDeviceIds 不支持的设备厂商", null);
                    this.resultCode = "不支持的设备厂商";
                } else if (iCallFromReflect == 1008614) {
                    t.a(bh.f3405b, "OaidMiit#getDeviceIds 获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程", null);
                    if (this.mSecondCallGetDeviceIds.compareAndSet(false, true)) {
                        preloadOaid(context);
                    } else {
                        this.resultCode = "结果会在回调中返回";
                    }
                } else if (iCallFromReflect == 1008615) {
                    t.a(bh.f3405b, "OaidMiit#getDeviceIds 反射调用出错", null);
                    this.resultCode = "反射调用出错";
                } else if (iCallFromReflect == 0) {
                    t.a(bh.f3405b, "OaidMiit#getDeviceIds 正确", null);
                    this.resultCode = "正确";
                } else {
                    this.resultCode = "未知 resultCode=" + iCallFromReflect;
                    t.a(bh.f3405b, "OaidMiit#getDeviceIds 未知 resultCode=" + iCallFromReflect, null);
                }
            } catch (Throwable th) {
                de.a("", th);
                this.mProloading = false;
            }
        }
    }
}
