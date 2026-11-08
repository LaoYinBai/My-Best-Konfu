package com.mbridge.msdk.foundation.download.core;

import com.mbridge.msdk.foundation.download.DownloadMessage;
import com.mbridge.msdk.foundation.download.DownloadResponse;
import com.mbridge.msdk.foundation.download.database.DownloadModel;
import com.mbridge.msdk.foundation.download.database.IDatabaseHelper;
import com.mbridge.msdk.foundation.download.resource.MBResourceManager;
import com.mbridge.msdk.foundation.download.utils.Objects;
import com.mbridge.msdk.foundation.download.utils.Utils;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class FindFileFromCache implements IDownloadTask {
    private final IDatabaseHelper databaseHelper;
    private final String downloadId;
    private DownloadMessage downloadMessage;
    private final DownloadModel downloadModel;
    private final DownloadRequest request;

    private FindFileFromCache(DownloadRequest downloadRequest, DownloadModel downloadModel, IDatabaseHelper iDatabaseHelper, String str, DownloadMessage downloadMessage) {
        this.request = downloadRequest;
        this.downloadModel = downloadModel;
        this.databaseHelper = iDatabaseHelper;
        this.downloadId = str;
        this.downloadMessage = downloadMessage;
    }

    @Override // com.mbridge.msdk.foundation.download.core.IDownloadTask
    public DownloadResponse run() {
        if (Objects.isNull(this.downloadModel)) {
            return null;
        }
        DownloadResponse downloadResponse = new DownloadResponse();
        File file = new File(this.request.getCacheDirectoryPath() + this.downloadMessage.getSaveFileName());
        if (!file.exists()) {
            this.request.setDownloadedBytes(0L);
            GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, "移除数据库中的数据： 不存在或者有问题");
            this.databaseHelper.remove(this.downloadId);
            MBResourceManager.getInstance().deleteFile(file);
            downloadResponse.setSuccessful(false);
        } else if (MBResourceManager.getInstance().getFileSize(file) == this.downloadModel.getDownloadedBytes()) {
            if (Utils.getDownloadRate(this.downloadModel.getTotalBytes(), this.downloadModel.getDownloadedBytes()) >= this.downloadMessage.getDownloadRate()) {
                this.request.setTotalBytes(this.downloadModel.getTotalBytes());
                this.request.setDownloadedBytes(this.downloadModel.getDownloadedBytes());
                downloadResponse.setSuccessful(true);
            } else {
                this.request.setTotalBytes(this.downloadModel.getTotalBytes());
                this.request.setDownloadedBytes(this.downloadModel.getDownloadedBytes());
                downloadResponse.setSuccessful(false);
            }
        } else {
            this.request.setDownloadedBytes(0L);
            GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, "移除数据库中的数据： 大小不一致");
            this.databaseHelper.remove(this.downloadId);
            MBResourceManager.getInstance().deleteFile(file);
            downloadResponse.setSuccessful(false);
        }
        return downloadResponse;
    }

    static IDownloadTask create(DownloadRequest downloadRequest, DownloadModel downloadModel, IDatabaseHelper iDatabaseHelper, String str, DownloadMessage downloadMessage) {
        return new FindFileFromCache(downloadRequest, downloadModel, iDatabaseHelper, str, downloadMessage);
    }
}
