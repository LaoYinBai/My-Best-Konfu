package com.ss.union.game.sdk.core.glide.util;

import android.view.View;
import com.ss.union.game.sdk.core.glide.ListPreloader;
import com.ss.union.game.sdk.core.glide.request.target.SizeReadyCallback;
import com.ss.union.game.sdk.core.glide.request.target.ViewTarget;
import com.ss.union.game.sdk.core.glide.request.transition.Transition;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ViewPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T>, SizeReadyCallback {
    private int[] a;
    private a b;

    public ViewPreloadSizeProvider() {
    }

    public ViewPreloadSizeProvider(View view) {
        this.b = new a(view, this);
    }

    @Override // com.ss.union.game.sdk.core.glide.ListPreloader.PreloadSizeProvider
    public int[] getPreloadSize(T t, int i, int i2) {
        if (this.a == null) {
            return null;
        }
        return Arrays.copyOf(this.a, this.a.length);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.SizeReadyCallback
    public void onSizeReady(int i, int i2) {
        this.a = new int[]{i, i2};
        this.b = null;
    }

    public void setView(View view) {
        if (this.a == null && this.b == null) {
            this.b = new a(view, this);
        }
    }

    private static final class a extends ViewTarget<View, Object> {
        @Override // com.ss.union.game.sdk.core.glide.request.target.Target
        public void onResourceReady(Object obj, Transition<? super Object> transition) {
        }

        a(View view, SizeReadyCallback sizeReadyCallback) {
            super(view);
            getSize(sizeReadyCallback);
        }
    }
}
