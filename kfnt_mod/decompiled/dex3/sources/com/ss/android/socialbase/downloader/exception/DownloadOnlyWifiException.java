package com.ss.android.socialbase.downloader.exception;

import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DownloadOnlyWifiException extends DownloadRetryNeedlessException {
    public DownloadOnlyWifiException() {
        super(DownloadErrorCode.ERROR_ONLY_WIFI, "download only wifi");
    }
}
