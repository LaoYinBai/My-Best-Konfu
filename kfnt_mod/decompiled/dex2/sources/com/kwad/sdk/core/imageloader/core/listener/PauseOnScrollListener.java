package com.kwad.sdk.core.imageloader.core.listener;

import android.widget.AbsListView;
import com.kwad.sdk.core.imageloader.core.ImageLoader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PauseOnScrollListener implements AbsListView.OnScrollListener {
    private final AbsListView.OnScrollListener externalListener;
    private ImageLoader imageLoader;
    private final boolean pauseOnFling;
    private final boolean pauseOnScroll;

    public PauseOnScrollListener(ImageLoader imageLoader, boolean z, boolean z2) {
        this(imageLoader, z, z2, null);
    }

    public PauseOnScrollListener(ImageLoader imageLoader, boolean z, boolean z2, AbsListView.OnScrollListener onScrollListener) {
        this.imageLoader = imageLoader;
        this.pauseOnScroll = z;
        this.pauseOnFling = z2;
        this.externalListener = onScrollListener;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.externalListener != null) {
            this.externalListener.onScroll(absListView, i, i2, i3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x000d  */
    @Override // android.widget.AbsListView.OnScrollListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onScrollStateChanged(android.widget.AbsListView r2, int r3) {
        /*
            r1 = this;
            switch(r3) {
                case 0: goto L13;
                case 1: goto L9;
                case 2: goto L4;
                default: goto L3;
            }
        L3:
            goto L18
        L4:
            boolean r0 = r1.pauseOnFling
            if (r0 == 0) goto L18
            goto Ld
        L9:
            boolean r0 = r1.pauseOnScroll
            if (r0 == 0) goto L18
        Ld:
            com.kwad.sdk.core.imageloader.core.ImageLoader r0 = r1.imageLoader
            r0.pause()
            goto L18
        L13:
            com.kwad.sdk.core.imageloader.core.ImageLoader r0 = r1.imageLoader
            r0.resume()
        L18:
            android.widget.AbsListView$OnScrollListener r0 = r1.externalListener
            if (r0 == 0) goto L21
            android.widget.AbsListView$OnScrollListener r0 = r1.externalListener
            r0.onScrollStateChanged(r2, r3)
        L21:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.imageloader.core.listener.PauseOnScrollListener.onScrollStateChanged(android.widget.AbsListView, int):void");
    }
}
