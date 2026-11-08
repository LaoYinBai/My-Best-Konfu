package com.ss.android.socialbase.downloader.impls;

import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.IDownloadProxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadProxy {
    private static volatile IDownloadProxy downloadIndependentProxy;
    private static volatile IDownloadProxy downloadProxy;

    public static IDownloadProxy get(boolean z) {
        if (z && DownloadComponentManager.supportMultiProc()) {
            if (downloadIndependentProxy == null) {
                synchronized (DownloadProxy.class) {
                    if (downloadIndependentProxy == null) {
                        downloadIndependentProxy = DownloadComponentManager.getIndependentHolderCreator().createProxy();
                    }
                }
            }
            return downloadIndependentProxy;
        }
        if (downloadProxy == null) {
            synchronized (DownloadProxy.class) {
                if (downloadProxy == null) {
                    downloadProxy = new ProcessDownloadHandler();
                }
            }
        }
        return downloadProxy;
    }
}
