package com.liulishuo.filedownloader.event;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
interface IDownloadEventPool {
    boolean addListener(String str, IDownloadListener iDownloadListener);

    void asyncPublishInNewThread(IDownloadEvent iDownloadEvent);

    boolean publish(IDownloadEvent iDownloadEvent);

    boolean removeListener(String str, IDownloadListener iDownloadListener);
}
