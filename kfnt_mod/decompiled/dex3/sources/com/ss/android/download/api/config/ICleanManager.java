package com.ss.android.download.api.config;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ICleanManager {
    void clearStorageSpace();

    boolean enableScan();

    boolean enableShowDownloadCleanDialog();

    boolean enableShowInstallCleanDialog();

    long getLastScanSpaceSize();

    boolean handleCleanSpace(int i, String str, boolean z, long j);

    void setModelId(long j);

    void startScan();
}
