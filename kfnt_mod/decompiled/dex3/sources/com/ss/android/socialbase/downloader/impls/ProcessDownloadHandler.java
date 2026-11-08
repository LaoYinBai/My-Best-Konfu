package com.ss.android.socialbase.downloader.impls;

import android.app.Notification;
import com.ss.android.socialbase.downloader.constants.ListenerType;
import com.ss.android.socialbase.downloader.depend.IDownloadFileUriProvider;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.depend.INotificationClickCallback;
import com.ss.android.socialbase.downloader.depend.ProcessCallback;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.downloader.IDownloadProxy;
import com.ss.android.socialbase.downloader.downloader.IDownloadServiceHandler;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadChunk;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ProcessDownloadHandler implements IDownloadProxy {
    private final boolean bugFixServiceAlive;
    private final IDownloadCache downloadCache;
    private final AbsDownloadEngine downloadEngine;
    private final IDownloadServiceHandler downloadServiceHandler;

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void startService() {
    }

    public ProcessDownloadHandler() {
        this(false);
    }

    public ProcessDownloadHandler(boolean z) {
        this.downloadEngine = DownloadComponentManager.getDownloadEngine();
        this.downloadCache = DownloadComponentManager.getDownloadCache();
        if (!z) {
            this.downloadServiceHandler = DownloadComponentManager.getDownloadServiceHandler();
        } else {
            this.downloadServiceHandler = DownloadComponentManager.getIndependentDownloadServiceHandler();
        }
        this.bugFixServiceAlive = DownloadSetting.obtainGlobal().optBugFix("service_alive", false);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void pause(int i) {
        if (this.downloadEngine != null) {
            this.downloadEngine.pause(i);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void cancel(int i, boolean z) {
        if (this.downloadEngine != null) {
            this.downloadEngine.cancel(i, z);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean canResume(int i) {
        if (this.downloadEngine != null) {
            return this.downloadEngine.isInDownloadTaskPool(i);
        }
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void resume(int i) {
        if (this.downloadEngine != null) {
            this.downloadEngine.resume(i);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void restart(int i) {
        if (this.downloadEngine != null) {
            this.downloadEngine.restart(i);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void pauseAll() {
        if (this.downloadEngine != null) {
            this.downloadEngine.shutDown();
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) {
        if (this.downloadCache != null) {
            return this.downloadCache.getFailedDownloadInfosWithMimeType(str);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public List<DownloadInfo> getAllDownloadInfo() {
        if (this.downloadCache != null) {
            return this.downloadCache.getAllDownloadInfo();
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void restartAllFailedDownloadTasks(List<String> list) {
        if (this.downloadEngine != null) {
            this.downloadEngine.restartAllFailedDownloadTasks(list);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void restartAllPauseReserveOnWifiDownloadTasks(List<String> list) {
        if (this.downloadEngine != null) {
            this.downloadEngine.restartAllPauseReserveOnWifiDownloadTasks(list);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public long getCurBytes(int i) {
        DownloadInfo downloadInfo;
        if (this.downloadCache == null || (downloadInfo = this.downloadCache.getDownloadInfo(i)) == null) {
            return 0L;
        }
        int chunkCount = downloadInfo.getChunkCount();
        if (chunkCount <= 1) {
            return downloadInfo.getCurBytes();
        }
        List<DownloadChunk> downloadChunk = this.downloadCache.getDownloadChunk(i);
        if (downloadChunk == null || downloadChunk.size() != chunkCount) {
            return 0L;
        }
        return DownloadUtils.getTotalOffset(downloadChunk);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public int getStatus(int i) {
        DownloadInfo downloadInfo;
        if (this.downloadEngine == null || (downloadInfo = this.downloadEngine.getDownloadInfo(i)) == null) {
            return 0;
        }
        return downloadInfo.getStatus();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean isDownloading(int i) {
        if (this.downloadEngine != null) {
            return this.downloadEngine.isDownloading(i);
        }
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public DownloadInfo getDownloadInfo(int i) {
        if (this.downloadEngine != null) {
            return this.downloadEngine.getDownloadInfo(i);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public List<DownloadInfo> getDownloadInfoList(String str) {
        if (this.downloadEngine != null) {
            return this.downloadEngine.getDownloadInfoList(str);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public List<DownloadChunk> getDownloadChunk(int i) {
        return this.downloadCache.getDownloadChunk(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public DownloadInfo getDownloadInfo(String str, String str2) {
        return getDownloadInfo(DownloadComponentManager.getDownloadId(str, str2));
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public int getDownloadId(String str, String str2) {
        return DownloadComponentManager.getDownloadId(str, str2);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) {
        if (this.downloadCache != null) {
            return this.downloadCache.getSuccessedDownloadInfosWithMimeType(str);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void clearDownloadData(int i, boolean z) {
        if (this.downloadEngine != null) {
            this.downloadEngine.clearDownloadData(i, z);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void resetDownloadData(int i, boolean z) {
        if (this.downloadEngine != null) {
            this.downloadEngine.resetDownloadData(i, z);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void forceDownloadIngoreRecommendSize(int i) {
        if (this.downloadEngine != null) {
            this.downloadEngine.forceDownloadIgnoreRecommendSize(i);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void addDownloadListener(int i, int i2, IDownloadListener iDownloadListener, ListenerType listenerType, boolean z) {
        if (this.downloadEngine != null) {
            this.downloadEngine.addDownloadListener(i, i2, iDownloadListener, listenerType, z);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void addDownloadListener(int i, int i2, IDownloadListener iDownloadListener, ListenerType listenerType, boolean z, boolean z2) {
        if (this.downloadEngine != null) {
            this.downloadEngine.addDownloadListener(i, i2, iDownloadListener, listenerType, z, z2);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void removeDownloadListener(int i, int i2, IDownloadListener iDownloadListener, ListenerType listenerType, boolean z) {
        if (this.downloadEngine != null) {
            this.downloadEngine.removeDownloadListener(i, i2, iDownloadListener, listenerType, z);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean isDownloadSuccessAndFileNotExist(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return false;
        }
        boolean zIsDownloadSuccessAndFileNotExist = DownloadUtils.isDownloadSuccessAndFileNotExist(downloadInfo.getStatus(), downloadInfo.getSavePath(), downloadInfo.getName());
        if (zIsDownloadSuccessAndFileNotExist) {
            if (DownloadExpSwitchCode.isSwitchEnable(DownloadExpSwitchCode.BACK_CLEAR_DATA)) {
                clearDownloadData(downloadInfo.getId(), true);
            } else {
                resetDownloadData(downloadInfo.getId(), true);
            }
        }
        return zIsDownloadSuccessAndFileNotExist;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void startForeground(int i, Notification notification) {
        if (this.downloadServiceHandler != null) {
            this.downloadServiceHandler.startForeground(i, notification);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void stopForeground(boolean z, boolean z2) {
        if (this.downloadServiceHandler != null) {
            this.downloadServiceHandler.stopForeground(z2);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean isServiceForeground() {
        if (this.downloadServiceHandler != null) {
            return this.downloadServiceHandler.isServiceForeground();
        }
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean isHttpServiceInit() {
        return DownloadComponentManager.isHttpServiceInit();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) {
        if (this.downloadCache != null) {
            return this.downloadCache.getUnCompletedDownloadInfosWithMimeType(str);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public List<DownloadInfo> getDownloadingDownloadInfosWithMimeType(String str) {
        if (this.downloadEngine != null) {
            return this.downloadEngine.getDownloadingDownloadInfosWithMimeType(str);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void tryDownload(DownloadTask downloadTask) {
        if (this.downloadServiceHandler != null) {
            this.downloadServiceHandler.tryDownload(downloadTask);
        } else if (downloadTask != null) {
            DownloadMonitorHelper.monitorSendWithTaskMonitor(downloadTask.getMonitorDepend(), downloadTask.getDownloadInfo(), new BaseException(1003, "downloadServiceHandler is null"), downloadTask.getDownloadInfo() != null ? downloadTask.getDownloadInfo().getStatus() : 0);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void tryDownloadWithEngine(DownloadTask downloadTask) {
        if (this.downloadServiceHandler != null) {
            this.downloadServiceHandler.tryDownloadWithEngine(downloadTask);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean retryDelayStart(int i) {
        if (this.downloadEngine != null) {
            return this.downloadEngine.retryDelayStart(i);
        }
        return false;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void setLogLevel(int i) {
        Logger.setLogLevel(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean isDownloadCacheSyncSuccess() {
        return this.downloadCache.isDownloadCacheSyncSuccess();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void syncDownloadInfo(DownloadInfo downloadInfo) {
        this.downloadCache.syncDownloadInfo(downloadInfo);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void syncDownloadChunks(int i, List<DownloadChunk> list) {
        this.downloadCache.syncDownloadChunks(i, list);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void setDownloadWithIndependentProcessStatus(int i, boolean z) {
        DownloadProcessDispatcher.getInstance().setDownloadWithIndependentProcessStatus(i, z);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public int getDownloadWithIndependentProcessStatus(int i) {
        return DownloadProcessDispatcher.getInstance().getDownloadWithIndependentProcessStatus(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void addDownloadChunk(DownloadChunk downloadChunk) {
        this.downloadCache.addDownloadChunk(downloadChunk);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean updateDownloadInfo(DownloadInfo downloadInfo) {
        return this.downloadCache.updateDownloadInfo(downloadInfo);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean removeDownloadInfo(int i) {
        return this.downloadCache.removeDownloadInfo(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void removeAllDownloadChunk(int i) {
        this.downloadCache.removeAllDownloadChunk(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void updateDownloadChunk(int i, int i2, long j) {
        this.downloadCache.updateDownloadChunk(i, i2, j);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void updateSubDownloadChunk(int i, int i2, int i3, long j) {
        this.downloadCache.updateSubDownloadChunk(i, i2, i3, j);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void updateSubDownloadChunkIndex(int i, int i2, int i3, int i4) {
        this.downloadCache.updateSubDownloadChunkIndex(i, i2, i3, i4);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean removeDownloadTaskData(int i) {
        return this.downloadCache.removeDownloadTaskData(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void clearData() {
        this.downloadCache.clearData();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) {
        this.downloadCache.syncDownloadInfoFromOtherCache(i, list);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public boolean isServiceAlive() {
        return this.bugFixServiceAlive && this.downloadServiceHandler != null && this.downloadServiceHandler.isServiceAlive();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void addProcessCallback(ProcessCallback processCallback) {
        DownloadComponentManager.addProcessCallback(processCallback);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void dispatchProcessCallback(int i, int i2) {
        if (DownloadComponentManager.getProcessCallbacks() != null) {
            for (ProcessCallback processCallback : DownloadComponentManager.getProcessCallbacks()) {
                if (processCallback != null) {
                    processCallback.callback(i2, i);
                }
            }
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public IDownloadNotificationEventListener getDownloadNotificationEventListener(int i) {
        if (this.downloadEngine != null) {
            return this.downloadEngine.getDownloadNotificationEventListener(i);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public INotificationClickCallback getNotificationClickCallback(int i) {
        INotificationClickCallback notificationClickCallback = this.downloadEngine != null ? this.downloadEngine.getNotificationClickCallback(i) : null;
        return notificationClickCallback == null ? DownloadComponentManager.getNotificationClickCallback() : notificationClickCallback;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void setDownloadNotificationEventListener(int i, IDownloadNotificationEventListener iDownloadNotificationEventListener) {
        if (this.downloadEngine != null) {
            this.downloadEngine.setDownloadNotificationEventListener(i, iDownloadNotificationEventListener);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public IDownloadFileUriProvider getDownloadFileUriProvider(int i) {
        if (this.downloadEngine != null) {
            return this.downloadEngine.getDownloadFileUriProvider(i);
        }
        return null;
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadProxy
    public void setThrottleNetSpeed(int i, long j) {
        if (this.downloadEngine != null) {
            this.downloadEngine.setThrottleNetSpeed(i, j);
        }
    }
}
