package com.mbridge.msdk.foundation.download.core;

import com.mbridge.msdk.foundation.download.DownloadPriority;
import java.util.concurrent.FutureTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadFutureTask extends FutureTask<Downloader> implements Comparable<DownloadFutureTask> {
    private final Downloader downloader;

    DownloadFutureTask(Downloader downloader) {
        super(downloader, null);
        this.downloader = downloader;
    }

    @Override // java.lang.Comparable
    public int compareTo(DownloadFutureTask downloadFutureTask) {
        DownloadPriority downloadPriority = this.downloader.downloadPriority;
        DownloadPriority downloadPriority2 = downloadFutureTask.downloader.downloadPriority;
        return downloadPriority == downloadPriority2 ? this.downloader.sequence - downloadFutureTask.downloader.sequence : downloadPriority2.ordinal() - downloadPriority.ordinal();
    }
}
