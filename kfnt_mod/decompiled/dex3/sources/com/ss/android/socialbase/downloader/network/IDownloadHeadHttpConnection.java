package com.ss.android.socialbase.downloader.network;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IDownloadHeadHttpConnection {
    void cancel();

    int getResponseCode() throws IOException;

    String getResponseHeaderField(String str);
}
