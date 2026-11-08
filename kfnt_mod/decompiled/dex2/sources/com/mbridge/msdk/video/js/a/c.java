package com.mbridge.msdk.video.js.a;

import android.app.Activity;
import android.content.Context;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.out.NativeListener;
import com.mbridge.msdk.video.js.c;

/* compiled from: DefaultJSCommon.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements com.mbridge.msdk.video.js.c {
    protected String j;
    protected com.mbridge.msdk.videocommon.d.c k;
    protected com.mbridge.msdk.click.b l;
    protected boolean a = false;
    protected boolean b = false;
    protected int c = 0;
    protected int d = 0;
    protected int e = 0;
    protected int f = 0;
    protected int g = 0;
    protected int h = 1;
    protected int i = -1;
    public c.a m = new a();
    protected int n = 2;

    @Override // com.mbridge.msdk.video.js.c
    public void g() {
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void a(int i) {
        this.n = i;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void c(int i) {
        this.c = i;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void b(int i) {
        this.d = i;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void d(int i) {
        this.e = i;
    }

    public final int k() {
        if (this.c == 0 && this.b) {
            this.c = 1;
        }
        return this.c;
    }

    public final int l() {
        if (this.d == 0 && this.b) {
            this.d = 1;
        }
        return this.d;
    }

    public final int m() {
        if (this.e == 0 && this.b) {
            this.e = 1;
        }
        return this.e;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void e(int i) {
        this.f = i;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final int a() {
        return this.f;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void f(int i) {
        this.g = i;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final int b() {
        return this.g;
    }

    public final boolean n() {
        return this.b;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void a(boolean z) {
        s.a("DefaultJSCommon", "setIsShowingTransparent:" + z);
        this.b = z;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final boolean c() {
        return this.a;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void b(boolean z) {
        this.a = z;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void a(String str) {
        s.a("DefaultJSCommon", "setUnitId:" + str);
        this.j = str;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void a(c.a aVar) {
        s.a("DefaultJSCommon", "setTrackingListener:" + aVar);
        this.m = aVar;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void a(com.mbridge.msdk.videocommon.d.c cVar) {
        s.a("DefaultJSCommon", "setSetting:" + cVar);
        this.k = cVar;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void f() {
        s.a("DefaultJSCommon", "release");
        if (this.l != null) {
            this.l.a(false);
            this.l.a((NativeListener.NativeTrackingListener) null);
            this.l.a();
        }
    }

    @Override // com.mbridge.msdk.video.js.c
    public void a(int i, String str) {
        s.a("DefaultJSCommon", "statistics,type:" + i + ",json:" + str);
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void g(int i) {
        this.i = i;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final int h() {
        return this.i;
    }

    @Override // com.mbridge.msdk.video.js.c
    public String h(int i) {
        s.a("DefaultJSCommon", "getSDKInfo");
        return "{}";
    }

    @Override // com.mbridge.msdk.video.js.c
    public final void i(int i) {
        s.a("DefaultJSCommon", "setAlertDialogRole " + i);
        this.h = i;
    }

    @Override // com.mbridge.msdk.video.js.c
    public final int i() {
        s.a("DefaultJSCommon", "getAlertDialogRole " + this.h);
        return this.h;
    }

    @Override // com.mbridge.msdk.video.js.c
    public void a(Activity activity) {
        s.a("DefaultJSCommon", "setActivity ");
    }

    @Override // com.mbridge.msdk.video.js.c
    public void a(Context context) {
        s.a("DefaultJSCommon", "setViewContext ");
    }

    @Override // com.mbridge.msdk.video.js.c
    public void a(com.mbridge.msdk.video.bt.module.a.b bVar) {
        s.a("DefaultJSCommon", "setInstallDialogCallback ");
    }

    @Override // com.mbridge.msdk.video.js.c
    public String d() {
        s.a("DefaultJSCommon", "init");
        return "{}";
    }

    @Override // com.mbridge.msdk.video.js.d
    public void click(int i, String str) {
        s.a("DefaultJSCommon", "click:type" + i + ",pt:" + str);
    }

    @Override // com.mbridge.msdk.video.js.d
    public void handlerH5Exception(int i, String str) {
        s.a("DefaultJSCommon", "handlerH5Exception,code=" + i + ",msg:" + str);
    }

    @Override // com.mbridge.msdk.video.js.c
    public void e() {
        s.a("DefaultJSCommon", "finish");
    }

    @Override // com.mbridge.msdk.video.js.c
    public void b(String str) {
        s.a("DefaultJSCommon", "setNotchArea");
    }

    @Override // com.mbridge.msdk.video.js.c
    public String j() {
        s.a("DefaultJSCommon", "getNotchArea");
        return null;
    }

    /* compiled from: DefaultJSCommon.java */
    public static class b implements c.a {
        private com.mbridge.msdk.video.js.c a;
        private c.a b;

        public b(com.mbridge.msdk.video.js.c cVar, c.a aVar) {
            this.a = cVar;
            this.b = aVar;
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final boolean onInterceptDefaultLoadingDialog() {
            return this.b != null && this.b.onInterceptDefaultLoadingDialog();
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onShowLoading(Campaign campaign) {
            if (this.b != null) {
                this.b.onShowLoading(campaign);
            }
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onDismissLoading(Campaign campaign) {
            if (this.b != null) {
                this.b.onDismissLoading(campaign);
            }
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onStartRedirection(Campaign campaign, String str) {
            if (this.b != null) {
                this.b.onStartRedirection(campaign, str);
            }
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onFinishRedirection(Campaign campaign, String str) {
            if (this.b != null) {
                this.b.onFinishRedirection(campaign, str);
            }
            if (this.a != null) {
                this.a.e();
            }
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onRedirectionFailed(Campaign campaign, String str) {
            if (this.b != null) {
                this.b.onRedirectionFailed(campaign, str);
            }
            if (this.a != null) {
                this.a.e();
            }
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onDownloadStart(Campaign campaign) {
            if (this.b != null) {
                this.b.onDownloadStart(campaign);
            }
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onDownloadFinish(Campaign campaign) {
            if (this.b != null) {
                this.b.onDownloadFinish(campaign);
            }
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public final void onDownloadProgress(int i) {
            if (this.b != null) {
                this.b.onDownloadProgress(i);
            }
        }

        @Override // com.mbridge.msdk.video.js.c.a
        public final void a() {
            if (this.b != null) {
                this.b.a();
            }
        }

        @Override // com.mbridge.msdk.video.js.c.a
        public final void a(boolean z) {
            if (this.b != null) {
                this.b.a(z);
            }
        }

        @Override // com.mbridge.msdk.video.js.c.a
        public final void a(int i, String str) {
            if (this.b != null) {
                this.b.a(i, str);
            }
        }

        @Override // com.mbridge.msdk.video.js.c.a
        public final void b() {
            if (this.b != null) {
                this.b.b();
            }
        }
    }

    /* compiled from: DefaultJSCommon.java */
    public static class a implements c.a {
        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public boolean onInterceptDefaultLoadingDialog() {
            s.a("DefaultJSCommon", "onInterceptDefaultLoadingDialog");
            return false;
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public void onShowLoading(Campaign campaign) {
            s.a("DefaultJSCommon", "onShowLoading,campaign:" + campaign);
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public void onDismissLoading(Campaign campaign) {
            s.a("DefaultJSCommon", "onDismissLoading,campaign:" + campaign);
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public void onStartRedirection(Campaign campaign, String str) {
            s.a("DefaultJSCommon", "onStartRedirection,campaign:" + campaign + ",url:" + str);
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public void onFinishRedirection(Campaign campaign, String str) {
            s.a("DefaultJSCommon", "onFinishRedirection,campaign:" + campaign + ",url:" + str);
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public void onRedirectionFailed(Campaign campaign, String str) {
            s.a("DefaultJSCommon", "onFinishRedirection,campaign:" + campaign + ",url:" + str);
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public void onDownloadStart(Campaign campaign) {
            s.a("DefaultJSCommon", "onDownloadStart,campaign:" + campaign);
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public void onDownloadFinish(Campaign campaign) {
            s.a("DefaultJSCommon", "onDownloadFinish,campaign:" + campaign);
        }

        @Override // com.mbridge.msdk.out.NativeListener.NativeTrackingListener
        public void onDownloadProgress(int i) {
            s.a("DefaultJSCommon", "onDownloadProgress,progress:" + i);
        }

        @Override // com.mbridge.msdk.video.js.c.a
        public void a() {
            s.a("DefaultJSCommon", "onInitSuccess");
        }

        @Override // com.mbridge.msdk.video.js.c.a
        public void a(boolean z) {
            s.a("DefaultJSCommon", "onStartInstall");
        }

        @Override // com.mbridge.msdk.video.js.c.a
        public void a(int i, String str) {
            s.a("DefaultJSCommon", "onH5Error,code:" + i + "，msg:" + str);
        }

        @Override // com.mbridge.msdk.video.js.c.a
        public void b() {
            s.a("DefaultJSCommon", "videoLocationReady");
        }
    }
}
