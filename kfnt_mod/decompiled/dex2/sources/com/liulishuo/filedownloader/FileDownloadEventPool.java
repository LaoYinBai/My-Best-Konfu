package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.event.DownloadEventPoolImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class FileDownloadEventPool extends DownloadEventPoolImpl {

    private static class HolderClass {
        private static final FileDownloadEventPool INSTANCE = new FileDownloadEventPool();

        private HolderClass() {
        }
    }

    private FileDownloadEventPool() {
    }

    public static FileDownloadEventPool getImpl() {
        return HolderClass.INSTANCE;
    }
}
