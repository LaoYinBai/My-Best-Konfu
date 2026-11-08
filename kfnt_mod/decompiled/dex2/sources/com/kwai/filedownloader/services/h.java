package com.kwai.filedownloader.services;

import android.util.SparseArray;
import com.kwai.filedownloader.download.DownloadLaunchRunnable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class h {
    private ThreadPoolExecutor b;
    private int d;
    private SparseArray<DownloadLaunchRunnable> a = new SparseArray<>();
    private final String c = "Network";
    private int e = 0;

    h(int i) {
        this.b = com.kwai.filedownloader.e.b.a(i, "Network");
        this.d = i;
    }

    private synchronized void c() {
        SparseArray<DownloadLaunchRunnable> sparseArray = new SparseArray<>();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = this.a.keyAt(i);
            DownloadLaunchRunnable downloadLaunchRunnable = this.a.get(iKeyAt);
            if (downloadLaunchRunnable.e()) {
                sparseArray.put(iKeyAt, downloadLaunchRunnable);
            }
        }
        this.a = sparseArray;
    }

    public synchronized int a() {
        c();
        return this.a.size();
    }

    public int a(String str, int i) {
        if (str == null) {
            return 0;
        }
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            DownloadLaunchRunnable downloadLaunchRunnableValueAt = this.a.valueAt(i2);
            if (downloadLaunchRunnableValueAt != null && downloadLaunchRunnableValueAt.e() && downloadLaunchRunnableValueAt.d() != i && str.equals(downloadLaunchRunnableValueAt.f())) {
                return downloadLaunchRunnableValueAt.d();
            }
        }
        return 0;
    }

    public void a(DownloadLaunchRunnable downloadLaunchRunnable) {
        int i;
        downloadLaunchRunnable.b();
        synchronized (this) {
            this.a.put(downloadLaunchRunnable.d(), downloadLaunchRunnable);
        }
        this.b.execute(downloadLaunchRunnable);
        if (this.e >= 600) {
            c();
            i = 0;
        } else {
            i = this.e + 1;
        }
        this.e = i;
    }

    public synchronized boolean a(int i) {
        if (a() > 0) {
            com.kwai.filedownloader.e.d.d(this, "Can't change the max network thread count, because the  network thread pool isn't in IDLE, please try again after all running tasks are completed or invoking FileDownloader#pauseAll directly.", new Object[0]);
            return false;
        }
        int iA = com.kwai.filedownloader.e.e.a(i);
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "change the max network thread count, from %d to %d", Integer.valueOf(this.d), Integer.valueOf(iA));
        }
        List<Runnable> listShutdownNow = this.b.shutdownNow();
        this.b = com.kwai.filedownloader.e.b.a(iA, "Network");
        if (listShutdownNow.size() > 0) {
            com.kwai.filedownloader.e.d.d(this, "recreate the network thread pool and discard %d tasks", Integer.valueOf(listShutdownNow.size()));
        }
        this.d = iA;
        return true;
    }

    public synchronized List<Integer> b() {
        ArrayList arrayList;
        c();
        arrayList = new ArrayList();
        for (int i = 0; i < this.a.size(); i++) {
            arrayList.add(Integer.valueOf(this.a.get(this.a.keyAt(i)).d()));
        }
        return arrayList;
    }

    public void b(int i) {
        c();
        synchronized (this) {
            DownloadLaunchRunnable downloadLaunchRunnable = this.a.get(i);
            if (downloadLaunchRunnable != null) {
                downloadLaunchRunnable.a();
                boolean zRemove = this.b.remove(downloadLaunchRunnable);
                if (com.kwai.filedownloader.e.d.a) {
                    com.kwai.filedownloader.e.d.c(this, "successful cancel %d %B", Integer.valueOf(i), Boolean.valueOf(zRemove));
                }
            }
            this.a.remove(i);
        }
    }

    public boolean c(int i) {
        DownloadLaunchRunnable downloadLaunchRunnable = this.a.get(i);
        return downloadLaunchRunnable != null && downloadLaunchRunnable.e();
    }
}
