package com.ss.android.socialbase.downloader.downloader;

import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class IndependentProcessDownloadService extends DownloadService {
    @Override // com.ss.android.socialbase.downloader.downloader.DownloadService, android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadComponentManager.setAppContext(this);
        if (DownloadComponentManager.getIndependentHolderCreator() == null) {
            DownloadComponentManager.setIndependentServiceCreator(new MultiProcCreater());
        }
        this.downloadServiceHandler = DownloadComponentManager.getIndependentDownloadServiceHandler();
        this.downloadServiceHandler.setDownloadService(new WeakReference(this));
    }
}
