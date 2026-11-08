package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface ILostServiceConnectedHandler {
    boolean dispatchTaskStart(BaseDownloadTask.IRunningTask iRunningTask);

    boolean isInWaitingList(BaseDownloadTask.IRunningTask iRunningTask);

    void taskWorkFine(BaseDownloadTask.IRunningTask iRunningTask);
}
