package com.ss.android.socialbase.downloader.reader;

import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.segment.Buffer;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IStreamReader {
    void close();

    Buffer read() throws InterruptedException, IOException, BaseException;

    void recycle(Buffer buffer);
}
