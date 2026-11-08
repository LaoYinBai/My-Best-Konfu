package com.kwad.sdk.core.download.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.core.IProgressRemoteView;
import com.kwad.sdk.api.core.RemoteViewBuilder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private IProgressRemoteView a;

    public c(IProgressRemoteView iProgressRemoteView) {
        this.a = iProgressRemoteView;
    }

    public static c a(Context context, int i, boolean z) {
        c cVar;
        int sDKType = KsAdSDKImpl.get().getSDKType();
        int apiVersionCode = KsAdSDKImpl.get().getApiVersionCode();
        boolean z2 = true;
        if ((sDKType != 1 || apiVersionCode < 3031000) && (sDKType != 2 || apiVersionCode < 3031800)) {
            z2 = false;
        }
        if (z2) {
            try {
                cVar = new c(RemoteViewBuilder.createProgressView(context, i, z));
            } catch (Throwable th) {
                com.kwad.sdk.core.d.a.b(th);
                cVar = null;
            }
        } else {
            cVar = new c(RemoteViewBuilder.createProgressView(context));
        }
        if (cVar == null) {
            try {
                return new c(RemoteViewBuilder.createProgressView(context));
            } catch (Throwable th2) {
                com.kwad.sdk.core.d.a.b(th2);
            }
        }
        return cVar;
    }

    RemoteViews a() {
        if (this.a != null) {
            return this.a.build();
        }
        return null;
    }

    public void a(int i, int i2, boolean z) {
        if (this.a != null) {
            this.a.setProgress(i, i2, z);
        }
    }

    public void a(Bitmap bitmap) {
        if (this.a != null) {
            this.a.setIcon(bitmap);
        }
    }

    public void a(String str) {
        if (this.a != null) {
            this.a.setName(str);
        }
    }

    public void a(boolean z) {
        try {
            if (this.a != null) {
                this.a.setControlBtnPaused(z);
            }
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.b(th);
        }
    }

    public void b(String str) {
        if (this.a != null) {
            this.a.setStatus(str);
        }
    }

    public void c(String str) {
        if (this.a != null) {
            this.a.setSize(str);
        }
    }

    public void d(String str) {
        if (this.a != null) {
            this.a.setPercentNum(str);
        }
    }
}
