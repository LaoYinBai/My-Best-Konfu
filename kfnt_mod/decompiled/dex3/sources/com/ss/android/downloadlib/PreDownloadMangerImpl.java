package com.ss.android.downloadlib;

import com.ss.android.download.api.PreDownloadManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PreDownloadMangerImpl implements PreDownloadManager {
    private static PreDownloadMangerImpl sInstance;

    @Override // com.ss.android.download.api.PreDownloadManager
    public void getSilentDownloadAppList() {
    }

    @Override // com.ss.android.download.api.PreDownloadManager
    public void tryStartSilentDownload(String str, boolean z, long j, String str2) {
    }

    private PreDownloadMangerImpl() {
    }

    public static PreDownloadMangerImpl getInstance() {
        if (sInstance == null) {
            synchronized (PreDownloadMangerImpl.class) {
                if (sInstance == null) {
                    sInstance = new PreDownloadMangerImpl();
                }
            }
        }
        return sInstance;
    }
}
