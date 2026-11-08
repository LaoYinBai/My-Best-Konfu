package com.ss.union.game.sdk.core.glide.util;

import com.ss.union.game.sdk.core.glide.ListPreloader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {
    private final int[] a;

    public FixedPreloadSizeProvider(int i, int i2) {
        this.a = new int[]{i, i2};
    }

    @Override // com.ss.union.game.sdk.core.glide.ListPreloader.PreloadSizeProvider
    public int[] getPreloadSize(T t, int i, int i2) {
        return this.a;
    }
}
