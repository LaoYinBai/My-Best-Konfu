package com.mbridge.msdk.out;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.splash.d.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBSplashHandler {
    private static boolean canSkip = true;
    private static int defCountDownS = 5;
    private static int logoSizeH = 0;
    private static int logoSizeW = 0;
    private static int orientation = 1;
    private c splashProvider;

    public MBSplashHandler(String str, String str2) {
        this(str, str2, canSkip, defCountDownS);
    }

    public MBSplashHandler(Activity activity, String str, String str2) {
        this(activity, str, str2, canSkip, defCountDownS);
    }

    public MBSplashHandler(String str, String str2, boolean z, int i) {
        this(str, str2, z, i, orientation, logoSizeH, logoSizeW);
    }

    public MBSplashHandler(Activity activity, String str, String str2, boolean z, int i) {
        this(activity, str, str2, z, i, orientation, logoSizeH, logoSizeW);
    }

    public MBSplashHandler(String str, String str2, boolean z, int i, int i2, int i3, int i4) {
        String strD = w.d(str2);
        if (!TextUtils.isEmpty(strD)) {
            w.a(str2, strD);
        }
        this.splashProvider = new c(null, str, str2);
        this.splashProvider.b(z);
        this.splashProvider.b(i);
        this.splashProvider.a(i2);
        this.splashProvider.a(i3, i4);
    }

    public MBSplashHandler(Activity activity, String str, String str2, boolean z, int i, int i2, int i3, int i4) {
        String strD = w.d(str2);
        if (!TextUtils.isEmpty(strD)) {
            w.a(str2, strD);
        }
        this.splashProvider = new c(activity, str, str2);
        this.splashProvider.b(z);
        this.splashProvider.b(i);
        this.splashProvider.a(i2);
        this.splashProvider.a(i3, i4);
    }

    public void setLoadTimeOut(long j) {
        if (this.splashProvider != null) {
            this.splashProvider.a(j);
        }
    }

    public void setSplashLoadListener(MBSplashLoadListener mBSplashLoadListener) {
        if (this.splashProvider != null) {
            this.splashProvider.a(mBSplashLoadListener);
        }
    }

    public void setSplashShowListener(MBSplashShowListener mBSplashShowListener) {
        if (this.splashProvider != null) {
            this.splashProvider.a(mBSplashShowListener);
        }
    }

    public void preLoad() throws Throwable {
        if (this.splashProvider != null) {
            this.splashProvider.b("");
        }
    }

    public void preLoadByToken(String str) throws Throwable {
        if (this.splashProvider != null) {
            this.splashProvider.a(str);
        }
    }

    public void loadAndShow(ViewGroup viewGroup) throws Throwable {
        if (this.splashProvider != null) {
            this.splashProvider.b("", viewGroup);
        }
    }

    public String getRequestId() {
        return this.splashProvider != null ? this.splashProvider.e() : "";
    }

    public void loadAndShowByToken(String str, ViewGroup viewGroup) throws Throwable {
        if (this.splashProvider != null) {
            this.splashProvider.a(str, viewGroup);
        }
    }

    public boolean isReady() {
        return isReady("");
    }

    public boolean isReady(String str) {
        if (this.splashProvider != null) {
            return this.splashProvider.c(str);
        }
        return false;
    }

    public void show(ViewGroup viewGroup) {
        if (this.splashProvider != null) {
            this.splashProvider.d("", viewGroup);
        }
    }

    public void show(ViewGroup viewGroup, String str) {
        if (this.splashProvider != null) {
            this.splashProvider.c(str, viewGroup);
        }
    }

    public void setLogoView(View view, int i, int i2) {
        if (this.splashProvider != null) {
            this.splashProvider.a(view, new RelativeLayout.LayoutParams(i, i2));
        }
    }

    public void setOrientation(int i) {
        if (this.splashProvider != null) {
            this.splashProvider.a(i);
        }
    }

    public void setDevCloseView(ViewGroup viewGroup) {
        if (this.splashProvider != null) {
            this.splashProvider.a(viewGroup);
        }
    }

    public void setSupportZoomOut(boolean z) {
        if (this.splashProvider != null) {
            this.splashProvider.c(z);
        }
    }

    public ViewGroup createZoomOutByType(ZoomOutTypeEnum zoomOutTypeEnum) {
        if (this.splashProvider != null) {
            return this.splashProvider.a(zoomOutTypeEnum);
        }
        return null;
    }

    public void zoomOutAttacked() {
        if (this.splashProvider != null) {
            this.splashProvider.f();
        }
    }

    public void zoomOutPlayFinish() {
        if (this.splashProvider != null) {
            this.splashProvider.g();
        }
    }

    public void onResume() {
        if (this.splashProvider != null) {
            this.splashProvider.h();
        }
    }

    public void onPause() {
        if (this.splashProvider != null) {
            this.splashProvider.i();
        }
    }

    public void onDestroy() {
        if (this.splashProvider != null) {
            this.splashProvider.j();
        }
    }

    public void allowClickSplash(boolean z) {
        if (this.splashProvider != null) {
            this.splashProvider.d(z);
        }
    }
}
