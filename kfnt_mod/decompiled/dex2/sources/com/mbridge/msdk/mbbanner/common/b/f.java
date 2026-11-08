package com.mbridge.msdk.mbbanner.common.b;

import com.mbridge.msdk.foundation.download.download.H5DownLoadManager;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: DownloadBannerUrlListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f implements H5DownLoadManager.IH5SourceDownloadListener {
    private static final String a = "f";
    private com.mbridge.msdk.mbbanner.common.c.b b;
    private String c;

    public f(com.mbridge.msdk.mbbanner.common.c.b bVar, String str) {
        this.b = bVar;
        this.c = str;
    }

    @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
    public void onSuccess(String str) {
        s.b(a, "DownloadBannerUrlListener HTML SUCCESS:" + str);
        if (this.b != null) {
            this.b.a(this.c, 3, str, true);
        }
    }

    @Override // com.mbridge.msdk.foundation.download.download.H5DownLoadManager.H5ResDownloadListerInter
    public void onFailed(String str, String str2) {
        s.b(a, "DownloadBannerUrlListener HTML FAIL:" + str);
        if (this.b != null) {
            this.b.a(this.c, 3, str, false);
        }
    }
}
