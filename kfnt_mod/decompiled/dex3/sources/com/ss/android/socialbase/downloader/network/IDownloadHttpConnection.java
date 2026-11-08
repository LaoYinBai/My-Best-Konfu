package com.ss.android.socialbase.downloader.network;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IDownloadHttpConnection extends IDownloadHeadHttpConnection {
    void end();

    InputStream getInputStream() throws IOException;
}
