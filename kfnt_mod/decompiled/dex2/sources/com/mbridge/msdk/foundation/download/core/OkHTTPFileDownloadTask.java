package com.mbridge.msdk.foundation.download.core;

import android.text.TextUtils;
import com.mbridge.msdk.foundation.download.DownloadError;
import com.mbridge.msdk.foundation.download.DownloadMessage;
import com.mbridge.msdk.foundation.download.DownloadProgress;
import com.mbridge.msdk.foundation.download.DownloadResponse;
import com.mbridge.msdk.foundation.download.DownloadStatus;
import com.mbridge.msdk.foundation.download.database.DownloadModel;
import com.mbridge.msdk.foundation.download.database.IDatabaseHelper;
import com.mbridge.msdk.foundation.download.resource.MBResourceManager;
import com.mbridge.msdk.foundation.download.resource.stream.DownloadFileOutputStream;
import com.mbridge.msdk.foundation.download.utils.ILogger;
import com.mbridge.msdk.foundation.download.utils.Objects;
import com.mbridge.msdk.foundation.download.utils.Utils;
import com.mbridge.msdk.thrid.okhttp.Response;
import com.mbridge.msdk.thrid.okhttp.ResponseBody;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class OkHTTPFileDownloadTask implements IDownloadTask {
    private final IDatabaseHelper databaseHelper;
    private volatile DownloadMessage downloadMessage;
    private DownloadModel downloadModel;
    private final DownloadRequest downloadRequest;
    private DownloadResponse downloadResponse;
    private String etag = "";
    private InputStream inputStream;
    private DownloadFileOutputStream outputStream;
    private ResponseBody responseBody;

    private OkHTTPFileDownloadTask(DownloadRequest downloadRequest, DownloadModel downloadModel, IDatabaseHelper iDatabaseHelper, DownloadMessage downloadMessage) {
        this.downloadRequest = downloadRequest;
        this.downloadModel = downloadModel;
        this.databaseHelper = iDatabaseHelper;
        this.downloadMessage = downloadMessage;
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x03c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03db A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03d0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:204:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01cb  */
    @Override // com.mbridge.msdk.foundation.download.core.IDownloadTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.mbridge.msdk.foundation.download.DownloadResponse run() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1025
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.download.core.OkHTTPFileDownloadTask.run():com.mbridge.msdk.foundation.download.DownloadResponse");
    }

    private void handlerRequestSuccessful(String str, String str2, String str3, Response response, int i) throws IllegalAccessException, IOException {
        boolean z = !isSupportResume(i, this.downloadModel);
        if (z && response != null && this.downloadRequest != null && i == 206 && response.body() != null && response.body().contentLength() != this.downloadRequest.getTotalBytes() - this.downloadRequest.getDownloadedBytes()) {
            ILogger logger = GlobalComponent.getInstance().getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("移除： handlerRequestSuccessful： isSupportResume ");
            sb.append(z);
            sb.append(" etag: ");
            sb.append(this.etag);
            sb.append(" etag: ");
            sb.append(this.downloadModel == null ? "null" : this.downloadModel.getEtag());
            sb.append(" responseCode: ");
            sb.append(i);
            logger.log(IDownloadTask.TAG, sb.toString());
            this.downloadRequest.setDownloadedBytes(0L);
            this.downloadRequest.setTotalBytes(0L);
            if (Objects.isNotNull(this.databaseHelper)) {
                this.databaseHelper.remove(str3, str2);
            }
            this.downloadModel = null;
            MBResourceManager.getInstance().deleteFile(new File(this.downloadRequest.getCacheDirectoryPath() + this.downloadMessage.getSaveFileName()));
        }
        GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, "网络请求成功，开始读取数据");
        this.responseBody = response.body();
        if (Objects.isNull(this.responseBody)) {
            this.downloadResponse.setError(new DownloadError(new IOException("response body is null")));
            this.downloadRequest.setTotalBytes(0L);
            this.downloadRequest.setDownloadedBytes(0L);
            this.downloadResponse.setSuccessful(false);
            return;
        }
        long jContentLength = this.responseBody.contentLength();
        if (this.downloadRequest.getTotalBytes() == 0) {
            this.downloadRequest.setTotalBytes(jContentLength);
        }
        this.inputStream = this.responseBody.byteStream();
        if (Objects.isNotNull(this.inputStream)) {
            handleInputStream(str, str2, str3, this.etag);
        } else {
            this.downloadResponse.setError(new DownloadError(new IOException("response inputStream is null")));
            this.downloadResponse.setSuccessful(false);
        }
    }

    private void handleInputStream(String str, String str2, String str3, String str4) throws IllegalAccessException, IOException {
        String str5;
        String str6 = str2;
        if (Objects.isNull(this.downloadModel)) {
            this.downloadModel = DownloadModel.create(this.downloadRequest.getDownloadId(), this.downloadMessage.getDownloadUrl(), this.downloadMessage.getResourceUrl(), str4, str2, this.downloadMessage.getSaveFileName(), this.downloadRequest.getTotalBytes(), 0L, this.downloadMessage.getDownloadRate(), 1, this.downloadMessage.getDownloadResourceType());
            this.databaseHelper.insert(this.downloadModel);
        } else {
            this.downloadModel = DownloadModel.create(this.downloadModel.getDownloadId(), this.downloadModel.getDownloadUrl(), this.downloadMessage.getResourceUrl(), this.downloadModel.getEtag(), this.downloadModel.getSaveDirectorPath(), this.downloadModel.getSaveFileName(), this.downloadRequest.getTotalBytes(), this.downloadModel.getDownloadedBytes(), this.downloadMessage.getDownloadRate(), this.downloadModel.getUsageCounter() + 1, this.downloadMessage.getDownloadResourceType());
            this.databaseHelper.update(this.downloadModel, str6);
        }
        this.outputStream = MBResourceManager.getInstance().getDownloadFileOutputStream(new File(str6, str));
        this.outputStream.seek(this.downloadRequest.getDownloadedBytes());
        byte[] bArr = new byte[GlobalComponent.getInstance().getByteBufferSize()];
        while (true) {
            int i = this.inputStream.read(bArr);
            if (i == -1) {
                str5 = str6;
                break;
            }
            this.outputStream.write(bArr, 0, i);
            this.downloadRequest.setDownloadedBytes(this.downloadRequest.getDownloadedBytes() + i);
            this.outputStream.flushAndSync();
            int downloadRate = Utils.getDownloadRate(this.downloadRequest.getTotalBytes(), this.downloadRequest.getDownloadedBytes());
            str5 = str6;
            sendProgress(this.downloadRequest, this.downloadMessage, this.downloadRequest.getDownloadedBytes(), this.downloadRequest.getTotalBytes(), downloadRate);
            if (this.downloadMessage.getDownloadRate() != 100 && downloadRate >= this.downloadMessage.getDownloadRate()) {
                break;
            }
            if (this.downloadRequest.getStatus() == DownloadStatus.CANCELLED) {
                this.downloadResponse.setCancelled(true);
                break;
            }
            str6 = str5;
        }
        GlobalComponent.getInstance().getDatabaseHelper().updateProgress(str3, str5, DownloadModel.create(str3, this.downloadMessage.getDownloadUrl(), this.downloadMessage.getResourceUrl(), str4, str2, this.downloadMessage.getSaveFileName(), this.downloadRequest.getTotalBytes(), this.downloadRequest.getDownloadedBytes(), this.downloadMessage.getDownloadRate(), this.downloadModel.getUsageCounter(), this.downloadMessage.getDownloadResourceType()));
        if (this.downloadResponse.isCancelled()) {
            return;
        }
        this.downloadResponse.setSuccessful(true);
    }

    private void handlerException(String str, String str2, String str3, Exception exc) {
        if (Objects.isNotNull(this.databaseHelper)) {
            GlobalComponent.getInstance().getLogger().log(IDownloadTask.TAG, "移除：handlerException");
            this.databaseHelper.remove(str3, str2);
        }
        this.downloadResponse.setError(new DownloadError(exc));
        this.downloadResponse.setSuccessful(false);
    }

    public static IDownloadTask create(DownloadRequest downloadRequest, DownloadModel downloadModel, IDatabaseHelper iDatabaseHelper, DownloadMessage downloadMessage) {
        return new OkHTTPFileDownloadTask(downloadRequest, downloadModel, iDatabaseHelper, downloadMessage);
    }

    private boolean isSupportResume(int i, DownloadModel downloadModel) {
        return i == 206 && !TextUtils.isEmpty(this.etag) && (downloadModel == null || TextUtils.equals(this.etag, downloadModel.getEtag()));
    }

    private void sendProgress(DownloadRequest downloadRequest, DownloadMessage downloadMessage, long j, long j2, int i) {
        if (downloadRequest.getStatus() != DownloadStatus.CANCELLED) {
            downloadRequest.handlerProcessEvent(downloadMessage, new DownloadProgress(j, j2, i));
        }
    }
}
