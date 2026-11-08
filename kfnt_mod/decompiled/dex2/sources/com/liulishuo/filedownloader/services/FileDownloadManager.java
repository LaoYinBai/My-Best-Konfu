package com.liulishuo.filedownloader.services;

import android.text.TextUtils;
import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.download.CustomComponentHolder;
import com.liulishuo.filedownloader.download.DownloadLaunchRunnable;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class FileDownloadManager implements IThreadPoolMonitor {
    private final FileDownloadDatabase mDatabase;
    private final FileDownloadThreadPool mThreadPool;

    FileDownloadManager() {
        CustomComponentHolder impl = CustomComponentHolder.getImpl();
        this.mDatabase = impl.getDatabaseInstance();
        this.mThreadPool = new FileDownloadThreadPool(impl.getMaxNetworkThreadCount());
    }

    public synchronized void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        FileDownloadModel fileDownloadModel;
        List<ConnectionModel> listFindConnectionModel;
        String targetFilePath;
        String tempPath;
        boolean z4 = true;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "request start the task with url(%s) path(%s) isDirectory(%B)", str, str2, Boolean.valueOf(z));
        }
        int iGenerateId = FileDownloadUtils.generateId(str, str2, z);
        FileDownloadModel fileDownloadModelFind = this.mDatabase.find(iGenerateId);
        if (z || fileDownloadModelFind != null) {
            fileDownloadModel = fileDownloadModelFind;
            listFindConnectionModel = null;
        } else {
            int iGenerateId2 = FileDownloadUtils.generateId(str, FileDownloadUtils.getParent(str2), true);
            FileDownloadModel fileDownloadModelFind2 = this.mDatabase.find(iGenerateId2);
            if (fileDownloadModelFind2 == null || !str2.equals(fileDownloadModelFind2.getTargetFilePath())) {
                listFindConnectionModel = null;
            } else {
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "task[%d] find model by dirCaseId[%d]", Integer.valueOf(iGenerateId), Integer.valueOf(iGenerateId2));
                }
                listFindConnectionModel = this.mDatabase.findConnectionModel(iGenerateId2);
            }
            fileDownloadModel = fileDownloadModelFind2;
        }
        if (FileDownloadHelper.inspectAndInflowDownloading(iGenerateId, fileDownloadModel, this, true)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "has already started download %d", Integer.valueOf(iGenerateId));
            }
            return;
        }
        if (fileDownloadModel != null) {
            targetFilePath = fileDownloadModel.getTargetFilePath();
        } else {
            targetFilePath = FileDownloadUtils.getTargetFilePath(str2, z, null);
        }
        String str3 = targetFilePath;
        if (FileDownloadHelper.inspectAndInflowDownloaded(iGenerateId, str3, z2, true)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "has already completed downloading %d", Integer.valueOf(iGenerateId));
            }
            return;
        }
        long soFar = fileDownloadModel != null ? fileDownloadModel.getSoFar() : 0L;
        if (fileDownloadModel != null) {
            tempPath = fileDownloadModel.getTempFilePath();
        } else {
            tempPath = FileDownloadUtils.getTempPath(str3);
        }
        if (FileDownloadHelper.inspectAndInflowConflictPath(iGenerateId, soFar, tempPath, str3, this)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "there is an another task with the same target-file-path %d %s", Integer.valueOf(iGenerateId), str3);
            }
            if (fileDownloadModel != null) {
                this.mDatabase.remove(iGenerateId);
                this.mDatabase.removeConnections(iGenerateId);
            }
            return;
        }
        if (fileDownloadModel != null && (fileDownloadModel.getStatus() == -2 || fileDownloadModel.getStatus() == -1 || fileDownloadModel.getStatus() == 1 || fileDownloadModel.getStatus() == 6 || fileDownloadModel.getStatus() == 2)) {
            if (fileDownloadModel.getId() != iGenerateId) {
                this.mDatabase.remove(fileDownloadModel.getId());
                this.mDatabase.removeConnections(fileDownloadModel.getId());
                fileDownloadModel.setId(iGenerateId);
                fileDownloadModel.setPath(str2, z);
                if (listFindConnectionModel != null) {
                    for (ConnectionModel connectionModel : listFindConnectionModel) {
                        connectionModel.setId(iGenerateId);
                        this.mDatabase.insertConnectionModel(connectionModel);
                    }
                }
            } else if (TextUtils.equals(str, fileDownloadModel.getUrl())) {
                z4 = false;
            } else {
                fileDownloadModel.setUrl(str);
            }
        } else {
            if (fileDownloadModel == null) {
                fileDownloadModel = new FileDownloadModel();
            }
            fileDownloadModel.setUrl(str);
            fileDownloadModel.setPath(str2, z);
            fileDownloadModel.setId(iGenerateId);
            fileDownloadModel.setSoFar(0L);
            fileDownloadModel.setTotal(0L);
            fileDownloadModel.setStatus((byte) 1);
            fileDownloadModel.setConnectionCount(1);
        }
        if (z4) {
            this.mDatabase.update(fileDownloadModel);
        }
        this.mThreadPool.execute(new DownloadLaunchRunnable.Builder().setModel(fileDownloadModel).setHeader(fileDownloadHeader).setThreadPoolMonitor(this).setMinIntervalMillis(Integer.valueOf(i2)).setCallbackProgressMaxCount(Integer.valueOf(i)).setForceReDownload(Boolean.valueOf(z2)).setWifiRequired(Boolean.valueOf(z3)).setMaxRetryTimes(Integer.valueOf(i3)).build());
    }

    public boolean isDownloading(String str, String str2) {
        return isDownloading(FileDownloadUtils.generateId(str, str2));
    }

    public boolean isDownloading(int i) {
        return isDownloading(this.mDatabase.find(i));
    }

    public boolean pause(int i) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "request pause the task %d", Integer.valueOf(i));
        }
        FileDownloadModel fileDownloadModelFind = this.mDatabase.find(i);
        if (fileDownloadModelFind == null) {
            return false;
        }
        fileDownloadModelFind.setStatus((byte) -2);
        this.mThreadPool.cancel(i);
        return true;
    }

    public void pauseAll() {
        List<Integer> allExactRunningDownloadIds = this.mThreadPool.getAllExactRunningDownloadIds();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "pause all tasks %d", Integer.valueOf(allExactRunningDownloadIds.size()));
        }
        Iterator<Integer> it = allExactRunningDownloadIds.iterator();
        while (it.hasNext()) {
            pause(it.next().intValue());
        }
    }

    public long getSoFar(int i) {
        FileDownloadModel fileDownloadModelFind = this.mDatabase.find(i);
        if (fileDownloadModelFind == null) {
            return 0L;
        }
        int connectionCount = fileDownloadModelFind.getConnectionCount();
        if (connectionCount <= 1) {
            return fileDownloadModelFind.getSoFar();
        }
        List<ConnectionModel> listFindConnectionModel = this.mDatabase.findConnectionModel(i);
        if (listFindConnectionModel == null || listFindConnectionModel.size() != connectionCount) {
            return 0L;
        }
        return ConnectionModel.getTotalOffset(listFindConnectionModel);
    }

    public long getTotal(int i) {
        FileDownloadModel fileDownloadModelFind = this.mDatabase.find(i);
        if (fileDownloadModelFind == null) {
            return 0L;
        }
        return fileDownloadModelFind.getTotal();
    }

    public byte getStatus(int i) {
        FileDownloadModel fileDownloadModelFind = this.mDatabase.find(i);
        if (fileDownloadModelFind == null) {
            return (byte) 0;
        }
        return fileDownloadModelFind.getStatus();
    }

    public boolean isIdle() {
        return this.mThreadPool.exactSize() <= 0;
    }

    public synchronized boolean setMaxNetworkThreadCount(int i) {
        return this.mThreadPool.setMaxNetworkThreadCount(i);
    }

    @Override // com.liulishuo.filedownloader.IThreadPoolMonitor
    public boolean isDownloading(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            return false;
        }
        boolean zIsInThreadPool = this.mThreadPool.isInThreadPool(fileDownloadModel.getId());
        if (FileDownloadStatus.isOver(fileDownloadModel.getStatus())) {
            if (!zIsInThreadPool) {
                return false;
            }
        } else if (!zIsInThreadPool) {
            FileDownloadLog.e(this, "%d status is[%s](not finish) & but not in the pool", Integer.valueOf(fileDownloadModel.getId()), Byte.valueOf(fileDownloadModel.getStatus()));
            return false;
        }
        return true;
    }

    @Override // com.liulishuo.filedownloader.IThreadPoolMonitor
    public int findRunningTaskIdBySameTempPath(String str, int i) {
        return this.mThreadPool.findRunningTaskIdBySameTempPath(str, i);
    }

    public boolean clearTaskData(int i) {
        if (i == 0) {
            FileDownloadLog.w(this, "The task[%d] id is invalid, can't clear it.", Integer.valueOf(i));
            return false;
        }
        if (isDownloading(i)) {
            FileDownloadLog.w(this, "The task[%d] is downloading, can't clear it.", Integer.valueOf(i));
            return false;
        }
        this.mDatabase.remove(i);
        this.mDatabase.removeConnections(i);
        return true;
    }

    public void clearAllTaskData() {
        this.mDatabase.clear();
    }
}
