package com.mbridge.msdk.foundation.download.core;

import com.mbridge.msdk.foundation.download.DownloadMessage;
import com.mbridge.msdk.foundation.download.DownloadProgress;
import com.mbridge.msdk.foundation.download.DownloadResponse;
import com.mbridge.msdk.foundation.download.DownloadStatus;
import com.mbridge.msdk.foundation.download.database.DownloadModel;
import com.mbridge.msdk.foundation.download.database.IDatabaseHelper;
import com.mbridge.msdk.foundation.download.utils.Objects;
import com.mbridge.msdk.foundation.download.utils.Utils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadTask implements IDownloadTask {
    private IDatabaseHelper databaseHelper;
    private volatile DownloadMessage downloadMessage;
    private DownloadModel downloadModel = null;
    private volatile DownloadRequest request;

    private DownloadTask(DownloadRequest downloadRequest, DownloadMessage downloadMessage, IDatabaseHelper iDatabaseHelper) {
        this.request = downloadRequest;
        this.downloadMessage = downloadMessage;
        this.databaseHelper = iDatabaseHelper;
    }

    static IDownloadTask create(DownloadRequest downloadRequest, DownloadMessage downloadMessage, IDatabaseHelper iDatabaseHelper) {
        return new DownloadTask(downloadRequest, downloadMessage, iDatabaseHelper);
    }

    @Override // com.mbridge.msdk.foundation.download.core.IDownloadTask
    public DownloadResponse run() throws InterruptedException {
        String downloadId = this.request.getDownloadId();
        if (this.request.getStatus() == DownloadStatus.CANCELLED) {
            DownloadResponse downloadResponse = new DownloadResponse();
            downloadResponse.setCancelled(true);
            return downloadResponse;
        }
        if (this.downloadMessage.getDownloadRate() == 0) {
            DownloadResponse downloadResponse2 = new DownloadResponse();
            downloadResponse2.setSuccessful(true);
            GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, String.format("资源的 downloadRate 为 0", new Object[0]));
            return downloadResponse2;
        }
        String cacheDirectoryPath = this.request.getCacheDirectoryPath();
        int cacheDirectorPathType = this.request.getCacheDirectorPathType();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.databaseHelper.find(downloadId, cacheDirectoryPath, new IDatabaseHelper.IDatabaseListener() { // from class: com.mbridge.msdk.foundation.download.core.DownloadTask.1
            @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper.IDatabaseListener
            public void onDatabase(DownloadModel downloadModel) {
                DownloadTask.this.downloadModel = downloadModel;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(10L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, "" + e.getMessage());
            countDownLatch.countDown();
        }
        GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, String.format("开始从最优缓存路径 %s 中查询文件", cacheDirectoryPath));
        DownloadResponse downloadResponseRun = FindFileFromCache.create(this.request, this.downloadModel, this.databaseHelper, downloadId, this.downloadMessage).run();
        switch (cacheDirectorPathType) {
            case 0:
                if (Objects.isNotNull(downloadResponseRun) && downloadResponseRun.isSuccessful()) {
                    long downloadedBytes = this.request.getDownloadedBytes();
                    long totalBytes = this.request.getTotalBytes();
                    sendProgress(this.request, this.downloadMessage, new DownloadProgress(downloadedBytes, totalBytes, Utils.getDownloadRate(totalBytes, downloadedBytes)));
                    GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, String.format("从 %s 查询到可用的文件", cacheDirectoryPath));
                    this.downloadModel = DownloadModel.create(this.downloadModel.getDownloadId(), this.downloadModel.getDownloadUrl(), this.downloadModel.getResourceUrl(), this.downloadModel.getEtag(), this.downloadModel.getSaveDirectorPath(), this.downloadModel.getSaveFileName(), this.request.getTotalBytes(), this.downloadModel.getDownloadedBytes(), this.downloadModel.getDownloadRate(), this.downloadModel.getUsageCounter() + 1, this.downloadModel.getDownloadResourceType());
                    this.databaseHelper.update(this.downloadModel, cacheDirectoryPath);
                    if (this.request.getStatus() == DownloadStatus.CANCELLED) {
                        downloadResponseRun.setCancelled(true);
                    }
                    return downloadResponseRun;
                }
                GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, String.format("开始从 %s 下载，已经下载的文件大小为 %d", this.downloadMessage.getDownloadUrl(), Long.valueOf(this.request.getDownloadedBytes())));
                return OkHTTPFileDownloadTask.create(this.request, this.downloadModel, this.databaseHelper, this.downloadMessage).run();
            case 1:
                if (Objects.isNotNull(downloadResponseRun) && downloadResponseRun.isSuccessful()) {
                    long totalBytes2 = this.request.getTotalBytes();
                    long downloadedBytes2 = this.request.getDownloadedBytes();
                    sendProgress(this.request, this.downloadMessage, new DownloadProgress(downloadedBytes2, totalBytes2, Utils.getDownloadRate(totalBytes2, downloadedBytes2)));
                    GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, String.format("从 %s 查询到文件", cacheDirectoryPath));
                    this.downloadModel = DownloadModel.create(this.downloadModel.getDownloadId(), this.downloadModel.getDownloadUrl(), this.downloadModel.getResourceUrl(), this.downloadModel.getEtag(), this.downloadModel.getSaveDirectorPath(), this.downloadModel.getSaveFileName(), this.request.getTotalBytes(), this.downloadModel.getDownloadedBytes(), this.downloadModel.getDownloadRate(), this.downloadModel.getUsageCounter() + 1, this.downloadModel.getDownloadResourceType());
                    this.databaseHelper.update(this.downloadModel, cacheDirectoryPath);
                    if (this.request.getStatus() == DownloadStatus.CANCELLED) {
                        downloadResponseRun.setCancelled(true);
                    }
                    return downloadResponseRun;
                }
                String directoryPathInternal = this.request.getDirectoryPathInternal();
                final CountDownLatch countDownLatch2 = new CountDownLatch(1);
                this.databaseHelper.find(downloadId, directoryPathInternal, new IDatabaseHelper.IDatabaseListener() { // from class: com.mbridge.msdk.foundation.download.core.DownloadTask.2
                    @Override // com.mbridge.msdk.foundation.download.database.IDatabaseHelper.IDatabaseListener
                    public void onDatabase(DownloadModel downloadModel) {
                        DownloadTask.this.downloadModel = downloadModel;
                        countDownLatch2.countDown();
                    }
                });
                try {
                    countDownLatch2.await(60L, TimeUnit.SECONDS);
                } catch (InterruptedException unused) {
                    countDownLatch2.countDown();
                }
                DownloadResponse downloadResponseRun2 = FindFileFromCache.create(this.request, this.downloadModel, this.databaseHelper, downloadId, this.downloadMessage).run();
                if (Objects.isNotNull(downloadResponseRun2) && downloadResponseRun2.isSuccessful()) {
                    long totalBytes3 = this.request.getTotalBytes();
                    long downloadedBytes3 = this.request.getDownloadedBytes();
                    sendProgress(this.request, this.downloadMessage, new DownloadProgress(downloadedBytes3, totalBytes3, Utils.getDownloadRate(totalBytes3, downloadedBytes3)));
                    GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, String.format("从 %s 查询到文件", cacheDirectoryPath));
                    this.downloadModel = DownloadModel.create(this.downloadModel.getDownloadId(), this.downloadModel.getDownloadUrl(), this.downloadModel.getResourceUrl(), this.downloadModel.getEtag(), this.downloadModel.getSaveDirectorPath(), this.downloadModel.getSaveFileName(), this.request.getTotalBytes(), this.downloadModel.getDownloadedBytes(), this.downloadModel.getDownloadRate(), this.downloadModel.getUsageCounter() + 1, this.downloadModel.getDownloadResourceType());
                    this.databaseHelper.update(this.downloadModel, cacheDirectoryPath);
                    if (this.request.getStatus() == DownloadStatus.CANCELLED) {
                        downloadResponseRun2.setCancelled(true);
                    }
                    return downloadResponseRun2;
                }
                GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, String.format("开始从 %s 下载，已经下载的文件大小为 %d", this.downloadMessage.getSaveFileName(), Long.valueOf(this.request.getDownloadedBytes())));
                return OkHTTPFileDownloadTask.create(this.request, this.downloadModel, this.databaseHelper, this.downloadMessage).run();
            default:
                return downloadResponseRun == null ? OkHTTPFileDownloadTask.create(this.request, this.downloadModel, this.databaseHelper, this.downloadMessage).run() : downloadResponseRun;
        }
    }

    private void sendProgress(DownloadRequest downloadRequest, DownloadMessage downloadMessage, DownloadProgress downloadProgress) {
        if (downloadRequest.getStatus() != DownloadStatus.CANCELLED) {
            downloadRequest.handlerProcessEvent(downloadMessage, downloadProgress);
        }
    }
}
