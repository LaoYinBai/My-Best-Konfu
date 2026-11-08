package com.ss.union.game.sdk.core.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.io.File;
import java.net.URL;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
interface b<T> {
    T load(Bitmap bitmap);

    T load(Drawable drawable);

    T load(Uri uri);

    T load(File file);

    T load(Integer num);

    T load(Object obj);

    T load(String str);

    @Deprecated
    T load(URL url);

    T load(byte[] bArr);
}
