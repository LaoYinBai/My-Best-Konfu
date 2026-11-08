package com.bumptech.glide;

import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
interface DownloadOptions {
    FutureTarget<File> downloadOnly(int i, int i2);

    <Y extends Target<File>> Y downloadOnly(Y y);
}
