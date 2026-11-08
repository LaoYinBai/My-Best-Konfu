package com.kwai.filedownloader;

import com.kwai.filedownloader.event.DownloadServiceConnectChangedEvent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class e extends com.kwai.filedownloader.event.c {
    private DownloadServiceConnectChangedEvent.ConnectStatus a;

    public abstract void a();

    @Override // com.kwai.filedownloader.event.c
    public boolean a(com.kwai.filedownloader.event.b bVar) {
        if (!(bVar instanceof DownloadServiceConnectChangedEvent)) {
            return false;
        }
        this.a = ((DownloadServiceConnectChangedEvent) bVar).a();
        if (this.a == DownloadServiceConnectChangedEvent.ConnectStatus.connected) {
            a();
            return false;
        }
        b();
        return false;
    }

    public abstract void b();

    public DownloadServiceConnectChangedEvent.ConnectStatus c() {
        return this.a;
    }
}
