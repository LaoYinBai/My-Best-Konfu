package com.bytedance.mobile.cpsdk;

import android.content.Context;
import android.util.Log;
import androidx.core.view.InputDeviceCompat;
import com.bytedance.mobile.cpsdk.CPConfig;
import cp.bd.c.ab;
import cp.bd.c.c;
import cp.bd.c.d;
import cp.bd.c.e;
import cp.bd.c.f;
import cp.bd.c.g;
import cp.bd.c.h;
import cp.bd.c.j;
import cp.bd.c.k;
import cp.bd.c.n;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CPManager {
    private static String libname = "cpsdk";
    private static volatile CPManager sInstance;
    private CPConfig mConfig;
    private Context mContext;

    private CPManager(CPConfig cPConfig, Context context) throws NumberFormatException {
        Long.parseLong("1");
        if (cPConfig == null) {
            throw new NullPointerException("config could not be null");
        }
        if (context == null) {
            throw new NullPointerException("context could not be null");
        }
        this.mContext = context.getApplicationContext();
        this.mConfig = cPConfig;
    }

    public static CPManager get() {
        return sInstance;
    }

    public static void init(CPConfig cPConfig, Context context) {
        Context applicationContext = context.getApplicationContext();
        if (sInstance == null) {
            synchronized (CPManager.class) {
                if (sInstance == null) {
                    n.a(applicationContext, libname);
                    d.a(65537, new e.1());
                    d.a(65538, new e.2());
                    d.a(65539, new e.3());
                    d.a(InputDeviceCompat.SOURCE_TRACKBALL, new e.4());
                    k kVar = ab.a(new String[]{"com.bytedance.ttnet.TTNetInit"}) ? new k() : new j();
                    d.a(196609, kVar);
                    d.a(196610, kVar);
                    d.a(196611, kVar);
                    h hVar = ab.a(new String[]{"com.bytedance.framwork.core.sdkmonitor.SDKMonitorUtils"}) ? new h() : new f();
                    g.a(hVar);
                    d.a(131073, hVar);
                    d.a(131074, hVar);
                    Log.d("METASEC", "init...");
                    CPManager cPManager = new CPManager(cPConfig, applicationContext);
                    sInstance = cPManager;
                    cPManager.init();
                }
            }
        }
    }

    private boolean init() {
        c cVarA = c.a();
        Context context = this.mContext;
        cVarA.a = context != null ? context.getApplicationContext() : null;
        Log.i("METASEC", "16777217");
        boolean zBooleanValue = ((Boolean) d.a(this.mConfig.toNativeValue())).booleanValue();
        Log.d("METASEC", "CPManagerAPI.init:".concat(String.valueOf(zBooleanValue)));
        return zBooleanValue;
    }

    private byte[] runModelOnImage(String str, CPConfig.ContentType contentType, CPConfig.CPScenes cPScenes) {
        byte[] bArrProcessFrames = CPManagerUtils.processFrames(CPManagerUtils.extractImages(str), contentType, cPScenes);
        Log.d("METASEC", "runModelOnImage ret:" + new String(bArrProcessFrames));
        return bArrProcessFrames;
    }

    private CPConfig.CPVerrificationResult runModelOnText(String str, CPConfig.ContentType contentType, CPConfig.CPScenes cPScenes) {
        return CPManagerUtils.processText(str, contentType, cPScenes);
    }

    private byte[] runModelOnVideo(String str, CPConfig.ContentType contentType, CPConfig.CPScenes cPScenes) {
        byte[] bArrProcessFrames = CPManagerUtils.processFrames(CPManagerUtils.extractVideo(str), contentType, cPScenes);
        Log.d("METASEC", "runModelOnVideo ret:" + new String(bArrProcessFrames));
        return bArrProcessFrames;
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }

    Context getContext() {
        return this.mContext;
    }

    public byte[] vectorizeContent(String str, CPConfig.ContentType contentType, CPConfig.CPScenes cPScenes) {
        Log.d("METASEC", "vectorizeContent contentType:" + contentType.getContentType());
        if (str != null && !str.isEmpty() && contentType != null) {
            boolean z = false;
            Iterator<Long> it = this.mConfig.mScenes.iterator();
            while (it.hasNext()) {
                if (it.next().longValue() == cPScenes.getScene()) {
                    z = true;
                }
            }
            if (!z) {
                return null;
            }
            if (contentType == CPConfig.ContentType.kCPContentTypeImage) {
                return runModelOnImage(str, contentType, cPScenes);
            }
            if (contentType == CPConfig.ContentType.kCPContentTypeVideo) {
                return runModelOnVideo(str, contentType, cPScenes);
            }
            if (contentType != CPConfig.ContentType.kCPContentTypeText) {
                CPConfig.ContentType contentType2 = CPConfig.ContentType.kCPContentTypeAudio;
            }
        }
        return null;
    }

    public CPConfig.CPVerrificationResult verifyContent(String str, CPConfig.ContentType contentType, CPConfig.CPScenes cPScenes) {
        Log.d("METASEC", "verifyContent contentType:" + contentType.getContentType());
        if (str == null || str.isEmpty() || contentType == null) {
            return CPConfig.CPVerrificationResult.CPVerificationResultPass;
        }
        boolean z = false;
        Iterator<Long> it = this.mConfig.mScenes.iterator();
        while (it.hasNext()) {
            if (it.next().longValue() == cPScenes.getScene()) {
                z = true;
            }
        }
        if (!z) {
            return CPConfig.CPVerrificationResult.CPVerificationResultPass;
        }
        if (contentType != CPConfig.ContentType.kCPContentTypeImage && contentType != CPConfig.ContentType.kCPContentTypeVideo) {
            if (contentType == CPConfig.ContentType.kCPContentTypeText) {
                return runModelOnText(str, contentType, cPScenes);
            }
            CPConfig.ContentType contentType2 = CPConfig.ContentType.kCPContentTypeAudio;
        }
        return CPConfig.CPVerrificationResult.CPVerificationResultPass;
    }
}
