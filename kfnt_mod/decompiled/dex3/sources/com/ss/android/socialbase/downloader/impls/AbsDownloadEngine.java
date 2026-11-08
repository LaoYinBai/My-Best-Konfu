package com.ss.android.socialbase.downloader.impls;

import android.database.sqlite.SQLiteException;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import com.ss.android.socialbase.downloader.constants.AsyncHandleStatus;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.constants.DownloadStatus;
import com.ss.android.socialbase.downloader.constants.EnqueueType;
import com.ss.android.socialbase.downloader.constants.ListenerType;
import com.ss.android.socialbase.downloader.constants.RetryDelayStatus;
import com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadStatusHandler;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.thread.DownloadRunnable;
import com.ss.android.socialbase.downloader.thread.WeakDownloadHandler;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadListenerUtils;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.android.socialbase.downloader.utils.LruCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class AbsDownloadEngine implements WeakDownloadHandler.IHandler {
    private static final String TAG = "AbsDownloadEngine";
    private final SparseArray<DownloadTask> downloadTaskMap = new SparseArray<>();
    private final SparseArray<DownloadTask> successDownloadTaskMap = new SparseArray<>();
    private final SparseArray<DownloadTask> failedDownloadTaskMap = new SparseArray<>();
    private final SparseArray<DownloadTask> retryDelayDownloadTaskMap = new SparseArray<>();
    private final SparseArray<DownloadTask> waitingAsyncDownloadTaskMap = new SparseArray<>();
    private final SparseArray<SparseArray<DownloadTask>> downloadTaskWithListenerMap = new SparseArray<>();
    private final LruCache<Integer, DownloadTask> pengingTaskCache = new LruCache<>();
    private final SparseArray<Long> lastTaskTryDownloadTime = new SparseArray<>();
    private final LinkedBlockingDeque<DownloadTask> orderedTaskQueue = new LinkedBlockingDeque<>();
    protected final WeakDownloadHandler mainHandler = new WeakDownloadHandler(Looper.getMainLooper(), this);
    private final IDownloadCache downloadCache = DownloadComponentManager.getDownloadCache();

    protected abstract DownloadRunnable doCancel(int i);

    protected abstract void doDownload(int i, DownloadTask downloadTask);

    protected abstract void doPause(int i);

    public abstract void doSetThrottleNetSpeed(int i, long j);

    protected abstract List<Integer> getAllAliveDownloadIds();

    public abstract boolean isDownloading(int i);

    public abstract void removeDownloadRunnable(DownloadRunnable downloadRunnable);

    protected AbsDownloadEngine() {
    }

    private void tryCacheSameTaskWithListenerHashCode(DownloadTask downloadTask) {
        int hashCodeForSameTask = downloadTask.getHashCodeForSameTask();
        if (hashCodeForSameTask == 0 && downloadTask.isAutoSetHashCodeForSameTask()) {
            hashCodeForSameTask = downloadTask.autoCalAndGetHashCodeForSameTask();
        }
        if (hashCodeForSameTask == 0) {
            return;
        }
        SparseArray<DownloadTask> sparseArray = this.downloadTaskWithListenerMap.get(downloadTask.getDownloadId());
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.downloadTaskWithListenerMap.put(downloadTask.getDownloadId(), sparseArray);
        }
        Logger.d(TAG, "tryCacheSameTaskWithListenerHashCode id:" + downloadTask.getDownloadId() + " listener hasCode:" + hashCodeForSameTask);
        sparseArray.put(hashCodeForSameTask, downloadTask);
    }

    private void tryDownload(DownloadTask downloadTask, boolean z) {
        DownloadInfo downloadInfo;
        int status;
        DownloadInfo downloadInfo2;
        DownloadTask downloadTask2;
        if (downloadTask == null || (downloadInfo = downloadTask.getDownloadInfo()) == null) {
            return;
        }
        if (downloadInfo.isEntityInvalid()) {
            DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadInfo, new BaseException(1003, "downloadInfo is Invalid, url is " + downloadInfo.getUrl() + " name is " + downloadInfo.getName() + " savePath is " + downloadInfo.getSavePath()), downloadInfo.getStatus());
            return;
        }
        boolean z2 = false;
        if (DownloadSetting.obtain(downloadInfo.getId()).optInt(DownloadSettingKeys.NO_NET_OPT, 0) == 1 && !DownloadUtils.isNetworkConnected(DownloadComponentManager.getAppContext()) && !downloadInfo.isFirstDownload()) {
            new DownloadStatusHandler(downloadTask, this.mainHandler).onError(new BaseException(DownloadErrorCode.ERROR_NETWORK_NOT_AVAILABLE, "network_not_available"));
            return;
        }
        int id = downloadInfo.getId();
        if (z) {
            cancelAlarm(downloadInfo);
        }
        if (this.failedDownloadTaskMap.get(id) != null) {
            this.failedDownloadTaskMap.remove(id);
        }
        if (this.successDownloadTaskMap.get(id) != null) {
            this.successDownloadTaskMap.remove(id);
        }
        if (this.retryDelayDownloadTaskMap.get(id) != null) {
            this.retryDelayDownloadTaskMap.remove(id);
        }
        if (this.waitingAsyncDownloadTaskMap.get(id) != null) {
            this.waitingAsyncDownloadTaskMap.remove(id);
        }
        if (isDownloading(id) && !downloadInfo.canReStartAsyncTask()) {
            Logger.d(TAG, "another task with same id is downloading when tryDownload");
            downloadTask.addListenerToDownloadingSameTask();
            DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadInfo, new BaseException(1003, "downloadInfo is isDownloading and addListenerToSameTask is false"), downloadInfo.getStatus());
            return;
        }
        Logger.d(TAG, "no downloading task :" + id);
        if (downloadInfo.canReStartAsyncTask()) {
            downloadInfo.setAsyncHandleStatus(AsyncHandleStatus.ASYNC_HANDLE_RESTART);
        }
        if (DownloadExpSwitchCode.isSwitchEnable(32768) && (downloadTask2 = (DownloadTask) this.pengingTaskCache.remove(Integer.valueOf(id))) != null) {
            downloadTask.copyListenerFromPendingTask(downloadTask2);
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        DownloadTask downloadTask3 = this.downloadTaskMap.get(id);
        if (downloadTask3 == null || (downloadInfo2 = downloadTask3.getDownloadInfo()) == null) {
            status = 0;
        } else {
            status = downloadInfo2.getStatus();
            if (DownloadStatus.isDownloading(status)) {
                z2 = true;
            }
        }
        Logger.d(TAG, "can add listener " + z2 + " , oldTaskStatus is :" + status);
        if (z2) {
            downloadTask.addListenerToDownloadingSameTask();
            return;
        }
        tryCacheSameTaskWithListenerHashCode(downloadTask);
        this.downloadTaskMap.put(id, downloadTask);
        this.lastTaskTryDownloadTime.put(id, Long.valueOf(jUptimeMillis));
        doDownload(id, downloadTask);
    }

    public synchronized void tryDownload(DownloadTask downloadTask) {
        if (downloadTask == null) {
            return;
        }
        DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
        if (downloadInfo == null) {
            return;
        }
        downloadInfo.setDownloadFromReserveWifi(false);
        if (downloadInfo.getEnqueueType() != EnqueueType.ENQUEUE_NONE) {
            enqueue(downloadTask);
        } else {
            tryDownload(downloadTask, true);
        }
    }

    private void enqueue(DownloadTask downloadTask) throws InterruptedException {
        DownloadInfo downloadInfo;
        if (downloadTask == null || (downloadInfo = downloadTask.getDownloadInfo()) == null) {
            return;
        }
        try {
            if (this.orderedTaskQueue.isEmpty()) {
                tryDownload(downloadTask, true);
                this.orderedTaskQueue.put(downloadTask);
                return;
            }
            if (downloadInfo.getEnqueueType() == EnqueueType.ENQUEUE_TAIL) {
                if (this.orderedTaskQueue.getFirst().getDownloadId() == downloadTask.getDownloadId() && isDownloading(downloadTask.getDownloadId())) {
                    return;
                }
                Iterator<DownloadTask> it = this.orderedTaskQueue.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DownloadTask next = it.next();
                    if (next != null && next.getDownloadId() == downloadTask.getDownloadId()) {
                        it.remove();
                        break;
                    }
                }
                this.orderedTaskQueue.put(downloadTask);
                new DownloadStatusHandler(downloadTask, this.mainHandler).onPrepare();
                return;
            }
            DownloadTask first = this.orderedTaskQueue.getFirst();
            if (first.getDownloadId() == downloadTask.getDownloadId() && isDownloading(downloadTask.getDownloadId())) {
                return;
            }
            pause(first.getDownloadId());
            tryDownload(downloadTask, true);
            if (first.getDownloadId() != downloadTask.getDownloadId()) {
                this.orderedTaskQueue.putFirst(downloadTask);
            }
        } catch (InterruptedException unused) {
        }
    }

    public synchronized DownloadInfo getDownloadInfo(int i) {
        DownloadInfo downloadInfo;
        DownloadTask downloadTask;
        downloadInfo = this.downloadCache.getDownloadInfo(i);
        if (downloadInfo == null && (downloadTask = this.downloadTaskMap.get(i)) != null) {
            downloadInfo = downloadTask.getDownloadInfo();
        }
        return downloadInfo;
    }

    public synchronized List<DownloadInfo> getDownloadInfoList(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List<DownloadInfo> downloadInfoList = this.downloadCache.getDownloadInfoList(str);
        if (downloadInfoList != null && !downloadInfoList.isEmpty()) {
            return downloadInfoList;
        }
        ArrayList arrayList = new ArrayList();
        int size = this.downloadTaskMap.size();
        for (int i = 0; i < size; i++) {
            DownloadTask downloadTaskValueAt = this.downloadTaskMap.valueAt(i);
            if (downloadTaskValueAt != null && downloadTaskValueAt.getDownloadInfo() != null && str.equals(downloadTaskValueAt.getDownloadInfo().getUrl())) {
                arrayList.add(downloadTaskValueAt.getDownloadInfo());
            }
        }
        return arrayList;
    }

    public synchronized boolean pause(int i) {
        Logger.d(TAG, "pause id=" + i);
        DownloadInfo downloadInfo = this.downloadCache.getDownloadInfo(i);
        if (downloadInfo != null && downloadInfo.getStatus() == 11) {
            return false;
        }
        synchronized (this.downloadTaskMap) {
            doPause(i);
        }
        if (downloadInfo == null) {
            DownloadTask downloadTask = this.downloadTaskMap.get(i);
            if (downloadTask != null) {
                new DownloadStatusHandler(downloadTask, this.mainHandler).onPause();
                return true;
            }
        } else {
            cancelAlarm(downloadInfo);
            if (downloadInfo.getStatus() == 1) {
                DownloadTask downloadTask2 = this.downloadTaskMap.get(i);
                if (downloadTask2 != null) {
                    new DownloadStatusHandler(downloadTask2, this.mainHandler).onPause();
                    return true;
                }
            } else if (DownloadStatus.isDownloading(downloadInfo.getStatus())) {
                downloadInfo.setStatus(-2);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean cancel(int i, boolean z) {
        DownloadTask downloadTask = this.downloadTaskMap.get(i);
        if (downloadTask == null && DownloadExpSwitchCode.isSwitchEnable(65536)) {
            downloadTask = getDownloadTask(i);
        }
        if (downloadTask != null) {
            if (!DownloadSetting.obtain(i).optBugFix(DownloadSettingKeys.BugFix.FIX_ON_CANCEL_CALL_TWICE, true)) {
                new DownloadStatusHandler(downloadTask, this.mainHandler).onCancel();
            }
            final DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
            final SparseArray<IDownloadListener> downloadListeners = downloadTask.getDownloadListeners(ListenerType.MAIN);
            final SparseArray<IDownloadListener> downloadListeners2 = downloadTask.getDownloadListeners(ListenerType.NOTIFICATION);
            this.mainHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.impls.AbsDownloadEngine.1
                @Override // java.lang.Runnable
                public void run() {
                    if (downloadListeners != null) {
                        synchronized (downloadListeners) {
                            for (int i2 = 0; i2 < downloadListeners.size(); i2++) {
                                IDownloadListener iDownloadListener = (IDownloadListener) downloadListeners.get(downloadListeners.keyAt(i2));
                                if (iDownloadListener != null) {
                                    iDownloadListener.onCanceled(downloadInfo);
                                }
                            }
                        }
                    }
                    if (downloadInfo == null || !downloadInfo.canShowNotification() || downloadListeners2 == null) {
                        return;
                    }
                    synchronized (downloadListeners2) {
                        for (int i3 = 0; i3 < downloadListeners2.size(); i3++) {
                            IDownloadListener iDownloadListener2 = (IDownloadListener) downloadListeners2.get(downloadListeners2.keyAt(i3));
                            if (iDownloadListener2 != null) {
                                iDownloadListener2.onCanceled(downloadInfo);
                            }
                        }
                    }
                }
            });
        }
        DownloadInfo downloadInfo2 = this.downloadCache.getDownloadInfo(i);
        if (DownloadExpSwitchCode.isSwitchEnable(65536)) {
            if (downloadInfo2 != null) {
                downloadInfo2.setStatus(-4);
            }
        } else if (downloadInfo2 != null && DownloadStatus.isDownloading(downloadInfo2.getStatus())) {
            downloadInfo2.setStatus(-4);
        }
        clearDownloadData(i, z);
        return true;
    }

    public synchronized boolean resume(int i) {
        DownloadTask downloadTask = this.downloadTaskMap.get(i);
        if (downloadTask != null) {
            DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
            if (downloadInfo != null) {
                downloadInfo.setDownloadFromReserveWifi(false);
            }
            tryDownload(downloadTask);
        } else {
            restart(i);
        }
        return true;
    }

    public synchronized boolean restart(int i) {
        DownloadTask downloadTask = this.failedDownloadTaskMap.get(i);
        if (downloadTask == null) {
            downloadTask = this.retryDelayDownloadTaskMap.get(i);
        }
        if (downloadTask == null) {
            return false;
        }
        DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
        if (downloadInfo != null) {
            downloadInfo.setDownloadFromReserveWifi(false);
        }
        tryDownload(downloadTask);
        return true;
    }

    private void cancelAlarm(DownloadInfo downloadInfo) {
        if (downloadInfo != null) {
            try {
                if (downloadInfo.getStatus() == 7 || downloadInfo.getRetryDelayStatus() != RetryDelayStatus.DELAY_RETRY_NONE) {
                    downloadInfo.setStatus(5);
                    downloadInfo.setRetryDelayStatus(RetryDelayStatus.DELAY_RETRY_NONE);
                    Logger.d(TAG, "cancelAlarm");
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public synchronized void setDownloadNotificationEventListener(int i, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        DownloadTask downloadTask = this.downloadTaskMap.get(i);
        if (downloadTask != null) {
            downloadTask.setNotificationEventListener(iDownloadNotificationEventListener);
        }
    }

    public synchronized INotificationClickCallback getNotificationClickCallback(int i) {
        DownloadTask downloadTask = this.downloadTaskMap.get(i);
        if (downloadTask != null) {
            return downloadTask.getNotificationClickCallback();
        }
        DownloadTask downloadTask2 = this.successDownloadTaskMap.get(i);
        if (downloadTask2 != null) {
            return downloadTask2.getNotificationClickCallback();
        }
        DownloadTask downloadTask3 = this.failedDownloadTaskMap.get(i);
        if (downloadTask3 != null) {
            return downloadTask3.getNotificationClickCallback();
        }
        DownloadTask downloadTask4 = this.retryDelayDownloadTaskMap.get(i);
        if (downloadTask4 != null) {
            return downloadTask4.getNotificationClickCallback();
        }
        DownloadTask downloadTask5 = this.waitingAsyncDownloadTaskMap.get(i);
        if (downloadTask5 == null) {
            return null;
        }
        return downloadTask5.getNotificationClickCallback();
    }

    public synchronized IDownloadNotificationEventListener getDownloadNotificationEventListener(int i) {
        DownloadTask downloadTask = this.downloadTaskMap.get(i);
        if (downloadTask != null) {
            return downloadTask.getNotificationEventListener();
        }
        DownloadTask downloadTask2 = this.successDownloadTaskMap.get(i);
        if (downloadTask2 != null) {
            return downloadTask2.getNotificationEventListener();
        }
        DownloadTask downloadTask3 = this.failedDownloadTaskMap.get(i);
        if (downloadTask3 != null) {
            return downloadTask3.getNotificationEventListener();
        }
        DownloadTask downloadTask4 = this.retryDelayDownloadTaskMap.get(i);
        if (downloadTask4 != null) {
            return downloadTask4.getNotificationEventListener();
        }
        DownloadTask downloadTask5 = this.waitingAsyncDownloadTaskMap.get(i);
        if (downloadTask5 == null) {
            return null;
        }
        return downloadTask5.getNotificationEventListener();
    }

    public synchronized IDownloadFileUriProvider getDownloadFileUriProvider(int i) {
        DownloadTask downloadTask = this.downloadTaskMap.get(i);
        if (downloadTask != null) {
            return downloadTask.getFileUriProvider();
        }
        DownloadTask downloadTask2 = this.successDownloadTaskMap.get(i);
        if (downloadTask2 != null) {
            return downloadTask2.getFileUriProvider();
        }
        DownloadTask downloadTask3 = this.failedDownloadTaskMap.get(i);
        if (downloadTask3 != null) {
            return downloadTask3.getFileUriProvider();
        }
        DownloadTask downloadTask4 = this.retryDelayDownloadTaskMap.get(i);
        if (downloadTask4 != null) {
            return downloadTask4.getFileUriProvider();
        }
        DownloadTask downloadTask5 = this.waitingAsyncDownloadTaskMap.get(i);
        if (downloadTask5 == null) {
            return null;
        }
        return downloadTask5.getFileUriProvider();
    }

    public synchronized boolean retryDelayStart(int i) {
        DownloadInfo downloadInfo;
        DownloadTask downloadTask = this.retryDelayDownloadTaskMap.get(i);
        if (downloadTask != null && (downloadInfo = downloadTask.getDownloadInfo()) != null) {
            if (downloadInfo.canStartRetryDelayTask()) {
                tryDownload(downloadTask, false);
            }
            return true;
        }
        DownloadInfo downloadInfo2 = this.downloadCache.getDownloadInfo(i);
        if (downloadInfo2 != null && downloadInfo2.canStartRetryDelayTask()) {
            tryDownload(new DownloadTask(downloadInfo2), false);
        }
        return false;
    }

    public synchronized boolean restartAsyncWaitingTask(int i) {
        DownloadInfo downloadInfo;
        DownloadTask downloadTask = this.waitingAsyncDownloadTaskMap.get(i);
        if (downloadTask == null || (downloadInfo = downloadTask.getDownloadInfo()) == null) {
            return false;
        }
        if (downloadInfo.canReStartAsyncTask()) {
            tryDownload(downloadTask);
        }
        return true;
    }

    public synchronized void restartAllFailedDownloadTasks(List<String> list) {
        DownloadInfo downloadInfo;
        try {
            boolean zIsWifi = DownloadExpSwitchCode.isSwitchEnable(DownloadExpSwitchCode.BUGFIX_ONLY_WIFI) ? DownloadUtils.isWifi(DownloadComponentManager.getAppContext()) : true;
            for (int i = 0; i < this.failedDownloadTaskMap.size(); i++) {
                DownloadTask downloadTask = this.failedDownloadTaskMap.get(this.failedDownloadTaskMap.keyAt(i));
                if (downloadTask != null && (downloadInfo = downloadTask.getDownloadInfo()) != null && downloadInfo.getMimeType() != null && list.contains(downloadInfo.getMimeType()) && (!downloadInfo.isOnlyWifi() || zIsWifi)) {
                    downloadInfo.setAutoResumed(true);
                    downloadInfo.setShowNotificationForNetworkResumed(true);
                    tryDownload(downloadTask);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void restartAllPauseReserveOnWifiDownloadTasks(List<String> list) {
        DownloadInfo downloadInfo;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (DownloadUtils.isWifi(DownloadComponentManager.getAppContext())) {
            for (int i = 0; i < this.downloadTaskMap.size(); i++) {
                DownloadTask downloadTask = this.downloadTaskMap.get(this.downloadTaskMap.keyAt(i));
                if (downloadTask != null && (downloadInfo = downloadTask.getDownloadInfo()) != null && downloadInfo.getMimeType() != null && list.contains(downloadInfo.getMimeType()) && isPauseReserveOnWifi(downloadInfo)) {
                    downloadInfo.setAutoResumed(true);
                    downloadInfo.setShowNotificationForNetworkResumed(true);
                    tryDownload(downloadTask);
                    downloadInfo.setDownloadFromReserveWifi(true);
                    IReserveWifiStatusListener reserveWifiStatusListener = Downloader.getInstance(DownloadComponentManager.getAppContext()).getReserveWifiStatusListener();
                    if (reserveWifiStatusListener != null) {
                        reserveWifiStatusListener.onStatusChanged(downloadInfo, 5, 2);
                    }
                }
            }
        }
    }

    private boolean isPauseReserveOnWifi(DownloadInfo downloadInfo) {
        if (downloadInfo != null && downloadInfo.statusInPause()) {
            return downloadInfo.isPauseReserveOnWifi();
        }
        return false;
    }

    public void shutDown() {
        List<Integer> allAliveDownloadIds = getAllAliveDownloadIds();
        if (allAliveDownloadIds == null) {
            return;
        }
        Iterator<Integer> it = allAliveDownloadIds.iterator();
        while (it.hasNext()) {
            pause(it.next().intValue());
        }
    }

    public void clearDownloadData(final int i, final boolean z) {
        DownloadInfo downloadInfo = this.downloadCache.getDownloadInfo(i);
        if (downloadInfo != null) {
            cancelAlarm(downloadInfo);
        }
        this.mainHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.impls.AbsDownloadEngine.2
            @Override // java.lang.Runnable
            public void run() {
                DownloadNotificationManager.getInstance().cancelNotification(i);
            }
        });
        DownloadComponentManager.submitCPUTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.impls.AbsDownloadEngine.3
            @Override // java.lang.Runnable
            public void run() {
                DownloadTask downloadTask;
                if (AbsDownloadEngine.this.doCancel(i) == null && (downloadTask = AbsDownloadEngine.this.getDownloadTask(i)) != null) {
                    DownloadInfo downloadInfo2 = downloadTask.getDownloadInfo();
                    SparseArray<IDownloadListener> downloadListeners = downloadTask.getDownloadListeners(ListenerType.SUB);
                    if (downloadListeners != null) {
                        synchronized (downloadListeners) {
                            for (int i2 = 0; i2 < downloadListeners.size(); i2++) {
                                IDownloadListener iDownloadListener = downloadListeners.get(downloadListeners.keyAt(i2));
                                if (iDownloadListener != null) {
                                    iDownloadListener.onCanceled(downloadInfo2);
                                }
                            }
                        }
                    }
                }
                AbsDownloadEngine.this.clearDownloadDataInSubThread(i, z);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void clearDownloadDataInSubThread(int i, boolean z) {
        Logger.d(TAG, "clearDownloadDataInSubThread::id=" + i + " deleteTargetFile=" + z);
        try {
            DownloadInfo downloadInfo = this.downloadCache.getDownloadInfo(i);
            if (downloadInfo != null) {
                if (z) {
                    DownloadUtils.deleteAllDownloadFiles(downloadInfo);
                } else {
                    DownloadUtils.deleteFile(downloadInfo.getTempPath(), downloadInfo.getTempName());
                }
                downloadInfo.erase();
            }
            try {
                this.downloadCache.removeDownloadTaskData(i);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
            refreshDownloadTaskMap(i, 0, -4);
            if (this.failedDownloadTaskMap.get(i) != null) {
                this.failedDownloadTaskMap.remove(i);
            }
            if (this.successDownloadTaskMap.get(i) != null) {
                this.successDownloadTaskMap.remove(i);
            }
            this.pengingTaskCache.remove(Integer.valueOf(i));
            DownloadSetting.removeTaskDownloadSetting(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void resetDownloadData(final int i, final boolean z) {
        DownloadInfo downloadInfo = this.downloadCache.getDownloadInfo(i);
        if (downloadInfo != null) {
            cancelAlarm(downloadInfo);
        }
        this.mainHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.impls.AbsDownloadEngine.4
            @Override // java.lang.Runnable
            public void run() {
                DownloadNotificationManager.getInstance().cancelNotification(i);
            }
        });
        DownloadComponentManager.submitCPUTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.impls.AbsDownloadEngine.5
            @Override // java.lang.Runnable
            public void run() {
                AbsDownloadEngine.this.doCancel(i);
                AbsDownloadEngine.this.resetDownloadDataInSubThread(i, z);
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetDownloadDataInSubThread(int i, boolean z) {
        try {
            DownloadInfo downloadInfo = this.downloadCache.getDownloadInfo(i);
            if (downloadInfo != null) {
                DownloadUtils.deleteAllDownloadFiles(downloadInfo, z);
                downloadInfo.erase();
            }
            try {
                this.downloadCache.removeAllDownloadChunk(i);
                this.downloadCache.updateDownloadInfo(downloadInfo);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
            if (this.failedDownloadTaskMap.get(i) != null) {
                this.failedDownloadTaskMap.remove(i);
            }
            if (this.successDownloadTaskMap.get(i) != null) {
                this.successDownloadTaskMap.remove(i);
            }
            this.pengingTaskCache.remove(Integer.valueOf(i));
            DownloadSetting.removeTaskDownloadSetting(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void forceDownloadIgnoreRecommendSize(int i) {
        DownloadInfo downloadInfo;
        DownloadTask downloadTask = this.downloadTaskMap.get(i);
        if (downloadTask != null && (downloadInfo = downloadTask.getDownloadInfo()) != null) {
            downloadInfo.setForceIgnoreRecommendSize(true);
            tryDownload(downloadTask);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isInDownloadTaskPool(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 == 0) goto L18
            android.util.SparseArray<com.ss.android.socialbase.downloader.model.DownloadTask> r0 = r1.downloadTaskMap     // Catch: java.lang.Throwable -> L15
            java.lang.Object r0 = r0.get(r2)     // Catch: java.lang.Throwable -> L15
            if (r0 != 0) goto L13
            android.util.SparseArray<com.ss.android.socialbase.downloader.model.DownloadTask> r0 = r1.failedDownloadTaskMap     // Catch: java.lang.Throwable -> L15
            java.lang.Object r2 = r0.get(r2)     // Catch: java.lang.Throwable -> L15
            if (r2 == 0) goto L18
        L13:
            r2 = 1
            goto L19
        L15:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L18:
            r2 = 0
        L19:
            monitor-exit(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.impls.AbsDownloadEngine.isInDownloadTaskPool(int):boolean");
    }

    public synchronized void removeDownloadListener(int i, int i2, IDownloadListener iDownloadListener, ListenerType listenerType, boolean z) {
        DownloadTask downloadTask = getDownloadTask(i);
        if (downloadTask == null) {
            downloadTask = this.pengingTaskCache.get(Integer.valueOf(i));
        }
        if (downloadTask != null) {
            downloadTask.removeDownloadListener(i2, iDownloadListener, listenerType, z);
        }
    }

    public synchronized void addDownloadListener(int i, int i2, IDownloadListener iDownloadListener, ListenerType listenerType, boolean z) {
        addDownloadListener(i, i2, iDownloadListener, listenerType, z, true);
    }

    public synchronized void addDownloadListener(int i, int i2, final IDownloadListener iDownloadListener, ListenerType listenerType, boolean z, boolean z2) {
        DownloadInfo downloadInfo;
        DownloadTask downloadTask = getDownloadTask(i);
        if (downloadTask != null) {
            downloadTask.addDownloadListener(i2, iDownloadListener, listenerType, z);
            final DownloadInfo downloadInfo2 = downloadTask.getDownloadInfo();
            if (z2 && downloadInfo2 != null && !isDownloading(i) && (listenerType == ListenerType.MAIN || listenerType == ListenerType.NOTIFICATION)) {
                boolean z3 = true;
                if (listenerType == ListenerType.NOTIFICATION && !downloadInfo2.canShowNotification()) {
                    z3 = false;
                }
                if (z3) {
                    this.mainHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.impls.AbsDownloadEngine.6
                        @Override // java.lang.Runnable
                        public void run() {
                            if (iDownloadListener != null) {
                                if (downloadInfo2.getStatus() == -3) {
                                    iDownloadListener.onSuccessed(downloadInfo2);
                                } else if (downloadInfo2.getStatus() == -1) {
                                    iDownloadListener.onFailed(downloadInfo2, new BaseException(1000, "try add listener for failed task"));
                                }
                            }
                        }
                    });
                }
            }
        } else if (DownloadExpSwitchCode.isSwitchEnable(32768) && (downloadInfo = this.downloadCache.getDownloadInfo(i)) != null && downloadInfo.getStatus() != -3) {
            DownloadTask downloadTask2 = this.pengingTaskCache.get(Integer.valueOf(i));
            if (downloadTask2 == null) {
                downloadTask2 = new DownloadTask(downloadInfo);
                this.pengingTaskCache.put(Integer.valueOf(i), downloadTask2);
            }
            downloadTask2.addDownloadListener(i2, iDownloadListener, listenerType, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadTask getDownloadTask(int i) {
        DownloadTask downloadTask = this.downloadTaskMap.get(i);
        if (downloadTask != null) {
            return downloadTask;
        }
        DownloadTask downloadTask2 = this.failedDownloadTaskMap.get(i);
        if (downloadTask2 != null) {
            return downloadTask2;
        }
        DownloadTask downloadTask3 = this.successDownloadTaskMap.get(i);
        if (downloadTask3 != null) {
            return downloadTask3;
        }
        DownloadTask downloadTask4 = this.retryDelayDownloadTaskMap.get(i);
        return downloadTask4 == null ? this.waitingAsyncDownloadTaskMap.get(i) : downloadTask4;
    }

    public List<DownloadInfo> getDownloadingDownloadInfosWithMimeType(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<Integer> it = getAllAliveDownloadIds().iterator();
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            DownloadInfo downloadInfo = getDownloadInfo(it.next().intValue());
            if (downloadInfo != null && str.equals(downloadInfo.getMimeType())) {
                arrayList.add(downloadInfo);
            }
        }
        return arrayList;
    }

    private void notifyDownloadTaskStatus(int i, BaseException baseException, DownloadTask downloadTask) {
        if (downloadTask != null) {
            DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
            SparseArray<IDownloadListener> downloadListeners = downloadTask.getDownloadListeners(ListenerType.MAIN);
            SparseArray<IDownloadListener> downloadListeners2 = downloadTask.getDownloadListeners(ListenerType.NOTIFICATION);
            boolean z = downloadTask.canShowNotification() || downloadInfo.isAutoInstallWithoutNotification();
            DownloadListenerUtils.notifyListener(i, downloadListeners, true, downloadInfo, baseException);
            DownloadListenerUtils.notifyListener(i, downloadListeners2, z, downloadInfo, baseException);
        }
    }

    private void tryDownloadNextTaskInQueue(int i) {
        DownloadTask first;
        if (this.orderedTaskQueue.isEmpty()) {
            return;
        }
        DownloadTask first2 = this.orderedTaskQueue.getFirst();
        if (first2 != null && first2.getDownloadId() == i) {
            this.orderedTaskQueue.poll();
        }
        if (this.orderedTaskQueue.isEmpty() || (first = this.orderedTaskQueue.getFirst()) == null) {
            return;
        }
        tryDownload(first, true);
    }

    private void removeTask(int i, int i2) {
        Logger.d(TAG, "removeTask id: " + i + " listener hasCode: " + i2);
        if (i2 == 0) {
            this.downloadTaskMap.remove(i);
            this.downloadTaskWithListenerMap.remove(i);
            return;
        }
        SparseArray<DownloadTask> sparseArray = this.downloadTaskWithListenerMap.get(i);
        if (sparseArray != null) {
            sparseArray.remove(i2);
            Logger.d(TAG, "after downloadTaskWithListenerMap removeTask taskArray.size: " + sparseArray.size());
            if (sparseArray.size() == 0) {
                this.downloadTaskMap.remove(i);
                this.downloadTaskWithListenerMap.remove(i);
                return;
            }
            return;
        }
        this.downloadTaskMap.remove(i);
    }

    public synchronized void refreshDownloadTaskMap(int i, int i2, int i3) {
        switch (i3) {
            case -7:
            case -1:
                DownloadTask downloadTask = this.downloadTaskMap.get(i);
                if (downloadTask != null) {
                    if (this.failedDownloadTaskMap.get(i) == null) {
                        this.failedDownloadTaskMap.put(i, downloadTask);
                    }
                    removeTask(i, i2);
                }
                tryDownloadNextTaskInQueue(i);
                break;
            case -6:
                this.successDownloadTaskMap.put(i, this.downloadTaskMap.get(i));
                removeTask(i, i2);
                break;
            case -4:
                removeTask(i, i2);
                tryDownloadNextTaskInQueue(i);
                break;
            case -3:
                this.successDownloadTaskMap.put(i, this.downloadTaskMap.get(i));
                removeTask(i, i2);
                tryDownloadNextTaskInQueue(i);
                break;
            case 7:
                DownloadTask downloadTask2 = this.downloadTaskMap.get(i);
                if (downloadTask2 != null) {
                    if (this.retryDelayDownloadTaskMap.get(i) == null) {
                        this.retryDelayDownloadTaskMap.put(i, downloadTask2);
                    }
                    removeTask(i, i2);
                }
                tryDownloadNextTaskInQueue(i);
                break;
            case 8:
                DownloadTask downloadTask3 = this.downloadTaskMap.get(i);
                if (downloadTask3 != null && this.waitingAsyncDownloadTaskMap.get(i) == null) {
                    this.waitingAsyncDownloadTaskMap.put(i, downloadTask3);
                }
                tryDownloadNextTaskInQueue(i);
                break;
        }
    }

    @Override // com.ss.android.socialbase.downloader.thread.WeakDownloadHandler.IHandler
    public void handleMsg(Message message) {
        int i = message.arg1;
        int i2 = message.arg2;
        Logger.d(TAG, "handleMsg id: " + i + " listener hasCode: " + i2);
        DownloadTask downloadTask = null;
        BaseException baseException = message.obj instanceof Exception ? (BaseException) message.obj : null;
        synchronized (this) {
            try {
                if (i2 == 0) {
                    downloadTask = this.downloadTaskMap.get(i);
                } else {
                    SparseArray<DownloadTask> sparseArray = this.downloadTaskWithListenerMap.get(i);
                    if (sparseArray != null) {
                        downloadTask = sparseArray.get(i2);
                    }
                }
                if (downloadTask == null) {
                    return;
                }
                notifyDownloadTaskStatus(message.what, baseException, downloadTask);
                refreshDownloadTaskMap(i, i2, message.what);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setThrottleNetSpeed(int i, long j) {
        DownloadInfo downloadInfo = this.downloadCache.getDownloadInfo(i);
        if (downloadInfo != null) {
            downloadInfo.setThrottleNetSpeed(j);
        }
        doSetThrottleNetSpeed(i, j);
    }
}
