package com.kwad.sdk.ec.download;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.ksad.download.c.b;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.KsAppDownloadListener;
import com.kwad.sdk.core.diskcache.ApkCacheManager;
import com.kwad.sdk.core.download.DownloadParams;
import com.kwad.sdk.core.download.DownloadStatusManager;
import com.kwad.sdk.core.download.c;
import com.kwad.sdk.core.download.f;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.export.proxy.AdDownloadProxy;
import com.kwad.sdk.utils.ag;
import com.kwad.sdk.utils.an;
import com.kwad.sdk.utils.i;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements c {
    public String a;

    @NonNull
    private final AdTemplate b;

    @NonNull
    private final AdInfo c;
    private KsAppDownloadListener d;
    private boolean e;
    private final boolean f;

    public a(@NonNull AdTemplate adTemplate, KsAppDownloadListener ksAppDownloadListener) {
        this.b = adTemplate;
        this.c = d.j(adTemplate);
        DownloadStatusManager.a().a(this, this.b);
        DownloadStatusManager.a().a(this.b);
        this.f = d.j(this.b).downloadSafeInfo.downloadPauseEnable;
        this.d = ksAppDownloadListener;
    }

    private int b(Context context) {
        h();
        return 1;
    }

    private void f() {
        KsAppDownloadListener ksAppDownloadListener = this.d;
        if (ksAppDownloadListener == null) {
        }
        int i = this.c.progress;
        switch (this.c.status) {
            case 0:
                ksAppDownloadListener.onIdle();
                break;
            case 1:
                ksAppDownloadListener.onProgressUpdate(0);
                if (ksAppDownloadListener instanceof com.kwad.sdk.core.download.a.c) {
                    ((com.kwad.sdk.core.download.a.c) ksAppDownloadListener).onDownloadStarted();
                    break;
                } else {
                    try {
                        ksAppDownloadListener.onDownloadStarted();
                        break;
                    } catch (Throwable th) {
                        com.kwad.sdk.core.d.a.b(th);
                        return;
                    }
                }
            case 2:
            case 3:
                ksAppDownloadListener.onProgressUpdate(i);
                break;
            case 4:
                if (ksAppDownloadListener instanceof com.kwad.sdk.core.download.a.c) {
                    ((com.kwad.sdk.core.download.a.c) ksAppDownloadListener).a(i);
                    break;
                }
                break;
            case 7:
                ksAppDownloadListener.onDownloadFailed();
                break;
            case 8:
            case 9:
                ksAppDownloadListener.onDownloadFinished();
                break;
            case 12:
                ksAppDownloadListener.onInstalled();
                break;
        }
    }

    private void g() {
        AdDownloadProxy proxyForDownload;
        String str = this.c.adBaseInfo.appPackageName;
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return;
        }
        if (ag.a(context, str)) {
            this.c.status = 12;
            return;
        }
        if (this.c.status == 12) {
            this.c.status = 0;
            this.c.progress = 0;
        }
        if (this.c.status == 8) {
            String str2 = this.c.downloadFilePath;
            if (TextUtils.isEmpty(str2) || !new File(str2).exists()) {
                this.c.status = 0;
                this.c.progress = 0;
            }
        }
        if (this.c.status != 0 || (proxyForDownload = KsAdSDKImpl.get().getProxyForDownload()) == null) {
            return;
        }
        String downloadFilePath = proxyForDownload.getDownloadFilePath(DownloadParams.transform(this.c));
        if (TextUtils.isEmpty(downloadFilePath) || !new File(downloadFilePath).exists()) {
            return;
        }
        this.c.downloadFilePath = downloadFilePath;
        this.c.status = 8;
    }

    private void h() {
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return;
        }
        if (b.a(context)) {
            com.kwad.sdk.core.download.d.a(context, this.c, Build.VERSION.SDK_INT >= 26 && an.a());
        } else {
            com.kwad.sdk.core.d.a.e("ApkDownloadHelper", "no network while download app");
        }
    }

    private void i() {
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return;
        }
        com.kwad.sdk.core.download.d.a(context, this.c.downloadId);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int a(Context context) {
        int iB;
        g();
        switch (this.c.status) {
            case 0:
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
                iB = b(context);
                break;
            case 2:
            case 3:
                if (!this.e || !this.f) {
                    iB = 1;
                    break;
                } else {
                    i();
                    iB = 0;
                    break;
                }
                break;
            case 8:
            case 9:
            case 11:
                d();
                iB = 0;
                break;
            case 10:
            default:
                iB = 0;
                break;
            case 12:
                c();
                iB = 0;
                break;
        }
        this.e = false;
        return iB;
    }

    @Override // com.kwad.sdk.core.download.c
    public String a() {
        return this.c.downloadId;
    }

    @Override // com.kwad.sdk.core.download.c
    public void a(String str, int i, int i2, int i3) {
        if (this.c.downloadId.equals(str)) {
            this.c.status = 3;
            this.c.progress = i;
            this.c.soFarBytes = i2;
            this.c.totalBytes = i3;
            f();
        }
    }

    @Override // com.kwad.sdk.core.download.c
    public void a(String str, int i, f fVar) {
        i.a(new Runnable() { // from class: com.kwad.sdk.ec.download.a.1
            @Override // java.lang.Runnable
            public void run() {
                com.kwad.sdk.home.download.a.a().b(a.this.b);
                com.kwad.sdk.home.download.a.a().c(a.this.b);
            }
        });
        com.kwad.sdk.core.a.a().d(d.j(this.b).downloadId);
        this.c.status = 12;
        f();
    }

    @Override // com.kwad.sdk.core.download.c
    public void a(String str, int i, String str2, f fVar) {
        if (this.c.downloadId.equals(str)) {
            this.c.status = 7;
            this.a = str2;
            f();
        }
    }

    @Override // com.kwad.sdk.core.download.c
    public void a(String str, f fVar) {
        if (this.c.downloadId.equals(str)) {
            if (this.c.status != 1) {
                this.c.status = 1;
            }
            f();
        }
    }

    @Override // com.kwad.sdk.core.download.c
    public void a(String str, String str2, f fVar) {
        if (this.c.downloadId.equals(str)) {
            this.c.downloadFilePath = str2;
            this.c.progress = 100;
            this.c.status = 8;
            ApkCacheManager.a().b();
            f();
        }
    }

    public boolean a(Context context, String str) {
        if (Build.VERSION.SDK_INT < 26) {
            return false;
        }
        boolean zCanRequestPackageInstalls = context.getPackageManager().canRequestPackageInstalls();
        if (!KsAdSDKImpl.get().isEnableInstallPermission() || zCanRequestPackageInstalls || !an.a()) {
            return false;
        }
        Intent intent = new Intent("intent.action.requestInstallPermission");
        intent.putExtra("needAllowDialog", true);
        intent.putExtra("filePath", str);
        intent.addFlags(268435456);
        context.startActivity(intent);
        return true;
    }

    @Override // com.kwad.sdk.core.download.c
    public String b() {
        return this.c.adBaseInfo.appPackageName;
    }

    @Override // com.kwad.sdk.core.download.c
    public void b(String str, f fVar) {
        if (this.c.downloadId.equals(str)) {
            this.c.status = 4;
            f();
        }
    }

    public void c() {
        i.a(new Runnable() { // from class: com.kwad.sdk.ec.download.a.2
            @Override // java.lang.Runnable
            public void run() {
                com.kwad.sdk.home.download.a.a().d(a.this.b);
            }
        });
        if (ag.c(KsAdSDKImpl.get().getContext(), com.kwad.sdk.core.response.a.a.v(this.c))) {
            com.kwad.sdk.core.report.a.e(this.b);
        }
    }

    @Override // com.kwad.sdk.core.download.c
    public void c(String str, f fVar) {
        if (this.c.downloadId.equals(str)) {
            this.c.status = 2;
            f();
        }
    }

    public void d() {
        String str = this.c.downloadFilePath;
        Context context = KsAdSDKImpl.get().getContext();
        if (context != null && !TextUtils.isEmpty(str)) {
            if (a(context, str)) {
                return;
            }
            KsAdSDKImpl.get().getProxyForAdInstall().installApp(context, str);
        } else {
            com.kwad.sdk.core.d.a.e("ApkDownloadHelper", "openApp fail appContext:" + context + "--filePath:" + str);
        }
    }

    @Override // com.kwad.sdk.core.download.c
    public void d(String str, f fVar) {
        if (this.c.downloadId.equals(str)) {
            this.c.status = 5;
            f();
        }
    }

    public void e() {
        this.d = null;
        DownloadStatusManager.a().a(this);
    }

    @Override // com.kwad.sdk.core.download.c
    public void e(String str, f fVar) {
    }
}
