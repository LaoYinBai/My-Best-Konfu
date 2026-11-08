package com.ss.android.socialbase.downloader.downloader;

import android.os.SystemClock;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.db.SqlDownloadCache;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.impls.DefaultDownloadCache;
import com.ss.android.socialbase.downloader.impls.DownloadCache;
import com.ss.android.socialbase.downloader.impls.DownloadProxy;
import com.ss.android.socialbase.downloader.model.DownloadChunk;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.RandomAccessOutputStream;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.reader.AsyncStreamReader;
import com.ss.android.socialbase.downloader.reader.IStreamReader;
import com.ss.android.socialbase.downloader.reader.SyncStreamReader;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadResponseHandler {
    private static final boolean DEBUG = false;
    public static final int MIN_SYNC_STEP_BYTE = 65536;
    public static final long MIN_SYNC_TIME_MS = 500;
    private static String TAG = "ResponseHandler";
    private final AppStatusManager appStatusManager;
    private final boolean bugfixCancelThenUpdate;
    private final IDownloadRunnableCallback callback;
    private volatile boolean canceled;
    private long curOffset;
    private long debugReadTimeNs;
    private long debugSyncTimeNs;
    private long debugTotalTimeNs;
    private long debugWriteTimeNs;
    private DownloadCache downloadCache;
    private final DownloadChunk downloadChunk;
    private volatile long downloadChunkContentLen;
    private final DownloadInfo downloadInfo;
    private volatile long endOffset;
    private BaseException exception;
    private long handleStartOffset;
    private final boolean hasSyncStrategy;
    private final IDownloadHttpConnection httpConnection;
    private final boolean isMonitorRw;
    private RandomAccessOutputStream outputStream;
    private volatile boolean paused;
    private boolean rwConcurrent;
    private final DownloadSetting setting;
    private ISqlDownloadCache sqlDownloadCache;
    private final long syncIntervalMsBg;
    private final long syncIntervalMsFg;
    private final String url;
    boolean openLimitSpeed = false;
    private volatile long lastSyncBytes = 0;
    private volatile long lastSyncTimestamp = 0;
    private IDownloadCache customCache = DownloadComponentManager.getDownloadCache();

    private boolean isNeedSync(long j, long j2) {
        return j > 65536 && j2 > 500;
    }

    public DownloadResponseHandler(DownloadInfo downloadInfo, String str, IDownloadHttpConnection iDownloadHttpConnection, DownloadChunk downloadChunk, IDownloadRunnableCallback iDownloadRunnableCallback) {
        this.downloadInfo = downloadInfo;
        this.url = str;
        if (this.customCache instanceof DefaultDownloadCache) {
            DefaultDownloadCache defaultDownloadCache = (DefaultDownloadCache) this.customCache;
            this.downloadCache = defaultDownloadCache.getDownloadCache();
            this.sqlDownloadCache = defaultDownloadCache.getSqlDownloadCache();
        }
        this.httpConnection = iDownloadHttpConnection;
        this.downloadChunk = downloadChunk;
        this.callback = iDownloadRunnableCallback;
        this.curOffset = downloadChunk.getCurrentOffset();
        this.handleStartOffset = this.curOffset;
        if (downloadChunk.isHostChunk()) {
            this.downloadChunkContentLen = downloadChunk.getContentLength();
        } else {
            this.downloadChunkContentLen = downloadChunk.getRetainLength(false);
        }
        this.endOffset = downloadChunk.getEndOffset();
        this.appStatusManager = AppStatusManager.getInstance();
        this.setting = DownloadSetting.obtain(downloadInfo.getId());
        this.hasSyncStrategy = this.setting.optInt(DownloadSettingKeys.SYNC_STRATEGY, 0) == 1;
        if (this.hasSyncStrategy) {
            long jOptInt = this.setting.optInt(DownloadSettingKeys.SYNC_INTERVAL_MS_FG, 5000);
            long jOptInt2 = this.setting.optInt(DownloadSettingKeys.SYNC_INTERVAL_MS_BG, 1000);
            this.syncIntervalMsFg = Math.max(jOptInt, 500L);
            this.syncIntervalMsBg = Math.max(jOptInt2, 500L);
        } else {
            this.syncIntervalMsFg = 0L;
            this.syncIntervalMsBg = 0L;
        }
        this.isMonitorRw = this.setting.optInt(DownloadSettingKeys.MONITOR_RW) == 1;
        this.bugfixCancelThenUpdate = DownloadExpSwitchCode.isSwitchEnable(65536);
    }

    public long getCurOffset() {
        return this.curOffset;
    }

    private boolean isStoppedStatus() {
        return this.paused || this.canceled;
    }

    public void pause() {
        if (this.paused) {
            return;
        }
        this.paused = true;
        cancelConnection();
    }

    public void cancel() {
        if (this.canceled) {
            return;
        }
        synchronized (this.callback) {
            this.canceled = true;
        }
        cancelConnection();
    }

    private void cancelConnection() {
        ExecutorService cPUThreadExecutor;
        if (this.httpConnection == null || (cPUThreadExecutor = DownloadComponentManager.getCPUThreadExecutor()) == null) {
            return;
        }
        cPUThreadExecutor.execute(new Runnable() { // from class: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    DownloadResponseHandler.this.httpConnection.end();
                } catch (Throwable unused) {
                }
            }
        });
    }

    private boolean canReuseConnection() {
        return this.downloadInfo.isNeedReuseFirstConnection() && this.downloadChunk.isReuseingFirstConnection();
    }

    public void setChunkOffset(long j, long j2, long j3) {
        this.curOffset = j;
        this.handleStartOffset = j;
        this.endOffset = j2;
        this.downloadChunkContentLen = j3;
    }

    public void setEndOffset(long j, long j2) {
        this.endOffset = j;
        this.downloadChunkContentLen = j2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:186:0x030e A[Catch: all -> 0x0561, TRY_ENTER, TryCatch #0 {all -> 0x0561, blocks: (B:129:0x0267, B:133:0x026f, B:137:0x027c, B:141:0x0282, B:142:0x0285, B:186:0x030e, B:187:0x0315, B:216:0x03b0, B:218:0x03b6, B:219:0x03b9, B:260:0x04a7, B:261:0x04a9, B:265:0x04b0, B:267:0x04d0, B:296:0x0555, B:298:0x055b, B:299:0x055e, B:300:0x0560, B:144:0x0297, B:145:0x029e, B:146:0x029f, B:147:0x02a6, B:151:0x02ab), top: B:332:0x002b, inners: #22 }] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x03b0 A[Catch: all -> 0x0561, TRY_ENTER, TryCatch #0 {all -> 0x0561, blocks: (B:129:0x0267, B:133:0x026f, B:137:0x027c, B:141:0x0282, B:142:0x0285, B:186:0x030e, B:187:0x0315, B:216:0x03b0, B:218:0x03b6, B:219:0x03b9, B:260:0x04a7, B:261:0x04a9, B:265:0x04b0, B:267:0x04d0, B:296:0x0555, B:298:0x055b, B:299:0x055e, B:300:0x0560, B:144:0x0297, B:145:0x029e, B:146:0x029f, B:147:0x02a6, B:151:0x02ab), top: B:332:0x002b, inners: #22 }] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0439 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:246:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x04d0 A[Catch: all -> 0x0561, TRY_LEAVE, TryCatch #0 {all -> 0x0561, blocks: (B:129:0x0267, B:133:0x026f, B:137:0x027c, B:141:0x0282, B:142:0x0285, B:186:0x030e, B:187:0x0315, B:216:0x03b0, B:218:0x03b6, B:219:0x03b9, B:260:0x04a7, B:261:0x04a9, B:265:0x04b0, B:267:0x04d0, B:296:0x0555, B:298:0x055b, B:299:0x055e, B:300:0x0560, B:144:0x0297, B:145:0x029e, B:146:0x029f, B:147:0x02a6, B:151:0x02ab), top: B:332:0x002b, inners: #22 }] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x055b A[Catch: all -> 0x0561, TryCatch #0 {all -> 0x0561, blocks: (B:129:0x0267, B:133:0x026f, B:137:0x027c, B:141:0x0282, B:142:0x0285, B:186:0x030e, B:187:0x0315, B:216:0x03b0, B:218:0x03b6, B:219:0x03b9, B:260:0x04a7, B:261:0x04a9, B:265:0x04b0, B:267:0x04d0, B:296:0x0555, B:298:0x055b, B:299:0x055e, B:300:0x0560, B:144:0x0297, B:145:0x029e, B:146:0x029f, B:147:0x02a6, B:151:0x02ab), top: B:332:0x002b, inners: #22 }] */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x056d  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0574 A[Catch: all -> 0x05d6, TryCatch #26 {all -> 0x05d6, blocks: (B:307:0x0570, B:309:0x0574, B:310:0x0576, B:320:0x058b, B:321:0x058c, B:323:0x0595, B:311:0x0577, B:313:0x057b, B:315:0x0584, B:316:0x0587), top: B:358:0x0570, inners: #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:321:0x058c A[Catch: all -> 0x05d6, TryCatch #26 {all -> 0x05d6, blocks: (B:307:0x0570, B:309:0x0574, B:310:0x0576, B:320:0x058b, B:321:0x058c, B:323:0x0595, B:311:0x0577, B:313:0x057b, B:315:0x0584, B:316:0x0587), top: B:358:0x0570, inners: #18 }] */
    /* JADX WARN: Type inference failed for: r6v0, types: [long] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10, types: [int] */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v38 */
    /* JADX WARN: Type inference failed for: r6v39 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8, types: [int] */
    /* JADX WARN: Type inference failed for: r6v9, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleResponse() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1516
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler.handleResponse():void");
    }

    private IStreamReader createStreamReader(InputStream inputStream) {
        int writeBufferSize = DownloadComponentManager.getWriteBufferSize();
        if (this.setting.optInt("rw_concurrent", 0) == 1 && this.downloadInfo.getChunkCount() == 1 && this.downloadInfo.getTotalBytes() > 20971520) {
            try {
                AsyncStreamReader asyncStreamReader = new AsyncStreamReader(inputStream, writeBufferSize, this.setting.optInt(DownloadSettingKeys.RW_CONCURRENT_MAX_BUFFER_COUNT, 4));
                this.rwConcurrent = true;
                return asyncStreamReader;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        SyncStreamReader syncStreamReader = new SyncStreamReader(inputStream, writeBufferSize);
        this.rwConcurrent = false;
        return syncStreamReader;
    }

    private void checkAndSync(boolean z) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        long j = jUptimeMillis - this.lastSyncTimestamp;
        if (this.hasSyncStrategy) {
            if (j > (this.appStatusManager.isAppForeground() ? this.syncIntervalMsFg : this.syncIntervalMsBg)) {
                sync();
                this.lastSyncTimestamp = jUptimeMillis;
                return;
            }
            return;
        }
        long j2 = this.curOffset - this.lastSyncBytes;
        if (z || isNeedSync(j2, j)) {
            sync();
            this.lastSyncTimestamp = jUptimeMillis;
        }
    }

    public long getLastSyncBytes() {
        return this.lastSyncBytes;
    }

    private void sync() {
        boolean z;
        long jNanoTime = this.isMonitorRw ? System.nanoTime() : 0L;
        try {
            this.outputStream.flushAndSync();
            z = true;
        } catch (Exception unused) {
            z = false;
        }
        if (z) {
            this.downloadInfo.updateRealDownloadTime(true);
            boolean z2 = this.downloadInfo.getChunkCount() > 1;
            IDownloadProxy iDownloadProxy = DownloadProxy.get(DownloadUtils.needNotifyDownloaderProcess());
            if (z2) {
                updateDownloadChunk(this.sqlDownloadCache);
                if (iDownloadProxy != null) {
                    iDownloadProxy.updateDownloadInfo(this.downloadInfo);
                } else {
                    this.sqlDownloadCache.OnDownloadTaskProgress(this.downloadInfo.getId(), this.downloadInfo.getCurBytes());
                }
            } else if (iDownloadProxy != null) {
                iDownloadProxy.updateDownloadInfo(this.downloadInfo);
            } else {
                this.sqlDownloadCache.OnDownloadTaskProgress(this.downloadChunk.getId(), this.curOffset);
            }
            this.lastSyncBytes = this.curOffset;
        }
        if (this.isMonitorRw) {
            this.debugSyncTimeNs += System.nanoTime() - jNanoTime;
        }
    }

    private void updateDownloadChunk(IDownloadCache iDownloadCache) {
        DownloadChunk firstReuseChunk;
        DownloadChunk downloadChunk;
        if (iDownloadCache == null) {
            return;
        }
        IDownloadProxy iDownloadProxy = null;
        boolean z = iDownloadCache instanceof SqlDownloadCache;
        if (z && (iDownloadProxy = DownloadProxy.get(DownloadUtils.needNotifyDownloaderProcess())) == null) {
            return;
        }
        IDownloadProxy iDownloadProxy2 = iDownloadProxy;
        if (this.downloadChunk.isHostChunk()) {
            firstReuseChunk = this.downloadChunk.getFirstReuseChunk();
        } else {
            firstReuseChunk = this.downloadChunk;
        }
        DownloadChunk downloadChunk2 = firstReuseChunk;
        if (downloadChunk2 != null) {
            downloadChunk2.setCurrentOffset(this.curOffset);
            if (z && iDownloadProxy2 != null) {
                iDownloadProxy2.updateSubDownloadChunk(downloadChunk2.getId(), downloadChunk2.getChunkIndex(), downloadChunk2.getHostChunkIndex(), this.curOffset);
                downloadChunk = downloadChunk2;
            } else {
                downloadChunk = downloadChunk2;
                iDownloadCache.updateSubDownloadChunk(downloadChunk2.getId(), downloadChunk2.getChunkIndex(), downloadChunk2.getHostChunkIndex(), this.curOffset);
            }
            if (downloadChunk.canRefreshCurOffsetForReuseChunk()) {
                boolean z2 = false;
                if (downloadChunk.hasNoBytesDownload()) {
                    long nextChunkCurOffset = downloadChunk.getNextChunkCurOffset();
                    if (nextChunkCurOffset > this.curOffset) {
                        if (z && iDownloadProxy2 != null) {
                            iDownloadProxy2.updateDownloadChunk(downloadChunk.getId(), downloadChunk.getHostChunkIndex(), nextChunkCurOffset);
                        } else {
                            iDownloadCache.updateDownloadChunk(downloadChunk.getId(), downloadChunk.getHostChunkIndex(), nextChunkCurOffset);
                        }
                        z2 = true;
                    }
                }
                if (z2) {
                    return;
                }
                if (z && iDownloadProxy2 != null) {
                    iDownloadProxy2.updateDownloadChunk(downloadChunk.getId(), downloadChunk.getHostChunkIndex(), this.curOffset);
                    return;
                } else {
                    iDownloadCache.updateDownloadChunk(downloadChunk.getId(), downloadChunk.getHostChunkIndex(), this.curOffset);
                    return;
                }
            }
            return;
        }
        if (this.downloadChunk.isHostChunk()) {
            if (z && iDownloadProxy2 != null) {
                iDownloadProxy2.updateDownloadChunk(this.downloadChunk.getId(), this.downloadChunk.getChunkIndex(), this.curOffset);
            } else {
                iDownloadCache.updateDownloadChunk(this.downloadChunk.getId(), this.downloadChunk.getChunkIndex(), this.curOffset);
            }
        }
    }
}
