package com.kwad.sdk.core.download.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.kwad.sdk.api.core.ICompletedRemoteView;
import com.kwad.sdk.api.core.RemoteViewBuilder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private ICompletedRemoteView a;

    public b(ICompletedRemoteView iCompletedRemoteView) {
        this.a = iCompletedRemoteView;
    }

    public static b a(Context context) {
        return new b(RemoteViewBuilder.createCompletedView(context));
    }

    RemoteViews a() {
        if (this.a != null) {
            return this.a.build();
        }
        return null;
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
            this.a.setInstallText(str);
        }
    }
}
