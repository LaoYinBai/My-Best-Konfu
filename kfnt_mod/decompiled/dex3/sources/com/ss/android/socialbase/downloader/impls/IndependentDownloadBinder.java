package com.ss.android.socialbase.downloader.impls;

import android.app.Notification;
import android.os.RemoteException;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider;
import com.ss.android.socialbase.downloader.depend.IDownloadAidlListener;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener;
import com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback;
import com.ss.android.socialbase.downloader.depend.ProcessAidlCallback;
import com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.ss.android.socialbase.downloader.downloader.IDownloadAidlService;
import com.ss.android.socialbase.downloader.downloader.IDownloadProxy;
import com.ss.android.socialbase.downloader.model.DownloadAidlTask;
import com.ss.android.socialbase.downloader.model.DownloadChunk;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import com.ss.android.socialbase.downloader.utils.IPCUtils;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class IndependentDownloadBinder extends IDownloadAidlService.Stub {
    private static final String TAG = "IndependentDownloadBinder";
    private final IDownloadProxy downloadProxy = new ProcessDownloadHandler(true);

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void tryDownload(DownloadAidlTask downloadAidlTask) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.tryDownloadWithEngine(IPCUtils.convertDownloadTaskFromAidl(downloadAidlTask));
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void pause(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.pause(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void cancel(int i, boolean z) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.cancel(i, z);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean canResume(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.canResume(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void resume(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.resume(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void restart(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.restart(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void pauseAll() throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.pauseAll();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public long getCurBytes(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return 0L;
        }
        return this.downloadProxy.getCurBytes(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public int getStatus(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return 0;
        }
        return this.downloadProxy.getStatus(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean isDownloading(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.isDownloading(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public DownloadInfo getDownloadInfo(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return this.downloadProxy.getDownloadInfo(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public List<DownloadInfo> getDownloadInfoList(String str) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return this.downloadProxy.getDownloadInfoList(str);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public List<DownloadChunk> getDownloadChunk(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return this.downloadProxy.getDownloadChunk(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public int getDownloadId(String str, String str2) throws RemoteException {
        if (this.downloadProxy == null) {
            return 0;
        }
        return this.downloadProxy.getDownloadId(str, str2);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public DownloadInfo getDownloadInfoByUrlAndPath(String str, String str2) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return this.downloadProxy.getDownloadInfo(str, str2);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return this.downloadProxy.getFailedDownloadInfosWithMimeType(str);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return this.downloadProxy.getSuccessedDownloadInfosWithMimeType(str);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public List<DownloadInfo> getDownloadingDownloadInfosWithMimeType(String str) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return this.downloadProxy.getDownloadingDownloadInfosWithMimeType(str);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public List<DownloadInfo> getAllDownloadInfo() throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return this.downloadProxy.getAllDownloadInfo();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void restartAllFailedDownloadTasks(List<String> list) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.restartAllFailedDownloadTasks(list);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void restartAllPauseReserveOnWifiDownloadTasks(List<String> list) throws RemoteException {
        if (this.downloadProxy != null) {
            this.downloadProxy.restartAllPauseReserveOnWifiDownloadTasks(list);
        }
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void clearDownloadData(int i, boolean z) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.clearDownloadData(i, z);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void resetDownloadData(int i, boolean z) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.clearDownloadData(i, z);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void forceDownloadIngoreRecommendSize(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.forceDownloadIngoreRecommendSize(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void removeDownloadListener(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.removeDownloadListener(i, i2, IPCUtils.convertListenerFromAidl(iDownloadAidlListener), DownloadUtils.convertListenerType(i3), z);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void addDownloadListener(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.addDownloadListener(i, i2, IPCUtils.convertListenerFromAidl(iDownloadAidlListener), DownloadUtils.convertListenerType(i3), z);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void addDownloadListener1(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z, boolean z2) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.addDownloadListener(i, i2, IPCUtils.convertListenerFromAidl(iDownloadAidlListener), DownloadUtils.convertListenerType(i3), z, z2);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean isDownloadSuccessAndFileNotExist(DownloadInfo downloadInfo) throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.isDownloadSuccessAndFileNotExist(downloadInfo);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void startForeground(int i, Notification notification) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.startForeground(i, notification);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void stopForeground(boolean z) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.stopForeground(true, z);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean isServiceForeground() throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.isServiceForeground();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void setThrottleNetSpeed(int i, long j) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.setThrottleNetSpeed(i, j);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean isHttpServiceInit() throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.isHttpServiceInit();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return this.downloadProxy.getUnCompletedDownloadInfosWithMimeType(str);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean retryDelayStart(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.retryDelayStart(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void setLogLevel(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.setLogLevel(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean isDownloadCacheSyncSuccess() throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.isDownloadCacheSyncSuccess();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void setDownloadWithIndependentProcessStatus(int i, boolean z) throws RemoteException {
        DownloadProcessDispatcher.getInstance().setDownloadIndependentProcessStatus(i, z);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public int getDownloadWithIndependentProcessStatus(int i) throws RemoteException {
        return DownloadProcessDispatcher.getInstance().getDownloadWithIndependentProcessStatusInner(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void addDownloadChunk(DownloadChunk downloadChunk) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.addDownloadChunk(downloadChunk);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean updateDownloadInfo(DownloadInfo downloadInfo) throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.updateDownloadInfo(downloadInfo);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean removeDownloadInfo(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.removeDownloadInfo(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void removeAllDownloadChunk(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.removeAllDownloadChunk(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void updateDownloadChunk(int i, int i2, long j) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.updateDownloadChunk(i, i2, j);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void updateSubDownloadChunk(int i, int i2, int i3, long j) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.updateSubDownloadChunk(i, i2, i3, j);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void updateSubDownloadChunkIndex(int i, int i2, int i3, int i4) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.updateSubDownloadChunkIndex(i, i2, i3, i4);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public boolean removeDownloadTaskData(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return false;
        }
        return this.downloadProxy.removeDownloadTaskData(i);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void clearData() throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.clearData();
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.syncDownloadInfoFromOtherCache(i, list);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void syncDownloadChunks(int i, List<DownloadChunk> list) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.syncDownloadChunks(i, list);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void addProcessCallback(ProcessAidlCallback processAidlCallback) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.addProcessCallback(IPCUtils.convertProcessAidlCallbackFromAidl(processAidlCallback));
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void dispatchProcessCallback(int i, int i2) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.dispatchProcessCallback(i, i2);
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public IDownloadNotificationEventAidlListener getDownloadNotificationEventListener(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return IPCUtils.convertDownloadNotificationEventListenerToAidl(this.downloadProxy.getDownloadNotificationEventListener(i));
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public INotificationClickAidlCallback getNotificationClickCallback(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return IPCUtils.convertNotificationClickCallbackToAidl(this.downloadProxy.getNotificationClickCallback(i));
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public void setDownloadNotificationEventListener(int i, IDownloadNotificationEventAidlListener iDownloadNotificationEventAidlListener) throws RemoteException {
        if (this.downloadProxy == null) {
            return;
        }
        this.downloadProxy.setDownloadNotificationEventListener(i, IPCUtils.convertDownloadNotificationEventListenerFromAidl(iDownloadNotificationEventAidlListener));
    }

    @Override // com.ss.android.socialbase.downloader.downloader.IDownloadAidlService
    public IDownloadAidlFileProvider getDownloadFileUriProvider(int i) throws RemoteException {
        if (this.downloadProxy == null) {
            return null;
        }
        return IPCUtils.convertFileProviderToAidl(this.downloadProxy.getDownloadFileUriProvider(i));
    }
}
