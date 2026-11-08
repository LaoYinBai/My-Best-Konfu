package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
interface d {
    Bitmap get(int i, int i2, Bitmap.Config config);

    int getSize(Bitmap bitmap);

    String logBitmap(int i, int i2, Bitmap.Config config);

    String logBitmap(Bitmap bitmap);

    void put(Bitmap bitmap);

    Bitmap removeLast();
}
