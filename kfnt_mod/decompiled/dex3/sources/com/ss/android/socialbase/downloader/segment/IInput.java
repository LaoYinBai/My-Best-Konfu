package com.ss.android.socialbase.downloader.segment;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IInput {
    @NonNull
    Buffer read() throws InterruptedException, StreamClosedException;
}
