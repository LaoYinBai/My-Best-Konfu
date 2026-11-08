package com.ss.android.socialbase.downloader.thread;

import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.ss.android.socialbase.downloader.constants.ByteInvalidRetryStatus;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.constants.RunStatus;
import com.ss.android.socialbase.downloader.depend.AbsDownloadForbiddenCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback;
import com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadForbiddenHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadInterceptor;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadResponseHandler;
import com.ss.android.socialbase.downloader.downloader.DownloadStatusHandler;
import com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator;
import com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.exception.DownloadFileExistException;
import com.ss.android.socialbase.downloader.exception.DownloadHttpException;
import com.ss.android.socialbase.downloader.exception.DownloadOnlyWifiException;
import com.ss.android.socialbase.downloader.exception.DownloadOutOfSpaceException;
import com.ss.android.socialbase.downloader.exception.DownloadPauseReserveWifiException;
import com.ss.android.socialbase.downloader.exception.DownloadRetryNeedlessException;
import com.ss.android.socialbase.downloader.exception.RetryCheckStatus;
import com.ss.android.socialbase.downloader.exception.RetryThrowable;
import com.ss.android.socialbase.downloader.impls.AbsDownloadEngine;
import com.ss.android.socialbase.downloader.impls.DefaultDownloadEngine;
import com.ss.android.socialbase.downloader.impls.RetryDelayTimeParamCalculator;
import com.ss.android.socialbase.downloader.impls.RetryScheduler;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadChunk;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.model.HttpResponse;
import com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.ss.android.socialbase.downloader.network.AbsDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.DeviceBandwidthSampler;
import com.ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.ss.android.socialbase.downloader.network.connectionpool.DownloadConnectionPool;
import com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHeadHttpConnection;
import com.ss.android.socialbase.downloader.network.connectionpool.FakeDownloadHttpConnection;
import com.ss.android.socialbase.downloader.segment.Segment;
import com.ss.android.socialbase.downloader.segment.SegmentDispatcher;
import com.ss.android.socialbase.downloader.segment.SegmentStrategy;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadHttpUtils;
import com.ss.android.socialbase.downloader.utils.DownloadSettingsUtils;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLHandshakeException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadRunnable implements IDownloadRunnableCallback, Runnable {
    private static final int MAX_RESET_RETAIN_RETRY_TIMES_COUNT = 3;
    private static final String TAG = "DownloadRunnable";
    private boolean acceptPartial;
    private boolean canResumeFromCache;
    private long curBytesNeedCheckSpaceOverFlow;
    private IDownloadDiskSpaceHandler diskSpaceHandler;
    private final IDownloadCache downloadCache;
    private DownloadInfo downloadInfo;
    private final DownloadTask downloadTask;
    private volatile BaseException errorException;
    private String existTargetFileName;
    private IDownloadHttpConnection firstGetConnection;
    private IDownloadHeadHttpConnection firstHeadConnection;
    private volatile DownloadResponseHandler firstHttpResponseHandler;
    private IDownloadForbiddenHandler forbiddenHandler;
    private final IChunkAdjustCalculator globalChunkAdjustCalculator;
    private final IChunkCntCalculator globalChunkCalculator;
    private final AtomicBoolean isAlive;
    private boolean isChunked;
    private boolean isResponseFromBegin;
    private boolean isSingleChunk;
    private Future mFuture;
    private long prepareDownloadTime;
    private AtomicInteger retainRetryTimes;
    private IRetryDelayTimeCalculator retryDelayTimeCalculator;
    private final DownloadSetting setting;
    private final DownloadStatusHandler statusHandler;
    private IChunkAdjustCalculator taskChunkAdjustCalculator;
    private IChunkCntCalculator taskChunkCalculator;
    private volatile boolean isTriedFixRangeNotSatisfiable = false;
    private final ArrayList<DownloadChunkRunnable> downloadChunkRunnableList = new ArrayList<>();
    private volatile RunStatus runStatus = RunStatus.RUN_STATUS_NONE;
    private volatile int bytesRetryCount = 5;
    private boolean needJumpToStart = false;
    private boolean firstHeadConnectionFailed = false;
    private boolean needCheckIfModified = false;
    private int resetRetainRetryTimesCount = 0;
    private volatile SegmentDispatcher segmentDispatcher = null;

    private boolean checkNeedRetryDelay() {
        return false;
    }

    public DownloadRunnable(DownloadTask downloadTask, Handler handler) {
        this.downloadTask = downloadTask;
        if (downloadTask != null) {
            this.downloadInfo = downloadTask.getDownloadInfo();
            this.taskChunkCalculator = downloadTask.getChunkStrategy();
            this.taskChunkAdjustCalculator = downloadTask.getChunkAdjustCalculator();
            this.forbiddenHandler = downloadTask.getForbiddenHandler();
            this.diskSpaceHandler = downloadTask.getDiskSpaceHandler();
            this.retryDelayTimeCalculator = getRetryDelayTimeCalculator(downloadTask);
            this.setting = DownloadSetting.obtain(this.downloadInfo.getId());
        } else {
            this.setting = DownloadSetting.obtainGlobal();
        }
        updateRetainRetryTimes();
        this.downloadCache = DownloadComponentManager.getDownloadCache();
        this.globalChunkCalculator = DownloadComponentManager.getChunkCntCalculator();
        this.globalChunkAdjustCalculator = DownloadComponentManager.getChunkAdjustCalculator();
        this.statusHandler = new DownloadStatusHandler(downloadTask, handler);
        this.isAlive = new AtomicBoolean(true);
    }

    private void updateRetainRetryTimes() {
        if (this.downloadInfo == null) {
            return;
        }
        int retryCount = this.downloadInfo.getRetryCount() - this.downloadInfo.getCurRetryTime();
        if (retryCount < 0) {
            retryCount = 0;
        }
        if (this.retainRetryTimes == null) {
            this.retainRetryTimes = new AtomicInteger(retryCount);
        } else {
            this.retainRetryTimes.set(retryCount);
        }
    }

    public void pause() {
        this.runStatus = RunStatus.RUN_STATUS_PAUSE;
        if (this.segmentDispatcher != null) {
            this.segmentDispatcher.pause();
        }
        if (this.firstHttpResponseHandler != null) {
            this.firstHttpResponseHandler.pause();
        }
        if (this.segmentDispatcher == null && this.firstHttpResponseHandler == null) {
            closeConnection();
            this.runStatus = RunStatus.RUN_STATUS_PAUSE;
            endDownloadRunnable();
        }
        try {
            Iterator it = ((ArrayList) this.downloadChunkRunnableList.clone()).iterator();
            while (it.hasNext()) {
                DownloadChunkRunnable downloadChunkRunnable = (DownloadChunkRunnable) it.next();
                if (downloadChunkRunnable != null) {
                    downloadChunkRunnable.pause();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void cancel() {
        this.runStatus = RunStatus.RUN_STATUS_CANCELED;
        if (this.segmentDispatcher != null) {
            this.segmentDispatcher.cancel();
        }
        if (this.firstHttpResponseHandler != null) {
            this.firstHttpResponseHandler.cancel();
        }
        if (this.segmentDispatcher == null && this.firstHttpResponseHandler == null) {
            closeConnection();
            this.runStatus = RunStatus.RUN_STATUS_CANCELED;
            endDownloadRunnable();
        }
        cancelAllChunkRunnable();
    }

    public DownloadTask getDownloadTask() {
        return this.downloadTask;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int calculateChunkCount(long r8, java.util.List<com.ss.android.socialbase.downloader.model.DownloadChunk> r10) {
        /*
            r7 = this;
            boolean r0 = r7.isMultiChunkDownloadAvailable()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L62
            boolean r0 = r7.canResumeFromCache
            if (r0 == 0) goto L1a
            if (r10 == 0) goto L13
            int r10 = r10.size()
            goto L60
        L13:
            com.ss.android.socialbase.downloader.model.DownloadInfo r10 = r7.downloadInfo
            int r10 = r10.getChunkCount()
            goto L60
        L1a:
            com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator r10 = r7.taskChunkCalculator
            if (r10 == 0) goto L25
            com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator r10 = r7.taskChunkCalculator
            int r10 = r10.calculateChunkCount(r8)
            goto L2b
        L25:
            com.ss.android.socialbase.downloader.downloader.IChunkCntCalculator r10 = r7.globalChunkCalculator
            int r10 = r10.calculateChunkCount(r8)
        L2b:
            com.ss.android.socialbase.downloader.network.NetTrafficManager r0 = com.ss.android.socialbase.downloader.network.NetTrafficManager.getInstance()
            com.ss.android.socialbase.downloader.network.NetworkQuality r0 = r0.getCurrentNetworkQuality()
            java.lang.String r3 = com.ss.android.socialbase.downloader.thread.DownloadRunnable.TAG
            java.lang.String r4 = "NetworkQuality is : %s"
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = r0.name()
            r5[r1] = r6
            java.lang.String r4 = java.lang.String.format(r4, r5)
            com.ss.android.socialbase.downloader.logger.Logger.d(r3, r4)
            com.ss.android.socialbase.downloader.model.DownloadInfo r3 = r7.downloadInfo
            java.lang.String r4 = r0.name()
            r3.setNetworkQuality(r4)
            com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator r3 = r7.taskChunkAdjustCalculator
            if (r3 == 0) goto L5a
            com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator r3 = r7.taskChunkAdjustCalculator
            int r10 = r3.calculateChunkCount(r10, r0)
            goto L60
        L5a:
            com.ss.android.socialbase.downloader.downloader.IChunkAdjustCalculator r3 = r7.globalChunkAdjustCalculator
            int r10 = r3.calculateChunkCount(r10, r0)
        L60:
            if (r10 > 0) goto L63
        L62:
            r10 = 1
        L63:
            boolean r0 = com.ss.android.socialbase.downloader.logger.Logger.debug()
            if (r0 == 0) goto L8c
            java.lang.String r0 = com.ss.android.socialbase.downloader.thread.DownloadRunnable.TAG
            java.lang.String r3 = "chunk count : %s for %s contentLen:%s"
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = java.lang.String.valueOf(r10)
            r4[r1] = r5
            com.ss.android.socialbase.downloader.model.DownloadInfo r1 = r7.downloadInfo
            java.lang.String r1 = r1.getName()
            r4[r2] = r1
            r1 = 2
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r4[r1] = r8
            java.lang.String r8 = java.lang.String.format(r3, r4)
            com.ss.android.socialbase.downloader.logger.Logger.d(r0, r8)
        L8c:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadRunnable.calculateChunkCount(long, java.util.List):int");
    }

    private boolean checkTaskStatusValid() {
        int status = this.downloadInfo.getStatus();
        if (status == 1 || this.downloadInfo.canSkipStatusHandler()) {
            return true;
        }
        if (status == -2 || status == -4) {
            return false;
        }
        onError(new BaseException(1000, "The download Task can't start, because its status is not prepare:" + status));
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00bb A[Catch: all -> 0x00fd, TryCatch #13 {all -> 0x00fd, blocks: (B:63:0x00b7, B:65:0x00bb, B:67:0x00bf, B:81:0x00fc), top: B:99:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0100  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void checkTaskCache() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadRunnable.checkTaskCache():void");
    }

    @Override // java.lang.Runnable
    public void run() {
        DownloadComponentManager.onDownloadTaskStart(this.downloadTask, 3);
        try {
            DeviceBandwidthSampler.getInstance().startSampling();
            runInner();
            DeviceBandwidthSampler.getInstance().stopSampling();
            DownloadComponentManager.onDownloadTaskFinish(this.downloadTask, 3);
        } catch (Throwable th) {
            DeviceBandwidthSampler.getInstance().stopSampling();
            throw th;
        }
    }

    private void runInner() throws SecurityException, IllegalArgumentException {
        Process.setThreadPriority(10);
        try {
            if (this.downloadInfo != null && this.prepareDownloadTime > 0) {
                this.downloadInfo.increaseDownloadPrepareTime(System.currentTimeMillis() - this.prepareDownloadTime);
            }
        } catch (Throwable unused) {
        }
        try {
            IDownloadInterceptor interceptor = this.downloadTask.getInterceptor();
            if (interceptor != null) {
                if (interceptor.intercepte()) {
                    this.statusHandler.onIntercept();
                    return;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (!checkTaskStatusValid()) {
            DownloadMonitorHelper.monitorSendWithTaskMonitor(this.downloadTask.getMonitorDepend(), this.downloadInfo, new BaseException(1003, "task status is invalid"), this.downloadInfo != null ? this.downloadInfo.getStatus() : 0);
            return;
        }
        while (true) {
            downloadInner();
            if (!this.needJumpToStart) {
                return;
            }
            if (this.bytesRetryCount > 0) {
                this.bytesRetryCount--;
            } else {
                if (this.downloadInfo.getCurBytes() != this.downloadInfo.getTotalBytes()) {
                    Logger.d(TAG, this.downloadInfo.getErrorBytesLog());
                    this.statusHandler.onError(new DownloadRetryNeedlessException(1027, "current bytes is not equals to total bytes, bytes invalid retry status is : " + this.downloadInfo.getByteInvalidRetryStatus()));
                    return;
                }
                if (this.downloadInfo.getCurBytes() <= 0) {
                    Logger.d(TAG, this.downloadInfo.getErrorBytesLog());
                    this.statusHandler.onError(new DownloadRetryNeedlessException(1026, "curBytes is 0, bytes invalid retry status is : " + this.downloadInfo.getByteInvalidRetryStatus()));
                    return;
                }
                if (this.downloadInfo.getTotalBytes() <= 0) {
                    Logger.d(TAG, this.downloadInfo.getErrorBytesLog());
                    this.statusHandler.onError(new DownloadRetryNeedlessException(DownloadErrorCode.ERROR_TOTAL_BYTES_ZERO, "TotalBytes is 0, bytes invalid retry status is : " + this.downloadInfo.getByteInvalidRetryStatus()));
                    return;
                }
            }
        }
    }

    private void downloadInner() {
        boolean z;
        List<DownloadChunk> downloadChunk;
        long jCurrentTimeMillis;
        try {
            this.runStatus = RunStatus.RUN_STATUS_NONE;
            this.downloadInfo.updateStartDownloadTime();
            this.downloadInfo.resetRealStartDownloadTime();
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            this.downloadInfo.setFirstSpeedTime(-1L);
            try {
                checkTaskCache();
                z = false;
            } catch (DownloadFileExistException e) {
                Logger.d(TAG, "file exist " + e.getExistTargetFileName());
                this.existTargetFileName = e.getExistTargetFileName();
                z = true;
            }
            if (!this.needJumpToStart) {
                this.statusHandler.onStart();
            }
            this.needJumpToStart = false;
            if (checkIsStoppedByUser()) {
                endDownloadRunnable();
                return;
            }
            if (!TextUtils.isEmpty(this.existTargetFileName) && z) {
                if (this.downloadInfo.isExpiredRedownload()) {
                    this.needCheckIfModified = DownloadUtils.cacheExpired(this.downloadInfo);
                }
                if (!this.needCheckIfModified) {
                    finishWithFileExist();
                    endDownloadRunnable();
                    return;
                }
            }
            while (!checkIsStoppedByUser()) {
                try {
                    try {
                        checkSavePathValid();
                        checkHasAnotherSameTask();
                        checkWifiTaskValid();
                        downloadChunk = this.downloadCache.getDownloadChunk(this.downloadInfo.getId());
                        checkTaskCanResume();
                    } catch (DownloadFileExistException unused) {
                        finishWithFileExist();
                    } catch (Throwable th) {
                        Logger.w(TAG, "downloadInner: throwable =  " + th);
                        if (this.runStatus != RunStatus.RUN_STATUS_PAUSE) {
                            onError(new BaseException(DownloadErrorCode.ERROR_DOWNLOAD_RUNNABLE_UNKNOWN, th));
                        }
                    }
                } catch (BaseException e2) {
                    Logger.w(TAG, "downloadInner: baseException = " + e2);
                    if (this.runStatus != RunStatus.RUN_STATUS_PAUSE) {
                        if (e2.getErrorCode() != 1025 && e2.getErrorCode() != 1009) {
                            if (canRetry(e2)) {
                                if (DownloadUtils.isHttpDataDirtyError(e2)) {
                                    clearCurrentDownloadData();
                                }
                                if (onRetry(e2, 0L) == RetryCheckStatus.RETURN) {
                                    endDownloadRunnable();
                                    return;
                                }
                            } else {
                                onError(e2);
                            }
                        }
                        this.runStatus = RunStatus.RUN_STATUS_END_RIGHT_NOW;
                        endDownloadRunnable();
                        return;
                    }
                } catch (RetryThrowable e3) {
                    try {
                        Logger.w(TAG, "downloadInner: retry throwable for " + e3.getErrorMsg());
                        if (this.runStatus != RunStatus.RUN_STATUS_PAUSE) {
                            if (this.retainRetryTimes != null && this.retainRetryTimes.get() > 0) {
                                this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.decrementAndGet());
                                this.downloadInfo.setStatus(5);
                            } else if (this.retainRetryTimes == null) {
                                onError(new BaseException(DownloadErrorCode.ERROR_RETAIN_RETRY_TIME_IS_NULL, "retry for Throwable, but retain retry time is NULL, last error is" + e3.getErrorMsg()));
                            } else if (this.downloadInfo.trySwitchToNextBackupUrl()) {
                                this.downloadInfo.setStatus(5);
                                this.retainRetryTimes.set(this.downloadInfo.getRetryCount());
                                this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.get());
                            } else {
                                onError(new BaseException(DownloadErrorCode.ERROR_RETRY_COUNT_ALL_USED, String.format("retry for Throwable, but retry Time %s all used, last error is %s", String.valueOf(this.downloadInfo.getRetryCount()), e3.getErrorMsg())));
                            }
                        }
                    } finally {
                        closeConnection();
                    }
                }
                if (downloadSegments()) {
                    Logger.i(TAG, "downloadSegments return");
                    endDownloadRunnable();
                    return;
                }
                String connectionUrl = this.downloadInfo.getConnectionUrl();
                if (checkIsStoppedByUser()) {
                    endDownloadRunnable();
                    return;
                }
                long firstOffset = this.canResumeFromCache ? DownloadUtils.getFirstOffset(this.downloadInfo) : 0L;
                DownloadChunk downloadChunkCreateFirstDownloadChunk = createFirstDownloadChunk(this.downloadInfo, firstOffset);
                List<HttpHeader> extraHeaders = getExtraHeaders(downloadChunkCreateFirstDownloadChunk);
                DownloadUtils.addThrottleNetSpeed(extraHeaders, this.downloadInfo);
                DownloadUtils.addTTNetProtectTimeout(extraHeaders, this.downloadInfo);
                this.downloadInfo.setPreconnectLevel(0);
                long jCurrentTimeMillis3 = System.currentTimeMillis();
                try {
                    doFirstConnect(connectionUrl, extraHeaders, firstOffset);
                    this.downloadInfo.increaseAllConnectTime(System.currentTimeMillis() - jCurrentTimeMillis3);
                    if (checkIsStoppedByUser()) {
                        endDownloadRunnable();
                        return;
                    }
                    long totalBytes = this.downloadInfo.getTotalBytes();
                    checkSpaceOverflow(totalBytes);
                    int iCalculateChunkCount = calculateChunkCount(totalBytes, downloadChunk);
                    if (checkIsStoppedByUser()) {
                        endDownloadRunnable();
                        return;
                    }
                    if (iCalculateChunkCount <= 0) {
                        throw new BaseException(DownloadErrorCode.ERROR_CHUNK_COUNT_ERROR, "chunkCount is 0");
                    }
                    this.isSingleChunk = iCalculateChunkCount == 1;
                    if (this.isSingleChunk) {
                        if (this.firstGetConnection == null) {
                            try {
                                jCurrentTimeMillis = System.currentTimeMillis();
                                try {
                                    createFirstGetConnection(connectionUrl, extraHeaders);
                                    this.downloadInfo.increaseAllConnectTime(System.currentTimeMillis() - jCurrentTimeMillis);
                                } catch (Throwable th2) {
                                    th = th2;
                                    this.downloadInfo.increaseAllConnectTime(System.currentTimeMillis() - jCurrentTimeMillis);
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                jCurrentTimeMillis = jCurrentTimeMillis3;
                            }
                        }
                        if (checkIsStoppedByUser()) {
                            endDownloadRunnable();
                            return;
                        } else {
                            this.downloadInfo.setFirstSpeedTime(System.currentTimeMillis() - jCurrentTimeMillis2);
                            resetRetainRetryTimes();
                            handleResponseWithSingleChunk(downloadChunkCreateFirstDownloadChunk, connectionUrl, this.firstGetConnection);
                        }
                    } else {
                        if (!this.downloadInfo.isNeedReuseFirstConnection()) {
                            closeFirstConnection();
                        }
                        if (checkIsStoppedByUser()) {
                            endDownloadRunnable();
                            return;
                        }
                        resetRetainRetryTimes();
                        this.downloadInfo.setFirstSpeedTime(System.currentTimeMillis() - jCurrentTimeMillis2);
                        if (this.canResumeFromCache) {
                            handleResponseWithMultiChunkFromResume(iCalculateChunkCount, downloadChunk);
                        } else {
                            handleResponseMultiChunkFromBegin(totalBytes, iCalculateChunkCount);
                        }
                    }
                    endDownloadRunnable();
                    return;
                } catch (Throwable th4) {
                    this.downloadInfo.increaseAllConnectTime(System.currentTimeMillis() - jCurrentTimeMillis3);
                    throw th4;
                }
            }
            endDownloadRunnable();
        } catch (Throwable th5) {
            endDownloadRunnable();
            throw th5;
        }
    }

    private void finishWithFileExist() {
        Logger.d(TAG, "finishWithFileExist");
        if (DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.FIX_END_FOR_FILE_EXIST_ERROR, true)) {
            if (this.existTargetFileName.equals(this.downloadInfo.getName())) {
                this.runStatus = RunStatus.RUN_STATUS_END_RIGHT_NOW;
                return;
            } else {
                this.runStatus = RunStatus.RUN_STATUS_END_FOR_FILE_EXIST;
                return;
            }
        }
        if (this.existTargetFileName.equals(this.downloadInfo.getTargetFilePath())) {
            this.runStatus = RunStatus.RUN_STATUS_END_RIGHT_NOW;
        } else {
            this.runStatus = RunStatus.RUN_STATUS_END_FOR_FILE_EXIST;
        }
    }

    private boolean downloadSegments() throws InterruptedException, BaseException {
        if (this.downloadInfo.isExpiredRedownload() || this.downloadInfo.getChunkCount() != 1 || this.downloadInfo.getThrottleNetSpeed() > 0) {
            return false;
        }
        JSONObject jSONObjectOptJSONObject = DownloadSetting.obtain(this.downloadInfo.getId()).optJSONObject(DownloadSettingKeys.SEGMENT_CONFIG);
        List<Segment> segments = this.downloadCache.getSegments(this.downloadInfo.getId());
        if (this.downloadInfo.getCurBytes() > 0) {
            if (segments == null || segments.isEmpty()) {
                return false;
            }
            if (jSONObjectOptJSONObject == null) {
                jSONObjectOptJSONObject = new JSONObject();
            }
        }
        if (jSONObjectOptJSONObject == null) {
            return false;
        }
        this.segmentDispatcher = new SegmentDispatcher(this.downloadInfo, SegmentStrategy.from(jSONObjectOptJSONObject), this);
        if (checkIsStoppedByUser()) {
            Logger.i(TAG, "downloadSegments: is stopped by user");
            if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                this.segmentDispatcher.cancel();
            } else {
                this.segmentDispatcher.pause();
            }
            return true;
        }
        return this.segmentDispatcher.downloadSegments(segments);
    }

    private void resetRetainRetryTimes() {
        if (DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.RESET_RETAIN_RETRY_TIMES, 0) != 1 || this.resetRetainRetryTimesCount >= 3) {
            return;
        }
        this.retainRetryTimes.set(this.downloadInfo.isBackUpUrlUsed() ? this.downloadInfo.getBackUpUrlRetryCount() : this.downloadInfo.getRetryCount());
        this.resetRetainRetryTimesCount++;
    }

    private void doFirstConnect(String str, List<HttpHeader> list, long j) throws RetryThrowable, BaseException {
        createFirstHeadConnection(str, list, j);
        if (this.firstHeadConnection != null) {
            try {
                handleFirstConnection(str, this.firstHeadConnection, j);
            } catch (Throwable unused) {
                this.firstHeadConnectionFailed = true;
            }
        }
        if (this.firstHeadConnection == null || this.firstHeadConnectionFailed) {
            createFirstGetConnection(str, list);
            handleFirstConnection(str, this.firstGetConnection, j);
        }
    }

    private void endDownloadRunnable() {
        boolean zDoTaskStatusHandle;
        boolean z;
        Logger.d(TAG, "endDownloadRunnable::runStatus=" + this.runStatus);
        boolean z2 = (this.runStatus == RunStatus.RUN_STATUS_PAUSE || this.runStatus == RunStatus.RUN_STATUS_CANCELED) ? false : true;
        try {
            zDoTaskStatusHandle = doTaskStatusHandle();
            z = false;
        } catch (Exception e) {
            if (e instanceof BaseException) {
                this.statusHandler.onError((BaseException) e);
            } else {
                this.statusHandler.onError(new BaseException(DownloadErrorCode.ERROR_DOWNLOAD_FINALLY_UNKNOWN, e));
            }
            zDoTaskStatusHandle = true;
            z = true;
        }
        if (zDoTaskStatusHandle || z) {
            this.isAlive.set(false);
            if (z2) {
                try {
                    AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
                    if (downloadEngine != null) {
                        downloadEngine.removeDownloadRunnable(this);
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    DownloadMonitorHelper.monitorSendWithTaskMonitor(this.downloadTask.getMonitorDepend(), this.downloadInfo, new BaseException(DownloadErrorCode.ERROR_REMOVE_DOWNLOAD_RUNNABLE, DownloadUtils.getErrorMsgWithTagPrefix(th, "removeDownloadRunnable")), this.downloadInfo != null ? this.downloadInfo.getStatus() : 0);
                    return;
                }
            }
            return;
        }
        this.needJumpToStart = true;
        Logger.d(TAG, "jump to restart");
    }

    private void createFirstHeadConnection(String str, List<HttpHeader> list, long j) throws RetryThrowable, BaseException {
        FakeDownloadHeadHttpConnection cachedHeadConnection;
        boolean z = true;
        if (this.downloadInfo.getChunkCount() == 1 && (cachedHeadConnection = DownloadConnectionPool.getInstance().getCachedHeadConnection(str, list)) != null) {
            this.firstHeadConnection = cachedHeadConnection;
            this.downloadInfo.setPreconnectLevel(1);
        }
        if (this.firstHeadConnection == null && !this.firstHeadConnectionFailed && this.downloadInfo.isHeadConnectionAvailable()) {
            try {
                int iOptInt = this.setting.optInt(DownloadSettingKeys.NET_LIB_STRATEGY);
                if (this.setting.optInt(DownloadSettingKeys.MONITOR_DOWNLOAD_CONNECT, 0) <= 0) {
                    z = false;
                }
                this.firstHeadConnection = DownloadComponentManager.downloadWithHeadConnection(str, list, iOptInt, z, this.downloadInfo);
            } catch (Throwable th) {
                this.downloadInfo.setHeadConnectionException(DownloadUtils.getThrowableMsg(th));
            }
        }
    }

    private void createFirstGetConnection(String str, List<HttpHeader> list) throws RetryThrowable, BaseException {
        if (this.firstGetConnection != null) {
            return;
        }
        FakeDownloadHttpConnection cachedDownloadConnection = this.downloadInfo.getChunkCount() == 1 ? DownloadConnectionPool.getInstance().getCachedDownloadConnection(str, list) : null;
        try {
            if (cachedDownloadConnection != null) {
                setHttpResponseStatus(this.firstGetConnection);
                this.downloadInfo.setPreconnectLevel(2);
                this.firstGetConnection = cachedDownloadConnection;
            } else {
                try {
                    this.firstGetConnection = DownloadComponentManager.downloadWithConnection(this.downloadInfo.isNeedDefaultHttpServiceBackUp(), this.downloadInfo.getMaxBytes(), str, null, list, this.setting.optInt(DownloadSettingKeys.NET_LIB_STRATEGY), this.setting.optInt(DownloadSettingKeys.MONITOR_DOWNLOAD_CONNECT, 0) > 0, this.downloadInfo);
                } catch (BaseException e) {
                    throw e;
                } catch (Throwable th) {
                    if (this.downloadInfo.isExpiredRedownload() && DownloadUtils.isResponseCode304Error(th) && DownloadUtils.hasDownloadCacheHeader(list)) {
                        Logger.d(TAG, "dcache=execepiton responseCode=304 lastModified not changed, use local file.. old cacheControl=" + this.downloadInfo.getCacheControl());
                        long jOptInt = DownloadUtils.parserMaxAge(this.downloadInfo.getCacheControl());
                        if (jOptInt <= 0) {
                            jOptInt = DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.DEFAULT_304_MAX_AGE, 300);
                        }
                        this.downloadInfo.setCacheExpiredTime(System.currentTimeMillis() + (jOptInt * 1000));
                        throw new DownloadFileExistException(this.existTargetFileName);
                    }
                    if (DownloadUtils.isResponseCode416Error(th)) {
                        handleResponseCodeError("", "http code 416");
                    } else if (DownloadUtils.isResponseCode412Error(th)) {
                        handleResponseCodeError("", "http code 412");
                    } else {
                        DownloadUtils.parseException(th, "CreateFirstConnection");
                    }
                }
            }
            if (this.firstGetConnection == null) {
                throw new BaseException(DownloadErrorCode.ERROR_NO_CONNECTION, new IOException("download can't continue, firstConnection is null"));
            }
        } finally {
            setHttpResponseStatus(this.firstGetConnection);
        }
    }

    private void closeFirstHeadConnection() {
        if (this.firstHeadConnection != null) {
            this.firstHeadConnection.cancel();
            this.firstHeadConnection = null;
        }
    }

    private void closeFirstConnection() {
        if (this.firstGetConnection != null) {
            this.firstGetConnection.end();
            this.firstGetConnection = null;
        }
    }

    private void closeConnection() {
        closeFirstHeadConnection();
        closeFirstConnection();
    }

    public static DownloadChunk createFirstDownloadChunk(DownloadInfo downloadInfo, long j) {
        return new DownloadChunk.Builder(downloadInfo.getId()).chunkIndex(-1).startOffset(0L).oldOffset(j).currentOffset(j).endOffset(0L).contentLength(downloadInfo.getTotalBytes() - j).build();
    }

    private List<HttpHeader> getExtraHeaders(DownloadChunk downloadChunk) {
        List<HttpHeader> listAddRangeHeader = DownloadUtils.addRangeHeader(this.downloadInfo.getExtraHeaders(), this.downloadInfo.geteTag(), downloadChunk);
        if (this.downloadInfo.isExpiredRedownload() && this.needCheckIfModified && this.downloadInfo.getLastModified() != null) {
            listAddRangeHeader.add(new HttpHeader(DownloadUtils.IF_MODIFIED_SINCE, this.downloadInfo.getLastModified()));
            listAddRangeHeader.add(new HttpHeader(DownloadUtils.HEADER_TAG_DOWNLOAD_CACHE, DownloadUtils.HEADER_TAG_DOWNLOAD_CACHE));
            Logger.d(TAG, "dcache::add head IF_MODIFIED_SINCE=" + this.downloadInfo.getLastModified());
        }
        return listAddRangeHeader;
    }

    private void handleFirstResponse() throws Throwable {
        if (this.firstHttpResponseHandler != null) {
            if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                this.downloadInfo.setStatus(-4);
                this.firstHttpResponseHandler.cancel();
            } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
                this.downloadInfo.setStatus(-2);
                this.firstHttpResponseHandler.pause();
            } else {
                this.firstHttpResponseHandler.handleResponse();
            }
        }
    }

    private boolean isStoppedStatus() {
        return this.runStatus == RunStatus.RUN_STATUS_CANCELED || this.runStatus == RunStatus.RUN_STATUS_PAUSE;
    }

    private boolean checkIsStoppedByUser() {
        if (!isStoppedStatus() && this.downloadInfo.getStatus() != -2) {
            return false;
        }
        if (isStoppedStatus()) {
            return true;
        }
        if (this.downloadInfo.getStatus() == -2) {
            this.runStatus = RunStatus.RUN_STATUS_PAUSE;
            return true;
        }
        if (this.downloadInfo.getStatus() != -4) {
            return true;
        }
        this.runStatus = RunStatus.RUN_STATUS_CANCELED;
        return true;
    }

    private boolean doTaskStatusHandle() {
        if (this.runStatus == RunStatus.RUN_STATUS_ERROR) {
            this.statusHandler.onError(this.errorException);
        } else if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
            this.statusHandler.onCancel();
        } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
            this.statusHandler.onPause();
        } else if (this.runStatus == RunStatus.RUN_STATUS_END_RIGHT_NOW) {
            try {
                this.statusHandler.onCompleteForFileExist();
            } catch (BaseException e) {
                this.statusHandler.onError(e);
            }
        } else if (this.runStatus == RunStatus.RUN_STATUS_END_FOR_FILE_EXIST) {
            try {
                this.statusHandler.onCompleteForFileExist(this.existTargetFileName);
            } catch (BaseException e2) {
                this.statusHandler.onError(e2);
            }
        } else {
            if (this.runStatus == RunStatus.RUN_STATUS_ALL_CHUNK_RETRY_WITH_RESET) {
                this.statusHandler.onRetry(this.errorException, false);
                return false;
            }
            if (this.runStatus == RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER) {
                return true;
            }
            if (this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY && !isAllChunkDownloadComplete()) {
                Logger.d(TAG, "doTaskStatusHandle retryDelay");
                startRetryDelayAlarm();
                return this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY;
            }
            try {
                if (!checkCompletedByteValid()) {
                    return false;
                }
                this.statusHandler.onCompleted();
                RetryScheduler.getInstance().scheduleRetryWhenHasTaskSucceed();
            } catch (Throwable th) {
                onError(new BaseException(DownloadErrorCode.ERROR_HANDLE_COMPLETE, DownloadUtils.getErrorMsgWithTagPrefix(th, "doTaskStatusHandle onComplete")));
            }
        }
        return true;
    }

    private boolean isAllChunkDownloadComplete() {
        if (this.downloadInfo.getChunkCount() <= 1) {
            return this.downloadInfo.getCurBytes() > 0 && this.downloadInfo.getCurBytes() == this.downloadInfo.getTotalBytes();
        }
        List<DownloadChunk> downloadChunk = this.downloadCache.getDownloadChunk(this.downloadInfo.getId());
        if (downloadChunk == null || downloadChunk.size() <= 1) {
            return false;
        }
        for (DownloadChunk downloadChunk2 : downloadChunk) {
            if (downloadChunk2 == null || !downloadChunk2.hasNoBytesDownload()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCompletedByteValid() {
        if (this.downloadInfo.isChunked()) {
            this.downloadInfo.setTotalBytes(this.downloadInfo.getCurBytes());
        }
        Logger.i(TAG, "checkCompletedByteValid: downloadInfo.getCurBytes() = " + this.downloadInfo.getCurBytes() + ",  downloadInfo.getTotalBytes() = " + this.downloadInfo.getTotalBytes());
        if (this.downloadInfo.getCurBytes() > 0) {
            if (this.downloadInfo.isIgnoreDataVerify()) {
                return true;
            }
            if (this.downloadInfo.getTotalBytes() > 0 && this.downloadInfo.getCurBytes() == this.downloadInfo.getTotalBytes()) {
                return true;
            }
        }
        this.downloadInfo.setByteInvalidRetryStatus(ByteInvalidRetryStatus.BYTE_INVALID_RETRY_STATUS_RESTART);
        this.downloadInfo.reset();
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
        return false;
    }

    private void startRetryDelayAlarm() {
        this.runStatus = RunStatus.RUN_STATUS_NONE;
    }

    private long getDelayTime() {
        return this.retryDelayTimeCalculator.calculateRetryDelayTime(this.downloadInfo.getCurRetryTimeInTotal(), this.downloadInfo.getTotalRetryCount());
    }

    private void checkHasAnotherSameTask() throws RetryThrowable, BaseException {
        AbsDownloadEngine downloadEngine;
        int id = this.downloadInfo.getId();
        int downloadId = DownloadComponentManager.getDownloadId(this.downloadInfo);
        if (this.downloadInfo.isDownloaded() && !this.downloadInfo.isExpiredRedownload() && !this.needCheckIfModified) {
            throw new BaseException(DownloadErrorCode.ERROR_FILE_ALREADY_EXISTS, "file has downloaded");
        }
        DownloadInfo downloadInfo = this.downloadCache.getDownloadInfo(downloadId);
        if (downloadInfo == null || (downloadEngine = DownloadComponentManager.getDownloadEngine()) == null || downloadInfo.getId() == id || !downloadInfo.equalsTask(this.downloadInfo)) {
            return;
        }
        if (downloadEngine.isDownloading(downloadInfo.getId())) {
            this.downloadCache.removeDownloadTaskData(id);
            throw new BaseException(1025, "another same task is downloading");
        }
        List<DownloadChunk> downloadChunk = this.downloadCache.getDownloadChunk(downloadId);
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
        this.downloadCache.removeDownloadTaskData(downloadId);
        if (downloadInfo == null || !downloadInfo.isBreakpointAvailable()) {
            return;
        }
        this.downloadInfo.copyFromCacheData(downloadInfo, false);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        if (downloadChunk != null) {
            for (DownloadChunk downloadChunk2 : downloadChunk) {
                downloadChunk2.setId(id);
                this.downloadCache.addDownloadChunk(downloadChunk2);
            }
        }
        throw new RetryThrowable("retry task because id generator changed");
    }

    private void handleResponseWithMultiChunkFromResume(int i, List<DownloadChunk> list) throws BaseException {
        if (list.size() != i) {
            throw new BaseException(DownloadErrorCode.ERROR_CHUNK_NOT_EQUALS_CACHE, new IllegalArgumentException());
        }
        handleResponseWithMultiChunk(list, this.downloadInfo.getTotalBytes());
    }

    private void handleResponseMultiChunkFromBegin(long j, int i) throws BaseException {
        long j2 = j / i;
        int id = this.downloadInfo.getId();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        long j3 = 0;
        while (i2 < i) {
            DownloadChunk downloadChunkBuild = new DownloadChunk.Builder(id).chunkIndex(i2).startOffset(j3).oldOffset(j3).currentOffset(j3).endOffset(i2 == i + (-1) ? 0L : (j3 + j2) - 1).build();
            arrayList.add(downloadChunkBuild);
            this.downloadCache.addDownloadChunk(downloadChunkBuild);
            j3 += j2;
            i2++;
        }
        this.downloadInfo.setChunkCount(i);
        this.downloadCache.updateChunkCount(id, i);
        handleResponseWithMultiChunk(arrayList, j);
    }

    private void handleResponseWithMultiChunk(List<DownloadChunk> list, long j) throws BaseException {
        long endOffset;
        for (DownloadChunk downloadChunk : list) {
            if (downloadChunk != null) {
                if (downloadChunk.getEndOffset() == 0) {
                    endOffset = j - downloadChunk.getCurrentOffset();
                } else {
                    endOffset = (downloadChunk.getEndOffset() - downloadChunk.getCurrentOffset()) + 1;
                }
                if (endOffset > 0) {
                    downloadChunk.setContentLength(endOffset);
                    if (this.downloadInfo.isNeedReuseFirstConnection() && this.firstGetConnection != null && (!this.downloadInfo.isHeadConnectionAvailable() || this.firstHeadConnectionFailed)) {
                        if (downloadChunk.getChunkIndex() == 0) {
                            this.downloadChunkRunnableList.add(new DownloadChunkRunnable(downloadChunk, this.downloadTask, this.firstGetConnection, this));
                        } else if (downloadChunk.getChunkIndex() > 0) {
                            this.downloadChunkRunnableList.add(new DownloadChunkRunnable(downloadChunk, this.downloadTask, this));
                        }
                    } else {
                        this.downloadChunkRunnableList.add(new DownloadChunkRunnable(downloadChunk, this.downloadTask, this));
                    }
                }
            }
        }
        if (DownloadExpSwitchCode.isSwitchEnable(64)) {
            ArrayList arrayList = new ArrayList(this.downloadChunkRunnableList.size());
            Iterator<DownloadChunkRunnable> it = this.downloadChunkRunnableList.iterator();
            while (it.hasNext()) {
                DownloadChunkRunnable next = it.next();
                if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                    next.cancel();
                } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
                    next.pause();
                } else {
                    arrayList.add(next);
                }
            }
            try {
                List<Future> listExecuteFutureTasks = DefaultDownloadEngine.executeFutureTasks(arrayList);
                for (Runnable unstartedTask = (Runnable) arrayList.remove(0); unstartedTask != null; unstartedTask = DefaultDownloadEngine.getUnstartedTask(listExecuteFutureTasks)) {
                    if (checkIsStoppedByUser()) {
                        return;
                    }
                    try {
                        unstartedTask.run();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (listExecuteFutureTasks == null || listExecuteFutureTasks.isEmpty()) {
                    return;
                }
                for (Future future : listExecuteFutureTasks) {
                    if (future != null && !future.isDone()) {
                        try {
                            future.get();
                        } catch (Throwable unused) {
                        }
                    }
                }
                return;
            } catch (Throwable unused2) {
                return;
            }
        }
        ArrayList arrayList2 = new ArrayList(this.downloadChunkRunnableList.size());
        Iterator<DownloadChunkRunnable> it2 = this.downloadChunkRunnableList.iterator();
        while (it2.hasNext()) {
            DownloadChunkRunnable next2 = it2.next();
            if (this.runStatus == RunStatus.RUN_STATUS_CANCELED) {
                next2.cancel();
            } else if (this.runStatus == RunStatus.RUN_STATUS_PAUSE) {
                next2.pause();
            } else {
                arrayList2.add(Executors.callable(next2));
            }
        }
        if (checkIsStoppedByUser()) {
            return;
        }
        try {
            DefaultDownloadEngine.invokeFutureTasks(arrayList2);
        } catch (InterruptedException e) {
            throw new BaseException(DownloadErrorCode.ERROR_THREAD_INTERRUPT, e);
        }
    }

    private void handleResponseWithSingleChunk(DownloadChunk downloadChunk, String str, IDownloadHttpConnection iDownloadHttpConnection) throws Throwable {
        downloadChunk.setContentLength(this.downloadInfo.getTotalBytes() - downloadChunk.getCurrentOffset());
        this.downloadInfo.setChunkCount(1);
        this.downloadCache.updateChunkCount(this.downloadInfo.getId(), 1);
        this.firstHttpResponseHandler = new DownloadResponseHandler(this.downloadInfo, str, iDownloadHttpConnection, downloadChunk, this);
        handleFirstResponse();
    }

    private boolean isMultiChunkDownloadAvailable() {
        return (this.downloadInfo == null || this.downloadInfo.isExpiredRedownload() || (this.canResumeFromCache && this.downloadInfo.getChunkCount() <= 1) || this.downloadInfo.isChunkDowngradeRetryUsed() || !this.acceptPartial || this.isChunked) ? false : true;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:84|6|(9:8|(1:10)|11|(2:13|(4:15|(2:17|(1:19)(2:20|21))(1:22)|23|(3:25|(1:27)|48))(2:28|(1:30)(4:31|(1:33)(1:34)|35|36)))|80|49|50|71|72)(2:37|(4:39|(1:41)(1:42)|43|44)(2:45|(2:75|76)))|47|48|80|49|50|71|72) */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x016a, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x016c, code lost:
    
        com.ss.android.socialbase.downloader.logger.Logger.e(com.ss.android.socialbase.downloader.thread.DownloadRunnable.TAG, "checkSpaceOverflow: setLength1 e = " + r0 + ", mustSetLength = " + r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x018e, code lost:
    
        if (r6 >= r2) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x019a, code lost:
    
        r5.setLength(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x019e, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01a0, code lost:
    
        com.ss.android.socialbase.downloader.logger.Logger.e(com.ss.android.socialbase.downloader.thread.DownloadRunnable.TAG, "checkSpaceOverflow: setLength2 ex = " + r0 + ", mustSetLength = " + r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01be, code lost:
    
        if (r4 == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01c6, code lost:
    
        throw new com.ss.android.socialbase.downloader.exception.BaseException(com.ss.android.socialbase.downloader.constants.DownloadErrorCode.ERROR_OUTPUT_STREAM_SET_LENGTH_IO, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01c7, code lost:
    
        if (r4 != false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01d8, code lost:
    
        throw new com.ss.android.socialbase.downloader.exception.BaseException(com.ss.android.socialbase.downloader.constants.DownloadErrorCode.ERROR_OUTPUT_STREAM_SET_LENGTH_IO, r0);
     */
    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void checkSpaceOverflow(long r19) throws java.lang.InterruptedException, java.io.IOException, com.ss.android.socialbase.downloader.exception.BaseException {
        /*
            Method dump skipped, instructions count: 490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.thread.DownloadRunnable.checkSpaceOverflow(long):void");
    }

    private void checkSpaceOverflowInProgress() throws BaseException {
        long availableSpaceBytes;
        int iOptInt;
        try {
            availableSpaceBytes = DownloadUtils.getAvailableSpaceBytes(this.downloadInfo.getTempPath());
        } catch (BaseException unused) {
            availableSpaceBytes = 0;
        }
        Logger.i(TAG, "checkSpaceOverflowInProgress: available = " + DownloadUtils.byteToMb(availableSpaceBytes) + "MB");
        if (availableSpaceBytes > 0) {
            long totalBytes = this.downloadInfo.getTotalBytes() - this.downloadInfo.getCurBytes();
            if (availableSpaceBytes < totalBytes && (iOptInt = DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.SPACE_FILL_MIN_KEEP_MB, 100)) > 0) {
                long j = availableSpaceBytes - (iOptInt * 1048576);
                Logger.i(TAG, "checkSpaceOverflowInProgress: minKeep  = " + iOptInt + "MB, canDownload = " + DownloadUtils.byteToMb(j) + "MB");
                if (j <= 0) {
                    this.curBytesNeedCheckSpaceOverFlow = 0L;
                    throw new DownloadOutOfSpaceException(availableSpaceBytes, totalBytes);
                }
                this.curBytesNeedCheckSpaceOverFlow = this.downloadInfo.getCurBytes() + j + 1048576;
                return;
            }
        }
        this.curBytesNeedCheckSpaceOverFlow = 0L;
    }

    private void checkWifiTaskValid() throws DownloadRetryNeedlessException {
        if (this.downloadInfo.isOnlyWifi() && !DownloadUtils.checkPermission(DownloadComponentManager.getAppContext(), MsgConstant.PERMISSION_ACCESS_NETWORK_STATE)) {
            throw new DownloadRetryNeedlessException(DownloadErrorCode.ERROR_CANNOT_ACCESS_NETWORK, String.format("download task need permission:%s", MsgConstant.PERMISSION_ACCESS_NETWORK_STATE));
        }
        if (!this.downloadInfo.isDownloadWithWifiValid()) {
            throw new DownloadOnlyWifiException();
        }
        if (!this.downloadInfo.isPauseReserveWithWifiValid()) {
            throw new DownloadPauseReserveWifiException();
        }
    }

    private void checkSavePathValid() throws InterruptedException, BaseException {
        if (TextUtils.isEmpty(this.downloadInfo.getSavePath())) {
            throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_EMPTY, "download savePath can not be empty");
        }
        if (TextUtils.isEmpty(this.downloadInfo.getName())) {
            throw new BaseException(DownloadErrorCode.ERROR_FILE_NAME_EMPTY, "download name can not be empty");
        }
        File file = new File(this.downloadInfo.getSavePath());
        if (!file.exists()) {
            boolean zMkdirs = file.mkdirs();
            if (zMkdirs || file.exists()) {
                return;
            }
            int i = 0;
            if (DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.OPT_MKDIR_FAILED, 0) == 1) {
                while (!zMkdirs) {
                    int i2 = i + 1;
                    if (i >= 3) {
                        break;
                    }
                    try {
                        Thread.sleep(10L);
                        zMkdirs = file.mkdirs();
                        i = i2;
                    } catch (InterruptedException unused) {
                    }
                }
                if (zMkdirs || file.exists()) {
                    return;
                }
                if (DownloadUtils.getAvailableSpaceBytes(this.downloadInfo.getSavePath()) < DownloadConstants.MK_DIR_MIN_SPACE) {
                    throw new BaseException(DownloadErrorCode.ERROR_INSUFFICIENT_SPACE, "download savePath directory can not created:" + this.downloadInfo.getSavePath());
                }
                throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_CREATE_FAILED, "download savePath directory can not created:" + this.downloadInfo.getSavePath());
            }
            throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_CREATE_FAILED, "download savePath directory can not created:" + this.downloadInfo.getSavePath());
        }
        if (file.isDirectory()) {
            return;
        }
        if (DownloadSettingsUtils.isOptimizeSavePath(this.downloadInfo)) {
            file.delete();
            if (file.mkdirs() || file.exists()) {
                return;
            }
            throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_NOT_DIRECTORY, "download savePath is not directory:path=" + this.downloadInfo.getSavePath());
        }
        throw new BaseException(DownloadErrorCode.ERROR_SAVE_PATH_NOT_DIRECTORY, "download savePath is not a directory:" + this.downloadInfo.getSavePath());
    }

    private boolean isResponseCodeError(int i, String str, String str2) {
        if (i == 412) {
            return true;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2) || !(this.isResponseFromBegin || this.acceptPartial)) {
            return (i == 201 || i == 416) && this.downloadInfo.getCurBytes() > 0;
        }
        return true;
    }

    private void handleResponseCodeError(String str, String str2) throws RetryThrowable {
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
        this.canResumeFromCache = false;
        this.downloadInfo.resetDataForEtagEndure(str);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        throw new RetryThrowable(str2);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void handleFirstConnection(String str, IDownloadHeadHttpConnection iDownloadHeadHttpConnection, long j) throws RetryThrowable, BaseException {
        long contentRangeOfInstanceLength;
        if (iDownloadHeadHttpConnection == null) {
            return;
        }
        try {
            HttpResponse httpResponse = new HttpResponse(str, iDownloadHeadHttpConnection);
            int i = httpResponse.responseCode;
            String contentType = httpResponse.getContentType();
            if (TextUtils.isEmpty(this.downloadInfo.getMimeType()) && !TextUtils.isEmpty(contentType)) {
                this.downloadInfo.setMimeType(contentType);
            }
            this.acceptPartial = httpResponse.acceptPartial();
            this.downloadInfo.setSupportPartial(this.acceptPartial);
            this.isResponseFromBegin = httpResponse.isResponseDataFromBegin();
            String str2 = this.downloadInfo.geteTag();
            String etag = httpResponse.getEtag();
            String lastModified = httpResponse.getLastModified();
            String cacheControl = httpResponse.getCacheControl();
            Logger.d(TAG, "dcache=responseCode=" + i + " last_modified=" + lastModified + " CACHE_CONTROL=" + cacheControl + " max-age=" + httpResponse.getMaxAge() + " isDeleteCacheIfCheckFailed=" + this.downloadInfo.isDeleteCacheIfCheckFailed());
            Logger.d(TAG, "dcache=firstOffset=" + j + " cur=" + lastModified + " before=" + this.downloadInfo.getLastModified() + " cur=" + httpResponse.getTotalLength() + " before=" + this.downloadInfo.getTotalBytes());
            if (!TextUtils.isEmpty(cacheControl)) {
                this.downloadInfo.setCacheControl(cacheControl);
                if (httpResponse.getMaxAge() > 0) {
                    this.downloadInfo.setCacheExpiredTime(System.currentTimeMillis() + (httpResponse.getMaxAge() * 1000));
                }
            }
            if (this.downloadInfo.isExpiredRedownload() && this.needCheckIfModified && !TextUtils.isEmpty(this.existTargetFileName)) {
                boolean z = false;
                if (i == 304 || ((!TextUtils.isEmpty(this.downloadInfo.getLastModified()) || !this.downloadInfo.isDeleteCacheIfCheckFailed()) && TextUtils.equals(lastModified, this.downloadInfo.getLastModified()))) {
                    z = true;
                }
                if (z) {
                    Logger.d(TAG, "dcache=responseCode=" + i + " lastModified not changed, use local file  " + lastModified);
                    throw new DownloadFileExistException(this.existTargetFileName);
                }
                DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
            }
            if (j > 0 && this.downloadInfo.isExpiredRedownload() && !TextUtils.equals(lastModified, this.downloadInfo.getLastModified())) {
                Logger.d(TAG, "dcache cdn file change, so retry");
                handleResponseCodeError("", "cdn file changed");
            }
            if (!TextUtils.isEmpty(lastModified)) {
                this.downloadInfo.setLastModified(lastModified);
            }
            if (isResponseCodeError(i, str2, etag)) {
                if (iDownloadHeadHttpConnection instanceof IDownloadHttpConnection) {
                    if (!TextUtils.isEmpty(str2) && str2.equals(etag)) {
                        etag = "";
                    }
                    handleResponseCodeError(etag, "eTag of server file changed");
                } else {
                    throw new DownloadHttpException(1002, i, "");
                }
            }
            if (!this.acceptPartial && !this.isResponseFromBegin) {
                if (i == 403) {
                    throw new BaseException(DownloadErrorCode.ERROR_DOWNLOAD_FORBIDDEN, "response code error : 403");
                }
                throw new DownloadHttpException(1004, i, "response code error : " + i);
            }
            if (this.isResponseFromBegin && j > 0) {
                if (iDownloadHeadHttpConnection instanceof IDownloadHttpConnection) {
                    handleResponseCodeError("", "http head request not support");
                } else {
                    throw new BaseException(1004, "isResponseFromBegin but firstOffset > 0");
                }
            }
            long contentLength = httpResponse.getContentLength();
            if (!(iDownloadHeadHttpConnection instanceof IDownloadHttpConnection) && contentLength < 0 && DownloadSettingsUtils.isOptimizeHeadRequest(this.downloadInfo)) {
                throw new BaseException(1004, "");
            }
            String fileNameFromConnection = TextUtils.isEmpty(this.downloadInfo.getName()) ? DownloadUtils.getFileNameFromConnection(iDownloadHeadHttpConnection, this.downloadInfo.getUrl()) : "";
            this.isChunked = httpResponse.isChunked();
            if (!this.isChunked && contentLength == 0 && !(iDownloadHeadHttpConnection instanceof IDownloadHttpConnection)) {
                throw new BaseException(1004, "");
            }
            if (this.isChunked) {
                contentRangeOfInstanceLength = -1;
            } else {
                String respHeadFieldIgnoreCase = DownloadUtils.getRespHeadFieldIgnoreCase(iDownloadHeadHttpConnection, "Content-Range");
                Logger.i(TAG, "firstConnection: contentRange = " + respHeadFieldIgnoreCase);
                if (!TextUtils.isEmpty(respHeadFieldIgnoreCase) && this.setting.optBugFix(DownloadSettingKeys.BugFix.FIX_GET_TOTAL_BYTES, true)) {
                    contentRangeOfInstanceLength = DownloadUtils.parseContentRangeOfInstanceLength(respHeadFieldIgnoreCase);
                    Logger.i(TAG, "firstConnection: 1 totalLength = " + contentRangeOfInstanceLength);
                } else {
                    contentRangeOfInstanceLength = j + contentLength;
                    Logger.e(TAG, "firstConnection: 2 totalLength = " + contentRangeOfInstanceLength + ", contentLength = " + contentLength);
                }
            }
            if (checkIsStoppedByUser()) {
                return;
            }
            if (this.downloadInfo.getExpectFileLength() > 0 && DownloadSetting.obtain(this.downloadInfo.getId()).optInt(DownloadSettingKeys.FORCE_CHECK_FILE_LENGTH) == 1 && this.downloadInfo.getExpectFileLength() != contentRangeOfInstanceLength) {
                throw new BaseException(DownloadErrorCode.ERROR_CHECK_FILE_LENGTH_FAILED, "expectFileLength = " + this.downloadInfo.getExpectFileLength() + " , totalLength = " + contentRangeOfInstanceLength);
            }
            this.statusHandler.onFirstConnectionSuccessed(contentRangeOfInstanceLength, etag, fileNameFromConnection);
        } catch (BaseException e) {
            throw e;
        } catch (RetryThrowable e2) {
            throw e2;
        } catch (Throwable th) {
            DownloadUtils.parseException(th, "HandleFirstConnection");
        }
    }

    public boolean isAlive() {
        return this.isAlive.get();
    }

    public int getDownloadId() {
        if (this.downloadInfo != null) {
            return this.downloadInfo.getId();
        }
        return 0;
    }

    public void prepareDownload() {
        this.prepareDownloadTime = System.currentTimeMillis();
        this.statusHandler.onPrepare();
    }

    private void checkTaskCanResume() {
        long curByte = DownloadUtils.getCurByte(this.downloadInfo);
        long curBytes = this.downloadInfo.getCurBytes();
        if (curByte != curBytes) {
            Logger.w(TAG, "checkTaskCanResume: offset = " + curByte + ", curBytes = " + curBytes);
        }
        this.downloadInfo.setCurBytes(curByte);
        this.canResumeFromCache = curByte > 0;
        if (this.canResumeFromCache || this.needCheckIfModified) {
            return;
        }
        Logger.i(TAG, "checkTaskCanResume: deleteAllDownloadFiles");
        this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
        this.downloadCache.removeSegments(this.downloadInfo.getId());
        DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public boolean onProgress(long j) throws BaseException {
        if (this.curBytesNeedCheckSpaceOverFlow > 0 && this.downloadInfo.getCurBytes() > this.curBytesNeedCheckSpaceOverFlow) {
            checkSpaceOverflowInProgress();
        }
        return this.statusHandler.onProgress(j);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onCompleted(DownloadChunkRunnable downloadChunkRunnable) {
        if (this.isSingleChunk) {
            return;
        }
        synchronized (this) {
            this.downloadChunkRunnableList.remove(downloadChunkRunnable);
        }
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public boolean canRetry(BaseException baseException) {
        if (this.segmentDispatcher != null && DownloadUtils.isNetworkError(baseException) && this.retainRetryTimes.get() < this.downloadInfo.getRetryCount()) {
            return false;
        }
        if (DownloadUtils.isResponseCodeError(baseException)) {
            if (this.isSingleChunk && !this.isTriedFixRangeNotSatisfiable) {
                DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
                this.isTriedFixRangeNotSatisfiable = true;
            }
            return true;
        }
        if ((this.retainRetryTimes == null || this.retainRetryTimes.get() <= 0) && !this.downloadInfo.hasNextBackupUrl()) {
            if (baseException == null) {
                return false;
            }
            if ((baseException.getErrorCode() != 1011 && (baseException.getCause() == null || !(baseException.getCause() instanceof SSLHandshakeException))) || !this.downloadInfo.canReplaceHttpForRetry()) {
                return false;
            }
        }
        return !(baseException instanceof DownloadRetryNeedlessException);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onError(BaseException baseException) {
        Logger.d(TAG, "onError:" + baseException.getMessage());
        this.runStatus = RunStatus.RUN_STATUS_ERROR;
        this.errorException = baseException;
        cancelAllChunkRunnable();
    }

    private void clearCurrentDownloadData() {
        Logger.w(TAG, "clearCurrentDownloadData::" + Log.getStackTraceString(new Throwable()));
        try {
            this.downloadCache.removeAllDownloadChunk(this.downloadInfo.getId());
            this.downloadCache.removeSegments(this.downloadInfo.getId());
            DownloadUtils.deleteAllDownloadFiles(this.downloadInfo);
            this.canResumeFromCache = false;
            this.downloadInfo.resetDataForEtagEndure("");
            this.downloadCache.updateDownloadInfo(this.downloadInfo);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onChunkDowngradeRetry(BaseException baseException) {
        if (this.downloadInfo != null) {
            this.downloadInfo.setChunkDowngradeRetryUsed(true);
        }
        onAllChunkRetryWithReset(baseException, false);
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void onAllChunkRetryWithReset(BaseException baseException, boolean z) {
        Logger.d(TAG, "onAllChunkRetryWithReset");
        this.runStatus = RunStatus.RUN_STATUS_ALL_CHUNK_RETRY_WITH_RESET;
        this.errorException = baseException;
        cancelAllChunkRunnable();
        if (z ? handleRetryTime(baseException) : false) {
            return;
        }
        clearCurrentDownloadData();
    }

    private void cancelAllChunkRunnable() {
        try {
            Iterator it = ((ArrayList) this.downloadChunkRunnableList.clone()).iterator();
            while (it.hasNext()) {
                DownloadChunkRunnable downloadChunkRunnable = (DownloadChunkRunnable) it.next();
                if (downloadChunkRunnable != null) {
                    downloadChunkRunnable.cancel();
                }
            }
        } catch (Throwable th) {
            Logger.i(TAG, "cancelAllChunkRunnable: " + th.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleForbiddenCallback(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.downloadInfo.setForbiddenBackupUrls(list, this.runStatus == RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER);
        AbsDownloadEngine downloadEngine = DownloadComponentManager.getDownloadEngine();
        if (downloadEngine != null) {
            downloadEngine.restartAsyncWaitingTask(this.downloadInfo.getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDiskSpaceCallback() {
        AbsDownloadEngine downloadEngine;
        if (checkIsStoppedByUser() || (downloadEngine = DownloadComponentManager.getDownloadEngine()) == null) {
            return;
        }
        downloadEngine.restartAsyncWaitingTask(this.downloadInfo.getId());
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public RetryCheckStatus onSingleChunkRetry(DownloadChunk downloadChunk, BaseException baseException, long j) {
        if (isStoppedStatus()) {
            return RetryCheckStatus.RETURN;
        }
        if (baseException != null && (baseException.getErrorCode() == 1047 || DownloadUtils.isInsufficientSpaceError(baseException))) {
            return onRetry(baseException, j);
        }
        this.errorException = baseException;
        this.downloadInfo.increaseCurBytes(-j);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        if (handleRetryTime(baseException)) {
            return RetryCheckStatus.RETURN;
        }
        this.statusHandler.onSingleChunkRetry(downloadChunk, baseException, this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY);
        if (this.runStatus != RunStatus.RUN_STATUS_RETRY_DELAY && this.downloadInfo.isNeedRetryDelay()) {
            long delayTime = getDelayTime();
            if (delayTime > 0) {
                Logger.i(TAG, "onSingleChunkRetry with delay time " + delayTime);
                try {
                    Thread.sleep(delayTime);
                } catch (Throwable th) {
                    Logger.w(TAG, "onSingleChunkRetry:" + th.getMessage());
                }
            }
        }
        return RetryCheckStatus.CONTINUE;
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public RetryCheckStatus onRetry(BaseException baseException, long j) {
        long totalBytes;
        long avaliableSpaceBytes;
        boolean z;
        this.errorException = baseException;
        this.downloadInfo.increaseCurBytes(-j);
        this.downloadCache.updateDownloadInfo(this.downloadInfo);
        if (isStoppedStatus()) {
            return RetryCheckStatus.RETURN;
        }
        if (baseException != null && baseException.getErrorCode() == 1047) {
            if (this.forbiddenHandler == null || this.downloadInfo.isForbiddenRetryed()) {
                if (handleRetryTime(baseException)) {
                    return RetryCheckStatus.RETURN;
                }
            } else {
                AbsDownloadForbiddenCallback absDownloadForbiddenCallback = new AbsDownloadForbiddenCallback() { // from class: com.ss.android.socialbase.downloader.thread.DownloadRunnable.1
                    @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadForbiddenCallback, com.ss.android.socialbase.downloader.depend.IDownloadForbiddenCallback
                    public void onCallback(List<String> list) {
                        super.onCallback(list);
                        DownloadRunnable.this.handleForbiddenCallback(list);
                    }
                };
                boolean zOnForbidden = this.forbiddenHandler.onForbidden(absDownloadForbiddenCallback);
                this.downloadInfo.setForbiddenRetryed();
                if (zOnForbidden) {
                    if (!absDownloadForbiddenCallback.hasCallback()) {
                        cancelAllChunkRunnable();
                        this.statusHandler.handleWaitingAsyncHandler();
                        this.runStatus = RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER;
                        return RetryCheckStatus.RETURN;
                    }
                    z = true;
                }
            }
            z = false;
        } else if (DownloadUtils.isInsufficientSpaceError(baseException)) {
            if (this.diskSpaceHandler == null) {
                onError(baseException);
                return RetryCheckStatus.RETURN;
            }
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            IDownloadDiskSpaceCallback iDownloadDiskSpaceCallback = new IDownloadDiskSpaceCallback() { // from class: com.ss.android.socialbase.downloader.thread.DownloadRunnable.2
                @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceCallback
                public void onDiskCleaned() {
                    synchronized (DownloadRunnable.this) {
                        atomicBoolean.set(true);
                        DownloadRunnable.this.handleDiskSpaceCallback();
                    }
                }
            };
            if (baseException instanceof DownloadOutOfSpaceException) {
                DownloadOutOfSpaceException downloadOutOfSpaceException = (DownloadOutOfSpaceException) baseException;
                avaliableSpaceBytes = downloadOutOfSpaceException.getAvaliableSpaceBytes();
                totalBytes = downloadOutOfSpaceException.getRequiredSpaceBytes();
            } else {
                totalBytes = this.downloadInfo.getTotalBytes();
                avaliableSpaceBytes = -1;
            }
            synchronized (this) {
                if (this.diskSpaceHandler.cleanUpDisk(avaliableSpaceBytes, totalBytes, iDownloadDiskSpaceCallback)) {
                    if (!DownloadSetting.obtain(this.downloadInfo.getId()).optBugFix(DownloadSettingKeys.BugFix.NOT_DELETE_WHEN_CLEAN_SPACE, false)) {
                        checkCompletedByteValid();
                    }
                    if (!atomicBoolean.get()) {
                        if (this.runStatus != RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER) {
                            this.runStatus = RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER;
                            cancelAllChunkRunnable();
                            this.statusHandler.handleWaitingAsyncHandler();
                        }
                        return RetryCheckStatus.RETURN;
                    }
                    if (handleRetryTime(baseException)) {
                        return RetryCheckStatus.RETURN;
                    }
                    z = true;
                } else {
                    if (this.runStatus == RunStatus.RUN_STATUS_WAITING_ASYNC_HANDLER) {
                        return RetryCheckStatus.RETURN;
                    }
                    onError(baseException);
                    return RetryCheckStatus.RETURN;
                }
            }
        } else {
            if (handleRetryTime(baseException)) {
                return RetryCheckStatus.RETURN;
            }
            z = false;
        }
        if (!z && checkNeedRetryDelay()) {
            cancelAllChunkRunnable();
        }
        this.statusHandler.onRetry(baseException, this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY);
        return this.runStatus == RunStatus.RUN_STATUS_RETRY_DELAY ? RetryCheckStatus.RETURN : RetryCheckStatus.CONTINUE;
    }

    private boolean handleRetryTime(BaseException baseException) {
        boolean z = true;
        if (this.retainRetryTimes != null) {
            if (this.retainRetryTimes.get() <= 0 || (baseException != null && baseException.getErrorCode() == 1070)) {
                if (this.downloadInfo.trySwitchToNextBackupUrl()) {
                    this.retainRetryTimes.set(this.downloadInfo.getBackUpUrlRetryCount());
                    this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.get());
                } else if (baseException != null && ((baseException.getErrorCode() == 1011 || (baseException.getCause() != null && (baseException.getCause() instanceof SSLHandshakeException))) && this.downloadInfo.canReplaceHttpForRetry())) {
                    this.retainRetryTimes.set(this.downloadInfo.getRetryCount());
                    this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.get());
                    this.downloadInfo.setHttpsToHttpRetryUsed(true);
                } else {
                    onError(new BaseException(baseException.getErrorCode(), String.format("retry for exception, but current retry time : %s , retry Time %s all used, last error is %s", String.valueOf(this.retainRetryTimes), String.valueOf(this.downloadInfo.getRetryCount()), baseException.getErrorMessage())));
                    return true;
                }
                z = false;
            }
            if (this.runStatus != RunStatus.RUN_STATUS_RETRY_DELAY && z) {
                this.downloadInfo.updateCurRetryTime(this.retainRetryTimes.decrementAndGet());
            }
            return false;
        }
        onError(new BaseException(DownloadErrorCode.ERROR_RETAIN_RETRY_TIME_IS_NULL, "retry for exception, but retain retry time is null, last error is :" + baseException.getErrorMessage()));
        return true;
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public synchronized DownloadChunk getUnCompletedSubChunk(int i) {
        DownloadChunk unCompletedSubChunk;
        if (this.downloadInfo.getChunkCount() < 2) {
            return null;
        }
        List<DownloadChunk> downloadChunk = this.downloadCache.getDownloadChunk(this.downloadInfo.getId());
        if (downloadChunk != null && !downloadChunk.isEmpty()) {
            for (int i2 = 0; i2 < downloadChunk.size(); i2++) {
                DownloadChunk downloadChunk2 = downloadChunk.get(i2);
                if (downloadChunk2 != null && (unCompletedSubChunk = getUnCompletedSubChunk(downloadChunk2, i)) != null) {
                    return unCompletedSubChunk;
                }
            }
            return null;
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback
    public void setHttpResponseStatus(IDownloadHeadHttpConnection iDownloadHeadHttpConnection) {
        boolean z;
        if (iDownloadHeadHttpConnection != null) {
            try {
                int responseCode = iDownloadHeadHttpConnection.getResponseCode();
                this.downloadInfo.setHttpStatusCode(responseCode);
                this.downloadInfo.setHttpStatusMessage(DownloadHttpUtils.httpCodeToMessage(responseCode));
                z = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        this.downloadInfo.setHttpStatusCode(-1);
        this.downloadInfo.setHttpStatusMessage("");
    }

    private DownloadChunk getUnCompletedSubChunk(DownloadChunk downloadChunk, int i) {
        DownloadChunk downloadChunk2 = null;
        if (!downloadChunk.isHostChunk()) {
            return null;
        }
        long retainLength = downloadChunk.getRetainLength(true);
        Logger.d(TAG, "reuseChunk retainLen:" + retainLength + " chunkIndex:" + i);
        boolean z = false;
        if (!downloadChunk.hasChunkDivided() && retainLength > DownloadConstants.MIN_CHUNK_REUSE_SIZE && this.downloadInfo.isNeedReuseChunkRunnable()) {
            List<DownloadChunk> listDivideChunkForReuse = downloadChunk.divideChunkForReuse(this.downloadInfo.getChunkCount(), this.downloadInfo.getTotalBytes());
            if (listDivideChunkForReuse != null) {
                Iterator<DownloadChunk> it = listDivideChunkForReuse.iterator();
                while (it.hasNext()) {
                    this.downloadCache.addSubDownloadChunk(it.next());
                }
            }
        } else {
            if (downloadChunk.hasChunkDivided()) {
            }
            if (z || !downloadChunk.hasChunkDivided()) {
                return null;
            }
            for (int i2 = 1; i2 < downloadChunk.getSubChunkList().size(); i2++) {
                DownloadChunk downloadChunk3 = downloadChunk.getSubChunkList().get(i2);
                if (downloadChunk3 != null) {
                    Logger.d(TAG, "check can checkUnCompletedChunk -- chunkIndex:" + downloadChunk3.getChunkIndex() + " currentOffset:" + downloadChunk3.getCurrentOffset() + "  startOffset:" + downloadChunk3.getStartOffset() + " contentLen:" + downloadChunk3.getContentLength());
                    if (downloadChunk3.getChunkIndex() < 0 || (!downloadChunk3.hasNoBytesDownload() && !downloadChunk3.isDownloading())) {
                        downloadChunk2 = downloadChunk3;
                        break;
                    }
                }
            }
            if (downloadChunk2 != null) {
                long currentOffset = downloadChunk.getCurrentOffset();
                Logger.d(TAG, "unComplete chunk " + downloadChunk.getChunkIndex() + " curOffset:" + currentOffset + " reuseChunk chunkIndex:" + i + " for subChunk:" + downloadChunk2.getChunkIndex());
                this.downloadCache.updateSubDownloadChunkIndex(downloadChunk2.getId(), downloadChunk2.getChunkIndex(), downloadChunk2.getHostChunkIndex(), i);
                downloadChunk2.setChunkIndex(i);
                downloadChunk2.setDownloading(true);
            }
            return downloadChunk2;
        }
        z = true;
        if (z) {
        }
        return null;
    }

    private IRetryDelayTimeCalculator getRetryDelayTimeCalculator(DownloadTask downloadTask) {
        IRetryDelayTimeCalculator retryDelayTimeCalculator = downloadTask.getRetryDelayTimeCalculator();
        if (retryDelayTimeCalculator != null) {
            return retryDelayTimeCalculator;
        }
        DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
        if (downloadInfo != null) {
            String retryDelayTimeArray = downloadInfo.getRetryDelayTimeArray();
            if (!TextUtils.isEmpty(retryDelayTimeArray)) {
                return new RetryDelayTimeParamCalculator(retryDelayTimeArray);
            }
        }
        return DownloadComponentManager.getRetryDelayTimeCalculator();
    }

    public Future getFuture() {
        return this.mFuture;
    }

    public void setFuture(Future future) {
        this.mFuture = future;
    }

    public void setThrottleNetSpeed(long j) {
        if (this.firstGetConnection != null && (this.firstGetConnection instanceof AbsDownloadHttpConnection)) {
            try {
                ((AbsDownloadHttpConnection) this.firstGetConnection).setThrottleNetSpeedWhenRunning(j);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
