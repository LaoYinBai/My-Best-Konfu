package com.mbridge.msdk.foundation.download.core;

import android.os.Build;
import android.text.TextUtils;
import com.mbridge.msdk.foundation.download.DownloadResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class SynchronizeAvailableDirectorPath implements IDownloadTask {
    private int cacheDirectorPathType;
    private String cacheDirectoryPath;
    private String cacheDirectoryPathExternal;
    private String cacheDirectoryPathInternal;
    private DownloadRequest downloadRequest;

    public SynchronizeAvailableDirectorPath(DownloadRequest downloadRequest) {
        this.downloadRequest = downloadRequest;
    }

    @Override // com.mbridge.msdk.foundation.download.core.IDownloadTask
    public DownloadResponse run() {
        this.cacheDirectoryPathExternal = this.downloadRequest.getDirectoryPathExternal();
        this.cacheDirectoryPathInternal = this.downloadRequest.getDirectoryPathInternal();
        if (TextUtils.isEmpty(this.cacheDirectoryPathInternal)) {
            throw new RuntimeException("必须指定 cacheDirectoryPathInternal");
        }
        try {
            try {
                if (TextUtils.isEmpty(this.cacheDirectoryPathExternal) || Build.VERSION.SDK_INT > 28) {
                    this.cacheDirectoryPath = this.cacheDirectoryPathInternal;
                    this.cacheDirectorPathType = 0;
                } else {
                    GlobalComponent.getInstance().getContext();
                    this.cacheDirectoryPath = this.cacheDirectoryPathInternal;
                    this.cacheDirectorPathType = 0;
                }
            } catch (Exception unused) {
                this.cacheDirectoryPath = this.cacheDirectoryPathInternal;
                this.cacheDirectorPathType = 0;
            }
            this.downloadRequest.setCacheDirectoryPath(this.cacheDirectoryPath);
            this.downloadRequest.setCacheDirectorPathType(this.cacheDirectorPathType);
            return null;
        } catch (Throwable th) {
            this.downloadRequest.setCacheDirectoryPath(this.cacheDirectoryPath);
            this.downloadRequest.setCacheDirectorPathType(this.cacheDirectorPathType);
            throw th;
        }
    }

    public static IDownloadTask create(DownloadRequest downloadRequest) {
        return new SynchronizeAvailableDirectorPath(downloadRequest);
    }
}
