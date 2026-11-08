package com.mbridge.msdk.mbbanner.common.b;

import android.graphics.Bitmap;
import com.mbridge.msdk.foundation.tools.s;

/* compiled from: DownloadImageListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g implements com.mbridge.msdk.foundation.same.c.c {
    private static final String a = "g";
    private com.mbridge.msdk.mbbanner.common.c.b b;
    private String c;

    public g(com.mbridge.msdk.mbbanner.common.c.b bVar, String str) {
        if (bVar != null) {
            this.b = bVar;
        }
        this.c = str;
    }

    @Override // com.mbridge.msdk.foundation.same.c.c
    public void onSuccessLoad(Bitmap bitmap, String str) {
        s.b(a, "DownloadImageListener campaign image success");
        this.b.a(this.c, 1, str, true);
    }

    @Override // com.mbridge.msdk.foundation.same.c.c
    public void onFailedLoad(String str, String str2) {
        s.b(a, "DownloadImageListener campaign image fail");
        this.b.a(this.c, 1, str2, false);
    }
}
