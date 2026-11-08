package com.mbridge.msdk.out;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface IDownloadListener {
    void onEnd(int i, int i2, String str);

    void onProgressUpdate(int i);

    void onStart();

    void onStatus(int i);
}
