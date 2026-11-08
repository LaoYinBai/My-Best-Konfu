package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.model.FileDownloadModel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface IThreadPoolMonitor {
    int findRunningTaskIdBySameTempPath(String str, int i);

    boolean isDownloading(FileDownloadModel fileDownloadModel);
}
