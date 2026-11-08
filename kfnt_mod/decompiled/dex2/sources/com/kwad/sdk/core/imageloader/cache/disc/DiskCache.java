package com.kwad.sdk.core.imageloader.cache.disc;

import android.graphics.Bitmap;
import com.kwad.sdk.core.imageloader.utils.IoUtils;
import java.io.File;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface DiskCache {
    void clear();

    void close();

    File get(String str);

    File getDirectory();

    boolean remove(String str);

    boolean save(String str, Bitmap bitmap);

    boolean save(String str, InputStream inputStream, IoUtils.CopyListener copyListener);
}
