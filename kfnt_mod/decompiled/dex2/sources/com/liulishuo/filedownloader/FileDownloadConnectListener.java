package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.IDownloadEvent;
import com.liulishuo.filedownloader.event.IDownloadListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class FileDownloadConnectListener extends IDownloadListener {
    private DownloadServiceConnectChangedEvent.ConnectStatus mConnectStatus;

    public abstract void connected();

    public abstract void disconnected();

    @Override // com.liulishuo.filedownloader.event.IDownloadListener
    public boolean callback(IDownloadEvent iDownloadEvent) {
        if (!(iDownloadEvent instanceof DownloadServiceConnectChangedEvent)) {
            return false;
        }
        this.mConnectStatus = ((DownloadServiceConnectChangedEvent) iDownloadEvent).getStatus();
        if (this.mConnectStatus == DownloadServiceConnectChangedEvent.ConnectStatus.connected) {
            connected();
            return false;
        }
        disconnected();
        return false;
    }

    public DownloadServiceConnectChangedEvent.ConnectStatus getConnectStatus() {
        return this.mConnectStatus;
    }
}
