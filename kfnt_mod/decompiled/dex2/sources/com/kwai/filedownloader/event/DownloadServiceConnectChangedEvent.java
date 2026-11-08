package com.kwai.filedownloader.event;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadServiceConnectChangedEvent extends b {
    private final ConnectStatus c;
    private final Class<?> d;

    public enum ConnectStatus {
        connected,
        disconnected,
        lost
    }

    public DownloadServiceConnectChangedEvent(ConnectStatus connectStatus, Class<?> cls) {
        super(com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ID);
        this.c = connectStatus;
        this.d = cls;
    }

    public ConnectStatus a() {
        return this.c;
    }
}
