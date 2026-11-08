package com.bytedance.mapplog_dr;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import bykvm_19do.bykvm_19do.bykvm_19do.a1;
import bykvm_19do.bykvm_19do.bykvm_19do.l0;
import bykvm_19do.bykvm_19do.bykvm_19do.t0;
import bykvm_19do.bykvm_19do.bykvm_19do.z0;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class VivoIdentifier {
    private String oaid;
    private String resultCode;
    private final AtomicBoolean mSecondCallGetDeviceIds = new AtomicBoolean();
    private volatile boolean mProloading = false;
    private final Object mLock = new Object();
    private final IIdentifierListener listener = new IdentifierListener();

    private int callFromReflect(Context context) {
        try {
            System.loadLibrary("nllvm1623827671");
            MdidSdkHelper.InitCert(context, loadPemFromAssetFile(context, context.getPackageName() + ".cert.pem"));
        } catch (Throwable th) {
            l0.c("如果用了1.0.26版本以下的信通院SDK，请忽略这个错误: ", th);
        }
        return MdidSdkHelper.InitSdk(context, false, this.listener);
    }

    private static String loadPemFromAssetFile(Context context, String str) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return sb.toString();
                }
                sb.append(line);
                sb.append('\n');
            }
        } catch (IOException unused) {
            Log.e(z0.j, "loadPemFromAssetFile failed");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setupOaid(String str) {
        t0.b(z0.j, "OaidMiit#setupOaid current oaid=" + str + ", global oaid=" + this.oaid);
        if (!TextUtils.isEmpty(str) && !str.equals(this.oaid)) {
            this.oaid = str;
        }
    }

    public a1.a getOaid(Context context) {
        synchronized (this.mLock) {
            if (this.mProloading) {
                try {
                    this.mLock.wait(10000L);
                } catch (Throwable th) {
                    l0.a("", th);
                }
            }
        }
        a1.a aVar = new a1.a();
        aVar.f1312a = this.oaid;
        return aVar;
    }

    public void preloadOaid(Context context) {
        synchronized (this.mLock) {
            this.mProloading = true;
            try {
                int iCallFromReflect = callFromReflect(context);
                if (iCallFromReflect == 1008614) {
                    t0.b(z0.j, "OaidMiit#getDeviceIds 获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程");
                    if (this.mSecondCallGetDeviceIds.compareAndSet(false, true)) {
                        preloadOaid(context);
                    } else {
                        this.resultCode = "结果会在回调中返回";
                    }
                } else if (iCallFromReflect == 0 || iCallFromReflect == 1008610) {
                    t0.b(z0.j, "OaidMiit#getDeviceIds 正确");
                    this.resultCode = "正确";
                } else {
                    this.resultCode = "未知 resultCode=" + iCallFromReflect;
                    t0.b(z0.j, "OaidMiit#getDeviceIds 未知 resultCode=" + iCallFromReflect);
                }
            } catch (Throwable th) {
                l0.a("", th);
                this.mProloading = false;
            }
        }
    }

    class IdentifierListener implements IIdentifierListener {
        private IdentifierListener() {
        }

        public void OnSupport(boolean z, IdSupplier idSupplier) {
            try {
                t0.b(z0.j, "OaidMiit#OnSupport isSupport=" + z + ", supplier=" + idSupplier);
                if (z && idSupplier != null) {
                    VivoIdentifier.this.setupOaid(idSupplier.getOAID());
                    synchronized (VivoIdentifier.this.mLock) {
                        VivoIdentifier.this.mProloading = false;
                        VivoIdentifier.this.mLock.notifyAll();
                    }
                }
            } catch (Throwable th) {
                l0.a("", th);
            }
        }

        public void onSupport(IdSupplier idSupplier) {
            try {
                t0.b(z0.j, "OaidMiit#OnSupport supplier=" + idSupplier);
                if (idSupplier != null && idSupplier.isSupported()) {
                    OnSupport(true, idSupplier);
                }
            } catch (Throwable th) {
                l0.a("", th);
            }
        }

        public void OnSupport(IdSupplier idSupplier) {
            onSupport(idSupplier);
        }
    }
}
