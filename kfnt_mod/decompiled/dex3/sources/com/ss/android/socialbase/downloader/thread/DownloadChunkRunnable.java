package com.ss.android.socialbase.downloader.thread;

import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.model.DownloadChunk;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadChunkRunnable implements Runnable {
    private static final String TAG = "DownloadChunkRunnable";
    private final IDownloadRunnableCallback callback;
    private volatile boolean canceled;
    private DownloadChunk curDownloadChunk;
    private IDownloadCache downloadCache;
    private DownloadChunk downloadChunk;
    private DownloadInfo downloadInfo;
    private DownloadResponseHandler downloadResponseHandler;
    private final DownloadTask downloadTask;
    private IDownloadHttpConnection httpConnection;
    private boolean isHttpConnectionInject;
    private volatile boolean paused;

    public DownloadChunkRunnable(DownloadChunk downloadChunk, DownloadTask downloadTask, IDownloadRunnableCallback iDownloadRunnableCallback) {
        this.isHttpConnectionInject = false;
        this.downloadChunk = downloadChunk;
        this.downloadTask = downloadTask;
        if (downloadTask != null) {
            this.downloadInfo = downloadTask.getDownloadInfo();
        }
        this.callback = iDownloadRunnableCallback;
        this.downloadCache = DownloadComponentManager.getDownloadCache();
        this.downloadChunk.setChunkRunnable(this);
    }

    public DownloadChunkRunnable(DownloadChunk downloadChunk, DownloadTask downloadTask, IDownloadHttpConnection iDownloadHttpConnection, IDownloadRunnableCallback iDownloadRunnableCallback) {
        this(downloadChunk, downloadTask, iDownloadRunnableCallback);
        this.httpConnection = iDownloadHttpConnection;
    }

    public int getChunkIndex() {
        return this.downloadChunk.getChunkIndex();
    }

    private String getUrl() {
        return this.downloadInfo.getConnectionUrl();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0017, code lost:
    
        r3.curDownloadChunk.setDownloading(false);
     */
    @Override // java.lang.Runnable
    @android.annotation.SuppressLint({"DefaultLocale"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() throws java.lang.SecurityException, java.lang.IllegalArgumentException {
        /*
            r3 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
            com.ss.android.socialbase.downloader.model.DownloadChunk r0 = r3.downloadChunk
            r3.curDownloadChunk = r0
        L9:
            r0 = 0
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5f
            r1.setChunkRunnable(r3)     // Catch: java.lang.Throwable -> L5f
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5f
            boolean r1 = r3.downloadChunkInner(r1)     // Catch: java.lang.Throwable -> L5f
            if (r1 != 0) goto L1d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5f
            r1.setDownloading(r0)     // Catch: java.lang.Throwable -> L5f
            goto L4d
        L1d:
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5f
            r1.setDownloading(r0)     // Catch: java.lang.Throwable -> L5f
            boolean r1 = r3.isStoppedStatus()     // Catch: java.lang.Throwable -> L5f
            if (r1 == 0) goto L29
            goto L4d
        L29:
            com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback r1 = r3.callback     // Catch: java.lang.Throwable -> L5f
            com.ss.android.socialbase.downloader.model.DownloadChunk r2 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5f
            int r2 = r2.getChunkIndex()     // Catch: java.lang.Throwable -> L5f
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r1.getUnCompletedSubChunk(r2)     // Catch: java.lang.Throwable -> L5f
            r3.curDownloadChunk = r1     // Catch: java.lang.Throwable -> L5f
            boolean r1 = r3.isStoppedStatus()     // Catch: java.lang.Throwable -> L5f
            if (r1 != 0) goto L4d
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk     // Catch: java.lang.Throwable -> L5f
            if (r1 != 0) goto L42
            goto L4d
        L42:
            r1 = 50
            java.lang.Thread.sleep(r1)     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L5f
            goto L9
        L48:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L5f
            goto L9
        L4d:
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk
            if (r1 == 0) goto L56
            com.ss.android.socialbase.downloader.model.DownloadChunk r1 = r3.curDownloadChunk
            r1.setDownloading(r0)
        L56:
            r3.closeConnection()
            com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback r0 = r3.callback
            r0.onCompleted(r3)
            return
        L5f:
            r1 = move-exception
            com.ss.android.socialbase.downloader.model.DownloadChunk r2 = r3.curDownloadChunk
            if (r2 == 0) goto L69
            com.ss.android.socialbase.downloader.model.DownloadChunk r2 = r3.curDownloadChunk
            r2.setDownloading(r0)
        L69:
            r3.closeConnection()
            com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback r0 = r3.callback
            r0.onCompleted(r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadChunkRunnable.run():void");
    }

    public void refreshResponseHandleOffset(long j, long j2) {
        if (this.downloadResponseHandler == null) {
            return;
        }
        this.downloadResponseHandler.setEndOffset(j, j2);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x01a3 A[Catch: Throwable -> 0x01be, BaseException -> 0x01c0, all -> 0x01c2, TRY_LEAVE, TryCatch #0 {all -> 0x01c2, blocks: (B:22:0x004f, B:26:0x0059, B:30:0x0064, B:35:0x00b5, B:37:0x00b9, B:39:0x00bd, B:42:0x00c7, B:45:0x00d0, B:64:0x0101, B:68:0x010b, B:70:0x010f, B:72:0x0113, B:75:0x0120, B:76:0x0135, B:81:0x013f, B:83:0x014e, B:85:0x0155, B:89:0x0161, B:90:0x0167, B:91:0x0178, B:139:0x01e5, B:143:0x01ef, B:145:0x01f5, B:148:0x01fe, B:150:0x0206, B:152:0x020c, B:156:0x0217, B:158:0x021b, B:160:0x0223, B:162:0x0235, B:171:0x025c, B:173:0x0262, B:175:0x026f, B:179:0x0277, B:174:0x0269, B:165:0x0242, B:166:0x024f, B:181:0x0282, B:183:0x028a, B:185:0x0292, B:187:0x029a, B:189:0x02a2, B:192:0x02ab, B:126:0x01c7, B:130:0x01d1, B:133:0x01d8, B:78:0x0137, B:80:0x013e, B:97:0x0186, B:98:0x0194, B:106:0x019f, B:108:0x01a3, B:110:0x01a7, B:113:0x01b1, B:116:0x01ba, B:117:0x01bd, B:53:0x00e5, B:55:0x00e9, B:57:0x00ed, B:60:0x00f7), top: B:197:0x01e5, inners: #17 }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01ef A[Catch: all -> 0x01c2, TRY_ENTER, TryCatch #0 {all -> 0x01c2, blocks: (B:22:0x004f, B:26:0x0059, B:30:0x0064, B:35:0x00b5, B:37:0x00b9, B:39:0x00bd, B:42:0x00c7, B:45:0x00d0, B:64:0x0101, B:68:0x010b, B:70:0x010f, B:72:0x0113, B:75:0x0120, B:76:0x0135, B:81:0x013f, B:83:0x014e, B:85:0x0155, B:89:0x0161, B:90:0x0167, B:91:0x0178, B:139:0x01e5, B:143:0x01ef, B:145:0x01f5, B:148:0x01fe, B:150:0x0206, B:152:0x020c, B:156:0x0217, B:158:0x021b, B:160:0x0223, B:162:0x0235, B:171:0x025c, B:173:0x0262, B:175:0x026f, B:179:0x0277, B:174:0x0269, B:165:0x0242, B:166:0x024f, B:181:0x0282, B:183:0x028a, B:185:0x0292, B:187:0x029a, B:189:0x02a2, B:192:0x02ab, B:126:0x01c7, B:130:0x01d1, B:133:0x01d8, B:78:0x0137, B:80:0x013e, B:97:0x0186, B:98:0x0194, B:106:0x019f, B:108:0x01a3, B:110:0x01a7, B:113:0x01b1, B:116:0x01ba, B:117:0x01bd, B:53:0x00e5, B:55:0x00e9, B:57:0x00ed, B:60:0x00f7), top: B:197:0x01e5, inners: #17 }] */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0107 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x01eb A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:234:? A[ADDED_TO_REGION, Catch: Throwable -> 0x01be, BaseException -> 0x01c0, all -> 0x01c2, REMOVE, SYNTHETIC, TRY_LEAVE, TryCatch #0 {all -> 0x01c2, blocks: (B:22:0x004f, B:26:0x0059, B:30:0x0064, B:35:0x00b5, B:37:0x00b9, B:39:0x00bd, B:42:0x00c7, B:45:0x00d0, B:64:0x0101, B:68:0x010b, B:70:0x010f, B:72:0x0113, B:75:0x0120, B:76:0x0135, B:81:0x013f, B:83:0x014e, B:85:0x0155, B:89:0x0161, B:90:0x0167, B:91:0x0178, B:139:0x01e5, B:143:0x01ef, B:145:0x01f5, B:148:0x01fe, B:150:0x0206, B:152:0x020c, B:156:0x0217, B:158:0x021b, B:160:0x0223, B:162:0x0235, B:171:0x025c, B:173:0x0262, B:175:0x026f, B:179:0x0277, B:174:0x0269, B:165:0x0242, B:166:0x024f, B:181:0x0282, B:183:0x028a, B:185:0x0292, B:187:0x029a, B:189:0x02a2, B:192:0x02ab, B:126:0x01c7, B:130:0x01d1, B:133:0x01d8, B:78:0x0137, B:80:0x013e, B:97:0x0186, B:98:0x0194, B:106:0x019f, B:108:0x01a3, B:110:0x01a7, B:113:0x01b1, B:116:0x01ba, B:117:0x01bd, B:53:0x00e5, B:55:0x00e9, B:57:0x00ed, B:60:0x00f7), top: B:197:0x01e5, inners: #17 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x010b A[Catch: all -> 0x01c2, Throwable -> 0x01c5, BaseException -> 0x01e1, TRY_ENTER, TryCatch #0 {all -> 0x01c2, blocks: (B:22:0x004f, B:26:0x0059, B:30:0x0064, B:35:0x00b5, B:37:0x00b9, B:39:0x00bd, B:42:0x00c7, B:45:0x00d0, B:64:0x0101, B:68:0x010b, B:70:0x010f, B:72:0x0113, B:75:0x0120, B:76:0x0135, B:81:0x013f, B:83:0x014e, B:85:0x0155, B:89:0x0161, B:90:0x0167, B:91:0x0178, B:139:0x01e5, B:143:0x01ef, B:145:0x01f5, B:148:0x01fe, B:150:0x0206, B:152:0x020c, B:156:0x0217, B:158:0x021b, B:160:0x0223, B:162:0x0235, B:171:0x025c, B:173:0x0262, B:175:0x026f, B:179:0x0277, B:174:0x0269, B:165:0x0242, B:166:0x024f, B:181:0x0282, B:183:0x028a, B:185:0x0292, B:187:0x029a, B:189:0x02a2, B:192:0x02ab, B:126:0x01c7, B:130:0x01d1, B:133:0x01d8, B:78:0x0137, B:80:0x013e, B:97:0x0186, B:98:0x0194, B:106:0x019f, B:108:0x01a3, B:110:0x01a7, B:113:0x01b1, B:116:0x01ba, B:117:0x01bd, B:53:0x00e5, B:55:0x00e9, B:57:0x00ed, B:60:0x00f7), top: B:197:0x01e5, inners: #17 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean downloadChunkInner(com.ss.android.socialbase.downloader.model.DownloadChunk r31) {
        /*
            Method dump skipped, instructions count: 696
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadChunkRunnable.downloadChunkInner(com.ss.android.socialbase.downloader.model.DownloadChunk):boolean");
    }

    private void revertDownloadChunk(DownloadChunk downloadChunk, long j) {
        DownloadChunk firstReuseChunk = downloadChunk.isHostChunk() ? downloadChunk.getFirstReuseChunk() : downloadChunk;
        if (firstReuseChunk != null) {
            if (firstReuseChunk.canRefreshCurOffsetForReuseChunk()) {
                this.downloadCache.updateDownloadChunk(firstReuseChunk.getId(), firstReuseChunk.getHostChunkIndex(), j);
            }
            firstReuseChunk.setCurrentOffset(j);
            this.downloadCache.updateSubDownloadChunk(firstReuseChunk.getId(), firstReuseChunk.getChunkIndex(), firstReuseChunk.getHostChunkIndex(), j);
            return;
        }
        if (downloadChunk.isHostChunk()) {
            this.downloadCache.updateDownloadChunk(downloadChunk.getId(), downloadChunk.getChunkIndex(), j);
        }
    }

    private void closeConnection() {
        if (this.httpConnection != null) {
            this.httpConnection.end();
            this.httpConnection = null;
        }
    }

    private boolean isStoppedStatus() {
        return this.paused || this.canceled;
    }

    public void pause() {
        this.paused = true;
        if (this.downloadResponseHandler != null) {
            this.downloadResponseHandler.pause();
        }
    }

    public void cancel() {
        this.canceled = true;
        if (this.downloadResponseHandler != null) {
            this.downloadResponseHandler.cancel();
        }
    }
}
