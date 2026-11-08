package com.ksad.download;

import com.kwad.sdk.core.download.DownloadParams;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private f a;

    private static final class a {
        private static final e a = new e();
    }

    public static e a() {
        return a.a;
    }

    public synchronized void a(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public synchronized void a(DownloadTask downloadTask) {
        if (downloadTask.isCanceled()) {
            a(downloadTask.getId());
        } else if (this.a != null) {
            this.a.a(downloadTask);
        }
    }

    public synchronized void a(DownloadTask downloadTask, boolean z) {
        if (downloadTask.isCanceled()) {
            a(downloadTask.getId());
        } else if (this.a != null) {
            this.a.a(downloadTask, z);
        }
    }

    public void a(f fVar) {
        this.a = fVar;
    }

    public synchronized void b(DownloadTask downloadTask) {
        if (downloadTask.isCanceled()) {
            a(downloadTask.getId());
        } else if (this.a != null) {
            this.a.b(downloadTask);
        }
    }

    public synchronized void c(DownloadTask downloadTask) {
        if (this.a != null) {
            this.a.c(downloadTask);
        }
        Object tag = downloadTask.getTag();
        if (tag instanceof DownloadParams) {
        }
    }
}
